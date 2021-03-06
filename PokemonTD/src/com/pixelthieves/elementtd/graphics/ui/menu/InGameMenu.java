package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
class InGameMenu extends CommonMenu {

    private final Button pause;

    InGameMenu(final Menu menu, Rectangle rectangle, int count) {
        super(menu, rectangle, Type.CLOSE_AND_EXIT, true, count);
        pause = new MenuButton(menu, rects.get(0)) {
            @Override
            public void process(float x, float y) {
                App app = menu.getApp();
                if (!app.isFinishedGame()) {
                    app.freeze(!app.isFreezed());
                }
            }
        };

        register(pause);
        this.setCloseTabWhenNotClicked(true);
    }

    @Override
    public void render() {
        super.render();
        pause.render(menu.getApp().isFreezed() ? "Resume" : "Pause");
    }
}
