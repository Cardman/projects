package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneInterface;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInterface extends StandardType implements GeneInterface {

    private final StringList allSuperTypes = new StringList();

    private final StringList superInterfaces;

    public StandardInterface(String _name,
            ObjectMap<MethodId, StandardMethod> _methods,
            StringList _superInterfaces) {
        super(_name, new StringMap<StandardField>(), new CustList<StandardConstructor>(), _methods);
        superInterfaces = _superInterfaces;
    }

    @Override
    public StringList getDirectInterfaces() {
        return new StringList(superInterfaces);
    }

    @Override
    public StringList getDirectSuperTypes() {
        return getDirectInterfaces();
    }

    @Override
    public StringList getAllGenericSuperTypes() {
        return allSuperTypes;
    }
    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

}
