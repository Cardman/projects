package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeInts implements AbsGeneComponentModelSubscribe<Ints> {
    private final CrudGeneFormSimpleElementSub<Integer> crud;
    public GeneComponentModelSubscribeInts(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        crud = new CrudGeneFormSimpleElementSub<Integer>(_fact, _facade, _sub, _fr);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        crud.initForm(new DisplayEntryCustSubElementInt(),new GeneComponentModelSubscribeFactoryDirect<Integer>(new GeneComponentModelSubscribeInteger(crud.getFactory())));
        return crud.getGroup();
    }

    @Override
    public Ints tryRet() {
        return new Ints(crud.getList());
    }

    @Override
    public void setupValue(Ints _value) {
        crud.setupValues(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return getCrud().subscribeButtons();
    }

    public CrudGeneFormSimpleElement<Integer> getCrud() {
        return crud.getCrud();
    }
}
