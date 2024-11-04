package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class GeneComponentModelLsStrSub<E> extends GeneComponentModelEltStrCom {
    private final GeneComponentModelLs<E> selectList;

    public GeneComponentModelLsStrSub(GeneComponentModelLs<E> _s) {
        this.selectList = _s;
    }

    public AbsCustComponent geneCommon(CustList<E> _values) {
        return selectList.geneCommon(_values);
    }

    public CustList<E> tryRet() {
        return selectList.tryRet();
    }

    public void setupValue(CustList<E> _types) {
        selectList.setupValue(_types);
    }
}
