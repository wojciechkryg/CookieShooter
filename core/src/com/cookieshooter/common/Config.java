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
    public final static int START_LEVEL = 0;
    //endregion Player

    //region Enemy

    public final static int MIN_X = -20;
    public final static int MAX_X = 20;
    public final static int MIN_Y = -30;
    public final static int MAX_Y = -15;
    public final static float ANIMATION_TIME = 1f/16f;

    //endregion Enemy

    //region Bullet

    public final static float BULLET_RATIO = 50;
    public final static float DEFAULT_FIRE_DELAY = 0.8f;

    //endregion Bullet

    //endregion Objects

    //region Menu

    public final static float MENU_BOTTOM_MARGIN = 10;

    //endregion Menu

    //region Pause

    public final static float PAUSE_MARGIN = 10;

    //endregion Pause

    //region End game

    public final static String ENDGAME_HIGSCORE_TEXT = "Highscore";
    public final static String ENDGAME_SCORE_TEXT = "Score";

    //endregion End game

    //region Preferences

    public final static String PREFERENCES_NAME = "COOKIE_SHOOTER";
    public final static String PREFERENCES_HIGHSCORE = "HIGHSCORE";
    public final static String PREFERENCES_SCORE = "SCORE";

    //endregion Preferences

}
