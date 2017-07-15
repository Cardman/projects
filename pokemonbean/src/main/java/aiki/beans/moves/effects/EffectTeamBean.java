package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorCategoryMult;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.util.CategoryMult;

public class EffectTeamBean extends EffectBean {

    @Accessible
    private boolean forbiddingHealing;

    @Accessible
    private StringList forbiddenBoost;

    @Accessible
    private StringList unusableMoves;

    @Accessible
    private StringList cancelChgtStatFoeTeam;

    @Accessible
    private StringList cancelChgtStatTeam;

    @Accessible
    private TreeMap<CategoryMult, Rate> multDamage;

    @Accessible
    private NatTreeMap<String, Rate> multStatistic;

    @Accessible
    private NatTreeMap<String, Rate> multStatisticFoe;

    @Accessible
    private StringList protectAgainstLowStat;

    @Accessible
    private boolean protectAgainstCh;

    @Accessible
    private StringList protectAgainstStatus;

    @Accessible
    private StringList disableFoeTeamEffects;

    @Accessible
    private StringList disableFoeTeamStatus;

    @Accessible
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
        NatTreeMap<String, Rate> multStatistic_;
        multStatistic_ = new NatTreeMap<String, Rate>();
        for (Statistic s: effect_.getMultStatistic().getKeys()) {
            multStatistic_.put(translatedStatistics_.getVal(s), effect_.getMultStatistic().getVal(s));
        }
        multStatistic = multStatistic_;
        NatTreeMap<String, Rate> multStatisticFoe_;
        multStatisticFoe_ = new NatTreeMap<String, Rate>();
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

    @Accessible
    private String clickStatus(Long _indexEffect, Long _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect.intValue());
        String st_ = getProtectAgainstStatus(effect_).get(_index.intValue());
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

    @Accessible
    private String getTrStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = protectAgainstStatus.get(_index.intValue());
        return translatedStatus_.getVal(st_);
    }

    @Accessible
    private String clickUnusableMove(Long _indexEffect, Long _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList unusableMoves_;
        unusableMoves_ = new StringList();
        for (String m: effect_.getUnusableMoves()) {
            unusableMoves_.add(m);
        }
        unusableMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        String st_ = unusableMoves_.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrUnusableMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = unusableMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickDisableFoeTeamEffects(Long _indexEffect, Long _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList disableFoeTeamEffects_;
        disableFoeTeamEffects_ = new StringList();
        for (String m: effect_.getDisableFoeTeamEffects()) {
            disableFoeTeamEffects_.add(m);
        }
        disableFoeTeamEffects_.sortElts(new ComparatorTrStrings(translatedMoves_));
        String st_ = disableFoeTeamEffects_.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrDisableFoeTeamEffects(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = disableFoeTeamEffects.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickDisableFoeTeamStatus(Long _indexEffect, Long _index) {
        EffectTeam effect_ = (EffectTeam) getEffect(_indexEffect.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringList disableFoeTeamStatus_;
        disableFoeTeamStatus_ = new StringList();
        for (String m: effect_.getDisableFoeTeamStatus()) {
            disableFoeTeamStatus_.add(m);
        }
        disableFoeTeamStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        String st_ = disableFoeTeamStatus_.get(_index.intValue());
        getForms().put(STATUS, st_);
        return STATUS;
    }

    @Accessible
    private String getTrDisableFoeTeamStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedStatus().getVal(getLanguage());
        String st_ = disableFoeTeamStatus.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private Listable<CategoryMult> getCatMult() {
        return multDamage.getKeys();
    }
}
