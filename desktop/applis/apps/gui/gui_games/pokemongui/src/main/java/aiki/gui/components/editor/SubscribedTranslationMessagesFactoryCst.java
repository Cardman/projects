package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public interface SubscribedTranslationMessagesFactoryCst<T> extends SubscribedTranslationMessagesFactoryCore<T> {
    StringMap<IdMap<T,String>> buildMessages(FacadeGame _facade);
}
