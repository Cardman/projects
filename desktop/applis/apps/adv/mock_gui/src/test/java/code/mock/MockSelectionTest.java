package code.mock;

import code.expressionlanguage.structs.NullStruct;
import code.gui.AbsGraphicList;
import code.gui.AbsGraphicListDef;
import code.util.Ints;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class MockSelectionTest extends EquallableMockGuiUtil {
    @Test
    public void m1() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().simpleSelectItem(1);
        assertEq("1",f_.getText());
        assertEq("1",f_.getGraphicComboGrInt().getSelectedItem());
        assertEq(1,f_.getGraphicComboGrInt().getSelectedIndexes().size());
    }
    @Test
    public void m2() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().simpleSelectItem(-1);
        assertEq("-1",f_.getText());
        assertNull(f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m3() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().addItem("12");
        assertEq("",f_.getText());
        assertEq("1",f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m4() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().removeItem(1);
        assertEq("1",f_.getText());
        assertEq("2",f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m5() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().removeItem(0);
        assertEq("",f_.getText());
        assertEq("2",f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m6() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().selectItem(3);
        f_.getGraphicComboGrInt().simpleRemoveItem(3);
        assertEq("2",f_.getText());
        assertEq("2",f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m7() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().simpleRemoveAllItems();
        assertEq("-1",f_.getText());
        assertNull(f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m8() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getGraphicComboGrInt().removeAllItems();
        f_.getGraphicComboGrInt().addItem("12");
        assertEq("0",f_.getText());
        assertEq("12",f_.getGraphicComboGrInt().getSelectedItem());
    }
    @Test
    public void m9() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        assertFalse(((MockComboBox )f_.getGraphicComboGrInt()).isPopuped());
        f_.getGraphicComboGrInt().popup();
        assertTrue(((MockComboBox )f_.getGraphicComboGrInt()).isPopuped());
        f_.getGraphicComboGrInt().setListener(f_.getGraphicComboGrInt().getListeners().first());
        f_.getGraphicComboGrInt().removeListener(f_.getGraphicComboGrInt().getListeners().first());
        assertEq(0,f_.getGraphicComboGrInt().getListeners().size());
        assertSame(f_.getGraphicComboGrInt().getCurrentSelected(),f_.getGraphicComboGrInt().self());
    }
    @Test
    public void m10() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        ((AbsGraphicListDef) graphicListString_).setSelectedIndexes(MockWithListSelectionSample.wrap(NumberUtil.wrapIntArray(2,3,5)));
        assertEq("2,3",f_.getText());
        Ints inds_ = ((AbsGraphicListDef) graphicListString_).getSelectedIndexes();
        assertEq(2, inds_.size());
        assertEq(2, inds_.get(0));
        assertEq(3, inds_.get(1));
        assertEq(2, graphicListString_.getSelectedValuesLsLen());
        assertEq(4, graphicListString_.size());
        assertFalse(graphicListString_.isSelectionEmpty());
    }
    @Test
    public void m11() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        ((AbsGraphicListDef)f_.getGraphicListString()).setSelectedIndice(2);
        assertEq("2",f_.getText());
        assertEq(2, ((AbsGraphicListDef) f_.getGraphicListString()).getSelectedIndex());
    }
    @Test
    public void m12() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        ((AbsGraphicListDef)f_.getGraphicListString()).setSelectedIndice(-1);
        assertEq("",f_.getText());
        assertEq(-1, ((AbsGraphicListDef) f_.getGraphicListString()).getSelectedIndex());
    }
    @Test
    public void m13() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.add(0,"-1");
        assertEq(5, graphicListString_.size());
    }
    @Test
    public void m14() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.add(0,null,"-1");
        assertEq(5, graphicListString_.size());
    }
    @Test
    public void m15() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.set(0,"-1");
        assertEq(4, graphicListString_.size());
    }
    @Test
    public void m16() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.set(0,null,"-1");
        assertEq(4, graphicListString_.size());
    }
    @Test
    public void m17() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.clearRevalidate();
        assertTrue(graphicListString_.isEmpty());
    }
    @Test
    public void m18() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.clearSelection();
        assertTrue(graphicListString_.isSelectionEmpty());
        assertFalse(graphicListString_.isEmpty());
    }
    @Test
    public void m19() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.remove(0);
        assertEq(3, graphicListString_.size());
        assertEq("5", graphicListString_.get(0));
        assertEq("7", graphicListString_.last());
        assertEq("7", graphicListString_.getList().last());
    }
    @Test
    public void m20() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getGraphicListString();
        graphicListString_.setVisibleRowCount(4);
        assertEq(4, ((AbsGraphicListDef)graphicListString_).getVisibleRowCount());
        assertSame(graphicListString_.self(), graphicListString_.visible());
        ((AbsGraphicListDef) graphicListString_).removeListener(((AbsGraphicListDef) graphicListString_).getListeners().first());
        assertEq(0, ((AbsGraphicListDef) graphicListString_).getListeners().size());
    }
    @Test
    public void m21() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        MockCustGrMultList graphicListString_ = f_.getInput();
        graphicListString_.setSelectedIndice(2);
        assertEq("2",f_.getText());
        Ints inds_ = graphicListString_.getSelectedIndexes();
        assertEq(1, inds_.size());
        assertEq(2, inds_.get(0));
        assertEq(1, graphicListString_.getSelectedValuesLsLen());
        assertEq(4, graphicListString_.size());
        assertFalse(graphicListString_.isSelectionEmpty());
    }
    @Test
    public void m22() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getInput().setSelectedIndice(2);
        assertEq("2",f_.getText());
        assertEq(2, f_.getInput().getSelectedIndex());
    }
    @Test
    public void m23() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getInput().setSelectedIndice(-1);
        assertEq("",f_.getText());
        assertEq(-1, f_.getInput().getSelectedIndex());
    }
    @Test
    public void m24() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.add(0,"-1");
        assertEq(5, graphicListString_.size());
    }
    @Test
    public void m25() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.add(0,null,"-1");
        assertEq(5, graphicListString_.size());
    }
    @Test
    public void m26() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.set(0,"-1");
        assertEq(4, graphicListString_.size());
    }
    @Test
    public void m27() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.set(0,null,"-1");
        assertEq(4, graphicListString_.size());
    }
    @Test
    public void m28() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.clearRevalidate();
        assertTrue(graphicListString_.isEmpty());
    }
    @Test
    public void m29() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.clearSelection();
        assertTrue(graphicListString_.isSelectionEmpty());
        assertFalse(graphicListString_.isEmpty());
    }
    @Test
    public void m30() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.remove(0);
        assertEq(3, graphicListString_.size());
        assertEq("9", graphicListString_.get(0));
        assertEq("11", graphicListString_.last());
        assertEq("11", graphicListString_.getList().last());
    }
    @Test
    public void m31() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        MockCustGrMultList graphicListString_ = f_.getInput();
        graphicListString_.setVisibleRowCount(4);
        assertSame(graphicListString_.scroll(), graphicListString_.visible());
        assertSame(graphicListString_.self(), graphicListString_.getGlobal());
    }
    @Test
    public void m32() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.clear();
        assertTrue(graphicListString_.isEmpty());
    }
    @Test
    public void m33() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        AbsGraphicList<String> graphicListString_ = f_.getInput();
        graphicListString_.clearAllRange();
        assertTrue(graphicListString_.isSelectionEmpty());
        assertFalse(graphicListString_.isEmpty());
    }
    @Test
    public void m34() {
        MockWithListSelectionSample f_ = new MockWithListSelectionSample(init(), "");
        f_.getMultList().add(NullStruct.NULL_VALUE);
        f_.getMultList().setSelectedIndice(0);
        assertEq("0",f_.getText());
        f_.getMultList().setCustCell(null,null,null,null);
        f_.getMultList().setDefCell(null,null);
        f_.getMultList().updateGraphics();
        assertTrue(f_.getMultList().isCust());
        assertFalse(new MockWithListSelectionSample(initDef(), "").getMultList().isCust());
    }
}
