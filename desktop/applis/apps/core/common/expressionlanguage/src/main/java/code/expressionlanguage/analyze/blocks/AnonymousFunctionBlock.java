package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class AnonymousFunctionBlock extends NamedCalledFunctionBlock implements ReturnableWithSignature {
    private RootBlock parentType;
    private int indexEnd;
    private StringList allReservedInners = new StringList();
    private final boolean staticMethod;
    private final boolean staticCallMethod;
    private int numberLambda;
    private final AnaAnonFctContent anaAnonFctContent = new AnaAnonFctContent();

    public AnonymousFunctionBlock(int _fctName,
                                  OffsetsBlock _offset, AnalyzedPageEl _page) {
        super(_fctName, _offset, _page);
        MethodAccessKind stCtx_ = _page.getStaticContext();
        staticMethod = stCtx_ == MethodAccessKind.STATIC;
        staticCallMethod = stCtx_ == MethodAccessKind.STATIC_CALL;
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

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,getParametersRef(), isVarargs());
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        if (isStaticMethod()) {
            return MethodAccessKind.STATIC;
        }
        if (staticCallMethod) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public void setParentType(RootBlock _parentType) {
        this.parentType = _parentType;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int _indexEnd) {
        this.indexEnd = _indexEnd;
    }

    public int getNumberLambda() {
        return numberLambda;
    }

    public void setNumberLambda(int _numberLambda) {
        this.numberLambda = _numberLambda;
    }

    public AnaCache getCache() {
        return anaAnonFctContent.getCache();
    }

    public AnaAnonFctContent getAnaAnonFctContent() {
        return anaAnonFctContent;
    }
}
