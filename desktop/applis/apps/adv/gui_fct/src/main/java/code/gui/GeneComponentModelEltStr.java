package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEltStr extends GeneComponentModelElt<String> {

    private final AbsMap<String, String> messages;

    public GeneComponentModelEltStr(AbstractProgramInfos _c, AbsMap<String, String> _messages) {
        this(_c, new DefCustCellRenderGeneImpl<String>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _messages);
    }
    public GeneComponentModelEltStr(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<String> _rend, AbsMap<String,String> _elts) {
        super(_c, _elts);
        messages = _rend.getMessages();
    }

    public AbsMap<String, String> getMessages() {
        return messages;
    }

    @Override
    protected AbsStringScrollCustomCombo<String> buildSelect() {
        return new EnumScrollCustomCombo<String>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(),getMessages());
    }

    @Override
    protected void setupValue(AbsStringScrollCustomCombo<String> _t, String _v) {
        _t.select(getElements().indexOfEntry(_v));
        _t.repaint();
    }

    @Override
    protected String defValue() {
        return "";
    }
}
