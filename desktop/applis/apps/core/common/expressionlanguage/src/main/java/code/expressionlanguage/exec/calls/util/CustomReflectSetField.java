package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class CustomReflectSetField extends CustomAbstractReflectField {

    private final Argument last;

    public CustomReflectSetField(IntParentRetriever _i, FieldMetaInfo _gl,
                                 Argument _last, boolean _lambda) {
        super(_i, _gl,_lambda);
        last = _last;
    }

    public Argument getLast() {
        return last;
    }

}
