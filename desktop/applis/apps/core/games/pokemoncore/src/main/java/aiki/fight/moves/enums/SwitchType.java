package aiki.fight.moves.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum SwitchType {
    NOTHING(""),LANCEUR("0"),CIBLE("1");
    private final String swType;

    SwitchType(String _p) {
        this.swType = _p;
    }

    public String getSwType() {
        return swType;
    }

    public static SwitchType getSwitchTypeByName(String _env) {
        for (SwitchType e: all()) {
            if (StringUtil.quickEq(e.getSwType(),_env)) {
                return e;
            }
        }
        return SwitchType.NOTHING;
    }
    public static CustList<SwitchType> all() {
        CustList<SwitchType> ls_ = new CustList<SwitchType>();
        ls_.add(NOTHING);
        ls_.add(LANCEUR);
        ls_.add(CIBLE);
        return ls_;
    }
}
