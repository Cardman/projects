package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormSimpleElement<K> extends CrudGeneFormListSub<K> {
    private DisplayEntryCustSubElement<K> displayEntryCustSub;
    private GeneComponentModelSimpleElement<K> genePair;

    public CrudGeneFormSimpleElement(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);

    }
    public void initForm(DisplayEntryCustSubElement<K> _d, AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<K> _k) {
        getCrudGeneFormSubContent().clear();
        displayEntryCustSub = _d;
        genePair = new GeneComponentModelSimpleElement<K>(_core,_k);
        initForm();
        initForm(_d.buildDisplay(), genePair, _d.buildCmp());
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(displayEntryCustSub.buildSub());
        ids_.add(new SubscribedTranslationPkKey<K>(this));
        return ids_;
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(genePair.all());
        return all_;
    }

    public GeneComponentModelSimpleElement<K> getGenePair() {
        return genePair;
    }
}
