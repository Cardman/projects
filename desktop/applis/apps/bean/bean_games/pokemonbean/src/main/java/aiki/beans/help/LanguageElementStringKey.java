package aiki.beans.help;
import code.util.core.StringUtil;
import code.util.ints.Equallable;

public final class LanguageElementStringKey implements Equallable<LanguageElementStringKey> {

    private final String language;

    private final String key;

    public LanguageElementStringKey(String _language, String _key) {
        language = _language;
        key = _key;
    }

    @Override
    public boolean eq(LanguageElementStringKey _g) {
        if (!StringUtil.quickEq(language, _g.language)) {
            return false;
        }
        return StringUtil.quickEq(key, _g.key);
    }

    public String getLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

}