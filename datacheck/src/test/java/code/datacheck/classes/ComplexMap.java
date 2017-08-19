package code.datacheck.classes;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class ComplexMap<K, V, T> extends AbMap<K, T> {

    private AbsMap<K, T> map;

    private Listable<V> values;

    private String field;

    private V value;

    public ComplexMap(AbsMap<K, T> _map) {
        map = _map;
    }

//    public ComplexMap(ListableEntries<? extends K, ? extends T> _arg0) {
//        map = new Map<K, T>(_arg0);
//    }

    public Listable<V> getValues() {
        return values;
    }
    public void setValues(Listable<? extends V> _values) {
        values = new CustList<V>(_values);
    }

    public String getField() {
        return field;
    }

    public void setField(String _field) {
        field = _field;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V _value) {
        value = _value;
    }

//    public Map<T, List<K>> reverseMap() {
//        return map.reverseMap();
//    }

    @Override
    public Listable<EntryCust<K, T>> entryList() {
        return map.entryList();
    }

//    @Override
//    public T get(Object _key) {
//        return map.get(_key);
//    }

    @Override
    public Listable<T> getValues(K _key) {
        return map.getValues(_key);
    }

    @Override
    public T getVal(K _key) {
        return map.getVal(_key);
    }

    @Override
    public boolean contains(K _key) {
        return map.contains(_key);
    }

//    public boolean has(T _value) {
//        return map.has(_value);
//    }

    @Override
    public Listable<T> values() {
        return map.values();
    }

    public void add(K _key, T _v) {
        map.add(_key, _v);
    }

    public void set(K _key, T _v) {
        map.set(_key, _v);
    }

    @Override
    public void move(K _oldKey, K _newKey) {
        map.move(_oldKey, _newKey);
    }

    @Override
    public void put(K _key, T _v) {
        map.put(_key, _v);
    }

    @Override
    public Listable<K> getKeysNullValue() {
        return map.getKeysNullValue();
    }

    @Override
    public void removeKey(K _key) {
        map.removeKey(_key);
    }

//    public T removedKey(K _key) {
//        return map.removedKey(_key);
//    }

    @Override
    public Listable<K> getKeys() {
        return map.getKeys();
    }

//    public List<K> getKeys(T _value) {
//        return map.getKeys(_value);
//    }

//    @Override
//    public void remove(Object _key) {
//        map.remove(_key);
//    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void putAllMap(ListableEntries<? extends K, ? extends T> _m) {
        map.putAllMap(_m);
    }

//    public void setModified() {
//        map.setModified();
//    }
//
//    public void setUnmodified() {
//        map.setUnmodified();
//    }
//
//    public boolean isModified() {
//        return map.isModified();
//    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

//    @Override
//    public boolean containsKey(Object _key) {
//        return map.containsKey(_key);
//    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean isCorrect() {
        return map.isCorrect();
    }

//    @Override
//    public boolean containsValue(Object _value) {
//        return map.containsValue(_value);
//    }
}
