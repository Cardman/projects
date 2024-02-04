package code.gui.files;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.gui.TextAnswerValue;
import code.mock.MockAbstractAction;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileOpenSaveFrameTest extends EquallableGuiCommonUtil {
    @Test
    public void input1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = new FileOpenSaveFrame(pr_,new ClosingFileSample());
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueLoadFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().getFileName().setText("txt");
        ((MockAbstractAction) GuiBaseUtil.getAction(saver_.getFileSaveDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = new FileOpenSaveFrame(pr_,new ClosingFileSample());
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueLoadFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().clickRow();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = new FileOpenSaveFrame(pr_,new ClosingFileSample());
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueLoadFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileOpenDialogContent().getFileName().setText("txt");
        AbsButton c_ = saver_.getMainAction();
        c_.getActionListeners().first().action();
        assertEq("",saver_.getFileSaveDialogContent().getSelectedAbsolutePath());
        assertEq("/tmp/txt",saver_.getFileOpenDialogContent().getSelectedAbsolutePath());
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void input4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenSaveFrame saver_ = new FileOpenSaveFrame(pr_,new ClosingFileSample());
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueLoadFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().getFileName().setText("txt1");
        saver_.getFileOpenDialogContent().getFileName().setText("txt2");
        AbsButton c_ = saver_.getMainAction();
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt1",saver_.getFileSaveDialogContent().getSelectedAbsolutePath());
        assertEq("/tmp/txt2",saver_.getFileOpenDialogContent().getSelectedAbsolutePath());
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void input5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenSaveFrame saver_ = new FileOpenSaveFrame(pr_,new ClosingFileSample());
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueLoadFileSample());
        assertTrue(saver_.getFrame().isVisible());
        AbsButton c_ = saver_.getCloseAction();
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
    /* MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt",FileSaveDialog.getStaticSelectedPath(saver_));*/
}
