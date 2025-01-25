package aiki.beans.fight;

import aiki.game.fight.*;

public final class MoveTeamPositionFighterName {
    private final MoveTeamPosition moveTeamPosition;
    private final String name;

    public MoveTeamPositionFighterName(MoveTeamPosition _m, String _n) {
        this.moveTeamPosition = _m;
        this.name = _n;
    }

    public MoveTeamPosition getMoveTeamPosition() {
        return moveTeamPosition;
    }

    public String getName() {
        return name;
    }
}
