package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelSubscribeRate implements AbsGeneComponentModelSubscribe<Rate> {
    private final GeneComponentModelRate crud;
    public GeneComponentModelSubscribeRate(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelRate(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneRate();
    }

    @Override
    public Rate tryRet() {
        return crud.valueRate();
    }

    @Override
    public void setupValue(Rate _value) {
        crud.valueRate(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
