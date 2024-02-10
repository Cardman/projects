package cards.president.comparators;

import cards.president.enumerations.CardPresident;
import code.util.ints.Comparing;

public abstract class AbsGameStrengthCardPresidentComparator implements Comparing<CardPresident> {

    private final boolean reverseGame;
    private final boolean decreasing;

    protected AbsGameStrengthCardPresidentComparator(
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
        return subSuit(_o1,_o2);
    }

    protected abstract int subSuit(CardPresident _o1, CardPresident _o2);

}
