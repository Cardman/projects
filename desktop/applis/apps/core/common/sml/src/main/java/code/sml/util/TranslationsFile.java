package code.sml.util;

import code.util.CollCapacity;
import code.util.EntryCust;
import code.util.StringMap;

public final class TranslationsFile {
    private final StringMap<String> mapping;
    public TranslationsFile() {
        mapping = new StringMap<String>();
    }
    public TranslationsFile(int _cap) {
        mapping = new StringMap<String>(new CollCapacity(_cap));
    }

    public static StringMap<String> extractMap(TranslationsFile _file) {
        StringMap<String> m_ = new StringMap<String>();
        for (String v:_file.getMapping().values()) {
            int sep_ = v.indexOf('=');
            m_.addEntry(v.substring(0,sep_),v.substring(sep_+1));
        }
        return m_;
    }

    public static StringMap<String> extractKeys(TranslationsFile _file) {
        StringMap<String> m_ = new StringMap<String>();
        for (EntryCust<String, String> v:_file.getMapping().entryList()) {
            int sep_ = v.getValue().indexOf('=');
            m_.addEntry(v.getKey(),v.getValue().substring(0,sep_));
        }
        return m_;
    }

    public void add(String _k, String _v) {
        getMapping().addEntry(_k, _v);
    }
    public StringMap<String> getMapping() {
        return mapping;
    }
}
