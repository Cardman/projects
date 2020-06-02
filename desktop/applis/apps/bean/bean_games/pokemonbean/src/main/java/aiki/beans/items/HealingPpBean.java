package aiki.beans.items;
import aiki.fight.items.HealingPp;

public class HealingPpBean extends HealingItemBean {
    private long healedMovePp;
    private long healingAllMovesFullpp;
    private boolean healingAllMovesPp;
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
    public boolean limitedPpMove() {
        return healedMovePp > 0;
    }
    public boolean limitedPpMoves() {
        return healingAllMovesFullpp > 0;
    }

    public boolean getHealingAllMovesPp() {
        return healingAllMovesPp;
    }

    public boolean getHealingMoveFullpp() {
        return healingMoveFullpp;
    }

    public long getHealedMovePp() {
        return healedMovePp;
    }

    public long getHealingAllMovesFullpp() {
        return healingAllMovesFullpp;
    }
}