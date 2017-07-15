package aiki.beans.help;
import code.util.StringList;
import code.util.ints.Equallable;

public final class LanguageElementKey<E extends Enum<E>> implements Equallable<LanguageElementKey<E>> {

    private final String language;

    private final E key;

    public LanguageElementKey(String _language, E _key) {
        language = _language;
        key = _key;
    }

    @Override
    public boolean eq(LanguageElementKey<E> _g) {
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

    public E getKey() {
        return key;
    }

}
