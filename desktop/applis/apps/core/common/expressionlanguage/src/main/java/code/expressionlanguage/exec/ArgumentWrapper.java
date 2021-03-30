package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public final class ArgumentWrapper {
    private final Argument value;
    private final AbstractWrapper wrapper;

    public ArgumentWrapper(Argument _value, AbstractWrapper _wrapper) {
        this.value = _value;
        this.wrapper = _wrapper;
    }

    public static Argument helpArg(ArgumentWrapper _wrap) {
        if (_wrap == null){
            return Argument.createVoid();
        }
        return Argument.getNullableValue(_wrap.value);
    }

    public Argument getValue() {
        return value;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }
}
