package com.cookieshooter.common;

public class Config {
    public final static float PPM = 10; // Pixel Per Meter

    public final static float SPEED = 5;

    //region StatsBar
    public final static float STATSBAR_RATIO = 10;
    public final static float BACKGROUND_R = 38/255f;
    public final static float BACKGROUND_G = 125/255f;
    public final static float BACKGROUND_B = 171/255f;
    public final static float MARGIN = 30;
    public final static float CHAR_WIDTH = 20;
    //endregion StatsBar

    //region Objects

    public final static float OBJECT_RATIO = 20;

    //region Player
    public final static int START_LEVEL = 1;
    //endregion Player

    //region Enemy

    public final static int MIN_X = -20;
    public final static int MAX_X = 20;
    public final static int MIN_Y = -30;
    public final static int MAX_Y = -5;

    //endregion Enemy

    //region Bullet

    public final static float BULLET_RATIO = 50;
    public final static float DEFAULT_FIRE_DELAY = 0.8f;

    //endregion Bullet

    //endregion Objects

    //region Menu

    public final static float MENU_BOTTOM_MARGIN = 10;

    //endregion Menu

}
