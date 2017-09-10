package aiki.fight.moves.enums;
import code.serialize.CheckedData;
import code.util.ints.Listable;


@CheckedData
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
    public static boolean equalsSet(Listable<TargetChoice> _list1,Listable<TargetChoice> _list2) {
        for (TargetChoice a: _list2) {
            boolean contains_ = false;
            for (TargetChoice b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (TargetChoice a: _list1) {
            boolean contains_ = false;
            for (TargetChoice b: _list2) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public boolean isWithChoice() {
        return withChoice;
    }
}
