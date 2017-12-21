package code.util.pagination;

import code.util.StringList;

public enum SearchingMode {
    WHOLE_STRING, SUBSTRING, META_CHARACTER, REG_EXP;
    public static SearchingMode getSearchingModeByName(String _env) {
        for (SearchingMode e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
}
