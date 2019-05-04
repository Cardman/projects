package code.gui.images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import code.images.ConverterBufferedImage;
import code.images.Image;
import code.images.IntPoint;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;

public final class ConverterGraphicBufferedImage {

    public static final int WHITE_RGB_INT = 256*256*256-1;

    private static final int THREE_BYTES = 256 * 256 * 256;

    public static boolean eq(BufferedImage _imgOne, BufferedImage _imgTwo) {
        if (_imgOne.getWidth() != _imgTwo.getWidth()) {
            return false;
        }
        if (_imgOne.getHeight() != _imgTwo.getHeight()) {
            return false;
        }
        int w_ = _imgOne.getWidth();
        int h_ = _imgOne.getHeight();
        for (int i = CustList.FIRST_INDEX; i < w_; i++) {
            for (int j = CustList.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Color getTransparentBlack() {
        return new Color(Color.BLACK.getRed(), Color.BLACK.getBlue(), Color.BLACK.getGreen(), 0);
    }

    public static Color getTransparentWhite() {
        return new Color(Color.WHITE.getRed(), Color.WHITE.getBlue(), Color.WHITE.getGreen(), 0);
    }

    public static BufferedImage centerImage(int[][] _front, int _side) {
        BufferedImage img_ = decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_side, _side, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2, null);
        return combined_;
    }
    public static BufferedImage centerImage(int[][] _front, int _width, int _height) {
        BufferedImage img_ = decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2, null);
        return combined_;
    }

    public static int[][] toArrays(BufferedImage _image) {
        int height_ = _image.getHeight();
        int width_ = _image.getWidth();
        int[][] arrays_ = new int[height_][width_];
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                arrays_[i][j] = Numbers.mod(_image.getRGB(j, i), 256*256*256);
            }
        }
        return arrays_;
    }

    public static BufferedImage decodeToImage(int[][] _imageString) {
        int height_ = _imageString.length;
        int width_ = _imageString[0].length;
        BufferedImage image_ = new BufferedImage(width_, height_, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                image_.setRGB(j, i, new Color(_imageString[i][j]).getRGB());
            }
        }
        return image_;
    }

    public static BufferedImage toRenderedImage(String _txt) {
        return toRenderedImage(new Image(_txt));
    }
    public static BufferedImage toRenderedImage(Image _image) {
        int w_ = _image.getWidth();
        int h_ = _image.getHeight();
        BufferedImage image_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        for (int i = CustList.FIRST_INDEX;i<h_;i++) {
            for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                image_.setRGB(j, i, _image.getPixel(j,i));
            }
        }
        return image_;
    }
    public static BufferedImage toRenderedImageQuick(String _txt) {
        StringList lines_ = StringList.splitChars(_txt, Image.SEPARATOR_CHAR);
        int w_ = Numbers.parseInt(lines_.first());
        int h_;
        if (w_ == 0) {
            h_ = 0;
        } else {
            h_ = (lines_.size() - 1) / w_;
        }
        BufferedImage image_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        for (int i = CustList.FIRST_INDEX;i<h_;i++) {
            for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                int index_ = j + w_ * i + 1;
                int int_ = Numbers.parseInt(lines_.get(index_));
                if (int_ == -1) {
                    image_.setRGB(j, i, getTransparentWhite().getRGB());
                } else {
                    image_.setRGB(j, i, int_);
                }
            }
        }
        return image_;
    }


    public static String toString(BufferedImage _image) {
        int w_ = _image.getWidth();
        int h_ = _image.getHeight();
        StringBuilder builder_ = new StringBuilder(String.valueOf(w_));
        for (int i = CustList.FIRST_INDEX;i<h_;i++) {
            for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                builder_.append(Image.SEPARATOR_CHAR);
                builder_.append(_image.getRGB(j, i));
            }
        }
        return builder_.toString();
    }


    public static void transparentAllWhite(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        int transWhite_ = getTransparentWhite().getRGB();
        for (int i = CustList.FIRST_INDEX; i < h_; i++) {
            for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                int rgb_ = _buffered.getRGB(j, i);
                if (rgb_ == white_) {
                    _buffered.setRGB(j, i, transWhite_);
                }
            }
        }
    }

    public static BufferedImage trim(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        int transWhite_ = getTransparentWhite().getRGB();
        BufferedImage buff_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        for (int i = CustList.FIRST_INDEX; i < w_; i++) {
            for (int j = CustList.FIRST_INDEX; j < h_; j++) {
                buff_.setRGB(i, j, _buffered.getRGB(i, j));
            }
        }
        EqList<IntPoint> addedPixels_ = new EqList<IntPoint>();
        EqList<IntPoint> currentPixels_ = new EqList<IntPoint>();
        EqList<IntPoint> newPixels_ = new EqList<IntPoint>();
        if (ConverterBufferedImage.isHandsFeet()) {
            if (_buffered.getRGB(CustList.FIRST_INDEX, CustList.FIRST_INDEX) == white_) {
                buff_.setRGB(CustList.FIRST_INDEX, CustList.FIRST_INDEX, transWhite_);
                addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(w_ - 1, CustList.FIRST_INDEX) == white_) {
                buff_.setRGB(w_ - 1, CustList.FIRST_INDEX, transWhite_);
                addedPixels_.add(new IntPoint(w_ - 1, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(CustList.FIRST_INDEX, h_ - 1) == white_) {
                buff_.setRGB(CustList.FIRST_INDEX, h_ - 1, transWhite_);
                addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, h_ - 1));
            }
            if (_buffered.getRGB(w_ -1, h_ - 1) == white_) {
                buff_.setRGB(w_ -1, h_ - 1, transWhite_);
                addedPixels_.add(new IntPoint(w_ -1, h_ - 1));
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, CustList.FIRST_INDEX) == white_) {
                    buff_.setRGB(i, CustList.FIRST_INDEX, transWhite_);
                    addedPixels_.add(new IntPoint(i, (int)CustList.FIRST_INDEX));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, h_ - 1) == white_) {
                    buff_.setRGB(i, h_ - 1, transWhite_);
                    addedPixels_.add(new IntPoint(i, h_ - 1));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(CustList.FIRST_INDEX, i) == white_) {
                    buff_.setRGB(CustList.FIRST_INDEX, i, transWhite_);
                    addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, i));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(w_ - 1, i) == white_) {
                    buff_.setRGB(w_ - 1, i, transWhite_);
                    addedPixels_.add(new IntPoint(w_ - 1, i));
                }
            }
        }
        currentPixels_.addAllElts(addedPixels_);
        while (true) {
            newPixels_ = new EqList<IntPoint>();
            for (IntPoint coords_: currentPixels_) {
                for (IntPoint coordsChild_: ConverterBufferedImage.getNext(w_, h_, coords_)) {
                    if (addedPixels_.containsObj(coordsChild_)) {
                        continue;
                    }
                    int rgb_ = _buffered.getRGB(coordsChild_.getXcoords(), coordsChild_.getYcoords());
                    if (rgb_ != white_) {
                        continue;
                    }
                    buff_.setRGB(coordsChild_.getXcoords(), coordsChild_.getYcoords(), transWhite_);
                    addedPixels_.add(coordsChild_);
                    newPixels_.add(coordsChild_);
                }
            }
            if (newPixels_.isEmpty()) {
                break;
            }
            currentPixels_ = new EqList<IntPoint>(newPixels_);
        }
        return buff_;
    }

    public static IntPointPair croppedPointDimensions(BufferedImage _buffered) {
        int indexOne_ = 0;
        int indexTwo_ = 0;
        int w_ = _buffered.getWidth();
        int h_ = _buffered.getHeight();
        int maxIndexOne_ = _buffered.getWidth() - 1;
        int maxIndexTwo_ = _buffered.getHeight() - 1;
        while (indexOne_ < w_) {
            boolean whiteCol_ = true;
            for (int y = CustList.FIRST_INDEX; y < h_; y++) {
                int alph_ = calculateAlphaCode(_buffered, indexOne_, y);
                if (alph_ != 0) {
                    whiteCol_ = false;
                    break;
                }
//                if (_buffered.getRGB(indexOne_, y) != TRANSPARENT_WHITE.getRGB()) {
                /*if (_buffered.getRGB(indexOne_, y) != TRANSPARENT_WHITE.getRGB()) {
                    whiteCol_ = false;
                    break;
                }*/
            }
            if (!whiteCol_) {
                break;
            }
            indexOne_ ++;
        }
        while (maxIndexOne_ >= 0) {
            boolean whiteCol_ = true;
            for (int y = CustList.FIRST_INDEX; y < h_; y++) {
                int alph_ = calculateAlphaCode(_buffered, maxIndexOne_, y);
                if (alph_ != 0) {
                    whiteCol_ = false;
                    break;
                }
                /*if (_buffered.getRGB(maxIndexOne_, y) != TRANSPARENT_WHITE.getRGB()) {
                    whiteCol_ = false;
                    break;
                }*/
            }
            if (!whiteCol_) {
                break;
            }
            maxIndexOne_ --;
        }
//        while (indexOne_ < maxIndexOne_) {
//            boolean whiteCol_ = true;
//            for (int y = CustList.FIRST_INDEX; y < h_; y++) {
////                if (_buffered.getRGB(indexOne_, y) != TRANSPARENT_WHITE.getRGB()) {
//                if (_buffered.getRGB(indexOne_, y) != WHITE_RGB_INT) {
//                    whiteCol_ = false;
//                    break;
//                }
//            }
//            if (!whiteCol_) {
//                break;
//            }
//            for (int y = CustList.FIRST_INDEX; y < h_; y++) {
//                if (_buffered.getRGB(maxIndexOne_, y) != WHITE_RGB_INT) {
//                    whiteCol_ = false;
//                    break;
//                }
//            }
//            if (!whiteCol_) {
//                break;
//            }
//            indexOne_ ++;
//            maxIndexOne_ --;
//        }
        int newWidth_ = maxIndexOne_ - indexOne_ + 1;
        while (indexTwo_ < h_) {
            boolean whiteRow_ = true;
            for (int x = CustList.FIRST_INDEX; x < w_; x++) {
                int alph_ = calculateAlphaCode(_buffered, x, indexTwo_);
                if (alph_ != 0) {
                    whiteRow_ = false;
                    break;
                }
                /*if (_buffered.getRGB(x, indexTwo_) != TRANSPARENT_WHITE.getRGB()) {
                    whiteRow_ = false;
                    break;
                }*/
            }
            if (!whiteRow_) {
                break;
            }
            indexTwo_ ++;
        }
        while (maxIndexTwo_ >= 0) {
            boolean whiteRow_ = true;
            for (int x = CustList.FIRST_INDEX; x < w_; x++) {
                int alph_ = calculateAlphaCode(_buffered, x, maxIndexTwo_);
                if (alph_ != 0) {
                    whiteRow_ = false;
                    break;
                }
                /*if (_buffered.getRGB(x, maxIndexTwo_) != TRANSPARENT_WHITE.getRGB()) {
                    whiteRow_ = false;
                    break;
                }*/
            }
            if (!whiteRow_) {
                break;
            }
            maxIndexTwo_ --;
        }
        int newHeight_ = maxIndexTwo_ - indexTwo_ + 1;
        return new IntPointPair(new IntPoint(indexOne_, indexTwo_),new IntPoint(newWidth_, newHeight_));
    }

    private static int calculateAlphaCode(BufferedImage _buffered,
            int _indexOne, int _y) {
        return _buffered.getRGB(_indexOne, _y) / THREE_BYTES;
    }


}
