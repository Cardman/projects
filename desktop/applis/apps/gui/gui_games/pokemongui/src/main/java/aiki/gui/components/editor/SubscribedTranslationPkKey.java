package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;

public final class SubscribedTranslationPkKey implements SubscribedTranslation {
    private final CrudGeneFormSubUp crud;

    public SubscribedTranslationPkKey(CrudGeneFormSubUp _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        crud.updateDisplayEntry(_api, _facade);
    }
}
