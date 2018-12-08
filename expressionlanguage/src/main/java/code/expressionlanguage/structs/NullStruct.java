package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class NullStruct implements DisplayableStruct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(_an.getStandards().getNullString());
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return _other == NULL_VALUE;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
