package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class Icon extends InteractiveBlock {

    private final SpriteBatch spriteBatch;
    private final TextureAtlas.AtlasRegion texture;

    Icon(Rectangle rectangle, SpriteBatch spriteBatch, TextureAtlas.AtlasRegion texture) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.texture = texture;
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, width, height);
        spriteBatch.end();
    }
}