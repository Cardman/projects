package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelEltEnum<E> extends GeneComponentModelElt<E> {
    protected GeneComponentModelEltEnum(AbstractProgramInfos _c, AbsMap<E,String> _messages, CustList<E> _elts) {
        this(_c, new CustCellRenderGeneStrImpl<E>(_c.getImageFactory(),_messages), _elts);
    }
    protected GeneComponentModelEltEnum(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<E> _rend, CustList<E> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    protected void setupValue(DefScrollCustomGraphicList<E> _t, E _v) {
        _t.select(new IdList<E>(getElements()).indexOfObj(_v));
    }
}
