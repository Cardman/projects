package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.*;

public abstract class StandardType implements GeneType {

    private static final String PACKAGE_NAME = "";

    private final String name;
    private final String packageName;

    private final CustList<StandardConstructor> constructors;
    private final StringMap<StandardField> fields;
    private final ObjectMap<MethodId, StandardMethod> methods;

    private final ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    protected StandardType(String _name,
            StringMap<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
            ObjectMap<MethodId, StandardMethod> _methods) {
        name = getNamePart(_name);
        packageName = getPackagePart(_name);
        fields = _fields;
        constructors = _constructors;
        methods = _methods;
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
    }
    public static String getNamePart(String _fullName) {
        int indexDot_ = _fullName.lastIndexOf('.');
        if (indexDot_ < 0) {
            return _fullName;
        }
        return _fullName.substring(indexDot_+1);
    }
    public static String getPackagePart(String _fullName) {
        int indexDot_ = _fullName.lastIndexOf('.');
        if (indexDot_ < 0) {
            return PACKAGE_NAME;
        }
        return _fullName.substring(0, indexDot_);
    }

    public abstract StringList getDirectSuperTypes();

    public abstract StringList getDirectInterfaces();
    @Override
    public boolean withoutInstance() {
        return isStaticType();
    }
    @Override
    public boolean isStaticType() {
        return true;
    }

    @Override
    public Ints getTypeVarCounts() {
        return new Ints(0);
    }

    @Override
    public CustList<StringList> getBoundAll() {
        return new CustList<StringList>();
    }

    @Override
    public CustList<TypeVar> getParamTypesMapValues() {
        return new CustList<TypeVar>();
    }

    public StringMap<StandardField> getFields() {
        return fields;
    }
    @Override
    public final ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final String getPackageName() {
        return packageName;
    }

    @Override
    public final String getFullName() {
        String pkg_ = getPackageName();
        String name_ = getName();
        if (pkg_.isEmpty()) {
            return name_;
        }
        return StringList.concat(pkg_,".",name_);
    }

    @Override
    public final String getGenericString() {
        return getFullName();
    }

    public CustList<StandardConstructor> getConstructors() {
        return constructors;
    }

    public ObjectMap<MethodId, StandardMethod> getMethods() {
        return methods;
    }
}
