package code.formathtml.render;

import code.formathtml.EquallableRenderUtil;
import org.junit.Test;

public final class MetaNumberedLabelTest extends EquallableRenderUtil {
    @Test
    public void convert1Test() {
        assertEq("1",MetaNumberedLabel.convert(1,MetaNumberBase.NUMBER));
    }
    @Test
    public void convert2Test() {
        assertEq("a",MetaNumberedLabel.convert(1,MetaNumberBase.LETTER));
    }
    @Test
    public void convert3Test() {
        assertEq("b",MetaNumberedLabel.convert(2,MetaNumberBase.LETTER));
    }
    @Test
    public void convert4Test() {
        assertEq("z",MetaNumberedLabel.convert(26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert5Test() {
        assertEq("aa",MetaNumberedLabel.convert(27,MetaNumberBase.LETTER));
    }
    @Test
    public void convert6Test() {
        assertEq("az",MetaNumberedLabel.convert(52,MetaNumberBase.LETTER));
    }
    @Test
    public void convert7Test() {
        assertEq("ba",MetaNumberedLabel.convert(53,MetaNumberBase.LETTER));
    }
    @Test
    public void convert8Test() {
        assertEq("zz",MetaNumberedLabel.convert(26 + 26*26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert9Test() {
        assertEq("aaa",MetaNumberedLabel.convert(26 + 26*26+1,MetaNumberBase.LETTER));
    }
    @Test
    public void convert10Test() {
        assertEq("aaz",MetaNumberedLabel.convert(26 + 26*26+26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert11Test() {
        assertEq("aba",MetaNumberedLabel.convert(26 + 26*26+27,MetaNumberBase.LETTER));
    }
    @Test
    public void convert12Test() {
        assertEq("abz",MetaNumberedLabel.convert(26 + 26*26+52,MetaNumberBase.LETTER));
    }
    @Test
    public void convert13Test() {
        assertEq("aca",MetaNumberedLabel.convert(26 + 26*26+53,MetaNumberBase.LETTER));
    }
    @Test
    public void convert14Test() {
        assertEq("azz",MetaNumberedLabel.convert(26 + 26*26+ 26*26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert15Test() {
        assertEq("baa",MetaNumberedLabel.convert(26 + 26*26+ 26*26+1,MetaNumberBase.LETTER));
    }
    @Test
    public void convert16Test() {
        assertEq("zzz",MetaNumberedLabel.convert(26 + 26*26+26*26*26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert17Test() {
        assertEq("aaaa",MetaNumberedLabel.convert(26 + 26*26+26*26*26+1,MetaNumberBase.LETTER));
    }
    @Test
    public void convert017Test() {
        assertEq("zzzz",MetaNumberedLabel.convert(26 + 26*26+26*26*26+26*26*26*26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert018Test() {
        assertEq("aaaaa",MetaNumberedLabel.convert(1+26 + 26*26+26*26*26+26*26*26*26,MetaNumberBase.LETTER));
    }
    @Test
    public void convert18Test() {
        assertEq("A",MetaNumberedLabel.convert(1,MetaNumberBase.MAJ_LETTER));
    }
    @Test
    public void convert19Test() {
        assertEq("B",MetaNumberedLabel.convert(2,MetaNumberBase.MAJ_LETTER));
    }
    @Test
    public void convert20Test() {
        assertEq("i",MetaNumberedLabel.convert(1,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert21Test() {
        assertEq("ii",MetaNumberedLabel.convert(2,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert22Test() {
        assertEq("iv",MetaNumberedLabel.convert(4,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert23Test() {
        assertEq("v",MetaNumberedLabel.convert(5,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert24Test() {
        assertEq("vi",MetaNumberedLabel.convert(6,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert25Test() {
        assertEq("ix",MetaNumberedLabel.convert(9,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert26Test() {
        assertEq("x",MetaNumberedLabel.convert(10,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert27Test() {
        assertEq("xi",MetaNumberedLabel.convert(11,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert28Test() {
        assertEq("xiv",MetaNumberedLabel.convert(14,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert29Test() {
        assertEq("xix",MetaNumberedLabel.convert(19,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert30Test() {
        assertEq("xxxix",MetaNumberedLabel.convert(39,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert31Test() {
        assertEq("xl",MetaNumberedLabel.convert(40,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert32Test() {
        assertEq("xlix",MetaNumberedLabel.convert(49,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert33Test() {
        assertEq("l",MetaNumberedLabel.convert(50,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert34Test() {
        assertEq("lxxxix",MetaNumberedLabel.convert(89,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert35Test() {
        assertEq("xc",MetaNumberedLabel.convert(90,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert36Test() {
        assertEq("xcix",MetaNumberedLabel.convert(99,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert37Test() {
        assertEq("c",MetaNumberedLabel.convert(100,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert38Test() {
        assertEq("cccxc",MetaNumberedLabel.convert(390,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert39Test() {
        assertEq("cccxcix",MetaNumberedLabel.convert(399,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert40Test() {
        assertEq("cd",MetaNumberedLabel.convert(400,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert41Test() {
        assertEq("dcccxc",MetaNumberedLabel.convert(890,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert42Test() {
        assertEq("dcccxcix",MetaNumberedLabel.convert(899,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert43Test() {
        assertEq("cmxcix",MetaNumberedLabel.convert(999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert44Test() {
        assertEq("mcmxcix",MetaNumberedLabel.convert(1999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert45Test() {
        assertEq("mmmdcccxc",MetaNumberedLabel.convert(3890,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert46Test() {
        assertEq("mmmdcccxcix",MetaNumberedLabel.convert(3899,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert47Test() {
        assertEq("mmmcmxcix",MetaNumberedLabel.convert(3999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert48Test() {
        assertEq("mqdcccxc",MetaNumberedLabel.convert(4890,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert49Test() {
        assertEq("mqdcccxcix",MetaNumberedLabel.convert(4899,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert50Test() {
        assertEq("mqcmxcix",MetaNumberedLabel.convert(4999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert51Test() {
        assertEq("qcmxcix",MetaNumberedLabel.convert(5999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert52Test() {
        assertEq("qmmmmcmxcix",MetaNumberedLabel.convert(9999,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert53Test() {
        assertEq("i ",MetaNumberedLabel.convert(10000,MetaNumberBase.LATIN_MIN));
    }
    @Test
    public void convert54Test() {
        assertEq("I ",MetaNumberedLabel.convert(10000,MetaNumberBase.LATIN_MAJ));
    }
}
