package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class StandardType implements GeneType {

    private static final String PACKAGE_NAME = "";

    private final String name;

    private final CustList<StandardConstructor> constructors;
    private final StringMap<StandardField> fields;
    private final ObjectMap<MethodId, StandardMethod> methods;

    private final ObjectMap<MethodId, EqList<ClassMethodId>> allOverridingMethods;

    private String iterative = "";

    protected StandardType(String _name,
            StringMap<StandardField> _fields,
            CustList<StandardConstructor> _constructors,
            ObjectMap<MethodId, StandardMethod> _methods) {
        name = _name;
        fields = _fields;
        constructors = _constructors;
        methods = _methods;
        allOverridingMethods = new ObjectMap<MethodId, EqList<ClassMethodId>>();
    }
    /** Copy the list*/
    public abstract StringList getDirectSuperClasses();
    public abstract StringList getDirectSuperTypes();

    public abstract StringList getDirectInterfaces();
    @Override
    public boolean isStaticType() {
        return true;
    }
    public final StringList getAllSuperTypes(LgNames _classes) {
        StringList list_ = new StringList();
        StringList current_ = new StringList(getName());
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                String baseType_ = c;
                StandardType stdType_ = _classes.getStandards().getVal(baseType_);
                StringList superTypes_ = stdType_.getDirectSuperTypes();
                for (String t: superTypes_) {
                    String format_ = t;
                    list_.add(format_);
                    next_.add(format_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return list_;
    }

    @Override
    public CustList<TypeVar> getParamTypes() {
        return new CustList<TypeVar>();
    }
    @Override
    public CustList<TypeVar> getParamTypesMapValues() {
        return new CustList<TypeVar>();
    }
    @Override
    public StringMap<TypeVar> getParamTypesMap() {
        return new StringMap<TypeVar>();
    }
    public String getPrettyString() {
        StringBuilder str_ = new StringBuilder();
        str_.append(getName());
        str_.append("\n");
        for (StandardField f: fields.values()) {
            str_.append(f.getPrettyString(getName()));
            str_.append("\n");
        }
        str_.append("\n");
        for (StandardConstructor c: constructors) {
            str_.append(c.getPrettyString(getName()));
            str_.append("\n");
        }
        for (StandardMethod m: methods.values()) {
            str_.append(m.getPrettyString());
            str_.append("\n");
        }
        return str_.toString();
    }
    public StringMap<StandardField> getFields() {
        return fields;
    }
    @Override
    public final ObjectMap<MethodId, EqList<ClassMethodId>> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getPackageName() {
        return PACKAGE_NAME;
    }

    @Override
    public final AccessEnum getAccess() {
        return AccessEnum.PUBLIC;
    }

    @Override
    public final String getFullDefinition() {
        return getFullName();
    }

    @Override
    public final String getFullName() {
        return getName();
    }

    @Override
    public final String getGenericString() {
        return getFullName();
    }

    @Override
    public final StandardType belong() {
        return this;
    }

    public CustList<StandardConstructor> getConstructors() {
        return constructors;
    }

    public ObjectMap<MethodId, StandardMethod> getMethods() {
        return methods;
    }
    public String getIterative() {
        return iterative;
    }
    public void setIterative(String _iterative) {
        iterative = _iterative;
    }
}
