package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.FieldMetaInfo;

public abstract class CustomAbstractReflectField extends AbstractReflectElement {

    private final ReflectingType reflect;
    private final FieldMetaInfo gl;

    public CustomAbstractReflectField(ReflectingType _reflect, FieldMetaInfo _gl,
                                      boolean _lambda) {
        super(_lambda);
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
