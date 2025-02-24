package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.fight.moves.effects.*;
import code.scripts.pages.aiki.MessagesDataEffprotectfromtypes;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectProtectFromTypesBean extends EffectBean {
    private CustList<TranslatedKey> immuAgainstTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectProtectFromTypes effect_ = (EffectProtectFromTypes) getEffect();
        immuAgainstTypes = listTrStringsTy(effect_.getImmuAgainstTypes(),getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_PROTECTFROMTYPES,MessagesDataEffprotectfromtypes.M_P_54_EFFECT);
    }

    @Override
    public void buildSubEff() {
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getImmuAgainstTypes(), MessagesPkBean.EFF_PROTECTFROMTYPES, MessagesDataEffprotectfromtypes.M_P_54_IMMU_MOVE_TYPES);
    }

    public String getTrType(int _index) {
        return immuAgainstTypes.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = immuAgainstTypes.get(_index);
//        return translatedTypes_.getVal(type_);
    }

    public CustList<TranslatedKey> getImmuAgainstTypes() {
        return immuAgainstTypes;
    }
}