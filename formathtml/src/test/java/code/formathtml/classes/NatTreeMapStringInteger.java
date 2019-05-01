package code.formathtml.classes;

import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;
import code.util.ints.SortableMap;

public final class NatTreeMapStringInteger implements SortableMap<String,Integer> {

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

    @Override
    public EntryCust<String, Integer> lowerEntry(String _key) {
        return tree.lowerEntry(_key);
    }

    @Override
    public String lowerKey(String _key) {
        return tree.lowerKey(_key);
    }

    @Override
    public EntryCust<String, Integer> floorEntry(String _key) {
        return tree.floorEntry(_key);
    }

    @Override
    public String floorKey(String _key) {
        return tree.floorKey(_key);
    }

    @Override
    public EntryCust<String, Integer> ceilingEntry(String _key) {
        return tree.ceilingEntry(_key);
    }

    @Override
    public String ceilingKey(String _key) {
        return tree.ceilingKey(_key);
    }

    @Override
    public EntryCust<String, Integer> higherEntry(String _key) {
        return tree.higherEntry(_key);
    }

    @Override
    public String higherKey(String _key) {
        return tree.higherKey(_key);
    }

    @Override
    public EntryCust<String, Integer> firstEntry() {
        return tree.firstEntry();
    }

    @Override
    public EntryCust<String, Integer> lastEntry() {
        return tree.lastEntry();
    }

    public void applyChanges() {
        tree.applyChanges();
    }

}
