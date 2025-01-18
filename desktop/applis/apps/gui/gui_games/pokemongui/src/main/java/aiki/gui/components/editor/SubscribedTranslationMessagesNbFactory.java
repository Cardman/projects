package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesNbFactory {

    public SubscribedTranslation buildSub(AbsMap<Integer, String> _map, AbsMap<String, String> _messages) {
        return new SubscribedTranslationMessagesNb(_map,_messages,this);
    }
    public abstract IntMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade);
    public abstract void delete(FacadeGame _facade, int _id);
    public abstract void rename(FacadeGame _facade, int _previous, int _next);
}
