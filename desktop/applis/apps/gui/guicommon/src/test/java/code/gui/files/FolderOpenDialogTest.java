package code.gui.files;

import code.gui.AbsButton;
import code.gui.TextAnswerValue;
import code.mock.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FolderOpenDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void select1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(false,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        tryClick((AbsButton) open_.getButtons().getComponent(0));
        assertTrue(open_.isVisible());
    }
    @Test
    public void select2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(true,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderSystem()).isDeepAccessible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        tryClick((AbsButton) open_.getButtons().getComponent(0));
        assertFalse(open_.isVisible());
        assertEq("/",FolderOpenDialog.getStaticSelectedPath(open_));
    }

    @Test
    public void select3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("/tmp/").mkdirs();
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(true,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderSystem()).isDeepAccessible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild());
        tryClick((AbsButton) open_.getButtons().getComponent(0));
        assertFalse(open_.isVisible());
        assertEq("/tmp/",FolderOpenDialog.getStaticSelectedPath(open_));
    }
    @Test
    public void select4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("sub").mkdirs();
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(true,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderSystem()).isDeepAccessible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild());
        pr_.getFileCoreStream().newFile("sub").delete();
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild().getFirstChild());
        assertEq(0,open_.getFolderSystem().getRoot().getFirstChild().getChildCount());
    }
    @Test
    public void cancel() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(false,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        tryClick((AbsButton) open_.getButtons().getComponent(1));
        assertFalse(open_.isVisible());
        assertEq("",FolderOpenDialog.getStaticSelectedPath(open_));
    }
    @Test
    public void clickRow() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        pr_.setCurrentPath("/");
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog(true,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild());
//        open_.getFileTable().setRowCount(1);
        open_.getFileTable().addSelectInterval(0,0);
        open_.getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",open_.getFileName().getText());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FolderOpenFrame open_ = save(pr_);
        FolderOpenFrame.setFolderOpenDialog(true,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FolderOpenFrame open_ = save(pr_);
        FolderOpenFrame.setFolderOpenDialog(false,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{0}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        updateFolderOpen(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileDialog.GUI).getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame saver_ = save(pr_);
        FolderOpenFrame.setFolderOpenDialog(true, saver_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFolderOpenDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFolderOpenDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }

    private FolderOpenFrame save(MockProgramInfos _pr) {
        return new FolderOpenFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }
}
