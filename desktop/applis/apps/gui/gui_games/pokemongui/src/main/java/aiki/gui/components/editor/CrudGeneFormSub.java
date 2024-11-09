package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public abstract class CrudGeneFormSub<K,V> extends AbsCrudGeneFormList<EditedCrudPair<K, V>> {
    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private AbsMap<K, String> messages;

    protected CrudGeneFormSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact,null);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);

    }

    public void initForm(AbsMap<K, String> _disp, GeneComponentModel<EditedCrudPair<K,V>> _v, AbsMap<K, V> _map) {
        Comparing<EditedCrudPair<K, V>> cmp_ = new ComparatorTrWrapperPairs<K, V>().wrap(_disp);
        initForm(new DisplayKeyOnly<K, V>(_disp),_v,new MapToEntriesListUtil<K,V>().build(_map), cmp_,new ValidateElementPair<K, V>(cmp_));
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
