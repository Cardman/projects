package code.bean.nat;

import code.util.CustList;
import code.util.StringList;

public final class SpecialNatClass {

    private final StringList allSuperTypes = new StringList();

    private final String superClass;

    private final StringList directInterfaces = new StringList();


    private final CustList<SpecNatMethod> methods;

    private final CustList<StandardField> fields;
    public SpecialNatClass(CustList<StandardField> _fields, CustList<SpecNatMethod> _methods, String _superClass) {
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
