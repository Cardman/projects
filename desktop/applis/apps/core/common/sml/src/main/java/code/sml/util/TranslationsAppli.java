package code.sml.util;

import code.util.CollCapacity;
import code.util.StringMap;

public final class TranslationsAppli {
    private final StringMap<TranslationsFile> mapping;
    public TranslationsAppli() {
        mapping = new StringMap<TranslationsFile>();
    }
    public TranslationsAppli(int _cap) {
        mapping = new StringMap<TranslationsFile>(new CollCapacity(_cap));
    }
    public StringMap<TranslationsFile> getMapping() {
        return mapping;
    }
}
