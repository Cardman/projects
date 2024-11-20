package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstEnvironmentType extends SubscribedTranslationMessagesFactoryCommonCst<EnvironmentType> {
    @Override
    public StringMap<IdMap<EnvironmentType, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedEnvironment();
    }
}
