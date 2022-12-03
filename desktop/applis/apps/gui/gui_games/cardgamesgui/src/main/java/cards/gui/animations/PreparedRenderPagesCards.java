package cards.gui.animations;

import code.bean.help.HelpCaller;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.formathtml.render.MetaDocument;
import code.sml.Document;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class PreparedRenderPagesCards {
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";

    private final StringMap<String> ms;
    private final NatConfigurationCore session;
    private final String firstUrl;
    private MetaDocument metaDocument;
    private final StringMap<Document> built;
    private final String lg;
    private NatNavigation navigation;

    private final NatDualConfigurationContext contextConf;
    public PreparedRenderPagesCards(StringMap<Document> _built, StringMap<String> _ms, NatConfigurationCore _session, NatDualConfigurationContext _contextConf, String _firstUrl) {
        lg = "";
        built = _built;
        ms = _ms;
        session = _session;
        contextConf = _contextConf;
        firstUrl = _firstUrl;
    }

    public void run() {
        navigation= new NatNavigation();
        session.setPrefix("c:");
        getNavigation().setSession(session);
        getNavigation().setLanguage(lg);
        getNavigation().setLanguages(new StringList(lg));
        Document rendStackCall_ = textSt(contextConf, getNavigation(), realPath(getNavigation()), built.getVal(firstUrl), ms);
        metaDocument = MetaDocument.newInstance(rendStackCall_, getNavigation().getSession().getRendKeyWords());
    }

    private Document textSt(NatDualConfigurationContext _contextConf, NatNavigation _navigation, String _realFilePath, Document _val, StringMap<String> _ms) {
        return HelpCaller.text(_contextConf, _navigation, _realFilePath, _val, _ms, lg);
    }

    private String realPath(NatNavigation _navigation) {
        return getRealFilePath(lg,_navigation.getSession().getFirstUrl());
    }
    static String getRealFilePath(String _lg, String _link) {
        return StringUtil.replace(_link, IMPLICIT_LANGUAGE, StringUtil.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }
    public NatNavigation getNavigation() {
        return navigation;
    }
    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
