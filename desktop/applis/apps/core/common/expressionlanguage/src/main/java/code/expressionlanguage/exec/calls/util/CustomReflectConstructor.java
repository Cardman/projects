package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.util.CustList;

public final class CustomReflectConstructor extends AbstractReflectElement {

    private final ConstructorMetaInfo gl;

    public CustomReflectConstructor(ConstructorMetaInfo _gl,
                                    CustList<Argument> _arguments, boolean _lambda) {
        super(_arguments,_lambda);
        gl = _gl;
    }

    public ConstructorMetaInfo getGl() {
        return gl;
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.CONSTRUCTOR;
    }
}
