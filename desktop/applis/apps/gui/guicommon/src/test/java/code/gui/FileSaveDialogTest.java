package code.gui;

import code.gui.events.CrossClosingDialogListEvent;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileSaveDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void init() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        assertTrue(((MockCustComponent)saver_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent) saver_.getButtons().getComponent(0)).isAccessible());
    }
    @Test
    public void input1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void input2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt",FileSaveDialog.getStaticSelectedPath(saver_));
    }
    @Test
    public void input3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt2");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt2",FileSaveDialog.getStaticSelectedPath(saver_));
    }
    @Test
    public void input4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("?");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void input5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void close() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), true, new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)),"en",true,"","/tmp",saver_);
        assertTrue(saver_.isVisible());
        new CrossClosingDialogListEvent(saver_.getAbsDialog(), new FileCloseableDialog(saver_)).close();
        assertFalse(saver_.isVisible());
    }
}
