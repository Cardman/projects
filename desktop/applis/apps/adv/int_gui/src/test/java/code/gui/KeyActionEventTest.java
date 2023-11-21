package code.gui;

import org.junit.Test;

public final class KeyActionEventTest extends EquallableIntGuiUtil {
    @Test
    public void c1() {
        KeyActionEvent k_ = new KeyActionEvent(0);
        assertFalse(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c2() {
        KeyActionEvent k_ = new KeyActionEvent(1);
        assertFalse(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c3() {
        KeyActionEvent k_ = new KeyActionEvent(2);
        assertFalse(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c4() {
        KeyActionEvent k_ = new KeyActionEvent(3);
        assertFalse(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c5() {
        KeyActionEvent k_ = new KeyActionEvent(4);
        assertFalse(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c6() {
        KeyActionEvent k_ = new KeyActionEvent(5);
        assertFalse(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c7() {
        KeyActionEvent k_ = new KeyActionEvent(6);
        assertFalse(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c8() {
        KeyActionEvent k_ = new KeyActionEvent(7);
        assertFalse(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c9() {
        KeyActionEvent k_ = new KeyActionEvent(8);
        assertTrue(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c10() {
        KeyActionEvent k_ = new KeyActionEvent(9);
        assertTrue(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c11() {
        KeyActionEvent k_ = new KeyActionEvent(10);
        assertTrue(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c12() {
        KeyActionEvent k_ = new KeyActionEvent(11);
        assertTrue(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c13() {
        KeyActionEvent k_ = new KeyActionEvent(12);
        assertTrue(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c14() {
        KeyActionEvent k_ = new KeyActionEvent(13);
        assertTrue(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c15() {
        KeyActionEvent k_ = new KeyActionEvent(14);
        assertTrue(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c16() {
        KeyActionEvent k_ = new KeyActionEvent(15);
        assertTrue(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c17() {
        KeyActionEvent k_ = new KeyActionEvent(false,false,false);
        assertFalse(k_.isAltDown());
        assertFalse(k_.isControlDown());
        assertFalse(k_.isShiftDown());
    }
    @Test
    public void c18() {
        KeyActionEvent k_ = new KeyActionEvent(true,true,true);
        assertTrue(k_.isAltDown());
        assertTrue(k_.isControlDown());
        assertTrue(k_.isShiftDown());
    }
    @Test
    public void c19() {
        CoreMouseLocation k_ = new CoreMouseLocation(1,2);
        assertEq(1,k_.getXcoord());
        assertEq(2,k_.getYcoord());
    }
    @Test
    public void c20() {
        CoreMouseButtons k_ = new CoreMouseButtons(false,false,false,0);
        assertFalse(k_.isLeftMouseButton());
        assertFalse(k_.isMiddleMouseButton());
        assertFalse(k_.isRightMouseButton());
        assertEq(0,k_.getClickCount());
    }
    @Test
    public void c21() {
        CoreMouseButtons k_ = new CoreMouseButtons(true,true,true,1);
        assertTrue(k_.isLeftMouseButton());
        assertTrue(k_.isMiddleMouseButton());
        assertTrue(k_.isRightMouseButton());
        assertEq(1,k_.getClickCount());
    }
}
