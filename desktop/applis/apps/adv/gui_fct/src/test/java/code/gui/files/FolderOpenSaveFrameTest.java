package code.gui.files;

import code.gui.*;
import code.gui.events.MockProgramInfosSecSample;
import code.mock.*;
import code.threads.ConcreteBoolean;
import org.junit.Test;

public final class FolderOpenSaveFrameTest extends EquallableGuiFctUtil {
    @Test
    public void input1() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenSaveFrame saver_ = saver(pr_);
        FolderOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().getFileName().setText("txt");
        ((MockAbstractAction) GuiBaseUtil.getAction(saver_.getFileSaveDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input2() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenSaveFrame saver_ = saver(pr_);
        FolderOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().clickRow();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input3() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenSaveFrame saver_ = saver(pr_);
        FolderOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFolderOpenDialogContent().getFileName().setText("txt");
        AbsButton c_ = saver_.getMainAction();
        c_.getActionListeners().first().action();
        assertEq("",saver_.getFileSaveDialogContent().getSelectedAbsolutePath());
//        assertEq("/tmp/txt",saver_.getFolderOpenDialogContent().getSelectedAbsolutePath());
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void input4() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FolderOpenSaveFrame saver_ = saver(pr_);
        FolderOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().getFileName().setText("txt1");
//        saver_.getFolderOpenDialogContent().getFileName().setText("txt2");
        AbsButton c_ = saver_.getMainAction();
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt1",saver_.getFileSaveDialogContent().getSelectedAbsolutePath());
//        assertEq("/tmp/txt2",saver_.getFolderOpenDialogContent().getSelectedAbsolutePath());
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void input5() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FolderOpenSaveFrame saver_ = saver(pr_);
        FolderOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        AbsButton c_ = saver_.getCloseAction();
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfosSecSample pr_ = init();
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
        MockProgramInfosSecSample pr_ = init();
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
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.fr());
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
    @Test
    public void select1() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(false,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        assertFalse(((MockCustComponent)open_.getFolderOpenDialogContent().getFileName()).isAccessible());
        tryClick((AbsButton) open_.getFolderOpenDialogContent().getButtons().getComponent(0));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void select2() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(true,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        assertFalse(((MockCustComponent)open_.getFolderOpenDialogContent().getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderOpenDialogContent().getFolderSystem()).isDeepAccessible());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot());
        tryClick((AbsButton) open_.getFolderOpenDialogContent().getButtons().getComponent(0));
        assertFalse(open_.getFrame().isVisible());
    }

    @Test
    public void select3() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("/tmp/").mkdirs();
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(true,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        assertFalse(((MockCustComponent)open_.getFolderOpenDialogContent().getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderOpenDialogContent().getFolderSystem()).isDeepAccessible());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot().getFirstChild());
        tryClick((AbsButton) open_.getFolderOpenDialogContent().getButtons().getComponent(0));
        assertFalse(open_.getFrame().isVisible());
    }
    @Test
    public void select4() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("sub").mkdirs();
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(true,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        assertFalse(((MockCustComponent)open_.getFolderOpenDialogContent().getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderOpenDialogContent().getFolderSystem()).isDeepAccessible());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot().getFirstChild());
        pr_.getFileCoreStream().newFile("sub").delete();
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot().getFirstChild().getFirstChild());
        assertEq(0,open_.getFolderOpenDialogContent().getFolderSystem().getRoot().getFirstChild().getChildCount());
    }
    @Test
    public void cancel() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(false,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        tryClick((AbsButton) open_.getFolderOpenDialogContent().getButtons().getComponent(1));
        assertFalse(open_.getFrame().isVisible());
    }
    @Test
    public void clickRow() {
        MockProgramInfosSecSample pr_ = init();
        updateFolderOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        pr_.setCurrentPath("/");
        FolderOpenFrame open_ = new FolderOpenFrame(pr_,new ConcreteBoolean());
        FolderOpenFrame.setFolderOpenDialog(true,open_,new DefButtonsOpenFolderPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot());
        open_.getFolderOpenDialogContent().getFolderSystem().select(open_.getFolderOpenDialogContent().getFolderSystem().getRoot().getFirstChild());
//        open_.getFileTable().setRowCount(1);
        open_.getFolderOpenDialogContent().getFileTable().addSelectInterval(0,0);
        open_.getFolderOpenDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",open_.getFolderOpenDialogContent().getFileName().getText());
    }
    private FolderOpenFrame save(MockProgramInfosSecSample _pr) {
        return new FolderOpenFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }
    private FolderOpenSaveFrame saver(MockProgramInfosSecSample _pr) {
        return new FolderOpenSaveFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }
    /* MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt",FileSaveDialog.getStaticSelectedPath(saver_));*/
}
