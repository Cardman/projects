package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.CustList;

public abstract class GeneComponentModelEntity<E> implements GeneComponentModel<EditedCrudPair<String, E>> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;

    protected GeneComponentModelEntity(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        this.compoFactory = _core;
        facade = _facade;
        subscribedTranslationList = _sub;
    }
    protected void buildKey(int _select, SubscribedTranslationMessagesFactory _builder, CustList<String> _exc) {
        if (_select >= 0) {
            geneComponentModelSelectKey = ConverterCommonMapUtil.merge(compoFactory, facade, _builder, new CustList<String>());
        } else {
            geneComponentModelSelectKey = ConverterCommonMapUtil.merge(compoFactory, facade, _builder, _exc);
        }
    }

    protected void updateSelector() {
        geneComponentModelSelectKey.getSelectUniq().getSelect().setEnabled(false);
    }
    public GeneComponentModelEltStrSub getGeneComponentModelSelectKey() {
        return geneComponentModelSelectKey;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }

    public SubscribedTranslationList getSubscribedTranslationList() {
        return subscribedTranslationList;
    }
}
