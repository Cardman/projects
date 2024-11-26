package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeString implements AbsGeneComponentModelSubscribe<String> {
    private final GeneComponentModelText crud;
    public GeneComponentModelSubscribeString(AbstractProgramInfos _fact) {
        crud = new GeneComponentModelText(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneString();
    }

    @Override
    public String tryRet() {
        return crud.valueString();
    }

    @Override
    public void setupValue(String _value) {
        crud.valueString(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
