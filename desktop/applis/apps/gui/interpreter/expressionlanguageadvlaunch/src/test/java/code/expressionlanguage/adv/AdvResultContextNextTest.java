package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

public final class AdvResultContextNextTest extends EquallableElAdvUtil {
//    @Test
//    public void window1() {
//        MockProgramInfos pr_ = advPr();
//        WindowCdmEditor w_ = updatedAdv(pr_);
//        w_.getFuture().attendre();
//        ((LgNamesWithNewAliases)w_.getBaseResult().getForwards().getGenerator()).getExecContent().getExecutingOptions().setMainThread("//");
//        WindowExpressionEditor e_ = geneSecAlready(w_);
//        e_.getTree().select(e_.getTree().getRoot());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
//        AbsDebuggerGui b_ = e_.getSessionExp();
//        menuExp(e_,b_);
//        assertEq(0,found(b_).size());
//    }
//    @Test
//    public void window2() {
//        MockProgramInfos pr_ = advPr();
//        WindowCdmEditor w_ = updatedAdv(pr_);
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
//        w_.getFuture().attendre();
//        WindowExpressionEditor e_ = geneSecAlready(w_);
//        e_.getTree().select(e_.getTree().getRoot());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
//        AbsDebuggerGui b_ = e_.getSessionExp();
//        menuExp(e_,b_);
//        assertEq(0,found(b_).size());
//    }
//    @Test
//    public void window3() {
//        MockProgramInfos pr_ = advPr();
//        WindowCdmEditor w_ = updatedAdv(pr_);
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
//        w_.getFuture().attendre();
//        WindowExpressionEditor e_ = geneSecAlready(w_);
//        e_.getTree().select(e_.getTree().getRoot());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
//        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
//        AbsDebuggerGui b_ = buildExpAdvCore(w_);
//        ManageOptions o_ = optBad(b_);
//        ResultContext r_ = res(b_, o_);
//        guiAna(r_,b_,o_,new StringMap<String>());
//        b_.getResultContextNext().next(r_,r_);
//        assertTrue(classesFilter(b_).getList().isEmpty());
//    }
//    @Test
//    public void failSrcFile() {
//        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAdv( "");
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
//        w_.getSoftParams().setLines(ls_);
//        analyzeBad(w_);
//        refreshClasses(w_);
//        assertEq(0,tabEditor(w_).getDico().size());
//    }
//    @Test
//    public void failSrcFile2() {
//        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAdv( "");
//        StringList ls_ = new StringList(w_.getSoftParams().getLines());
//        ls_.add("keyWords=If=;");
////        w_.getSoftParams().setLines(ls_);
//        analyzeBad2(w_);
//        refreshClasses(w_);
//        assertEq(0,tabEditor(w_).getDico().size());
//    }
//    @Test
//    public void noAna() {
//        WindowCdmEditor w_ = newWindowLoadDefExpWorkspaceAlreadyAdv( "src//bad","public class pkg.ExClass:AbsStringReplacer{Second s;public StringSegment index(String t,int i){return t.indexOf('C',i)>-1?new(begin:t.indexOf('C',i),end:t.indexOf('C',i)+1):null;}public String replace(String t, int i, int b, int e){return \"c\";}}","public class pkg.Second{}");
//        StreamTextFile.saveTextFile("/project/sources/exp/0.txt","",w_.getCommonFrame().getFrames().getStreams());
//        ((MockMenuItem)w_.getFolderExpressionMenu()).getActionListeners().get(0).action();
//        WindowExpressionEditor s_ = w_.getExpressionEditors().get(0);
//        s_.setLimitSymbol(1);
//        s_.getTree().select(s_.getTree().getRoot());
//        s_.getTree().select(s_.getTree().getRoot().getFirstChild().getNextSibling());
//        s_.getTabs().get(0).getCenter().select(0,0);
//        currentElement(s_.getTabs().get(0));
//        w_.setBaseResult(null);
//        new RefreshLocationTask(w_.getPanel(),w_,new ResultRowSrcLocationList(AnalyzedPageEl.setInnerAnalyzing(),"",0,new CustList<SrcFileLocation>(),new CustList<RowSrcLocation>())).run();
//        assertEq(0,s_.getSymbols().size());
//    }
    @Test
    public void window() {
        MockProgramInfos pr_ = genePr();
        WindowCdmEditor w_ = updatedAdv(pr_);
        w_.getFuture().attendre();
        w_.getFutureDbgInit().attendre();
        WindowExpressionEditor e_ = geneSecAlready(w_);
        save(pr_,"src/file.txt","public class pkg.Ex {static int v;public static that int exmeth(String[] _v){return that(v);}}");
        e_.getTree().select(e_.getTree().getRoot());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild());
        e_.getTree().select(e_.getTree().getRoot().getFirstChild().getFirstChild().getFirstChild());
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        AbsDebuggerGui b_ = e_.getSessionSingleMain();
        menuSingleMain(e_,b_);
        vararg(b_).setSelected(false);
        retVal(b_).setSelected(true);
        AutoCompleteDocument cl_ = classesFilter(b_);
        cl_.getTextField().setText("pkg.Ex");
        cl_.enterEvent();
        AutoCompleteDocument meths_ = methodFilter(b_);
        meths_.getTextField().setText("exmeth");
        meths_.enterEvent();
        assertFalse(methods(b_).isEmpty());
    }
    private AbsCustCheckBox vararg(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getVararg();
    }
    private AbsCustCheckBox param(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getParamRef();
    }

    private AbsCustCheckBox retVal(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getRetRef();
    }
    private CustList<ExecOverridableBlock> methods(AbsDebuggerGui _b) {
        String idCl_ = ((InitDebGuiImpl)_b).getClassesField().getText();
        ExecRootBlock type_ = ((InitDebGuiImpl)_b).selectedType(idCl_);
        return ((InitDebGuiImpl)_b).methods(type_);
    }
    private AutoCompleteDocument methodFilter(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getClMethFieldAutoComplete();
    }

    protected static WindowCdmEditor updatedAdv(AbstractProgramInfos _pr) {
        WindowCdmEditor w_ = quickCreateAdv(_pr);
        w_.updateCommentsInit(new StringList());
        return w_;
    }

    public static WindowCdmEditor quickCreateAdv(AbstractProgramInfos _pr) {
        _pr.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        _pr.setLanguage(FileInfos.EN);
        update((MockProgramInfos) _pr);
        return windowAdv(_pr);
    }


    public static WindowCdmEditor windowAdv(AbstractProgramInfos _pr) {
        CdmFactory fact_ = new CdmFactory(_pr, new MockInterceptor());
//        _pr.getCounts().addEntry(WindowCdmEditor.CDM_EDITOR,_pr.getThreadFactory().newAtomicInteger());
        WindowCdmEditor w_ = new WindowCdmEditor(_pr,fact_, new LanguagesButtonsPair(null,null,null));
        AdvResultContextNext res_ = new AdvResultContextNext(w_, _pr, fact_);
        w_.setMainResultNext(res_);
        w_.setResultContextNext(res_);
        return w_;
    }
    private void callers(WindowExpressionEditor _s) {
        AbsPanel p_ = (AbsPanel) _s.getPanelSymbols().getComponent(1);
        ((MockPlainButton)(p_.getComponent(1))).getActionListeners().get(0).action();
    }
    protected static void analyzeBad(WindowCdmEditor _w) {
        _w.getFuture().attendre();
        AbsActionListener ev_ = ((MockMenuItem) _w.getAnalyzeMenu()).getActionListeners().get(0);
        ev_.action();
    }
    protected static void analyzeBad2(WindowCdmEditor _w) {
        _w.getFuture().attendre();
        ((LgNamesWithNewAliases)_w.getBaseResult().getForwards().getGenerator()).getExecContent().getExecutingOptions().setMainThread("//");
        AbsActionListener ev_ = ((MockMenuItem) _w.getAnalyzeMenu()).getActionListeners().get(0);
        ev_.action();
    }
    private AutoCompleteDocument classesFilter(AbsDebuggerGui _b) {
        return ((InitDebGuiImpl)_b).getClassesFieldAutoComplete();
    }
    private CustList<SegmentFindPart> found(AbsDebuggerGui _b) {
        return ((ExpDebGuiImpl)_b).getFound();
    }
    public static AbsDebuggerGui buildExpAdv(WindowCdmEditor _w) {
        AbstractProgramInfos pr_ = _w.getFrames();
        SampleMockResultContextNext m_ = new SampleMockResultContextNext(_w,_w.getFrames(),_w.getFactory());
        return new ExpDebGuiImpl(new ExpMenuFrameInteract(pr_.getCompoFactory().newMenuItem()),m_, pr_,_w.getFactory());
    }
    public static AbsDebuggerGui buildExpAdvCore(WindowCdmEditor _w) {
        AbstractProgramInfos pr_ = _w.getFrames();
        SampleMockResultContextNext m_ = new SampleMockResultContextNext(_w,_w.getFrames(),_w.getFactory());
        return new InitDebGuiImpl(new ExpMenuFrameInteract(pr_.getCompoFactory().newMenuItem()),m_, pr_,_w.getFactory());
    }
    public static WindowCdmEditor newWindowLoadDefExpWorkspaceAdv(String _expSrc) {
        return newWindowLoadDefExpWorkspaceAdv("src",_expSrc);
    }
    public static WindowCdmEditor newWindowLoadDefExpWorkspaceAdv(String _srcFolder, String _expSrc) {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        pr_.getFileCoreStream().newFile("/project/sources/exp/src/").mkdirs();
        StreamTextFile.saveTextFile("/project/sources/exp/src/file_exp.txt",_expSrc,pr_.getStreams());
        WindowCdmEditor w_ = windowLoadDefAdv(pr_);
        ManageOptions copy_ = w_.manage(w_.getSoftParams().getLines());
        copy_.getEx().setAccess("/project/sources/exp/");
        copy_.getEx().setSrcFolder(_srcFolder);
        w_.getSoftParams().setFolderExpression("/project/sources/exp");
        w_.getSoftParams().setLines(WindowCdmEditor.linesConf(copy_));
        return w_;
    }
    public static WindowCdmEditor windowLoadDefAdv(AbstractProgramInfos _pr) {
        WindowCdmEditor w_ = updatedAdv(_pr);
        AbsTreeGui tr_ = w_.getFolderSystem();
        tr_.select(tr_.getRoot().getFirstChild());
        ((MockMenuItem)w_.getCreate()).getActionListeners().get(0).action();
        w_.getTargetName().setText("file.txt");
        ((MockPlainButton)w_.getValidateDialog()).getActionListeners().get(0).action();
        w_.getTabs().get(0).getWholeWord().setSelected(false);
        return w_;
    }

    public static WindowCdmEditor newWindowLoadDefExpWorkspaceAlreadyAdv(String _expSrc, String _expSrc2) {
        return newWindowLoadDefExpWorkspaceAlreadyAdv("src",_expSrc,_expSrc2);
    }
    public static WindowCdmEditor newWindowLoadDefExpWorkspaceAlreadyAdv(String _srcFolder, String _expSrc, String _expSrc2) {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        pr_.getFileCoreStream().newFile("/project/sources/exp/src/").mkdirs();
        StreamTextFile.saveTextFile("/project/sources/exp/src/file_exp.txt",_expSrc,pr_.getStreams());
        StreamTextFile.saveTextFile("/project/sources/exp/src/file_exp2.txt",_expSrc2,pr_.getStreams());
        WindowCdmEditor w_ = windowLoadDefAdv(pr_);
        ManageOptions copy_ = w_.manage(w_.getSoftParams().getLines());
        copy_.getEx().setAccess("/project/sources/exp/");
        copy_.getEx().setSrcFolder(_srcFolder);
        w_.getSoftParams().setFolderExpression("/project/sources/exp");
        w_.getSoftParams().setLines(WindowCdmEditor.linesConf(copy_));
        return w_;
    }
    private static MockProgramInfos advPr() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("src/file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
//        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());

        CdmParameterSoftModel c_ = new CdmParameterSoftModel();
        c_.setExecConf(current_);
        c_.getOpenedFiles().add("src/file.txt");
        c_.setFolderExpression("/project/sources");
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(c_),pr_.getStreams());


        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        pr_.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        pr_.setLanguage(FileInfos.EN);
        update(pr_);
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        return pr_;
    }
    private static MockProgramInfos advPrKw() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
        String current_ = "/editor/conf.xml";
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(current_,new StringList("src/file.txt")),pr_.getStreams());
        StreamFolderFile.makeParent(current_,pr_.getFileCoreStream());
        StringList lines_ = new StringList();
        lines_.add("/project/sources");
        lines_.add("en");
        lines_.add("");
//        StreamTextFile.saveTextFile(current_, StringUtil.join(lines_,'\n'),pr_.getStreams());

        CdmParameterSoftModel c_ = new CdmParameterSoftModel();
        c_.setExecConf(current_);
        c_.getOpenedFiles().add("src/file.txt");
        c_.setFolderExpression("/project/sources");
        StreamTextFile.saveTextFile(WindowCdmEditor.getTempDefConf(pr_),WindowCdmEditor.buildDefConfFile(c_),pr_.getStreams());


        pr_.getFileCoreStream().newFile("/project/sources/src/").mkdirs();
        pr_.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        pr_.setLanguage(FileInfos.EN);
        update(pr_);
        pr_.getFileCoreStream().newFile("/project/sources/exp/errors/").mkdirs();
        pr_.getFileCoreStream().newFile("/project/sources/exp/files/").mkdirs();
        return pr_;
    }
}
