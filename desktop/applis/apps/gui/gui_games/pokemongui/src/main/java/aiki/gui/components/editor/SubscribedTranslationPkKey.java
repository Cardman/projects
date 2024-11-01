package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;

public final class SubscribedTranslationPkKey<K,V> implements SubscribedTranslation {
    private final CrudGeneFormSub<K,V> crud;

    public SubscribedTranslationPkKey(CrudGeneFormSub<K,V> _c) {
        this.crud = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        crud.updateDisplayEntry(_api, _facade);
        crud.refresh();
    }
}
