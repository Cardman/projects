package code.sml.util;

public final class MessagesTranslations {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private MessagesTranslations() {
    }
    public static TranslationsFile mes(String _key) {
        TranslationsFile m_ = new TranslationsFile();
        m_.add(_key,BASE);
        return m_;
    }
}
