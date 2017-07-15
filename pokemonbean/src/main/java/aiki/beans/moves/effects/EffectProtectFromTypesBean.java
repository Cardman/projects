package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectProtectFromTypes;

public class EffectProtectFromTypesBean extends EffectBean {

    @Accessible
    private StringList immuAgainstTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectProtectFromTypes effect_ = (EffectProtectFromTypes) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList immuAgainstTypes_;
        immuAgainstTypes_ = new StringList();
        for (String type_: effect_.getImmuAgainstTypes()) {
            immuAgainstTypes_.add(type_);
        }
        immuAgainstTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        immuAgainstTypes = immuAgainstTypes_;
    }

    @Accessible
    private String getTrType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = immuAgainstTypes.get(_index.intValue());
        return translatedTypes_.getVal(type_);
    }
}
