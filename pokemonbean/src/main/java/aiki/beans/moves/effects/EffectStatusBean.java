package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectStatus;

public class EffectStatusBean extends EffectBean {

    @Accessible
    private TreeMap<String, Rate> lawStatus;

    @Accessible
    private StringList deletedStatus;

    private NatTreeMap<String, String> localFailStatus;

    @Accessible
    private boolean koUserHealSubst;

    @Accessible
    private boolean statusFromUser;

    @Accessible
    private NatTreeMap<String,String> mapVarsStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatus effect_ = (EffectStatus) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String, Rate> lawStatus_;
        lawStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        LgInt sum_ = effect_.getLawStatus().sum();
        for (String s: effect_.getLawStatus().events()) {
            lawStatus_.put(s, new Rate(effect_.getLawStatus().rate(s), sum_));
        }
        lawStatus = lawStatus_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatTreeMap<String, String> localFailStatus_;
        localFailStatus_ = new NatTreeMap<String,String>();
        NatTreeMap<String,String> mapVarsStatus_;
        mapVarsStatus_ = new NatTreeMap<String,String>();
        for (String s: effect_.getLocalFailStatus().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailStatus().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatus_.put(translatedStatus_.getVal(s), formula_);
            mapVarsStatus_.putAllTreeMap(data_.getDescriptions(effect_.getLocalFailStatus().getVal(s), getLanguage()));
        }
        mapVarsStatus = mapVarsStatus_;
        localFailStatus = localFailStatus_;
        StringList deletedStatus_;
        deletedStatus_ = new StringList();
        for (String s: effect_.getDeletedStatus()) {
            deletedStatus_.add(s);
        }
        deletedStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        deletedStatus = deletedStatus_;
        koUserHealSubst = effect_.getKoUserHealSubst();
        statusFromUser = effect_.getStatusFromUser();
    }

    @Accessible
    private String clickLink(Long _indexEffect, Long _index) {
        TreeMap<String, Rate> lawStatus_ = getLawStatus(_indexEffect);
        String status_ = lawStatus_.getKey(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }

    @Accessible
    private String getTrLink(Long _index) {
        String status_ = lawStatus.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(status_);
    }

    private TreeMap<String, Rate> getLawStatus(Long _indexEffect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EffectStatus effect_ = (EffectStatus) getEffect(_indexEffect.intValue());
        TreeMap<String, Rate> lawStatus_;
        lawStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        LgInt sum_ = effect_.getLawStatus().sum();
        for (String s: effect_.getLawStatus().events()) {
            lawStatus_.put(s, new Rate(effect_.getLawStatus().rate(s), sum_));
        }
        return lawStatus_;
    }

    @Accessible
    private String clickLinkDeleted(Long _indexEffect, Long _index) {
        StringList deletedStatus_ = getDeletedStatus(_indexEffect);
        String status_ = deletedStatus_.get(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }

    @Accessible
    private String getTrLinkDeleted(Long _index) {
        String status_ = deletedStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(status_);
    }

    private StringList getDeletedStatus(Long _indexEffect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EffectStatus effect_ = (EffectStatus) getEffect(_indexEffect.intValue());
        StringList deletedStatus_;
        deletedStatus_ = new StringList();
        for (String s: effect_.getDeletedStatus()) {
            deletedStatus_.add(s);
        }
        deletedStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        return deletedStatus_;
    }

    @Accessible
    private boolean isStatus(Long _index) {
        return !lawStatus.getKey(_index.intValue()).isEmpty();
    }

    @Accessible
    private String getFail(Long _index) {
        String status_ = lawStatus.getKey(_index.intValue());
        if (!localFailStatus.contains(status_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailStatus.getVal(status_);
    }
}
