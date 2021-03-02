package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;
import code.bean.nat.NativeConfigurationLoader;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.Navigation;
import code.formathtml.render.MetaDocument;
import code.formathtml.util.DualAnalyzedContext;
import code.sml.Document;
import code.util.StringMap;
import code.util.consts.Constants;

public final class PreparedRenderPagesCards extends AbstractPreparedPagesCards {

    private MetaDocument metaDocument;
    private final Document document;
    public PreparedRenderPagesCards(String _lg, BeanNatLgNames _stds, Document _document, StringMap<Document> _built) {
        super(_lg, _stds, _built);
        document = _document;
    }
    @Override
    public void run() {
        BeanNatLgNames prepared_ = prepare();
        getNavigation().initializeRendSession(getContext(), prepared_, StackCall.newInstance(InitPhase.NOTHING,getContext()));
        Document doc_ = getNavigation().getDocument();
        metaDocument = MetaDocument.newInstance(doc_, getNavigation().getSession().getRendKeyWords());
    }

    public BeanNatLgNames prepare() {
        setNavigation(new Navigation());
        getNavigation().setSession(new Configuration());
        getNavigation().setLanguage(getLg());
        getNavigation().setLanguages(Constants.getAvailableLanguages());
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(getBeanNatLgNames(), null);
        DualAnalyzedContext du_ = getNavigation().loadConfiguration("", getBeanNatLgNames(), DefaultFileBuilder.newInstance(getBeanNatLgNames().getContent()), nat_,document);
        return common(du_);
    }

    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
