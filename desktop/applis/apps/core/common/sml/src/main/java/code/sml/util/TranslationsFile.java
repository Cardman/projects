package code.sml.util;

import code.util.StringMap;

public final class TranslationsFile {
    private final StringMap<String> mapping = new StringMap<String>();

    public StringMap<String> getMapping() {
        return mapping;
    }
}
