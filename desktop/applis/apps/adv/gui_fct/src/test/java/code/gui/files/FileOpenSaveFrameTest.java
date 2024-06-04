package code.gui.files;

import code.gui.*;
import code.gui.events.MockProgramInfosSecSample;
import code.mock.*;
import code.stream.FileListInfo;
import code.stream.PathsUtil;
import code.threads.ConcreteBoolean;
import org.junit.Test;

public final class FileOpenSaveFrameTest extends EquallableGuiFctUtil {
    @Test
    public void initSec() {
        MockProgramInfosSecSample pr_ = initHomePath();
        update(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame( true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        assertTrue(((MockCustComponent)saver_.getFileDialogContent().getFileName()).isAccessible());
        assertTrue(((MockCustComponent) saver_.getFileDialogContent().getButtons().getComponent(0)).isAccessible());
    }
    @Test
    public void inputSec1() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame( true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        ((MockAbstractAction)GuiBaseUtil.getAction(saver_.getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void inputSec2() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame( true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void inputSec3() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame( true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt2");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void inputSec4() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame( true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("/"+ (char) 31);
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void inputSec5() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = saver(pr_);
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().getFileName().setText("txt");
        ((MockAbstractAction) GuiBaseUtil.getAction(saver_.getFileSaveDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = saver(pr_);
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileSaveDialogContent().clickRow();
        assertTrue(saver_.getFrame().isVisible());
    }
    @Test
    public void input3() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileOpenSaveFrame saver_ = saver(pr_);
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
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
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenSaveFrame saver_ = saver(pr_);
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
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
        MockProgramInfosSecSample pr_ = init();
        updateFileOpenSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenSaveFrame saver_ = saver(pr_);
        FileOpenSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new SaveFileSample(),new ContinueFileSample());
        assertTrue(saver_.getFrame().isVisible());
        AbsButton c_ = saver_.getCloseAction();
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void inputFrame1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = saverFrame(pr_);
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new AdvButtonsSavePanel(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = saverFrame(pr_);
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new AdvButtonsSavePanel(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(1);
        c_.getActionListeners().first().action();
        assertEq("",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame3() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = saverFrame(pr_);
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFileName().setText("txt");
        MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertEq("/tmp/txt",saver_.getFrame().getTitle());
    }
    @Test
    public void inputFrame4() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getTranslations().getMapping().getVal("en").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.en());
        pr_.getTranslations().getMapping().getVal("fr").getMapping().getVal(FileFrame.GUI).getMapping().addEntry(FileFrame.CONFIRM,MessagesConfirmDialog.fr());
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = saverFrame(pr_);
        FileSaveFrame.setFileSaveDialogByFrame(true, "/tmp",saver_,new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(saver_.getFrame().isVisible());
    }
    @Test
    public void koCreate1() {
        MockProgramInfosSecSample pr_ = init();
        assertTrue(FileSaveDialogContent.koCreate("",pr_));
    }
    @Test
    public void koCreate2() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getFileCoreStream().newFile("/tmp").mkdirs();
        assertTrue(FileSaveDialogContent.koCreate("tmp",pr_));
    }
    @Test
    public void koCreate3() {
        MockProgramInfosSecSample pr_ = init();
        assertFalse(FileSaveDialogContent.koCreate("tmp",pr_));
    }
    @Test
    public void initHome() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        assertTrue(((MockCustComponent)saver_.getFileDialogContent().getFileName()).isAccessible());
        assertTrue(((MockCustComponent) saver_.getFileDialogContent().getButtons().getComponent(0)).isAccessible());
    }
    @Test
    public void createFolder1() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        tryType(saver_.getFileDialogContent().getTypedString(),"");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder2() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        tryType(saver_.getFileDialogContent().getTypedString(),"/");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder3() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFolderSystem().select(null);
        tryType(saver_.getFileDialogContent().getTypedString(),"/");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(0,info_.getNames().length);
    }
    @Test
    public void createFolder4() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        tryType(saver_.getFileDialogContent().getTypedString(),"sub");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(1,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void createFolder5() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFolderSystem().select(null);
        tryType(saver_.getFileDialogContent().getTypedString(),"sub");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(1,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void createFolder6() {
        MockProgramInfosSecSample pr_ = initHomePath();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(saver_.getFrame().isVisible());
        saver_.getFileDialogContent().getFolderSystem().select(null);
        tryType(saver_.getFileDialogContent().getTypedString(),"sub");
        tryClick(saver_.getFileDialogContent().getSearch());
        FileListInfo info_ = PathsUtil.abs(pr_.getFileCoreStream().newFile("/home/"), pr_.getFileCoreStream());
        assertFalse(info_.isNul());
        assertEq(2,info_.getNames().length);
        assertEq("sub",info_.getNames()[0].getName());
        assertEq("txt",info_.getNames()[1].getName());
        assertTrue(pr_.getFileCoreStream().newFile("/home/sub/").isDirectory());
    }
    @Test
    public void clickRow1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(((MockCustComponent)saver_.getFileDialogContent().getFileTable()).isDeepAccessible());
        saver_.getFileDialogContent().getFileTable().addSelectInterval(0,0);
        saver_.getFileDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("txt",saver_.getFileDialogContent().getFileName().getText());
    }
    @Test
    public void clickRow2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileSave(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.getFileCoreStream().newFile("home").mkdirs();
        pr_.setCurrentPath("/home");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FileSaveFrame saver_ = new FileSaveFrame(pr_,new ConcreteBoolean());
        FileSaveFrame.setFileSaveDialogByFrame(true, "/home",saver_, new DefButtonsSavePanelAct(new SaveFileSample(),new ContinueFileSample()));
        assertTrue(((MockCustComponent)saver_.getFileDialogContent().getFileTable()).isDeepAccessible());
        saver_.getFileDialogContent().getFileTable().getListSelectionListeners().get(0).valueChanged(0,0);
        assertEq("",saver_.getFileDialogContent().getFileName().getText());
    }
    private FileOpenSaveFrame saver(MockProgramInfosSecSample _pr) {
        return new FileOpenSaveFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }

    private FileSaveFrame saverFrame(MockProgramInfosSecSample _pr) {
        return new FileSaveFrame(_pr, _pr.getThreadFactory().newAtomicBoolean());
    }
    /* MockPlainButton c_ = (MockPlainButton) saver_.getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(saver_.getFrame().isVisible());
        assertEq("/tmp/txt",FileSaveFrame.getStaticSelectedPath(saver_));*/
}
