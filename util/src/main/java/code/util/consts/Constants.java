package code.util.consts;
import code.util.StringList;

public final class Constants {

    private Constants() {
    }

    public static StringList getAvailableLanguages() {
        return new StringList(Languages.getLanguages());
    }

    public static String getDisplayLanguage(String _language) {
        return Languages.getDisplayLanguages().getVal(_language);
    }

    public static String getDefaultLanguage() {
        return Languages.getLanguages().first();
    }
}
