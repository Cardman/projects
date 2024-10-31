package code.gui;

import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelLsStr extends GeneComponentModelLs<String> implements GeneComponentModel<StringList> {
    public GeneComponentModelLsStr(AbstractProgramInfos _c, StringMap<String> _messages, StringList _elts) {
        this(_c, new CustCellRenderGeneStrImpl<String>(_c.getImageFactory(),_messages), _elts);
    }
    public GeneComponentModelLsStr(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<String> _rend, StringList _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    protected int indexOf(String _t) {
        return StringUtil.indexOf(getElements(),_t);
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
