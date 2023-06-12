package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.*;
import code.gui.AbstractAdvGraphicListGeneratorStruct;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractLightProgramInfos;
import code.stream.BytesInfo;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.EntryCust;
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
        FileInfos file_ = fileInfos(_factories, null, new MemInputFiles(new byte[0], new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
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
        FileInfos file_ = fileInfos(_factories, _issuer, new MemInputFiles(StringUtil.encode(StringUtil.join(ls_, '\n')), new BytesInfo(new byte[0], true), new BytesInfo(new byte[0], true)));
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        Options opts_ = new Options();
        ExecutingOptions ex_ = exec(_factories, _interceptor, _adv, _cr, _lg);
        ExecutingOptions.setupOptionals(0,opts_,ex_,_otherLines);
        return CustContextFactory.stds(file_, ex_, opts_, mess_, kwl_);
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
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        MemoryReporter m_ = (MemoryReporter) lg_.getExecContent().getInfos().getReporter();
        MemInputFiles src_ = new MemInputFiles(m_.getConf(),_input.getSrc(),_input.getFiles());
        FileInfos file_ = fileInfos(exec_.getLightProgramInfos(), _issuer, src_);
        return nextValidate(_base, lg_, exec_, file_);
    }

    public static ResultContext nextValidateMemoQuick(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer) {
        return nextValidateMemoQuick(_base,_input,_issuer,new StringMap<String>());
    }
    public static ResultContext nextValidateMemoQuick(ResultContext _base, MemInputFiles _input, AbstractIssuer _issuer, StringMap<String> _addedFiles) {
        if (_base.getPageEl().notAllEmptyErrors()) {
            return _base;
        }
        LgNamesGui lg_ = (LgNamesGui) _base.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        MemoryReporter m_ = (MemoryReporter) lg_.getExecContent().getInfos().getReporter();
        MemInputFiles src_ = new MemInputFiles(m_.getConf(),_input.getSrc(),_input.getFiles());
        FileInfos file_ = fileInfos(exec_.getLightProgramInfos(), _issuer, src_);
        return nextValidateQuick(_base, lg_, exec_, file_, _addedFiles);
    }

    private static FileInfos fileInfos(AbstractLightProgramInfos _frames, AbstractIssuer _issuer, MemInputFiles _input) {
        AbstractNameValidating validator_ = _frames.getValidator();
        return FileInfos.buildMemoryFromFile(_frames, _frames.getGenerator(),
                validator_, _issuer, _input, _frames.getZipFact(), _frames.getThreadFactory());
    }


    public static ResultContext nextValidate(ResultContext _base, LgNamesGui _lg, ExecutingOptions _exec, FileInfos _file) {
        String archive_ = _exec.getAccess();
        ReadFiles result_ = _file.getReporter().getFiles(archive_);
        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, _exec, _file, result_);
        if (list_ == null) {
            return _base;
        }
        StringMap<String> srcFiles_ = ContextFactory.filter(list_, _exec.getSrcFolder());
        AnalyzedPageEl copy_ = AnalyzedPageEl.copy(_base.getPageEl());
        copy_.addResources(list_);
        AnalyzedPageEl resultAna_ = ClassesUtil.buildUserCode(srcFiles_, copy_);
        Classes.postValidate(resultAna_);
        Forwards forwards_ = CustContextFactory.fwd(_base.getForwards().getOptions(), _lg, _base.getForwards().getFileBuilder());
        forwards_.getClasses().getCommon().setStaticFields(resultAna_.getStaticFields());
        ResultContext res_ = new ResultContext(resultAna_, forwards_, resultAna_.getMessages());
        if (resultAna_.notAllEmptyErrors()) {
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

    public static ResultContext nextValidateQuick(ResultContext _base, LgNamesGui _lg, ExecutingOptions _exec, FileInfos _file, StringMap<String> _addedFiles) {
        AnalyzedPageEl ana_ = nextValidateQuickAna(_base, _exec, _file, _addedFiles);
        if (ana_ == null) {
            return _base;
        }
        Forwards forwards_ = CustContextFactory.fwd(_base.getForwards().getOptions(), _lg, _base.getForwards().getFileBuilder());
        forwards_.getClasses().getCommon().setStaticFields(ana_.getStaticFields());
        return new ResultContext(ana_, forwards_, ana_.getMessages());
    }

    public static AnalyzedPageEl nextValidateQuickAna(ResultContext _base, ExecutingOptions _exec, FileInfos _file, StringMap<String> _addedFiles) {
        String archive_ = _exec.getAccess();
        ReadFiles result_ = _file.getReporter().getFiles(archive_);
        for (EntryCust<String,String> e: _addedFiles.entryList()) {
            result_.getZipFiles().put(e.getKey(),e.getValue());
        }
        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, _exec, _file, result_);
        if (list_ == null) {
            return null;
        }
        StringMap<String> srcFiles_ = ContextFactory.filter(list_, _exec.getSrcFolder());
        AnalyzedPageEl copy_ = AnalyzedPageEl.copy(_base.getPageEl());
        copy_.addResources(list_);
        AnalyzedPageEl resultAna_ = ClassesUtil.buildUserCode(srcFiles_, copy_);
        Classes.postValidate(resultAna_);
        return resultAna_;
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
