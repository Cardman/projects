package code.expressionlanguage.inherits;

import code.util.EqList;
import code.util.StringList;

public final class TypeOwnersDepends {

    private final StringList typeOwners = new StringList();
    private final EqList<ClassInheritsDeps> depends = new EqList<ClassInheritsDeps>();
    public StringList getTypeOwners() {
        return typeOwners;
    }
    public EqList<ClassInheritsDeps> getDepends() {
        return depends;
    }
}
