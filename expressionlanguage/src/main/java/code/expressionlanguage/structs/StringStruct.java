package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class StringStruct extends CharSequenceStruct {

    private final String instance;

    public StringStruct(String _instance) {
        instance = _instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasString();
    }

    @Override
    public String getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
