package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticStatus<T> implements DisplayEntryCustSubElement<EditedCrudPair<StatisticStatus, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factorySt;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticStatus(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.buildMessages(_fact,_facade);
        factorySt = _sub.getFactorySt();
        this.types = factorySt.buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(types, factorySt, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<StatisticStatus, T>> buildDisplay() {
        return new DisplayEntryCustStatisticStatus<T>(stats,types);
    }

    @Override
    public Comparing<EditedCrudPair<StatisticStatus, T>> buildCmp() {
        return new ComparingKeyStatisticStatus<T>(stats,types);
    }
}
