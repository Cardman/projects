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
import code.util.*;

public class EffectTeamBean extends EffectBean {
    private boolean forbiddingHealing;
    private CustList<TranslatedKey> forbiddenBoost;
    private CustList<TranslatedKey> unusableMoves;
    private StringList cancelChgtStatFoeTeam;
    private StringList cancelChgtStatTeam;
    private DictionaryComparator<TranslatedKeyPair, Rate> multDamage;
    private NatStringTreeMap< Rate> multStatistic;
    private NatStringTreeMap< Rate> multStatisticFoe;
    private StringList protectAgainstLowStat;
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
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
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
        StringList cancelChgtStatFoeTeam_;
        cancelChgtStatFoeTeam_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStatFoeTeam()) {
            cancelChgtStatFoeTeam_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStatFoeTeam_.sort();
        cancelChgtStatFoeTeam = cancelChgtStatFoeTeam_;
        StringList cancelChgtStatTeam_;
        cancelChgtStatTeam_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStatTeam()) {
            cancelChgtStatTeam_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStatTeam_.sort();
        cancelChgtStatTeam = cancelChgtStatTeam_;
        StringList protectAgainstLowStat_;
        protectAgainstLowStat_ = new StringList();
        for (Statistic s: effect_.getProtectAgainstLowStat()) {
            protectAgainstLowStat_.add(translatedStatistics_.getVal(s));
        }
        protectAgainstLowStat_.sort();
        protectAgainstLowStat = protectAgainstLowStat_;
        NatStringTreeMap< Rate> multStatistic_;
        multStatistic_ = new NatStringTreeMap< Rate>();
        for (Statistic s: effect_.getMultStatistic().getKeys()) {
            multStatistic_.put(translatedStatistics_.getVal(s), effect_.getMultStatistic().getVal(s));
        }
        multStatistic = multStatistic_;
        NatStringTreeMap< Rate> multStatisticFoe_;
        multStatisticFoe_ = new NatStringTreeMap< Rate>();
        for (Statistic s: effect_.getMultStatisticFoe().getKeys()) {
            multStatisticFoe_.put(translatedStatistics_.getVal(s), effect_.getMultStatisticFoe().getVal(s));
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

    public static TranslatedKeyPair build(FacadeGame _fac, CategoryMult _c) {
        return new TranslatedKeyPair(buildCa(_fac, _c.getCategory()), new TranslatedKey(Long.toString(_c.getMult()), Long.toString(_c.getMult())));
    }

    public String clickStatus(int _indexEffect, int _index) {
        return tryRedirect(((EffectTeamBean)getForms().getCurrentBean().get(_indexEffect)).protectAgainstStatus.get(_index));
    }

    public String getTrStatus(int _index) {
        return protectAgainstStatus.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String st_ = protectAgainstStatus.get(_index);
//        return translatedStatus_.getVal(st_);
    }
    public String clickUnusableMove(int _indexEffect, int _index) {
        return tryRedirect(((EffectTeamBean)getForms().getCurrentBean().get(_indexEffect)).unusableMoves.get(_index));
    }
    public String getTrUnusableMove(int _index) {
        return unusableMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = unusableMoves.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamEffects(int _indexEffect, int _index) {
        return tryRedirect(((EffectTeamBean)getForms().getCurrentBean().get(_indexEffect)).disableFoeTeamEffects.get(_index));
    }
    public String getTrDisableFoeTeamEffects(int _index) {
        return disableFoeTeamEffects.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = disableFoeTeamEffects.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamStatus(int _indexEffect, int _index) {
        return tryRedirect(((EffectTeamBean)getForms().getCurrentBean().get(_indexEffect)).disableFoeTeamStatus.get(_index));
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

    public StringList getCancelChgtStatFoeTeam() {
        return cancelChgtStatFoeTeam;
    }

    public long getDefaultBoost() {
        return defaultBoost;
    }

    public StringList getCancelChgtStatTeam() {
        return cancelChgtStatTeam;
    }

    public StringList getProtectAgainstLowStat() {
        return protectAgainstLowStat;
    }

    public CustList<TranslatedKey> getProtectAgainstStatus() {
        return protectAgainstStatus;
    }

    public NatStringTreeMap<Rate> getMultStatistic() {
        return multStatistic;
    }

    public NatStringTreeMap<Rate> getMultStatisticFoe() {
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