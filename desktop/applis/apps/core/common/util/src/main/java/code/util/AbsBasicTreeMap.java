package code.util;

import code.util.core.IndexConstants;
import code.util.core.SortConstants;

public abstract class AbsBasicTreeMap<K,V> extends AbsMap<K,V> {

    @Override
    public void put(K _key, V _value) {
        int index_ = 0;
        while (true) {
            if (index_ >= getList().size()) {
                getList().add(new EntryCust<K, V>(_key, _value));
                return;
            }
            EntryCust<K, V> c_ = getList().get(index_);
            int res_ = compare(_key, c_.getKey());
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
    public int indexOfEntry(K _key) {
        int index_ = IndexConstants.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            int res_ = compare(_key, e.getKey());
            if (res_ == SortConstants.EQ_CMP) {
                return index_;
            }
            index_++;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    protected abstract int compare(K _one, K _two);
}
