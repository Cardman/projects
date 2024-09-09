package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CustomReflectSetField extends CustomAbstractReflectField {

    private final Struct last;
    private final int parent;

    public CustomReflectSetField(IntParentRetriever _i, FieldMetaInfo _gl,
                                 Struct _last, boolean _lambda, int _par) {
        super(_i, _gl,_lambda);
        last = _last;
        this.parent = _par;
    }

    public int getParent() {
        return parent;
    }

    public Struct getLast() {
        return last;
    }

}
