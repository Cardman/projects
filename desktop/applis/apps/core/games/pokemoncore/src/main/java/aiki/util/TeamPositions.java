package aiki.util;

import aiki.game.fight.TeamPosition;
import code.util.*;

public abstract class TeamPositions<T> extends AbsBasicMap<TeamPosition,T> {
    @Override
    protected boolean matchKeys(TeamPosition _one, TeamPosition _two) {
        return _one.eq(_two);
    }
}
