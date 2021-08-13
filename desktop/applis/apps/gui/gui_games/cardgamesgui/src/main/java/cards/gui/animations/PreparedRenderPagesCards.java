package cards.gui.animations;

import code.bean.help.HelpCaller;
import code.bean.help.HelpContextEl;
import code.bean.nat.NativeConfigurationLoader;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.util.StringMap;
import code.util.consts.Constants;

public final class PreparedRenderPagesCards {

    private final StringMap<String> ms;
    private MetaDocument metaDocument;
    private final StringMap<Document> built;
    private final String lg;
    private final Document document;
    private Navigation navigation;

    public PreparedRenderPagesCards(String _lg, Document _document, StringMap<Document> _built, StringMap<String> _ms) {
        lg = _lg;
        document = _document;
        built = _built;
        ms = _ms;
    }

    public void run() {
        navigation= new Navigation();
        getNavigation().setSession(new Configuration());
        getNavigation().setLanguage(lg);
        getNavigation().setLanguages(Constants.getAvailableLanguages());
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(null,null);
        DualConfigurationContext contextConf_ = new DualConfigurationContext();
        nat_.specificLoadBegin(navigation.getSession(), document,contextConf_);
        RendStackCall rendStackCall_ = textSt(contextConf_, getNavigation(), realPath(getNavigation()), built.getVal(realPath(getNavigation())), ms);
        Document doc_ = rendStackCall_.getDocument();
        metaDocument = MetaDocument.newInstance(doc_, getNavigation().getSession().getRendKeyWords());
    }

    private static RendStackCall textSt(DualConfigurationContext _contextConf, Navigation _navigation, String _realFilePath, Document _val, StringMap<String> _ms) {
        HelpContextEl ctx_ = new HelpContextEl();
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        HelpCaller.text(_contextConf, _navigation, _realFilePath, _val, _ms, ctx_, rendStackCall_);
        return rendStackCall_;
    }

    private String realPath(Navigation _navigation) {
        return AbstractPreparedPagesCards.getRealFilePath(lg,_navigation.getSession().getFirstUrl());
    }
    public Navigation getNavigation() {
        return navigation;
    }
    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
