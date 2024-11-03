package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.AbsCrudGeneForm;
import code.gui.initialize.*;

public final class SubscribedTranslationPkKey implements SubscribedTranslation {
    private final AbsCrudGeneForm crud;

    public SubscribedTranslationPkKey(AbsCrudGeneForm _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        crud.possibleSort();
        crud.refresh();
    }
}
