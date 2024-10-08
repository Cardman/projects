package aiki.beans.fight;

import aiki.util.TeamPositionList;
import code.util.CustList;

public final class ImgMovesListTeamPositionsList {
    private final CustList<FighterImgPkNameMv> keyPks;
    private final TeamPositionList teamPositions;

    public ImgMovesListTeamPositionsList(CustList<FighterImgPkNameMv> _k,TeamPositionList _t) {
        this.keyPks = _k;
        this.teamPositions = _t;
    }

    public CustList<FighterImgPkNameMv> getKeyPks() {
        return keyPks;
    }

    public TeamPositionList getTeamPositions() {
        return teamPositions;
    }
}
