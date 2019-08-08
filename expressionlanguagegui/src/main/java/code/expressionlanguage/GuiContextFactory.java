package code.expressionlanguage;

import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.KeyWordsMap;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    public static GuiContextEl buildDefKw(String _lang,
                                               Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
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
    public static GuiContextEl build(int _stack,
                                     Options _options, ExecutingOptions _exec, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        CustInitializer ci_ = new GuiInitializer();
        GuiContextEl r_ = new GuiContextEl(_stack, cl_, ci_, _options, _exec, _definedKw, _definedLgNames,_tabWidth);
        ContextFactory.validate(_definedKw,_definedLgNames,_files,r_);
        return r_;
    }
}
