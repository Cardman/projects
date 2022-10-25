package code.util.consts;
import code.util.StringList;
import code.util.StringMap;

public final class Constants {

    private Constants() {
    }

    public static StringList getAvailableLanguages() {
        return new StringList(Languages.getLanguages());
    }

    public static String getDisplayLanguage(String _language) {
        return Languages.getDisplayLanguages().getVal(_language);
    }

    public static StringMap<String> getDisplayLanguages() {
        return new StringMap<String>(Languages.getDisplayLanguages());
    }

    public static String getDefaultLanguage() {
        return Languages.getLanguages().first();
    }
}
