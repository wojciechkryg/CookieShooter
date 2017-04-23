package com.cookieshooter.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public final class GameStateManager {

    //region Private variables

    private static GameStateManager instance;

    private static Stack<State> states = new Stack<State>();

    //endregion Private variables

    //region Constructors

    private GameStateManager() {}

    //endregion Constructors

    //region Public methods

    public static GameStateManager getInstance() {
        return instance = instance != null ? instance : new GameStateManager();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        if (states.size() == 0) {
            return;
        }
        states.pop().dispose();
    }

    public void set(State state) {
        pop();
        push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }

    //endregion Public methods
}
