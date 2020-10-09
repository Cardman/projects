package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.util.StatisticPokemon;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;


public final class ItemForBattle extends Item {

    public static final String ITEM = "aiki.fight.items.ItemForBattle";

    private StringList typesPk;
    private boolean cancelImmuType;
    private boolean againstEvo;
    private boolean attackLast;
    private boolean boostExp;
    private StringList immuStatus;
    private boolean immuLowStatis;
    private StringMap<Short> increasingMaxNbRoundTrap;
    private boolean attacksSoon;
    private StringList synchroStatus;
    private StringMap<String> failStatus;
    private Rate protectAgainstKo;
    private Rate protectAgainstKoIfFullHp;
    private Rate drainedHpByDamageRate;
    private EnumMap<Statistic, Short> winEvFight;
    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private EnumMap<Statistic, Byte> multStatRank;

    private ObjectMap<StatisticPokemon, Byte> multStatPokemonRank;

    private EnumMap<Statistic, String> multStat;
    private StringMap<Short> increasingMaxNbRoundGlobalMove;
    private StringMap<Short> increasingMaxNbRoundTeamMove;
    private StringList immuMoves;
    private StringList hatching;
    private StringList immuTypes;
    private StringList immuWeather;
    private EnumMap<Statistic, Byte> boostStatisSuperEff;
    private StringMap<EnumMap<Statistic, Byte>> boostStatisTypes;

