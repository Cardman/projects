package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommon implements SubscribedTranslationMessagesFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _map, AbsMap<String,String> _withEmpty) {
        return new SubscribedTranslationMessages(_map,this, _withEmpty);
    }

    @Override
    public StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new StringMap<String>(buildMessages(_facade).getVal(_api.getLanguage()));
    }

    @Override
    public StringMap<String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<String,String> _withEmpty) {
        StringMap<String> out_ = new StringMap<String>(buildMessages(_facade).getVal(_api.getLanguage()));
        out_.addAllEntries(_withEmpty);
        return out_;
    }
}
