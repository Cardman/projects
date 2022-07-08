package code.gui;

import code.mock.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ConfirmDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void yesNo1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",GuiConstants.YES_NO_OPTION,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNo2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",GuiConstants.YES_NO_OPTION,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(2);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",GuiConstants.YES_NO_CANCEL_OPTION,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.YES_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",GuiConstants.YES_NO_CANCEL_OPTION,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(2);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.NO_OPTION,conf_.getAnswer());
    }
    @Test
    public void yesNoCancel3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",GuiConstants.YES_NO_CANCEL_OPTION,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(3);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.CANCEL_OPTION,conf_.getAnswer());
    }
    @Test
    public void ok() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMiniDialog("","","en",2,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq(GuiConstants.OK_OPTION,conf_.getAnswer());
    }
    @Test
    public void showTextField1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showTextField("","","","en",conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(2);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(3);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(0);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq("_",conf_.textValue().getTypedText());
        assertEq(GuiConstants.YES_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showTextField2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showTextField("","","","en",conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockTextField field_ = (MockTextField) pane_.getComponent(2);
        field_.setText("_");
        MockPanel buttons_ = (MockPanel) pane_.getComponent(3);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
        assertEq("",conf_.textValue().getTypedText());
        assertEq(GuiConstants.NO_OPTION,conf_.textValue().getAnswer());
    }
    @Test
    public void showMessage1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMessage("","","en",GuiConstants.ERROR_MESSAGE,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
    }
    @Test
    public void showMessage2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMessage("","","en",GuiConstants.INFORMATION_MESSAGE,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
    }
    @Test
    public void showMessage3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new double[0], new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        ConfirmDialog conf_ = new ConfirmDialog(pr_);
        ConfirmDialog.showMessage("","","en",GuiConstants.WARNING_MESSAGE,conf_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        AbsPanel pane_ = conf_.getAbsDialog().getPane();
        MockPanel buttons_ = (MockPanel) pane_.getComponent(1);
        MockPlainButton button_ = (MockPlainButton) buttons_.getComponent(1);
        assertTrue(button_.isAccessible());
        button_.getActionListeners().first().action();
        assertFalse(conf_.getAbsDialog().isVisible());
    }
}
