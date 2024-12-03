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
        ((MockAbstractAction) GuiBaseUtil.getAction(g_.getTextPane(), GuiConstants.VK_SPACE, GuiConstants.CTRL_DOWN_MASK)).action();
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
        ((MockAbstractAction) GuiBaseUtil.getAction(g_.getTextPane(), GuiConstants.VK_SPACE, GuiConstants.CTRL_DOWN_MASK)).action();
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
        ((MockAbstractAction) GuiBaseUtil.getAction(g_.getTextPane(), GuiConstants.VK_SPACE, GuiConstants.CTRL_DOWN_MASK)).action();
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
        ((MockAbstractAction) GuiBaseUtil.getAction(g_.getTextPane(), GuiConstants.VK_SPACE, GuiConstants.CTRL_DOWN_MASK)).action();
        assertTrue(g_.getWords().containsObj(M_1));
    }
}
