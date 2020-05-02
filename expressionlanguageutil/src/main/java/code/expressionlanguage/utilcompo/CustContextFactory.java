package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
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
    public static RunnableContextEl buildDefKw(String _lang,
            Options _options, ExecutingOptions _exec,LgNamesUtils _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        KeyWords kwl_ = new KeyWords();
        AnalysisMessages mess_ = new AnalysisMessages();
        if (StringList.quickEq(_lang, "en")) {
            _undefinedLgNames.messages(mess_,_lang,_exec.getMessages());
            _undefinedLgNames.keyWord(kwl_,_lang,_exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            _undefinedLgNames.messages(mess_,_lang,_exec.getMessages());
            _undefinedLgNames.keyWord(kwl_,_lang,_exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.messages(mess_,_exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.keyWord(kwl_,_exec.getKeyWords(), new StringMap<String>());
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
            stds_.messages(mess_,_lang,_exec.getMessages());
            stds_.keyWord(kwl_,_lang,_exec.getKeyWords());
            stds_.otherAlias(_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            stds_.messages(mess_,_lang,_exec.getMessages());
            stds_.keyWord(kwl_,_lang,_exec.getKeyWords());
            stds_.otherAlias(_lang,_exec.getAliases());
        } else {
            stds_.messages(mess_,_exec.getMessages(), new StringMap<String>());
            stds_.keyWord(kwl_,_exec.getKeyWords(), new StringMap<String>());
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
        RunnableContextEl rCont_ = build(_stack, _options, _exec, _mess,_definedKw,
                _definedLgNames, _files, _exec.getTabWidth());
        if (!rCont_.isEmptyErrors()) {
            _progressingTests.showErrors(rCont_,_exec);
            return;
        }
        String aliasExecuteTests_ = _definedLgNames.getAliasExecuteTests();
        String infoTest_ = _definedLgNames.getAliasInfoTest();
        Struct infoStruct_ = rCont_.getInit().processInit(rCont_,
                NullStruct.NULL_VALUE, infoTest_, "", -1);
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                aliasExecuteTests_,new StringList(infoTest_));
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ShowUpdates showUpdates_ = rCont_.putInThread(infoStruct_,_progressingTests);
        new Thread(showUpdates_).start();
        Argument arg_ = RunnableStruct.invoke(argGlLoc_,
                _definedLgNames.getAliasExecute(), fct_,
                new CustList<Argument>(argMethod_), rCont_, null);
        showUpdates_.stop();
        if (rCont_.isCovering()) {
            String exp_ = _exec.getCoverFolder();
            for (EntryCust<String,String> f:FileBlock.export(rCont_).entryList()) {
                _definedLgNames.coverFile(exp_,f.getKey(),f.getValue(),rCont_);
            }
        }
        _progressingTests.setResults(rCont_,arg_);
    }
    public static RunnableContextEl build(int _stack,
            Options _options, ExecutingOptions _exec,AnalysisMessages _mess, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        RunnableContextEl r_ = new RunnableContextEl(_stack, cl_, ci_, _options, _exec, _mess,_definedKw, _definedLgNames,_tabWidth);
        r_.setCovering(_exec.isCovering());
        ContextFactory.validate(_mess,_definedKw,_definedLgNames,_files,r_,_exec.getSrcFolder(),_definedLgNames.defComments());
        return r_;
    }
}
