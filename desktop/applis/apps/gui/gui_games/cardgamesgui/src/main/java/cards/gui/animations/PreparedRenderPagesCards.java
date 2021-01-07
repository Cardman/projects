package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.render.MetaDocument;
import code.sml.Document;

public final class PreparedRenderPagesCards extends AbstractPreparedPagesCards {

    private MetaDocument metaDocument;
    public PreparedRenderPagesCards(String _conf, String _lg, BeanNatLgNames _stds) {
        super(_conf, _lg, _stds);
    }
    @Override
    public void run() {
        BeanNatLgNames prepared_ = prepare();
        getNavigation().initializeRendSession(getContext(), prepared_, StackCall.newInstance(InitPhase.NOTHING,getContext()));
        Document doc_ = getNavigation().getDocument();
        metaDocument = MetaDocument.newInstance(doc_, getNavigation().getSession().getRendKeyWords());
    }

    public MetaDocument getMetaDocument() {
        return metaDocument;
    }
}
