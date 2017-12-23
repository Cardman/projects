package aiki.beans.help;
import code.util.StringList;
import code.util.ints.Equallable;

public final class LanguageElementKey implements Equallable<LanguageElementKey> {

    private final String language;

    private final Object key;

    public LanguageElementKey(String _language, Object _key) {
        language = _language;
        key = _key;
    }

    @Override
    public boolean eq(LanguageElementKey _g) {
        if (!StringList.quickEq(language, _g.language)) {
            return false;
        }
        if (key != _g.key) {
            return false;
        }
        return true;
    }

    public String getLanguage() {
        return language;
    }

    public Object getKey() {
        return key;
    }

}
