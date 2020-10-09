package code.images;
import static code.images.EquallableImageUtil.assertEq;
import static org.junit.Assert.assertTrue;

import code.util.core.StringUtil;
import org.junit.Test;

import code.util.StringList;


public class ImageTest {

    @Test
    public void newImage1Test() {
        Image img_ = new Image("0");
        assertEq(0, img_.getWidth());
        assertEq(0, img_.getPixels().size());
    }

    @Test
    public void newImage2Test() {
        Image img_ = new Image(StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR),"2"));
        assertEq(1, img_.getWidth());
        assertEq(1, img_.getPixels().size());
        assertEq(2, img_.getPixels().first());
    }

    @Test
    public void newImage3Test() {
        Image img_ = new Image(StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR),"2",String.valueOf(Image.SEPARATOR_CHAR),"3"));
        assertEq(1, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(2, img_.getPixels().first());
        assertEq(3, img_.getPixels().last());
    }

    @Test
    public void newImage4Test() {
        Image img_ = new Image(StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR),"3",String.valueOf(Image.SEPARATOR_CHAR),"4"));
        assertEq(2, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(3, img_.getPixels().first());
        assertEq(4, img_.getPixels().last());
    }

    @Test
    public void isValid1Test() {
        Image img_ = new Image(StringUtil.concat("0",String.valueOf(Image.SEPARATOR_CHAR),"1"));
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid2Test() {
        Image img_ = new Image("0");
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid3Test() {
        Image img_ = new Image(StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR),"1"));
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid4Test() {
        Image img_ = new Image("1");
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid5Test() {
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR),"4",String.valueOf(Image.SEPARATOR_CHAR),"5"));
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid6Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertTrue(img_.isValid());
    }

    @Test
    public void toString1Test() {
        Image img_ = new Image("0");
        assertEq("0", img_.display());
    }

    @Test
    public void toString2Test() {
        Image img_ = new Image(StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR),"2"));
        assertEq(StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR),"2"), img_.display());
    }

    @Test
    public void toString3Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertEq(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)), img_.display());
    }

    @Test
    public void getHeight1Test() {
        Image img_ = new Image("0");
        assertEq(0, img_.getHeight());
    }

    @Test
    public void getHeight2Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertEq(2, img_.getHeight());
    }

    @Test
    public void getDimensions1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        IntPoint dims_ = Image.getDimensions(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)), 1);
        assertEq(3, dims_.getXcoords());
        assertEq(2, dims_.getYcoords());
    }

    @Test
    public void getPixel1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertEq(1, img_.getPixel(0, 0));
        assertEq(2, img_.getPixel(1, 0));
        assertEq(6, img_.getPixel(2, 0));
        assertEq(9, img_.getPixel(0, 1));
        assertEq(-1, img_.getPixel(1, 1));
        assertEq(12, img_.getPixel(2, 1));
    }

    @Test
    public void clip1Test() {
        Image img_ = new Image("0");
        assertEq(img_.display(), img_.clip(0, 0, 1, 1).display());
    }

    @Test
    public void clip2Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertEq(new Image("0").display(), img_.clip(2, 2, 1, 1).display());
    }

    @Test
    public void clip3Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        assertEq(new Image("0").display(), img_.clip(3, 1, 1, 1).display());
    }

    @Test
    public void clip4Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        Image res_ = new Image(StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR),"-1",String.valueOf(Image.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 1, 2, 2).display());
    }

    @Test
    public void clip5Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        Image res_ = new Image(StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR),"2",String.valueOf(Image.SEPARATOR_CHAR),"6",String.valueOf(Image.SEPARATOR_CHAR),"-1",String.valueOf(Image.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 0, 2, 2).display());
    }

    @Test
    public void clip6Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image(StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR)));
        Image res_ = new Image(StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR),"2",String.valueOf(Image.SEPARATOR_CHAR),"6",String.valueOf(Image.SEPARATOR_CHAR),"-1",String.valueOf(Image.SEPARATOR_CHAR),"12"));
        assertEq(res_.display(), img_.clip(1, 0, 3, 3).display());
    }

    @Test
    public void clip7Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        String img_ = StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        String res_ = StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR),"2",String.valueOf(Image.SEPARATOR_CHAR),"6",String.valueOf(Image.SEPARATOR_CHAR),"-1",String.valueOf(Image.SEPARATOR_CHAR),"12");
        assertEq(res_, Image.clip(img_, 1, 0, 3, 3));
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
        int[][] out_ = Image.clipSixtyFour(img_, 0, 0, 3, 2);
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
        int[][] out_ = Image.clipSixtyFour(img_, 1, 1, 2, 1);
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
        int[][] out_ = Image.clipSixtyFour(img_, 1, 1, 3, 2);
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
        int[][] out_ = Image.clipSixtyFour(img_, 0, 1, 2, 1);
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
        int[][] out_ = Image.clipSixtyFour(img_, 1, 0, 2, 1);
        assertEq(1, out_.length);
        assertEq(2, out_[0].length);
        assertEq(5, out_[0][0]);
        assertEq(8, out_[0][1]);
    }
    @Test
    public void isValidNotEmpty1Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty2Test() {
        assertTrue(!Image.isValidNotEmpty("0", 5));
    }

    @Test
    public void isValidNotEmpty3Test() {
        assertTrue(!Image.isValidNotEmpty("", 5));
    }

    @Test
    public void isValidNotEmpty4Test() {
        assertTrue(!Image.isValidNotEmpty("", 5));
    }

    @Test
    public void isValidNotEmpty5Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty6Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(Image.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty11Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty12Test() {
        assertTrue(!Image.isValidNotEmpty("0", 5, 3));
    }

    @Test
    public void isValidNotEmpty13Test() {
        assertTrue(!Image.isValidNotEmpty("", 5, 3));
    }

    @Test
    public void isValidNotEmpty14Test() {
        assertTrue(!Image.isValidNotEmpty("", 5, 3));
    }

    @Test
    public void isValidNotEmpty15Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty16Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty17Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty18Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty19Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty20Test() {
        StringList pixels_ = new StringList("4","-5","-6","7","-8","9");
        String img_ = StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(Image.isValidNotEmpty(img_, 3, 2));
    }

    @Test
    public void isValidNotEmptyLower1Test() {
        StringList pixels_ = new StringList();
        String img_ = StringUtil.concat("0",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower2Test() {
        assertTrue(!Image.isValidNotEmptyLower("0", 5));
    }

    @Test
    public void isValidNotEmptyLower3Test() {
        assertTrue(!Image.isValidNotEmptyLower("", 5));
    }

    @Test
    public void isValidNotEmptyLower4Test() {
        assertTrue(!Image.isValidNotEmptyLower("", 5));
    }

    @Test
    public void isValidNotEmptyLower5Test() {
        StringList pixels_ = new StringList("a");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower6Test() {
        StringList pixels_ = new StringList("1","");
        //StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("-1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("1",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = StringUtil.concat("5",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower11Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("4",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower12Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("2",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower13Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("3",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower14Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = StringUtil.concat("6",String.valueOf(Image.SEPARATOR_CHAR), StringUtil.join(pixels_, Image.SEPARATOR_CHAR));
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }
}
