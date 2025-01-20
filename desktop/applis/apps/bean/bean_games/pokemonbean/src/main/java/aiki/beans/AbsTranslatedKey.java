package aiki.beans;

public abstract class AbsTranslatedKey {
    private final TranslatedKey key;

    protected AbsTranslatedKey(String _k, String _t) {
        this.key = new TranslatedKey(_k,_t);
    }

    public TranslatedKey getKey() {
        return key;
    }
}
