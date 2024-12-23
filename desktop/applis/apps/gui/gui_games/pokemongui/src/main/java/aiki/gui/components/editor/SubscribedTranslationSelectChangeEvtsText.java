package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationSelectChangeEvtsText<T> implements SubscribedTranslation {
    private final GeneComponentModelElt<T> input;

    public SubscribedTranslationSelectChangeEvtsText(GeneComponentModelElt<T> _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        input.getSelect().events(null);
    }
}
