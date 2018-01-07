package code.formathtml.util;

import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.ObjectMap;

public final class ValidatorStruct implements Struct {

    private Validator translator;

    public ValidatorStruct(Validator _translator) {
        translator = _translator;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return translator.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Validator getInstance() {
        return translator;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

}
