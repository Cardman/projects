package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationSelectChangeEvtsText implements SubscribedTranslation {
    private final ScrollCustomComboInt input;

    public SubscribedTranslationSelectChangeEvtsText(ScrollCustomComboInt _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        input.events(null);
    }
}
