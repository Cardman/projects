package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticPokemon implements AbsGeneComponentModelSubscribe<StatisticPokemon> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<Statistic> statistic;
    private GeneComponentModelEltEnumSub<String> pk;
    private final SubscribedTranslationList subscribedTranslationList;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeStatisticPokemon(SubscribedTranslationList _subscription, String _file, String _k, String _v) {
        programInfos = _subscription.getProgramInfos();
        facade = _subscription.getFacadeGame();
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
        pk = ConverterCommonMapUtil.buildPkFull(programInfos, facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,pk.geneEnum()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            statistic.getSelectUniq().getSelect().setEnabled(false);
            pk.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public StatisticPokemon tryRet() {
        return new StatisticPokemon(statistic.tryRet(), pk.tryRet());
    }

    @Override
    public void setupValue(StatisticPokemon _value) {
        statistic.setupValue(_value.getStatistic());
        pk.setupValue(_value.getPokemon());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(statistic.getSubs());
        ids_.addAllElts(pk.getSubs());
        return ids_;
    }

}
