package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.analyze.blocks.MethodKind;
import code.expressionlanguage.analyze.blocks.OverridableBlock;
import code.expressionlanguage.analyze.blocks.ReturnableWithSignature;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class ExecOverridableBlock extends ExecNamedFunctionBlock implements GeneCustModifierMethod,ReturnableWithSignature {

    private final int modifierOffset;

    private final boolean staticMethod;
    private final boolean staticCallMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final MethodKind kind;
    public ExecOverridableBlock(OverridableBlock _offset) {
        super(_offset);
        modifierOffset = _offset.getModifierOffset();
        staticMethod = _offset.isStaticMethod();
        staticCallMethod = _offset.isStaticCallMethod();
        finalMethod = _offset.isFinalMethod();
        abstractMethod = _offset.isAbstractMethod();
        kind = _offset.getKind();
    }

    @Override
    public boolean isFinalMethod() {
        return finalMethod;
    }

    @Override
    public boolean hiddenInstance() {
        return staticCallMethod || staticMethod;
    }

    @Override
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST) {
            pTypes_.add(getImportedReturnType());
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());

    }

    public MethodModifier getModifier() {
        if (abstractMethod) {
            return MethodModifier.ABSTRACT;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }
    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public void buildImportedTypes(OverridableBlock _key) {
        setImportedReturnType(_key.getImportedReturnType());
        getImportedParametersTypes().addAllElts(_key.getImportedParametersTypes());
    }

    public MethodKind getKind() {
        return kind;
    }
}
