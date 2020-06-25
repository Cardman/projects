package code.expressionlanguage.exec.util;

import code.util.StringList;

public final class ExecTypeVar {

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
