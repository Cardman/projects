package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class CustomReflectSetField extends CustomAbstractReflectField {

    private final Argument last;
    private final int parent;

    public CustomReflectSetField(IntParentRetriever _i, FieldMetaInfo _gl,
                                 Argument _last, boolean _lambda, int _par) {
        super(_i, _gl,_lambda);
        last = _last;
        this.parent = _par;
    }

    public int getParent() {
        return parent;
    }

    public Argument getLast() {
        return last;
    }

}
