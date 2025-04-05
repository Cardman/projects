package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSimplePair<K,V> implements GeneComponentModel<EditedCrudPair<K,V>> {
    private final AbstractProgramInfos factory;
    private final AbsGeneComponentModelSubscribeFactory<K> keys;
    private final AbsGeneComponentModelSubscribeFactory<V> values;
    private AbsGeneComponentModelSubscribe<K> key;
    private AbsGeneComponentModelSubscribe<V> value;
    private final String textIntroKey;
    private final String textIntroValue;

    public GeneComponentModelSimplePair(AbstractProgramInfos _api, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v) {
        this(_api,_k,_v,"","");
    }

    public GeneComponentModelSimplePair(AbstractProgramInfos _api, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v, String _txtKey, String _txtValue) {
        this.factory = _api;
        this.keys = _k;
        this.values = _v;
        this.textIntroKey = _txtKey;
        this.textIntroValue = _txtValue;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        AbsPanel page_ = factory.getCompoFactory().newPageBox();
        key = keys.build();
        value = values.build();
        page_.add(SubscribedTranslationList.lineDir(factory,textIntroKey,key.geneEnum(_select,0)));
        page_.add(SubscribedTranslationList.lineDir(factory,textIntroValue,value.geneEnum(_select,1)));
        return page_;
    }

    @Override
    public EditedCrudPair<K, V> value() {
        return new EditedCrudPair<K, V>(key.tryRet(), value.tryRet());
    }

    @Override
    public void value(EditedCrudPair<K, V> _v) {
        key.setupValue(_v.getKey());
        value.setupValue(_v.getValue());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(key.getSubs());
        ids_.addAllElts(value.getSubs());
        return ids_;
    }

    public AbsGeneComponentModelSubscribe<K> getKey() {
        return key;
    }

    public AbsGeneComponentModelSubscribe<V> getValue() {
        return value;
    }
}
