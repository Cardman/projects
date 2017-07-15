package aiki.beans.items;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionItem;

public class EvolvingItemBean extends ItemBean {

    @Accessible
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
                if (!(e instanceof EvolutionItem)) {
                    continue;
                }
                if (getName() == null) {
                    continue;
                }
                EvolutionItem evo_ = (EvolutionItem) e;
                if (!StringList.quickEq(evo_.getItem(), getName())) {
                    continue;
                }
                pokemon_.add(p);
            }
        }
        pokemon_.removeDuplicates();
        pokemon_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        pokemon = pokemon_;
    }

    @Accessible
    private String getTrPokemon(Long _index) {
        String type_ = pokemon.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }

    @Accessible
    private String clickPokemon(Long _index) {
        String type_ = pokemon.get(_index.intValue());
        getForms().put(PK, type_);
        return POKEMON;
    }
}
