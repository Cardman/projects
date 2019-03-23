package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ContextFactory {

    private ContextFactory(){}
    public static ContextEl buildDefKw(String _lang, DefaultLockingClass _lock, Initializer _init,
                                       Options _options, LgNames _undefinedLgNames, int _tabWidth) {
        KeyWordsMap km_ = new KeyWordsMap(); 
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else if (StringList.quickEq(_lang, "fr")) {
            km_.initFrStds(_undefinedLgNames);
        } else {
            return null;
        }
        return build(CustList.INDEX_NOT_FOUND_ELT,_lock, _init, _options, kwl_, _undefinedLgNames, _tabWidth);
    }
    public static ContextEl build(int _stack, DefaultLockingClass _lock,Initializer _init,
            Options _options,KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, int _tabWidth) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options, _definedKw, _definedLgNames, _tabWidth);
        return validate(_definedKw, _definedLgNames, _files, contextEl_);
    }

    public static ContextEl validate(KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl) {
        validateStds(_contextEl, _definedKw, _definedLgNames);
        StringMap<String> srcFiles_ = new StringMap<String>();
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith("src/")) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        _contextEl.getClasses().addResources(_files);
        Classes.validateAll(srcFiles_, _contextEl);
        return _contextEl;
    }

    public static ContextEl build(int _stack, DefaultLockingClass _lock, Initializer _init,
                                  Options _options, KeyWords _definedKw, LgNames _definedLgNames, int _tabWidth) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options, _definedKw, _definedLgNames,_tabWidth);
        validateStds(contextEl_,_definedKw,_definedLgNames);
        return contextEl_;
    }
    public static void validateStds(ContextEl _context,KeyWords _definedKw, LgNames _definedLgNames) {
        _context.setStandards(_definedLgNames);
        StringList keyWords_ = _definedKw.allKeyWords();
        _definedKw.validateKeyWordContents(_context, keyWords_);
        StringList escapings_ = _definedKw.allEscapings();
        _definedKw.validateEscapingsContents(_context, escapings_);
        StringList nbWords_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpDec(), _definedKw.getKeyWordNbExpBin(),_definedKw.getKeyWordNbHex());
        _definedKw.validateNbWordContents(_context, nbWords_);
        _definedKw.validateBinarySeparators(_context);
        StringList prims_ = _definedLgNames.allPrimitives();
        _definedLgNames.validatePrimitiveContents(_context, prims_);
        StringList refTypes_ = _definedLgNames.allRefTypes();
        _definedLgNames.validateRefTypeContents(_context, refTypes_, prims_);
        StringMap<StringList> methods_ = _definedLgNames.allTableTypeMethodNames();
        _definedLgNames.validateMethodsContents(_context, methods_, prims_);
        StringMap<StringList> fields_ = _definedLgNames.allTableTypeFieldNames();
        _definedLgNames.validateFieldsContents(_context, fields_, prims_);
        //duplicates
        _definedKw.validateKeyWordDuplicates(_context, keyWords_);
        _definedKw.validateEscapingsDuplicates(_context, escapings_);
        StringList nbWordsDec_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpDec());
        _definedKw.validateNbWordDuplicates(_context, nbWordsDec_);
        StringList nbWordsBin_ = _definedKw.allNbWords(_definedKw.getKeyWordNbExpBin(),_definedKw.getKeyWordNbHex());
        _definedKw.validateNbWordDuplicates(_context, nbWordsBin_);
        _definedKw.validateStartsPrefixesDuplicates(_context);
        _definedLgNames.validatePrimitiveDuplicates(_context, prims_);
        _definedLgNames.validateRefTypeDuplicates(_context, refTypes_);
        _definedLgNames.validateMethodsDuplicates(_context, methods_);
        _definedLgNames.validateFieldsDuplicates(_context, fields_);
        if (!_context.getClasses().isEmptyStdError()) {
            return;
        }
        _definedLgNames.build();
        _definedLgNames.setupOverrides(_context);
        _context.initError();
    }
}
