package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.util.CategoryMult;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectTeamBean extends EffectBean {
    private boolean forbiddingHealing;
    private StringList forbiddenBoost;
    private StringList unusableMoves;
    private StringList cancelChgtStatFoeTeam;
    private StringList cancelChgtStatTeam;
    private DictionaryComparator<CategoryMult, Rate> multDamage;
    private NatStringTreeMap< Rate> multStatistic;
    private NatStringTreeMap< Rate> multStatisticFoe;
    private StringList protectAgainstLowStat;
    private boolean protectAgainstCh;
    private StringList protectAgainstStatus;
    private StringList disableFoeTeamEffects;
    private StringList disableFoeTeamStatus;
    private long defaultBoost;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectTeam effect_ = (EffectTeam) getEffect();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        forbiddingHealing = effect_.getForbiddingHealing();
        protectAgainstCh = effect_.getProtectAgainstCh();
        defaultBoost = data_.getDefaultBoost();
        StringList forbiddenBoost_;
        forbiddenBoost_ = new StringList();
        for (Statistic s: effect_.getForbiddenBoost()) {
            forbiddenBoost_.add(translatedStatistics_.getVal(s));
        }
        forbiddenBoost_.sort();
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
        DictionaryComparator<CategoryMult, Rate> multDamage_;
        multDamage_ = DictionaryComparatorUtil.buildCategoryMult();
        for (CategoryMult c: effect_.getMultDamage().getKeys()) {
            CategoryMult cat_ = new CategoryMult();
            cat_.setCategory(translatedCategories_.getVal(c.getCategory()));
            cat_.setMult(c.getMult());
            multDamage_.put(cat_, effect_.getMultDamage().getVal(c));
        }
        multDamage = multDamage_;
        protectAgainstStatus = getProtectAgainstStatus(effect_);
        unusableMoves = unusableMoves(effect_);
        disableFoeTeamEffects = disableFoeTeamEffects(effect_);
        disableFoeTeamStatus = disableFoeTeamStatus(effect_);
    }

    private StringList unusableMoves(EffectTeam _effect) {
        DataBase data_ = getDataBase();
        StringList unusableMoves_;
        unusableMoves_ = new StringList();
        for (String m: _effect.getUnusableMoves()) {
            unusableMoves_.add(m);
        }
        unusableMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return unusableMoves_;
    }

    private StringList disableFoeTeamEffects(EffectTeam _effect) {
        DataBase data_ = getDataBase();
        StringList disableFoeTeamEffects_;
        disableFoeTeamEffects_ = new StringList();
        for (String m: _effect.getDisableFoeTeamEffects()) {
            disableFoeTeamEffects_.add(m);
        }
        disableFoeTeamEffects_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return disableFoeTeamEffects_;
    }

    private StringList disableFoeTeamStatus(EffectTeam _effect) {
        DataBase data_ = getDataBase();
        StringList disableFoeTeamStatus_;
        disableFoeTeamStatus_ = new StringList();
        for (String m: _effect.getDisableFoeTeamStatus()) {
            disableFoeTeamStatus_.add(m);
        }
        disableFoeTeamStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        return disableFoeTeamStatus_;
    }

    public String clickStatus(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        String st_ = getProtectAgainstStatus(effect_).get(_index);
        return tryRedirectSt(st_);
    }

    private StringList getProtectAgainstStatus(EffectTeam _effect) {
        DataBase data_ = getDataBase();
        StringList protectAgainstStatus_;
        protectAgainstStatus_ = new StringList();
        for (String s: _effect.getProtectAgainstStatus()) {
            protectAgainstStatus_.add(s);
        }
        protectAgainstStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        return protectAgainstStatus_;
    }
    public String getTrStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = protectAgainstStatus.get(_index);
        return translatedStatus_.getVal(st_);
    }
    public String clickUnusableMove(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        StringList unusableMoves_ = unusableMoves(effect_);
        String st_ = unusableMoves_.get(_index);
        return tryRedirectMv(st_);
    }
    public String getTrUnusableMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamEffects(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        StringList disableFoeTeamEffects_ = disableFoeTeamEffects(effect_);
        String st_ = disableFoeTeamEffects_.get(_index);
        return tryRedirectMv(st_);
    }
    public String getTrDisableFoeTeamEffects(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = disableFoeTeamEffects.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamStatus(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        StringList disableFoeTeamStatus_ = disableFoeTeamStatus(effect_);
        String st_ = disableFoeTeamStatus_.get(_index);
        return tryRedirectSt(st_);
    }
    public String getTrDisableFoeTeamStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = disableFoeTeamStatus.get(_index);
        return translatedMoves_.getVal(st_);
    }

    public boolean getForbiddingHealing() {
        return forbiddingHealing;
    }

    public boolean getProtectAgainstCh() {
        return protectAgainstCh;
    }

    public StringList getForbiddenBoost() {
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

    public StringList getProtectAgainstStatus() {
        return protectAgainstStatus;
    }

    public NatStringTreeMap<Rate> getMultStatistic() {
        return multStatistic;
    }

    public NatStringTreeMap<Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public DictionaryComparator<CategoryMult,Rate> getMultDamage() {
        return multDamage;
    }

    public StringList getUnusableMoves() {
        return unusableMoves;
    }

    public StringList getDisableFoeTeamEffects() {
        return disableFoeTeamEffects;
    }

    public StringList getDisableFoeTeamStatus() {
        return disableFoeTeamStatus;
    }
}