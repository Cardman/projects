package aiki.beans.abilities;

import aiki.beans.CommonBean;
import aiki.beans.EndRoundCommon;
import aiki.beans.facade.comparators.ComparatorStatusStatistic;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.*;
import code.maths.Rate;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.StringUtil;

public class AbilityBean extends CommonBean {
    static final String EFFECT_SEND_BEAN=PkScriptPages.REN_ADD_WEB_HTML_SENDING_EFFSENDING_HTML;
    private String name;
    private String displayName;
    private boolean achievedDisappearedPk;
    private boolean breakProtection;
    private StringList breakProtectionMoves;
    private boolean cancelSecEffectOther;
    private boolean cancelSecEffectOwner;
    private boolean chgtTypeByDamage;
    private boolean copyMovesTypes;
    private boolean forbidUseBerryAgainstFoes;
    private boolean giveItemToAllyHavingUsed;
    private boolean healedStatusBySwitch;
    private boolean ignFoeStatisBoost;
    private boolean immuCh;
    private boolean immuDamageAllyMoves;
    private boolean immuDamageRecoil;
    private boolean immuDamageTrappingMoves;
    private boolean immuRechargeRound;
    private StringList immuRechargeRoundMoves;
    private boolean immuSufferedDamageLowEff;
    private boolean inflictingDamageInsteadOfSuffering;
    private boolean mumy;
    private boolean nbHits;
    private boolean plate;
    private boolean reverseEffectsPowerMovesTypesGlobal;
    private StringList reverseEffectsPowerMovesTypesGlobalAbilities;
    private boolean slowing;
    private boolean takeItemByDamagingMove;
    private String multDamage;
    private String multPower;
    private String typeForMoves;
    private Rate healHpWhileUsingBerry;
    private Rate healedHpRateBySwitch;
    private Rate maxHpForUsingBerry;
    private Rate multAllyDamage;
    private Rate multDamageCh;
    private Rate multEvtRateCh;
    private Rate multEvtRateSecEffectOwner;
    private Rate multStab;
    private Rate multSufferedDamageSuperEff;
    private Rate multVarBoost;
    private Rate recoilDamageFoe;
    private StringList ignAbility;
    private StringList ignFoeTeamMove;
    private StringList immuAbility;
    private StringList immuAllyFromMoves;
    private StringList immuMove;
    private StringList immuStatusBeginRound;
    private StringList immuWeather;
    private long decreaseNecStepsHatch;
    private long nbUsedPp;
    private DictionaryComparator<String, Rate> singleStatus;
    private IdList<Statistic> immuLowStat;
    private IdList<Statistic> maxStatisticsIfCh;
    private CustList<StatisticStatus> immuLowStatIfStatus;
    private CustList<TypesDuo> breakFoeImmune;
    private DictionaryComparator<Statistic, Long> bonusStatRank;
    private DictionaryComparator<Statistic, Long> boostStatRankEndRound;
    private DictionaryComparator<Statistic, Long> boostStatRankProtected;
    private DictionaryComparator<Statistic, Long> lowStatFoeHit;
    private DictionaryComparator<Statistic, Long> multStatIfKoFoe;
    private DictionaryComparator<Statistic, Long> multStatIfLowStat;
    private DictionaryComparator<Statistic, String> multStat;
    private DictionaryComparator<Statistic, Rate> multStatAlly;
    private DictionaryComparator<StatisticCategory, Long> multStatIfDamageCat;
    private DictionaryComparator<StatisticCategory, Rate> multStatIfCat;
    private DictionaryComparator<StatisticStatus, Long> multStatIfStatutRank;
    private DictionaryComparator<StatisticType, Long> multStatIfDamgeType;
    private DictionaryComparator<WeatherType, Rate> healHpByTypeIfWeather;
    private DictionaryComparator<String, TypeDamageBoost> changingBoostTypes;
    private DictionaryComparator<String, Long> increasedPrio;
    private DictionaryComparator<String, Long> increasedPrioTypes;
    private DictionaryComparator<String, String> chgtTypeByWeather;
    private DictionaryComparator<String, String> failStatus;
    private DictionaryComparator<String, String> forwardStatus;
    private DictionaryComparator<String, Rate> divideStatusRound;
    private DictionaryComparator<String, Rate> healHpByWeather;
    private DictionaryComparator<String, Rate> multDamageFoe;
    private DictionaryComparator<String, Rate> multPowerMovesTypesGlobal;
    private DictionaryComparator<String,IdList<Statistic>> immuLowStatisTypes;
    private DictionaryComparator<String, StringList> immuMoveTypesByWeather;
    private DictionaryComparator<String, StringList> immuStatus;
    private DictionaryComparator<String, StringList> immuStatusTypes;
    private final EndRoundCommon endRoundCommon = new EndRoundCommon();
//    private boolean endRound;
//    private int endRoundRank;
//    private StringList reasonsEndRound;
//    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;
    private final Rate defEff = Rate.one();
    private final StringList pokemon = new StringList();

