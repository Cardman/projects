package code.images;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.PairEq;
import code.util.PairNumber;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;
import code.util.opers.PairUtil;

public final class ConverterBufferedImage {

    public static final String IMG_EXT = "png";

    public static final int WHITE_RGB_INT = Color.WHITE.getRGB();

    private static final String JAVAX_IMAGEIO_GIF_IMAGE_1_0 = "javax_imageio_gif_image_1.0";

    private static final String IMAGE_DESCRIPTOR = "ImageDescriptor";

    private static final String IMAGE_TOP_POSITION = "imageTopPosition";

    private static final String IMAGE_LEFT_POSITION = "imageLeftPosition";

    private static final String IMAGE_HEIGHT = "imageHeight";

    private static final String IMAGE_WIDTH = "imageWidth";

    private static final long FACTOR_MILLIS_SECONDS = 10l;

    private static final String NETSCAPE = "NETSCAPE";

    private static final String TRANSPARENT_COLOR_INDEX = "transparentColorIndex";

    private static final String TRANSPARENT_COLOR_FLAG = "transparentColorFlag";

    private static final String USER_INPUT_FLAG = "userInputFlag";

    private static final String NONE = "none";

    private static final String DISPOSAL_METHOD = "disposalMethod";

    private static final String TWO = "2.0";

    private static final String AUTHENTICATION_CODE = "authenticationCode";

    private static final String APPLICATION_ID = "applicationID";

    private static final String ZERO = "0";

    private static final String FALSE = "FALSE";

    private static final String APPLICATION_EXTENSION = "ApplicationExtension";

    private static final String APPLICATION_EXTENSIONS = "ApplicationExtensions";

    private static final String DELAY_TIME = "delayTime";

    private static final String GRAPHIC_CONTROL_EXTENSION = "GraphicControlExtension";

    private static final String GIF = "gif";

    private static final StringList AVAILABLE_FORMATS = new StringList("png","jpg","bmp",GIF,"svg");

    private static boolean _handsFeet_ = true;

    private static final String BASE64 = ";base64,";

    private static final String DATA_IMAGE = "data:image/";

    private static final String EMPTY_STRING = "";

//    private static final int RGB_BITS = 24;

//    private static final int ALHPA = 0xff;

    private static final int THREE_BYTES = 256 * 256 * 256;

    private ConverterBufferedImage() {
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
    public static int[][] stackImages(int[][] _back, int[][] _front) {
        int[][] img_ = _front;
        if (img_.length == 0) {
            return _back;
        }
        int w_ = Math.max(_back[0].length, _front[0].length);
        int h_ = Math.max(_back.length, _front.length);
        int wMin_ = _front[0].length;
        int hMin_ = _front.length;

        int[][] str_ = new int[h_][w_];
        int hLoc_ = _back.length;
        int wLoc_ = _back[0].length;
        for (int i = 0; i < hLoc_; i++) {
            for (int j = 0; j < wLoc_; j++) {
                str_[i][j] =_back[i][j];
            }
        }
        int offx_ = (w_ - wMin_) / 2;
        int offy_ = (h_ - hMin_) / 2;
        hLoc_ = _front.length + offy_;
        wLoc_ = _front[0].length + offx_;
        for (int i = offy_; i < hLoc_; i++) {
            for (int j = offx_; j < wLoc_; j++) {
                int pixel_ = _front[i-offy_][j-offx_];
                if (pixel_ != 256*256*256 - 1) {
                    str_[i][j] =pixel_;
                }
            }
        }
        return str_;
    }

    public static BufferedImage centerImage(int[][] _front, int _side) {
        BufferedImage img_ = ConverterBufferedImage.decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_side, _side, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2, null);
        return combined_;
    }
    public static BufferedImage centerImage(int[][] _front, int _width, int _height) {
        BufferedImage img_ = ConverterBufferedImage.decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2, null);
        return combined_;
    }


    public static BufferedImage getSquareColor(String _color, String _sep, int _sideLen) {
        Color c_ = ConverterBufferedImage.getColor(_color, _sep);
        BufferedImage image_ = new BufferedImage(_sideLen, _sideLen, BufferedImage.TYPE_INT_RGB);
        for (int i = CustList.FIRST_INDEX;i<_sideLen;i++) {
            for (int j = CustList.FIRST_INDEX;j<_sideLen;j++) {
                image_.setRGB(j, i, c_.getRGB());
            }
        }
        return image_;
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
        int w_ = Integer.parseInt(lines_.first());
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
                int int_ = Integer.parseInt(lines_.get(index_));
                if (int_ == -1) {
                    image_.setRGB(j, i, getTransparentWhite().getRGB());
                } else {
                    image_.setRGB(j, i, int_);
                }
            }
        }
        return image_;
    }
    public static BufferedImage toRenderedImage(Animation _images) {
        int w_ = _images.getImagesDelays().first().getImage().getWidth();
        int h_ = _images.getImagesDelays().first().getImage().getHeight();
        BufferedImage image_ = new BufferedImage(w_*_images.getImagesDelays().size(), h_, BufferedImage.TYPE_INT_ARGB);
        int imgs_ = _images.getImagesDelays().size();
        for (int img_ = CustList.FIRST_INDEX; img_< imgs_;img_++) {
            for (int i = CustList.FIRST_INDEX;i<h_;i++) {
                for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                    image_.setRGB(j+w_*img_, i, _images.getImagesDelays().get(img_).getImage().getPixel(j,i));
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
        ConverterBufferedImage._handsFeet_ = _handsFeet;
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

    public static String getSquareColorSixtyFour(String _color,
            String _separatorRgb, int _sideLength) {
        Color c_ = ConverterBufferedImage.getColor(_color, _separatorRgb);
        int[][] pixels_ = new int[_sideLength][_sideLength];
        for (int i = 0; i < _sideLength; i++) {
            for (int j = 0; j < _sideLength; j++) {
                pixels_[i][j] = c_.getRGB();
            }
        }
        return BaseSixtyFourUtil.getSringByImage(pixels_);
    }
}
