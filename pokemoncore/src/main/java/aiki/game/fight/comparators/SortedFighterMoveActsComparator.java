package aiki.game.fight.comparators;
import java.util.Comparator;

import code.maths.Rate;
import code.util.CustList;
import code.util.Numbers;
import code.util.comparators.ComparatorBoolean;
import aiki.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.MoveData;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;

public class SortedFighterMoveActsComparator implements
        Comparator<TeamPosition> {

    private Fight fight;

    private DataBase data;

    private boolean revertedSpeed;

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
        boolean slowOne_=false;
        boolean canAttackLastOne_=false;
        byte varPrioOne_=0;
        byte prioOne_=0;
        String categOne_=DataBase.EMPTY_STRING;
        MoveData fAtt_=data.getMove(moveOne_);
        prioOne_=fAtt_.getPriority();
        categOne_ = fAtt_.getCategory();
        if(fighterOne_.capaciteActive()){
            AbilityData fCapac_=fighterOne_.ficheCapaciteActuelle(data);
            slowOne_=fCapac_.isSlowing();
            if(fCapac_.getIncreasedPrio().contains(categOne_)){
                varPrioOne_+=fCapac_.getIncreasedPrio().getVal(categOne_);
            }
            for (String type_: FightFacade.moveTypes(fight, _fighterOne, moveOne_, data)) {
                if (fCapac_.getIncreasedPrioTypes().contains(type_)) {
                    varPrioOne_ += fCapac_.getIncreasedPrioTypes().getVal(type_);
                }
            }
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
        String moveTwo_=fighterTwo_.getFinalChosenMove();
        boolean slowTwo_=false;
        boolean canAttackLastTwo_=false;
        byte varPrioTwo_=0;
        byte prioTwo_=0;
        String categTwo_=DataBase.EMPTY_STRING;
        fAtt_=data.getMove(moveTwo_);
        prioTwo_=fAtt_.getPriority();
        categTwo_ = fAtt_.getCategory();
        if(fighterTwo_.capaciteActive()){
            AbilityData fCapac_=fighterTwo_.ficheCapaciteActuelle(data);
            slowTwo_=fCapac_.isSlowing();
            if(fCapac_.getIncreasedPrio().contains(categTwo_)){
                varPrioTwo_+=fCapac_.getIncreasedPrio().getVal(categTwo_);
            }
            for (String type_: FightFacade.moveTypes(fight, _fighterTwo, moveTwo_, data)) {
                if (fCapac_.getIncreasedPrioTypes().contains(type_)) {
                    varPrioTwo_ += fCapac_.getIncreasedPrioTypes().getVal(type_);
                }
            }
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

        prioOne_+=varPrioOne_;
        prioTwo_+=varPrioTwo_;
        if(prioTwo_>prioOne_){
            permuter_=true;
        }else if(Numbers.eq(prioTwo_, prioOne_)){
            if(slowOne_&&!slowTwo_){
                permuter_=true;
            }else if(ComparatorBoolean.eq(slowOne_,slowTwo_)){
                if(canAttackLastOne_&&!canAttackLastTwo_){
                    permuter_=true;
                }else if(ComparatorBoolean.eq(canAttackLastOne_, canAttackLastTwo_)){
                    Rate speedOne_=FightFacade.speed(fight,_fighterOne,data);
                    Rate speedTwo_=FightFacade.speed(fight,_fighterTwo,data);
                    if (Rate.eq(speedTwo_,speedOne_)) {
                        return CustList.EQ_CMP;
                    }
                    if(ComparatorBoolean.diff(Rate.strGreater(speedTwo_,speedOne_), revertedSpeed)){
                        permuter_=true;
                    }
                }
            }
        }
        if (permuter_) {
            return CustList.SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }

}
