package code.sml.util;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class Translations {
    private final StringMap<TranslationsLg> mapping = new StringMap<TranslationsLg>();

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
}
