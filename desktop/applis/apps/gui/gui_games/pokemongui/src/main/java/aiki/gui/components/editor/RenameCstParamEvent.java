package aiki.gui.components.editor;

import code.gui.events.*;

public final class RenameCstParamEvent<K> implements AbsActionListener {
    private final CrudGeneFormTrCstParamList<K> crud;
    private final K key;
    public RenameCstParamEvent(CrudGeneFormTrCstParamList<K> _c, K _k) {
        crud = _c;
        key = _k;
    }

    @Override
    public void action() {
        crud.apply(key);
    }
}
