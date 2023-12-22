package aiki.fight.moves.effects.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum MoveChoiceRestrictionType {
    NOTHING(""),FORCE("0"),FORBIDDEN("1"),LANCEUR_ATTAQUES("2"),DER("3"),CATEGORIE_AUTRE("4");
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
