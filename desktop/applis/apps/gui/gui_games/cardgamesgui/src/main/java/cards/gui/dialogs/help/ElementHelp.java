package cards.gui.dialogs.help;


import code.scripts.pages.cards.AbsHelpCards;
import code.util.StringMap;

public class ElementHelp {
    private final String nom;
    private final String chemin;
    private final StringMap<AbsHelpCards> built;

    public ElementHelp(String _pnom, String _pchemin, StringMap<AbsHelpCards> _pbuilt) {
        nom = _pnom;
        chemin = _pchemin;
        built = _pbuilt;
    }

    public String nom() {
        return nom;
    }
    public String chemin() {
        return chemin;
    }

//    public StringMap<StringMap<String>> ct() {
//        return ct;
//    }
    public StringMap<AbsHelpCards> built() {
        return built;
    }

}
