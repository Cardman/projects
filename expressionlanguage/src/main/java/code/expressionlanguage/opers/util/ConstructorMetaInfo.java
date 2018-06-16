package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;
import code.util.StringList;

public final class ConstructorMetaInfo implements Struct {

    private final String className;
    private final ConstructorId realId;

    public ConstructorMetaInfo(String _className, ConstructorId _realId) {
        className = _className;
        realId = _realId;
    }

    public String getClassName() {
        return className;
    }

    public ConstructorId getRealId() {
        return realId;
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
        return _contextEl.getStandards().getAliasConstructor();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ConstructorMetaInfo)) {
            return false;
        }
        ConstructorMetaInfo info_ = (ConstructorMetaInfo) _other;
        if (!StringList.quickEq(className, info_.className)) {
            return false;
        }
        return realId.eq(info_.realId);
    }

    @Override
    public Object getInstance() {
        return this;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
