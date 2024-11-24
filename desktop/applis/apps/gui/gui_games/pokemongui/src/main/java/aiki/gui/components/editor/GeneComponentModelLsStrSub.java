package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class GeneComponentModelLsStrSub<E,F> extends GeneComponentModelEltStrCom implements AbsGeneComponentModelSubscribe<F> {
    private final GeneComponentModelLs<E> selectList;
    private final IntListConvert<E,F> converter;

    public GeneComponentModelLsStrSub(GeneComponentModelLs<E> _s, IntListConvert<E,F> _conv) {
        this.selectList = _s;
        this.converter = _conv;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return selectList.geneCommon(new CustList<E>());
    }

    public AbsCustComponent geneEnum() {
        return geneEnum(0,0);
    }

    public F tryRet() {
        return converter.fromList(selectList.tryRet());
    }

    public void setupValue(F _types) {
        selectList.setupValue(converter.toList(_types));
    }
}
