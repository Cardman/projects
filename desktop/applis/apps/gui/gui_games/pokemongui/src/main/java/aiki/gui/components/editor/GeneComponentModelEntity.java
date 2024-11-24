package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.CustList;
import code.util.StringMap;

public abstract class GeneComponentModelEntity<E> implements GeneComponentModel<EditedCrudPair<String, E>> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final AbsCommonFrame frame;
    private GeneComponentModelEltEnumSub<String> geneComponentModelSelectKey;

    protected GeneComponentModelEntity(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        frame = _fr;
        this.compoFactory = _core;
        facade = _facade;
        subscribedTranslationList = _sub;
    }
    protected void buildKey(int _select, SubscribedTranslationMessagesFactory _builder, CustList<String> _exc) {
        if (_select >= 0) {
            geneComponentModelSelectKey = ConverterCommonMapUtil.merge(compoFactory, facade, _builder, new CustList<String>(), new StringMap<String>());
        } else {
            geneComponentModelSelectKey = ConverterCommonMapUtil.merge(compoFactory, facade, _builder, _exc, new StringMap<String>());
        }
    }

    protected void updateSelector() {
        geneComponentModelSelectKey.getSelectUniq().getSelect().setEnabled(false);
    }
    public AbsCustComponent geneComponentModelSelectKey() {
        GeneComponentModelEltEnumSub<String> ref_ = getGeneComponentModelSelectKey();
        AbsCustComponent compo_ = ref_.geneEnum();
        ref_.getSelectUniq().getSelect().select(0);
        ref_.getSelectUniq().getSelect().text();
        return compo_;
    }
    public GeneComponentModelEltEnumSub<String> getGeneComponentModelSelectKey() {
        return geneComponentModelSelectKey;
    }

    public AbsCommonFrame getFrame() {
        return frame;
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
