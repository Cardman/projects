package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelEventString extends GeneComponentModelEvent<String> {
    private final GeneComponentModelText event;
    public GeneComponentModelEventString(AbstractProgramInfos _c) {
        super(_c);
        event = new GeneComponentModelText(_c);
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        return event.geneString();
    }

    @Override
    protected String valEvent() {
        return event.valueString();
    }

    @Override
    protected void updateEvent(String _e) {
        event.valueString(_e);
        event.getTextPane().setEditable(false);
    }

    public GeneComponentModelText getEvent() {
        return event;
    }
}
