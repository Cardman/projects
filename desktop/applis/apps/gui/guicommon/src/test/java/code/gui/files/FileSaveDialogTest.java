package code.gui.files;

import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.gui.TextAnswerValue;
import code.gui.events.CrossClosingDialogListEvent;
import code.mock.*;
import code.stream.FileListInfo;
import code.stream.PathsUtil;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileSaveDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void init() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        assertTrue(((MockCustComponent)saver_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent) saver_.getButtons().getComponent(0)).isAccessible());
    }
    @Test
    public void input1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt");
        ((MockAbstractAction)GuiBaseUtil.getAction(saver_.getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void input2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt",FileSaveDialog.getStaticSelectedPath(saver_));
    }
    @Test
    public void input3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("txt2");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt2",FileSaveDialog.getStaticSelectedPath(saver_));
    }
    @Test
    public void input4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("/"+ (char) 31);
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void input5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFileName().setText("");
        MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.isVisible());
    }
    @Test
    public void close() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/tmp",saver_);
        assertTrue(saver_.isVisible());
        new CrossClosingDialogListEvent(saver_.getAbsDialog(), new FileCloseableDialog(saver_)).close();
        assertFalse(saver_.isVisible());
    }
    @Test
    public void initHome() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialogByFrame(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        assertTrue(((MockCustComponent)saver_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent) saver_.getButtons().getComponent(0)).isAccessible());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ClosingFileSample());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new AdvButtonsSavePanel(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ClosingFileSample());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new AdvButtonsSavePanel(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(1);
        c_.getActionListeners().first().action();
        assertEq("",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ClosingFileSample());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ClosingFileSample());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void koCreate1() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        assertTrue(FileSaveDialog.koCreate("",pr_));
    }
    @Test
    public void koCreate2() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("/tmp").mkdirs();
        assertTrue(FileSaveDialog.koCreate("tmp",pr_));
    }
    @Test
    public void koCreate3() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        assertFalse(FileSaveDialog.koCreate("tmp",pr_));
    }
    @Test
    public void createFolder1() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        tryType(saver_.getTypedString(),"");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder2() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        tryType(saver_.getTypedString(),"/");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder3() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFolderSystem().select(null);
        tryType(saver_.getTypedString(),"/");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder4() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        tryType(saver_.getTypedString(),"sub");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(1,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void createFolder5() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFolderSystem().select(null);
        tryType(saver_.getTypedString(),"sub");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(1,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void createFolder6() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(saver_.isVisible());
        saver_.getFolderSystem().select(null);
        tryType(saver_.getTypedString(),"sub");
        tryClick(saver_.getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(2,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertEq("txt",info_.getNames()[1].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void clickRow1() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(((MockCustComponent)saver_.getFileTable()).isDeepAccessible());
        saver_.getFileTable().addSelectInterval(0,0);
        saver_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("txt",saver_.getFileName().getText());
    }
    @Test
    public void clickRow2() {
        MockProgramInfos pr_ = new MockProgramInfos("/home", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveDialog saver_ = new FileSaveDialog(pr_);
        FileSaveDialog.setFileSaveDialog(pr_.getFrameFactory().newDialog(), true, "/home",saver_);
        assertTrue(((MockCustComponent)saver_.getFileTable()).isDeepAccessible());
        saver_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",saver_.getFileName().getText());
    }
}
