package code.util;

import code.util.core.IndexConstants;

public abstract class NumberMap<K, V> extends AbsMap<K, V> {

    protected NumberMap() {
    }

    protected NumberMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    public int indexOfEntry(K _key, int _from) {
        long convert_ = convert(_key);
        int s_ = size();
        for (int i = _from; i < s_;i++) {
            if (convert(getKey(i)) == convert_) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    abstract long convert(K _key);
}
