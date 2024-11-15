package aiki.gui.components.editor;

import code.gui.*;

public final class GeneComponentModelEltEnumSub<T> extends GeneComponentModelEltStrCom implements AbsGeneComponentModelSubscribe<T>{
    private final GeneComponentModelEltEnum<T> selectUniq;

    public GeneComponentModelEltEnumSub(GeneComponentModelEltEnum<T> _s) {
        this.selectUniq = _s;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCustComponent panel_ = geneEnum();
        if (GeneComponentModelEltStrSub.disable(_select, _value)) {
            selectUniq.getSelect().setEnabled(false);
        }
        return panel_;
    }

    public AbsCustComponent geneEnum() {
        return selectUniq.geneEnum(null);
    }

    public T tryRet() {
        return selectUniq.tryRet(null);
    }

    public void setupValue(T _value) {
        selectUniq.setupValue(_value);
    }
}
