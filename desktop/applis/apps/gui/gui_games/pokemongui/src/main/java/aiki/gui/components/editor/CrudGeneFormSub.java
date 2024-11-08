package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class CrudGeneFormSub<K,V> extends AbsCrudGeneFormMap<K, V> {
    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private AbsMap<K, String> messages;

    protected CrudGeneFormSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);

    }

    public void initForm(AbsMap<K, String> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, AbsMap<K, V> _map) {
        initForm(new DisplayKeyOnly<K, V>(_disp),_k,_v,new ComparatorTr<K>(_disp),_map);
        messages = _disp;
    }

    public AbsMap<K, String> getMessages() {
        return messages;
    }

    public SubscribedTranslationList subscription() {
        return getCrudGeneFormSubContent().getSubscription();
    }
    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }

    protected abstract IdList<SubscribedTranslation> all();

    @Override
    public void select(int _index) {
        build();
        super.select(_index);
    }

    protected abstract void build();

    @Override
    public void formAdd() {
        build();
        super.formAdd();
    }

    @Override
    public void selectOrAdd() {
        getCrudGeneFormSubContent().selectOrAdd(all());
        super.selectOrAdd();
    }
    @Override
    public void cancel() {
        getCrudGeneFormSubContent().removeOpenSub();
        cancelBase();
    }

    public void cancelBase() {
        super.cancel();
    }

    protected void afterChange() {
        getCrudGeneFormSubContent().afterChange();
    }

}
