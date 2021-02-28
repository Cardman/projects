package cards.gui.animations;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatLgNames;
import code.sml.Document;

public final class PreparedPagesCards extends AbstractPreparedPagesCards {

    private final Document doc;
    private final AbstractNativeInit init;

    public PreparedPagesCards(Document _doc, String _conf, String _lg, BeanNatLgNames _stds, AbstractNativeInit _init) {
        super(_conf, _lg, _stds);
        doc = _doc;
        init = _init;
    }
    @Override
    public void run() {
        prepareDoc(doc, init);
    }
}
