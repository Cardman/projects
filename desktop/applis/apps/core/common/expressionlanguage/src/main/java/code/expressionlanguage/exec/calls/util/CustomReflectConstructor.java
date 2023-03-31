package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.ConstructorMetaInfo;

public final class CustomReflectConstructor extends AbstractReflectElement {

    private final ConstructorMetaInfo gl;

    private final ArrayRefState arrRef;

    public CustomReflectConstructor(ConstructorMetaInfo _gl, ArrayRefState _a) {
        super(false);
        arrRef = _a;
        gl = _gl;
    }

    public ConstructorMetaInfo getGl() {
        return gl;
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.CONSTRUCTOR;
    }

    public ArrayRefState getArrRef() {
        return arrRef;
    }

}
