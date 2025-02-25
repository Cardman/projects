package aiki.beans;

import aiki.beans.game.*;

public final class BeanDisplayPlaceNamePk implements BeanDisplayEltGrid<PlaceNamePk> {
    @Override
    public int displayEltGrid(CommonBean _rend, PlaceNamePk _info) {
        _rend.formatMessageDirCts(_info.getName());
        _rend.formatMessageDirCts(Long.toString(_info.getIndex()));
        return 2;
    }
}
