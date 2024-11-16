package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSimpleElement<K> implements GeneComponentModel<K> {
    private final AbstractProgramInfos factory;
    private final AbsGeneComponentModelSubscribeFactory<K> keys;
    private AbsGeneComponentModelSubscribe<K> key;

    public GeneComponentModelSimpleElement(AbstractProgramInfos _api, AbsGeneComponentModelSubscribeFactory<K> _k) {
        this.factory = _api;
        this.keys = _k;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        AbsPanel page_ = factory.getCompoFactory().newPageBox();
        key = keys.build();
        page_.add(key.geneEnum(_select,0));
        return page_;
    }

    @Override
    public K value() {
        return key.tryRet();
    }

    @Override
    public void value(K _v) {
        key.setupValue(_v);
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(key.getSubs());
        return ids_;
    }

    public AbsGeneComponentModelSubscribe<K> getKey() {
        return key;
    }

}
