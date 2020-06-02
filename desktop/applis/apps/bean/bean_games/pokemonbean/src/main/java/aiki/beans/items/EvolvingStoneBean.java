package aiki.beans.items;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionStone;
import code.util.StringList;
import code.util.StringMap;

public class EvolvingStoneBean extends ItemBean {
    private StringList pokemon;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringList pokemon_;
        pokemon_ = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            for (Evolution e: pkData_.getEvolutions().values()) {
                if (!(e instanceof EvolutionStone)) {
                    continue;
                }
                if (getName() == null) {
                    continue;
                }
                EvolutionStone evo_ = (EvolutionStone) e;
                if (!StringList.quickEq(evo_.getStone(), getName())) {
                    continue;
                }
                pokemon_.add(p);
            }
        }
        pokemon_.removeDuplicates();
        pokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        pokemon = pokemon_;
    }
    public String getTrPokemon(Long _index) {
        String type_ = pokemon.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickPokemon(Long _index) {
        String type_ = pokemon.get(_index.intValue());
        getForms().put(PK, type_);
        return POKEMON;
    }

    public StringList getPokemon() {
        return pokemon;
    }
}