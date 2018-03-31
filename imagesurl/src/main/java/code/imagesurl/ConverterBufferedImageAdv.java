package code.imagesurl;

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

import code.images.GifAnimation;
import code.images.Image;
import code.util.CustList;
import code.util.Numbers;
import code.util.PairNumber;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;

public class ConverterBufferedImageAdv {

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

    private static final String BASE64 = ";base64,";

    private static final String DATA_IMAGE = "data:image/";

    private static final String EMPTY_STRING = "";

    public static String stackImages(String _back, String _front) {
        String img_ = _front;
        if (img_.isEmpty()) {
            return _back;
        }
//        BufferedImage imgBuff_ = ConverterBufferedImage.toRenderedImageQuick(img_);
        BufferedImage imgBuff_ = decodeToImage(img_);
        String imgBack_ = _back;
//        BufferedImage imgBuffBack_ = ConverterBufferedImage.toRenderedImageQuick(imgBack_);
        BufferedImage imgBuffBack_ = decodeToImage(imgBack_);
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
        String str_ = toBaseSixtyFour(combined_);
        g_.dispose();
        return str_;
    }

    public static StringList getAvailableFormats() {
        return new StringList(AVAILABLE_FORMATS);
    }

    public static String clipSixtyFour(String _image,int _x,int _y,int _w,int _h) {
        BufferedImage img_ = decodeToImage(_image);
        int xp_ = Math.min(_x+_w, img_.getWidth());
        int yp_ = Math.min(_y+_h, img_.getHeight());
        int rw_ = xp_ - _x;
        int rh_ = yp_ - _y;
        BufferedImage subImg_ = img_.getSubimage(_x, _y, rw_, rh_);
        return toBaseSixtyFour(subImg_);
    }

    public static BufferedImage centerImage(String _front, int _side) {
        BufferedImage img_ = decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_side, _side, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_side - wMin_) / 2, (_side - hMin_) / 2, null);
        return combined_;
    }
    public static BufferedImage centerImage(String _front, int _width, int _height) {
        BufferedImage img_ = decodeToImage(_front);
        int wMin_ = img_.getWidth();
        int hMin_ = img_.getHeight();
        BufferedImage combined_ = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = combined_.getGraphics();
        g_.drawImage(img_, (_width - wMin_) / 2, (_height - hMin_) / 2, null);
        return combined_;
    }

    public static byte[] toBytesGif(GifAnimation _gif) {
        return toBytesGif(_gif.getImages(), _gif.getDelayMillis() / FACTOR_MILLIS_SECONDS, _gif.isLoop());
    }
