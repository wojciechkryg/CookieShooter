package com.cookieshooter.utils;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class RandomVector extends Vector2 {
    public RandomVector(int minX, int maxX, int minY, int maxY) {

        Random rand = new Random(System.currentTimeMillis());

        int randomX = rand.nextInt(maxX + 1 - minX) + minX;
        int randomY = rand.nextInt(maxY + 1 - minY) + minY;

        x = randomX;
        y = randomY;

    }
}
