package cards.gui.dialogs.help;


import code.formathtml.Navigation;
import code.formathtml.render.MetaDocument;

public class ElementHelp {
    private String nom;

    private String file = "";

    private Navigation navigation;
    private MetaDocument metaDocument;

    public ElementHelp(String _pnom) {
        nom = _pnom;
    }

    public void ajouterInfo(String _file) {
        file = _file;
    }

    public String nom() {
        return nom;
    }

    public String getFile() {
        return file;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation _navigation) {
        this.navigation = _navigation;
    }

    public MetaDocument getMetaDocument() {
        return metaDocument;
    }

    public void setMetaDocument(MetaDocument _metaDocument) {
        this.metaDocument = _metaDocument;
    }
}