//TODO change to images
    public static byte[] toBytesGif(CustList<BufferedImage> _images, long _delayHundreds, boolean _loop) {
        try {

            ImageWriter writer_ = ImageIO.getImageWritersByFormatName(GIF).next();
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageOutputStream cios_ = ImageIO.createImageOutputStream(baos_);
            writer_.setOutput(cios_);
            int noi_ = _images.size();
            ImageWriteParam writeParam_ = writer_.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier_ = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
            IIOMetadata metadata_ = writer_.getDefaultImageMetadata(typeSpecifier_, writeParam_);
            setMeta(metadata_, _delayHundreds, _loop);

            writer_.prepareWriteSequence(metadata_);
            for (int i = 0; i < noi_; i++) {
                BufferedImage image_ = _images.get(i);

                metadata_ = writer_.getDefaultImageMetadata(typeSpecifier_, writeParam_);
                setMeta(metadata_, _delayHundreds, _loop);
//                writer_.writeToSequence(new IIOImage(image_, null, metadata_), null);
                writer_.writeToSequence(new IIOImage(image_, null, metadata_), null);
            }

            writer_.endWriteSequence();
            cios_.flush();
            return baos_.toByteArray();
        } catch (Exception _0) {
        }
        return null;
    }

    public static String toBaseSixtyFour(BufferedImage _buffer, String _format) {
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

    public static String toBaseSixtyFour(BufferedImage _buffer) {
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

    public static String toBaseSixtyFourGif(ImageReader _reader) {
        String contourChart_ = EMPTY_STRING;
        try {

            ImageWriter writer_ = ImageIO.getImageWritersByFormatName(GIF).next();
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageOutputStream cios_ = ImageIO.createImageOutputStream(baos_);
            writer_.setOutput(cios_);
            int noi_ = _reader.getNumImages(true);
            for (int i = 0; i < noi_; i++) {
                BufferedImage image_ = _reader.read(i);
                IIOMetadata metadata_ = _reader.getImageMetadata(i);

                if (i == CustList.FIRST_INDEX) {
                    writer_.prepareWriteSequence(metadata_);
                }
                writer_.writeToSequence(new IIOImage(image_, null, metadata_), null);
            }

            cios_.flush();
            baos_.flush();
            byte[] imageInByte_ = baos_.toByteArray();
            StringBuilder sb_ = new StringBuilder();
            sb_.append(BaseSixtyFourUtil.printBaseSixtyFourBinary(imageInByte_));
            contourChart_ = sb_.toString();
        } catch (IOException _0) {
        }
        return contourChart_;
    }

    public static String toBaseSixtyFourOptGif(ImageReader _reader) {
        return BaseSixtyFourUtil.printBaseSixtyFourBinary(toBytesOptGif(_reader));
    }

    public static byte[] toBytesOptGif(ImageReader _reader) {
        try {

            int noi_ = _reader.getNumImages(true);

            IIOMetadata metadata_ = _reader.getStreamMetadata();
            boolean loop_ = false;
            long hundreds_ = 0L;
            if (metadata_ != null) {
                String metaFormatName_ = metadata_.getNativeMetadataFormatName();
                IIOMetadataNode root_ = (IIOMetadataNode) metadata_.getAsTree(metaFormatName_);
                Element elt_ = getReadOnlyNode(root_, GRAPHIC_CONTROL_EXTENSION);
                if (elt_ != null) {
                    hundreds_ = Long.parseLong(elt_.getAttribute(DELAY_TIME));
                }
                Element appEntensionsNode_ = getReadOnlyNode(root_, APPLICATION_EXTENSIONS);
                if (appEntensionsNode_ != null) {
                    loop_ = true;
                }
            }

            BufferedImage master_ = null;

            CustList<BufferedImage> images_ = new CustList<BufferedImage>();

            for (int i = CustList.FIRST_INDEX; i < noi_; i++) {
                BufferedImage image_ = _reader.read(i);
                metadata_ = _reader.getImageMetadata(i);
                String metaFormatName_ = metadata_.getNativeMetadataFormatName();
                IIOMetadataNode root_ = (IIOMetadataNode) metadata_.getAsTree(metaFormatName_);
                Element elt_ = getReadOnlyNode(root_, GRAPHIC_CONTROL_EXTENSION);
                if (elt_ != null) {
                    hundreds_ = Long.parseLong(elt_.getAttribute(DELAY_TIME));
                }
                Element appEntensionsNode_ = getReadOnlyNode(root_, APPLICATION_EXTENSIONS);
                if (appEntensionsNode_ != null) {
                    loop_ = true;
                }

                Node tree_ = metadata_.getAsTree(JAVAX_IMAGEIO_GIF_IMAGE_1_0);
                NodeList children_ = tree_.getChildNodes();

                for (int j = CustList.FIRST_INDEX; j < children_.getLength(); j++) {
                    Element nodeItem_ = (Element) children_.item(j);

                    if(!StringList.quickEq(nodeItem_.getNodeName(),IMAGE_DESCRIPTOR)){
                        continue;
                    }
                    if(i==CustList.FIRST_INDEX){
                        int imageWidth_ = Integer.parseInt(nodeItem_.getAttribute(IMAGE_WIDTH));
                        int imageHeight_ = Integer.parseInt(nodeItem_.getAttribute(IMAGE_HEIGHT));
                        master_ = new BufferedImage(imageWidth_, imageHeight_, BufferedImage.TYPE_INT_ARGB);
                    }
                    int imageLeftPosition_ = Integer.parseInt(nodeItem_.getAttribute(IMAGE_LEFT_POSITION));
                    int imageTopPosition_ = Integer.parseInt(nodeItem_.getAttribute(IMAGE_TOP_POSITION));
                    master_.getGraphics().drawImage(image_, imageLeftPosition_, imageTopPosition_, null);
                }
                int w_ = master_.getWidth();
                int h_ = master_.getHeight();
                BufferedImage copy_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
                for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                    for (int k = CustList.FIRST_INDEX; k < h_; k++) {
                        copy_.setRGB(j, k, master_.getRGB(j, k));
                    }
                }
                images_.add(copy_);
            }
            return toBytesGif(images_, hundreds_, loop_);
        } catch (IOException _0) {
            return null;
        }
    }

    public static String toBaseSixtyFourGif(CustList<BufferedImage> _images) {
        String contourChart_ = EMPTY_STRING;
        try {

            ImageWriter writer_ = ImageIO.getImageWritersByFormatName(GIF).next();
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ImageOutputStream cios_ = ImageIO.createImageOutputStream(baos_);
            writer_.setOutput(cios_);
            int noi_ = _images.size();
            ImageWriteParam writeParam_ = writer_.getDefaultWriteParam();
            ImageTypeSpecifier typeSpecifier_ = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
            IIOMetadata metadata_ = writer_.getDefaultImageMetadata(typeSpecifier_, writeParam_);
            for (int i = 0; i < noi_; i++) {
                BufferedImage image_ = _images.get(i);

                if (i == CustList.FIRST_INDEX) {
                    writer_.prepareWriteSequence(metadata_);
                }
                writer_.writeToSequence(new IIOImage(image_, null, metadata_), null);
            }

            cios_.flush();
            baos_.flush();
            byte[] imageInByte_ = baos_.toByteArray();
            StringBuilder sb_ = new StringBuilder();
            sb_.append(BaseSixtyFourUtil.printBaseSixtyFourBinary(imageInByte_));
            contourChart_ = sb_.toString();
        } catch (IOException _0) {
        }
        return contourChart_;
    }

    public static String toBaseSixtyFourGif(GifAnimation _gif) {
        return BaseSixtyFourUtil.printBaseSixtyFourBinary(toBytesGif(_gif));
    }

    public static String toBaseSixtyFourGif(CustList<BufferedImage> _images, long _delayHundreds, boolean _loop) {
        return BaseSixtyFourUtil.printBaseSixtyFourBinary(toBytesGif(_images, _delayHundreds, _loop));
    }

    public static GifAnimation getAnimation(String _string) {
        return getAnimation(BaseSixtyFourUtil.parseBaseSixtyFourBinary(_string));
    }

    public static GifAnimation getAnimation(byte[] _data) {
        try {
            ByteArrayInputStream bis_ = new ByteArrayInputStream(_data);
            ImageReader reader_ = ImageIO.getImageReadersByFormatName(GIF).next();
            ImageInputStream ciis_ = ImageIO.createImageInputStream(bis_);
            reader_.setInput(ciis_, false);
            bis_.close();
            GifAnimation gif_ = new GifAnimation();
            int noi_ = reader_.getNumImages(true);
            IIOMetadata image_ = reader_.getStreamMetadata();
            boolean loop_ = false;
            if (image_ != null) {
                String metaFormatName_ = image_.getNativeMetadataFormatName();
                IIOMetadataNode root_ = (IIOMetadataNode) image_.getAsTree(metaFormatName_);
                Element elt_ = getReadOnlyNode(root_, GRAPHIC_CONTROL_EXTENSION);
                if (elt_ != null) {
                    gif_.setDelayMillis(Long.parseLong(elt_.getAttribute(DELAY_TIME)) * FACTOR_MILLIS_SECONDS);
                }
                Element appEntensionsNode_ = getReadOnlyNode(root_, APPLICATION_EXTENSIONS);
                if (appEntensionsNode_ != null) {
                    loop_ = true;
                }
            }
            for (int i = CustList.FIRST_INDEX; i < noi_; i++) {
                gif_.getImages().add(reader_.read(i));
                image_ = reader_.getImageMetadata(i);
                if (image_ != null) {
                    String metaFormatName_ = image_.getNativeMetadataFormatName();
                    IIOMetadataNode root_ = (IIOMetadataNode) image_.getAsTree(metaFormatName_);
                    Element elt_ = getReadOnlyNode(root_, GRAPHIC_CONTROL_EXTENSION);
                    if (elt_ != null) {
                        gif_.setDelayMillis(Long.parseLong(elt_.getAttribute(DELAY_TIME)) * FACTOR_MILLIS_SECONDS);
                    }
                    Element appEntensionsNode_ = getReadOnlyNode(root_, APPLICATION_EXTENSIONS);
                    if (appEntensionsNode_ != null) {
                        loop_ = true;
                    }
                }
            }
            gif_.setLoop(loop_);
            return gif_;
        } catch (Exception _0) {
            return null;
        }
    }

    public static byte[] toBytesQuick(String _img) {
        return toBytes(toRenderedImageQuick(_img));
    }

    public static byte[] toBytes(BufferedImage _buf) {
        return toBytes(_buf,IMG_EXT);
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

    public static String surroundImage(String _image, String _format) {
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

    public static String surroundImage(String _image) {
        String contourChart_ = EMPTY_STRING;
        StringBuilder sb_ = new StringBuilder();
        sb_.append(DATA_IMAGE);
        sb_.append(IMG_EXT);
        sb_.append(BASE64);
        sb_.append(_image);
        contourChart_ = sb_.toString();
        return contourChart_;
    }
    public static String toBaseSixtyFour(String _txtImg) {
        return toBaseSixtyFour(toRenderedImageQuick(_txtImg),IMG_EXT);
    }

    public static String toBaseSixtyFourQuick(String _txtImg) {
        return toBaseSixtyFour(toRenderedImageQuick(_txtImg));
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
    public static String getSquareColorSixtyFour(String _color, String _sep, int _sideLen) {
        return toBaseSixtyFour(getSquareColor(_color, _sep, _sideLen));
    }

    public static Color getTransparentWhite() {
        return new Color(Color.WHITE.getRed(), Color.WHITE.getBlue(), Color.WHITE.getGreen(), 0);
    }

    public static BufferedImage getSquareColor(String _color, String _sep, int _sideLen) {
        Color c_ = getColor(_color, _sep);
        BufferedImage image_ = new BufferedImage(_sideLen, _sideLen, BufferedImage.TYPE_INT_RGB);
        for (int i = CustList.FIRST_INDEX;i<_sideLen;i++) {
            for (int j = CustList.FIRST_INDEX;j<_sideLen;j++) {
                image_.setRGB(j, i, c_.getRGB());
            }
        }
        return image_;
    }

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

    public static ImageWriter getGifImage(CustList<BufferedImage> _images, long _delayHundreds, boolean _loop) {
        ImageWriter writer_ = ImageIO.getImageWritersByFormatName(GIF).next();
        ImageWriteParam writeParam_ = writer_.getDefaultWriteParam();
        ImageTypeSpecifier typeSpecifier_ = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
        IIOMetadata metadata_ = writer_.getDefaultImageMetadata(typeSpecifier_, writeParam_);
        setMeta(metadata_, _delayHundreds, _loop);

        try {
            int noi_ = _images.size();
            writer_.prepareWriteSequence(metadata_);
            for (int i = 0; i < noi_; i++) {
                BufferedImage image_ = _images.get(i);

                metadata_ = writer_.getDefaultImageMetadata(typeSpecifier_, writeParam_);
                setMeta(metadata_, _delayHundreds, _loop);
                writer_.writeToSequence(new IIOImage(image_, null, metadata_), null);
            }
            writer_.endWriteSequence();
            return writer_;
        } catch (IOException _0) {
            return null;
        }

    }

    private static void setMeta(IIOMetadata _meta, long _delayHundreds, boolean _loop) {
        String metaFormatName_ = _meta.getNativeMetadataFormatName();
        IIOMetadataNode root_ = (IIOMetadataNode) _meta.getAsTree(metaFormatName_);

        if (_loop) {
            IIOMetadataNode appEntensionsNode_ = getNode(root_, APPLICATION_EXTENSIONS);
            IIOMetadataNode child_ = new IIOMetadataNode(APPLICATION_EXTENSION);
            child_.setAttribute(APPLICATION_ID, NETSCAPE);
            child_.setAttribute(AUTHENTICATION_CODE, TWO);
            child_.setUserObject(Numbers.wrapByteArray((byte)1,(byte) 0,(byte) 0));
            appEntensionsNode_.appendChild(child_);
        }

        Element elt_ = getNode(root_, GRAPHIC_CONTROL_EXTENSION);
        elt_.setAttribute(DELAY_TIME, String.valueOf(_delayHundreds));
        elt_.setAttribute(DISPOSAL_METHOD, NONE);
        elt_.setAttribute(USER_INPUT_FLAG, FALSE);
        elt_.setAttribute(TRANSPARENT_COLOR_FLAG, FALSE);
        elt_.setAttribute(TRANSPARENT_COLOR_INDEX, ZERO);
        try {
            _meta.setFromTree(metaFormatName_, root_);
        } catch (IIOInvalidTreeException _0) {
        }
    }
    private static IIOMetadataNode getNode(IIOMetadataNode _rootNode, String _nodeName) {
        int nNodes_ = _rootNode.getLength();
        for (int i = CustList.FIRST_INDEX; i < nNodes_; i++) {
            if (_rootNode.item(i).getNodeName().compareToIgnoreCase(_nodeName) == CustList.EQ_CMP) {
                return (IIOMetadataNode) _rootNode.item(i);
            }
        }
        IIOMetadataNode node_ = new IIOMetadataNode(_nodeName);
        _rootNode.appendChild(node_);
        return node_;
    }
    private static IIOMetadataNode getReadOnlyNode(IIOMetadataNode _rootNode, String _nodeName) {
        int nNodes_ = _rootNode.getLength();
        for (int i = CustList.FIRST_INDEX; i < nNodes_; i++) {
            if (_rootNode.item(i).getNodeName().compareToIgnoreCase(_nodeName) == CustList.EQ_CMP) {
                return (IIOMetadataNode) _rootNode.item(i);
            }
        }
        return null;
    }

    public static ImageReader getGifImage(String _imageString) {
        byte[] imageByte_;
        try {
            imageByte_ = BaseSixtyFourUtil.parseBaseSixtyFourBinary(_imageString);
            ByteArrayInputStream bis_ = new ByteArrayInputStream(imageByte_);
            ImageReader reader_ = ImageIO.getImageReadersByFormatName(GIF).next();
            ImageInputStream ciis_ = ImageIO.createImageInputStream(bis_);
            reader_.setInput(ciis_, false);
            bis_.close();
            return reader_;
        } catch (Exception _0) {
            return null;
        }
    }

    public static BufferedImage decodeToImage(String _imageString) {
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

    public static PairNumber<Integer,Integer> getDimensions(String _imageString) {
        BufferedImage img_ = decodeToImage(_imageString);
        return new PairNumber<Integer,Integer>(img_.getWidth(), img_.getHeight());
    }

    public static String getDataImage() {
        return DATA_IMAGE;
    }

}
