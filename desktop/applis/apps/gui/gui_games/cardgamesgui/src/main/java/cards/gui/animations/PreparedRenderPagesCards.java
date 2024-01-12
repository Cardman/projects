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
    private final StringMap<int[][]> images;
    public PreparedRenderPagesCards(StringMap<Document> _built, StringMap<String> _ms, NatConfigurationCore _session, NatDualConfigurationContext _contextConf, String _firstUrl, StringMap<int[][]> _imgs) {
        lg = "";
        built = _built;
        ms = _ms;
        session = _session;
        contextConf = _contextConf;
        firstUrl = _firstUrl;
        images = _imgs;
    }

    public void run() {
        navigation= new NatNavigation();
        session.setPrefix("c:");
        getNavigation().setSession(session);
        getNavigation().setLanguage(lg);
        getNavigation().setLanguages(new StringList(lg));
        metaDocument = textSt(contextConf, getNavigation(), realPath(getNavigation()), built.getVal(firstUrl), ms,images);
    }

    private MetaDocument textSt(NatDualConfigurationContext _contextConf, NatNavigation _navigation, String _realFilePath, Document _val, StringMap<String> _ms, StringMap<int[][]> _imgs) {
        return HelpCaller.text(_contextConf, _navigation, _realFilePath, _val, _ms, lg,_imgs);
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
