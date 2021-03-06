package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.graphics.ui.GuiBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 11/19/13.
 */
class MenuTab extends GuiBox {

    private static final float LINE_HEIGHT = 2;
    protected final List<Rectangle> rects;
    protected final int count;
    protected final MenuTab parent;
    protected float segment;
    protected final Menu menu;
    protected final float buttonHeight;
    private boolean closeTabWhenNotClicked = true;
    private boolean renderLines = true;
    protected List<MenuTab> cards = new ArrayList<MenuTab>();

    MenuTab(Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, rectangle);
        this.menu = menu;
        this.parent = parent;
        this.count = count;
        this.buttonHeight = height / count;
        segment = width / 20;
        rects = getRects(count);
    }

    private List<Rectangle> getRects(int count) {
        List<Rectangle> rects = new ArrayList<Rectangle>();
        for (int i = 0; i < count; i++) {
            int offset = (int) (buttonHeight * (i + 1));
            rects.add(new Rectangle(x, y + height - offset, width, buttonHeight));
        }
        return rects;
    }

    @Override
    public void render() {
        super.render();
        if (isRenderLines()) {
            shapeRenderer.setColor(GuiBox.darkerColor);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (int i = 1; i < count; i++) {
                float offset = i * (height / count);
                shapeRenderer.rect(x + segment, offset + y - LINE_HEIGHT / 2, width - segment * 2, LINE_HEIGHT);
            }
            shapeRenderer.end();
        }
    }

    public boolean isCloseTabWhenNotClicked() {
        if (parent == null) {
            return closeTabWhenNotClicked;
        } else {
            return parent.isCloseTabWhenNotClicked();
        }
    }

    public void setCloseTabWhenNotClicked(boolean closeTabWhenNotClicked) {
        if (parent == null) {
            this.closeTabWhenNotClicked = closeTabWhenNotClicked;
        } else {
            parent.setCloseTabWhenNotClicked(closeTabWhenNotClicked);
        }
    }

    public boolean isRenderLines() {
        return renderLines;
    }

    public void setRenderLines(boolean renderLines) {
        this.renderLines = renderLines;
    }

    public void close() {
        menu.switchCard(parent);
    }

    public void pan(float x, float y, float deltaX, float deltaY) {
        for (MenuTab card : cards) {
            card.pan(x, y, deltaX, deltaY);
        }
    }
}