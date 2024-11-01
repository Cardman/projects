package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationPkMessages implements SubscribedTranslation {
    private final AbsMap<String, String> input;

    public SubscribedTranslationPkMessages(AbsMap<String, String> _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        input.clear();
        input.addAllEntries(messages_);
    }
}
