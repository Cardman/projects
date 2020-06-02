package code.util;
import code.util.ints.Comparing;

/**
    @author Cardman
*/

public final class TreeMap<K, V> extends AbsMap<K, V> {

    private final Comparing<K> comparator;

    public TreeMap(Comparing<K> _cmp) {
        comparator = _cmp;
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
            int res_ = comparator.compare(_key, c_.getKey());
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
    protected int indexOfEntry(K _key) {
        int index_ = CustList.FIRST_INDEX;
        K key_ = _key;
        for (EntryCust<K, V> e:getList()) {
            int res_ = comparator.compare(key_, e.getKey());
            if (res_ == CustList.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public Comparing<K> comparator() {
        return comparator;
    }


}
