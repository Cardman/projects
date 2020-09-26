package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.GeneCustModifierMethod;
import code.expressionlanguage.analyze.blocks.MethodKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class ExecOverridableBlock extends ExecNamedFunctionBlock implements GeneCustModifierMethod,ExecReturnableWithSignature {

    private final MethodModifier methodModifier;

    private final MethodKind kind;
    public ExecOverridableBlock(String _name, boolean _varargs, AccessEnum _access, StringList _parametersNames, MethodModifier _modifier, MethodKind _kind, int _offsetTrim) {
        super(_name, _varargs, _access, _parametersNames, _offsetTrim);
        methodModifier = _modifier;
        kind = _kind;
    }

    public boolean isAbstractMethod() {
        return methodModifier == MethodModifier.ABSTRACT;
    }

    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST
                ||kind == MethodKind.TRUE_OPERATOR || kind == MethodKind.FALSE_OPERATOR) {
            pTypes_.add(getImportedReturnType());
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());

    }

    public MethodModifier getModifier() {
        return methodModifier;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public void buildImportedTypes(String _importedReturnType, StringList _importedParametersTypes) {
        setImportedReturnType(_importedReturnType);
        getImportedParametersTypes().addAllElts(_importedParametersTypes);
    }

    public MethodKind getKind() {
        return kind;
    }
}
