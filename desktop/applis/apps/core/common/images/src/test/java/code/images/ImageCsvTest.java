package code.images;

import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ImageCsvTest extends EquallableImageUtil {

    @Test
    public void newImage1Test() {
        ImageCsv img_ = new ImageCsv("0");
        assertEq(0, img_.getWidth());
        assertEq(0, img_.getPixels().size());
    }

    @Test
    public void newImage2Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR),"2"));
        assertEq(1, img_.getWidth());
        assertEq(1, img_.getPixels().size());
        assertEq(2, img_.getPixels().first());
    }

    @Test
    public void newImage3Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR),"2",Character.toString(ImageCsv.SEPARATOR_CHAR),"3"));
        assertEq(1, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(2, img_.getPixels().first());
        assertEq(3, img_.getPixels().last());
    }

    @Test
    public void newImage4Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"3",Character.toString(ImageCsv.SEPARATOR_CHAR),"4"));
        assertEq(2, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(3, img_.getPixels().first());
        assertEq(4, img_.getPixels().last());
    }


    @Test
    public void toBaseSixtyFour() {
        assertEq("AAACAAADAAAE",ImageCsv.toBaseSixtyFour(StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"3",Character.toString(ImageCsv.SEPARATOR_CHAR),"4")));
    }

    @Test
    public void isValid1Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("0",Character.toString(ImageCsv.SEPARATOR_CHAR),"1"));
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid2Test() {
        ImageCsv img_ = new ImageCsv("0");
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid3Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR),"1"));
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid4Test() {
        ImageCsv img_ = new ImageCsv("1");
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid5Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR),"4",Character.toString(ImageCsv.SEPARATOR_CHAR),"5"));
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid6Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertTrue(img_.isValid());
    }

    @Test
    public void toString1Test() {
        ImageCsv img_ = new ImageCsv("0");
        assertEq("0", img_.display());
    }

    @Test
    public void toString2Test() {
        ImageCsv img_ = new ImageCsv(StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR),"2"));
        assertEq(StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR),"2"), img_.display());
    }

    @Test
    public void toString3Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertEq(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)), img_.display());
    }

    @Test
    public void getHeight1Test() {
        ImageCsv img_ = new ImageCsv("0");
        assertEq(0, img_.getHeight());
    }

    @Test
    public void getHeight2Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertEq(2, img_.getHeight());
    }

    @Test
    public void getDimensions1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        IntPoint dims_ = ImageCsv.getDimensions(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)), 1);
        assertEq(3, dims_.getXcoords());
        assertEq(2, dims_.getYcoords());
    }

    @Test
    public void getPixel1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertEq(1, img_.getPixel(0, 0));
        assertEq(2, img_.getPixel(1, 0));
        assertEq(6, img_.getPixel(2, 0));
        assertEq(9, img_.getPixel(0, 1));
        assertEq(-1, img_.getPixel(1, 1));
        assertEq(12, img_.getPixel(2, 1));
    }

    @Test
    public void clip1Test() {
        ImageCsv img_ = new ImageCsv("0");
        assertEq(img_.display(), img_.clip(0, 0, 1, 1).display());
    }

    @Test
    public void clip2Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertEq(new ImageCsv("0").display(), img_.clip(2, 2, 1, 1).display());
    }

    @Test
    public void clip3Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        assertEq(new ImageCsv("0").display(), img_.clip(3, 1, 1, 1).display());
    }

    @Test
    public void clip4Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        ImageCsv res_ = new ImageCsv(StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"-1",Character.toString(ImageCsv.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 1, 2, 2).display());
    }

    @Test
    public void clip5Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        ImageCsv res_ = new ImageCsv(StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"2",Character.toString(ImageCsv.SEPARATOR_CHAR),"6",Character.toString(ImageCsv.SEPARATOR_CHAR),"-1",Character.toString(ImageCsv.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 0, 2, 2).display());
    }

    @Test
    public void clip6Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        ImageCsv img_ = new ImageCsv(StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR)));
        ImageCsv res_ = new ImageCsv(StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"2",Character.toString(ImageCsv.SEPARATOR_CHAR),"6",Character.toString(ImageCsv.SEPARATOR_CHAR),"-1",Character.toString(ImageCsv.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 0, 3, 3).display());
    }

    @Test
    public void clip7Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        String res_ = StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR),"2",Character.toString(ImageCsv.SEPARATOR_CHAR),"6",Character.toString(ImageCsv.SEPARATOR_CHAR),"-1",Character.toString(ImageCsv.SEPARATOR_CHAR),"12");
        assertEq(res_, ImageCsv.clip(img_, 1, 0, 3, 3));
    }

    @Test
    public void clipSixtyFour1Test() {
        int[][] img_ = new int[2][3];
        img_[0][0] = 2;
        img_[0][1] = 5;
        img_[0][2] = 8;
        img_[1][0] = 7;
        img_[1][1] = 1;
        img_[1][2] = 3;
        int[][] out_ = BaseSixtyFourUtil.clipSixtyFour(img_, 0, 0, 3, 2);
        assertEq(2, out_.length);
        assertEq(3, out_[0].length);
        assertEq(2, out_[0][0]);
        assertEq(5, out_[0][1]);
        assertEq(8, out_[0][2]);
        assertEq(3, out_[1].length);
        assertEq(7, out_[1][0]);
        assertEq(1, out_[1][1]);
        assertEq(3, out_[1][2]);
    }

    @Test
    public void clipSixtyFour2Test() {
        int[][] img_ = new int[2][3];
        img_[0][0] = 2;
        img_[0][1] = 5;
        img_[0][2] = 8;
        img_[1][0] = 7;
        img_[1][1] = 1;
        img_[1][2] = 3;
        int[][] out_ = BaseSixtyFourUtil.clipSixtyFour(img_, 1, 1, 2, 1);
        assertEq(1, out_.length);
        assertEq(2, out_[0].length);
        assertEq(1, out_[0][0]);
        assertEq(3, out_[0][1]);
    }

    @Test
    public void clipSixtyFour3Test() {
        int[][] img_ = new int[2][3];
        img_[0][0] = 2;
        img_[0][1] = 5;
        img_[0][2] = 8;
        img_[1][0] = 7;
        img_[1][1] = 1;
        img_[1][2] = 3;
        int[][] out_ = BaseSixtyFourUtil.clipSixtyFour(img_, 1, 1, 3, 2);
        assertEq(1, out_.length);
        assertEq(2, out_[0].length);
        assertEq(1, out_[0][0]);
        assertEq(3, out_[0][1]);
    }

    @Test
    public void clipSixtyFour4Test() {
        int[][] img_ = new int[2][3];
        img_[0][0] = 2;
        img_[0][1] = 5;
        img_[0][2] = 8;
        img_[1][0] = 7;
        img_[1][1] = 1;
        img_[1][2] = 3;
        int[][] out_ = BaseSixtyFourUtil.clipSixtyFour(img_, 0, 1, 2, 1);
        assertEq(1, out_.length);
        assertEq(2, out_[0].length);
        assertEq(7, out_[0][0]);
        assertEq(1, out_[0][1]);
    }

    @Test
    public void clipSixtyFour5Test() {
        int[][] img_ = new int[2][3];
        img_[0][0] = 2;
        img_[0][1] = 5;
        img_[0][2] = 8;
        img_[1][0] = 7;
        img_[1][1] = 1;
        img_[1][2] = 3;
        int[][] out_ = BaseSixtyFourUtil.clipSixtyFour(img_, 1, 0, 2, 1);
        assertEq(1, out_.length);
        assertEq(2, out_[0].length);
        assertEq(5, out_[0][0]);
        assertEq(8, out_[0][1]);
    }
    @Test
    public void isValidNotEmpty1Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty2Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("0", 5));
    }

    @Test
    public void isValidNotEmpty3Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("", 5));
    }

    @Test
    public void isValidNotEmpty4Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("", 5));
    }

    @Test
    public void isValidNotEmpty5Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty6Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(ImageCsv.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty11Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty12Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("0", 5, 3));
    }

    @Test
    public void isValidNotEmpty13Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("", 5, 3));
    }

    @Test
    public void isValidNotEmpty14Test() {
        assertTrue(!ImageCsv.isValidNotEmpty("", 5, 3));
    }

    @Test
    public void isValidNotEmpty15Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty16Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty17Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty18Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty19Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty20Test() {
        StringList pixels_ = new StringList("4","-5","-6","7","-8","9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(ImageCsv.isValidNotEmpty(img_, 3, 2));
    }

    @Test
    public void isValidNotEmpty21Test() {
        StringList pixels_ = new StringList("1","2","3","a","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty22Test() {
        StringList pixels_ = new StringList("1","2","3","-","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty23Test() {
        StringList pixels_ = new StringList("1","2","3","'-'","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty24Test() {
        StringList pixels_ = new StringList("3","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("a",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmpty(img_, 3));
    }
    @Test
    public void isValidNotEmptyLower1Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower2Test() {
        assertTrue(!ImageCsv.isValidNotEmptyLower("0", 5));
    }

    @Test
    public void isValidNotEmptyLower3Test() {
        assertTrue(!ImageCsv.isValidNotEmptyLower("", 5));
    }

    @Test
    public void isValidNotEmptyLower4Test() {
        assertTrue(!ImageCsv.isValidNotEmptyLower("", 5));
    }

    @Test
    public void isValidNotEmptyLower5Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower6Test() {
        StringList pixels_ = new StringList("1","");
        //StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(ImageCsv.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower11Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("4",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower12Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("2",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower13Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower14Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("6",Character.toString(ImageCsv.SEPARATOR_CHAR), StringUtil.join(pixels_, ImageCsv.SEPARATOR_CHAR));
        assertTrue(!ImageCsv.isValidNotEmptyLower(img_, 3));
    }
}
