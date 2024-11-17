package aiki.gui.components.editor;

import code.util.IdList;

public class GeneComponentModelEltStrCom {
    private final IdList<SubscribedTranslation> subs = new IdList<SubscribedTranslation>();

    protected static boolean disable(int _select, int _value) {
        return _select >= 0 && _value == 0;
    }

    public IdList<SubscribedTranslation> getSubs() {
        return subs;
    }
}
