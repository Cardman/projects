package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEltEnum<E> extends GeneComponentModelElt<E> {

    private final AbsMap<E, String> messages;

    public GeneComponentModelEltEnum(AbstractProgramInfos _c, AbsMap<E, String> _messages) {
        this(_c, new CustCellRenderGeneStrImpl<E>(_c.getImageFactory(),_messages), _messages);
    }
    public GeneComponentModelEltEnum(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<E> _rend, AbsMap<E,String> _elts) {
        super(_c, _elts);
        messages = _rend.getMessages();
    }

    @Override
    protected AbsStringScrollCustomCombo<E> buildSelect() {
        return new EnumScrollCustomCombo<E>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), messages);
    }

    @Override
    protected E defValue() {
        return null;
    }

    @Override
    protected void setupValue(AbsStringScrollCustomCombo<E> _t, E _v) {
        _t.select(getElements().indexOfEntry(_v));
        _t.repaint();
    }
}
