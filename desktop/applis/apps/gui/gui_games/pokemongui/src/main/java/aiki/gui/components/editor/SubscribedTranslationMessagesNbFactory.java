package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesNbFactory {

    public SubscribedTranslation buildSub(AbsMap<Short, String> _map, AbsMap<String, String> _messages) {
        return new SubscribedTranslationMessagesNb(_map,_messages,this);
    }
    public abstract ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade);
    public abstract void delete(FacadeGame _facade, short _id);
    public abstract void rename(FacadeGame _facade, short _previous, short _next);
}
