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

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;

public class StatsBar {

    private float width, height;
    private float x, y;

    private ShapeRenderer shapeRenderer;

    private Color backgroundColor;
    private BitmapFont bitmapFont;

    public StatsBar() {
        initStatsBar();
        initFont();
    }

    private void initStatsBar() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight() / Config.STATSBAR_RATIO;
        x = 0;
        y = Gdx.graphics.getHeight() - height;

        shapeRenderer = new ShapeRenderer();

        backgroundColor = new Color(Config.BACKGROUND_R, Config.BACKGROUND_G, Config.BACKGROUND_B, 1);
    }

    private void initFont() {
        bitmapFont = FontHelper.getInstance().getFont32();
        bitmapFont.setColor(Color.WHITE);
    }

    public void drawStatsBar(SpriteBatch batch, Stats stats) {
        drawBackground();
        drawScore(batch, stats.getPoints());
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
        bitmapFont.draw(batch, "SCORE: " + score, x + Config.MARGIN, y + (bitmapFont.getXHeight() + height) / 2);
        batch.end();
    }
}
