package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class CustomReflectGetField extends CustomAbstractReflectField {

    private final Argument argument;

    public CustomReflectGetField(ReflectingType _reflect, FieldMetaInfo _gl,
                                 Argument _argument, boolean _lambda) {
        super(_reflect,_gl,_lambda);
        argument = _argument;
    }

    public Argument getArgument() {
        return argument;
    }

}
