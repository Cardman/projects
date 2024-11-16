package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeInteger implements AbsGeneComponentModelSubscribe<Integer> {
    private final GeneComponentModelInt crud;
    public GeneComponentModelSubscribeInteger(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelInt(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneInt();
    }

    @Override
    public Integer tryRet() {
        return crud.valueInt();
    }

    @Override
    public void setupValue(Integer _value) {
        crud.valueInt(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
