package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationPkSelect implements SubscribedTranslation {
    private final GeneComponentModelEltStr input;

    public SubscribedTranslationPkSelect(GeneComponentModelEltStr _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        input.getMessages().clear();
        input.getMessages().addAllEntries(messages_);
        AbsStringScrollCustomCombo<String> sel_ = input.getSelect();
        if (sel_ == null) {
            return;
        }
        sel_.repaint();
    }
}
