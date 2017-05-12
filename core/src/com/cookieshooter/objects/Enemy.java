package com.cookieshooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;
import com.cookieshooter.objects.base.Object;
import com.cookieshooter.utils.RandomVector;

public class Enemy extends Object {

    private TextureRegion[] animationFrames;
    private Animation animation;
    private Vector2 velocity;

    private float radius;
    private float elapsedTime;


    public Enemy(Viewport viewport, World world) {
        super(viewport, world);
        init();
    }

    private void init() {
        initSize();
        initVelocity();
        initBody();
        initImage();
        initAnimation();
        isToDestroy = false;
    }

    private void initSize() {
        radius = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initVelocity() {
        int minX = Config.MIN_X;
        int maxX = Config.MAX_X;
        int minY = Config.MIN_Y;
        int maxY = Config.MAX_Y;

        velocity = new RandomVector(minX, maxX, minY, maxY);
    }

    private void initBody() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth() / 2, radius + viewport.getWorldHeight());
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        bodyDefinition.angularDamping = 0;
        bodyDefinition.linearDamping = 0;
        body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDefinition.shape = shape;
        fixtureDefinition.friction = 0;
        fixtureDefinition.restitution = 1;
        body.createFixture(fixtureDefinition);
        body.setLinearVelocity(velocity);
        body.setUserData(this);

        shape.dispose();
    }

    private void initImage() {
        texture = new Texture(AssetsPath.ENEMY);
        sprite = new Sprite(texture);
        update(0);
    }

    private void initAnimation() {
        Texture tempTexture = new Texture(AssetsPath.CRASH_ENEMY);
        TextureRegion tempFrames[][] = TextureRegion.split(tempTexture, texture.getWidth(), texture.getHeight());

        animationFrames = new TextureRegion[4];
        int index = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                animationFrames[index++] = tempFrames[j][i];
            }
        }

        animation = new Animation(1f / 4f, animationFrames);
    }

    @Override
    public void update(float deltaTime) {
        float spriteX = (body.getPosition().x - radius) * Config.PPM;
        float spriteY = (body.getPosition().y - radius) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    @Override
    public void draw(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
//      batch.draw(sprite, sprite.getX(), sprite.getY(), radius * Config.OBJECT_RATIO, radius * Config.OBJECT_RATIO);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY(), radius * Config.OBJECT_RATIO, radius * Config.OBJECT_RATIO);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void destroy() {

        dispose();
        world.destroyBody(body);
    }

    public void setIsToDestroy(Boolean value) {
        isToDestroy = value;
    }

    public Boolean getIsToDestroy() {
        return isToDestroy;
    }

}
