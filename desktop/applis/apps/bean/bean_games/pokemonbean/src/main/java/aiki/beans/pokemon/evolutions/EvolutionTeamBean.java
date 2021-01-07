package aiki.beans.pokemon.evolutions;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionTeam;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionTeamBean extends EvolutionBean {
    private String other;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionTeam evo_ = (EvolutionTeam) getEvo();
        StringMap<String> translationsPokemon_;
        DataBase data_ = (DataBase) getDataBase();
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        other = translationsPokemon_.getVal(evo_.getPokemon());
    }
    public String clickTeam(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutions_.sortElts(new ComparatorTrStrings(translationsPokemon_));
        EvolutionTeam evo_ = (EvolutionTeam) pk_.getEvolutions().getVal(evolutions_.get(_index));
        getForms().put(PK,evo_.getPokemon());
        return POKEMON;
    }

    public String getOther() {
        return other;
    }
}