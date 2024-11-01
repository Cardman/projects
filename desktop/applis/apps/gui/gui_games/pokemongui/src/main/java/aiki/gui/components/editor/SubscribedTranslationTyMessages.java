package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public final class SubscribedTranslationTyMessages implements SubscribedTranslation {
    private final AbsMap<String, String> input;

    public SubscribedTranslationTyMessages(AbsMap<String, String> _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedTypes().getVal(_api.getLanguage()));
        input.clear();
        input.addAllEntries(messages_);
    }
}
