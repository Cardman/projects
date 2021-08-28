package code.gui;

import javax.swing.*;
import java.awt.*;

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

    public static int getSelectTable(boolean _mult) {
        int value_;
        if (_mult) {
            value_ = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        } else {
            value_ = ListSelectionModel.SINGLE_SELECTION;
        }
        return value_;
    }

    public static int cursor(boolean _enabled) {
        int value_;
        if (!_enabled) {
            value_ = Cursor.DEFAULT_CURSOR;
        } else {
            value_ = Cursor.HAND_CURSOR;
        }
        return value_;
    }
}
