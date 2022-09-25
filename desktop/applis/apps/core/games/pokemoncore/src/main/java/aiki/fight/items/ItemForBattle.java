package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.util.StatisticPokemonByte;
import aiki.fight.util.StatisticPokemons;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.IdMap;
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
    private IdMap<Statistic, Short> winEvFight;
    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private IdMap<Statistic, Byte> multStatRank;

    private StatisticPokemons multStatPokemonRank;

    private IdMap<Statistic, String> multStat;
    private StringMap<Short> increasingMaxNbRoundGlobalMove;
    private StringMap<Short> increasingMaxNbRoundTeamMove;
    private StringList immuMoves;
    private StringList hatching;
    private StringList immuTypes;
    private StringList immuWeather;
    private IdMap<Statistic, Byte> boostStatisSuperEff;
    private StringMap<IdMap<Statistic, Byte>> boostStatisTypes;

    private CustList<EffectEndRound> effectEndRound;
    private CustList<EffectWhileSendingWithStatistic> effectSending;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkEvents(lawForAttackFirst,_data);
        hatching = StringUtil.intersect(hatching,_data.getPokedex().getKeys());
        DataInfoChecker.checkGreater(1,effectEndRound.size(),_data);
        DataInfoChecker.checkGreater(1,effectSending.size(),_data);
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual)) {
                _data.setError(true);
            }
        }
        if (!effectSending.isEmpty()) {
            effectSending.first().validate(_data);
        }
        for (StatisticPokemonByte e : multStatPokemonRank
                .entryList()) {
            DataInfoChecker.checkStringListContains(_data.getPokedex().getKeys(),e.getStat().getPokemon(),_data);
        }
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStatRank.getKeys(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStat.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getMovesEffectGlobal(),increasingMaxNbRoundGlobalMove.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroShorts(increasingMaxNbRoundGlobalMove.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getMovesEffectTeam(),increasingMaxNbRoundTeamMove.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroShorts(increasingMaxNbRoundTeamMove.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),typesPk,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),immuTypes,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),immuMoves,_data);
        DataInfoChecker.checkStringListContains(_data.getMovesEffectGlobalWeather(),immuWeather,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),boostStatisSuperEff.getKeys(),_data);
        DataInfoChecker.checkPositiveBytes(boostStatisSuperEff.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),boostStatisTypes.getKeys(),_data);
        for (IdMap<Statistic, Byte> t : boostStatisTypes.values()) {
            DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),t.getKeys(),_data);
            DataInfoChecker.checkPositiveBytes(t.values(),_data);
        }
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),immuStatus,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),synchroStatus,_data);
        DataInfoChecker.checkStringListContains(synchroStatus,failStatus.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTrappingMoves(),increasingMaxNbRoundTrap.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroShorts(increasingMaxNbRoundTrap.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),winEvFight.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroShorts(winEvFight.values(),_data);
        DataInfoChecker.checkPositiveOrZero(protectAgainstKo,_data);
        DataInfoChecker.checkPositiveOrZero(protectAgainstKoIfFullHp,_data);
        DataInfoChecker.checkPositiveOrZero(drainedHpByDamageRate,_data);
        DataInfoChecker.checkPositiveOrZero(multTrappingDamage,_data);
        DataInfoChecker.checkPositiveOrZero(multWinningHappiness,_data);
        DataInfoChecker.checkPositiveOrZero(multWinningEv,_data);
        DataInfoChecker.checkPositiveOrZero(multWinningExp,_data);
        DataInfoChecker.checkPositiveOrZero(multDrainedHp,_data);
        DataInfoChecker.checkPositiveOrZero(damageRecoil,_data);
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

    public IdMap<Statistic, Short> getWinEvFight() {
        return winEvFight;
    }

    public void setWinEvFight(IdMap<Statistic, Short> _winEvFight) {
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

    public IdMap<Statistic, Byte> getMultStatRank() {
        return multStatRank;
    }

    public void setMultStatRank(IdMap<Statistic, Byte> _multStat) {
        multStatRank = _multStat;
    }

    public StatisticPokemons getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public void setMultStatPokemonRank(
            StatisticPokemons _multStatPokemonRank) {
        multStatPokemonRank = _multStatPokemonRank;
    }

    public IdMap<Statistic, String> getMultStat() {
        return multStat;
    }

    public void setMultStat(IdMap<Statistic, String> _multStat) {
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

    public IdMap<Statistic, Byte> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public void setBoostStatisSuperEff(
            IdMap<Statistic, Byte> _boostStatisSuperEff) {
        boostStatisSuperEff = _boostStatisSuperEff;
    }

    public StringMap<IdMap<Statistic, Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public void setBoostStatisTypes(
            StringMap<IdMap<Statistic, Byte>> _boostStatisTypes) {
        boostStatisTypes = _boostStatisTypes;
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
}
