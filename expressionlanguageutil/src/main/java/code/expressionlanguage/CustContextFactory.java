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
    public static ContextEl buildDefKw(String _lang,
            Options _options, LgNames _undefinedLgNames, StringMap<String> _files) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, kwl_, _undefinedLgNames, _files);
    }
    public static ContextEl buildDefKw(String _lang, Options _options, LgNames _undefinedLgNames) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT, _options, kwl_, _undefinedLgNames);
    }
    public static ContextEl build(int _stack,
            Options _options, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        return ContextFactory.build(_stack, cl_, ci_, _options, _definedKw, _definedLgNames, _files);
    }
    public static ContextEl build(int _stack, Options _options, KeyWords _definedKw, LgNames _definedLgNames) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new CustInitializer();
        return ContextFactory.build(_stack, cl_, ci_, _options, _definedKw, _definedLgNames);
    }
}
