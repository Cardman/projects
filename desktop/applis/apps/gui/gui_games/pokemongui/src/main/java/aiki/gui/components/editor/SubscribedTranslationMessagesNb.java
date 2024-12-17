package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNb implements SubscribedTranslation {
    private final AbsMap<Short, String> input;
    private final AbsMap<String, String> messages;
    private final SubscribedTranslationMessagesNbFactory factory;

    public SubscribedTranslationMessagesNb(AbsMap<Short, String> _c, AbsMap<String, String> _m, SubscribedTranslationMessagesNbFactory _sub) {
        this.input = _c;
        this.factory = _sub;
        this.messages = _m;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase, RenamingImgNamePhase _renamingImgNamePhase) {
        ShortMap<String> map_ = factory.retrieveMap(_api, _facade);
        input.clear();
        input.addAllEntries(ConverterCommonMapUtil.map(map_,messages));
    }
}
