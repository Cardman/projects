package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelEltCommon<T> {
    private final AbstractProgramInfos compoFactory;
    private final CustList<T> elements;
    protected GeneComponentModelEltCommon(AbstractProgramInfos _c, CustList<T> _elts) {
        this.compoFactory = _c;
        elements = _elts;
    }



    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }

    public CustList<T> getElements() {
        return elements;
    }
}
