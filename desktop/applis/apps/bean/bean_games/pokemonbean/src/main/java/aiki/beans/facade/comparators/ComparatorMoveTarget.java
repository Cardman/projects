package aiki.beans.facade.comparators;

import aiki.beans.fight.DuoTeamPosition;
import aiki.beans.fight.TrPkMoveTarget;
import code.util.ints.Comparing;

public final class ComparatorMoveTarget implements Comparing<TrPkMoveTarget> {

    @Override
    public int compare(TrPkMoveTarget _o1, TrPkMoveTarget _o2) {
        return DuoTeamPosition.compare(new DuoTeamPosition(_o1.getMoveTarget()),new DuoTeamPosition(_o2.getMoveTarget()));
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