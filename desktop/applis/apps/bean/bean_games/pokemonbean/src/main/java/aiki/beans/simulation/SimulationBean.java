package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.DifficultyCommon;
import aiki.beans.WithDifficultyCommon;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.comparators.ComparatorRadioLineMoves;
import aiki.beans.facade.dto.KeptMovesAfterFight;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.dto.PokemonTrainerDto;
import aiki.beans.facade.simulation.dto.RadioLineMove;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.beans.moves.MovesBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.util.AvailableMovesInfos;
import aiki.game.params.Difficulty;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.levels.Level;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class SimulationBean extends CommonBean  implements WithDifficultyCommon {
    private final DifficultyCommon difficultyCommon = new DifficultyCommon();
    private CustList<PlaceIndex> places = new CustList<PlaceIndex>();

    private Coords coords;
    private boolean freeTeams;
    private int nbTeams;
    private int indexTeam;
    private int selectedFoePk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final CustList<CustList<PokemonTrainerDto>> foeTeams = new CustList<CustList<PokemonTrainerDto>>();
    private int selectedAllyPk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final CustList<CustList<PokemonTrainerDto>> allyTeams = new CustList<CustList<PokemonTrainerDto>>();
    private final Ints multiplicities = new Ints();

    private final CustList<EnvironmentType> environmentsList = new CustList<EnvironmentType>();
    private DictionaryComparator<String, String> environments;

    private boolean enableEvolutions = true;
    private final CustList<PokemonPlayerDto> team = new CustList<PokemonPlayerDto>();
    private int selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private String selectedFoeAction = TeamCrud.NOTHING.getTeamCrudString();
    private String selectedAllyAction = TeamCrud.NOTHING.getTeamCrudString();
    private String selectedAction = TeamCrud.NOTHING.getTeamCrudString();

    private StringMap<Long> availableEvosLevel = new StringMap<Long>();
    private DictionaryComparator<String, String> availableEvos;

    private String chosenEvo = DataBase.EMPTY_STRING;
    private long levelEvo;

    private FightSimulation simulation;

    private final Difficulty difficulty = new Difficulty();

    private int noFight;
    private String selectedRound ="0";
    private IntTreeMap< Integer> round = new IntTreeMap< Integer>();

    private String placeFight = Long.toString(Fighter.BACK);
    private IntTreeMap< String> placesFight = new IntTreeMap< String>();
    private String target ="0";
    private IntTreeMap< String> targetFight = new IntTreeMap< String>();
    private String currentAbility = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> abilities;
    private final CustList<SelectLineMove> keptMoves = new CustList<SelectLineMove>();
    private final CustList<RadioLineMove> movesSet = new CustList<RadioLineMove>();
    private int selectedMove;
    private boolean allyChoice;
    private final StringList comments = new StringList();

    private CustList<PokemonPlayer> teamAfterFight = new CustList<PokemonPlayer>();
    private final CustList<KeptMovesAfterFight> keptMovesAbilitiesDto = new CustList<KeptMovesAfterFight>();
    private final CustList<SelectLineMove> keptMovesAfterFight = new CustList<SelectLineMove>();
    private String evolutionAfterFight = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> evolutionsAfterFight;
    private String abilityAfterFight = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> abilitiesAfterFight;
    private int stepNumber;
    private boolean ok;
    private boolean displayIfError;

    @Override
    public void beforeDisplaying() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        if (simu_ == SimulationSteps.DIFF) {

            stateDiff();
        } else if (simu_ == SimulationSteps.FOE) {
            stateFoe();
        } else if (simu_ == SimulationSteps.TEAM) {
            stateTeam();
        } else if (simu_ == SimulationSteps.FRONT) {
            stateFront();
            //display front fighters
        } else if (simu_ == SimulationSteps.MOVES) {
            //.
            stateMoves();

        } else if (simu_ == SimulationSteps.MOVES_FIGHT) {
            stateMovesFight();
        } else if (simu_ == SimulationSteps.SIMULATION) {
            stateSimu();
        }
    }

    private void stateSimu() {
        if (isIssue()) {
            setupComments();
        }
        teamAfterFight = new CustList<PokemonPlayer>();
        if (!simulation.getProbleme()) {
            teamAfterFight = simulation.getTeamAfterFight();
        }
    }

    private void stateMovesFight() {
        if (selectedPk != IndexConstants.INDEX_NOT_FOUND_ELT) {
            targetFight = new IntTreeMap< String>();
            int mult_ = simulation.getFirstMult();
            for (int i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
                targetFight.put(i, Long.toString(i));
            }
            movesSet.clear();
            if (NumberUtil.parseInt(selectedRound) == 0) {
                StringList moves_ = team.get(selectedPk).getMoves();
                moveSetInit(moves_);
                return;
            }
            StringList moves_ = simulation.getKeptMoves().getVal(selectedPk).getVal(new KeyFightRound(IndexConstants.FIRST_INDEX, NumberUtil.parseInt(selectedRound) -1));
            moveSetInit(moves_);
        }
    }

    private void moveSetInit(StringList _moves) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: _moves) {
            MoveData moveData_ = data_.getMoves().getVal(k);
            RadioLineMove line_ = new RadioLineMove();
            MovesBean.line(translationsMoves_,translationsTypes_,translationsCategories_,k,moveData_,line_,getDataBase());
            movesSet.add(line_);
        }
        int i_ = IndexConstants.FIRST_INDEX;
        movesSet.sortElts(new ComparatorRadioLineMoves());
        for (RadioLineMove l: movesSet) {
            l.setIndex(i_);
            i_++;
        }
    }

    private void stateMoves() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilities = DictionaryComparatorUtil.buildAbilities(data_,getLanguage());
        currentAbility = DataBase.EMPTY_STRING;
        if (isAvailableMoves()) {
            if (isAvailableAbilities()) {
                for (String a : getAvailableAbilities()) {
                    abilities.put(a, translationsAbilities_.getVal(a));
                }
                currentAbility = abilities.firstKey();
            }
            //keptMoves
            keptMoves.clear();
            StringMap<String> translationsMoves_;
            translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
            StringMap<String> translationsTypes_;
            translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
            StringMap<String> translationsCategories_;
            translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
            AvailableMovesInfos info_;
            info_ = simulation.getAvailableMoves().getVal(selectedPk);
            for (String k : info_.getMoves().getKeys()) {
                MoveData moveData_ = data_.getMoves().getVal(k);
                SelectLineMove line_ = MovesBean.buildLine(translationsMoves_,translationsTypes_,translationsCategories_,k,moveData_,getDataBase());
//                    line_.setName(k);
//                    line_.setDisplayName(translationsMoves_.getVal(k));
//                    StringList types_ = new StringList();
//                    for (String t : moveData_.getTypes()) {
//                        types_.add(translationsTypes_.getVal(t));
//                    }
//                    line_.setTypes(types_);
//                    line_.setPp(moveData_.getPp());
//                    line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
//                    line_.setDamageMove(moveData_ instanceof DamagingMoveData);
//                    if (line_.isDamageMove()) {
//                        DamagingMoveData damag_ = (DamagingMoveData) moveData_;
//                        line_.setDirect(damag_.isDirect());
//                    }
//                    line_.setPriority(moveData_.getPriority());
                line_.setSelected(info_.getMoves().getVal(k) == BoolVal.TRUE);
                keptMoves.add(line_);
            }
            keptMoves.sortElts(new ComparatorMoves());
        }
    }

    private void stateFront() {
        round = new IntTreeMap< Integer>();
        placesFight = new IntTreeMap< String>();
        int nbRounds_ = simulation.nbRounds();
        for (int i = IndexConstants.FIRST_INDEX; i < nbRounds_; i++) {
            round.put(i, i);
        }
        int mult_ = simulation.getFirstMult();
        placesFight.put(Fighter.BACK, DataBase.EMPTY_STRING);
        for (int i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
            placesFight.put(i, Long.toString(i));
        }
    }

    private void stateTeam() {
        DataBase data_ = getDataBase();
        if (!simulation.getTeam().isEmpty()) {
            ok = true;
        }
        boolean nothing_ = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.NOTHING;
        if (nothing_) {
            return;
        }
        if (getForms().contains(CST_POKEMON_ADDED)) {
            PokemonPlayerDto pk_ = getForms().getVal(CST_POKEMON_ADDED);
            getForms().removeKey(CST_POKEMON_ADDED);
            pk_.setIndex(team.size());
            team.add(pk_);
            simulation.addPokemonPlayer(pk_.getPokemon(), pk_.getMoves(), 0, Rate.zero(), data_);
        } else if(getForms().contains(CST_POKEMON_INDEX_EDIT)) {
            int index_ = getForms().getValInt(CST_POKEMON_INDEX_EDIT);
            getForms().removeKey(CST_POKEMON_INDEX_EDIT);
            team.get(index_).setMoves(getForms().getValList(CST_POKEMON_MOVES_EDIT));
            getForms().removeKey(CST_POKEMON_MOVES_EDIT);
            team.get(index_).getPokemon().setItem(getForms().getValStr(CST_ITEM_EDIT));
            getForms().removeKey(CST_ITEM_EDIT);
            simulation.setPokemonPlayerObject(index_, team.get(index_).getPokemon().getItem());
            Rate exp_ = getForms().getValRate(CST_POKEMON_EXPERIENCE);
            getForms().removeKey(CST_POKEMON_EXPERIENCE);
            PokemonPlayer pkPlayer_ = simulation.getTeam().get(index_);
            pkPlayer_.setWonExpSinceLastLevel(exp_);
            String ball_ = getForms().getValStr(CST_CATCHING_BALL);
            getForms().removeKey(CST_CATCHING_BALL);
            pkPlayer_.setUsedBallCatching(ball_);
            long happy_ = getForms().getValLong(CST_POKEMON_HAPPINESS);
            getForms().removeKey(CST_POKEMON_HAPPINESS);
            Rate hp_ = getForms().getValRate(CST_POKEMON_HP).absNb();
            getForms().removeKey(CST_POKEMON_HP);
            boolean heal_ = getForms().getValBool(CST_HEAL_EDIT_PK);
            getForms().removeKey(CST_HEAL_EDIT_PK);
            for (Statistic s:Statistic.getStatisticsWithBase()) {
                long ev_ = getForms().getValLong(StringUtil.concat(CST_POKEMON_EV_VAR, s.getStatName()));
                getForms().removeKey(StringUtil.concat(CST_POKEMON_EV_VAR,s.getStatName()));
                if (ev_ > data_.getMaxEv()) {
                    ev_ = data_.getMaxEv();
                }
                pkPlayer_.getEv().put(s, ev_);
            }
            if (Rate.strGreater(hp_, pkPlayer_.pvMax(data_)) || heal_) {
                pkPlayer_.setRemainingHp(pkPlayer_.pvMax(data_));
            } else {
                pkPlayer_.setRemainingHp(hp_);
            }
            pkPlayer_.setHappiness(happy_);
            simulation.setInitialMoves(index_, team.get(index_).getMoves(), data_);
            getForms().removeKey(CST_ITEMS_SET_EDIT);
            getForms().removeKey(CST_POKEMON_NAME_EDIT);
            getForms().removeKey(CST_POKEMON_LEVEL_EDIT);
        }
    }

    private void stateFoe() {
        DataBase data_ = getDataBase();
        if (freeTeams) {
            stateFoeFree();
        } else {
            if (!getForms().contains(CST_NO_FIGHT)) {
                getForms().put(CST_NO_FIGHT, IndexConstants.FIRST_INDEX);
            }
            noFight = getForms().getValInt(CST_NO_FIGHT);
            coords = getForms().getValCoords(CST_COORDS);
            if (coords != null) {
                ok = true;
            }
            places = PlaceIndex.places(data_);
        }
    }

    private void stateFoeFree() {
        if (okFights()) {
            ok = true;
        }
        boolean remove_ = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.REMOVE;
        if (remove_) {
            return;
        }
        boolean nothing_ = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.NOTHING;
        if (nothing_) {
            return;
        }
        boolean add_ = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.ADD;
        if (add_) {
            boolean foe_ = getForms().getValBool(CST_POKEMON_FOE);
            PokemonTrainerDto pk_;
            pk_ = new PokemonTrainerDto();
            pk_.getPkTrainer().getMoves().addAllElts(getForms().getValList(CST_POKEMON_MOVES_EDIT));
            pk_.getPkTrainer().setAbility(getForms().getValStr(CST_POKEMON_ABILITY_EDIT));
            pk_.getPkTrainer().setName(getForms().getValStr(CST_POKEMON_NAME_EDIT));
            pk_.getPkTrainer().setGender(getForms().getValGen(CST_POKEMON_GENDER_EDIT));
            pk_.getPkTrainer().setItem(getForms().getValStr(CST_ITEM_EDIT));
            pk_.getPkTrainer().setLevel(getForms().getValLong(CST_POKEMON_LEVEL_EDIT));
            if (foe_) {
                pk_.setIndex(foeTeams.get(indexTeam).size());
                foeTeams.get(indexTeam).add(pk_);
            } else {
                pk_.setIndex(allyTeams.get(indexTeam).size());
                allyTeams.get(indexTeam).add(pk_);
            }
        } else {
            boolean foe_ = getForms().getValBool(CST_POKEMON_FOE);
            PokemonTrainerDto pk_;
            if (foe_) {
                pk_ = foeTeams.get(indexTeam).get(selectedFoePk);
            } else {
                pk_ = allyTeams.get(indexTeam).get(selectedAllyPk);
            }
            pk_.getPkTrainer().getMoves().clear();
            pk_.getPkTrainer().getMoves().addAllElts(getForms().getValList(CST_POKEMON_MOVES_EDIT));
            pk_.getPkTrainer().setAbility(getForms().getValStr(CST_POKEMON_ABILITY_EDIT));
            pk_.getPkTrainer().setName(getForms().getValStr(CST_POKEMON_NAME_EDIT));
            pk_.getPkTrainer().setGender(getForms().getValGen(CST_POKEMON_GENDER_EDIT));
            pk_.getPkTrainer().setItem(getForms().getValStr(CST_ITEM_EDIT));
            pk_.getPkTrainer().setLevel(getForms().getValLong(CST_POKEMON_LEVEL_EDIT));
        }
        getForms().put(CST_ADDING_TRAINER_PK,TeamCrud.NOTHING);
    }

    private void stateDiff() {
        DataBase data_ = getDataBase();
        difficultyCommon.init(data_,getLanguage(),difficulty);
    }

    public boolean isDiffState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.DIFF;
    }
    public boolean isFoeState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FOE;
    }
    public boolean isTeamState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.TEAM;
    }
    public boolean isEvolutionsState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.EVOLUTIONS;
    }
    public boolean isFrontState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FRONT;
    }
    public boolean isMovesState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES;
    }
    public boolean isMovesFightState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES_FIGHT;
    }
    public boolean isSimulationState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.SIMULATION;
    }
    public boolean isEvolutionAfterFightState() {
        SimulationSteps simu_ = getForms().getValSimStep(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.EVO_AFTER_FIGHT;
    }
    public boolean isMultiLayer(int _index) {
        return layers(_index).size() > IndexConstants.ONE_ELEMENT;
    }
    public CustList<Level> layers(int _index) {
        Place pl_ = places.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    public String getTrainerName() {
        if (coords == null) {
            return DataBase.EMPTY_STRING;
        }
        StringBuilder placeName_ = new StringBuilder();
        DataBase data_ = getDataBase();
        Place pl_ = data_.getMap().getPlace(coords.getNumberPlace());
        placeName_.append(pl_.getName());
        placeName_.append(CST_SPACE);
        if (pl_ instanceof Cave) {
            placeName_.append(coords.getLevel().getLevelIndex());
            placeName_.append(CST_SPACE);
        }
        placeName_.append(getTrainerName(coords));
        placeName_.append(CST_SPACE);
        placeName_.append(noFight);
        return placeName_.toString();
    }

    private String getTrainerName(Coords _coords) {
        DataBase data_ = getDataBase();
        return data_.getMap().getTrainerName(_coords);
//        Place pl_ = data_.getMap().getPlace(_coords.getNumberPlace());
//        Level level_ = pl_.getLevelByCoords(_coords);
//        if (level_ instanceof LevelIndoorGym) {
//            LevelIndoorGym g_ = (LevelIndoorGym) level_;
//            if (Point.eq(g_.getGymLeaderCoords(), _coords.getLevel().getPoint())) {
//                return g_.getGymLeader().getName();
//            }
//            return DataBase.EMPTY_STRING;
//        }
//        if (level_ instanceof LevelLeague) {
//            LevelLeague l_ = (LevelLeague) level_;
//            return l_.getTrainer().getName();
//        }
//        if (level_ instanceof LevelWithWildPokemon) {
//            LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
//            if (w_.getDualFights().contains(_coords.getLevel().getPoint())) {
//                return StringUtil.join(w_.getDualFights().getVal(_coords.getLevel().getPoint()).getNames(), CST_SPACE);
//            }
//        }
//        return DataBase.EMPTY_STRING;
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        //getForms().removeKey(INSIDE);
        DataBase data_ = getDataBase();
        Place pl_ = data_.getMap().getPlace(_indexOne);
        if (pl_ instanceof League) {
            League l_ = (League) pl_;
            coords = new Coords();
            coords.setNumberPlace(_indexOne);
            coords.setLevel(new LevelPoint());
            coords.getLevel().setLevelIndex(_indexTwo);
            coords.getLevel().setPoint(new Point(((LevelLeague)l_.getLevelsList().get(_indexTwo)).getTrainerCoords().value()));
            getForms().put(CST_COORDS, coords);
            noFight = IndexConstants.FIRST_INDEX;
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (pl_ instanceof City) {
            City c_ = (City) pl_;
            for (Point p: c_.getBuildings().getKeys()) {
                Building b_ = c_.getBuildings().getVal(p);
                if (b_ instanceof Gym) {
                    Coords co_ = new Coords();
                    co_.setNumberPlace(_indexOne);
                    co_.getLevel().setLevelIndex(_indexTwo);
                    co_.affectInside(new Point(p));
                    getForms().put(CST_COORDS,co_);
                    return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
                }
            }
        }
        Coords co_ = new Coords();
        co_.setNumberPlace(_indexOne);
        co_.getLevel().setLevelIndex(_indexTwo);
        getForms().put(CST_COORDS,co_);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }
    public void validateDiffChoice() {
        ok = true;
        DataBase data_ = getDataBase();
        difficultyCommon.apply(data_,difficulty);
        simulation = new FightSimulation(difficulty, data_);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FOE);
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        stepNumber++;
        if (freeTeams) {
            environments = DictionaryComparatorUtil.buildEnvStr(data_,getLanguage());
            environments.addAllEntries(DictionaryComparatorUtil.trEnvs(data_,getLanguage()));
            selectedFoePk = IndexConstants.INDEX_NOT_FOUND_ELT;
            selectedAllyPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        } else {
            environments = DictionaryComparatorUtil.buildEnvStr(data_,getLanguage());
        }
    }
    public void cancelDiffChoice() {
        ok = true;
        foeTeams.clear();
        allyTeams.clear();
        simulation = null;
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        stepNumber--;
    }
    public void validateFoeChoice() {
        ok = true;
        if (coords == null) {
            ok = false;
            return;
        }
        DataBase data_ = getDataBase();
        simulation.initializeFight(coords, noFight, data_);
        allyTeams.clear();
        foeTeams.clear();
        for (CustList<PkTrainer> t: simulation.getAllyTeamAll()) {
            CustList<PokemonTrainerDto> e_ = new CustList<PokemonTrainerDto>();
            for (PkTrainer p: t) {
                e_.add(PokemonTrainerDto.fromPokemonTrainer(p));
            }
            allyTeams.add(e_);
        }
        for (CustList<PkTrainer> t: simulation.getFoeTeamsAll()) {
            CustList<PokemonTrainerDto> e_ = new CustList<PokemonTrainerDto>();
            for (PkTrainer p: t) {
                e_.add(PokemonTrainerDto.fromPokemonTrainer(p));
            }
            foeTeams.add(e_);
        }
        multiplicities.clear();
        for (int t: simulation.getMultAll()) {
            multiplicities.add(t);
        }
        environmentsList.clear();
        environmentsList.addAllElts(simulation.getEnvironmentAll());
        indexTeam=0;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }
    public int[][] getImageFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        return data_.getMiniPk(pk_.getPkTrainer().getName());
    }
    public String getNameFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        return data_.translatePokemon(pk_.getPkTrainer().getName());
    }
    public long getLevelFoe(int _index) {
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        return pk_.getPkTrainer().getLevel();
    }
    public String getAbilityFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        return data_.translateAbility(pk_.getPkTrainer().getAbility());
    }
    public String getGenderFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        return data_.translateGenders(pk_.getPkTrainer().getGender());
    }
    public StringList getMovesFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getPkTrainer().getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemFoe(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = foeTeams.get(indexTeam).get(_index);
//        if (pk_.getPkTrainer().getItem().isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        return data_.translateItem(pk_.getPkTrainer().getItem());
    }
    public String selectFoePk() {
//        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.NOTHING) {
//            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
//        }
        if (selectedFoePk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.EDIT) {
            getForms().put(CST_POKEMON_FOE, true);
            getForms().putItems(CST_ITEMS_SET_EDIT, new StringMap<Item>());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedFoePk);
            getForms().put(CST_POKEMON_NAME_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getLevel());
            getForms().put(CST_ITEM_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getItem());
            getForms().put(CST_POKEMON_GENDER_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getGender());
            getForms().put(CST_POKEMON_MOVES_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getMoves());
            getForms().put(CST_POKEMON_ABILITY_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getAbility());
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.REMOVE) {
            int index_ = selectedFoePk;
            foeTeams.get(indexTeam).remove(index_);
            int size_ = foeTeams.get(indexTeam).size();
            for (int i = index_; i < size_;i++) {
                foeTeams.get(indexTeam).get(i).setIndex(i);
            }
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public boolean errorSelectedFoePk() {
        return selectedFoePk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedFoeAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedFoeAction) != TeamCrud.REMOVE);
    }
    public int[][] getImageAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        return data_.getMiniPk(pk_.getPkTrainer().getName());
    }
    public String getNameAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        return data_.translatePokemon(pk_.getPkTrainer().getName());
    }
    public long getLevelAlly(int _index) {
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        return pk_.getPkTrainer().getLevel();
    }
    public String getAbilityAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        return data_.translateAbility(pk_.getPkTrainer().getAbility());
    }
    public String getGenderAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        return data_.translateGenders(pk_.getPkTrainer().getGender());
    }
    public StringList getMovesAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getPkTrainer().getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemAlly(int _index) {
        DataBase data_ = getDataBase();
        PokemonTrainerDto pk_ = allyTeams.get(indexTeam).get(_index);
//        if (pk_.getPkTrainer().getItem().isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        return data_.translateItem(pk_.getPkTrainer().getItem());
    }
    public String selectAllyPk() {
//        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.NOTHING) {
//            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
//        }
        if (selectedAllyPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.EDIT) {
            getForms().put(CST_POKEMON_FOE, false);
            getForms().putItems(CST_ITEMS_SET_EDIT, new StringMap<Item>());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedAllyPk);
            getForms().put(CST_POKEMON_NAME_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getLevel());
            getForms().put(CST_ITEM_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getItem());
            getForms().put(CST_POKEMON_GENDER_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getGender());
            getForms().put(CST_POKEMON_MOVES_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getMoves());
            getForms().put(CST_POKEMON_ABILITY_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getAbility());
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.REMOVE) {
            int index_ = selectedAllyPk;
            allyTeams.get(indexTeam).remove(index_);
            int size_ = allyTeams.get(indexTeam).size();
            for (int i = index_; i < size_;i++) {
                allyTeams.get(indexTeam).get(i).setIndex(i);
            }
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public boolean errorSelectedAllyPk() {
        return selectedAllyPk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedAllyAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedAllyAction) != TeamCrud.REMOVE);
    }
    public String addPkTrainer() {
        DataBase data_ = getDataBase();
        Pokemon pk_ = data_.getMap().getFirstPokemon();
        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesBeforeLevel(pk_.getLevel());
        getForms().put(CST_POKEMON_NAME_EDIT, pk_.getName());
        getForms().put(CST_POKEMON_LEVEL_EDIT, pk_.getLevel());
        getForms().put(CST_ITEM_EDIT, pk_.getItem());
        getForms().put(CST_POKEMON_GENDER_EDIT, Gender.NO_GENDER);
        getForms().put(CST_POKEMON_MOVES_EDIT, moves_);
        getForms().put(CST_POKEMON_ABILITY_EDIT, pk_.getAbility());
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.ADD);
        getForms().putItems(CST_ITEMS_SET_EDIT, new StringMap<Item>());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public void validateFoeChoiceFree() {
        ok = true;
        coords = new Coords();
        if (!okFights()) {
            ok = false;
            return;
        }
        CustList<FreeTeamChoice> chs_ = new CustList<FreeTeamChoice>();
        for (int i = 0; i < nbTeams; i++) {
            FreeTeamChoice ch_ = new FreeTeamChoice();
            int mult_ = multiplicities.get(i);
            if (mult_ <= 0) {
                mult_ = 1;
            }
            if (mult_ >= DataBase.MAX_MULT_FIGHT) {
                mult_ = DataBase.MAX_MULT_FIGHT;
            }
            int nbMaxAct_ = mult_;
            if (!allyTeams.get(i).isEmpty()) {
                mult_ = 2;
                nbMaxAct_ = 1;
            }
            ch_.setNbMaxActions(nbMaxAct_);
            ch_.setMultiplicity(mult_);
            ch_.setEnv(environmentsList.get(i));
            CustList<PkTrainer> ally_;
            ally_ = new CustList<PkTrainer>();
            for (PokemonTrainerDto p: allyTeams.get(i)) {
                ally_.add(p.toPokemonTrainer());
            }
            CustList<PkTrainer> foe_;
            foe_ = new CustList<PkTrainer>();
            for (PokemonTrainerDto p: foeTeams.get(i)) {
                foe_.add(p.toPokemonTrainer());
            }
            ch_.getAllyTeam().addAllElts(ally_);
            ch_.getFoeTeam().addAllElts(foe_);
            chs_.add(ch_);
        }
        simulation.setTeams(chs_);
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        indexTeam=0;
        getForms().removeKey(CST_POKEMON_INDEX_EDIT);
        getForms().removeKey(CST_POKEMON_ADDED);
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }
    private boolean okFights() {
        for (CustList<PokemonTrainerDto> t: foeTeams) {
            if (t.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public String selectPk() {
//        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.NOTHING) {
//            return AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML;
//        }
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.EDIT) {
            getForms().putItems(CST_ITEMS_SET_EDIT, new StringMap<Item>());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedPk);
            getForms().put(CST_POKEMON_NAME_EDIT, team.get(selectedPk).getPokemon().getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, team.get(selectedPk).getPokemon().getLevel());
            getForms().put(CST_ITEM_EDIT, team.get(selectedPk).getPokemon().getItem());
            getForms().put(CST_POKEMON_MOVES_EDIT, team.get(selectedPk).getMoves());
            getForms().put(CST_POKEMON_EXPERIENCE, simulation.getTeam().get(selectedPk).getWonExpSinceLastLevel());
            getForms().put(CST_POKEMON_HAPPINESS, simulation.getTeam().get(selectedPk).getHappiness());
            getForms().put(CST_HEAL_EDIT_PK, false);
            getForms().put(CST_POKEMON_HP, simulation.getTeam().get(selectedPk).getRemainingHp());
            getForms().put(CST_CATCHING_BALL, simulation.getTeam().get(selectedPk).getUsedBallCatching());
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                getForms().put(StringUtil.concat(CST_POKEMON_EV_VAR,s.getStatName()), simulation.getTeam().get(selectedPk).getEv().getVal(s));
            }
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.REMOVE) {
            int index_ = selectedPk;
            team.remove(index_);
            int size_ = team.size();
            for (int i = index_; i < size_;i++) {
                team.get(i).setIndex(i);
            }
            simulation.removePokemonPlayer(index_);
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public boolean errorSelectedPk() {
        return selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedAction) != TeamCrud.REMOVE);
    }
    public String add() {
        getForms().putPokedex(CST_POKEMON_SET_SIMU, new StringMap<PokemonData>());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML;
    }
    public int[][] getImage(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.getMiniPk(pk_.getPokemon().getName());
    }
    public String getName(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translatePokemon(pk_.getPokemon().getName());
    }
    public long getLevel(int _index) {
        PokemonPlayerDto pk_ = team.get(_index);
        return pk_.getPokemon().getLevel();
    }
    public String getAbility(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translateAbility(pk_.getPokemon().getAbility());
    }
    public String getGender(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translateGenders(pk_.getPokemon().getGender());
    }
    public StringList getMoves(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItem(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
//        if (pk_.getPokemon().getItem().isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        return data_.translateItem(pk_.getPokemon().getItem());
    }
    public void validateTeam() {
        //later (before fight) simulation.validateTeam();
        ok = true;
        if (simulation.getTeam().isEmpty()) {
            ok = false;
            return;
        }
        enableEvolutions = true;
        //simulation.validateTeam();
        DataBase data_ = getDataBase();
        simulation.setFirstEvolutions(data_);
        //selectedPk = CustList.FIRST_INDEX;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
//        if (!enableEvolutions) {
//            simulation.initializeFrontFighters();
//            getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
//            stepNumber++;
//            stepNumber++;
//            return;
//        }
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
        stepNumber++;
    }
    public void validateFoeChoiceSkipEvolutions() {
        ok = true;
        if (simulation.getTeam().isEmpty()) {
            ok = false;
            return;
        }
        DataBase data_ = getDataBase();
        //check if all pokemon have the maximum moves
//        for (PokemonPlayer p: simulation.getTeam()) {
//            if (p.getMoves().size() != data_.getNbMaxMoves()) {
//                return;
//            }
//        }
        enableEvolutions = false;
        //simulation.validateTeam();
        simulation.setFirstEvolutions(data_);
        //selectedPk = CustList.FIRST_INDEX;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        simulation.initializeFrontFighters();
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
        stepNumber++;
    }
    public void cancelTeam() {
        ok = true;
        team.clear();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber--;
    }
    public void cancelEvolutions() {
        ok = true;
        simulation.cancelEvolutions();
        getForms().removeKey(CST_POKEMON_INDEX_EDIT);
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber--;
    }
    public void validateEvolutions() {
        ok = true;
        simulation.initializeFrontFighters();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().removeKey(CST_POKEMON_INDEX_EDIT);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
    }
    public boolean selectedIndex() {
        return getForms().contains(CST_POKEMON_INDEX_EDIT);
    }
    public void displayEvolutions() {
        DataBase data_ = getDataBase();
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            availableEvos = DictionaryComparatorUtil.buildPkStr(data_,getLanguage());
            return;
        }
        getForms().put(CST_POKEMON_INDEX_EDIT, selectedPk);
        evoList(data_,selectedPk);
    }
    public void validateEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = getDataBase();
        int index_ = getForms().getValInt(CST_POKEMON_INDEX_EDIT);
        levelEvo = NumberUtil.max(levelEvo, availableEvosLevel.getVal(chosenEvo));
        simulation.setNextEvolutions(index_, chosenEvo, levelEvo, data_);
        evoList(data_, index_);
    }
    public void cancelEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = getDataBase();
        int index_ = getForms().getValInt(CST_POKEMON_INDEX_EDIT);
        simulation.cancelEvolutions(index_, data_);
        evoList(data_, index_);
    }

    private void evoList(DataBase _data, int _index) {
        StringMap<Long> evos_ = simulation.getAvailableEvolutions().get(_index);
        availableEvosLevel = new StringMap<Long>(evos_);
        StringMap<String> map_ = _data.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = DictionaryComparatorUtil.buildPkStr(_data,getLanguage());
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }

    public void validateFrontFighter() {
        displayIfError = false;
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        simulation.getFrontFighters().first().get(NumberUtil.parseInt(selectedRound)).put(selectedPk, NumberUtil.parseInt(placeFight));
    }
    public void cancelFrontFighters() {
        displayIfError = false;
        ok = true;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.cancelEvolutions();
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
            getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
            stepNumber--;
            stepNumber--;
            return;
        }
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
        stepNumber--;
    }
    public void validateFrontFighters() {
        displayIfError = true;
        ok = true;
        DataBase data_ = getDataBase();
        simulation.prepareMovesToBeLearntOneFight(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.initializeAllMoves(data_);
            getForms().put(CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
            stepNumber++;
            stepNumber++;
            return;
        }
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.MOVES);
        stepNumber++;
    }
    public boolean selectedIndexForMoves() {
        return selectedPk != IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public boolean isAvailableMoves() {
        return simulation.isAvailableMoves(selectedPk);
    }
    public boolean isAvailableAbilities() {
        return simulation.isAvailableAbilities(selectedPk);
    }

    private StringList getAvailableAbilities() {
        return simulation.getAvailableAbilities(selectedPk);
    }
    public void validateMoves() {
        displayIfError = false;
        if (isAvailableAbilities()) {
            if (currentAbility.isEmpty()) {
                return;
            }
            int r_ = simulation.getAvailableMoves().getVal(selectedPk).getKey().getRound();
            simulation.setAbilityWhileFight(selectedPk, IndexConstants.FIRST_INDEX, r_, currentAbility);
        }
        StringList moves_ = new StringList();
        for (SelectLineMove m: keptMoves) {
            if (m.isSelected()) {
                moves_.add(m.getName());
            }
        }
        DataBase data_ = getDataBase();
        simulation.keepMoves(selectedPk, moves_, data_);
    }
    public void cancelMoves() {
        displayIfError = false;
        ok = true;
        DataBase data_ = getDataBase();
        simulation.cancelAllMovesOneFight(selectedPk, data_);
    }
    public void cancelMovesSets() {
        displayIfError = false;
        ok = true;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber--;
    }
    public void validateMovesSets() {
        displayIfError = true;
        DataBase data_ = getDataBase();
        ok = true;
        simulation.validateAllMoves(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber++;
    }
    public void validateMovesChoice() {
        displayIfError = false;
        if (selectedMove == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        String move_ = movesSet.get(selectedMove).getName();
        DataBase data_ = getDataBase();
        simulation.chooseMoveFirstFight(NumberUtil.parseInt(selectedRound), selectedPk, move_, allyChoice, NumberUtil.parseInt(target), data_);
    }
    public void cancelMovesEvos() {
        displayIfError = false;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedMove = IndexConstants.INDEX_NOT_FOUND_ELT;
        ok = true;
        if (!enableEvolutions) {
            getForms().put(CST_SIMULATION_STATE, SimulationSteps.FRONT);
            stepNumber--;
            stepNumber--;
            return;
        }
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.MOVES);
        stepNumber--;
    }
    public void displayComments() {
        setupComments();
    }
    private void setupComments() {
        comments.clear();
        for (String l: simulation.getComment()) {
            comments.add(escapedStringQuote(l));
        }
    }
    public void hideComments() {
        comments.clear();
    }
    public void changeFight() {
        ok = true;
        DataBase data_ = getDataBase();
        simulation.validateTeam(data_);
        teamAfterFight.clear();
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber--;
    }
    public void changeFightWhileEnd() {
        ok = true;
        keptMovesAfterFight.clear();
        keptMovesAbilitiesDto.clear();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.SIMULATION);
        stepNumber--;
    }
    public void simulateFight() {
        ok = true;
        DataBase data_ = getDataBase();
        simulation.validateTeam(data_);
        simulation.simulateFight(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.SIMULATION);
        stepNumber++;
    }
    public void nextFight() {
        ok = true;
        keptMovesAbilitiesDto.clear();
        for (PokemonPlayer p: teamAfterFight) {
            KeptMovesAfterFight k_;
            k_ = new KeptMovesAfterFight();
            k_.setAbility(p.getAbility());
            k_.setEvolutions(new StringList(p.getName()));
            k_.setMoves(new StringList(p.getMoves().getKeys()));
            keptMovesAbilitiesDto.add(k_);
        }
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.EVO_AFTER_FIGHT);
        stepNumber++;
    }
    public boolean isFightAfter() {
        if (teamAfterFight.isEmpty()) {
            return false;
        }
        return simulation.hasNextFight();
    }
    public int[][] getImageAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.getMiniPk(pk_.getName());
    }
    public String getNameAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translatePokemon(pk_.getName());
    }
    public long getLevelAfterFight(int _index) {
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return pk_.getLevel();
    }
    public String getAbilityAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translateAbility(pk_.getAbility());
    }
    public String getGenderAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translateGenders(pk_.getGender());
    }
    public StringList getMovesAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves().getKeys()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemAfterFight(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
//        if (pk_.getItem().isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        return data_.translateItem(pk_.getItem());
    }
    public LgInt getRemainingLifeRate(int _index) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return pk_.rateRemainHp(data_);
    }
    public Rate numberNecessaryPointsForGrowingLevel(int _index){
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        long level_ = pk_.getLevel();
        Rate diff_ = FightFacade.numberNecessaryPointsForGrowingLevel(pk_.getName(),level_+1L,data_);
        diff_.removeNb(pk_.getWonExpSinceLastLevel());
        return diff_;
    }
    public void selectPkAfterFight() {
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        DataBase data_ = getDataBase();
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        evolutionAfterFight = k_.getEvolutions().last();
        abilityAfterFight = k_.getAbility();
        evolutionsAfterFight = DictionaryComparatorUtil.buildPkStr(data_,getLanguage());
        afterFighter(k_);
    }

    public void validateEvolutionAfterFight() {
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        k_.getEvolutions().add(evolutionAfterFight);
        afterFighter(k_);
        abilityAfterFight = abilitiesAfterFight.firstKey();
    }

    public void cancelEvolutionsAfterFight() {
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        String base_ = k_.getEvolutions().first();
        k_.getEvolutions().clear();
        k_.getEvolutions().add(base_);
        evolutionAfterFight = base_;
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        k_.getMoves().clear();
        k_.getMoves().addAllElts(pk_.getMoves().getKeys());
        k_.setAbility(pk_.getAbility());
        afterFighter(k_);
        abilityAfterFight = k_.getAbility();
    }

    private void afterFighter(KeptMovesAfterFight _k) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringList evolutions_ = pk_.directEvolutionsByStone(evolutionAfterFight, data_);
        for (String e: evolutions_) {
            evolutionsAfterFight.put(e, translationsPokemon_.getVal(e));
        }
        StringMap<BoolVal> selectedMoves_ = PokemonPlayer.getMovesForEvolution(pk_.getLevel(), _k.getMoves(), evolutionAfterFight, data_);
        keptMovesAfterFight.clear();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String m: selectedMoves_.getKeys()) {
            MoveData moveData_ = data_.getMoves().getVal(m);
            SelectLineMove line_ = MovesBean.buildLine(translationsMoves_,translationsTypes_,translationsCategories_,m,moveData_,getDataBase());
//            SelectLineMove line_ = new SelectLineMove();
//            line_.setName(m);
//            line_.setDisplayName(data_.translateMove(m));
//            line_.setCategory(data_.getTranslatedCategories().getVal(getLanguage()).getVal(moveData_.getCategory()));
//            StringList types_ = new StringList();
//            for (String t: moveData_.getTypes()) {
//                types_.add(translationsTypes_.getVal(t));
//            }
//            line_.setTypes(types_);
//            line_.setPp(moveData_.getPp());
//            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
//            if (line_.isDamageMove()) {
//                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
//                line_.setDirect(damag_.isDirect());
//            }
//            line_.setPriority(moveData_.getPriority());
            line_.setSelected(selectedMoves_.getVal(m) == BoolVal.TRUE);
            keptMovesAfterFight.add(line_);
        }
        keptMovesAfterFight.sortElts(new ComparatorMoves());
        //StringList moves_ = data_.getPokemon(lastPk_).getMovesBeforeLevel(pk_.getLevel());
        StringList abilities_ = data_.getPokemon(evolutionAfterFight).getAbilities();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesAfterFight = DictionaryComparatorUtil.buildAbilities(data_,getLanguage());
        for (String a: abilities_) {
            abilitiesAfterFight.put(a, translationsAbilities_.getVal(a));
        }
    }

    public void validateMovesAbilityAfterFight() {
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        StringList moves_ = new StringList();
        for (SelectLineMove l: keptMovesAfterFight) {
            if (l.isSelected()) {
                moves_.add(l.getName());
            }
        }
        k_.setMoves(moves_);
        k_.setAbility(abilityAfterFight);
    }
    public void validateMovesAfterFight() {
        ok = true;
        DataBase data_ = getDataBase();
        int len_ = teamAfterFight.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (keptMovesAbilitiesDto.get(i).getEvolutions().size() <= DataBase.ONE_POSSIBLE_CHOICE) {
                continue;
            }
            teamAfterFight.get(i).setAbility(keptMovesAbilitiesDto.get(i).getAbility());
            teamAfterFight.get(i).setName(keptMovesAbilitiesDto.get(i).getEvolutions().last());
            teamAfterFight.get(i).getMoves().clear();
            for (String m: keptMovesAbilitiesDto.get(i).getMoves()) {
                teamAfterFight.get(i).getMoves().put(m, new UsesOfMove(data_.getMove(m).getPp()));
            }
            teamAfterFight.get(i).fullHeal(data_);
        }
        team.clear();
        int i_ = IndexConstants.FIRST_INDEX;
        for (PokemonPlayer p: teamAfterFight) {
            PokemonPlayerDto pk_ = new PokemonPlayerDto();
            pk_.setIndex(i_);
            pk_.setPokemon(p);
            pk_.setMoves(new StringList(p.getMoves().getKeys()));
            team.add(pk_);
            i_++;
        }
        simulation.getTeam().clear();
        simulation.getTeam().addAllElts(teamAfterFight);
        simulation.nextFight();
        indexTeam++;
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber = 2;
    }
    public boolean isIssue() {
        return simulation.getProbleme();
    }
