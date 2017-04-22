package com.cookieshooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.cookieshooter.common.AssetsPath;

public final class FontHelper {

    private static FontHelper instance;
    private BitmapFont font32;

    private FontHelper () {

    }

    public static FontHelper getInstance() {
        if (instance == null) {
            instance = new FontHelper();
        }
        return instance;
    }

    public void initFonts() {
        initFont32();
    }

    private void initFont32() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(AssetsPath.OPENSANS));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.borderWidth = 1;
        font32 = generator.generateFont(parameter);
        generator.dispose();
    }

    public BitmapFont getFont32() {
        return font32;
    }
}
