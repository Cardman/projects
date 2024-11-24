package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticCategory<T> implements DisplayEntryCustSubElement<EditedCrudPair<StatisticCategory, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> cats;
    private final SubscribedTranslationMessagesFactory factoryCa;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticCategory(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.buildMessages(_fact,_facade);
        factoryCa = _sub.getFactoryCa();
        this.cats = factoryCa.buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(cats, factoryCa, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<StatisticCategory, T>> buildDisplay() {
        return new DisplayEntryCustStatisticCategory<T>(stats, cats);
    }

    @Override
    public Comparing<EditedCrudPair<StatisticCategory, T>> buildCmp() {
        return new ComparingKeyStatisticCategory<T>(stats, cats);
    }
}
