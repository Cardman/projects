package code.gui.files;

import code.gui.CoreMouseLocation;
import code.gui.TextAnswerValue;
import code.mock.*;
import code.stream.AbstractFile;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileTableTest extends EquallableGuiCommonUtil {
    @Test
    public void sort1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 0, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt1",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 0, 0);
        click(open_, 0, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt2",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[]{1}, StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 1, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt1",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[]{1}, StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 1, 0);
        click(open_, 1, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt2",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[]{1}, StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner_",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 2, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt1",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt2",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort6() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[]{1}, StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner_",false);
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        click(open_, 2, 0);
        click(open_, 2, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("txt2",open_.getFileTable().getValueAt(0,FileTable.NAME_INDEX));
        assertEq("txt1",open_.getFileTable().getValueAt(1,FileTable.NAME_INDEX));
    }
    @Test
    public void sort7() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
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
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        open_.getThread().join();
        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        click(open_, 3, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("tmp1/sub/txt1",open_.getFileTable().getValueAt(0,FileTable.PATH_INDEX));
        assertEq("tmp1/txt1",open_.getFileTable().getValueAt(1,FileTable.PATH_INDEX));
    }
    @Test
    public void sort8() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
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
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getTypedString().setText("txt1");
        tryClick(open_.getSearchButton());
        open_.getThread().join();
        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        click(open_, 3, 0);
        click(open_, 3, 0);
        assertEq(2,open_.getFileTable().getRowCount());
        assertEq("tmp1/txt1",open_.getFileTable().getValueAt(0,FileTable.PATH_INDEX));
        assertEq("tmp1/sub/txt1",open_.getFileTable().getValueAt(1,FileTable.PATH_INDEX));
    }
    @Test
    public void sort9() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
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
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
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
    private void click(FileOpenDialog _open, int _x, int _y) {
        ((MockTableGui) _open.getFileTable()).getHeadersCl().get(0).mouseClicked(new CoreMouseLocation(_x, _y),null,null);
    }
}
