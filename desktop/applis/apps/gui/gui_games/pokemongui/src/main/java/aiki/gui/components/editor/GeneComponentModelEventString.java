package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEventString extends GeneComponentModelEvent<String> {
    private final GeneComponentModelString event;
    public GeneComponentModelEventString(AbstractProgramInfos _c) {
        super(_c);
        event = new GeneComponentModelString(_c, new StringList(), new DefValidateText());
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
        event.getTextField().setEditable(false);
    }

    public GeneComponentModelString getEvent() {
        return event;
    }
}
