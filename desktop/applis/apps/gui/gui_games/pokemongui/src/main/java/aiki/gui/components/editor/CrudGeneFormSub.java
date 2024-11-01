package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class CrudGeneFormSub<K,V> extends AbsCrudGeneFormMap<K, V> {
    private final CrudGeneFormSubContent crudGeneFormSubContent;

    protected CrudGeneFormSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub);

    }

    public void initForm(AbsMap<K, String> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, AbsMap<K, V> _map) {
        initForm(new DisplayKeyOnly<K, V>(_disp),_k,_v,new ComparatorTr<K>(_disp),_map);
    }

    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }

    protected abstract IdList<SubscribedTranslation> all();

    @Override
    public void selectOrAdd() {
        getCrudGeneFormSubContent().selectOrAdd(all());
        super.selectOrAdd();
    }
    @Override
    public void cancel() {
        getCrudGeneFormSubContent().removeOpenSub();
        super.cancel();
    }

    protected abstract void afterChange();

    protected void subscribeAll() {
        getCrudGeneFormSubContent().addAllSub(subscribe());
        getCrudGeneFormSubContent().addSub(new SubscribedTranslationPkKey<K, V>(this));
    }
    protected abstract IdList<SubscribedTranslation> subscribe();
    public abstract void updateDisplayEntry(AbstractProgramInfos _api, FacadeGame _facade);
}
