package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.game.fight.util.MoveTarget;
import code.util.Numbers;

public final class ComparatorMoveTarget implements Comparing<MoveTarget> {

    @Override
    public int compare(MoveTarget _o1, MoveTarget _o2) {
        int res_ = _o1.getMove().compareTo(_o2.getMove());
        if (res_ != 0) {
            return res_;
        }
        res_ = Numbers.compare(_o1.getTarget().getTeam(), _o2.getTarget().getTeam());
        if (res_ != 0) {
            return res_;
        }
        return Numbers.compare(_o1.getTarget().getPosition(), _o2.getTarget().getPosition());
    }

}