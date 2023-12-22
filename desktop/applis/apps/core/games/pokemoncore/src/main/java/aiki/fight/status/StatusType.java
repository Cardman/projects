package aiki.fight.status;


import code.util.CustList;
import code.util.core.StringUtil;

public enum StatusType {
    INDIVIDUEL(""), RELATION_UNIQUE("0");
    private final String staType;

    StatusType(String _p) {
        this.staType = _p;
    }

    public String getStaType() {
        return staType;
    }

    public static StatusType getStatusTypeByName(String _env) {
        for (StatusType e: all()) {
            if (StringUtil.quickEq(e.getStaType(),_env)) {
                return e;
            }
        }
        return StatusType.INDIVIDUEL;
    }
    public static CustList<StatusType> all() {
        CustList<StatusType> ls_ = new CustList<StatusType>();
        ls_.add(INDIVIDUEL);
        ls_.add(RELATION_UNIQUE);
        return ls_;
    }
}
