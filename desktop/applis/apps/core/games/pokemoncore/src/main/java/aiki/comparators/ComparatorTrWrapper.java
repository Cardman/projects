package aiki.comparators;

import code.util.*;

public final class ComparatorTrWrapper<T> {
    public ComparatorTr<T> wrap(AbsMap<T,String> _map) {
        return new ComparatorTr<T>(_map);
    }
}
