package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticStatus implements AbsGeneComponentModelSubscribe<StatisticStatus> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<Statistic> statistic;
    private GeneComponentModelEltEnumSub<String> status;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeStatisticStatus(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        statistic = ConverterCommonMapUtil.buildStatisticsElt(programInfos,facade,subscribedTranslationList);
        form_.add(statistic.geneEnum());
        status = ConverterCommonMapUtil.buildStatus(programInfos, facade,subscribedTranslationList,new StringMap<String>());
        form_.add(status.geneEnum());
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            statistic.getSelectUniq().getSelect().setEnabled(false);
            status.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public StatisticStatus tryRet() {
        return new StatisticStatus(statistic.tryRet(),status.tryRet());
    }

    @Override
    public void setupValue(StatisticStatus _value) {
        statistic.setupValue(_value.getStatistic());
        status.setupValue(_value.getStatus());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(statistic.getSubs());
        ids_.addAllElts(status.getSubs());
        return ids_;
    }

}
