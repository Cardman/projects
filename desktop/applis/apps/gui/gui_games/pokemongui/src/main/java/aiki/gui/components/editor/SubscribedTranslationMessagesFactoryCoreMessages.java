package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.util.AbsMap;

public interface SubscribedTranslationMessagesFactoryCoreMessages<T> {
    AbsMap<T,String> buildMessagesCopy(FacadeGame _facade, String _lg);
    SubscribedTranslationMessagesFactoryCoreContainer<T> getContainer();
}
