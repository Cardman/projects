package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class TypeVar {

    private String name;

    private StringList constraints;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public StringList getConstraints() {
        return constraints;
    }

    public void setConstraints(StringList _constraints) {
        constraints = _constraints;
    }
}
