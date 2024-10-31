package code.gui;

import code.gui.initialize.*;
import code.util.AbsMap;
import code.util.EntryCust;
import code.util.TreeMap;
import code.util.ints.*;

public final class CrudGeneForm<K,V> extends AbsCrudGeneForm {
    private DisplayEntryCust<K,V> displayEntry;
    private GeneComponentModel<K> geneKey;
    private GeneComponentModel<V> geneValue;
    private AbsMap<K,V> list;
    private int selectedIndex = -1;

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
        selectedIndex = _index;
        EntryCust<K, V> e_ = list.getEntry(_index);
        getElement().removeAll();
        AbsCustComponent key_ = geneKey.gene();
        geneKey.value(e_.getKey());
        AbsCustComponent value_ = geneValue.gene();
        geneValue.value(e_.getValue());
        getElement().add(key_);
        getElement().add(value_);
        selectOrAdd();
    }

    public void formAdd() {
        selectedIndex = -1;
        getElement().removeAll();
        getElement().add(geneKey.gene());
        getElement().add(geneValue.gene());
        selectOrAdd();
    }
    public void validAddEdit() {
        if (selectedIndex < 0) {
            K key_ = geneKey.value();
            if (list.contains(key_)) {
                return;
            }
            list.put(key_, geneValue.value());
        } else {
            list.setValue(selectedIndex, geneValue.value());
        }
        afterModif();
    }
    public void validRemove() {
        list.remove(selectedIndex);
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
