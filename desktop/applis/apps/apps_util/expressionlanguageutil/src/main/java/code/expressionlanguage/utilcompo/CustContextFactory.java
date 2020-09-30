package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static ResultsRunnableContext buildDefKw(String _lang,
            Options _options, ExecutingOptions _exec,LgNamesUtils _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        if (StringList.quickEq(_lang, "en")) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _undefinedLgNames.allAlias(_exec.getAliases(), new StringMap<String>());
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, _exec,mess_,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static void executeDefKw(String _lang,
                               Options _options, ExecutingOptions _exec,StringMap<String> _files, ProgressingTests _progressingTests,FileInfos _infos) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        LgNamesUtils stds_ = new LgNamesUtils(_infos);
        if (StringList.quickEq(_lang, "en")) {
            stds_.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            stds_.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            stds_.otherAlias(_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            stds_.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            stds_.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            stds_.otherAlias(_lang,_exec.getAliases());
        } else {
            stds_.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            stds_.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            stds_.allAlias(_exec.getAliases(), new StringMap<String>());
        }
        execute(-1,_options,_exec,mess_,kwl_,stds_,_files,_progressingTests);
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
        CustContextFactory.reportErrors(rCont_,_definedLgNames,_options,_exec, reportedMessages_);
        if (!reportedMessages_.isAllEmptyErrors()) {
            _progressingTests.showErrors(rCont_,reportedMessages_,_options,_exec);
            return;
        }
        String infoTest_ = _definedLgNames.getCustAliases().getAliasInfoTest();
        Struct infoStruct_ = rCont_.getInit().processInit(rCont_,
                NullStruct.NULL_VALUE, infoTest_,rCont_.getClasses().getClassBody(infoTest_), "", -1);
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ShowUpdates showUpdates_ = new ShowUpdates(infoStruct_,rCont_,_progressingTests);
        new Thread(showUpdates_).start();
        ExecNamedFunctionBlock fctBody_ = rCont_.getExecuteMethod();
        Argument arg_ = RunnableStruct.invoke(argGlLoc_,
                _definedLgNames.getCustAliases().getAliasExecute(), rCont_.getExecuteType(), fctBody_,
                new CustList<Argument>(argMethod_), rCont_);
        showUpdates_.stop();
        if (_options.isCovering()) {
            String exp_ = _exec.getCoverFolder();
            for (EntryCust<String,String> f:ExecFileBlock.export(rCont_).entryList()) {
                _definedLgNames.getInfos().getReporter().coverFile(exp_, f.getKey(), f.getValue(), rCont_);
            }
        }
        _progressingTests.setResults(rCont_,arg_);
    }
    public static void reportErrors(RunnableContextEl _ctx, LgNamesUtils _definedLgNames, Options _opts, ExecutingOptions _exec, ReportedMessages _reportedMessages) {
        if (_exec.isErrors()) {
            String exp_ = _exec.getErrorsFolder();
            for (EntryCust<String,String> f: _reportedMessages.getErrors().entryList()) {
                _definedLgNames.getInfos().getReporter().errorFile(exp_, f.getKey(), f.getValue(), _ctx);
            }
        }
    }
    public static ResultsRunnableContext build(int _stack,
            Options _options, ExecutingOptions _exec,AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        ClassesCommon com_ = new ClassesCommon();
        _definedLgNames.setExecutingOptions(_exec);
        RunnableContextEl r_ = new RunnableContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(_tabWidth, _stack, _definedLgNames, new Classes(com_), new Coverage(_options.isCovering()), cl_, ci_));
        ReportedMessages reportedMessages_ = ContextFactory.validate(_mess, _definedKw, _definedLgNames, _files, r_, _exec.getSrcFolder(), _definedLgNames.getCustAliases().defComments(), _options, com_,
                new DefaultConstantsCalculator(_definedLgNames.getNbAlias()), new CustFileBuilder(_definedLgNames.getContent(), _definedLgNames.getCustAliases()), _definedLgNames.getContent());
        return new ResultsRunnableContext(r_,reportedMessages_);
    }
}
