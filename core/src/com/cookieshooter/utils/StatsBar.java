package com.cookieshooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;

public class StatsBar {

    //region Private variables

    private float width, height;
    private float x, y;
    private float levelWidth;

    private Texture hearthTexture;
    private Sprite hearthSprite;

    private ShapeRenderer shapeRenderer;

    private Color backgroundColor;
    private BitmapFont bitmapFont;

    //endregion Private variables

    //region Constructors

    public StatsBar() {
        initStatsBar();
        initFont();
        initHearth();
        levelWidth = getLevelWidth();
    }

    //endregion Constructors

    //region Public methods

    public void drawStatsBar(SpriteBatch batch, Stats stats) {
        drawBackground();
        drawStats(batch, stats);
        drawHearths(batch, stats);
    }

    public void dispose() {
        hearthTexture.dispose();
    }

    //endregion Public methods

    //region Private methods

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

    private void initHearth() {
        hearthTexture = new Texture(AssetsPath.HEARTH);
        hearthSprite = new Sprite(hearthTexture);
        float hearthY = hearthTexture.getHeight() / 3;
        hearthSprite.setPosition(width / 2, y + hearthY);
    }

    private float getLevelWidth() {
        String sample = "LEVEL: ";
        GlyphLayout layout = new GlyphLayout(bitmapFont, sample);
        return layout.width;
    }

    private void drawBackground() {
        Gdx.gl.glEnable(GL20.GL_BLEND);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(backgroundColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawStats(SpriteBatch batch, Stats stats) {

        float levelLabelWidth = levelWidth + getNumberOfDigits(stats.getLevel()) * Config.CHAR_WIDTH;

        batch.begin();
        bitmapFont.draw(batch, "SCORE: " + stats.getPoints(), x + Config.MARGIN, y + (bitmapFont.getXHeight() + height) / 2);
        bitmapFont.draw(batch, "LEVEL: " + stats.getLevel(), width - levelLabelWidth - Config.MARGIN, y + (bitmapFont.getXHeight() + height) / 2);
        batch.end();
    }

    private void drawHearths(SpriteBatch batch, Stats stats) {
        batch.begin();
        batch.draw(hearthSprite, hearthSprite.getX(), hearthSprite.getY(), hearthSprite.getWidth(), hearthSprite.getHeight());
        batch.end();
    }

    private int getNumberOfDigits(int number) {
        return (int) (Math.log10(number) + 1);
    }

    //endregion Private methods
}
