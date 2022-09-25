package code.images;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ImageCsv {

    public static final char SEPARATOR_CHAR = ';';

    private static final String RETURN_LINE2 = "\r";

    private static final String RETURN_LINE = "\n";

    private static final String EMPTY_IMAGE = "0";

    private static final char MINUS = '-';

    private int width;

    private final Ints pixels;

    private ImageCsv() {
        pixels = new Ints();
    }
    public ImageCsv(String _contentFile) {
        pixels = new Ints();
        String string_ = StringUtil.removeStrings(_contentFile, RETURN_LINE, RETURN_LINE2);
        StringList lines_ = StringUtil.splitChars(string_, SEPARATOR_CHAR);
        width = NumberUtil.parseInt(lines_.first());
        int len_ = lines_.size();
        for (int i = IndexConstants.SECOND_INDEX; i<len_; i++) {
            pixels.add(NumberUtil.parseInt(lines_.get(i)));
        }
    }

    public boolean isValid() {
        if (pixels.isEmpty()) {
            return width == 0;
        }
        if (width <= 0) {
            return false;
        }
        return pixels.size() % width == 0;
    }

    public static boolean isValidNotEmpty(String _img, int _sideLength) {
        return isValidNotEmpty(_img,_sideLength,_sideLength);
    }

    public static boolean isValidNotEmptyLower(String _img, int _sideLength) {
        int readWidth_ = widthImg(_img);
        if (readWidth_ <= 0) {
            return false;
        }
        StringList nbs_ = StringUtil.splitChars(_img, SEPARATOR_CHAR);
        if (koNumbers(nbs_)) {
            return false;
        }
        int nb_ = nbs_.size()-1;
        if (nb_ % readWidth_ != 0) {
            return false;
        }
        int h_ = nb_ / readWidth_;
        return h_ <= _sideLength && readWidth_ <= _sideLength;
    }

    public static boolean isValidNotEmpty(String _img, int _width, int _height) {
        if (widthImg(_img) != _width) {
            return false;
        }
        StringList nbs_ = StringUtil.splitChars(_img, SEPARATOR_CHAR);
        if (koNumbers(nbs_)) {
            return false;
        }
        int nb_ = nbs_.size()-1;
        return nb_ == _width * _height;
    }
    private static boolean koNumbers(CustList<String> _nbs) {
        int nb_ = _nbs.size();
        for (int i = 0; i < nb_; i++) {
            if (!isNumber(_nbs.get(i))) {
                return true;
            }
        }
        return false;
    }
    private static int widthImg(String _img) {
        int index_ = _img.indexOf(SEPARATOR_CHAR);
        if (index_ < 0) {
            return -3;
        }
        if (_img.charAt(0) == MINUS) {
            return -1;
        }
        String width_ = _img.substring(0, index_);
        if (!isNumber(width_)) {
            return -2;
        }
        return NumberUtil.parseInt(width_);
    }

    public static IntPoint getDimensions(String _img, int _sideLength) {
        int nb_ = StringUtil.splitChars(_img, SEPARATOR_CHAR).size() - 1;
        int i_ = _img.indexOf(SEPARATOR_CHAR);
        String w_ = _img.substring(0, i_);
        int width_ = NumberUtil.parseInt(w_);
        int heigth_ = nb_ / width_;
        return new IntPoint(width_/_sideLength, heigth_/_sideLength);
    }

    public static String clip(String _image,int _x,int _y,int _w,int _h) {
        return new ImageCsv(_image).clip(_x, _y, _w, _h).display();
    }
    public ImageCsv clip(int _x, int _y, int _w, int _h) {
        if (pixels.isEmpty()) {
            return new ImageCsv(EMPTY_IMAGE);
        }
        int x_ = NumberUtil.abs(_x);
        int y_ = NumberUtil.abs(_y);
        int xp_ = NumberUtil.min(_x+_w,width);
        int yp_ = NumberUtil.min(_y+_h,getHeight());
        int rw_ = xp_ - x_;
        int rh_ = yp_ - y_;
        if (rw_ <= 0 || rh_ <= 0) {
            return new ImageCsv(EMPTY_IMAGE);
        }
        ImageCsv return_ = new ImageCsv();
        return_.width = rw_;
        int maxYCoords_ = y_+rh_;
        int maxXCoords_ = x_+rw_;
        for (int j=y_;j<maxYCoords_;j++) {
            for (int i=x_;i<maxXCoords_;i++) {
                return_.pixels.add(getPixel(i, j));
            }
        }
        return return_;
    }
    public static String toBaseSixtyFour(String _string) {
        return BaseSixtyFourUtil.getStringByImage(getImageByString(_string));
    }

    public static int[][] getImageByString(String _string) {
        ImageCsv i_ = new ImageCsv(_string);
        Ints pixels_ = i_.getPixels();
        int width_ = i_.getWidth();
        int height_ = i_.getHeight();
        int[][] img_ = new int[height_][width_];
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                img_[i][j] = pixels_.get(j + width_ * i);
            }
        }
        return img_;
    }
    public int getPixel(int _x, int _y) {
        int index_ = _x + width * _y;
        return pixels.get(index_);
    }

    public int getHeight() {
        if (pixels.isEmpty()) {
            return 0;
        }
        return pixels.size()/width;
    }

    public int getWidth() {
        return width;
    }


    public Ints getPixels() {
        return pixels;
    }

    static boolean isNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        if (_string.charAt(i_) == MINUS) {
            if (_string.length() == IndexConstants.ONE_ELEMENT) {
                return false;
            }
            i_++;
        }
        int len_ = _string.length();
        while (i_ < len_) {
            if (!isDigit(_string.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }
    static boolean isDigit(char _ch) {
        return _ch >= '0' && _ch <= '9';
    }

    public String display() {
        StringBuilder return_ = new StringBuilder(Integer.toString(width));
        for (int i: pixels) {
            return_.append(SEPARATOR_CHAR);
            return_.append(i);
        }
        return return_.toString();
    }
}
