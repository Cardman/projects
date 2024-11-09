package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelLsEnum<E> extends GeneComponentModelLs<E> implements GeneComponentModel<IdList<E>> {
    public GeneComponentModelLsEnum(AbstractProgramInfos _c, AbsMap<E, String> _messages) {
        this(_c, new DefCustCellRenderGeneImpl<E>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _messages);
    }
    public GeneComponentModelLsEnum(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<E> _rend, AbsMap<E,String> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        return buildLs();
    }
    @Override
    public IdList<E> value() {
        return new IdList<E>(tryRet());
    }

    @Override
    public void value(IdList<E> _v) {
        setupValue(_v);
    }
}
