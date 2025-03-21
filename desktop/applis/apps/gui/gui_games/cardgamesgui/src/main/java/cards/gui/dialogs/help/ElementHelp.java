package cards.gui.dialogs.help;


import code.sml.Document;
import code.util.StringMap;

public class ElementHelp {
    private final String nom;
    private final String chemin;
    private final StringMap<StringMap<String>> ct;
    private final StringMap<Document> built;

    public ElementHelp(String _pnom, String _pchemin, StringMap<StringMap<String>> _pct, StringMap<Document> _pbuilt) {
        nom = _pnom;
        chemin = _pchemin;
        ct = _pct;
        built = _pbuilt;
    }

    public String nom() {
        return nom;
    }
    public String chemin() {
        return chemin;
    }

    public StringMap<StringMap<String>> ct() {
        return ct;
    }
    public StringMap<Document> built() {
        return built;
    }

}
