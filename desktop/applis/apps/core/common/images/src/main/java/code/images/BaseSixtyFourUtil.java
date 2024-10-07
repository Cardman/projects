package code.images;

import code.util.CustList;
import code.util.Ints;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class BaseSixtyFourUtil {

//    private static final int FIRST_DIGIT = '0';
//    private static final int FIRST_LOW_LETTER = NumberUtil.MIN_LOW;
//    private static final int FIRST_UPP_LETTER = NumberUtil.MIN_UPP;
    private static final short BYTE = 256;
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
//    private static final byte NB_LETTERS = 26;
//    private static final byte NB_LETTERS_UPP_LOW = 52;
//    private static final byte NB_DIGITS_LETTERS = 62;

    private BaseSixtyFourUtil(){
    }
    public static CustList<int[][]> getImagesByString(CustList<String> _ls, String _base) {
        CustList<int[][]> anim_ = new CustList<int[][]>();
        for (String p: _ls) {
            anim_.add(BaseSixtyFourUtil.getImageByString(p,_base));
        }
        return anim_;
    }
    public static int[][] getImageByString(String _image, String _base) {
        int index_ = StringUtil.getFirstPrintableCharIndex(_image);
        if (index_ < 0) {
            return new int[0][0];
        }
        String img_ = _image.substring(index_);
        int len_ = img_.length();
        if (len_ < 4) {
            return new int[0][0];
        }
        if (len_ % 4 != 0) {
            return new int[0][0];
        }
        return processDef(img_, len_,_base);
    }

    private static int[][] processDef(String _img, int _len, String _base) {
        byte[] widthArray_ = parseFourChars(_img.substring(0, 4),_base);
        if (widthArray_.length == 0) {
            return new int[0][0];
        }
        int width_ = retrieveWidth(widthArray_);
        int fourWidth_ = width_ * 4;
        if (fourWidth_ <= 0) {
            return new int[0][0];
        }
        if ((_len - 4) % fourWidth_ != 0) {
            return new int[0][0];
        }
        int height_ = (_len - 4) / fourWidth_;
        if (height_ <= 0) {
            return new int[0][0];
        }
        int[][] image_ = new int[height_][width_];
        int[] row_ = new int[width_];
        int i_ = 4;
        int h_ = 0;
        int w_ = 0;
        int max_ = _len - 4;
        while (i_ <= max_) {
            String part_ = _img.substring(i_, i_ + 4);
            byte[] pixel_ = parseFourChars(part_,_base);
            if (pixel_.length == 0) {
                return new int[0][0];
            }
            int color_ = retrieveWidth(pixel_);
            row_[w_] = color_;
            w_++;
            if (w_ >= width_) {
                image_[h_] = row_;
                w_ = 0;
                h_++;
                row_ = new int[width_];
            }
            i_ += 4;
        }
        return image_;
    }

    private static int retrieveWidth(byte[] _array) {
        int width_ = 0;
        for (byte b: _array) {
            int real_ = b;
            if (real_ < 0) {
                real_ += 256;
            }
            width_ *= 256;
            width_ += real_;
        }
        return width_;
    }

    static byte[] parseFourChars(String _text, String _base) {
        byte[] out_ = new byte[THREE_COLORS_BYTES];
        int o_=0;

        byte[] quadruplet_ = new byte[FOUR_BITS];

        // convert the quadruplet to three bytes.
        for(int i=0; i<FOUR_BITS; i++ ) {
            char ch_ = _text.charAt(i);

            byte v_ = charToByte(ch_,_base);
            if (v_ < 0) {
                return new byte[0];
            }
            quadruplet_[i] = v_;
        }
         // quadruplet is now filled.
        int firstBytes_ = quadruplet_[0];
        int secondBytes_ = quadruplet_[1];
        int thirdBytes_ = quadruplet_[2];
        int fourthBytes_ = quadruplet_[THREE_COLORS_BYTES];
        out_[o_] = (byte) (FOUR_BITS * firstBytes_ + secondBytes_ / SIXTEEN_BITS);
        o_++;
        out_[o_] = (byte)(secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
        o_++;
        out_[o_] = (byte)(thirdBytes_ * SIXTY_FOUR_BITS +fourthBytes_);
        return out_;
    }

    public static byte charToByte(char _ch, String _base) {
        int index_ = _base.indexOf(_ch);
        if (index_ < 0) {
            return -1;
        }
        return (byte) index_;
//        int index_ = _base.indexOf(_ch);
//        if (index_ > -1) {
//            return (byte) index_;
//        }
//        byte v_;
//        if (_ch >= FIRST_DIGIT && _ch <= '9') {
//            int diff_ = _ch - FIRST_DIGIT;
//            v_ = (byte) (NB_LETTERS_UPP_LOW + diff_);
//        } else if (_ch >= FIRST_LOW_LETTER && _ch <= NumberUtil.MIN_LOW + 25) {
//            int diff_ = _ch - FIRST_LOW_LETTER;
//            v_ = (byte) (NB_LETTERS+diff_);
//        } else if (_ch >= FIRST_UPP_LETTER && _ch <= NumberUtil.MIN_UPP + 25) {
//            int diff_ = _ch - FIRST_UPP_LETTER;
//            v_ = (byte) diff_;
//        } else if (_ch == '+') {
//            v_ = NB_DIGITS_LETTERS;
//        } else {
//            v_ = NB_DIGITS_LETTERS + 1;
//        }
//        return v_;
    }

    public static CustList<String> getStringsByImage(CustList<int[][]> _image, String _base) {
        CustList<String> ls_ = new CustList<String>();
        for (int[][] i: _image) {
            ls_.add(getStringByImage(i,_base));
        }
        return ls_;
    }
    public static String getStringByImage(int[][] _image, String _base) {
        if (_image.length == 0) {
            return "";
        }
        int w_ = _image[0].length;
        StringBuilder str_ = new StringBuilder(4+_image[0].length*_image.length*4);
        byte[] bytes_ = new byte[THREE_COLORS_BYTES];
        bytes_[0] = (byte) ((w_ / (256*256))%256);
        bytes_[1] = (byte) ((w_ / 256)%256);
        bytes_[2] = (byte) (w_ %256);
        str_.append(printThreeBytes(bytes_,_base));
        for (int[] a : _image) {
            for (int j = 0; j < w_; j++) {
                bytes_ = new byte[THREE_COLORS_BYTES];
                int p_ = a[j];
                bytes_[0] = (byte) ((p_ / (256 * 256)) % 256);
                bytes_[1] = (byte) ((p_ / 256) % 256);
                bytes_[2] = (byte) (p_ % 256);
                str_.append(printThreeBytes(bytes_,_base));
            }
        }
        return str_.toString();
    }

    public static String printThreeBytes(byte[] _input, String _base) {
        if (_input.length == 1) {
            int adj_ = adj(_input[0]);
            return ""+encode(adj_ / FOUR_BITS, _base) +encode(
                    (adj_%FOUR_BITS) *SIXTEEN_BITS,_base)+"==";
        }
        if (_input.length == 2) {
            int adj_ = adj(_input[0]);
            int adjNext_ = adj(_input[1]);
            return ""+encode(adj_ / FOUR_BITS, _base)+encode(
                    ((adj_%FOUR_BITS)*SIXTEEN_BITS) +
                            ((adjNext_/SIXTEEN_BITS)%SIXTEEN_BITS),_base)+encode(
                    (adjNext_%SIXTEEN_BITS) *FOUR_BITS,_base)+"=";
        }
        char[] buf_ = new char[FOUR_BITS];
        int ptr_ = 0;
        int i = 0;
        int adj_ = adj(_input[i]);
        buf_[ptr_] = encode(adj_/FOUR_BITS,_base);
        ptr_++;
        int adjNext_ = adj(_input[i+1]);
        int adjNextNext_ = adj(_input[i + 2]);
        buf_[ptr_] = encode(
                ((adj_%FOUR_BITS)*SIXTEEN_BITS) +
                ((adjNext_/SIXTEEN_BITS)%SIXTEEN_BITS),_base);
        ptr_++;
        buf_[ptr_] = encode(
                    ((adjNext_%SIXTEEN_BITS)*FOUR_BITS)+
                    ((adjNextNext_/SIXTY_FOUR_BITS)%FOUR_BITS),_base);
        ptr_++;
        buf_[ptr_] = encode(adjNextNext_%SIXTY_FOUR_BITS,_base);
        return String.valueOf(buf_);
    }

    private static int adj(int _input) {
        int adjNextNext_ = _input;
        if (adjNextNext_ < 0) {
            adjNextNext_ += BYTE;
        }
        return adjNextNext_;
    }

    private static char encode(int _i, String _base) {
        return _base.charAt(_i);
//        if (_i < NB_LETTERS) {
//            return (char) (FIRST_UPP_LETTER+_i);
//        }
//        if (_i < NB_LETTERS_UPP_LOW) {
//            return (char) (FIRST_LOW_LETTER-NB_LETTERS+_i);
//        }
//        if (_i < NB_DIGITS_LETTERS) {
//            return (char) (FIRST_DIGIT-NB_LETTERS_UPP_LOW+_i);
//        }
//        if (_i == NB_DIGITS_LETTERS) {
//            return '+';
//        }
//        return '/';
    }

    public static String checkBase(String _base, String _defaultBase) {
        if (_base.length() != 64) {
            return _defaultBase;
        }
        if (_base.indexOf('=') > -1) {
            return _defaultBase;
        }
        if (StringUtil.removeAllSpaces(_base).length() < _base.length()) {
            return _defaultBase;
        }
        Ints ints_ = new Ints();
        for (char c: _base.toCharArray()) {
            ints_.add((int)c);
        }
        if (ints_.hasDuplicates()) {
            return _defaultBase;
        }
        return _base;
    }

    public static int[][] clipSixtyFour(int[][] _image,int _x,int _y,int _w,int _h) {
        int xp_ = NumberUtil.min(_x+_w, _image[0].length);
        int yp_ = NumberUtil.min(_y+_h, _image.length);
        int rw_ = xp_ - _x;
        int rh_ = yp_ - _y;
        int xMax_ = _x + rw_;
        int yMax_ = _y + rh_;
        int[][] subImg_ = new int[rh_][rw_];
        for (int i = _y; i < yMax_; i++) {
            for (int j = _x; j < xMax_; j++) {
                fwd(_image, _x, _y, subImg_, i, j);
            }
        }
        return subImg_;
    }

    private static void fwd(int[][] _image, int _x, int _y, int[][] _subImg, int _i, int _j) {
        _subImg[_i -_y][_j -_x] = _image[_i][_j];
    }
}
