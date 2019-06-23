package code.formathtml.util;

import code.bean.validator.Validator;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ValidatorStruct implements RealInstanceStruct {

    private Validator translator;

    public ValidatorStruct(Validator _translator) {
        translator = _translator;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }


    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return translator.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return translator;
    }


}
