package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;


public final class EffectDamage extends Effect {

    private byte chRate;

    private boolean constDamage;

    private MonteCarloString damageLaw;

    private StringMap<Rate> multDamageAgainst;

    private MonteCarloNumber chLaw;

    // private MonteCarloNumber powerLaw;

    private MonteCarloNumber hitsLaw;

    private String power;

    private boolean randMax;

    private boolean summingUserTeamOkFighter;

    private IdList<Statistic> ignVarStatTargetPos;

    private IdList<Statistic> ignVarStatUserNeg;

    private boolean userAttack;

    private Statistic statisAtt;

    private boolean targetDefense;

    private Statistic statisDef;

    private IdMap<Statistic, Byte> boostStatisOnceKoFoe;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkEvents(chLaw,_data);
        DataInfoChecker.checkEvents(hitsLaw,_data);
        DataInfoChecker.checkEvents(damageLaw,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkTargetNot(TargetChoice.ALLIE,getTargetChoice(),_data);
        DataInfoChecker.checkTargetNot(TargetChoice.ALLIES,getTargetChoice(),_data);
        DataInfoChecker.checkTargetNot(TargetChoice.UNIQUE_IMPORTE,getTargetChoice(),_data);
        DataInfoChecker.checkTargetNot(TargetChoice.GLOBALE,getTargetChoice(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),boostStatisOnceKoFoe.getKeys(),_data);
        DataInfoChecker.checkPositiveBytes(boostStatisOnceKoFoe.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getCategories(),multDamageAgainst.getKeys(),_data);
        DataInfoChecker.checkPositiveRates(multDamageAgainst.values(),_data);
        patch();
        if (chLaw.minimum().lowerThanOne()) {
            _data.setError(true);
        }
        Rate min_ = hitsLaw.minimum();
        DataInfoChecker.checkPositive(min_,_data);
        DataInfoChecker.checkIntegers(hitsLaw.events(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),ignVarStatTargetPos,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),ignVarStatUserNeg,_data);
        DataInfoChecker.checkStatistics(Statistic.ATTACK,Statistic.SPECIAL_ATTACK,statisAtt,_data);
        DataInfoChecker.checkStatistics(Statistic.DEFENSE,Statistic.SPECIAL_DEFENSE,statisDef,_data);
        if (constDamage) {
            checkConstDamage(_data);
            return;
        }
        if (!multDamageAgainst.isEmpty()) {
            DataInfoChecker.checkEmptyStringList(damageLaw.events(),_data);
            DataInfoChecker.checkEmptyString(power,_data);
            return;
        }
        if (!damageLaw.events().isEmpty()) {
            DataInfoChecker.checkEmptyString(power,_data);
            return;
        }
        DataInfoChecker.checkEmptyNotString(power,_data);
    }
    public void patch() {
        if (chLaw.nbEvents() == 0) {
            chLaw.addQuickEvent(Rate.one(),LgInt.one());
        }
        if (hitsLaw.nbEvents() == 0) {
            hitsLaw.addQuickEvent(Rate.one(),LgInt.one());
        }
    }

    private void checkConstDamage(DataBase _data) {
        if (!Rate.isValid(power)) {
            _data.setError(true);
        }
    }

    public byte getChRate() {
        return chRate;
    }

    public void setChRate(byte _chRate) {
        chRate = _chRate;
    }

    public boolean getConstDamage() {
        return constDamage;
    }

    public void setConstDamage(boolean _constDamage) {
        constDamage = _constDamage;
    }

    public MonteCarloString getDamageLaw() {
        return damageLaw;
    }

    public void setDamageLaw(MonteCarloString _damageLaw) {
        damageLaw = _damageLaw;
    }

    public StringMap<Rate> getMultDamageAgainst() {
        return multDamageAgainst;
    }

    public void setMultDamageAgainst(StringMap<Rate> _multDamageAgainst) {
        multDamageAgainst = _multDamageAgainst;
    }

    public MonteCarloNumber getChLaw() {
        return chLaw;
    }

    public void setChLaw(MonteCarloNumber _chLaw) {
        chLaw = _chLaw;
    }

    // public MonteCarloNumber getPowerLaw() {
    // return powerLaw;
    // }

    // public void setPowerLaw(MonteCarloNumber _power) {
    // powerLaw = _power;
    // }

    public MonteCarloNumber getHitsLaw() {
        return hitsLaw;
    }

    public void setHitsLaw(MonteCarloNumber _hitsLaw) {
        hitsLaw = _hitsLaw;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String _power) {
        power = _power;
    }

    public boolean getRandMax() {
        return randMax;
    }

    public void setRandMax(boolean _randMax) {
        randMax = _randMax;
    }

    public boolean getSummingUserTeamOkFighter() {
        return summingUserTeamOkFighter;
    }

    public void setSummingUserTeamOkFighter(boolean _summingUserTeamOkFighter) {
        summingUserTeamOkFighter = _summingUserTeamOkFighter;
    }

    public IdList<Statistic> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public void setIgnVarStatTargetPos(IdList<Statistic> _ignVarStatTargetPos) {
        ignVarStatTargetPos = _ignVarStatTargetPos;
    }

    public IdList<Statistic> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public void setIgnVarStatUserNeg(IdList<Statistic> _ignVarStatUserNeg) {
        ignVarStatUserNeg = _ignVarStatUserNeg;
    }

    public boolean isUserAttack() {
        return userAttack;
    }

    public void setUserAttack(boolean _userAttack) {
        userAttack = _userAttack;
    }

    public Statistic getStatisAtt() {
        return statisAtt;
    }

    public void setStatisAtt(Statistic _statisAtt) {
        statisAtt = _statisAtt;
    }

    public boolean isTargetDefense() {
        return targetDefense;
    }

    public void setTargetDefense(boolean _targetDefense) {
        targetDefense = _targetDefense;
    }

    public Statistic getStatisDef() {
        return statisDef;
    }

    public void setStatisDef(Statistic _statisDef) {
        statisDef = _statisDef;
    }

    public IdMap<Statistic, Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public void setBoostStatisOnceKoFoe(
            IdMap<Statistic, Byte> _boostStatisOnceKoFoe) {
        boostStatisOnceKoFoe = _boostStatisOnceKoFoe;
    }
}
