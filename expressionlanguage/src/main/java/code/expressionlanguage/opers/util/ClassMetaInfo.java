package code.expressionlanguage.opers.util;
import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMetaInfo implements Struct {

    private static final String EMPTY_STRING = "";

    private final String name;

    private final String superClass;

    private final StringList superInterfaces = new StringList();

    private final StringMap<FieldMetaInfo> fieldsInfos;
    private final ObjectNotNullMap<MethodId, MethodMetaInfo> methodsInfos;

    private final ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> constructorsInfos;

    private final ClassCategory category;

    private final boolean abstractType;

    private final boolean finalType;

    public ClassMetaInfo(String _name,
            String _superClass,StringMap<FieldMetaInfo> _fields,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods,
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> _constructors,
            ClassCategory _category,
            boolean _abstractType,
            boolean _finalType) {
        name = _name;
        superClass = _superClass;
        fieldsInfos = _fields;
        methodsInfos = _methods;
        constructorsInfos = _constructors;
        category = _category;
        abstractType = _abstractType;
        finalType = _finalType;
    }

    public ClassMetaInfo(String _name,
            StringList _superInterfaces,StringMap<FieldMetaInfo> _fields,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods,
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> _constructors,
            ClassCategory _category) {
        name = _name;
        superInterfaces.addAllElts(_superInterfaces);
        superClass = EMPTY_STRING;
        fieldsInfos = _fields;
        methodsInfos = _methods;
        constructorsInfos = _constructors;
        category = _category;
        abstractType = true;
        finalType = false;
    }

    public String getName() {
        return name;
    }

    public String getSuperClass() {
        return superClass;
    }

    public ClassCategory getCategory() {
        return category;
    }

    public StringMap<FieldMetaInfo> getFieldsInfos() {
        return fieldsInfos;
    }
    public ObjectNotNullMap<MethodId, MethodMetaInfo> getMethodsInfos() {
        return methodsInfos;
    }

    public ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> getConstructorsInfos() {
        return constructorsInfos;
    }

    public boolean isAbstractType() {
        return abstractType;
    }

    public boolean isFinalType() {
        return finalType;
    }

    public StringList getSuperInterfaces() {
        return new StringList(superInterfaces);
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
        return _contextEl.getStandards().getAliasClass();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ClassMetaInfo)) {
            return false;
        }
        ClassMetaInfo info_ = (ClassMetaInfo) _other;
        return StringList.quickEq(name, info_.getName());
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
