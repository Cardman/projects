package aiki.beans.pokemon.evolutions;

import aiki.comparators.DictionaryComparatorUtil;
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
        DataBase data_ = getDataBase();
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        other = translationsPokemon_.getVal(evo_.getPokemon());
    }
    public String clickTeam(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        evolutions_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        EvolutionTeam evo_ = (EvolutionTeam) pk_.getEvolutions().getVal(evolutions_.get(_index));
        return tryRedirectPk(evo_.getPokemon());
    }

    public String getOther() {
        return other;
    }
}