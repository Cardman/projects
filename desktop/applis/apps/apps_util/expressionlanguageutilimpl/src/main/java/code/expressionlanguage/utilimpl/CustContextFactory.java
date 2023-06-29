package code.expressionlanguage.utilimpl;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultContext buildDefKw(Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files) {
        return build(_undefinedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean(),_options, _exec, _undefinedLgNames, _files, new StringList());
    }
    public static void executeDefKw(Options _options, ExecutingOptions _exec, StringMap<String> _files, ProgressingTests _progressingTests, LgNamesGui _stds) {
        execute(_options,_exec, _stds,_files,_progressingTests);
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
                               ProgressingTests _progressingTests) {
        AbstractAtomicBoolean stop_ = _definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean();
        _progressingTests.setStop(stop_);
        ResultContext res_ = build(stop_,_options, _exec,
                _definedLgNames, _files, new StringList());
        ContextEl rCont_ = res_.getContext();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        FileInfos infos_ = _definedLgNames.getExecContent().getInfos();
        CustContextFactory.reportErrors(_options, _exec, reportedMessages_, infos_);
        if (!(rCont_ instanceof RunnableContextEl)) {
            _progressingTests.showErrors(reportedMessages_,_options,_exec,infos_);
            return;
        }
        _progressingTests.ctx((RunnableContextEl) rCont_);
        _progressingTests.showWarnings(_progressingTests.ctx(),reportedMessages_,_options,_exec,infos_);
        infos_.tryLogIssue("OK");
        String infoTest_ = _definedLgNames.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(className_, className_.getRootBlock().getEmptyCtorPair(), new Argument(), new Parameters(), InstancingStep.NEWING),rCont_,StackCall.newInstance(InitPhase.NOTHING,rCont_)).getValue());
        ((FieldableStruct)infoStruct_).getEntryStruct(new ClassField(infoTest_,_definedLgNames.getExecContent().getCustAliases().getAliasInfoTestNbThreads())).setStruct(new IntStruct(2));
        AbstractScheduledExecutorService sch_ = ((RunnableContextEl) rCont_).getCurrentThreadFactory().newScheduledExecutorService();
        ShowUpdates showUpdates_ = new ShowUpdates(infoStruct_,(RunnableContextEl) rCont_,_progressingTests,_definedLgNames);
        AbstractFuture abstractFuture_ = sch_.scheduleAtFixedRateNanos(showUpdates_, 0, 1);
        ExecTypeFunction pair_ = ((LgNamesWithNewAliases) rCont_.getStandards()).getExecContent().getExecutingBlocks().getExecuteMethodPair();
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ArgumentListCall argList_ = new ArgumentListCall(argMethod_);
        ExecFormattedRootBlock aClass_ = ExecFormattedRootBlock.build(_definedLgNames.getExecContent().getCustAliases().getAliasExecute(),rCont_.getClasses());
        Argument arg_ = RunnableStruct.invoke(argGlLoc_,
                aClass_,
                (RunnableContextEl) rCont_, pair_, StackCall.newInstance(InitPhase.NOTHING,rCont_), argList_);
        abstractFuture_.cancel(false);
        sch_.shutdown();
        _progressingTests.finish(((RunnableContextEl) rCont_),infoStruct_, _definedLgNames);
        if (_options.isCovering()) {
            for (EntryCust<String,String> f:ExecFileBlock.export(rCont_).entryList()) {
                infos_.getReporter().coverFile(_exec, f.getKey(), f.getValue());
            }
        }
        _progressingTests.setResults((RunnableContextEl) rCont_,Argument.getNullableValue(arg_), _definedLgNames);
    }
    public static void reportErrors(Options _options, ExecutingOptions _exec, ReportedMessages _reportedMessages, FileInfos _infos) {
        if (_options.isGettingErrors()) {
            for (EntryCust<String,String> f: _reportedMessages.getErrors().entryList()) {
                _infos.getReporter().errorFile(_exec, f.getKey(), f.getValue());
            }
        }
    }
    public static ResultContext build(AbstractAtomicBoolean _stop,Options _options, ExecutingOptions _exec, LgNamesGui _definedLgNames, StringMap<String> _files, StringList _mainArgs) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        preinit(_options, _exec, mess_, kwl_, _definedLgNames);
        parts(_exec, _definedLgNames, _mainArgs);
        AnalyzedPageEl page_ = mapping(_definedLgNames);
        Forwards forwards_ = builder(_options, _definedLgNames, page_);
        forwards_.getResources().addAllEntries(_files);
        AnalysisMessages.validateMessageContents(mess_.allMessages(_definedLgNames.getExecContent().getCustAliases().extractMessagesKeys()), page_);
        ContextFactory.validateStds(forwards_,mess_, kwl_, _definedLgNames.getExecContent().getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ResultContext r_ = new ResultContext(page_, forwards_);
        ResultContext res_ = ResultContext.def(r_, _files,  _exec.getSrcFolder());
        ResultContext.fwd(res_, new AdvContextGenerator(_stop));
        Classes.tryInit(res_);
        return res_;
    }

    public static ResultContext stds(FileInfos _file, ExecutingOptions _ex, Options _opts) {
        return stds(_file, _ex, _opts, false);
    }
    public static ResultContext stds(FileInfos _file, ExecutingOptions _ex, Options _opts, boolean _light) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        _opts.setReadOnly(true);
        LgNamesGui stds_ = new LgNamesGui(_file, _ex.getListGenerator().getInterceptor(),_light);
        CustContextFactory.preinit(_opts, _ex, mess_, kwl_, stds_);
        CustContextFactory.parts(_ex,stds_,new StringList());
        AnalyzedPageEl page_ = CustContextFactory.mapping(stds_);
        Forwards forwards_ = CustContextFactory.builder(_opts, stds_, page_);
        AnalysisMessages.validateMessageContents(mess_.allMessages(stds_.getExecContent().getCustAliases().extractMessagesKeys()), page_);
        ContextFactory.validateStds(forwards_, mess_, kwl_, stds_.getExecContent().getCustAliases().defComments(), _opts, stds_.getContent(), page_);
        if (page_.notAllEmptyErrors()) {
            return new ResultContext(page_, forwards_, page_.getMessages());
        }
        return new ResultContext(page_, forwards_);
    }
    public static void parts(ExecutingOptions _exec, LgNamesGui _definedLgNames, StringList _mainArgs) {
        _definedLgNames.getExecContent().setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(_mainArgs, _exec.getLightProgramInfos(),_exec.getListGenerator());
    }

    public static Forwards builder(Options _options, LgNamesGui _definedLgNames, AnalyzedPageEl _page) {
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getExecContent().getCustAliases());
        Forwards forwards_ = fwd(_options, _definedLgNames, fileBuilder_);
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
