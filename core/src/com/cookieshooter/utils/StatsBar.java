package com.cookieshooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;

public class StatsBar {

    private float width, height;
    private float x, y;

    private ShapeRenderer shapeRenderer;

    private Color backgroundColor;
    private BitmapFont bitmapFont;

    public StatsBar() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight() / Config.STATSBAR_RATIO;
        x = 0;
        y = Gdx.graphics.getHeight() - height;

        shapeRenderer = new ShapeRenderer();

        backgroundColor = new Color(Config.BACKGROUND_R, Config.BACKGROUND_G, Config.BACKGROUND_B, 1);

        initFont();
    }

    private void initFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(AssetsPath.OPENSANS));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.borderWidth = 1;
        bitmapFont = generator.generateFont(parameter);
        generator.dispose();

        bitmapFont.setColor(Color.WHITE);
    }

    public void drawStatsBar(SpriteBatch batch, int score) {
        drawBackground();
        drawScore(batch, score);
    }

    private void drawBackground() {
        Gdx.gl.glEnable(GL20.GL_BLEND);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(backgroundColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawScore(SpriteBatch batch, int score) {
        batch.begin();
        bitmapFont.draw(batch, "SCORE: " + score, x + 20, y + 20 );
        batch.end();
    }
}