    private CustList<EffectEndRound> effectEndRound;
    private CustList<EffectWhileSending> effectSending;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawForAttackFirst.checkEvents()) {
            _data.setError(true);
        }
        hatching = StringUtil.intersect(hatching,_data.getPokedex().getKeys());
        if (effectEndRound.size() > 1) {
            _data.setError(true);
        }
        if (effectSending.size() > 1) {
            _data.setError(true);
        }
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual)) {
                _data.setError(true);
            }
        }
        if (!effectSending.isEmpty()) {
            effectSending.first().validate(_data);
        }
        for (EntryCust<StatisticPokemon, Byte> e : multStatPokemonRank
                .entryList()) {
            if (!_data.getPokedex().contains(e.getKey().getPokemon())) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStatRank.getKeys())) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                multStat.getKeys())) {
            _data.setError(true);
        }
        if (!_data.getMovesEffectGlobal().containsAllObj(
                increasingMaxNbRoundGlobalMove.getKeys())) {
            _data.setError(true);
        }
        for (short s : increasingMaxNbRoundGlobalMove.values()) {
            if (s < 0) {
                _data.setError(true);
            }
        }
        if (!_data.getMovesEffectTeam().containsAllObj(
                increasingMaxNbRoundTeamMove.getKeys())) {
            _data.setError(true);
        }
        for (short s : increasingMaxNbRoundTeamMove.values()) {
            if (s < 0) {
                _data.setError(true);
            }
        }
        if (!_data.getTypes().containsAllObj(typesPk)) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(immuTypes)) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(immuMoves)) {
            _data.setError(true);
        }
        if (!_data.getMovesEffectGlobalWeather().containsAllObj(immuWeather)) {
            _data.setError(true);
        }
        for (EntryCust<Statistic, Byte> e : boostStatisSuperEff.entryList()) {
            if (!e.getKey().isBoost()) {
                _data.setError(true);
            }
        }
        for (String t : boostStatisTypes.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), t)) {
                _data.setError(true);
            }
            for (EntryCust<Statistic, Byte> s : boostStatisTypes.getVal(t)
                    .entryList()) {
                if (!s.getKey().isBoost()) {
                    _data.setError(true);
                }
            }
        }
        if (!_data.getStatus().containsAllAsKeys(immuStatus)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(synchroStatus)) {
            _data.setError(true);
        }
        if (!synchroStatus.containsAllObj(failStatus.getKeys())) {
            _data.setError(true);
        }
        if (!_data.getTrappingMoves().containsAllObj(
                increasingMaxNbRoundTrap.getKeys())) {
            _data.setError(true);
        }
        for (short s : increasingMaxNbRoundTrap.values()) {
            if (s < 0) {
                _data.setError(true);
            }
        }
        if (!Statistic.getStatisticsWithBase().containsAllObj(
                winEvFight.getKeys())) {
            _data.setError(true);
        }
        for (Statistic s : winEvFight.getKeys()) {
            if (winEvFight.getVal(s) < 0) {
                _data.setError(true);
            }
        }
        if (!protectAgainstKo.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!protectAgainstKoIfFullHp.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!drainedHpByDamageRate.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multTrappingDamage.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multWinningHappiness.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multWinningEv.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multWinningExp.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!multDrainedHp.isZeroOrGt()) {
            _data.setError(true);
        }
        if (!damageRecoil.isZeroOrGt()) {
            _data.setError(true);

        }
    }

    public boolean isUsedForExp() {
        if (boostExp) {
            return true;
        }
        if (againstEvo) {
            return true;
        }
        if (!winEvFight.isEmpty()) {
            return true;
        }
        if (!hatching.isEmpty()) {
            return true;
        }
        if (!multWinningHappiness.isZero()) {
            return true;
        }
        if (!multWinningEv.isZero()) {
            return true;
        }
        return !multWinningExp.isZero();
    }

    public boolean enabledSending() {
        return !effectSending.isEmpty();
    }

    public StringList getTypesPk() {
        return typesPk;
    }

    public void setTypesPk(StringList _typesPk) {
        typesPk = _typesPk;
    }

    public boolean getCancelImmuType() {
        return cancelImmuType;
    }

    public void setCancelImmuType(boolean _cancelImmuType) {
        cancelImmuType = _cancelImmuType;
    }

    public boolean getAgainstEvo() {
        return againstEvo;
    }

    public void setAgainstEvo(boolean _againstEvo) {
        againstEvo = _againstEvo;
    }

    public boolean getAttackLast() {
        return attackLast;
    }

    public void setAttackLast(boolean _attackLast) {
        attackLast = _attackLast;
    }

    public boolean getBoostExp() {
        return boostExp;
    }

    public void setBoostExp(boolean _boostExp) {
        boostExp = _boostExp;
    }

    public StringList getImmuStatus() {
        return immuStatus;
    }

    public void setImmuStatus(StringList _immuStatus) {
        immuStatus = _immuStatus;
    }

    public boolean getImmuLowStatis() {
        return immuLowStatis;
    }

    public void setImmuLowStatis(boolean _immuLowStatis) {
        immuLowStatis = _immuLowStatis;
    }

    public StringMap<Short> getIncreasingMaxNbRoundTrap() {
        return increasingMaxNbRoundTrap;
    }

    public void setIncreasingMaxNbRoundTrap(
            StringMap<Short> _increasingMaxNbRoundTrap) {
        increasingMaxNbRoundTrap = _increasingMaxNbRoundTrap;
    }

    public boolean getAttacksSoon() {
        return attacksSoon;
    }

    public void setAttacksSoon(boolean _attacksSoon) {
        attacksSoon = _attacksSoon;
    }

    public StringList getSynchroStatus() {
        return synchroStatus;
    }

    public void setSynchroStatus(StringList _synchroStatus) {
        synchroStatus = _synchroStatus;
    }

    public StringMap<String> getFailStatus() {
        return failStatus;
    }

    public void setFailStatus(StringMap<String> _failStatus) {
        failStatus = _failStatus;
    }

    public Rate getProtectAgainstKo() {
        return protectAgainstKo;
    }

    public void setProtectAgainstKo(Rate _protectAgainstKo) {
        protectAgainstKo = _protectAgainstKo;
    }

    public Rate getProtectAgainstKoIfFullHp() {
        return protectAgainstKoIfFullHp;
    }

    public void setProtectAgainstKoIfFullHp(Rate _protectAgainstKoIfFullHp) {
        protectAgainstKoIfFullHp = _protectAgainstKoIfFullHp;
    }

    public Rate getDrainedHpByDamageRate() {
        return drainedHpByDamageRate;
    }

    public void setDrainedHpByDamageRate(Rate _drainedHpByDamageRate) {
        drainedHpByDamageRate = _drainedHpByDamageRate;
    }

    public EnumMap<Statistic, Short> getWinEvFight() {
        return winEvFight;
    }

    public void setWinEvFight(EnumMap<Statistic, Short> _winEvFight) {
        winEvFight = _winEvFight;
    }

    public MonteCarloBoolean getLawForAttackFirst() {
        return lawForAttackFirst;
    }

    public void setLawForAttackFirst(MonteCarloBoolean _lawForAttackFirst) {
        lawForAttackFirst = _lawForAttackFirst;
    }

    public Rate getMultTrappingDamage() {
        return multTrappingDamage;
    }

    public void setMultTrappingDamage(Rate _multTrappingDamage) {
        multTrappingDamage = _multTrappingDamage;
    }

    public Rate getMultWinningHappiness() {
        return multWinningHappiness;
    }

    public void setMultWinningHappiness(Rate _multWinningHappiness) {
        multWinningHappiness = _multWinningHappiness;
    }

    public Rate getMultWinningEv() {
        return multWinningEv;
    }

    public void setMultWinningEv(Rate _multWinningEv) {
        multWinningEv = _multWinningEv;
    }

    public Rate getMultWinningExp() {
        return multWinningExp;
    }

    public void setMultWinningExp(Rate _multWinningExp) {
        multWinningExp = _multWinningExp;
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

    public Rate getMultDrainedHp() {
        return multDrainedHp;
    }

    public void setMultDrainedHp(Rate _multDrainedHp) {
        multDrainedHp = _multDrainedHp;
    }

    public Rate getDamageRecoil() {
        return damageRecoil;
    }

    public void setDamageRecoil(Rate _damageRecoil) {
        damageRecoil = _damageRecoil;
    }

    public EnumMap<Statistic, Byte> getMultStatRank() {
        return multStatRank;
    }

    public void setMultStatRank(EnumMap<Statistic, Byte> _multStat) {
        multStatRank = _multStat;
    }

    public ObjectMap<StatisticPokemon, Byte> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public void setMultStatPokemonRank(
            ObjectMap<StatisticPokemon, Byte> _multStatPokemonRank) {
        multStatPokemonRank = _multStatPokemonRank;
    }

    public EnumMap<Statistic, String> getMultStat() {
        return multStat;
    }

    public void setMultStat(EnumMap<Statistic, String> _multStat) {
        multStat = _multStat;
    }

    public StringMap<Short> getIncreasingMaxNbRoundGlobalMove() {
        return increasingMaxNbRoundGlobalMove;
    }

    public void setIncreasingMaxNbRoundGlobalMove(
            StringMap<Short> _increasingMaxNbRoundGlobalMove) {
        increasingMaxNbRoundGlobalMove = _increasingMaxNbRoundGlobalMove;
    }

    public StringMap<Short> getIncreasingMaxNbRoundTeamMove() {
        return increasingMaxNbRoundTeamMove;
    }

    public void setIncreasingMaxNbRoundTeamMove(
            StringMap<Short> _increasingMaxNbRoundTeamMove) {
        increasingMaxNbRoundTeamMove = _increasingMaxNbRoundTeamMove;
    }

    public StringList getHatching() {
        return hatching;
    }

    public void setHatching(StringList _hatching) {
        hatching = _hatching;
    }

    public StringList getImmuTypes() {
        return immuTypes;
    }

    public void setImmuTypes(StringList _immuTypes) {
        immuTypes = _immuTypes;
    }

    public StringList getImmuMoves() {
        return immuMoves;
    }

    public void setImmuMoves(StringList _immuMoves) {
        immuMoves = _immuMoves;
    }

    public StringList getImmuWeather() {
        return immuWeather;
    }

    public void setImmuWeather(StringList _immuWeather) {
        immuWeather = _immuWeather;
    }

    public EnumMap<Statistic, Byte> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public void setBoostStatisSuperEff(
            EnumMap<Statistic, Byte> _boostStatisSuperEff) {
        boostStatisSuperEff = _boostStatisSuperEff;
    }

    public StringMap<EnumMap<Statistic, Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public void setBoostStatisTypes(
            StringMap<EnumMap<Statistic, Byte>> _boostStatisTypes) {
        boostStatisTypes = _boostStatisTypes;
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
}
