package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeByte implements AbsGeneComponentModelSubscribe<Byte> {
    private final GeneComponentModelInt crud;
    public GeneComponentModelSubscribeByte(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelInt(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneInt();
    }

    @Override
    public Byte tryRet() {
        return (byte)crud.valueInt();
    }

    @Override
    public void setupValue(Byte _value) {
        crud.valueInt(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
