package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CustomReflectLambdaConstructor extends AbstractReflectElement {

    private final ConstructorMetaInfo gl;

    private final Argument argument;
    private final ArgumentListCall array;
    private final int ref;
    public CustomReflectLambdaConstructor(ConstructorMetaInfo _gl,
                                          Struct _struct, ArgumentListCall _array, int _r) {
        super(true);
        gl = _gl;
        argument = new Argument(_struct);
        array = _array;
        ref = _r;
    }

    public ConstructorMetaInfo getGl() {
        return gl;
    }

    public int getRef() {
        return ref;
    }

    public Argument getArgument() {
        return argument;
    }

    public ArgumentListCall getArray() {
        return array;
    }
}
