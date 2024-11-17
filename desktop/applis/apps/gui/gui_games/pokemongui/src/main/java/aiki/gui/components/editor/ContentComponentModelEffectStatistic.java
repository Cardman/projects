package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectStatistic {

    private CrudGeneFormSimpleFormSub<Statistic, Byte> statisVarRank;
    private CrudGeneFormSimpleFormSub<Statistic, String> localFailStatis;
    private GeneComponentModelRate evtRate;
    private GeneComponentModelLsStrSub<Statistic> copyBoost;
    private GeneComponentModelLsStrSub<Statistic> swapBoostStatis;
    private CrudGeneFormSimpleFormSub<Statistic, String> localFailSwapBoostStatis;
    private CrudGeneFormMonteCarloSub<Statistic> lawBoost;
    private GeneComponentModelLsStrSub<Statistic> cancelLowStat;
    private GeneComponentModelLsStrSub<Statistic> cancelChgtStat;
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        evtRate = new GeneComponentModelRate(_core);
        selected_.add(evtRate.geneRate(Rate.zero()));
        copyBoost = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(copyBoost.geneEnum());
        swapBoostStatis = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(swapBoostStatis.geneEnum());
        cancelLowStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(cancelLowStat.geneEnum());
        cancelChgtStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(cancelChgtStat.geneEnum());
        statisVarRank = new CrudGeneFormSimpleFormSub<Statistic,Byte>(_core, _fac, _fact, _f);
        statisVarRank.initForm(new DisplayEntryCustSubImpl<Statistic>(_fact.getFactoryStat(), new IdMap<Statistic, String>()),_fact.getFactoryStat().buildMessages(_core,_fac), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_core)));
        selected_.add(statisVarRank.getGroup());
        localFailStatis = new CrudGeneFormSimpleFormSub<Statistic,String>(_core, _fac, _fact, _f);
        localFailStatis.initForm(new DisplayEntryCustSubImpl<Statistic>(_fact.getFactoryStat(), new IdMap<Statistic, String>()),_fact.getFactoryStat().buildMessages(_core,_fac), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryString(_core));
        selected_.add(localFailStatis.getGroup());
        localFailSwapBoostStatis = new CrudGeneFormSimpleFormSub<Statistic,String>(_core, _fac, _fact, _f);
        localFailSwapBoostStatis.initForm(new DisplayEntryCustSubImpl<Statistic>(_fact.getFactoryStat(), new IdMap<Statistic, String>()),_fact.getFactoryStat().buildMessages(_core,_fac), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryString(_core));
        selected_.add(localFailSwapBoostStatis.getGroup());
        lawBoost = new CrudGeneFormMonteCarloSub<Statistic>(_f,_core, ConverterCommonMapUtil.buildStatisticsElt(_core, _fac, _fact), _fact.getFactoryStat().buildMessages(_core,_fac), new DisplayEntryCustSubImpl<Statistic>(_fact.getFactoryStat(), new IdMap<Statistic, String>()));
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
        _edited.setStatisVarRank(ConverterCommonMapUtil.buildIdMapStatisticByte(statisVarRank.getList()));
        _edited.setLocalFailStatis(ConverterCommonMapUtil.buildIdMapStatisticString(localFailStatis.getList()));
        _edited.setLocalFailSwapBoostStatis(ConverterCommonMapUtil.buildIdMapStatisticString(localFailSwapBoostStatis.getList()));
        _edited.setLawBoost(ConverterCommonMapUtil.buildMonteCarloEnumStatistic(lawBoost.getList()));
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

    public GeneComponentModelLsStrSub<Statistic> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public GeneComponentModelLsStrSub<Statistic> getCancelLowStat() {
        return cancelLowStat;
    }

    public GeneComponentModelLsStrSub<Statistic> getCopyBoost() {
        return copyBoost;
    }

    public GeneComponentModelLsStrSub<Statistic> getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public CrudGeneFormSimpleForm<Statistic, Byte> getStatisVarRank() {
        return statisVarRank.getCrud();
    }

    public CrudGeneFormSimpleForm<Statistic, String> getLocalFailStatis() {
        return localFailStatis.getCrud();
    }

    public CrudGeneFormSimpleForm<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis.getCrud();
    }

    public CrudGeneFormMonteCarloSub<Statistic> getLawBoost() {
        return lawBoost;
    }

}
