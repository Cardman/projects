package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CustomReflectLambdaConstructor extends AbstractReflectElement {

    private final ConstructorMetaInfo gl;

    private final Argument argument;
    private final ArgumentListCall array;
    public CustomReflectLambdaConstructor(ConstructorMetaInfo _gl,
                                          Struct _struct, ArgumentListCall _array) {
        super(true);
        gl = _gl;
        argument = new Argument(_struct);
        array = _array;
    }

    public ConstructorMetaInfo getGl() {
        return gl;
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.CONSTRUCTOR;
    }

    public Argument getArgument() {
        return argument;
    }

    public ArgumentListCall getArray() {
        return array;
    }
}
