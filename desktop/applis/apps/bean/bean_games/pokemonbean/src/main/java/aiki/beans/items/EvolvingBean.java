package aiki.beans.items;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;

public abstract class EvolvingBean extends ItemBean{
    private StringList pokemon;

    public String getTrPokemon(int _index) {
        String type_ = pokemon.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickPokemon(int _index) {
        String type_ = pokemon.get(_index);
        return tryRedirectPk(type_);
    }

    public StringList getPokemon() {
        return pokemon;
    }

    public void pk(StringList _p) {
        _p.removeDuplicates();
        _p.sortElts(DictionaryComparatorUtil.cmpPokemon(getDataBase(),getLanguage()));
        setPokemon(_p);
    }
    public void setPokemon(StringList _p) {
        this.pokemon = _p;
    }
}
