package aiki.beans.fight;

import aiki.util.TeamPositionList;
import code.util.CustList;

public final class ImgMovesListTeamPositionsList {
    private final CustList<FighterImgPkNameMv> keyPks;
    private final TeamPositionList teamPositions;
    private final CustList<String> namesPk = new CustList<String>();

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

    public CustList<String> getNamesPk() {
        return namesPk;
    }
}
