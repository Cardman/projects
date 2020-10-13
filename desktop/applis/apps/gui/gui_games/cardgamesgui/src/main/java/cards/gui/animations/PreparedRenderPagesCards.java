package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;
import code.formathtml.render.MetaDocument;
import code.sml.Document;

public final class PreparedRenderPagesCards extends AbstractPreparedPagesCards {

    private MetaDocument metaDocument;
    public PreparedRenderPagesCards(String _conf, String _lg, BeanNatLgNames _stds) {
        super(_conf, _lg, _stds);
    }
    @Override
    public void run() {
        prepare();
        getNavigation().initializeRendSession(getContext(), getBeanNatLgNames());
        Document doc_ = getNavigation().getDocument();
        metaDocument = MetaDocument.newInstance(doc_, getNavigation().getSession().getRendKeyWords());
    }

    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
