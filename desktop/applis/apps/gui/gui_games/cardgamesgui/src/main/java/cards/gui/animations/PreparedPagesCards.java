package cards.gui.animations;

import code.bean.nat.AbstractNativeInit;
import code.bean.nat.BeanNatCommonLgNames;
import code.sml.Document;
import code.util.*;

public final class PreparedPagesCards extends AbstractPreparedPagesCards {

    private final AbstractNativeInit init;
    private final StringMap<String> other;

    public PreparedPagesCards(String _lg, BeanNatCommonLgNames _stds, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringList _lgs) {
        super(_lg, _stds, _built, _lgs);
        init = _init;
        other = _other;
    }
    @Override
    public void run() {
        prepareDoc(init, other);
    }
}
