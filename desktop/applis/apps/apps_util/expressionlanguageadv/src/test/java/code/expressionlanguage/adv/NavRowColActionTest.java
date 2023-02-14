package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class NavRowColActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(1);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(2);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(6,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(6,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(2);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(7,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(7,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(1);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(4,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(4,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(3);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getResult().getValid().get());
    }
    @Test
    public void action6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(1);
        o_.getCol().setValue(6);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getResult().getValid().get());
    }
    @Test
    public void action7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(2);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(10,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(10,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(2);
        o_.getCol().setValue(6);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getResult().getValid().get());
    }
    @Test
    public void action9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(1);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        tabEditor(w_).goToRowCol(o_.getResult());
        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello\nworld");
        NavRowColAction ev_ = tabEditor(w_).getNavRowCol();
        ev_.action();
        OutputDialogNavLine o_ = ev_.getOutputDialogNavLine();
        assertEq(-1,o_.getResult().getIndex());
        o_.getRow().setValue(1);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();
        assertFalse(o_.getResult().getValid().get());
    }
}
