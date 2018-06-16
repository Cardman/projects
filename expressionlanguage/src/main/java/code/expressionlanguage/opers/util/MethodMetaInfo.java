package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;
import code.util.StringList;


public final class MethodMetaInfo implements Struct {

    private final String className;

    private final MethodId realId;

    private final MethodModifier modifier;

    private final String returnType;

    private boolean polymorph = true;

    public MethodMetaInfo(String _className, MethodId _realId, MethodModifier _modifier,String _returnType) {
        className = _className;
        realId = _realId;
        modifier = _modifier;
        returnType = _returnType;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public void setPolymorph(boolean _polymorph) {
        polymorph = _polymorph;
    }

    public String getClassName() {
        return className;
    }
    public MethodId getRealId() {
        return realId;
    }
    public boolean isStatic() {
        return getModifier() == MethodModifier.STATIC;
    }

    public boolean isAbstract() {
        return getModifier() == MethodModifier.ABSTRACT;
    }
    public MethodModifier getModifier() {
        return modifier;
    }
    public String getReturnType() {
        return returnType;
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
        return _contextEl.getStandards().getAliasMethod();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof MethodMetaInfo)) {
            return false;
        }
        MethodMetaInfo info_ = (MethodMetaInfo) _other;
        if (polymorph != info_.polymorph) {
            return false;
        }
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
