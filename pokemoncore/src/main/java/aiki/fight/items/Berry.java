package aiki.fight.items;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class Berry extends Item {

    public static final String ITEM = "aiki.fight.items.Berry";

    private Rate healHpBySuperEffMove;
    private boolean lawForAttackFirst;

    private StringMap<EfficiencyRate> multFoesDamage;

    private EnumMap<Statistic, BoostHpRate> multStat;

    private boolean withoutFail;
    private int healPp;

    private Rate healHp;

    private Rate maxHpHealingHp;

    private StringList healStatus;

    private Rate healHpRate;

    private Rate maxHpHealingHpRate;

    private StringMap<Rate> damageRateRecoilFoe;

    private String categoryBoosting;

    private EnumMap<Statistic, Byte> boostStatis;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getStatus().containsAllAsKeys(healStatus)) {
            _data.setError(true);
            return;

        }
        for (String s : multFoesDamage.getKeys()) {
            if (!_data.getTypes().containsObj(s)) {
                _data.setError(true);
                return;

            }
            if (!multFoesDamage.getVal(s).getEff().isZeroOrGt()) {
                _data.setError(true);
                return;

            }
            if (!multFoesDamage.getVal(s).getHpRate().isZeroOrGt()) {
                _data.setError(true);
                return;

            }
        }
        for (Statistic s : multStat.getKeys()) {
            if (!s.isBoost()) {
                _data.setError(true);
                return;

            }
            if (!multStat.getVal(s).getHpRate().isZeroOrGt()) {
                _data.setError(true);
                return;

            }
            if (multStat.getVal(s).getBoost() < 0) {
                _data.setError(true);
                return;

            }
        }
        if (!_data.getCategories()
                .containsAllObj(damageRateRecoilFoe.getKeys())) {
            _data.setError(true);
            return;

        }
        for (Rate v : damageRateRecoilFoe.values()) {
            if (!v.isZeroOrGt()) {
                _data.setError(true);
                return;

            }
        }
        if (!healHpBySuperEffMove.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!healHp.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!maxHpHealingHp.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (maxHpHealingHp.greaterOrEqualsOne()) {
            _data.setError(true);
            return;

        }
        if (!healHpRate.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!maxHpHealingHpRate.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (maxHpHealingHpRate.greaterOrEqualsOne()) {
            _data.setError(true);
            return;

        }
        if (healPp < 0) {
            _data.setError(true);
            return;

        }
        if (!categoryBoosting.isEmpty()) {
            if (!_data.getAllCategories().containsObj(categoryBoosting)) {
                _data.setError(true);
                return;

            }
        }
        for (EntryCust<Statistic, Byte> e : boostStatis.entryList()) {
            if (!e.getKey().isBoost()) {
                _data.setError(true);
                return;

            }
            e.getValue().byteValue();
        }
        if (!healHpBySuperEffMove.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!damageRateRecoilFoe.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!healHp.isZero()) {
                _data.setError(true);
                return;

            }
            if (!healHpRate.isZero()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!healHp.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!damageRateRecoilFoe.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!healHpRate.isZero()) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (!healHpRate.isZero()) {
            if (!multFoesDamage.isEmpty()) {
                _data.setError(true);
                return;

            }
            if (!damageRateRecoilFoe.isEmpty()) {
                _data.setError(true);
                return;

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

    public EnumMap<Statistic, BoostHpRate> getMultStat() {
        return multStat;
    }

    public void setMultStat(EnumMap<Statistic, BoostHpRate> _multStat) {
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

    public EnumMap<Statistic, Byte> getBoostStatis() {
        return boostStatis;
    }

    public void setBoostStatis(EnumMap<Statistic, Byte> _boostStatis) {
        boostStatis = _boostStatis;
    }
}
