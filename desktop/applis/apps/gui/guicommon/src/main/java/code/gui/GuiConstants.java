package code.gui;

import javax.swing.*;
import java.awt.*;

public final class GuiConstants {

    public static final String FOLDER_MESSAGES_GUI = "resources_gui/gui/components";
    public static final int HAND_CURSOR = Cursor.HAND_CURSOR;
    public static final int HORIZONTAL_SPLIT = JSplitPane.HORIZONTAL_SPLIT;
    public static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
    public static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
    public static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;
    public static final int YES_NO_OPTION = JOptionPane.YES_NO_OPTION;
    public static final int YES_NO_CANCEL_OPTION = JOptionPane.YES_NO_CANCEL_OPTION;
    public static final int NO_OPTION = JOptionPane.NO_OPTION;
    public static final int YES_OPTION = JOptionPane.YES_OPTION;
    public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;
    public static final int OK_OPTION = JOptionPane.OK_OPTION;

    private GuiConstants() {
    }

    public static int toSplitOrientation(int _orientation) {
        if (_orientation == JSplitPane.VERTICAL_SPLIT) {
            return _orientation;
        }
        return HORIZONTAL_SPLIT;
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
            value_ = HAND_CURSOR;
        }
        return value_;
    }
}
