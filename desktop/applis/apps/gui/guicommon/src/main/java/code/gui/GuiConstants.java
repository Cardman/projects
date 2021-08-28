package code.gui;

import javax.swing.*;

public final class GuiConstants {

    public static final String FOLDER_MESSAGES_GUI = "resources_gui/gui/components";

    private GuiConstants() {
    }

    public static int toSplitOrientation(int _orientation) {
        if (_orientation == JSplitPane.VERTICAL_SPLIT) {
            return _orientation;
        }
        return JSplitPane.HORIZONTAL_SPLIT;
    }

    public static int getOrient(int _o) {
        if (_o == SwingConstants.VERTICAL) {
            return SwingConstants.VERTICAL;
        }
        return SwingConstants.HORIZONTAL;
    }

    public static boolean isHorizProgress(int _orientation) {
        return _orientation == JProgressBar.HORIZONTAL;
    }

    public static int getHoriz(boolean _bool) {
        int value_;
        if (_bool) {
            value_ = JProgressBar.HORIZONTAL;
        } else {
            value_ = JProgressBar.VERTICAL;
        }
        return value_;
    }
}
