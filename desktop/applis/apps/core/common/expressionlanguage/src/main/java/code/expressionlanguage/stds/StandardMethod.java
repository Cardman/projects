package code.expressionlanguage.stds;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction {

    private final MethodModifier modifier;
    private final StdCaller caller;

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier) {
        this(_name, _parametersTypes, _returnType, _varargs,_modifier,new StringList());
    }
    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier ,StdCaller _caller) {
        this(_name, _parametersTypes, _returnType, _varargs,_modifier,new StringList(),_caller);
    }

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier, StringList _paramNames) {
        this(_name, _parametersTypes, _returnType, _varargs,_modifier,_paramNames,null);
    }

    public StandardMethod(String _name, StringList _parametersTypes,
                          String _returnType, boolean _varargs, MethodModifier _modifier, StringList _paramNames ,StdCaller _caller) {
        super(_name, _parametersTypes, _returnType, _varargs,_paramNames);
        modifier = _modifier;
        caller = _caller;
    }

    public MethodId getId() {
        String name_ = getName();
        return new MethodId(MethodId.getKind(modifier), name_, getImportedParametersTypes(), isVarargs());
    }

    public MethodModifier getModifier() {
        return modifier;
    }

    public StdCaller getCaller() {
        return caller;
    }
}
