package code.images;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.PairEq;
import code.util.PairNumber;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;
import code.util.opers.PairUtil;

public final class ConverterBufferedImageNav {

    public static final String IMG_EXT = "png";

    public static final int WHITE_RGB_INT = Color.WHITE.getRGB();

    private static final String GIF = "gif";

    private static final StringList AVAILABLE_FORMATS = new StringList("png","jpg","bmp",GIF,"svg");

    private static boolean _handsFeet_ = true;

    private static final String BASE64 = ";base64,";

    private static final String DATA_IMAGE = "data:image/";

    private static final String EMPTY_STRING = "";

    private static final int THREE_BYTES = 256 * 256 * 256;

    private ConverterBufferedImageNav() {
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
        for (int i = CustList.FIRST_INDEX; i < w_; i++) {
            for (int j = CustList.FIRST_INDEX; j < h_; j++) {
                if (_imgOne.getRGB(i, j) != _imgTwo.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String stackImages(String _back, String _front, boolean _png) {
        String img_ = _front;
        if (img_.isEmpty()) {
            return _back;
        }
//        BufferedImage imgBuff_ = ConverterBufferedImage.toRenderedImageQuick(img_);
        BufferedImage imgBuff_ = ConverterBufferedImageNav.decodeToImage(img_, _png);
        String imgBack_ = _back;
//        BufferedImage imgBuffBack_ = ConverterBufferedImage.toRenderedImageQuick(imgBack_);
        BufferedImage imgBuffBack_ = ConverterBufferedImageNav.decodeToImage(imgBack_, _png);
        int w_ = Math.max(imgBuffBack_.getWidth(), imgBuff_.getWidth());
        int h_ = Math.max(imgBuffBack_.getHeight(), imgBuff_.getHeight());
        int wMin_ = imgBuff_.getWidth();
        int hMin_ = imgBuff_.getHeight();
        BufferedImage combined_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(imgBuffBack_, 0, 0, null);
        g_.drawImage(imgBuff_, (w_ - wMin_) / 2, (h_ - hMin_) / 2, null);

        //return ConverterBufferedImage.toString(combined_);
        String str_ = ConverterBufferedImageNav.toBaseSixtyFour(combined_, _png);
        g_.dispose();
        return str_;
    }

    public static BufferedImage centerImage(String _front, int _side, boolean _png) {
        BufferedImage img_ = ConverterBufferedImageNav.decodeToImage(_front, _png);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_side, _side, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2, null);
        return combined_;
    }

    public static BufferedImage centerImage(String _front, int _width, int _height, boolean _png) {
        BufferedImage img_ = ConverterBufferedImageNav.decodeToImage(_front, _png);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2, null);
        return combined_;
    }

    public static String toBaseSixtyFour(BufferedImage _buffer, boolean _png) {
        if (!_png) {
            int h_ = _buffer.getHeight();
            int w_ = _buffer.getWidth();
            int[][] img_ = new int[h_][w_];
            for (int i = 0; i< w_; i++) {
                for (int j = 0; j< h_;j++) {
                    img_[j][i] = _buffer.getRGB(i,j);
                }
            }
            return BaseSixtyFourUtil.getStringByImage(img_);
        }
        String contourChart_ = EMPTY_STRING;
        try {
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageIO.write( _buffer, IMG_EXT, baos_ );
            baos_.flush();
            byte[] imageInByte_ = baos_.toByteArray();
            contourChart_ = BaseSixtyFourUtil.printBaseSixtyFourBinary(imageInByte_);
        } catch (IOException _0) {
        }
        return contourChart_;
    }

    public static String toBaseSixtyFour(BufferedImage _buffer, boolean _png, String _format) {
        if (!_png) {
            int h_ = _buffer.getHeight();
            int w_ = _buffer.getWidth();
            int[][] img_ = new int[h_][w_];
            for (int i = 0; i< w_; i++) {
                for (int j = 0; j< h_;j++) {
                    img_[j][i] = _buffer.getRGB(i,j);
                }
            }
            return BaseSixtyFourUtil.getStringByImage(img_);
        }
        String contourChart_ = EMPTY_STRING;
        try {
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageIO.write( _buffer, _format, baos_ );
            baos_.flush();
            byte[] imageInByte_ = baos_.toByteArray();
            StringBuilder sb_ = new StringBuilder();
            sb_.append(DATA_IMAGE);
            sb_.append(_format);
            sb_.append(BASE64);
            sb_.append(BaseSixtyFourUtil.printBaseSixtyFourBinary(imageInByte_));
            contourChart_ = sb_.toString();
        } catch (IOException _0) {
        }
        return contourChart_;
    }

    public static String getSquareColorSixtyFour(String _color, String _sep, int _sideLen, boolean _png) {
        return toBaseSixtyFour(getSquareColor(_color, _sep, _sideLen), _png);
    }

    public static BufferedImage getSquareColor(String _color, String _sep, int _sideLen) {
        Color c_ = ConverterBufferedImageNav.getColor(_color, _sep);
        BufferedImage image_ = new BufferedImage(_sideLen, _sideLen, BufferedImage.TYPE_INT_ARGB);
        for (int i = CustList.FIRST_INDEX;i<_sideLen;i++) {
            for (int j = CustList.FIRST_INDEX;j<_sideLen;j++) {
                image_.setRGB(j, i, c_.getRGB());
            }
        }
        return image_;
    }

    public static String surroundImage(String _image, boolean _png, String _format) {
        if (!_png) {
            return _image;
        }
        String contourChart_ = EMPTY_STRING;
        StringBuilder sb_ = new StringBuilder();
        if (AVAILABLE_FORMATS.containsStr(_format)) {
            sb_.append(DATA_IMAGE);
            sb_.append(_format);
            sb_.append(BASE64);
        } else {
            sb_.append(DATA_IMAGE);
            sb_.append(IMG_EXT);
            sb_.append(BASE64);
        }
        sb_.append(_image);
        contourChart_ = sb_.toString();
        return contourChart_;
    }

    public static String surroundImage(boolean _png, String _image) {
        if (!_png) {
            return _image;
        }
        String contourChart_ = EMPTY_STRING;
        StringBuilder sb_ = new StringBuilder();
        sb_.append(DATA_IMAGE);
        sb_.append(IMG_EXT);
        sb_.append(BASE64);
        sb_.append(_image);
        contourChart_ = sb_.toString();
        return contourChart_;
    }

    public static PairNumber<Integer,Integer> getDimensions(String _imageString, boolean _png) {
        BufferedImage img_ = decodeToImage(_imageString, _png);
        return new PairNumber<Integer,Integer>(img_.getWidth(), img_.getHeight());
    }

    public static BufferedImage decodeToImage(String _imageString, boolean _png) {
        if (!_png) {
            int[][] img_ = BaseSixtyFourUtil.getImageByString(_imageString);
            if (img_.length == 0) {
                return null;
            }
            BufferedImage imgBuf_ = new BufferedImage(img_[0].length, img_.length, BufferedImage.TYPE_INT_RGB);
            int y_ = 0;
            for (int[] r: img_) {
                int x_ = 0;
                for (int p: r) {
                    imgBuf_.setRGB(x_, y_, p);
                    x_++;
                }
                y_++;
            }
            return imgBuf_;
        }
        BufferedImage image_ = null;
        byte[] imageByte_;
        try {
            imageByte_ = BaseSixtyFourUtil.parseBaseSixtyFourBinary(_imageString);
            ByteArrayInputStream bis_ = new ByteArrayInputStream(imageByte_);
            image_ = ImageIO.read(bis_);
            bis_.close();
        } catch (Exception _0) {
        }
        return image_;
    }


    public static byte[] toBytes(BufferedImage _buf, String _format) {
        try {
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageIO.write(_buf, _format, baos_);
            baos_.flush();
            byte[] imageInByte_ = baos_.toByteArray();
            baos_.close();
            return imageInByte_;
        } catch (IOException _0) {
            return null;
        }
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

    public static boolean containsInsideWhites(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        EqList<PairNumber<Integer,Integer>> addedPixels_ = whitePixels(_buffered);
        for (int i = CustList.FIRST_INDEX; i < h_; i++) {
            for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                if (addedPixels_.containsObj(new PairNumber<Integer,Integer>(j,i))) {
                    continue;
                }
                int rgb_ = _buffered.getRGB(j, i);
                if (rgb_ == white_) {
                    return true;
                }
            }
        }
        return false;
    }

    public static EqList<PairNumber<Integer,Integer>> containedWhiteInside(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        EqList<PairNumber<Integer,Integer>> addedPixels_ = whitePixels(_buffered);
        EqList<PairNumber<Integer,Integer>> list_ = new EqList<PairNumber<Integer,Integer>>();
        for (int i = CustList.FIRST_INDEX; i < h_; i++) {
            for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                if (addedPixels_.containsObj(new PairNumber<Integer,Integer>(j,i))) {
                    continue;
                }
                int rgb_ = _buffered.getRGB(j, i);
                if (rgb_ != white_) {
                    continue;
                }
                list_.add(new PairNumber<Integer,Integer>(j,i));
            }
        }
        return list_;
    }

    public static EqList<PairNumber<Integer,Integer>> whitePixels(BufferedImage _buffered) {
        int h_ = _buffered.getHeight();
        int w_ = _buffered.getWidth();
        int white_ = WHITE_RGB_INT;
        EqList<PairNumber<Integer,Integer>> addedPixels_ = new EqList<PairNumber<Integer,Integer>>();
        EqList<PairNumber<Integer,Integer>> currentPixels_ = new EqList<PairNumber<Integer,Integer>>();
        EqList<PairNumber<Integer,Integer>> newPixels_ = new EqList<PairNumber<Integer,Integer>>();
        if (isHandsFeet()) {
            if (_buffered.getRGB(CustList.FIRST_INDEX, CustList.FIRST_INDEX) == white_) {
                addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(w_ - 1, CustList.FIRST_INDEX) == white_) {
                addedPixels_.add(new PairNumber<Integer,Integer>(w_ - 1, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(CustList.FIRST_INDEX, h_ - 1) == white_) {
                addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, h_ - 1));
            }
            if (_buffered.getRGB(w_ - 1, h_ - 1) == white_) {
                addedPixels_.add(new PairNumber<Integer,Integer>(w_ -1, h_ - 1));
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, CustList.FIRST_INDEX) == white_) {
                    addedPixels_.add(new PairNumber<Integer,Integer>(i, (int)CustList.FIRST_INDEX));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, h_ - 1) == white_) {
                    addedPixels_.add(new PairNumber<Integer,Integer>(i, h_ - 1));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(CustList.FIRST_INDEX, i) == white_) {
                    addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, i));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(w_ - 1, i) == white_) {
                    addedPixels_.add(new PairNumber<Integer,Integer>(w_ - 1, i));
                }
            }
            addedPixels_.removeDuplicates();
        }
        currentPixels_.addAllElts(addedPixels_);
        while (true) {
            newPixels_ = new EqList<PairNumber<Integer,Integer>>();
            for (PairNumber<Integer,Integer> coords_: currentPixels_) {
                for (PairNumber<Integer,Integer> coordsChild_: PairUtil.getNext(w_, h_, coords_)) {
                    if (addedPixels_.containsObj(coordsChild_)) {
                        continue;
                    }
                    int rgb_ = _buffered.getRGB(coordsChild_.getFirst(), coordsChild_.getSecond());
                    if (rgb_ != white_) {
                        continue;
                    }
                    addedPixels_.add(coordsChild_);
                    newPixels_.add(coordsChild_);
                }
            }
            if (newPixels_.isEmpty()) {
                break;
            }
            currentPixels_ = new EqList<PairNumber<Integer,Integer>>(newPixels_);
        }
        return addedPixels_;
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
        EqList<PairNumber<Integer,Integer>> addedPixels_ = new EqList<PairNumber<Integer,Integer>>();
        EqList<PairNumber<Integer,Integer>> currentPixels_ = new EqList<PairNumber<Integer,Integer>>();
        EqList<PairNumber<Integer,Integer>> newPixels_ = new EqList<PairNumber<Integer,Integer>>();
        if (isHandsFeet()) {
            if (_buffered.getRGB(CustList.FIRST_INDEX, CustList.FIRST_INDEX) == white_) {
                buff_.setRGB(CustList.FIRST_INDEX, CustList.FIRST_INDEX, transWhite_);
                addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(w_ - 1, CustList.FIRST_INDEX) == white_) {
                buff_.setRGB(w_ - 1, CustList.FIRST_INDEX, transWhite_);
                addedPixels_.add(new PairNumber<Integer,Integer>(w_ - 1, (int)CustList.FIRST_INDEX));
            }
            if (_buffered.getRGB(CustList.FIRST_INDEX, h_ - 1) == white_) {
                buff_.setRGB(CustList.FIRST_INDEX, h_ - 1, transWhite_);
                addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, h_ - 1));
            }
            if (_buffered.getRGB(w_ -1, h_ - 1) == white_) {
                buff_.setRGB(w_ -1, h_ - 1, transWhite_);
                addedPixels_.add(new PairNumber<Integer,Integer>(w_ -1, h_ - 1));
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, CustList.FIRST_INDEX) == white_) {
                    buff_.setRGB(i, CustList.FIRST_INDEX, transWhite_);
                    addedPixels_.add(new PairNumber<Integer,Integer>(i, (int)CustList.FIRST_INDEX));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered.getRGB(i, h_ - 1) == white_) {
                    buff_.setRGB(i, h_ - 1, transWhite_);
                    addedPixels_.add(new PairNumber<Integer,Integer>(i, h_ - 1));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(CustList.FIRST_INDEX, i) == white_) {
                    buff_.setRGB(CustList.FIRST_INDEX, i, transWhite_);
                    addedPixels_.add(new PairNumber<Integer,Integer>((int)CustList.FIRST_INDEX, i));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered.getRGB(w_ - 1, i) == white_) {
                    buff_.setRGB(w_ - 1, i, transWhite_);
                    addedPixels_.add(new PairNumber<Integer,Integer>(w_ - 1, i));
                }
            }
        }
        currentPixels_.addAllElts(addedPixels_);
        while (true) {
            newPixels_ = new EqList<PairNumber<Integer,Integer>>();
            for (PairNumber<Integer,Integer> coords_: currentPixels_) {
                for (PairNumber<Integer,Integer> coordsChild_: PairUtil.getNext(w_, h_, coords_)) {
                    if (addedPixels_.containsObj(coordsChild_)) {
                        continue;
                    }
                    int rgb_ = _buffered.getRGB(coordsChild_.getFirst(), coordsChild_.getSecond());
                    if (rgb_ != white_) {
                        continue;
                    }
                    buff_.setRGB(coordsChild_.getFirst(), coordsChild_.getSecond(), transWhite_);
                    addedPixels_.add(coordsChild_);
                    newPixels_.add(coordsChild_);
                }
            }
            if (newPixels_.isEmpty()) {
                break;
            }
            currentPixels_ = new EqList<PairNumber<Integer,Integer>>(newPixels_);
        }
        return buff_;
    }

    public static PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> croppedPointDimensions(BufferedImage _buffered) {
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
        return new PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>>(new PairNumber<Integer,Integer>(indexOne_, indexTwo_),new PairNumber<Integer,Integer>(newWidth_, newHeight_));
    }

    private static int calculateAlphaCode(BufferedImage _buffered,
            int _indexOne, int _y) {
//        return (_buffered.getRGB(_indexOne, _y) >> RGB_BITS) & ALHPA;
        return _buffered.getRGB(_indexOne, _y) / THREE_BYTES;
    }

