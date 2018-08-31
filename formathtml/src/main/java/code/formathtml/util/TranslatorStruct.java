package code.formathtml.util;

import code.bean.translator.Translator;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.ObjectMap;

public final class TranslatorStruct implements Struct {

    private Translator translator;

    public TranslatorStruct(Translator _translator) {
        translator = _translator;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
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
    public String getClassName(ExecutableCode _contextEl) {
        return translator.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Translator getInstance() {
        return translator;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

}
