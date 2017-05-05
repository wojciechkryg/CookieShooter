package com.cookieshooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.cookieshooter.common.AssetsPath;

public final class SoundHelper {
    private static SoundHelper instance;

    private Music ouch;
    private Music shoot;
    private Music chrup;

    private SoundHelper() {
        initSounds();
    }

    public static SoundHelper getInstance() {
        if (instance == null) {
            instance = new SoundHelper();
        }
        return instance;
    }

    private void initSounds() {
        ouch = Gdx.audio.newMusic(Gdx.files.internal(AssetsPath.OUCH));
        shoot = Gdx.audio.newMusic(Gdx.files.internal(AssetsPath.SHOOT));
        chrup = Gdx.audio.newMusic(Gdx.files.internal(AssetsPath.CHRUP));
    }

    public void playOuch() {
        ouch.play();
    }

    public void playShoot() {
        shoot.play();
    }

    public void playChrup() {
        chrup.play();
    }

    public void dispose() {
        instance = null;
        ouch.dispose();
        shoot.dispose();
        chrup.dispose();
    }
}
