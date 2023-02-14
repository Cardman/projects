package code.sml.util;

import code.util.StringMap;

public final class Translations {
    private final StringMap<TranslationsLg> mapping = new StringMap<TranslationsLg>();

    public StringMap<TranslationsLg> getMapping() {
        return mapping;
    }
}
