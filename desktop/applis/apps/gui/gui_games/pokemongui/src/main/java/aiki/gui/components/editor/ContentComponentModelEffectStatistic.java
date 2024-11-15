package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;

public final class ContentComponentModelEffectStatistic {

    private CrudGeneFormSimpleForm<Statistic, Byte> statisVarRank;
    private CrudGeneFormSimpleForm<Statistic, String> localFailStatis;
    private GeneComponentModelRate evtRate;
    private GeneComponentModelLsStrSub<Statistic> copyBoost;
    private GeneComponentModelLsStrSub<Statistic> swapBoostStatis;
    private CrudGeneFormSimpleForm<Statistic, String> localFailSwapBoostStatis;
    private CrudGeneFormMonteCarlo<Statistic> lawBoost;
    private GeneComponentModelLsStrSub<Statistic> cancelLowStat;
    private GeneComponentModelLsStrSub<Statistic> cancelChgtStat;
    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        evtRate = new GeneComponentModelRate(_core);
        selected_.add(evtRate.geneRate(Rate.zero()));
        copyBoost = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
        selected_.add(copyBoost.geneEnum());
        swapBoostStatis = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
        selected_.add(swapBoostStatis.geneEnum());
        cancelLowStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
        selected_.add(cancelLowStat.geneEnum());
        cancelChgtStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac);
        selected_.add(cancelChgtStat.geneEnum());
        statisVarRank = new CrudGeneFormSimpleForm<Statistic,Byte>(_core, _fac, _fact, _f);
        statisVarRank.initForm();
        IdMap<Statistic, String> trsStat_ = _fac.getData().getTranslatedStatistics().getVal(_core.getLanguage());
        statisVarRank.initForm(new DisplayEntryCustNoSubImpl<Statistic>(),trsStat_,_core, new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core,trsStat_), new GeneComponentModelSubscribeFactoryByte(_core),new IdMap<Statistic, Byte>());
        selected_.add(statisVarRank.getGroup());
        localFailStatis = new CrudGeneFormSimpleForm<Statistic,String>(_core, _fac, _fact, _f);
        localFailStatis.initForm();
        localFailStatis.initForm(new DisplayEntryCustNoSubImpl<Statistic>(),trsStat_,_core, new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core,trsStat_), new GeneComponentModelSubscribeFactoryString(_core),new IdMap<Statistic, String>());
        selected_.add(localFailStatis.getGroup());
        localFailSwapBoostStatis = new CrudGeneFormSimpleForm<Statistic,String>(_core, _fac, _fact, _f);
        localFailSwapBoostStatis.initForm();
        localFailSwapBoostStatis.initForm(new DisplayEntryCustNoSubImpl<Statistic>(),trsStat_,_core, new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core,trsStat_), new GeneComponentModelSubscribeFactoryString(_core),new IdMap<Statistic, String>());
        selected_.add(localFailSwapBoostStatis.getGroup());
        lawBoost = new CrudGeneFormMonteCarlo<Statistic>(_core,new ComparingEnumKey<Statistic,LgInt>(trsStat_));
        lawBoost.setFrame(_f);
        lawBoost.initForm();
        lawBoost.initForm(new StatisticLgIntDisplayEntryCust(trsStat_),new GeneComponentModelEventStatistic(_core,trsStat_),new MonteCarloEnum<Statistic>(),new ComparingEnumKey<Statistic,LgInt>(trsStat_));
        selected_.add(lawBoost.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    void buildEntity(EffectStatistic _edited) {
        _edited.setEvtRate(evtRate.valueRate());
        _edited.setCopyBoost(new IdList<Statistic>(copyBoost.tryRet()));
        _edited.setSwapBoostStatis(new IdList<Statistic>(swapBoostStatis.tryRet()));
        _edited.setCancelLowStat(new IdList<Statistic>(cancelLowStat.tryRet()));
        _edited.setCancelChgtStat(new IdList<Statistic>(cancelChgtStat.tryRet()));
        _edited.setStatisVarRank(new IdMap<Statistic, Byte>());
        new MapToEntriesListUtil<Statistic,Byte>().feedMap(statisVarRank.getList(), _edited.getStatisVarRank());
        _edited.setLocalFailStatis(new IdMap<Statistic, String>());
        new MapToEntriesListUtil<Statistic,String>().feedMap(localFailStatis.getList(), _edited.getLocalFailStatis());
        _edited.setLocalFailSwapBoostStatis(new IdMap<Statistic, String>());
        new MapToEntriesListUtil<Statistic,String>().feedMap(localFailSwapBoostStatis.getList(), _edited.getLocalFailSwapBoostStatis());
        _edited.setLawBoost(new MonteCarloEnum<Statistic>());
        new MapToEntriesListUtil<Statistic,LgInt>().feedMap(lawBoost.getList(), _edited.getLawBoost());
    }
    void feedForm(EffectStatistic _edited) {
        evtRate.valueRate(_edited.getEvtRate());
        copyBoost.setupValue(_edited.getCopyBoost());
        swapBoostStatis.setupValue(_edited.getSwapBoostStatis());
        cancelLowStat.setupValue(_edited.getCancelLowStat());
        cancelChgtStat.setupValue(_edited.getCancelChgtStat());
        statisVarRank.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_edited.getStatisVarRank()));
        localFailStatis.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_edited.getLocalFailStatis()));
        localFailSwapBoostStatis.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_edited.getLocalFailSwapBoostStatis()));
        lawBoost.setupValues(new MapToEntriesListUtil<Statistic,LgInt>().build(_edited.getLawBoost()));
    }

    public CrudGeneFormSimpleForm<Statistic, Byte> getStatisVarRank() {
        return statisVarRank;
    }

    public CrudGeneFormSimpleForm<Statistic, String> getLocalFailStatis() {
        return localFailStatis;
    }

    public CrudGeneFormSimpleForm<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }

    public CrudGeneFormMonteCarlo<Statistic> getLawBoost() {
        return lawBoost;
    }
}
