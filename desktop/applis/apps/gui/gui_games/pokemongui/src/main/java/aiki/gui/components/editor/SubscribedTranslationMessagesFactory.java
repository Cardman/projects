package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public interface SubscribedTranslationMessagesFactory {
    SubscribedTranslation buildSub(AbsMap<String, String> _map);
    StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade);
}
