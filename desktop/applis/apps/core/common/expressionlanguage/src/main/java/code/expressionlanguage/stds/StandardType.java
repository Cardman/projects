package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.functionid.MethodId;
import code.util.*;

public abstract class StandardType implements GeneType {

    private static final String PACKAGE_NAME = "";

    private final String name;
    private final String packageName;

    private final CustList<StandardConstructor> constructors;
    private final StringMap<StandardField> fields;
    private final ObjectMap<MethodId, StandardMethod> methods;

    protected StandardType(String _name,
            StringMap<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
            ObjectMap<MethodId, StandardMethod> _methods) {
        name = getNamePart(_name);
        packageName = getPackagePart(_name);
        fields = _fields;
        constructors = _constructors;
        methods = _methods;
    }
    public static String getNamePart(String _fullName) {
        int indexDot_ = _fullName.lastIndexOf('.');
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

    public StringList getParamTypesValues() {
        return new StringList();
    }
    public StringMap<StandardField> getFields() {
        return fields;
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
        return StringList.spliceIfFirst('.',StringList.concat(pkg_,".",name_));
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

    public boolean isSubTypeOf(String _fullName, ContextEl _an) {
        if (StringList.quickEq(getFullName(),_fullName)) {
            return true;
        }
        return StringList.contains(getAllSuperTypes(),_fullName);
    }
}
