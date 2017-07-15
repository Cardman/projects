package code.util.consts;
import java.util.Locale;

import code.util.CustList;
import code.util.StringList;

public final class Constants {

    public static final String RETURN_LINE = "\n";

    public static final String EMPTY_STRING = "";

    private static String _language_;

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

    public static Iterable<String> getAvailableLanguages() {
        return new StringList(Languages.getLanguages());
    }

    public static String getDisplayLanguage(String _language) {
        return Languages.getDisplayLanguages().getVal(_language);
    }

    public static String getLanguage() {
        if (_language_ == null) {
            _language_ = Languages.getLanguages().first();
        }
        return _language_;
    }

    public static void setSystemLanguage(String _language) {
        Locale.setDefault(new Locale(_language));
        setLanguage(_language);
    }

    public static void setLanguage(String _language) {
        if (_language == null) {
            return;
        }
        _language_ = _language;
    }
}
