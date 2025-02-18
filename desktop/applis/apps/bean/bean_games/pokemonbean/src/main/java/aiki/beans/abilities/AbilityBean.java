package aiki.beans.abilities;

import aiki.beans.CommonBean;
import aiki.beans.EndRoundCommon;
import aiki.beans.TranslatedKey;
import aiki.beans.facade.comparators.ComparatorTranslatedKeyPair;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
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
    private CustList<TranslatedKey> breakProtectionMoves;
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
    private CustList<TranslatedKey> immuRechargeRoundMoves;
    private boolean immuSufferedDamageLowEff;
    private boolean inflictingDamageInsteadOfSuffering;
    private boolean mumy;
    private boolean nbHits;
    private boolean plate;
    private boolean reverseEffectsPowerMovesTypesGlobal;
    private CustList<TranslatedKey> reverseEffectsPowerMovesTypesGlobalAbilities;
    private boolean slowing;
    private boolean takeItemByDamagingMove;
    private String multDamage;
    private String multPower;
    private TranslatedKey typeForMoves;
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
    private CustList<TranslatedKey> ignAbility;
    private CustList<TranslatedKey> ignFoeTeamMove;
    private CustList<TranslatedKey> immuAbility;
    private CustList<TranslatedKey> immuAllyFromMoves;
    private CustList<TranslatedKey> immuMove;
    private CustList<TranslatedKey> immuStatusBeginRound;
    private CustList<TranslatedKey> immuWeather;
    private long decreaseNecStepsHatch;
    private long nbUsedPp;
    private DictionaryComparator<TranslatedKey, Rate> singleStatus;
    private CustList<TranslatedKey> immuLowStat;
    private CustList<TranslatedKey> maxStatisticsIfCh;
    private CustList<TranslatedKeyPair> immuLowStatIfStatus;
    private CustList<TranslatedKeyPair> breakFoeImmune;
    private DictionaryComparator<TranslatedKey, Long> bonusStatRank;
    private DictionaryComparator<TranslatedKey, Long> boostStatRankEndRound;
    private DictionaryComparator<TranslatedKey, Long> boostStatRankProtected;
    private DictionaryComparator<TranslatedKey, Long> lowStatFoeHit;
    private DictionaryComparator<TranslatedKey, Long> multStatIfKoFoe;
    private DictionaryComparator<TranslatedKey, Long> multStatIfLowStat;
    private DictionaryComparator<TranslatedKey, String> multStat;
    private DictionaryComparator<TranslatedKey, Rate> multStatAlly;
    private DictionaryComparator<TranslatedKeyPair, Long> multStatIfDamageCat;
    private DictionaryComparator<TranslatedKeyPair, Rate> multStatIfCat;
    private DictionaryComparator<TranslatedKeyPair, Long> multStatIfStatutRank;
    private DictionaryComparator<TranslatedKeyPair, Long> multStatIfDamgeType;
    private DictionaryComparator<TranslatedKeyPair, Rate> healHpByTypeIfWeather;
    private DictionaryComparator<TranslatedKey, TypeDamageBoost> changingBoostTypes;
    private DictionaryComparator<TranslatedKey, Long> increasedPrio;
    private DictionaryComparator<TranslatedKey, Long> increasedPrioTypes;
    private DictionaryComparator<TranslatedKey, TranslatedKey> chgtTypeByWeather;
    private DictionaryComparator<TranslatedKey, String> failStatus;
    private DictionaryComparator<TranslatedKey, TranslatedKey> forwardStatus;
    private DictionaryComparator<TranslatedKey, Rate> divideStatusRound;
    private DictionaryComparator<TranslatedKey, Rate> healHpByWeather;
    private DictionaryComparator<TranslatedKey, Rate> multDamageFoe;
    private DictionaryComparator<TranslatedKey, Rate> multPowerMovesTypesGlobal;
    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuLowStatisTypes;
    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuMoveTypesByWeather;
    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatus;
    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatusTypes;
    private final EndRoundCommon endRoundCommon = new EndRoundCommon();
