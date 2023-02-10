package code.mock;

import code.gui.AbsAttrSet;
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
}
