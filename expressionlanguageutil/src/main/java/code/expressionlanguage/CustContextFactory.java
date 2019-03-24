package code.expressionlanguage;

import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.KeyWordsMap;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
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
    public static RunnableContextEl build(int _stack,
            Options _options, ExecutingOptions _exec,KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        RunnableContextEl r_ = new RunnableContextEl(_stack, cl_, ci_, _options, _exec, _definedKw, _definedLgNames,_tabWidth);
        ContextFactory.validate(_definedKw,_definedLgNames,_files,r_);
        return r_;
    }
}
