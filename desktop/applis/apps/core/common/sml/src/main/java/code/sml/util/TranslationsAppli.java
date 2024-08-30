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
    public TranslationsFile val() {
        return getMapping().getVal(Translations.FILES_PATH);
    }
    public void sys(TranslationsFile _tf) {
        getMapping().addEntry(Translations.FILES_PATH,_tf);
    }
    public StringMap<TranslationsFile> getMapping() {
        return mapping;
    }
}
