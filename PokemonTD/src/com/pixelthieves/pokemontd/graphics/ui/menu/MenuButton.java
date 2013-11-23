package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.Button;

/**
 * Created by Tomas on 11/19/13.
 */
abstract class MenuButton extends Button {

    MenuButton(Menu menu, Rectangle rect) {
        super(menu, rect, menu.getFont(), BitmapFont.HAlignment.CENTER);
    }
}