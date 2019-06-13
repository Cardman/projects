package code.util;

/**
    @author Cardman
*/
public final class NatTreeMap<K extends Number, V> extends AbsMap<K, V>  {

    public NatTreeMap() {
    }
    
    public NatTreeMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public CustList<K> getKeys() {
        CustList<K> s_ = new CustList<K>();
        for (EntryCust<K, V> e: getList()) {
            s_.add(e.getKey());
        }
        return s_;
    }

    @Override
    public void put(K _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = Numbers.compareLg(_key.longValue(),c_.getKey().longValue());
            if (res_ < 0) {
                getList().add(index_, new EntryCust<K, V>(_key, _value));
                return;
            }
            if (res_ == 0) {
                setValue(index_, _value);
                return;
            }
            index_++;
        }
    }

    @Override
    int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            Number c_ = _key;
            int res_ = Numbers.compareLg(c_.longValue(),e.getKey().longValue());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

}
