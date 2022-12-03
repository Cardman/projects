package cards.gui.dialogs.help;


import code.bean.nat.NatNavigation;
import code.formathtml.render.MetaDocument;

public class ElementHelp {
    private String nom;

    private String file = "";

    private NatNavigation navigation;
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

    public NatNavigation getNavigation() {
        return navigation;
    }

    public void setNavigation(NatNavigation _navigation) {
        this.navigation = _navigation;
    }

    public MetaDocument getMetaDocument() {
        return metaDocument;
    }

    public void setMetaDocument(MetaDocument _metaDocument) {
        this.metaDocument = _metaDocument;
    }
}
