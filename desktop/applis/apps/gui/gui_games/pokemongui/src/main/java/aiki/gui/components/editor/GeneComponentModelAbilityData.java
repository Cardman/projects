package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelAbilityData extends GeneComponentModelEntity<AbilityData> {
    private CrudGeneFormSimpleFormSub<Statistic, Byte> bonusStatRank;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> boostStatRankEndRound;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> boostStatRankProtected;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> lowStatFoeHit;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> multStatIfKoFoe;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> multStatIfLowStat;
    private GeneComponentModelString multPower;
    private GeneComponentModelString multDamage;
    private GeneComponentModelLsStrSub<String> ignAbility;
    private GeneComponentModelLsStrSub<String> ignFoeTeamMove;
    private GeneComponentModelLsStrSub<String> immuAbility;
    private GeneComponentModelLsStrSub<String> immuAllyFromMoves;
    private GeneComponentModelLsStrSub<String> immuMove;
    private GeneComponentModelLsStrSub<String> immuStatusBeginRound;
    private GeneComponentModelLsStrSub<String> immuWeather;
    private GeneComponentModelEltEnumSub<String> typeForMoves;
    private GeneComponentModelRate healHpWhileUsingBerry;
    private GeneComponentModelRate healedHpRateBySwitch;
    private GeneComponentModelRate maxHpForUsingBerry;
    private GeneComponentModelRate multAllyDamage;
    private GeneComponentModelRate multDamageCh;
    private GeneComponentModelRate multEvtRateCh;
    private GeneComponentModelRate multEvtRateSecEffectOwner;
    private GeneComponentModelRate multStab;
    private GeneComponentModelRate multSufferedDamageSuperEff;
    private GeneComponentModelRate multVarBoost;
    private GeneComponentModelRate recoilDamageFoe;
    private GeneComponentModelInt decreaseNecStepsHatch;
    private GeneComponentModelInt nbUsedPp;
    private AbsCustCheckBox forbidUseBerryAgainstFoes;
    private AbsCustCheckBox chgtTypeByDamage;
    private AbsCustCheckBox ignFoeStatisBoost;
    private AbsCustCheckBox immuCh;
    private AbsCustCheckBox immuDamageTrappingMoves;
    private AbsCustCheckBox immuDamageAllyMoves;
    private AbsCustCheckBox immuDamageRecoil;
    private AbsCustCheckBox immuRechargeRound;
    private AbsCustCheckBox slowing;
    private AbsCustCheckBox immuSufferedDamageLowEff;
    private AbsCustCheckBox cancelSecEffectOther;
    private AbsCustCheckBox cancelSecEffectOwner;
    private AbsCustCheckBox inflictingDamageInsteadOfSuffering;
    private AbsCustCheckBox nbHits;
    private AbsCustCheckBox breakProtection;
    private AbsCustCheckBox plate;
    private AbsCustCheckBox healedStatusBySwitch;
    private AbsCustCheckBox achievedDisappearedPk;
    private AbsCustCheckBox mumy;
    private AbsCustCheckBox copyMovesTypes;
    private AbsCustCheckBox reverseEffectsPowerMovesTypesGlobal;
    private AbsCustCheckBox takeItemByDamagingMove;
    private AbsCustCheckBox giveItemToAllyHavingUsed;
    private CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> effectSending;
    private CrudGeneFormSimpleElementSub<EffectEndRound> effectEndRound;

    public GeneComponentModelAbilityData(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryAb factoryAb_ = getSubscribedTranslationList().getFactoryAb();
        buildKey(_select,factoryAb_,factoryAb_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        bonusStatRank=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        bonusStatRank.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(bonusStatRank.getGroup());
        boostStatRankEndRound=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        boostStatRankEndRound.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(boostStatRankEndRound.getGroup());
        boostStatRankProtected=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        boostStatRankProtected.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(boostStatRankProtected.getGroup());
        lowStatFoeHit=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        lowStatFoeHit.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(lowStatFoeHit.getGroup());
        multStatIfKoFoe=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfKoFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(multStatIfKoFoe.getGroup());
        multStatIfLowStat=new CrudGeneFormSimpleFormSub<Statistic,Byte>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfLowStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(getCompoFactory())));
        form_.add(multStatIfLowStat.getGroup());
        multPower = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        form_.add(multPower.geneString());
        multDamage = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        form_.add(multDamage.geneString());
        ignAbility=ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(ignAbility.geneEnum());
        ignFoeTeamMove=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(ignFoeTeamMove.geneEnum());
        immuAbility=ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(immuAbility.geneEnum());
        immuAllyFromMoves=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(immuAllyFromMoves.geneEnum());
        immuMove=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(immuMove.geneEnum());
        immuStatusBeginRound=ConverterCommonMapUtil.buildStatusList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(immuStatusBeginRound.geneEnum());
        immuWeather=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(immuWeather.geneEnum());
        typeForMoves = ConverterCommonMapUtil.buildTypeElt(getCompoFactory(),getFacade(),getSubscribedTranslationList(),ConverterCommonMapUtil.defKeyEmpty(" "));
        form_.add(typeForMoves.geneEnum());
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectSending.getGroup());
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectEndRound.getGroup());
        forbidUseBerryAgainstFoes=compoFactory_.newCustCheckBox();
        form_.add(forbidUseBerryAgainstFoes);
        chgtTypeByDamage=compoFactory_.newCustCheckBox();
        form_.add(chgtTypeByDamage);
        ignFoeStatisBoost=compoFactory_.newCustCheckBox();
        form_.add(ignFoeStatisBoost);
        immuCh=compoFactory_.newCustCheckBox();
        form_.add(immuCh);
        immuDamageTrappingMoves=compoFactory_.newCustCheckBox();
        form_.add(immuDamageTrappingMoves);
        immuDamageAllyMoves=compoFactory_.newCustCheckBox();
        form_.add(immuDamageAllyMoves);
        immuDamageRecoil=compoFactory_.newCustCheckBox();
        form_.add(immuDamageRecoil);
        immuRechargeRound=compoFactory_.newCustCheckBox();
        form_.add(immuRechargeRound);
        slowing=compoFactory_.newCustCheckBox();
        form_.add(slowing);
        immuSufferedDamageLowEff=compoFactory_.newCustCheckBox();
        form_.add(immuSufferedDamageLowEff);
        cancelSecEffectOther=compoFactory_.newCustCheckBox();
        form_.add(cancelSecEffectOther);
        cancelSecEffectOwner=compoFactory_.newCustCheckBox();
        form_.add(cancelSecEffectOwner);
        inflictingDamageInsteadOfSuffering=compoFactory_.newCustCheckBox();
        form_.add(inflictingDamageInsteadOfSuffering);
        nbHits=compoFactory_.newCustCheckBox();
        form_.add(nbHits);
        breakProtection=compoFactory_.newCustCheckBox();
        form_.add(breakProtection);
        plate=compoFactory_.newCustCheckBox();
        form_.add(plate);
        healedStatusBySwitch=compoFactory_.newCustCheckBox();
        form_.add(healedStatusBySwitch);
        achievedDisappearedPk=compoFactory_.newCustCheckBox();
        form_.add(achievedDisappearedPk);
        mumy=compoFactory_.newCustCheckBox();
        form_.add(mumy);
        copyMovesTypes=compoFactory_.newCustCheckBox();
        form_.add(copyMovesTypes);
        reverseEffectsPowerMovesTypesGlobal=compoFactory_.newCustCheckBox();
        form_.add(reverseEffectsPowerMovesTypesGlobal);
        takeItemByDamagingMove=compoFactory_.newCustCheckBox();
        form_.add(takeItemByDamagingMove);
        giveItemToAllyHavingUsed=compoFactory_.newCustCheckBox();
        form_.add(giveItemToAllyHavingUsed);
        decreaseNecStepsHatch = new GeneComponentModelInt(getCompoFactory());
        form_.add(decreaseNecStepsHatch.geneInt());
        nbUsedPp = new GeneComponentModelInt(getCompoFactory());
        form_.add(nbUsedPp.geneInt());
        healHpWhileUsingBerry=new GeneComponentModelRate(getCompoFactory());
        form_.add(healHpWhileUsingBerry.geneRate(Rate.zero()));
        healedHpRateBySwitch=new GeneComponentModelRate(getCompoFactory());
        form_.add(healedHpRateBySwitch.geneRate(Rate.zero()));
        maxHpForUsingBerry=new GeneComponentModelRate(getCompoFactory());
        form_.add(maxHpForUsingBerry.geneRate(Rate.zero()));
        multAllyDamage=new GeneComponentModelRate(getCompoFactory());
        form_.add(multAllyDamage.geneRate(Rate.zero()));
        multDamageCh=new GeneComponentModelRate(getCompoFactory());
        form_.add(multDamageCh.geneRate(Rate.zero()));
        multEvtRateCh=new GeneComponentModelRate(getCompoFactory());
        form_.add(multEvtRateCh.geneRate(Rate.zero()));
        multEvtRateSecEffectOwner=new GeneComponentModelRate(getCompoFactory());
        form_.add(multEvtRateSecEffectOwner.geneRate(Rate.zero()));
        multStab=new GeneComponentModelRate(getCompoFactory());
        form_.add(multStab.geneRate(Rate.zero()));
        multSufferedDamageSuperEff=new GeneComponentModelRate(getCompoFactory());
        form_.add(multSufferedDamageSuperEff.geneRate(Rate.zero()));
        multVarBoost=new GeneComponentModelRate(getCompoFactory());
        form_.add(multVarBoost.geneRate(Rate.zero()));
        recoilDamageFoe=new GeneComponentModelRate(getCompoFactory());
        form_.add(recoilDamageFoe.geneRate(Rate.zero()));
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,AbilityData> value() {
        AbilityData ent_ = Instances.newAbilityData();
        ent_.setBonusStatRank(ConverterCommonMapUtil.buildIdMapStatisticByte(bonusStatRank.getList()));
        ent_.setBoostStatRankEndRound(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatRankEndRound.getList()));
        ent_.setBoostStatRankProtected(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatRankProtected.getList()));
        ent_.setLowStatFoeHit(ConverterCommonMapUtil.buildIdMapStatisticByte(lowStatFoeHit.getList()));
        ent_.setMultStatIfKoFoe(ConverterCommonMapUtil.buildIdMapStatisticByte(multStatIfKoFoe.getList()));
        ent_.setMultStatIfLowStat(ConverterCommonMapUtil.buildIdMapStatisticByte(multStatIfLowStat.getList()));
        ent_.setMultPower(multPower.valueString());
        ent_.setMultDamage(multDamage.valueString());
        ent_.setIgnAbility(new StringList(ignAbility.tryRet()));
        ent_.setIgnFoeTeamMove(new StringList(ignFoeTeamMove.tryRet()));
        ent_.setImmuAbility(new StringList(immuAbility.tryRet()));
        ent_.setImmuAllyFromMoves(new StringList(immuAllyFromMoves.tryRet()));
        ent_.setImmuMove(new StringList(immuMove.tryRet()));
        ent_.setImmuStatusBeginRound(new StringList(immuStatusBeginRound.tryRet()));
        ent_.setImmuWeather(new StringList(immuWeather.tryRet()));
        ent_.setTypeForMoves(typeForMoves.tryRet());
        ent_.setEffectSending(effectSending.getList());
        ent_.setEffectEndRound(effectEndRound.getList());
        ent_.setForbidUseBerryAgainstFoes(forbidUseBerryAgainstFoes.isSelected());
        ent_.setChgtTypeByDamage(chgtTypeByDamage.isSelected());
        ent_.setIgnFoeStatisBoost(ignFoeStatisBoost.isSelected());
        ent_.setImmuCh(immuCh.isSelected());
        ent_.setImmuDamageTrappingMoves(immuDamageTrappingMoves.isSelected());
        ent_.setImmuDamageAllyMoves(immuDamageAllyMoves.isSelected());
        ent_.setImmuDamageRecoil(immuDamageRecoil.isSelected());
        ent_.setImmuRechargeRound(immuRechargeRound.isSelected());
        ent_.setSlowing(slowing.isSelected());
        ent_.setImmuSufferedDamageLowEff(immuSufferedDamageLowEff.isSelected());
        ent_.setCancelSecEffectOther(cancelSecEffectOther.isSelected());
        ent_.setCancelSecEffectOwner(cancelSecEffectOwner.isSelected());
        ent_.setInflictingDamageInsteadOfSuffering(inflictingDamageInsteadOfSuffering.isSelected());
        ent_.setNbHits(nbHits.isSelected());
        ent_.setBreakProtection(breakProtection.isSelected());
        ent_.setPlate(plate.isSelected());
        ent_.setHealedStatusBySwitch(healedStatusBySwitch.isSelected());
        ent_.setAchievedDisappearedPk(achievedDisappearedPk.isSelected());
        ent_.setMumy(mumy.isSelected());
        ent_.setCopyMovesTypes(copyMovesTypes.isSelected());
        ent_.setReverseEffectsPowerMovesTypesGlobal(reverseEffectsPowerMovesTypesGlobal.isSelected());
        ent_.setTakeItemByDamagingMove(takeItemByDamagingMove.isSelected());
        ent_.setGiveItemToAllyHavingUsed(giveItemToAllyHavingUsed.isSelected());
        ent_.setDecreaseNecStepsHatch(decreaseNecStepsHatch.valueInt());
        ent_.setNbUsedPp(nbUsedPp.valueInt());
        ent_.setHealHpWhileUsingBerry(healHpWhileUsingBerry.valueRate());
        ent_.setHealedHpRateBySwitch(healedHpRateBySwitch.valueRate());
        ent_.setMaxHpForUsingBerry(maxHpForUsingBerry.valueRate());
        ent_.setMultAllyDamage(multAllyDamage.valueRate());
        ent_.setMultDamageCh(multDamageCh.valueRate());
        ent_.setMultEvtRateCh(multEvtRateCh.valueRate());
        ent_.setMultEvtRateSecEffectOwner(multEvtRateSecEffectOwner.valueRate());
        ent_.setMultStab(multStab.valueRate());
        ent_.setMultSufferedDamageSuperEff(multSufferedDamageSuperEff.valueRate());
        ent_.setMultVarBoost(multVarBoost.valueRate());
        ent_.setRecoilDamageFoe(recoilDamageFoe.valueRate());
        return new EditedCrudPair<String, AbilityData>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,AbilityData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        AbilityData ability_ = _v.getValue();
        bonusStatRank.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getBonusStatRank()));
        boostStatRankEndRound.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getBoostStatRankEndRound()));
        boostStatRankProtected.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getBoostStatRankProtected()));
        lowStatFoeHit.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getLowStatFoeHit()));
        multStatIfKoFoe.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getMultStatIfKoFoe()));
        multStatIfLowStat.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(ability_.getMultStatIfLowStat()));
        multPower.valueString(ability_.getMultPower());
        multDamage.valueString(ability_.getMultDamage());
        ignAbility.setupValue(ability_.getIgnAbility());
        ignFoeTeamMove.setupValue(ability_.getIgnFoeTeamMove());
        immuAbility.setupValue(ability_.getImmuAbility());
        immuAllyFromMoves.setupValue(ability_.getImmuAllyFromMoves());
        immuMove.setupValue(ability_.getImmuMove());
        immuStatusBeginRound.setupValue(ability_.getImmuStatusBeginRound());
        immuWeather.setupValue(ability_.getImmuWeather());
        typeForMoves.setupValue(ability_.getTypeForMoves());
        effectSending.setupValues(ability_.getEffectSending());
        effectEndRound.setupValues(ability_.getEffectEndRound());
        forbidUseBerryAgainstFoes.setSelected(ability_.isForbidUseBerryAgainstFoes());
        chgtTypeByDamage.setSelected(ability_.isChgtTypeByDamage());
        ignFoeStatisBoost.setSelected(ability_.isIgnFoeStatisBoost());
        immuCh.setSelected(ability_.isImmuCh());
        immuDamageTrappingMoves.setSelected(ability_.isImmuDamageTrappingMoves());
        immuDamageAllyMoves.setSelected(ability_.isImmuDamageAllyMoves());
        immuDamageRecoil.setSelected(ability_.isImmuDamageRecoil());
        immuRechargeRound.setSelected(ability_.isImmuRechargeRound());
        slowing.setSelected(ability_.isSlowing());
        immuSufferedDamageLowEff.setSelected(ability_.isImmuSufferedDamageLowEff());
        cancelSecEffectOther.setSelected(ability_.isCancelSecEffectOther());
        cancelSecEffectOwner.setSelected(ability_.isCancelSecEffectOwner());
        inflictingDamageInsteadOfSuffering.setSelected(ability_.isInflictingDamageInsteadOfSuffering());
        nbHits.setSelected(ability_.isNbHits());
        breakProtection.setSelected(ability_.isBreakProtection());
        plate.setSelected(ability_.isPlate());
        healedStatusBySwitch.setSelected(ability_.isHealedStatusBySwitch());
        achievedDisappearedPk.setSelected(ability_.isAchievedDisappearedPk());
        mumy.setSelected(ability_.isMumy());
        copyMovesTypes.setSelected(ability_.isCopyMovesTypes());
        reverseEffectsPowerMovesTypesGlobal.setSelected(ability_.isReverseEffectsPowerMovesTypesGlobal());
        takeItemByDamagingMove.setSelected(ability_.isTakeItemByDamagingMove());
        giveItemToAllyHavingUsed.setSelected(ability_.isGiveItemToAllyHavingUsed());
        decreaseNecStepsHatch.valueInt(ability_.getDecreaseNecStepsHatch());
        nbUsedPp.valueInt(ability_.getNbUsedPp());
        healHpWhileUsingBerry.valueRate(ability_.getHealHpWhileUsingBerry());
        healedHpRateBySwitch.valueRate(ability_.getHealedHpRateBySwitch());
        maxHpForUsingBerry.valueRate(ability_.getMaxHpForUsingBerry());
        multAllyDamage.valueRate(ability_.getMultAllyDamage());
        multDamageCh.valueRate(ability_.getMultDamageCh());
        multEvtRateCh.valueRate(ability_.getMultEvtRateCh());
        multEvtRateSecEffectOwner.valueRate(ability_.getMultEvtRateSecEffectOwner());
        multStab.valueRate(ability_.getMultStab());
        multSufferedDamageSuperEff.valueRate(ability_.getMultSufferedDamageSuperEff());
        multVarBoost.valueRate(ability_.getMultVarBoost());
        recoilDamageFoe.valueRate(ability_.getRecoilDamageFoe());
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(bonusStatRank.subscribeButtons());
        ids_.addAllElts(boostStatRankEndRound.subscribeButtons());
        ids_.addAllElts(boostStatRankProtected.subscribeButtons());
        ids_.addAllElts(lowStatFoeHit.subscribeButtons());
        ids_.addAllElts(multStatIfKoFoe.subscribeButtons());
        ids_.addAllElts(multStatIfLowStat.subscribeButtons());
        ids_.addAllElts(getTypeForMoves().getSubs());
        ids_.addAllElts(ignAbility.getSubs());
        ids_.addAllElts(ignFoeTeamMove.getSubs());
        ids_.addAllElts(immuAbility.getSubs());
        ids_.addAllElts(immuAllyFromMoves.getSubs());
        ids_.addAllElts(immuMove.getSubs());
        ids_.addAllElts(immuStatusBeginRound.getSubs());
        ids_.addAllElts(immuWeather.getSubs());
        return ids_;
    }

    public GeneComponentModelEltEnumSub<String> getTypeForMoves() {
        return typeForMoves;
    }

    public GeneComponentModelString getMultDamage() {
        return multDamage;
    }

    public GeneComponentModelString getMultPower() {
        return multPower;
    }

    public CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> getEffectSending() {
        return effectSending;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }
    public AbsCustCheckBox getForbidUseBerryAgainstFoes(){
        return forbidUseBerryAgainstFoes;
    }

    public AbsCustCheckBox getChgtTypeByDamage(){
        return chgtTypeByDamage;
    }

    public AbsCustCheckBox getIgnFoeStatisBoost(){
        return ignFoeStatisBoost;
    }

    public AbsCustCheckBox getImmuCh(){
        return immuCh;
    }

    public AbsCustCheckBox getImmuDamageTrappingMoves(){
        return immuDamageTrappingMoves;
    }

    public AbsCustCheckBox getImmuDamageAllyMoves(){
        return immuDamageAllyMoves;
    }

    public AbsCustCheckBox getImmuDamageRecoil(){
        return immuDamageRecoil;
    }

    public AbsCustCheckBox getImmuRechargeRound(){
        return immuRechargeRound;
    }

    public AbsCustCheckBox getSlowing(){
        return slowing;
    }

    public AbsCustCheckBox getImmuSufferedDamageLowEff(){
        return immuSufferedDamageLowEff;
    }

    public AbsCustCheckBox getCancelSecEffectOther(){
        return cancelSecEffectOther;
    }

    public AbsCustCheckBox getCancelSecEffectOwner(){
        return cancelSecEffectOwner;
    }

    public AbsCustCheckBox getInflictingDamageInsteadOfSuffering(){
        return inflictingDamageInsteadOfSuffering;
    }

    public AbsCustCheckBox getNbHits(){
        return nbHits;
    }

    public AbsCustCheckBox getBreakProtection(){
        return breakProtection;
    }

    public AbsCustCheckBox getPlate(){
        return plate;
    }

    public AbsCustCheckBox getHealedStatusBySwitch(){
        return healedStatusBySwitch;
    }

    public AbsCustCheckBox getAchievedDisappearedPk(){
        return achievedDisappearedPk;
    }

    public AbsCustCheckBox getMumy(){
        return mumy;
    }

    public AbsCustCheckBox getCopyMovesTypes(){
        return copyMovesTypes;
    }

    public AbsCustCheckBox getReverseEffectsPowerMovesTypesGlobal(){
        return reverseEffectsPowerMovesTypesGlobal;
    }

    public AbsCustCheckBox getTakeItemByDamagingMove(){
        return takeItemByDamagingMove;
    }

    public AbsCustCheckBox getGiveItemToAllyHavingUsed(){
        return giveItemToAllyHavingUsed;
    }

}
