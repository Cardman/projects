package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeShort implements AbsGeneComponentModelSubscribe<Short> {
    private final GeneComponentModelInt crud;
    public GeneComponentModelSubscribeShort(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelInt(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneInt();
    }

    @Override
    public Short tryRet() {
        return (short)crud.valueInt();
    }

    @Override
    public void setupValue(Short _value) {
        crud.valueInt(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
