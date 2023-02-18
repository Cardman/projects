package code.expressionlanguage.adv;

import code.mock.MockCustComponent;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class ReplaceNextActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello world");
        tabEditor(w_).getCenter().select(3,4);
        replaceText(w_);
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("L");
        ((MockPlainButton)tabEditor(w_).getReplaceNext()).getActionListeners().get(0).action();
        assertEq("helLo worLd", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        assertTrue(tabEditor(w_).getFinderPanel().isVisible());
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(w_,"a");
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplaceNext()).getActionListeners().get(0).action();
        assertEq("a ame", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("the theme");
        replaceText(w_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(w_,"the");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceNext()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("these");
        ((MockPlainButton)tabEditor(w_).getReplaceNext()).getActionListeners().get(0).action();
        assertEq("these theseme", tabEditor(w_).getCenter().getText());
    }
}
