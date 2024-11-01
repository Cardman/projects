package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public final class SubscribedTranslationMvMessages implements SubscribedTranslation {
    private final AbsMap<String, String> input;

    public SubscribedTranslationMvMessages(AbsMap<String, String> _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedMoves().getVal(_api.getLanguage()));
        input.clear();
        input.addAllEntries(messages_);
    }
}
