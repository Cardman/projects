package aiki.beans.items;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.StringList;
import code.util.StringMap;

public class BerryBean extends ItemBean {
    private Rate healHpBySuperEffMove;
    private boolean lawForAttackFirst;
    private DictionaryComparator<String, EfficiencyRate> multFoesDamage;
    private DictionaryComparator<Statistic, BoostHpRate> multStat;
    private boolean withoutFail;
    private long healPp;
    private Rate healHp;
    private Rate maxHpHealingHp;
    private StringList healStatus;
    private Rate healHpRate;
    private Rate maxHpHealingHpRate;
    private DictionaryComparator<String, Rate> damageRateRecoilFoe;
    private String categoryBoosting;
    private DictionaryComparator<Statistic, Long> boostStatis;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        Berry item_ = (Berry) getItem();
        healHpBySuperEffMove = item_.getHealHpBySuperEffMove();
        lawForAttackFirst = item_.getLawForAttackFirst();
        withoutFail = item_.getWithoutFail();
        healPp = item_.getHealPp();
        healHp = item_.getHealHp();
        maxHpHealingHp = item_.getMaxHpHealingHp();
        healHpRate = item_.getHealHpRate();
        maxHpHealingHpRate = item_.getMaxHpHealingHpRate();
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        if (!item_.getCategoryBoosting().isEmpty()) {
            categoryBoosting = translatedCategories_.getVal(item_.getCategoryBoosting());
        } else {
            categoryBoosting = DataBase.EMPTY_STRING;
        }
        DictionaryComparator<String, EfficiencyRate> multFoesDamage_;
        multFoesDamage_ = DictionaryComparatorUtil.buildTypesTypeEfficiencyRate(data_,getLanguage());
        for (String s: item_.getMultFoesDamage().getKeys()) {
            multFoesDamage_.put(s, item_.getMultFoesDamage().getVal(s));
        }
        multFoesDamage = multFoesDamage_;
        DictionaryComparator<Statistic, BoostHpRate> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisBoostHpRate(data_,getLanguage());
        for (Statistic s: item_.getMultStat().getKeys()) {
            multStat_.put(s, item_.getMultStat().getVal(s));
        }
        multStat = multStat_;
        StringList healStatus_;
        healStatus_ = new StringList();
        for (String s: item_.getHealStatus()) {
            healStatus_.add(s);
        }
        healStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        healStatus = healStatus_;
        DictionaryComparator<String, Rate> damageRateRecoilFoe_;
        damageRateRecoilFoe_ = DictionaryComparatorUtil.buildCatsRate(data_,getLanguage());
        for (String s: item_.getDamageRateRecoilFoe().getKeys()) {
            damageRateRecoilFoe_.put(s, item_.getDamageRateRecoilFoe().getVal(s));
        }
        damageRateRecoilFoe = damageRateRecoilFoe_;
        DictionaryComparator<Statistic, Long> boostStatis_;
        boostStatis_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: item_.getBoostStatis().getKeys()) {
            boostStatis_.put(s, item_.getBoostStatis().getVal(s));
        }
        boostStatis = boostStatis_;
    }
    public boolean isHealingPp() {
        return healPp > 0;
    }
    public String getTrMultFoesDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = multFoesDamage.getKey(_index);
        return translatedTypes_.getVal(type_);
    }
    public String getTrMultStat(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = multStat.getKey(_index);
        return translatedStatistics_.getVal(type_);
    }
    public String getTrBoostStat(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = boostStatis.getKey(_index);
        return translatedStatistics_.getVal(type_);
    }
    public String getTrStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = healStatus.get(_index);
        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        String status_ = healStatus.get(_index);
        return tryRedirectSt(status_);
    }
    public String getTrCategRecoil(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        String status_ = damageRateRecoilFoe.getKey(_index);
        return translatedCategories_.getVal(status_);
    }

    public Rate getHealHpBySuperEffMove() {
        return healHpBySuperEffMove;
    }

    public boolean getLawForAttackFirst() {
        return lawForAttackFirst;
    }

    public boolean getWithoutFail() {
        return withoutFail;
    }

    public long getHealPp() {
        return healPp;
    }

    public Rate getHealHp() {
        return healHp;
    }

    public Rate getMaxHpHealingHp() {
        return maxHpHealingHp;
    }

    public Rate getHealHpRate() {
        return healHpRate;
    }

    public Rate getMaxHpHealingHpRate() {
        return maxHpHealingHpRate;
    }

    public DictionaryComparator<String,EfficiencyRate> getMultFoesDamage() {
        return multFoesDamage;
    }

    public DictionaryComparator<Statistic,BoostHpRate> getMultStat() {
        return multStat;
    }

    public StringList getHealStatus() {
        return healStatus;
    }

    public DictionaryComparator<String,Rate> getDamageRateRecoilFoe() {
        return damageRateRecoilFoe;
    }

    public DictionaryComparator<Statistic,Long> getBoostStatis() {
        return boostStatis;
    }

    public String getCategoryBoosting() {
        return categoryBoosting;
    }
}