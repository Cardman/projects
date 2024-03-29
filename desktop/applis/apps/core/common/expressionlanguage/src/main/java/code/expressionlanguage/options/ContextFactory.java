package code.expressionlanguage.options;

import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ContextFactory {

    private ContextFactory(){}

    public static StringMap<String> filter(StringMap<String> _files, String _folder) {
        StringMap<String> srcFiles_ = new StringMap<String>();
        String pref_ = StringUtil.concat(_folder,"/");
        for (EntryCust<String, String> e: _files.entryList()) {
        	if (!e.getKey().startsWith(pref_)) {
        		continue;
        	}
        	srcFiles_.addEntry(e.getKey(), e.getValue());
        }
        return srcFiles_;
    }

    public static boolean validateStds(AnalysisElementsBase _a) {
        Forwards fwd_ = _a.getFwd();
        KeyWords definedKw_ = _a.getDefinedKw();
        AnalyzedPageEl page_ = _a.getPage();
        if (!page_.isEmptyMessageError()) {
            return false;
        }
        if (validatedStds(fwd_, _a.getMess(),definedKw_, _a.getComments(), _a.getOptions(), _a.getContent(), page_)) {
            build(fwd_, definedKw_, page_.getOptions(), page_,_a.getLightResultContextNext());
            return true;
        }
        return false;
    }

    public static void build(Forwards _fwd, KeyWords _definedKw, Options _options, AnalyzedPageEl _page, AbsBuildLightResultContextNext _b) {
        ParsedArgument.buildCustom(_options, _definedKw);
        _b.build((LgNames) _fwd.getGenerator());
        ValidatorStandard.setupOverrides(_page);
    }

    public static boolean validatedStds(Forwards _fwd, AnalysisMessages _mess, KeyWords _definedKw,
                                        CustList<CommentDelimiters> _comments, Options _options, LgNamesContent _content, AnalyzedPageEl _page) {
        AbstractFileBuilder builder_ = beforeBuild(_fwd, _mess, _definedKw, _comments, _options, _content, _page);
        return validate(_definedKw, _page, builder_);
    }

    public static AbstractFileBuilder beforeBuild(Forwards _fwd, AnalysisMessages _mess, KeyWords _definedKw, CustList<CommentDelimiters> _comments, Options _options, LgNamesContent _content, AnalyzedPageEl _page) {
        _page.setOptions(_options);
        CustList<CommentDelimiters> comments_ = _options.getComments();
        CommentsUtil.checkAndUpdateComments(comments_, _comments);
        _page.setComments(comments_);
        _page.setDefaultAccess(_options.getDefaultAccess());
        _page.setAnalysisMessages(_mess);
        _page.setKeyWords(_definedKw);
        _page.setStandards(_content);
        _page.setCalculator(_fwd.getConstantsCalculator());
        AbstractFileBuilder builder_ = _fwd.getFileBuilder();
        _page.setFileBuilder(builder_);
        _page.setResources(_fwd.getResources());
        _page.setStaticFields(_fwd.getStaticFields());
        _page.setTabWidth(_options.getTabWidth());
        _page.setGettingErrors(_options.isGettingErrors());
        return builder_;
    }

    public static boolean validate(KeyWords _definedKw, AnalyzedPageEl _page, AbstractFileBuilder _builder) {
        DefaultAliasGroups defaultAliasGroups_ = _builder.getDefaultAliasGroups();
        StringMap<String> keyWords_ = _definedKw.allKeyWords(_page.getMappingKeyWords());
        _definedKw.validateKeyWordContents(keyWords_, _page);
        StringMap<String> escapings_ = _definedKw.allEscapings(_page.getMappingKeyWords());
        _definedKw.validateEscapingsContents(escapings_, _page);
        StringMap<String> nbWords_ = _definedKw.allNbWords(_page.getMappingKeyWords(),_definedKw.allNbWordsBasic(_page.getMappingKeyWords()));
        _definedKw.validateNbWordContents(nbWords_, _page);
        _definedKw.validateBinarySeparators(_page);
        StringMap<String> prims_ = defaultAliasGroups_.allPrimitives(_page.getMappingAliases());
        ValidatorStandard.validatePrimitiveContents(prims_, _page);
        ValidatorStandard.validatePrimitiveDuplicates(prims_, _page);
        _definedKw.validateKeyWordDuplicates(keyWords_, _page);
        _definedKw.validateEscapingsDuplicates(escapings_, _page);
        StringMap<String> nbWordsBasic_ = _definedKw.allNbWords(_page.getMappingKeyWords(),new StringMap<String>());
        _definedKw.validateNbWordDuplicates(nbWordsBasic_, _page);
        StringMap<String> nbWordsDec_ = _definedKw.allNbWords(_page.getMappingKeyWords(),_definedKw.allNbWordsDec(_page.getMappingKeyWords()));
        _definedKw.validateNbWordDuplicates(nbWordsDec_, _page);
        StringMap<String> nbWordsBin_ = _definedKw.allNbWords(_page.getMappingKeyWords(),_definedKw.allNbWordsBin(_page.getMappingKeyWords()));
        _definedKw.validateNbWordDuplicates(nbWordsBin_, _page);
        StringMap<String> nbWordsPreBin_ = _definedKw.allNbWords(_page.getMappingKeyWords(),_definedKw.allNbWordsPreBin(_page.getMappingKeyWords()));
        _definedKw.validateNbWordDuplicates(nbWordsPreBin_, _page);
        _definedKw.validateStartsPrefixesDuplicates(_page);
        StringMap<String> refTypes_ = defaultAliasGroups_.allRefTypes(_page.getMappingAliases());
        ValidatorStandard.validateRefTypeContents(refTypes_, prims_, _page);
        boolean dup_ = ValidatorStandard.validateRefTypeDuplicates(refTypes_, _page);
        if (dup_) {
            return false;
        }
        StringMap<CustList<KeyValueMemberName>> methods_ = defaultAliasGroups_.allTableTypeMethodNames(_page.getMappingAliases());
        ValidatorStandard.validateMethodsContents(methods_, prims_, _page);
        CustList<CustList<KeyValueMemberName>> params_ = defaultAliasGroups_.allTableTypeMethodParamNames(_page.getMappingAliases());
        ValidatorStandard.validateParamtersContents(params_, prims_, _page);
        StringMap<CustList<KeyValueMemberName>> fields_ = defaultAliasGroups_.allTableTypeFieldNames(_page.getMappingAliases());
        ValidatorStandard.validateFieldsContents(fields_, prims_, _page);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = defaultAliasGroups_.allTableTypeVarTypes(_page.getMappingAliases());
        ValidatorStandard.validateVarTypesContents(varTypes_, prims_, _page);
        //duplicates
        ValidatorStandard.validateMethodsDuplicates(methods_, _page);
        ValidatorStandard.validateParamtersDuplicates(params_, _page);
        ValidatorStandard.validateFieldsDuplicates(fields_, _page);
        ValidatorStandard.validateVarTypesDuplicates(varTypes_, _page);
        CustList<KeyValueMemberName> merge_ = defaultAliasGroups_.allMergeTableTypeMethodNames(_page.getMappingAliases());
        ValidatorStandard.validateMergedDuplicates(merge_, _page);
        return _page.isEmptyStdError();
    }
}
