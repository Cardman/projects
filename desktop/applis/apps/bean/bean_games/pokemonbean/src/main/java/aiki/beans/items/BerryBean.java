package aiki.beans.items;
import aiki.beans.facade.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class BerryBean extends ItemBean {
    private Rate healHpBySuperEffMove;
    private boolean lawForAttackFirst;
    private TreeMap<String, EfficiencyRate> multFoesDamage;
    private TreeMap<Statistic, BoostHpRate> multStat;
    private boolean withoutFail;
    private int healPp;
    private Rate healHp;
    private Rate maxHpHealingHp;
    private StringList healStatus;
    private Rate healHpRate;
    private Rate maxHpHealingHpRate;
    private TreeMap<String, Rate> damageRateRecoilFoe;
    private String categoryBoosting;
    private TreeMap<Statistic, Byte> boostStatis;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = (DataBase) getDataBase();
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
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        if (!item_.getCategoryBoosting().isEmpty()) {
            categoryBoosting = translatedCategories_.getVal(item_.getCategoryBoosting());
        } else {
            categoryBoosting = DataBase.EMPTY_STRING;
        }
        TreeMap<String, EfficiencyRate> multFoesDamage_;
        multFoesDamage_ = new TreeMap<String, EfficiencyRate>(new ComparatorTrStrings(translatedTypes_));
        for (String s: item_.getMultFoesDamage().getKeys()) {
            multFoesDamage_.put(s, item_.getMultFoesDamage().getVal(s));
        }
        multFoesDamage = multFoesDamage_;
        TreeMap<Statistic, BoostHpRate> multStat_;
        multStat_ = new TreeMap<Statistic, BoostHpRate>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getMultStat().getKeys()) {
            multStat_.put(s, item_.getMultStat().getVal(s));
        }
        multStat = multStat_;
        StringList healStatus_;
        healStatus_ = new StringList();
        for (String s: item_.getHealStatus()) {
            healStatus_.add(s);
        }
        healStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        healStatus = healStatus_;
        TreeMap<String, Rate> damageRateRecoilFoe_;
        damageRateRecoilFoe_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedCategories_));
        for (String s: item_.getDamageRateRecoilFoe().getKeys()) {
            damageRateRecoilFoe_.put(s, item_.getDamageRateRecoilFoe().getVal(s));
        }
        damageRateRecoilFoe = damageRateRecoilFoe_;
        TreeMap<Statistic, Byte> boostStatis_;
        boostStatis_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getBoostStatis().getKeys()) {
            boostStatis_.put(s, item_.getBoostStatis().getVal(s));
        }
        boostStatis = boostStatis_;
    }
    public boolean isHealingPp() {
        return healPp > 0;
    }
    public String getTrMultFoesDamage(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = multFoesDamage.getKey(_index);
        return translatedTypes_.getVal(type_);
    }
    public String getTrMultStat(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = multStat.getKey(_index);
        return translatedStatistics_.getVal(type_);
    }
    public String getTrBoostStat(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = boostStatis.getKey(_index);
        return translatedStatistics_.getVal(type_);
    }
    public String getTrStatus(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = healStatus.get(_index);
        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        String status_ = healStatus.get(_index);
        getForms().put(STATUS, status_);
        return STATUS;
    }
    public String getTrCategRecoil(int _index) {
        DataBase data_ = (DataBase) getDataBase();
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

    public int getHealPp() {
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

    public TreeMap<String,EfficiencyRate> getMultFoesDamage() {
        return multFoesDamage;
    }

    public TreeMap<Statistic,BoostHpRate> getMultStat() {
        return multStat;
    }

    public StringList getHealStatus() {
        return healStatus;
    }

    public TreeMap<String,Rate> getDamageRateRecoilFoe() {
        return damageRateRecoilFoe;
    }

    public TreeMap<Statistic,Byte> getBoostStatis() {
        return boostStatis;
    }

    public String getCategoryBoosting() {
        return categoryBoosting;
    }
}