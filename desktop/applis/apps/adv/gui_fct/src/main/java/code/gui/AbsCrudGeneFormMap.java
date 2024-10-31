package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.TreeMap;
import code.util.ints.Comparing;

public abstract class AbsCrudGeneFormMap<K,V> extends AbsCrudGeneForm {
    private DisplayEntryCust<K,V> displayEntry;
    private GeneComponentModel<K> geneKey;
    private GeneComponentModel<V> geneValue;
    private AbsMap<K,V> list;
    private int selectedIndex = -1;

    protected AbsCrudGeneFormMap(AbstractProgramInfos _fact) {
        super(_fact);
        list = new IdMap<K, V>();
    }

    public void initForm(AbsCommonFrame _fr,DisplayEntryCust<K,V> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, Comparing<K> _cmp, AbsMap<K,V> _map) {
        setGeneKey(_k);
        geneValue = _v;
        setDisplayEntry(_disp);
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
        K key_;
        V value_;
        if (getSelectedIndex() < 0) {
            key_ = geneKey.value();
            if (invalidKey(key_)) {
                return;
            }
            value_ = geneValue.value();
            list.put(key_, value_);
        } else {
            key_ = list.getKey(getSelectedIndex());
            value_ = geneValue.value();
            list.setValue(getSelectedIndex(), value_);
        }
        afterModif(-1, key_, value_);
    }

    protected boolean invalidKey(K _key) {
        return list.contains(_key);
    }

    public void validRemove() {
        K key_ = list.getKey(getSelectedIndex());
        V value_ = list.getValue(getSelectedIndex());
        afterModif(getSelectedIndex(), key_, value_);
    }
    protected abstract void afterModif(int _index, K _key, V _value);

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public GeneComponentModel<K> getGeneKey() {
        return geneKey;
    }

    public void setGeneKey(GeneComponentModel<K> _g) {
        this.geneKey = _g;
    }

    public GeneComponentModel<V> getGeneValue() {
        return geneValue;
    }


    public void setDisplayEntry(DisplayEntryCust<K, V> _d) {
        this.displayEntry = _d;
    }

    public AbsMap<K, V> getList() {
        return list;
    }
}
