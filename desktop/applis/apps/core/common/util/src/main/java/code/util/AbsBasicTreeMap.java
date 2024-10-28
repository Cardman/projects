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
            if (res_ < SortConstants.EQ_CMP) {
                getList().add(index_, new EntryCust<K, V>(_key, _value));
                return;
            }
            if (res_ == SortConstants.EQ_CMP) {
                setValue(index_, _value);
                return;
            }
            index_++;
        }
    }

    @Override
    public int indexOfEntry(K _key, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            int res_ = compare(_key, getKey(i));
            if (res_ == SortConstants.EQ_CMP) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    protected abstract int compare(K _one, K _two);
}
