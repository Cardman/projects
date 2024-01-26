package code.sml.util;

import code.util.StringMap;

public final class TranslationsLg {
    private final StringMap<TranslationsAppli> mapping = new StringMap<TranslationsAppli>();

    private final String key;
    public TranslationsLg() {
        this("");
    }
    public TranslationsLg(String _k) {
        key = _k;
    }

    public String getKey() {
        return key;
    }

    public StringMap<TranslationsAppli> getMapping() {
        return mapping;
    }
}
