package code.sml.util;

import code.util.StringMap;

public final class TranslationsAppli {
    private final StringMap<TranslationsFile> mapping = new StringMap<TranslationsFile>();

    public StringMap<TranslationsFile> getMapping() {
        return mapping;
    }
}
