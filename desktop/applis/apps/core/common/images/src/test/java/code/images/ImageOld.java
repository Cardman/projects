package code.images;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class ImageOld implements Displayable {

    static final char SEPARATOR_CHAR = ';';

    private static final String RETURN_LINE2 = "\r";

    private static final String RETURN_LINE = "\n";

//    private static final String EMPTY_STRING = "";

    private static final String EMPTY_IMAGE = "0";

    private static final char MINUS = '-';

    private int width;

    private Ints pixels;

    private ImageOld() {
        pixels = new Ints();
    }
    public ImageOld(String _contentFile) {
        pixels = new Ints();
//        String string_ = _contentFile.replace(RETURN_LINE, EMPTY_STRING);
//        string_ = string_.replace(RETURN_LINE2, EMPTY_STRING);
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
        if (StringUtil.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringUtil.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        for (String s: StringUtil.splitChars(_img, SEPARATOR_CHAR)) {
            if (!isNumber(s)) {
                return false;
            }
            nb_++;
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_--;
        if (!_img.startsWith(StringUtil.concat(String.valueOf(_sideLength), String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        return nb_ == _sideLength * _sideLength;
    }

    public static boolean isValidNotEmptyLower(String _img, int _sideLength) {
        if (StringUtil.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringUtil.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        int w_ = -1;
        for (String s: StringUtil.splitChars(_img, SEPARATOR_CHAR)) {
            if (!isNumber(s)) {
                return false;
            }
            int int_ = NumberUtil.parseInt(s);
            nb_++;
            if (w_ < 0) {
                w_ = int_;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_--;
        if (nb_ % w_ != 0) {
            return false;
        }
        int h_ = nb_ / w_;
        return h_ <= _sideLength && w_ <= _sideLength;
    }

    public static boolean isValidNotEmpty(String _img, int _width, int _height) {
        if (StringUtil.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringUtil.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        for (String s: StringUtil.splitChars(_img, SEPARATOR_CHAR)) {
            if (!isNumber(s)) {
                return false;
            }
            nb_++;
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_--;
        if (!_img.startsWith(StringUtil.concat(String.valueOf(_width),String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        return nb_ == _width * _height;
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
        StringList list_ = StringUtil.splitChars(_image, SEPARATOR_CHAR);
        int nb_ = list_.size() - 1;
        int width_ = NumberUtil.parseInt(list_.first());
        int heigth_ = nb_ / width_;
        int xp_ = Math.min(_x+_w,width_);
        int yp_ = Math.min(_y+_h,heigth_);
        int rw_ = xp_ - _x;
        int rh_ = yp_ - _y;
        StringBuilder return_ = new StringBuilder(String.valueOf(rw_));
        int maxYCoords_ = _y+rh_;
        int maxXCoords_ = _x+rw_;
        for (int j=_y;j<maxYCoords_;j++) {
            for (int i=_x;i<maxXCoords_;i++) {
                int index_ = i + width_ * j + 1;
                return_.append(SEPARATOR_CHAR);
                return_.append(list_.get(index_));
            }
        }
        return return_.toString();
    }
    public ImageOld clip(int _x, int _y, int _w, int _h) {
        if (pixels.isEmpty()) {
            return new ImageOld(EMPTY_IMAGE);
        }
        int x_ = Math.abs(_x);
        int y_ = Math.abs(_y);
        int xp_ = Math.min(_x+_w,width);
        int yp_ = Math.min(_y+_h,getHeight());
        int rw_ = xp_ - x_;
        int rh_ = yp_ - y_;
        if (rw_ <= 0 || rh_ <= 0) {
            return new ImageOld(EMPTY_IMAGE);
        }
        ImageOld return_ = new ImageOld();
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
    @Override
    public String display() {
        StringBuilder return_ = new StringBuilder(String.valueOf(width));
        for (int i: pixels) {
            return_.append(SEPARATOR_CHAR);
            return_.append(i);
        }
        return return_.toString();
    }
}
