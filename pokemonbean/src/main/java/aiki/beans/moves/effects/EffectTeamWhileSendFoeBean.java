package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorStatisticTr;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public class EffectTeamWhileSendFoeBean extends EffectBean {
    private NatTreeMap<Short, String> statusByNbUses;
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
        NatTreeMap<Short, String> statusByNbUses_;
        statusByNbUses_ = new NatTreeMap<Short, String>();
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
    public String getTranslatedStatistic(Long _index) {
        Statistic st_ = getSortedStatistics().get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
    public Listable<Statistic> getSortedStatistics() {
        EnumList<Statistic> list_;
        list_ = new EnumList<Statistic>(statistics.getKeys());
        DataBase data_ = (DataBase) getDataBase();
        list_.sortElts(new ComparatorStatisticTr(data_, getLanguage()));
        return list_;
    }
    public String getTranslatedType(Long _index) {
        String type_ = deletedByFoeTypes.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String clickStatus(Long _indexEffect, Long _index) {
        NatTreeMap<Short, String> statusByNbUses_;
        statusByNbUses_ = new NatTreeMap<Short, String>();
        EffectTeamWhileSendFoe effect_ = (EffectTeamWhileSendFoe) getEffect(_indexEffect.intValue());
        for (Short s: effect_.getStatusByNbUses().getKeys()) {
            String status_ = effect_.getStatusByNbUses().getVal(s);
            statusByNbUses_.put(s, status_);
        }
        String status_ = statusByNbUses_.getValue(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }
    public String getTranslatedStatus(Long _index) {
        String status_ = statusByNbUses.getValue(_index.intValue());
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

    public NatTreeMap<Short,String> getStatusByNbUses() {
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