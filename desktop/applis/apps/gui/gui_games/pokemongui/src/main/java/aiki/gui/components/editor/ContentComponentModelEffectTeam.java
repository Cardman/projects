package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectTeam {

    private AbsCustCheckBox forbiddingHealing;
    private AbsCustCheckBox protectAgainstCh;
    private GeneComponentModelLsStrSub<String,StringList> protectAgainstStatus;

    private GeneComponentModelLsStrSub<String,StringList> disableFoeTeamStatus;
    private GeneComponentModelLsStrSub<String,StringList> unusableMoves;

    private GeneComponentModelLsStrSub<String,StringList> disableFoeTeamEffects;

    private CrudGeneFormSimpleFormSub<Statistic,Rate> multStatistic;
    private CrudGeneFormSimpleFormSub<Statistic,Rate> multStatisticFoe;
    private CrudGeneFormSimpleFormSub<CategoryMult, Rate> multDamage;

    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> forbiddenBoost;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> cancelChgtStatFoeTeam;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> cancelChgtStatTeam;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> protectAgainstLowStat;


    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        forbiddingHealing = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_FORBID_HEAL,forbiddingHealing));
        protectAgainstCh = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_PROTECT_AG_CH,protectAgainstCh));
        forbiddenBoost = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_FORBID_BOOST,forbiddenBoost.geneEnum()));
        cancelChgtStatFoeTeam = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_CANCEL_CHGT_STAT_FOE_INTRO,cancelChgtStatFoeTeam.geneEnum()));
        cancelChgtStatTeam = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_CANCEL_CHGT_STAT_INTRO,cancelChgtStatTeam.geneEnum()));
        protectAgainstLowStat = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_PROTECT_AG_LAW_STATIS,protectAgainstLowStat.geneEnum()));
         disableFoeTeamStatus = ConverterCommonMapUtil.buildStatusList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_DELETE_STATUS,disableFoeTeamStatus.geneEnum()));
        protectAgainstStatus = ConverterCommonMapUtil.buildStatusList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_PROTECT_AG_STATUS,protectAgainstStatus.geneEnum()));
        unusableMoves = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_FORBID_MOVE,unusableMoves.geneEnum()));
        disableFoeTeamEffects = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_DELETE_EFFECTS,disableFoeTeamEffects.geneEnum()));
        multStatistic = new CrudGeneFormSimpleFormSub<Statistic, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multStatistic.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_STATISTIC,MessagesDataEffteam.M_P_66_RATE);
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_MULT_STAT,multStatistic.getGroup()));
        multStatisticFoe = new CrudGeneFormSimpleFormSub<Statistic, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multStatisticFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_STATISTIC,MessagesDataEffteam.M_P_66_RATE);
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_MULT_STAT_FOE,multStatisticFoe.getGroup()));
        multDamage = new CrudGeneFormSimpleFormSub<CategoryMult, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamage.initFormWithVal(new DisplayEntryCustSubElementCategoryMult(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory()),new GeneComponentModelSubscribeFactoryDirect<CategoryMult>(new GeneComponentModelSubscribeCategoryMult(_core, MessagesPkBean.EFF_TEAM, MessagesDataEffteam.M_P_66_MULT, MessagesDataEffteam.M_P_66_CAT)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_TEAM,"",MessagesDataEffteam.M_P_66_RATE);
        selected_.add(line(_core,MessagesDataEffteam.M_P_66_MULT_DAMAGE,multDamage.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_TEAM, _key,_input);
    }
    void buildEntity(EffectTeam _edited) {
        _edited.setForbiddingHealing(forbiddingHealing.isSelected());
        _edited.setProtectAgainstCh(protectAgainstCh.isSelected());
        _edited.setForbiddenBoost(forbiddenBoost.tryRet());
        _edited.setCancelChgtStatFoeTeam(cancelChgtStatFoeTeam.tryRet());
        _edited.setCancelChgtStatTeam(cancelChgtStatTeam.tryRet());
        _edited.setProtectAgainstLowStat(protectAgainstLowStat.tryRet());
        _edited.setProtectAgainstStatus(protectAgainstStatus.tryRet());
        _edited.setDisableFoeTeamStatus(disableFoeTeamStatus.tryRet());
        _edited.setUnusableMoves(unusableMoves.tryRet());
        _edited.setDisableFoeTeamEffects(disableFoeTeamEffects.tryRet());
        _edited.setMultStatistic(ConverterCommonMapUtil.buildIdMapStatisticRate(multStatistic.getList()));
        _edited.setMultStatisticFoe(ConverterCommonMapUtil.buildIdMapStatisticRate(multStatisticFoe.getList()));
        _edited.setMultDamage(ConverterCommonMapUtil.buildCategoryMults(multDamage.getList()));
    }
    void feedForm(EffectTeam _edited) {
        forbiddingHealing.setSelected(_edited.getForbiddingHealing());
        protectAgainstCh.setSelected(_edited.getProtectAgainstCh());
        forbiddenBoost.setupValue(_edited.getForbiddenBoost());
        cancelChgtStatFoeTeam.setupValue(_edited.getCancelChgtStatFoeTeam());
        cancelChgtStatTeam.setupValue(_edited.getCancelChgtStatTeam());
        protectAgainstLowStat.setupValue(_edited.getProtectAgainstLowStat());
        protectAgainstStatus.setupValue(_edited.getProtectAgainstStatus());
        disableFoeTeamStatus.setupValue(_edited.getDisableFoeTeamStatus());
        unusableMoves.setupValue(_edited.getUnusableMoves());
        disableFoeTeamEffects.setupValue(_edited.getDisableFoeTeamEffects());
        multStatistic.setupValues(new MapToEntriesListUtil<Statistic,Rate>().build(_edited.getMultStatistic()));
        multStatisticFoe.setupValues(new MapToEntriesListUtil<Statistic,Rate>().build(_edited.getMultStatisticFoe()));
        multDamage.setupValues(new MapToEntriesListUtil<CategoryMult,Rate>().build(_edited.getMultDamage()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public AbsCustCheckBox getForbiddingHealing() {
        return forbiddingHealing;
    }

    public AbsCustCheckBox getProtectAgainstCh() {
        return protectAgainstCh;
    }

    public GeneComponentModelLsStrSub<String,StringList> getProtectAgainstStatus() {
        return protectAgainstStatus;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDisableFoeTeamStatus() {
        return disableFoeTeamStatus;
    }

    public GeneComponentModelLsStrSub<String,StringList> getUnusableMoves() {
        return unusableMoves;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDisableFoeTeamEffects() {
        return disableFoeTeamEffects;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Rate> getMultStatistic() {
        return multStatistic;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public CrudGeneFormSimpleFormSub<CategoryMult, Rate> getMultDamage() {
        return multDamage;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getForbiddenBoost() {
        return forbiddenBoost;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCancelChgtStatFoeTeam() {
        return cancelChgtStatFoeTeam;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }
}
