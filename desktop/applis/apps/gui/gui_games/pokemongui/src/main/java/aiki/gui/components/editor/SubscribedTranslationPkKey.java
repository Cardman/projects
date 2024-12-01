package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.AbsCrudGeneForm;
import code.gui.initialize.*;

public final class SubscribedTranslationPkKey<E> implements SubscribedTranslation {
    private final AbsCrudGeneForm<E> crud;

    public SubscribedTranslationPkKey(AbsCrudGeneForm<E> _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        crud.possibleSort();
        crud.refresh();
    }
}
