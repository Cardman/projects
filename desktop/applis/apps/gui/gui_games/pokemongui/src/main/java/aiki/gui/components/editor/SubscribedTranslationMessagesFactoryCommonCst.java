package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommonCst<T> implements SubscribedTranslationMessagesFactoryCst<T> {
    @Override
    public SubscribedTranslation buildSub(AbsMap<T, String> _map, AbsMap<T,String> _withEmpty) {
        return new SubscribedTranslationMessages<T>(_map,this, _withEmpty);
    }

    @Override
    public AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return new IdMap<T, String>(buildMessages(_facade).getVal(_api.getLanguage()));
    }

    @Override
    public AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<T,String> _withEmpty) {
        IdMap<T, String> out_ = new IdMap<T, String>(buildMessages(_facade).getVal(_api.getLanguage()));
        out_.addAllEntries(_withEmpty);
        return out_;
    }
}
