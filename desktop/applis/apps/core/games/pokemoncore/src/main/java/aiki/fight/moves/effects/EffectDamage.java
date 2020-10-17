package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.EnumList;
import code.util.EnumMap;
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

    private EnumList<Statistic> ignVarStatTargetPos;

    private EnumList<Statistic> ignVarStatUserNeg;

    private boolean userAttack;

    private Statistic statisAtt;

    private boolean targetDefense;

    private Statistic statisDef;

    private EnumMap<Statistic, Byte> boostStatisOnceKoFoe;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!chLaw.checkEvents()) {
            _data.setError(true);
        }
        if (!hitsLaw.checkEvents()) {
            _data.setError(true);
        }
        if (!damageLaw.checkEvents()) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.ALLIE) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.ALLIES) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.UNIQUE_IMPORTE) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.GLOBALE) {
            _data.setError(true);
        }
        for (Statistic s : boostStatisOnceKoFoe.getKeys()) {
            if (!s.isBoost()) {
                _data.setError(true);
            }
            if (boostStatisOnceKoFoe.getVal(s) < 0) {
                _data.setError(true);
            }
        }
        if (!_data.getCategories().containsAllObj(multDamageAgainst.getKeys())) {
            _data.setError(true);
        }
        if (!chLaw.events().isEmpty()) {
            Rate min_ = chLaw.minimum();
            if (min_.lowerThanOne()) {
                _data.setError(true);
            }
        } else {
            chLaw.addQuickEvent(Rate.one(),LgInt.one());
        }
        if (!hitsLaw.events().isEmpty()) {
            Rate min_ = hitsLaw.minimum();
            if (!min_.isZeroOrGt()) {
                _data.setError(true);
            }
            if (min_.isZero()) {
                _data.setError(true);
            }
            for (Rate e : hitsLaw.events()) {
                if (!e.isInteger()) {
                    _data.setError(true);
                }
            }
        } else {
            hitsLaw.addQuickEvent(Rate.one(),LgInt.one());
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                ignVarStatTargetPos)) {
            _data.setError(true);
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(
                ignVarStatUserNeg)) {
            _data.setError(true);
        }
        if (statisAtt != Statistic.ATTACK) {
            if (statisAtt != Statistic.SPECIAL_ATTACK) {
                _data.setError(true);
            }
        }
        if (statisDef != Statistic.DEFENSE) {
            if (statisDef != Statistic.SPECIAL_DEFENSE) {
                _data.setError(true);
            }
        }
        if (constDamage) {
            if (!Rate.isValid(power)) {
                _data.setError(true);
            }
            return;
        }
        if (!multDamageAgainst.isEmpty()) {
            if (!damageLaw.events().isEmpty()) {
                _data.setError(true);
            }
            if (!power.isEmpty()) {
                _data.setError(true);
            }
            return;
        }
        if (!damageLaw.events().isEmpty()) {
            if (!power.isEmpty()) {
                _data.setError(true);
            }
            return;
        }
        if (power.isEmpty()) {
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

    public EnumList<Statistic> getIgnVarStatTargetPos() {
        return ignVarStatTargetPos;
    }

    public void setIgnVarStatTargetPos(EnumList<Statistic> _ignVarStatTargetPos) {
        ignVarStatTargetPos = _ignVarStatTargetPos;
    }

    public EnumList<Statistic> getIgnVarStatUserNeg() {
        return ignVarStatUserNeg;
    }

    public void setIgnVarStatUserNeg(EnumList<Statistic> _ignVarStatUserNeg) {
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

    public EnumMap<Statistic, Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public void setBoostStatisOnceKoFoe(
            EnumMap<Statistic, Byte> _boostStatisOnceKoFoe) {
        boostStatisOnceKoFoe = _boostStatisOnceKoFoe;
    }
}
