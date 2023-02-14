package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockMenuItem;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class OutputDialogCommentsTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        assertEq(0,o_.getResult().getComments().size());
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        assertEq(1,w_.getComments().size());
        assertEq("\\*",w_.getComments().get(0).getBegin());
        assertEq("*\\",w_.getComments().get(0).getEnd().get(0));
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(1).getBeginArea().setText("\\/");
        o_.getCommentsRows().get(1).getEndArea().setText("/\\");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        assertEq(2,w_.getComments().size());
        assertEq("\\*",w_.getComments().get(0).getBegin());
        assertEq("*\\",w_.getComments().get(0).getEnd().get(0));
        assertEq("\\/",w_.getComments().get(1).getBegin());
        assertEq("/\\",w_.getComments().get(1).getEnd().get(0));
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(1).getBeginArea().setText("\\/");
        o_.getCommentsRows().get(1).getEndArea().setText("/\\");
        o_.getCommentsRows().get(0).getSelectForDelete().setSelected(true);
        ((MockPlainButton)o_.getRem()).getActionListeners().get(0).action();
        assertEq(0,o_.getCommentsRows().get(0).getIndex());
        assertEq("\\/",o_.getCommentsRows().get(0).getBeginArea().getText());
        assertEq("/\\",o_.getCommentsRows().get(0).getEndArea().getText());
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        assertEq(1,w_.getComments().size());
        assertEq("\\/",w_.getComments().get(0).getBegin());
        assertEq("/\\",w_.getComments().get(0).getEnd().get(0));
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(1).getBeginArea().setText("\\/");
        o_.getCommentsRows().get(1).getEndArea().setText("/\\");
        o_.getCommentsRows().get(1).getSelectForDelete().setSelected(true);
        ((MockPlainButton)o_.getRem()).getActionListeners().get(0).action();
        assertEq(0,o_.getCommentsRows().get(0).getIndex());
        assertEq("\\*",o_.getCommentsRows().get(0).getBeginArea().getText());
        assertEq("*\\",o_.getCommentsRows().get(0).getEndArea().getText());
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        assertEq(1,w_.getComments().size());
        assertEq("\\*",w_.getComments().get(0).getBegin());
        assertEq("*\\",w_.getComments().get(0).getEnd().get(0));
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        ChangeCommentsEvent ev2_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev2_.action();
        OutputDialogComments o2_ = ev2_.getOutputDialogComments();
        assertEq(1,o2_.getResult().getComments().size());
        assertEq("\\*",o2_.getResult().getComments().get(0).getBegin());
        assertEq("*\\",o2_.getResult().getComments().get(0).getEnd().get(0));
    }
    @Test
    public void action6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) w_.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        OutputDialogComments o_ = ev_.getOutputDialogComments();
        assertEq(0,o_.getResult().getComments().size());
        ((MockPlainButton)o_.getAdd()).getActionListeners().get(0).action();
        o_.getCommentsRows().get(0).getBeginArea().setText("\\*");
        o_.getCommentsRows().get(0).getEndArea().setText("*\\");
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();
        assertFalse(o_.getResult().getValid().get());
        w_.afterChangingComments(o_.getResult());
        assertEq(0,w_.getComments().size());
    }
}
