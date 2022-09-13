package aiki.fight.abilities;

import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.util.*;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.AbsMap;
import code.util.EqList;

import code.util.StringList;
import code.util.StringMap;
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
    private EnumList<Statistic> immuLowStat;

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
    private AbsMap<Statistic, Byte> bonusStatRank;
    private AbsMap<Statistic, Byte> boostStatRankProtected;
    private AbsMap<Statistic, Byte> boostStatRankEndRound;
    private AbsMap<Statistic, Rate> multStatAlly;
    private AbsMap<Statistic, Byte> multStatIfKoFoe;
    private AbsMap<Statistic, Byte> multStatIfLowStat;
    private StatisticCategoryList<Rate> multStatIfCat;

    private StatisticStatusList multStatIfStatutRank;

    private StatisticCategoryList<Byte> multStatIfDamageCat;

    private StatisticTypeList<Byte> multStatIfDamgeType;

    private AbsMap<Statistic, String> multStat;
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
    private EnumList<Statistic> maxStatisticsIfCh;
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

    private StringMap<EnumList<Statistic>> immuLowStatisTypes;

    private AbsMap<Statistic, Byte> lowStatFoeHit;

    private boolean copyMovesTypes;

    private StringMap<Rate> multPowerMovesTypesGlobal;

    private boolean reverseEffectsPowerMovesTypesGlobal;

    private Rate healHpWhileUsingBerry;

    private boolean takeItemByDamagingMove;

    private boolean giveItemToAllyHavingUsed;

    public void validate(DataBase _data) {
        if (!maxHpForUsingBerry.isZeroOrGt()) {
            _data.setError(true);
        }
        if (maxHpForUsingBerry.greaterOrEqualsOne()) {
            _data.setError(true);
        }
        if (!singleStatus.checkEvents()) {
            _data.setError(true);
        }
        StringList events_ = new StringList(singleStatus.events());
        StringUtil.removeObj(events_, DataBase.EMPTY_STRING);
        if (!_data.getStatus().containsAllAsKeys(events_)) {
            _data.setError(true);
        }
        if (!events_.isEmpty()) {
            if (!events_.containsAllObj(failStatus.getKeys())) {
                _data.setError(true);
            }
        }
        for (WeatherType t : healHpByTypeIfWeather.getKeys()) {
            if (!StringUtil.contains(_data.getMovesEffectGlobalWeather(), t.getWeather()) && !t.getWeather().isEmpty()) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), t.getType())) {
                _data.setError(true);
            }
            if (!healHpByTypeIfWeather.getVal(t).isZeroOrGt()) {
                _data.setError(true);
            }
            if (healHpByTypeIfWeather.getVal(t).isZero()) {
                _data.setError(true);
            }
        }
        for (StatisticCategory t : multStatIfCat.getKeys()) {
            if (!t.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getAllCategories(), t.getCategory())) {
                _data.setError(true);
            }
            if (!multStatIfCat.getVal(t).isZeroOrGt()) {
                _data.setError(true);
            }
        }
        for (StatisticStatus t : immuLowStatIfStatus) {
            if (!t.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!_data.getStatus().contains(t.getStatus())) {
                _data.setError(true);
            }
        }
        for (TypesDuo t : breakFoeImmune) {
            if (!StringUtil.contains(_data.getTypes(), t.getDamageType())) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), t.getPokemonType())) {
                _data.setError(true);
            }
        }
        if (!_data.getMovesEffectGlobalWeather().containsAllObj(immuWeather)) {
            _data.setError(true);
        }
        CustList<String> keys_ = chgtTypeByWeather.getKeys();
        if (!keys_.isEmpty()) {
            StringUtil.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobalWeather().containsAllObj(keys_)) {
                _data.setError(true);
            }
            if (!_data.getTypes().containsAllObj(chgtTypeByWeather.values())) {
                _data.setError(true);
            }
        }
        keys_ = immuStatus.getKeys();
        if (!keys_.isEmpty()) {
            StringUtil.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobal().containsAllObj(keys_)) {
                _data.setError(true);
            }
            for (StringList k : immuStatus.values()) {
                if (!_data.getStatus().containsAllAsKeys(k)) {
                    _data.setError(true);
                }
            }
        }
        keys_ = immuMoveTypesByWeather.getKeys();
        if (!keys_.isEmpty()) {
            StringUtil.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobal().containsAllObj(keys_)) {
                _data.setError(true);
            }
            for (StringList k : immuMoveTypesByWeather.values()) {
                if (!_data.getTypes().containsAllObj(k)) {
                    _data.setError(true);
                }
            }
        }
        keys_ = healHpByWeather.getKeys();
        if (!keys_.isEmpty()) {
            StringUtil.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobalWeather().containsAllObj(keys_)) {
                _data.setError(true);
            }
            for (Rate v : healHpByWeather.values()) {
                if (v.isZero()) {
                    _data.setError(true);
                }
            }
        }
        if (!_data.getStatus().containsAllAsKeys(immuStatusBeginRound)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(divideStatusRound.getKeys())) {
            _data.setError(true);
        }
        for (Rate v : divideStatusRound.values()) {
            if (!v.isZeroOrGt()) {
                _data.setError(true);
            }
            if (v.isZero()) {
                _data.setError(true);
            }
        }
        if (!_data.getAbilities().containsAllAsKeys(immuAbility)) {
            _data.setError(true);
        }
        if (!_data.getAbilities().containsAllAsKeys(ignAbility)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(ignFoeTeamMove)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(immuMove)) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(multDamageFoe.getKeys())) {
            _data.setError(true);
        }
        for (Rate v : multDamageFoe.values()) {
            if (!v.isZeroOrGt()) {
                _data.setError(true);
            }
            if (v.isZero()) {
                _data.setError(true);
            }
        }
        if (!multDamageCh.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multAllyDamage.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multSufferedDamageSuperEff.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multEvtRateCh.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multEvtRateSecEffectOwner.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(immuLowStat)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(forwardStatus.getKeys())) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(forwardStatus.values())) {
            _data.setError(true);
        }
        if (!forwardStatus.isEmpty()) {
            for (String k : failStatus.getKeys()) {
                boolean appear_ = false;
                for (String v : forwardStatus.values()) {
                    if (StringUtil.quickEq(k, v)) {
                        appear_ = true;
                        break;
                    }
                }
                if (!appear_) {
                    _data.setError(true);
                }
            }
        }
        if (!_data.getAllCategories().containsAllObj(increasedPrio.getKeys())) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(increasedPrioTypes.getKeys())) {
            _data.setError(true);
        }
        for (String s : increasedPrio.getKeys()) {
            if (increasedPrio.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        for (String s : increasedPrioTypes.getKeys()) {
            if (increasedPrioTypes.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        if (!typeForMoves.isEmpty()) {
            if (!StringUtil.contains(_data.getTypes(), typeForMoves)) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStat.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStatAlly.getKeys())) {
            _data.setError(true);
        }
        for (Rate v : multStatAlly.values()) {
            if (!v.isZeroOrGt()) {
                _data.setError(true);
            }
            if (v.isZero()) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                maxStatisticsIfCh)) {
            _data.setError(true);
        }
        if (!multStab.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!healedHpRateBySwitch.isZeroOrGt()) {
            _data.setError(true);
        }
        if (nbUsedPp < 0) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                bonusStatRank.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                boostStatRankProtected.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                boostStatRankEndRound.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStatIfKoFoe.getKeys())) {
            _data.setError(true);
        }
        for (Statistic s : boostStatRankProtected.getKeys()) {
            if (boostStatRankProtected.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        for (Statistic s : multStatIfKoFoe.getKeys()) {
            if (multStatIfKoFoe.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        for (Statistic s : boostStatRankEndRound.getKeys()) {
            if (boostStatRankEndRound.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        for (Statistic s : multStatIfLowStat.getKeys()) {
            if (multStatIfLowStat.getVal(s) <= 0) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStatIfLowStat.getKeys())) {
            _data.setError(true);
        }
        for (StatisticStatus k : multStatIfStatutRank.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!_data.getStatus().contains(k.getStatus())) {
                _data.setError(true);
            }
            if (multStatIfStatutRank.getVal(k) <= 0) {
                _data.setError(true);
            }
        }
        for (StatisticCategory k : multStatIfDamageCat.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getCategories(), k.getCategory())) {
                _data.setError(true);
            }
            if (multStatIfDamageCat.getVal(k) <= 0) {
                _data.setError(true);
            }
        }
        for (StatisticType k : multStatIfDamgeType.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                _data.setError(true);
            }
            if (!StringUtil.contains(_data.getTypes(), k.getType())) {
                _data.setError(true);
            }
            if (multStatIfDamgeType.getVal(k) <= 0) {
                _data.setError(true);
            }
        }
        if (!recoilDamageFoe.isZeroOrGt()) {
            _data.setError(true);
        }
        if (decreaseNecStepsHatch < 0) {
            _data.setError(true);
        }
        for (String k : changingBoostTypes.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k)) {
                _data.setError(true);
            }
            TypeDamageBoost type_ = changingBoostTypes.getVal(k);
            if (!StringUtil.contains(_data.getTypes(), type_.getType())) {
                _data.setError(true);
            }
            if (!type_.getBoost().greaterOrEqualsOne()) {
                _data.setError(true);
            }
        }
        if (!_data.getMoves().containsAllAsKeys(immuAllyFromMoves)) {
            _data.setError(true);
        }
        for (String k : immuStatusTypes.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), k)) {
                _data.setError(true);
            }
            if (!_data.getStatus().containsAllAsKeys(immuStatusTypes.getVal(k))) {
                _data.setError(true);
            }
        }
        for (EntryCust<String, EnumList<Statistic>> e : immuLowStatisTypes
                .entryList()) {
            if (!StringUtil.contains(_data.getTypes(), e.getKey())) {
                _data.setError(true);
            }
        }
        for (Statistic s : lowStatFoeHit.getKeys()) {
            if (!s.isBoost()) {
                _data.setError(true);
            }
            if (lowStatFoeHit.getVal(s) >= 0) {
                _data.setError(true);
            }
        }
        if (!_data.getTypes().containsAllObj(
                multPowerMovesTypesGlobal.getKeys())) {
            _data.setError(true);
        }
        for (Rate r : multPowerMovesTypesGlobal.values()) {
            if (!r.isZeroOrGt()) {
                _data.setError(true);
            }
        }
        if (!healHpWhileUsingBerry.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual)) {
                if (!(effectEndRound.first() instanceof EffectEndRoundTeam)) {
                    if (!(effectEndRound.first() instanceof EffectEndRoundMultiRelation)) {
                        _data.setError(true);
                    }
                }
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

    public EnumList<Statistic> getImmuLowStat() {
        return immuLowStat;
    }

    public void setImmuLowStat(EnumList<Statistic> _immuLowStat) {
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

    public AbsMap<Statistic, Byte> getBonusStatRank() {
        return bonusStatRank;
    }

    public void setBonusStatRank(AbsMap<Statistic, Byte> _bonusStatRank) {
        bonusStatRank = _bonusStatRank;
    }

    public AbsMap<Statistic, Byte> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public void setBoostStatRankProtected(
            AbsMap<Statistic, Byte> _boostStatRankProtected) {
        boostStatRankProtected = _boostStatRankProtected;
    }

    public AbsMap<Statistic, Byte> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public void setBoostStatRankEndRound(
            AbsMap<Statistic, Byte> _boostStatRankEndRound) {
        boostStatRankEndRound = _boostStatRankEndRound;
    }

    public AbsMap<Statistic, Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public void setMultStatAlly(AbsMap<Statistic, Rate> _multStat) {
        multStatAlly = _multStat;
    }

    public AbsMap<Statistic, Byte> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public void setMultStatIfKoFoe(AbsMap<Statistic, Byte> _multStatIfKoFoe) {
        multStatIfKoFoe = _multStatIfKoFoe;
    }

    public AbsMap<Statistic, Byte> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public void setMultStatIfLowStat(AbsMap<Statistic, Byte> _multStatIfLowStat) {
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

    public AbsMap<Statistic, String> getMultStat() {
        return multStat;
    }

    public void setMultStat(AbsMap<Statistic, String> _multStat) {
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

    public EnumList<Statistic> getMaxStatisticsIfCh() {
        return maxStatisticsIfCh;
    }

    public void setMaxStatisticsIfCh(EnumList<Statistic> _maxStatisticsIfCh) {
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

    public StringMap<EnumList<Statistic>> getImmuLowStatisTypes() {
        return immuLowStatisTypes;
    }

    public void setImmuLowStatisTypes(
            StringMap<EnumList<Statistic>> _immuLowStatisTypes) {
        immuLowStatisTypes = _immuLowStatisTypes;
    }

    public AbsMap<Statistic, Byte> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public void setLowStatFoeHit(AbsMap<Statistic, Byte> _lowStatFoeHit) {
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
