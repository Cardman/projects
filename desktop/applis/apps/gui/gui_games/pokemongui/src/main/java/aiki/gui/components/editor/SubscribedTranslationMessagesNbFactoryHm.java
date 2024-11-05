package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryHm extends SubscribedTranslationMessagesNbFactory {

    @Override
    public ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getHm();
    }
}
