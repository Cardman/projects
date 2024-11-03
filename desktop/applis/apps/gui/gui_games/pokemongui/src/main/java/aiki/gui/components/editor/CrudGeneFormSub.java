package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class CrudGeneFormSub<K,V> extends AbsCrudGeneFormMap<K, V> implements CrudGeneFormSubUp {
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

    protected void afterChange() {
        getCrudGeneFormSubContent().afterChange(this);
    }

    @Override
    public void subscribeAll(IdList<SubscribedTranslation> _sub) {
        getCrudGeneFormSubContent().addAllSub(_sub);
        getCrudGeneFormSubContent().addSub(new SubscribedTranslationPkKey(this));
    }
}
