package code.gui.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import code.gui.CustGraphics;
import code.images.IntPoint;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class ConverterGraphicBufferedImage {

    public static final int WHITE_RGB_INT = 256*256*256-1;

    private static final int THREE_BYTES = 256 * 256 * 256;

    private ConverterGraphicBufferedImage(){
    }
    public static boolean eq(BufferedImage _imgOne, BufferedImage _imgTwo) {
        if (_imgOne.getWidth() != _imgTwo.getWidth()) {
            return false;
        }
        if (_imgOne.getHeight() != _imgTwo.getHeight()) {
            return false;
        }
        int w_ = _imgOne.getWidth();
        int h_ = _imgOne.getHeight();
        for (int i = IndexConstants.FIRST_INDEX; i < w_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < h_; j++) {
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
        CustGraphics g_ = new CustGraphics(combined_.getGraphics());
        g_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2);
        return combined_;
    }
    public static BufferedImage centerImage(int[][] _front, int _width, int _height) {
        BufferedImage img_ = decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        CustGraphics g_ = new CustGraphics(combined_.getGraphics());
        g_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2);
        return combined_;
    }

    public static int[][] toArrays(BufferedImage _image) {
        int height_ = _image.getHeight();
        int width_ = _image.getWidth();
        int[][] arrays_ = new int[height_][width_];
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                arrays_[i][j] = NumberUtil.mod(_image.getRGB(j, i), 256*256*256);
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


    public static void transparentAllWhite(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        int transWhite_ = getTransparentWhite().getRGB();
        for (int i = IndexConstants.FIRST_INDEX; i < h_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < w_; j++) {
                int rgb_ = _buffered.getRGB(j, i);
                if (rgb_ == white_) {
                    _buffered.setRGB(j, i, transWhite_);
                }
            }
        }
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
            for (int y = IndexConstants.FIRST_INDEX; y < h_; y++) {
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
            indexOne_++;
        }
        while (maxIndexOne_ >= 0) {
            boolean whiteCol_ = true;
            for (int y = IndexConstants.FIRST_INDEX; y < h_; y++) {
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
            maxIndexOne_--;
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
//            indexOne_++;
//            maxIndexOne_--;
//        }
        int newWidth_ = maxIndexOne_ - indexOne_ + 1;
        while (indexTwo_ < h_) {
            boolean whiteRow_ = true;
            for (int x = IndexConstants.FIRST_INDEX; x < w_; x++) {
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
            indexTwo_++;
        }
        while (maxIndexTwo_ >= 0) {
            boolean whiteRow_ = true;
            for (int x = IndexConstants.FIRST_INDEX; x < w_; x++) {
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
            maxIndexTwo_--;
        }
        int newHeight_ = maxIndexTwo_ - indexTwo_ + 1;
        return new IntPointPair(new IntPoint(indexOne_, indexTwo_),new IntPoint(newWidth_, newHeight_));
    }

    private static int calculateAlphaCode(BufferedImage _buffered,
            int _indexOne, int _y) {
        return _buffered.getRGB(_indexOne, _y) / THREE_BYTES;
    }


}
