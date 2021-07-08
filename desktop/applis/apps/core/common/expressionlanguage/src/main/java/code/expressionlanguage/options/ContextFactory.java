package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ContextFactory {

    private ContextFactory(){}

    public static ContextEl addResourcesAndValidate(Options _opt, StringMap<String> _files, String _folder, AnalyzedPageEl _page, Forwards _forwards) {
        StringMap<String> srcFiles_ = new StringMap<String>();
        String pref_ = StringUtil.concat(_folder,"/");
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith(pref_)) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        _page.addResources(_files);
        return Classes.validateAll(_opt,srcFiles_, _page, _forwards);
    }

    public static void validateStds(Forwards _fwd, AnalysisMessages _mess, KeyWords _definedKw,
                                    CustList<CommentDelimiters> _comments, Options _options, LgNamesContent _content, AnalyzedPageEl _page) {
        if (validatedStds(_fwd, _mess,_definedKw,_comments,_options, _content, _page)) {
            _fwd.getGenerator().build();
            ValidatorStandard.setupOverrides(_page);
        }
    }
    public static boolean validatedStds(Forwards _fwd, AnalysisMessages _mess, KeyWords _definedKw,
                                        CustList<CommentDelimiters> _comments, Options _options, LgNamesContent _content, AnalyzedPageEl _page) {
        _page.setLogErr(_fwd.getGenerator());
        _page.setOptions(_options);
        CustList<CommentDelimiters> comments_ = _options.getComments();
        CommentsUtil.checkAndUpdateComments(comments_,_comments);
        _page.setComments(comments_);
        _page.setDefaultAccess(_options.getDefaultAccess());
        _page.setAnalysisMessages(_mess);
        _page.setKeyWords(_definedKw);
        _page.setStandards(_content);
        _page.setCalculator(_fwd.getConstantsCalculator());
        _page.setFieldFilter(_fwd.getFieldFilter());
        _page.setFileBuilder(_fwd.getFileBuilder());
        _page.setResources(_fwd.getResources());
        _page.setStaticFields(_fwd.getStaticFields());
        _page.setTabWidth(_options.getTabWidth());
        _page.setGettingErrors(_options.isGettingErrors());
        AnalysisMessages.validateMessageContents(_mess.allMessages(), _page);
        if (!_page.isEmptyMessageError()) {
            return false;
        }
        StringMap<String> keyWords_ = _definedKw.allKeyWords();
        _definedKw.validateKeyWordContents(keyWords_, _page);
        StringMap<String> escapings_ = _definedKw.allEscapings();
        _definedKw.validateEscapingsContents(escapings_, _page);
        StringMap<String> nbWords_ = _definedKw.allNbWords(_definedKw.allNbWordsBasic());
        _definedKw.validateNbWordContents(nbWords_, _page);
        _definedKw.validateBinarySeparators(_page);
        DefaultAliasGroups defaultAliasGroups_ = _fwd.getFileBuilder().getDefaultAliasGroups();
        StringMap<String> prims_ = defaultAliasGroups_.allPrimitives();
        ValidatorStandard.validatePrimitiveContents(prims_, _page);
        ValidatorStandard.validatePrimitiveDuplicates(prims_, _page);
        _definedKw.validateKeyWordDuplicates(keyWords_, _page);
        _definedKw.validateEscapingsDuplicates(escapings_, _page);
        StringMap<String> nbWordsBasic_ = _definedKw.allNbWords(new StringMap<String>());
        _definedKw.validateNbWordDuplicates(nbWordsBasic_, _page);
        StringMap<String> nbWordsDec_ = _definedKw.allNbWords(_definedKw.allNbWordsDec());
        _definedKw.validateNbWordDuplicates(nbWordsDec_, _page);
        StringMap<String> nbWordsBin_ = _definedKw.allNbWords(_definedKw.allNbWordsBin());
        _definedKw.validateNbWordDuplicates(nbWordsBin_, _page);
        StringMap<String> nbWordsPreBin_ = _definedKw.allNbWords(_definedKw.allNbWordsPreBin());
        _definedKw.validateNbWordDuplicates(nbWordsPreBin_, _page);
        _definedKw.validateStartsPrefixesDuplicates(_page);
        StringMap<String> refTypes_ = defaultAliasGroups_.allRefTypes();
        ValidatorStandard.validateRefTypeContents(refTypes_, prims_, _page);
        boolean dup_ = ValidatorStandard.validateRefTypeDuplicates(refTypes_, _page);
        if (dup_) {
            return false;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = defaultAliasGroups_.allTableTypeMethodNames();
        ValidatorStandard.validateMethodsContents(methods_, prims_, _page);
        CustList<CustList<KeyValueMemberName>> params_ = defaultAliasGroups_.allTableTypeMethodParamNames();
        ValidatorStandard.validateParamtersContents(params_, prims_, _page);
        StringMap<CustList<KeyValueMemberName>> fields_ = defaultAliasGroups_.allTableTypeFieldNames();
        ValidatorStandard.validateFieldsContents(fields_, prims_, _page);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = defaultAliasGroups_.allTableTypeVarTypes();
        ValidatorStandard.validateVarTypesContents(varTypes_, prims_, _page);
        //duplicates
        ValidatorStandard.validateMethodsDuplicates(methods_, _page);
        ValidatorStandard.validateParamtersDuplicates(params_, _page);
        ValidatorStandard.validateFieldsDuplicates(fields_, _page);
        ValidatorStandard.validateVarTypesDuplicates(varTypes_, _page);
        CustList<CustList<KeyValueMemberName>> merge_ = defaultAliasGroups_.allMergeTableTypeMethodNames();
        ValidatorStandard.validateMergedDuplicates(merge_, _page);
        return _page.isEmptyStdError();
    }
}
