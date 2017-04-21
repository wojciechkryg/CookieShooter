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
        Texture playBtn = new Texture(AssetsPath.MENU_START);
        Drawable drawable = getTextureDrawable(playBtn);
        ImageButton playButton = new ImageButton(drawable);
        playButton.addListener(eventListener);
        playButton.setPosition(width / 2 - playButton.getWidth() / 2, height / 3 - playButton.getHeight() / 2);

        return playButton;
    }

    //endregion Public methods

    //region Private methods

    private TextureRegionDrawable getTextureDrawable(Texture texture) {
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    //endregion Private methods
}
