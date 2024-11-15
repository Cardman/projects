package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.*;

public final class GeneComponentModelEltStrSub extends GeneComponentModelEltStrCom implements AbsGeneComponentModelSubscribe<String>{
    private final GeneComponentModelEltStr selectUniq;

    public GeneComponentModelEltStrSub(GeneComponentModelEltStr _s) {
        this.selectUniq = _s;
    }

    public GeneComponentModelEltStr getSelectUniq() {
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

    static boolean disable(int _select, int _value) {
        return _select >= 0 && _value == 0;
    }

    public AbsCustComponent geneEnum() {
        return selectUniq.geneEnum(DataBase.EMPTY_STRING);
    }

    public String tryRet() {
        return selectUniq.tryRet(DataBase.EMPTY_STRING);
    }

    public void setupValue(String _value) {
        selectUniq.setupValue(_value);
    }
}
