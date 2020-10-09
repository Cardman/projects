package cards.president.comparators;
import cards.president.enumerations.CardPresident;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthCardPresidentComparator implements Comparing<CardPresident> {

    public static final byte CARD_AVG_STRENGTH = 7;

    private boolean reverseGame;
    private boolean decreasing;

    public GameStrengthCardPresidentComparator(
            boolean _reverseGame,
            boolean _decroissant){
        reverseGame = _reverseGame;
        decreasing = _decroissant;
    }

    @Override
    public int compare(CardPresident _o1, CardPresident _o2) {
        int mult_ = 1;
        if(decreasing){
            mult_ = -1;
        }
        if(_o1.strength(reverseGame) > _o2.strength(reverseGame)){
            return mult_;
        }
        if(_o1.strength(reverseGame) < _o2.strength(reverseGame)){
            return -mult_;
        }
        return SortConstants.EQ_CMP;
    }

}
