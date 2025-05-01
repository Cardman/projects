package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.*;

public final class GeneComponentModelEventBoolVal extends GeneComponentModelEvent<BoolVal> {
    private final AbsCompoFactory compoFactory;
    private AbsCustCheckBox event;

    public GeneComponentModelEventBoolVal(AbstractProgramInfos _fact, String _file, String _k, String _v) {
        super(_fact, _file, _k, _v);
        compoFactory = _fact.getCompoFactory();
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        event = compoFactory.newCustCheckBox();
        return event;
    }

    @Override
    protected BoolVal valEvent() {
        return ComparatorBoolean.of(event.isSelected());
    }

    @Override
    protected void updateEvent(BoolVal _e) {
        event.setSelected(_e == BoolVal.TRUE);
    }

    public AbsCustCheckBox getEvent() {
        return event;
    }
}
