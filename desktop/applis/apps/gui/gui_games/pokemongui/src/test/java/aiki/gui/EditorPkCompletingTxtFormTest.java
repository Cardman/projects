package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import code.gui.*;
import code.mock.*;
import org.junit.Test;

public final class EditorPkCompletingTxtFormTest extends InitEditorPkForm {
    @Test
    public void auto1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+2");
        g_.getTextPane().setSelectionStart(1);
        g_.getTextPane().setSelectionEnd(1);
        action(g_);
        assertFalse(g_.getWords().containsObj(M_1));
    }
    @Test
    public void auto2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{}");
        g_.getTextPane().setSelectionStart(3);
        g_.getTextPane().setSelectionEnd(3);
        action(g_);
        assertTrue(g_.getWords().containsObj(M_1));
    }
    @Test
    public void auto3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        assertTrue(g_.getWords().containsObj(M_1));
    }
    @Test
    public void auto4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{_}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        assertTrue(g_.getWords().containsObj(M_1));
    }
    @Test
    public void auto5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        down(g_);
        assertEq(1,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{_}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        down(g_);
        assertEq(-1,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("");
        g_.getTextPane().setSelectionStart(0);
        g_.getTextPane().setSelectionEnd(0);
        enter(g_);
        assertEq("\n",g_.getTextPane().getText());
    }
    @Test
    public void auto8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{M}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        enter(g_);
        assertEq("1+{M1}",g_.getTextPane().getText());
    }
    @Test
    public void auto9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{M}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        g_.getElement().select(0);
        g_.getElement().events();
        assertEq("1+{M1}",g_.getTextPane().getText());
    }
    @Test
    public void auto10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        down(g_);
        up(g_);
        assertEq(0,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{_}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        down(g_);
        up(g_);
        assertEq(-1,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        left(g_);
        assertEq(0,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{VA}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        right(g_);
        assertEq(0,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(3);
        g_.getTextPane().setSelectionEnd(3);
        action(g_);
        left(g_);
        assertFalse(g_.getPopupMenu().isVisible());
        ((MockTxtComponent)g_.getTextPane()).getAutoCompleteListeners().get(0).changedUpdate(0,0);
    }
    @Test
    public void auto15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        right(g_);
        assertEq(-1,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        g_.getTextPane().insert("A",4);
        assertEq(0,g_.getElement().getSelectedIndex());
    }
    @Test
    public void auto17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        window(pr_, facade_);
        GeneComponentModelText g_ = new GeneComponentModelText(pr_);
        g_.geneString();
        g_.addComplete(facade_);
        g_.getTextPane().setText("1+{V}");
        g_.getTextPane().setSelectionStart(4);
        g_.getTextPane().setSelectionEnd(4);
        action(g_);
        g_.getTextPane().remove(3,1);
        assertEq(0,g_.getElement().getSelectedIndex());
    }
    private void action(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_SPACE, GuiConstants.CTRL_DOWN_MASK)).action();
    }

    private void down(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_DOWN,0)).action();
    }

    private void up(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_UP,0)).action();
    }

    private void enter(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_ENTER,0)).action();
    }

    private void left(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_LEFT,0)).action();
    }

    private void right(GeneComponentModelText _g) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getTextPane(), GuiConstants.VK_RIGHT,0)).action();
    }
}
