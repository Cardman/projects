package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public interface SubscribedTranslationMessagesNbFactory {
    SubscribedTranslation buildSub(AbsMap<Short, String> _map, AbsMap<String, String> _messages);
    ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade);
}
