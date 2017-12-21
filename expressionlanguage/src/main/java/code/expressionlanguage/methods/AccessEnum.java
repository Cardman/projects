package code.expressionlanguage.methods;

import code.util.StringList;

public enum AccessEnum {
    PUBLIC,PROTECTED,PACKAGE,PRIVATE;
    public static AccessEnum getAccessByName(String _name) {
        for (AccessEnum a: values()) {
            if (StringList.quickEq(a.name(), _name)) {
                return a;
            }
        }
        return null;
    }
}
