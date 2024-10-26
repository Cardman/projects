package code.gui;

import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEltStr extends GeneComponentModelElt<String> {
    public GeneComponentModelEltStr(AbstractProgramInfos _c, StringMap<String> _messages, CustList<String> _elts) {
        this(_c, new CustCellRenderGeneStrImpl<String>(_c.getImageFactory(),_messages), _elts);
    }
    public GeneComponentModelEltStr(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<String> _rend, CustList<String> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    protected void setupValue(DefScrollCustomGraphicList<String> _t, String _v) {
        _t.select(StringUtil.indexOf(getElements(),_v));
    }

}
