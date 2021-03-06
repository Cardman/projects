package aiki.beans.abilities;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStatisticCategory;
import aiki.beans.facade.comparators.ComparatorStatisticType;
import aiki.beans.facade.comparators.ComparatorStatusStatistic;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.beans.facade.comparators.ComparatorWeatherType;
import aiki.beans.facade.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypeDamageBoost;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.WeatherType;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;

public class AbilityBean extends CommonBean {
    static final String EFFECT_SEND_BEAN="web/html/sending/effsending.html";
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
    private Rate recoilDamageFoeByKoOwner;
    private StringList ignAbility;
    private StringList ignFoeTeamMove;
    private StringList immuAbility;
    private StringList immuAllyFromMoves;
    private StringList immuMove;
    private StringList immuStatusBeginRound;
    private StringList immuWeather;
    private int decreaseNecStepsHatch;
    private int nbUsedPp;
    private TreeMap<String, Rate> singleStatus;
    private EnumList<Statistic> immuLowStat;
    private EnumList<Statistic> maxStatisticsIfCh;
    private CustList<StatisticStatus> immuLowStatIfStatus;
    private CustList<TypesDuo> breakFoeImmune;
    private TreeMap<Statistic, Byte> bonusStatRank;
    private TreeMap<Statistic, Byte> boostStatRankEndRound;
    private TreeMap<Statistic, Byte> boostStatRankProtected;
    private TreeMap<Statistic, Byte> lowStatFoeHit;
    private TreeMap<Statistic, Byte> multStatIfKoFoe;
    private TreeMap<Statistic, Byte> multStatIfLowStat;
    private TreeMap<Statistic, String> multStat;
    private TreeMap<Statistic, Rate> multStatAlly;
    private TreeMap<StatisticCategory, Byte> multStatIfDamageCat;
    private TreeMap<StatisticCategory, Rate> multStatIfCat;
    private TreeMap<StatisticStatus, Byte> multStatIfStatutRank;
    private TreeMap<StatisticType, Byte> multStatIfDamgeType;
    private TreeMap<WeatherType, Rate> healHpByTypeIfWeather;
    private TreeMap<String, TypeDamageBoost> changingBoostTypes;
    private TreeMap<String, Short> increasedPrio;
    private TreeMap<String, Short> increasedPrioTypes;
    private TreeMap<String, String> chgtTypeByWeather;
    private TreeMap<String, String> failStatus;
    private TreeMap<String, String> forwardStatus;
    private TreeMap<String, Rate> divideStatusRound;
    private TreeMap<String, Rate> healHpByWeather;
    private TreeMap<String, Rate> multDamageFoe;
    private TreeMap<String, Rate> multPowerMovesTypesGlobal;
    private TreeMap<String,EnumList<Statistic>> immuLowStatisTypes;
    private TreeMap<String, StringList> immuMoveTypesByWeather;
    private TreeMap<String, StringList> immuStatus;
    private TreeMap<String, StringList> immuStatusTypes;
    private boolean endRound;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;
    private final Rate defEff = Rate.one();
    private final StringList pokemon = new StringList();

