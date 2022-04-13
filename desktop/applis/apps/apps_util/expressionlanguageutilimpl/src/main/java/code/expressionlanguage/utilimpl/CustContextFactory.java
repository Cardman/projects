package code.expressionlanguage.utilimpl;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.EntryCust;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultContext buildDefKw(String _lang,
                                                    Options _options, ExecutingOptions _exec, LgNamesWithNewAliases _undefinedLgNames, StringMap<String> _files) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        if (!_lang.isEmpty()) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.getCustAliases().otherAlias(_undefinedLgNames.getContent(),_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().allAlias(_undefinedLgNames.getContent(),_exec.getAliases(), new StringMap<String>());
        }
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns()));
        return build(_options, _exec,mess_,kwl_, _undefinedLgNames, _files);
    }
    public static void executeDefKw(String _lang,
                                    Options _options, ExecutingOptions _exec, StringMap<String> _files, ProgressingTests _progressingTests, LgNamesUtils _stds) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        if (!_lang.isEmpty()) {
            _stds.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _stds.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _stds.getCustAliases().otherAlias(_stds.getContent(),_lang,_exec.getAliases());
        } else {
            _stds.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _stds.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _stds.getCustAliases().allAlias(_stds.getContent(),_exec.getAliases(), new StringMap<String>());
        }
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns()));
        execute(_options,_exec,mess_,kwl_, _stds,_files,_progressingTests);
    }
    public static void execute(Options _options, ExecutingOptions _exec,
                               AnalysisMessages _mess,
                               KeyWords _definedKw,
                               LgNamesUtils _definedLgNames, StringMap<String> _files,
                               ProgressingTests _progressingTests) {
        _progressingTests.init(_exec);
        ResultContext res_ = build(_options, _exec, _mess,_definedKw,
                _definedLgNames, _files);
        ContextEl rCont_ = res_.getContext();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        FileInfos infos_ = _definedLgNames.getInfos();
        CustContextFactory.reportErrors(_options, _exec, reportedMessages_, infos_);
        if (!(rCont_ instanceof RunnableContextEl)) {
            _progressingTests.showErrors(reportedMessages_,_options,_exec,infos_);
            return;
        }
        _progressingTests.showWarnings((RunnableContextEl) rCont_,reportedMessages_,_options,_exec,infos_);
        AbstractIssuer issuer_ = _definedLgNames.getInfos().getLogger().getIssuer();
        if (issuer_ != null) {
            issuer_.log("OK");
        }
        String infoTest_ = _definedLgNames.getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = rCont_.getInit().processInit(rCont_,
                NullStruct.NULL_VALUE, className_, "", -1);
        AbstractScheduledExecutorService sch_ = ((RunnableContextEl) rCont_).getCurrentThreadFactory().newScheduledExecutorService();
        ShowUpdates showUpdates_ = new ShowUpdates(infoStruct_,(RunnableContextEl) rCont_,_progressingTests,_definedLgNames);
        AbstractFuture abstractFuture_ = sch_.scheduleAtFixedRateNanos(showUpdates_, 0, 1);
        ExecTypeFunction pair_ = ((LgNamesWithNewAliases) rCont_.getStandards()).getExecutingBlocks().getExecuteMethodPair();
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ArgumentListCall argList_ = new ArgumentListCall(argMethod_);
        ExecFormattedRootBlock aClass_ = ExecFormattedRootBlock.build(_definedLgNames.getCustAliases().getAliasExecute(),rCont_.getClasses());
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
        _progressingTests.setResults((RunnableContextEl) rCont_,arg_, _definedLgNames);
    }
    public static void reportErrors(Options _options, ExecutingOptions _exec, ReportedMessages _reportedMessages, FileInfos _infos) {
        if (_options.isGettingErrors()) {
            for (EntryCust<String,String> f: _reportedMessages.getErrors().entryList()) {
                _infos.getReporter().errorFile(_exec, f.getKey(), f.getValue());
            }
        }
    }
    public static ResultContext build(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesWithNewAliases _definedLgNames, StringMap<String> _files) {
        _definedLgNames.setExecutingOptions(_exec);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        CustFileBuilder fileBuilder_ = CustFileBuilder.newInstance(_definedLgNames.getContent(), _definedLgNames.getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames, fileBuilder_, _options);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ContextEl reportedMessages_ = ContextFactory.addResourcesAndValidate(_files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultContext(reportedMessages_, page_.getMessages());
    }
}
