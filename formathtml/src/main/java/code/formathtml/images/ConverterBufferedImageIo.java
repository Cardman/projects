package code.formathtml.images;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import code.images.Animation;
import code.images.ConverterBufferedImage;
import code.images.Image;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EqList;
import code.util.PairNumber;
import code.util.StringList;

public final class ConverterBufferedImageIo {

    private ConverterBufferedImageIo() {
    }

    public static BufferedImage readImage(String _fileName) {
        return ConverterBufferedImage.toRenderedImageQuick(StreamTextFile.contentsOfFile(_fileName));
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
            _0.printStackTrace();
            return null;
        }
    }
    public static void fromTextToImage(String _fromFileName, String _toFileName) {
        if (!_toFileName.contains(StringList.POINT)) {
            fromTextToImage(_fromFileName,_toFileName+StringList.POINT+ConverterBufferedImage.IMG_EXT,ConverterBufferedImage.IMG_EXT);
        } else {
            String ext_ = StringList.splitStrings(_toFileName,StringList.POINT).get(1);
            fromTextToImage(_fromFileName,_toFileName,ext_);
        }
    }
    public static void fromTextToImage(String _fromFileName, String _toFileName, String _format) {
        write(ConverterBufferedImage.toRenderedImage(new Image(StreamTextFile.contentsOfFile(_fromFileName))), _format, new File(_toFileName));
    }
    public static void save(String _fileName, Animation _image, String _format) {
        write(ConverterBufferedImage.toRenderedImage(_image), _format, new File(_fileName));
    }
    public static void save(String _fileName, Image _image, String _format) {
        write(ConverterBufferedImage.toRenderedImage(_image), _format, new File(_fileName));
    }
    public static void trimCopy(String _fileName,String _fileNameExport) {
        BufferedImage img_ = read(new File(_fileName));
        write(img_,ConverterBufferedImage.IMG_EXT,new File(_fileNameExport));
    }
    public static BufferedImage trim(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return ConverterBufferedImage.trim(img_);
    }
    public static boolean containsInsideWhites(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return ConverterBufferedImage.containsInsideWhites(img_);
    }
    public static EqList<PairNumber<Integer,Integer>> containedWhiteInside(String _fileName) {
        BufferedImage img_ = read(new File(_fileName));
        return ConverterBufferedImage.containedWhiteInside(img_);
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
            _0.printStackTrace();
        }
    }
}
