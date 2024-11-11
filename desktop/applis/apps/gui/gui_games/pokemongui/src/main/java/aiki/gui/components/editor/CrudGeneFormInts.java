package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormInts extends CrudGeneFormListSub<Integer> {

    private GeneComponentModelInt geneComponentModelInt;

    public CrudGeneFormInts(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, new IntComparator());
    }

    public void initForm(CustList<Integer> _map) {
        geneComponentModelInt = new GeneComponentModelInt(getFactory());
        initForm(new DisplayKeyOnlyInteger(), geneComponentModelInt, _map,new IntComparator());
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }

    public GeneComponentModelInt getGeneComponentModelInt() {
        return geneComponentModelInt;
    }
}
