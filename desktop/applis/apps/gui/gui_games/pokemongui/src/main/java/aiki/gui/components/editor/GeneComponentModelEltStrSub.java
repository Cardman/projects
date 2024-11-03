package aiki.gui.components.editor;

import code.gui.*;

public final class GeneComponentModelEltStrSub extends GeneComponentModelEltStrCom {
    private final GeneComponentModelEltStr selectUniq;

    public GeneComponentModelEltStrSub(GeneComponentModelEltStr _s) {
        this.selectUniq = _s;
    }

    public GeneComponentModelEltStr getSelectUniq() {
        return selectUniq;
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
