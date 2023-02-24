package code.sml.util;

import code.util.StringMap;

public final class TranslationsFile {
    private final StringMap<String> mapping = new StringMap<String>();

    public void add(String _k, String _v) {
        getMapping().addEntry(_k, _v);
    }
    public StringMap<String> getMapping() {
        return mapping;
    }
}
