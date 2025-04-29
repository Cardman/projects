package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticType implements AbsGeneComponentModelSubscribe<StatisticType> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<Statistic> statistic;
    private GeneComponentModelEltEnumSub<String> type;
    private final SubscribedTranslationList subscribedTranslationList;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeStatisticType(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription, String _file, String _k,String _v) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
        file =_file;
        titleKey =_k;
        titleValue =_v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        statistic = ConverterCommonMapUtil.buildStatisticsElt(programInfos,facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleKey,statistic.geneEnum()));
        type = ConverterCommonMapUtil.buildTypeElt(programInfos, facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,type.geneEnum()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            statistic.getSelectUniq().getSelect().setEnabled(false);
            type.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public StatisticType tryRet() {
        StatisticType lv_ = new StatisticType();
        lv_.setStatistic(statistic.tryRet());
        lv_.setType(type.tryRet());
        return lv_;
    }

    @Override
    public void setupValue(StatisticType _value) {
        statistic.setupValue(_value.getStatistic());
        type.setupValue(_value.getType());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(statistic.getSubs());
        ids_.addAllElts(type.getSubs());
        return ids_;
    }

}
