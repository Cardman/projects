package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationSelect implements SubscribedTranslation {
    private final GeneComponentModelEltStr input;

    public SubscribedTranslationSelect(GeneComponentModelEltStr _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        input.reset();
    }
}
