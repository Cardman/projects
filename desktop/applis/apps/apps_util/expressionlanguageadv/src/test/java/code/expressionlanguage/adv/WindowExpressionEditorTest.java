package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.mock.MockMenuItem;
import code.mock.MockPlainButton;
import code.mock.MockWindow;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowExpressionEditorTest extends EquallableElAdvUtil {
    @Test
    public void init1() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getChooseFolder()).getActionListeners().get(0).action();
        assertEq("/folder/exp",((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getFolderExp());
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getCreateEnv()).getActionListeners().get(0).action();
        assertEq(1,w_.getExpressionEditors().size());
    }
    @Test
    public void init2() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getChooseFolder()).getActionListeners().get(0).action();
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getCreateEnv()).getActionListeners().get(0).action();
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
    public void init6() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getChooseFolder()).getActionListeners().get(0).action();
        ((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getSrcFolder().setText("sample_src");
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getCreateEnv()).getActionListeners().get(0).action();
        assertEq(1,w_.getExpressionEditors().size());
        assertEq("sample_src",w_.getExpressionEditors().get(0).getManageOptions().getEx().getSrcFolder());
    }
    @Test
    public void closeMain() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        CustList<WindowExpressionEditor> exp_ = new CustList<WindowExpressionEditor>(w_.getExpressionEditors());
        w_.quit();
        assertFalse(exp_.get(0).getCommonFrame().isVisible());
        assertTrue(w_.getExpressionEditors().isEmpty());
    }
    @Test
    public void closeSec() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        ((MockWindow)w_.getExpressionEditors().get(0).getCommonFrame()).getWindowListenersDef().get(0).windowClosing();
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
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getChooseFolder()).getActionListeners().get(0).action();
        assertEq(0,w_.getExpressionEditors().size());
    }
    @Test
    public void noSelect() {
        WindowCdmEditor w_=newWindowLoadDefExpFolderAlready("/folder/exp","file.txt");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
        s_.getTree().select(null);
        assertEq(1,s_.getTabs().size());
    }
    @Test
    public void lg() {
        WindowCdmEditor w_=newWindowLoadDefExpFolder("/folder/exp");
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/folder/exp").mkdirs();
        StreamTextFile.saveTextFile("/folder/exp/file.txt","",w_.getCommonFrame().getFrames().getStreams());
        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getChooseFolder()).getActionListeners().get(0).action();
        assertEq("/folder/exp",((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getFolderExp());
        ((MockPlainButton)((FolderForExpression)((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0)).getDialogExpresion().getCreateEnv()).getActionListeners().get(0).action();
        ((MockMenuItem)w_.getExpressionEditors().get(0).getLanguageMenu()).getActionListeners().get(0).action();
        w_.getExpressionEditors().get(0).getLanguageFrames().get(0).getChosenLanguage().selectItem(StringUtil.indexOf(w_.getCommonFrame().getFrames().getLanguages(),"fr"));
        ((MockPlainButton)w_.getExpressionEditors().get(0).getLanguageFrames().get(0).getVal()).getActionListeners().get(0).action();
        WindowCdmEditor w2_=newWindowLoadDefExpFolder("/folder/exp");
        String name_ = w_.getConfGlobal();
        String name2_ = w_.getExecConf();
        StreamTextFile.saveTextFile(name_,StreamTextFile.contentsOfFile(name_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        StreamTextFile.saveTextFile(name2_,StreamTextFile.contentsOfFile(name2_,w_.getCommonFrame().getFrames().getFileCoreStream(),w_.getCommonFrame().getFrames().getStreams()),w2_.getCommonFrame().getFrames().getStreams());
        w2_.updateEnv(name_);
        assertEq("fr",w2_.manage(w2_.getSoftParams().getLines()).getEx().getLg());
        assertEq("en",w2_.manage(ExecutingOptions.lines(StreamTextFile.contentsOfFile(w2_.getExecConf(),w2_.getCommonFrame().getFrames().getFileCoreStream(),w2_.getCommonFrame().getFrames().getStreams()))).getEx().getLg());
    }
}
