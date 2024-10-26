package code.gui;

import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelLsStr extends GeneComponentModelLs<String> {
    public GeneComponentModelLsStr(AbstractProgramInfos _c, StringMap<String> _messages, CustList<String> _elts) {
        this(_c, new CustCellRenderGeneStrImpl<String>(_c.getImageFactory(),_messages), _elts);
    }
    public GeneComponentModelLsStr(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<String> _rend, CustList<String> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    protected void setupValue(DefScrollCustomGraphicList<String> _t, CustList<String> _v) {
        Ints ind_ = new Ints();
        for (String s: _v) {
            ind_.add(StringUtil.indexOf(getElements(),s));
        }
        _t.select(ind_);
    }

}
