package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CustomReflectAnnotationsSupp extends AbstractReflectElement {

    private final MethodMetaInfo gl;

    private final CustList<Argument> arguments;
    public CustomReflectAnnotationsSupp(MethodMetaInfo _gl,
                                        CustList<Argument> _arguments) {
        super(false);
        gl = _gl;
        arguments = _arguments;
    }

    public ReflectingType getReflect() {
        return ReflectingType.ANNOT_SUPP;
    }

    public MethodMetaInfo getGl() {
        return gl;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}