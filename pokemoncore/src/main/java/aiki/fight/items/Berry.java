package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class Berry extends Item {

    public static final String ITEM = "aiki.fight.items.Berry";

    @CheckedData
    private Rate healHpBySuperEffMove;
    @CheckedData
    private boolean lawForAttackFirst;

    private StringMap<EfficiencyRate> multFoesDamage;

    private EnumMap<Statistic,BoostHpRate> multStat;

    @CheckedData
    private boolean withoutFail;
    @CheckedData
    private int healPp;

    @CheckedData
    private Rate healHp;

    @CheckedData
    private Rate maxHpHealingHp;

    private StringList healStatus;

    @CheckedData
    private Rate healHpRate;

    @CheckedData
    private Rate maxHpHealingHpRate;

    private StringMap<Rate> damageRateRecoilFoe;

    private String categoryBoosting;

    private EnumMap<Statistic,Byte> boostStatis;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getStatus().containsAllAsKeys(healStatus)) {
            throw new DataException();
        }
        for (String s: multFoesDamage.getKeys()) {
            if (!_data.getTypes().containsObj(s)) {
                throw new DataException();
            }
            if (!multFoesDamage.getVal(s).getEff().isZeroOrGt()) {
                throw new DataException();
            }
            if (!multFoesDamage.getVal(s).getHpRate().isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (Statistic s: multStat.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
            if (!multStat.getVal(s).getHpRate().isZeroOrGt()) {
                throw new DataException();
            }
            if (multStat.getVal(s).getBoost() < 0) {
                throw new DataException();
            }
        }
        if (!_data.getCategories().containsAllObj(damageRateRecoilFoe.getKeys())) {
            throw new DataException();
        }
        for (Rate v: damageRateRecoilFoe.values()) {
            if (!v.isZeroOrGt()) {
                throw new DataException();
            }
        }
        if (!healHpBySuperEffMove.isZeroOrGt()) {
            throw new DataException();
        }
        if (!healHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!maxHpHealingHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (maxHpHealingHp.greaterOrEqualsOne()) {
            throw new DataException();
        }
        if (!healHpRate.isZeroOrGt()) {
            throw new DataException();
        }
        if (!maxHpHealingHpRate.isZeroOrGt()) {
            throw new DataException();
        }
        if (maxHpHealingHpRate.greaterOrEqualsOne()) {
            throw new DataException();
        }
        if (healPp < 0) {
            throw new DataException();
        }
        if (!categoryBoosting.isEmpty()) {
            if (!_data.getAllCategories().containsObj(categoryBoosting)) {
                throw new DataException();
            }
        }
        for (Statistic s: boostStatis.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
        }
        if (!healHpBySuperEffMove.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                throw new DataException();
            }
            if (!damageRateRecoilFoe.isEmpty()) {
                throw new DataException();
            }
            if (!healHp.isZero()) {
                throw new DataException();
            }
            if (!healHpRate.isZero()) {
                throw new DataException();
            }
            return;
        }
        if (!healHp.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                throw new DataException();
            }
            if (!damageRateRecoilFoe.isEmpty()) {
                throw new DataException();
            }
            if (!healHpRate.isZero()) {
                throw new DataException();
            }
            return;
        }
        if (!healHpRate.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                throw new DataException();
            }
            if (!damageRateRecoilFoe.isEmpty()) {
                throw new DataException();
            }
        }
    }

    public Rate getHealHpBySuperEffMove() {
        return healHpBySuperEffMove;
    }
    public void setHealHpBySuperEffMove(Rate _healHp) {
        healHpBySuperEffMove = _healHp;
    }
    public boolean getLawForAttackFirst() {
        return lawForAttackFirst;
    }
    public void setLawForAttackFirst(boolean _lawForAttackFirst) {
        lawForAttackFirst = _lawForAttackFirst;
    }
    public StringMap<EfficiencyRate> getMultFoesDamage() {
        return multFoesDamage;
    }
    public void setMultFoesDamage(StringMap<EfficiencyRate> _multFoesDamage) {
        multFoesDamage = _multFoesDamage;
    }
    public EnumMap<Statistic,BoostHpRate> getMultStat() {
        return multStat;
    }
    public void setMultStat(EnumMap<Statistic,BoostHpRate> _multStat) {
        multStat = _multStat;
    }

    public boolean getWithoutFail() {
        return withoutFail;
    }
    public void setWithoutFail(boolean _withoutFail) {
        withoutFail = _withoutFail;
    }
    public int getHealPp() {
        return healPp;
    }
    public void setHealPp(int _healPp) {
        healPp = _healPp;
    }

    public Rate getHealHp() {
        return healHp;
    }

    public void setHealHp(Rate _healHp) {
        healHp = _healHp;
    }

    public Rate getMaxHpHealingHp() {
        return maxHpHealingHp;
    }

    public void setMaxHpHealingHp(Rate _maxHpHealingHp) {
        maxHpHealingHp = _maxHpHealingHp;
    }

    public StringList getHealStatus() {
        return healStatus;
    }
    public void setHealStatus(StringList _healStatus) {
        healStatus = _healStatus;
    }

    public Rate getHealHpRate() {
        return healHpRate;
    }

    public void setHealHpRate(Rate _healHpRate) {
        healHpRate = _healHpRate;
    }

    public Rate getMaxHpHealingHpRate() {
        return maxHpHealingHpRate;
    }

    public void setMaxHpHealingHpRate(Rate _maxHpHealingHpRate) {
        maxHpHealingHpRate = _maxHpHealingHpRate;
    }

    public StringMap<Rate> getDamageRateRecoilFoe() {
        return damageRateRecoilFoe;
    }
    public void setDamageRateRecoilFoe(StringMap<Rate> _damageRateRecoilFoe) {
        damageRateRecoilFoe = _damageRateRecoilFoe;
    }

    public String getCategoryBoosting() {
        return categoryBoosting;
    }

    public void setCategoryBoosting(String _categoryBoosting) {
        categoryBoosting = _categoryBoosting;
    }

    public EnumMap<Statistic,Byte> getBoostStatis() {
        return boostStatis;
    }

    public void setBoostStatis(EnumMap<Statistic,Byte> _boostStatis) {
        boostStatis = _boostStatis;
    }
}
