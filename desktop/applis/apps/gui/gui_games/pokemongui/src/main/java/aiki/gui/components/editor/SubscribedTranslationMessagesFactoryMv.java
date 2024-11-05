package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.MoveData;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryMv extends SubscribedTranslationMessagesFactoryCommonParam<MoveData> {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedMoves();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameMove(_previous,_next);
    }

    @Override
    public StringMap<MoveData> all(FacadeGame _facade) {
        return _facade.getData().getMoves();
    }
}
