package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ContextFactory {

    private ContextFactory(){}

    public static ReportedMessages validate(AnalysisMessages _mess, KeyWords _definedKw, BuildableLgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl, String _folder,
                                            CustList<CommentDelimiters> _comments, Options _options, ClassesCommon _com, AbstractConstantsCalculator _calculator, AbstractFileBuilder _fileBuilder, LgNamesContent _content) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        validateStds(_mess, _definedKw, _definedLgNames, _comments, _options, _com, _calculator, _fileBuilder, _content, _contextEl.getTabWidth(), page_, new DefaultFieldFilter());
        return addResourcesAndValidate(_files, _contextEl, _folder, page_, new Forwards());
    }

    public static ReportedMessages addResourcesAndValidate(StringMap<String> _files, ContextEl _contextEl, String _folder, AnalyzedPageEl _page, Forwards _forwards) {
        StringMap<String> srcFiles_ = new StringMap<String>();
        String pref_ = StringUtil.concat(_folder,"/");
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith(pref_)) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        _page.addResources(_files);
        return Classes.validateAll(srcFiles_, _contextEl, _page, _forwards);
    }

    public static ContextEl simpleBuild(int _stack, Options _options, BuildableLgNames _definedLgNames, int _tabWidth) {
        return _definedLgNames.newContext(_tabWidth,_stack, new Coverage(_options.isCovering()));
    }

    public static void validateStds(AnalysisMessages _mess, KeyWords _definedKw, BuildableLgNames _definedLgNames,
                                    CustList<CommentDelimiters> _comments, Options _options, ClassesCommon _com, AbstractConstantsCalculator _calculator, AbstractFileBuilder _fileBuilder, LgNamesContent _content, int _tabWidth, AnalyzedPageEl _page, AbstractFieldFilter _fieldFilter) {
        if (validatedStds(_mess,_definedKw,_comments,_options,_com,_calculator,_fileBuilder,_content,_tabWidth,_page, _fieldFilter)) {
            _definedLgNames.build();
            ValidatorStandard.setupOverrides(_page);
        }
    }
    public static boolean validatedStds(AnalysisMessages _mess, KeyWords _definedKw,
                                        CustList<CommentDelimiters> _comments, Options _options, ClassesCommon _com, AbstractConstantsCalculator _calculator, AbstractFileBuilder _fileBuilder, LgNamesContent _content, int _tabWidth, AnalyzedPageEl _page, AbstractFieldFilter _fieldFilter) {
        _page.setOptions(_options);
        CustList<CommentDelimiters> comments_ = _options.getComments();
        CommentsUtil.checkAndUpdateComments(comments_,_comments);
        _page.setComments(comments_);
        _page.setDefaultAccess(_options.getDefaultAccess());
        _page.setAnalysisMessages(_mess);
        _page.setKeyWords(_definedKw);
        _page.setStandards(_content);
        _page.setCalculator(_calculator);
        _page.setFieldFilter(_fieldFilter);
        _page.setFileBuilder(_fileBuilder);
        _page.setResources(_com.getResources());
        _page.setStaticFields(_com.getStaticFields());
        _page.setTabWidth(_tabWidth);
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
        DefaultAliasGroups defaultAliasGroups_ = _fileBuilder.getDefaultAliasGroups();
        StringMap<String> prims_ = defaultAliasGroups_.allPrimitives();
        ValidatorStandard.validatePrimitiveContents(prims_, _page);
        ValidatorStandard.validatePrimitiveDuplicates(prims_, _page);
        _definedKw.validateKeyWordDuplicates(keyWords_, _page);
        _definedKw.validateEscapingsDuplicates(escapings_, _page);
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
