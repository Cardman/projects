package aiki.beans.items;
import code.bean.Accessible;
import aiki.fight.items.HealingPp;

public class HealingPpBean extends HealingItemBean {

    @Accessible
    private long healedMovePp;

    @Accessible
    private long healingAllMovesFullpp;

    @Accessible
    private boolean healingAllMovesPp;

    @Accessible
    private boolean healingMoveFullpp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingPp item_ = (HealingPp) getItem();
        healedMovePp = item_.getHealedMovePp();
        healingAllMovesFullpp = item_.getHealingAllMovesFullpp();
        healingAllMovesPp = item_.isHealingAllMovesPp();
        healingMoveFullpp = item_.getHealingMoveFullpp();
    }

    @Accessible
    private boolean limitedPpMove() {
        return healedMovePp > 0;
    }

    @Accessible
    private boolean limitedPpMoves() {
        return healingAllMovesFullpp > 0;
    }
}
