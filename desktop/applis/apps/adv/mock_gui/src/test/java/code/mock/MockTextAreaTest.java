package code.mock;

import code.gui.images.MetaPoint;
import code.gui.images.MetaRect;
import org.junit.Test;

public final class MockTextAreaTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.setSelectionStart(1);
        t_.setSelectionEnd(4);
        t_.select(1,4);
        assertEq("ell",t_.getSelectedText());
    }
    @Test
    public void t2() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.select(1,7);
        assertEq("ello",t_.getSelectedText());
    }
    @Test
    public void t3() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.select(-1,7);
        assertEq("hello",t_.getSelectedText());
    }
    @Test
    public void t4() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.setSelectionStart(1);
        t_.setSelectionEnd(4);
        t_.select(1,4);
        t_.replaceSelection("fmm");
        assertEq("hfmmo",t_.getText());
    }
    @Test
    public void t5() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq("hello",t_.getText());
    }
    @Test
    public void t6() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        t_.insert("m",1);
        t_.insert("n",1);
        assertEq("hnmello",t_.getText());
    }
    @Test
    public void t7() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        t_.replaceRange("a",1,2);
        t_.replaceRange("",2,3);
        t_.selectAll();
        assertEq("halo",t_.getText());
        assertEq("halo",t_.getSelectedText());
    }
    @Test
    public void t8() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        t_.setEditable(false);
        assertFalse(t_.isEnabled());
        t_.setTabSize(8);
        assertEq(8,t_.getTabSize());
        assertEq(16, t_.getCols());
        assertEq(16, t_.getRows());
    }
    @Test
    public void t9() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("__");
        t_.setCaretPosition(1);
        t_.moveCaretPosition(2);
        assertEq(1, t_.getSelectionStart());
        assertEq(2, t_.getCaretPosition());
        assertEq(2, t_.getSelectionEnd());
    }
    @Test
    public void t10() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.select(7,7);
        assertEq("",t_.getSelectedText());
    }
    @Test
    public void t11() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq(0,t_.viewToModel(new MetaPoint(0,0)));
    }
    @Test
    public void t12() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq(1,t_.viewToModel(new MetaPoint(t_.heightFont(),0)));
    }
    @Test
    public void t13() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\n");
        assertEq(1,t_.viewToModel(new MetaPoint(t_.heightFont(),0)));
    }
    @Test
    public void t14() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(6,t_.viewToModel(new MetaPoint(0,t_.heightFont())));
    }
    @Test
    public void t15() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(7,t_.viewToModel(new MetaPoint(t_.heightFont(),t_.heightFont())));
    }
    @Test
    public void t16() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq(-1,t_.viewToModel(new MetaPoint(5*t_.heightFont(),0)));
    }
    @Test
    public void t17() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(5,t_.viewToModel(new MetaPoint(5*t_.heightFont(),0)));
    }
    @Test
    public void t18() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(-1,t_.viewToModel(new MetaPoint(5*t_.heightFont(),t_.heightFont())));
    }
    @Test
    public void t19() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(-1,t_.viewToModel(new MetaPoint(-1,0)));
    }
    @Test
    public void t20() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(-1,t_.viewToModel(new MetaPoint(0,-1)));
    }
    @Test
    public void t21() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq(4,t_.viewToModel(new MetaPoint(4*t_.heightFont(),0)));
    }
    @Test
    public void t22() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(4,t_.viewToModel(new MetaPoint(4*t_.heightFont(),0)));
    }
    @Test
    public void t23() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(10,t_.viewToModel(new MetaPoint(4*t_.heightFont(),t_.heightFont())));
    }
    @Test
    public void t24() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        assertEq(-1,t_.viewToModel(new MetaPoint(0,t_.heightFont())));
    }
    @Test
    public void t25() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        assertEq(-1,t_.viewToModel(new MetaPoint(0,2*t_.heightFont())));
    }
    @Test
    public void t26() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        MetaRect r_ = t_.modelToView(5);
        assertEq(0,r_.getHeight());
        assertEq(0,r_.getWidth());
    }
    @Test
    public void t27() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        MetaRect r_ = t_.modelToView(11);
        assertEq(0,r_.getHeight());
        assertEq(0,r_.getWidth());
    }
    @Test
    public void t28() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        MetaRect r_ = t_.modelToView(4);
        assertEq(4L*t_.heightFont(),r_.getPoint().getXcoord());
        assertEq(0,r_.getPoint().getYcoord());
        assertEq(t_.heightFont(),r_.getHeight());
        assertEq(t_.heightFont(),r_.getWidth());
    }
    @Test
    public void t29() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        MetaRect r_ = t_.modelToView(10);
        assertEq(4L*t_.heightFont(),r_.getPoint().getXcoord());
        assertEq(t_.heightFont(),r_.getPoint().getYcoord());
        assertEq(t_.heightFont(),r_.getHeight());
        assertEq(t_.heightFont(),r_.getWidth());
    }
    @Test
    public void t30() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        MetaRect r_ = t_.modelToView(5);
        assertEq(5L*t_.heightFont(),r_.getPoint().getXcoord());
        assertEq(0,r_.getPoint().getYcoord());
        assertEq(t_.heightFont(),r_.getHeight());
        assertEq(t_.heightFont(),r_.getWidth());
    }
    @Test
    public void t31() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        MetaRect r_ = t_.modelToView(4);
        assertEq(4L*t_.heightFont(),r_.getPoint().getXcoord());
        assertEq(0,r_.getPoint().getYcoord());
        assertEq(t_.heightFont(),r_.getHeight());
        assertEq(t_.heightFont(),r_.getWidth());
    }
    @Test
    public void t32() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        MetaRect r_ = t_.modelToView(6);
        assertEq(0,r_.getPoint().getXcoord());
        assertEq(t_.heightFont(),r_.getPoint().getYcoord());
        assertEq(t_.heightFont(),r_.getHeight());
        assertEq(t_.heightFont(),r_.getWidth());
    }
    @Test
    public void t33() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        MetaRect r_ = t_.modelToView(-1);
        assertEq(0,r_.getHeight());
        assertEq(0,r_.getWidth());
    }
    @Test
    public void t34() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        t_.insert("m",1);
        t_.insert("n",1);
        t_.remove(1,2);
        assertEq("hello",t_.getText());
    }
    @Test
    public void t35() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        t_.select(1,1);
        t_.downAction();
        assertEq(7,t_.getCaretPosition());
    }
    @Test
    public void t36() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        t_.select(7,7);
        t_.upAction();
        assertEq(1,t_.getCaretPosition());
    }
    @Test
    public void t37() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.setSelectionStart(1);
        t_.setSelectionEnd(4);
        t_.select(1,4);
        t_.enterAction();
        assertEq("h\no",t_.getText());
    }
    @Test
    public void t38() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        t_.select(2,2);
        t_.leftAction();
        assertEq(1,t_.getCaretPosition());
    }
    @Test
    public void t39() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello\nworld");
        t_.select(1,1);
        t_.rightAction();
        assertEq(2,t_.getCaretPosition());
    }
    @Test
    public void t40() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.setEnabled(false);
        t_.select(1,4);
        assertEq("hello",t_.getText());
    }
}
