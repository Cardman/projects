package cards.gui.animations;

import code.bean.help.HelpCaller;
import code.bean.help.HelpContextEl;
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
    private final Configuration session;
    private final String firstUrl;
    private MetaDocument metaDocument;
    private final StringMap<Document> built;
    private final String lg;
    private Navigation navigation;

    private final DualConfigurationContext contextConf;
    public PreparedRenderPagesCards(String _lg, StringMap<Document> _built, StringMap<String> _ms, Configuration _session, DualConfigurationContext _contextConf, String _firstUrl) {
        lg = _lg;
        built = _built;
        ms = _ms;
        session = _session;
        contextConf = _contextConf;
        firstUrl = _firstUrl;
    }

    public void run() {
        navigation= new Navigation();
        getNavigation().setSession(session);
        getNavigation().setLanguage(lg);
        getNavigation().setLanguages(Constants.getAvailableLanguages());
        RendStackCall rendStackCall_ = textSt(contextConf, getNavigation(), realPath(getNavigation()), built.getVal(firstUrl), ms);
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
