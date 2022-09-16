package aiki.game.fight;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import code.maths.Rate;
import code.util.comparators.ComparatorBoolean;
import code.util.core.SortConstants;

public final class SortedFighterEndRoundComparator extends SortedFighterSpeedComparator {

    public SortedFighterEndRoundComparator(Fight _fight, DataBase _data,
            boolean _revertedSpeed) {
        super(_fight, _data, _revertedSpeed);
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter fighterOne_= getFight().getFighter(_fighterOne);
        Fighter fighterTwo_= getFight().getFighter(_fighterTwo);
        return compare(_fighterOne, _fighterTwo, getData(), getFight(), isRevertedSpeed(), fighterOne_, fighterTwo_);
    }

    public static int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo, DataBase _data, Fight _fight, boolean _revertedSpeed, Fighter _one, Fighter _two) {
        boolean slowOne_= _one.isSlowing(_data);
        Item it1_ = FightItems.useItsObject(_fight, _fighterOne, _data);
        boolean canAttackLastOne_ = it1_ instanceof ItemForBattle && ((ItemForBattle) it1_).getAttackLast();
        boolean slowTwo_= _two.isSlowing(_data);
        Item it2_ = FightItems.useItsObject(_fight, _fighterTwo, _data);
        boolean canAttackLastTwo_ = it2_ instanceof ItemForBattle && ((ItemForBattle) it2_).getAttackLast();
        if(slowOne_&&!slowTwo_){
            return SortConstants.SWAP_SORT;
        }else if(ComparatorBoolean.eq(slowOne_, slowTwo_)){
            if(canAttackLastOne_&&!canAttackLastTwo_){
                return SortConstants.SWAP_SORT;
            }else if(ComparatorBoolean.eq(canAttackLastOne_,canAttackLastTwo_)){
                Rate speedOne_= FightOrder.speed(_fight, _fighterOne, _data);
                Rate speedTwo_= FightOrder.speed(_fight, _fighterTwo, _data);
                if (Rate.eq(speedTwo_,speedOne_)) {
                    return SortConstants.EQ_CMP;
                }
                if(ComparatorBoolean.diff(Rate.strGreater(speedTwo_,speedOne_), _revertedSpeed)){
                    return SortConstants.SWAP_SORT;
                }
                return SortConstants.NO_SWAP_SORT;
            }
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
