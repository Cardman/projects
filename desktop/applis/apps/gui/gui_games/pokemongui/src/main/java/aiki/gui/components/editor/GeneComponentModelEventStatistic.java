package aiki.gui.components.editor;

import aiki.fight.enums.Statistic;
import code.gui.AbsCustComponent;
import code.gui.GeneComponentModelEvent;
import code.gui.initialize.AbstractProgramInfos;

public final class GeneComponentModelEventStatistic extends GeneComponentModelEvent<Statistic> {
    private final GeneComponentModelEltEnumSub<Statistic> event;
    public GeneComponentModelEventStatistic(AbstractProgramInfos _c, GeneComponentModelEltEnumSub<Statistic> _e) {
        super(_c);
        event = _e;
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        return event.geneEnum(_select,0);
    }

    @Override
    protected Statistic valEvent() {
        return event.tryRet();
    }

    @Override
    protected void updateEvent(Statistic _e) {
        event.setupValue(_e);
    }

    public GeneComponentModelEltEnumSub<Statistic> getEvent() {
        return event;
    }
}
