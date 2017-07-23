package aiki.fight.abilities;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypeDamageBoost;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.WeatherType;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class AbilityData {

    private EqList<TypesDuo> breakFoeImmune;

    @CheckedData
    private boolean forbidUseBerryAgainstFoes;
    private StringMap<String> chgtTypeByWeather;
    @CheckedData
    private boolean chgtTypeByDamage;
    @CheckedData
    private Rate recoilDamageFoe;
    @CheckedData
    private Rate recoilDamageFoeByKoOwner;
    @CheckedData
    private int decreaseNecStepsHatch;
    private StringMap<Rate> divideStatusRound;
    private StringMap<Rate> healHpByWeather;
    private StringList ignAbility;
    private StringList ignFoeTeamMove;
    @CheckedData
    private boolean ignFoeStatisBoost;
    private StringList immuMove;
    private EnumList<Statistic> immuLowStat;

    private EqList<StatisticStatus> immuLowStatIfStatus;

    @CheckedData
    private boolean immuCh;
    private StringList immuWeather;
    @CheckedData
    private boolean immuDamageTrappingMoves;
    @CheckedData
    private boolean immuDamageAllyMoves;
    @CheckedData
    private boolean immuDamageRecoil;
    private StringList immuAbility;
    private StringList immuStatusBeginRound;
    @CheckedData
    private boolean immuRechargeRound;
    private StringMap<StringList> immuStatus;
    @CheckedData
    private boolean slowing;
    private StringMap<Rate> multDamageFoe;
    @CheckedData
    private Rate multDamageCh;
    @CheckedData
    private Rate multAllyDamage;
    @CheckedData
    private Rate multSufferedDamageSuperEff;
    @CheckedData
    private boolean immuSufferedDamageLowEff;
    @CheckedData
    private Rate multEvtRateCh;
    @CheckedData
    private boolean cancelSecEffectOther;
    @CheckedData
    private boolean cancelSecEffectOwner;
    @CheckedData
    private Rate multEvtRateSecEffectOwner;
    @CheckedData
    private String multPower;
    @CheckedData
    private String multDamage;
    @CheckedData
    private Rate multStab;
    private EnumMap<Statistic,Byte> bonusStatRank;
    private EnumMap<Statistic,Byte> boostStatRankProtected;
    private EnumMap<Statistic,Byte> boostStatRankEndRound;
    private EnumMap<Statistic,Rate> multStatAlly;
    private EnumMap<Statistic,Byte> multStatIfKoFoe;
    private EnumMap<Statistic,Byte> multStatIfLowStat;
    private ObjectMap<StatisticCategory,Rate> multStatIfCat;

    private ObjectMap<StatisticStatus,Byte> multStatIfStatutRank;

    private ObjectMap<StatisticCategory,Byte> multStatIfDamageCat;

    private ObjectMap<StatisticType,Byte> multStatIfDamgeType;

    private EnumMap<Statistic,String> multStat;
    @CheckedData
    private boolean inflictingDamageInsteadOfSuffering;
    @CheckedData
    private Rate multVarBoost;
    @CheckedData
    private int nbUsedPp;
    @CheckedData
    private boolean nbHits;
    @CheckedData
    private boolean breakProtection;
    @CheckedData
    private boolean plate;
    @CheckedData
    private boolean healedStatusBySwitch;
    @CheckedData
    private Rate healedHpRateBySwitch;
    private StringMap<Short> increasedPrio;
    private StringMap<Short> increasedPrioTypes;
    private EnumList<Statistic> maxStatisticsIfCh;
    private MonteCarloString singleStatus;
    @CheckedData
    private boolean achievedDisappearedPk;
    private StringMap<String> forwardStatus;
    private StringMap<String> failStatus;
    @CheckedData
    private String typeForMoves;
    @CheckedData
    private Rate maxHpForUsingBerry;
    @CheckedData
    private boolean mumy;

    private ObjectMap<WeatherType,Rate> healHpByTypeIfWeather;

    private StringMap<StringList> immuMoveTypesByWeather;

    private CustList<EffectEndRound> effectEndRound;
    private CustList<EffectWhileSending> effectSending;

    private StringMap<TypeDamageBoost> changingBoostTypes;

    private StringList immuAllyFromMoves;

    private StringMap<StringList> immuStatusTypes;

    private StringMap<EnumList<Statistic>> immuLowStatisTypes;

    private EnumMap<Statistic,Byte> lowStatFoeHit;

    @CheckedData
    private boolean copyMovesTypes;

    private StringMap<Rate> multPowerMovesTypesGlobal;

    @CheckedData
    private boolean reverseEffectsPowerMovesTypesGlobal;

    @CheckedData
    private Rate healHpWhileUsingBerry;

    @CheckedData
    private boolean takeItemByDamagingMove;

    @CheckedData
    private boolean giveItemToAllyHavingUsed;

    public void validate(DataBase _data) {
        if (!maxHpForUsingBerry.isZeroOrGt()) {
            throw new DataException();
        }
        if (maxHpForUsingBerry.greaterOrEqualsOne()) {
            throw new DataException();
        }
        singleStatus.checkEvents();
        StringList events_ = new StringList(singleStatus.events());
        events_.removeObj(DataBase.EMPTY_STRING);
        if (!_data.getStatus().containsAllAsKeys(events_)) {
            throw new DataException();
        }
        if (!events_.isEmpty()) {
            if (!events_.containsAllObj(failStatus.getKeys())) {
                throw new DataException();
            }
        }
        for (WeatherType t: healHpByTypeIfWeather.getKeys()) {
            if (!_data.getMovesEffectGlobalWeather().containsObj(t.getWeather()) && !t.getWeather().isEmpty()) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(t.getType())) {
                throw new DataException();
            }
            if (!healHpByTypeIfWeather.getVal(t).isZeroOrGt()) {
                throw new DataException();
            }
            if (healHpByTypeIfWeather.getVal(t).isZero()) {
                throw new DataException();
            }
        }
        for (StatisticCategory t: multStatIfCat.getKeys()) {
            if (!t.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getAllCategories().containsObj(t.getCategory())) {
                throw new DataException();
            }
            if (!multStatIfCat.getVal(t).isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (StatisticStatus t: immuLowStatIfStatus) {
            if (!t.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getStatus().contains(t.getStatus())) {
                throw new DataException();
            }
        }
        for (TypesDuo t: breakFoeImmune) {
            if (!_data.getTypes().containsObj(t.getDamageType())) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(t.getPokemonType())) {
                throw new DataException();
            }
        }
        if (!_data.getMovesEffectGlobalWeather().containsAllObj(immuWeather)) {
            throw new DataException();
        }
        StringList keys_ = chgtTypeByWeather.getKeys();
        if (!keys_.isEmpty()) {
            keys_.removeObj(DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobalWeather().containsAllObj(keys_)) {
                throw new DataException();
            }
            if (!_data.getTypes().containsAllObj(chgtTypeByWeather.values())) {
                throw new DataException();
            }
        }
        keys_ = immuStatus.getKeys();
        if (!keys_.isEmpty()) {
            keys_.removeObj(DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobal().containsAllObj(keys_)) {
                throw new DataException();
            }
            for (StringList k: immuStatus.values()) {
                if (!_data.getStatus().containsAllAsKeys(k)) {
                    throw new DataException();
                }
            }
        }
        keys_ = immuMoveTypesByWeather.getKeys();
        if (!keys_.isEmpty()) {
            keys_.removeObj(DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobal().containsAllObj(keys_)) {
                throw new DataException();
            }
            for (StringList k: immuMoveTypesByWeather.values()) {
                if (!_data.getTypes().containsAllObj(k)) {
                    throw new DataException();
                }
            }
        }
        keys_ = healHpByWeather.getKeys();
        if (!keys_.isEmpty()) {
            keys_.removeObj(DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobalWeather().containsAllObj(keys_)) {
                throw new DataException();
            }
            for (Rate v: healHpByWeather.values()) {
                if (v.isZero()) {
                    throw new DataException();
                }
            }
        }
        if (!_data.getStatus().containsAllAsKeys(immuStatusBeginRound)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(divideStatusRound.getKeys())) {
            throw new DataException();
        }
        for (Rate v: divideStatusRound.values()) {
            if (!v.isZeroOrGt()) {
                throw new DataException();
            }
            if (v.isZero()) {
                throw new DataException();
            }
        }
        if (!_data.getAbilities().containsAllAsKeys(immuAbility)) {
            throw new DataException();
        }
        if (!_data.getAbilities().containsAllAsKeys(ignAbility)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(ignFoeTeamMove)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(immuMove)) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(multDamageFoe.getKeys())) {
            throw new DataException();
        }
        for (Rate v:multDamageFoe.values()) {
            if (!v.isZeroOrGt()) {
                throw new DataException();
            }
            if (v.isZero()) {
                throw new DataException();
            }
        }
        if (!multDamageCh.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multAllyDamage.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multSufferedDamageSuperEff.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multEvtRateCh.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multEvtRateSecEffectOwner.isZeroOrGt()) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(immuLowStat)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(forwardStatus.getKeys())) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(forwardStatus.values())) {
            throw new DataException();
        }
        if (!forwardStatus.isEmpty()) {
            for (String k: failStatus.getKeys()) {
                boolean appear_ = false;
                for (String v: forwardStatus.values()) {
                    if (StringList.quickEq(k, v)) {
                        appear_ = true;
                        break;
                    }
                }
                if (!appear_) {
                    throw new DataException();
                }
            }
//            if (!forwardStatus.values().containsAllObj(failStatus.getKeys())) {
//                throw new DataException();
//            }
        }
        if (!_data.getAllCategories().containsAllObj(increasedPrio.getKeys())) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(increasedPrioTypes.getKeys())) {
            throw new DataException();
        }
        if (!typeForMoves.isEmpty()) {
            if (!_data.getTypes().containsObj(typeForMoves)) {
                throw new DataException();
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStat.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStatAlly.getKeys())) {
            throw new DataException();
        }
        for (Rate v: multStatAlly.values()) {
            if (!v.isZeroOrGt()) {
                throw new DataException();
            }
            if (v.isZero()) {
                throw new DataException();
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(maxStatisticsIfCh)) {
            throw new DataException();
        }
        if (!multStab.isZeroOrGt()) {
            throw new DataException();
        }
        if (!healedHpRateBySwitch.isZeroOrGt()) {
            throw new DataException();
        }
        if (nbUsedPp < 0) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(bonusStatRank.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(boostStatRankProtected.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(boostStatRankEndRound.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStatIfKoFoe.getKeys())) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStatIfLowStat.getKeys())) {
            throw new DataException();
        }
        for (StatisticStatus k:multStatIfStatutRank.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getStatus().contains(k.getStatus())) {
                throw new DataException();
            }
        }
        for (StatisticCategory k:multStatIfDamageCat.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getCategories().containsObj(k.getCategory())) {
                throw new DataException();
            }
        }
        for (StatisticType k:multStatIfDamgeType.getKeys()) {
            if (!k.getStatistic().isBoost()) {
                throw new DataException();
            }
            if (!_data.getTypes().containsObj(k.getType())) {
                throw new DataException();
            }
        }
        if (!recoilDamageFoe.isZeroOrGt()) {
            throw new DataException();
        }
        if (decreaseNecStepsHatch < 0) {
            throw new DataException();
        }
        for (String k: changingBoostTypes.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            TypeDamageBoost type_ = changingBoostTypes.getVal(k);
            if (!_data.getTypes().containsObj(type_.getType())) {
                throw new DataException();
            }
            if (!type_.getBoost().greaterOrEqualsOne()) {
                throw new DataException();
            }
        }
        if (!_data.getMoves().containsAllAsKeys(immuAllyFromMoves)) {
            throw new DataException();
        }
        for (String k: immuStatusTypes.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            if (!_data.getStatus().containsAllAsKeys(immuStatusTypes.getVal(k))) {
                throw new DataException();
            }
        }
        for (String k: immuLowStatisTypes.getKeys()) {
            if (!_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
        }
        for (Statistic s: lowStatFoeHit.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
            if (lowStatFoeHit.getVal(s) >= 0) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(multPowerMovesTypesGlobal.getKeys())) {
            throw new DataException();
        }
        for (Rate r: multPowerMovesTypesGlobal.values()) {
            if (!r.isZeroOrGt()) {
                throw new DataException();
            }
        }
        if (!healHpWhileUsingBerry.isZeroOrGt()) {
            throw new DataException();
        }
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual)) {
                if (!(effectEndRound.first() instanceof EffectEndRoundTeam)) {
                    if (!(effectEndRound.first() instanceof EffectEndRoundMultiRelation)) {
                        throw new DataException();
                    }
                }
            }
        }
        if (!effectSending.isEmpty()) {
            effectSending.first().validate(_data);
        }
    }

    public boolean enabledSending() {
        return !effectSending.isEmpty();
    }

    public EqList<TypesDuo> getBreakFoeImmune() {
        return breakFoeImmune;
    }

    public void setBreakFoeImmune(EqList<TypesDuo> _breakFoeImmune) {
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

    public Rate getRecoilDamageFoeByKoOwner() {
        return recoilDamageFoeByKoOwner;
    }

    public void setRecoilDamageFoeByKoOwner(Rate _recoilDamageFoeByKoOwner) {
        recoilDamageFoeByKoOwner = _recoilDamageFoeByKoOwner;
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

    public EqList<StatisticStatus> getImmuLowStatIfStatus() {
        return immuLowStatIfStatus;
    }

    public void setImmuLowStatIfStatus(EqList<StatisticStatus> _immuLowStatIfStatus) {
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

    public EnumMap<Statistic,Byte> getBonusStatRank() {
        return bonusStatRank;
    }

    public void setBonusStatRank(EnumMap<Statistic,Byte> _bonusStatRank) {
        bonusStatRank = _bonusStatRank;
    }

    public EnumMap<Statistic,Byte> getBoostStatRankProtected() {
        return boostStatRankProtected;
    }

    public void setBoostStatRankProtected(EnumMap<Statistic,Byte> _boostStatRankProtected) {
        boostStatRankProtected = _boostStatRankProtected;
    }

    public EnumMap<Statistic,Byte> getBoostStatRankEndRound() {
        return boostStatRankEndRound;
    }

    public void setBoostStatRankEndRound(EnumMap<Statistic,Byte> _boostStatRankEndRound) {
        boostStatRankEndRound = _boostStatRankEndRound;
    }

    public EnumMap<Statistic,Rate> getMultStatAlly() {
        return multStatAlly;
    }

    public void setMultStatAlly(EnumMap<Statistic,Rate> _multStat) {
        multStatAlly = _multStat;
    }

    public EnumMap<Statistic,Byte> getMultStatIfKoFoe() {
        return multStatIfKoFoe;
    }

    public void setMultStatIfKoFoe(EnumMap<Statistic,Byte> _multStatIfKoFoe) {
        multStatIfKoFoe = _multStatIfKoFoe;
    }

    public EnumMap<Statistic,Byte> getMultStatIfLowStat() {
        return multStatIfLowStat;
    }

    public void setMultStatIfLowStat(EnumMap<Statistic,Byte> _multStatIfLowStat) {
        multStatIfLowStat = _multStatIfLowStat;
    }

    public ObjectMap<StatisticCategory,Rate> getMultStatIfCat() {
        return multStatIfCat;
    }

    public void setMultStatIfCat(ObjectMap<StatisticCategory,Rate> _multStatIfCat) {
        multStatIfCat = _multStatIfCat;
    }

    public ObjectMap<StatisticStatus,Byte> getMultStatIfStatutRank() {
        return multStatIfStatutRank;
    }

    public void setMultStatIfStatutRank(ObjectMap<StatisticStatus,Byte> _multStatIfStatutRank) {
        multStatIfStatutRank = _multStatIfStatutRank;
    }

    public ObjectMap<StatisticCategory,Byte> getMultStatIfDamageCat() {
        return multStatIfDamageCat;
    }

    public void setMultStatIfDamageCat(ObjectMap<StatisticCategory,Byte> _multStatIfDamageCat) {
        multStatIfDamageCat = _multStatIfDamageCat;
    }

    public ObjectMap<StatisticType,Byte> getMultStatIfDamgeType() {
        return multStatIfDamgeType;
    }

    public void setMultStatIfDamgeType(ObjectMap<StatisticType,Byte> _multStatIfDamgeType) {
        multStatIfDamgeType = _multStatIfDamgeType;
    }

    public EnumMap<Statistic,String> getMultStat() {
        return multStat;
    }

    public void setMultStat(EnumMap<Statistic,String> _multStat) {
        multStat = _multStat;
    }

    public boolean isInflictingDamageInsteadOfSuffering() {
        return inflictingDamageInsteadOfSuffering;
    }

    public void setInflictingDamageInsteadOfSuffering(boolean _inflictingDamageInsteadOfSuffering) {
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

    public ObjectMap<WeatherType,Rate> getHealHpByTypeIfWeather() {
        return healHpByTypeIfWeather;
    }

    public void setHealHpByTypeIfWeather(ObjectMap<WeatherType,Rate> _healHpByTypeIfWeather) {
        healHpByTypeIfWeather = _healHpByTypeIfWeather;
    }

    public StringMap<StringList> getImmuMoveTypesByWeather() {
        return immuMoveTypesByWeather;
    }

    public void setImmuMoveTypesByWeather(StringMap<StringList> _immuMoveTypesByWeather) {
        immuMoveTypesByWeather = _immuMoveTypesByWeather;
    }

    public CustList<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }

    public void setEffectEndRound(CustList<EffectEndRound> _effectEndRound) {
        effectEndRound = _effectEndRound;
    }

    public CustList<EffectWhileSending> getEffectSending() {
        return effectSending;
    }

    public void setEffectSending(CustList<EffectWhileSending> _effectSending) {
        effectSending = _effectSending;
    }

    public StringMap<TypeDamageBoost> getChangingBoostTypes() {
        return changingBoostTypes;
    }

    public void setChangingBoostTypes(StringMap<TypeDamageBoost> _changingBoostTypes) {
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

    public void setImmuLowStatisTypes(StringMap<EnumList<Statistic>> _immuLowStatisTypes) {
        immuLowStatisTypes = _immuLowStatisTypes;
    }

    public EnumMap<Statistic,Byte> getLowStatFoeHit() {
        return lowStatFoeHit;
    }

    public void setLowStatFoeHit(EnumMap<Statistic,Byte> _lowStatFoeHit) {
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

    public void setMultPowerMovesTypesGlobal(StringMap<Rate> _multPowerMovesTypesGlobal) {
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

    public void setReverseEffectsPowerMovesTypesGlobal(boolean _reverseEffectsPowerMovesTypesGlobal) {
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
