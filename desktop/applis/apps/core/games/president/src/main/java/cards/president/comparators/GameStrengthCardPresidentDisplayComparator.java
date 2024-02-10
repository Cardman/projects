package cards.president.comparators;

import cards.consts.DisplayingCommon;
import cards.president.enumerations.CardPresident;
import code.util.core.NumberUtil;

public final class GameStrengthCardPresidentDisplayComparator extends AbsGameStrengthCardPresidentComparator {

    private final DisplayingCommon displayingCommon;
    public GameStrengthCardPresidentDisplayComparator(
            boolean _reverseGame,
            DisplayingCommon _dis){
        super(_reverseGame, _dis.isDecreasing());
        displayingCommon = _dis;
    }

    @Override
    protected int subSuit(CardPresident _o1, CardPresident _o2) {
        return NumberUtil.compareLg(_o1.getId().forceCouleurDansUnTri(displayingCommon.getSuits()),_o2.getId().forceCouleurDansUnTri(displayingCommon.getSuits()));
    }
}
