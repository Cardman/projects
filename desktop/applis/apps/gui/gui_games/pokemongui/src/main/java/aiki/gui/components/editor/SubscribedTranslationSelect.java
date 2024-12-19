package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationSelect implements SubscribedTranslation {
    private final GeneComponentModelStr input;

    public SubscribedTranslationSelect(GeneComponentModelStr _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        input.reset();
    }
}
