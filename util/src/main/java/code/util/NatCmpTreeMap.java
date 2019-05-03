package code.util;
import code.util.comparators.NatComparator;
import code.util.ints.Cmp;
import code.util.ints.Comparing;

/**
    @author Cardman
*/
public final class NatCmpTreeMap<K extends Cmp<K>, V> extends AbsMap<K, V> {

    public void putAllTreeMap(NatCmpTreeMap<K, V> _m) {
        for (EntryCust<K,V> e: _m.getList()) {
            put(e.getKey(), e.getValue());
        }
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
            int res_ = _key.cmp(c_.getKey());
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
            Cmp<K> c_ = _key;
            int res_ = c_.cmp(e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }


    public Comparing<K> comparator() {
        return new NatComparator<K>();
    }


}
