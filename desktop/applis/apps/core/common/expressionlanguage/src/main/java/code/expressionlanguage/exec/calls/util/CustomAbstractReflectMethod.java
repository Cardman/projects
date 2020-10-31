package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public abstract class CustomAbstractReflectMethod extends AbstractReflectElement {

    private final MethodMetaInfo gl;

    public CustomAbstractReflectMethod(MethodMetaInfo _gl,
                                       CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        gl = _gl;
    }

    public MethodMetaInfo getGl() {
        return gl;
    }
}
