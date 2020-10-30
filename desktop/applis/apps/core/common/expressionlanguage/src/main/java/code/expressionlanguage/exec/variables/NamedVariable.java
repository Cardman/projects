package code.expressionlanguage.exec.variables;

import code.util.core.StringUtil;

public abstract class NamedVariable {
    private final String name;

    protected NamedVariable(String _name) {
        this.name = StringUtil.nullToEmpty(_name);
    }

    public String getName() {
        return name;
    }

}