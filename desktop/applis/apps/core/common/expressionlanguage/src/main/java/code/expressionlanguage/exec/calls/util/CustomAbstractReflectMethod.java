package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.MethodMetaInfo;

public abstract class CustomAbstractReflectMethod extends AbstractReflectElement {

    private final MethodMetaInfo gl;

    protected CustomAbstractReflectMethod(MethodMetaInfo _gl,
                                       boolean _lambda) {
        super(_lambda);
        gl = _gl;
    }

    public MethodMetaInfo getGl() {
        return gl;
    }
}
