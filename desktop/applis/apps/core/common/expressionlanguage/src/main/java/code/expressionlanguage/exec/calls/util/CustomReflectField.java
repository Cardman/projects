package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.util.CustList;

public final class CustomReflectField extends AbstractReflectElement {

    private final ReflectingType reflect;
    private final FieldMetaInfo gl;

    public CustomReflectField(ReflectingType _reflect,FieldMetaInfo _gl,
                              CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        reflect = _reflect;
        gl = _gl;
    }

    public FieldMetaInfo getGl() {
        return gl;
    }

    @Override
    public ReflectingType getReflect() {
        return reflect;
    }
}
