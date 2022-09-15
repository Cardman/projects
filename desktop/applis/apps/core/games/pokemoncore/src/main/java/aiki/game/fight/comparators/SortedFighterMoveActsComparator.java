package aiki.game.fight.comparators;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;
import code.maths.Rate;
import code.util.comparators.ComparatorBoolean;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class SortedFighterMoveActsComparator implements
        Comparing<TeamPosition> {

    private final Fight fight;

    private final DataBase data;

    private final boolean revertedSpeed;

    public SortedFighterMoveActsComparator(Fight _fight, DataBase _data,
            boolean _revertedSpeed) {
        fight = _fight;
        data = _data;
        revertedSpeed = _revertedSpeed;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter fighterOne_=fight.getFighter(_fighterOne);
        String moveOne_=fighterOne_.getFinalChosenMove();
        boolean slowOne_=fighterOne_.isSlowing(data);
        byte varPrioOne_= fighterOne_.varPrio(_fighterOne,moveOne_,fight,data);
        MoveData fAtt_=data.getMove(moveOne_);
        byte prioOne_ = fAtt_.getPriority();
        Item it1_ = FightFacade.useItsObject(fight, _fighterOne, data);
        boolean canAttackLastOne_ = it1_ instanceof ItemForBattle && ((ItemForBattle) it1_).getAttackLast();
        Fighter fighterTwo_=fight.getFighter(_fighterTwo);
        String moveTwo_=fighterTwo_.getFinalChosenMove();
        boolean slowTwo_=fighterTwo_.isSlowing(data);
        byte varPrioTwo_=fighterTwo_.varPrio(_fighterTwo,moveTwo_,fight,data);
        fAtt_=data.getMove(moveTwo_);
        byte prioTwo_ = fAtt_.getPriority();
        Item it2_ = FightFacade.useItsObject(fight, _fighterTwo, data);
        boolean canAttackLastTwo_ = it2_ instanceof ItemForBattle && ((ItemForBattle) it2_).getAttackLast();
        boolean permuter_=false;

        prioOne_+=varPrioOne_;
        prioTwo_+=varPrioTwo_;
        if(prioTwo_>prioOne_){
            permuter_=true;
        }else if(NumberUtil.eq(prioTwo_, prioOne_)){
            if(slowOne_&&!slowTwo_){
                permuter_=true;
            }else if(ComparatorBoolean.eq(slowOne_,slowTwo_)){
                if(canAttackLastOne_&&!canAttackLastTwo_){
                    permuter_=true;
                }else if(ComparatorBoolean.eq(canAttackLastOne_, canAttackLastTwo_)){
                    Rate speedOne_=FightFacade.speed(fight,_fighterOne,data);
                    Rate speedTwo_=FightFacade.speed(fight,_fighterTwo,data);
                    if (Rate.eq(speedTwo_,speedOne_)) {
                        return SortConstants.EQ_CMP;
                    }
                    if(ComparatorBoolean.diff(Rate.strGreater(speedTwo_,speedOne_), revertedSpeed)){
                        permuter_=true;
                    }
                }
            }
        }
        if (permuter_) {
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
