package aiki.beans;

import aiki.beans.pokemon.*;

public final class BeanDisplayLevelMove implements BeanDisplayElt<LevelMoveTranslatedKey> {

    @Override
    public int displayElt(CommonBean _rend, LevelMoveTranslatedKey _info) {
        _rend.formatMessageDirCts(Long.toString(_info.getLevel()));
        _rend.formatMessageDirCts(_info.getMove());
        return 1;
    }
}
