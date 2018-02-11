package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionStone;

public class EvolutionStoneBean extends EvolutionBean {

    @Accessible
    private String stone;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionStone evo_ = (EvolutionStone) getEvo();
        StringMap<String> translationsItems_;
        DataBase data_ = (DataBase) getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        stone = translationsItems_.getVal(evo_.getStone());
    }

    @Accessible
    private String clickStone(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutions_.sortElts(new ComparatorTrStrings(translationsPokemon_));
        EvolutionStone evo_ = (EvolutionStone) pk_.getEvolutions().getVal(evolutions_.get(_index.intValue()));
        getForms().put(ITEM,evo_.getStone());
        return EVO_STONE;
    }
}
