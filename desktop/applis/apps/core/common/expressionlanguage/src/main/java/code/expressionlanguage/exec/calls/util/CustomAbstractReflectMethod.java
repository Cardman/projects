package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public abstract class CustomAbstractReflectMethod extends AbstractReflectElement {

    private final MethodMetaInfo gl;

    public CustomAbstractReflectMethod(MethodMetaInfo _gl,
                                       boolean _lambda) {
        super(_lambda);
        gl = _gl;
    }

    public MethodMetaInfo getGl() {
        return gl;
    }
}
