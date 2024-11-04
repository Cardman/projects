package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEltStr extends GeneComponentModelElt<String> {

    public GeneComponentModelEltStr(AbstractProgramInfos _c, AbsMap<String, String> _messages) {
        this(_c, new DefCustCellRenderGeneImpl<String>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _messages);
    }
    public GeneComponentModelEltStr(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<String> _rend, AbsMap<String,String> _elts) {
        super(_c, _elts,_rend);
    }

    @Override
    protected String defValue() {
        return "";
    }
}
