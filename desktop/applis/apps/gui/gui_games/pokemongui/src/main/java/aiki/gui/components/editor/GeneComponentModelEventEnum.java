package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelEventEnum<T> extends GeneComponentModelEvent<T> {
    private final GeneComponentModelEltEnumSub<T> event;
    public GeneComponentModelEventEnum(AbstractProgramInfos _c, GeneComponentModelEltEnumSub<T> _e, String _f, String _k, String _v) {
        super(_c,_f, _k, _v);
        event = _e;
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        return event.geneEnum(_select,0);
    }

    @Override
    protected T valEvent() {
        return event.tryRet();
    }

    @Override
    protected void updateEvent(T _e) {
        event.setupValue(_e);
    }

    public GeneComponentModelEltEnumSub<T> getEvent() {
        return event;
    }
}
