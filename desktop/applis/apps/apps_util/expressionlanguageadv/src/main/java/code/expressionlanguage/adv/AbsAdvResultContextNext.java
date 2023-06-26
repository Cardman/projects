package code.expressionlanguage.adv;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.gui.unit.UnitIssuer;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilfiles.DefaultFileSystem;
import code.expressionlanguage.utilfiles.DefaultLogger;
import code.expressionlanguage.utilfiles.DefaultReporter;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.core.ReadFiles;
import code.threads.AbstractAtomicBoolean;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;

public abstract class AbsAdvResultContextNext implements AbsResultContextNext {
    private final AbstractProgramInfos frames;
    private final CdmFactory factory;
    protected AbsAdvResultContextNext(AbstractProgramInfos _frames, CdmFactory _progressingTests) {
        frames = _frames;
        factory = _progressingTests;
    }

    protected ResultContext init(Options _opt,ManageOptions _man,UnitIssuer _issuer){
        AbstractNameValidating validator_ = frames.getValidator();
        DefaultUniformingString un_ = new DefaultUniformingString();
        FileInfos file_ = new FileInfos(new DefaultLogger(_issuer,frames.getFileCoreStream(),frames.getStreams()),
                new DefaultFileSystem(un_,validator_,frames.getFileCoreStream(),frames.getStreams()), new DefaultReporter(factory.getProgramInfos(),validator_, un_, false,new TechInfos(frames.getThreadFactory(),frames.getStreams()),frames.getFileCoreStream()), frames.getGenerator(), frames.getStreams().getZipFact(), frames.getThreadFactory());
        _man.getEx().setLightProgramInfos(frames);
        _man.getEx().setListGenerator(factory);
        return CustContextFactory.stds(file_, _man.getEx(),_opt);
    }

    @Override
    public ResultContext next(ResultContext _r, ResultContext _u) {
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _r.getForwards().getGenerator();
        Forwards forwards_ = CustContextFactory.fwd(_u.getForwards().getOptions(), lg_, _u.getForwards().getFileBuilder());
        forwards_.getClasses().getCommon().setStaticFields(_u.getPageEl().getStaticFields());
        return new ResultContext(_u.getPageEl(),forwards_,_u.getReportedMessages());
    }

    @Override
    public StringMap<String> files(ResultContext _r, StringMap<String> _files) {
        if (_r == null) {
            return new StringMap<String>();
        }
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _r.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        String archive_ = exec_.getAccess();
        FileInfos file_ = lg_.getExecContent().getInfos();
        ReadFiles result_ = file_.getReporter().getFiles(archive_);
        result_.getZipFiles().putAllMap(_files);
        AbstractReporter reporter_ = file_.getReporter();
        return reporter_.getSrc(archive_, exec_, result_);
    }

    @Override
    public AnalyzedPageEl nextAna(ResultContext _r, StringMap<String> _files) {
        if (_r == null) {
            return AnalyzedPageEl.setInnerAnalyzing();
        }
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _r.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        return ResultContext.def(_r.getPageEl(),_files,exec_.getSrcFolder());
    }

    @Override
    public ResultContext next(ResultContext _r, StringMap<String> _files) {
        if (_r == null) {
            return null;
        }
        if (_r.getPageEl().notAllEmptyErrors()) {
            return null;
        }
        LgNamesWithNewAliases lg_ = (LgNamesWithNewAliases) _r.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        String archive_ = exec_.getAccess();
        FileInfos file_ = lg_.getExecContent().getInfos();
        ReadFiles result_ = file_.getReporter().getFiles(archive_);
        result_.getZipFiles().putAllMap(_files);
        StringMap<String> list_ = RunningTest.tryGetSrc(archive_, exec_, file_, result_);
        if (list_ == null) {
            return null;
        }
        ResultContext d_ = ResultContext.def(_r, list_, exec_.getSrcFolder());
        CustContextFactory.reportErrors(_r.getForwards().getOptions(), lg_.getExecContent().getExecutingOptions(), d_.getReportedMessages(), file_);
        return d_;
    }

    @Override
    public AbsAdvContextGenerator generate() {
        return generateAdv(frames.getThreadFactory().newAtomicBoolean());
    }

    @Override
    public AbsAdvContextGenerator generateAdv(AbstractAtomicBoolean _at) {
        return new AdvContextGenerator(_at);
    }
}
