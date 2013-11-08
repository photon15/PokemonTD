package com.xkings.pokemontd.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.graphics.Shader;

/**
 * Created by Tomas on 11/7/13.
 */
public class GrayscaleRenderer implements Renderable {

    private final Renderable wrappedRenderer;
    private final SpriteBatch spriteBatch;
    private final int width;
    private final int height;
    private final ShaderProgram shader;
    private final FrameBuffer fbo;

    public GrayscaleRenderer(Renderable wrappedRenderer) {
        this.wrappedRenderer = wrappedRenderer;
        this.spriteBatch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        shader = Shader.getShader("grayscale");
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);

    }

    @Override
    public void render() {
        fbo.begin();
        wrappedRenderer.render();
        fbo.end();
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        spriteBatch.draw(fbo.getColorBufferTexture(), 0, 0, width, height, 0, 0, width, height, false, true);
        spriteBatch.end();
    }


}