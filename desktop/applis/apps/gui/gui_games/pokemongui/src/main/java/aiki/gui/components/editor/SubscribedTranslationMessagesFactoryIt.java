package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.Item;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryIt extends SubscribedTranslationMessagesFactoryCommonParam<Item> {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedItems();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameItem(_previous,_next);
    }

    @Override
    public StringMap<Item> all(FacadeGame _facade) {
        return _facade.getData().getItems();
    }
}
