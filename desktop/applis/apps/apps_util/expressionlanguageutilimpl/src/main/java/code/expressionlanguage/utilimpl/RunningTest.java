package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.AbstractAdvGraphicListGeneratorStruct;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
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

    private RunningTest() {
    }
    public static RunningTest newFromFile(StringList _lgs,String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.file = true;
        r_.infos = _infos;
        r_.languages = _lgs;
        return r_;
    }

    public static RunningTest newFromContent(StringList _lgs,String _fileConf, ProgressingTests _progressingTests, FileInfos _infos) {
        RunningTest r_ = new RunningTest();
        r_.fileConfOrContent = _fileConf;
        r_.progressingTests = _progressingTests;
        r_.infos = _infos;
        r_.languages = _lgs;
        return r_;
    }
    @Override
    public void run() {
        String content_ = retrieve();
        launchByConfContent(languages,content_,progressingTests,infos);
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

    public static boolean launchByConfContent(StringList _lgs,String _content, ProgressingTests _progressingTests, FileInfos _infos) {
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
        CustContextFactory.executeDefKw(opt_,exec_,list_,_progressingTests, new LgNamesGui(_infos,_progressingTests.getFactory().getInterceptor()));
        return true;
    }

    public static ResultContext baseValidateMemoDef(String _lg, AbstractInterceptor _interceptor, AbstractLightProgramInfos _factories, AbstractAdvGraphicListGenerator _adv, AbstractAdvGraphicListGeneratorStruct _cr) {
        FileInfos file_ = MemoResultContextNext.fileInfos(_factories, null, new MemInputFiles(new byte[0], new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        Options opts_ = new Options();
        opts_.setReadOnly(true);
        ExecutingOptions ex_ = exec(_factories, _interceptor, _adv, _cr, _lg);
        LgNamesGui stds_ = new LgNamesGui(file_, ex_.getListGenerator().getInterceptor());
        CustContextFactory.preinit(opts_, ex_, mess_, kwl_, stds_);
        CustContextFactory.parts(ex_,stds_,new StringList());
        AnalyzedPageEl page_ = CustContextFactory.mapping(stds_);
        Forwards forwards_ = CustContextFactory.builder(opts_, stds_, page_);
        ContextFactory.beforeBuild(forwards_, mess_, kwl_,stds_.getExecContent().getCustAliases().defComments(), opts_,stds_.getContent(), page_);
        ContextFactory.build(forwards_, kwl_, opts_,page_);
        ClassesUtil.buildCoreBracesBodies(page_);
        return new ResultContext(page_, forwards_, page_.getMessages());
    }

    public static ResultContext baseValidateMemo(String _lg, StringList _otherLines, AbstractInterceptor _interceptor, AbstractLightProgramInfos _factories, AbstractAdvGraphicListGenerator _adv, AbstractAdvGraphicListGeneratorStruct _cr, AbstractIssuer _issuer) {
        StringList ls_ = new StringList();
        ls_.add("");
        ls_.add(_lg);
        ls_.addAllElts(_otherLines);
        FileInfos file_ = MemoResultContextNext.fileInfos(_factories, _issuer, new MemInputFiles(StringUtil.encode(StringUtil.join(ls_, '\n')), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        Options opts_ = new Options();
        ExecutingOptions ex_ = exec(_factories, _interceptor, _adv, _cr, _lg);
        ExecutingOptions.setupOptionals(0,opts_,ex_,_otherLines);
        return CustContextFactory.stds(file_, ex_, opts_);
    }

    private static ExecutingOptions exec(AbstractLightProgramInfos _factories, AbstractInterceptor _interceptor, AbstractAdvGraphicListGenerator _adv, AbstractAdvGraphicListGeneratorStruct _cr, String _lg) {
        CdmFactory cdmFactory_ = new CdmFactory(_factories, _interceptor, _adv, _cr);
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
        LgNamesGui lg_ = (LgNamesGui) _base.getForwards().getGenerator();
        return nextValidate(_base, lg_, new MemoResultContextNext(_base,_input,_issuer));
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


    public static ResultContext nextValidate(ResultContext _base, LgNamesWithNewAliases _lg, AbsLightResultContextNext _n) {
        ResultContext res_ = _n.next(_base, new StringMap<String>());
        if (res_ == null) {
            return _base;
        }
        if (res_.getPageEl().notAllEmptyErrors()) {
            return res_;
        }
        AnalyzedPageEl fwd_ = res_.getPageEl();
        Forwards f_ = res_.getForwards();
        ForwardInfos.generalForward(fwd_,f_);
        ContextEl ctx_ =  new GuiContextEl(_lg.getExecContent().getInfos().getThreadFactory().newAtomicBoolean(),null,_lg.newContextCommon(f_.getOptions(),f_),_lg.args());
        Classes.forwardAndClear(ctx_);
        res_.setContext(ctx_);
        ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getForwards().getOptions());
        return res_;
    }

    public static ResultContext nextValidateQuick(AbsLightResultContextNext _a, ResultContext _base, StringMap<String> _addedFiles) {
        ResultContext ana_ = _a.next(_base, _addedFiles);
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
