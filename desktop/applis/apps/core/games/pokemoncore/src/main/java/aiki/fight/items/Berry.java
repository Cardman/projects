package aiki.fight.items;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public final class Berry extends Item {

    public static final String ITEM = "aiki.fight.items.Berry";

    private Rate healHpBySuperEffMove;
    private boolean lawForAttackFirst;

    private StringMap<EfficiencyRate> multFoesDamage;

    private AbsMap<Statistic, BoostHpRate> multStat;

    private boolean withoutFail;
    private int healPp;

    private Rate healHp;

    private Rate maxHpHealingHp;

    private StringList healStatus;

    private Rate healHpRate;

    private Rate maxHpHealingHpRate;

    private StringMap<Rate> damageRateRecoilFoe;

    private String categoryBoosting;

    private AbsMap<Statistic, Byte> boostStatis;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),healStatus,_data);
        CustList<String> keysMultFoes_ = multFoesDamage.getKeys();
        DataInfoChecker.checkStringListContains(_data.getTypes(), keysMultFoes_,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),multStat.getKeys(),_data);
        for (EfficiencyRate s : multFoesDamage.values()) {
            DataInfoChecker.checkPositiveOrZero(s.getEff(),_data);
            DataInfoChecker.checkPositiveOrZero(s.getHpRate(),_data);
        }
        for (BoostHpRate s : multStat.values()) {
            DataInfoChecker.checkPositiveOrZero(s.getHpRate(),_data);
            DataInfoChecker.checkPositiveOrZero(s.getBoost(),_data);
        }
        CustList<String> keysMultRecoilFoes_ = damageRateRecoilFoe.getKeys();
        DataInfoChecker.checkStringListContains(_data.getCategories(), keysMultRecoilFoes_,_data);
        DataInfoChecker.checkPositiveOrZeroRates(damageRateRecoilFoe.values(),_data);
        DataInfoChecker.checkPositiveOrZero(healHpBySuperEffMove,_data);
        DataInfoChecker.checkPositiveOrZero(healHp,_data);
        DataInfoChecker.checkPositiveOrZero(maxHpHealingHp,_data);
        DataInfoChecker.checkLowerOne(maxHpHealingHp,_data);
        DataInfoChecker.checkPositiveOrZero(healHpRate,_data);
        DataInfoChecker.checkPositiveOrZero(maxHpHealingHpRate,_data);
        DataInfoChecker.checkLowerOne(maxHpHealingHpRate,_data);
        DataInfoChecker.checkPositiveOrZero(healPp,_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getAllCategories(), categoryBoosting,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),boostStatis.getKeys(),_data);
        if (!healHpBySuperEffMove.isZero()) {
            DataInfoChecker.checkEmptyStringList(keysMultFoes_,_data);
            DataInfoChecker.checkEmptyStringList(keysMultRecoilFoes_,_data);
            DataInfoChecker.checkZero(healHp,_data);
            DataInfoChecker.checkZero(healHpRate,_data);
            return;
        }
        if (!healHp.isZero()) {
            DataInfoChecker.checkEmptyStringList(keysMultFoes_,_data);
            DataInfoChecker.checkEmptyStringList(keysMultRecoilFoes_,_data);
            DataInfoChecker.checkZero(healHpRate,_data);
            return;
        }
        if (!healHpRate.isZero()) {
            DataInfoChecker.checkEmptyStringList(keysMultFoes_,_data);
            DataInfoChecker.checkEmptyStringList(keysMultRecoilFoes_,_data);
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

    public AbsMap<Statistic, BoostHpRate> getMultStat() {
        return multStat;
    }

    public void setMultStat(AbsMap<Statistic, BoostHpRate> _multStat) {
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

    public AbsMap<Statistic, Byte> getBoostStatis() {
        return boostStatis;
    }

    public void setBoostStatis(AbsMap<Statistic, Byte> _boostStatis) {
        boostStatis = _boostStatis;
    }
}
