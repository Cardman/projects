package code.gui.images;

import code.gui.EquallableGuiFctUtil;
import code.gui.GuiConstants;
import code.mock.MockImage;
import code.mock.MockImageFactory;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class ConverterGraphicBufferedImageTest extends EquallableGuiFctUtil {
    @Test
    public void croppedPointDimensions1() {
        AbstractImage img_ = ConverterGraphicBufferedImage.decodeToImage(new MockImageFactory(),new int[][]{new int[]{GuiConstants.WHITE, GuiConstants.WHITE, GuiConstants.WHITE}, new int[]{GuiConstants.WHITE,  opaque(), GuiConstants.WHITE}, new int[]{GuiConstants.WHITE, GuiConstants.WHITE, GuiConstants.WHITE}});
        ConverterGraphicBufferedImage.transparentAllWhite(img_);
        IntPointPair pair_ = ConverterGraphicBufferedImage.croppedPointDimensions(img_);
        assertEq(1,pair_.getXcoords().getXcoords());
        assertEq(1,pair_.getXcoords().getYcoords());
        assertEq(1,pair_.getYcoords().getXcoords());
        assertEq(1,pair_.getYcoords().getYcoords());
    }
    @Test
    public void croppedPointDimensions2() {
        IntPointPair pair_ = ConverterGraphicBufferedImage.croppedPointDimensions(new MockImage(new int[][]{new int[]{transparent(), transparent(), transparent(), transparent()}, new int[]{transparent(), opaque(), opaque(), transparent()}, new int[]{transparent(), opaque(), opaque(), transparent()}, new int[]{transparent(), transparent(), transparent(), transparent()}}));
        assertEq(1,pair_.getXcoords().getXcoords());
        assertEq(1,pair_.getXcoords().getYcoords());
        assertEq(2,pair_.getYcoords().getXcoords());
        assertEq(2,pair_.getYcoords().getYcoords());
    }
    @Test
    public void croppedPointDimensions3() {
        IntPointPair pair_ = ConverterGraphicBufferedImage.croppedPointDimensions(new MockImage(new int[][]{new int[]{transparent(), transparent(), transparent()}, new int[]{transparent(), transparent(), transparent()}, new int[]{transparent(), transparent(), transparent()}}));
        assertEq(0,pair_.getXcoords().getXcoords());
        assertEq(0,pair_.getXcoords().getYcoords());
        assertEq(0,pair_.getYcoords().getXcoords());
        assertEq(0,pair_.getYcoords().getYcoords());
    }
    @Test
    public void transparentAllWhite() {
        AbstractImage img_ = ConverterGraphicBufferedImage.decodeToImage(new MockImageFactory(),new int[][]{new int[]{GuiConstants.WHITE, GuiConstants.WHITE, GuiConstants.WHITE}, new int[]{GuiConstants.WHITE, GuiConstants.WHITE, GuiConstants.WHITE}, new int[]{GuiConstants.WHITE, GuiConstants.WHITE, GuiConstants.WHITE}});
        ConverterGraphicBufferedImage.transparentAllWhite(img_);
        IntPointPair pair_ = ConverterGraphicBufferedImage.croppedPointDimensions(img_);
        assertEq(0,pair_.getXcoords().getXcoords());
        assertEq(0,pair_.getXcoords().getYcoords());
        assertEq(0,pair_.getYcoords().getXcoords());
        assertEq(0,pair_.getYcoords().getYcoords());
    }
    @Test
    public void toBytes() {
        byte[] bs_ = ConverterGraphicBufferedImage.toBytes(NumberUtil.wrapIntArray(GuiConstants.newColor(1, 2, 3, 4)));
        assertEq(4, bs_.length);
        assertEq(4, bs_[0]);
        assertEq(1, bs_[1]);
        assertEq(2, bs_[2]);
        assertEq(3, bs_[3]);
    }
    @Test
    public void toArrays() {
        int[][] bs_ = ConverterGraphicBufferedImage.toArrays(new MockImage(new int[][]{new int[]{1}}));
        assertEq(1, bs_.length);
        assertEq(1, bs_[0][0]);
    }
    @Test
    public void centerImage1() {
        AbstractImage img_ = ConverterGraphicBufferedImage.centerImage(new MockImageFactory(),new int[][]{new int[]{opaque()}},1);
        assertEq(1, img_.getHeight());
        assertEq(1, img_.getWidth());
    }
    @Test
    public void centerImage2() {
        AbstractImage img_ = ConverterGraphicBufferedImage.centerImage(new MockImageFactory(),new int[][]{new int[]{opaque()}},1,1);
        assertEq(1, img_.getHeight());
        assertEq(1, img_.getWidth());
    }
    private int opaque() {
        return GuiConstants.newColor(1, false);
    }

    private int transparent() {
        return GuiConstants.newColor(1,true);
    }
}
