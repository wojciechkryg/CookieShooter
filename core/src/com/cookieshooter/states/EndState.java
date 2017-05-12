package com.cookieshooter.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cookieshooter.common.AssetsPath;

public class EndState extends State {

    private Stage stage;

    public EndState(){
        super();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        createActors(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void createActors(float width, float height) {

        Texture gameOver = new Texture(AssetsPath.GAME_OVER);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(gameOver));
        float scale = width / gameOver.getWidth();
        drawable.setMinWidth(width);
        drawable.setMinHeight(gameOver.getHeight() * scale);
        Image actor = new Image(drawable);
        actor.setPosition(0, height - (actor.getHeight() + height / 10));

        stage.addActor(actor);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
