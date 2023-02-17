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
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertTrue(w_.getCommonFrame().isVisible());
        assertEq(1,w_.getIdes().size());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertEq(1,w_.getSpinner().getMin());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertEq(8, GuiBaseUtil.getActions(tabEditor(w_).getCenter()).size());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        assertEq(2,((MockPanel)w_.getPanel()).getAccessible().size());
        assertEq(2,w_.getPanel().getComponentCount());
        assertFalse(((MockCustComponent)tabEditor(w_).getNavModifPanel()).isAccessible());
    }
    @Test
    public void tabs() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        w_.getSpinner().setValue(8);
        AbsTabStops tabs_ = ((MockAttrSet) ((MockTextPane) tabEditor(w_).getCenter()).getParagraph()).getTabs();
        assertEq(TabValueChanged.TABS, tabs_.getLength());
        assertEq(96, tabs_.getTab(0).getValue());
        assertEq(192, tabs_.getTab(1).getValue());
    }
    @Test
    public void chCenter() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("_");
        ((MockTextPane)tabEditor(w_).getCenter()).getAutoCompleteListeners().get(0).changedUpdate();
        assertEq("_", tabEditor(w_).getCenter().getText());
    }
    @Test
    public void folderCreate() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"folder/"));
        windowLoadDef(pr_);
        assertTrue(pr_.getFileCoreStream().newFile("/project/sources/src/folder/").isDirectory());
    }
    @Test
    public void notSrc() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"folder/"));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot());
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(pr_.getFileCoreStream().newFile("/project/sources/folder/").isDirectory());
    }
    @Test
    public void fileExist() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("TEXT");
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile("/project/sources/src/file.txt").exists());
        assertEq("TEXT",tabEditor(w_).getCenter().getText());
    }
    @Test
    public void fileSelect() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild().getNextSibling());
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(pr_.getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void noSelect() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(null);
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(pr_.getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void noText() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.NO_OPTION,""));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(null);
        tr_.getTreeSelectionListeners().get(0).valueChanged(null);
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        assertFalse(pr_.getFileCoreStream().newFile("/project/sources/file.txt").exists());
    }
    @Test
    public void saveText() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo(new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"));
        WindowCdmEditor w_ = windowLoadDef(pr_);
        tabEditor(w_).getCenter().setText("TEXT");
        save(w_);
        assertEq("TEXT",StreamTextFile.contentsOfFile("/project/sources/src/file.txt", pr_.getFileCoreStream(), pr_.getStreams()));
    }
    @Test
    public void fileConf1() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNo();
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertFalse(pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).exists());
        assertEq("",w_.getExecConf());
    }
    @Test
    public void fileConf2() {
        String chooseConf_ = "/editor/conf.xml";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoChooseFolder(chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertFalse(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
    }
    @Test
    public void fileConf3() {
        String chooseConf_ = "/editor/conf.xml";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoChooseFolderConf("/folder/sources/");
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertFalse(pr_.getFileCoreStream().newFile(chooseConf_).exists());
    }
    @Test
    public void fileConf4() {
        String chooseConf_ = "/editor/conf.xml";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList());
        assertTrue(pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).exists());
        assertEq(chooseConf_,w2_.getExecConf());
    }
    @Test
    public void fileConf5() {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).renameTo(pr_.getFileCoreStream().newFile("/editor/conf.xml"));
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertTrue(pr_.getFileCoreStream().newFile("/editor/conf.xml").exists());
        assertEq(chooseConf_,w2_.getExecConf());
    }
    @Test
    public void fileConf6() {
        String chooseConf_ = "/editor/conf.xml";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        CustList<CommentDelimiters> dels_ = new CustList<CommentDelimiters>();
        dels_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        w_.saveComments(dels_);
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList());
        assertTrue(pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).exists());
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
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        CustList<CommentDelimiters> dels_ = new CustList<CommentDelimiters>();
        dels_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        w_.saveComments(dels_);
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).renameTo(pr_.getFileCoreStream().newFile("/editor/conf.xml"));
        assertTrue(pr_.getFileCoreStream().newFile("/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
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
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        w_.saveComments(new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).renameTo(pr_.getFileCoreStream().newFile("/editor/conf.xml"));
        assertTrue(pr_.getFileCoreStream().newFile("/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertEq(chooseConf_,w2_.getExecConf());
        CustList<CommentDelimiters> result_ = w2_.getComments();
        assertEq(0,result_.size());
        assertEq("en",w2_.getUsedLg());
    }
    @Test
    public void fileConf9() {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        w_.saveComments(new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        pr_.getFileCoreStream().newFile(WindowCdmEditor.getTempDefConf(pr_)).renameTo(pr_.getFileCoreStream().newFile("/editor/conf.xml"));
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(WindowCdmEditor.ROOT_CONF+"_");
        doc_.appendChild(elt_);
        StreamTextFile.saveTextFile("/editor/conf.xml",doc_.export(),pr_.getStreams());
        assertTrue(pr_.getFileCoreStream().newFile("/editor/conf.xml").exists());
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList("/editor/conf.xml"));
        assertEq("",w2_.getCurrentFolder());
        assertEq("",w2_.getExecConf());
    }
    @Test
    public void fileConf10() {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoDirConf("/folder/sources/", "/editor/conf.xml");
        StreamFolderFile.makeParent("/editor/conf.xml",pr_.getFileCoreStream());
        StreamTextFile.saveTextFile("/editor/conf.xml",WindowCdmEditor.buildDefConfFile(chooseConf_,new StringList()),pr_.getStreams());
        StringList lines_ = new StringList();
        lines_.add("/folder/sources/");
        lines_.add("en");
        StreamTextFile.saveTextFile(chooseConf_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        ((MockMenuItem)w_.getChooseFile()).getActionListeners().get(0).action();
        assertEq(chooseConf_,w_.getExecConf());
        assertEq("/folder/sources/",w_.getCurrentFolder());
    }
    @Test
    public void fileConf11() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoDirConf("/folder/sources/", "");
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        ((MockMenuItem)w_.getChooseFile()).getActionListeners().get(0).action();
        assertEq("",w_.getExecConf());
        assertEq("",w_.getCurrentFolder());
    }
    @Test
    public void fileConf12() {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        w_.getSrcFolder().setText("other_src");
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        ManageOptions res_ = w_.saveComments(new CustList<CommentDelimiters>());
        assertEq("other_src",res_.getEx().getSrcFolder());
    }
    @Test
    public void fileConf13() {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = newMockProgramInfosInitConfNoFolder("/folder/sources/", chooseConf_);
        WindowCdmEditor w_ =windowLoadDefInit(pr_);
        w_.updateCommentsInit(new StringList());
        assertTrue(((MockPlainButton)w_.getChooseFolder()).isDeepAccessible());
        ((MockPlainButton)w_.getChooseFolder()).getActionListeners().get(0).action();
        assertTrue(((MockPlainButton)w_.getCreateFile()).isDeepAccessible());
        ((MockPlainButton)w_.getCreateFile()).getActionListeners().get(0).action();
        assertTrue(pr_.getFileCoreStream().newFile(chooseConf_).exists());
        w_.saveComments(new CustList<CommentDelimiters>());
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(WindowCdmEditor.ROOT_CONF);
        elt_.setAttribute(WindowCdmEditor.NODE_PATH,chooseConf_);
        elt_.appendChild(doc_.createElement(WindowCdmEditor.ROOT_CONF));
        doc_.appendChild(elt_);
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),doc_.export(),pr_.getStreams());
        WindowCdmEditor w2_ =windowLoadDefInit(pr_);
        w2_.updateCommentsInit(new StringList());
        assertEq("/folder/sources/",w2_.getCurrentFolder());
        assertEq("/editor/conf.txt",w2_.getExecConf());
        assertEq(0,w2_.getTabs().size());
    }
    @Test
    public void noSelectTree() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        w_.getFolderSystem().select(null);
        assertFalse(w_.applyTreeChangeSelected());
    }
    @Test
    public void quit1() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        w_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(w_.getCommonFrame().isVisible());
        assertEq(0,w_.getIdes().size());
    }
    @Test
    public void quit2() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        AbsGroupFrame w_ =windowLoadDef(pr_);
        w_.canChangeLanguage();
        w_.getApplicationName();
        w_.changeLanguage("");
        w_.dispatchExit();
        assertFalse(w_.getCommonFrame().isVisible());
    }
}
