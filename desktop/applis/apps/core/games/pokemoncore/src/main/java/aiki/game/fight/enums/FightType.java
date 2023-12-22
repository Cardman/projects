package aiki.game.fight.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum FightType {
    NOTHING(true,false, ""),SAUVAGE(true,true, "0"),DRESSEUR("1"),DRESSEUR_GYM("2"),GYM_LEADER("3"),DRESSEUR_LIGUE("4"),TMP_TRAINER("5");
    private final boolean wild;
    private final boolean existing;
    private final String ft;
    FightType(String _p){
        this.ft = _p;
        wild=false;
        existing=true;
    }
    FightType(boolean _wild, boolean _existing, String _p){
        wild=_wild;
        existing=_existing;
        this.ft = _p;
    }

    public String getFt() {
        return ft;
    }

    public static FightType getFightTypeByName(String _env) {
        for (FightType e: all()) {
            if (StringUtil.quickEq(e.getFt(),_env)) {
                return e;
            }
        }
        return FightType.NOTHING;
    }
    public static CustList<FightType> all() {
        CustList<FightType> ls_ = new CustList<FightType>();
        ls_.add(NOTHING);
        ls_.add(SAUVAGE);
        ls_.add(DRESSEUR);
        ls_.add(DRESSEUR_GYM);
        ls_.add(GYM_LEADER);
        ls_.add(DRESSEUR_LIGUE);
        ls_.add(TMP_TRAINER);
        return ls_;
    }
    public boolean isWild() {
        return wild;
    }
    public boolean isExisting() {
        return existing;
    }
}
