package com.cookieshooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cookieshooter.objects.Enemy;
import com.cookieshooter.objects.Player;

import org.w3c.dom.Entity;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.List;


public class ListenerClass implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Object userA = contact.getFixtureA().getBody().getUserData();
        Object userB = contact.getFixtureB().getBody().getUserData();
        if ((userA instanceof Player && userB instanceof Enemy)) {
            ((Enemy)userB).setIsToDestroy(true);
        } else if (userB instanceof Player && userA instanceof Enemy) {
            ((Enemy)userA).setIsToDestroy(true);
        } else if (userA instanceof Enemy && userB instanceof Ground) {
            ((Enemy)userA).setIsToDestroy(true);
        } else if (userB instanceof Enemy && userA instanceof Ground) {
            ((Enemy)userB).setIsToDestroy(true);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
