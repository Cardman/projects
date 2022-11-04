package aiki.beans.pokemon.evolutions;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionStone;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionStoneBean extends EvolutionBean {
    private String stone;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionStone evo_ = (EvolutionStone) getEvo();
        StringMap<String> translationsItems_;
        DataBase data_ = getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        stone = translationsItems_.getVal(evo_.getStone());
    }
    public String clickStone(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        evolutions_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        EvolutionStone evo_ = (EvolutionStone) pk_.getEvolutions().getVal(evolutions_.get(_index));
        return tryRedirectIt(evo_.getStone());
    }

    public String getStone() {
        return stone;
    }
}