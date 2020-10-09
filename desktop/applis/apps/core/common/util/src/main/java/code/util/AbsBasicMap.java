package code.util;

import code.util.core.IndexConstants;

public abstract class AbsBasicMap<K,V> extends AbsMap<K, V> {

    protected AbsBasicMap() {

    }
    protected AbsBasicMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public int indexOfEntry(K _key) {
        int index_ = IndexConstants.FIRST_INDEX;
        for (EntryCust<K, V> e:getList()) {
            K k_ = e.getKey();
            if (matchKeys(_key, k_)) {
                return index_;
            }
            index_++;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    protected abstract boolean matchKeys(K _one,K _two);
}
