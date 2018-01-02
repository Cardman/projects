package code.util;
import code.util.annot.CapacityInit;
import code.util.ints.ListableEntries;




public final class CharMap<V> extends AbsMap<Character,V> {

    //list cannot be null, even by reflection
//    private final transient CustList<EntryCust<Character,V>> list = new CustList<EntryCust<Character,V>>();

    public CharMap() {
    }

    public CharMap(ListableEntries<Character, V> _arg0) {
        putAllMap(_arg0);
    }

    @CapacityInit
    public CharMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public CustList<V> getValues(Character _key) {
        CustList<V> values_ = new CustList<V>();
        if (_key == null) {
            for (EntryCust<Character, V> e:getList()) {
                if (e.getKey() == null) {
                    values_.add(e.getValue());
                }
            }
            return values_;
        }
        for (EntryCust<Character, V> e:getList()) {
            Character key_ = e.getKey();
            if (key_ == null) {
                continue;
            }
            if (_key.charValue() == key_.charValue()) {
                values_.add(e.getValue());
            }
        }
        return values_;
    }

    @Override
    public CustList<V> values() {
        CustList<V> s_ = new CustList<V>();
        for (EntryCust<Character, V> e: getList()) {
            s_.add(e.getValue());
        }
//        return new CustList<>(super.values());
        return s_;
    }

    public void retainKeys(CharList _keys) {
        for (Character k: getKeys()) {
            if (!_keys.containsObj(k)) {
                removeKey(k);
            }
        }
    }

    @Override
    public CharList getKeys() {
        CharList s_ = new CharList();
        for (EntryCust<Character, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }
    @Override
    public CharList getKeysNullValue() {
        CharList list_ = new CharList();
        for (EntryCust<Character, V> e: getList()) {
            if (e.getValue() != null) {
                continue;
            }
            list_.add(e.getKey());
        }
        return list_;
    }

    @Override
    public void putAllMap(ListableEntries<Character, V> _m) {
        //setModified();
        for (EntryCust<Character,V> e: _m.entryList()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    int indexOfEntry(Character _key) {
        int index_ = CustList.FIRST_INDEX;
        if (_key == null) {
            for (EntryCust<Character, V> e:getList()) {
                if (e.getKey() == null) {
                    return index_;
                }
                index_++;
            }
            return CustList.INDEX_NOT_FOUND_ELT;
        }
        for (EntryCust<Character, V> e:getList()) {
            Character key_ = e.getKey();
            if (key_ == null) {
                continue;
            }
            if (_key.charValue() == key_.charValue()) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

//    @Override
//    public boolean isEmpty() {
//        return list.isEmpty();
//    }
//
//    @Override
//    public int size() {
//        return list.size();
//    }
}
