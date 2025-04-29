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
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeStatisticCategory(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription, String _file, String _k,String _v) {
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
        categories = ConverterCommonMapUtil.buildCatElt(programInfos, facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,categories.geneEnum()));
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
