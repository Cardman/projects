package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CustomReflectConstructor extends AbstractReflectElement {

    private final ConstructorMetaInfo gl;

    private final Argument argument;
    public CustomReflectConstructor(ConstructorMetaInfo _gl,
                                    Struct _struct) {
        super(false);
        gl = _gl;
        argument = new Argument(_struct);
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

}
