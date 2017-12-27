package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;
import code.util.StringList;

public final class StringStruct implements Struct {

    private final String instance;

    public StringStruct(String _instance) {
        instance = _instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
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
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StringStruct)) {
            return false;
        }
        StringStruct other_ = (StringStruct) _other;
        return StringList.quickEq(getInstance(), other_.getInstance());
    }

}
