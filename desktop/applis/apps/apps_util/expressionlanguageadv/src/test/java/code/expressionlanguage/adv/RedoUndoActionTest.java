package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockCustComponent;
import code.mock.MockFileSet;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class RedoUndoActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_,pr_);
        assertEq(1, w_.getTabEditor().getTexts().size());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_,pr_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_,pr_);
        assertEq(2, w_.getTabEditor().getTexts().size());
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_,pr_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_,pr_);
        assertEq("halo",tabEditor(w_).getCenter().getText());
        undo(w_);
        assertEq("hello",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_,pr_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_,pr_);
        undo(w_);
        redo(w_);
        assertEq("halo",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        tabEditor(w_).getCenter().setText("hello");
        storeEdit(w_,pr_);
        tabEditor(w_).getCenter().setText("halo");
        storeEdit(w_,pr_);
        undo(w_);
        redo(w_);
        clearEdit(w_,pr_);
        assertEq(0, w_.getTabEditor().getTexts().size());
    }
}
