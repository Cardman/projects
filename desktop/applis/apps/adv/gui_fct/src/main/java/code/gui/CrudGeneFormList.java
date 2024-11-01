package code.gui;

import code.gui.initialize.*;
import code.util.ints.*;

public final class CrudGeneFormList<E> extends AbsCrudGeneFormList<E> {

    public CrudGeneFormList(AbstractProgramInfos _fact) {
        this(_fact, null);
    }

    public CrudGeneFormList(AbstractProgramInfos _fact, Comparing<E> _c) {
        super(_fact, _c);
    }

    @Override
    protected void afterModif(int _index, E _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterModif();
    }
}
