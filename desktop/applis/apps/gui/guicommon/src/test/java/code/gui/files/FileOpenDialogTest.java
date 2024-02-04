package code.gui.files;

import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.gui.TextAnswerValue;
import code.gui.events.AbsActionListener;
import code.mock.*;
import code.threads.ConcreteBoolean;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileOpenDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isCurrentFolderRoot());
        assertTrue(open_.isVisible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        assertEq(2,open_.getFolderSystem().selectEvt().getChildCount());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("/tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("/tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(false,"/",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertFalse(open_.isCurrentFolderRoot());
        assertTrue(open_.isVisible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        assertEq(1,open_.getFolderSystem().selectEvt().getChildCount());

    }
    @Test
    public void clickRow1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",open_.getFileName().getText());
    }
    @Test
    public void clickRow2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileTable().addSelectInterval(0,0);
        open_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("txt1",open_.getFileName().getText());
        assertEq("/tmp/txt1",open_.getSelectedPath());
        assertEq("/tmp/txt1",open_.getSelectedAbsolutePath());
    }
    @Test
    public void type() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("tex1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileName().setText("tex");
        ((AbsActionListener)GuiBaseUtil.getAction(open_.getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertEq("tex1",open_.getFileName().getText());
    }
    @Test
    public void abs1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileName().setText("/tmp/txt1");
        open_.submit();
        assertEq("/tmp/txt1",open_.getFileName().getText());
        assertEq("/tmp/txt1",open_.getSelectedPath());
        assertEq("/tmp/txt1",open_.getSelectedAbsolutePath());
    }
    @Test
    public void abs2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileName().setText("tmp1/txt1");
        open_.submit();
        assertEq("tmp1/txt1",open_.getFileName().getText());
        assertEq("/tmp/tmp1/txt1",open_.getSelectedPath());
        assertEq("/tmp/tmp1/txt1",open_.getSelectedAbsolutePath());
    }
    @Test
    public void abs3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileName().setText("txt3");
        open_.submit();
        assertEq("",open_.getSelectedPath());
        assertEq("",open_.getSelectedAbsolutePath());
    }
    @Test
    public void abs4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFileTable().addSelectInterval(0,0);
        open_.getFileName().setText("txt3");
        open_.submit();
        assertEq("/tmp/txt1",open_.getSelectedPath());
        assertEq("/tmp/txt1",open_.getSelectedAbsolutePath());
    }
    @Test
    public void abs5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        pr_.getFileCoreStream().newFile("/tmp/txt1").delete();
        open_.getFileTable().addSelectInterval(0,0);
        open_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        open_.getFileName().setText("txt3");
        open_.submit();
        assertEq("",FileOpenDialog.getStaticSelectedPath(open_));
        assertEq("",open_.getSelectedAbsolutePath());
    }
    @Test
    public void search1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        open_.getThread().join();
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(2,open_.getFileTable().getRowCount());
    }
    @Test
    public void search2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        open_.setKeepSearching(false);
        open_.getThread().join();
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(0,open_.getFileTable().getRowCount());
    }
    @Test
    public void search3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        tryClick(open_.getStop());
        open_.getThread().join();
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(0,open_.getFileTable().getRowCount());
    }
    @Test
    public void search4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog(true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        open_.getThread().join();
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        ((MockCompoFactory)pr_.getCompoFactory()).getLater().clear();
        assertEq(2,open_.getFileTable().getRowCount());
        open_.getTypedString().setText("txt2");
        tryClick(open_.getSearchButton());
        open_.getThread().join();
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(2,open_.getFileTable().getRowCount());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ClosingFileSample());
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueLoadFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ClosingFileSample());
        FileOpenFrame.setFileSaveDialogByFrame(false,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueLoadFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFileOpen(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenFrame saver_ = new FileOpenFrame(pr_,new ClosingFileSample());
        FileOpenFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsOpenPanelAct(new ContinueLoadFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
}
