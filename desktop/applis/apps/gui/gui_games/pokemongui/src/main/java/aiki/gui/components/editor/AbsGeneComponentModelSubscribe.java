package aiki.gui.components.editor;

import code.gui.AbsCustComponent;
import code.util.IdList;

public interface AbsGeneComponentModelSubscribe<T> {
    AbsCustComponent geneEnum(int _select, int _value);
    T tryRet();
    void setupValue(T _value);
    IdList<SubscribedTranslation> getSubs();
}
