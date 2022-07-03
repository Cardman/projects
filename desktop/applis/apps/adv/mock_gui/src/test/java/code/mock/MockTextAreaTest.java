package code.mock;

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
        assertEq("",t_.getSelectedText());
    }
    @Test
    public void t3() {
        MockTextArea t_ = new MockTextArea();
        t_.append("hello");
        t_.select(-1,7);
        assertEq("",t_.getSelectedText());
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
        t_.forceInsert("m",1);
        t_.insert("n",1);
        assertEq("hnmello",t_.getText());
    }
    @Test
    public void t7() {
        MockTextArea t_ = new MockTextArea(16,16);
        t_.setText("hello");
        t_.forceReplaceRange("a",1,2);
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
        t_.viewToModel(null);
        assertEq(16, t_.getCols());
        assertEq(16, t_.getRows());
    }
}
