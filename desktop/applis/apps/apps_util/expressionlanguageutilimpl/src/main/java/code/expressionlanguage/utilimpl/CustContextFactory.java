package code.expressionlanguage.utilimpl;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.ClassField;
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
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultContext buildDefKw(String _lang,
                                                    Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        preinit(_lang, _options, _exec, mess_, kwl_, _undefinedLgNames);
        return build(_options, _exec,mess_,kwl_, _undefinedLgNames, _files);
    }
    public static void executeDefKw(String _lang,
                                    Options _options, ExecutingOptions _exec, StringMap<String> _files, ProgressingTests _progressingTests, LgNamesGui _stds) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        preinit(_lang, _options, _exec, mess_, kwl_, _stds);
        execute(_options,_exec,mess_,kwl_, _stds,_files,_progressingTests);
    }

    private static void preinit(String _lang, Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _kwl, LgNamesGui _aliases) {
        preinitAliases(_lang, _exec, _mess, _kwl, _aliases);
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns()));
    }

    public static void preinitAliases(String _lang, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _kwl, LgNamesGui _aliases) {
        if (!_lang.isEmpty()) {
            _aliases.getExecContent().getCustAliases().messages(_mess, _lang, _exec.getMessages());
            _aliases.getExecContent().getCustAliases().keyWord(_kwl, _lang, _exec.getKeyWords());
            _aliases.getExecContent().getCustAliases().otherAlias(_aliases.getContent(), _lang, _exec.getAliases());
            _aliases.getGuiAliases().otherAliasGui(_aliases.addon(_lang),_exec.getAliases());
        } else {
            _aliases.getExecContent().getCustAliases().messages(_mess, _exec.getMessages(), new StringMap<String>());
            _aliases.getExecContent().getCustAliases().keyWord(_kwl, _exec.getKeyWords(), new StringMap<String>());
            _aliases.getExecContent().getCustAliases().allAlias(_aliases.getContent(), _exec.getAliases(), new StringMap<String>());
            _aliases.getGuiAliases().otherAliasGui(_exec.getAliases(),new StringMap<String>());
        }
    }

    public static void execute(Options _options, ExecutingOptions _exec,
                               AnalysisMessages _mess,
                               KeyWords _definedKw,
                               LgNamesGui _definedLgNames, StringMap<String> _files,
                               ProgressingTests _progressingTests) {
        _progressingTests.init(_exec);
        ResultContext res_ = build(_options, _exec, _mess,_definedKw,
                _definedLgNames, _files);
        ContextEl rCont_ = res_.getContext();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        FileInfos infos_ = _definedLgNames.getExecContent().getInfos();
        CustContextFactory.reportErrors(_options, _exec, reportedMessages_, infos_);
        if (!(rCont_ instanceof RunnableContextEl)) {
            _progressingTests.showErrors(reportedMessages_,_options,_exec,infos_);
            return;
        }
        _progressingTests.showWarnings((RunnableContextEl) rCont_,reportedMessages_,_options,_exec,infos_);
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
    public static ResultContext build(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        _definedLgNames.getExecContent().setExecutingOptions(_exec);
        _definedLgNames.getExecContent().updateTranslations(_exec.getLightProgramInfos().getTranslations(),_exec.getLightProgramInfos().getLanguage());
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(new StringList(), _exec.getLightProgramInfos(),_exec.getListGenerator());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(new AdvSymbolFactory(_definedLgNames));
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getExecContent().getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames, _definedLgNames.getExecContent(), fileBuilder_, _options);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getExecContent().getCustAliases().defComments(_exec.getLg()), _options, _definedLgNames.getContent(), page_);
        ContextEl reportedMessages_ = ContextFactory.addResourcesAndValidate(_files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultContext(reportedMessages_, page_.getMessages());
    }
}
