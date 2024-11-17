package aiki.gui.components.editor;

import code.gui.*;

public final class GeneComponentModelEltEnumSub<T> extends GeneComponentModelEltStrCom implements AbsGeneComponentModelSubscribe<T>{
    private final GeneComponentModelElt<T> selectUniq;

    public GeneComponentModelEltEnumSub(GeneComponentModelElt<T> _s) {
        this.selectUniq = _s;
    }

    public GeneComponentModelElt<T> getSelectUniq() {
        return selectUniq;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCustComponent panel_ = geneEnum();
        if (disable(_select, _value)) {
            selectUniq.getSelect().setEnabled(false);
        }
        return panel_;
    }

    public AbsCustComponent geneEnum() {
        return selectUniq.geneEnum();
    }

    public T tryRet() {
        return selectUniq.tryRet();
    }

    public void setupValue(T _value) {
        selectUniq.setupValue(_value);
    }
}
