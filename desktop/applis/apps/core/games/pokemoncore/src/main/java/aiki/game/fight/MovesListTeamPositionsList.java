package aiki.game.fight;

import aiki.util.TeamPositionList;
import code.util.CustList;

public final class MovesListTeamPositionsList {
    private final CustList<FighterNamePkNameMv> keyPks;
    private final TeamPositionList teamPositions;

    public MovesListTeamPositionsList(CustList<FighterNamePkNameMv> _k,TeamPositionList _t) {
        this.keyPks = _k;
        this.teamPositions = _t;
    }

    public CustList<FighterNamePkNameMv> getKeyPks() {
        return keyPks;
    }

    public TeamPositionList getTeamPositions() {
        return teamPositions;
    }
}
