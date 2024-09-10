package aiki.beans.status;

import aiki.beans.WithFilterBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.status.Status;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.StringUtil;

public class StatusSetBean extends WithFilterBean {
    private AbsMap<String,Status> sortedStatus = new StringMap<Status>();

    @Override
    public void beforeDisplaying() {
        sortedStatus = getForms().getValStatusData(CST_STATUS_SET);
        escapeInputs();
    }
    public String search() {
        AbsMap<String,Status> sortedAbilities_;
        DataBase data_ = getDataBase();
        sortedAbilities_ = DictionaryComparatorUtil.buildStatusData(data_,getLanguage());
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        for (EntryCust<String, Status> i: data_.getStatus().entryList()) {
            String displayName_ = translationsStatus_.getVal(i.getKey());
            if (StringUtil.match(displayName_, getTypedStatus())) {
                sortedAbilities_.put(i.getKey(),i.getValue());
            }
        }
        getForms().putStatus(CST_STATUS_SET, sortedAbilities_);
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return tryRedirectSt(sortedAbilities_.firstKey());
        }
//        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        return PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public String clickStatus(int _index) {
        return tryRedirectSt(sortedStatus.getKey(_index));
    }
    public String getTrStatus(int _index) {
        String ability_ = sortedStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(ability_);
    }

    public CustList<String> getSortedStatus() {
        return sortedStatus.getKeys();
    }
}