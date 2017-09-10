package code.images;
import static code.images.EquallableImageUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.images.Image;
import code.util.PairNumber;
import code.util.StringList;

@SuppressWarnings("static-method")
public class ImageTest {

    @Test
    public void newImage1Test() {
        Image img_ = new Image("0");
        assertEq(0, img_.getWidth());
        assertEq(0, img_.getPixels().size());
    }

    @Test
    public void newImage2Test() {
        Image img_ = new Image("1"+Image.SEPARATOR_CHAR+"2");
        assertEq(1, img_.getWidth());
        assertEq(1, img_.getPixels().size());
        assertEq(2, img_.getPixels().first().intValue());
    }

    @Test
    public void newImage3Test() {
        Image img_ = new Image("1"+Image.SEPARATOR_CHAR+"2"+Image.SEPARATOR_CHAR+"3");
        assertEq(1, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(2, img_.getPixels().first().intValue());
        assertEq(3, img_.getPixels().last().intValue());
    }

    @Test
    public void newImage4Test() {
        Image img_ = new Image("2"+Image.SEPARATOR_CHAR+"3"+Image.SEPARATOR_CHAR+"4");
        assertEq(2, img_.getWidth());
        assertEq(2, img_.getPixels().size());
        assertEq(3, img_.getPixels().first().intValue());
        assertEq(4, img_.getPixels().last().intValue());
    }

    @Test
    public void isValid1Test() {
        Image img_ = new Image("0"+Image.SEPARATOR_CHAR+"1");
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid2Test() {
        Image img_ = new Image("0");
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid3Test() {
        Image img_ = new Image("1"+Image.SEPARATOR_CHAR+"1");
        assertTrue(img_.isValid());
    }

    @Test
    public void isValid4Test() {
        Image img_ = new Image("1");
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid5Test() {
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+"4"+Image.SEPARATOR_CHAR+"5");
        assertTrue(!img_.isValid());
    }

    @Test
    public void isValid6Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        assertTrue(img_.isValid());
    }

    @Test
    public void toString1Test() {
        Image img_ = new Image("0");
        assertEq("0", img_.toString());
    }

    @Test
    public void toString2Test() {
        Image img_ = new Image("1"+Image.SEPARATOR_CHAR+"2");
        assertEq("1"+Image.SEPARATOR_CHAR+"2", img_.toString());
    }

    @Test
    public void toString3Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        assertEq("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR), img_.toString());
    }

    @Test
    public void getHeight1Test() {
        Image img_ = new Image("0");
        assertEq(0, img_.getHeight());
    }

    @Test
    public void getHeight2Test() {
        StringList pixels_ = new StringList("1","2","1","2","1","2");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        assertEq(2, img_.getHeight());
    }

    @Test
    public void getDimensions1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        PairNumber<Integer,Integer> dims_ = Image.getDimensions("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR), 1);
        assertEq(3, dims_.getFirst().intValue());
        assertEq(2, dims_.getSecond().intValue());
    }

    @Test
    public void getPixel1Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
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
        assertEq(img_.toString(), img_.clip(0, 0, 1, 1).toString());
    }

    @Test
    public void clip2Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        assertEq(new Image("0").toString(), img_.clip(2, 2, 1, 1).toString());
    }

    @Test
    public void clip3Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        assertEq(new Image("0").toString(), img_.clip(3, 1, 1, 1).toString());
    }

    @Test
    public void clip4Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        Image res_ = new Image("2"+Image.SEPARATOR_CHAR+"-1"+Image.SEPARATOR_CHAR+"12");
        assertEq(res_.toString(), img_.clip(1, 1, 2, 2).toString());
    }

    @Test
    public void clip5Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        Image res_ = new Image("2"+Image.SEPARATOR_CHAR+"2"+Image.SEPARATOR_CHAR+"6"+Image.SEPARATOR_CHAR+"-1"+Image.SEPARATOR_CHAR+"12");
        assertEq(res_.toString(), img_.clip(1, 0, 2, 2).toString());
    }

    @Test
    public void clip6Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        Image img_ = new Image("3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR));
        Image res_ = new Image("2"+Image.SEPARATOR_CHAR+"2"+Image.SEPARATOR_CHAR+"6"+Image.SEPARATOR_CHAR+"-1"+Image.SEPARATOR_CHAR+"12");
        assertEq(res_.toString(), img_.clip(1, 0, 3, 3).toString());
    }

    @Test
    public void clip7Test() {
        StringList pixels_ = new StringList("1","2","6","9","-1","12");
        String img_ = "3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        String res_ = "2"+Image.SEPARATOR_CHAR+"2"+Image.SEPARATOR_CHAR+"6"+Image.SEPARATOR_CHAR+"-1"+Image.SEPARATOR_CHAR+"12";
        assertEq(res_.toString(), Image.clip(img_, 1, 0, 3, 3).toString());
    }

    @Test
    public void isValidNotEmpty1Test() {
        StringList pixels_ = new StringList();
        String img_ = "0"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
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
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty6Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "-1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "5"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5));
    }

    @Test
    public void isValidNotEmpty10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(Image.isValidNotEmpty(img_, 3));
    }

    @Test
    public void isValidNotEmpty11Test() {
        StringList pixels_ = new StringList();
        String img_ = "0"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
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
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty16Test() {
        StringList pixels_ = new StringList("1","");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty17Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "-1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty18Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty19Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "5"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmpty(img_, 5, 3));
    }

    @Test
    public void isValidNotEmpty20Test() {
        StringList pixels_ = new StringList("4","-5","-6","7","-8","9");
        String img_ = "3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(Image.isValidNotEmpty(img_, 3, 2));
    }

    @Test
    public void isValidNotEmptyLower1Test() {
        StringList pixels_ = new StringList();
        String img_ = "0"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
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
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower6Test() {
        StringList pixels_ = new StringList("1","");
        //StringList pixels_ = new StringList("1");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower7Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "-1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower8Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "1"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower9Test() {
        StringList pixels_ = new StringList("1");
        String img_ = "5"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 5));
    }

    @Test
    public void isValidNotEmptyLower10Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower11Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "4"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower12Test() {
        StringList pixels_ = new StringList("1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "2"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower13Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "3"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }

    @Test
    public void isValidNotEmptyLower14Test() {
        StringList pixels_ = new StringList("1","2","3","1","2","3","4","-5","-6","-7","-8","-9");
        String img_ = "6"+Image.SEPARATOR_CHAR+pixels_.join(Image.SEPARATOR_CHAR);
        assertTrue(!Image.isValidNotEmptyLower(img_, 3));
    }
}
