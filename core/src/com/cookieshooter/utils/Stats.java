package com.cookieshooter.utils;

import com.cookieshooter.common.Config;

public class Stats {
    private int points;
    private int level;
    private int lives;

    public Stats() {
        points = 0;
        level = Config.START_LEVEL;
        lives = 3;
    }

    public int getPoints() {
        return points;
    }

    public void changePoints(int deltaPoints) {
        points+= deltaPoints;
    }

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        level++;
    }

    public int getLives() {
        return lives;
    }

    public void incrementLives() {
        lives++;
    }

    public void decrementLives() {
        lives--;
    }
}
