package aiki.map.characters.enums;

import code.util.CustList;
import code.util.core.StringUtil;

public enum GeranceType {
    HEAL(""),FOSSILE("0"),HOST("1");
    private final String ger;

    GeranceType(String _p) {
        this.ger = _p;
    }

    public String getGer() {
        return ger;
    }

    public static GeranceType getGeranceTypeByName(String _env) {
        for (GeranceType e: all()) {
            if (StringUtil.quickEq(e.getGer(),_env)) {
                return e;
            }
        }
        return GeranceType.HEAL;
    }
    public static CustList<GeranceType> all() {
        CustList<GeranceType> ls_ = new CustList<GeranceType>();
        ls_.add(HEAL);
        ls_.add(FOSSILE);
        ls_.add(HOST);
        return ls_;
    }
}
