package code.images;
import java.awt.image.BufferedImage;

import code.sml.FromAndToString;
import code.util.CustList;
import code.util.Numbers;
import code.util.PairNumber;
import code.util.StringList;
import code.util.ints.Displayable;

public final class Image implements Displayable {

    public static final char SEPARATOR_CHAR = ';';

    private static final String RETURN_LINE2 = "\r";

    private static final String RETURN_LINE = "\n";

//    private static final String EMPTY_STRING = "";

    private static final String EMPTY_IMAGE = "0";

    private static final char MINUS = '-';

    private int width;

    private Numbers<Integer> pixels;

    private Image() {
        pixels = new Numbers<Integer>();
    }
    public Image(String _contentFile) {
        pixels = new Numbers<Integer>();
//        String string_ = _contentFile.replace(RETURN_LINE, EMPTY_STRING);
//        string_ = string_.replace(RETURN_LINE2, EMPTY_STRING);
        String string_ = StringList.removeStrings(_contentFile, RETURN_LINE, RETURN_LINE2);
        StringList lines_ = StringList.splitChars(string_, SEPARATOR_CHAR);
        width = Integer.parseInt(lines_.first());
        int len_ = lines_.size();
        for (int i=CustList.SECOND_INDEX;i<len_;i++) {
            pixels.add(Integer.parseInt(lines_.get(i)));
        }
    }

    @FromAndToString
    public static Image newImage(String _string) {
        return new Image(_string);
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

    public static boolean isValid(String _img) {
        int nb_ = 0;
        for (String s: StringList.splitChars(_img, SEPARATOR_CHAR)) {
            try {
                Integer.parseInt(s);
                nb_ ++;
            } catch (Exception _0) {
                return false;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_ --;
        int i_ = _img.indexOf(SEPARATOR_CHAR);
        if (i_ == CustList.INDEX_NOT_FOUND_ELT) {
            return StringList.quickEq(_img,EMPTY_IMAGE);
        }
        String w_ = _img.substring(0, i_);
        int width_ = Integer.parseInt(w_);
        if (width_ <= 0) {
            return false;
        }
        return nb_ % width_ == 0;
    }

    public static boolean isValidNotEmpty(String _img, int _sideLength) {
        if (StringList.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringList.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        for (String s: StringList.splitChars(_img, SEPARATOR_CHAR)) {
            try {
                Integer.parseInt(s);
                nb_ ++;
            } catch (Exception _0) {
                return false;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_ --;
        if (!_img.startsWith(StringList.concat(String.valueOf(_sideLength), String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        return nb_ == _sideLength * _sideLength;
    }

    public static boolean isValidNotEmptyLower(String _img, int _sideLength) {
        if (StringList.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringList.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        int w_ = -1;
        for (String s: StringList.splitChars(_img, SEPARATOR_CHAR)) {
            try {
                int int_ = Integer.parseInt(s);
                nb_ ++;
                if (w_ < 0) {
                    w_ = int_;
                }
            } catch (Exception _0) {
                return false;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_ --;
        if (nb_ % w_ != 0) {
            return false;
        }
        int h_ = nb_ / w_;
        return h_ <= _sideLength && w_ <= _sideLength;
    }

    public static boolean isValidNotEmpty(String _img, int _width, int _height) {
        if (StringList.quickEq(_img,EMPTY_IMAGE)) {
            return false;
        }
        if (_img.isEmpty()) {
            return false;
        }
        if (_img.startsWith(StringList.concat(EMPTY_IMAGE,String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        int nb_ = 0;
        for (String s: StringList.splitChars(_img, SEPARATOR_CHAR)) {
            try {
                Integer.parseInt(s);
                nb_ ++;
            } catch (Exception _0) {
                return false;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_ --;
        if (!_img.startsWith(StringList.concat(String.valueOf(_width),String.valueOf(SEPARATOR_CHAR)))) {
            return false;
        }
        return nb_ == _width * _height;
    }

    public static boolean isValidNotEmptyScale(String _img, int _sideLength) {
        int nb_ = 0;
        for (String s: StringList.splitChars(_img, SEPARATOR_CHAR)) {
            try {
                Integer.parseInt(s);
                nb_ ++;
            } catch (Exception _0) {
                return false;
            }
        }
        if (_img.charAt(0) == MINUS) {
            return false;
        }
        nb_ --;
        int i_ = _img.indexOf(SEPARATOR_CHAR);
        if (i_ == CustList.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        String w_ = _img.substring(0, i_);
        int width_ = Integer.parseInt(w_);
        if (width_ % _sideLength != 0) {
            return false;
        }
        int heigth_ = nb_ / width_;
        return heigth_ % _sideLength == 0;
    }

    public static PairNumber<Integer,Integer> getDimensions(String _img, int _sideLength) {
        int nb_ = StringList.splitChars(_img, SEPARATOR_CHAR).size() - 1;
        int i_ = _img.indexOf(SEPARATOR_CHAR);
        String w_ = _img.substring(0, i_);
        int width_ = Integer.parseInt(w_);
        int heigth_ = nb_ / width_;
        return new PairNumber<Integer,Integer>(width_/_sideLength, heigth_/_sideLength);
    }

    public static String clipSixtyFour(String _image,int _x,int _y,int _w,int _h) {
        BufferedImage img_ = ConverterBufferedImage.decodeToImage(_image);
        int xp_ = Math.min(_x+_w, img_.getWidth());
        int yp_ = Math.min(_y+_h, img_.getHeight());
        int rw_ = xp_ - _x;
        int rh_ = yp_ - _y;
        BufferedImage subImg_ = img_.getSubimage(_x, _y, rw_, rh_);
        return ConverterBufferedImage.toBaseSixtyFour(subImg_);
    }

    public static String clip(String _image,int _x,int _y,int _w,int _h) {
        StringList list_ = StringList.splitChars(_image, SEPARATOR_CHAR);
        int nb_ = list_.size() - 1;
        int width_ = Integer.parseInt(list_.first());
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
    public Image clip(int _x,int _y,int _w,int _h) {
        if (pixels.isEmpty()) {
            return new Image(EMPTY_IMAGE);
        }
        int x_ = Math.abs(_x);
        int y_ = Math.abs(_y);
        int xp_ = Math.min(_x+_w,width);
        int yp_ = Math.min(_y+_h,getHeight());
        int rw_ = xp_ - x_;
        int rh_ = yp_ - y_;
        if (rw_ <= 0 || rh_ <= 0) {
            return new Image(EMPTY_IMAGE);
        }
        Image return_ = new Image();
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


    public void setWidth(int _width) {
        width = _width;
    }


    public Numbers<Integer> getPixels() {
        return pixels;
    }


    public void setPixels(Numbers<Integer> _pixels) {
        pixels = _pixels;
    }

    @FromAndToString
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
