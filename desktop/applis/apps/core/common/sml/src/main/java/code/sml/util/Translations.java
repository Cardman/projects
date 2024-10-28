package code.sml.util;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class Translations {
    public static final String FILES_PATH = "0";
    public static final String TEMP_FOLDER = "0";
    private final StringMap<TranslationsLg> mapping = new StringMap<TranslationsLg>();
    private final StringMap<TranslationsAppli> files = new StringMap<TranslationsAppli>();
    private final StringMap<String> indexes = new StringMap<String>();

    public StringMap<TranslationsAppli> byAppl(String _appl) {
        StringMap<TranslationsAppli> m_ = new StringMap<TranslationsAppli>();
        for (EntryCust<String,TranslationsLg> e: mapping.entryList()) {
            for (EntryCust<String, TranslationsAppli> f: e.getValue().getMapping().entryList()) {
                if (StringUtil.quickEq(f.getKey(), _appl)) {
                    m_.addEntry(e.getKey(),f.getValue());
                }
            }
        }
        return m_;
    }
    public StringMap<TranslationsLg> getMapping() {
        return mapping;
    }

    public StringMap<TranslationsAppli> getFiles() {
        return files;
    }

    public StringMap<String> getIndexes() {
        return indexes;
    }
}
