package aiki.fight.abilities;

import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.util.*;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.*;
import code.util.core.StringUtil;


public final class AbilityData {

    private CustList<TypesDuo> breakFoeImmune;

    private boolean forbidUseBerryAgainstFoes;
    private StringMap<String> chgtTypeByWeather;
    private boolean chgtTypeByDamage;
    private Rate recoilDamageFoe;
    private int decreaseNecStepsHatch;
    private StringMap<Rate> divideStatusRound;
    private StringMap<Rate> healHpByWeather;
    private StringList ignAbility;
    private StringList ignFoeTeamMove;
    private boolean ignFoeStatisBoost;
    private StringList immuMove;
    private IdList<Statistic> immuLowStat;

    private CustList<StatisticStatus> immuLowStatIfStatus;

    private boolean immuCh;
    private StringList immuWeather;
    private boolean immuDamageTrappingMoves;
    private boolean immuDamageAllyMoves;
    private boolean immuDamageRecoil;
    private StringList immuAbility;
    private StringList immuStatusBeginRound;
    private boolean immuRechargeRound;
    private StringMap<StringList> immuStatus;
    private boolean slowing;
    private StringMap<Rate> multDamageFoe;
    private Rate multDamageCh;
    private Rate multAllyDamage;
    private Rate multSufferedDamageSuperEff;
    private boolean immuSufferedDamageLowEff;
    private Rate multEvtRateCh;
    private boolean cancelSecEffectOther;
    private boolean cancelSecEffectOwner;
    private Rate multEvtRateSecEffectOwner;
    private String multPower;
    private String multDamage;
    private Rate multStab;
    private IdMap<Statistic, Byte> bonusStatRank;
    private IdMap<Statistic, Byte> boostStatRankProtected;
    private IdMap<Statistic, Byte> boostStatRankEndRound;
    private IdMap<Statistic, Rate> multStatAlly;
    private IdMap<Statistic, Byte> multStatIfKoFoe;
    private IdMap<Statistic, Byte> multStatIfLowStat;
    private StatisticCategoryList<Rate> multStatIfCat;

    private StatisticStatusList multStatIfStatutRank;

    private StatisticCategoryList<Byte> multStatIfDamageCat;

    private StatisticTypeList<Byte> multStatIfDamgeType;

    private IdMap<Statistic, String> multStat;
    private boolean inflictingDamageInsteadOfSuffering;
    private Rate multVarBoost;
    private int nbUsedPp;
    private boolean nbHits;
    private boolean breakProtection;
    private boolean plate;
    private boolean healedStatusBySwitch;
    private Rate healedHpRateBySwitch;
    private StringMap<Short> increasedPrio;
    private StringMap<Short> increasedPrioTypes;
    private IdList<Statistic> maxStatisticsIfCh;
    private MonteCarloString singleStatus;
    private boolean achievedDisappearedPk;
    private StringMap<String> forwardStatus;
    private StringMap<String> failStatus;
    private String typeForMoves;
    private Rate maxHpForUsingBerry;
    private boolean mumy;

    private WeatherTypes healHpByTypeIfWeather;

    private StringMap<StringList> immuMoveTypesByWeather;

    private CustList<EffectEndRound> effectEndRound;
    private CustList<EffectWhileSendingWithStatistic> effectSending;

    private StringMap<TypeDamageBoost> changingBoostTypes;

    private StringList immuAllyFromMoves;

    private StringMap<StringList> immuStatusTypes;

    private StringMap<IdList<Statistic>> immuLowStatisTypes;

    private IdMap<Statistic, Byte> lowStatFoeHit;

    private boolean copyMovesTypes;

    private StringMap<Rate> multPowerMovesTypesGlobal;

    private boolean reverseEffectsPowerMovesTypesGlobal;

    private Rate healHpWhileUsingBerry;

    private boolean takeItemByDamagingMove;

    private boolean giveItemToAllyHavingUsed;

