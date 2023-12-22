package aiki.game.fight.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum FightState {
    SWITCH_PROPOSE("0"), SWITCH_WHILE_KO_USER("1"), ATTAQUES("2"), APPRENDRE_EVOLUER("3"), SWITCH_APRES_ATTAQUE("4"), CAPTURE_KO("5"), FIN_CBT_SAUVAGE("6"),
    FIN_CBT_DRESSEUR("7"), REDESSIN_SCENE("8"), SURNOM("9"), RIEN("");
    private final String fsType;

    FightState(String _p) {
        this.fsType = _p;
    }

    public String getFsType() {
        return fsType;
    }

    public static FightState getFightStateByName(String _env) {
        for (FightState e: all()) {
            if (StringUtil.quickEq(e.getFsType(),_env)) {
                return e;
            }
        }
        return FightState.RIEN;
    }
    public static CustList<FightState> all() {
        CustList<FightState> ls_ = new CustList<FightState>();
        ls_.add(SWITCH_PROPOSE);
        ls_.add(SWITCH_WHILE_KO_USER);
        ls_.add(ATTAQUES);
        ls_.add(APPRENDRE_EVOLUER);
        ls_.add(SWITCH_APRES_ATTAQUE);
        ls_.add(CAPTURE_KO);
        ls_.add(FIN_CBT_SAUVAGE);
        ls_.add(FIN_CBT_DRESSEUR);
        ls_.add(REDESSIN_SCENE);
        ls_.add(SURNOM);
        ls_.add(RIEN);
        return ls_;
    }
}
