package com.cookieshooter.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;
import com.cookieshooter.utils.FontHelper;

public class EndState extends State {

    private Stage stage;
    private BitmapFont font;

    private int score;
    private int highscore;

    public EndState() {
        super();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        createActors(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setScores();
        initFont();
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

    public void drawScores(SpriteBatch batch) {
        batch.begin();

        int height = Gdx.graphics.getHeight() / 3;
        float highscoreWidth = new GlyphLayout(font, Config.ENDGAME_HIGSCORE_TEXT).width;
        float scoreWidth = new GlyphLayout(font, Config.ENDGAME_SCORE_TEXT).width;

        String highscoreText = String.format("%s: %d", Config.ENDGAME_HIGSCORE_TEXT, highscore);
        String scoreText = String.format("%s: %d", Config.ENDGAME_SCORE_TEXT, score);

        font.draw(batch, highscoreText, (Gdx.graphics.getWidth() / 2) - highscoreWidth, height);
        font.draw(batch, scoreText, (Gdx.graphics.getWidth() / 2) - scoreWidth, height - 50);

        batch.end();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        if (Gdx.input.justTouched()) {
            GameStateManager.getInstance().set(new MenuState());
        }
        batch.begin();
        stage.draw();
        batch.end();
        drawScores(batch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    private void initFont() {
        font = FontHelper.getInstance().getFont32();
        font.setColor(Color.WHITE);
    }

    private void setScores() {
        Preferences preferences = Gdx.app.getPreferences(Config.PREFERENCES_NAME);
        highscore = preferences.getInteger(Config.PREFERENCES_HIGHSCORE);
        score = preferences.getInteger(Config.PREFERENCES_SCORE);
    }
}
