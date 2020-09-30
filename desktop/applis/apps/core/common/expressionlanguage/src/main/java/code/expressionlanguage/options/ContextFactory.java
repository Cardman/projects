package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ContextFactory {

    private ContextFactory(){}

    public static ReportedMessages validate(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl, String _folder,
                                            CustList<CommentDelimiters> _comments, Options _options, ClassesCommon _com, AbstractConstantsCalculator _calculator, AbstractFileBuilder _fileBuilder, LgNamesContent _content) {
        AnalyzedPageEl page_ = validateStds(_mess, _definedKw, _definedLgNames, _comments, _options, _com, _calculator, _fileBuilder, _content, _contextEl.getTabWidth());
        return addResourcesAndValidate(_files, _contextEl, _folder, page_, new Forwards());
    }

    public static ReportedMessages addResourcesAndValidate(StringMap<String> _files, ContextEl _contextEl, String _folder, AnalyzedPageEl _page, Forwards _forwards) {
        StringMap<String> srcFiles_ = new StringMap<String>();
        String pref_ = StringList.concat(_folder,"/");
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith(pref_)) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        _page.addResources(_files);
        return Classes.validateAll(srcFiles_, _contextEl, _page, _forwards);
    }

    public static ContextEl simpleBuild(int _stack, DefaultLockingClass _lock, Initializer _init, Options _options, LgNames _definedLgNames, int _tabWidth, ClassesCommon _com) {
        return new SingleContextEl(_stack, _lock, _init, _options, _definedLgNames,_tabWidth, _com);
    }

    public static AnalyzedPageEl validateStds(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames,
                                              CustList<CommentDelimiters> _comments, Options _options, ClassesCommon _com, AbstractConstantsCalculator _calculator, AbstractFileBuilder _fileBuilder, LgNamesContent _content, int _tabWidth) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setOptions(_options);
        CustList<CommentDelimiters> comments_ = _options.getComments();
        CommentsUtil.checkAndUpdateComments(comments_,_comments);
        page_.setComments(comments_);
        page_.setAnalysisMessages(_mess);
        page_.setKeyWords(_definedKw);
        page_.setStandards(_content);
        page_.setCalculator(_calculator);
        page_.setFileBuilder(_fileBuilder);
        page_.setResources(_com.getResources());
        page_.setStaticFields(_com.getStaticFields());
        page_.setTabWidth(_tabWidth);
        page_.setGettingErrors(_options.isGettingErrors());
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        if (!page_.isEmptyMessageError()) {
            return page_;
        }
        StringMap<String> keyWords_ = _definedKw.allKeyWords();
        _definedKw.validateKeyWordContents(keyWords_, page_);
        StringMap<String> escapings_ = _definedKw.allEscapings();
        _definedKw.validateEscapingsContents(escapings_, page_);
        StringMap<String> nbWords_ = _definedKw.allNbWords(_definedKw.allNbWordsBasic());
        _definedKw.validateNbWordContents(nbWords_, page_);
        _definedKw.validateBinarySeparators(page_);
        DefaultAliasGroups defaultAliasGroups_ = _fileBuilder.getDefaultAliasGroups();
        StringMap<String> prims_ = defaultAliasGroups_.allPrimitives();
        ValidatorStandard.validatePrimitiveContents(prims_, page_);
        ValidatorStandard.validatePrimitiveDuplicates(prims_, page_);
        _definedKw.validateKeyWordDuplicates(keyWords_, page_);
        _definedKw.validateEscapingsDuplicates(escapings_, page_);
        StringMap<String> nbWordsDec_ = _definedKw.allNbWords(_definedKw.allNbWordsDec());
        _definedKw.validateNbWordDuplicates(nbWordsDec_, page_);
        StringMap<String> nbWordsBin_ = _definedKw.allNbWords(_definedKw.allNbWordsBin());
        _definedKw.validateNbWordDuplicates(nbWordsBin_, page_);
        StringMap<String> nbWordsPreBin_ = _definedKw.allNbWords(_definedKw.allNbWordsPreBin());
        _definedKw.validateNbWordDuplicates(nbWordsPreBin_, page_);
        _definedKw.validateStartsPrefixesDuplicates(page_);
        StringMap<String> refTypes_ = defaultAliasGroups_.allRefTypes();
        ValidatorStandard.validateRefTypeContents(refTypes_, prims_, page_);
        boolean dup_ = ValidatorStandard.validateRefTypeDuplicates(refTypes_, page_);
        if (dup_) {
            return page_;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = defaultAliasGroups_.allTableTypeMethodNames();
        ValidatorStandard.validateMethodsContents(methods_, prims_, page_);
        CustList<CustList<KeyValueMemberName>> params_ = defaultAliasGroups_.allTableTypeMethodParamNames();
        ValidatorStandard.validateParamtersContents(params_, prims_, page_);
        StringMap<CustList<KeyValueMemberName>> fields_ = defaultAliasGroups_.allTableTypeFieldNames();
        ValidatorStandard.validateFieldsContents(fields_, prims_, page_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = defaultAliasGroups_.allTableTypeVarTypes();
        ValidatorStandard.validateVarTypesContents(varTypes_, prims_, page_);
        //duplicates
        ValidatorStandard.validateMethodsDuplicates(methods_, page_);
        ValidatorStandard.validateParamtersDuplicates(params_, page_);
        ValidatorStandard.validateFieldsDuplicates(fields_, page_);
        ValidatorStandard.validateVarTypesDuplicates(varTypes_, page_);
        CustList<CustList<KeyValueMemberName>> merge_ = defaultAliasGroups_.allMergeTableTypeMethodNames();
        ValidatorStandard.validateMergedDuplicates(merge_, page_);
        if (!page_.isEmptyStdError()) {
            return page_;
        }
        _definedLgNames.build();
        ValidatorStandard.setupOverrides(page_);
        return page_;
    }
}
