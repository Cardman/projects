package cards.gui.animations;

import code.bean.help.HelpCaller;
import code.formathtml.render.MetaDocument;
import code.sml.Document;
import code.sml.NavigationCore;
import code.sml.util.TranslationsAppli;
import code.util.StringList;
import code.util.StringMap;

public final class PreparedRenderPagesCards {

    private final TranslationsAppli ms;
    private final Document document;
    private MetaDocument metaDocument;
    private NavigationCore navigation;

    private final StringMap<String> contextConf;
    private final StringMap<int[][]> images;

    public PreparedRenderPagesCards(TranslationsAppli _ms, StringMap<String> _contextConf, StringMap<int[][]> _imgs, Document _doc) {
        ms = _ms;
        contextConf = _contextConf;
        images = _imgs;
        document = _doc;
    }

    public void run() {
        navigation= new NavigationCore();
//        session.setPrefix("c:");
//        getNavigation().setSession(session);
        getNavigation().setLanguage("");
//        session.setCurrentLanguage("");
        getNavigation().setLanguages(new StringList(""));
        metaDocument = textSt(contextConf, document, ms,images);
    }

    private MetaDocument textSt(StringMap<String> _contextConf, Document _val, TranslationsAppli _ms, StringMap<int[][]> _imgs) {
        return HelpCaller.text(_val, _ms, _imgs, _contextConf);
    }

    public NavigationCore getNavigation() {
        return navigation;
    }
    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
