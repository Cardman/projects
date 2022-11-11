package aiki.beans.pokemon.evolutions;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionBean extends CommonBean {
    private String displayBase;
    private String base;

    private Evolution evo;
    private String displayName;
    private String name;
    private int index;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evo = data_.getPokemon(base).getEvolutions().getVal(name);
        displayBase = translationsPokemon_.getVal(base);
        displayName = translationsPokemon_.getVal(name);
    }

    protected Evolution getEvo() {
        return evo;
    }
    public String clickEvo(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(base);
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        evolutions_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        return tryRedirectPk(evolutions_.get(_index));
    }

    public String getBase() {
        return base;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setBase(String _base) {
        base = _base;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayBase() {
        return displayBase;
    }
}