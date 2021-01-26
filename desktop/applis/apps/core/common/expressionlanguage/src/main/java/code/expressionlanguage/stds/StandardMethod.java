package code.expressionlanguage.stds;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction {

    private final MethodModifier modifier;

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier) {
        this(_name, _parametersTypes, _returnType, _varargs,_modifier,new StringList());
    }

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier, StringList _paramNames) {
        super(_name, _parametersTypes, _returnType, _varargs,_paramNames);
        modifier = _modifier;
    }

    public MethodId getId() {
        String name_ = getName();
        return new MethodId(MethodId.getKind(modifier), name_, getImportedParametersTypes(), isVarargs());
    }

    public MethodModifier getModifier() {
        return modifier;
    }

}
