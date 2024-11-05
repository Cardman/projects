package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryTm extends SubscribedTranslationMessagesNbFactory {

    @Override
    public ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getTm();
    }
}
