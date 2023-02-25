package code.expressionlanguage.adv;

import code.mock.MockMenuItem;
import code.stream.StreamTextFile;
import org.junit.Test;

public final class WindowExpressionEditorTest extends EquallableElAdvUtil {
    @Test
    public void init1() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        assertEq(1,w_.getExpressionEditors().size());
    }
    @Test
    public void init2() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild());
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void init3() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild());
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void init4() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.getTree().select(s_.getTree().getRoot());
        s_.getTree().select(s_.getTree().getRoot().getFirstChild());
        closeTab(s_.getTabs().get(s_.getEditors().getSelectedIndex()));
        assertEq(0,s_.getTabs().size());
    }
    @Test
    public void init5() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        assertEq(0,s_.getTabs().size());
    }
    @Test
    public void closeMain() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        w_.quit();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        assertFalse(s_.getCommonFrame().isVisible());
    }
    @Test
    public void closeSec() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        w_.getExpressionEditors().get(0).getCommonFrame().setVisible(false);
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        assertTrue(s_.getCommonFrame().isVisible());
    }
    @Test
    public void initNo() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        assertEq(0,w_.getExpressionEditors().size());
    }
}
