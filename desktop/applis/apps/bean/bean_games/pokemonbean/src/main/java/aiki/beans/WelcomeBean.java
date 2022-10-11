package aiki.beans;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;
import code.util.StringList;

public class WelcomeBean extends CommonBean {

    private final StringList moves = new StringList();

    @Override
    public void beforeDisplaying() {
        if (moves.isEmpty()) {
            StringList learntMoves_ = new StringList();
//            StringList notLearntMoves_ = new StringList();
            DataBase data_ = getDataBase();
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                for (LevelMove l: pkData_.getLevMoves()) {
                    learntMoves_.add(l.getMove());
                }
                for (String m: pkData_.getMoveTutors()) {
                    learntMoves_.add(m);
                }
                for (Short hm_: pkData_.getHiddenMoves()) {
                    learntMoves_.add(data_.getHm().getVal(hm_));
                }
                for (Short tm_: pkData_.getTechnicalMoves()) {
                    learntMoves_.add(data_.getTm().getVal(tm_));
                }
            }
            learntMoves_.removeDuplicates();
            getForms().put(CST_LEARNT_MOVES, learntMoves_);
            moves.addAllElts(data_.getMoves().getKeys());
        }
    }
    public String seeAllMoves() {
        getForms().put(CST_MOVES_SET, new StringList());
        getForms().removeKey(CST_LEARNT);
        return CST_MOVES;
    }
    public String seeLearntMoves() {
        getForms().put(CST_MOVES_SET, new StringList());
        getForms().put(CST_LEARNT, true);
        return CST_MOVES;
    }
    public String seeNotLearntMoves() {
        getForms().put(CST_MOVES_SET, new StringList());
        getForms().put(CST_LEARNT, false);
        return CST_MOVES;
    }
    public String clickAbilities() {
        getForms().put(CST_ABILITIES_SET, new StringList());
        return CST_ABILITIES;
    }
    public String clickStatus() {
        getForms().put(CST_STATUS_SET, new StringList());
        return CST_STATUS_SET;
    }
    public String clickItems() {
        getForms().put(CST_ITEMS_SET, new StringList());
        return CST_ITEMS;
    }
    public String clickPokedex() {
        getForms().put(CST_POKEMON_SET, new StringList());
        return CST_POKEMON_SET;
    }
    public String clickSimulation() {
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        return CST_SIMULATION;
    }
}