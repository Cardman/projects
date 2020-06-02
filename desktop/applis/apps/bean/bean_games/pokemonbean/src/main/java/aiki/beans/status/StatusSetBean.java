package aiki.beans.status;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;

public class StatusSetBean extends CommonBean {
    private StringList sortedStatus = new StringList();
    private String typedStatus = DataBase.EMPTY_STRING;

    @Override
    public void beforeDisplaying() {
        sortedStatus = (StringList) getForms().getVal(STATUS_SET);
        typedStatus = escapedStringQuote(typedStatus);
    }
    public String search() {
        StringList sortedAbilities_;
        sortedAbilities_ = new StringList();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        for (String i: data_.getStatus().getKeys()) {
            String displayName_ = translationsStatus_.getVal(i);
            if (!StringList.match(displayName_, typedStatus)) {
                continue;
            }
            sortedAbilities_.add(i);
        }
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(STATUS, sortedAbilities_.first());
            return STATUS;
        }
        sortedAbilities_.sortElts(new ComparatorTrStrings(translationsStatus_));
        getForms().put(STATUS_SET, sortedAbilities_);
        return STATUS_SET;
    }
    public String clickStatus(Long _index) {
        getForms().put(STATUS, sortedStatus.get(_index.intValue()));
        return STATUS;
    }
    public String getTrStatus(Long _index) {
        String ability_ = sortedStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translationsStatus_.getVal(ability_);
    }

    public void setTypedStatus(String _typedStatus) {
        typedStatus = _typedStatus;
    }

    public String getTypedStatus() {
        return typedStatus;
    }

    public StringList getSortedStatus() {
        return sortedStatus;
    }
}