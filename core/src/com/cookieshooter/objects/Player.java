package com.cookieshooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;
import com.cookieshooter.objects.base.Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.cookieshooter.common.Config.DEFAULT_FIRE_DELAY;


public class Player extends Object {

    //region Private variables

    private float width, height;

    private List<Bullet> bullets = new ArrayList<Bullet>();

    private float fireBulletDelay = DEFAULT_FIRE_DELAY;

    private int points;

    //endregion

    //region Constructors

    public Player(Viewport viewport, World world) {
        super(viewport, world);
        init();
    }

    //endregion Constructors

    //region Public methods

    //region Overrides

    @Override
    public void update(float deltaTime) {
        moveSprite();
        handleInput();
        updateBullets(deltaTime);
        updateFireBulletDelay(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), width * Config.OBJECT_RATIO, height * Config.OBJECT_RATIO);
    }

    @Override
    public void dispose() {
        texture.dispose();
        //todo: dispose bullets
    }

    @Override
    public void destroy() {

    }
    //endregion Overrides

    public void handleInput() {
        handleAccelerationInput();
        handleTouchInput();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points){
        this.points += points;
    }


    //endregion Public methods

    //region Private methods

    private void init() {
        initSize();
        initBody();
        initImage();
        points = 0;
    }

    private void initSize() {
        width = viewport.getWorldWidth() / Config.OBJECT_RATIO;
        height = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initBody() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth() / 2, height);
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        bodyDefinition.linearDamping = 0;
        bodyDefinition.angularDamping = 0;

        body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        fixtureDefinition.shape = shape;
        fixtureDefinition.friction = 0;
        body.createFixture(fixtureDefinition);
        body.setLinearVelocity(0, -10);
        body.setUserData(this);

        shape.dispose();
    }

    private void initImage() {
        texture = new Texture(AssetsPath.PLAYER);
        sprite = new Sprite(texture);
        update(0);
    }

    private float getAcceleration(float rotationRate) {
        return -1 * rotationRate;
    }

    private void moveBody(float acceleration) {
        body.setLinearVelocity(new Vector2(acceleration * width * Config.SPEED, body.getLinearVelocity().y));
    }

    private void moveSprite() {
        float spriteX = (body.getPosition().x - height) * Config.PPM;
        float spriteY = (body.getPosition().y - width) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    private void handleAccelerationInput() {
        moveBody(getAcceleration(Gdx.input.getAccelerometerX()));
    }

    private void handleTouchInput() {
        if (Gdx.input.justTouched()) {
            shoot();
        }
    }

    private void shoot() {
        if (canShoot()) {
            bullets.add(getBullet());
            fireBulletDelay = DEFAULT_FIRE_DELAY;
        }
    }

    private void updateBullets(float deltaTime) {
        for (Iterator<Bullet> bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
            Bullet bullet = bulletIterator.next();
            if (bullet.isOutOfViewport() ) {
                bullet.destroy();
                bulletIterator.remove();
                continue;
            }
            else if(bullet.getIsToDestroy()){
                addPoints(1);
                bullet.destroy();
                bulletIterator.remove();
                continue;
            }
            bullet.update(deltaTime);
        }
    }

    private Bullet getBullet() {
        return new Bullet(viewport, world, getBulletPosition());
    }

    private Vector2 getBulletPosition() {
        return new Vector2(
                body.getPosition().x,
                body.getPosition().y + (2*height)
        );
    }

    private void updateFireBulletDelay(float deltaTime) {
        fireBulletDelay -= deltaTime;
    }

    private boolean canShoot() {
        return fireBulletDelay < 0;
    }


    //endregion Private methods
}
