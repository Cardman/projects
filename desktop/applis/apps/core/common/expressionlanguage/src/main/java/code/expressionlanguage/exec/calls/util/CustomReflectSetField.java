package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.util.CustList;

public final class CustomReflectSetField extends CustomAbstractReflectField {

    private final Argument first;
    private final Argument last;

    public CustomReflectSetField(ReflectingType _reflect, FieldMetaInfo _gl,
                                 Argument _first, Argument _last, boolean _lambda) {
        super(_reflect,_gl,_lambda);
        first = _first;
        last = _last;
    }

    public Argument getFirst() {
        return first;
    }

    public Argument getLast() {
        return last;
    }

}
