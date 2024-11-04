package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryIt extends SubscribedTranslationMessagesFactoryCommon {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedItems();
    }
}
