package code.bean.nat;

import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class SpecialNatClass {

    private final StringList allSuperTypes = new StringList();

    private final String superClass;

    private final StringList directInterfaces = new StringList();

    private final String name;
    private final String packageName;

    private final CustList<SpecNatMethod> methods;

    private final CustList<StandardField> fields;
    public SpecialNatClass(String _name, CustList<StandardField> _fields, CustList<SpecNatMethod> _methods, String _superClass) {
        name = StandardType.getNamePart(_name);
        packageName = StandardType.getPackagePart(_name);
        methods = _methods;
        superClass = _superClass;
        fields = _fields;
    }
    public String getSuperClass() {
        return superClass;
    }

    public StringList getDirectInterfaces() {
        return directInterfaces;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFullName() {
        String pkg_ = getPackageName();
        String name_ = getName();
        return StringUtil.spliceIfFirst('.', StringUtil.concat(pkg_,".",name_));
    }

    public CustList<SpecNatMethod> getMethods() {
        return methods;
    }

    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    public CustList<StandardField> getFields() {
        return fields;
    }
}