//    private boolean endRound;
//    private int endRoundRank;
//    private StringList reasonsEndRound;
//    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;
    private final Rate defEff = Rate.one();
    private final CustList<TranslatedKey> pokemon = new CustList<TranslatedKey>();

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
        breakProtectionMoves = listTrStringsMv(breakProtectionMoves_,getFacade());
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
        immuRechargeRoundMoves = listTrStringsMv(immuRechargeRoundMoves(data_),getFacade());
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
        immuMove = listTrStringsMv(ability_.getImmuMove(),getFacade());
        immuAllyFromMoves = listTrStringsMv(ability_.getImmuAllyFromMoves(),getFacade());
        immuWeather = listTrStringsMv(ability_.getImmuWeather(),getFacade());
        ignAbility = ignAbility(ability_);
        immuAbility = immuAbility(ability_);
        ignFoeTeamMove = listTrStringsMv(ability_.getIgnFoeTeamMove(),getFacade());
        immuStatusBeginRound = listTrStringsSt(ability_.getImmuStatusBeginRound(),getFacade());
        tpForMoves(ability_);
        singleStatus = singleStatus(ability_);
        immuLowStat = listTrStringsSi(ability_.getImmuLowStat(),getFacade());
        maxStatisticsIfCh = listTrStringsSi(ability_.getMaxStatisticsIfCh(),getFacade());
        immuMoveTypesByWeather = immuMoveTypesByWeather(ability_);
        immuStatus = immuStatus(ability_);
        immuStatusTypes = immuStatusTypes(ability_);
        immuLowStatIfStatus = immuLowStatIfStatus(ability_);
        immuLowStatisTypes = immuLowStatisTypes(ability_);
        DictionaryComparator<TranslatedKey,String> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisString();
        for (Statistic s: ability_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(ability_.getMultStat().getVal(s), getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(ability_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(buildSi(getFacade(),s), formula_);
        }
        multStat = multStat_;
        forwardStatus = forwardStatus(ability_);
        DictionaryComparator<TranslatedKey, String> failStatus_ = DictionaryComparatorUtil.buildStatusStrOnly();
        for (String s: ability_.getFailStatus().getKeys()) {
            String fail_ = ability_.getFailStatus().getVal(s);
            String formula_ = data_.getFormula(fail_, getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(fail_, getLanguage()));
            failStatus_.put(buildSt(getFacade(),s), formula_);
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
        DictionaryComparator<TranslatedKey, Long> increasedPrio_;
        increasedPrio_ = DictionaryComparatorUtil.buildCatsShort();
        for (String c: ability_.getIncreasedPrio().getKeys()) {
            increasedPrio_.put(buildCa(getFacade(),c), ability_.getIncreasedPrio().getVal(c));
        }
        increasedPrio = increasedPrio_;
        DictionaryComparator<TranslatedKey, Long> increasedPrioTypes_;
        increasedPrioTypes_ = DictionaryComparatorUtil.buildTypesShort();
        for (String c: ability_.getIncreasedPrioTypes().getKeys()) {
            increasedPrioTypes_.put(buildTy(getFacade(),c), ability_.getIncreasedPrioTypes().getVal(c));
        }
        increasedPrioTypes = increasedPrioTypes_;
        DictionaryComparator<TranslatedKey, TranslatedKey> chgtTypeByWeather_;
        chgtTypeByWeather_ = DictionaryComparatorUtil.buildMovesStr();
        for (String c: ability_.getChgtTypeByWeather().getKeys()) {
            chgtTypeByWeather_.put(buildMv(getFacade(),c), buildTy(getFacade(),ability_.getChgtTypeByWeather().getVal(c)));
        }
        chgtTypeByWeather = chgtTypeByWeather_;
        DictionaryComparator<TranslatedKey, Rate> divideStatusRound_;
        divideStatusRound_ = DictionaryComparatorUtil.buildStatusRate();
        for (String c: ability_.getDivideStatusRound().getKeys()) {
            divideStatusRound_.put(buildSt(getFacade(),c), ability_.getDivideStatusRound().getVal(c));
        }
        divideStatusRound = divideStatusRound_;
        DictionaryComparator<TranslatedKey, Rate> healHpByWeather_;
        healHpByWeather_ = DictionaryComparatorUtil.buildMovesRate();
        for (String c: ability_.getHealHpByWeather().getKeys()) {
            healHpByWeather_.put(buildMv(getFacade(),c), ability_.getHealHpByWeather().getVal(c));
        }
        healHpByWeather = healHpByWeather_;
        DictionaryComparator<TranslatedKeyPair, Rate> healHpByTypeIfWeather_;
        healHpByTypeIfWeather_ = DictionaryComparatorUtil.buildWeatherType();
        for (WeatherType w: ability_.getHealHpByTypeIfWeather().getKeys()) {
            healHpByTypeIfWeather_.put(buildPair(getFacade(), w), ability_.getHealHpByTypeIfWeather().getVal(w));
        }
        healHpByTypeIfWeather = healHpByTypeIfWeather_;
        DictionaryComparator<TranslatedKey, TypeDamageBoost> changingBoostTypes_;
        changingBoostTypes_ = DictionaryComparatorUtil.buildTypesTypeDamageBoost();
        for (String w: ability_.getChangingBoostTypes().getKeys()) {
            changingBoostTypes_.put(buildTy(getFacade(),w), ability_.getChangingBoostTypes().getVal(w));
        }
        changingBoostTypes = changingBoostTypes_;
        DictionaryComparator<TranslatedKeyPair, Long> multStatIfDamageCat_;
        multStatIfDamageCat_ = DictionaryComparatorUtil.buildStatisticCategoryByte();
        for (StatisticCategory w: ability_.getMultStatIfDamageCat().getKeys()) {
            multStatIfDamageCat_.put(buildPair(getFacade(),w), ability_.getMultStatIfDamageCat().getVal(w));
        }
        multStatIfDamageCat = multStatIfDamageCat_;
        DictionaryComparator<TranslatedKeyPair, Rate> multStatIfCat_;
        multStatIfCat_ = DictionaryComparatorUtil.buildStatisticCategoryRate();
        for (StatisticCategory w: ability_.getMultStatIfCat().getKeys()) {
            multStatIfCat_.put(buildPair(getFacade(),w), ability_.getMultStatIfCat().getVal(w));
        }
        multStatIfCat = multStatIfCat_;
        DictionaryComparator<TranslatedKeyPair, Long> multStatIfDamgeType_;
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
        multStatIfDamgeType_ = DictionaryComparatorUtil.buildStatisTypeByte();
        for (StatisticType w: ability_.getMultStatIfDamgeType().getKeys()) {
            multStatIfDamgeType_.put(buildPair(getFacade(),w), ability_.getMultStatIfDamgeType().getVal(w));
        }
        multStatIfDamgeType = multStatIfDamgeType_;
        DictionaryComparator<TranslatedKeyPair, Long> multStatIfStatutRank_;
        multStatIfStatutRank_ = DictionaryComparatorUtil.buildStatisticStatus();
        for (StatisticStatus w: ability_.getMultStatIfStatutRank().getKeys()) {
            multStatIfStatutRank_.put(buildPair(getFacade(), w), ability_.getMultStatIfStatutRank().getVal(w));
        }
        multStatIfStatutRank = multStatIfStatutRank_;
        DictionaryComparator<TranslatedKey, Rate> multDamageFoe_;
        multDamageFoe_ = DictionaryComparatorUtil.buildTypesRate();
        for (String c: ability_.getMultDamageFoe().getKeys()) {
            multDamageFoe_.put(buildTy(getFacade(), c), ability_.getMultDamageFoe().getVal(c));
        }
        multDamageFoe = multDamageFoe_;
        DictionaryComparator<TranslatedKey, Rate> multPowerMovesTypesGlobal_;
        multPowerMovesTypesGlobal_ = DictionaryComparatorUtil.buildTypesRate();
        for (String c: ability_.getMultPowerMovesTypesGlobal().getKeys()) {
            multPowerMovesTypesGlobal_.put(buildTy(getFacade(), c), ability_.getMultPowerMovesTypesGlobal().getVal(c));
        }
        multPowerMovesTypesGlobal = multPowerMovesTypesGlobal_;
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
    }

    public static TranslatedKeyPair buildPair(FacadeGame _data, StatisticType _w) {
        return new TranslatedKeyPair(buildSi(_data, _w.getStatistic()), buildTy(_data, _w.getType()));
    }

    public static TranslatedKeyPair buildPair(FacadeGame _data, StatisticCategory _w) {
        return new TranslatedKeyPair(buildSi(_data, _w.getStatistic()), buildCa(_data, _w.getCategory()));
    }

    public static TranslatedKeyPair buildPair(FacadeGame _data, StatisticStatus _w) {
        return new TranslatedKeyPair(buildSi(_data, _w.getStatistic()), buildSt(_data, _w.getStatus()));
    }

    public static TranslatedKeyPair buildPairRev(FacadeGame _data, StatisticStatus _w) {
        return new TranslatedKeyPair(buildSt(_data, _w.getStatus()),buildSi(_data, _w.getStatistic()));
    }

    public static TranslatedKeyPair buildPair(FacadeGame _data, WeatherType _w) {
        return new TranslatedKeyPair(buildMv(_data, _w.getWeather()), buildTy(_data, _w.getType()));
    }

    private DictionaryComparator<TranslatedKey, Rate> multStatAlly(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Rate> multStatAlly_;
        multStatAlly_ = DictionaryComparatorUtil.buildStatisRate();
        for (Statistic s: _ability.getMultStatAlly().getKeys()) {
            multStatAlly_.put(buildSi(getFacade(),s), _ability.getMultStatAlly().getVal(s));
        }
        return multStatAlly_;
    }

    private DictionaryComparator<TranslatedKey, Long> multStatIfLowStat(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> multStatIfLowStat_;
        multStatIfLowStat_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getMultStatIfLowStat().getKeys()) {
            multStatIfLowStat_.put(buildSi(getFacade(),s), _ability.getMultStatIfLowStat().getVal(s));
        }
        return multStatIfLowStat_;
    }

    private DictionaryComparator<TranslatedKey, Long> multStatIfKoFoe(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> multStatIfKoFoe_;
        multStatIfKoFoe_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getMultStatIfKoFoe().getKeys()) {
            multStatIfKoFoe_.put(buildSi(getFacade(),s), _ability.getMultStatIfKoFoe().getVal(s));
        }
        return multStatIfKoFoe_;
    }

    private DictionaryComparator<TranslatedKey, Long> lowStatFoeHit(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> lowStatFoeHit_;
        lowStatFoeHit_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getLowStatFoeHit().getKeys()) {
            lowStatFoeHit_.put(buildSi(getFacade(),s), _ability.getLowStatFoeHit().getVal(s));
        }
        return lowStatFoeHit_;
    }

    private DictionaryComparator<TranslatedKey, Long> boostStatRankProtected(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> boostStatRankProtected_;
        boostStatRankProtected_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getBoostStatRankProtected().getKeys()) {
            boostStatRankProtected_.put(buildSi(getFacade(),s), _ability.getBoostStatRankProtected().getVal(s));
        }
        return boostStatRankProtected_;
    }

    private DictionaryComparator<TranslatedKey, Long> boostStatRankEndRound(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> boostStatRankEndRound_;
        boostStatRankEndRound_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getBoostStatRankEndRound().getKeys()) {
            boostStatRankEndRound_.put(buildSi(getFacade(),s), _ability.getBoostStatRankEndRound().getVal(s));
        }
        return boostStatRankEndRound_;
    }

    private DictionaryComparator<TranslatedKey, Long> bonusStatRank(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Long> bonusStatRank_;
        bonusStatRank_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: _ability.getBonusStatRank().getKeys()) {
            bonusStatRank_.put(buildSi(getFacade(),s), _ability.getBonusStatRank().getVal(s));
        }
        return bonusStatRank_;
    }

    private CustList<TranslatedKeyPair> breakFoeImmune(AbilityData _ability) {
        CustList<TranslatedKeyPair> breakFoeImmune_;
        breakFoeImmune_ = new CustList<TranslatedKeyPair>();
        for (TypesDuo s: _ability.getBreakFoeImmune()) {
            breakFoeImmune_.add(new TranslatedKeyPair(buildTy(getFacade(),s.getDamageType()),buildTy(getFacade(),s.getPokemonType())));
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
        breakFoeImmune_.sortElts(new ComparatorTranslatedKeyPair());
        return breakFoeImmune_;
    }

    private DictionaryComparator<TranslatedKey, TranslatedKey> forwardStatus(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, TranslatedKey> forwardStatus_;
        forwardStatus_ = DictionaryComparatorUtil.buildStatusStr();
        for (String s: _ability.getForwardStatus().getKeys()) {
            forwardStatus_.put(buildSt(getFacade(),s), buildSt(getFacade(), _ability.getForwardStatus().getVal(s)));
        }
        return forwardStatus_;
    }

    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuLowStatisTypes(AbilityData _ability) {
//        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuLowStatisTypes_;
        immuLowStatisTypes_ = DictionaryComparatorUtil.buildTypesStrList();
        for (String t: _ability.getImmuLowStatisTypes().getKeys()) {
            immuLowStatisTypes_.put(buildTy(getFacade(), t), listTrStringsSi(_ability.getImmuLowStatisTypes().getVal(t),getFacade()));
        }
//        for (IdList<Statistic> v: immuLowStatisTypes_.values()) {
//            v.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
//        }
        return immuLowStatisTypes_;
    }

    private CustList<TranslatedKeyPair> immuLowStatIfStatus(AbilityData _ability) {
        CustList<TranslatedKeyPair> immuLowStatIfStatus_;
        immuLowStatIfStatus_ = new CustList<TranslatedKeyPair>();
        for (StatisticStatus s: _ability.getImmuLowStatIfStatus()) {
            immuLowStatIfStatus_.add(buildPairRev(getFacade(),new StatisticStatus(s.getStatistic(),s.getStatus())));
        }
        immuLowStatIfStatus_.sortElts(new ComparatorTranslatedKeyPair());
        return immuLowStatIfStatus_;
    }

    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatusTypes(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatusTypes_;
        immuStatusTypes_ = DictionaryComparatorUtil.buildTypesStrList();
        for (String t: _ability.getImmuStatusTypes().getKeys()) {
            CustList<TranslatedKey> sub_ = new CustList<TranslatedKey>();
            for (String s: _ability.getImmuStatusTypes().getVal(t)) {
                sub_.add(buildSt(getFacade(),s));
            }
            sub_.sortElts(new ComparingTranslatedKey());
            immuStatusTypes_.put(buildTy(getFacade(),t), sub_);
        }
        return immuStatusTypes_;
    }

    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatus(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuStatus_;
        immuStatus_ = DictionaryComparatorUtil.buildMovesStrList();
        for (String t: _ability.getImmuStatus().getKeys()) {
            CustList<TranslatedKey> sub_ = new CustList<TranslatedKey>();
            for (String s: _ability.getImmuStatus().getVal(t)) {
                sub_.add(buildSt(getFacade(),s));
            }
            sub_.sortElts(new ComparingTranslatedKey());
            immuStatus_.put(buildMv(getFacade(),t), sub_);
        }
        return immuStatus_;
    }

    private DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuMoveTypesByWeather(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> immuMoveTypesByWeather_;
        immuMoveTypesByWeather_ = DictionaryComparatorUtil.buildMovesStrList();
        for (String t: _ability.getImmuMoveTypesByWeather().getKeys()) {
            CustList<TranslatedKey> sub_ = new CustList<TranslatedKey>();
            for (String s: _ability.getImmuMoveTypesByWeather().getVal(t)) {
                sub_.add(buildTy(getFacade(),s));
            }
            sub_.sortElts(new ComparingTranslatedKey());
            immuMoveTypesByWeather_.put(buildMv(getFacade(),t), sub_);
        }
        return immuMoveTypesByWeather_;
    }
//
//    private IdList<Statistic> maxStatisticsIfCh(AbilityData _ability) {
//        DataBase data_ = getDataBase();
//        IdList<Statistic> maxStatisticsIfCh_;
//        maxStatisticsIfCh_ = new IdList<Statistic>();
//        for (Statistic s: _ability.getMaxStatisticsIfCh()) {
//            maxStatisticsIfCh_.add(s);
//        }
//        maxStatisticsIfCh_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
//        return maxStatisticsIfCh_;
//    }
//
//    private IdList<Statistic> immuLowStat(AbilityData _ability) {
//        DataBase data_ = getDataBase();
//        IdList<Statistic> immuLowStat_;
//        immuLowStat_ = new IdList<Statistic>();
//        for (Statistic s: _ability.getImmuLowStat()) {
//            immuLowStat_.add(s);
//        }
//        immuLowStat_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
//        return immuLowStat_;
//    }

    private DictionaryComparator<TranslatedKey, Rate> singleStatus(AbilityData _ability) {
        DictionaryComparator<TranslatedKey, Rate> singleStatus_;
        singleStatus_ = DictionaryComparatorUtil.buildStatusRate();
        for (String s: _ability.getSingleStatus().eventsDiff()) {
            singleStatus_.put(buildSt(getFacade(),s), _ability.getSingleStatus().normalizedRate(s));
        }
        return singleStatus_;
    }

    private void tpForMoves(AbilityData _ability) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_;
//        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        if (!_ability.getTypeForMoves().isEmpty()) {
            typeForMoves = buildTy(getFacade(),_ability.getTypeForMoves());
        } else {
            typeForMoves = new TranslatedKey(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        }
    }

    private CustList<TranslatedKey> immuAbility(AbilityData _ability) {
        CustList<TranslatedKey> immuAbility_;
        immuAbility_ = new CustList<TranslatedKey>();
        for (String m: _ability.getImmuAbility()) {
            immuAbility_.add(buildAb(getFacade(),m));
        }
        immuAbility_.sortElts(DictionaryComparatorUtil.cmpAbilities());
        return immuAbility_;
    }

    private CustList<TranslatedKey> ignAbility(AbilityData _ability) {
        CustList<TranslatedKey> ignAbility_;
        ignAbility_ = new CustList<TranslatedKey>();
        for (String m: _ability.getIgnAbility()) {
            ignAbility_.add(buildAb(getFacade(),m));
        }
        ignAbility_.sortElts(DictionaryComparatorUtil.cmpAbilities());
        return ignAbility_;
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

    private CustList<TranslatedKey> reverseEffectsPowerMovesTypesGlobalAbilities() {
        CustList<TranslatedKey> reverseEffectsPowerMovesTypesGlobalAbilities_ = reverseEffects(getFacade());
        reverseEffectsPowerMovesTypesGlobalAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities());
        return reverseEffectsPowerMovesTypesGlobalAbilities_;
    }

    static CustList<TranslatedKey> reverseEffects(FacadeGame _data) {
        CustList<TranslatedKey> reverseEffectsPowerMovesTypesGlobalAbilities_;
        reverseEffectsPowerMovesTypesGlobalAbilities_ = new CustList<TranslatedKey>();
        for (String a: _data.getData().getAbilities().getKeys()) {
            AbilityData ab_ = _data.getData().getAbility(a);
            if (!ab_.getMultPowerMovesTypesGlobal().isEmpty()) {
                reverseEffectsPowerMovesTypesGlobalAbilities_.add(buildAb(_data,a));
            }
        }
        return reverseEffectsPowerMovesTypesGlobalAbilities_;
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
        pokemon.addAllElts(listTrStringsPk(pkLearn(data_,name),getFacade()));
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
        return immuMove.get(_index).getTranslation();
//        String type_ = immuMove.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickIndex() {
        getForms().safeAbilities(CST_ABILITIES_SET);
        return PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML;
    }
    public String clickImmuMove(int _index) {
        return tryRedirect(immuMove.get(_index));
    }
    public String getTrImmuAllyFromMoves(int _index) {
        return immuAllyFromMoves.get(_index).getTranslation();
//        String type_ = immuAllyFromMoves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickImmuAllyFromMoves(int _index) {
        return tryRedirect(immuAllyFromMoves.get(_index));
    }
    public String getTrWeather(int _index) {
        return immuWeather.get(_index).getTranslation();
//        return getDataBase().getTranslatedMoves().getVal(getLanguage()).getVal(immuWeather.get(_index));
    }
    public String clickWeather(int _index) {
        return tryRedirect(immuWeather.get(_index));
    }
    public String getTrIgnAbility(int _index) {
        return ignAbility.get(_index).getTranslation();
//        String type_ = ignAbility.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translatedAbilities_.getVal(type_);
    }
    public String clickIgnAbility(int _index) {
//        String type_ = ignAbility.get(_index).getKey();
//        ignAbility.get(_index).getRedirect().manage();
        return tryRedirect(ignAbility.get(_index));
//        return tryRedirectAb(type_);
    }
    public String getTrIgnFoeTeamMove(int _index) {
        return ignFoeTeamMove.get(_index).getTranslation();
//        String type_ = ignFoeTeamMove.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickIgnFoeTeamMove(int _index) {
        return tryRedirect(ignFoeTeamMove.get(_index));
    }
    public String getTrImmuAbility(int _index) {
        return immuAbility.get(_index).getTranslation();
//        String type_ = immuAbility.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translatedAbilities_.getVal(type_);
    }
    public String clickImmuAbility(int _index) {
        return tryRedirect(immuAbility.get(_index));
//        String type_ = immuAbility.get(_index).getKey();
//        return tryRedirectAb(type_);
    }
    public String getTrImmuStatusBeginRound(int _index) {
        return immuStatusBeginRound.get(_index).getTranslation();
//        String type_ = immuStatusBeginRound.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatusBeginRound(int _index) {
        return tryRedirect(immuStatusBeginRound.get(_index));
    }
    public boolean isStatus(int _index) {
        return !singleStatus.getKey(_index).getKey().isEmpty();
    }
    public String getTrSingleStatus(int _index) {
        return singleStatus.getKey(_index).getTranslation();
//        String type_ = singleStatus.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(type_);
    }
    public String clickSingleStatus(int _index) {
        return tryRedirect(singleStatus.getKey(_index));
    }
    public AbilityData getAbility() {
        DataBase data_ = getDataBase();
        return data_.getAbility(name);
    }
    public String getTrBreakProtectionMoves(int _index) {
        return breakProtectionMoves.get(_index).getTranslation();
//        String move_ = breakProtectionMoves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(move_);
    }
    public String clickBreakProtectionMoves(int _index) {
        return tryRedirect(breakProtectionMoves.get(_index));
    }
    public String getTrImmuRechargeRoundMoves(int _index) {
        return immuRechargeRoundMoves.get(_index).getTranslation();
//        String move_ = immuRechargeRoundMoves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(move_);
    }
    public String clickImmuRechargeRoundMoves(int _index) {
        return tryRedirect(immuRechargeRoundMoves.get(_index));
    }
    public String getTrReversePowerTypesAbilities(int _index) {
        return reverseEffectsPowerMovesTypesGlobalAbilities.get(_index).getTranslation();
//        String move_ = reverseEffectsPowerMovesTypesGlobalAbilities.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translationsAbilities_.getVal(move_);
    }
    public String clickReversePowerTypesAbilities(int _index) {
        return tryRedirect(reverseEffectsPowerMovesTypesGlobalAbilities.get(_index));
//        String move_ = reverseEffectsPowerMovesTypesGlobalAbilities.get(_index).getKey();
//        return tryRedirectAb(move_);
    }
    public String getTrMultStat(int _index) {
        return multStat.getKey(_index).getTranslation();
//        Statistic type_ = multStat.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrImmuLowStat(int _index) {
        return immuLowStat.get(_index).getTranslation();
//        Statistic type_ = immuLowStat.get(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrMaxStatisticsIfCh(int _index) {
        return maxStatisticsIfCh.get(_index).getTranslation();
//        Statistic type_ = maxStatisticsIfCh.get(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public boolean isMoveByWeather(int _index) {
        return !immuMoveTypesByWeather.getKey(_index).getKey().isEmpty();
    }
    public String getTrImmuMoveByWeather(int _index) {
        return immuMoveTypesByWeather.getKey(_index).getTranslation();
//        String move_ = immuMoveTypesByWeather.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(move_);
    }
    public String clickImmuMoveByWeather(int _index) {
        return tryRedirect(immuMoveTypesByWeather.getKey(_index));
    }
    public String getTrImmuTypeByWeather(int _indexOne, int _indexTwo) {
        return immuMoveTypesByWeather.getValue(_indexOne).get(_indexTwo).getTranslation();
//        String move_ = immuMoveTypesByWeather.getValue(_indexOne).get(_indexTwo);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public boolean isMoveByStatus(int _index) {
        return !immuStatus.getKey(_index).getKey().isEmpty();
    }
    public String getTrImmuStatusWeather(int _index) {
        return immuStatus.getKey(_index).getTranslation();
//        String move_ = immuStatus.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(move_);
    }
    public String clickImmuStatusWeather(int _index) {
        return tryRedirect(immuStatus.getKey(_index));
    }
    public String getTrImmuStatus(int _indexOne, int _indexTwo) {
        return immuStatus.getValue(_indexOne).get(_indexTwo).getTranslation();
//        String move_ = immuStatus.getValue(_indexOne).get(_indexTwo);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(move_);
    }
    public String clickImmuStatus(int _indexOne, int _indexTwo) {
        return tryRedirect(immuStatus.getValue(_indexOne).get(_indexTwo));
    }
    public String getTrImmuStatusTypes(int _index) {
        return immuStatusTypes.getKey(_index).getTranslation();
//        String move_ = immuStatusTypes.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public String getTrImmuStatusValue(int _indexOne, int _indexTwo) {
        return immuStatusTypes.getValue(_indexOne).get(_indexTwo).getTranslation();
//        String move_ = immuStatusTypes.getValue(_indexOne).get(_indexTwo);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(move_);
    }
    public String clickImmuStatusTypes(int _indexOne, int _indexTwo) {
        return tryRedirect(immuStatusTypes.getValue(_indexOne).get(_indexTwo));
    }
    public String getTrForwardStatusKey(int _index) {
        return forwardStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickForwardStatusKey(int _index) {
        return tryRedirect(forwardStatus.getKey(_index));
    }
    public String getTrForwardStatusValue(int _index) {
        return forwardStatus.getValue(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickForwardStatusValue(int _index) {
        return tryRedirect(forwardStatus.getValue(_index));
    }
    public String getTrFailStatus(int _index) {
        return failStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickFailStatus(int _index) {
        return tryRedirect(failStatus.getKey(_index));
    }
    public String getTrImmuLowStatIfStatusKey(int _index) {
        return immuLowStatIfStatus.get(_index).getFirst().getTranslation();
//        String status_ = immuLowStatIfStatus.get(_index).getStatus();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickImmuLowStatIfStatusKey(int _index) {
        return tryRedirect(immuLowStatIfStatus.get(_index).getFirst());
    }
    public String getTrImmuLowStatIfStatusValue(int _index) {
        return immuLowStatIfStatus.get(_index).getSecond().getTranslation();
//        Statistic status_ = immuLowStatIfStatus.get(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(status_);
    }
    public boolean isChgtTypeByWeather(int _index) {
        return !chgtTypeByWeather.getKey(_index).getKey().isEmpty();
    }
    public String getTrChgtTypeByWeatherKey(int _index) {
        return chgtTypeByWeather.getKey(_index).getTranslation();
//        String status_ = chgtTypeByWeather.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(status_);
    }
    public String clickChgtTypeByWeatherKey(int _index) {
        return tryRedirect(chgtTypeByWeather.getKey(_index));
    }
    public String getTrChgtTypeByWeatherValue(int _index) {
        return chgtTypeByWeather.getValue(_index).getTranslation();
//        String status_ = chgtTypeByWeather.getValue(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(status_);
    }
    public String getTrDivideStatusRoundKey(int _index) {
        return divideStatusRound.getKey(_index).getTranslation();
//        String status_ = divideStatusRound.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickDivideStatusRoundKey(int _index) {
        return tryRedirect(divideStatusRound.getKey(_index));
    }
    public boolean isHealHpByWeather(int _index) {
        return !healHpByWeather.getKey(_index).getKey().isEmpty();
    }
    public String getTrHealHpByWeatherKey(int _index) {
        return healHpByWeather.getKey(_index).getTranslation();
//        String status_ = healHpByWeather.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(status_);
    }
    public String clickHealHpByWeatherKey(int _index) {
        return tryRedirect(healHpByWeather.getKey(_index));
    }
    public boolean isHealHpByTypeIfWeather(int _index) {
        return !healHpByTypeIfWeather.getKey(_index).getFirst().getKey().isEmpty();
    }
    public String getTrHealHpByTypeIfWeatherKey(int _index) {
        return healHpByTypeIfWeather.getKey(_index).getFirst().getTranslation();
//        String status_ = healHpByTypeIfWeather.getKey(_index).getWeather();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(status_);
    }
    public String clickHealHpByTypeIfWeatherKey(int _index) {
        return tryRedirect(healHpByTypeIfWeather.getKey(_index).getFirst());
    }
    public String getTrHealHpByTypeIfWeatherKeySec(int _index) {
        return healHpByTypeIfWeather.getKey(_index).getSecond().getTranslation();
//        String status_ = healHpByTypeIfWeather.getKey(_index).getType();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(status_);
    }
    public String getTrMultStatIfDamageCatKey(int _index) {
        return multStatIfDamageCat.getKey(_index).getFirst().getTranslation();
//        Statistic status_ = multStatIfDamageCat.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfDamageCatKeySec(int _index) {
        return multStatIfDamageCat.getKey(_index).getSecond().getTranslation();
//        String status_ = multStatIfDamageCat.getKey(_index).getCategory();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsCategories_;
//        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
//        return translationsCategories_.getVal(status_);
    }
    public String getTrMultStatIfCatKey(int _index) {
        return multStatIfCat.getKey(_index).getFirst().getTranslation();
//        Statistic status_ = multStatIfCat.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfCatKeySec(int _index) {
        return multStatIfCat.getKey(_index).getSecond().getTranslation();
//        String status_ = multStatIfCat.getKey(_index).getCategory();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsCategories_;
//        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
//        return translationsCategories_.getVal(status_);
    }
    public String getTrMultStatIfDamgeType(int _index) {
        return multStatIfDamgeType.getKey(_index).getFirst().getTranslation();
//        Statistic status_ = multStatIfDamgeType.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfDamgeTypeSec(int _index) {
        return multStatIfDamgeType.getKey(_index).getSecond().getTranslation();
//        String status_ = multStatIfDamgeType.getKey(_index).getType();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(status_);
    }
    public String getTrMultStatIfStatutRank(int _index) {
        return multStatIfStatutRank.getKey(_index).getFirst().getTranslation();
//        Statistic status_ = multStatIfStatutRank.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(status_);
    }
    public String getTrMultStatIfStatutRankSec(int _index) {
        return multStatIfStatutRank.getKey(_index).getSecond().getTranslation();
//        String status_ = multStatIfStatutRank.getKey(_index).getStatus();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String clickMultStatIfStatutRankSec(int _index) {
        return tryRedirect(multStatIfStatutRank.getKey(_index).getSecond());
    }
    public String getTrMultPowerMovesTypesGlobalKey(int _index) {
        return multPowerMovesTypesGlobal.getKey(_index).getTranslation();
//        String status_ = multPowerMovesTypesGlobal.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsMoves_.getVal(status_);
    }
    public String getTrImmuLowStatisTypes(int _index) {
        return immuLowStatisTypes.getKey(_index).getTranslation();
//        String move_ = immuLowStatisTypes.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public String getTrImmuLowStatisValue(int _indexOne, int _indexTwo) {
        return immuLowStatisTypes.getValue(_indexOne).get(_indexTwo).getTranslation();
//        Statistic move_ = immuLowStatisTypes.getValue(_indexOne).get(_indexTwo);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrBreakFoeImmuneKey(int _index) {
        return breakFoeImmune.get(_index).getFirst().getTranslation();
//        String status_ = breakFoeImmune.get(_index).getDamageType();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String getTrBreakFoeImmuneValue(int _index) {
        return breakFoeImmune.get(_index).getSecond().getTranslation();
//        String status_ = breakFoeImmune.get(_index).getPokemonType();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsStatus_.getVal(status_);
    }
    public String getTrBonusStatRank(int _index) {
        return bonusStatRank.getKey(_index).getTranslation();
//        Statistic move_ = bonusStatRank.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankEndRound(int _index) {
        return boostStatRankEndRound.getKey(_index).getTranslation();
//        Statistic move_ = boostStatRankEndRound.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankProtected(int _index) {
        return boostStatRankProtected.getKey(_index).getTranslation();
//        Statistic move_ = boostStatRankProtected.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrLowStatFoeHit(int _index) {
        return lowStatFoeHit.getKey(_index).getTranslation();
//        Statistic move_ = lowStatFoeHit.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfKoFoe(int _index) {
        return multStatIfKoFoe.getKey(_index).getTranslation();
//        Statistic move_ = multStatIfKoFoe.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfLowStat(int _index) {
        return multStatIfLowStat.getKey(_index).getTranslation();
//        Statistic move_ = multStatIfLowStat.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatAlly(int _index) {
        return multStatAlly.getKey(_index).getTranslation();
//        Statistic move_ = multStatAlly.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translationsStatistics_;
//        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translationsStatistics_.getVal(move_);
    }
    public String getTrIncreasedPrio(int _index) {
        return increasedPrio.getKey(_index).getTranslation();
//        String move_ = increasedPrio.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsCategories_;
//        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
//        return translationsCategories_.getVal(move_);
    }
    public String getTrIncreasedPrioTypes(int _index) {
        return increasedPrioTypes.getKey(_index).getTranslation();
//        String move_ = increasedPrioTypes.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public String getTrMultDamageFoe(int _index) {
        return multDamageFoe.getKey(_index).getTranslation();
//        String move_ = multDamageFoe.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public String getTrChangingBoostTypesOld(int _index) {
        return changingBoostTypes.getKey(_index).getTranslation();
//        String move_ = changingBoostTypes.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translationsTypes_.getVal(move_);
    }
    public String getTrChangingBoostTypesNew(int _index) {
        String move_ = changingBoostTypes.getValue(_index).getType();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsTypes_.getVal(move_);
    }
    public String getTrPokemon(int _index) {
        return pokemon.get(_index).getTranslation();
//        String pk_ = pokemon.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemons_;
//        translationsPokemons_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translationsPokemons_.getVal(pk_);
    }
    public String clickPokemon(int _index) {
        return tryRedirect(pokemon.get(_index));
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

    public CustList<TranslatedKey> getBreakProtectionMoves() {
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

    public DictionaryComparator<TranslatedKey,TranslatedKey> getChgtTypeByWeather() {
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

    public CustList<TranslatedKey> getImmuRechargeRoundMoves() {
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

    public CustList<TranslatedKey> getReverseEffectsPowerMovesTypesGlobalAbilities() {
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

    public CustList<TranslatedKey> getImmuMove() {
        return immuMove;
    }

    public CustList<TranslatedKey> getImmuAllyFromMoves() {
        return immuAllyFromMoves;
    }

    public CustList<TranslatedKey> getImmuWeather() {
        return immuWeather;
    }

    public CustList<TranslatedKey> getIgnAbility() {
        return ignAbility;
    }

    public CustList<TranslatedKey> getIgnFoeTeamMove() {
        return ignFoeTeamMove;
    }

    public CustList<TranslatedKey> getImmuAbility() {
        return immuAbility;
    }

    public CustList<TranslatedKey> getImmuStatusBeginRound() {
        return immuStatusBeginRound;
    }

    public TranslatedKey getTypeForMoves() {
        return typeForMoves;
    }

    public DictionaryComparator<TranslatedKey,TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public String getMultPower() {
        return multPower;
    }

    public String getMultDamage() {
        return multDamage;
    }

    public DictionaryComparator<TranslatedKey,Rate> getHealHpByWeather() {
        return healHpByWeather;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public CustList<TranslatedKey> getImmuLowStat() {
        return immuLowStat;
    }

    public CustList<TranslatedKeyPair> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public DictionaryComparator<TranslatedKey,CustList<TranslatedKey>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
    }

    public CustList<TranslatedKey> getMaxStatisticsIfCh() {
        return maxStatisticsIfCh;
    }

    public DictionaryComparator<TranslatedKey,Rate> getSingleStatus() {
        return singleStatus;
    }

    public DictionaryComparator<TranslatedKey,CustList<TranslatedKey>> getImmuMoveTypesByWeather() {
        return immuMoveTypesByWeather;
    }

    public DictionaryComparator<TranslatedKey,CustList<TranslatedKey>> getImmuStatus() {
        return immuStatus;
    }

    public DictionaryComparator<TranslatedKey,CustList<TranslatedKey>> getImmuStatusTypes() {
        return immuStatusTypes;
    }

    public DictionaryComparator<TranslatedKey,Rate> getDivideStatusRound() {
        return divideStatusRound;
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getForwardStatus() {
        return forwardStatus;
    }

    public CustList<TranslatedKeyPair> getBreakFoeImmune() {
        return breakFoeImmune;
    }

    public Rate getDefEff() {
        return defEff;
    }

    public DictionaryComparator<TranslatedKey,String> getMultStat() {
        return multStat;
    }

    public DictionaryComparator<TranslatedKeyPair,Long> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public DictionaryComparator<TranslatedKeyPair,Long> getMultStatIfDamgeType() {
        return multStatIfDamgeType;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getMultStatIfCat() {
        return multStatIfCat;
    }

    public DictionaryComparator<TranslatedKeyPair,Long> getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public DictionaryComparator<TranslatedKey,Long> getBonusStatRank() {
        return bonusStatRank;
    }

    public DictionaryComparator<TranslatedKey,Long> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public DictionaryComparator<TranslatedKey,Long> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public DictionaryComparator<TranslatedKey,Long> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public DictionaryComparator<TranslatedKey,Long> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public DictionaryComparator<TranslatedKey,Long> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public DictionaryComparator<TranslatedKey,Long> getIncreasedPrio() {
        return increasedPrio;
    }

    public DictionaryComparator<TranslatedKey,Long> getIncreasedPrioTypes() {
        return increasedPrioTypes;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultDamageFoe() {
        return multDamageFoe;
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultPowerMovesTypesGlobal() {
        return multPowerMovesTypesGlobal;
    }

    public DictionaryComparator<TranslatedKey,String> getFailStatus() {
        return failStatus;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public Rate getHealedHpRateBySwitch() {
        return healedHpRateBySwitch;
    }

    public CustList<TranslatedKey> getPokemon() {
        return pokemon;
    }
}