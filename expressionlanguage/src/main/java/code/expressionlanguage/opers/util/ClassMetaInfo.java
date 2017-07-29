package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootedBlock;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMetaInfo {

    private final String superClass;

    private final StringMap<FieldMetaInfo> fields;
    
    private final ObjectNotNullMap<FctConstraints, MethodMetaInfo> methods;

    private final ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> constructors;

    private final ClassCategory category;

    private final boolean abstractType;

    private final boolean finalType;

    public ClassMetaInfo(String _superClass,StringMap<FieldMetaInfo> _fields,
            ObjectNotNullMap<FctConstraints, MethodMetaInfo> _methods,
            ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> _constructors,
            ClassCategory _category,
            boolean _abstractType,
            boolean _finalType) {
        superClass = _superClass;
        fields = _fields;
        methods = _methods;
        constructors = _constructors;
        category = _category;
        abstractType = _abstractType;
        finalType = _finalType;
    }

    public StringList getSuperClasses(ContextEl _cont) {
        StringList list_ = new StringList();
        String className_ = superClass;
        Classes classes_ = _cont.getClasses();
        list_.add(className_);
        while (!StringList.quickEq(className_, Object.class.getName())) {
            RootedBlock clBl_ = classes_.getClassBody(className_);
            className_ = clBl_.getSuperClass();
            list_.add(className_);
        }
        return list_;
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
    
    public ObjectNotNullMap<FctConstraints, MethodMetaInfo> getMethods() {
        return new ObjectNotNullMap<FctConstraints, MethodMetaInfo>(methods);
    }

    public ObjectNotNullMap<FctConstraints, ConstructorMetaInfo> getConstructors() {
        return new ObjectNotNullMap<FctConstraints, ConstructorMetaInfo>(constructors);
    }

    public boolean isAbstractType() {
        return abstractType;
    }

    public boolean isFinalType() {
        return finalType;
    }
}
