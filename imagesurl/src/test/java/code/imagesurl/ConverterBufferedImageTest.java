package code.imagesurl;
import static code.images.EquallableImageUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;

import code.images.ConverterBufferedImage;
import code.images.GifAnimation;
import code.util.CustList;
import code.util.EqList;
import code.util.PairEq;
import code.util.PairNumber;
import code.util.comparators.ComparatorPairNumber;

@SuppressWarnings("static-method")
public class ConverterBufferedImageTest {

    private static final String TRANSPARENT_WHITE = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP4//8/AwAI/AL+eMSysAAAAABJRU5ErkJggg==";

    @BeforeClass
    public static void useMemory() {
        ImageIO.setUseCache(false);
    }

    @Test
    public void toBaseSixtyFour1Test() {
        BufferedImage img_ = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        assertEq("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=", ConverterBufferedImageAdv.toBaseSixtyFour(img_));
    }

    @Test
    public void toBaseSixtyFour2Test() {
        BufferedImage img_ = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        assertEq("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=", ConverterBufferedImageAdv.toBaseSixtyFour(img_, "png"));
    }

    @Test
    public void toBaseSixtyFour3Test() {
        BufferedImage img_ = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        assertEq(TRANSPARENT_WHITE, ConverterBufferedImageAdv.toBaseSixtyFour(img_));
    }

    @Test
    public void decodeToImage1Test() {
        BufferedImage img_ = ConverterBufferedImageAdv.decodeToImage("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=");
        assertEq(1, img_.getWidth());
        assertEq(1, img_.getHeight());
        assertEq(Color.WHITE.getRGB(), img_.getRGB(0, 0));
    }

    @Test
    public void decodeToImage2Test() {
        BufferedImage img_ = ConverterBufferedImageAdv.decodeToImage("");
        assertNull( img_);
    }

    @Test
    public void toBaseSixtyFourGif1Test() {
        BufferedImage img_ = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        CustList<BufferedImage> images_ = new CustList<BufferedImage>(img_);
        assertEq("R0lGODlhAQABAPAAAP///////yH5BAABAAAAIf8LTkVUU0NBUEUyLjADAQAAACwAAAAAAQABAEAIBAABBAQAOw==",ConverterBufferedImageAdv.toBaseSixtyFourGif(images_, 1, true));
    }

    @Test
    public void toBaseSixtyFourGif2Test() {
        BufferedImage img_ = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        CustList<BufferedImage> images_ = new CustList<BufferedImage>(img_);
        assertEq("R0lGODlhAQABAPAAAP///////yH5BAABAAAALAAAAAABAAEAQAgEAAEEBAA7",ConverterBufferedImageAdv.toBaseSixtyFourGif(images_, 1, false));
    }

    @Test
    public void getAnimation1Test() {
        GifAnimation gif_ = ConverterBufferedImageAdv.getAnimation("R0lGODlhAQABAPAAAP///////yH5BAABAAAAIf8LTkVUU0NBUEUyLjADAQAAACwAAAAAAQABAEAIBAABBAQAOw==");
        assertEq(1, gif_.getImages().size());
        assertEq(1, gif_.getImages().get(0).getHeight());
        assertEq(1, gif_.getImages().get(0).getWidth());
        assertEq(Color.WHITE.getRGB(), gif_.getImages().get(0).getRGB(0, 0));
        assertEq(10, gif_.getDelayMillis());
        assertTrue(gif_.isLoop());
    }

    @Test
    public void getAnimation2Test() {
        GifAnimation gif_ = ConverterBufferedImageAdv.getAnimation("R0lGODlhAQABAPAAAP///////yH5BAABAAAALAAAAAABAAEAQAgEAAEEBAA7");
        assertEq(1, gif_.getImages().size());
        assertEq(1, gif_.getImages().get(0).getHeight());
        assertEq(1, gif_.getImages().get(0).getWidth());
        assertEq(Color.WHITE.getRGB(), gif_.getImages().get(0).getRGB(0, 0));
        assertEq(10, gif_.getDelayMillis());
        assertTrue(!gif_.isLoop());
    }

    @Test
    public void getDimensions1Test() {
        PairNumber<Integer,Integer> dims_ = ConverterBufferedImageAdv.getDimensions("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=");
        assertEq(1, dims_.getFirst().intValue());
        assertEq(1, dims_.getSecond().intValue());
    }

