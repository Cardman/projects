package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.scripts.pages.aiki.MessagesDataEffstatus;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.*;

public class EffectStatusBean extends EffectBean {
    private DictionaryComparator<TranslatedKey,StatRankRate> lawStatus;
//    private DictionaryComparator<TranslatedKey, Rate> lawStatus;
    private CustList<TranslatedKey> deletedStatus;

//    private StringMap< String> localFailStatus;
    private boolean koUserHealSubst;
    private boolean statusFromUser;
    private NatStringTreeMap<String> mapVarsStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatus effect_ = (EffectStatus) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, StatRankRate> lawStatus_;
        lawStatus_ = new DictionaryComparator<TranslatedKey,StatRankRate>(new ComparingTranslatedKey());
        for (String s: effect_.getLawStatus().eventsDiff()) {
            String formula_ = data_.getFormula(StringUtil.nullToEmpty(effect_.getLocalFailStatus().getVal(s)), getLanguage());
            lawStatus_.put(buildSt(getFacade(),s), new StatRankRate(new IdMap<Statistic, Long>(),Statistic.NOTHING,formula_,effect_.getLawStatus().normalizedRate(s)));
        }
        lawStatus = lawStatus_;
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//        StringMap< String> localFailStatus_;
//        localFailStatus_ = new StringMap<String>();
        NatStringTreeMap<String> mapVarsStatus_;
        mapVarsStatus_ = new NatStringTreeMap<String>();
        for (EntryCust<String, String> s: effect_.getLocalFailStatus().entryList()) {
//            String formula_ = data_.getFormula(s.getValue(), getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            localFailStatus_.put(s.getKey(), formula_);
            mapVarsStatus_.putAllMap(data_.getDescriptions(s.getValue(), getLanguage()));
        }
        mapVarsStatus = mapVarsStatus_;
//        localFailStatus = localFailStatus_;
        deletedStatus = listTrStringsSt(effect_.getDeletedStatus(),getFacade());
        koUserHealSubst = effect_.getKoUserHealSubst();
        statusFromUser = effect_.getStatusFromUser();
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_EFFECT);
    }

    @Override
    public void buildSubEff() {
        if (!getLawStatus().isEmpty()) {
            new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(MessagesPkBean.EFF_STATUS, MessagesDataEffstatus.M_P_59_OTHER_STATUS),new BeanDisplayStatRankRate(false, true)).displayGrid(this,getLawStatus(),MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_LAW_STATUS,MessagesDataEffstatus.M_P_59_STATUS,MessagesDataEffstatus.M_P_59_FAIL,MessagesDataEffstatus.M_P_59_RATE_EVENT);
            mapVarsInit(getMapVarsStatus());
        }
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, getDeletedStatus(), MessagesPkBean.EFF_STATUS, MessagesDataEffstatus.M_P_59_DELETED_STATUS);
        displayBoolTrue(toInt(getKoUserHealSubst()), MessagesPkBean.EFF_STATUS, MessagesDataEffstatus.M_P_59_KO_USER);
        displayBoolTrue(toInt(getStatusFromUser()), MessagesPkBean.EFF_STATUS, MessagesDataEffstatus.M_P_59_FORWARD);
    }

    public String clickLink(int _index) {
        return tryRedirect(lawStatus.getKey(_index));
    }
    public String getTrLink(int _index) {
        return lawStatus.getKey(_index).getTranslation();
//        String status_ = lawStatus.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return StringUtil.nullToEmpty(translatedStatus_.getVal(status_));
    }

    public String clickLinkDeleted(int _index) {
        return tryRedirect(deletedStatus.get(_index));
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
        return lawStatus.getValue(_index).getFail();
//        String status_ = lawStatus.getKey(_index).getKey();
//        return StringUtil.nullToEmpty(localFailStatus.getVal(status_));
    }

    public DictionaryComparator<TranslatedKey,StatRankRate> getLawStatus() {
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