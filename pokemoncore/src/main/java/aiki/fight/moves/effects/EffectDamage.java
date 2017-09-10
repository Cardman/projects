package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.serialize.CheckedData;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class EffectDamage extends Effect {

    @CheckedData
    private byte chRate;

    @CheckedData
    private boolean constDamage;

    private MonteCarloString damageLaw;

    private StringMap<Rate> multDamageAgainst;

    private MonteCarloNumber chLaw;

//    private MonteCarloNumber powerLaw;

    private MonteCarloNumber hitsLaw;

    @CheckedData
    private String power;

    @CheckedData
    private boolean randMax;

    @CheckedData
    private boolean summingUserTeamOkFighter;

    private EnumList<Statistic> ignVarStatTargetPos;

    private EnumList<Statistic> ignVarStatUserNeg;

    @CheckedData
    private boolean userAttack;

    @CheckedData
    private Statistic statisAtt;

    @CheckedData
    private boolean targetDefense;

    @CheckedData
    private Statistic statisDef;

    private EnumMap<Statistic,Byte> boostStatisOnceKoFoe;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        chLaw.checkEvents();
        hitsLaw.checkEvents();
        damageLaw.checkEvents();
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.ALLIE) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.ALLIES) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.UNIQUE_IMPORTE) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.GLOBALE) {
            throw new DataException();
        }
        for (Statistic s: boostStatisOnceKoFoe.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
            if (boostStatisOnceKoFoe.getVal(s) < 0) {
                throw new DataException();
            }
        }
        if (!_data.getCategories().containsAllObj(multDamageAgainst.getKeys())) {
            throw new DataException();
        }
        if (!multDamageAgainst.isEmpty()) {
            for (EntryCust<String,Rate> e: multDamageAgainst.entryList()) {
                e.getValue().isZero();
            }
        }
        if (!chLaw.events().isEmpty()) {
            Rate min_ = chLaw.minimum();
            if (min_.lowerThanOne()) {
                throw new DataException();
            }
        }
        if (!hitsLaw.events().isEmpty()) {
            Rate min_ = hitsLaw.minimum();
            if (!min_.isZeroOrGt()) {
                throw new DataException();
            }
            if (min_.isZero()) {
                throw new DataException();
            }
            for (Rate e: hitsLaw.events()) {
                if (!e.isInteger()) {
                    throw new DataException();
                }
            }
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(ignVarStatTargetPos)) {
            throw new DataException();
        }
        if (!Statistic.getStatisticsWithBoost().containsAllObj(ignVarStatUserNeg)) {
            throw new DataException();
        }
        if (!statisAtt.isBoost()) {
            throw new DataException();
        }
        if (!statisAtt.isWithBaseStatistic()) {
            throw new DataException();
        }
        if (!statisDef.isBoost()) {
            throw new DataException();
        }
        if (!statisDef.isWithBaseStatistic()) {
            throw new DataException();
        }
        if (statisAtt != Statistic.ATTACK) {
            if (statisAtt != Statistic.SPECIAL_ATTACK) {
                throw new DataException();
            }
        }
        if (statisDef != Statistic.DEFENSE) {
            if (statisDef != Statistic.SPECIAL_DEFENSE) {
                throw new DataException();
            }
        }
        if (constDamage) {
            if (!Rate.isValid(power)) {
                throw new DataException();
            }
            return;
        }
        if (!multDamageAgainst.isEmpty()) {
            if (!damageLaw.events().isEmpty()) {
                throw new DataException();
            }
            if (!power.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (!damageLaw.events().isEmpty()) {
            if (!power.isEmpty()) {
                throw new DataException();
            }
            return;
        }
        if (power.isEmpty()) {
            throw new DataException();
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

//    public MonteCarloNumber getPowerLaw() {
//        return powerLaw;
//    }

//    public void setPowerLaw(MonteCarloNumber _power) {
//        powerLaw = _power;
//    }

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

    public EnumMap<Statistic,Byte> getBoostStatisOnceKoFoe() {
        return boostStatisOnceKoFoe;
    }

    public void setBoostStatisOnceKoFoe(EnumMap<Statistic,Byte> _boostStatisOnceKoFoe) {
        boostStatisOnceKoFoe = _boostStatisOnceKoFoe;
    }
}
