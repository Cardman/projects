package aiki.beans.help;

public final class LanguageElementStringKey {

    private final String language;

    private final String key;

    public LanguageElementStringKey(String _language, String _key) {
        language = _language;
        key = _key;
    }

    public String getLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

}