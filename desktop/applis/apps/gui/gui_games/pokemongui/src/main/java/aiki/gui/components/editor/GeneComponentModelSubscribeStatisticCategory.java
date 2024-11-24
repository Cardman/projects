package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticCategory implements AbsGeneComponentModelSubscribe<StatisticCategory> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<Statistic> statistic;
    private GeneComponentModelEltEnumSub<String> categories;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeStatisticCategory(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        statistic = ConverterCommonMapUtil.buildStatisticsElt(programInfos,facade,subscribedTranslationList);
        form_.add(statistic.geneEnum());
        categories = ConverterCommonMapUtil.buildCatElt(programInfos, facade,subscribedTranslationList);
        form_.add(categories.geneEnum());
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            statistic.getSelectUniq().getSelect().setEnabled(false);
            categories.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public StatisticCategory tryRet() {
        return new StatisticCategory(statistic.tryRet(), categories.tryRet());
    }

    @Override
    public void setupValue(StatisticCategory _value) {
        statistic.setupValue(_value.getStatistic());
        categories.setupValue(_value.getCategory());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(statistic.getSubs());
        ids_.addAllElts(categories.getSubs());
        return ids_;
    }

}
