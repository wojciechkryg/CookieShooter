package com.cookieshooter.objects;

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

public class Bullet extends Object {

    //region Private variables

    private float width, height;

    //endregion Private variables

    //region Constructors

    public Bullet(Viewport viewport, World world, Vector2 position) {
        super(viewport, world);
        init(position);
    }

    //endregion Constructors

    //region Public methods

    //region Overrides

    @Override
    public void update() {
        moveBody();
        moveSprite();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), width * Config.OBJECT_RATIO, height * Config.OBJECT_RATIO);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    //endregion Overrides

    //endregion Public methods

    //region Private methods

    private void init(Vector2 position) {
        initSize();
        initBody(position);
        initImage();
    }

    private void initSize() {
        width = viewport.getWorldWidth() / Config.OBJECT_RATIO;
        height = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initBody(Vector2 position) {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(position.x, position.y);
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
        // todo: texture is ignored
        texture = new Texture(AssetsPath.PLAYER);
        sprite = new Sprite(texture);
        update();
    }

    private void moveSprite() {
        float spriteX = (body.getPosition().x - height) * Config.PPM;
        float spriteY = (body.getPosition().y - width) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    private void moveBody() {
        body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 7f*Config.SPEED));
    }

    //endregion Private methods
}
