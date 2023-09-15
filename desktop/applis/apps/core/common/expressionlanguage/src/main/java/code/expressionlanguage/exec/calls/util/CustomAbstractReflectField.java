package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.FieldMetaInfo;

public abstract class CustomAbstractReflectField extends AbstractReflectElement {

    private final FieldMetaInfo gl;
    private final IntParentRetriever intParentRetriever;

    protected CustomAbstractReflectField(IntParentRetriever _i, FieldMetaInfo _gl,
                                         boolean _lambda) {
        super(_lambda);
        intParentRetriever = _i;
        gl = _gl;
    }

    public FieldMetaInfo getGl() {
        return gl;
    }

    public IntParentRetriever getIntParentRetriever() {
        return intParentRetriever;
    }
}
