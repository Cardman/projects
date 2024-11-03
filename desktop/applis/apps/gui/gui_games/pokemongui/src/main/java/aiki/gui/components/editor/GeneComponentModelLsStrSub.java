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

    public CustList<String> tryRet() {
        return selectList.tryRet();
    }

    public void setupValue(CustList<String> _types) {
        selectList.setupValue(_types);
    }
}
