package aiki.beans;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.db.DataBase;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.status.Status;
import aiki.fight.util.LevelMove;
import aiki.game.fight.FightSimulation;
import code.scripts.confs.PkScriptPages;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

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
                for (Integer hm_: pkData_.getHiddenMoves()) {
                    learntMoves_.add(StringUtil.nullToEmpty(data_.getHm().getVal(hm_)));
                }
                for (Integer tm_: pkData_.getTechnicalMoves()) {
                    learntMoves_.add(StringUtil.nullToEmpty(data_.getTm().getVal(tm_)));
                }
            }
            learntMoves_.removeDuplicates();
            StringMap<MoveData> moveData_ = FightSimulation.expand(learntMoves_,data_);
            getForms().putMoves(CST_LEARNT_MOVES, moveData_);
            moves.addAllElts(data_.getMoves().getKeys());
        }
    }

    public String seeAllMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().removeKey(CST_LEARNT);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().put(CST_LEARNT, true);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeNotLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, new StringMap<MoveData>());
        getForms().put(CST_LEARNT, false);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String clickAbilities() {
        getForms().putAbilities(CST_ABILITIES_SET, new StringMap<AbilityData>());
        return PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML;
    }
    public String clickStatus() {
        getForms().putStatus(CST_STATUS_SET, new StringMap<Status>());
        return PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public String clickItems() {
        getForms().putItems(CST_ITEMS_SET, new StringMap<Item>());
        return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }
    public String clickPokedex() {
        getForms().putPokedex(CST_POKEMON_SET, new StringMap<PokemonData>());
        return PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML;
    }
    public String clickSimulation() {
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}