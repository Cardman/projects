package code.sml.util;

import code.util.StringMap;

public final class TranslationsLg {
    private final StringMap<TranslationsAppli> mapping = new StringMap<TranslationsAppli>();

    public StringMap<TranslationsAppli> getMapping() {
        return mapping;
    }
}
