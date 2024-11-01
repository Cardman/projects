package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class GeneComponentModelEltStrSub extends GeneComponentModelEltStrCom {
    private final GeneComponentModelEltStr selectUniq;

    public GeneComponentModelEltStrSub(GeneComponentModelEltStr _s) {
        this.selectUniq = _s;
    }

    public GeneComponentModelEltStr getSelectUniq() {
        return selectUniq;
    }

    public IdList<SubscribedTranslation> subsPk() {
        return subs(new SubscribedTranslationPkMessages(getSelectUniq().getMessages()));
    }
    public IdList<SubscribedTranslation> subs(SubscribedTranslation _s) {
        IdList<SubscribedTranslation> s_ = new IdList<SubscribedTranslation>(getSubs());
        s_.add(_s);
        s_.add(new SubscribedTranslationSelect(selectUniq));
        return s_;
    }

    public AbsCustComponent geneEnum(String _value) {
        return selectUniq.geneEnum(_value);
    }

    public String tryRet(String _def) {
        return selectUniq.tryRet(_def);
    }

    public void setupValue(String _value) {
        selectUniq.setupValue(_value);
    }
}
