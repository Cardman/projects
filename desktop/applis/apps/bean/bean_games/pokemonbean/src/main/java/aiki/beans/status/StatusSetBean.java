package aiki.beans.status;

import aiki.beans.*;
import aiki.beans.game.DifficultyBeanForm;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.fight.status.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class StatusSetBean extends WithFilterBean {
    private AbsMap<TranslatedKey,Status> sortedStatus = DictionaryComparatorUtil.buildStatusData();
    private IntBeanChgSubmit updateValues;
    public StatusSetBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataStatusset.M_P_89_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.STATUSSET,MessagesDataStatusset.M_P_89_INDEX);
//        initPage();
        initLine();
        formatMessage(MessagesPkBean.STATUSSET,MessagesDataStatusset.M_P_89_CONTENT);
        setTypedStatus(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedStatus().tryRet()));
        feedParents();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.STATUSSET,MessagesDataStatusset.M_P_89_OK));
        getUpdateValues().addEvt(new StatusSetBeanSearch(this));
        feedParents();
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getSortedStatus());
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.STATUSSET,MessagesDataStatusset.M_P_89_INDEX);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.STATUSSET).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        sortedStatus = getForms().getValStatusData(CST_STATUS_SET);
//        escapeInputs();
    }
    public String search() {
        AbsMap<TranslatedKey,Status> sortedAbilities_;
        DataBase data_ = getDataBase();
        sortedAbilities_ = DictionaryComparatorUtil.buildStatusData();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        for (EntryCust<String, Status> i: data_.getStatus().entryList()) {
            String displayName_ = translationsStatus_.getVal(i.getKey());
            if (StringUtil.match(displayName_, getTypedStatus().tryRet())) {
                sortedAbilities_.put(buildSt(getFacade(),i.getKey()),i.getValue());
            }
        }
        getForms().putStatus(CST_STATUS_SET, sortedAbilities_);
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return tryRedirect(sortedAbilities_.firstKey());
        }
//        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        return PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public String clickStatus(int _index) {
        return tryRedirect(sortedStatus.getKey(_index));
    }
    public String getTrStatus(int _index) {
        return sortedStatus.getKey(_index).getTranslation();
//        String ability_ = sortedStatus.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsStatus_;
//        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translationsStatus_.getVal(ability_);
    }

    public CustList<TranslatedKey> getSortedStatus() {
        return sortedStatus.getKeys();
    }
}