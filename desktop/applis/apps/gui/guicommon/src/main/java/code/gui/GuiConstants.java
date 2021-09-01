package code.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    public static final String BORDER_LAYOUT_CENTER = BorderLayout.CENTER;
    public static final String BORDER_LAYOUT_SOUTH = BorderLayout.SOUTH;
    public static final String BORDER_LAYOUT_EAST = BorderLayout.EAST;
    public static final String BORDER_LAYOUT_NORTH = BorderLayout.NORTH;
    public static final String BORDER_LAYOUT_WEST = BorderLayout.WEST;
    public static final String BORDER_LAYOUT_BEFORE_FIRST_LINE = BorderLayout.BEFORE_FIRST_LINE;
    public static final String BORDER_LAYOUT_AFTER_LAST_LINE = BorderLayout.AFTER_LAST_LINE;
    public static final String BORDER_LAYOUT_BEFORE_LINE_BEGINS = BorderLayout.BEFORE_LINE_BEGINS;
    public static final String BORDER_LAYOUT_AFTER_LINE_ENDS = BorderLayout.AFTER_LINE_ENDS;
    public static final int CTRL_DOWN_MASK = InputEvent.CTRL_DOWN_MASK;
    public static final int SHIFT_DOWN_MASK = InputEvent.SHIFT_DOWN_MASK;
    public static final int ALT_DOWN_MASK = InputEvent.ALT_DOWN_MASK;
    public static final int VK_A = KeyEvent.VK_A;
    public static final int VK_B = KeyEvent.VK_B;
    public static final int VK_C = KeyEvent.VK_C;
    public static final int VK_D = KeyEvent.VK_D;
    public static final int VK_E = KeyEvent.VK_E;
    public static final int VK_F = KeyEvent.VK_F;
    public static final int VK_G = KeyEvent.VK_G;
    public static final int VK_H = KeyEvent.VK_H;
    public static final int VK_I = KeyEvent.VK_I;
    public static final int VK_J = KeyEvent.VK_J;
    public static final int VK_K = KeyEvent.VK_K;
    public static final int VK_L = KeyEvent.VK_L;
    public static final int VK_M = KeyEvent.VK_M;
    public static final int VK_N = KeyEvent.VK_N;
    public static final int VK_O = KeyEvent.VK_O;
    public static final int VK_P = KeyEvent.VK_P;
    public static final int VK_Q = KeyEvent.VK_Q;
    public static final int VK_R = KeyEvent.VK_R;
    public static final int VK_S = KeyEvent.VK_S;
    public static final int VK_T = KeyEvent.VK_T;
    public static final int VK_U = KeyEvent.VK_U;
    public static final int VK_V = KeyEvent.VK_V;
    public static final int VK_W = KeyEvent.VK_W;
    public static final int VK_X = KeyEvent.VK_X;
    public static final int VK_Y = KeyEvent.VK_Y;
    public static final int VK_Z = KeyEvent.VK_Z;
    public static final int VK_ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int VK_F1 = KeyEvent.VK_F1;
    public static final int VK_F2 = KeyEvent.VK_F2;
    public static final int VK_F3 = KeyEvent.VK_F3;
    public static final int VK_UP = KeyEvent.VK_UP;
    public static final int VK_DOWN = KeyEvent.VK_DOWN;
    public static final int VK_LEFT = KeyEvent.VK_LEFT;
    public static final int VK_RIGHT = KeyEvent.VK_RIGHT;
    public static final int VK_ENTER = KeyEvent.VK_ENTER;
    public static final int BOLD = Font.BOLD;
    public static final int ITALIC = Font.ITALIC;
    public static final int PLAIN = Font.PLAIN;
    public static final int WHITE = -1;
    public static final int BLACK = (int) (256*256*256*255L);
    public static final int RED = (int) (256*256*256*255L+256*256*255L);
    public static final int GREEN = (int) (256*256*256*255L+256*255L);
    public static final int BLUE = (int) (256*256*256*255L+255L);
    public static final int YELLOW = (int) (256*256*256*255L+256*256*255L+256*255L);
    public static final int GRAY = (int) (256*256*256*255L+256*256*128L+256*128L+128L);
    public static final int ORANGE = (int) (256*256*256*255L+256*256*255L+256*200L);
    public static final int MAGENTA = (int) (256*256*256*255L+256*256*255L+255L);
    public static final int CYAN = (int) (256*256*256*255L+256*255L+255L);
    public static final int DO_NOTHING_ON_CLOSE = WindowConstants.DO_NOTHING_ON_CLOSE;
    public static final int HIDE_ON_CLOSE = WindowConstants.HIDE_ON_CLOSE;
    public static final int EXIT_ON_CLOSE = WindowConstants.EXIT_ON_CLOSE;
    public static final int DISPOSE_ON_CLOSE = WindowConstants.DISPOSE_ON_CLOSE;
    public static final int CENTER = SwingConstants.CENTER;
    public static final int RIGHT = SwingConstants.RIGHT;
    public static final int TOP = SwingConstants.TOP;

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
        return _orientation == SwingConstants.HORIZONTAL;
    }

    public static int getHoriz(boolean _bool) {
        int value_;
        if (_bool) {
            value_ = SwingConstants.HORIZONTAL;
        } else {
            value_ = SwingConstants.VERTICAL;
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

    public static int fontStyle(boolean _bold, boolean _italic) {
        int font_;
        if (_bold) {
            if (_italic) {
                font_ = BOLD + ITALIC;
            } else {
                font_ = BOLD;
            }
        } else {
            if (_italic) {
                font_ = ITALIC;
            } else {
                font_ = PLAIN;
            }
        }
        return font_;
    }

    public static boolean italicFlag(int _font) {
        return _font == BOLD + ITALIC || _font == ITALIC;
    }

    public static boolean boldFlag(int _font) {
        return _font == BOLD + ITALIC || _font == BOLD;
    }

    public static int newColor(int _r,int _g, int _b, int _a) {
        long av_ = range(_a) * 256 * 256 * 256;
        long rv_ = range(_r) * 256 * 256;
        long gv_ = range(_g) * 256;
        return (int) (av_ + rv_ + gv_ + _b);
    }
    public static int newColor(int _r,int _g, int _b) {
        return newColor(_r, _g, _b, 255);
    }
    public static int newColor(int _rgb) {
        int alphaPart_ = alpha(_rgb);
        long rgb_ = (_rgb - alphaPart_ *256L*256*256);
        return (int) (255L*256*256*256+rgb_);
    }
    public static int redPart(int _rgb) {
        long rgb_ = adj(_rgb);
        return (int) ((rgb_ / (256 * 256))%256);
    }
    public static int greenPart(int _rgb) {
        long rgb_ = adj(_rgb);
        return (int) ((rgb_ / 256)%256);
    }
    public static int bluePart(int _rgb) {
        long rgb_ = adj(_rgb);
        return (int) (rgb_%256);
    }
    public static int alpha(int _rgb) {
        long rgb_ = adj(_rgb);
        return (int) ((rgb_ / (256 * 256 * 256))%256);
    }

    private static long adj(int _rgb) {
        return _rgb + 2L*Integer.MAX_VALUE+2L;
    }

    public static int newColor(int _rgba, boolean _hasAlpha) {
        if (_hasAlpha) {
            return _rgba;
        }
        return newColor(_rgba);
    }

    private static long range(int _value) {
        return Math.min(Math.max(0, _value),255);
    }

    public static boolean isMultiSelect(int _selectionMode) {
        return _selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
    }
}
