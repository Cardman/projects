package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctOperation extends AbsFctOperation {

    public FctOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        init(_page);
        int off_ = StringUtil.getFirstPrintableCharIndex(getCallFctContent().getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        AnaClassArgumentMatching clCur_ = getPreviousResultClass(_page);
        StringList l_ = clCur_.getNames();
        setDelta(_page);
        String trimMeth_ = getCallFctContent().getMethodName().trim();
        boolean staticChoiceMethod_ = stChoice(_page);
        boolean accessSuperTypes_ = superClass(_page);
        boolean baseAccess_ = baseAccess(_page);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_) || StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = clName(_page);
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _page);
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            return;
        }
        setMethodFound(trimMeth_);
        setMethodInfos(getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, isImportType(), _page, new ScopeFilter(null, baseAccess_, accessSuperTypes_, isLvalue(), staticChoiceMethod_, _page.getGlobalClass()), getFormattedFilter(_page, this)));
        filterByNameReturnType(_page, trimMeth_, getMethodInfos());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getCallFctContent().getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        AnaClassArgumentMatching clCur_ = getPreviousResultClass(_page);
        setDelta(_page);
        String trimMeth_ = getCallFctContent().getMethodName().trim();

        boolean staticChoiceMethod_ = stChoice(_page);
        boolean accessSuperTypes_ = superClass(_page);
        boolean baseAccess_ = baseAccess(_page);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        StringList l_ = clCur_.getNames();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_) || StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            trimMeth_ = trimMeth_.substring(trimMeth_.indexOf('.')+1).trim();
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            String className_ = clNameAna(_page);
            checkOwner(_page,clCur_,className_);
            trimMeth_ = trimMeth_.substring(trimMeth_.lastIndexOf(PAR_RIGHT) + 1).trim();
            l_ = getBounds(className_, _page);
        }
        ClassMethodIdAncestor feed_ = id(trimMeth_);
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        StringList arrayBounds_ = getArrayBounds(bounds_);
        if (!arrayBounds_.isEmpty()) {
            if (!StringUtil.quickEq(trimMeth_, _page.getAliasClone())) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFile(_page.getCurrentFile());
                undefined_.setIndexFile(_page);
                //trimMeth_ len
                undefined_.buildError(_page.getAnalysisMessages().getArrayCloneOnly(),
                        _page.getAliasClone(),
                        StringUtil.join(arrayBounds_, ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(undefined_);
                addErr(undefined_.getBuiltError());
                return;
            }
            setErrLeftValue(true);
            setClonedMethod(true);
            String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, trimMeth_, new StringList());
            getCallFctContent().setClassMethodId(new ClassMethodId(foundClass_, id_));
            setResultClass(new AnaClassArgumentMatching(arrayBounds_));
            return;
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            trueFalse(_page, trimMeth_, staticChoiceMethod_, feed_, bounds_, name_);
            return;
        }
        std(_page, trimMeth_, bounds_, name_, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, isLvalue(), staticChoiceMethod_, _page.getGlobalClass()));
    }
    private boolean stChoice(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String trimMeth_ = getCallFctContent().getMethodName().trim();
        return StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_) || StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_);
    }
    private boolean baseAccess(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String trimMeth_ = getCallFctContent().getMethodName().trim();
        return !StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_);
    }
    private boolean superClass(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        String trimMeth_ = getCallFctContent().getMethodName().trim();
        return !StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_);
    }

    private void setDelta(AnalyzedPageEl _page) {
        String methodName_ = getCallFctContent().getMethodName();
        String trimMeth_ = methodName_.trim();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThisaccess_ = keyWords_.getKeyWordThisaccess();
        int delta_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        int lenMeh_ = trimMeth_.length();
        if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordSuper_)||StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThat_)) {
            int after_ = trimMeth_.indexOf('.') + 1;
            delta_ += after_;
            lenMeh_ -= after_;
            delta_ += StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
            lenMeh_ -= StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(after_));
        } else if (StringExpUtil.startsWithKeyWord(trimMeth_, keyWordThisaccess_)) {
            int lastAfter_ = trimMeth_.lastIndexOf(PAR_RIGHT) + 1;
            delta_ += lastAfter_;
            lenMeh_ -= lastAfter_;
            delta_ += StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
            lenMeh_ -= StringUtil.getFirstPrintableCharIndex(trimMeth_.substring(lastAfter_));
        }
        setLengthMethod(lenMeh_);
        setDelta(delta_);
    }
    private static StringList getArrayBounds(StringList _bounds) {
        StringList b_ = new StringList();
        for (String b: _bounds) {
            if (b.startsWith(StringExpUtil.ARR_CLASS)) {
                b_.add(b);
            }
        }
        return b_;
    }
}
