package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementStatisticPokemon<T> implements DisplayEntryCustSubElement<EditedCrudPair<StatisticPokemon, T>> {
    private final AbsMap<Statistic, String> stats;
    private final AbsMap<String, String> pks;
    private final SubscribedTranslationMessagesFactory factoryPk;
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat;

    public DisplayEntryCustSubElementStatisticPokemon(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        factoryStat = _sub.getFactoryStat();
        this.stats = factoryStat.getContainer().buildMessages(_fact,_facade);
        factoryPk = _sub.getFactoryPk();
        this.pks = factoryPk.getContainer().buildMessages(_fact,_facade);
    }


    @Override
    public IdList<SubscribedTranslation> buildSub() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<Statistic>(stats,factoryStat, new IdMap<Statistic, String>()));
        ids_.add(new SubscribedTranslationMessages<String>(pks, factoryPk, new StringMap<String>()));
        return ids_;
    }

    @Override
    public DisplayEntryCust<Integer, EditedCrudPair<StatisticPokemon, T>> buildDisplay() {
        return new DisplayEntryCustStatisticPokemon<T>(stats, pks);
    }

    @Override
    public Comparing<EditedCrudPair<StatisticPokemon, T>> buildCmp() {
        return new ComparingKeyStatisticPokemon<T>(stats, pks);
    }
}
