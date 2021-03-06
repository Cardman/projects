package aiki.fight.moves.enums;
import code.util.StringList;


public enum TargetChoice {
    ADJ_ADV(false),
    ADJ_MULT(false),
    ADJ_UNIQ(true),
    ALLIE(true),
    ALLIES(false),
    ANY_FOE(true),
    AUTRE_UNIQ(true),
    GLOBALE(false),
    LANCEUR(false),
    PSEUDO_GLOBALE(false),
    TOUS_ADV(false),
    UNIQUE_IMPORTE(true),
    NOTHING(false);

    private final boolean withChoice;

    TargetChoice(boolean _withChoice) {
        withChoice = _withChoice;
    }

    public boolean isWithChoice() {
        return withChoice;
    }
}
