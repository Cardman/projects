package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.EvolutionMove;

public class EvolutionMoveBean extends EvolutionBean {

    @Accessible
    private String move;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMove evo_ = (EvolutionMove) getEvo();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        move = translationsMoves_.getVal(evo_.getMove());
    }

    @Accessible
    protected String clickMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(getBase());
        StringList evolutions_ = new StringList(pk_.getEvolutions().getKeys());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutions_.sortElts(new ComparatorTrStrings(translationsPokemon_));
        EvolutionMove evo_ = (EvolutionMove) pk_.getEvolutions().getVal(evolutions_.get(_index.intValue()));
        getForms().put(MOVE,evo_.getMove());
        return MOVE;
    }
}
