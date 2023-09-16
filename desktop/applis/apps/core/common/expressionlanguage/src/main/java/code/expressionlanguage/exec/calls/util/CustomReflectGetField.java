package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.FieldMetaInfo;

public final class CustomReflectGetField extends CustomAbstractReflectField {
    private final int parent;
    public CustomReflectGetField(IntParentRetriever _i, FieldMetaInfo _gl,
                                 boolean _lambda, int _par) {
        super(_i, _gl,_lambda);
        parent = _par;
    }

    public int getParent() {
        return parent;
    }
}
