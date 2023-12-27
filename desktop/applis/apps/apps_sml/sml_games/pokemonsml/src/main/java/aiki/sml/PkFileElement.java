package aiki.sml;

public final class PkFileElement<T> {
    private final String key;
    private final T data;

    public PkFileElement(String _k, T _d) {
        this.key = _k;
        this.data = _d;
    }

    public String getKey() {
        return key;
    }

    public T getData() {
        return data;
    }
}
