package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;
import code.util.Replacement;

public final class ReplacementStruct implements Struct {

    private final Replacement instance;

    public ReplacementStruct(Replacement _instance) {
        instance = _instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasReplacement();
    }

    @Override
    public Replacement getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ReplacementStruct)) {
            return false;
        }
        ReplacementStruct other_ = (ReplacementStruct) _other;
        return getInstance() == other_.getInstance();
    }

}
