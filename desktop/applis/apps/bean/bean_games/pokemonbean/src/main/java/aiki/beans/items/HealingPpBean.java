package aiki.beans.items;
import aiki.beans.StringMapObject;
import aiki.facade.FacadeGame;
import aiki.fight.items.HealingPp;
import code.scripts.pages.aiki.*;

public final class HealingPpBean extends HealingItemBean {
    private long healedMovePp;
    private long healingAllMovesFullpp;
    private boolean healingAllMovesPp;
    private boolean healingMoveFullpp;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        healItem();
        displayBoolTrue(toInt(healingAllMovesPp),MessagesPkBean.IT_HEALINGPP,MessagesDataItemsHealingpp.M_P_25_FULL_HEAL_MOVES);
        displayBoolTrue(toInt(healingMoveFullpp),MessagesPkBean.IT_HEALINGPP,MessagesDataItemsHealingpp.M_P_25_FULL_HEAL_MOVE);
        displayIntDef(healedMovePp,MessagesPkBean.IT_HEALINGPP,MessagesDataItemsHealingpp.M_P_25_HEAL_MOVE);
        displayIntDef(healingAllMovesFullpp,MessagesPkBean.IT_HEALINGPP,MessagesDataItemsHealingpp.M_P_25_HEAL_MOVES);
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingHealItem();
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