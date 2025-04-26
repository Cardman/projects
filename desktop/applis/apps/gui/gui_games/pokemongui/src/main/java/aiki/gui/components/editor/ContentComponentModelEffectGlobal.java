package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
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
    private GeneComponentModelLsStrSub<String,StringList> preventStatus;

    private GeneComponentModelLsStrSub<String,StringList> immuneTypes;

    private GeneComponentModelLsStrSub<String,StringList> disableImmuAgainstTypes;
    private GeneComponentModelLsStrSub<String,StringList> cancelProtectingAbilities;
    private GeneComponentModelLsStrSub<String,StringList> unusableMoves;

    private GeneComponentModelLsStrSub<String,StringList> movesUsedByTargetedFighters;

    private GeneComponentModelLsStrSub<String,StringList> cancelEffects;
    private GeneComponentModelLsStrSub<String,StringList> changedTypesTerrain;

    private CrudGeneFormSimpleFormSub<String,Rate> multDamagePrepaRound;

    private CrudGeneFormSimpleFormSub<String,Rate> multPowerMoves;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageTypesMoves;
    private CrudGeneFormSimpleFormSub<TypesDuo, Rate> efficiencyMoves;

    private CrudGeneFormSimpleFormSub<StatisticType,Rate> multStatIfContainsType;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> cancelChgtStat;

    private GeneComponentModelEltEnumSub<String> invokedMoveTerrain;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        cancelChgtStat = ConverterCommonMapUtil.buildStatisticsLs(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_CANCEL_CHGT_STATIS,cancelChgtStat.geneEnum()));
        weather = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_IS_WEATHER,weather));
        canceledIfUsed = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_CANCEL_REUSE,canceledIfUsed));
        reverseOrderOfSortBySpeed = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_REVERSE_SPEED,reverseOrderOfSortBySpeed));
        puttingKo = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_PUTTING_KO,puttingKo));
        unusableItem = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_UNUSABLE_ITEM,unusableItem));
        multAccuracy = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_ACC_INTRO,multAccuracy.geneRate()));
        damageEndRound = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_DAMAGE_END_ROUND_INTRO,damageEndRound.geneRate()));
        healingEndRound = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_HEALING_END_ROUND_INTRO,healingEndRound.geneRate()));
        healingEndRoundGround = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_HEALING_END_ROUND_GROUND_INTRO,healingEndRoundGround.geneRate()));
        multEffectLovingAlly = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_LOVE_INTRO,multEffectLovingAlly.geneRate()));
        invokedMoveTerrain = ConverterCommonMapUtil.buildMvFull(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory(),ConverterCommonMapUtil.defKeyEmpty(" "));
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_INVOKED_MOVE,invokedMoveTerrain.geneEnum()));
        preventStatus = ConverterCommonMapUtil.buildStatusList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_FORBID_STATUS,preventStatus.geneEnum()));
        immuneTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_IMMUNE_TYPES,immuneTypes.geneEnum()));
        disableImmuAgainstTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_DISABLE_IMMU_TYPES,disableImmuAgainstTypes.geneEnum()));
        changedTypesTerrain = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_CHANGING_TYPE_INVOKED,changedTypesTerrain.geneEnum()));
        cancelProtectingAbilities = ConverterCommonMapUtil.buildAbilityList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_DISABLE_IMMU_ABILITIES,cancelProtectingAbilities.geneEnum()));
        unusableMoves = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_UNUSABLE_MOVES,unusableMoves.geneEnum()));
        movesUsedByTargetedFighters = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_DAMAGE_TYPE,movesUsedByTargetedFighters.geneEnum()));
        cancelEffects = ConverterCommonMapUtil.buildMoveList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_CANCEL_EFFECTS,cancelEffects.geneEnum()));
        multDamagePrepaRound = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamagePrepaRound.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_core.getFactory().getFactoryTy(),_core.getProgramInfos(),_core.getFacadeGame(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_DAMAGE_TYPE,MessagesDataEffglobal.M_P_49_RATE);
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_DAMAGE_TYPE,multDamagePrepaRound.getGroup()));
        multDamageTypesMoves = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamageTypesMoves.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactoryTy(),_core.getProgramInfos(),_core.getFacadeGame(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MOVE_TYPE,MessagesDataEffglobal.M_P_49_RATE_DAMAGE);
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_POWER_TYPE,multDamageTypesMoves.getGroup()));
        multPowerMoves = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multPowerMoves.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactoryMv(),_core.getProgramInfos(),_core.getFacadeGame(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_MOVE,MessagesDataEffglobal.M_P_49_RATE_DAMAGE);
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_POWER_MOVE,multPowerMoves.getGroup()));
        multStatIfContainsType = new CrudGeneFormSimpleFormSub<StatisticType, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multStatIfContainsType.initFormWithVal(new DisplayEntryCustSubElementStatisticType<Rate>(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory()),new GeneComponentModelSubscribeFactoryDirect<StatisticType>(new GeneComponentModelSubscribeStatisticType(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_STATISTIC,MessagesDataEffglobal.M_P_49_POKEMON_TYPE_STAT)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_GLOBAL,"",MessagesDataEffglobal.M_P_49_RATE_POKEMON_STATISTIC);
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_MULT_STAT_TYPE,multStatIfContainsType.getGroup()));
        efficiencyMoves = new CrudGeneFormSimpleFormSub<TypesDuo, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        efficiencyMoves.initFormWithVal(new DisplayEntryCustSubElementTypesDuo(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory()),new GeneComponentModelSubscribeFactoryDirect<TypesDuo>(new GeneComponentModelSubscribeTypesDuo(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory(),MessagesPkBean.EFF_GLOBAL,MessagesDataEffglobal.M_P_49_DAMAGE_TYPE,MessagesDataEffglobal.M_P_49_POKEMON_TYPE)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_GLOBAL,"",MessagesDataEffglobal.M_P_49_EFFICIENCY);
        selected_.add(line(_core,MessagesDataEffglobal.M_P_49_EFFICIENCY_TABLE,efficiencyMoves.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_GLOBAL, _key,_input);
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
        _edited.setPreventStatus(preventStatus.tryRet());
        _edited.setImmuneTypes(immuneTypes.tryRet());
        _edited.setDisableImmuAgainstTypes(disableImmuAgainstTypes.tryRet());
        _edited.setCancelProtectingAbilities(cancelProtectingAbilities.tryRet());
        _edited.setUnusableMoves(unusableMoves.tryRet());
        _edited.setMovesUsedByTargetedFighters(movesUsedByTargetedFighters.tryRet());
        _edited.setCancelEffects(cancelEffects.tryRet());
        _edited.setChangedTypesTerrain(changedTypesTerrain.tryRet());
        _edited.setInvokedMoveTerrain(invokedMoveTerrain.tryRet());
        _edited.setCancelChgtStat(cancelChgtStat.tryRet());
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

    public GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> getCancelChgtStat() {
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

    public GeneComponentModelLsStrSub<String,StringList> getCancelEffects() {
        return cancelEffects;
    }

    public GeneComponentModelLsStrSub<String,StringList> getCancelProtectingAbilities() {
        return cancelProtectingAbilities;
    }

    public GeneComponentModelLsStrSub<String,StringList> getChangedTypesTerrain() {
        return changedTypesTerrain;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getImmuneTypes() {
        return immuneTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getMovesUsedByTargetedFighters() {
        return movesUsedByTargetedFighters;
    }

    public GeneComponentModelLsStrSub<String,StringList> getPreventStatus() {
        return preventStatus;
    }

    public GeneComponentModelLsStrSub<String,StringList> getUnusableMoves() {
        return unusableMoves;
    }
}
