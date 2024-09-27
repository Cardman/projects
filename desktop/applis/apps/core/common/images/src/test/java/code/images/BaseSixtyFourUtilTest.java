package code.images;

import org.junit.Test;

public final class BaseSixtyFourUtilTest extends EquallableImageUtil {

    @Test
    public void parseFourChars1Test() {
        byte[] bytes_ = parseFourChars("AAAA");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(0, bytes_[2]);
    }

    @Test
    public void parseFourChars2Test() {
        byte[] bytes_ = parseFourChars("AAAB");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(1, bytes_[2]);
    }

    @Test
    public void parseFourChars3Test() {
        byte[] bytes_ = parseFourChars("AAA/");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(63, bytes_[2]);
    }

    @Test
    public void parseFourChars4Test() {
        byte[] bytes_ = parseFourChars("AABA");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(64, bytes_[2]);
    }

    @Test
    public void parseFourChars5Test() {
        byte[] bytes_ = parseFourChars("AAB/");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(127, bytes_[2]);
    }

    @Test
    public void parseFourChars6Test() {
        byte[] bytes_ = parseFourChars("AACA");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(-128, bytes_[2]);
    }

    @Test
    public void parseFourChars7Test() {
        byte[] bytes_ = parseFourChars("AAAZ");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(25, bytes_[2]);
    }

    @Test
    public void parseFourChars8Test() {
        byte[] bytes_ = parseFourChars("AAAa");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(26, bytes_[2]);
    }

    @Test
    public void parseFourChars9Test() {
        byte[] bytes_ = parseFourChars("AAAz");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(51, bytes_[2]);
    }

    @Test
    public void parseFourChars10Test() {
        byte[] bytes_ = parseFourChars("AAA0");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(52, bytes_[2]);
    }

    @Test
    public void parseFourChars11Test() {
        byte[] bytes_ = parseFourChars("AAA9");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(61, bytes_[2]);
    }

    @Test
    public void parseFourChars12Test() {
        byte[] bytes_ = parseFourChars("AAA+");
        assertEq(3, bytes_.length);
        assertEq(0, bytes_[0]);
        assertEq(0, bytes_[1]);
        assertEq(62, bytes_[2]);
    }

    @Test
    public void parseFourChars13Test() {
        byte[] bytes_ = parseFourChars("/1z+");
        assertEq(3, bytes_.length);
        assertEq(-1, bytes_[0]);
        assertEq(92, bytes_[1]);
        assertEq(-2, bytes_[2]);
    }

