package code.gui;

import code.gui.initialize.*;
import code.util.ints.*;

public final class CrudGeneForm<K,V> extends AbsCrudGeneFormMap<K,V> {

    public CrudGeneForm(AbstractProgramInfos _fact, Comparing<K> _cmp) {
        super(_fact, _cmp);
    }

    @Override
    protected void afterModif(int _index, K _key, V _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterModif();
    }
}
