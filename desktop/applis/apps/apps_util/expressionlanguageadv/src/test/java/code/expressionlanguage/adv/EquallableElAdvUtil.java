package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.util.TranslationsLg;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.AbstractBinFact;
import code.stream.core.AbstractTextFact;
import code.stream.core.AbstractZipFact;
import code.threads.ThState;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableElAdvUtil {
    public static void assertNull(AbsCustComponent _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractBinFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractZipFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractTextFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertSame(AbsCustComponent _expected, AbsCustComponent _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ThState _expected, ThState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static WindowCdmEditor windowLoadDef(AbstractProgramInfos _pr) {
        WindowCdmEditor w_ = updated(_pr);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        w_.getTabs().get(0).getWholeWord().setSelected(false);
        return w_;
    }

    private static WindowCdmEditor updated(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        w_.updateCommentsInit(new StringList());
        return w_;
    }

    public static WindowCdmEditor windowLoadDefTwice(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        return w_;
    }

    public static WindowCdmEditor windowLoadDefTwiceOtherPlace(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockCustComponent)tr_).getKeyPressListeners().get(0).keyPressed(new KeyActionEvent(true,false,false),(char)0,GuiConstants.VK_F5);
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        return w_;
    }

    public static WindowCdmEditor windowLoadDefTwiceRefresh(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        _pr.getFileCoreStream().newFile("/project/sources/src/under/").mkdirs();
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot());
        tr_.select(tr_.getRoot().getFirstChild());
        tr_.select(tr_.getRoot().getFirstChild().getFirstChild());
        refresh(w_);
        saveTextFile("/project/sources/src/under/file1", "",w_);
        saveTextFile("/project/sources/src/under/file2", "",w_);
        return w_;
    }
    public static WindowCdmEditor windowLoadDefTwiceRefreshDelete(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        _pr.getFileCoreStream().newFile("/project/sources/src/under/").mkdirs();
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot());
        tr_.select(tr_.getRoot().getFirstChild());
        tr_.select(tr_.getRoot().getFirstChild().getFirstChild());
        _pr.getFileCoreStream().newFile("/project/sources/src/under/").delete();
        return w_;
    }
    public static WindowCdmEditor windowLoadDefTwiceRefreshDeleteExceptRoot(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        _pr.getFileCoreStream().newFile("/project/sources/src/under/").mkdirs();
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot());
        tr_.select(tr_.getRoot().getFirstChild());
        tr_.select(tr_.getRoot().getFirstChild().getFirstChild());
        _pr.getFileCoreStream().newFile("/project/sources/src/under/").delete();
        _pr.getFileCoreStream().newFile("/project/sources/src/").delete();
        return w_;
    }
    public static WindowCdmEditor windowLoadDefNoTab(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        return w_;
    }
    public static WindowCdmEditor windowLoadDefNoTabQuick(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        WindowCdmEditor w_ = window(_pr);
        w_.updateCommentsInit(new StringList());
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        return w_;
    }
    public static WindowCdmEditor windowLoadDefInit(WindowCdmEditor _first) {
        AbstractProgramInfos pr_ = _first.getCommonFrame().getFrames();
        pr_.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        pr_.setLanguage(FileInfos.EN);
        update((MockProgramInfos) pr_);
        return window(pr_);
    }

    public static WindowCdmEditor windowLoadDefInit(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        return window(_pr);
    }

    public static WindowCdmEditor window(AbstractProgramInfos _pr) {
        CdmFactory fact_ = new CdmFactory(_pr, new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct());
        _pr.getCounts().addEntry(WindowCdmEditor.CDM_EDITOR,_pr.getThreadFactory().newAtomicInteger());
        WindowCdmEditor w_ = new WindowCdmEditor("en", _pr,fact_);
//        w_.getTabEditor().getWholeWord().setSelected(false);
        return w_;
    }

    public static double[] dbs(double... _args) {
        return _args;
    }

    public static void update(MockProgramInfos _pr) {
        FileInfos.enTr(FileInfos.initComments(lg(_pr,FileInfos.EN)));
        FileInfos.frTr(FileInfos.initComments(lg(_pr,FileInfos.FR)));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        TranslationsLg lg_ = new TranslationsLg();
        _pr.getTranslations().getMapping().addEntry(_key, lg_);
        return lg_;
    }
    public static WindowCdmEditor newWindowLoadDef() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        return windowLoadDef(pr_);
    }
    public static WindowCdmEditor newWindowLoadDefExpFolder(String..._args) {
        MockProgramInfos pr_ = newMockProgramInfosInitConfExpFolder(_args);
        return updated(pr_);
    }
    public static WindowCdmEditor newWindowLoadDefExpFolderAlready(String _folder, String _sec) {
        MockProgramInfos pr_ = newMockProgramInfosInitConfExpFolderAlready(_folder, _sec);
        return updated(pr_);
    }
    public static WindowCdmEditor newWindowLoadDefMessages() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfMessages();
        return windowLoadDef(pr_);
    }
    public static WindowCdmEditor newWindowLoadDefAliasesKeywords() {
        MockProgramInfos pr_ = newMockProgramInfosInitConfAliasesKeywords();
        return windowLoadDef(pr_);
    }
    public static MockProgramInfos newMockProgramInfosInitConf() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfExpFolder(String... _folders) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], _folders, new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfExpFolderAlready(String _folder, String _sec) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        CdmParameterSoftModel c_ = new CdmParameterSoftModel();
        c_.setExecConf(current_);
        c_.setFolderExpression(_folder);
        c_.getOpenedFiles().add("file.txt");
        c_.getOpenedFilesToInit().add(_sec);
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(c_),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfMessages() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        lines_.add("messages=VoidType=void\\u0020type?");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfAliasesKeywords() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        lines_.add("aliases=Runnable=$core.Runner");
        lines_.add("keyWords=If=even");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfTab() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        StreamTextFile.saveTextFile("/project/sources/src/file.txt","TEXT",pr_.getStreams());
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNo(TextAnswerValue _ans) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[]{_ans}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        StreamTextFile.saveTextFile("/project/sources/other_file.txt", "",pr_.getStreams());
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoArr(TextAnswerValue... _ans) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], _ans), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoArrRem(int... _ans) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), _ans, new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION,"file.txt"),new TextAnswerValue(GuiConstants.YES_OPTION,"file2.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoDeepProject() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoDirConfSave(String _folder, String _conf) {
        String chooseConf_ = "/editor/conf.txt";
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[]{_conf}, new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.getFileCoreStream().newFile(_folder).mkdirs();
        StreamFolderFile.makeParent("/editor/conf.xml",pr_.getFileCoreStream());
        StreamTextFile.saveTextFile("/editor/conf.xml",WindowCdmEditor.buildDefConfFile(chooseConf_,new StringList()),pr_.getStreams());
        StringList lines_ = new StringList();
        lines_.add("/folder/sources");
        lines_.add("en");
        StreamTextFile.saveTextFile(chooseConf_, StringUtil.join(lines_,'\n'),pr_.getStreams());
        return pr_;
    }

    public static void saveComments(WindowCdmEditor _editor, CustList<CommentDelimiters> _comm) {
        _editor.updateComments(_comm);
        _editor.saveConf();
    }
    public static String contentsOfFile(String _nomFichier, WindowCdmEditor _tech) {
        return StreamTextFile.contentsOfFile(_nomFichier,_tech.getCommonFrame().getFrames().getFileCoreStream(),_tech.getCommonFrame().getFrames().getStreams());
    }
    public static boolean saveTextFile(String _nomFichier, String _content, WindowCdmEditor _tech) {
        return StreamTextFile.saveTextFile(_nomFichier,_content,_tech.getCommonFrame().getFrames().getStreams());
    }
    public static AbstractFile newFile(WindowCdmEditor _tmpUserFolderSl, String _name) {
        return _tmpUserFolderSl.getCommonFrame().getFrames().getFileCoreStream().newFile(_name);
    }
    public static String getTempDefConf(WindowCdmEditor _tmpUserFolderSl) {
        return getTempDefConf(_tmpUserFolderSl.getCommonFrame().getFrames());
    }
    public static String getTempDefConf(AbstractProgramInfos _tmpUserFolderSl) {
        return WindowCdmEditor.getTempDefConf(_tmpUserFolderSl);
    }
    public static MockProgramInfos newMockProgramInfosInitConfNo() {
        return new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoFolder(String _folder, String _conf) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[]{_folder,_conf}, new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.getFileCoreStream().newFile(_folder).mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoDirConf(String _folder, String _conf) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[]{_conf}, new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.getFileCoreStream().newFile(_folder).mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoChooseFolder(String _conf) {
        return new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[]{"",_conf}, new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
    }
    public static MockProgramInfos newMockProgramInfosInitConfNoChooseFolderConf(String _folder) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(new CustomSeedGene(dbs(0.75)), new int[0], new String[]{_folder, ""}, new TextAnswerValue[0]), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.getFileCoreStream().newFile(_folder).mkdirs();
        return pr_;
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", new MockEventListIncr(_s,new int[0],new String[0],new TextAnswerValue[0]), _set);
    }

    protected static TabEditor tabEditor(WindowCdmEditor _w) {
        return tabEditor(_w,0);
    }

    protected static TabEditor tabSelect(WindowCdmEditor _w) {
        return _w.getTabs().get(_w.getEditors().getSelectedIndex());
    }

    protected static TabEditor tabEditor(WindowCdmEditor _w, int _index) {
        return _w.getTabs().get(_index);
    }

    protected static void enter(AutoCompleteDocument _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getTextField(), GuiConstants.VK_ENTER,0)).action();
    }

    protected void findNow(WindowCdmEditor _w, String _v) {
        tabEditor(_w).getFinder().setText(_v);
        invokeAndClear(_w.getCommonFrame().getFrames());
    }

    protected void changeNow(WindowCdmEditor _w, String _v) {
        tabEditor(_w).getCenter().setText(_v);
        invokeAndClear(_w.getCommonFrame().getFrames());
    }
    protected void findText(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        invokeAndClear(_w.getCommonFrame().getFrames());
    }
    protected void replaceText(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_R,GuiConstants.CTRL_DOWN_MASK)).action();
        invokeAndClear(_w.getCommonFrame().getFrames());
    }
    protected static void invokeAndClear(AbstractProgramInfos _pr) {
        ((MockCompoFactory) _pr.getCompoFactory()).invoke();
        ((MockCompoFactory) _pr.getCompoFactory()).getLater().clear();
    }
    protected void storeEdit(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK)).action();
        invokeAndClear(_w.getCommonFrame().getFrames());
    }
    protected void save(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_S,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void closeTab(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabSelect(_w).getCenter(), GuiConstants.VK_K,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void closeTab(TabEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getCenter(), GuiConstants.VK_K,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void saveSelected(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabSelect(_w).getCenter(), GuiConstants.VK_S,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void undo(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void redo(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK)).action();
    }

    protected static void refresh(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getFolderSystem(), GuiConstants.VK_F5,GuiConstants.CTRL_DOWN_MASK)).action();
    }

    protected static void rename(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getFolderSystem(), GuiConstants.VK_F6,GuiConstants.CTRL_DOWN_MASK)).action();
    }

    protected static void remove(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(_w.getFolderSystem(), GuiConstants.VK_DELETE,0)).action();
    }
    protected void clearEdit(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK)).action();
        invokeAndClear(_w.getCommonFrame().getFrames());
    }

    protected static OutputDialogComments comments(WindowCdmEditor _w) {
        ChangeCommentsEvent ev_ = (ChangeCommentsEvent) ((MockMenuItem) _w.getCommentsMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getOutputDialogComments();
    }

    protected static OutputDialogMessages messages(WindowCdmEditor _w) {
        ChangeMessagesEvent ev_ = (ChangeMessagesEvent) ((MockMenuItem) _w.getMessagesMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getOutputDialogMessages();
    }

    protected static OutputDialogAliases aliases(WindowCdmEditor _w) {
        ChangeAliasesEvent ev_ = (ChangeAliasesEvent) ((MockMenuItem) _w.getAliasesMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getOutputDialogAliases();
    }
    protected static OutputDialogTab tabulations(WindowCdmEditor _w) {
        ChangeTabulationsEvent ev_ = (ChangeTabulationsEvent) ((MockMenuItem) _w.getTabulationsMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getOutputDialogTabs();
    }

    protected static OutputDialogLanguage language(WindowCdmEditor _w) {
        ChangeLanguageEvent ev_ = (ChangeLanguageEvent) ((MockMenuItem) _w.getLanguageMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getOutputDialogLanguage();
    }

    protected static CdmParameterSoftDialog softParams(WindowCdmEditor _w) {
        CdmParameterSoftEvent ev_ = (CdmParameterSoftEvent) ((MockMenuItem) _w.getSoftParamsMenu()).getActionListeners().get(0);
        ev_.action();
        return ev_.getParameterSoftDialog();
    }

}
