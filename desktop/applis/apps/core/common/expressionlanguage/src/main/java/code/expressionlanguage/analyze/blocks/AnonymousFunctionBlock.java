package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.common.GeneCustStaticMethod;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class AnonymousFunctionBlock extends NamedFunctionBlock implements GeneCustStaticMethod,ReturnableWithSignature {
    private RootBlock parentType;
    private int indexEnd;
    private StringList allReservedInners = new StringList();
    private final boolean staticMethod;
    private final boolean staticCallMethod;
    private int numberLambda;
    private final AnaCache cache = new AnaCache();

    public AnonymousFunctionBlock(int _fctName,
                                  OffsetsBlock _offset, AnalyzedPageEl _page) {
        super(_fctName, _offset, _page);
        MethodAccessKind stCtx_ = _page.getStaticContext();
        staticMethod = stCtx_ == MethodAccessKind.STATIC;
        staticCallMethod = stCtx_ == MethodAccessKind.STATIC_CALL;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }

    public MethodModifier getModifier() {
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isStaticCallMethod() {
        return staticCallMethod;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        if (staticMethod) {
            return MethodAccessKind.STATIC;
        }
        if (staticCallMethod) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }

    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        checkReturnFctOverridable(_anEl, _page);
    }

    private void checkReturnFctOverridable(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (_anEl.canCompleteNormally(this)) {
                //error
                FoundErrorInterpret miss_ = new FoundErrorInterpret();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                //return type len
                miss_.buildError(_page.getAnalysisMessages().getMissingAbrupt(),
                        _page.getKeyWords().getKeyWordThrow(),
                        _page.getKeyWords().getKeyWordReturn(),
                        getPseudoSignature(_page));
                _page.addLocError(miss_);
                addNameErrors(miss_);
            }
        }
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public void setParentType(RootBlock parentType) {
        this.parentType = parentType;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

    public int getNumberLambda() {
        return numberLambda;
    }

    public void setNumberLambda(int numberLambda) {
        this.numberLambda = numberLambda;
    }

    public AnaCache getCache() {
        return cache;
    }
}
