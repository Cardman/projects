package aiki.gui.components.editor;

import code.gui.AbsCustComponent;
import code.gui.GeneComponentModelRate;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;

public final class GeneComponentModelEventRate extends GeneComponentModelEvent<Rate> {
    private final GeneComponentModelRate event;
    public GeneComponentModelEventRate(AbstractProgramInfos _c) {
        super(_c);
        event = new GeneComponentModelRate(_c);
    }
    public GeneComponentModelEventRate(AbstractProgramInfos _c, String _k, String _v) {
        super(_c, _k, _v);
        event = new GeneComponentModelRate(_c);
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        return event.geneRate();
    }

    @Override
    protected Rate valEvent() {
        return event.valueRate();
    }

    @Override
    protected void updateEvent(Rate _e) {
        event.valueRate(_e);
        event.getTextRate().setEditable(false);
    }

    public GeneComponentModelRate getEvent() {
        return event;
    }
}
