package aiki.beans.items;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;

public class BerryBean extends ItemBean {

    @Accessible
    private Rate healHpBySuperEffMove;

    @Accessible
    private boolean lawForAttackFirst;

    @Accessible
    private TreeMap<String, EfficiencyRate> multFoesDamage;

    @Accessible
    private TreeMap<Statistic, BoostHpRate> multStat;

    @Accessible
    private boolean withoutFail;

    @Accessible
    private int healPp;

    @Accessible
    private Rate healHp;

    @Accessible
    private Rate maxHpHealingHp;

    @Accessible
    private StringList healStatus;

    @Accessible
    private Rate healHpRate;

    @Accessible
    private Rate maxHpHealingHpRate;

    @Accessible
    private TreeMap<String, Rate> damageRateRecoilFoe;

    @Accessible
    private String categoryBoosting;

    @Accessible
    private TreeMap<Statistic, Byte> boostStatis;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
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

    @Accessible
    private boolean isHealingPp() {
        return healPp > 0;
    }

    @Accessible
    private String getTrMultFoesDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = multFoesDamage.getKey(_index.intValue());
        return translatedTypes_.getVal(type_);
    }

    @Accessible
    private String getTrMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = multStat.getKey(_index.intValue());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrBoostStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic type_ = boostStatis.getKey(_index.intValue());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = healStatus.get(_index.intValue());
        return translatedStatus_.getVal(status_);
    }

    @Accessible
    private String clickStatus(Long _index) {
        String status_ = healStatus.get(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }

    @Accessible
    private String getTrCategRecoil(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        String status_ = damageRateRecoilFoe.getKey(_index.intValue());
        return translatedCategories_.getVal(status_);
    }
}
