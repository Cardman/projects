package code.expressionlanguage.stds;

import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction {

    private final MethodModifier modifier;

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier ,StdCaller _caller) {
        this(_name, _parametersTypes, _returnType, _varargs,_modifier,new StringList(),_caller);
    }

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier, StringList _paramNames ,StdCaller _caller) {
        super(_name, _parametersTypes, _returnType, _varargs,_paramNames, _caller);
        modifier = _modifier;
    }

    @Override
    public Identifiable id() {
        return getId();
    }

    @Override
    public String getSignature(DisplayedStrings _dis) {
        return getId().getSignature(_dis);
    }

    public MethodId getId() {
        String name_ = getName();
        return new MethodId(MethodId.getKind(modifier), name_, getImportedParametersTypes(), isVarargs());
    }

    public MethodModifier getModifier() {
        return modifier;
    }
}