    public void validate(DataBase _data) {
        DataInfoChecker.checkPositiveOrZero(maxHpForUsingBerry,_data);
        DataInfoChecker.checkLowerOneEq(maxHpForUsingBerry,_data);
        DataInfoChecker.checkEvents(singleStatus,_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getStatus().getKeys(),singleStatus.events(),_data);
        StringList events_ = new StringList(singleStatus.events());
        StringUtil.removeObj(events_, DataBase.EMPTY_STRING);
        if (!events_.isEmpty()) {
            DataInfoChecker.checkStringListContains(events_,failStatus.getKeys(),_data);
        }
        DataInfoChecker.checkPositiveRates(healHpByTypeIfWeather.values(),_data);
        DataInfoChecker.checkWeatherTypes(healHpByTypeIfWeather.getKeys(), _data);
        DataInfoChecker.checkPositiveOrZeroRates(multStatIfCat.values(),_data);
        DataInfoChecker.checkStatisticCategory(_data.getAllCategories(), multStatIfCat.getKeys(), _data);
        DataInfoChecker.checkStatisticStatus(immuLowStatIfStatus, _data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),TypesDuos.getTypesFrom(breakFoeImmune),_data);
        DataInfoChecker.checkStringListContains(_data.getMovesEffectGlobalWeather(),immuWeather,_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobalWeather(),chgtTypeByWeather.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),chgtTypeByWeather.values(),_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobal(),immuStatus.getKeys(),_data);
        DataInfoChecker.checkStringListContainsAll(_data.getStatus().getKeys(), immuStatus.values(), _data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobal(),immuMoveTypesByWeather.getKeys(),_data);
        DataInfoChecker.checkStringListContainsAll(_data.getTypes(), immuMoveTypesByWeather.values(), _data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobal(),healHpByWeather.getKeys(),_data);
        for (Rate v : healHpByWeather.values()) {
            DataInfoChecker.checkNonZero(v,_data);
        }
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),immuStatusBeginRound,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),divideStatusRound.getKeys(),_data);
        DataInfoChecker.checkPositiveRates(divideStatusRound.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getAbilities().getKeys(),immuAbility,_data);
        DataInfoChecker.checkStringListContains(_data.getAbilities().getKeys(),ignAbility,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),ignFoeTeamMove,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),immuMove,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multDamageFoe.getKeys(),_data);
        DataInfoChecker.checkPositiveRates(multDamageFoe.values(),_data);
        DataInfoChecker.checkPositiveOrZero(multDamageCh,_data);
        DataInfoChecker.checkPositiveOrZero(multAllyDamage,_data);
        DataInfoChecker.checkPositiveOrZero(multSufferedDamageSuperEff,_data);
        DataInfoChecker.checkPositiveOrZero(multEvtRateCh,_data);
        DataInfoChecker.checkPositiveOrZero(multEvtRateSecEffectOwner,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),immuLowStat,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),forwardStatus.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),forwardStatus.values(),_data);
        if (!forwardStatus.isEmpty()) {
            DataInfoChecker.checkStringListContains(forwardStatus.values(),failStatus.getKeys(),_data);
//            for (String k : failStatus.getKeys()) {
//                boolean appear_ = false;
//                for (String v : forwardStatus.values()) {
//                    if (StringUtil.quickEq(k, v)) {
//                        appear_ = true;
//                        break;
//                    }
//                }
//                if (!appear_) {
//                    _data.setError(true);
//                }
//            }
        }
        DataInfoChecker.checkStringListContains(_data.getAllCategories(),increasedPrio.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),increasedPrioTypes.getKeys(),_data);
        DataInfoChecker.checkPositiveShorts(increasedPrio.values(),_data);
        DataInfoChecker.checkPositiveShorts(increasedPrioTypes.values(),_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getTypes(), typeForMoves,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStat.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatAlly.getKeys(),_data);
        DataInfoChecker.checkPositiveRates(multStatAlly.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),maxStatisticsIfCh,_data);
        DataInfoChecker.checkPositiveOrZero(multStab,_data);
        DataInfoChecker.checkPositiveOrZero(healedHpRateBySwitch,_data);
        DataInfoChecker.checkPositiveOrZero(nbUsedPp,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),bonusStatRank.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),boostStatRankProtected.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),boostStatRankEndRound.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatIfKoFoe.getKeys(),_data);
        DataInfoChecker.checkPositiveBytes(boostStatRankProtected.values(),_data);
        DataInfoChecker.checkPositiveBytes(multStatIfKoFoe.values(),_data);
        DataInfoChecker.checkPositiveBytes(boostStatRankEndRound.values(),_data);
        DataInfoChecker.checkPositiveBytes(multStatIfLowStat.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatIfLowStat.getKeys(),_data);
        DataInfoChecker.checkPositiveBytes(multStatIfStatutRank.values(),_data);
        DataInfoChecker.checkStatisticStatus(multStatIfStatutRank.getKeys(), _data);
        DataInfoChecker.checkPositiveBytes(multStatIfDamageCat.values(),_data);
        DataInfoChecker.checkStatisticCategory(_data.getCategories(), multStatIfDamageCat.getKeys(), _data);
        DataInfoChecker.checkPositiveBytes(multStatIfDamgeType.values(),_data);
        DataInfoChecker.checkStatisticType(multStatIfDamgeType.getKeys(), _data);
        DataInfoChecker.checkPositiveOrZero(recoilDamageFoe,_data);
        DataInfoChecker.checkPositiveOrZero(decreaseNecStepsHatch,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),changingBoostTypes.getKeys(),_data);
        for (TypeDamageBoost k : changingBoostTypes.values()) {
            DataInfoChecker.checkStringListContains(_data.getTypes(),k.getType(),_data);
            if (!k.getBoost().greaterOrEqualsOne()) {
                _data.setError(true);
            }
        }
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),immuAllyFromMoves,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),immuStatusTypes.getKeys(),_data);
        DataInfoChecker.checkStringListContainsAll(_data.getStatus().getKeys(), immuStatusTypes.values(), _data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),immuLowStatisTypes.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),lowStatFoeHit.getKeys(),_data);
        DataInfoChecker.checkNegativeBytes(lowStatFoeHit.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multPowerMovesTypesGlobal.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multPowerMovesTypesGlobal.values(),_data);
        DataInfoChecker.checkPositiveOrZero(healHpWhileUsingBerry,_data);
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual) && !(effectEndRound.first() instanceof EffectEndRoundTeam) && !(effectEndRound.first() instanceof EffectEndRoundMultiRelation)) {
                _data.setError(true);
            }
        }
        if (!effectSending.isEmpty()) {
            effectSending.first().validate(_data);
        }
    }

    public boolean containsStatisticStatus(StatisticStatus _s) {
        for (StatisticStatus e:immuLowStatIfStatus) {
            if (_s.eq(e)) {
                return true;
            }
        }
        return false;
    }
    public boolean enabledSending() {
        return !effectSending.isEmpty();
    }

    public CustList<TypesDuo> getBreakFoeImmune() {
        return breakFoeImmune;
    }

    public void setBreakFoeImmune(CustList<TypesDuo> _breakFoeImmune) {
        breakFoeImmune = _breakFoeImmune;
    }

    public boolean isForbidUseBerryAgainstFoes() {
        return forbidUseBerryAgainstFoes;
    }

    public void setForbidUseBerryAgainstFoes(boolean _forbidUseBerryAgainstFoes) {
        forbidUseBerryAgainstFoes = _forbidUseBerryAgainstFoes;
    }

    public StringMap<String> getChgtTypeByWeather() {
        return chgtTypeByWeather;
    }

    public void setChgtTypeByWeather(StringMap<String> _chgtTypeByWeather) {
        chgtTypeByWeather = _chgtTypeByWeather;
    }

    public boolean isChgtTypeByDamage() {
        return chgtTypeByDamage;
    }

    public void setChgtTypeByDamage(boolean _chgtTypeByDamage) {
        chgtTypeByDamage = _chgtTypeByDamage;
    }

    public Rate getRecoilDamageFoe() {
        return recoilDamageFoe;
    }

    public void setRecoilDamageFoe(Rate _recoilDamageFoe) {
        recoilDamageFoe = _recoilDamageFoe;
    }

    public int getDecreaseNecStepsHatch() {
        return decreaseNecStepsHatch;
    }

    public void setDecreaseNecStepsHatch(int _decreaseNecStepsHatch) {
        decreaseNecStepsHatch = _decreaseNecStepsHatch;
    }

    public StringMap<Rate> getDivideStatusRound() {
        return divideStatusRound;
    }

    public void setDivideStatusRound(StringMap<Rate> _divideStatusRound) {
        divideStatusRound = _divideStatusRound;
    }

    public StringMap<Rate> getHealHpByWeather() {
        return healHpByWeather;
    }

    public void setHealHpByWeather(StringMap<Rate> _healHpByWeather) {
        healHpByWeather = _healHpByWeather;
    }

    public StringList getIgnAbility() {
        return ignAbility;
    }

    public void setIgnAbility(StringList _ignAbility) {
        ignAbility = _ignAbility;
    }

    public StringList getIgnFoeTeamMove() {
        return ignFoeTeamMove;
    }

    public void setIgnFoeTeamMove(StringList _ignFoeTeamMove) {
        ignFoeTeamMove = _ignFoeTeamMove;
    }

    public boolean isIgnFoeStatisBoost() {
        return ignFoeStatisBoost;
    }

    public void setIgnFoeStatisBoost(boolean _ignFoeStatisBoost) {
        ignFoeStatisBoost = _ignFoeStatisBoost;
    }

    public StringList getImmuMove() {
        return immuMove;
    }

    public void setImmuMove(StringList _immuMove) {
        immuMove = _immuMove;
    }

    public IdList<Statistic> getImmuLowStat() {
        return immuLowStat;
    }

    public void setImmuLowStat(IdList<Statistic> _immuLowStat) {
        immuLowStat = _immuLowStat;
    }

    public CustList<StatisticStatus> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public void setImmuLowStatIfStatus(
            CustList<StatisticStatus> _immuLowStatIfStatus) {
        immuLowStatIfStatus = _immuLowStatIfStatus;
    }

    public boolean isImmuCh() {
        return immuCh;
    }

    public void setImmuCh(boolean _immuCh) {
        immuCh = _immuCh;
    }

    public StringList getImmuWeather() {
        return immuWeather;
    }

    public void setImmuWeather(StringList _immuWeather) {
        immuWeather = _immuWeather;
    }

    public boolean isImmuDamageTrappingMoves() {
        return immuDamageTrappingMoves;
    }

    public void setImmuDamageTrappingMoves(boolean _immuDamageTrappingMoves) {
        immuDamageTrappingMoves = _immuDamageTrappingMoves;
    }

    public boolean isImmuDamageAllyMoves() {
        return immuDamageAllyMoves;
    }

    public void setImmuDamageAllyMoves(boolean _immuDamageAllyMoves) {
        immuDamageAllyMoves = _immuDamageAllyMoves;
    }

    public boolean isImmuDamageRecoil() {
        return immuDamageRecoil;
    }

    public void setImmuDamageRecoil(boolean _immuDamageRecoil) {
        immuDamageRecoil = _immuDamageRecoil;
    }

    public StringList getImmuAbility() {
        return immuAbility;
    }

    public void setImmuAbility(StringList _immuAbility) {
        immuAbility = _immuAbility;
    }

    public StringList getImmuStatusBeginRound() {
        return immuStatusBeginRound;
    }

    public void setImmuStatusBeginRound(StringList _immuStatus) {
        immuStatusBeginRound = _immuStatus;
    }

    public boolean isImmuRechargeRound() {
        return immuRechargeRound;
    }

    public void setImmuRechargeRound(boolean _immuRechargeRound) {
        immuRechargeRound = _immuRechargeRound;
    }

    public StringMap<StringList> getImmuStatus() {
        return immuStatus;
    }

    public void setImmuStatus(StringMap<StringList> _immuStatut) {
        immuStatus = _immuStatut;
    }

    public boolean isSlowing() {
        return slowing;
    }

    public void setSlowing(boolean _slowing) {
        slowing = _slowing;
    }

    public StringMap<Rate> getMultDamageFoe() {
        return multDamageFoe;
    }

    public void setMultDamageFoe(StringMap<Rate> _multDamage) {
        multDamageFoe = _multDamage;
    }

    public Rate getMultDamageCh() {
        return multDamageCh;
    }

    public void setMultDamageCh(Rate _multDamageCh) {
        multDamageCh = _multDamageCh;
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }

    public void setMultAllyDamage(Rate _multAllyDamage) {
        multAllyDamage = _multAllyDamage;
    }

    public Rate getMultSufferedDamageSuperEff() {
        return multSufferedDamageSuperEff;
    }

    public void setMultSufferedDamageSuperEff(Rate _multSufferedDamageSuperEff) {
        multSufferedDamageSuperEff = _multSufferedDamageSuperEff;
    }

    public boolean isImmuSufferedDamageLowEff() {
        return immuSufferedDamageLowEff;
    }

    public void setImmuSufferedDamageLowEff(boolean _immuSufferedDamageLowEff) {
        immuSufferedDamageLowEff = _immuSufferedDamageLowEff;
    }

    public Rate getMultEvtRateCh() {
        return multEvtRateCh;
    }

    public void setMultEvtRateCh(Rate _multEvtRateCh) {
        multEvtRateCh = _multEvtRateCh;
    }

    public boolean isCancelSecEffectOther() {
        return cancelSecEffectOther;
    }

    public void setCancelSecEffectOther(boolean _cancelSecEffectOther) {
        cancelSecEffectOther = _cancelSecEffectOther;
    }

    public boolean isCancelSecEffectOwner() {
        return cancelSecEffectOwner;
    }

    public void setCancelSecEffectOwner(boolean _cancelSecEffectOwner) {
        cancelSecEffectOwner = _cancelSecEffectOwner;
    }

    public Rate getMultEvtRateSecEffectOwner() {
        return multEvtRateSecEffectOwner;
    }

    public void setMultEvtRateSecEffectOwner(Rate _multEvtRateSecEffectOwner) {
        multEvtRateSecEffectOwner = _multEvtRateSecEffectOwner;
    }

    public String getMultPower() {
        return multPower;
    }

    public void setMultPower(String _multPower) {
        multPower = _multPower;
    }

    public String getMultDamage() {
        return multDamage;
    }

    public void setMultDamage(String _multDamage) {
        multDamage = _multDamage;
    }

    public Rate getMultStab() {
        return multStab;
    }

    public void setMultStab(Rate _multStab) {
        multStab = _multStab;
    }

    public IdMap<Statistic, Byte> getBonusStatRank() {
        return bonusStatRank;
    }

    public void setBonusStatRank(IdMap<Statistic, Byte> _bonusStatRank) {
        bonusStatRank = _bonusStatRank;
    }

    public IdMap<Statistic, Byte> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public void setBoostStatRankProtected(
            IdMap<Statistic, Byte> _boostStatRankProtected) {
        boostStatRankProtected = _boostStatRankProtected;
    }

    public IdMap<Statistic, Byte> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public void setBoostStatRankEndRound(
            IdMap<Statistic, Byte> _boostStatRankEndRound) {
        boostStatRankEndRound = _boostStatRankEndRound;
    }

    public IdMap<Statistic, Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public void setMultStatAlly(IdMap<Statistic, Rate> _multStat) {
        multStatAlly = _multStat;
    }

    public IdMap<Statistic, Byte> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public void setMultStatIfKoFoe(IdMap<Statistic, Byte> _multStatIfKoFoe) {
        multStatIfKoFoe = _multStatIfKoFoe;
    }

    public IdMap<Statistic, Byte> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public void setMultStatIfLowStat(IdMap<Statistic, Byte> _multStatIfLowStat) {
        multStatIfLowStat = _multStatIfLowStat;
    }

    public StatisticCategoryList<Rate> getMultStatIfCat() {
        return multStatIfCat;
    }

    public void setMultStatIfCat(
            StatisticCategoryList<Rate> _multStatIfCat) {
        multStatIfCat = _multStatIfCat;
    }

    public StatisticStatusList getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public void setMultStatIfStatutRank(
            StatisticStatusList _multStatIfStatutRank) {
        multStatIfStatutRank = _multStatIfStatutRank;
    }

    public StatisticCategoryList<Byte> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public void setMultStatIfDamageCat(
            StatisticCategoryList<Byte> _multStatIfDamageCat) {
        multStatIfDamageCat = _multStatIfDamageCat;
    }

    public StatisticTypeList<Byte> getMultStatIfDamgeType() {
        return multStatIfDamgeType;
    }

    public void setMultStatIfDamgeType(
            StatisticTypeList<Byte> _multStatIfDamgeType) {
        multStatIfDamgeType = _multStatIfDamgeType;
    }

    public IdMap<Statistic, String> getMultStat() {
        return multStat;
    }

    public void setMultStat(IdMap<Statistic, String> _multStat) {
        multStat = _multStat;
    }

    public boolean isInflictingDamageInsteadOfSuffering() {
        return inflictingDamageInsteadOfSuffering;
    }

    public void setInflictingDamageInsteadOfSuffering(
            boolean _inflictingDamageInsteadOfSuffering) {
        inflictingDamageInsteadOfSuffering = _inflictingDamageInsteadOfSuffering;
    }

    public Rate getMultVarBoost() {
        return multVarBoost;
    }

    public void setMultVarBoost(Rate _multVarBoost) {
        multVarBoost = _multVarBoost;
    }

    public int getNbUsedPp() {
        return nbUsedPp;
    }

    public void setNbUsedPp(int _nbUsedPp) {
        nbUsedPp = _nbUsedPp;
    }

    public boolean isNbHits() {
        return nbHits;
    }

    public void setNbHits(boolean _nbHits) {
        nbHits = _nbHits;
    }

    public boolean isBreakProtection() {
        return breakProtection;
    }

    public void setBreakProtection(boolean _breakProtection) {
        breakProtection = _breakProtection;
    }

    public boolean isPlate() {
        return plate;
    }

    public void setPlate(boolean _plate) {
        plate = _plate;
    }

    public boolean isHealedStatusBySwitch() {
        return healedStatusBySwitch;
    }

    public void setHealedStatusBySwitch(boolean _healedStatusBySwitch) {
        healedStatusBySwitch = _healedStatusBySwitch;
    }

    public Rate getHealedHpRateBySwitch() {
        return healedHpRateBySwitch;
    }

    public void setHealedHpRateBySwitch(Rate _healedHpRateBySwitch) {
        healedHpRateBySwitch = _healedHpRateBySwitch;
    }

    public StringMap<Short> getIncreasedPrio() {
        return increasedPrio;
    }

    public void setIncreasedPrio(StringMap<Short> _increasedPrio) {
        increasedPrio = _increasedPrio;
    }

    public StringMap<Short> getIncreasedPrioTypes() {
        return increasedPrioTypes;
    }

    public void setIncreasedPrioTypes(StringMap<Short> _increasedPrioTypes) {
        increasedPrioTypes = _increasedPrioTypes;
    }

    public IdList<Statistic> getMaxStatisticsIfCh() {
        return maxStatisticsIfCh;
    }

    public void setMaxStatisticsIfCh(IdList<Statistic> _maxStatisticsIfCh) {
        maxStatisticsIfCh = _maxStatisticsIfCh;
    }

    public MonteCarloString getSingleStatus() {
        return singleStatus;
    }

    public void setSingleStatus(MonteCarloString _singleStatus) {
        singleStatus = _singleStatus;
    }

    public boolean isAchievedDisappearedPk() {
        return achievedDisappearedPk;
    }

    public void setAchievedDisappearedPk(boolean _achievedDisappearedPk) {
        achievedDisappearedPk = _achievedDisappearedPk;
    }

    public StringMap<String> getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(StringMap<String> _forwardStatus) {
        forwardStatus = _forwardStatus;
    }

    public StringMap<String> getFailStatus() {
        return failStatus;
    }

    public void setFailStatus(StringMap<String> _failStatus) {
        failStatus = _failStatus;
    }

    public String getTypeForMoves() {
        return typeForMoves;
    }

    public void setTypeForMoves(String _typeForMoves) {
        typeForMoves = _typeForMoves;
    }

    public Rate getMaxHpForUsingBerry() {
        return maxHpForUsingBerry;
    }

    public void setMaxHpForUsingBerry(Rate _maxHpForUsingBerry) {
        maxHpForUsingBerry = _maxHpForUsingBerry;
    }

    public boolean isMumy() {
        return mumy;
    }

    public void setMumy(boolean _mumy) {
        mumy = _mumy;
    }

    public WeatherTypes getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public void setHealHpByTypeIfWeather(
            WeatherTypes _healHpByTypeIfWeather) {
        healHpByTypeIfWeather = _healHpByTypeIfWeather;
    }

    public StringMap<StringList> getImmuMoveTypesByWeather() {
        return immuMoveTypesByWeather;
    }

    public void setImmuMoveTypesByWeather(
            StringMap<StringList> _immuMoveTypesByWeather) {
        immuMoveTypesByWeather = _immuMoveTypesByWeather;
    }

    public CustList<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }

    public void setEffectEndRound(CustList<EffectEndRound> _effectEndRound) {
        effectEndRound = _effectEndRound;
    }

    public CustList<EffectWhileSendingWithStatistic> getEffectSending() {
        return effectSending;
    }

    public void setEffectSending(CustList<EffectWhileSendingWithStatistic> _effectSending) {
        effectSending = _effectSending;
    }

    public StringMap<TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public void setChangingBoostTypes(
            StringMap<TypeDamageBoost> _changingBoostTypes) {
        changingBoostTypes = _changingBoostTypes;
    }

    public StringList getImmuAllyFromMoves() {
        return immuAllyFromMoves;
    }

    public void setImmuAllyFromMoves(StringList _immuAllyFromMoves) {
        immuAllyFromMoves = _immuAllyFromMoves;
    }

    public StringMap<StringList> getImmuStatusTypes() {
        return immuStatusTypes;
    }

    public void setImmuStatusTypes(StringMap<StringList> _immuStatusTypes) {
        immuStatusTypes = _immuStatusTypes;
    }

    public StringMap<IdList<Statistic>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
    }

    public void setImmuLowStatisTypes(
            StringMap<IdList<Statistic>> _immuLowStatisTypes) {
        immuLowStatisTypes = _immuLowStatisTypes;
    }

    public IdMap<Statistic, Byte> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public void setLowStatFoeHit(IdMap<Statistic, Byte> _lowStatFoeHit) {
        lowStatFoeHit = _lowStatFoeHit;
    }

    public boolean isCopyMovesTypes() {
        return copyMovesTypes;
    }

    public void setCopyMovesTypes(boolean _copyMovesTypes) {
        copyMovesTypes = _copyMovesTypes;
    }

    public StringMap<Rate> getMultPowerMovesTypesGlobal() {
        return multPowerMovesTypesGlobal;
    }

    public void setMultPowerMovesTypesGlobal(
            StringMap<Rate> _multPowerMovesTypesGlobal) {
        multPowerMovesTypesGlobal = _multPowerMovesTypesGlobal;
    }

    public Rate getHealHpWhileUsingBerry() {
        return healHpWhileUsingBerry;
    }

    public void setHealHpWhileUsingBerry(Rate _healHpWhileUsingBerry) {
        healHpWhileUsingBerry = _healHpWhileUsingBerry;
    }

    public boolean isReverseEffectsPowerMovesTypesGlobal() {
        return reverseEffectsPowerMovesTypesGlobal;
    }

    public void setReverseEffectsPowerMovesTypesGlobal(
            boolean _reverseEffectsPowerMovesTypesGlobal) {
        reverseEffectsPowerMovesTypesGlobal = _reverseEffectsPowerMovesTypesGlobal;
    }

    public boolean isTakeItemByDamagingMove() {
        return takeItemByDamagingMove;
    }

    public void setTakeItemByDamagingMove(boolean _takeItemByDamagingMove) {
        takeItemByDamagingMove = _takeItemByDamagingMove;
    }

    public boolean isGiveItemToAllyHavingUsed() {
        return giveItemToAllyHavingUsed;
    }

    public void setGiveItemToAllyHavingUsed(boolean _giveItemToAllyHavingUsed) {
        giveItemToAllyHavingUsed = _giveItemToAllyHavingUsed;
    }
}
