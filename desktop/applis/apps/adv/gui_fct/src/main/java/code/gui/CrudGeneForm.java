package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.EntryCust;
import code.util.TreeMap;
import code.util.ints.Comparing;

public final class CrudGeneForm<K,V> extends AbsCrudGeneForm {
    private DisplayEntryCust<K,V> displayEntry;
    private GeneComponentModel<K> geneKey;
    private GeneComponentModel<V> geneValue;
    private AbsMap<K,V> list;

    public CrudGeneForm(AbstractProgramInfos _fact, Comparing<K> _cmp) {
        super(_fact);
        list = new TreeMap<K, V>(_cmp);
    }

    public void initForm(AbsCommonFrame _fr,DisplayEntryCust<K,V> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, Comparing<K> _cmp, AbsMap<K,V> _map) {
        geneKey = _k;
        geneValue = _v;
        displayEntry = _disp;
        list = new TreeMap<K, V>(_cmp);
        list.putAllMap(_map);
        initForm(_fr);
    }

    @Override
    protected int size() {
        return list.size();
    }

    @Override
    public String display(int _index) {
        EntryCust<K, V> e_ = list.getEntry(_index);
        return displayEntry.display(e_.getKey(), e_.getValue());
    }

    public void select(int _index) {
        EntryCust<K, V> e_ = list.getEntry(_index);
        getElement().removeAll();
        getElement().add(geneKey.gene(e_.getKey()));
        getElement().add(geneValue.gene(e_.getValue()));
        selectOrAdd();
    }

    public void formAdd() {
        getElement().removeAll();
        getElement().add(geneKey.gene());
        getElement().add(geneValue.gene());
        selectOrAdd();
    }
    public void validAddEdit() {
        list.put(geneKey.value(), geneValue.value());
        afterModif();
    }
    public void validRemove() {
        list.removeKey(geneKey.value());
        afterModif();
    }


    public GeneComponentModel<K> getGeneKey() {
        return geneKey;
    }

    public GeneComponentModel<V> getGeneValue() {
        return geneValue;
    }


    public AbsMap<K, V> getList() {
        return list;
    }

}
