package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ContextFactory {

    private ContextFactory(){}

    public static ReportedMessages validate(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl, String _folder,
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
        return Classes.validateAll(srcFiles_, _contextEl);
    }

    public static ContextEl build(int _stack, DefaultLockingClass _lock, Initializer _init,
                                  Options _options, AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, int _tabWidth) {
        ContextEl contextEl_ = new SingleContextEl(_stack, _lock, _init, _options, _definedKw, _definedLgNames,_tabWidth);
        validateStds(contextEl_,_mess,_definedKw,_definedLgNames, new CustList<CommentDelimiters>());
        return contextEl_;
    }
    public static void validateStds(ContextEl _context, AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames,
                                    CustList<CommentDelimiters> _comments) {
        _context.setAnalyzing();
        CustList<CommentDelimiters> comments_ = _context.getOptions().getComments();
        CommentsUtil.checkAndUpdateComments(comments_,_comments);
        _context.getAnalyzing().setComments(comments_);
        _context.getAnalyzing().setAnalysisMessages(_mess);
        _context.getAnalyzing().setKeyWords(_definedKw);
        _context.getCoverage().setKeyWords(_context,_definedKw);
        _context.setStandards(_definedLgNames);
        AnalysisMessages.validateMessageContents(_context,_mess.allMessages());
        if (!_context.isEmptyMessageError()) {
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
        ValidatorStandard.validatePrimitiveContents(_context, prims_);
        ValidatorStandard.validatePrimitiveDuplicates(_context, prims_);
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
        ValidatorStandard.validateRefTypeContents(_context, refTypes_, prims_);
        boolean dup_ = ValidatorStandard.validateRefTypeDuplicates(_context, refTypes_);
        if (dup_) {
            return;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = _definedLgNames.allTableTypeMethodNames();
        ValidatorStandard.validateMethodsContents(_context, methods_, prims_);
        CustList<CustList<KeyValueMemberName>> params_ = _definedLgNames.allTableTypeMethodParamNames();
        ValidatorStandard.validateParamtersContents(_context, params_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = _definedLgNames.allTableTypeFieldNames();
        ValidatorStandard.validateFieldsContents(_context, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = _definedLgNames.allTableTypeVarTypes();
        ValidatorStandard.validateVarTypesContents(_context, varTypes_, prims_);
        //duplicates
        ValidatorStandard.validateMethodsDuplicates(_context, methods_);
        ValidatorStandard.validateParamtersDuplicates(_context, params_);
        ValidatorStandard.validateFieldsDuplicates(_context, fields_);
        ValidatorStandard.validateVarTypesDuplicates(_context, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = _definedLgNames.allMergeTableTypeMethodNames();
        ValidatorStandard.validateMergedDuplicates(_context, merge_);
        if (!_context.isEmptyStdError()) {
            return;
        }
        _definedLgNames.build();
        ValidatorStandard.setupOverrides(_context);
    }
}
