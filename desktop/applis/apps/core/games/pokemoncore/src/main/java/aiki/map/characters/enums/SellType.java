package aiki.map.characters.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum SellType {
    ITEM(""),TM("0"),MOVE("1");
    private final String sel;

    SellType(String _p) {
        this.sel = _p;
    }

    public String getSel() {
        return sel;
    }

    public static SellType getSellTypeByName(String _env) {
        for (SellType e: all()) {
            if (StringUtil.quickEq(e.getSel(),_env)) {
                return e;
            }
        }
        return SellType.ITEM;
    }
    public static CustList<SellType> all() {
        CustList<SellType> ls_ = new CustList<SellType>();
        ls_.add(ITEM);
        ls_.add(TM);
        ls_.add(MOVE);
        return ls_;
    }
}
