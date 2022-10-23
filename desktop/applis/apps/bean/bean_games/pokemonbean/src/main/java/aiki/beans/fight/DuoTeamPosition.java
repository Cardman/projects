package aiki.beans.fight;

import aiki.game.fight.MoveTeamPosition;
import aiki.game.fight.util.MoveTarget;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DuoTeamPosition {
    private final String move;
    private final int team;
    private final int position;
    public DuoTeamPosition(MoveTeamPosition _t) {
        move = _t.getMove();
        team = _t.getTeamPosition().getTeam();
        position = _t.getTeamPosition().getPosition();
    }
    public DuoTeamPosition(MoveTarget _t) {
        move = _t.getMove();
        team = _t.getTarget().getTeam();
        position = _t.getTarget().getPosition();
    }
    public static int compare(DuoTeamPosition _o1, DuoTeamPosition _o2) {
        int res_ = StringUtil.compareStrings(_o1.move,_o2.move);
        if (res_ != 0) {
            return res_;
        }
        res_ = NumberUtil.compareLg(_o1.team, _o2.team);
        if (res_ != 0) {
            return res_;
        }
        return NumberUtil.compareLg(_o1.position, _o2.position);
    }
}