//    static int testHardOps(int _code) {
//        return (_code >> RGB_BITS) & ALHPA;
//    }

    public static void validateColor(String _color, String _separator) {
        getColor(_color, _separator);
    }

    public static Color getColor(String _color, String _separator) {
        StringList list_ = StringList.splitStrings(_color,_separator);
        Numbers<Integer> ints_ = new Numbers<Integer>();
        for (String c: list_) {
            ints_.add(Integer.parseInt(c));
        }
        return new Color(ints_.first(), ints_.get(CustList.SECOND_INDEX), ints_.get(CustList.SECOND_INDEX+1));
    }

    public static boolean isHandsFeet() {
        return _handsFeet_;
    }

    public static String getDataImage() {
        return DATA_IMAGE;
    }

    public static void setHandsFeet(boolean _handsFeet) {
        ConverterBufferedImageNav._handsFeet_ = _handsFeet;
    }

    public static Color getTransparentBlack() {
        return new Color(Color.BLACK.getRed(), Color.BLACK.getBlue(), Color.BLACK.getGreen(), 0);
    }

    public static Color getTransparentWhite() {
        return new Color(Color.WHITE.getRed(), Color.WHITE.getBlue(), Color.WHITE.getGreen(), 0);
    }

    public static StringList getAvailableFormats() {
        return new StringList(AVAILABLE_FORMATS);
    }
}
