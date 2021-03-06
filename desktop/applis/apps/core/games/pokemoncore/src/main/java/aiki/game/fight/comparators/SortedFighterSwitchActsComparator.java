package aiki.game.fight.comparators;
import aiki.db.DataBase;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.TeamPosition;
import code.maths.Rate;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class SortedFighterSwitchActsComparator implements
        Comparing<TeamPosition> {

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
            return SortConstants.SWAP_SORT;
        }
        if(Rate.eq(vitesseTwo_,vitesseOne_)){
            return SortConstants.EQ_CMP;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
