package code.gui;

import code.util.core.NumberUtil;

public final class GuiConstants {

    public static final String FOLDER_MESSAGES_GUI = "resources_gui/gui/components";
    public static final int HORIZONTAL_SPLIT = 1;
    public static final int ERROR_MESSAGE = 0;
    public static final int WARNING_MESSAGE = 2;
    public static final int INFORMATION_MESSAGE = 1;
    public static final int YES_NO_OPTION = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int NO_OPTION = 1;
    public static final int YES_OPTION = 0;
    public static final int CANCEL_OPTION = 2;
    public static final int OK_OPTION = 0;
    public static final String BORDER_LAYOUT_CENTER = "Center";
    public static final String BORDER_LAYOUT_SOUTH = "South";
    public static final String BORDER_LAYOUT_EAST = "East";
    public static final String BORDER_LAYOUT_NORTH = "North";
    public static final String BORDER_LAYOUT_WEST = "West";
    public static final String BORDER_LAYOUT_BEFORE_FIRST_LINE = "First";
    public static final String BORDER_LAYOUT_AFTER_LAST_LINE = "Last";
    public static final String BORDER_LAYOUT_BEFORE_LINE_BEGINS = "Before";
    public static final String BORDER_LAYOUT_AFTER_LINE_ENDS = "After";
    public static final int CTRL_DOWN_MASK = 128;
    public static final int SHIFT_DOWN_MASK = 64;
    public static final int ALT_DOWN_MASK = 512;
    public static final int VK_A = 'A';
    public static final int VK_B = 'B';
    public static final int VK_C = 'C';
    public static final int VK_D = 'D';
    public static final int VK_E = 'E';
    public static final int VK_F = 'F';
    public static final int VK_G = 'G';
    public static final int VK_H = 'H';
    public static final int VK_I = 'I';
    public static final int VK_J = 'J';
    public static final int VK_K = 'K';
    public static final int VK_L = 'L';
    public static final int VK_M = 'M';
    public static final int VK_N = 'N';
    public static final int VK_O = 'O';
    public static final int VK_P = 'P';
    public static final int VK_Q = 'Q';
    public static final int VK_R = 'R';
    public static final int VK_S = 'S';
    public static final int VK_T = 'T';
    public static final int VK_U = 'U';
    public static final int VK_V = 'V';
    public static final int VK_W = 'W';
    public static final int VK_X = 'X';
    public static final int VK_Y = 'Y';
    public static final int VK_Z = 'Z';
    public static final int VK_ESCAPE = 27;
    public static final int VK_F1 = 112;
    public static final int VK_F2 = 113;
    public static final int VK_F3 = 114;
    public static final int VK_UP = 38;
    public static final int VK_DOWN = 40;
    public static final int VK_LEFT = 37;
    public static final int VK_RIGHT = 39;
    public static final int VK_ENTER = 10;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int PLAIN = 0;
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
    public static final int DO_NOTHING_ON_CLOSE = 0;
    public static final int HIDE_ON_CLOSE = 1;
    public static final int EXIT_ON_CLOSE = 3;
    public static final int DISPOSE_ON_CLOSE = 2;
    public static final int CENTER = 0;
    public static final int RIGHT = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 0;

    private GuiConstants() {
    }

    public static int toSplitOrientation(int _orientation) {
        if (_orientation == 0) {
            return _orientation;
        }
        return HORIZONTAL_SPLIT;
    }

    public static int getOrient(int _o) {
        if (_o == VERTICAL) {
            return VERTICAL;
        }
        return HORIZONTAL;
    }

    public static void setOrient(AbsSlider _slider,int _o) {
        if (getOrient(_o) == HORIZONTAL) {
            _slider.setHorizontal();
        } else {
            _slider.setVertical();
        }
    }

    public static void setHorizProg(AbsProgressBar _bar, boolean _horiz) {
        if (_horiz) {
            _bar.setHorizontal();
        } else {
            _bar.setVertical();
        }
    }

    public static void setSelectTable(AbsTableGui _table,boolean _mult) {
        if (_mult) {
            _table.setMultiSelect();
        } else {
            _table.setSingleSelect();
        }
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
        long av_ = range(_a) * 256L * 256L * 256L;
        long rv_ = range(_r) * 256L * 256L;
        long gv_ = range(_g) * 256L;
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
        return NumberUtil.min(NumberUtil.max(0, _value),255);
    }

}
