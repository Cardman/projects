package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import org.junit.Test;

public final class FindActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        findText(w_,pr_);
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("",w_.getTabEditor().getFinder().getText());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0, w_.getTabEditor().getParts().size());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(2,3);
        findText(w_, pr_);
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("l",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, w_.getTabEditor().getParts().size());
        assertEq(2, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(3, w_.getTabEditor().getParts().get(0).getEnd());
        assertEq(3, w_.getTabEditor().getParts().get(1).getBegin());
        assertEq(4, w_.getTabEditor().getParts().get(1).getEnd());
        assertEq(0, w_.getTabEditor().getCurrentPart());
    }

    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(2,3);
        findText(w_,pr_);
        ((MockPlainButton)w_.getTabEditor().getCloseFinder()).getActionListeners().get(0).action();
        assertFalse(w_.getTabEditor().getFinderPanel().isVisible());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(2,3);
        findText(w_, pr_);
        ((MockPlainButton)w_.getTabEditor().getCloseFinder()).getActionListeners().get(0).action();
        w_.getTabEditor().getCenter().select(1,2);
        findText(w_, pr_);
        assertEq("e",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, w_.getTabEditor().getParts().size());
        assertEq(1, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(2, w_.getTabEditor().getParts().get(0).getEnd());
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        findText(w_, pr_);
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, w_.getTabEditor().getParts().size());
    }
    @Test
    public void action6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        new FinderTextChange(w_).insertUpdate();
        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, w_.getTabEditor().getParts().size());
    }
    @Test
    public void action7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        new FinderTextChange(w_).removeUpdate();
        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, w_.getTabEditor().getParts().size());
    }
    @Test
    public void action8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(2,3);
        findText(w_, pr_);
        findNow(pr_, w_, "e");
        assertEq("e",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, w_.getTabEditor().getParts().size());
        assertEq(1, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(2, w_.getTabEditor().getParts().get(0).getEnd());
    }
    @Test
    public void action9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(2,3);
        findText(w_, pr_);
        findNow(pr_, w_, "ll");
        assertEq("ll",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, w_.getTabEditor().getParts().size());
        assertEq(2, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(4, w_.getTabEditor().getParts().get(0).getEnd());
    }
    @Test
    public void action10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "e");
        assertEq("e",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, w_.getTabEditor().getParts().size());
        assertEq(1, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(2, w_.getTabEditor().getParts().get(0).getEnd());
    }
    @Test
    public void action11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "ll");
        assertEq("ll",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, w_.getTabEditor().getParts().size());
        assertEq(2, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(4, w_.getTabEditor().getParts().get(0).getEnd());
    }
    @Test
    public void action12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "i");
        assertEq("i",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, w_.getTabEditor().getParts().size());
    }
    @Test
    public void action13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("hello");
        w_.getTabEditor().getCenter().select(3,4);
        findText(w_, pr_);
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("l",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, w_.getTabEditor().getParts().size());
        assertEq(2, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(3, w_.getTabEditor().getParts().get(0).getEnd());
        assertEq(3, w_.getTabEditor().getParts().get(1).getBegin());
        assertEq(4, w_.getTabEditor().getParts().get(1).getEnd());
        assertEq(1, w_.getTabEditor().getCurrentPart());
    }
    @Test
    public void action14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getTabEditor().getCenter().setText("helo");
        findText(w_, pr_);
        findNow(pr_,w_,"l");
        w_.getTabEditor().getCenter().select(2,2);
        changeNow(pr_,w_,"hello");
        assertTrue(((MockCustComponent)w_.getTabEditor().getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getTabEditor().getCloseFinder()).isAccessible());
        assertEq("l",w_.getTabEditor().getFinder().getText());
        assertEq(5,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getTabEditor().getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, w_.getTabEditor().getParts().size());
        assertEq(2, w_.getTabEditor().getParts().get(0).getBegin());
        assertEq(3, w_.getTabEditor().getParts().get(0).getEnd());
        assertEq(3, w_.getTabEditor().getParts().get(1).getBegin());
        assertEq(4, w_.getTabEditor().getParts().get(1).getEnd());
        assertEq(0, w_.getTabEditor().getCurrentPart());
    }
    private void findNow(MockProgramInfos _pr, WindowCdmEditor _w, String _v) {
        _w.getTabEditor().getFinder().setText(_v);
        ((MockCompoFactory) _pr.getCompoFactory()).invoke();
    }

    private void changeNow(MockProgramInfos _pr, WindowCdmEditor _w, String _v) {
        _w.getTabEditor().getCenter().setText(_v);
        ((MockCompoFactory) _pr.getCompoFactory()).invoke();
    }
    private void findText(WindowCdmEditor _w, MockProgramInfos _pr) {
        ((MockAbstractAction)GuiBaseUtil.getAction(_w.getTabEditor().getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        ((MockCompoFactory) _pr.getCompoFactory()).invoke();
        ((MockCompoFactory) _pr.getCompoFactory()).getLater().clear();
    }

}
