package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryIt extends SubscribedTranslationMessagesFactoryCommon {

    @Override
    public StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new StringMap<String>(_facade.getData().getTranslatedItems().getVal(_api.getLanguage()));
    }
}
