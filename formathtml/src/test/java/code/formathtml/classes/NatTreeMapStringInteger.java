package code.formathtml.classes;

import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;

public final class NatTreeMapStringInteger implements ListableEntries<String,Integer> {

    private final NatStringTreeMap<Integer> tree;

    public NatTreeMapStringInteger() {
        tree = new NatStringTreeMap<Integer>();
    }

    @Override
    public final SimpleIterable entries() {
        return tree.entries();
    }

    @Override
    public EntryCust<String,Integer> get(int _index) {
        return tree.get(_index);
    }

    @Override
    public void putAllMap(ListableEntries<String, Integer> _m) {
        tree.putAllMap(_m);
    }

    @Override
    public final Iterable<EntryCust<String, Integer>> entryList() {
        return tree.entryList();
    }

    public void setKey(int _i, String _k) {
        tree.setKey(_i, _k);
    }

    @Override
    public void setValue(int _i, Integer _object) {
        tree.setValue(_i, _object);
    }

    @Override
    public Integer getValue(int _index) {
        return tree.getValue(_index);
    }

    @Override
    public String getKey(int _index) {
        return tree.getKey(_index);
    }

    @Override
    public CustList<String> getKeys() {
        return tree.getKeys();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public void put(String _key, Integer _value) {
        tree.put(_key, _value);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean contains(String _key) {
        return tree.contains(_key);
    }

    @Override
    public Integer getVal(String _key) {
        return tree.getVal(_key);
    }

    @Override
    public void move(String _oldKey, String _newKey) {
        tree.move(_oldKey, _newKey);
    }

    @Override
    public CustList<Integer> values() {
        return tree.values();
    }

    @Override
    public void removeKey(String _key) {
        tree.removeKey(_key);
    }

    void applyChanges() {
        for (int i = CustList.FIRST_INDEX; i < tree.size(); i++) {
            for (int j = i + 1; j < tree.size(); j++) {
                String c_ = tree.get(i).getKey();
                int res_ = c_.compareTo(tree.get(j).getKey());
                if (res_ > CustList.EQ_CMP) {
                    EntryCust<String, Integer> e_ = tree.get(i);
                    EntryCust<String, Integer> f_ = tree.get(j);
                    tree.setKey(j,e_.getKey());
                    tree.setKey(i,f_.getKey());
                    tree.setValue(j, e_.getValue());
                    tree.setValue(i, f_.getValue());
                }
            }
        }
    }

}
