package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstStat extends SubscribedTranslationMessagesFactoryCommonCst<Statistic> {
    @Override
    public StringMap<IdMap<Statistic, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedStatistics();
    }
}
