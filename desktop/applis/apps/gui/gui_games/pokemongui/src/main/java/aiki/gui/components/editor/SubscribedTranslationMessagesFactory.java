package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public interface SubscribedTranslationMessagesFactory extends SubscribedTranslationMessagesFactoryCore<String> {
    StringMap<StringMap<String>> buildMessages(FacadeGame _facade);
    boolean contains(FacadeGame _facade,String _key);
    void rename(FacadeGame _facade,String _previous, String _next);
    StringList mids(FacadeGame _facade);
}
