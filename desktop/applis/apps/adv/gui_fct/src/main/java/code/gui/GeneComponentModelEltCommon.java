package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelEltCommon<T> {
    private final AbstractProgramInfos compoFactory;
    private final AbsMap<T,String> elements;
    protected GeneComponentModelEltCommon(AbstractProgramInfos _c, AbsMap<T,String> _elts) {
        this.compoFactory = _c;
        elements = _elts;
    }



    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }

    public AbsMap<T,String> getElements() {
        return elements;
    }
}
