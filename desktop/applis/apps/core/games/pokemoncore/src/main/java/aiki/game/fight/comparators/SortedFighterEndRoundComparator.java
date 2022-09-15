package aiki.game.fight.comparators;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;
import code.maths.Rate;
import code.util.comparators.ComparatorBoolean;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class SortedFighterEndRoundComparator implements Comparing<TeamPosition> {

    private Fight fight;

    private DataBase data;

    private boolean revertedSpeed;

    public SortedFighterEndRoundComparator(Fight _fight, DataBase _data,
            boolean _revertedSpeed) {
        fight = _fight;
        data = _data;
        revertedSpeed = _revertedSpeed;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter fighterOne_=fight.getFighter(_fighterOne);
        boolean slowOne_=fighterOne_.isSlowing(data);
        Item it1_ = FightFacade.useItsObject(fight, _fighterOne, data);
        boolean canAttackLastOne_ = it1_ instanceof ItemForBattle && ((ItemForBattle) it1_).getAttackLast();
        Fighter fighterTwo_=fight.getFighter(_fighterTwo);
        boolean slowTwo_=fighterTwo_.isSlowing(data);
        Item it2_ = FightFacade.useItsObject(fight, _fighterTwo, data);
        boolean canAttackLastTwo_ = it2_ instanceof ItemForBattle && ((ItemForBattle) it2_).getAttackLast();
        boolean permuter_=false;
        if(slowOne_&&!slowTwo_){
            permuter_=true;
        }else if(ComparatorBoolean.eq(slowOne_, slowTwo_)){
            if(canAttackLastOne_&&!canAttackLastTwo_){
                permuter_=true;
            }else if(ComparatorBoolean.eq(canAttackLastOne_,canAttackLastTwo_)){
                Rate speedOne_=FightFacade.speed(fight,_fighterOne,data);
                Rate speedTwo_=FightFacade.speed(fight,_fighterTwo,data);
                if (Rate.eq(speedTwo_,speedOne_)) {
                    return SortConstants.EQ_CMP;
                }
                if(ComparatorBoolean.diff(Rate.strGreater(speedTwo_,speedOne_),revertedSpeed)){
                    permuter_=true;
                }
            }
        }
        if (permuter_) {
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
