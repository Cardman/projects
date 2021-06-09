package code.expressionlanguage.stds;

import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.WithCstFieldInfos;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;

public final class StandardClass extends AbstractStandardClass implements WithCstFieldInfos {

    private final CustList<CstFieldInfo> fields;
    public StandardClass(String _name,CustList<CstFieldInfo> _fields,
            CustList<StandardConstructor> _constructors,
                         CustList<StandardMethod> _methods,
            String _superClass, StdClassModifier _modifier) {
        super(_name, _constructors, _methods, _superClass, _modifier);
        fields = _fields;
    }
    public StandardClass(String _name,CustList<CstFieldInfo> _fields,
            CustList<StandardConstructor> _constructors,
                         CustList<StandardMethod> _methods,
            String _superClass, MethodModifier _modifier) {
        super(_name, _constructors, _methods, _superClass, _modifier);
        fields = _fields;
    }

    public static CustList<CstFieldInfo> getCstFields(StandardType _std) {
        if (_std instanceof WithCstFieldInfos) {
            return ((WithCstFieldInfos)_std).getCstFields();
        }
        return new CustList<CstFieldInfo>();
    }
    @Override
    public CustList<CstFieldInfo> getCstFields() {
        return fields;
    }
}
