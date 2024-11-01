package aiki.gui.components.editor;

import aiki.comparators.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class CrudGeneFormSub<K,V> extends AbsCrudGeneFormMap<K, V> {
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscription;
    private final IdList<SubscribedTranslation> subscribedTranslations;
    private final IdList<SubscribedTranslation> subscribedTranslationsForm;

    protected CrudGeneFormSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact);
        facadeGame = _facade;
        subscription = _sub;
        subscribedTranslations = _sub.getSubscribedTranslations();
        subscribedTranslationsForm = new IdList<SubscribedTranslation>();

    }

    public void initForm(AbsMap<K, String> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, AbsMap<K, V> _map) {
        initForm(new DisplayKeyOnly<K, V>(_disp),_k,_v,new ComparatorTr<K>(_disp),_map);
    }
    public void clear() {
        subscribedTranslationsForm.clear();
    }

    public SubscribedTranslationList getSubscription() {
        return subscription;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    protected abstract IdList<SubscribedTranslation> all();

    @Override
    public void selectOrAdd() {
        for (SubscribedTranslation s: all()) {
            subscribedTranslations.add(s);
            subscribedTranslationsForm.add(s);
        }
        super.selectOrAdd();
    }
    @Override
    public void cancel() {
        removeOpenSub();
        super.cancel();
    }

    protected void removeOpenSub() {
        subscribedTranslations.removeAllElements(subscribedTranslationsForm);
    }

    protected void clearSub() {
        subscribedTranslationsForm.clear();
    }
    protected void addAllSub(IdList<SubscribedTranslation> _sub) {
        for (SubscribedTranslation s:_sub) {
            addSub(s);
        }
    }
    protected void addSub(SubscribedTranslation _sub) {
        subscribedTranslations.add(_sub);
        subscribedTranslationsForm.add(_sub);
    }

    protected abstract void afterChange();

    protected void subscribeAll() {
        addAllSub(subscribe());
        addSub(new SubscribedTranslationPkKey<K, V>(this));
    }
    protected abstract IdList<SubscribedTranslation> subscribe();
    public abstract void updateDisplayEntry(AbstractProgramInfos _api, FacadeGame _facade);
}
