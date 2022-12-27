package code.gui;

import org.junit.Test;

public final class GuiConstantsTest extends EquallableIntGuiUtil {
    @Test
    public void toSplitOrientation1() {
        assertEq(GuiConstants.HORIZONTAL_SPLIT,GuiConstants.toSplitOrientation(GuiConstants.HORIZONTAL_SPLIT));
    }
    @Test
    public void toSplitOrientation2() {
        assertEq(0,GuiConstants.toSplitOrientation(0));
    }
    @Test
    public void getOrient1() {
        assertEq(GuiConstants.HORIZONTAL,GuiConstants.getOrient(GuiConstants.HORIZONTAL));
    }
    @Test
    public void getOrient2() {
        assertEq(GuiConstants.VERTICAL,GuiConstants.getOrient(GuiConstants.VERTICAL));
    }
    @Test
    public void italicFlag1() {
        assertFalse(GuiConstants.italicFlag(GuiConstants.fontStyle(false, false)));
    }
    @Test
    public void italicFlag2() {
        assertFalse(GuiConstants.italicFlag(GuiConstants.fontStyle(true, false)));
    }
    @Test
    public void italicFlag3() {
        assertTrue(GuiConstants.italicFlag(GuiConstants.fontStyle(false, true)));
    }
    @Test
    public void italicFlag4() {
        assertTrue(GuiConstants.italicFlag(GuiConstants.fontStyle(true, true)));
    }
    @Test
    public void boldFlag1() {
        assertFalse(GuiConstants.boldFlag(GuiConstants.fontStyle(false, false)));
    }
    @Test
    public void boldFlag2() {
        assertFalse(GuiConstants.boldFlag(GuiConstants.fontStyle(false, true)));
    }
    @Test
    public void boldFlag3() {
        assertTrue(GuiConstants.boldFlag(GuiConstants.fontStyle(true, false)));
    }
    @Test
    public void boldFlag4() {
        assertTrue(GuiConstants.boldFlag(GuiConstants.fontStyle(true, true)));
    }
    @Test
    public void redPart1() {
        assertEq(37,GuiConstants.redPart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),true)));
    }
    @Test
    public void redPart2() {
        assertEq(37,GuiConstants.redPart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),false)));
    }
    @Test
    public void greenPart1() {
        assertEq(17,GuiConstants.greenPart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),true)));
    }
    @Test
    public void greenPart2() {
        assertEq(17,GuiConstants.greenPart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),false)));
    }
    @Test
    public void bluePart1() {
        assertEq(23,GuiConstants.bluePart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),true)));
    }
    @Test
    public void bluePart2() {
        assertEq(23,GuiConstants.bluePart(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),false)));
    }
    @Test
    public void alpha1() {
        assertEq(31,GuiConstants.alpha(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),true)));
    }
    @Test
    public void alpha2() {
        assertEq(255,GuiConstants.alpha(GuiConstants.newColor(GuiConstants.newColor(37,17,23,31),false)));
    }
    @Test
    public void alpha3() {
        assertEq(255,GuiConstants.alpha(GuiConstants.newColor(GuiConstants.newColor(37,17,23),true)));
    }
    @Test
    public void alpha4() {
        assertEq(255,GuiConstants.alpha(GuiConstants.newColor(GuiConstants.newColor(37,17,23),false)));
    }
    @Test
    public void nullToEmpty1() {
        assertEq(0,GuiConstants.nullToEmpty(null).length);
    }
    @Test
    public void nullToEmpty2() {
        assertEq(0,GuiConstants.nullToEmpty(new byte[0]).length);
    }
}
