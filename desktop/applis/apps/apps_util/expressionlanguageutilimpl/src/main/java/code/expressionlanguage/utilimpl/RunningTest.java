package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.stream.BytesInfo;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RunningTest implements Runnable {
    private String fileConfOrContent;
    private ProgressingTests progressingTests;
    private boolean file;
    private FileInfos infos;
    private StringList languages;
    private AbsBuildLightResultContextNext buildLightResultContextNext;
    private AbsFileBuilderListGene factory;

    private RunningTest() {
    }
    public static RunningTest newFromFile(StringList _lgs, String _fileConf, ProgressingTests _progressingTests, FileInfos _infos, AbsBuildLightResultContextNext _b, AbsFileBuilderListGene _fact) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        r_.infos = _infos;
        r_.languages = _lgs;
        r_.buildLightResultContextNext = _b;
        r_.factory = _fact;
        return r_;
    }

    public static RunningTest newFromContent(StringList _lgs, String _fileConf, ProgressingTests _progressingTests, FileInfos _infos, AbsBuildLightResultContextNext _b, AbsFileBuilderListGene _fact) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.infos = _infos;
        r_.languages = _lgs;
        r_.buildLightResultContextNext = _b;
        r_.factory = _fact;
        return r_;
    }
    @Override
    public void run() {
        String content_ = retrieve();
        launchByConfContent(languages,content_,progressingTests,infos, buildLightResultContextNext,factory);
    }

    public String retrieve() {
        String content_;
        if (file) {
            content_ = infos.getReporter().conf(fileConfOrContent);
        } else {
            content_ = infos.getReporter().confTxt(fileConfOrContent);
        }
        return content_;
    }

    public ProgressingTests getProgressingTests() {
        return progressingTests;
    }

    public static boolean launchByConfContent(StringList _lgs, String _content, ProgressingTests _progressingTests, FileInfos _infos, AbsBuildLightResultContextNext _lgname, AbsFileBuilderListGene _fact) {
        StringList linesFiles_ = ExecutingOptions.lines(StringUtil.nullToEmpty(_content));
        if (linesFiles_.size() < 2) {
            return false;
        }
        String archive_ = linesFiles_.first();
        ReadFiles result_ = _infos.getReporter().getFiles(archive_);
        if (result_.getType() == OutputType.NOTHING) {
            return false;
        }
        ManageOptions manage_ = new ManageOptions(_lgs, linesFiles_,_progressingTests);
        ExecutingOptions exec_ = manage_.getEx();
        Options opt_ = manage_.getOptions();
        StringMap<String> list_ = tryGetSrc(archive_, exec_, _infos, result_);
        if (list_ == null) {
            return false;
        }
        opt_.setReadOnly(true);
        CustContextFactory.executeDefKw(opt_,exec_,list_,_progressingTests, new LgNamesGui(_infos,_progressingTests.getFactory().getInterceptor()), _lgname,_fact);
        return true;
    }

    public static ResultContext baseValidateMemoDef(String _lg, AbstractInterceptor _interceptor, AbstractLightProgramInfos _factories, AbsFileBuilderListGene _fact, AbsBuildLightResultContextNext _b) {
        FileInfos file_ = MemoResultContextNext.fileInfos(_factories, null, new MemInputFiles(new byte[0], new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        Options opts_ = new Options();
        opts_.setReadOnly(true);
        ExecutingOptions ex_ = exec(_factories, _interceptor, _lg);
        LgNamesGui stds_ = new LgNamesGui(file_, ex_.getInterceptor());
        CustContextFactory.preinit(opts_, ex_, mess_, kwl_, stds_);
        CustContextFactory.parts(ex_,stds_,new StringList());
        AnalyzedPageEl page_ = CustContextFactory.mapping(stds_);
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(stds_.getContent(), stds_.getGuiAliases(), stds_.getExecContent().getCustAliases());
        CustContextFactory.updateBuilders(stds_,page_, _fact);
        Forwards forwards_ = CustContextFactory.fwd(opts_, stds_, page_, fileBuilder_);
        ContextFactory.beforeBuild(forwards_, mess_, kwl_,stds_.getExecContent().getCustAliases().defComments(), opts_,stds_.getContent(), page_);
        ContextFactory.build(forwards_, kwl_, opts_,page_, _b);
        return new ResultContext(page_, forwards_);
    }

    public static ResultContext baseValidateMemo(String _lg, StringList _otherLines, AbstractInterceptor _interceptor, AbstractLightProgramInfos _factories, AbstractIssuer _issuer, AbsBuildLightResultContextNext _a, AbsFileBuilderListGene _fact) {
        StringList ls_ = new StringList();
        ls_.add("");
        ls_.add(_lg);
        ls_.addAllElts(_otherLines);
        FileInfos file_ = MemoResultContextNext.fileInfos(_factories, _issuer, new MemInputFiles(StringUtil.encode(StringUtil.join(ls_, '\n')), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        Options opts_ = new Options();
        ExecutingOptions ex_ = exec(_factories, _interceptor, _lg);
        ExecutingOptions.setupOptionals(0,opts_,ex_,_otherLines);
        return CustContextFactory.stds(file_, ex_, opts_, _a,_fact);
    }

    private static ExecutingOptions exec(AbstractLightProgramInfos _factories, AbstractInterceptor _interceptor, String _lg) {
        CdmFactory cdmFactory_ = new CdmFactory(_factories, _interceptor);
        ExecutingOptions ex_ = new ExecutingOptions();
        ex_.setLightProgramInfos(_factories);
        ex_.setLg(_lg);
        ex_.setListGenerator(cdmFactory_);
        return ex_;
    }

    public static ResultContext nextValidateMemo(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer) {
        if (_base.getPageEl().notAllEmptyErrors()) {
            return _base;
        }
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _base.getForwards().getGenerator();
        MemoResultContextNext memo_ = new MemoResultContextNext(_base, _input, _issuer);
        return nextValidate(_base, lg_, memo_,memo_);
    }

    public static ResultContext nextValidateMemoQuick(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer) {
        return nextValidateMemoQuick(_base,_input,_issuer,new StringMap<String>());
    }
    public static ResultContext nextValidateMemoQuick(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer, StringMap<String> _addedFiles) {
        if (_base.getPageEl().notAllEmptyErrors()) {
            return _base;
        }
        return nextValidateQuick(new MemoResultContextNext(_base,_input,_issuer),_base, _addedFiles);
    }


    public static ResultContext nextValidate(ResultContext _base, LgNamesWithNewAliases _lg, AbsLightResultContextNext _b, AbsLightMemoResultContextNext _n) {
        ResultContext res_ = _b.next(_base, new StringMap<String>(), new DefStackStopper());
        if (res_ == null) {
            return _base;
        }
        ResultContext.fwd(res_,_n.generate(_lg.getExecContent().getInfos().getThreadFactory().newAtomicBoolean()));
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext nextValidateQuick(AbsLightResultContextNext _a, ResultContext _base, StringMap<String> _addedFiles) {
        ResultContext ana_ = _a.next(_base, _addedFiles, new DefStackStopper());
        if (ana_ == null) {
            return _base;
        }
        return ana_;
    }

    public static StringMap<String> tryGetSrc(String _archive, ExecutingOptions _exec, FileInfos _infos,ReadFiles _results) {
        AbstractReporter reporter_ = _infos.getReporter();
        String folderPath_ = reporter_.getFolderPath(_archive,_exec,_results);
        if (reporter_.koPaths(folderPath_, _exec)) {
            return null;
        }
        StringMap<String> readZip_ = reporter_.getSrc(_archive, _exec, _results);
        ReadBinFiles resultOs_ = reporter_.getBinFiles(folderPath_+_exec.getFiles());

        _infos.getFileSystem().build(_exec, resultOs_);
        return readZip_;
    }

}
