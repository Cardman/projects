package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationPkKey<E> implements SubscribedTranslation {
    private final AbsCrudGeneFormList<E> crud;

    public SubscribedTranslationPkKey(AbsCrudGeneFormList<E> _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        crud.possibleSort();
        crud.refresh();
    }
}
