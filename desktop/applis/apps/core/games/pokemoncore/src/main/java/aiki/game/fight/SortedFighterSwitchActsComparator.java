package aiki.game.fight;
import aiki.db.DataBase;
import code.maths.Rate;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class SortedFighterSwitchActsComparator implements
        Comparing<TeamPosition> {

    private final Fight fight;

    private final DataBase data;

    public SortedFighterSwitchActsComparator(Fight _fight, DataBase _data) {
        fight = _fight;
        data = _data;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Rate vitesseOne_= FightOrder.speed(fight, _fighterOne, data);
        Rate vitesseTwo_= FightOrder.speed(fight, _fighterTwo, data);
        boolean permuter_ = Rate.strLower(vitesseTwo_, vitesseOne_);
        if(permuter_){
            return SortConstants.SWAP_SORT;
        }
        if(Rate.eq(vitesseTwo_,vitesseOne_)){
            return SortConstants.EQ_CMP;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
