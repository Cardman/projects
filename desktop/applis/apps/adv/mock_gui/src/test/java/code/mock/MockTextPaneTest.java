package code.mock;

import code.gui.AbsAttrSet;
import code.gui.AbsTabStops;
import org.junit.Test;

public final class MockTextPaneTest extends EquallableMockGuiUtil {
    @Test
    public void f1() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setFontSize(12);
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0,new MockWithAdvActionSample())),0,0);
        assertEq(1, t_.getActionsMap().size());
    }
    @Test
    public void f2() {
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        MockAdvAbstractAction ac_ = new MockAdvAbstractAction(new MockAdvAction(0, e_));
        ac_.action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void f3() {
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        MockAdvAbstractAction ac_ = new MockAdvAbstractAction(new MockAdvAction(0, e_));
        ac_.setEnabled(false);
        ac_.action(null,null);
        assertFalse(e_.isAct());
    }
    @Test
    public void f4() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setSelectedTextColor(2);
        assertEq(2,t_.getSelectedTextColor());
    }
    @Test
    public void f5() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setSelectionColor(2);
        assertEq(2,t_.getSelectionColor());
    }
    @Test
    public void f6() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setCaretColor(2);
        assertEq(2,t_.getCaretColor());
    }
    @Test
    public void f7() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        AbsAttrSet a_ = i_.getCompoFactory().newAttrSet();
        a_.addFontSize(2);
        a_.addBackground(3);
        a_.addForeground(4);
        a_.addBold(false);
        assertFalse(((MockAttrSet)a_).isBold());
        a_.addBold(true);
        assertTrue(((MockAttrSet)a_).isBold());
        a_.addItalic(false);
        assertFalse(((MockAttrSet)a_).isItalic());
        a_.addItalic(true);
        assertTrue(((MockAttrSet)a_).isItalic());
        a_.addUnderline(false);
        assertFalse(((MockAttrSet)a_).isUnderline());
        a_.addUnderline(true);
        assertTrue(((MockAttrSet)a_).isUnderline());
        t_.setCharacterAttributes(0,-1,a_,true);
        t_.setCharacterAttributes(0,1,a_,false);
        t_.setCharacterAttributes(0,2,a_,true);
        t_.setCharacterAttributes(1,2,a_,false);
        assertEq(1,t_.getAttrSets().getVal("0").size());
        assertEq(2,t_.getAttrSets().getVal("1").size());
        assertEq(2,((MockAttrSet)t_.getAttrSets().getVal("0").get(0)).getFontSize());
        assertEq(3,((MockAttrSet)t_.getAttrSets().getVal("0").get(0)).getBack());
        assertEq(4,((MockAttrSet)t_.getAttrSets().getVal("0").get(0)).getFore());
        assertEq(2,((MockAttrSet)t_.getAttrSets().getVal("1").get(0)).getFontSize());
        assertEq(3,((MockAttrSet)t_.getAttrSets().getVal("1").get(0)).getBack());
        assertEq(4,((MockAttrSet)t_.getAttrSets().getVal("1").get(0)).getFore());
        assertEq(2,((MockAttrSet)t_.getAttrSets().getVal("1").get(1)).getFontSize());
        assertEq(3,((MockAttrSet)t_.getAttrSets().getVal("1").get(1)).getBack());
        assertEq(4,((MockAttrSet)t_.getAttrSets().getVal("1").get(1)).getFore());
    }
    @Test
    public void f8() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        AbsAttrSet a_ = i_.getCompoFactory().newAttrSet();
        a_.addFontSize(2);
        a_.addBackground(3);
        a_.addForeground(4);
        t_.setCharacterAttributes(0,-1,a_,true);
        t_.setCharacterAttributes(0,1,a_,false);
        t_.clearCharacterAttributes(0,-1);
        t_.clearCharacterAttributes(0,2);
        assertEq(0,t_.getAttrSets().getVal("0").size());
        assertEq(0,t_.getAttrSets().getVal("1").size());
    }
    @Test
    public void f9() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        MockAdvCaret l_ = new MockAdvCaret();
        t_.addCaretListener(l_);
        t_.getCaretListeners().get(0).caretUpdate(1,2);
        assertEq(1,l_.getBegin());
        assertEq(2,l_.getEnd());
        t_.removeCaretListener(l_);
        assertEq(0,t_.getCaretListeners().size());
    }
    @Test
    public void f10() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        MockAdvCaret l_ = new MockAdvCaret();
        t_.addCaretListener(l_);
        t_.setText("_");
        t_.select(0,1);
        assertEq(0,l_.getBegin());
        assertEq(1,l_.getEnd());
    }
    @Test
    public void f11() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        AbsAttrSet a_ = i_.getCompoFactory().newAttrSet();
        AbsTabStops ts_ = i_.getCompoFactory().newAbsTabStops(1);
        ts_.setTab(0,i_.getCompoFactory().newAbsTabStop(2));
        assertEq(2,ts_.getTab(0).getValue());
        assertEq(1,ts_.getLength());
        a_.addTabs(ts_);
        t_.setParagraphAttributes(a_);
        assertEq(1,((MockAttrSet)t_.getParagraph()).getTabs().getLength());
    }
    @Test
    public void f12() {
        MockProgramInfosSample i_ = init();
        MockTextPane t_ = (MockTextPane) i_.getCompoFactory().newTextPane();
        t_.setText("hello");
        AbsAttrSet a_ = i_.getCompoFactory().newAttrSet();
        a_.addFontSize(2);
        a_.addBackground(3);
        a_.addForeground(4);
        t_.addAutoComplete(new MockAutoCompleteListener(t_));
        t_.setCharacterAttributes(0,2,a_,false);
        assertEq(5,t_.getSelectionEnd());
        assertEq(1, t_.getAutoCompleteListeners().size());
    }
    @Test
    public void f13() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setFontSize(12);
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0,new MockWithAdvActionSample())),0,0);
        t_.unregisterKeyboardAction(0,0);
        assertEq(0, t_.getActionsMap().size());
    }
    @Test
    public void f14() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setEditable(true);
        t_.append("hello");
        t_.setSelectionStart(1);
        t_.setSelectionEnd(4);
        t_.select(1,4);
        t_.replaceSelection("fmm");
        assertEq("hfmmo",t_.getText());
    }
    @Test
    public void f15() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        t_.setEditable(false);
        t_.append("hello");
        t_.setSelectionStart(1);
        t_.setSelectionEnd(4);
        t_.select(1,4);
        t_.replaceSelection("fmm");
        assertEq("hello",t_.getText());
    }
}
