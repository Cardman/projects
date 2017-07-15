package code.util.consts;
import code.util.StringList;
import code.util.StringMap;

final class Languages {

    private static final Languages LANGUAGES = new Languages();

    private static final String EN = "English";

    private static final String FR = "Fran\u00E7ais";

    private StringMap<String> displayLanguages = new StringMap<String>();

    private StringList languages = new StringList("en","fr");

    private Languages() {
        displayLanguages.put(languages.first(), EN);
        displayLanguages.put(languages.last(), FR);
    }

    static StringList getLanguages() {
        return LANGUAGES.languages;
    }

    static StringMap<String> getDisplayLanguages() {
        return LANGUAGES.displayLanguages;
    }
}
