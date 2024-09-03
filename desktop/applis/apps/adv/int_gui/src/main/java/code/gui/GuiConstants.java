package code.gui;

import code.util.core.NumberUtil;

public final class GuiConstants {

//    public static final String FOLDER_MESSAGES_GUI = "resources_gui/gui/components";
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
    public static final int CTRL_DOWN_MASK = 128;
    public static final int SHIFT_DOWN_MASK = 64;
    public static final int ALT_DOWN_MASK = 512;
    public static final int VK_A = NumberUtil.MIN_UPP;
    public static final int VK_B = VK_A+1;
    public static final int VK_C = VK_B+1;
    public static final int VK_D = VK_C+1;
    public static final int VK_E = VK_D+1;
    public static final int VK_F = VK_E+1;
    public static final int VK_G = VK_F+1;
    public static final int VK_H = VK_G+1;
    public static final int VK_I = VK_H+1;
    public static final int VK_J = VK_I+1;
    public static final int VK_K = VK_J+1;
    public static final int VK_L = VK_K+1;
    public static final int VK_M = VK_L+1;
    public static final int VK_N = VK_M+1;
    public static final int VK_O = VK_N+1;
    public static final int VK_P = VK_O+1;
    public static final int VK_Q = VK_P+1;
    public static final int VK_R = VK_Q+1;
    public static final int VK_S = VK_R+1;
    public static final int VK_T = VK_S+1;
    public static final int VK_U = VK_T+1;
    public static final int VK_V = VK_U+1;
    public static final int VK_W = VK_V+1;
    public static final int VK_X = VK_W+1;
    public static final int VK_Y = VK_X+1;
    public static final int VK_Z = VK_Y+1;
    public static final int VK_ESCAPE = 27;
    public static final int VK_F1 = 112;
    public static final int VK_F2 = 113;
    public static final int VK_F3 = 114;
    public static final int VK_F4 = 115;
    public static final int VK_F5 = 116;
    public static final int VK_F6 = 117;
    public static final int VK_DELETE = 127;
    public static final int VK_UP = 38;
    public static final int VK_DOWN = 40;
    public static final int VK_LEFT = 37;
    public static final int VK_RIGHT = 39;
    public static final int VK_ENTER = 10;
    public static final int VK_PAGE_UP = 33;
    public static final int VK_PAGE_DOWN = 34;
    public static final int VK_END = 35;
    public static final int VK_HOME = 36;
    public static final int VK_SPACE = 32;
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
//    public static final int DO_NOTHING_ON_CLOSE = 0;
//    public static final int HIDE_ON_CLOSE = 1;
//    public static final int EXIT_ON_CLOSE = 3;
//    public static final int DISPOSE_ON_CLOSE = 2;
    public static final int CENTER = 0;
    public static final int RIGHT = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 0;
    public static final int REMAINDER = 0;

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

    public static byte[] nullToEmpty(byte[] _array) {
        if (_array == null) {
            return new byte[0];
        }
        return _array;
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
