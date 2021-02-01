package code.util.ints;

import code.util.SimpleItr;

public interface SimpleIterable {

    SimpleItr simpleIterator();

}
package code.util.ints;
import code.util.CustList;

public interface Listable<T> extends Iterable<T>,SimpleIterable , Countable {

    Iterable<T> getList();
    CustList<T> getReverse();

    void add(T _e);

    void addAllElts(Listable<T> _c);

    void clear();

    boolean isValidIndex(int _index);

    void set(int _index, T _element);

    void add(int _index, T _element);

    void remove(int _index);

    T first();

    T last();

    CustList<T> sub(int _from, int _to);

    void swapIndexes(int _i, int _j);
}
package code.util;
import code.util.core.IndexConstants;
import code.util.ints.ListableEntries;
import code.util.ints.SimpleIterable;



public abstract class AbsMap<K, V> implements ListableEntries<K, V> {

    //list cannot be null, even by reflection
    private final CustList<EntryCust<K,V>> list;

    protected AbsMap() {
        list = new CustList<EntryCust<K,V>>();
    }
    protected AbsMap(CollCapacity _capacity) {
        list = new CustList<EntryCust<K,V>>(_capacity);
    }

    @Override
    public final SimpleIterable entries() {
        return new CustList<EntryCust<K,V>>(getList());
    }

    @Override
    public final Iterable<EntryCust<K, V>> entryList() {
        return new CustList<EntryCust<K,V>>(getList());
    }

    @Override
    public CustList<K> getKeys() {
        CustList<K> l_ = new CustList<K>();
        for (EntryCust<K,V> e: list) {
            l_.add(e.getKey());
        }
        return l_;
    }

    public boolean containsAllAsKeys(CustList<K> _elements) {
        for (K k: _elements) {
            if (!contains(k)) {
                return false;
            }
        }
        return true;
    }

    public K firstKey() {
        return getList().first().getKey();
    }

    public K lastKey() {
        return getList().last().getKey();
    }

    public V firstValue() {
        return getList().first().getValue();
    }

    public V lastValue() {
        return getList().last().getValue();
    }

    protected final CustList<EntryCust<K, V>> getList() {
        return list;
    }

    public void setKey(int _i, K _k) {
        EntryCust<K,V> bk_ = list.get(_i);
        list.set(_i, new EntryCust<K,V>(_k, bk_.getValue()));
    }

    @Override
    public K getKey(int _i) {
        return list.get(_i).getKey();
    }

    @Override
    public V getValue(int _i) {
        return list.get(_i).getValue();
    }

    @Override
    public void setValue(int _i, V _object) {
        list.get(_i).setValue(_object);
    }

    public void tryAdd(K _key, V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        addEntry(_key,_v);
    }
    public void set(K _key,V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        setValue(index_, _v);
    }
    @Override
    public void clear() {
        getList().clear();
    }
    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public int size() {
        return getList().size();
    }
    public EntryCust<K,V> getEntryByKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    @Override
    public void put(K _key, V _v) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            addEntry(_key, _v);
            return;
        }
        setValue(index_, _v);
    }
    @Override
    public boolean contains(K _key) {
        return getEntryByKey(_key) != null;
    }
    @Override
    public V getVal(K _key) {
        EntryCust<K,V> e_ = getEntryByKey(_key);
        if (e_ == null) {
            return null;
        }
        return e_.getValue();
    }
    @Override
    public void move(K _oldKey, K _newKey) {
        int size_ = size();
        V value_ = getVal(_oldKey);
        removeKey(_oldKey);
        if (size_ != size()) {
            put(_newKey, value_);
        }
    }

    @Override
    public CustList<V> values() {
        CustList<V> s_ = new CustList<V>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getValue());
        }
        return s_;
    }

    @Override
    public void putAllMap(ListableEntries<K, V> _m) {
        for (EntryCust<K,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }
    public void addAllEntries(ListableEntries<K, V> _m) {
        for (EntryCust<K,V> e: _m.entryList()) {
            addEntry(e.getKey(), e.getValue());
        }
    }
    @Override
    public void removeKey(K _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        getList().remove(index_);
    }

    public boolean isValidIndex(int _index) {
        return getList().isValidIndex(_index);
    }

    public abstract int indexOfEntry(K _key);

    public void addEntry(K _k, V _v) {
        list.add(new EntryCust<K, V>(_k, _v));
    }
}


package code.util.ints;

public interface SimpleEntries {

    SimpleIterable entries();

}


package code.util;


public final class SimpleItr {

    private Object[] list;
    private int index;
    private int length;

    public SimpleItr(Object[] _list) {
        list = _list;
        length = _list.length;
    }

    public boolean hasNext() {
        return index < length;
    }

    public Object next() {
        Object element_ = list[index];
        index++;
        return element_;
    }


}
package code.util.ints;

public interface SimpleEntry {

    Object getSimpleKey();
    Object getSimpleValue();
}

package code.util;

import code.util.ints.SimpleEntry;


public final class EntryCust<K, V> implements SimpleEntry {

    private K key;

    private V value;

    EntryCust(K _k, V _v) {
        key = _k;
        value = _v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V _v) {
        value = _v;
    }

    @Override
    public Object getSimpleKey() {
        return getKey();
    }

    @Override
    public Object getSimpleValue() {
        return getValue();
    }
}
