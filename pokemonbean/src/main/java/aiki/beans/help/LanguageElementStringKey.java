package aiki.beans.help;
import code.util.StringList;
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
        if (!StringList.quickEq(language, _g.language)) {
            return false;
        }
        if (!StringList.quickEq(key, _g.key)) {
            return false;
        }
        return true;
    }

    public String getLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

}