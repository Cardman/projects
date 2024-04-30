package aiki.facade.enums;


import code.util.IdList;
import code.util.core.StringUtil;

public enum SearchingMode {
    WHOLE_STRING("0"), BEGIN("1"), END("2"), SUBSTRING("3"), META_CHARACTER("4"), MATCH_SPACE("5");

    private final String keySearch;
    SearchingMode(String _n) {
        keySearch = _n;
    }

    public static SearchingMode getSearchingModeByName(String _env) {
        for (SearchingMode e: all()) {
            if (StringUtil.quickEq(e.keySearch, _env)) {
                return e;
            }
        }
        return WHOLE_STRING;
    }

    public static IdList<SearchingMode> all() {
        IdList<SearchingMode> ls_ = new IdList<SearchingMode>();
        ls_.add(WHOLE_STRING);
        ls_.add(BEGIN);
        ls_.add(END);
        ls_.add(SUBSTRING);
        ls_.add(META_CHARACTER);
        ls_.add(MATCH_SPACE);
        return ls_;
    }
}
