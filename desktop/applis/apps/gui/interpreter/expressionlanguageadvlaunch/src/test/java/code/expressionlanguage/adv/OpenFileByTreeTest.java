package code.expressionlanguage.adv;

import code.gui.GuiConstants;
import code.gui.TextAnswerValue;
import org.junit.Test;

public final class OpenFileByTreeTest extends EquallableElAdvUtil {
    @Test
    public void append1() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        closeTab(w_);
        closeTab(w_);
        assertEq(0,w_.getTabs().size());
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        assertEq(1,w_.getTabs().size());
        assertEq("/project/sources/src/file.txt",tabEditor(w_,0).getFullPath());
        assertEq(0,w_.getEditors().getSelectedIndex());
    }
    @Test
    public void append2() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        closeTab(w_);
        closeTab(w_);
        assertEq(0,w_.getTabs().size());
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        assertEq(2,w_.getTabs().size());
        assertEq("/project/sources/src/file2.txt",tabEditor(w_,1).getFullPath());
        assertEq(1,w_.getEditors().getSelectedIndex());
    }
    @Test
    public void review1() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        closeTab(w_);
        closeTab(w_);
        assertEq(0,w_.getTabs().size());
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        assertEq(2,w_.getTabs().size());
        assertEq(0,w_.getEditors().getSelectedIndex());
    }
    @Test
    public void review2() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        closeTab(w_);
        closeTab(w_);
        assertEq(0,w_.getTabs().size());
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getFolderSystem().getTreeSelectionListeners().get(0).valueChanged(null);
        assertEq(2,w_.getTabs().size());
        assertEq(1,w_.getEditors().getSelectedIndex());
    }
}
