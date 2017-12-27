package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public final class StringBuilderStruct implements Struct {

    private final StringBuilder instance;

    public StringBuilderStruct(StringBuilder _instance) {
        instance = _instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasStringBuilder();
    }

    @Override
    public StringBuilder getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StringBuilderStruct)) {
            return false;
        }
        StringBuilderStruct other_ = (StringBuilderStruct) _other;
        return getInstance() == other_.getInstance();
    }

}
