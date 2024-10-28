package code.gui.files;

import code.gui.CoreMouseLocation;
import code.gui.EquallableGuiFctUtil;
import code.gui.events.MockProgramInfosSecSample;
import code.mock.*;
import code.stream.AbstractFile;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class FileTableTest extends EquallableGuiFctUtil {
    @Test
    public void sort1() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 0, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort2() {
        MockProgramInfosSecSample pr_ = init();
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 0, 0);
        click(open_, 0, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort3() {
        MockProgramInfosSecSample pr_ = init(1);
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 1, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort4() {
        MockProgramInfosSecSample pr_ = init(1);
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 1, 0);
        click(open_, 1, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort5() {
        MockProgramInfosSecSample pr_ = init(1);
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner_",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 2, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort6() {
        MockProgramInfosSecSample pr_ = init(1);
        updateFileOpen(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner_",false);
        FileOpenFrame open_ = new FileOpenFrame(pr_,new ConcreteBoolean(false));
        FileOpenFrame.setFileSaveDialogByFrame(true,"/tmp",open_,new DefButtonsOpenPanelAct(new ContinueFileSample()));
        assertTrue(open_.getFrame().isVisible());
        click(open_, 2, 0);
        click(open_, 2, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("txt2",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort7() {
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
//        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        click(open_, 3, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("tmp1/sub/txt1",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.PATH_INDEX));
        assertEq("tmp1/txt1",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.PATH_INDEX));
    }
    @Test
    public void sort8() {
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
//        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        click(open_, 3, 0);
        click(open_, 3, 0);
        assertEq(2,open_.getFileDialogContent().getFileTable().getRowCount());
        assertEq("tmp1/txt1",open_.getFileDialogContent().getFileTable().getValueAt(0,FileTable.PATH_INDEX));
        assertEq("tmp1/sub/txt1",open_.getFileDialogContent().getFileTable().getValueAt(1,FileTable.PATH_INDEX));
    }
    @Test
    public void sort9() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        CustList<AbstractFile> ls_ = new CustList<AbstractFile>();
        ls_.add(pr_.getFileCoreStream().newFile("tmp1/sub/txt1"));
        ls_.add(pr_.getFileCoreStream().newFile("tmp1/sub/txt1"));
        ls_.sortElts(new FileComparator(true,FileTable.PATH_INDEX,"tmp1/"));
        assertEq(2,ls_.size());
    }
    @Test
    public void sort10() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("tmp1/sub").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        pr_.getStreams().getTextFact().write("tmp1/sub/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/sub/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt1","inner",false);
        pr_.getStreams().getTextFact().write("tmp1/txt2","inner",false);
        pr_.getStreams().getTextFact().write("tmp2/txt","inner",false);
        CustList<AbstractFile> ls_ = new CustList<AbstractFile>();
        ls_.add(pr_.getFileCoreStream().newFile("tmp1/sub/txt1"));
        ls_.add(pr_.getFileCoreStream().newFile("tmp1/sub/txt1"));
        ls_.sortElts(new FileComparator(false,FileTable.PATH_INDEX,"tmp1/"));
        assertEq(2,ls_.size());
    }
    @Test
    public void indexPath1() {
        MockProgramInfosSecSample pr_ = init();
        StringMap<String> indexes_ = FileDialogContent.index("a/b", pr_.getValidator());
        assertEq(2,indexes_.size());
        assertEq("0",indexes_.getVal("a"));
        assertEq("1",indexes_.getVal("b"));
    }
    @Test
    public void indexPath2() {
        MockProgramInfosSecSample pr_ = init();
        StringMap<String> indexes_ = FileDialogContent.index("b/a", pr_.getValidator());
        assertEq(2,indexes_.size());
        assertEq("0",indexes_.getVal("b"));
        assertEq("1",indexes_.getVal("a"));
    }
    @Test
    public void indexPath3() {
        MockProgramInfosSecSample pr_ = init();
        StringMap<String> indexes_ = FileDialogContent.index("!", pr_.getValidator());
        assertEq(0,indexes_.size());
    }
    @Test
    public void indexPath4() {
        MockProgramInfosSecSample pr_ = init();
        StringMap<String> indexes_ = FileDialogContent.index("", pr_.getValidator());
        assertEq(0,indexes_.size());
    }
    private void click(FileOpenFrame _open, int _x, int _y) {
        ((MockTableGui) _open.getFileDialogContent().getFileTable()).getHeadersCl().get(0).mouseReleased(new CoreMouseLocation(_x, _y),null,null);
    }
}
