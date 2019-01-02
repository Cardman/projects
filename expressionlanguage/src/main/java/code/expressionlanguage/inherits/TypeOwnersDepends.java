package code.expressionlanguage.inherits;

import code.util.StringList;

public final class TypeOwnersDepends {

    private final StringList typeOwners = new StringList();
    private final StringList depends = new StringList();
    public StringList getTypeOwners() {
        return typeOwners;
    }
    public StringList getDepends() {
        return depends;
    }
}
