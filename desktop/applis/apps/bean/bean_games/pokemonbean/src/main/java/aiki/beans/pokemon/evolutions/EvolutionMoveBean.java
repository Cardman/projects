package aiki.beans.pokemon.evolutions;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionMove;
import code.util.StringList;
import code.util.StringMap;

public class EvolutionMoveBean extends EvolutionBean {
    private String move;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMove evo_ = (EvolutionMove) getEvo();
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        move = translationsMoves_.getVal(evo_.getMove());
    }
    public String clickMove(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        evolutions_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        EvolutionMove evo_ = (EvolutionMove) pk_.getEvolutions().getVal(evolutions_.get(_index));
        return tryRedirectMv(evo_.getMove());
    }

    public String getMove() {
        return move;
    }
}