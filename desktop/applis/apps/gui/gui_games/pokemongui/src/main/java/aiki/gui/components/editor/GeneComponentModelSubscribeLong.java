package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeLong implements AbsGeneComponentModelSubscribe<Long> {
    private final GeneComponentModelLong crud;
    public GeneComponentModelSubscribeLong(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelLong(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.gene(0L);
    }

    @Override
    public Long tryRet() {
        return crud.valueLong();
    }

    @Override
    public void setupValue(Long _value) {
        crud.valueLong(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
