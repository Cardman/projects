package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelLsStr extends GeneComponentModelLs<String> implements GeneComponentModel<StringList> {
    public GeneComponentModelLsStr(AbstractProgramInfos _c, AbsMap<String, String> _messages) {
        this(_c, new DefCustCellRenderGeneImpl<String>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _messages);
    }
    public GeneComponentModelLsStr(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<String> _rend, AbsMap<String,String> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    protected int indexOf(String _t) {
        return getElements().indexOfEntry(_t);
    }

    @Override
    public StringList value() {
        return new StringList(tryRet());
    }

    @Override
    public StringList value(StringList _v) {
        StringList ls_ = new StringList(tryRet());
        setupValue(getSelect(),_v);
        return ls_;
    }
}
