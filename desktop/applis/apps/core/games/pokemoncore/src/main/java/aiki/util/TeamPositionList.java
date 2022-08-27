package aiki.util;

import aiki.game.fight.TeamPosition;
import code.util.AbEqList;
import code.util.CollCapacity;
import code.util.CustList;

public final class TeamPositionList extends AbEqList<TeamPosition> {
    public TeamPositionList() {
    }
    public TeamPositionList(CustList<TeamPosition> _ls) {
        super(_ls);
    }
    public TeamPositionList(CollCapacity _cap) {
        super(_cap);
    }
    public static TeamPositionList newList(TeamPosition... _args) {
        TeamPositionList tp_ = new TeamPositionList(new CollCapacity(_args.length));
        for (TeamPosition t: _args) {
            tp_.add(t);
        }
        return tp_;
    }
    @Override
    public boolean match(TeamPosition _one, TeamPosition _two) {
        return _one.eq(_two);
    }
}
