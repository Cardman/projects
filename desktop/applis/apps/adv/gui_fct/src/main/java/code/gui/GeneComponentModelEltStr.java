package code.gui;

import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEltStr extends GeneComponentModelElt<String> {

    private final AbsMap<String, String> messages;

    public GeneComponentModelEltStr(AbstractProgramInfos _c, StringMap<String> _messages, CustList<String> _elts) {
        this(_c, new CustCellRenderGeneStrImpl<String>(_c.getImageFactory(),_messages), _elts);
    }
    public GeneComponentModelEltStr(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<String> _rend, CustList<String> _elts) {
        super(_c, _elts);
        messages = _rend.getMessages();
    }

    @Override
    protected AbsStringScrollCustomCombo<String> buildSelect() {
        return new EnumScrollCustomCombo<String>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(),messages);
    }

    @Override
    protected void setupValue(AbsStringScrollCustomCombo<String> _t, String _v) {
        _t.select(StringUtil.indexOf(getElements(),_v));
        _t.repaint();
    }

    @Override
    protected String defValue() {
        return "";
    }
}
