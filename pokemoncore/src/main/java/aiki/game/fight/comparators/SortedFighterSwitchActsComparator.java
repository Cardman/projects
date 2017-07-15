package aiki.game.fight.comparators;
import java.util.Comparator;

import code.maths.Rate;
import code.util.CustList;
import aiki.DataBase;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.TeamPosition;

public class SortedFighterSwitchActsComparator implements
        Comparator<TeamPosition> {

    private Fight fight;

    private DataBase data;

    public SortedFighterSwitchActsComparator(Fight _fight, DataBase _data) {
        fight = _fight;
        data = _data;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        boolean permuter_=false;
        Rate vitesseOne_=FightFacade.speed(fight,_fighterOne,data);
        Rate vitesseTwo_=FightFacade.speed(fight,_fighterTwo,data);
        if(Rate.strLower(vitesseTwo_,vitesseOne_)){
            permuter_=true;
        }
        if(permuter_){
            return CustList.SWAP_SORT;
        }
        if(Rate.eq(vitesseTwo_,vitesseOne_)){
            return CustList.EQ_CMP;
        }
        return CustList.NO_SWAP_SORT;
    }

}