    @Override
    public void beforeDisplaying() {
        name = getForms().getValStr(CST_ABILITY);
        DataBase data_ = getDataBase();
        pkLearn();
        AbilityData ability_ = data_.getAbility(name);
        endRondElts(ability_);
        sending = !ability_.getEffectSending().isEmpty();
        StringMap<String> translatedAbilities_;
        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        displayName = translatedAbilities_.getVal(name);
        achievedDisappearedPk = ability_.isAchievedDisappearedPk();
        breakProtection = ability_.isBreakProtection();
        StringList breakProtectionMoves_;
        breakProtectionMoves_ = new StringList();
        breakProtectionMoves_.addAllElts(data_.getMovesProtAgainstDamageMoves());
        breakProtectionMoves_.addAllElts(data_.getMovesProtAgainstStatusMoves());
        breakProtectionMoves_.addAllElts(data_.getMovesProtAgainstPrio());
        breakProtectionMoves_.addAllElts(data_.getMovesProtAgainstMultiTarget());
        breakProtectionMoves_.addAllElts(data_.getMovesProtSingleTarget());
        breakProtectionMoves_.removeDuplicates();
        breakProtectionMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        breakProtectionMoves = breakProtectionMoves_;
        cancelSecEffectOther = ability_.isCancelSecEffectOther();
        cancelSecEffectOwner = ability_.isCancelSecEffectOwner();
        chgtTypeByDamage = ability_.isChgtTypeByDamage();
        copyMovesTypes = ability_.isCopyMovesTypes();
        forbidUseBerryAgainstFoes = ability_.isForbidUseBerryAgainstFoes();
        giveItemToAllyHavingUsed = ability_.isGiveItemToAllyHavingUsed();
        healedStatusBySwitch = ability_.isHealedStatusBySwitch();
        ignFoeStatisBoost = ability_.isIgnFoeStatisBoost();
        immuCh = ability_.isImmuCh();
        immuDamageAllyMoves = ability_.isImmuDamageAllyMoves();
        immuDamageRecoil = ability_.isImmuDamageRecoil();
        immuDamageTrappingMoves = ability_.isImmuDamageTrappingMoves();
        immuRechargeRound = ability_.isImmuRechargeRound();
        immuRechargeRoundMoves = immuRechargeRoundMoves();
        immuSufferedDamageLowEff = ability_.isImmuSufferedDamageLowEff();
        inflictingDamageInsteadOfSuffering = ability_.isInflictingDamageInsteadOfSuffering();
        mumy = ability_.isMumy();
        nbHits = ability_.isNbHits();
        plate = ability_.isPlate();
        reverseEffectsPowerMovesTypesGlobal = ability_.isReverseEffectsPowerMovesTypesGlobal();
        reverseEffectsPowerMovesTypesGlobalAbilities = reverseEffectsPowerMovesTypesGlobalAbilities();
        slowing = ability_.isSlowing();
        takeItemByDamagingMove = ability_.isTakeItemByDamagingMove();
        healHpWhileUsingBerry = ability_.getHealHpWhileUsingBerry();
        healedHpRateBySwitch = ability_.getHealedHpRateBySwitch();
        maxHpForUsingBerry = ability_.getMaxHpForUsingBerry();
        multAllyDamage = ability_.getMultAllyDamage();
        multDamageCh = ability_.getMultDamageCh();
        multEvtRateCh =ability_.getMultEvtRateCh();
        multEvtRateSecEffectOwner = ability_.getMultEvtRateSecEffectOwner();
        multStab = ability_.getMultStab();
        multSufferedDamageSuperEff = ability_.getMultSufferedDamageSuperEff();
        multVarBoost = ability_.getMultVarBoost();
        recoilDamageFoe = ability_.getRecoilDamageFoe();
        decreaseNecStepsHatch = ability_.getDecreaseNecStepsHatch();
        nbUsedPp = ability_.getNbUsedPp();
        NatStringTreeMap<String> mapVars_;
        mapVars_ = new NatStringTreeMap<String>();
        multPower = data_.getFormula(ability_.getMultPower(), getLanguage());
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//        multPower = StringList.replace(multPower, loc_);
//        multPower = multPower.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        multPower = multPower.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        multDamage = data_.getFormula(ability_.getMultDamage(), getLanguage());
//        multDamage = StringList.replace(multDamage, loc_);
//        multDamage = multDamage.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        multDamage = multDamage.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        immuMove = immuMoves(ability_);
        immuAllyFromMoves = immuAllyFromMoves(ability_);
        immuWeather = immuWeather(ability_);
        ignAbility = ignAbility(ability_);
        immuAbility = immuAbility(ability_);
        ignFoeTeamMove = ignFoeTeamMove(ability_);
        immuStatusBeginRound = immuStatusBeginRound(ability_);
        tpForMoves(ability_);
        singleStatus = singleStatus(ability_);
        immuLowStat = immuLowStat(ability_);
        maxStatisticsIfCh = maxStatisticsIfCh(ability_);
        immuMoveTypesByWeather = immuMoveTypesByWeather(ability_);
        immuStatus = immuStatus(ability_);
        immuStatusTypes = immuStatusTypes(ability_);
        immuLowStatIfStatus = immuLowStatIfStatus(ability_);
        immuLowStatisTypes = immuLowStatisTypes(ability_);
        DictionaryComparator<Statistic,String> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisString(data_,getLanguage());
        for (Statistic s: ability_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(ability_.getMultStat().getVal(s), getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(ability_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(s, formula_);
        }
        multStat = multStat_;
        forwardStatus = forwardStatus(ability_);
        DictionaryComparator<String, String> failStatus_;
        failStatus_ = DictionaryComparatorUtil.buildStatusStr(data_,getLanguage());
        for (String s: ability_.getFailStatus().getKeys()) {
            String fail_ = ability_.getFailStatus().getVal(s);
            String formula_ = data_.getFormula(fail_, getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(fail_, getLanguage()));
            failStatus_.put(s, formula_);
        }
        failStatus = failStatus_;
        breakFoeImmune = breakFoeImmune(ability_);
        bonusStatRank = bonusStatRank(ability_);
        boostStatRankEndRound = boostStatRankEndRound(ability_);
        boostStatRankProtected = boostStatRankProtected(ability_);
        lowStatFoeHit = lowStatFoeHit(ability_);
        multStatIfKoFoe = multStatIfKoFoe(ability_);
        multStatIfLowStat = multStatIfLowStat(ability_);
        multStatAlly = multStatAlly(ability_);
        DictionaryComparator<String, Long> increasedPrio_;
        increasedPrio_ = DictionaryComparatorUtil.buildCatsShort(data_,getLanguage());
        for (String c: ability_.getIncreasedPrio().getKeys()) {
            increasedPrio_.put(c, ability_.getIncreasedPrio().getVal(c));
        }
        increasedPrio = increasedPrio_;
        DictionaryComparator<String, Long> increasedPrioTypes_;
        increasedPrioTypes_ = DictionaryComparatorUtil.buildTypesShort(data_,getLanguage());
        for (String c: ability_.getIncreasedPrioTypes().getKeys()) {
            increasedPrioTypes_.put(c, ability_.getIncreasedPrioTypes().getVal(c));
        }
        increasedPrioTypes = increasedPrioTypes_;
        DictionaryComparator<String, String> chgtTypeByWeather_;
        chgtTypeByWeather_ = DictionaryComparatorUtil.buildMovesStr(data_,getLanguage());
        for (String c: ability_.getChgtTypeByWeather().getKeys()) {
            chgtTypeByWeather_.put(c, ability_.getChgtTypeByWeather().getVal(c));
        }
        chgtTypeByWeather = chgtTypeByWeather_;
        DictionaryComparator<String, Rate> divideStatusRound_;
        divideStatusRound_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String c: ability_.getDivideStatusRound().getKeys()) {
            divideStatusRound_.put(c, ability_.getDivideStatusRound().getVal(c));
        }
        divideStatusRound = divideStatusRound_;
        DictionaryComparator<String, Rate> healHpByWeather_;
        healHpByWeather_ = DictionaryComparatorUtil.buildMovesRate(data_,getLanguage());
        for (String c: ability_.getHealHpByWeather().getKeys()) {
            healHpByWeather_.put(c, ability_.getHealHpByWeather().getVal(c));
        }
        healHpByWeather = healHpByWeather_;
        DictionaryComparator<WeatherType, Rate> healHpByTypeIfWeather_;
        healHpByTypeIfWeather_ = DictionaryComparatorUtil.buildWeatherType(data_, getLanguage());
        for (WeatherType w: ability_.getHealHpByTypeIfWeather().getKeys()) {
            healHpByTypeIfWeather_.put(w, ability_.getHealHpByTypeIfWeather().getVal(w));
        }
        healHpByTypeIfWeather = healHpByTypeIfWeather_;
        DictionaryComparator<String, TypeDamageBoost> changingBoostTypes_;
        changingBoostTypes_ = DictionaryComparatorUtil.buildTypesTypeDamageBoost(data_,getLanguage());
        for (String w: ability_.getChangingBoostTypes().getKeys()) {
            changingBoostTypes_.put(w, ability_.getChangingBoostTypes().getVal(w));
        }
        changingBoostTypes = changingBoostTypes_;
        DictionaryComparator<StatisticCategory, Long> multStatIfDamageCat_;
        multStatIfDamageCat_ = DictionaryComparatorUtil.buildStatisticCategoryByte(data_,getLanguage());
        for (StatisticCategory w: ability_.getMultStatIfDamageCat().getKeys()) {
            multStatIfDamageCat_.put(w, ability_.getMultStatIfDamageCat().getVal(w));
        }
        multStatIfDamageCat = multStatIfDamageCat_;
        DictionaryComparator<StatisticCategory, Rate> multStatIfCat_;
        multStatIfCat_ = DictionaryComparatorUtil.buildStatisticCategoryRate(data_,getLanguage());
        for (StatisticCategory w: ability_.getMultStatIfCat().getKeys()) {
            multStatIfCat_.put(w, ability_.getMultStatIfCat().getVal(w));
        }
        multStatIfCat = multStatIfCat_;
        DictionaryComparator<StatisticType, Long> multStatIfDamgeType_;
//        multStatIfDamgeType_ = new TreeMap<new>(new NaturalComparator<StatisticType>() {
//            @Override
//            public int compare(StatisticType _o1, StatisticType _o2) {
//                DataBase dataCmp_ = (DataBase) getDataBase();
//                Map<Statistic,String> translatedStatisticsCmp_;
//                translatedStatisticsCmp_ = dataCmp_.getTranslatedStatistics().getVal(getLanguage());
//                int res_ = ComparatorTrString.compare(translatedStatisticsCmp_, _o1.getStatistic(), _o2.getStatistic());
//                if (res_ != 0) {
//                    return res_;
//                }
//                Map<String,String> translatedCategoriesCmp_;
//                translatedCategoriesCmp_ = dataCmp_.getTranslatedTypes().getVal(getLanguage());
//                return ComparatorTrString.compare(translatedCategoriesCmp_, _o1.getType(), _o2.getType());
//            }
//        });
        multStatIfDamgeType_ = DictionaryComparatorUtil.buildStatisTypeByte(data_, getLanguage());
        for (StatisticType w: ability_.getMultStatIfDamgeType().getKeys()) {
            multStatIfDamgeType_.put(w, ability_.getMultStatIfDamgeType().getVal(w));
        }
        multStatIfDamgeType = multStatIfDamgeType_;
        DictionaryComparator<StatisticStatus, Long> multStatIfStatutRank_;
        multStatIfStatutRank_ = DictionaryComparatorUtil.buildStatisticStatus(data_, getLanguage());
        for (StatisticStatus w: ability_.getMultStatIfStatutRank().getKeys()) {
            multStatIfStatutRank_.put(w, ability_.getMultStatIfStatutRank().getVal(w));
        }
        multStatIfStatutRank = multStatIfStatutRank_;
        DictionaryComparator<String, Rate> multDamageFoe_;
        multDamageFoe_ = DictionaryComparatorUtil.buildTypesRate(data_,getLanguage());
        for (String c: ability_.getMultDamageFoe().getKeys()) {
            multDamageFoe_.put(c, ability_.getMultDamageFoe().getVal(c));
        }
        multDamageFoe = multDamageFoe_;
        DictionaryComparator<String, Rate> multPowerMovesTypesGlobal_;
        multPowerMovesTypesGlobal_ = DictionaryComparatorUtil.buildTypesRate(data_,getLanguage());
        for (String c: ability_.getMultPowerMovesTypesGlobal().getKeys()) {
            multPowerMovesTypesGlobal_.put(c, ability_.getMultPowerMovesTypesGlobal().getVal(c));
        }
        multPowerMovesTypesGlobal = multPowerMovesTypesGlobal_;
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
    }

    private DictionaryComparator<Statistic, Rate> multStatAlly(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Rate> multStatAlly_;
        multStatAlly_ = DictionaryComparatorUtil.buildStatisRate(data_,getLanguage());
        for (Statistic s: _ability.getMultStatAlly().getKeys()) {
            multStatAlly_.put(s, _ability.getMultStatAlly().getVal(s));
        }
        return multStatAlly_;
    }

    private DictionaryComparator<Statistic, Long> multStatIfLowStat(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> multStatIfLowStat_;
        multStatIfLowStat_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getMultStatIfLowStat().getKeys()) {
            multStatIfLowStat_.put(s, _ability.getMultStatIfLowStat().getVal(s));
        }
        return multStatIfLowStat_;
    }

