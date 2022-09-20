package aiki.fight.moves.enums;


import code.util.CustList;

public enum TargetChoice {
    ADJ_ADV(false, "ADJ_ADV"),
    ADJ_MULT(false, "ADJ_MULT"),
    ADJ_UNIQ(true, "ADJ_UNIQ"),
    ALLIE(true, "ALLIE"),
    ALLIES(false, "ALLIES"),
    ANY_FOE(true, "ANY_FOE"),
    AUTRE_UNIQ(true, "AUTRE_UNIQ"),
    GLOBALE(false, "GLOBALE"),
    LANCEUR(false, "LANCEUR"),
    PSEUDO_GLOBALE(false, "PSEUDO_GLOBALE"),
    TOUS_ADV(false, "TOUS_ADV"),
    UNIQUE_IMPORTE(true, "UNIQUE_IMPORTE"),
    NOTHING(false, "NOTHING");

    private final boolean withChoice;
    private final String targetName;

    TargetChoice(boolean _withChoice, String _t) {
        withChoice = _withChoice;
        targetName = _t;
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
