package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;
import code.util.StringList;


public final class FieldMetaInfo implements Struct {
    private final AccessEnum access;
    private final String declaringClass;
    private final String name;

    private final String type;

    private final boolean staticField;

    private final boolean finalField;

    private final boolean enumElement;

    public FieldMetaInfo(String _declaringClass,
            String _name,
            String _returnType, boolean _static,
            boolean _finalField, boolean _enumElement,
            AccessEnum _access) {
        declaringClass = _declaringClass;
        name = _name;
        type = _returnType;
        staticField = _static;
        finalField = _finalField;
        enumElement = _enumElement;
        access = _access;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public boolean isPublic() {
        return access == AccessEnum.PUBLIC;
    }
    
    public boolean isProtected() {
        return access == AccessEnum.PROTECTED;
    }
    
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }

    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }
    public String getDeclaringClass() {
        return declaringClass;
    }
    public String getName() {
        return name;
    }
    public boolean isStaticField() {
        return staticField;
    }
    public boolean isFinalField() {
        return finalField;
    }
    public String getType() {
        return type;
    }
    public boolean isEnumElement() {
        return enumElement;
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
        return _contextEl.getStandards().getAliasField();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FieldMetaInfo)) {
            return false;
        }
        FieldMetaInfo f_ = (FieldMetaInfo) _other;
        if (!StringList.quickEq(declaringClass, f_.declaringClass)) {
            return false;
        }
        return StringList.quickEq(name, f_.name);
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
