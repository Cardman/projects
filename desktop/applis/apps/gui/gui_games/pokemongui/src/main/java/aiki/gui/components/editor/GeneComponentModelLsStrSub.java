package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class GeneComponentModelLsStrSub extends GeneComponentModelEltStrCom {
    private final GeneComponentModelLsStr selectList;

    public GeneComponentModelLsStrSub(GeneComponentModelLsStr _s) {
        this.selectList = _s;
    }

    public AbsCustComponent geneCommon(CustList<String> _values) {
        return selectList.geneCommon(_values);
    }
    public IdList<SubscribedTranslation> subsTy() {
        return subs(new SubscribedTranslationTyMessages(selectList.getRender().getMessages()));
    }

    public IdList<SubscribedTranslation> subs(SubscribedTranslation _s) {
        IdList<SubscribedTranslation> s_ = new IdList<SubscribedTranslation>(getSubs());
        s_.add(_s);
        return s_;
    }
    public CustList<String> tryRet() {
        return selectList.tryRet();
    }

    public void setupValue(CustList<String> _types) {
        selectList.setupValue(_types);
    }
}
