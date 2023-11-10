package code.expressionlanguage.utilimpl;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultContext buildDefKw(Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, AbsBuildLightResultContextNext _lgnames, AbsFileBuilderListGene _fact, AbsAdvContextGenerator _abs) {
        return customer(_exec, _files, predefined(_options, _exec, _undefinedLgNames, _files, new StringList(), _lgnames, _fact), _abs);
    }
    public static void executeDefKw(Options _options, ExecutingOptions _exec, StringMap<String> _files, ProgressingTests _progressingTests, LgNamesGui _stds, AbsBuildLightResultContextNext _lgname, AbsFileBuilderListGene _fact) {
        execute(_options,_exec, _stds,_files,_progressingTests, _lgname,_fact);
    }

    public static void preinit(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _kwl, LgNamesGui _aliases) {
        _aliases.getExecContent().updateTranslations(_exec.getLightProgramInfos().getTranslations(),_exec.getLightProgramInfos().getLanguage(),_exec.getLg());
        preinitAliases(_exec, _mess, _kwl, _aliases.getContent(), _aliases.getExecContent().getCustAliases(), _aliases.getGuiAliases());
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns(), _aliases.getExecContent().getCustAliases().extractMessagesKeys()));
    }

    public static void preinitAliases(ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _kwl, LgNamesContent _base, CustAliases _util, GuiAliases _gui) {
        _util.messages(_mess, _exec.getMessages());
        _util.keyWord(_kwl, _exec.getKeyWords());
        _kwl.initSupplDigits();
        aliases(_exec, _base, _util, _gui);
    }

    public static void aliases(ExecutingOptions _exec, LgNamesContent _base, CustAliases _util, GuiAliases _gui) {
        _util.otherAlias(_base, _exec.getAliases());
        StringMap<String> keys_ = LgNamesGui.extractAliasesKeys(_util);
        _gui.otherAliasGui(LgNamesGui.addon(_util),_exec.getAliases(),keys_);
    }

    public static void execute(Options _options, ExecutingOptions _exec,
                               LgNamesGui _definedLgNames, StringMap<String> _files,
                               ProgressingTests _progressingTests, AbsBuildLightResultContextNext _lgname, AbsFileBuilderListGene _fact) {
        AbstractAtomicBoolean stop_ = _definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean();
        _progressingTests.setStop(stop_);
        AbsAdvContextGenerator adv_ = _fact.gene(stop_);
        ResultContext res_ = buildDefKw(_options, _exec, _definedLgNames, _files, _lgname, _fact, adv_);
        ContextEl rCont_ = res_.getContext();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        FileInfos infos_ = _definedLgNames.getExecContent().getInfos();
        CustContextFactory.reportErrors(_options, _exec, reportedMessages_, infos_);
        if (!(rCont_ instanceof InterruptibleContextEl)) {
            _progressingTests.showErrors(reportedMessages_,_options,_exec,infos_);
            return;
        }
        _progressingTests.ctx((InterruptibleContextEl) rCont_);
        _progressingTests.showWarnings(_progressingTests.ctx(),reportedMessages_,_options,_exec,infos_);
        infos_.tryLogIssue("OK");
        String infoTest_ = _definedLgNames.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(rCont_,className_, new Argument()),rCont_,StackCall.newInstance(InitPhase.NOTHING,rCont_)).getValue());
        ((FieldableStruct)infoStruct_).getEntryStruct(new ClassField(infoTest_,_definedLgNames.getExecContent().getCustAliases().getAliasInfoTestNbThreads())).setStruct(new IntStruct(2));
        AbstractScheduledExecutorService sch_ = _definedLgNames.getExecContent().getCustAliases().getInfos().getThreadFactory().newScheduledExecutorService();
        ShowUpdates showUpdates_ = new ShowUpdates(infoStruct_, rCont_,_progressingTests,_definedLgNames);
        AbstractFuture abstractFuture_ = sch_.scheduleAtFixedRateNanos(showUpdates_, 0, 1);
        ExecTypeFunction pair_ = ((LgNamesWithNewAliases) rCont_.getStandards()).getExecContent().getExecutingBlocks().getExecuteMethodPair();
        Argument argMethod_ = new Argument(infoStruct_);
        ArgumentListCall argList_ = new ArgumentListCall(argMethod_);
        Argument arg_ = EventStruct.invoke(NullStruct.NULL_VALUE,
                rCont_, pair_, StackCall.newInstance(InitPhase.NOTHING,rCont_), argList_);
        abstractFuture_.cancel(false);
        sch_.shutdown();
        _progressingTests.finish(rCont_,infoStruct_, _definedLgNames);
        if (_options.isCovering()) {
            for (EntryCust<String,String> f:ExecFileBlock.export(rCont_).entryList()) {
                infos_.getReporter().coverFile(_exec, f.getKey(), f.getValue());
            }
        }
        _progressingTests.setResults(rCont_,_exec,Argument.getNullableValue(arg_), _definedLgNames);
    }
    public static void reportErrors(Options _options, ExecutingOptions _exec, ReportedMessages _reportedMessages, FileInfos _infos) {
        if (_options.isGettingErrors()) {
            for (EntryCust<String,String> f: _reportedMessages.getErrors().entryList()) {
                _infos.getReporter().errorFile(_exec, f.getKey(), f.getValue());
            }
        }
    }

    public static ResultContext predefined(Options _options, ExecutingOptions _exec, LgNamesGui _definedLgNames, StringMap<String> _files, StringList _mainArgs, AbsBuildLightResultContextNext _l, AbsFileBuilderListGene _fact) {
        AnalysisElementsBase e_ = elts(_exec, _options, _definedLgNames, _files, _mainArgs, _fact);
        return stds(e_.getMess(), _definedLgNames.getExecContent().getCustAliases().extractMessagesKeys(), _l, e_);
    }

    public static ResultContext customer(ExecutingOptions _exec, StringMap<String> _files, ResultContext _r, AbsLightContextGenerator _abs) {
        ResultContext res_ = ResultContext.def(_r, _files,  _exec.getSrcFolder());
        ResultContext.fwd(res_, _abs);
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext stds(FileInfos _file, ExecutingOptions _ex, Options _opts, boolean _light, AbsBuildLightResultContextNext _a, AbsFileBuilderListGene _fact) {
        _opts.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(_file, _ex.getInterceptor(),_light);
        AnalysisElementsBase e_ = elts(_ex, _opts, stds_, new StringMap<String>(), new StringList(),_fact);
        return stds(e_.getMess(), stds_.getExecContent().getCustAliases().extractMessagesKeys(), _a, e_);
    }

    private static AnalysisElementsBase elts(ExecutingOptions _ex, Options _opts, LgNamesGui _stds, StringMap<String> _files, StringList _parts, AbsFileBuilderListGene _fact) {
        AnalyzedPageEl page_ = page(_ex, _opts, _stds, _parts);
        return new AnalysisElementsBase(fwd(_opts, _stds, _files, page_,_fact), page_.getAnalysisMessages(), page_.getKeyWords(), _stds.getExecContent().getCustAliases().defComments(), _opts, _stds.getContent(), page_);
    }

    public static Forwards fwd(Options _opts, LgNamesGui _stds, StringMap<String> _files, AnalyzedPageEl _page, AbsFileBuilderListGene _fact) {
        return builder(_opts, _stds, _files, _page,_fact);
    }

    public static Forwards builder(Options _opts, LgNamesGui _stds, StringMap<String> _files, AnalyzedPageEl _page, AbsFileBuilderListGene _fact) {
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_stds.getContent(), _stds.getGuiAliases(), _stds.getExecContent().getCustAliases());
        updateBuilders(_stds, _page,_fact);
        return fwd(_opts, _stds, _files, _page, fileBuilder_);
    }

    public static void updateBuilders(LgNamesGui _stds, AnalyzedPageEl _page, AbsFileBuilderListGene _fact) {
        CustList<AbsAliasFileBuilder> builders_ = _fact.build(_stds);
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }

    public static Forwards fwd(Options _opts, LgNamesGui _stds, StringMap<String> _files, AnalyzedPageEl _page, GuiFileBuilder _fileBuilder) {
        Forwards forwards_ = fwd(_opts, _stds, _page, _fileBuilder);
        forwards_.getResources().addAllEntries(_files);
        return forwards_;
    }

    public static AnalyzedPageEl page(ExecutingOptions _ex, Options _opts, LgNamesGui _stds, StringList _parts) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        CustContextFactory.preinit(_opts, _ex, mess_, kwl_, _stds);
        CustContextFactory.parts(_ex, _stds, _parts);
        AnalyzedPageEl page_ = CustContextFactory.mapping(_stds);
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kwl_);
        return page_;
    }

    public static ResultContext stds(AnalysisMessages _mess, StringMap<String> _ma, AbsBuildLightResultContextNext _a, AnalysisElementsBase _elts) {
        AnalysisMessages.validateMessageContents(_mess.allMessages(_ma), _elts.getPage());
        _elts.setLightResultContextNext(_a);
        ContextFactory.validateStds(_elts);
        if (_elts.getPage().notAllEmptyErrors()) {
            return new ResultContext(_elts.getPage(), _elts.getFwd(), _elts.getPage().getMessages());
        }
        return new ResultContext(_elts.getPage(), _elts.getFwd());
    }
    public static void parts(ExecutingOptions _exec, LgNamesGui _definedLgNames, StringList _mainArgs) {
        _definedLgNames.getExecContent().setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(_mainArgs, _exec.getLightProgramInfos());
    }

    public static Forwards fwd(Options _options, LgNamesGui _definedLgNames, AnalyzedPageEl _page, AbstractFileBuilder _fileBuilder) {
        Forwards forwards_ = fwd(_options, _definedLgNames, _fileBuilder);
        _page.setLogErr(forwards_);
        return forwards_;
    }

    public static AnalyzedPageEl mapping(LgNamesWithNewAliases _definedLgNames) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(new AdvSymbolFactory(_definedLgNames.getExecContent().getCustAliases().getMathAdvAliases()));
        page_.setMappingKeyWords(_definedLgNames.getExecContent().getCustAliases().extractKeywordsKeys());
        StringMap<String> m_ = _definedLgNames.getExecContent().getCustAliases().extractAliasesKeys();
        m_.addAllEntries(LgNamesGui.extractAliasesKeys(_definedLgNames.getExecContent().getCustAliases()));
        page_.setMappingAliases(m_);
        return page_;
    }

    public static Forwards fwd(Options _options, LgNamesWithNewAliases _definedLgNames, AbstractFileBuilder _builder) {
        return new Forwards(_definedLgNames, _definedLgNames.getExecContent(), _builder, _options);
    }
}
