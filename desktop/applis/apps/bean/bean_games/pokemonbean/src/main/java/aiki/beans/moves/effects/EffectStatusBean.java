package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectStatus;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;

public class EffectStatusBean extends EffectBean {
    private DictionaryComparator<TranslatedKey, Rate> lawStatus;
    private CustList<TranslatedKey> deletedStatus;

    private StringMap< String> localFailStatus;
    private boolean koUserHealSubst;
    private boolean statusFromUser;
    private NatStringTreeMap<String> mapVarsStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatus effect_ = (EffectStatus) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, Rate> lawStatus_;
        lawStatus_ = DictionaryComparatorUtil.buildStatusRate();
        for (String s: effect_.getLawStatus().eventsDiff()) {
            lawStatus_.put(buildSt(getFacade(),s), effect_.getLawStatus().normalizedRate(s));
        }
        lawStatus = lawStatus_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringMap< String> localFailStatus_;
        localFailStatus_ = new StringMap<String>();
        NatStringTreeMap<String> mapVarsStatus_;
        mapVarsStatus_ = new NatStringTreeMap<String>();
        for (EntryCust<String, String> s: effect_.getLocalFailStatus().entryList()) {
            String formula_ = data_.getFormula(s.getValue(), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            localFailStatus_.put(s.getKey(), formula_);
            mapVarsStatus_.putAllMap(data_.getDescriptions(s.getValue(), getLanguage()));
        }
        mapVarsStatus = mapVarsStatus_;
        localFailStatus = localFailStatus_;
        deletedStatus = listTrStringsSt(effect_.getDeletedStatus(),getFacade());
        koUserHealSubst = effect_.getKoUserHealSubst();
        statusFromUser = effect_.getStatusFromUser();
    }
    public String clickLink(int _indexEffect, int _index) {
        return tryRedirect(((EffectStatusBean)getForms().getCurrentBean().get(_indexEffect)).lawStatus.getKey(_index));
    }
    public String getTrLink(int _index) {
        return lawStatus.getKey(_index).getTranslation();
//        String status_ = lawStatus.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return StringUtil.nullToEmpty(translatedStatus_.getVal(status_));
    }

    public String clickLinkDeleted(int _indexEffect, int _index) {
        return tryRedirect(((EffectStatusBean)getForms().getCurrentBean().get(_indexEffect)).deletedStatus.get(_index));
    }
    public String getTrLinkDeleted(int _index) {
        return deletedStatus.get(_index).getTranslation();
//        String status_ = deletedStatus.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(status_);
    }

    public boolean isStatus(int _index) {
        return !lawStatus.getKey(_index).getKey().isEmpty();
    }
    public String getFail(int _index) {
        String status_ = lawStatus.getKey(_index).getKey();
        return StringUtil.nullToEmpty(localFailStatus.getVal(status_));
    }

    public DictionaryComparator<TranslatedKey,Rate> getLawStatus() {
        return lawStatus;
    }

    public NatStringTreeMap<String> getMapVarsStatus() {
        return mapVarsStatus;
    }

    public CustList<TranslatedKey> getDeletedStatus() {
        return deletedStatus;
    }

    public boolean getKoUserHealSubst() {
        return koUserHealSubst;
    }

    public boolean getStatusFromUser() {
        return statusFromUser;
    }
}