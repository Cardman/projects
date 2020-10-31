package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.util.CustList;

public abstract class AbstractReflectElement implements CallingState {

    private final CustList<Argument> arguments;

    private final boolean lambda;

    public AbstractReflectElement(CustList<Argument> _arguments, boolean _lambda) {
        arguments = _arguments;
        lambda = _lambda;
    }

    public abstract ReflectingType getReflect();
    public CustList<Argument> getArguments() {
        return arguments;
    }
    public boolean isLambda() {
        return lambda;
    }

}
