package aiki.fight.moves.effects.enums;


import aiki.db.*;
import code.util.*;
import code.util.core.*;

public enum MoveChoiceRestrictionType {
    NOTHING(""),FORCE(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE),FORBIDDEN(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN),LANCEUR_ATTAQUES(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES),DER(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER),CATEGORIE_AUTRE(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE);
    private final String resType;

    MoveChoiceRestrictionType(String _p) {
        this.resType = _p;
    }

    public String getResType() {
        return resType;
    }

    public static MoveChoiceRestrictionType getMoveChoiceRestrictionTypeByName(String _env) {
        for (MoveChoiceRestrictionType e: all()) {
            if (StringUtil.quickEq(e.getResType(),_env)) {
                return e;
            }
        }
        return MoveChoiceRestrictionType.NOTHING;
    }
    public static CustList<MoveChoiceRestrictionType> all() {
        CustList<MoveChoiceRestrictionType> ls_ = new CustList<MoveChoiceRestrictionType>();
        ls_.add(NOTHING);
        ls_.add(FORCE);
        ls_.add(FORBIDDEN);
        ls_.add(LANCEUR_ATTAQUES);
        ls_.add(DER);
        ls_.add(CATEGORIE_AUTRE);
        return ls_;
    }
}
