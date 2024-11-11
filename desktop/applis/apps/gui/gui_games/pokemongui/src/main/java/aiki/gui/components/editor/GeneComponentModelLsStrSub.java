package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class GeneComponentModelLsStrSub<E> extends GeneComponentModelEltStrCom implements AbsGeneComponentModelSubscribe<CustList<E>> {
    private final GeneComponentModelLs<E> selectList;

    public GeneComponentModelLsStrSub(GeneComponentModelLs<E> _s) {
        this.selectList = _s;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return selectList.geneCommon(new CustList<E>());
    }

    public AbsCustComponent geneEnum() {
        return geneEnum(0,0);
    }

    public CustList<E> tryRet() {
        return selectList.tryRet();
    }

    public void setupValue(CustList<E> _types) {
        selectList.setupValue(_types);
    }
}
