package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import org.junit.Test;

public final class FindActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        findText(w_,pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("", tabEditor(w_).getFinder().getText());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0, tabEditor(w_).getParts().size());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }

    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_,pr_);
        ((MockPlainButton) tabEditor(w_).getCloseFinder()).getActionListeners().get(0).action();
        assertFalse(tabEditor(w_).getNavModifPanel().isVisible());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        ((MockPlainButton) tabEditor(w_).getCloseFinder()).getActionListeners().get(0).action();
        tabEditor(w_).getCenter().select(1,2);
        findText(w_, pr_);
        assertEq("e", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(1, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(2, tabEditor(w_).getParts().get(0).getEnd());
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        findText(w_, pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
    }
    @Test
    public void action6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        new FinderTextChange(tabEditor(w_)).insertUpdate();
        invokeAndClear(pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isAccessible());
        assertEq("", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
    }
    @Test
    public void action7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        new FinderTextChange(tabEditor(w_)).changedUpdate();
        invokeAndClear(pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isAccessible());
        assertEq("", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
    }
    @Test
    public void action8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        findNow(pr_, w_, "e");
        assertEq("e", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(1, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(2, tabEditor(w_).getParts().get(0).getEnd());
    }
    @Test
    public void action9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        findNow(pr_, w_, "ll");
        assertEq("ll", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(0).getEnd());
    }
    @Test
    public void action10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "e");
        assertEq("e", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(1, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(2, tabEditor(w_).getParts().get(0).getEnd());
    }
    @Test
    public void action11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "ll");
        assertEq("ll", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(0).getEnd());
    }
    @Test
    public void action12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        findText(w_,pr_);
        findNow(pr_, w_, "i");
        assertEq("i", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
    }
    @Test
    public void action13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(3,4);
        findText(w_, pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helo");
        findText(w_, pr_);
        findNow(pr_,w_,"l");
        tabEditor(w_).getCenter().select(2,2);
        changeNow(pr_,w_,"hello");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(2,2);
        findText(w_, pr_);
        findNow(pr_,w_,"o");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(4, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
        assertEq(4, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(5, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        tabEditor(w_).getCenter().select(8,8);
        findText(w_, pr_);
        findNow(pr_,w_,"o");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(4, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(7, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(8, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(1, tabEditor(w_).getCurrentPart());
        assertEq(7, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        tabEditor(w_).getCenter().select(5,5);
        findText(w_, pr_);
        findNow(pr_,w_,"o");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(4, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(7, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(8, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(1, tabEditor(w_).getCurrentPart());
        assertEq(7, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        tabEditor(w_).getCenter().select(2,2);
        findText(w_, pr_);
        findNow(pr_,w_,"o");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(4, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(7, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(8, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
        assertEq(4, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(5, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        tabEditor(w_).getCaseSens().setSelected(false);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(2, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        findNow(pr_, w_,"ol");
        tabEditor(w_).getCaseSens().setSelected(false);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("ol", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        findNow(pr_, w_,"o");
        tabEditor(w_).getCaseSens().setSelected(false);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(4, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        tabEditor(w_).getCenter().select(2,3);
        findText(w_, pr_);
        tabEditor(w_).getCaseSens().setSelected(false);
        tabEditor(w_).getCaseSens().setSelected(true);
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(false);
        findNow(pr_, w_,"O");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("O", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action24() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(false);
        findNow(pr_, w_,"H");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("H", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action25() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(false);
        findNow(pr_, w_,"WORLD");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("WORLD", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(6, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(11, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action26() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(false);
        findNow(pr_, w_,"HELLO");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("HELLO", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(0, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action27() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"o");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("o", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action28() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("helLo");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"h");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("h", tabEditor(w_).getFinder().getText());
        assertEq(5,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action29() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"world");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("world", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(6, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(11, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action30() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"hello");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("hello", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(0, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(5, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action31() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("a_b");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"a_b");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("a_b", tabEditor(w_).getFinder().getText());
        assertEq(3,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1, tabEditor(w_).getParts().size());
        assertEq(0, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
    }
    @Test
    public void action32() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText(">");
        findText(w_, pr_);
        tabEditor(w_).getWholeWord().setSelected(true);
        tabEditor(w_).getCaseSens().setSelected(true);
        findNow(pr_, w_,"<");
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        assertEq("<", tabEditor(w_).getFinder().getText());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0, tabEditor(w_).getParts().size());
        assertEq(-1, tabEditor(w_).getCurrentPart());
    }
}
