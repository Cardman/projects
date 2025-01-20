package aiki.beans;


public final class TranslatedKey {
    private final String key;
    private final String translation;

    public TranslatedKey(String _k, String _t) {
        this.key = _k;
        translation = _t;
    }

    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return translation;
    }
}
