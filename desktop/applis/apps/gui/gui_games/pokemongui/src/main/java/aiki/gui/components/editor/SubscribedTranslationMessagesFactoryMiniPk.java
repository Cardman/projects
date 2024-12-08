package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryMiniPk extends SubscribedTranslationMessagesFactoryImgPk {
    @Override
    public StringMap<ImageArrayBaseSixtyFour> all(FacadeGame _facade) {
        return _facade.getData().getMiniPk();
    }
}
