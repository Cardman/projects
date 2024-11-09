package aiki.comparators;

import code.util.*;

public final class ComparatorTrWrapper<T> {
    public ComparatorTr<T,T> wrap(AbsMap<T,String> _map) {
        return new ComparatorTr<T,T>(_map, new IdRetriever<T>());
    }
}
