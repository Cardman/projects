package aiki.fight.moves.enums;


import aiki.db.DataBase;
import code.util.CustList;
import code.util.core.StringUtil;

public enum TargetChoice {
    ADJ_ADV(false, DataBase.DEF_TARGET_ADJ_ADV),
    ADJ_MULT(false, DataBase.DEF_TARGET_ADJ_MULT),
    ADJ_UNIQ(true, DataBase.DEF_TARGET_ADJ_UNIQ),
    ALLIE(true, DataBase.DEF_TARGET_ALLIE),
    ALLIES(false, DataBase.DEF_TARGET_ALLIES),
    ANY_FOE(true, DataBase.DEF_TARGET_ANY_FOE),
    AUTRE_UNIQ(true, DataBase.DEF_TARGET_AUTRE_UNIQ),
    GLOBALE(false, DataBase.DEF_TARGET_GLOBALE),
    LANCEUR(false, DataBase.DEF_TARGET_LANCEUR),
    PSEUDO_GLOBALE(false, DataBase.DEF_TARGET_PSEUDO_GLOBALE),
    TOUS_ADV(false, DataBase.DEF_TARGET_TOUS_ADV),
    UNIQUE_IMPORTE(true, DataBase.DEF_TARGET_UNIQUE_IMPORTE),
    NOTHING(false, DataBase.DEF_TARGET_NOTHING),
    NONE(false, "");

    private final boolean withChoice;
    private final String targetName;

    TargetChoice(boolean _withChoice, String _t) {
        withChoice = _withChoice;
        targetName = _t;
    }
    public static TargetChoice getTargetChoiceByName(String _env) {
        for (TargetChoice e: TargetChoice.all()) {
            if (StringUtil.quickEq(e.targetName, _env)) {
                return e;
            }
        }
        return TargetChoice.NOTHING;
    }
    public static CustList<TargetChoice> all() {
        CustList<TargetChoice> targets_ = new CustList<TargetChoice>();
        targets_.add(ADJ_ADV);
        targets_.add(ADJ_MULT);
        targets_.add(ADJ_UNIQ);
        targets_.add(ALLIE);
        targets_.add(ALLIES);
        targets_.add(ANY_FOE);
        targets_.add(AUTRE_UNIQ);
        targets_.add(GLOBALE);
        targets_.add(LANCEUR);
        targets_.add(PSEUDO_GLOBALE);
        targets_.add(TOUS_ADV);
        targets_.add(UNIQUE_IMPORTE);
        targets_.add(NOTHING);
        return targets_;
    }
    public boolean isWithChoice() {
        return withChoice;
    }

    public String getTargetName() {
        return targetName;
    }
}
