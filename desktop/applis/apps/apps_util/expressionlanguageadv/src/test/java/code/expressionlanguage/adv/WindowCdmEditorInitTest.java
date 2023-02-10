package code.expressionlanguage.adv;

import code.gui.AbsGroupFrame;
import code.gui.GuiBaseUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockCustComponent;
import code.mock.MockFileSet;
import code.mock.MockPanel;
import code.mock.MockProgramInfos;
import code.util.IdList;
import org.junit.Test;

public final class WindowCdmEditorInitTest extends EquallableElAdvUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        assertTrue(w_.getCommonFrame().isVisible());
        assertEq(1,w_.getIdes().size());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        assertEq(1,w_.getSpinner().getMin());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        assertEq(1, GuiBaseUtil.getActions(w_.getTabEditor().getCenter()).size());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        assertEq(2,((MockPanel)w_.getPanel()).getAccessible().size());
        assertEq(2,w_.getPanel().getComponentCount());
        assertFalse(((MockCustComponent)w_.getTabEditor().getFinderPanel()).isAccessible());
    }
    @Test
    public void quit1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_, new IdList<WindowCdmEditor>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(w_.getCommonFrame().isVisible());
        assertEq(0,w_.getIdes().size());
    }
    @Test
    public void quit2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AbsGroupFrame w_ =window(pr_, new IdList<WindowCdmEditor>());
        w_.canChangeLanguage();
        w_.getApplicationName();
        w_.changeLanguage("");
        w_.dispatchExit();
        assertFalse(w_.getCommonFrame().isVisible());
    }
}
