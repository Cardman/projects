package code.expressionlanguage.exec;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ArgumentWrapper {
    private final Struct value;
    private final AbstractWrapper wrapper;

    public ArgumentWrapper(Struct _value) {
        this(_value,null);
    }

    public ArgumentWrapper(Struct _value, AbstractWrapper _wrapper) {
        this.value = ArgumentListCall.getNull(_value);
        this.wrapper = _wrapper;
    }

    public static Struct helpArg(ArgumentWrapper _wrap) {
        if (_wrap == null){
            return NullStruct.NULL_VALUE;
        }
        return _wrap.value;
    }

    public Struct getValue() {
        return value;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }
}
