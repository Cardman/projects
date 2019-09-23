package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.KeyWordsMap;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;

public final class CustContextFactory {
    private CustContextFactory(){}
    public static RunnableContextEl buildDefKw(String _lang,
            Options _options, ExecutingOptions _exec,LgNamesUtils _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
            _undefinedLgNames.otherAlias(_lang);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
            _undefinedLgNames.otherAlias(_lang);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, _exec,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static void executeDefKw(String _lang,
                               Options _options, ExecutingOptions _exec,StringMap<String> _files, ProgressingTests _progressingTests) {
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lang);
        LgNamesUtils stds_ = new LgNamesUtils();
        if (StringList.quickEq(_lang, "en")) {
            stds_.otherAlias(_lang);
            km_.initEnStds(stds_);
        } else if (StringList.quickEq(_lang, "fr")) {
            stds_.otherAlias(_lang);
            km_.initFrStds(stds_);
        } else {
            return;
        }
        execute(-1,_options,_exec,kwl_,stds_,_files,_progressingTests);
    }
    public static void execute(int _stack,
                               Options _options, ExecutingOptions _exec,KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, ProgressingTests _progressingTests) {
        RunnableContextEl rCont_ = build(_stack, _options, _exec, _definedKw, _definedLgNames, _files, _exec.getTabWidth());
        if (!rCont_.getClasses().isEmptyErrors() || !rCont_.getClasses().isEmptyStdError()) {
            _progressingTests.showErrors(rCont_,_exec);
            return;
        }
        String aliasExecuteTests_ = _definedLgNames.getAliasExecuteTests();
        String infoTest_ = _definedLgNames.getAliasInfoTest();
        Struct infoStruct_ = rCont_.getInit().processInit(rCont_, NullStruct.NULL_VALUE, infoTest_, "", -1);
        MethodId fct_ = new MethodId(true, aliasExecuteTests_,new StringList(infoTest_));
        Argument argGlLoc_ = new Argument();
        Argument argMethod_ = new Argument(infoStruct_);
        ShowUpdates showUpdates_ = rCont_.putInThread(infoStruct_,_progressingTests);
        new Thread(showUpdates_).start();
        Argument arg_ = RunnableStruct.invoke(argGlLoc_, _definedLgNames.getAliasExecute(), fct_, new CustList<Argument>(argMethod_), rCont_, null);
        showUpdates_.stop();
        if (rCont_.isCovering()) {
            String exp_ = _exec.getCoverFolder();
            for (EntryCust<String,String> f:FileBlock.export(rCont_).entryList()) {
                String full_ = exp_ + f.getKey();
                int end_ = full_.lastIndexOf('/');
                if (end_ > -1) {
                    String par_ = full_.substring(0, end_);
                    if (!par_.isEmpty()) {
                        new File(par_).mkdirs();
                    }
                }
                StreamTextFile.saveTextFile(full_,f.getValue());
            }
        }
        _progressingTests.setResults(rCont_,arg_);
    }
    public static RunnableContextEl build(int _stack,
            Options _options, ExecutingOptions _exec,KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        RunnableContextEl r_ = new RunnableContextEl(_stack, cl_, ci_, _options, _exec, _definedKw, _definedLgNames,_tabWidth);
        r_.setCovering(_exec.isCovering());
        ContextFactory.validate(_definedKw,_definedLgNames,_files,r_,_exec.getSrcFolder());
        return r_;
    }
}