    @Test
    public void parseFourChars14Test() {
        byte[] bytes_ = parseFourChars("|1z_");
        assertEq(3, bytes_.length);
        assertEq(-1, bytes_[0]);
        assertEq(92, bytes_[1]);
        assertEq(-1, bytes_[2]);
    }
    @Test
    public void printThreeBytes1Test() {
        byte[] bytes_ = parseFourChars("AAAA");
        assertEq("AAAA", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes2Test() {
        byte[] bytes_ = parseFourChars("AAAB");
        assertEq("AAAB", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes3Test() {
        byte[] bytes_ = parseFourChars("AAA/");
        assertEq("AAA/", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes4Test() {
        byte[] bytes_ = parseFourChars("AABA");
        assertEq("AABA", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes5Test() {
        byte[] bytes_ = parseFourChars("AAB/");
        assertEq("AAB/", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes6Test() {
        byte[] bytes_ = parseFourChars("AACA");
        assertEq("AACA", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes7Test() {
        byte[] bytes_ = parseFourChars("AAAZ");
        assertEq("AAAZ", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes8Test() {
        byte[] bytes_ = parseFourChars("AAAa");
        assertEq("AAAa", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes9Test() {
        byte[] bytes_ = parseFourChars("AAAz");
        assertEq("AAAz", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes10Test() {
        byte[] bytes_ = parseFourChars("AAA0");
        assertEq("AAA0", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes11Test() {
        byte[] bytes_ = parseFourChars("AAA9");
        assertEq("AAA9", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes12Test() {
        byte[] bytes_ = parseFourChars("AAA+");
        assertEq("AAA+", printThreeBytes(bytes_));
    }

    @Test
    public void printThreeBytes13Test() {
        byte[] bytes_ = parseFourChars("/1z+");
        assertEq("/1z+", printThreeBytes(bytes_));
    }
    @Test
    public void getImageByString0Test() {
        int[][] img_ = getImageByString("\nAAABAAAA");
        assertEq(1, img_.length);
        assertEq(1, img_[0].length);
        assertEq(0, img_[0][0]);
    }
    @Test
    public void getImageByString1Test() {
        int[][] img_ = getImageByString("AAABAAAA");
        assertEq(1, img_.length);
        assertEq(1, img_[0].length);
        assertEq(0, img_[0][0]);
    }
    @Test
    public void getImageByString2Test() {
        int[][] img_ = getImageByString("AAABABAB");
        assertEq(1, img_.length);
        assertEq(1, img_[0].length);
        assertEq(4097, img_[0][0]);
    }
    @Test
    public void getImageByString3Test() {
        int[][] img_ = getImageByString("AAACABABbaba");
        assertEq(1, img_.length);
        assertEq(2, img_[0].length);
        assertEq(4097, img_[0][0]);
        assertEq(7186138, img_[0][1]);
    }
    @Test
    public void getImageByString4Test() {
        int[][] img_ = getImageByString("AAABABABbaba");
        assertEq(2, img_.length);
        assertEq(1, img_[0].length);
        assertEq(4097, img_[0][0]);
        assertEq(1, img_[1].length);
        assertEq(7186138, img_[1][0]);
    }
    @Test
    public void getImageByString5Test() {
        int[][] img_ = getImageByString("AAAB////");
        assertEq(1, img_.length);
        assertEq(1, img_[0].length);
        assertEq(16777215, img_[0][0]);
    }
    @Test
    public void getImageByString6Test() {
        int[][] img_ = getImageByString("AAACbabaABAB");
        assertEq(1, img_.length);
        assertEq(2, img_[0].length);
        assertEq(7186138, img_[0][0]);
        assertEq(4097, img_[0][1]);
    }
    @Test
    public void getImageByString7Test() {
        int[][] img_ = getImageByString("AAABbabaABAB");
        assertEq(2, img_.length);
        assertEq(1, img_[0].length);
        assertEq(7186138, img_[0][0]);
        assertEq(1, img_[1].length);
        assertEq(4097, img_[1][0]);
    }
    @Test
    public void getImageByString8Test() {
        StringBuilder imgStr_ = new StringBuilder(4+4*128);
        imgStr_.append("AACA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        int[][] img_ = getImageByString(imgStr_.toString());
        assertEq(1, img_.length);
        assertEq(128, img_[0].length);
        checkZero(img_[0]);
    }
    @Test
    public void getImageByString9Test() {
        int[][] img_ = getImageByString("AAACAAADAAAEAAAFAAAGAAAHAAAI");
        assertEq(3, img_.length);
        assertEq(2, img_[0].length);
        assertEq(3, img_[0][0]);
        assertEq(4, img_[0][1]);
        assertEq(2, img_[1].length);
        assertEq(5, img_[1][0]);
        assertEq(6, img_[1][1]);
        assertEq(2, img_[2].length);
        assertEq(7, img_[2][0]);
        assertEq(8, img_[2][1]);
    }
    @Test
    public void getImageByString10Test() {
        int[][] img_ = getImageByString("AAADAAADAAAEAAAFAAAGAAAHAAAI");
        assertEq(2, img_.length);
        assertEq(3, img_[0].length);
        assertEq(3, img_[0][0]);
        assertEq(4, img_[0][1]);
        assertEq(5, img_[0][2]);
        assertEq(3, img_[1].length);
        assertEq(6, img_[1][0]);
        assertEq(7, img_[1][1]);
        assertEq(8, img_[1][2]);
    }
    @Test
    public void getImageByString11Test() {
        int[][] img_ = getImageByString("AAACbaba");
        assertEq(0, img_.length);
    }
    @Test
    public void getImageByString12Test() {
        int[][] img_ = getImageByString("AAACba");
        assertEq(0, img_.length);
    }
    @Test
    public void getImageByString13Test() {
        int[][] img_ = getImageByString("AAAC");
        assertEq(0, img_.length);
    }
    @Test
    public void getImageByString14Test() {
        int[][] img_ = getImageByString("AAAA");
        assertEq(0, img_.length);
    }
    @Test
    public void getImageByString15Test() {
        int[][] img_ = getImageByString("AAA");
        assertEq(0, img_.length);
    }
    @Test
    public void getImageByString16Test() {
        int[][] img_ = getImageByString("\n");
        assertEq(0, img_.length);
    }
    @Test
    public void getSringByImage1Test() {
        int[][] img_ = getImageByString("AAABAAAA");
        assertEq("AAABAAAA", getStringByImage(img_));
    }
    @Test
    public void getSringByImage2Test() {
        int[][] img_ = getImageByString("AAABABAB");
        assertEq("AAABABAB", getStringByImage(img_));
    }
    @Test
    public void getSringByImage3Test() {
        int[][] img_ = getImageByString("AAACABABbaba");
        assertEq("AAACABABbaba", getStringByImage(img_));
    }
    @Test
    public void getSringByImage4Test() {
        int[][] img_ = getImageByString("AAABABABbaba");
        assertEq("AAABABABbaba", getStringByImage(img_));
    }
    @Test
    public void getSringByImage5Test() {
        int[][] img_ = getImageByString("AAAB////");
        assertEq("AAAB////", getStringByImage(img_));
    }
    @Test
    public void getSringByImage6Test() {
        int[][] img_ = getImageByString("AAACbabaABAB");
        assertEq("AAACbabaABAB", getStringByImage(img_));
    }

    @Test
    public void getSringByImage7Test() {
        int[][] img_ = getImageByString("AAABbabaABAB");
        assertEq("AAABbabaABAB", getStringByImage(img_));
    }

    @Test
    public void getSringByImage8Test() {
        StringBuilder imgStr_ = new StringBuilder(4+4*128);
        imgStr_.append("AACA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        imgStr_.append("AAAA");
        int[][] img_ = getImageByString(imgStr_.toString());
        assertEq(imgStr_.toString(), getStringByImage(img_));
    }

    private byte[] parseFourChars(String _text) {
        return BaseSixtyFourUtil.parseFourChars(_text, BASE);
    }

    private String printThreeBytes(byte[] _bytes) {
        return BaseSixtyFourUtil.printThreeBytes(_bytes, BASE);
    }

    private String getStringByImage(int[][] _img) {
        return BaseSixtyFourUtil.getStringByImage(_img, BASE);
    }

    private int[][] getImageByString(String _img) {
        return BaseSixtyFourUtil.getImageByString(_img,BASE);
    }

}
