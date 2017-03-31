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


public class Player extends Object {

    //region Private variables

    private float width, height;

    //endregion

    //region Constructors

    public Player(Viewport viewport, World world) {
        super(viewport, world);
        init();
    }

    //endregion Contructors

    //region Public methods

    //region Overrides

    @Override
    public void handleInput() {
        move(getAcceleration(Gdx.input.getAccelerometerX()));
    }

    @Override
    public void move(float acceleration) {
        body.setLinearVelocity(new Vector2(acceleration * width * Config.SPEED, body.getLinearVelocity().y));
    }

    @Override
    public void update() {
        moveSprite();
        handleInput();
    }

    //endregion Overrides

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), width * Config.OBJECT_RATIO, height * Config.OBJECT_RATIO);
    }

    public void dispose() {
        texture.dispose();
    }

    //endregion Public methods

    //region Private methods

    private void init() {
        initSize();
        initBody();
        initImage();
    }

    private void initSize() {
        width = viewport.getWorldWidth() / Config.OBJECT_RATIO;
        height = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initBody() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth() / 2, height);
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        fixtureDefinition.shape = shape;
        body.createFixture(fixtureDefinition);

        shape.dispose();
    }

    private void initImage() {
        texture = new Texture(AssetsPath.PLAYER);
        sprite = new Sprite(texture);
        update();
    }

    private float getAcceleration(float rotationRate) {
        return -1 * rotationRate;
    }

    private void moveSprite() {
        float spriteX = (body.getPosition().x - height) * Config.PPM;
        float spriteY = (body.getPosition().y - width) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    //endregion Private methods
}
