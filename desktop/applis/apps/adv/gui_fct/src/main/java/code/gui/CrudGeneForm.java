package code.gui;

import code.gui.initialize.*;

public final class CrudGeneForm<K,V> extends AbsCrudGeneFormMap<K,V> {

    public CrudGeneForm(AbstractProgramInfos _fact) {
        super(_fact);
    }

    @Override
    protected void afterModif(int _index, K _key, V _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterModif();
    }
}
