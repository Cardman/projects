package aiki.gui.components.editor;

import aiki.fight.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEventStatistic extends GeneComponentModelEvent<Statistic> {
    private final GeneComponentModelEltEnumSub<Statistic> event;
    public GeneComponentModelEventStatistic(AbstractProgramInfos _c, AbsMap<Statistic, String> _m) {
        super(_c);
        event = new GeneComponentModelEltEnumSub<Statistic>(new GeneComponentModelEltEnum<Statistic>(_c, _m));
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
