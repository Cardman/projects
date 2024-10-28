package code.util;

import code.util.core.IndexConstants;

public abstract class AbsBasicMap<K,V> extends AbsMap<K, V> {

    protected AbsBasicMap() {

    }
    protected AbsBasicMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public int indexOfEntry(K _key, int _from) {
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            K k_ = getKey(i);
            if (matchKeys(_key, k_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    protected abstract boolean matchKeys(K _one,K _two);
}
