package aiki.beans.fight;

import aiki.beans.game.*;
import aiki.util.*;
import code.util.*;

public final class ImgMovesListTeamPositionsList {
    private final CustList<ImgPkPlayer> keyPks;
    private final TeamPositionList teamPositions;
    private final CustList<String> namesPk = new CustList<String>();

    public ImgMovesListTeamPositionsList(CustList<ImgPkPlayer> _k,TeamPositionList _t) {
        this.keyPks = _k;
        this.teamPositions = _t;
    }

    public CustList<ImgPkPlayer> getKeyPks() {
        return keyPks;
    }

    public TeamPositionList getTeamPositions() {
        return teamPositions;
    }

    public CustList<String> getNamesPk() {
        return namesPk;
    }
}
