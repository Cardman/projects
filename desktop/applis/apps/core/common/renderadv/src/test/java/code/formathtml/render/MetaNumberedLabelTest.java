package code.formathtml.render;

import code.sml.MessagesRendKeyWordsDefs;
import org.junit.Test;

public final class MetaNumberedLabelTest extends EquallableRenderAdvUtil {
    @Test
    public void convert1Test() {
        assertEq("1",MetaNumberedLabel.convert(1,MetaNumberBase.NUMBER, MessagesRendKeyWordsDefs.init()));
    }
    @Test
    public void convert2Test() {
        assertEq("a", convertMinLetter(1));
    }
    @Test
    public void convert3Test() {
        assertEq("b", convertMinLetter(2));
    }
    @Test
    public void convert4Test() {
        assertEq("z", convertMinLetter(26));
    }
    @Test
    public void convert5Test() {
        assertEq("aa", convertMinLetter(27));
    }
    @Test
    public void convert6Test() {
        assertEq("az", convertMinLetter(52));
    }
    @Test
    public void convert7Test() {
        assertEq("ba", convertMinLetter(53));
    }
    @Test
    public void convert8Test() {
        assertEq("zz", convertMinLetter(26 + 26*26));
    }
    @Test
    public void convert9Test() {
        assertEq("aaa", convertMinLetter(26 + 26*26+1));
    }
    @Test
    public void convert10Test() {
        assertEq("aaz", convertMinLetter(26 + 26*26+26));
    }
    @Test
    public void convert11Test() {
        assertEq("aba", convertMinLetter(26 + 26*26+27));
    }
    @Test
    public void convert12Test() {
        assertEq("abz", convertMinLetter(26 + 26*26+52));
    }
    @Test
    public void convert13Test() {
        assertEq("aca", convertMinLetter(26 + 26*26+53));
    }
    @Test
    public void convert14Test() {
        assertEq("azz", convertMinLetter(26 + 26*26+ 26*26));
    }
    @Test
    public void convert15Test() {
        assertEq("baa", convertMinLetter(26 + 26*26+ 26*26+1));
    }
    @Test
    public void convert16Test() {
        assertEq("zzz", convertMinLetter(26 + 26*26+26*26*26));
    }
    @Test
    public void convert17Test() {
        assertEq("aaaa", convertMinLetter(26 + 26*26+26*26*26+1));
    }
    @Test
    public void convert017Test() {
        assertEq("zzzz", convertMinLetter(26 + 26*26+26*26*26+26*26*26*26));
    }
    @Test
    public void convert018Test() {
        assertEq("aaaaa", convertMinLetter(1+26 + 26*26+26*26*26+26*26*26*26));
    }
    @Test
    public void convert18Test() {
        assertEq("A", convertMajLetter(1));
    }
    @Test
    public void convert19Test() {
        assertEq("B", convertMajLetter(2));
    }
    @Test
    public void convert20Test() {
        assertEq("i", convertMinLatin(1));
    }
    @Test
    public void convert21Test() {
        assertEq("ii", convertMinLatin(2));
    }
    @Test
    public void convert22Test() {
        assertEq("iv", convertMinLatin(4));
    }
    @Test
    public void convert23Test() {
        assertEq("v", convertMinLatin(5));
    }
    @Test
    public void convert24Test() {
        assertEq("vi", convertMinLatin(6));
    }
    @Test
    public void convert25Test() {
        assertEq("ix", convertMinLatin(9));
    }
    @Test
    public void convert26Test() {
        assertEq("x", convertMinLatin(10));
    }
    @Test
    public void convert27Test() {
        assertEq("xi", convertMinLatin(11));
    }
    @Test
    public void convert28Test() {
        assertEq("xiv", convertMinLatin(14));
    }
    @Test
    public void convert29Test() {
        assertEq("xix", convertMinLatin(19));
    }
    @Test
    public void convert30Test() {
        assertEq("xxxix", convertMinLatin(39));
    }
    @Test
    public void convert31Test() {
        assertEq("xl", convertMinLatin(40));
    }
    @Test
    public void convert32Test() {
        assertEq("xlix", convertMinLatin(49));
    }
    @Test
    public void convert33Test() {
        assertEq("l", convertMinLatin(50));
    }
    @Test
    public void convert34Test() {
        assertEq("lxxxix", convertMinLatin(89));
    }
    @Test
    public void convert35Test() {
        assertEq("xc", convertMinLatin(90));
    }
    @Test
    public void convert36Test() {
        assertEq("xcix", convertMinLatin(99));
    }
    @Test
    public void convert37Test() {
        assertEq("c", convertMinLatin(100));
    }
    @Test
    public void convert38Test() {
        assertEq("cccxc", convertMinLatin(390));
    }
    @Test
    public void convert39Test() {
        assertEq("cccxcix", convertMinLatin(399));
    }
    @Test
    public void convert40Test() {
        assertEq("cd", convertMinLatin(400));
    }
    @Test
    public void convert41Test() {
        assertEq("dcccxc", convertMinLatin(890));
    }
    @Test
    public void convert42Test() {
        assertEq("dcccxcix", convertMinLatin(899));
    }
    @Test
    public void convert43Test() {
        assertEq("cmxcix", convertMinLatin(999));
    }
    @Test
    public void convert44Test() {
        assertEq("mcmxcix", convertMinLatin(1999));
    }
    @Test
    public void convert45Test() {
        assertEq("mmmdcccxc", convertMinLatin(3890));
    }
    @Test
    public void convert46Test() {
        assertEq("mmmdcccxcix", convertMinLatin(3899));
    }
    @Test
    public void convert47Test() {
        assertEq("mmmcmxcix", convertMinLatin(3999));
    }
    @Test
    public void convert48Test() {
        assertEq("mqdcccxc", convertMinLatin(4890));
    }
    @Test
    public void convert49Test() {
        assertEq("mqdcccxcix", convertMinLatin(4899));
    }
    @Test
    public void convert50Test() {
        assertEq("mqcmxcix", convertMinLatin(4999));
    }
    @Test
    public void convert51Test() {
        assertEq("qcmxcix", convertMinLatin(5999));
    }
    @Test
    public void convert52Test() {
        assertEq("qmmmmcmxcix", convertMinLatin(9999));
    }
    @Test
    public void convert53Test() {
        assertEq("i+|", convertMinLatin(10000));
    }
    @Test
    public void convert54Test() {
        assertEq("I+|",MetaNumberedLabel.convert(10000,MetaNumberBase.LATIN_MAJ, MessagesRendKeyWordsDefs.init()));
    }

    private String convertMinLetter(int _number) {
        return MetaNumberedLabel.convert(_number, MetaNumberBase.LETTER, MessagesRendKeyWordsDefs.init());
    }

    private String convertMajLetter(int _number) {
        return MetaNumberedLabel.convert(_number, MetaNumberBase.MAJ_LETTER, MessagesRendKeyWordsDefs.init());
    }

    private String convertMinLatin(int _number) {
        return MetaNumberedLabel.convert(_number, MetaNumberBase.LATIN_MIN, MessagesRendKeyWordsDefs.init());
    }

}
