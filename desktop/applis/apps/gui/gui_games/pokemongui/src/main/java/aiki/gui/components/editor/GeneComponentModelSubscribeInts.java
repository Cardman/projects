package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeInts implements AbsGeneComponentModelSubscribe<Ints> {
    private final CrudGeneFormInts crud;
    public GeneComponentModelSubscribeInts(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        crud = new CrudGeneFormInts(_fact, _facade, _sub, _fr);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        crud.initForm();
        crud.initForm(new CustList<Integer>());
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
        return crud.all();
    }

    public CrudGeneFormInts getCrud() {
        return crud;
    }
}
