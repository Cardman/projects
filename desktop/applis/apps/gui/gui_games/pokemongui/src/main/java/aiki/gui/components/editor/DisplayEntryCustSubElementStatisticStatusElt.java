package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticStatusElt implements DisplayEntryCustSubElement<StatisticStatus> {
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;
    private final AbsMap<Statistic, String> stats;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticStatusElt(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.buildMessages(_fact,_facade);
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, StatisticStatus> buildDisplay() {
        return new DisplayEntryCustStatisticStatusElt(types,stats);
    }

    @Override
    public Comparing<StatisticStatus> buildCmp() {
        return new ComparatorStatusStatistic(types,stats);
    }
}
