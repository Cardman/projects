package code.util.consts;
import code.util.CustList;
import code.util.StringList;

public final class Constants {

    public static final String RETURN_LINE = "\n";

    public static final String EMPTY_STRING = "";

    private Constants() {
    }

    /**@see System#exit(int)*/
    public static void exit() {
        System.exit(CustList.SIZE_EMPTY);
    }

    public static void sleep(long _time) {
        long millis_ = System.currentTimeMillis();
        while (millis_ + _time > System.currentTimeMillis()) {
            continue;
        }
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
