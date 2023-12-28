package code.gui.files;

import code.gui.AbsButton;
import code.gui.TextAnswerValue;
import code.mock.MockCustComponent;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FolderOpenDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void select1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog("en",false,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        tryClick((AbsButton) open_.getButtons().getComponent(0));
        assertTrue(open_.isVisible());
    }
    @Test
    public void select2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog("en",false,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
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
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getFileCoreStream().newFile("/tmp/").mkdirs();
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog("en",true,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        assertFalse(((MockCustComponent)open_.getFileName()).isAccessible());
        assertTrue(((MockCustComponent)open_.getFolderSystem()).isDeepAccessible());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot());
        open_.getFolderSystem().select(open_.getFolderSystem().getRoot().getFirstChild());
        tryClick((AbsButton) open_.getButtons().getComponent(0));
        assertFalse(open_.isVisible());
        assertEq("tmp/",FolderOpenDialog.getStaticSelectedPath(open_));
    }
    @Test
    public void cancel() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        pr_.getStreams().getTextFact().write("txt","inner",false);
        FolderOpenDialog open_ = new FolderOpenDialog(pr_);
        FolderOpenDialog.setFolderOpenDialog("en",false,open_,pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)));
        assertTrue(open_.isVisible());
        tryClick((AbsButton) open_.getButtons().getComponent(1));
        assertFalse(open_.isVisible());
        assertEq("",FolderOpenDialog.getStaticSelectedPath(open_));
    }
}
