package code.images;

import code.util.CustList;
import code.util.EqList;
import org.junit.Test;

import static code.images.EquallableImageUtil.assertEq;

public class ConverterBufferedImageTest {
    @Test
    public void stackImages1Test() {
        int[][] fr_ = new int[2][2];
        fr_[0][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[1][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        int[][] bk_ = new int[2][2];
        bk_[0][0] = 3;
        bk_[0][1] = 5;
        bk_[1][0] = ConverterBufferedImage.WHITE_RGB_INT;
        bk_[1][1] = ConverterBufferedImage.WHITE_RGB_INT;
        int[][] out_ = ConverterBufferedImage.stackImages(bk_, fr_);
        assertEq(2,out_.length);
        assertEq(2,out_[0].length);
        assertEq(3,out_[0][0]);
        assertEq(7,out_[0][1]);
        assertEq(2,out_[1].length);
        assertEq(ConverterBufferedImage.WHITE_RGB_INT,out_[1][0]);
        assertEq(3,out_[1][1]);
    }
    @Test
    public void stackImages2Test() {
        int[][] fr_ = new int[0][0];
        int[][] bk_ = new int[0][0];
        int[][] out_ = ConverterBufferedImage.stackImages(bk_, fr_);
        assertEq(0,out_.length);
    }
    @Test
    public void getIntColor1Test() {
        int intColor_ = ConverterBufferedImage.getIntColor("12,168,14", ",");
        assertEq(829454,intColor_);
    }
    @Test
    public void getIntColor2Test() {
        int intColor_ = ConverterBufferedImage.getIntColor("12,168", ",");
        assertEq(-1,intColor_);
    }
    @Test
    public void getSquareColorSixtyFour1Test() {
        String intColor_ = ConverterBufferedImage.getSquareColorSixtyFour("12,168,14", ",",1);
        assertEq("AAABDKgO",intColor_);
    }
    @Test
    public void whitePixels1Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][2] = 14;
        fr_[1][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        fr_[1][2] = 6;
        fr_[2][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][2] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.whitePixels(true, fr_);
        assertEq(3,intPoints_.size());
    }
    @Test
    public void whitePixels2Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][0] = 14;
        fr_[1][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        fr_[1][0] = 6;
        fr_[2][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][0] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.whitePixels(true, fr_);
        assertEq(3,intPoints_.size());
    }
    @Test
    public void whitePixels3Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][2] = 14;
        fr_[1][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        fr_[1][2] = 6;
        fr_[2][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][2] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.whitePixels(false, fr_);
        assertEq(3,intPoints_.size());
    }
    @Test
    public void whitePixels4Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][0] = 14;
        fr_[1][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        fr_[1][0] = 6;
        fr_[2][2] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][0] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.whitePixels(false, fr_);
        assertEq(3,intPoints_.size());
    }
    @Test
    public void containedWhiteInside1Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][2] = 14;
        fr_[1][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][1] = 3;
        fr_[1][2] = 6;
        fr_[2][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][2] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.containedWhiteInside(true, fr_);
        assertEq(0,intPoints_.size());
    }
    @Test
    public void containedWhiteInside2Test() {
        int[][] fr_ = new int[3][3];
        fr_[0][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[0][1] = 7;
        fr_[0][2] = 14;
        fr_[1][0] = 3;
        fr_[1][1] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[1][2] = 6;
        fr_[2][0] = ConverterBufferedImage.WHITE_RGB_INT;
        fr_[2][1] = 3;
        fr_[2][2] = 6;
        EqList<IntPoint> intPoints_ = ConverterBufferedImage.containedWhiteInside(true, fr_);
        assertEq(1,intPoints_.size());
    }
    @Test
    public void getPolygons() {
        EqList<IntPoint> l_ = new EqList<IntPoint>();
        IntPoint pt_ = new IntPoint(0,0);
        l_.add(pt_);
        IntPoint pt2_ = new IntPoint(pt_);
        pt2_.setYcoords(1);
        l_.add(pt2_);
        IntPoint pt3_ = new IntPoint(5,3);
        l_.add(pt3_);
        IntPoint pt4_ = new IntPoint(5,4);
        l_.add(pt4_);
        IntPoint pt5_ = new IntPoint(5,5);
        l_.add(pt5_);
        IntPoint pt6_ = new IntPoint(4,5);
        l_.add(pt6_);
        IntPoint pt7_ = new IntPoint(4,4);
        l_.add(pt7_);
        CustList<EqList<IntPoint>> polygons_ = ConverterBufferedImage.getPolygons(l_);
        assertEq(7, polygons_.size());
    }
}
