package com.spellcastrpg.main;

import com.spellcastrpg.main.geometry.Rectangle;

public interface IAnchor {
    enum HAnchor implements IAnchor {
        WEST {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect;
            }
        },
        CENTER {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect.translate(-rect.getWidth() / 2, 0);
            }
        },
        EAST {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect.translate(-rect.getWidth(), 0);
            }
        }
    }
    enum VAnchor implements IAnchor  {
        NORTH {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect;
            }
        },
        CENTER {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect.translate(0, -rect.getHeight() / 2);
            }
        },
        SOUTH {
            @Override
            public Rectangle anchor(Rectangle rect) {
                return rect.translate(0, -rect.getHeight());
            }
        }
    }

    Rectangle anchor(Rectangle rect);
}
