package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
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
        return validate( _mess,_definedKw,_definedLgNames, _files, contextEl_,_folder, new CustList<CommentDelimiters>());
    }

    public static ContextEl validate(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl, String _folder,
                                     CustList<CommentDelimiters> _comments) {
        validateStds(_contextEl, _mess, _definedKw, _definedLgNames,_comments);
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
        validateStds(contextEl_,_mess,_definedKw,_definedLgNames, new CustList<CommentDelimiters>());
        return contextEl_;
    }
    public static void validateStds(ContextEl _context, AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames,
                                    CustList<CommentDelimiters> _comments) {
        CommentsUtil.checkAndUpdateComments(_context.getComments(),_comments);
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
        ApplyCoreMethodUtil.validatePrimitiveContents(_context, prims_);
        ApplyCoreMethodUtil.validatePrimitiveDuplicates(_context, prims_);
        _definedKw.validateKeyWordDuplicates(_context, keyWords_);
        _definedKw.validateEscapingsDuplicates(_context, escapings_);
        StringMap<String> nbWordsDec_ = _definedKw.allNbWords(_definedKw.allNbWordsDec());
        _definedKw.validateNbWordDuplicates(_context, nbWordsDec_);
        StringMap<String> nbWordsBin_ = _definedKw.allNbWords(_definedKw.allNbWordsBin());
        _definedKw.validateNbWordDuplicates(_context, nbWordsBin_);
        StringMap<String> nbWordsPreBin_ = _definedKw.allNbWords(_definedKw.allNbWordsPreBin());
        _definedKw.validateNbWordDuplicates(_context, nbWordsPreBin_);
        _definedKw.validateStartsPrefixesDuplicates(_context);
        StringMap<String> refTypes_ = _definedLgNames.allRefTypes();
        ApplyCoreMethodUtil.validateRefTypeContents(_context, refTypes_, prims_);
        boolean dup_ = ApplyCoreMethodUtil.validateRefTypeDuplicates(_context, refTypes_);
        if (dup_) {
            return;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = _definedLgNames.allTableTypeMethodNames();
        ApplyCoreMethodUtil.validateMethodsContents(_context, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = _definedLgNames.allTableTypeFieldNames();
        ApplyCoreMethodUtil.validateFieldsContents(_context, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = _definedLgNames.allTableTypeVarTypes();
        ApplyCoreMethodUtil.validateVarTypesContents(_context, varTypes_, prims_);
        //duplicates
        ApplyCoreMethodUtil.validateMethodsDuplicates(_context, methods_);
        ApplyCoreMethodUtil.validateFieldsDuplicates(_context, fields_);
        ApplyCoreMethodUtil.validateVarTypesDuplicates(_context, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = _definedLgNames.allMergeTableTypeMethodNames();
        ApplyCoreMethodUtil.validateMergedDuplicates(_context, merge_);
        if (!_context.getClasses().isEmptyStdError()) {
            return;
        }
        _definedLgNames.build();
        ApplyCoreMethodUtil.setupOverrides(_context);
    }
}
