package code.imagesurl;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import code.images.Animation;
import code.images.ConverterBufferedImage;
import code.images.Image;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.PairNumber;
import code.util.opers.PairUtil;

public final class ConverterBufferedImageIo {

    public static final String IMG_EXT = "png";

    public static final int WHITE_RGB_INT = 256*256*256-1;

    private ConverterBufferedImageIo() {
    }

    public static Image toImage(String _fileName) {
        return new Image(toString(_fileName));
    }
    public static String toString(String _fileName) {
        try {
            BufferedImage img_ = ImageIO.read(new File(_fileName));
            int w_ = img_.getWidth();
            int h_ = img_.getHeight();
            StringBuilder builder_ = new StringBuilder(String.valueOf(w_));
            for (int i = CustList.FIRST_INDEX;i<h_;i++) {
                for (int j = CustList.FIRST_INDEX;j<w_;j++) {
                    builder_.append(Image.SEPARATOR_CHAR);
                    builder_.append(img_.getRGB(j, i));
                }
            }
            return builder_.toString();
        } catch (Exception _0) {
            return null;
        }
    }

    public static void save(String _fileName, Animation _image, String _format) {
        write(toRenderedImage(_image), _format, new File(_fileName));
    }
    public static void save(String _fileName, Image _image, String _format) {
        write(toRenderedImage(_image), _format, new File(_fileName));
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
    public static void trimCopy(String _fileName,String _fileNameExport) {
        BufferedImage img_ = read(new File(_fileName));
        write(img_,IMG_EXT,new File(_fileNameExport));
    }
    public static BufferedImage trim(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return trim(img_);
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
        if (ConverterBufferedImage.isHandsFeet()) {
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

    public static Color getTransparentWhite() {
        return new Color(Color.WHITE.getRed(), Color.WHITE.getBlue(), Color.WHITE.getGreen(), 0);
    }
    public static boolean containsInsideWhites(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return ConverterBufferedImage.containsInsideWhites(toArrays(img_));
    }
    public static EqList<PairNumber<Integer,Integer>> containedWhiteInside(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return ConverterBufferedImage.containedWhiteInside(toArrays(img_));
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

    private static BufferedImage read(File _file) {
        try {
            return ImageIO.read(_file);
        } catch (IOException _0) {
            return null;
        }
    }

    private static void write(RenderedImage _img, String _format, File _out) {
        try {
            ImageIO.write(_img, _format, _out);
        } catch (IOException _0) {
        }
    }
}
