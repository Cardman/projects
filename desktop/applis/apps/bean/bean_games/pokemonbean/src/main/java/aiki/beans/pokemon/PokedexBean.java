package aiki.beans.pokemon;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import code.images.BaseSixtyFourUtil;
import code.util.AbsMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class PokedexBean extends CommonBean {


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
    public String search() {
        StringList pokedex_ = pokedex();
        getForms().put(CST_POKEMON_SET, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_PK,pokedex_.first());
            return CST_POKEMON;
        }
        return CST_POKEMON_SET;
    }

    public static boolean atLeastMatchType(StringMap<String> _translationsTypes, boolean _wholeWord, String _typedType, StringList _types) {
        boolean atLeastMatchType_ = false;
        for (String t: _types) {
            String displayType_;
            displayType_ = _translationsTypes.getVal(t);
            if (_wholeWord) {
                if (StringUtil.quickEq(displayType_, _typedType)) {
                    atLeastMatchType_ = true;
                }
            } else {
                if (StringUtil.match(displayType_, _typedType)) {
                    atLeastMatchType_ = true;
                }
            }
        }
        return atLeastMatchType_;
    }

    public String getMiniImage(int _number) {
        String name_ = getPokedex().get(_number).getName();
        DataBase data_ = getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public String clickLink(int _number) {
        getForms().put(CST_PK, getPokedex().get(_number).getName());
        return CST_POKEMON;
    }
}