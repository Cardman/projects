package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import code.images.BaseSixtyFourUtil;
import code.util.AbsMap;
import code.util.StringList;

public class SelectPokemonBean extends CommonBean {

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        AbsMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        setBooleans(DictionaryComparatorUtil.buildBoolStr(data_, getLanguage()));
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            getBooleans().put(s.getBoolName(), translatedBooleans_.getVal(s));
        }
        StringList pokedex_ = getForms().getValList(CST_POKEMON_SET);
        setupPokedex(pokedex_);
    }
    public static String cancel() {
        return CST_POKEMON;
    }
    public String search() {
        StringList pokedex_ = pokedex();
        getForms().put(CST_POKEMON_SET, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_POKEMON_NAME_EDIT,pokedex_.first());
            return CST_POKEMON;
        }
        return CST_POKEMON_SET;
    }
    public String getMiniImage(int _number) {
        String name_ = getPokedex().get(_number).getName();
        DataBase data_ = getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public String clickLink(int _number) {
        getForms().put(CST_POKEMON_NAME_EDIT, getPokedex().get(_number).getName());
        return CST_POKEMON;
    }

}