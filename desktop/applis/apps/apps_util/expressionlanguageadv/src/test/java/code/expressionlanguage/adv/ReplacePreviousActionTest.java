package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockCustComponent;
import code.mock.MockFileSet;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class ReplacePreviousActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("hello world");
        tabEditor(w_).getCenter().select(3,4);
        replaceText(w_,pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("L");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("heLLo world", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertTrue(tabEditor(w_).getFinderPanel().isVisible());
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"a");
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("a theme", tabEditor(w_).getCenter().getText());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("these");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("these theme", tabEditor(w_).getCenter().getText());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
    }
    @Test
    public void action5() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("a theme", tabEditor(w_).getCenter().getText());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("a ame", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action6() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("these");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("these theme", tabEditor(w_).getCenter().getText());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("thesese theseme", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action7() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getNextOcc()).getActionListeners().get(0).action();
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("a ame", tabEditor(w_).getCenter().getText());
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
    }
    @Test
    public void action8() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getNextOcc()).getActionListeners().get(0).action();
        tabEditor(w_).getReplacer().setText("these");
        ((MockPlainButton)tabEditor(w_).getReplacePrevious()).getActionListeners().get(0).action();
        assertEq("these theseme", tabEditor(w_).getCenter().getText());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacePrevious()).isDeepAccessible());
    }
}
