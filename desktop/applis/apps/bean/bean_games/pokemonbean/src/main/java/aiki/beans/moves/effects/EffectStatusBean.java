package aiki.beans.moves.effects;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectStatus;
import code.maths.Rate;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectStatusBean extends EffectBean {
    private TreeMap<String, Rate> lawStatus;
    private StringList deletedStatus;

    private NatStringTreeMap< String> localFailStatus;
    private boolean koUserHealSubst;
    private boolean statusFromUser;
    private NatStringTreeMap<String> mapVarsStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatus effect_ = (EffectStatus) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String, Rate> lawStatus_;
        lawStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getLawStatus().events()) {
            lawStatus_.put(s, effect_.getLawStatus().normalizedRate(s));
        }
        lawStatus = lawStatus_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        NatStringTreeMap< String> localFailStatus_;
        localFailStatus_ = new NatStringTreeMap<String>();
        NatStringTreeMap<String> mapVarsStatus_;
        mapVarsStatus_ = new NatStringTreeMap<String>();
        for (String s: effect_.getLocalFailStatus().getKeys()) {
            String formula_ = data_.getFormula(effect_.getLocalFailStatus().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatus_.put(translatedStatus_.getVal(s), formula_);
            mapVarsStatus_.putAllMap(data_.getDescriptions(effect_.getLocalFailStatus().getVal(s), getLanguage()));
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
    public String clickLink(int _indexEffect, int _index) {
        TreeMap<String, Rate> lawStatus_ = getLawStatus(_indexEffect);
        String status_ = lawStatus_.getKey(_index);
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }
    public String getTrLink(int _index) {
        String status_ = lawStatus.getKey(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(status_);
    }

    private TreeMap<String, Rate> getLawStatus(int _indexEffect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EffectStatus effect_ = (EffectStatus) getEffect(_indexEffect);
        TreeMap<String, Rate> lawStatus_;
        lawStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getLawStatus().events()) {
            lawStatus_.put(s, effect_.getLawStatus().normalizedRate(s));
        }
        return lawStatus_;
    }
    public String clickLinkDeleted(int _indexEffect, int _index) {
        StringList deletedStatus_ = getDeletedStatus(_indexEffect);
        String status_ = deletedStatus_.get(_index);
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }
    public String getTrLinkDeleted(int _index) {
        String status_ = deletedStatus.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(status_);
    }

    private StringList getDeletedStatus(int _indexEffect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EffectStatus effect_ = (EffectStatus) getEffect(_indexEffect);
        StringList deletedStatus_;
        deletedStatus_ = new StringList();
        for (String s: effect_.getDeletedStatus()) {
            deletedStatus_.add(s);
        }
        deletedStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        return deletedStatus_;
    }
    public boolean isStatus(int _index) {
        return !lawStatus.getKey(_index).isEmpty();
    }
    public String getFail(int _index) {
        String status_ = lawStatus.getKey(_index);
        if (!localFailStatus.contains(status_)) {
            return DataBase.EMPTY_STRING;
        }
        return localFailStatus.getVal(status_);
    }

    public TreeMap<String,Rate> getLawStatus() {
        return lawStatus;
    }

    public NatStringTreeMap<String> getMapVarsStatus() {
        return mapVarsStatus;
    }

    public StringList getDeletedStatus() {
        return deletedStatus;
    }

    public boolean getKoUserHealSubst() {
        return koUserHealSubst;
    }

    public boolean getStatusFromUser() {
        return statusFromUser;
    }
}