    private DictionaryComparator<Statistic, Long> multStatIfKoFoe(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> multStatIfKoFoe_;
        multStatIfKoFoe_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getMultStatIfKoFoe().getKeys()) {
            multStatIfKoFoe_.put(s, _ability.getMultStatIfKoFoe().getVal(s));
        }
        return multStatIfKoFoe_;
    }

    private DictionaryComparator<Statistic, Long> lowStatFoeHit(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> lowStatFoeHit_;
        lowStatFoeHit_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getLowStatFoeHit().getKeys()) {
            lowStatFoeHit_.put(s, _ability.getLowStatFoeHit().getVal(s));
        }
        return lowStatFoeHit_;
    }

    private DictionaryComparator<Statistic, Long> boostStatRankProtected(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> boostStatRankProtected_;
        boostStatRankProtected_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getBoostStatRankProtected().getKeys()) {
            boostStatRankProtected_.put(s, _ability.getBoostStatRankProtected().getVal(s));
        }
        return boostStatRankProtected_;
    }

    private DictionaryComparator<Statistic, Long> boostStatRankEndRound(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> boostStatRankEndRound_;
        boostStatRankEndRound_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getBoostStatRankEndRound().getKeys()) {
            boostStatRankEndRound_.put(s, _ability.getBoostStatRankEndRound().getVal(s));
        }
        return boostStatRankEndRound_;
    }

    private DictionaryComparator<Statistic, Long> bonusStatRank(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<Statistic, Long> bonusStatRank_;
        bonusStatRank_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: _ability.getBonusStatRank().getKeys()) {
            bonusStatRank_.put(s, _ability.getBonusStatRank().getVal(s));
        }
        return bonusStatRank_;
    }

    private CustList<TypesDuo> breakFoeImmune(AbilityData _ability) {
        DataBase data_ = getDataBase();
        CustList<TypesDuo> breakFoeImmune_;
        breakFoeImmune_ = new CustList<TypesDuo>();
        for (TypesDuo s: _ability.getBreakFoeImmune()) {
            breakFoeImmune_.add(new TypesDuo(s.getDamageType(),s.getPokemonType()));
        }
//        breakFoeImmune_.sort(new NaturalComparator<TypesDuo>() {
//            @Override
//            public int compare(TypesDuo _o1, TypesDuo _o2) {
//                DataBase dataCmp_ = (DataBase) getDataBase();
//                Map<String,String> translatedTypesCmp_;
//                translatedTypesCmp_ = dataCmp_.getTranslatedTypes().getVal(getLanguage());
//                int res_ = ComparatorTrString.compare(translatedTypesCmp_, _o1.getDamageType(), _o2.getDamageType());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return ComparatorTrString.compare(translatedTypesCmp_, _o1.getPokemonType(), _o2.getPokemonType());
//            }
//        });
        breakFoeImmune_.sortElts(new ComparatorTypesDuo(data_, getLanguage(), false,false));
        return breakFoeImmune_;
    }

    private DictionaryComparator<String, String> forwardStatus(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, String> forwardStatus_;
        forwardStatus_ = DictionaryComparatorUtil.buildStatusStr(data_,getLanguage());
        for (String s: _ability.getForwardStatus().getKeys()) {
            forwardStatus_.put(s, _ability.getForwardStatus().getVal(s));
        }
        return forwardStatus_;
    }

    private DictionaryComparator<String, IdList<Statistic>> immuLowStatisTypes(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String,IdList<Statistic>> immuLowStatisTypes_;
        immuLowStatisTypes_ = DictionaryComparatorUtil.buildTypesStaList(data_,getLanguage());
        for (String t: _ability.getImmuLowStatisTypes().getKeys()) {
            immuLowStatisTypes_.put(t, new IdList<Statistic>(_ability.getImmuLowStatisTypes().getVal(t)));
        }
        for (IdList<Statistic> v: immuLowStatisTypes_.values()) {
            v.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
        }
        return immuLowStatisTypes_;
    }

    private CustList<StatisticStatus> immuLowStatIfStatus(AbilityData _ability) {
        DataBase data_ = getDataBase();
        CustList<StatisticStatus> immuLowStatIfStatus_;
        immuLowStatIfStatus_ = new CustList<StatisticStatus>();
        for (StatisticStatus s: _ability.getImmuLowStatIfStatus()) {
            immuLowStatIfStatus_.add(new StatisticStatus(s.getStatistic(),s.getStatus()));
        }
        immuLowStatIfStatus_.sortElts(new ComparatorStatusStatistic(data_, getLanguage()));
        return immuLowStatIfStatus_;
    }

    private DictionaryComparator<String, StringList> immuStatusTypes(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, StringList> immuStatusTypes_;
        immuStatusTypes_ = DictionaryComparatorUtil.buildTypesStrList(data_,getLanguage());
        for (String t: _ability.getImmuStatusTypes().getKeys()) {
            immuStatusTypes_.put(t, new StringList(_ability.getImmuStatusTypes().getVal(t)));
        }
        for (StringList v: immuStatusTypes_.values()) {
            v.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        }
        return immuStatusTypes_;
    }

    private DictionaryComparator<String, StringList> immuStatus(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, StringList> immuStatus_;
        immuStatus_ = DictionaryComparatorUtil.buildMovesStrList(data_,getLanguage());
        for (String t: _ability.getImmuStatus().getKeys()) {
            immuStatus_.put(t, new StringList(_ability.getImmuStatus().getVal(t)));
        }
        for (StringList v: immuStatus_.values()) {
            v.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        }
        return immuStatus_;
    }

    private DictionaryComparator<String, StringList> immuMoveTypesByWeather(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, StringList> immuMoveTypesByWeather_;
        immuMoveTypesByWeather_ = DictionaryComparatorUtil.buildMovesStrList(data_,getLanguage());
        for (String t: _ability.getImmuMoveTypesByWeather().getKeys()) {
            immuMoveTypesByWeather_.put(t, new StringList(_ability.getImmuMoveTypesByWeather().getVal(t)));
        }
        for (StringList v: immuMoveTypesByWeather_.values()) {
            v.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        }
        return immuMoveTypesByWeather_;
    }

    private IdList<Statistic> maxStatisticsIfCh(AbilityData _ability) {
        DataBase data_ = getDataBase();
        IdList<Statistic> maxStatisticsIfCh_;
        maxStatisticsIfCh_ = new IdList<Statistic>();
        for (Statistic s: _ability.getMaxStatisticsIfCh()) {
            maxStatisticsIfCh_.add(s);
        }
        maxStatisticsIfCh_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
        return maxStatisticsIfCh_;
    }

    private IdList<Statistic> immuLowStat(AbilityData _ability) {
        DataBase data_ = getDataBase();
        IdList<Statistic> immuLowStat_;
        immuLowStat_ = new IdList<Statistic>();
        for (Statistic s: _ability.getImmuLowStat()) {
            immuLowStat_.add(s);
        }
        immuLowStat_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
        return immuLowStat_;
    }

    private DictionaryComparator<String, Rate> singleStatus(AbilityData _ability) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, Rate> singleStatus_;
        singleStatus_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String s: _ability.getSingleStatus().eventsDiff()) {
            singleStatus_.put(s, _ability.getSingleStatus().normalizedRate(s));
        }
        return singleStatus_;
    }

    private void tpForMoves(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_;
        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        if (!_ability.getTypeForMoves().isEmpty()) {
            typeForMoves = translatedTypes_.getVal(_ability.getTypeForMoves());
        } else {
            typeForMoves = DataBase.EMPTY_STRING;
        }
    }

    private StringList immuStatusBeginRound(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList immuStatusBeginRound_;
        immuStatusBeginRound_ = new StringList();
        for (String m: _ability.getImmuStatusBeginRound()) {
            immuStatusBeginRound_.add(m);
        }
        immuStatusBeginRound_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        return immuStatusBeginRound_;
    }

    private StringList ignFoeTeamMove(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList ignFoeTeamMove_;
        ignFoeTeamMove_ = new StringList();
        for (String m: _ability.getIgnFoeTeamMove()) {
            ignFoeTeamMove_.add(m);
        }
        ignFoeTeamMove_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return ignFoeTeamMove_;
    }

    private StringList immuAbility(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList immuAbility_;
        immuAbility_ = new StringList();
        for (String m: _ability.getImmuAbility()) {
            immuAbility_.add(m);
        }
        immuAbility_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return immuAbility_;
    }

    private StringList ignAbility(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList ignAbility_;
        ignAbility_ = new StringList();
        for (String m: _ability.getIgnAbility()) {
            ignAbility_.add(m);
        }
        ignAbility_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return ignAbility_;
    }

    private StringList immuWeather(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList immuWeather_;
        immuWeather_ = new StringList();
        for (String m: _ability.getImmuWeather()) {
            immuWeather_.add(m);
        }
        immuWeather_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return immuWeather_;
    }

    private void endRondElts(AbilityData _ability) {
        EffectEndRound effect_;
        DataBase data_ = getDataBase();
        if (!_ability.getEffectEndRound().isEmpty()) {
//            endRound = true;
            effect_ = _ability.getEffectEndRound().first();
//            endRoundRank = effect_.getEndRoundRank();
//            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
//            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
//            endRound = false;
            effect_ = null;
//            endRoundRank = 0;
//            reasonsEndRound = new StringList();
//            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        endRoundCommon.endRondElts(data_,effect_,getLanguage());
    }
    public EndRoundCommon getEndRoundCommon() {
        return endRoundCommon;
    }

    private StringList immuAllyFromMoves(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList immuAllyFromMoves_;
        immuAllyFromMoves_ = new StringList();
        for (String m: _ability.getImmuAllyFromMoves()) {
            immuAllyFromMoves_.add(m);
        }
        immuAllyFromMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return immuAllyFromMoves_;
    }

    private StringList immuMoves(AbilityData _ability) {
        DataBase data_ = getDataBase();
        StringList immuMoves_;
        immuMoves_ = new StringList();
        for (String m: _ability.getImmuMove()) {
            immuMoves_.add(m);
        }
        immuMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return immuMoves_;
    }

    private StringList reverseEffectsPowerMovesTypesGlobalAbilities() {
        DataBase data_ = getDataBase();
        StringList reverseEffectsPowerMovesTypesGlobalAbilities_ = reverseEffects(data_);
        reverseEffectsPowerMovesTypesGlobalAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return reverseEffectsPowerMovesTypesGlobalAbilities_;
    }

    static StringList reverseEffects(DataBase _data) {
        StringList reverseEffectsPowerMovesTypesGlobalAbilities_;
        reverseEffectsPowerMovesTypesGlobalAbilities_ = new StringList();
        for (String a: _data.getAbilities().getKeys()) {
            AbilityData ab_ = _data.getAbility(a);
            if (!ab_.getMultPowerMovesTypesGlobal().isEmpty()) {
                reverseEffectsPowerMovesTypesGlobalAbilities_.add(a);
            }
        }
        return reverseEffectsPowerMovesTypesGlobalAbilities_;
    }

    private StringList immuRechargeRoundMoves() {
        DataBase data_ = getDataBase();
        StringList immuRechargeRoundMoves_ = immuRechargeRoundMoves(data_);
        immuRechargeRoundMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return immuRechargeRoundMoves_;
    }

    static StringList immuRechargeRoundMoves(DataBase _data) {
        StringList immuRechargeRoundMoves_;
        immuRechargeRoundMoves_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            if (move_.getRechargeRound()) {
                immuRechargeRoundMoves_.add(m);
            }
        }
        return immuRechargeRoundMoves_;
    }

    private void pkLearn() {
        DataBase data_ = getDataBase();
        pokemon.clear();
        pokemon.addAllElts(pkLearn(data_,name));
        pokemon.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
    }
    static CustList<String> pkLearn(DataBase _db, String _name) {
        CustList<String> ls_ = new CustList<String>();
        for (String p: _db.getPokedex().getKeys()) {
            PokemonData pk_ = _db.getPokemon(p);
            if (StringUtil.contains(pk_.getAbilities(), _name)) {
                ls_.add(p);
            }
        }
        return ls_;
    }

