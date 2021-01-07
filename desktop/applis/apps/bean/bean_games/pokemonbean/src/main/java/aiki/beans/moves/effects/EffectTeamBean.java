package aiki.beans.moves.effects;
import aiki.beans.facade.comparators.ComparatorCategoryMult;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.util.CategoryMult;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public class EffectTeamBean extends EffectBean {
    private boolean forbiddingHealing;
    private StringList forbiddenBoost;
    private StringList unusableMoves;
    private StringList cancelChgtStatFoeTeam;
    private StringList cancelChgtStatTeam;
    private TreeMap<CategoryMult, Rate> multDamage;
    private NatStringTreeMap< Rate> multStatistic;
    private NatStringTreeMap< Rate> multStatisticFoe;
    private StringList protectAgainstLowStat;
    private boolean protectAgainstCh;
    private StringList protectAgainstStatus;
    private StringList disableFoeTeamEffects;
    private StringList disableFoeTeamStatus;
    private int defaultBoost;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectTeam effect_ = (EffectTeam) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        forbiddingHealing = effect_.getForbiddingHealing();
        protectAgainstCh = effect_.getProtectAgainstCh();
        defaultBoost = data_.getDefaultBoost();
        StringList forbiddenBoost_;
        forbiddenBoost_ = new StringList();
        for (Statistic s: effect_.getForbiddenBoost()) {
            forbiddenBoost_.add(translatedStatistics_.getVal(s));
        }
        forbiddenBoost = forbiddenBoost_;
        StringList cancelChgtStatFoeTeam_;
        cancelChgtStatFoeTeam_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStatFoeTeam()) {
            cancelChgtStatFoeTeam_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStatFoeTeam = cancelChgtStatFoeTeam_;
        StringList cancelChgtStatTeam_;
        cancelChgtStatTeam_ = new StringList();
        for (Statistic s: effect_.getCancelChgtStatTeam()) {
            cancelChgtStatTeam_.add(translatedStatistics_.getVal(s));
        }
        cancelChgtStatTeam = cancelChgtStatTeam_;
        StringList protectAgainstLowStat_;
        protectAgainstLowStat_ = new StringList();
        for (Statistic s: effect_.getProtectAgainstLowStat()) {
            protectAgainstLowStat_.add(translatedStatistics_.getVal(s));
        }
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
        TreeMap<CategoryMult, Rate> multDamage_;
        multDamage_ = new TreeMap<CategoryMult, Rate>(new ComparatorCategoryMult());
        for (CategoryMult c: effect_.getMultDamage().getKeys()) {
            CategoryMult cat_ = new CategoryMult();
            cat_.setCategory(translatedCategories_.getVal(c.getCategory()));
            cat_.setMult(c.getMult());
            multDamage_.put(cat_, effect_.getMultDamage().getVal(c));
        }
        multDamage = multDamage_;
        StringList protectAgainstStatus_ = getProtectAgainstStatus(effect_);
        protectAgainstStatus = protectAgainstStatus_;
        StringList unusableMoves_;
        unusableMoves_ = new StringList();
        for (String m: effect_.getUnusableMoves()) {
            unusableMoves_.add(m);
        }
        unusableMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        unusableMoves = unusableMoves_;
        StringList disableFoeTeamEffects_;
        disableFoeTeamEffects_ = new StringList();
        for (String m: effect_.getDisableFoeTeamEffects()) {
            disableFoeTeamEffects_.add(m);
        }
        disableFoeTeamEffects_.sortElts(new ComparatorTrStrings(translatedMoves_));
        disableFoeTeamEffects = disableFoeTeamEffects_;
        StringList disableFoeTeamStatus_;
        disableFoeTeamStatus_ = new StringList();
        for (String m: effect_.getDisableFoeTeamStatus()) {
            disableFoeTeamStatus_.add(m);
        }
        disableFoeTeamStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        disableFoeTeamStatus = disableFoeTeamStatus_;
    }
    public String clickStatus(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        String st_ = getProtectAgainstStatus(effect_).get(_index);
        getForms().put(STATUS, st_);
        return STATUS;
    }

    private StringList getProtectAgainstStatus(EffectTeam _effect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringList protectAgainstStatus_;
        protectAgainstStatus_ = new StringList();
        for (String s: _effect.getProtectAgainstStatus()) {
            protectAgainstStatus_.add(s);
        }
        protectAgainstStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        return protectAgainstStatus_;
    }
    public String getTrStatus(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = protectAgainstStatus.get(_index);
        return translatedStatus_.getVal(st_);
    }
    public String clickUnusableMove(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList unusableMoves_;
        unusableMoves_ = new StringList();
        for (String m: effect_.getUnusableMoves()) {
            unusableMoves_.add(m);
        }
        unusableMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        String st_ = unusableMoves_.get(_index);
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrUnusableMove(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamEffects(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList disableFoeTeamEffects_;
        disableFoeTeamEffects_ = new StringList();
        for (String m: effect_.getDisableFoeTeamEffects()) {
            disableFoeTeamEffects_.add(m);
        }
        disableFoeTeamEffects_.sortElts(new ComparatorTrStrings(translatedMoves_));
        String st_ = disableFoeTeamEffects_.get(_index);
        getForms().put(MOVE, st_);
        return MOVE;
    }
    public String getTrDisableFoeTeamEffects(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = disableFoeTeamEffects.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickDisableFoeTeamStatus(int _indexEffect, int _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringList disableFoeTeamStatus_;
        disableFoeTeamStatus_ = new StringList();
        for (String m: effect_.getDisableFoeTeamStatus()) {
            disableFoeTeamStatus_.add(m);
        }
        disableFoeTeamStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        String st_ = disableFoeTeamStatus_.get(_index);
        getForms().put(STATUS, st_);
        return STATUS;
    }
    public String getTrDisableFoeTeamStatus(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = disableFoeTeamStatus.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public Listable<CategoryMult> getCatMult() {
        return multDamage.getKeys();
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

    public int getDefaultBoost() {
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

    public TreeMap<CategoryMult,Rate> getMultDamage() {
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