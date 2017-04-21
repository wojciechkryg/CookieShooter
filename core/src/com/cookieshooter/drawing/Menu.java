package com.cookieshooter.drawing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;

public class Menu {

    //region Private variables

    private float width;
    private float height;

    //endregion Private variables

    //region Constructors

    public Menu(float width, float height) {
        this.width = width;
        this.height = height;
    }

    //endregion Constructors

    //region Public methods

    public Actor getLogoActor() {
        Texture logo = new Texture(AssetsPath.LOGO);
        Drawable drawable = getTextureDrawable(logo);
        float scale = width / logo.getWidth();
        drawable.setMinWidth(width);
        drawable.setMinHeight(logo.getHeight() * scale);
        Image actor = new Image(drawable);
        actor.setPosition(0, height - (actor.getHeight() + height / 10));

        return actor;
    }

    public Actor getPlayButtonActor(EventListener eventListener) {
        Actor button = getImageButtonActor(AssetsPath.MENU_START_BUTTON);

        button.addListener(eventListener);
        button.setPosition(width / 2 - button.getWidth() / 2, height / 3 - button.getHeight() / 2);

        return button;
    }

    public Actor getExitButtonActor(EventListener eventListener) {
        Actor button = getImageButtonActor(AssetsPath.MENU_EXIT_BUTTON);

        button.addListener(eventListener);
        button.setPosition(width / 2 - button.getWidth() / 2, Config.MENU_BOTTOM_MARGIN);

        return button;
    }

    //endregion Public methods

    //region Private methods

    private TextureRegionDrawable getTextureDrawable(Texture texture) {
        return new TextureRegionDrawable(new TextureRegion(texture));
    }


    private Actor getImageButtonActor(String assetPath) {
        Texture buttonTexture = new Texture(assetPath);
        return new ImageButton(getTextureDrawable(buttonTexture));
    }

    //endregion Private methods
}
