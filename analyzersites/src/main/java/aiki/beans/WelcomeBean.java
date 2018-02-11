package aiki.beans;
import code.bean.Accessible;
import code.util.StringList;
import aiki.DataBase;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.util.LevelMove;

public class WelcomeBean extends CommonBean {

    private StringList moves = new StringList();

    @Override
    public void beforeDisplaying() {
        if (moves.isEmpty()) {
            StringList learntMoves_ = new StringList();
//            StringList notLearntMoves_ = new StringList();
            DataBase data_ = (DataBase) getDataBase();
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
            getForms().put(LEARNT_MOVES, learntMoves_);
            moves.addAllElts(data_.getMoves().getKeys());
        }
    }

    @Accessible
    private String seeAllMoves() {
        getForms().put(MOVES_SET, new StringList());
        getForms().removeKey(LEARNT);
        return MOVES;
    }

    @Accessible
    private String seeLearntMoves() {
        getForms().put(MOVES_SET, new StringList());
        getForms().put(LEARNT, true);
        return MOVES;
    }

    @Accessible
    private String seeNotLearntMoves() {
        getForms().put(MOVES_SET, new StringList());
        getForms().put(LEARNT, false);
        return MOVES;
    }

    @Accessible
    private String clickAbilities() {
        getForms().put(ABILITIES_SET, new StringList());
        return ABILITIES;
    }

    @Accessible
    private String clickStatus() {
        getForms().put(STATUS_SET, new StringList());
        return STATUS_SET;
    }

    @Accessible
    private String clickItems() {
        getForms().put(ITEMS_SET, new StringList());
        return ITEMS;
    }

    @Accessible
    private String clickPokedex() {
        getForms().put(POKEMON_SET, new StringList());
        return POKEMON_SET;
    }

    @Accessible
    private String clickSimulation() {
        getForms().put(SIMULATION_STATE, SimulationSteps.DIFF);
        return SIMULATION;
    }
}
