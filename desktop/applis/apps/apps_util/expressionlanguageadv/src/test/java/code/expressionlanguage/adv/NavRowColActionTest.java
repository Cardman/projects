package code.expressionlanguage.adv;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockPlainButton;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class NavRowColActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(1);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action2() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(6,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(6,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action3() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(7,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(7,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action4() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(1);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(4,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(4,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action5() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(3);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getVal().isEnabled());
    }
    @Test
    public void action6() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(1);
        o_.getCol().setValue(6);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(5,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(5,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action7() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(10,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(10,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action8() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(6);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());
        assertEq(11,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(11,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action9() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(1);
        o_.getCol().setValue(1);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action10() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(3);
        o_.getCol().setValue(3);
        ((MockPlainButton)o_.getCancel()).getActionListeners().get(0).action();
        assertEq(0,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(0,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action11() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(6);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(8,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action12() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nw\torld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(5);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(8,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(8,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action13() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(9);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(11,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(11,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action14() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(10);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertTrue(o_.getVal().isEnabled());

        assertEq(12,tabEditor(w_).getCenter().getSelectionStart());
        assertEq(12,tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void action15() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\nworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(7);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getVal().isEnabled());
    }
    @Test
    public void action16() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(1);
        o_.getCol().setValue(7);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getVal().isEnabled());
    }
    @Test
    public void action17() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("hello\n\tworld");
        OutputDialogNavLine o_=navigateInsideTab(w_);
        
        o_.getRow().setValue(2);
        o_.getCol().setValue(11);
        ((MockPlainButton)o_.getVal()).getActionListeners().get(0).action();
        assertFalse(o_.getVal().isEnabled());
    }
}
