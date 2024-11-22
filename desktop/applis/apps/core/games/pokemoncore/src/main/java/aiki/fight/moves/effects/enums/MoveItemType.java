package aiki.fight.moves.effects.enums;


import aiki.db.*;
import code.util.*;
import code.util.core.*;

public enum MoveItemType {
    GIVE_OBJECT_TARGET(DataBase.DEF_MOVE_ITEM_TYPE_GIVE_OBJECT_TARGET),EXCHANGE_OBJECTS(DataBase.DEF_MOVE_ITEM_TYPE_EXCHANGE_OBJECTS),TAKE_OBJET(DataBase.DEF_MOVE_ITEM_TYPE_TAKE_OBJET),REMOVE_TARGET_OBJECT(DataBase.DEF_MOVE_ITEM_TYPE_REMOVE_TARGET_OBJECT),
    REUSE_LAST_OBJECT(""),DELETE_DEF_TARGET_BERRY(DataBase.DEF_MOVE_ITEM_TYPE_DELETE_DEF_TARGET_BERRY),USE_OBJECT_AS_POSSIBLE(DataBase.DEF_MOVE_ITEM_TYPE_USE_OBJECT_AS_POSSIBLE);
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
