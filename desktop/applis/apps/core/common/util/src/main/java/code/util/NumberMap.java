package code.util;

public abstract class NumberMap<K, V> extends AbsMap<K, V> {

    public NumberMap() {
    }
    
    public NumberMap(CollCapacity _capacity) {
        super(_capacity);
    }

    @Override
    protected int indexOfEntry(K _key) {
        long convert_ = convert(_key);
        int s_ = size();
        for (int i = 0; i < s_;i++) {
            if (eqLoc(i,convert_)) {
                return i;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    private boolean eqLoc(int _keyIndex, long _converted) {
        return convert(getKey(_keyIndex)) == _converted;
    }
    abstract long convert(K _key);
}
