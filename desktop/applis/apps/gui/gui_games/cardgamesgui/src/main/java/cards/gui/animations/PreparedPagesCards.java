package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;
import code.sml.Document;

public final class PreparedPagesCards extends AbstractPreparedPagesCards {

    private final Document doc;
    public PreparedPagesCards(Document _doc, String _conf, String _lg, BeanNatLgNames _stds) {
        super(_conf, _lg, _stds);
        doc = _doc;
    }
    @Override
    public void run() {
        prepareDoc(doc);
    }
}
