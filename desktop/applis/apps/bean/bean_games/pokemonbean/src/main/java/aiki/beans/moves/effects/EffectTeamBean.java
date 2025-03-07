package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.beans.abilities.TranslatedKeyPair;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.maths.*;
import code.scripts.pages.aiki.MessagesDataEffteam;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectTeamBean extends EffectBean {
    private boolean forbiddingHealing;
    private CustList<TranslatedKey> forbiddenBoost;
    private CustList<TranslatedKey> unusableMoves;
    private CustList<TranslatedKey> cancelChgtStatFoeTeam;
    private CustList<TranslatedKey> cancelChgtStatTeam;
    private DictionaryComparator<TranslatedKeyPair, Rate> multDamage;
    private DictionaryComparator<TranslatedKey, Rate> multStatistic;
    private DictionaryComparator<TranslatedKey, Rate> multStatisticFoe;
    private CustList<TranslatedKey> protectAgainstLowStat;
    private boolean protectAgainstCh;
    private CustList<TranslatedKey> protectAgainstStatus;
    private CustList<TranslatedKey> disableFoeTeamEffects;
    private CustList<TranslatedKey> disableFoeTeamStatus;
    private long defaultBoost;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectTeam effect_ = (EffectTeam) getEffect();
        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        forbiddingHealing = effect_.getForbiddingHealing();
        protectAgainstCh = effect_.getProtectAgainstCh();
        defaultBoost = data_.getDefaultBoost();
        CustList<TranslatedKey> forbiddenBoost_;
        forbiddenBoost_ = new CustList<TranslatedKey>();
        for (Statistic s: effect_.getForbiddenBoost()) {
            forbiddenBoost_.add(buildSi(getFacade(),s));
        }
        forbiddenBoost_.sortElts(new ComparingTranslatedKey());
        forbiddenBoost = forbiddenBoost_;
//        StringList cancelChgtStatFoeTeam_;
//        cancelChgtStatFoeTeam_ = new StringList();
//        for (Statistic s: effect_.getCancelChgtStatFoeTeam()) {
//            cancelChgtStatFoeTeam_.add(translatedStatistics_.getVal(s));
//        }
//        cancelChgtStatFoeTeam_.sort();
        cancelChgtStatFoeTeam = listTrStringsSi(effect_.getCancelChgtStatFoeTeam(),getFacade());
//        StringList cancelChgtStatTeam_;
//        cancelChgtStatTeam_ = new StringList();
//        for (Statistic s: effect_.getCancelChgtStatTeam()) {
//            cancelChgtStatTeam_.add(translatedStatistics_.getVal(s));
//        }
//        cancelChgtStatTeam_.sort();
        cancelChgtStatTeam = listTrStringsSi(effect_.getCancelChgtStatTeam(),getFacade());
//        StringList protectAgainstLowStat_;
//        protectAgainstLowStat_ = new StringList();
//        for (Statistic s: effect_.getProtectAgainstLowStat()) {
//            protectAgainstLowStat_.add(translatedStatistics_.getVal(s));
//        }
//        protectAgainstLowStat_.sort();
        protectAgainstLowStat = listTrStringsSi(effect_.getProtectAgainstLowStat(),getFacade());
        DictionaryComparator<TranslatedKey, Rate> multStatistic_;
        multStatistic_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (Statistic s: effect_.getMultStatistic().getKeys()) {
            multStatistic_.put(buildSi(getFacade(),s), effect_.getMultStatistic().getVal(s));
        }
        multStatistic = multStatistic_;
        DictionaryComparator<TranslatedKey, Rate> multStatisticFoe_;
        multStatisticFoe_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (Statistic s: effect_.getMultStatisticFoe().getKeys()) {
            multStatisticFoe_.put(buildSi(getFacade(),s), effect_.getMultStatisticFoe().getVal(s));
        }
        multStatisticFoe = multStatisticFoe_;
        DictionaryComparator<TranslatedKeyPair, Rate> multDamage_;
        multDamage_ = DictionaryComparatorUtil.buildCategoryMult();
        for (CategoryMult c: effect_.getMultDamage().getKeys()) {
            multDamage_.put(build(getFacade(), c), effect_.getMultDamage().getVal(c));
        }
        multDamage = multDamage_;
        protectAgainstStatus = listTrStringsSt(effect_.getProtectAgainstStatus(),getFacade());
        unusableMoves = listTrStringsMv(effect_.getUnusableMoves(),getFacade());
        disableFoeTeamEffects = listTrStringsMv(effect_.getDisableFoeTeamEffects(),getFacade());
        disableFoeTeamStatus = listTrStringsSt(effect_.getDisableFoeTeamStatus(),getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_EFFECT);
    }

    @Override
    public void buildSubEff() {
        displayBoolTrue(toInt(getForbiddingHealing()), MessagesPkBean.EFF_TEAM, MessagesDataEffteam.M_P_66_FORBID_HEAL);
        displayBoolTrue(toInt(getProtectAgainstCh()), MessagesPkBean.EFF_TEAM, MessagesDataEffteam.M_P_66_PROTECT_AG_CH);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getForbiddenBoost(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_FORBID_BOOST);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getCancelChgtStatFoeTeam(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_CANCEL_CHGT_STAT_FOE,Long.toString(getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getCancelChgtStatTeam(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_CANCEL_CHGT_STAT,Long.toString(getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getProtectAgainstLowStat(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_PROTECT_AG_LAW_STATIS);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getProtectAgainstStatus(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_PROTECT_AG_STATUS);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,getMultStatistic(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_MULT_STAT,MessagesDataEffteam.M_P_66_STATISTIC,MessagesDataEffteam.M_P_66_RATE);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,getMultStatisticFoe(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_MULT_STAT_FOE,MessagesDataEffteam.M_P_66_STATISTIC,MessagesDataEffteam.M_P_66_RATE);
        new BeanDisplayMap<TranslatedKeyPair,Rate>(new BeanDisplayTranslatedKeyPair(),new BeanDisplayRate()).displayGrid(this,getMultDamage(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_MULT_DAMAGE,MessagesDataEffteam.M_P_66_CAT,MessagesDataEffteam.M_P_66_MULT,MessagesDataEffteam.M_P_66_RATE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getUnusableMoves(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_FORBID_MOVE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getDisableFoeTeamEffects(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_DELETE_EFFECTS);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getDisableFoeTeamStatus(),MessagesPkBean.EFF_TEAM,MessagesDataEffteam.M_P_66_DELETE_STATUS);
    }

    public static TranslatedKeyPair build(FacadeGame _fac, CategoryMult _c) {
        return new TranslatedKeyPair(buildCa(_fac, _c.getCategory()), new TranslatedKey(Long.toString(_c.getMult()), Long.toString(_c.getMult())));
    }

    public String clickStatus(int _index) {
        return tryRedirect(protectAgainstStatus.get(_index));
    }

    public String getTrStatus(int _index) {
        return protectAgainstStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String st_ = protectAgainstStatus.get(_index);
//        return translatedStatus_.getVal(st_);
    }
    public String clickUnusableMove(int _index) {
        return tryRedirect(unusableMoves.get(_index));
    }
    public String getTrUnusableMove(int _index) {
        return unusableMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = unusableMoves.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamEffects(int _index) {
        return tryRedirect(disableFoeTeamEffects.get(_index));
    }
    public String getTrDisableFoeTeamEffects(int _index) {
        return disableFoeTeamEffects.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = disableFoeTeamEffects.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamStatus(int _index) {
        return tryRedirect(disableFoeTeamStatus.get(_index));
    }
    public String getTrDisableFoeTeamStatus(int _index) {
        return disableFoeTeamStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String st_ = disableFoeTeamStatus.get(_index);
//        return translatedMoves_.getVal(st_);
    }

    public boolean getForbiddingHealing() {
        return forbiddingHealing;
    }

    public boolean getProtectAgainstCh() {
        return protectAgainstCh;
    }

    public CustList<TranslatedKey> getForbiddenBoost() {
        return forbiddenBoost;
    }

    public CustList<TranslatedKey> getCancelChgtStatFoeTeam() {
        return cancelChgtStatFoeTeam;
    }

    public long getDefaultBoost() {
        return defaultBoost;
    }

    public CustList<TranslatedKey> getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }

    public CustList<TranslatedKey> getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }

    public CustList<TranslatedKey> getProtectAgainstStatus() {
        return protectAgainstStatus;
    }

    public DictionaryComparator<TranslatedKey, Rate> getMultStatistic() {
        return multStatistic;
    }

    public DictionaryComparator<TranslatedKey, Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getMultDamage() {
        return multDamage;
    }

    public CustList<TranslatedKey> getUnusableMoves() {
        return unusableMoves;
    }

    public CustList<TranslatedKey> getDisableFoeTeamEffects() {
        return disableFoeTeamEffects;
    }

    public CustList<TranslatedKey> getDisableFoeTeamStatus() {
        return disableFoeTeamStatus;
    }
}