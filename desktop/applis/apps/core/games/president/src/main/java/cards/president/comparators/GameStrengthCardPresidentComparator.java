package cards.president.comparators;
import cards.president.enumerations.CardPresident;
import code.util.core.SortConstants;

public final class GameStrengthCardPresidentComparator extends AbsGameStrengthCardPresidentComparator {

    public static final byte CARD_AVG_STRENGTH = 7;
    public static final byte CARD_MAX_STRENGTH = 13;

    public GameStrengthCardPresidentComparator(
            boolean _reverseGame,
            boolean _decroissant){
        super(_reverseGame, _decroissant);
    }

    @Override
    protected int subSuit(CardPresident _o1, CardPresident _o2) {
        return SortConstants.EQ_CMP;
    }
}