//    public boolean isIssueAfterFight() {
//        if (!simulation.isAcceptableChoices()) {
//            //the fight could not be finished
//            return false;
//        }
//        return simulation.getProbleme();
//    }
    public StringList getKoPlayers() {
        return translatePk(getDataBase(), simulation.getKoPlayers());
    }
    public StringList getNotKoFrontFoes() {
        return translatePk(getDataBase(), simulation.getNotKoFrontFoes());
    }
    public StringList getKoFoes() {
        return translatePk(getDataBase(), simulation.getKoFoes());
    }

    static StringList translatePk(DataBase _db, StringList _list) {
        StringList list_ = new StringList();
        for (String f: _list) {
            list_.add(_db.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }
//    public boolean isRulesIssue() {
//        return simulation.getIssue().isRules();
//    }
//    public boolean isRulesMovesIssue() {
//        return simulation.getIssue() == IssueSimulation.RULES_MOVES;
//    }
//    public boolean isRulesLearnIssue() {
//        return simulation.getIssue() == IssueSimulation.RULES_LEARN;
//    }
//    public boolean isRulesSwitchIssue() {
//        return simulation.getIssue() == IssueSimulation.RULES_SWITCH;
//    }
//    public boolean isSendingIssue() {
//        return simulation.getIssue() == IssueSimulation.SENDING_ISSUE;
//    }
//    public boolean isRandomIssue() {
//        return simulation.getIssue() == IssueSimulation.RANDOM;
//    }
//    public boolean isUsingIssue() {
//        return simulation.getIssue() == IssueSimulation.CANNOT_USE;
//    }
//    public boolean isHardSimulationIssue() {
//        return simulation.getIssue() == IssueSimulation.TOO_HARD_SIMULATION;
//    }
    public int getRealStepNumber() {
        return stepNumber + 1;
    }
    public String quit() {
        simulation = null;
        team.clear();
        teamAfterFight.clear();
        getForms().removeKey(CST_SIMULATION_STATE);
        getForms().removeKey(CST_COORDS);
        stepNumber = 0;
        ok = true;
        return PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML;
    }


    public DifficultyCommon getDifficultyCommon() {
        return difficultyCommon;
    }

//    public void setFreeTeams(boolean _freeTeams) {
//        freeTeams = _freeTeams;
//    }

    public boolean getFreeTeams() {
        return freeTeams;
    }

    public Ints getNumbers() {
        Ints i_ = new Ints();
        for (int i = 0; i < nbTeams; i++) {
            i_.add(i+1);
        }
        return i_;
    }
    public int getNbTeams() {
        return nbTeams;
    }

    public void setNbTeams(int _n) {
        this.nbTeams = NumberUtil.max(_n,0);
        freeTeams = _n > 0;
        indexTeam = 0;
        foeTeams.clear();
        allyTeams.clear();
        environmentsList.clear();
        multiplicities.clear();
        for (int i = 0; i < _n; i++) {
            foeTeams.add(new CustList<PokemonTrainerDto>());
            allyTeams.add(new CustList<PokemonTrainerDto>());
            environmentsList.add(EnvironmentType.ROAD);
            multiplicities.add(1);
        }
    }

    public int selectedTeamNumber() {
        return indexTeam+1;
    }

    public int getIndexTeam() {
        return indexTeam;
    }

    public void setIndexTeam(int _i) {
        this.indexTeam = _i;
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
    }

    public void setMultiplicity(int _multiplicity) {
        multiplicities.set(indexTeam,_multiplicity);
    }

    public int getMultiplicity() {
        return multiplicities.get(indexTeam);
    }

    public DictionaryComparator<String,String> getEnvironments() {
        return environments;
    }

    public String getEnvironment() {
        return environmentsList.get(indexTeam).getEnvName();
    }

    public void setEnvironment(String _environment) {
        environmentsList.set(indexTeam,EnvironmentType.getEnvByName(_environment));
    }

    public CustList<PokemonTrainerDto> getFoeTeam() {
        return foeTeams.get(indexTeam);
    }

    public void setSelectedFoePk(int _selectedFoePk) {
        selectedFoePk = _selectedFoePk;
    }

    public int getSelectedFoePk() {
        return selectedFoePk;
    }

    public void setSelectedFoeAction(String _selectedFoeAction) {
        selectedFoeAction = _selectedFoeAction;
    }

    public String getSelectedFoeAction() {
        return selectedFoeAction;
    }

    public CustList<PokemonTrainerDto> getAllyTeam() {
        return allyTeams.get(indexTeam);
    }

    public void setSelectedAllyPk(int _selectedAllyPk) {
        selectedAllyPk = _selectedAllyPk;
    }

    public int getSelectedAllyPk() {
        return selectedAllyPk;
    }

    public void setSelectedAllyAction(String _selectedAllyAction) {
        selectedAllyAction = _selectedAllyAction;
    }

    public String getSelectedAllyAction() {
        return selectedAllyAction;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }

    public boolean getOk() {
        return ok;
    }

    public CustList<PokemonPlayerDto> getTeam() {
        return team;
    }

    public void setSelectedPk(int _selectedPk) {
        selectedPk = _selectedPk;
    }

    public int getSelectedPk() {
        return selectedPk;
    }

    public void setSelectedAction(String _selectedAction) {
        selectedAction = _selectedAction;
    }

    public String getSelectedAction() {
        return selectedAction;
    }

    public DictionaryComparator<String,String> getAvailableEvos() {
        return availableEvos;
    }

    public String getChosenEvo() {
        return chosenEvo;
    }

    public void setChosenEvo(String _chosenEvo) {
        chosenEvo = _chosenEvo;
    }

    public void setLevelEvo(long _levelEvo) {
        levelEvo = _levelEvo;
    }

    public long getLevelEvo() {
        return levelEvo;
    }

    public IntTreeMap<Integer> getRound() {
        return round;
    }

    public String getSelectedRound() {
        return selectedRound;
    }

    public void setSelectedRound(String _selectedRound) {
        selectedRound = _selectedRound;
    }

    public IntTreeMap<String> getPlacesFight() {
        return placesFight;
    }

    public String getPlaceFight() {
        return placeFight;
    }

    public void setPlaceFight(String _placeFight) {
        placeFight = _placeFight;
    }

    public boolean getDisplayIfError() {
        return displayIfError;
    }

    public DictionaryComparator<String,String> getAbilities() {
        return abilities;
    }

    public String getCurrentAbility() {
        return currentAbility;
    }

    public void setCurrentAbility(String _currentAbility) {
        currentAbility = _currentAbility;
    }

    public CustList<SelectLineMove> getKeptMoves() {
        return keptMoves;
    }

    public CustList<RadioLineMove> getMovesSet() {
        return movesSet;
    }

    public void setSelectedMove(int _selectedMove) {
        selectedMove = _selectedMove;
    }

    public int getSelectedMove() {
        return selectedMove;
    }

    public void setAllyChoice(boolean _allyChoice) {
        allyChoice = _allyChoice;
    }

    public boolean getAllyChoice() {
        return allyChoice;
    }

    public IntTreeMap<String> getTargetFight() {
        return targetFight;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String _target) {
        target = _target;
    }

    public StringList getComments() {
        return comments;
    }

    public CustList<PokemonPlayer> getTeamAfterFight() {
        return teamAfterFight;
    }

    public DictionaryComparator<String,String> getEvolutionsAfterFight() {
        return evolutionsAfterFight;
    }

    public String getEvolutionAfterFight() {
        return evolutionAfterFight;
    }

    public void setEvolutionAfterFight(String _evolutionAfterFight) {
        evolutionAfterFight = _evolutionAfterFight;
    }

    public DictionaryComparator<String,String> getAbilitiesAfterFight() {
        return abilitiesAfterFight;
    }

    public String getAbilityAfterFight() {
        return abilityAfterFight;
    }

    public void setAbilityAfterFight(String _abilityAfterFight) {
        abilityAfterFight = _abilityAfterFight;
    }

    public CustList<SelectLineMove> getKeptMovesAfterFight() {
        return keptMovesAfterFight;
    }
}