package cards.gui.dialogs.help;


import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.formathtml.render.MetaDocument;
import code.sml.Document;
import code.util.StringMap;

public class ElementHelp {
    private final String nom;
    private final String chemin;
    private final StringMap<NatConfigurationCore> cf;
    private final StringMap<NatDualConfigurationContext> ct;
    private final StringMap<Document> built;
    private NatNavigation navigation;
    private MetaDocument metaDocument;

    public ElementHelp(String _pnom, String _pchemin, StringMap<NatConfigurationCore> _pcf, StringMap<NatDualConfigurationContext> _pct, StringMap<Document> _pbuilt) {
        nom = _pnom;
        chemin = _pchemin;
        cf = _pcf;
        ct = _pct;
        built = _pbuilt;
    }

    public String nom() {
        return nom;
    }
    public String chemin() {
        return chemin;
    }
    public StringMap<NatConfigurationCore> cf() {
        return cf;
    }
    public StringMap<NatDualConfigurationContext> ct() {
        return ct;
    }
    public StringMap<Document> built() {
        return built;
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
