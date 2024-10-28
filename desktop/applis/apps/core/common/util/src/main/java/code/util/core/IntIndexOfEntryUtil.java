package code.util.core;

import code.util.CustList;
import code.util.IntMap;
import code.util.Ints;
import code.util.ints.IntIndexOfEntry;

public final class IntIndexOfEntryUtil<K> {
    private final IntIndexOfEntry<K> collection;

    public IntIndexOfEntryUtil(IntIndexOfEntry<K> _c) {
        this.collection = _c;
    }

    public CustList<K> differentKeys() {
        int len_ = collection.size();
        IntMap<K> allIndexes_ = new IntMap<K>();
        for (int i = 0; i < len_; i++) {
            K key_ = collection.getKey(i);
            allIndexes_.tryAdd(valuesKeyIndexes(key_).first(), key_);
        }
        return allIndexes_.values();
    }

    public Ints valuesKeyIndexes(K _key) {
        Ints v_ = new Ints();
        int i_ = 0;
        while (true) {
            int index_ = collection.indexOfEntry(_key, i_);
            if (index_ < 0) {
                break;
            }
            v_.add(index_);
            i_ = index_ + 1;
        }
        return v_;
    }
}
