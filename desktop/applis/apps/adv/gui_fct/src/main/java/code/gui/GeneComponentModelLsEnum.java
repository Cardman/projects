package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelLsEnum<E> extends GeneComponentModelLs<E> implements GeneComponentModel<IdList<E>> {
    public GeneComponentModelLsEnum(AbstractProgramInfos _c, IdMap<E, String> _messages) {
        this(_c, new CustCellRenderGeneStrImpl<E>(_c.getImageFactory(),_messages), _messages);
    }
    public GeneComponentModelLsEnum(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<E> _rend, IdMap<E,String> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    public IdList<E> value() {
        return new IdList<E>(tryRet());
    }

    @Override
    public IdList<E> value(IdList<E> _v) {
        IdList<E> ls_ = new IdList<E>(tryRet());
        setupValue(getSelect(),_v);
        return ls_;
    }

    @Override
    protected int indexOf(E _t) {
        return getElements().indexOfEntry(_t);
    }
}
