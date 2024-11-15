package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public interface SubscribedTranslationMessagesFactoryCore<T> {
    SubscribedTranslation buildSub(AbsMap<T, String> _map, AbsMap<T,String> _withEmpty);
    AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade);
    AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<T,String> _withEmpty);
}
