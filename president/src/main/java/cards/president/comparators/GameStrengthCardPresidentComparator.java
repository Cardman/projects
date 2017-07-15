package cards.president.comparators;
import java.util.Comparator;

import code.util.CustList;
import cards.president.enumerations.CardPresident;

public final class GameStrengthCardPresidentComparator implements Comparator<CardPresident> {

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
        return CustList.EQ_CMP;
    }

}
