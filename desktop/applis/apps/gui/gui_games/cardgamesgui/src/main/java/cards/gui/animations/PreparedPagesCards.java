package cards.gui.animations;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatLgNames;
import code.sml.Document;
import code.util.StringMap;

public final class PreparedPagesCards extends AbstractPreparedPagesCards {

    private final Document doc;
    private final AbstractNativeInit init;
    private final StringMap<String> other;

    public PreparedPagesCards(Document _doc, String _lg, BeanNatLgNames _stds, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other) {
        super(_lg, _stds, _built);
        doc = _doc;
        init = _init;
        other = _other;
    }
    @Override
    public void run() {
        prepareDoc(doc, init, other);
    }
}
