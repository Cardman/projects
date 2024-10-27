package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelEltEnum<E> extends GeneComponentModelElt<E> {

    private final AbsMap<E, String> messages;

    protected GeneComponentModelEltEnum(AbstractProgramInfos _c, AbsMap<E,String> _messages, CustList<E> _elts) {
        this(_c, new CustCellRenderGeneStrImpl<E>(_c.getImageFactory(),_messages), _elts);
    }
    protected GeneComponentModelEltEnum(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<E> _rend, CustList<E> _elts) {
        super(_c, _elts);
        messages = _rend.getMessages();
    }

    @Override
    protected AbsStringScrollCustomCombo<E> buildSelect() {
        return new EnumScrollCustomCombo<E>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), messages);
    }

    @Override
    protected void setupValue(AbsStringScrollCustomCombo<E> _t, E _v) {
        _t.select(new IdList<E>(getElements()).indexOfObj(_v));
        _t.repaint();
    }
}
