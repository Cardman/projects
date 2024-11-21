package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectGlobal {

    private AbsCustCheckBox weather;
    private AbsCustCheckBox canceledIfUsed;
    private AbsCustCheckBox reverseOrderOfSortBySpeed;
    private AbsCustCheckBox puttingKo;
    private AbsCustCheckBox unusableItem;
    private GeneComponentModelRate multAccuracy;

    private GeneComponentModelRate damageEndRound;

    private GeneComponentModelRate healingEndRound;

    private GeneComponentModelRate healingEndRoundGround;
    private GeneComponentModelRate multEffectLovingAlly;
    private GeneComponentModelLsStrSub<String> preventStatus;

    private GeneComponentModelLsStrSub<String> immuneTypes;

    private GeneComponentModelLsStrSub<String> disableImmuAgainstTypes;
    private GeneComponentModelLsStrSub<String> cancelProtectingAbilities;
    private GeneComponentModelLsStrSub<String> unusableMoves;

    private GeneComponentModelLsStrSub<String> movesUsedByTargetedFighters;

    private GeneComponentModelLsStrSub<String> cancelEffects;
    private GeneComponentModelLsStrSub<String> changedTypesTerrain;

    private CrudGeneFormSimpleFormSub<String,Rate> multDamagePrepaRound;

    private CrudGeneFormSimpleFormSub<String,Rate> multPowerMoves;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageTypesMoves;
    private CrudGeneFormSimpleFormSub<TypesDuo, Rate> efficiencyMoves;

    private CrudGeneFormSimpleFormSub<StatisticType,Rate> multStatIfContainsType;
    private GeneComponentModelLsStrSub<Statistic> cancelChgtStat;

    private GeneComponentModelEltEnumSub<String> invokedMoveTerrain;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        cancelChgtStat = ConverterCommonMapUtil.buildStatisticsLs(_core,_fac,_fact);
        selected_.add(cancelChgtStat.geneEnum());
        weather = _core.getCompoFactory().newCustCheckBox();
        selected_.add(weather);
        canceledIfUsed = _core.getCompoFactory().newCustCheckBox();
        selected_.add(canceledIfUsed);
        reverseOrderOfSortBySpeed = _core.getCompoFactory().newCustCheckBox();
        selected_.add(reverseOrderOfSortBySpeed);
        puttingKo = _core.getCompoFactory().newCustCheckBox();
        selected_.add(puttingKo);
        unusableItem = _core.getCompoFactory().newCustCheckBox();
        selected_.add(unusableItem);
        multAccuracy = new GeneComponentModelRate(_core);
        selected_.add(multAccuracy.geneRate(Rate.zero()));
        damageEndRound = new GeneComponentModelRate(_core);
        selected_.add(damageEndRound.geneRate(Rate.zero()));
        healingEndRound = new GeneComponentModelRate(_core);
        selected_.add(healingEndRound.geneRate(Rate.zero()));
        healingEndRoundGround = new GeneComponentModelRate(_core);
        selected_.add(healingEndRoundGround.geneRate(Rate.zero()));
        multEffectLovingAlly = new GeneComponentModelRate(_core);
        selected_.add(multEffectLovingAlly.geneRate(Rate.zero()));
        invokedMoveTerrain = ConverterCommonMapUtil.buildMvFull(_core,_fac,_fact,ConverterCommonMapUtil.defKeyEmpty(" "));
        selected_.add(invokedMoveTerrain.geneEnum());
        preventStatus = ConverterCommonMapUtil.buildStatusList(_core,_fac,_fact);
        selected_.add(preventStatus.geneEnum());
        immuneTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(immuneTypes.geneEnum());
        disableImmuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(disableImmuAgainstTypes.geneEnum());
        changedTypesTerrain = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(changedTypesTerrain.geneEnum());
        cancelProtectingAbilities = ConverterCommonMapUtil.buildAbilityList(_core,_fac,_fact);
        selected_.add(cancelProtectingAbilities.geneEnum());
        unusableMoves = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(unusableMoves.geneEnum());
        movesUsedByTargetedFighters = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(movesUsedByTargetedFighters.geneEnum());
        cancelEffects = ConverterCommonMapUtil.buildMoveList(_core,_fac,_fact);
        selected_.add(cancelEffects.geneEnum());
        multDamagePrepaRound = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        multDamagePrepaRound.initFormNoVal(new DisplayEntryCustSubElementImpl<String,Rate>(_fact.getFactoryTy(),_core,_fac, new StringMap<String>()),buildPart(_core,_fac,_fact.getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multDamagePrepaRound.getGroup());
        multDamageTypesMoves = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        multDamageTypesMoves.initFormNoVal(new DisplayEntryCustSubElementImpl<String, Rate>(_fact.getFactoryTy(),_core,_fac, new StringMap<String>()),buildPart(_core,_fac,_fact.getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multDamageTypesMoves.getGroup());
        multPowerMoves = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        multPowerMoves.initFormNoVal(new DisplayEntryCustSubElementImpl<String, Rate>(_fact.getFactoryMv(),_core,_fac, new StringMap<String>()),buildPart(_core,_fac,_fact.getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multPowerMoves.getGroup());
        multStatIfContainsType = new CrudGeneFormSimpleFormSub<StatisticType, Rate>(_core, _fac, _fact, _f);
        multStatIfContainsType.initForm(new DisplayEntryCustSubElementStatisticType(_core,_fac,_fact),new GeneComponentModelSubscribeFactoryDirect<StatisticType>(new GeneComponentModelSubscribeStatisticType(_core,_fac,_fact)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(multStatIfContainsType.getGroup());
        efficiencyMoves = new CrudGeneFormSimpleFormSub<TypesDuo, Rate>(_core, _fac, _fact, _f);
        efficiencyMoves.initForm(new DisplayEntryCustSubElementTypesDuo(_core,_fac,_fact),new GeneComponentModelSubscribeFactoryDirect<TypesDuo>(new GeneComponentModelSubscribeTypesDuo(_core,_fac,_fact)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(efficiencyMoves.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectGlobal _edited) {
        _edited.setWeather(weather.isSelected());
        _edited.setCanceledIfUsed(canceledIfUsed.isSelected());
        _edited.setReverseOrderOfSortBySpeed(reverseOrderOfSortBySpeed.isSelected());
        _edited.setPuttingKo(puttingKo.isSelected());
        _edited.setUnusableItem(unusableItem.isSelected());
        _edited.setMultAccuracy(multAccuracy.valueRate());
        _edited.setDamageEndRound(damageEndRound.valueRate());
        _edited.setHealingEndRound(healingEndRound.valueRate());
        _edited.setHealingEndRoundGround(healingEndRoundGround.valueRate());
        _edited.setMultEffectLovingAlly(multEffectLovingAlly.valueRate());
        _edited.setPreventStatus(new StringList(preventStatus.tryRet()));
        _edited.setImmuneTypes(new StringList(immuneTypes.tryRet()));
        _edited.setDisableImmuAgainstTypes(new StringList(disableImmuAgainstTypes.tryRet()));
        _edited.setCancelProtectingAbilities(new StringList(cancelProtectingAbilities.tryRet()));
        _edited.setUnusableMoves(new StringList(unusableMoves.tryRet()));
        _edited.setMovesUsedByTargetedFighters(new StringList(movesUsedByTargetedFighters.tryRet()));
        _edited.setCancelEffects(new StringList(cancelEffects.tryRet()));
        _edited.setChangedTypesTerrain(new StringList(changedTypesTerrain.tryRet()));
        _edited.setInvokedMoveTerrain(invokedMoveTerrain.tryRet());
        _edited.setCancelChgtStat(new IdList<Statistic>(cancelChgtStat.tryRet()));
        _edited.setMultDamagePrepaRound(ConverterCommonMapUtil.buildStringMapRate(multDamagePrepaRound.getList()));
        _edited.setMultPowerMoves(ConverterCommonMapUtil.buildStringMapRate(multPowerMoves.getList()));
        _edited.setMultDamageTypesMoves(ConverterCommonMapUtil.buildStringMapRate(multDamageTypesMoves.getList()));
        _edited.setEfficiencyMoves(ConverterCommonMapUtil.buildTypesDuos(efficiencyMoves.getList()));
        _edited.setMultStatIfContainsType(ConverterCommonMapUtil.buildStatisticTypeRate(multStatIfContainsType.getList()));
    }
    void feedForm(EffectGlobal _edited) {
        weather.setSelected(_edited.getWeather());
        canceledIfUsed.setSelected(_edited.getCanceledIfUsed());
        reverseOrderOfSortBySpeed.setSelected(_edited.getReverseOrderOfSortBySpeed());
        puttingKo.setSelected(_edited.getPuttingKo());
        unusableItem.setSelected(_edited.getUnusableItem());
        multAccuracy.valueRate(_edited.getMultAccuracy());
        damageEndRound.valueRate(_edited.getDamageEndRound());
        healingEndRound.valueRate(_edited.getHealingEndRound());
        healingEndRoundGround.valueRate(_edited.getHealingEndRoundGround());
        multEffectLovingAlly.valueRate(_edited.getMultEffectLovingAlly());
        preventStatus.setupValue(_edited.getPreventStatus());
        immuneTypes.setupValue(_edited.getImmuneTypes());
        disableImmuAgainstTypes.setupValue(_edited.getDisableImmuAgainstTypes());
        cancelProtectingAbilities.setupValue(_edited.getCancelProtectingAbilities());
        unusableMoves.setupValue(_edited.getUnusableMoves());
        movesUsedByTargetedFighters.setupValue(_edited.getMovesUsedByTargetedFighters());
        cancelEffects.setupValue(_edited.getCancelEffects());
        changedTypesTerrain.setupValue(_edited.getChangedTypesTerrain());
        invokedMoveTerrain.setupValue(_edited.getInvokedMoveTerrain());
        cancelChgtStat.setupValue(_edited.getCancelChgtStat());
        multDamagePrepaRound.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultDamagePrepaRound()));
        multPowerMoves.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultPowerMoves()));
        multDamageTypesMoves.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultDamageTypesMoves()));
        efficiencyMoves.setupValues(new MapToEntriesListUtil<TypesDuo, Rate>().build(_edited.getEfficiencyMoves()));
        multStatIfContainsType.setupValues(new MapToEntriesListUtil<StatisticType, Rate>().build(_edited.getMultStatIfContainsType()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public AbsCustCheckBox getWeather() {
        return weather;
    }

    public AbsCustCheckBox getCanceledIfUsed() {
        return canceledIfUsed;
    }

    public AbsCustCheckBox getPuttingKo() {
        return puttingKo;
    }

    public AbsCustCheckBox getReverseOrderOfSortBySpeed() {
        return reverseOrderOfSortBySpeed;
    }

    public AbsCustCheckBox getUnusableItem() {
        return unusableItem;
    }

    public GeneComponentModelLsStrSub<Statistic> getCancelChgtStat() {
        return cancelChgtStat;
    }

    public CrudGeneFormSimpleFormSub<StatisticType, Rate> getMultStatIfContainsType() {
        return multStatIfContainsType;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultDamagePrepaRound() {
        return multDamagePrepaRound;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultDamageTypesMoves() {
        return multDamageTypesMoves;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultPowerMoves() {
        return multPowerMoves;
    }

    public CrudGeneFormSimpleFormSub<TypesDuo, Rate> getEfficiencyMoves() {
        return efficiencyMoves;
    }

    public GeneComponentModelEltEnumSub<String> getInvokedMoveTerrain() {
        return invokedMoveTerrain;
    }

    public GeneComponentModelLsStrSub<String> getCancelEffects() {
        return cancelEffects;
    }

    public GeneComponentModelLsStrSub<String> getCancelProtectingAbilities() {
        return cancelProtectingAbilities;
    }

    public GeneComponentModelLsStrSub<String> getChangedTypesTerrain() {
        return changedTypesTerrain;
    }

    public GeneComponentModelLsStrSub<String> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public GeneComponentModelLsStrSub<String> getImmuneTypes() {
        return immuneTypes;
    }

    public GeneComponentModelLsStrSub<String> getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }

    public GeneComponentModelLsStrSub<String> getPreventStatus() {
        return preventStatus;
    }

    public GeneComponentModelLsStrSub<String> getUnusableMoves() {
        return unusableMoves;
    }
}
