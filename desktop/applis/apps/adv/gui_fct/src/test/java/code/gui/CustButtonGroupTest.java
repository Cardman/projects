package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.mock.MockRadioButton;
import org.junit.Test;

public final class CustButtonGroupTest extends EquallableGuiFctUtil {
    @Test
    public void s1() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r_ = init_.getCompoFactory().newRadioButton("0");
        r_.setSelected(false);
        c_.add(r_);
        c_.add(r_);
        assertEq(1,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s2() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s3() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        assertTrue(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
    @Test
    public void s4() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertTrue(r2_.isSelected());
        assertSame(r2_,c_.getSelected());
    }
    @Test
    public void s5() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        assertTrue(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
    @Test
    public void s6() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_);
        r2_.setSelected(true);
        ((MockRadioButton)r2_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertTrue(r2_.isSelected());
        assertSame(r2_,c_.getSelected());
    }
    @Test
    public void s7() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        r1_.setSelected(true);
        ((MockRadioButton)r1_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r2_.isSelected());
        assertTrue(r1_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
    @Test
    public void s8() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r_ = init_.getCompoFactory().newRadioButton("0");
        r_.setSelected(false);
        c_.add(r_,"0");
        c_.add(r_,"0");
        assertEq(1,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s9() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s10() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        assertTrue(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
    @Test
    public void s11() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertTrue(r2_.isSelected());
        assertSame(r2_,c_.getSelected());
    }
    @Test
    public void s12() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertTrue(r2_.isSelected());
        assertSame(r2_,c_.getSelected());
    }
    @Test
    public void s13() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_,"1");
        r2_.setSelected(true);
        ((MockRadioButton)r2_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertTrue(r2_.isSelected());
        assertSame(r2_,c_.getSelected());
    }
    @Test
    public void s14() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        r1_.setSelected(true);
        ((MockRadioButton)r1_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r2_.isSelected());
        assertTrue(r1_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
    @Test
    public void s15() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"0");
        assertEq(2,c_.getGroup().size());
        assertTrue(r1_.isSelected());
        assertTrue(r2_.isSelected());
    }
    @Test
    public void s16() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"0");
        assertEq(2,c_.getGroup().size());
        assertEq(2,c_.getButtonCount());
        c_.clearSelection();
        assertFalse(r1_.isSelected());
        assertFalse(r2_.isSelected());
    }
    @Test
    public void s17() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r_ = init_.getCompoFactory().newRadioButton("0");
        r_.setSelected(false);
        c_.add(r_);
        c_.remove(r_);
        assertEq(0,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s18() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r_ = init_.getCompoFactory().newRadioButton("0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("0");
        r_.setSelected(false);
        c_.add(r_);
        c_.remove(r2_);
        assertEq(1,c_.getGroup().size());
    }
    @Test
    public void s19() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r_ = init_.getCompoFactory().newRadioButton("0");
        r_.setSelected(true);
        c_.add(r_);
        c_.remove(r_);
        assertEq(0,c_.getGroup().size());
        assertNull(c_.getSelected());
    }
    @Test
    public void s20() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        r1_.setSelected(false);
        ((MockRadioButton)r1_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertNull(c_.getSelected());
    }
    @Test
    public void s21() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(true);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_,"1");
        r1_.setSelected(false);
        ((MockRadioButton)r1_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertNull(c_.getSelected());
    }
    @Test
    public void s22() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_,"0");
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(true);
        c_.add(r2_,"1");
        assertEq(2,c_.getGroup().size());
        r2_.setSelected(false);
        ((MockRadioButton)r2_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertFalse(r2_.isSelected());
        assertFalse(r1_.isSelected());
        assertNull(c_.getSelected());
    }
    @Test
    public void s23() {
        MockProgramInfosSecSample init_ = init();
        CustButtonGroup c_ = new CustButtonGroup();
        AbsRadioButton r1_ = init_.getCompoFactory().newRadioButton("0");
        r1_.setSelected(false);
        c_.add(r1_);
        AbsRadioButton r2_ = init_.getCompoFactory().newRadioButton("1");
        r2_.setSelected(false);
        c_.add(r2_);
        assertEq(2,c_.getGroup().size());
        r1_.setSelected(true);
        ((MockRadioButton)r1_).getActionListeners().get(0).action();
        assertEq(2,c_.getGroup().size());
        assertTrue(r1_.isSelected());
        assertFalse(r2_.isSelected());
        assertSame(r1_,c_.getSelected());
    }
}
