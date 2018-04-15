package aiki.game.fight.comparators;
import java.util.Comparator;

import aiki.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;
import code.maths.Rate;
import code.util.CustList;
import code.util.comparators.ComparatorBoolean;

public final class SortedFighterEndRoundComparator implements Comparator<TeamPosition> {

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
        boolean slowOne_=false;
        boolean canAttackLastOne_=false;
        if(fighterOne_.capaciteActive()){
            AbilityData fCapac_=fighterOne_.ficheCapaciteActuelle(data);
            slowOne_=fCapac_.isSlowing();
        }
        if(FightFacade.canUseItsObject(fight,_fighterOne,data)){
            String it_ = fighterOne_.getItem();
            Item objet_=data.getItem(it_);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                canAttackLastOne_=objetAttachable_.getAttackLast();
            }
        }
        Fighter fighterTwo_=fight.getFighter(_fighterTwo);
        boolean slowTwo_=false;
        boolean canAttackLastTwo_=false;
        if(fighterTwo_.capaciteActive()){
            AbilityData fCapac_=fighterTwo_.ficheCapaciteActuelle(data);
            slowTwo_=fCapac_.isSlowing();
        }
        if(FightFacade.canUseItsObject(fight,_fighterTwo,data)){
            String it_ = fighterTwo_.getItem();
            Item objet_=data.getItem(it_);
            if(objet_ instanceof ItemForBattle){
                ItemForBattle objetAttachable_=(ItemForBattle)objet_;
                canAttackLastTwo_=objetAttachable_.getAttackLast();
            }
        }
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
                    return CustList.EQ_CMP;
                }
                if(ComparatorBoolean.diff(Rate.strGreater(speedTwo_,speedOne_),revertedSpeed)){
                    permuter_=true;
                }
            }
        }
        if (permuter_) {
            return CustList.SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }

}
