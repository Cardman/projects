package code.expressionlanguage.utilimpl;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultsRunnableContext buildDefKw(String _lang,
                                                    Options _options, ExecutingOptions _exec, LgNamesWithNewAliases _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        if (StringList.quickEq(_lang, "en")) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.getCustAliases().otherAlias(_undefinedLgNames.getContent(),_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.getCustAliases().otherAlias(_undefinedLgNames.getContent(),_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().allAlias(_undefinedLgNames.getContent(),_exec.getAliases(), new StringMap<String>());
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, _exec,mess_,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static void executeDefKw(String _lang,
                                    Options _options, ExecutingOptions _exec, StringMap<String> _files, ProgressingTests _progressingTests, LgNamesUtils _stds) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        if (StringList.quickEq(_lang, "en")) {
            _stds.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _stds.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _stds.getCustAliases().otherAlias(_stds.getContent(),_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            _stds.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _stds.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _stds.getCustAliases().otherAlias(_stds.getContent(),_lang,_exec.getAliases());
        } else {
            _stds.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _stds.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _stds.getCustAliases().allAlias(_stds.getContent(),_exec.getAliases(), new StringMap<String>());
        }
        execute(-1,_options,_exec,mess_,kwl_, _stds,_files,_progressingTests);
    }
    public static void execute(int _stack,
                               Options _options, ExecutingOptions _exec,
                               AnalysisMessages _mess,
                               KeyWords _definedKw,
                               LgNamesUtils _definedLgNames, StringMap<String> _files,
                               ProgressingTests _progressingTests) {
        ResultsRunnableContext res_ = build(_stack, _options, _exec, _mess,_definedKw,
                _definedLgNames, _files, _exec.getTabWidth());
        RunnableContextEl rCont_ = res_.getRunnable();
        ReportedMessages reportedMessages_ = res_.getReportedMessages();
        CustContextFactory.reportErrors(rCont_, _options, _exec, reportedMessages_, _definedLgNames.getInfos());
        if (!reportedMessages_.isAllEmptyErrors()) {
            _progressingTests.showErrors(rCont_,reportedMessages_,_options,_exec);
            return;
        }
        String infoTest_ = _definedLgNames.getCustAliases().getAliasInfoTest();
        Struct infoStruct_ = rCont_.getInit().processInit(rCont_,
                NullStruct.NULL_VALUE, infoTest_,rCont_.getClasses().getClassBody(infoTest_), "", -1);
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ShowUpdates showUpdates_ = new ShowUpdates(infoStruct_,rCont_,_progressingTests,_definedLgNames);
        new Thread(showUpdates_).start();
        ExecNamedFunctionBlock fctBody_ = ((LgNamesWithNewAliases) rCont_.getStandards()).getExecutingBlocks().getExecuteMethod();
        Argument arg_ = RunnableStruct.invoke(argGlLoc_,
                _definedLgNames.getCustAliases().getAliasExecute(), ((LgNamesWithNewAliases) rCont_.getStandards()).getExecutingBlocks().getExecuteType(), fctBody_,
                new CustList<Argument>(argMethod_), rCont_);
        showUpdates_.stop();
        if (_options.isCovering()) {
            String exp_ = _exec.getCoverFolder();
            for (EntryCust<String,String> f:ExecFileBlock.export(rCont_).entryList()) {
                _definedLgNames.getInfos().getReporter().coverFile(exp_, f.getKey(), f.getValue(), rCont_);
            }
        }
        _progressingTests.setResults(rCont_,arg_, _definedLgNames);
    }
    public static void reportErrors(RunnableContextEl _ctx, Options _options, ExecutingOptions _exec, ReportedMessages _reportedMessages, FileInfos _infos) {
        if (_options.isGettingErrors()) {
            String exp_ = _exec.getErrorsFolder();
            for (EntryCust<String,String> f: _reportedMessages.getErrors().entryList()) {
                _infos.getReporter().errorFile(exp_, f.getKey(), f.getValue(), _ctx);
            }
        }
    }
    public static ResultsRunnableContext build(int _stack,
                                               Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesWithNewAliases _definedLgNames, StringMap<String> _files, int _tabWidth) {
        _definedLgNames.setExecutingOptions(_exec);
        RunnableContextEl r_ = (RunnableContextEl) _definedLgNames.newContext(_tabWidth, _stack, new Coverage(_options.isCovering()));
        ReportedMessages reportedMessages_ = ContextFactory.validate(_mess, _definedKw, _definedLgNames, _files, r_, _exec.getSrcFolder(), _definedLgNames.getCustAliases().defComments(), _options, r_.getClasses().getCommon(),
                new DefaultConstantsCalculator(_definedLgNames.getContent().getNbAlias()), CustFileBuilder.newInstance(_definedLgNames.getContent(), _definedLgNames.getCustAliases()), _definedLgNames.getContent());
        return new ResultsRunnableContext(r_,reportedMessages_);
    }
}
