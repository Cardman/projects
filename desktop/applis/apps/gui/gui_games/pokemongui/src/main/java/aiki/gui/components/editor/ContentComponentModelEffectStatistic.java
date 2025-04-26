package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectStatistic {

    private CrudGeneFormSimpleFormSub<Statistic, Long> statisVarRank;
    private CrudGeneFormSimpleFormSub<Statistic, String> localFailStatis;
    private GeneComponentModelRate evtRate;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> copyBoost;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> swapBoostStatis;
    private CrudGeneFormSimpleFormSub<Statistic, String> localFailSwapBoostStatis;
    private CrudGeneFormMonteCarloSub<Statistic> lawBoost;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> cancelLowStat;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> cancelChgtStat;
    private AbsPanel form;

    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        evtRate = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_RATE,evtRate.geneRate()));
        copyBoost = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_COPY_BOOST,copyBoost.geneEnum()));
        swapBoostStatis = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_SWAP_BOOST,swapBoostStatis.geneEnum()));
        cancelLowStat = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_CANCEL_LOW_STAT_INTRO,cancelLowStat.geneEnum()));
        cancelChgtStat = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_CANCEL_CHGT_STAT_INTRO,cancelChgtStat.geneEnum()));
        statisVarRank = new CrudGeneFormSimpleFormSub<Statistic,Long>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        statisVarRank.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST);
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,statisVarRank.getGroup()));
        localFailStatis = new CrudGeneFormSimpleFormSub<Statistic,String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        localFailStatis.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()), new GeneComponentModelSubscribeFactoryString(_core.getProgramInfos(),_core.getFacadeGame()),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_FAIL);
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_FAIL_VAR,localFailStatis.getGroup()));
        localFailSwapBoostStatis = new CrudGeneFormSimpleFormSub<Statistic,String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        localFailSwapBoostStatis.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()), new GeneComponentModelSubscribeFactoryString(_core.getProgramInfos(),_core.getFacadeGame()),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_FAIL);
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_FAIL_SWAP,localFailSwapBoostStatis.getGroup()));
        lawBoost = new CrudGeneFormMonteCarloSub<Statistic>(_core.getFrame(),_core.getProgramInfos());
        lawBoost.initFormKeys(ConverterCommonMapUtil.buildStatisticsElt(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory()),new DisplayEntryCustSubElementLgIntImpl<Statistic>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_RATE_EVENT);
        selected_.add(line(_core,MessagesDataEffstatis.M_P_58_LAW,lawBoost.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_STATIS, _key,_input);
    }

    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    void buildEntity(EffectStatistic _edited) {
        _edited.setEvtRate(evtRate.valueRate());
        _edited.setCopyBoost(copyBoost.tryRet());
        _edited.setSwapBoostStatis(swapBoostStatis.tryRet());
        _edited.setCancelLowStat(cancelLowStat.tryRet());
        _edited.setCancelChgtStat(cancelChgtStat.tryRet());
        _edited.setStatisVarRank(ConverterCommonMapUtil.buildIdMapStatisticInteger(statisVarRank.getList()));
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
        statisVarRank.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_edited.getStatisVarRank()));
        localFailStatis.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_edited.getLocalFailStatis()));
        localFailSwapBoostStatis.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_edited.getLocalFailSwapBoostStatis()));
        lawBoost.setupValues(new MapToEntriesListUtil<Statistic,LgInt>().build(_edited.getLawBoost()));
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCancelLowStat() {
        return cancelLowStat;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCopyBoost() {
        return copyBoost;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getSwapBoostStatis() {
        return swapBoostStatis;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Long> getStatisVarRank() {
        return statisVarRank;
    }

    public CrudGeneFormSimpleFormSub<Statistic, String> getLocalFailStatis() {
        return localFailStatis;
    }

    public CrudGeneFormSimpleFormSub<Statistic, String> getLocalFailSwapBoostStatis() {
        return localFailSwapBoostStatis;
    }

    public CrudGeneFormMonteCarloSub<Statistic> getLawBoost() {
        return lawBoost;
    }

}
