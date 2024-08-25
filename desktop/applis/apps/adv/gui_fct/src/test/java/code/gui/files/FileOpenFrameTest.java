package code.gui.files;

import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.MockProgramInfosSecSample;
import code.mock.*;
import code.threads.ConcreteBoolean;
import org.junit.Test;

public final class FileOpenFrameTest extends EquallableGuiFctUtil {
    @Test
    public void init1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFileDialogContent().isCurrentFolderRoot());
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFolderSystem().select(open_.getFileDialogContent().getFolderSystem().getRoot());
        assertEq(2,open_.getFileDialogContent().getFolderSystem().selectEvt().getChildCount());
    }
    @Test
    public void init2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("/tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("/tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(false,"/",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertFalse(open_.getFileDialogContent().isCurrentFolderRoot());
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFolderSystem().select(open_.getFileDialogContent().getFolderSystem().getRoot());
        assertEq(1,open_.getFileDialogContent().getFolderSystem().selectEvt().getChildCount());

    }
    @Test
    public void clickRow1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",open_.getFileDialogContent().getFileName().getText());
    }
    @Test
    public void clickRow2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileTable().addSelectInterval(0,0);
        open_.getFileDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("txt1",open_.getFileDialogContent().getFileName().getText());
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedPath());
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void type() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("tex1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileName().setText("tex");
        ((AbsActionListener)GuiBaseUtil.getAction(open_.getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertEq("tex1",open_.getFileDialogContent().getFileName().getText());
    }
    @Test
    public void abs1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileName().setText("/tmp/txt1");
        open_.getFileDialogContent().submit();
        assertEq("/tmp/txt1",open_.getFileDialogContent().getFileName().getText());
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedPath());
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void abs2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileName().setText("tmp1/txt1");
        open_.getFileDialogContent().submit();
        assertEq("tmp1/txt1",open_.getFileDialogContent().getFileName().getText());
        assertEq("/tmp/tmp1/txt1",open_.getFileDialogContent().getSelectedPath());
        assertEq("/tmp/tmp1/txt1",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void abs3() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileName().setText("txt3");
        open_.getFileDialogContent().submit();
        assertEq("",open_.getFileDialogContent().getSelectedPath());
        assertEq("",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void abs4() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getFileTable().addSelectInterval(0,0);
        open_.getFileDialogContent().getFileName().setText("txt3");
        open_.getFileDialogContent().submit();
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedPath());
        assertEq("/tmp/txt1",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void abs5() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        pr_.getFileCoreStream().newFile("/tmp/txt1").delete();
        open_.getFileDialogContent().getFileTable().addSelectInterval(0,0);
        open_.getFileDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        open_.getFileDialogContent().getFileName().setText("txt3");
        open_.getFileDialogContent().submit();
        assertEq("",open_.getFileDialogContent().getSelectedAbsolutePath());
    }
    @Test
    public void search1() {
        MockProgramInfosSecSample pr_ = init();
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
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getTypedString().setText("txt1");
        tryClick(open_.getFileDialogContent().getSearchButton());
        tryAn((MockThreadFactory) pr_.getThreadFactory());
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
    }
    @Test
    public void search2() {
        MockProgramInfosSecSample pr_ = init();
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
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getTypedString().setText("txt1");
        tryClick(open_.getFileDialogContent().getSearchButton());
        open_.getFileDialogContent().setKeepSearching(false);
        tryAn((MockThreadFactory) pr_.getThreadFactory());
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(0,open_.getFileDialogContent().getFileTable().getRowCount());
    }
    @Test
    public void search3() {
        MockProgramInfosSecSample pr_ = init();
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
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getTypedString().setText("txt1");
        tryClick(open_.getFileDialogContent().getSearchButton());
        tryClick(open_.getFileDialogContent().getStop());
        tryAn((MockThreadFactory) pr_.getThreadFactory());
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(0,open_.getFileDialogContent().getFileTable().getRowCount());
    }
    @Test
    public void search4() {
        MockProgramInfosSecSample pr_ = init();
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
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        open_.getFileDialogContent().getTypedString().setText("txt1");
        tryClick(open_.getFileDialogContent().getSearchButton());
        tryAn((MockThreadFactory) pr_.getThreadFactory());
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
//        ((MockCompoFactory)pr_.getCompoFactory()).getLater().clear();
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        open_.getFileDialogContent().getTypedString().setText("txt2");
        tryClick(open_.getFileDialogContent().getSearchButton());
        tryAn((MockThreadFactory) pr_.getThreadFactory());
        //((MockCompoFactory)pr_.getCompoFactory()).invoke();
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = save(pr_);
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenFrame open_ = save(pr_);
        FileOpenFrame.setFileSaveDialogByFrame(false,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
    }
    @Test
    public void inputFrame3() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(MessagesGuiFct.GUI).getMapping().addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(MessagesGuiFct.GUI).getMapping().addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenFrame saver_ = save(pr_);
        FileOpenFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }

    private FileOpenFrame save(MockProgramInfosSecSample _pr) {
        return new FileOpenFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }

    /* MockPlainButton c_ = (MockPlainButton) saver_.getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.isVisible());
        assertEq("/tmp/txt",FileSaveDialog.getStaticSelectedPath(saver_));*/
}
