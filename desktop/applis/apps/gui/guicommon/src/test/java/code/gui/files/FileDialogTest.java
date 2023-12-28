package code.gui.files;

import code.gui.TextAnswerValue;
import code.gui.TopLeftFrame;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void coords1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        FileDialog.saveCoords("/tmp","_",1,2,pr_.getStreams());
        TopLeftFrame tl_ = FileDialog.loadCoords("/tmp", "_", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(1,tl_.getWidth());
        assertEq(2,tl_.getHeight());
    }
    @Test
    public void coords2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        TopLeftFrame tl_ = FileDialog.loadCoords("/tmp", "_", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(0,tl_.getWidth());
        assertEq(0,tl_.getHeight());
    }
}
