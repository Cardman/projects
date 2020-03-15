package code.images;

import code.util.StringList;

public final class BaseSixtyFourUtil {

    private static final int FIRST_DIGIT = '0';
    private static final int FIRST_LOW_LETTER = 'a';
    private static final int FIRST_UPP_LETTER = 'A';
    private static final short BYTE = 256;
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
    private static final byte NB_LETTERS = 26;
    private static final byte NB_LETTERS_UPP_LOW = 52;
    private static final byte NB_DIGITS_LETTERS = 62;

    private BaseSixtyFourUtil(){
    }
    public static int[][] getImageByString(String _image) {
        int index_ = StringList.getFirstPrintableCharIndex(_image);
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
        byte[] widthArray_ = parseFourChars(img_.substring(0, 4));
        int width_ = 0;
        for (byte b: widthArray_) {
            int real_ = b;
            if (real_ < 0) {
                real_ += 256;
            }
            width_ *= 256;
            width_ += real_;
        }
        int fourWidth_ = width_ * 4;
        if (fourWidth_ <= 0) {
            return new int[0][0];
        }
        if ((len_ - 4) % fourWidth_ != 0) {
            return new int[0][0];
        }
        int height_ = (len_ - 4) / fourWidth_;
        if (height_ <= 0) {
            return new int[0][0];
        }
        int[][] image_ = new int[height_][width_];
        int[] row_ = new int[width_];
        int i_ = 4;
        int h_ = 0;
        int w_ = 0;
        int max_ = len_ - 4;
        while (i_ <= max_) {
            String part_ = img_.substring(i_, i_ + 4);
            byte[] pixel_ = parseFourChars(part_);
            int color_ = 0;
            for (byte b: pixel_) {
                int real_ = b;
                if (real_ < 0) {
                    real_ += 256;
                }
                color_ *= 256;
                color_ += real_;
            }
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

    static byte[] parseFourChars(String _text) {
        byte[] out_ = new byte[THREE_COLORS_BYTES];
        int o_=0;

        byte[] quadruplet_ = new byte[FOUR_BITS];

        // convert the quadruplet to three bytes.
        for(int i=0; i<FOUR_BITS; i++ ) {
            char ch_ = _text.charAt(i);
            
            byte v_;
            if (ch_ >= FIRST_DIGIT && ch_ <= '9') {
                int diff_ = ch_ - FIRST_DIGIT;
                v_ = (byte) (NB_LETTERS_UPP_LOW + diff_);
            } else if (ch_ >= FIRST_LOW_LETTER && ch_ <= 'z') {
                int diff_ = ch_ - FIRST_LOW_LETTER;
                v_ = (byte) (NB_LETTERS+diff_);
            } else if (ch_ >= FIRST_UPP_LETTER && ch_ <= 'Z') {
                int diff_ = ch_ - FIRST_UPP_LETTER;
                v_ = (byte) diff_;
            } else if (ch_ == '+') {
                v_ = NB_DIGITS_LETTERS;
            } else {
                v_ = NB_DIGITS_LETTERS + 1;
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
    public static String getStringByImage(int[][] _image) {
        int w_ = _image[0].length;
        StringBuilder str_ = new StringBuilder(4+_image[0].length*_image.length*4);
        byte[] bytes_ = new byte[THREE_COLORS_BYTES];
        bytes_[0] = (byte) ((w_ / (256*256))%256);
        bytes_[1] = (byte) ((w_ / 256)%256);
        bytes_[2] = (byte) (w_ %256);
        str_.append(printThreeBytes(bytes_));
        for (int[] a : _image) {
            for (int j = 0; j < w_; j++) {
                bytes_ = new byte[THREE_COLORS_BYTES];
                int p_ = a[j];
                bytes_[0] = (byte) ((p_ / (256 * 256)) % 256);
                bytes_[1] = (byte) ((p_ / 256) % 256);
                bytes_[2] = (byte) (p_ % 256);
                str_.append(printThreeBytes(bytes_));
            }
        }
        return str_.toString();
    }

    static String printThreeBytes(byte[] _input) {
        char[] buf_ = new char[FOUR_BITS];
        int ptr_ = 0;
        int i = 0;
        int adj_ = _input[i];
        if (adj_ < 0) {
            adj_ += BYTE;
        }
        buf_[ptr_] = encode(adj_/FOUR_BITS);
        ptr_++;
        int adjNext_ = _input[i+1];
        if (adjNext_ < 0) {
            adjNext_ += BYTE;
        }
        int adjNextNext_ = _input[i+2];
        if (adjNextNext_ < 0) {
            adjNextNext_ += BYTE;
        }
        buf_[ptr_] = encode(
                ((adj_%FOUR_BITS)*SIXTEEN_BITS) +
                ((adjNext_/SIXTEEN_BITS)%SIXTEEN_BITS));
        ptr_++;
        buf_[ptr_] = encode(
                    ((adjNext_%SIXTEEN_BITS)*FOUR_BITS)+
                    ((adjNextNext_/SIXTY_FOUR_BITS)%FOUR_BITS));
        ptr_++;
        buf_[ptr_] = encode(adjNextNext_%SIXTY_FOUR_BITS);
        return new String(buf_);
    }

    private static char encode(int _i) {
        if (_i < NB_LETTERS) {
            return (char) (FIRST_UPP_LETTER+_i);
        }
        if (_i < NB_LETTERS_UPP_LOW) {
            return (char) (FIRST_LOW_LETTER-NB_LETTERS+_i);
        }
        if (_i < NB_DIGITS_LETTERS) {
            return (char) (FIRST_DIGIT-NB_LETTERS_UPP_LOW+_i);
        }
        if (_i == NB_DIGITS_LETTERS) {
            return '+';
        }
        return '/';
    }
}
