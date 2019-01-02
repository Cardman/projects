package code.expressionlanguage;

import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.ExecutingOptions;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.KeyWordsMap;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class CustContextFactory {
    public static ContextEl buildDefKw(String _lang,
            Options _options, ExecutingOptions _exec,LgNames _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, _exec,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static ContextEl buildDefKw(String _lang, Options _options, ExecutingOptions _exec,LgNames _undefinedLgNames, int _tabWidth) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, _exec,kwl_, _undefinedLgNames, _tabWidth);
    }
    public static ContextEl build(int _stack,
            Options _options, ExecutingOptions _exec,KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        return ContextFactory.build(_stack, cl_, ci_, _options, _exec,_definedKw, _definedLgNames, _files,_tabWidth);
    }
    public static ContextEl build(int _stack, Options _options, ExecutingOptions _exec,KeyWords _definedKw, LgNames _definedLgNames, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        return ContextFactory.build(_stack, cl_, ci_, _options, _exec,_definedKw, _definedLgNames,_tabWidth);
    }
}
