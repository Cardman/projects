package code.util.consts;
import code.util.StringList;
import code.util.StringMap;

enum Languages {
EN("en","English"),FR("fr","Fran\u00E7ais");

    private final String key;
    private final String display;
    Languages(String _key, String _display) {
        key = _key;
        display = _display;
    }

    static StringList getLanguages() {
        StringList lgs_ = new StringList();
        lgs_.add(EN.key);
        lgs_.add(FR.key);
        return lgs_;
    }

    static StringMap<String> getDisplayLanguages() {
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(EN.key,EN.display);
        m_.addEntry(FR.key,FR.display);
        return m_;
    }
}
