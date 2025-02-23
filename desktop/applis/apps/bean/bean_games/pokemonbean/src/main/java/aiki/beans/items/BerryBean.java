package aiki.beans.items;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.util.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.NumberUtil;

public final class BerryBean extends ItemBean {
    private Rate healHpBySuperEffMove;
    private boolean lawForAttackFirst;
    private DictionaryComparator<TranslatedKey, EfficiencyRate> multFoesDamage;
    private DictionaryComparator<TranslatedKey, BoostHpRate> multStat;
    private boolean withoutFail;
    private long healPp;
    private Rate healHp;
    private Rate maxHpHealingHp;
    private CustList<TranslatedKey> healStatus;
    private Rate healHpRate;
    private Rate maxHpHealingHpRate;
    private DictionaryComparator<TranslatedKey, Rate> damageRateRecoilFoe;
    private TranslatedKey categoryBoosting;
    private DictionaryComparator<TranslatedKey, Long> boostStatis;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        displayIntDef(healHpBySuperEffMove,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_WIN_SUPER_EFF);
        displayBoolTrue(toInt(lawForAttackFirst),MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_SORTING_USERS);
        displayBoolTrue(toInt(withoutFail),MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_WITHOUT_FAIL);
        displayIntDef(healPp,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_HEAL_PP);
        displayHealHp(healHp,maxHpHealingHp,MessagesDataItemsBerry.M_P_17_HEAL_HP_TIME,MessagesDataItemsBerry.M_P_17_HEAL_HP_ONLY_ROUND_HEAL,MessagesDataItemsBerry.M_P_17_HEAL_HP);
        displayHealHp(healHpRate,maxHpHealingHpRate,MessagesDataItemsBerry.M_P_17_HEAL_HP_RATE_TIME,MessagesDataItemsBerry.M_P_17_HEAL_HP_RATE_ONLY_ROUND_HEAL,MessagesDataItemsBerry.M_P_17_HEAL_HP_RATE);
        new BeanDisplayMap<TranslatedKey,EfficiencyRate>(new BeanDisplayTranslatedKey(),new BeanDisplayEfficiencyRate()).displayGrid(this,multFoesDamage,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_MULT_DAMAGE_FOE,MessagesDataItemsBerry.M_P_17_MULT_DAMAGE_FOE_TYPE,MessagesDataItemsBerry.M_P_17_MULT_DAMAGE_FOE_EFF,MessagesDataItemsBerry.M_P_17_MULT_DAMAGE_FOE_RATE);
        new BeanDisplayMap<TranslatedKey,BoostHpRate>(new BeanDisplayTranslatedKey(),new BeanDisplayBoostHpRate()).displayGrid(this,multStat,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_MULT_STAT,MessagesDataItemsBerry.M_P_17_MULT_STAT_KEY,MessagesDataItemsBerry.M_P_17_MULT_STAT_HP,MessagesDataItemsBerry.M_P_17_MULT_STAT_BOOST);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,healStatus,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_HEAL_STATUS);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,damageRateRecoilFoe,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_RECOIL,MessagesDataItemsBerry.M_P_17_RECOIL_CAT,MessagesDataItemsBerry.M_P_17_RECOIL_HP);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,boostStatis,MessagesPkBean.IT_BERRY,MessagesDataItemsBerry.M_P_17_CATEGORY,MessagesDataItemsBerry.M_P_17_CATEGORY_STAT,MessagesDataItemsBerry.M_P_17_CATEGORY_BOOST);
    }
    private void displayHealHp(Rate _heal, Rate _max, String _gene, String _noMax, String _withMax) {
        displayBoolTrue(NumberUtil.abs(_heal.signumToLong()),MessagesPkBean.IT_BERRY,_gene);
        displayBoolTrue(NumberUtil.abs(_heal.signumToLong())*(1-NumberUtil.abs(_max.signumToLong())),MessagesPkBean.IT_BERRY,_noMax,_heal.toNumberString());
        displayBoolTrue(NumberUtil.abs(_heal.signumToLong())* NumberUtil.abs(_max.signumToLong()),MessagesPkBean.IT_BERRY,_withMax,_max.toNumberString(),_heal.toNumberString());
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        Berry item_ = (Berry) getItem();
        healHpBySuperEffMove = item_.getHealHpBySuperEffMove();
        lawForAttackFirst = item_.getLawForAttackFirst();
        withoutFail = item_.getWithoutFail();
        healPp = item_.getHealPp();
        healHp = item_.getHealHp();
        maxHpHealingHp = item_.getMaxHpHealingHp();
        healHpRate = item_.getHealHpRate();
        maxHpHealingHpRate = item_.getMaxHpHealingHpRate();
//        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        if (!item_.getCategoryBoosting().isEmpty()) {
            categoryBoosting = buildCa(getFacade(),item_.getCategoryBoosting());
        } else {
            categoryBoosting = new TranslatedKey(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        }
        DictionaryComparator<TranslatedKey, EfficiencyRate> multFoesDamage_;
        multFoesDamage_ = DictionaryComparatorUtil.buildTypesTypeEfficiencyRate();
        for (String s: item_.getMultFoesDamage().getKeys()) {
            multFoesDamage_.put(buildTy(getFacade(),s), item_.getMultFoesDamage().getVal(s));
        }
        multFoesDamage = multFoesDamage_;
        DictionaryComparator<TranslatedKey, BoostHpRate> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisBoostHpRate();
        for (Statistic s: item_.getMultStat().getKeys()) {
            multStat_.put(buildSi(getFacade(),s), item_.getMultStat().getVal(s));
        }
        multStat = multStat_;
        healStatus = listTrStringsSt(item_.getHealStatus(),getFacade());
        DictionaryComparator<TranslatedKey, Rate> damageRateRecoilFoe_;
        damageRateRecoilFoe_ = DictionaryComparatorUtil.buildCatsRate();
        for (String s: item_.getDamageRateRecoilFoe().getKeys()) {
            damageRateRecoilFoe_.put(buildCa(getFacade(),s), item_.getDamageRateRecoilFoe().getVal(s));
        }
        damageRateRecoilFoe = damageRateRecoilFoe_;
        DictionaryComparator<TranslatedKey, Long> boostStatis_;
        boostStatis_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: item_.getBoostStatis().getKeys()) {
            boostStatis_.put(buildSi(getFacade(),s), item_.getBoostStatis().getVal(s));
        }
        boostStatis = boostStatis_;
    }
    public boolean isHealingPp() {
        return healPp > 0;
    }
    public String getTrMultFoesDamage(int _index) {
        return multFoesDamage.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = multFoesDamage.getKey(_index);
//        return translatedTypes_.getVal(type_);
    }
    public String getTrMultStat(int _index) {
        return multStat.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        Statistic type_ = multStat.getKey(_index);
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrBoostStat(int _index) {
        return boostStatis.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        Statistic type_ = boostStatis.getKey(_index);
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrStatus(int _index) {
        return healStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String status_ = healStatus.get(_index);
//        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        return tryRedirect(healStatus.get(_index));
    }
    public String getTrCategRecoil(int _index) {
        return damageRateRecoilFoe.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
//        String status_ = damageRateRecoilFoe.getKey(_index);
//        return translatedCategories_.getVal(status_);
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

    public DictionaryComparator<TranslatedKey,EfficiencyRate> getMultFoesDamage() {
        return multFoesDamage;
    }

    public DictionaryComparator<TranslatedKey,BoostHpRate> getMultStat() {
        return multStat;
    }

    public CustList<TranslatedKey> getHealStatus() {
        return healStatus;
    }

    public DictionaryComparator<TranslatedKey,Rate> getDamageRateRecoilFoe() {
        return damageRateRecoilFoe;
    }

    public DictionaryComparator<TranslatedKey,Long> getBoostStatis() {
        return boostStatis;
    }

    public TranslatedKey getCategoryBoosting() {
        return categoryBoosting;
    }
}