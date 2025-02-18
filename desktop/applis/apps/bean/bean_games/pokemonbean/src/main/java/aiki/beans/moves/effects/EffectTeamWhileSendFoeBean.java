package aiki.beans.moves.effects;

import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import code.util.*;

public class EffectTeamWhileSendFoeBean extends EffectBean {
    private LongTreeMap< TranslatedKey> statusByNbUses;
    private CustList<TranslatedKey> deletedByFoeTypes;
    private String damageRateAgainstFoe;
    private DictionaryComparator<TranslatedKey,Long> statistics;
    private StringList reasonsSending;
    private NatStringTreeMap<String> mapVarsFailSending;
    private NatStringTreeMap<String> mapVarsDamageSentFoe;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectTeamWhileSendFoe effect_ = (EffectTeamWhileSendFoe) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey,Long> statistics_;
        statistics_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: effect_.getStatistics().getKeys()) {
            statistics_.put(buildSi(getFacade(),s), effect_.getStatistics().getVal(s));
        }
        statistics = statistics_;
//        StringList deletedByFoeTypes_;
//        deletedByFoeTypes_ = new StringList();
//        for (String t: effect_.getDeletedByFoeTypes()) {
//            deletedByFoeTypes_.add(t);
//        }
//        deletedByFoeTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        deletedByFoeTypes = listTrStringsTy(effect_.getDeletedByFoeTypes(),getFacade());
        statusByNbUses = statusByNbUses(effect_.getStatusByNbUses());
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
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
        reasonsSending = CommonBean.getFormattedReasons(data_, effect_.getFailSending(), getLanguage());
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

    private LongTreeMap<TranslatedKey> statusByNbUses(LongMap<String> _st) {
        LongTreeMap< TranslatedKey> statusByNbUses_;
        statusByNbUses_ = new LongTreeMap< TranslatedKey>();
        for (Long s: _st.getKeys()) {
            String status_ = _st.getVal(s);
            statusByNbUses_.put(s, buildSt(getFacade(),status_));
        }
        return statusByNbUses_;
    }

    public String getTranslatedStatistic(int _index) {
        return statistics.getKey(_index).getTranslation();
//        Statistic st_ = getSortedStatistics().get(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(st_);
    }
//    public CustList<Statistic> getSortedStatistics() {
//        IdList<Statistic> list_;
//        list_ = new IdList<Statistic>(statistics.getKeys());
//        DataBase data_ = getDataBase();
//        list_.sortElts(DictionaryComparatorUtil.cmpStatistic(data_,getLanguage()));
//        return list_;
//    }
    public String getTranslatedType(int _index) {
        return deletedByFoeTypes.get(_index).getTranslation();
//        String type_ = deletedByFoeTypes.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
    }
    public String clickStatus(int _indexEffect, int _index) {
        return tryRedirect(((EffectTeamWhileSendFoeBean)getForms().getCurrentBean().get(_indexEffect)).statusByNbUses.getValue(_index));
    }
    public String getTranslatedStatus(int _index) {
        return statusByNbUses.getValue(_index).getTranslation();
    }

    public String getDamageRateAgainstFoe() {
        return damageRateAgainstFoe;
    }

    public NatStringTreeMap<String> getMapVarsDamageSentFoe() {
        return mapVarsDamageSentFoe;
    }

    public DictionaryComparator<TranslatedKey,Long> getStatistics() {
        return statistics;
    }

    public LongTreeMap<TranslatedKey> getStatusByNbUses() {
        return statusByNbUses;
    }

    public StringList getReasonsSending() {
        return reasonsSending;
    }

    public NatStringTreeMap<String> getMapVarsFailSending() {
        return mapVarsFailSending;
    }

    public CustList<TranslatedKey> getDeletedByFoeTypes() {
        return deletedByFoeTypes;
    }
}