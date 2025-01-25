package aiki.beans.facade.comparators;

import aiki.beans.fight.DuoTeamPosition;
import aiki.beans.fight.MoveTeamPositionFighterName;
import code.util.ints.Comparing;

public final class ComparatorMoveTeamPosition implements Comparing<MoveTeamPositionFighterName> {

    @Override
    public int compare(MoveTeamPositionFighterName _o1, MoveTeamPositionFighterName _o2) {
        return DuoTeamPosition.compare(new DuoTeamPosition(_o1.getMoveTeamPosition()),new DuoTeamPosition(_o2.getMoveTeamPosition()));
//        int res_ = StringUtil.compareStrings(_o1.getMove(),_o2.getMove());
//        if (res_ != 0) {
//            return res_;
//        }
//        res_ = NumberUtil.compareLg(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//        if (res_ != 0) {
//            return res_;
//        }
//        return NumberUtil.compareLg(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
    }

}