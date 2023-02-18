package code.expressionlanguage.adv;

import code.mock.*;
import org.junit.Test;

public final class ReplaceOneActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        tabEditor(w_).getCenter().select(1,3);
        replaceText(w_);
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
        WindowCdmEditor w_=newWindowLoadDef();
        assertTrue(tabEditor(w_).getFinderPanel().isVisible());
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(w_,"a");
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        replaceText(w_);
        assertFalse(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getFinder()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getReplacer()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getCloseFinder()).isDeepAccessible());
        findNow(w_,"el");
        assertTrue(((MockCustComponent) tabEditor(w_).getReplaceOne()).isDeepAccessible());
        tabEditor(w_).getReplacer().setText("a");
        ((MockPlainButton)tabEditor(w_).getReplaceOne()).getActionListeners().get(0).action();
        assertEq("halo", tabEditor(w_).getCenter().getText());
    }
}
