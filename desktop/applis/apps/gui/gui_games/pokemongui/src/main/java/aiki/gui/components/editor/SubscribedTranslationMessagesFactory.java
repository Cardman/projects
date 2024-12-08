package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public interface SubscribedTranslationMessagesFactory extends SubscribedTranslationMessagesFactoryCoreMessages<String> {
    boolean contains(FacadeGame _facade,String _key);
    void rename(FacadeGame _facade,String _previous, String _next);
    void renameExp(FacadeGame _facade, String _previous, String _next);
    void cancel();
    StringList mids(FacadeGame _facade);
}
