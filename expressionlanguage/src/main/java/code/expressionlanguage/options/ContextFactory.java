package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ContextFactory {

    public static ContextEl buildDefKw(String _lang, DefaultLockingClass _lock,Initializer _init,
            Options _options, LgNames _undefinedLgNames, StringMap<String> _files) {
        ContextEl context_ = buildDefKw(_lang, _lock, _init, _options, _undefinedLgNames);
        Classes.validateAll(_files, context_);
        return context_;
    }
    public static ContextEl buildDefKw(String _lang, DefaultLockingClass _lock,Initializer _init,
            Options _options, LgNames _undefinedLgNames) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT,_lock, _init, _options, kwl_, _undefinedLgNames);
    }
    public static ContextEl build(int _stack, DefaultLockingClass _lock,Initializer _init,
            Options _options, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files) {
        ContextEl context_ = build(_stack, _lock, _init, _options, _definedKw, _definedLgNames);
        Classes.validateAll(_files, context_);
        return context_;
    }
    public static ContextEl build(int _stack, DefaultLockingClass _lock,Initializer _init,
            Options _options, KeyWords _definedKw, LgNames _definedLgNames) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options, _definedKw);
        contextEl_.setStandards(_definedLgNames);
        StringList keyWords_ = _definedKw.allKeyWords();
        _definedKw.validateKeyWordContents(contextEl_, keyWords_);
        StringList escapings_ = _definedKw.allEscapings();
        _definedKw.validateEscapingsContents(contextEl_, escapings_);
        StringList nbWords_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpDec(), _definedKw.getKeyWordNbExpBin(),_definedKw.getKeyWordNbHex());
        _definedKw.validateNbWordContents(contextEl_, nbWords_);
        _definedKw.validateBinarySeparators(contextEl_);
        StringList prims_ = _definedLgNames.allPrimitives();
        _definedLgNames.validatePrimitiveContents(contextEl_, prims_);
        StringList refTypes_ = _definedLgNames.allRefTypes();
        _definedLgNames.validateRefTypeContents(contextEl_, refTypes_, prims_);
        StringMap<StringList> methods_ = _definedLgNames.allTableTypeMethodNames();
        _definedLgNames.validateMethodsContents(contextEl_, methods_, prims_);
        StringMap<StringList> fields_ = _definedLgNames.allTableTypeFieldNames();
        _definedLgNames.validateFieldsContents(contextEl_, fields_, prims_);
        //duplicates
        _definedKw.validateKeyWordDuplicates(contextEl_, keyWords_);
        _definedKw.validateEscapingsDuplicates(contextEl_, escapings_);
        StringList nbWordsDec_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpDec());
        _definedKw.validateNbWordDuplicates(contextEl_, nbWordsDec_);
        StringList nbWordsBin_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpBin(),_definedKw.getKeyWordNbHex());
        _definedKw.validateNbWordDuplicates(contextEl_, nbWordsBin_);
        _definedKw.validateStartsPrefixesDuplicates(contextEl_);
        _definedLgNames.validatePrimitiveDuplicates(contextEl_, prims_);
        _definedLgNames.validateRefTypeDuplicates(contextEl_, refTypes_);
        _definedLgNames.validateMethodsDuplicates(contextEl_, methods_);
        _definedLgNames.validateFieldsDuplicates(contextEl_, fields_);
        if (!contextEl_.getClasses().isEmptyStdError()) {
            return contextEl_;
        }
        _definedLgNames.build();
        _definedLgNames.setupOverrides(contextEl_);
        contextEl_.initError();
        return contextEl_;
    }
}
