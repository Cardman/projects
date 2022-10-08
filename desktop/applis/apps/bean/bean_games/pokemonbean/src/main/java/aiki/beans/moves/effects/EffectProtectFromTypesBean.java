package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import code.util.StringList;
import code.util.StringMap;

public class EffectProtectFromTypesBean extends EffectBean {
    private StringList immuAgainstTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectProtectFromTypes effect_ = (EffectProtectFromTypes) getEffect();
        DataBase data_ = getDataBase();
        StringList immuAgainstTypes_;
        immuAgainstTypes_ = new StringList();
        for (String type_: effect_.getImmuAgainstTypes()) {
            immuAgainstTypes_.add(type_);
        }
        immuAgainstTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        immuAgainstTypes = immuAgainstTypes_;
    }
    public String getTrType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = immuAgainstTypes.get(_index);
        return translatedTypes_.getVal(type_);
    }

    public StringList getImmuAgainstTypes() {
        return immuAgainstTypes;
    }
}