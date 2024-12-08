package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticType<T> implements DisplayEntryCustSubElement<EditedCrudPair<StatisticType, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticType(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.getContainer().buildMessages(_fact,_facade);
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.getContainer().buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<StatisticType, T>> buildDisplay() {
        return new DisplayEntryCustStatisticType<T>(stats,types);
    }

    @Override
    public Comparing<EditedCrudPair<StatisticType, T>> buildCmp() {
        return new ComparingKeyStatisticType<T>(stats,types);
    }
}
