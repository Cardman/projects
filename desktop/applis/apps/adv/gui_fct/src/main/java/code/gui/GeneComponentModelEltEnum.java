package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEltEnum<E> extends GeneComponentModelElt<E> {

    public GeneComponentModelEltEnum(AbstractProgramInfos _c, AbsMap<E, String> _messages) {
        this(_c, new DefCustCellRenderGeneImpl<E>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _messages);
    }
    public GeneComponentModelEltEnum(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<E> _rend, AbsMap<E,String> _elts) {
        super(_c, _elts, _rend);
    }

    @Override
    protected E defValue() {
        return null;
    }

}
