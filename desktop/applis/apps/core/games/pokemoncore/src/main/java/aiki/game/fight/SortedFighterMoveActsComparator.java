package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;

public final class SortedFighterMoveActsComparator extends SortedFighterSpeedComparator {

    public SortedFighterMoveActsComparator(Fight _fight, DataBase _data,
            boolean _revertedSpeed) {
        super(_fight, _data, _revertedSpeed);
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter fighterOne_= getFight().getFighter(_fighterOne);
        Fighter fighterTwo_= getFight().getFighter(_fighterTwo);
        String moveOne_=fighterOne_.getFinalChosenMove();
        byte varPrioOne_= fighterOne_.varPrio(_fighterOne,moveOne_, getFight(), getData());
        MoveData fAtt_= getData().getMove(moveOne_);
        byte prioOne_ = fAtt_.getPriority();
        String moveTwo_=fighterTwo_.getFinalChosenMove();
        byte varPrioTwo_=fighterTwo_.varPrio(_fighterTwo,moveTwo_, getFight(), getData());
        fAtt_= getData().getMove(moveTwo_);
        byte prioTwo_ = fAtt_.getPriority();

        prioOne_+=varPrioOne_;
        prioTwo_+=varPrioTwo_;
        if(prioTwo_>prioOne_){
            return SortConstants.SWAP_SORT;
        }
        if(NumberUtil.eq(prioTwo_, prioOne_)){
            return SortedFighterEndRoundComparator.compare(_fighterOne, _fighterTwo, getData(), getFight(), isRevertedSpeed(),fighterOne_,fighterTwo_);
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
