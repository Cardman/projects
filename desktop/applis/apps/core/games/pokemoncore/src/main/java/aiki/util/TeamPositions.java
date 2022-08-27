package aiki.util;

import aiki.game.fight.TeamPosition;

public abstract class TeamPositions<T> extends CommonMap<TeamPosition,T> {
    @Override
    protected boolean eq(TeamPosition _one, TeamPosition _two) {
        return _one.eq(_two);
    }
}
