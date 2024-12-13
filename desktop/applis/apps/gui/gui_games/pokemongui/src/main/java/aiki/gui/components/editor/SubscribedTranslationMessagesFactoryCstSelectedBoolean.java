package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.facade.enums.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCstSelectedBoolean extends SubscribedTranslationMessagesFactoryCommonCst<SelectedBoolean> {
    @Override
    public StringMap<IdMap<SelectedBoolean, String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedBooleans();
    }
}
