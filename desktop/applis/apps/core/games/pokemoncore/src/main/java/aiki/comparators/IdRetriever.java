package aiki.comparators;

public final class IdRetriever<K> implements IntRetriever<K,K> {
    @Override
    public K retrieve(K _elt) {
        return _elt;
    }
}
