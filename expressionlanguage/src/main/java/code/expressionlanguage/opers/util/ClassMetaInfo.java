package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMetaInfo {

    private static final String EMPTY_STRING = "";

    private final String name;

    private final String superClass;

    private final StringList superInterfaces = new StringList();

    private final StringMap<FieldMetaInfo> fields;
    private final ObjectNotNullMap<MethodId, MethodMetaInfo> methods;

    private final ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> constructors;

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
        fields = _fields;
        methods = _methods;
        constructors = _constructors;
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
        fields = _fields;
        methods = _methods;
        constructors = _constructors;
        category = _category;
        abstractType = true;
        finalType = false;
    }

    public StringList getSuperClasses(ContextEl _cont) {
        StringList list_ = new StringList();
        String className_ = superClass;
        Classes classes_ = _cont.getClasses();
        list_.add(className_);
        while (!StringList.quickEq(className_, Object.class.getName())) {
            UniqueRootedBlock clBl_ = (UniqueRootedBlock) classes_.getClassBody(className_);
            className_ = clBl_.getSuperClass();
            list_.add(className_);
        }
        return list_;
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

    public StringMap<FieldMetaInfo> getFields() {
        return new StringMap<FieldMetaInfo>(fields);
    }
    public ObjectNotNullMap<MethodId, MethodMetaInfo> getMethods() {
        return new ObjectNotNullMap<MethodId, MethodMetaInfo>(methods);
    }

    public ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> getConstructors() {
        return new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>(constructors);
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
}
