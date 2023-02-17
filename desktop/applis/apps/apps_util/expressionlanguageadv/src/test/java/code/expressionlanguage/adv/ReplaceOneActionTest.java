package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import org.junit.Test;

public final class ReplaceOneActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(1,3);
        replaceText(w_,pr_);
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplaceOne()).getActionListeners().get(0).action();
        assertEq("halo", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertTrue(tabEditor(w_).getFinderPanel().isVisible());
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"a");
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_,pr_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(pr_,w_,"el");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplaceOne()).getActionListeners().get(0).action();
        assertEq("halo", tabEditor(w_).getCenter().getText());
    }
}
