package code.expressionlanguage.exec.util;

import code.util.core.StringUtil;

public final class NameAndType {
    private final String name;
    private final String type;

    public NameAndType(String _name, String _type) {
        this.name = StringUtil.nullToEmpty(_name);
        this.type = StringUtil.nullToEmpty(_type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
