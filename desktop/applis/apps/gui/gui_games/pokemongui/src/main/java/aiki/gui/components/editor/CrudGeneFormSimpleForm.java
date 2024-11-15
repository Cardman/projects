package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormSimpleForm<K, V> extends CrudGeneFormListSub<EditedCrudPair<K, V>> {
    private DisplayEntryCustSub<K> displayEntryCustSub;
    private AbsMap<K, String> messages;
    private GeneComponentModelSimplePair<K,V> genePair;

    public CrudGeneFormSimpleForm(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);

    }
    public void initForm(DisplayEntryCustSub<K> _d,AbsMap<K, String> _disp, AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v, AbsMap<K,V> _evos) {
        getCrudGeneFormSubContent().clear();
        displayEntryCustSub = _d;
        genePair = new GeneComponentModelSimplePair<K,V>(_core,_k,_v);
        initForm();
        messages = _disp;
        Comparing<EditedCrudPair<K, V>> cmp_ = new ComparatorTrWrapperPairs<K, V>().wrap(_disp);
        initForm(new DisplayKeyOnly<K, V>(_disp), genePair, new MapToEntriesListUtil<K,V>().build(_evos), cmp_,new ValidateElementPair<K, V>(cmp_));
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(displayEntryCustSub.buildSub(messages));
        ids_.add(new SubscribedTranslationPkKey<EditedCrudPair<K, V>>(this));
        return ids_;
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(genePair.all());
        return all_;
    }

    public GeneComponentModelSimplePair<K, V> getGenePair() {
        return genePair;
    }
}