    @Test
    public void stackImages1Test() {
        assertEq("IMG", ConverterBufferedImageAdv.stackImages("IMG", ""));
    }

    @Test
    public void stackImages2Test() {
        String back_ = TRANSPARENT_WHITE;
        String front_ = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=";
        assertEq(front_, ConverterBufferedImageAdv.stackImages(back_, front_));
    }

    @Test
    public void stackImages3Test() {
        String front_ = TRANSPARENT_WHITE;
        String back_ = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=";
        assertEq(back_, ConverterBufferedImageAdv.stackImages(back_, front_));
    }

    @Test
    public void centerImage1Test() {
        String front_ = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=";
        BufferedImage img_ = ConverterBufferedImageAdv.centerImage(front_, 3);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void centerImage2Test() {
        String front_ = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=";
        BufferedImage img_ = ConverterBufferedImageAdv.centerImage(front_, 3, 3);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void centerImage3Test() {
        String front_ = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVR42mP4DwQACfsD/Wj6HMwAAAAASUVORK5CYII=";
        BufferedImage img_ = ConverterBufferedImageAdv.centerImage(front_, 3, 4);
        assertEq(3, img_.getWidth());
        assertEq(4, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(0, 3));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(1, 3));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 2));
        assertEq(ConverterBufferedImage.getTransparentBlack().getRGB(), img_.getRGB(2, 3));
    }

    @Test
    public void transparentAllWhite1Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        ConverterBufferedImage.transparentAllWhite(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void trim1Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        img_ = ConverterBufferedImage.trim(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void trim2Test() {
        ConverterBufferedImage.setHandsFeet(false);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        img_ = ConverterBufferedImage.trim(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void trim3Test() {
        ConverterBufferedImage.setHandsFeet(false);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        img_ = ConverterBufferedImage.trim(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(0, 1));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 2));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 2));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 1));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void trim4Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        img_ = ConverterBufferedImage.trim(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(0, 1));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 2));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 2));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(2, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(2, 1));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void trim5Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        img_ = ConverterBufferedImage.trim(img_);
        assertEq(3, img_.getWidth());
        assertEq(3, img_.getHeight());
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 0));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(0, 1));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(0, 2));
        assertEq(Color.WHITE.getRGB(), img_.getRGB(1, 0));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(1, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(1, 2));
        assertEq(Color.BLACK.getRGB(), img_.getRGB(2, 0));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 1));
        assertEq(ConverterBufferedImage.getTransparentWhite().getRGB(), img_.getRGB(2, 2));
    }

    @Test
    public void whitePixels1Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        list_.sortElts(new ComparatorPairNumber<Integer,Integer>());
        assertEq(8, list_.size());
        assertEq(0, list_.get(0).getFirst().intValue());
        assertEq(0, list_.get(0).getSecond().intValue());
        assertEq(0, list_.get(1).getFirst().intValue());
        assertEq(1, list_.get(1).getSecond().intValue());
        assertEq(0, list_.get(2).getFirst().intValue());
        assertEq(2, list_.get(2).getSecond().intValue());
        assertEq(1, list_.get(3).getFirst().intValue());
        assertEq(0, list_.get(3).getSecond().intValue());
        assertEq(1, list_.get(4).getFirst().intValue());
        assertEq(2, list_.get(4).getSecond().intValue());
        assertEq(2, list_.get(5).getFirst().intValue());
        assertEq(0, list_.get(5).getSecond().intValue());
        assertEq(2, list_.get(6).getFirst().intValue());
        assertEq(1, list_.get(6).getSecond().intValue());
        assertEq(2, list_.get(7).getFirst().intValue());
        assertEq(2, list_.get(7).getSecond().intValue());
    }

    @Test
    public void whitePixels2Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.BLACK.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.BLACK.getRGB());
        img_.setRGB(1, 1, Color.WHITE.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.BLACK.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        list_.sortElts(new ComparatorPairNumber<Integer,Integer>());
        assertEq(4, list_.size());
        assertEq(0, list_.get(0).getFirst().intValue());
        assertEq(0, list_.get(0).getSecond().intValue());
        assertEq(0, list_.get(1).getFirst().intValue());
        assertEq(2, list_.get(1).getSecond().intValue());
        assertEq(2, list_.get(2).getFirst().intValue());
        assertEq(0, list_.get(2).getSecond().intValue());
        assertEq(2, list_.get(3).getFirst().intValue());
        assertEq(2, list_.get(3).getSecond().intValue());
    }

    @Test
    public void whitePixels3Test() {
        ConverterBufferedImage.setHandsFeet(true);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        assertEq(0, list_.size());
    }

    @Test
    public void whitePixels4Test() {
        ConverterBufferedImage.setHandsFeet(false);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        list_.sortElts(new ComparatorPairNumber<Integer,Integer>());
        assertEq(8, list_.size());
        assertEq(0, list_.get(0).getFirst().intValue());
        assertEq(0, list_.get(0).getSecond().intValue());
        assertEq(0, list_.get(1).getFirst().intValue());
        assertEq(1, list_.get(1).getSecond().intValue());
        assertEq(0, list_.get(2).getFirst().intValue());
        assertEq(2, list_.get(2).getSecond().intValue());
        assertEq(1, list_.get(3).getFirst().intValue());
        assertEq(0, list_.get(3).getSecond().intValue());
        assertEq(1, list_.get(4).getFirst().intValue());
        assertEq(2, list_.get(4).getSecond().intValue());
        assertEq(2, list_.get(5).getFirst().intValue());
        assertEq(0, list_.get(5).getSecond().intValue());
        assertEq(2, list_.get(6).getFirst().intValue());
        assertEq(1, list_.get(6).getSecond().intValue());
        assertEq(2, list_.get(7).getFirst().intValue());
        assertEq(2, list_.get(7).getSecond().intValue());
    }

    @Test
    public void whitePixels5Test() {
        ConverterBufferedImage.setHandsFeet(false);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.BLACK.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.BLACK.getRGB());
        img_.setRGB(1, 1, Color.WHITE.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.BLACK.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        list_.sortElts(new ComparatorPairNumber<Integer,Integer>());
        assertEq(4, list_.size());
        assertEq(0, list_.get(0).getFirst().intValue());
        assertEq(0, list_.get(0).getSecond().intValue());
        assertEq(0, list_.get(1).getFirst().intValue());
        assertEq(2, list_.get(1).getSecond().intValue());
        assertEq(2, list_.get(2).getFirst().intValue());
        assertEq(0, list_.get(2).getSecond().intValue());
        assertEq(2, list_.get(3).getFirst().intValue());
        assertEq(2, list_.get(3).getSecond().intValue());
    }

    @Test
    public void whitePixels6Test() {
        ConverterBufferedImage.setHandsFeet(false);
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.whitePixels(img_);
        list_.sortElts(new ComparatorPairNumber<Integer,Integer>());
        assertEq(4, list_.size());
        assertEq(0, list_.get(0).getFirst().intValue());
        assertEq(1, list_.get(0).getSecond().intValue());
        assertEq(1, list_.get(1).getFirst().intValue());
        assertEq(0, list_.get(1).getSecond().intValue());
        assertEq(1, list_.get(2).getFirst().intValue());
        assertEq(2, list_.get(2).getSecond().intValue());
        assertEq(2, list_.get(3).getFirst().intValue());
        assertEq(1, list_.get(3).getSecond().intValue());
    }

    @Test
    public void croppedPointDimensions1Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> ptDims_;
        ptDims_ = ConverterBufferedImage.croppedPointDimensions(img_);
        assertEq(1, ptDims_.getFirst().getFirst().intValue());
        assertEq(1, ptDims_.getFirst().getSecond().intValue());
        assertEq(1, ptDims_.getSecond().getFirst().intValue());
        assertEq(1, ptDims_.getSecond().getSecond().intValue());
    }

    @Test
    public void croppedPointDimensions2Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.BLACK.getRGB());
        img_.setRGB(0, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 0, Color.BLACK.getRGB());
        img_.setRGB(2, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> ptDims_;
        ptDims_ = ConverterBufferedImage.croppedPointDimensions(img_);
        assertEq(0, ptDims_.getFirst().getFirst().intValue());
        assertEq(0, ptDims_.getFirst().getSecond().intValue());
        assertEq(3, ptDims_.getSecond().getFirst().intValue());
        assertEq(3, ptDims_.getSecond().getSecond().intValue());
    }

    @Test
    public void croppedPointDimensions4Test() {
        BufferedImage img_ = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 1, Color.BLACK.getRGB());
        img_.setRGB(2, 2, Color.BLACK.getRGB());
        img_.setRGB(2, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> ptDims_;
        ptDims_ = ConverterBufferedImage.croppedPointDimensions(img_);
        assertEq(1, ptDims_.getFirst().getFirst().intValue());
        assertEq(1, ptDims_.getFirst().getSecond().intValue());
        assertEq(2, ptDims_.getSecond().getFirst().intValue());
        assertEq(2, ptDims_.getSecond().getSecond().intValue());
    }

    @Test
    public void croppedPointDimensions5Test() {
        BufferedImage img_ = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(1, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> ptDims_;
        ptDims_ = ConverterBufferedImage.croppedPointDimensions(img_);
        assertEq(1, ptDims_.getFirst().getFirst().intValue());
        assertEq(1, ptDims_.getFirst().getSecond().intValue());
        assertEq(1, ptDims_.getSecond().getFirst().intValue());
        assertEq(2, ptDims_.getSecond().getSecond().intValue());
    }

    @Test
    public void croppedPointDimensions6Test() {
        BufferedImage img_ = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(0, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(1, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(2, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 0, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 1, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 2, ConverterBufferedImage.getTransparentWhite().getRGB());
        img_.setRGB(3, 3, ConverterBufferedImage.getTransparentWhite().getRGB());
        PairEq<PairNumber<Integer,Integer>,PairNumber<Integer,Integer>> ptDims_;
        ptDims_ = ConverterBufferedImage.croppedPointDimensions(img_);
        assertEq(4, ptDims_.getFirst().getFirst().intValue());
        assertEq(4, ptDims_.getFirst().getSecond().intValue());
        assertEq(-4, ptDims_.getSecond().getFirst().intValue());
        assertEq(-4, ptDims_.getSecond().getSecond().intValue());
    }

    @Test
    public void containedWhiteInside1Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        assertEq(0, ConverterBufferedImage.containedWhiteInside(img_).size());
    }

    @Test
    public void containedWhiteInside2Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.BLACK.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.BLACK.getRGB());
        img_.setRGB(1, 1, Color.WHITE.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.BLACK.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        EqList<PairNumber<Integer,Integer>> list_ = ConverterBufferedImage.containedWhiteInside(img_);
        assertEq(1, list_.size());
        assertEq(1, list_.get(0).getFirst().intValue());
        assertEq(1, list_.get(0).getSecond().intValue());
    }

    @Test
    public void containsInsideWhites1Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.WHITE.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.WHITE.getRGB());
        img_.setRGB(1, 1, Color.BLACK.getRGB());
        img_.setRGB(1, 2, Color.WHITE.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.WHITE.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        assertTrue(!ConverterBufferedImage.containsInsideWhites(img_));
    }

    @Test
    public void containsInsideWhites2Test() {
        BufferedImage img_ = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        img_.setRGB(0, 0, Color.WHITE.getRGB());
        img_.setRGB(0, 1, Color.BLACK.getRGB());
        img_.setRGB(0, 2, Color.WHITE.getRGB());
        img_.setRGB(1, 0, Color.BLACK.getRGB());
        img_.setRGB(1, 1, Color.WHITE.getRGB());
        img_.setRGB(1, 2, Color.BLACK.getRGB());
        img_.setRGB(2, 0, Color.WHITE.getRGB());
        img_.setRGB(2, 1, Color.BLACK.getRGB());
        img_.setRGB(2, 2, Color.WHITE.getRGB());
        assertTrue(ConverterBufferedImage.containsInsideWhites(img_));
    }

    @Test
    public void getColor1Test() {
        Color c_ = ConverterBufferedImage.getColor("27;14;52", ";");
        assertEq(27, c_.getRed());
        assertEq(14, c_.getGreen());
        assertEq(52, c_.getBlue());
    }

    @Test(expected=NumberFormatException.class)
    public void getColor1FailTest() {
        ConverterBufferedImage.getColor("27;14;52", ",");
    }

    @Test(expected=NumberFormatException.class)
    public void getColor2FailTest() {
        ConverterBufferedImage.getColor("27;a;52", ";");
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getColor3FailTest() {
        ConverterBufferedImage.getColor("27;52", ";");
    }

    @Test
    public void validateColor1Test() {
        ConverterBufferedImage.validateColor("27;14;52", ";");
    }

    @Test(expected=NumberFormatException.class)
    public void validateColor1FailTest() {
        ConverterBufferedImage.validateColor("27;14;52", ",");
    }

    @Test(expected=NumberFormatException.class)
    public void validateColor2FailTest() {
        ConverterBufferedImage.validateColor("27;a;52", ";");
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void validateColor3FailTest() {
        ConverterBufferedImage.validateColor("27;52", ";");
    }
}
