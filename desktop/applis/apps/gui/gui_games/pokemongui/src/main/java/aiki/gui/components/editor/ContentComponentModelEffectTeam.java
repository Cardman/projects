package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
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
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        forbiddingHealing = _core.getCompoFactory().newCustCheckBox();
        selected_.add(forbiddingHealing);
        protectAgainstCh = _core.getCompoFactory().newCustCheckBox();
        selected_.add(protectAgainstCh);
        forbiddenBoost = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(forbiddenBoost.geneEnum());
        cancelChgtStatFoeTeam = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(cancelChgtStatFoeTeam.geneEnum());
        cancelChgtStatTeam = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(cancelChgtStatTeam.geneEnum());
        protectAgainstLowStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(protectAgainstLowStat.geneEnum());
         disableFoeTeamStatus = ConverterCommonMapUtil.buildStatusList(_core,_fac,_fact);
        selected_.add(disableFoeTeamStatus.geneEnum());
        protectAgainstStatus = ConverterCommonMapUtil.buildStatusList(_core,_fac,_fact);
        selected_.add(protectAgainstStatus.geneEnum());
        unusableMoves = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(unusableMoves.geneEnum());
        disableFoeTeamEffects = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(disableFoeTeamEffects.geneEnum());
        multStatistic = new CrudGeneFormSimpleFormSub<Statistic, Rate>(_core, _fac, _fact, _f);
        multStatistic.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multStatistic.getGroup());
        multStatisticFoe = new CrudGeneFormSimpleFormSub<Statistic, Rate>(_core, _fac, _fact, _f);
        multStatisticFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multStatisticFoe.getGroup());
        multDamage = new CrudGeneFormSimpleFormSub<CategoryMult, Rate>(_core, _fac, _fact, _f);
        multDamage.initFormWithVal(new DisplayEntryCustSubElementCategoryMult(_core,_fac,_fact),new GeneComponentModelSubscribeFactoryDirect<CategoryMult>(new GeneComponentModelSubscribeCategoryMult(_core,_fac,_fact)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multDamage.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
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
