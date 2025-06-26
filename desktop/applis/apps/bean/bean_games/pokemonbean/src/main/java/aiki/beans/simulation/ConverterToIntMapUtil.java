package aiki.beans.simulation;

import code.util.*;

public final class ConverterToIntMapUtil<K> {
    public IntMap<String> convert(AbsMap<K,String> _id) {
        IntMap<String> m_ = new IntMap<String>();
        int len_ = _id.size();
        for (int i = 0; i < len_; i++) {
            m_.addEntry(i, _id.getValue(i));
        }
        return m_;
    }
}
