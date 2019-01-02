package aiki.beans.pokemon.evolutions;
import aiki.comparators.ComparatorTrStrings;
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
        DataBase data_ = (DataBase) getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        item = translationsItems_.getVal(evo_.getItem());
    }
    public String clickItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutions_.sortElts(new ComparatorTrStrings(translationsPokemon_));
        EvolutionItem evo_ = (EvolutionItem) pk_.getEvolutions().getVal(evolutions_.get(_index.intValue()));
        getForms().put(ITEM,evo_.getItem());
        return EVO_ITEM;
    }

    public String getItem() {
        return item;
    }
}