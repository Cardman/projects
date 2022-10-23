package aiki.beans;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.status.Status;
import aiki.fight.util.LevelMove;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public class WelcomeBean extends CommonBean {

    private final StringList moves = new StringList();

    @Override
    public void beforeDisplaying() {
        if (moves.isEmpty()) {
            StringList learntMoves_ = new StringList();
//            StringList notLearntMoves_ = new StringList();
            DataBase data_ = getDataBase();
            for (EntryCust<String, PokemonData> p: data_.getPokedex().entryList()) {
                PokemonData pkData_ = p.getValue();
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
            StringMap<MoveData> moveData_ = moves(learntMoves_, data_);
            getForms().putMoves(CST_LEARNT_MOVES, moveData_);
            moves.addAllElts(data_.getMoves().getKeys());
        }
    }

    private StringMap<MoveData> moves(StringList _learntMoves, DataBase _data) {
        StringMap<MoveData> moveData_ = new StringMap<MoveData>();
        for (String m: _learntMoves) {
            moveData_.addEntry(m, _data.getMove(m));
        }
        return moveData_;
    }

    public String seeAllMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().removeKey(CST_LEARNT);
        return AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().put(CST_LEARNT, true);
        return CST_MOVES;
    }
    public String seeNotLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().put(CST_LEARNT, false);
        return CST_MOVES;
    }
    public String clickAbilities() {
        getForms().putAbilities(CST_ABILITIES_SET, new StringMap<AbilityData>());
        return CST_ABILITIES;
    }
    public String clickStatus() {
        getForms().putStatus(CST_STATUS_SET, new StringMap<Status>());
        return CST_STATUS_SET;
    }
    public String clickItems() {
        getForms().putItems(CST_ITEMS_SET, new StringMap<Item>());
        return CST_ITEMS;
    }
    public String clickPokedex() {
        getForms().putPokedex(CST_POKEMON_SET, new StringMap<PokemonData>());
        return CST_POKEMON_SET;
    }
    public String clickSimulation() {
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        return CST_SIMULATION;
    }
}