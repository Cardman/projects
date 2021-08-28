package code.gui.images;



import code.gui.GuiConstants;
import code.images.IntPoint;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class ConverterGraphicBufferedImage {

    public static final int WHITE_RGB_INT = Integer.MAX_VALUE;

    private static final int THREE_BYTES = 256 * 256 * 256;

    private ConverterGraphicBufferedImage(){
    }
    public static boolean eq(AbstractImage _imgOne, AbstractImage _imgTwo) {
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

    public static AbstractImage centerImage(AbstractImageFactory _fact,int[][] _front, int _side) {
        AbstractImage img_ = decodeToImage(_fact,_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        AbstractImage combined_ = _fact.newImageArgb(_side, _side);
        combined_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2);
        return combined_;
    }
    public static AbstractImage centerImage(AbstractImageFactory _fact,int[][] _front, int _width, int _height) {
        AbstractImage img_ = decodeToImage(_fact,_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        AbstractImage combined_ = _fact.newImageArgb(_width, _height);
        combined_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2);
        return combined_;
    }

    public static int[][] toArrays(AbstractImage _image) {
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

    public static byte[] toBytes(int[] _pix) {
        int n_ = _pix.length;
        byte[] out_ = new byte[n_ * 4];
        int pos_ = 0;
        for (int p : _pix) {
            out_[pos_] = (byte) (p / (256 * 256 * 256));
            out_[pos_ + 1] = (byte) (p / (256 * 256) % 256);
            out_[pos_ + 2] = (byte) (p / (256) % 256);
            out_[pos_ + 3] = (byte) (p % 256);
            pos_ += 4;
        }
        return out_;
    }
    public static AbstractImage decodeToImage(AbstractImageFactory _fact,int[][] _imageString) {
        int height_ = _imageString.length;
        int width_ = _imageString[0].length;
        AbstractImage image_ = _fact.newImageArgb(width_, height_);
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                image_.setRGB(j, i, GuiConstants.newColor(_imageString[i][j]));
            }
        }
        return image_;
    }


    public static void transparentAllWhite(AbstractImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int transWhite_ = 256*256*255+256*255+255;
        for (int i = IndexConstants.FIRST_INDEX; i < h_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < w_; j++) {
                int rgb_ = _buffered.getRGB(j, i);
                if (rgb_ == WHITE_RGB_INT) {
                    _buffered.setRGB(j, i, transWhite_);
                }
            }
        }
    }

    public static IntPointPair croppedPointDimensions(AbstractImage _buffered) {
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

    private static int calculateAlphaCode(AbstractImage _buffered,
            int _indexOne, int _y) {
        return _buffered.getRGB(_indexOne, _y) / THREE_BYTES;
    }


//    public static void drawImage(AbstractImage _dest, AbstractImage _img, int _x, int _y) {
//        int h_ = _img.getHeight() + _y;
//        int w_ = _img.getWidth() + _x;
//        for (int i = _y; i < h_; i++) {
//            for (int j = _x; j < w_; j++) {
//                _dest.setRGB(j,i,_img.getRGB(j-_x,i-_y));
//            }
//        }
//    }
}
