package aiki.beans.pokemon.evolutions;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionItem;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionItemBean extends EvolutionBean {
    private String item;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionItem evo_ = (EvolutionItem) getEvo();
        StringMap<String> translationsItems_;
        DataBase data_ = getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        item = translationsItems_.getVal(evo_.getItem());
    }
    public String clickItem(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        evolutions_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        EvolutionItem evo_ = (EvolutionItem) pk_.getEvolutions().getVal(evolutions_.get(_index));
        return tryRedirectIt(evo_.getItem());
    }

    public String getItem() {
        return item;
    }
}