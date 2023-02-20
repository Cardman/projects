package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.mock.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowCdmEditorInitTest extends EquallableElAdvUtil {
    @Test
    public void init1() {
        WindowCdmEditor w_=newWindowLoadDef();
        assertTrue(w_.getCommonFrame().isVisible());
    }
    @Test
    public void init2() {
        WindowCdmEditor w_=newWindowLoadDef();
        assertEq(1,tabulations(w_).getTabulation().getMin());
    }
    @Test
    public void init3() {
        WindowCdmEditor w_=newWindowLoadDef();
        assertEq(9, GuiBaseUtil.getActions(tabEditor(w_).getCenter()).size());
    }
    @Test
    public void init4() {
        WindowCdmEditor w_=newWindowLoadDef();
        assertEq(1,((MockPanel)w_.getPanel()).getAccessible().size());
        assertEq(1,w_.getPanel().getComponentCount());
        assertFalse(((MockCustComponent)tabEditor(w_).getNavModifPanel()).isAccessible());
    }
    @Test
    public void tabs() {
        WindowCdmEditor w_=newWindowLoadDef();
        w_.setTabWidth(8);
        w_.updateCurrentTab();
        AbsTabStops tabs_ = ((MockAttrSet) ((MockTextPane) tabEditor(w_).getCenter()).getParagraph()).getTabs();
        assertEq(TabValueChanged.TABS, tabs_.getLength());
        assertEq(96, tabs_.getTab(0).getValue());
        assertEq(192, tabs_.getTab(1).getValue());
    }
    @Test
    public void chCenter() {
        WindowCdmEditor w_=newWindowLoadDef();
        tabEditor(w_).getCenter().setText("_");
        ((MockTextPane)tabEditor(w_).getCenter()).getAutoCompleteListeners().get(0).changedUpdate();
        assertEq("_", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void folderCreate() {
        WindowCdmEditor w_ = windowLoadDefNoTab(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"folder/")));
        assertTrue(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/folder/").isDirectory());
    }
    @Test
    public void notSrc() {
        WindowCdmEditor w_ = windowLoadDefNoTab(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"folder/")));
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot());
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/folder/").isDirectory());
    }
    @Test
    public void fileExist() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")));
        tabEditor(w_).getCenter().setText("TEXT");
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertTrue(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/file.txt").exists());
        assertEq("TEXT",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void fileSelect() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")));
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild().getNextSibling());
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void noSelect() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")));
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(null);
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void noText() {
        WindowCdmEditor w_ = windowLoadDefNoTab(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.NO_OPTION,"")));
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(null);
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void saveText() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")));
        tabEditor(w_).getCenter().setText("TEXT");
        save(w_);
        assertEq("TEXT",contentsOfFile("/project/sources/src/file.txt", w_));
    }
    @Test
    public void closeTab() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")));
        tabEditor(w_).getCenter().setText("TEXT");
        saveSelected(w_);
        closeTab(w_);
        assertEq(0,w_.getTabs().size());
        assertEq(0,w_.getEditors().getComponentCount());
        assertEq(0,w_.getOpenedFiles().size());
    }
    @Test
    public void twoFiles() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        w_.getEditors().selectIndex(0);
        tabSelect(w_).getCenter().setText("TEXT");
        saveSelected(w_);
        w_.getEditors().selectIndex(1);
        tabSelect(w_).getCenter().setText("TEXT2");
        saveSelected(w_);
        assertEq("TEXT",contentsOfFile("/project/sources/src/file.txt", w_));
        assertEq("TEXT2",contentsOfFile("/project/sources/src/file2.txt", w_));
    }
    @Test
    public void refreshTreeYes() {
        WindowCdmEditor w_ = windowLoadDefTwiceRefresh(newMockProgramInfosInitConfNoDeepProject());
        assertEq(0,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild()));
        refresh(w_);
        assertEq(2,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild()));
        assertEq("file1",((AbstractMutableTreeNode)MutableTreeNodeCoreUtil.getChildAt(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild(),0)).getUserObject());
        assertEq("file2",((AbstractMutableTreeNode)MutableTreeNodeCoreUtil.getChildAt(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild(),1)).getUserObject());
    }
    @Test
    public void refreshTreeNo() {
        WindowCdmEditor w_ = windowLoadDefTwiceRefresh(newMockProgramInfosInitConfNoDeepProject());
        assertEq(1,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild()));
        assertEq("under/",((AbstractMutableTreeNode)w_.getFolderSystem().getRoot().getFirstChild().getFirstChild()).getUserObject());
        assertEq(0,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild()));
        w_.getFolderSystem().select(null);
        refresh(w_);
        assertEq(0,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild()));
    }
    @Test
    public void refreshTreeDelete() {
        WindowCdmEditor w_ = windowLoadDefTwiceRefreshDelete(newMockProgramInfosInitConfNoDeepProject());
        assertEq(1,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild()));
        refresh(w_);
        assertEq(0,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild()));
    }
    @Test
    public void refreshTreeDeleteExceptRoot() {
        WindowCdmEditor w_ = windowLoadDefTwiceRefreshDeleteExceptRoot(newMockProgramInfosInitConfNoDeepProject());
        assertEq(1,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot().getFirstChild()));
        refresh(w_);
        assertEq(0,MutableTreeNodeCoreUtil.getChildCount(w_.getFolderSystem().getRoot()));
    }
    @Test
    public void refreshTreeDeleteEdited1() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        w_.getEditors().selectIndex(0);
        tabSelect(w_).getCenter().setText("TEXT");
        saveSelected(w_);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/file2.txt").delete();
        assertEq(2,w_.getTabs().size());
        assertEq(2,w_.getEditors().getComponentCount());
        refresh(w_);
        assertEq(1,w_.getTabs().size());
        assertEq(1,w_.getEditors().getComponentCount());
        assertEq("TEXT",contentsOfFile("/project/sources/src/file.txt", w_));
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/file2.txt").exists());
    }
    @Test
    public void refreshTreeDeleteEdited2() {
        WindowCdmEditor w_ = windowLoadDefTwice(newMockProgramInfosInitConfNoArr(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")));
        w_.getEditors().selectIndex(0);
        tabSelect(w_).getCenter().setText("TEXT");
        saveSelected(w_);
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/file2.txt").delete();
        assertEq(2,w_.getTabs().size());
        assertEq(2,w_.getEditors().getComponentCount());
        w_.getFolderSystem().select(w_.getFolderSystem().getRoot().getFirstChild().getFirstChild().getNextSibling());
        assertEq(1,w_.getTabs().size());
        assertEq(1,w_.getEditors().getComponentCount());
        assertEq("TEXT",contentsOfFile("/project/sources/src/file.txt", w_));
        assertFalse(w_.getCommonFrame().getFrames().getFileCoreStream().newFile("/project/sources/src/file2.txt").exists());
    }
    @Test
    public void reload() {
        WindowCdmEditor w_ = windowLoadDef(newMockProgramInfosInitConfTab());
        assertEq("TEXT",tabSelect(w_).getCenter().getText());
    }
    @Test
    public void fileConf1() {
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNo());
        w_.updateCommentsInit(new StringList());
        assertFalse(newFile(w_,getTempDefConf(w_)).exists());
        assertEq("",w_.getExecConf());
    }
    @Test
    public void fileConf2() {
        String chooseConf_ = "/editor/conf.xml";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoChooseFolder(chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertFalse(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
    }
    @Test
    public void fileConf3() {
        String chooseConf_ = "/editor/conf.xml";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoChooseFolderConf("/folder/sources/"));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertFalse(newFile(w_,chooseConf_).exists());
    }
    @Test
    public void fileConf4() {
        String chooseConf_ = "/editor/conf.xml";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList());
        assertTrue(newFile(w_,getTempDefConf(w_)).exists());
        assertEq(chooseConf_,w2_.getExecConf());
    }
    @Test
    public void fileConf5() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        newFile(w_,getTempDefConf(w_)).renameTo(newFile(w_,"/editor/conf.xml"));
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertTrue(newFile(w_,"/editor/conf.xml").exists());
        assertEq(chooseConf_,w2_.getExecConf());
    }
    @Test
    public void fileConf6() {
        String chooseConf_ = "/editor/conf.xml";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        CustList<CommentDelimiters> dels_ = new CustList<CommentDelimiters>();
        dels_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        saveComments(w_,dels_);
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList());
        assertTrue(newFile(w_,getTempDefConf(w_)).exists());
        assertEq(chooseConf_,w2_.getExecConf());
        CustList<CommentDelimiters> result_ = w2_.getComments();
        assertEq(1,result_.size());
        assertEq("\\*",result_.get(0).getBegin());
        assertEq("*\\",result_.get(0).getEnd().get(0));
        assertEq("en",w2_.getUsedLg());
    }
    @Test
    public void fileConf7() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        CustList<CommentDelimiters> dels_ = new CustList<CommentDelimiters>();
        dels_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        saveComments(w_,dels_);
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        newFile(w_,getTempDefConf(w_)).renameTo(newFile(w_,"/editor/conf.xml"));
        assertTrue(newFile(w_,"/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertEq(chooseConf_,w2_.getExecConf());
        CustList<CommentDelimiters> result_ = w2_.getComments();
        assertEq(1,result_.size());
        assertEq("\\*",result_.get(0).getBegin());
        assertEq("*\\",result_.get(0).getEnd().get(0));
        assertEq("en",w2_.getUsedLg());
    }
    @Test
    public void fileConf8() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        saveComments(w_,new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        newFile(w_,getTempDefConf(w_)).renameTo(newFile(w_,"/editor/conf.xml"));
        assertTrue(newFile(w_,"/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertEq(chooseConf_,w2_.getExecConf());
        CustList<CommentDelimiters> result_ = w2_.getComments();
        assertEq(0,result_.size());
        assertEq("en",w2_.getUsedLg());
    }
    @Test
    public void fileConf9() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        saveComments(w_,new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        newFile(w_,getTempDefConf(w_)).renameTo(newFile(w_,"/editor/conf.xml"));
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(WindowCdmEditor.ROOT_CONF+"_");
        doc_.appendChild(elt_);
        saveTextFile("/editor/conf.xml",doc_.export(),w_);
        assertTrue(newFile(w_,"/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertEq("",w2_.getCurrentFolder());
        assertEq("",w2_.getExecConf());
    }
    @Test
    public void fileConf10() {
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoDirConfSave("/folder/sources", "/editor/conf.xml"));
        w_.updateCommentsInit(new StringList());
        ((MockMenuItem)w_.getChooseFile()).getActionListeners().get(0).action();
        assertEq("/editor/conf.txt",w_.getExecConf());
        assertEq("/folder/sources",w_.getCurrentFolder());
    }
    @Test
    public void fileConf11() {
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoDirConf("/folder/sources", ""));
        w_.updateCommentsInit(new StringList());
        ((MockMenuItem)w_.getChooseFile()).getActionListeners().get(0).action();
        assertEq("",w_.getExecConf());
        assertEq("",w_.getCurrentFolder());
    }
    @Test
    public void fileConf12() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        w_.getSrcFolder().setText("other_src");
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        ManageOptions res_ = saveComments(w_,new CustList<CommentDelimiters>());
        assertEq("other_src",res_.getEx().getSrcFolder());
    }
    @Test
    public void fileConf13() {
        String chooseConf_ = "/editor/conf.txt";
        WindowCdmEditor w_ =windowLoadDefInit(newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_));
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(newFile(w_,chooseConf_).exists());
        saveComments(w_,new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(WindowCdmEditor.ROOT_CONF);
        elt_.setAttribute(WindowCdmEditor.NODE_PATH,chooseConf_);
        elt_.appendChild(doc_.createElement(WindowCdmEditor.ROOT_CONF));
        doc_.appendChild(elt_);
        saveTextFile(getTempDefConf(w_),doc_.export(),w_);
        WindowCdmEditor w2_ =windowLoadDefInit(w_);
        w2_.updateCommentsInit(new StringList());
        assertEq("/folder/sources",w2_.getCurrentFolder());
        assertEq("/editor/conf.txt",w2_.getExecConf());
        assertEq(0,w2_.getTabs().size());
    }
    @Test
    public void noSelectTree() {
        WindowCdmEditor w_=newWindowLoadDef();
        w_.getFolderSystem().select(null);
        assertFalse(w_.applyTreeChangeSelected(true));
    }
    @Test
    public void quit1() {
        WindowCdmEditor w_=newWindowLoadDef();
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(w_.getCommonFrame().isVisible());
    }
    @Test
    public void quit2() {
        AbsGroupFrame w_ =newWindowLoadDef();
        w_.canChangeLanguage();
        w_.getApplicationName();
        w_.changeLanguage("");
        w_.dispatchExit();
        assertFalse(w_.getCommonFrame().isVisible());
    }
}
