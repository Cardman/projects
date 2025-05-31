package aiki.gui.components.editor;

import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelAbilityData extends GeneComponentModelEntity<AbilityData> {
    private AbilityData element;
    private CrudGeneFormSimpleElementSub<TypesDuo> breakFoeImmune;
    private CrudGeneFormSimpleElementSub<StatisticStatus> immuLowStatIfStatus;
    private CrudGeneFormSimpleFormSub<String,Rate> divideStatusRound;
    private CrudGeneFormSimpleFormSub<String,Rate> healHpByWeather;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageFoe;
    private CrudGeneFormSimpleFormSub<String,Rate> multPowerMovesTypesGlobal;
    private CrudGeneFormSimpleFormSub<String,Long> increasedPrio;
    private CrudGeneFormSimpleFormSub<String,Long> increasedPrioTypes;
    private CrudGeneFormSimpleFormSub<Statistic, Rate> multStatAlly;
    private CrudGeneFormSimpleFormSub<Statistic, String> multStat;
    private CrudGeneFormSimpleFormSub<Statistic, Long> bonusStatRank;
    private CrudGeneFormSimpleFormSub<Statistic, Long> boostStatRankEndRound;
    private CrudGeneFormSimpleFormSub<Statistic, Long> boostStatRankProtected;
    private CrudGeneFormSimpleFormSub<Statistic, Long> lowStatFoeHit;
    private CrudGeneFormSimpleFormSub<Statistic, Long> multStatIfKoFoe;
    private CrudGeneFormSimpleFormSub<Statistic, Long> multStatIfLowStat;
    private CrudGeneFormSimpleFormSub<String, String> chgtTypeByWeather;
    private CrudGeneFormSimpleFormSub<String, String> failStatus;
    private CrudGeneFormSimpleFormSub<String, String> forwardStatus;
    private CrudGeneFormSimpleFormSub<String, TypeDamageBoost> changingBoostTypes;
    private CrudGeneFormSimpleFormSub<WeatherType,Rate> healHpByTypeIfWeather;
    private CrudGeneFormSimpleFormSub<String, StringList> immuMoveTypesByWeather;
    private CrudGeneFormSimpleFormSub<String, StringList> immuStatus;
    private CrudGeneFormSimpleFormSub<String, StringList> immuStatusTypes;
    private CrudGeneFormSimpleFormSub<String, IdList<Statistic>> immuLowStatisTypes;
    private CrudGeneFormSimpleFormSub<StatisticType,Long> multStatIfDamgeType;
    private CrudGeneFormSimpleFormSub<StatisticStatus,Long> multStatIfStatutRank;
    private CrudGeneFormSimpleFormSub<StatisticCategory,Long> multStatIfDamageCat;
    private CrudGeneFormSimpleFormSub<StatisticCategory,Rate> multStatIfCat;
    private CrudGeneFormMonteCarloSub<String> singleStatus;
    private GeneComponentModelSubscribeString multPower;
    private GeneComponentModelSubscribeString multDamage;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> immuLowStat;
    private GeneComponentModelLsStrSub<Statistic,IdList<Statistic>> maxStatisticsIfCh;
    private GeneComponentModelLsStrSub<String,StringList> ignAbility;
    private GeneComponentModelLsStrSub<String,StringList> ignFoeTeamMove;
    private GeneComponentModelLsStrSub<String,StringList> immuAbility;
    private GeneComponentModelLsStrSub<String,StringList> immuAllyFromMoves;
    private GeneComponentModelLsStrSub<String,StringList> immuMove;
    private GeneComponentModelLsStrSub<String,StringList> immuStatusBeginRound;
    private GeneComponentModelLsStrSub<String,StringList> immuWeather;
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
    private GeneComponentModelLong decreaseNecStepsHatch;
    private GeneComponentModelLong nbUsedPp;
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
        page_.add(line(MessagesDataAbilityData.M_P_1_MAME,geneComponentModelSelectKey()));
        AbsPanel form_ = compoFactory_.newLineBox();
        breakFoeImmune = new CrudGeneFormSimpleElementSub<TypesDuo>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        breakFoeImmune.initForm(new DisplayEntryCustSubElementTypesDuoElt(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<TypesDuo>(new GeneComponentModelSubscribeTypesDuo(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_BREAK_FOE_IMMUNE_KEY,MessagesDataAbilityData.M_P_1_BREAK_FOE_IMMUNE_VALUE)));
        form_.add(line(MessagesDataAbilityData.M_P_1_BREAK_FOE_IMMUNE,breakFoeImmune.getGroup()));
        immuLowStatIfStatus = new CrudGeneFormSimpleElementSub<StatisticStatus>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        immuLowStatIfStatus.initForm(new DisplayEntryCustSubElementStatisticStatusElt(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticStatus>(new GeneComponentModelSubscribeStatisticStatus(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_IMMU_LOW_STAT_AFF_KEY,MessagesDataAbilityData.M_P_1_IMMU_LOW_STAT_AFF_VALUE)));
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_LOW_STAT_AFF,immuLowStatIfStatus.getGroup()));
        divideStatusRound = new CrudGeneFormSimpleFormSub<String, Rate>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        divideStatusRound.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(getSubscribedTranslationList().getFactorySt(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactorySt(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_DIVIDE_STATUS_ROUND_KEY,MessagesDataAbilityData.M_P_1_DIVIDE_STATUS_ROUND_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_DIVIDE_STATUS_ROUND,divideStatusRound.getGroup()));
        healHpByWeather = new CrudGeneFormSimpleFormSub<String, Rate>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        healHpByWeather.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryMv(),ConverterCommonMapUtil.defKeyEmpty()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_KEY,MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER,healHpByWeather.getGroup()));
        multDamageFoe = new CrudGeneFormSimpleFormSub<String, Rate>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        multDamageFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_DAMAGE_FOE_TYPE,MessagesDataAbilityData.M_P_1_MULT_DAMAGE_FOE_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_DAMAGE_FOE,multDamageFoe.getGroup()));
        multPowerMovesTypesGlobal = new CrudGeneFormSimpleFormSub<String, Rate>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        multPowerMovesTypesGlobal.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_POWER_MOVES_TYPES_GLOBAL_MOVE,MessagesDataAbilityData.M_P_1_MULT_POWER_MOVES_TYPES_GLOBAL_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_POWER_MOVES_TYPES_GLOBAL,multPowerMovesTypesGlobal.getGroup()));
        increasedPrio = new CrudGeneFormSimpleFormSub<String, Long>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        increasedPrio.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(getSubscribedTranslationList().getFactoryCa(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryCa(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_INCREASED_PRIO_CAT,MessagesDataAbilityData.M_P_1_INCREASED_PRIO_INC);
        form_.add(line(MessagesDataAbilityData.M_P_1_INCREASED_PRIO,increasedPrio.getGroup()));
        increasedPrioTypes = new CrudGeneFormSimpleFormSub<String, Long>(getCompoFactory(), getFacade(), getSubscribedTranslationList(), getFrame());
        increasedPrioTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_INCREASED_PRIO_TYPE_KEY,MessagesDataAbilityData.M_P_1_INCREASED_PRIO_TYPE_INC);
        form_.add(line(MessagesDataAbilityData.M_P_1_INCREASED_PRIO_TYPE,increasedPrioTypes.getGroup()));
        multStatAlly=new CrudGeneFormSimpleFormSub<Statistic,Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatAlly.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_ALLY_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_ALLY_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_ALLY,multStatAlly.getGroup()));
        multStat=new CrudGeneFormSimpleFormSub<Statistic,String>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<String>(new GeneComponentModelSubscribeString(getCompoFactory(),getFacade())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT,multStat.getGroup()));
        bonusStatRank=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        bonusStatRank.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_KEY,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK,bonusStatRank.getGroup()));
        boostStatRankEndRound=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        boostStatRankEndRound.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_END_ROUND_KEY,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_END_ROUND_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_END_ROUND,boostStatRankEndRound.getGroup()));
        boostStatRankProtected=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        boostStatRankProtected.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_PROTECTED_KEY,MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_PROTECTED_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_BONUS_STAT_RANK_PROTECTED,boostStatRankProtected.getGroup()));
        lowStatFoeHit=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        lowStatFoeHit.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_LOW_STAT_FOE_HIT_KEY,MessagesDataAbilityData.M_P_1_LOW_STAT_FOE_HIT_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_LOW_STAT_FOE_HIT,lowStatFoeHit.getGroup()));
        multStatIfKoFoe=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfKoFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_IF_KO_FOE_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_IF_KO_FOE_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_IF_KO_FOE,multStatIfKoFoe.getGroup()));
        multStatIfLowStat=new CrudGeneFormSimpleFormSub<Statistic,Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfLowStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_IF_LOW_STAT_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_IF_LOW_STAT_BOOST);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_IF_LOW_STAT,multStatIfLowStat.getGroup()));
        chgtTypeByWeather=new CrudGeneFormSimpleFormSub<String,String>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        chgtTypeByWeather.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryMv(), ConverterCommonMapUtil.defKeyEmpty()), new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryTy(),new StringMap<String>()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_CHGT_TYPE_BY_WEATHER_KEY,MessagesDataAbilityData.M_P_1_CHGT_TYPE_BY_WEATHER_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_CHGT_TYPE_BY_WEATHER,chgtTypeByWeather.getGroup()));
        failStatus=new CrudGeneFormSimpleFormSub<String,String>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        failStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactorySt(),getCompoFactory(),getFacade(), new StringMap<String>()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(getCompoFactory(),getFacade()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_FAIL_STATUS_KEY,MessagesDataAbilityData.M_P_1_FAIL_STATUS_COND);
        form_.add(line(MessagesDataAbilityData.M_P_1_FAIL_STATUS,failStatus.getGroup()));
        forwardStatus=new CrudGeneFormSimpleFormSub<String,String>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        forwardStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(getSubscribedTranslationList().getFactorySt(),getCompoFactory(),getFacade(), new StringMap<String>()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactorySt(),new StringMap<String>()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_FORWARD_STATUS_KEY,MessagesDataAbilityData.M_P_1_FORWARD_STATUS_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_FORWARD_STATUS,forwardStatus.getGroup()));
        changingBoostTypes=new CrudGeneFormSimpleFormSub<String,TypeDamageBoost>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        changingBoostTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,TypeDamageBoost>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<TypeDamageBoost>(new GeneComponentModelSubscribeTypeDamageBoost(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_CHANGING_BOOST_TYPES_NEW,MessagesDataAbilityData.M_P_1_CHANGING_BOOST_TYPES_RATE)),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_CHANGING_BOOST_TYPES_OLD,"");
        form_.add(line(MessagesDataAbilityData.M_P_1_CHANGING_BOOST_TYPES,changingBoostTypes.getGroup()));
        healHpByTypeIfWeather=new CrudGeneFormSimpleFormSub<WeatherType,Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        healHpByTypeIfWeather.initFormWithVal(new DisplayEntryCustSubElementWeatherType<Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList()), new GeneComponentModelSubscribeFactoryDirect<WeatherType>(new GeneComponentModelSubscribeWeatherType(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_TYPE_KEY,MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_TYPE_KEY_SEC)), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,"",MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_TYPE_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_HEAL_HP_BY_WEATHER_TYPE,healHpByTypeIfWeather.getGroup()));
        immuMoveTypesByWeather=new CrudGeneFormSimpleFormSub<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        immuMoveTypesByWeather.initFormWithVal(new DisplayEntryCustSubElementImpl<String,StringList>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryMv(), ConverterCommonMapUtil.defKeyEmpty()), new GeneComponentModelSubscribeFactorySelLs<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryTy(),new StringSubscribeBuilderUtilFactory()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_IMMU_MOVE_TYPES_WEATHER_KEY,MessagesDataAbilityData.M_P_1_IMMU_MOVE_TYPES_WEATHER_TYPE);
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_MOVE_TYPES_WEATHER,immuMoveTypesByWeather.getGroup()));
        immuStatus=new CrudGeneFormSimpleFormSub<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        immuStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String,StringList>(getSubscribedTranslationList().getFactoryMv(),getCompoFactory(),getFacade(), ConverterCommonMapUtil.defKeyEmpty()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryMv(), ConverterCommonMapUtil.defKeyEmpty()), new GeneComponentModelSubscribeFactorySelLs<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactorySt(),new StringSubscribeBuilderUtilFactory()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_IMMU_STATUS_KEY,MessagesDataAbilityData.M_P_1_IMMU_STATUS_STATUS);
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_STATUS,immuStatus.getGroup()));
        immuStatusTypes=new CrudGeneFormSimpleFormSub<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        immuStatusTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,StringList>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactorySelLs<String,StringList>(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactorySt(),new StringSubscribeBuilderUtilFactory()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_IMMU_STATUS_TYPE_KEY,MessagesDataAbilityData.M_P_1_IMMU_STATUS_TYPE_STATUS);
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_STATUS_TYPE,immuStatusTypes.getGroup()));
        immuLowStatisTypes=new CrudGeneFormSimpleFormSub<String,IdList<Statistic>>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        immuLowStatisTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,IdList<Statistic>>(getSubscribedTranslationList().getFactoryTy(),getCompoFactory(),getFacade(), new StringMap<String>()), buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactorySelLs<Statistic,IdList<Statistic>>(getCompoFactory(),getFacade(),getSubscribedTranslationList().getFactoryStat(),new IdSubscribeBuilderUtilFactory<Statistic>()),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_IMMU_LOW_STATIS_TYPES_KEY,MessagesDataAbilityData.M_P_1_IMMU_LOW_STATIS_TYPES_VALUE);
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_LOW_STATIS_TYPES,immuLowStatisTypes.getGroup()));
        multStatIfDamgeType = new CrudGeneFormSimpleFormSub<StatisticType, Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfDamgeType.initFormWithVal(new DisplayEntryCustSubElementStatisticType<Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticType>(new GeneComponentModelSubscribeStatisticType(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_TYPE_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_TYPE_KEY_SEC)),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,"",MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_TYPE_VAR);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_TYPE,multStatIfDamgeType.getGroup()));
        multStatIfStatutRank = new CrudGeneFormSimpleFormSub<StatisticStatus, Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfStatutRank.initFormWithVal(new DisplayEntryCustSubElementStatisticStatus<Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticStatus>(new GeneComponentModelSubscribeStatisticStatus(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_STATUS_RANK_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_STATUS_RANK_KEY_SEC)),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,"",MessagesDataAbilityData.M_P_1_MULT_STAT_STATUS_RANK_VAR);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_STATUS_RANK,multStatIfStatutRank.getGroup()));
        multStatIfDamageCat = new CrudGeneFormSimpleFormSub<StatisticCategory, Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfDamageCat.initFormWithVal(new DisplayEntryCustSubElementStatisticCategory<Long>(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticCategory>(new GeneComponentModelSubscribeStatisticCategory(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_CAT_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_CAT_KEY_SEC)),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(getCompoFactory())),MessagesPkBean.AB_DATA,"",MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_CAT_VAR);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_DAMAGE_CAT,multStatIfDamageCat.getGroup()));
        multStatIfCat = new CrudGeneFormSimpleFormSub<StatisticCategory, Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStatIfCat.initFormWithVal(new DisplayEntryCustSubElementStatisticCategory<Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticCategory>(new GeneComponentModelSubscribeStatisticCategory(getCompoFactory(),getFacade(),getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_MULT_STAT_CAT_KEY,MessagesDataAbilityData.M_P_1_MULT_STAT_CAT_KEY_SEC)),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())),MessagesPkBean.AB_DATA,"",MessagesDataAbilityData.M_P_1_MULT_STAT_CAT_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAT_CAT,multStatIfCat.getGroup()));
        singleStatus = ConverterCommonMapUtil.buildStatusLaw(getFrame(), getCompoFactory(), getFacade(), getSubscribedTranslationList(),MessagesPkBean.AB_DATA,MessagesDataAbilityData.M_P_1_SINGLE_STATUS_KEY,MessagesDataAbilityData.M_P_1_SINGLE_STATUS_RATE);
        form_.add(line(MessagesDataAbilityData.M_P_1_SINGLE_STATUS,singleStatus.getGroup()));
        multPower = new GeneComponentModelSubscribeString(getCompoFactory(),getFacade());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_POWER_INTRO,multPower.geneEnum()));
        multPower.addComplete();
        multDamage = new GeneComponentModelSubscribeString(getCompoFactory(),getFacade());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_DAMAGE_INTRO,multDamage.geneEnum()));
        multDamage.addComplete();
        immuLowStat=ConverterCommonMapUtil.buildStatisticsLs(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_LOW_STAT,immuLowStat.geneEnum()));
        maxStatisticsIfCh=ConverterCommonMapUtil.buildStatisticsLs(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_MAX_STATISTICS_IF_CH,maxStatisticsIfCh.geneEnum()));
        ignAbility=ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IGN_ABILITY,ignAbility.geneEnum()));
        ignFoeTeamMove=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IGN_FOE_TEAM_MOVE,ignFoeTeamMove.geneEnum()));
        immuAbility=ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_ABILITY,immuAbility.geneEnum()));
        immuAllyFromMoves=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_ALLY_FROM_MOVES,immuAllyFromMoves.geneEnum()));
        immuMove=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_MOVES,immuMove.geneEnum()));
        immuStatusBeginRound=ConverterCommonMapUtil.buildStatusList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_STATUS_BEGIN_ROUND,immuStatusBeginRound.geneEnum()));
        immuWeather=ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_WEATHERS,immuWeather.geneEnum()));
        typeForMoves = ConverterCommonMapUtil.buildTypeElt(getCompoFactory(),getFacade(),getSubscribedTranslationList(),ConverterCommonMapUtil.defKeyEmpty());
        form_.add(line(MessagesDataAbilityData.M_P_1_TYPE_FOR_MOVES_INTRO,typeForMoves.geneEnum()));
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList(), true))));
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_EFFECT,effectSending.getGroup()));
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList(), true))));
        form_.add(line(MessagesDataAbilityData.M_P_1_ENDROUND,effectEndRound.getGroup()));
        forbidUseBerryAgainstFoes=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_FORBID_BERRY_FOES,forbidUseBerryAgainstFoes));
        chgtTypeByDamage=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_CHGT_TYPE_BY_DAMAGE,chgtTypeByDamage));
        ignFoeStatisBoost=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IGN_FOE_SATIS_BOOST,ignFoeStatisBoost));
        immuCh=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_CH,immuCh));
        immuDamageTrappingMoves=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_DAMAGE_TRAPPING_MOVES,immuDamageTrappingMoves));
        immuDamageAllyMoves=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_DAMAGE_ALLY_MOVES,immuDamageAllyMoves));
        immuDamageRecoil=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_DAMAGE_RECOIL,immuDamageRecoil));
        immuRechargeRound=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_RECHARGE_ROUND,immuRechargeRound));
        slowing=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_SLOWING,slowing));
        immuSufferedDamageLowEff=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_IMMU_SUFFERED_DAMAGE_LOW_EFF,immuSufferedDamageLowEff));
        cancelSecEffectOther=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_CANCEL_SEC_EFFECT_OTHER,cancelSecEffectOther));
        cancelSecEffectOwner=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_CANCEL_SEC_EFFECT_OWNER,cancelSecEffectOwner));
        inflictingDamageInsteadOfSuffering=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_INFLICTING_DAMAGE_INSTEAD_SUFFERING,inflictingDamageInsteadOfSuffering));
        nbHits=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_NB_HITS,nbHits));
        breakProtection=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_BREAK_PROTECTION,breakProtection));
        plate=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_PLATE,plate));
        healedStatusBySwitch=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_HEALED_STATUS_BY_SWITCH,healedStatusBySwitch));
        achievedDisappearedPk=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_ACHIEVED_DISAPPEARED,achievedDisappearedPk));
        mumy=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_MUMY,mumy));
        copyMovesTypes=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_COPY_MOVE_TYPES,copyMovesTypes));
        reverseEffectsPowerMovesTypesGlobal=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_REVERSE_EFFECTS_POWER_GLOBAL,reverseEffectsPowerMovesTypesGlobal));
        takeItemByDamagingMove=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_ACHIEVED_DISAPPEARED,takeItemByDamagingMove));
        giveItemToAllyHavingUsed=compoFactory_.newCustCheckBox();
        form_.add(line(MessagesDataAbilityData.M_P_1_CANCEL_USING_ITEMS_TEAM,giveItemToAllyHavingUsed));
        decreaseNecStepsHatch = new GeneComponentModelLong(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_DECREASE_NEC_STEPS_HATCH_INTRO,decreaseNecStepsHatch.geneLong()));
        nbUsedPp = new GeneComponentModelLong(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_NB_USED_PP_INTRO,nbUsedPp.geneLong()));
        healHpWhileUsingBerry=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_HEAL_HP_WHILE_USING_BERRY_INTRO,healHpWhileUsingBerry.geneRate()));
        healedHpRateBySwitch=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_HEALED_HP_RATE_BY_SWITCH_INTRO,healedHpRateBySwitch.geneRate()));
        maxHpForUsingBerry=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MAX_HP_FOR_USING_BERRY_INTRO,maxHpForUsingBerry.geneRate()));
        multAllyDamage=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_ALLY_DAMAGE_INTRO,multAllyDamage.geneRate()));
        multDamageCh=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_DAMAGE_CH_INTRO,multDamageCh.geneRate()));
        multEvtRateCh=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_EVT_RATE_CH_INTRO,multEvtRateCh.geneRate()));
        multEvtRateSecEffectOwner=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_EVT_RATE_SEC_EFFECT_OWNER_INTRO,multEvtRateSecEffectOwner.geneRate()));
        multStab=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_STAB_INTRO,multStab.geneRate()));
        multSufferedDamageSuperEff=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_SUFFERED_DAMAGE_SUPER_EFF_INTRO,multSufferedDamageSuperEff.geneRate()));
        multVarBoost=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_MULT_VAR_BOOST_INTRO,multVarBoost.geneRate()));
        recoilDamageFoe=new GeneComponentModelRate(getCompoFactory());
        form_.add(line(MessagesDataAbilityData.M_P_1_RECOIL_DAMAGE_FOE_INTRO,recoilDamageFoe.geneRate()));
        sc_.setViewportView(form_);
        page_.add(sc_);
        element = Instances.newAbilityData();
        getFacade().getData().getAbilities().put(DataBase.EMPTY_STRING, element);
        return page_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return line(MessagesPkBean.AB_DATA,_key,_input);
    }

    @Override
    public EditedCrudPair<String,AbilityData> value() {
        AbilityData ent_ = element;
        ent_.setBreakFoeImmune(breakFoeImmune.getList());
        ent_.setImmuLowStatIfStatus(immuLowStatIfStatus.getList());
        ent_.setDivideStatusRound(ConverterCommonMapUtil.buildStringMapRate(divideStatusRound.getList()));
        ent_.setHealHpByWeather(ConverterCommonMapUtil.buildStringMapRate(healHpByWeather.getList()));
        ent_.setMultDamageFoe(ConverterCommonMapUtil.buildStringMapRate(multDamageFoe.getList()));
        ent_.setMultPowerMovesTypesGlobal(ConverterCommonMapUtil.buildStringMapRate(multPowerMovesTypesGlobal.getList()));
        ent_.setIncreasedPrio(ConverterCommonMapUtil.buildStringMapInteger(increasedPrio.getList()));
        ent_.setIncreasedPrioTypes(ConverterCommonMapUtil.buildStringMapInteger(increasedPrioTypes.getList()));
        ent_.setMultStatAlly(ConverterCommonMapUtil.buildIdMapStatisticRate(multStatAlly.getList()));
        ent_.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticString(multStat.getList()));
        ent_.setBonusStatRank(ConverterCommonMapUtil.buildIdMapStatisticInteger(bonusStatRank.getList()));
        ent_.setBoostStatRankEndRound(ConverterCommonMapUtil.buildIdMapStatisticInteger(boostStatRankEndRound.getList()));
        ent_.setBoostStatRankProtected(ConverterCommonMapUtil.buildIdMapStatisticInteger(boostStatRankProtected.getList()));
        ent_.setLowStatFoeHit(ConverterCommonMapUtil.buildIdMapStatisticInteger(lowStatFoeHit.getList()));
        ent_.setMultStatIfKoFoe(ConverterCommonMapUtil.buildIdMapStatisticInteger(multStatIfKoFoe.getList()));
        ent_.setMultStatIfLowStat(ConverterCommonMapUtil.buildIdMapStatisticInteger(multStatIfLowStat.getList()));
        ent_.setChgtTypeByWeather(ConverterCommonMapUtil.buildStringMapString(chgtTypeByWeather.getList()));
        ent_.setFailStatus(ConverterCommonMapUtil.buildStringMapString(failStatus.getList()));
        ent_.setForwardStatus(ConverterCommonMapUtil.buildStringMapString(forwardStatus.getList()));
        ent_.setChangingBoostTypes(ConverterCommonMapUtil.buildStringMapTypeDamageBoost(changingBoostTypes.getList()));
        ent_.setHealHpByTypeIfWeather(ConverterCommonMapUtil.buildWeatherTypes(healHpByTypeIfWeather.getList()));
        ent_.setImmuMoveTypesByWeather(ConverterCommonMapUtil.buildStringMapStringList(immuMoveTypesByWeather.getList()));
        ent_.setImmuStatus(ConverterCommonMapUtil.buildStringMapStringList(immuStatus.getList()));
        ent_.setImmuStatusTypes(ConverterCommonMapUtil.buildStringMapStringList(immuStatusTypes.getList()));
        ent_.setImmuLowStatisTypes(ConverterCommonMapUtil.buildStringMapIdListStatistic(immuLowStatisTypes.getList()));
        ent_.setMultStatIfDamgeType(ConverterCommonMapUtil.buildStatisticTypeByte(multStatIfDamgeType.getList()));
        ent_.setMultStatIfStatutRank(ConverterCommonMapUtil.buildStatisticStatusList(multStatIfStatutRank.getList()));
        ent_.setMultStatIfDamageCat(ConverterCommonMapUtil.buildStatisticCategoryByte(multStatIfDamageCat.getList()));
        ent_.setMultStatIfCat(ConverterCommonMapUtil.buildStatisticCategoryRate(multStatIfCat.getList()));
        ent_.setSingleStatus(ConverterCommonMapUtil.buildMonteCarloString(singleStatus.getList()));
        ent_.setMultPower(multPower.tryRet());
        ent_.setMultDamage(multDamage.tryRet());
        ent_.setImmuLowStat(immuLowStat.tryRet());
        ent_.setMaxStatisticsIfCh(maxStatisticsIfCh.tryRet());
        ent_.setIgnAbility(ignAbility.tryRet());
        ent_.setIgnFoeTeamMove(ignFoeTeamMove.tryRet());
        ent_.setImmuAbility(immuAbility.tryRet());
        ent_.setImmuAllyFromMoves(immuAllyFromMoves.tryRet());
        ent_.setImmuMove(immuMove.tryRet());
        ent_.setImmuStatusBeginRound(immuStatusBeginRound.tryRet());
        ent_.setImmuWeather(immuWeather.tryRet());
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
        ent_.setDecreaseNecStepsHatch(decreaseNecStepsHatch.valueLong());
        ent_.setNbUsedPp(nbUsedPp.valueLong());
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
        AbilityData ability_ = ConverterCommonMapUtil.copyAbilityData(_v.getValue());
        element = ability_;
        getFacade().getData().getAbilities().put(DataBase.EMPTY_STRING, ability_);
        breakFoeImmune.setupValues(ability_.getBreakFoeImmune());
        immuLowStatIfStatus.setupValues(ability_.getImmuLowStatIfStatus());
        divideStatusRound.setupValues(new MapToEntriesListUtil<String,Rate>().build(ability_.getDivideStatusRound()));
        healHpByWeather.setupValues(new MapToEntriesListUtil<String,Rate>().build(ability_.getHealHpByWeather()));
        multDamageFoe.setupValues(new MapToEntriesListUtil<String,Rate>().build(ability_.getMultDamageFoe()));
        multPowerMovesTypesGlobal.setupValues(new MapToEntriesListUtil<String,Rate>().build(ability_.getMultPowerMovesTypesGlobal()));
        increasedPrio.setupValues(new MapToEntriesListUtil<String,Long>().build(ability_.getIncreasedPrio()));
        increasedPrioTypes.setupValues(new MapToEntriesListUtil<String,Long>().build(ability_.getIncreasedPrioTypes()));
        multStatAlly.setupValues(new MapToEntriesListUtil<Statistic,Rate>().build(ability_.getMultStatAlly()));
        multStat.setupValues(new MapToEntriesListUtil<Statistic,String>().build(ability_.getMultStat()));
        bonusStatRank.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getBonusStatRank()));
        boostStatRankEndRound.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getBoostStatRankEndRound()));
        boostStatRankProtected.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getBoostStatRankProtected()));
        lowStatFoeHit.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getLowStatFoeHit()));
        multStatIfKoFoe.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getMultStatIfKoFoe()));
        multStatIfLowStat.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(ability_.getMultStatIfLowStat()));
        chgtTypeByWeather.setupValues(new MapToEntriesListUtil<String,String>().build(ability_.getChgtTypeByWeather()));
        failStatus.setupValues(new MapToEntriesListUtil<String,String>().build(ability_.getFailStatus()));
        forwardStatus.setupValues(new MapToEntriesListUtil<String,String>().build(ability_.getForwardStatus()));
        changingBoostTypes.setupValues(new MapToEntriesListUtil<String,TypeDamageBoost>().build(ability_.getChangingBoostTypes()));
        healHpByTypeIfWeather.setupValues(new MapToEntriesListUtil<WeatherType,Rate>().build(ability_.getHealHpByTypeIfWeather()));
        immuMoveTypesByWeather.setupValues(new MapToEntriesListUtil<String,StringList>().build(ability_.getImmuMoveTypesByWeather()));
        immuStatus.setupValues(new MapToEntriesListUtil<String,StringList>().build(ability_.getImmuStatus()));
        immuStatusTypes.setupValues(new MapToEntriesListUtil<String,StringList>().build(ability_.getImmuStatusTypes()));
        immuLowStatisTypes.setupValues(new MapToEntriesListUtil<String,IdList<Statistic>>().build(ability_.getImmuLowStatisTypes()));
        multStatIfDamgeType.setupValues(new MapToEntriesListUtil<StatisticType,Long>().build(ability_.getMultStatIfDamgeType()));
        multStatIfStatutRank.setupValues(new MapToEntriesListUtil<StatisticStatus,Long>().build(ability_.getMultStatIfStatutRank()));
        multStatIfDamageCat.setupValues(new MapToEntriesListUtil<StatisticCategory,Long>().build(ability_.getMultStatIfDamageCat()));
        multStatIfCat.setupValues(new MapToEntriesListUtil<StatisticCategory,Rate>().build(ability_.getMultStatIfCat()));
        singleStatus.setupValues(new MapToEntriesListUtil<String,LgInt>().build(ability_.getSingleStatus()));
        multPower.setupValue(ability_.getMultPower());
        multDamage.setupValue(ability_.getMultDamage());
        immuLowStat.setupValue(ability_.getImmuLowStat());
        maxStatisticsIfCh.setupValue(ability_.getMaxStatisticsIfCh());
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
        decreaseNecStepsHatch.valueLong(ability_.getDecreaseNecStepsHatch());
        nbUsedPp.valueLong(ability_.getNbUsedPp());
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
        ids_.addAllElts(breakFoeImmune.subscribeButtons());
        ids_.addAllElts(immuLowStatIfStatus.subscribeButtons());
        ids_.addAllElts(divideStatusRound.subscribeButtons());
        ids_.addAllElts(healHpByWeather.subscribeButtons());
        ids_.addAllElts(multDamageFoe.subscribeButtons());
        ids_.addAllElts(multPowerMovesTypesGlobal.subscribeButtons());
        ids_.addAllElts(increasedPrio.subscribeButtons());
        ids_.addAllElts(increasedPrioTypes.subscribeButtons());
        ids_.addAllElts(multStatAlly.subscribeButtons());
        ids_.add(new SubscribedTranslationRenamingValCrud<Statistic>(multStat.getCrud()));
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.addAllElts(bonusStatRank.subscribeButtons());
        ids_.addAllElts(boostStatRankEndRound.subscribeButtons());
        ids_.addAllElts(boostStatRankProtected.subscribeButtons());
        ids_.addAllElts(lowStatFoeHit.subscribeButtons());
        ids_.addAllElts(multStatIfKoFoe.subscribeButtons());
        ids_.addAllElts(multStatIfLowStat.subscribeButtons());
        ids_.addAllElts(chgtTypeByWeather.subscribeButtons());
        ids_.add(new SubscribedTranslationRenamingValCrud<String>(failStatus.getCrud()));
        ids_.addAllElts(failStatus.subscribeButtons());
        ids_.addAllElts(forwardStatus.subscribeButtons());
        ids_.addAllElts(changingBoostTypes.subscribeButtons());
        ids_.addAllElts(healHpByTypeIfWeather.subscribeButtons());
        ids_.addAllElts(immuMoveTypesByWeather.subscribeButtons());
        ids_.addAllElts(immuStatus.subscribeButtons());
        ids_.addAllElts(immuStatusTypes.subscribeButtons());
        ids_.addAllElts(immuLowStatisTypes.subscribeButtons());
        ids_.addAllElts(multStatIfDamgeType.subscribeButtons());
        ids_.addAllElts(multStatIfStatutRank.subscribeButtons());
        ids_.addAllElts(multStatIfDamageCat.subscribeButtons());
        ids_.addAllElts(multStatIfCat.subscribeButtons());
        ids_.addAllElts(singleStatus.subscribeButtons());
        ids_.addAllElts(getTypeForMoves().getSubs());
        ids_.addAllElts(immuLowStat.getSubs());
        ids_.addAllElts(maxStatisticsIfCh.getSubs());
        ids_.addAllElts(ignAbility.getSubs());
        ids_.addAllElts(ignFoeTeamMove.getSubs());
        ids_.addAllElts(immuAbility.getSubs());
        ids_.addAllElts(immuAllyFromMoves.getSubs());
        ids_.addAllElts(immuMove.getSubs());
        ids_.addAllElts(immuStatusBeginRound.getSubs());
        ids_.addAllElts(immuWeather.getSubs());
        ids_.addAllElts(multDamage.getSubs());
        ids_.addAllElts(multPower.getSubs());
        return ids_;
    }

    public CrudGeneFormSimpleFormSub<String, String> getFailStatus() {
        return failStatus;
    }

    public GeneComponentModelEltEnumSub<String> getTypeForMoves() {
        return typeForMoves;
    }

    public CrudGeneFormSimpleElementSub<StatisticStatus> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public CrudGeneFormSimpleFormSub<StatisticStatus, Long> getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public CrudGeneFormSimpleFormSub<StatisticCategory, Long> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public CrudGeneFormSimpleFormSub<String, TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public CrudGeneFormSimpleFormSub<WeatherType, Rate> getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public GeneComponentModelSubscribeString getMultDamage() {
        return multDamage;
    }

    public GeneComponentModelSubscribeString getMultPower() {
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

    public CrudGeneFormSimpleFormSub<String, StringList> getImmuStatusTypes() {
        return immuStatusTypes;
    }

    public CrudGeneFormSimpleFormSub<String, IdList<Statistic>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
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
