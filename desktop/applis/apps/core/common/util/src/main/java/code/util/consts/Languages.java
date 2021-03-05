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
        for (Languages l: values()) {
            lgs_.add(l.key);
        }
        return lgs_;
    }

    static StringMap<String> getDisplayLanguages() {
        StringMap<String> m_ = new StringMap<String>();
        for (Languages l: values()) {
            m_.put(l.key, l.display);
        }
        return m_;
    }
}
