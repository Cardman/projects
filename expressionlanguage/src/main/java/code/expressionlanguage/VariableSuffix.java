package code.expressionlanguage;

import code.util.StringList;

public enum VariableSuffix {
DISTINCT,FIELDS,MERGED,NONE;
    public static VariableSuffix getVariableSuffixByName(String _env) {
        for (VariableSuffix e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return NONE;
    }
}
