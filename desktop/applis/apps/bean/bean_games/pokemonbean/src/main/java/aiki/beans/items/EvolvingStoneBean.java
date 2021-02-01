package aiki.beans.items;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionStone;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class EvolvingStoneBean extends ItemBean {
    private StringList pokemon;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
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
                if (!StringUtil.quickEq(evo_.getStone(), getName())) {
                    continue;
                }
                pokemon_.add(p);
            }
        }
        pokemon_.removeDuplicates();
        pokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        pokemon = pokemon_;
    }
    public String getTrPokemon(int _index) {
        String type_ = pokemon.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickPokemon(int _index) {
        String type_ = pokemon.get(_index);
        getForms().put(CST_PK, type_);
        return CST_POKEMON;
    }

    public StringList getPokemon() {
        return pokemon;
    }
}