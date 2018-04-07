package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.util.StatisticPokemon;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
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
    private boolean repellingWildPk;
    private StringList synchroStatus;
    private StringMap<String> failStatus;
    private Rate protectAgainstKo;
    private Rate protectAgainstKoIfFullHp;
    private Rate drainedHpByDamageRate;
    private EnumMap<Statistic,Short> winEvFight;
    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningMoney;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private EnumMap<Statistic,Byte> multStatRank;

    private ObjectMap<StatisticPokemon,Byte> multStatPokemonRank;

    private EnumMap<Statistic,String> multStat;
    private StringMap<Short> increasingMaxNbRoundGlobalMove;
    private StringMap<Short> increasingMaxNbRoundTeamMove;
    private StringList immuMoves;
    private StringList hatching;
    private StringList immuTypes;
    private StringList immuWeather;
    private EnumMap<Statistic,Byte> boostStatisSuperEff;
    private StringMap<EnumMap<Statistic,Byte>> boostStatisTypes;
    private boolean detruitSiContact;
    private boolean switchPossibleSiTouche;
    private boolean switchForceAdvSiTouche;
    //private StringList sansEffetCapacite;
    private boolean transfertObjSiContact;
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
            throw new DataException();
        }
        hatching.retainAllElements(_data.getPokedex().getKeys());
        if (effectEndRound.size() > 1) {
            throw new DataException();
        }
        if (effectSending.size() > 1) {
            throw new DataException();
        }
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
            if (!(effectEndRound.first() instanceof EffectEndRoundIndividual)) {
                if (!(effectEndRound.first() instanceof EffectEndRoundTeam)) {
                    throw new DataException();
                }
            }
        }
        if (!effectSending.isEmpty()) {
            effectSending.first().validate(_data);
        }
        for (EntryCust<StatisticPokemon,Byte> e: multStatPokemonRank.entryList()) {
            if (!_data.getPokedex().contains(e.getKey().getPokemon())) {
                throw new DataException();
            }
            e.getValue().byteValue();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStatRank.getKeys())) {
            throw new DataException();
        }
        for (EntryCust<Statistic,Byte> e: multStatRank.entryList()) {
            e.getValue().byteValue();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(multStat.getKeys())) {
            throw new DataException();
        }
        if (!_data.getMovesEffectGlobal().containsAllObj(increasingMaxNbRoundGlobalMove.getKeys())) {
            throw new DataException();
        }
        for (short s: increasingMaxNbRoundGlobalMove.values()) {
            if (s < 0) {
                throw new DataException();
            }
        }
        if (!_data.getMovesEffectTeam().containsAllObj(increasingMaxNbRoundTeamMove.getKeys())) {
            throw new DataException();
        }
        for (short s: increasingMaxNbRoundTeamMove.values()) {
            if (s < 0) {
                throw new DataException();
            }
        }
        if (!_data.getTypes().containsAllObj(typesPk)) {
            throw new DataException();
        }
        if (!_data.getTypes().containsAllObj(immuTypes)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(immuMoves)) {
            throw new DataException();
        }
        if (!_data.getMovesEffectGlobalWeather().containsAllObj(immuWeather)) {
            throw new DataException();
        }
        for (EntryCust<Statistic,Byte> e: boostStatisSuperEff.entryList()) {
            if (!e.getKey().isBoost()) {
                throw new DataException();
            }
            e.getValue().byteValue();
        }
        for (String t: boostStatisTypes.getKeys()) {
            if (!_data.getTypes().containsObj(t)) {
                throw new DataException();
            }
            for (EntryCust<Statistic,Byte> s: boostStatisTypes.getVal(t).entryList()) {
                if (!s.getKey().isBoost()) {
                    throw new DataException();
                }
                s.getValue().byteValue();
            }
        }
        if (!_data.getStatus().containsAllAsKeys(immuStatus)) {
            throw new DataException();
        }
        if (!_data.getStatus().containsAllAsKeys(synchroStatus)) {
            throw new DataException();
        }
        if (!synchroStatus.containsAllObj(failStatus.getKeys())) {
            throw new DataException();
        }
        if (!_data.getTrappingMoves().containsAllObj(increasingMaxNbRoundTrap.getKeys())) {
            throw new DataException();
        }
        for (short s: increasingMaxNbRoundTrap.values()) {
            if (s < 0) {
                throw new DataException();
            }
        }
        if (!Statistic.getStatisticsWithBase().containsAllObj(winEvFight.getKeys())) {
            throw new DataException();
        }
        for (Statistic s: winEvFight.getKeys()) {
            if (winEvFight.getVal(s) < 0) {
                throw new DataException();
            }
        }
        if (!protectAgainstKo.isZeroOrGt()) {
            throw new DataException();
        }
        if (!protectAgainstKoIfFullHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!drainedHpByDamageRate.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multTrappingDamage.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multWinningMoney.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multWinningHappiness.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multWinningEv.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multWinningExp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!multDrainedHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!damageRecoil.isZeroOrGt()) {
            throw new DataException();
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
        if (!multWinningMoney.isZero()) {
            return true;
        }
        if (!multWinningHappiness.isZero()) {
            return true;
        }
        if (!multWinningEv.isZero()) {
            return true;
        }
        if (!multWinningExp.isZero()) {
            return true;
        }
        return false;
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
    public void setIncreasingMaxNbRoundTrap(StringMap<Short> _increasingMaxNbRoundTrap) {
        increasingMaxNbRoundTrap = _increasingMaxNbRoundTrap;
    }
    public boolean getAttacksSoon() {
        return attacksSoon;
    }
    public void setAttacksSoon(boolean _attacksSoon) {
        attacksSoon = _attacksSoon;
    }
    public boolean getRepellingWildPk() {
        return repellingWildPk;
    }
    public void setRepellingWildPk(boolean _repellingWildPk) {
        repellingWildPk = _repellingWildPk;
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
    public EnumMap<Statistic,Short> getWinEvFight() {
        return winEvFight;
    }
    public void setWinEvFight(EnumMap<Statistic,Short> _winEvFight) {
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
    public Rate getMultWinningMoney() {
        return multWinningMoney;
    }
    public void setMultWinningMoney(Rate _multWinningMoney) {
        multWinningMoney = _multWinningMoney;
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
    public EnumMap<Statistic,Byte> getMultStatRank() {
        return multStatRank;
    }
    public void setMultStatRank(EnumMap<Statistic,Byte> _multStat) {
        multStatRank = _multStat;
    }
    public ObjectMap<StatisticPokemon,Byte> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }
    public void setMultStatPokemonRank(ObjectMap<StatisticPokemon,Byte> _multStatPokemonRank) {
        multStatPokemonRank = _multStatPokemonRank;
    }

    public EnumMap<Statistic,String> getMultStat() {
        return multStat;
    }
    public void setMultStat(EnumMap<Statistic,String> _multStat) {
        multStat = _multStat;
    }
    public StringMap<Short> getIncreasingMaxNbRoundGlobalMove() {
        return increasingMaxNbRoundGlobalMove;
    }
    public void setIncreasingMaxNbRoundGlobalMove(StringMap<Short> _increasingMaxNbRoundGlobalMove) {
        increasingMaxNbRoundGlobalMove = _increasingMaxNbRoundGlobalMove;
    }
    public StringMap<Short> getIncreasingMaxNbRoundTeamMove() {
        return increasingMaxNbRoundTeamMove;
    }
    public void setIncreasingMaxNbRoundTeamMove(StringMap<Short> _increasingMaxNbRoundTeamMove) {
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


    public EnumMap<Statistic,Byte> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public void setBoostStatisSuperEff(EnumMap<Statistic,Byte> _boostStatisSuperEff) {
        boostStatisSuperEff = _boostStatisSuperEff;
    }

    public StringMap<EnumMap<Statistic,Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public void setBoostStatisTypes(
            StringMap<EnumMap<Statistic,Byte>> _boostStatisTypes) {
        boostStatisTypes = _boostStatisTypes;
    }

    public boolean getDetruitSiContact() {
        return detruitSiContact;
    }
    public void setDetruitSiContact(boolean _detruitSiContact) {
        detruitSiContact = _detruitSiContact;
    }
    public boolean getSwitchPossibleSiTouche() {
        return switchPossibleSiTouche;
    }
    public void setSwitchPossibleSiTouche(boolean _switchPossibleSiTouche) {
        switchPossibleSiTouche = _switchPossibleSiTouche;
    }
    public boolean getSwitchForceAdvSiTouche() {
        return switchForceAdvSiTouche;
    }
    public void setSwitchForceAdvSiTouche(boolean _switchForceAdvSiTouche) {
        switchForceAdvSiTouche = _switchForceAdvSiTouche;
    }
    public boolean getTransfertObjSiContact() {
        return transfertObjSiContact;
    }
    public void setTransfertObjSiContact(boolean _transfertObjSiContact) {
        transfertObjSiContact = _transfertObjSiContact;
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