//    private String quoteBraces(String _formula) {
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
////        _formula = _formula.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////        _formula = _formula.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//        return StringList.replace(_formula, loc_);
//    }

    public EffectWhileSendingWithStatistic getEffectSending() {
        return getAbility().getEffectSending().first();
    }
    public boolean decreaseNecStepsHatchInt() {
        return decreaseNecStepsHatch > 0;
    }
    public boolean nbUsedPpInt() {
        return nbUsedPp > 0;
    }
    public String getTrImmuMove(int _index) {
        String type_ = immuMove.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickIndex() {
        getForms().safeAbilities(CST_ABILITIES_SET);
        return PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML;
    }
    public String clickImmuMove(int _index) {
        String type_ = immuMove.get(_index);
        return tryRedirectMv(type_);
    }
    public String getTrImmuAllyFromMoves(int _index) {
        String type_ = immuAllyFromMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickImmuAllyFromMoves(int _index) {
        return tryRedirectMv(immuAllyFromMoves.get(_index));
    }
    public String getTrWeather(int _index) {
        return getDataBase().getTranslatedMoves().getVal(getLanguage()).getVal(immuWeather.get(_index));
    }
    public String clickWeather(int _index) {
        return tryRedirectMv(immuWeather.get(_index));
    }
    public String getTrIgnAbility(int _index) {
        String type_ = ignAbility.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickIgnAbility(int _index) {
        String type_ = ignAbility.get(_index);
        return tryRedirectAb(type_);
    }
    public String getTrIgnFoeTeamMove(int _index) {
        String type_ = ignFoeTeamMove.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickIgnFoeTeamMove(int _index) {
        String type_ = ignFoeTeamMove.get(_index);
        return tryRedirectMv(type_);
    }
    public String getTrImmuAbility(int _index) {
        String type_ = immuAbility.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickImmuAbility(int _index) {
        String type_ = immuAbility.get(_index);
        return tryRedirectAb(type_);
    }
    public String getTrImmuStatusBeginRound(int _index) {
        String type_ = immuStatusBeginRound.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatusBeginRound(int _index) {
        String type_ = immuStatusBeginRound.get(_index);
        return tryRedirectSt(type_);
    }
    public boolean isStatus(int _index) {
        return !singleStatus.getKey(_index).isEmpty();
    }
    public String getTrSingleStatus(int _index) {
        String type_ = singleStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickSingleStatus(int _index) {
        String type_ = singleStatus.getKey(_index);
        return tryRedirectSt(type_);
    }
    public AbilityData getAbility() {
        DataBase data_ = getDataBase();
        return data_.getAbility(name);
    }
    public String getTrBreakProtectionMoves(int _index) {
        String move_ = breakProtectionMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move_);
    }
    public String clickBreakProtectionMoves(int _index) {
        String move_ = breakProtectionMoves.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrImmuRechargeRoundMoves(int _index) {
        String move_ = immuRechargeRoundMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move_);
    }
    public String clickImmuRechargeRoundMoves(int _index) {
        String move_ = immuRechargeRoundMoves.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrReversePowerTypesAbilities(int _index) {
        String move_ = reverseEffectsPowerMovesTypesGlobalAbilities.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(move_);
    }
    public String clickReversePowerTypesAbilities(int _index) {
        String move_ = reverseEffectsPowerMovesTypesGlobalAbilities.get(_index);
        return tryRedirectAb(move_);
    }
    public String getTrMultStat(int _index) {
        Statistic type_ = multStat.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrImmuLowStat(int _index) {
        Statistic type_ = immuLowStat.get(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMaxStatisticsIfCh(int _index) {
        Statistic type_ = maxStatisticsIfCh.get(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public boolean isMoveByWeather(int _index) {
        return !immuMoveTypesByWeather.getKey(_index).isEmpty();
    }
    public String getTrImmuMoveByWeather(int _index) {
        String move_ = immuMoveTypesByWeather.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move_);
    }
    public String clickImmuMoveByWeather(int _index) {
        String move_ = immuMoveTypesByWeather.getKey(_index);
        return tryRedirectMv(move_);
    }
    public String getTrImmuTypeByWeather(int _indexOne, int _indexTwo) {
        String move_ = immuMoveTypesByWeather.getValue(_indexOne).get(_indexTwo);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public boolean isMoveByStatus(int _index) {
        return !immuStatus.getKey(_index).isEmpty();
    }
    public String getTrImmuStatusWeather(int _index) {
        String move_ = immuStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(move_);
    }
    public String clickImmuStatusWeather(int _index) {
        String move_ = immuStatus.getKey(_index);
        return tryRedirectMv(move_);
    }
    public String getTrImmuStatus(int _indexOne, int _indexTwo) {
        String move_ = immuStatus.getValue(_indexOne).get(_indexTwo);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(move_);
    }
    public String clickImmuStatus(int _indexOne, int _indexTwo) {
        String move_ = immuStatus.getValue(_indexOne).get(_indexTwo);
        return tryRedirectSt(move_);
    }
    public String getTrImmuStatusTypes(int _index) {
        String move_ = immuStatusTypes.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrImmuStatusValue(int _indexOne, int _indexTwo) {
        String move_ = immuStatusTypes.getValue(_indexOne).get(_indexTwo);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(move_);
    }
    public String clickImmuStatusTypes(int _indexOne, int _indexTwo) {
        String move_ = immuStatusTypes.getValue(_indexOne).get(_indexTwo);
        return tryRedirectSt(move_);
    }
    public String getTrForwardStatusKey(int _index) {
        String status_ = forwardStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickForwardStatusKey(int _index) {
        String status_ = forwardStatus.getKey(_index);
        return tryRedirectSt(status_);
    }
    public String getTrForwardStatusValue(int _index) {
        String status_ = forwardStatus.getValue(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickForwardStatusValue(int _index) {
        String status_ = forwardStatus.getValue(_index);
        return tryRedirectSt(status_);
    }
    public String getTrFailStatus(int _index) {
        String status_ = failStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickFailStatus(int _index) {
        String status_ = failStatus.getKey(_index);
        return tryRedirectSt(status_);
    }
    public String getTrImmuLowStatIfStatusKey(int _index) {
        String status_ = immuLowStatIfStatus.get(_index).getStatus();
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickImmuLowStatIfStatusKey(int _index) {
        String status_ = immuLowStatIfStatus.get(_index).getStatus();
        return tryRedirectSt(status_);
    }
    public String getTrImmuLowStatIfStatusValue(int _index) {
        Statistic status_ = immuLowStatIfStatus.get(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(status_);
    }
    public boolean isChgtTypeByWeather(int _index) {
        return !chgtTypeByWeather.getKey(_index).isEmpty();
    }
    public String getTrChgtTypeByWeatherKey(int _index) {
        String status_ = chgtTypeByWeather.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(status_);
    }
    public String clickChgtTypeByWeatherKey(int _index) {
        String status_ = chgtTypeByWeather.getKey(_index);
        return tryRedirectMv(status_);
    }
    public String getTrChgtTypeByWeatherValue(int _index) {
        String status_ = chgtTypeByWeather.getValue(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(status_);
    }
    public String getTrDivideStatusRoundKey(int _index) {
        String status_ = divideStatusRound.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickDivideStatusRoundKey(int _index) {
        String status_ = divideStatusRound.getKey(_index);
        return tryRedirectSt(status_);
    }
    public boolean isHealHpByWeather(int _index) {
        return !healHpByWeather.getKey(_index).isEmpty();
    }
    public String getTrHealHpByWeatherKey(int _index) {
        String status_ = healHpByWeather.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(status_);
    }
    public String clickHealHpByWeatherKey(int _index) {
        String status_ = healHpByWeather.getKey(_index);
        return tryRedirectMv(status_);
    }
    public boolean isHealHpByTypeIfWeather(int _index) {
        return !healHpByTypeIfWeather.getKey(_index).getWeather().isEmpty();
    }
    public String getTrHealHpByTypeIfWeatherKey(int _index) {
        String status_ = healHpByTypeIfWeather.getKey(_index).getWeather();
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(status_);
    }
    public String clickHealHpByTypeIfWeatherKey(int _index) {
        String status_ = healHpByTypeIfWeather.getKey(_index).getWeather();
        return tryRedirectMv(status_);
    }
    public String getTrHealHpByTypeIfWeatherKeySec(int _index) {
        String status_ = healHpByTypeIfWeather.getKey(_index).getType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(status_);
    }
    public String getTrMultStatIfDamageCatKey(int _index) {
        Statistic status_ = multStatIfDamageCat.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfDamageCatKeySec(int _index) {
        String status_ = multStatIfDamageCat.getKey(_index).getCategory();
        DataBase data_ = getDataBase();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        return translationsCategories_.getVal(status_);
    }
    public String getTrMultStatIfCatKey(int _index) {
        Statistic status_ = multStatIfCat.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfCatKeySec(int _index) {
        String status_ = multStatIfCat.getKey(_index).getCategory();
        DataBase data_ = getDataBase();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        return translationsCategories_.getVal(status_);
    }
    public String getTrMultStatIfDamgeType(int _index) {
        Statistic status_ = multStatIfDamgeType.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfDamgeTypeSec(int _index) {
        String status_ = multStatIfDamgeType.getKey(_index).getType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(status_);
    }
    public String getTrMultStatIfStatutRank(int _index) {
        Statistic status_ = multStatIfStatutRank.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfStatutRankSec(int _index) {
        String status_ = multStatIfStatutRank.getKey(_index).getStatus();
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String clickMultStatIfStatutRankSec(int _index) {
        String status_ = multStatIfStatutRank.getKey(_index).getStatus();
        return tryRedirectSt(status_);
    }
    public String getTrMultPowerMovesTypesGlobalKey(int _index) {
        String status_ = multPowerMovesTypesGlobal.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsMoves_.getVal(status_);
    }
    public String getTrImmuLowStatisTypes(int _index) {
        String move_ = immuLowStatisTypes.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrImmuLowStatisValue(int _indexOne, int _indexTwo) {
        Statistic move_ = immuLowStatisTypes.getValue(_indexOne).get(_indexTwo);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrBreakFoeImmuneKey(int _index) {
        String status_ = breakFoeImmune.get(_index).getDamageType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String getTrBreakFoeImmuneValue(int _index) {
        String status_ = breakFoeImmune.get(_index).getPokemonType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsStatus_.getVal(status_);
    }
    public String getTrBonusStatRank(int _index) {
        Statistic move_ = bonusStatRank.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankEndRound(int _index) {
        Statistic move_ = boostStatRankEndRound.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankProtected(int _index) {
        Statistic move_ = boostStatRankProtected.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrLowStatFoeHit(int _index) {
        Statistic move_ = lowStatFoeHit.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfKoFoe(int _index) {
        Statistic move_ = multStatIfKoFoe.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfLowStat(int _index) {
        Statistic move_ = multStatIfLowStat.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatAlly(int _index) {
        Statistic move_ = multStatAlly.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrIncreasedPrio(int _index) {
        String move_ = increasedPrio.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        return translationsCategories_.getVal(move_);
    }
    public String getTrIncreasedPrioTypes(int _index) {
        String move_ = increasedPrioTypes.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrMultDamageFoe(int _index) {
        String move_ = multDamageFoe.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrChangingBoostTypesOld(int _index) {
        String move_ = changingBoostTypes.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrChangingBoostTypesNew(int _index) {
        String move_ = changingBoostTypes.getValue(_index).getType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrPokemon(int _index) {
        String pk_ = pokemon.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemons_;
        translationsPokemons_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemons_.getVal(pk_);
    }
    public String clickPokemon(int _index) {
        String pk_ = pokemon.get(_index);
        return tryRedirectPk(pk_);
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean getSending() {
        return sending;
    }

    public boolean getAchievedDisappearedPk() {
        return achievedDisappearedPk;
    }

    public boolean getBreakProtection() {
        return breakProtection;
    }

    public StringList getBreakProtectionMoves() {
        return breakProtectionMoves;
    }

    public boolean getCancelSecEffectOther() {
        return cancelSecEffectOther;
    }

    public boolean getCancelSecEffectOwner() {
        return cancelSecEffectOwner;
    }

    public boolean getChgtTypeByDamage() {
        return chgtTypeByDamage;
    }

    public DictionaryComparator<String,String> getChgtTypeByWeather() {
        return chgtTypeByWeather;
    }

    public boolean getCopyMovesTypes() {
        return copyMovesTypes;
    }

    public boolean getForbidUseBerryAgainstFoes() {
        return forbidUseBerryAgainstFoes;
    }

    public boolean getGiveItemToAllyHavingUsed() {
        return giveItemToAllyHavingUsed;
    }

    public boolean getHealedStatusBySwitch() {
        return healedStatusBySwitch;
    }

    public boolean getIgnFoeStatisBoost() {
        return ignFoeStatisBoost;
    }

    public boolean getImmuCh() {
        return immuCh;
    }

    public boolean getImmuDamageAllyMoves() {
        return immuDamageAllyMoves;
    }

    public boolean getImmuDamageRecoil() {
        return immuDamageRecoil;
    }

    public boolean getImmuDamageTrappingMoves() {
        return immuDamageTrappingMoves;
    }

    public boolean getImmuRechargeRound() {
        return immuRechargeRound;
    }

    public StringList getImmuRechargeRoundMoves() {
        return immuRechargeRoundMoves;
    }

    public boolean getImmuSufferedDamageLowEff() {
        return immuSufferedDamageLowEff;
    }

    public boolean getInflictingDamageInsteadOfSuffering() {
        return inflictingDamageInsteadOfSuffering;
    }

    public boolean getMumy() {
        return mumy;
    }

    public boolean getNbHits() {
        return nbHits;
    }

    public boolean getPlate() {
        return plate;
    }

    public boolean getReverseEffectsPowerMovesTypesGlobal() {
        return reverseEffectsPowerMovesTypesGlobal;
    }

    public StringList getReverseEffectsPowerMovesTypesGlobalAbilities() {
        return reverseEffectsPowerMovesTypesGlobalAbilities;
    }

    public boolean getSlowing() {
        return slowing;
    }

    public boolean getTakeItemByDamagingMove() {
        return takeItemByDamagingMove;
    }

    public Rate getHealHpWhileUsingBerry() {
        return healHpWhileUsingBerry;
    }

    public Rate getMaxHpForUsingBerry() {
        return maxHpForUsingBerry;
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }

    public Rate getMultDamageCh() {
        return multDamageCh;
    }

    public Rate getMultEvtRateCh() {
        return multEvtRateCh;
    }

    public Rate getMultEvtRateSecEffectOwner() {
        return multEvtRateSecEffectOwner;
    }

    public Rate getMultStab() {
        return multStab;
    }

    public Rate getMultSufferedDamageSuperEff() {
        return multSufferedDamageSuperEff;
    }

    public Rate getMultVarBoost() {
        return multVarBoost;
    }

    public Rate getRecoilDamageFoe() {
        return recoilDamageFoe;
    }

    public long getDecreaseNecStepsHatch() {
        return decreaseNecStepsHatch;
    }

    public long getNbUsedPp() {
        return nbUsedPp;
    }

    public StringList getImmuMove() {
        return immuMove;
    }

    public StringList getImmuAllyFromMoves() {
        return immuAllyFromMoves;
    }

    public StringList getImmuWeather() {
        return immuWeather;
    }

    public StringList getIgnAbility() {
        return ignAbility;
    }

    public StringList getIgnFoeTeamMove() {
        return ignFoeTeamMove;
    }

    public StringList getImmuAbility() {
        return immuAbility;
    }

    public StringList getImmuStatusBeginRound() {
        return immuStatusBeginRound;
    }

    public String getTypeForMoves() {
        return typeForMoves;
    }

    public DictionaryComparator<String,TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public String getMultPower() {
        return multPower;
    }

    public String getMultDamage() {
        return multDamage;
    }

    public DictionaryComparator<String,Rate> getHealHpByWeather() {
        return healHpByWeather;
    }

    public DictionaryComparator<WeatherType,Rate> getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public IdList<Statistic> getImmuLowStat() {
        return immuLowStat;
    }

    public CustList<StatisticStatus> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public DictionaryComparator<String,IdList<Statistic>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
    }

    public IdList<Statistic> getMaxStatisticsIfCh() {
        return maxStatisticsIfCh;
    }

    public DictionaryComparator<String,Rate> getSingleStatus() {
        return singleStatus;
    }

    public DictionaryComparator<String,StringList> getImmuMoveTypesByWeather() {
        return immuMoveTypesByWeather;
    }

    public DictionaryComparator<String,StringList> getImmuStatus() {
        return immuStatus;
    }

    public DictionaryComparator<String,StringList> getImmuStatusTypes() {
        return immuStatusTypes;
    }

    public DictionaryComparator<String,Rate> getDivideStatusRound() {
        return divideStatusRound;
    }

    public DictionaryComparator<String,String> getForwardStatus() {
        return forwardStatus;
    }

    public CustList<TypesDuo> getBreakFoeImmune() {
        return breakFoeImmune;
    }

    public Rate getDefEff() {
        return defEff;
    }

    public DictionaryComparator<Statistic,String> getMultStat() {
        return multStat;
    }

    public DictionaryComparator<StatisticCategory,Long> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public DictionaryComparator<StatisticType,Long> getMultStatIfDamgeType() {
        return multStatIfDamgeType;
    }

    public DictionaryComparator<StatisticCategory,Rate> getMultStatIfCat() {
        return multStatIfCat;
    }

    public DictionaryComparator<StatisticStatus,Long> getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public DictionaryComparator<Statistic,Long> getBonusStatRank() {
        return bonusStatRank;
    }

    public DictionaryComparator<Statistic,Long> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public DictionaryComparator<Statistic,Long> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public DictionaryComparator<Statistic,Long> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public DictionaryComparator<Statistic,Long> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public DictionaryComparator<Statistic,Long> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public DictionaryComparator<Statistic,Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public DictionaryComparator<String,Long> getIncreasedPrio() {
        return increasedPrio;
    }

    public DictionaryComparator<String,Long> getIncreasedPrioTypes() {
        return increasedPrioTypes;
    }

    public DictionaryComparator<String,Rate> getMultDamageFoe() {
        return multDamageFoe;
    }

    public DictionaryComparator<String,Rate> getMultPowerMovesTypesGlobal() {
        return multPowerMovesTypesGlobal;
    }

    public DictionaryComparator<String,String> getFailStatus() {
        return failStatus;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public Rate getHealedHpRateBySwitch() {
        return healedHpRateBySwitch;
    }

    public StringList getPokemon() {
        return pokemon;
    }
}