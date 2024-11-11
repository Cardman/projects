package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public interface SubscribedTranslationMessagesFactory {
    SubscribedTranslation buildSub(AbsMap<String, String> _map, AbsMap<String,String> _withEmpty);
    StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade);
    StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<String,String> _withEmpty);
    StringMap<StringMap<String>> buildMessages(FacadeGame _facade);
    boolean contains(FacadeGame _facade,String _key);
    void rename(FacadeGame _facade,String _previous, String _next);
}
