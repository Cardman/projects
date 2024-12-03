package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeString implements AbsGeneComponentModelSubscribe<String> {
    private final GeneComponentModelText crud;
    private final FacadeGame facadeGame;
    public GeneComponentModelSubscribeString(AbstractProgramInfos _fact, FacadeGame _fac) {
        crud = new GeneComponentModelText(_fact);
        facadeGame = _fac;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCustComponent c_ = geneEnum();
        addComplete();
        return c_;
    }

    public AbsCustComponent geneEnum() {
        return crud.geneString();
    }

    public void addComplete() {
        crud.addComplete(facadeGame);
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
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationRenamingId(crud.getTextPane()));
        return ids_;
    }

}
