package aiki.beans.moves.effects;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStatisticTr;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import code.util.*;

public class EffectTeamWhileSendFoeBean extends EffectBean {
    private ShortTreeMap< String> statusByNbUses;
    private StringList deletedByFoeTypes;
    private String damageRateAgainstFoe;
    private TreeMap<Statistic,Byte> statistics;
    private StringList reasonsSending;
    private NatStringTreeMap<String> mapVarsFailSending;
    private NatStringTreeMap<String> mapVarsDamageSentFoe;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectTeamWhileSendFoe effect_ = (EffectTeamWhileSendFoe) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<Statistic,Byte> statistics_;
        statistics_ = new TreeMap<Statistic, Byte>(new ComparatorStatisticTr(data_, getLanguage()));
        for (Statistic s: effect_.getStatistics().getKeys()) {
            statistics_.put(s, effect_.getStatistics().getVal(s));
        }
        statistics = statistics_;
        StringList deletedByFoeTypes_;
        deletedByFoeTypes_ = new StringList();
        for (String t: effect_.getDeletedByFoeTypes()) {
            deletedByFoeTypes_.add(t);
        }
        deletedByFoeTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        deletedByFoeTypes = deletedByFoeTypes_;
        ShortTreeMap< String> statusByNbUses_;
        statusByNbUses_ = new ShortTreeMap< String>();
        for (Short s: effect_.getStatusByNbUses().getKeys()) {
            String status_ = effect_.getStatusByNbUses().getVal(s);
            statusByNbUses_.put(s, status_);
        }
        statusByNbUses = statusByNbUses_;
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasonsSending_ = CommonBean.getFormattedReasons(data_, getFailSendingReasons(), getLanguage());
//        reasonsSending_ = new StringList();
//        for (String f: getFailSendingReasons()) {
//            String formula_ = data_.getFormula(f, getLanguage());
////            formula_ = StringList.replace(formula_, locHtml_);
////            formula_ = formula_.replace(EAMP, E_AMP);
////            formula_ = formula_.replace(EGT, E_GT);
////            formula_ = formula_.replace(ELT, E_LT);
////            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            reasonsSending_.add(formula_);
//        }
        reasonsSending = reasonsSending_;
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getFailSending(),getLanguage());
        NatStringTreeMap<String> mapVarsFailSending_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFailSending_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailSending = mapVarsFailSending_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        damageRateAgainstFoe = data_.getFormula(effect_.getDamageRateAgainstFoe(),getLanguage());
//        damageRateAgainstFoe = StringList.replace(damageRateAgainstFoe, loc_);
//        damageRateAgainstFoe = damageRateAgainstFoe.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        damageRateAgainstFoe = damageRateAgainstFoe.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        mapVars_ = data_.getDescriptions(effect_.getDamageRateAgainstFoe(),getLanguage());
        NatStringTreeMap<String> mapVarsDamageSentFoe_ = new NatStringTreeMap<String>();
        desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsDamageSentFoe_.put(k, mapVars_.getVal(k));
        }
        mapVarsDamageSentFoe = mapVarsDamageSentFoe_;
    }
    public String getTranslatedStatistic(int _index) {
        Statistic st_ = getSortedStatistics().get(_index);
        DataBase data_ = (DataBase) getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public CustList<Statistic> getSortedStatistics() {
        IdList<Statistic> list_;
        list_ = new IdList<Statistic>(statistics.getKeys());
        DataBase data_ = (DataBase) getDataBase();
        list_.sortElts(new ComparatorStatisticTr(data_, getLanguage()));
        return list_;
    }
    public String getTranslatedType(int _index) {
        String type_ = deletedByFoeTypes.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String clickStatus(int _indexEffect, int _index) {
        ShortTreeMap< String> statusByNbUses_;
        statusByNbUses_ = new ShortTreeMap< String>();
        EffectTeamWhileSendFoe effect_ = (EffectTeamWhileSendFoe) getEffect(_indexEffect);
        for (Short s: effect_.getStatusByNbUses().getKeys()) {
            String status_ = effect_.getStatusByNbUses().getVal(s);
            statusByNbUses_.put(s, status_);
        }
        String status_ = statusByNbUses_.getValue(_index);
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }
    public String getTranslatedStatus(int _index) {
        String status_ = statusByNbUses.getValue(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(status_);
    }

    private StringList getFailSendingReasons() {
        EffectTeamWhileSendFoe effect_ = (EffectTeamWhileSendFoe) getEffect();
        return getReasons(effect_.getFailSending());
    }

    public String getDamageRateAgainstFoe() {
        return damageRateAgainstFoe;
    }

    public NatStringTreeMap<String> getMapVarsDamageSentFoe() {
        return mapVarsDamageSentFoe;
    }

    public TreeMap<Statistic,Byte> getStatistics() {
        return statistics;
    }

    public ShortTreeMap<String> getStatusByNbUses() {
        return statusByNbUses;
    }

    public StringList getReasonsSending() {
        return reasonsSending;
    }

    public NatStringTreeMap<String> getMapVarsFailSending() {
        return mapVarsFailSending;
    }

    public StringList getDeletedByFoeTypes() {
        return deletedByFoeTypes;
    }
}