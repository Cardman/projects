package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormSimpleForm<K, V> extends CrudGeneFormListSub<EditedCrudPair<K, V>> {
    private DisplayEntryCustSubElement<EditedCrudPair<K, V>> displayEntryCustSub;
    private GeneComponentModelSimplePair<K,V> genePair;

    public CrudGeneFormSimpleForm(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);

    }

    public void initForm(DisplayEntryCustSubElement<EditedCrudPair<K, V>> _d, AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v, ValidateElementPairFactory<K,V> _facto) {
        getCrudGeneFormSubContent().clear();
        displayEntryCustSub = _d;
        genePair = new GeneComponentModelSimplePair<K,V>(_core,_k,_v);
        initForm();
        Comparing<EditedCrudPair<K, V>> cmp_ = _d.buildCmp();
        initForm(_d.buildDisplay(), genePair, cmp_,_facto.build(cmp_));
    }

    public void initForm(DisplayEntryCustSubElement<EditedCrudPair<K, V>> _d, AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v, ValidateElementPairFactory<K,V> _facto, String _txtKey, String _txtValue) {
        getCrudGeneFormSubContent().clear();
        displayEntryCustSub = _d;
        genePair = new GeneComponentModelSimplePair<K,V>(_core,_k,_v,_txtKey,_txtValue);
        initForm();
        Comparing<EditedCrudPair<K, V>> cmp_ = _d.buildCmp();
        initForm(_d.buildDisplay(), genePair, cmp_,_facto.build(cmp_));
    }
    public void initForm(DisplayEntryCust<Integer,EditedCrudPair<K, V>> _d, Comparing<EditedCrudPair<K, V>> _disp, AbstractProgramInfos _core, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v) {
        getCrudGeneFormSubContent().clear();
        displayEntryCustSub = null;
        genePair = new GeneComponentModelSimplePair<K,V>(_core,_k,_v);
        initForm();
        initForm(_d, genePair, _disp,new ValidateElementPair<K, V>(_disp));
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        if (displayEntryCustSub != null) {
            ids_.addAllElts(displayEntryCustSub.buildSub());
            ids_.add(new SubscribedTranslationPkKey<EditedCrudPair<K, V>>(this));
        }
        return ids_;
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(genePair.all());
        return all_;
    }
    public AbsGeneComponentModelSubscribe<K> getKey() {
        return genePair.getKey();
    }
    public AbsGeneComponentModelSubscribe<V> getValue() {
        return genePair.getValue();
    }
    public GeneComponentModelSimplePair<K, V> getGenePair() {
        return genePair;
    }
}