    @Override
    public void beforeDisplaying() {
        name = getForms().getValStr(CST_ABILITY);
        pokemon.clear();
        DataBase data_ = getDataBase();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pk_ = data_.getPokemon(p);
            if (!StringUtil.contains(pk_.getAbilities(), name)) {
                continue;
            }
            pokemon.add(p);
        }
        pokemon.sortElts(new ComparatorTrStrings(data_.getTranslatedPokemon().getVal(getLanguage())));
        AbilityData ability_ = data_.getAbility(name);
        if (!ability_.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = ability_.getEffectEndRound().first();
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        sending = !ability_.getEffectSending().isEmpty();
        StringMap<String> translatedCategories_;
        translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        StringMap<String> translatedAbilities_;
        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
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
        breakProtectionMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
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
        StringList immuRechargeRoundMoves_;
        immuRechargeRoundMoves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getRechargeRound()) {
                continue;
            }
            immuRechargeRoundMoves_.add(m);
        }
        immuRechargeRoundMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuRechargeRoundMoves = immuRechargeRoundMoves_;
        immuSufferedDamageLowEff = ability_.isImmuSufferedDamageLowEff();
        inflictingDamageInsteadOfSuffering = ability_.isInflictingDamageInsteadOfSuffering();
        mumy = ability_.isMumy();
        nbHits = ability_.isNbHits();
        plate = ability_.isPlate();
        reverseEffectsPowerMovesTypesGlobal = ability_.isReverseEffectsPowerMovesTypesGlobal();
        StringList reverseEffectsPowerMovesTypesGlobalAbilities_;
        reverseEffectsPowerMovesTypesGlobalAbilities_ = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultPowerMovesTypesGlobal().isEmpty()) {
                continue;
            }
            reverseEffectsPowerMovesTypesGlobalAbilities_.add(a);
        }
        reverseEffectsPowerMovesTypesGlobalAbilities_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        reverseEffectsPowerMovesTypesGlobalAbilities = reverseEffectsPowerMovesTypesGlobalAbilities_;
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
        recoilDamageFoeByKoOwner = Rate.zero();
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
        StringList immuMoves_;
        immuMoves_ = new StringList();
        for (String m: ability_.getImmuMove()) {
            immuMoves_.add(m);
        }
        immuMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuMove = immuMoves_;
        StringList immuAllyFromMoves_;
        immuAllyFromMoves_ = new StringList();
        for (String m: ability_.getImmuAllyFromMoves()) {
            immuAllyFromMoves_.add(m);
        }
        immuAllyFromMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuAllyFromMoves = immuAllyFromMoves_;
        StringList immuWeather_;
        immuWeather_ = new StringList();
        for (String m: ability_.getImmuWeather()) {
            immuWeather_.add(m);
        }
        immuWeather_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuWeather = immuWeather_;
        StringList ignAbility_;
        ignAbility_ = new StringList();
        for (String m: ability_.getIgnAbility()) {
            ignAbility_.add(m);
        }
        ignAbility_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        ignAbility = ignAbility_;
        StringList immuAbility_;
        immuAbility_ = new StringList();
        for (String m: ability_.getImmuAbility()) {
            immuAbility_.add(m);
        }
        immuAbility_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        immuAbility = immuAbility_;
        StringList ignFoeTeamMove_;
        ignFoeTeamMove_ = new StringList();
        for (String m: ability_.getIgnFoeTeamMove()) {
            ignFoeTeamMove_.add(m);
        }
        ignFoeTeamMove_.sortElts(new ComparatorTrStrings(translatedMoves_));
        ignFoeTeamMove = ignFoeTeamMove_;
        StringList immuStatusBeginRound_;
        immuStatusBeginRound_ = new StringList();
        for (String m: ability_.getImmuStatusBeginRound()) {
            immuStatusBeginRound_.add(m);
        }
        immuStatusBeginRound_.sortElts(new ComparatorTrStrings(translatedStatus_));
        immuStatusBeginRound = immuStatusBeginRound_;
        StringMap<String> translatedTypes_;
        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        if (!ability_.getTypeForMoves().isEmpty()) {
            typeForMoves = translatedTypes_.getVal(ability_.getTypeForMoves());
        } else {
            typeForMoves = DataBase.EMPTY_STRING;
        }
        TreeMap<String, Rate> singleStatus_;
        singleStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: ability_.getSingleStatus().events()) {
            singleStatus_.put(s, ability_.getSingleStatus().normalizedRate(s));
        }
        singleStatus = singleStatus_;
        EnumMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        EnumList<Statistic> immuLowStat_;
        immuLowStat_ = new EnumList<Statistic>();
        for (Statistic s: ability_.getImmuLowStat()) {
            immuLowStat_.add(s);
        }
        immuLowStat_.sortElts(new ComparatorTrStringStatistic(translatedStatistics_));
        immuLowStat = immuLowStat_;
        EnumList<Statistic> maxStatisticsIfCh_;
        maxStatisticsIfCh_ = new EnumList<Statistic>();
        for (Statistic s: ability_.getMaxStatisticsIfCh()) {
            maxStatisticsIfCh_.add(s);
        }
        maxStatisticsIfCh_.sortElts(new ComparatorTrStringStatistic(translatedStatistics_));
        maxStatisticsIfCh = maxStatisticsIfCh_;
        TreeMap<String, StringList> immuMoveTypesByWeather_;
        immuMoveTypesByWeather_ = new TreeMap<String, StringList>(new ComparatorTrStrings(translatedMoves_));
        for (String t: ability_.getImmuMoveTypesByWeather().getKeys()) {
            immuMoveTypesByWeather_.put(t, new StringList(ability_.getImmuMoveTypesByWeather().getVal(t)));
        }
        for (StringList v: immuMoveTypesByWeather_.values()) {
            v.sortElts(new ComparatorTrStrings(translatedTypes_));
        }
        immuMoveTypesByWeather = immuMoveTypesByWeather_;
        TreeMap<String, StringList> immuStatus_;
        immuStatus_ = new TreeMap<String, StringList>(new ComparatorTrStrings(translatedMoves_));
        for (String t: ability_.getImmuStatus().getKeys()) {
            immuStatus_.put(t, new StringList(ability_.getImmuStatus().getVal(t)));
        }
        for (StringList v: immuStatus_.values()) {
            v.sortElts(new ComparatorTrStrings(translatedStatus_));
        }
        immuStatus = immuStatus_;
        TreeMap<String, StringList> immuStatusTypes_;
        immuStatusTypes_ = new TreeMap<String, StringList>(new ComparatorTrStrings(translatedTypes_));
        for (String t: ability_.getImmuStatusTypes().getKeys()) {
            immuStatusTypes_.put(t, new StringList(ability_.getImmuStatusTypes().getVal(t)));
        }
        for (StringList v: immuStatusTypes_.values()) {
            v.sortElts(new ComparatorTrStrings(translatedStatus_));
        }
        immuStatusTypes = immuStatusTypes_;
        CustList<StatisticStatus> immuLowStatIfStatus_;
        immuLowStatIfStatus_ = new CustList<StatisticStatus>();
        for (StatisticStatus s: ability_.getImmuLowStatIfStatus()) {
            immuLowStatIfStatus_.add(new StatisticStatus(s.getStatistic(),s.getStatus()));
        }
        immuLowStatIfStatus_.sortElts(new ComparatorStatusStatistic(data_, getLanguage()));
        immuLowStatIfStatus = immuLowStatIfStatus_;
        TreeMap<String,EnumList<Statistic>> immuLowStatisTypes_;
        immuLowStatisTypes_ = new TreeMap<String,EnumList<Statistic>>(new ComparatorTrStrings(translatedTypes_));
        for (String t: ability_.getImmuLowStatisTypes().getKeys()) {
            immuLowStatisTypes_.put(t, new EnumList<Statistic>(ability_.getImmuLowStatisTypes().getVal(t)));
        }
        for (EnumList<Statistic> v: immuLowStatisTypes_.values()) {
            v.sortElts(new ComparatorTrStringStatistic(translatedStatistics_));
        }
        immuLowStatisTypes = immuLowStatisTypes_;
        TreeMap<Statistic,String> multStat_;
        multStat_ = new TreeMap<Statistic, String>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(ability_.getMultStat().getVal(s), getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(ability_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(s, formula_);
        }
        multStat = multStat_;
        TreeMap<String, String> forwardStatus_;
        forwardStatus_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedStatus_));
        for (String s: ability_.getForwardStatus().getKeys()) {
            forwardStatus_.put(s, ability_.getForwardStatus().getVal(s));
        }
        forwardStatus = forwardStatus_;
        TreeMap<String, String> failStatus_;
        failStatus_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedStatus_));
        for (String s: ability_.getFailStatus().getKeys()) {
            String fail_ = ability_.getFailStatus().getVal(s);
            String formula_ = data_.getFormula(fail_, getLanguage());
//            formula_ = quoteBraces(formula_);
            mapVars_.putAllMap(data_.getDescriptions(fail_, getLanguage()));
            failStatus_.put(s, formula_);
        }
        failStatus = failStatus_;
        CustList<TypesDuo> breakFoeImmune_;
        breakFoeImmune_ = new CustList<TypesDuo>();
        for (TypesDuo s: ability_.getBreakFoeImmune()) {
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
        breakFoeImmune_.sortElts(new ComparatorTypesDuo(data_, getLanguage(), false));
        breakFoeImmune = breakFoeImmune_;
        TreeMap<Statistic, Byte> bonusStatRank_;
        bonusStatRank_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getBonusStatRank().getKeys()) {
            bonusStatRank_.put(s, ability_.getBonusStatRank().getVal(s));
        }
        bonusStatRank = bonusStatRank_;
        TreeMap<Statistic, Byte> boostStatRankEndRound_;
        boostStatRankEndRound_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getBoostStatRankEndRound().getKeys()) {
            boostStatRankEndRound_.put(s, ability_.getBoostStatRankEndRound().getVal(s));
        }
        boostStatRankEndRound = boostStatRankEndRound_;
        TreeMap<Statistic, Byte> boostStatRankProtected_;
        boostStatRankProtected_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getBoostStatRankProtected().getKeys()) {
            boostStatRankProtected_.put(s, ability_.getBoostStatRankProtected().getVal(s));
        }
        boostStatRankProtected = boostStatRankProtected_;
        TreeMap<Statistic, Byte> lowStatFoeHit_;
        lowStatFoeHit_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getLowStatFoeHit().getKeys()) {
            lowStatFoeHit_.put(s, ability_.getLowStatFoeHit().getVal(s));
        }
        lowStatFoeHit = lowStatFoeHit_;
        TreeMap<Statistic, Byte> multStatIfKoFoe_;
        multStatIfKoFoe_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getMultStatIfKoFoe().getKeys()) {
            multStatIfKoFoe_.put(s, ability_.getMultStatIfKoFoe().getVal(s));
        }
        multStatIfKoFoe = multStatIfKoFoe_;
        TreeMap<Statistic, Byte> multStatIfLowStat_;
        multStatIfLowStat_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getMultStatIfLowStat().getKeys()) {
            multStatIfLowStat_.put(s, ability_.getMultStatIfLowStat().getVal(s));
        }
        multStatIfLowStat = multStatIfLowStat_;
        TreeMap<Statistic, Rate> multStatAlly_;
        multStatAlly_ = new TreeMap<Statistic, Rate>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: ability_.getMultStatAlly().getKeys()) {
            multStatAlly_.put(s, ability_.getMultStatAlly().getVal(s));
        }
        multStatAlly = multStatAlly_;
        TreeMap<String, Short> increasedPrio_;
        increasedPrio_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedCategories_));
        for (String c: ability_.getIncreasedPrio().getKeys()) {
            increasedPrio_.put(c, ability_.getIncreasedPrio().getVal(c));
        }
        increasedPrio = increasedPrio_;
        TreeMap<String, Short> increasedPrioTypes_;
        increasedPrioTypes_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedTypes_));
        for (String c: ability_.getIncreasedPrioTypes().getKeys()) {
            increasedPrioTypes_.put(c, ability_.getIncreasedPrioTypes().getVal(c));
        }
        increasedPrioTypes = increasedPrioTypes_;
        TreeMap<String, String> chgtTypeByWeather_;
        chgtTypeByWeather_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedMoves_));
        for (String c: ability_.getChgtTypeByWeather().getKeys()) {
            chgtTypeByWeather_.put(c, ability_.getChgtTypeByWeather().getVal(c));
        }
        chgtTypeByWeather = chgtTypeByWeather_;
        TreeMap<String, Rate> divideStatusRound_;
        divideStatusRound_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String c: ability_.getDivideStatusRound().getKeys()) {
            divideStatusRound_.put(c, ability_.getDivideStatusRound().getVal(c));
        }
        divideStatusRound = divideStatusRound_;
        TreeMap<String, Rate> healHpByWeather_;
        healHpByWeather_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedMoves_));
        for (String c: ability_.getHealHpByWeather().getKeys()) {
            healHpByWeather_.put(c, ability_.getHealHpByWeather().getVal(c));
        }
        healHpByWeather = healHpByWeather_;
        TreeMap<WeatherType, Rate> healHpByTypeIfWeather_;
        healHpByTypeIfWeather_ = new TreeMap<WeatherType, Rate>(new ComparatorWeatherType(data_, getLanguage()));
        for (WeatherType w: ability_.getHealHpByTypeIfWeather().getKeys()) {
            healHpByTypeIfWeather_.put(w, ability_.getHealHpByTypeIfWeather().getVal(w));
        }
        healHpByTypeIfWeather = healHpByTypeIfWeather_;
        TreeMap<String, TypeDamageBoost> changingBoostTypes_;
        changingBoostTypes_ = new TreeMap<String, TypeDamageBoost>(new ComparatorTrStrings(translatedTypes_));
        for (String w: ability_.getChangingBoostTypes().getKeys()) {
            changingBoostTypes_.put(w, ability_.getChangingBoostTypes().getVal(w));
        }
        changingBoostTypes = changingBoostTypes_;
        TreeMap<StatisticCategory, Byte> multStatIfDamageCat_;
        multStatIfDamageCat_ = new TreeMap<StatisticCategory, Byte>(new ComparatorStatisticCategory(data_, getLanguage()));
        for (StatisticCategory w: ability_.getMultStatIfDamageCat().getKeys()) {
            multStatIfDamageCat_.put(w, ability_.getMultStatIfDamageCat().getVal(w));
        }
        multStatIfDamageCat = multStatIfDamageCat_;
        TreeMap<StatisticCategory, Rate> multStatIfCat_;
        multStatIfCat_ = new TreeMap<StatisticCategory, Rate>(new ComparatorStatisticCategory(data_, getLanguage()));
        for (StatisticCategory w: ability_.getMultStatIfCat().getKeys()) {
            multStatIfCat_.put(w, ability_.getMultStatIfCat().getVal(w));
        }
        multStatIfCat = multStatIfCat_;
        TreeMap<StatisticType, Byte> multStatIfDamgeType_;
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
        multStatIfDamgeType_ = new TreeMap<StatisticType, Byte>(new ComparatorStatisticType(data_, getLanguage()));
        for (StatisticType w: ability_.getMultStatIfDamgeType().getKeys()) {
            multStatIfDamgeType_.put(w, ability_.getMultStatIfDamgeType().getVal(w));
        }
        multStatIfDamgeType = multStatIfDamgeType_;
        TreeMap<StatisticStatus, Byte> multStatIfStatutRank_;
        multStatIfStatutRank_ = new TreeMap<StatisticStatus, Byte>(new ComparatorStatusStatistic(data_, getLanguage()));
        for (StatisticStatus w: ability_.getMultStatIfStatutRank().getKeys()) {
            multStatIfStatutRank_.put(w, ability_.getMultStatIfStatutRank().getVal(w));
        }
        multStatIfStatutRank = multStatIfStatutRank_;
        TreeMap<String, Rate> multDamageFoe_;
        multDamageFoe_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String c: ability_.getMultDamageFoe().getKeys()) {
            multDamageFoe_.put(c, ability_.getMultDamageFoe().getVal(c));
        }
        multDamageFoe = multDamageFoe_;
        TreeMap<String, Rate> multPowerMovesTypesGlobal_;
        multPowerMovesTypesGlobal_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String c: ability_.getMultPowerMovesTypesGlobal().getKeys()) {
            multPowerMovesTypesGlobal_.put(c, ability_.getMultPowerMovesTypesGlobal().getVal(c));
        }
        multPowerMovesTypesGlobal = multPowerMovesTypesGlobal_;
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(ability_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
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
        if (!getForms().contains(CST_ABILITIES_SET)) {
            getForms().put(CST_ABILITIES_SET, new StringList());
        }
        return CST_ABILITIES;
    }
    public String clickImmuMove(int _index) {
        String type_ = immuMove.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrImmuAllyFromMoves(int _index) {
        String type_ = immuAllyFromMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickImmuAllyFromMoves(int _index) {
        String type_ = immuAllyFromMoves.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrWeather(int _index) {
        String type_ = immuWeather.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickWeather(int _index) {
        String type_ = immuWeather.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrIgnAbility(int _index) {
        String type_ = ignAbility.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickIgnAbility(int _index) {
        String type_ = ignAbility.get(_index);
        getForms().put(CST_ABILITY, type_);
        return CST_ABILITY;
    }
    public String getTrIgnFoeTeamMove(int _index) {
        String type_ = ignFoeTeamMove.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickIgnFoeTeamMove(int _index) {
        String type_ = ignFoeTeamMove.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrImmuAbility(int _index) {
        String type_ = immuAbility.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickImmuAbility(int _index) {
        String type_ = immuAbility.get(_index);
        getForms().put(CST_ABILITY, type_);
        return CST_ABILITY;
    }
    public String getTrImmuStatusBeginRound(int _index) {
        String type_ = immuStatusBeginRound.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatusBeginRound(int _index) {
        String type_ = immuStatusBeginRound.get(_index);
        getForms().put(CST_STATUS, type_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, type_);
        return CST_STATUS;
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
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
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
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
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
        getForms().put(CST_ABILITY, move_);
        return CST_ABILITY;
    }
    public String getTrMultStat(int _index) {
        Statistic type_ = multStat.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrImmuLowStat(int _index) {
        Statistic type_ = immuLowStat.get(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMaxStatisticsIfCh(int _index) {
        Statistic type_ = maxStatisticsIfCh.get(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
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
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
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
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
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
        getForms().put(CST_STATUS, move_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, move_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }
    public String getTrImmuLowStatIfStatusValue(int _index) {
        Statistic status_ = immuLowStatIfStatus.get(_index).getStatistic();
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
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
        getForms().put(CST_MOVE, status_);
        return CST_MOVE;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
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
        getForms().put(CST_MOVE, status_);
        return CST_MOVE;
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
        getForms().put(CST_MOVE, status_);
        return CST_MOVE;
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
        EnumMap<Statistic,String> translationsStatistics_;
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
        EnumMap<Statistic,String> translationsStatistics_;
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
        EnumMap<Statistic,String> translationsStatistics_;
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
        EnumMap<Statistic,String> translationsStatistics_;
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
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }
    public String getTrMultPowerMovesTypesGlobalKey(int _index) {
        String status_ = multPowerMovesTypesGlobal.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translationsMoves_.getVal(status_);
    }
    public String clickMultPowerMovesTypesGlobalKey(int _index) {
        String status_ = multPowerMovesTypesGlobal.getKey(_index);
        getForms().put(CST_MOVE, status_);
        return CST_MOVE;
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
        EnumMap<Statistic,String> translationsStatistics_;
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
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankEndRound(int _index) {
        Statistic move_ = boostStatRankEndRound.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrBoostStatRankProtected(int _index) {
        Statistic move_ = boostStatRankProtected.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrLowStatFoeHit(int _index) {
        Statistic move_ = lowStatFoeHit.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfKoFoe(int _index) {
        Statistic move_ = multStatIfKoFoe.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatIfLowStat(int _index) {
        Statistic move_ = multStatIfLowStat.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translationsStatistics_.getVal(move_);
    }
    public String getTrMultStatAlly(int _index) {
        Statistic move_ = multStatAlly.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic,String> translationsStatistics_;
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
        getForms().put(CST_PK, pk_);
        return CST_POKEMON;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public int getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
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

    public TreeMap<String,String> getChgtTypeByWeather() {
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

    public Rate getRecoilDamageFoeByKoOwner() {
        return recoilDamageFoeByKoOwner;
    }

    public int getDecreaseNecStepsHatch() {
        return decreaseNecStepsHatch;
    }

    public int getNbUsedPp() {
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

    public TreeMap<String,TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public String getMultPower() {
        return multPower;
    }

    public String getMultDamage() {
        return multDamage;
    }

    public TreeMap<String,Rate> getHealHpByWeather() {
        return healHpByWeather;
    }

    public TreeMap<WeatherType,Rate> getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public EnumList<Statistic> getImmuLowStat() {
        return immuLowStat;
    }

    public CustList<StatisticStatus> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public TreeMap<String,EnumList<Statistic>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
    }

    public EnumList<Statistic> getMaxStatisticsIfCh() {
        return maxStatisticsIfCh;
    }

    public TreeMap<String,Rate> getSingleStatus() {
        return singleStatus;
    }

    public TreeMap<String,StringList> getImmuMoveTypesByWeather() {
        return immuMoveTypesByWeather;
    }

    public TreeMap<String,StringList> getImmuStatus() {
        return immuStatus;
    }

    public TreeMap<String,StringList> getImmuStatusTypes() {
        return immuStatusTypes;
    }

    public TreeMap<String,Rate> getDivideStatusRound() {
        return divideStatusRound;
    }

    public TreeMap<String,String> getForwardStatus() {
        return forwardStatus;
    }

    public CustList<TypesDuo> getBreakFoeImmune() {
        return breakFoeImmune;
    }

    public Rate getDefEff() {
        return defEff;
    }

    public TreeMap<Statistic,String> getMultStat() {
        return multStat;
    }

    public TreeMap<StatisticCategory,Byte> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public TreeMap<StatisticType,Byte> getMultStatIfDamgeType() {
        return multStatIfDamgeType;
    }

    public TreeMap<StatisticCategory,Rate> getMultStatIfCat() {
        return multStatIfCat;
    }

    public TreeMap<StatisticStatus,Byte> getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public TreeMap<Statistic,Byte> getBonusStatRank() {
        return bonusStatRank;
    }

    public TreeMap<Statistic,Byte> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public TreeMap<Statistic,Byte> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public TreeMap<Statistic,Byte> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public TreeMap<Statistic,Byte> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public TreeMap<Statistic,Byte> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public TreeMap<Statistic,Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public TreeMap<String,Short> getIncreasedPrio() {
        return increasedPrio;
    }

    public TreeMap<String,Short> getIncreasedPrioTypes() {
        return increasedPrioTypes;
    }

    public TreeMap<String,Rate> getMultDamageFoe() {
        return multDamageFoe;
    }

    public TreeMap<String,Rate> getMultPowerMovesTypesGlobal() {
        return multPowerMovesTypesGlobal;
    }

    public TreeMap<String,String> getFailStatus() {
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