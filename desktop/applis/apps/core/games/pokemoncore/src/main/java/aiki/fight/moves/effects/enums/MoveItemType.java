package aiki.fight.moves.effects.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum MoveItemType {
    GIVE_OBJECT_TARGET("0"),EXCHANGE_OBJECTS("1"),TAKE_OBJET("2"),REMOVE_TARGET_OBJECT("3"),
    REUSE_LAST_OBJECT(""),DELETE_DEF_TARGET_BERRY("4"),USE_OBJECT_AS_POSSIBLE("5");
    private final String itType;

    MoveItemType(String _p) {
        this.itType = _p;
    }

    public String getItType() {
        return itType;
    }

    public static MoveItemType getMoveItemTypeTypeByName(String _env) {
        for (MoveItemType e: all()) {
            if (StringUtil.quickEq(e.getItType(),_env)) {
                return e;
            }
        }
        return MoveItemType.REUSE_LAST_OBJECT;
    }
    public static CustList<MoveItemType> all() {
        CustList<MoveItemType> ls_ = new CustList<MoveItemType>();
        ls_.add(GIVE_OBJECT_TARGET);
        ls_.add(EXCHANGE_OBJECTS);
        ls_.add(TAKE_OBJET);
        ls_.add(REMOVE_TARGET_OBJECT);
        ls_.add(REUSE_LAST_OBJECT);
        ls_.add(DELETE_DEF_TARGET_BERRY);
        ls_.add(USE_OBJECT_AS_POSSIBLE);
        return ls_;
    }
}
