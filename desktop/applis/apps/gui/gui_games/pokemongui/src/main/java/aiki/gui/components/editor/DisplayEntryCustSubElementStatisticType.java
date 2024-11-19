package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticType implements DisplayEntryCustSubElement<EditedCrudPair<StatisticType, Rate>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> types;
    private final SubscribedTranslationMessagesFactory factoryTy;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticType(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.buildMessages(_fact,_facade);
        factoryTy = _sub.getFactoryTy();
        this.types = factoryTy.buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(types,factoryTy, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<StatisticType, Rate>> buildDisplay() {
        return new DisplayEntryCustStatisticType(stats,types);
    }

    @Override
    public Comparing<EditedCrudPair<StatisticType, Rate>> buildCmp() {
        return new ComparingKeyStatisticType(stats,types);
    }
}
