package aiki.beans;

import aiki.beans.pokemon.*;

public final class BeanDisplayLevelMove implements BeanDisplayEltGrid<LevelMoveTranslatedKey> {

    @Override
    public int displayEltGrid(CommonBean _rend, LevelMoveTranslatedKey _info) {
        _rend.formatMessageDirCts(Long.toString(_info.getLevel()));
        _rend.formatMessageDirCts(_info.getMove());
        return 1;
    }
}
