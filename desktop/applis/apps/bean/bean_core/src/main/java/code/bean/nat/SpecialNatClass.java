package code.bean.nat;

import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.AbstractStandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;

public final class SpecialNatClass extends AbstractStandardClass {

    private final CustList<StandardField> fields;
    public SpecialNatClass(String _name, CustList<StandardField> _fields, CustList<StandardConstructor> _constructors, CustList<StandardMethod> _methods, String _superClass, MethodModifier _modifier) {
        super(_name, _constructors, _methods, _superClass, _modifier);
        fields = _fields;
    }
    public CustList<StandardField> getFields() {
        return fields;
    }
}
