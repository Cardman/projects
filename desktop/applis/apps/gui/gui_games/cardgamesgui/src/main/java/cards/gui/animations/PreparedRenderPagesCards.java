package cards.gui.animations;

import code.bean.help.HelpCaller;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.formathtml.render.MetaDocument;
import code.sml.Document;
import code.util.StringList;
import code.util.StringMap;

public final class PreparedRenderPagesCards {

    private final StringMap<String> ms;
    private final NatConfigurationCore session;
    private final Document document;
    private MetaDocument metaDocument;
    private NatNavigation navigation;

    private final NatDualConfigurationContext contextConf;
    private final StringMap<int[][]> images;

    public PreparedRenderPagesCards(StringMap<String> _ms, NatConfigurationCore _session, NatDualConfigurationContext _contextConf, StringMap<int[][]> _imgs, Document _doc) {
        ms = _ms;
        session = _session;
        contextConf = _contextConf;
        images = _imgs;
        document = _doc;
    }

    public void run() {
        navigation= new NatNavigation();
        session.setPrefix("c:");
        getNavigation().setSession(session);
        getNavigation().setLanguage("");
        getNavigation().setLanguages(new StringList(""));
        metaDocument = textSt(contextConf, getNavigation(), document, ms,images);
    }

    private MetaDocument textSt(NatDualConfigurationContext _contextConf, NatNavigation _navigation, Document _val, StringMap<String> _ms, StringMap<int[][]> _imgs) {
        return HelpCaller.text(_val, _ms, "",_imgs, _contextConf.getProperties(), _contextConf.getMessagesFolder(), _navigation.getSession().getNat().getPrefix());
    }

    public NatNavigation getNavigation() {
        return navigation;
    }
    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
