package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ContextFactory {

    private ContextFactory(){}

    public static ContextEl build(int _stack, DefaultLockingClass _lock,Initializer _init,
            Options _options,AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, int _tabWidth, String _folder) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options,  _mess, _definedKw, _definedLgNames, _tabWidth);
        return validate( _mess,_definedKw,_definedLgNames, _files, contextEl_,_folder);
    }

    public static ContextEl validate(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl, String _folder) {
        validateStds(_contextEl, _mess, _definedKw, _definedLgNames);
        StringMap<String> srcFiles_ = new StringMap<String>();
        String pref_ = StringList.concat(_folder,"/");
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith(pref_)) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        _contextEl.getClasses().addResources(_files);
        Classes.validateAll(srcFiles_, _contextEl);
        return _contextEl;
    }

    public static ContextEl build(int _stack, DefaultLockingClass _lock, Initializer _init,
                                  Options _options, AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, int _tabWidth) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options,  _mess, _definedKw, _definedLgNames,_tabWidth);
        validateStds(contextEl_,_mess,_definedKw,_definedLgNames);
        return contextEl_;
    }
    public static void validateStds(ContextEl _context, AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames) {
        _context.setStandards(_definedLgNames);
        AnalysisMessages.validateMessageContents(_context,_mess.allMessages());
        if (!_context.getClasses().isEmptyMessageError()) {
            return;
        }
        StringMap<String> keyWords_ = _definedKw.allKeyWords();
        _definedKw.validateKeyWordContents(_context, keyWords_);
        StringMap<String> escapings_ = _definedKw.allEscapings();
        _definedKw.validateEscapingsContents(_context, escapings_);
        StringMap<String> nbWords_ = _definedKw.allNbWords(_definedKw.allNbWordsBasic());
        _definedKw.validateNbWordContents(_context, nbWords_);
        _definedKw.validateBinarySeparators(_context);
        StringMap<String> prims_ = _definedLgNames.allPrimitives();
        _definedLgNames.validatePrimitiveContents(_context, prims_);
        _definedLgNames.validatePrimitiveDuplicates(_context, prims_);
        _definedKw.validateKeyWordDuplicates(_context, keyWords_);
        _definedKw.validateEscapingsDuplicates(_context, escapings_);
        StringMap<String> nbWordsDec_ = _definedKw.allNbWords(_definedKw.allNbWordsDec());
        _definedKw.validateNbWordDuplicates(_context, nbWordsDec_);
        StringMap<String> nbWordsBin_ = _definedKw.allNbWords(_definedKw.allNbWordsBin());
        _definedKw.validateNbWordDuplicates(_context, nbWordsBin_);
        _definedKw.validateStartsPrefixesDuplicates(_context);
        StringMap<String> refTypes_ = _definedLgNames.allRefTypes();
        _definedLgNames.validateRefTypeContents(_context, refTypes_, prims_);
        boolean dup_ = _definedLgNames.validateRefTypeDuplicates(_context, refTypes_);
        if (dup_) {
            return;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = _definedLgNames.allTableTypeMethodNames();
        _definedLgNames.validateMethodsContents(_context, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = _definedLgNames.allTableTypeFieldNames();
        _definedLgNames.validateFieldsContents(_context, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = _definedLgNames.allTableTypeVarTypes();
        _definedLgNames.validateVarTypesContents(_context, varTypes_, prims_);
        //duplicates
        _definedLgNames.validateMethodsDuplicates(_context, methods_);
        _definedLgNames.validateFieldsDuplicates(_context, fields_);
        _definedLgNames.validateVarTypesDuplicates(_context, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = _definedLgNames.allMergeTableTypeMethodNames();
        _definedLgNames.validateMergedDuplicates(_context, merge_);
        if (!_context.getClasses().isEmptyStdError()) {
            return;
        }
        _definedLgNames.build();
        _definedLgNames.setupOverrides(_context);
        _context.initError();
    }
}
