package aiki.beans.facade.comparators;

import aiki.beans.fight.DuoTeamPosition;
import aiki.game.fight.util.MoveTarget;
import code.util.ints.Comparing;

public final class ComparatorMoveTarget implements Comparing<MoveTarget> {

    @Override
    public int compare(MoveTarget _o1, MoveTarget _o2) {
        return DuoTeamPosition.compare(new DuoTeamPosition(_o1),new DuoTeamPosition(_o2));
//        int res_ = StringUtil.compareStrings(_o1.getMove(),_o2.getMove());
//        if (res_ != 0) {
//            return res_;
//        }
//        res_ = NumberUtil.compareLg(_o1.getTarget().getTeam(), _o2.getTarget().getTeam());
//        if (res_ != 0) {
//            return res_;
//        }
//        return NumberUtil.compareLg(_o1.getTarget().getPosition(), _o2.getTarget().getPosition());
    }

}