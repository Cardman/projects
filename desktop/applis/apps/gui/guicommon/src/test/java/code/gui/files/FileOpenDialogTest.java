package code.gui.files;

import code.gui.TextAnswerValue;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.threads.ConcreteBoolean;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileOpenDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",true,"/tmp",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        assertEq(2,open_.getFolderSystem().selectEvt().getChildCount());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt1","inner",false);
        pr_.getStreams().getTextFact().write("txt2","inner",false);
        pr_.getFileCoreStream().newFile("/tmp1").mkdirs();
        pr_.getFileCoreStream().newFile("/tmp2").mkdirs();
        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
        FileOpenDialog.setFileOpenDialog("en",false,"/",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        assertEq(1,open_.getFolderSystem().selectEvt().getChildCount());

    }
//    @Test
//    public void init3() {
//        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
//        pr_.getFileCoreStream().newFile("tmp").mkdirs();
//        pr_.setCurrentPath("/tmp");
//        pr_.getFileCoreStream().newFile("tmp1").mkdirs();
//        pr_.getFileCoreStream().newFile("tmp2").mkdirs();
//        pr_.getStreams().getTextFact().write("txt1","inner",false);
//        pr_.getStreams().getTextFact().write("txt2","inner",false);
//        pr_.setCurrentPath("/");
//        FileOpenDialog open_ = new FileOpenDialog(new ConcreteBoolean(false),new ConcreteBoolean(false),pr_);
//        FileOpenDialog.setFileOpenDialog("en",true,"/tmp/",open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
//        assertTrue(open_.isVisible());
//        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
//        assertEq(2,open_.getFolderSystem().selectEvt().getChildCount());
//        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild());
//        assertEq(2,open_.getFileTable().getRowCount());
//    }
}
