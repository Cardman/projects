package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class CustomReflectAnnotations extends AbstractReflectElement {

    private final ReflectingType reflect;

    private final AnnotatedStruct gl;

    private final CustList<Struct> arguments;
    public CustomReflectAnnotations(ReflectingType _reflect, AnnotatedStruct _gl,
                                    CustList<Struct> _arguments) {
        super(false);
        reflect = _reflect;
        gl = _gl;
        arguments = _arguments;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public AnnotatedStruct getGl() {
        return gl;
    }

    public CustList<Struct> getArguments() {
        return arguments;
    }
}