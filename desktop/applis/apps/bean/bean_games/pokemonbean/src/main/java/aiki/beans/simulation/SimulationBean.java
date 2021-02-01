package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.comparators.*;
import aiki.beans.facade.dto.KeptMovesAfterFight;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.dto.PokemonTrainerDto;
import aiki.beans.facade.simulation.dto.RadioLineMove;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.FightSimulation;
import aiki.game.fight.Fighter;
import aiki.game.fight.KeyFightRound;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.AvailableMovesInfos;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
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
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class SimulationBean extends CommonBean {
    private boolean allowCatchingKo;
    private boolean allowedSwitchPlacesEndRound;
    private String diffWinningExpPtsFight;
    private TreeMap<String, String> winPointsFight;
    private Rate rateWinningExpPtsFight;
    private Rate winTrainerExp;
    private TreeMap<String, String> damageRates;
    private String damageRatePlayer;
    private NatCmpTreeMap<Rate,Rate> damageRatePlayerTable;
    private String damageRateLawFoe;
    private NatCmpTreeMap<Rate,Rate> damageRateFoeTable;
    private boolean endFightIfOneTeamKo;
    private Rate rateWinMoneyBase;
    private Rate rateLooseMoneyWin;
    private int ivPlayer;
    private int ivFoe;
    private boolean stillPossibleFlee;
    private boolean restoredMovesEndFight;
    private boolean enabledClosing;
    private boolean randomWildFight;
    private boolean skipLearningMovesWhileNotGrowingLevel;
    private CustList<PlaceIndex> places = new CustList<PlaceIndex>();

    private Coords coords;
    private boolean freeTeams;
    private int selectedFoePk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final CustList<PokemonTrainerDto> foeTeam = new CustList<PokemonTrainerDto>();
    private int selectedAllyPk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final CustList<PokemonTrainerDto> allyTeam = new CustList<PokemonTrainerDto>();
    private int multiplicity = 1;

    private int nbMaxActions;
    private String environment = EnvironmentType.ROAD.name();
    private TreeMap<String, String> environments;

    private boolean enableEvolutions = true;
    private final CustList<PokemonPlayerDto> team = new CustList<PokemonPlayerDto>();
    private int selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private String selectedFoeAction = TeamCrud.NOTHING.name();
    private String selectedAllyAction = TeamCrud.NOTHING.name();
    private String selectedAction = TeamCrud.NOTHING.name();

    private StringMap<Short> availableEvosLevel = new StringMap<Short>();
    private TreeMap<String, String> availableEvos;

    private String chosenEvo = DataBase.EMPTY_STRING;
    private int levelEvo;

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
    private TreeMap<String,String> abilities;
    private final CustList<SelectLineMove> keptMoves = new CustList<SelectLineMove>();
    private final CustList<RadioLineMove> movesSet = new CustList<RadioLineMove>();
    private int selectedMove;
    private boolean allyChoice;
    private final StringList comments = new StringList();

    private CustList<PokemonPlayer> teamAfterFight = new CustList<PokemonPlayer>();
    private final CustList<KeptMovesAfterFight> keptMovesAbilitiesDto = new CustList<KeptMovesAfterFight>();
    private final CustList<SelectLineMove> keptMovesAfterFight = new CustList<SelectLineMove>();
    private String evolutionAfterFight = DataBase.EMPTY_STRING;
    private TreeMap<String,String> evolutionsAfterFight;
    private String abilityAfterFight = DataBase.EMPTY_STRING;
    private TreeMap<String,String> abilitiesAfterFight;
    private int stepNumber;
    private boolean ok;
    private boolean displayIfError;

    @Override
    public void beforeDisplaying() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        DataBase data_ = (DataBase) getDataBase();
        if (simu_ == SimulationSteps.DIFF) {

            damageRates = new TreeMap<String, String>(new ComparatorDifficultyModelLaw());
            winPointsFight = new TreeMap<String, String>(new ComparatorDifficultyWinPointsFight());
            EnumMap<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
            for (DifficultyWinPointsFight k: trWinPts_.getKeys()) {
//                winPointsFight.put(k, XmlParser.transformSpecialChars(trWinPts_.getVal(k)));
                winPointsFight.put(k.name(), trWinPts_.getVal(k));
            }
            EnumMap<DifficultyModelLaw, String> trWinLaw_ = data_.getTranslatedDiffModelLaw().getVal(getLanguage());
            for (DifficultyModelLaw k: trWinLaw_.getKeys()) {
//                damageRates.put(k, XmlParser.transformSpecialChars(trWinLaw_.getVal(k)));
                damageRates.put(k.name(), trWinLaw_.getVal(k));
            }
            diffWinningExpPtsFight = difficulty.getDiffWinningExpPtsFight().name();
            allowCatchingKo = difficulty.getAllowCatchingKo();
            allowedSwitchPlacesEndRound = difficulty.getAllowedSwitchPlacesEndRound();
            winTrainerExp = difficulty.getWinTrainerExp();
            rateWinningExpPtsFight = difficulty.getRateWinningExpPtsFight();
            endFightIfOneTeamKo = difficulty.getEndFightIfOneTeamKo();
            ivPlayer = difficulty.getIvPlayer();
            ivFoe = difficulty.getIvFoe();
            rateWinMoneyBase = difficulty.getRateWinMoneyBase();
            rateLooseMoneyWin = difficulty.getRateLooseMoneyWin();
            stillPossibleFlee = difficulty.getStillPossibleFlee();
            restoredMovesEndFight = difficulty.getRestoredMovesEndFight();
            enabledClosing = difficulty.getEnabledClosing();
            randomWildFight = difficulty.getRandomWildFight();
            skipLearningMovesWhileNotGrowingLevel = difficulty.isSkipLearningMovesWhileNotGrowingLevel();
            damageRatePlayer = difficulty.getDamageRatePlayer().name();
            damageRateLawFoe = difficulty.getDamageRateLawFoe().name();
            damageRatePlayerTable = new NatCmpTreeMap<Rate, Rate>();
            MonteCarloNumber law_;
            law_ = data_.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRatePlayer)).getLaw();
            for (Rate e: law_.events()) {
                damageRatePlayerTable.put(e, law_.normalizedRate(e));
            }
            damageRateFoeTable = new NatCmpTreeMap<Rate, Rate>();
            law_ = data_.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRateLawFoe)).getLaw();
            for (Rate e: law_.events()) {
                damageRateFoeTable.put(e, law_.normalizedRate(e));
            }
        } else if (simu_ == SimulationSteps.FOE) {
            if (freeTeams) {
                if (!foeTeam.isEmpty()) {
                    ok = true;
                }
                boolean remove_ = getForms().getVal(CST_ADDING_TRAINER_PK) == TeamCrud.REMOVE;
                if (remove_) {
                    return;
                }
                boolean nothing_ = getForms().getVal(CST_ADDING_TRAINER_PK) == TeamCrud.NOTHING;
                if (nothing_) {
                    return;
                }
                boolean add_ = getForms().getVal(CST_ADDING_TRAINER_PK) == TeamCrud.ADD;
                if (add_) {
                    boolean foe_ = (Boolean) getForms().getVal(CST_POKEMON_FOE);
                    PokemonTrainerDto pk_;
                    pk_ = new PokemonTrainerDto();
                    pk_.getMoves().addAllElts((StringList) getForms().getVal(CST_POKEMON_MOVES_EDIT));
                    pk_.setAbility((String) getForms().getVal(CST_POKEMON_ABILITY_EDIT));
                    pk_.setName((String) getForms().getVal(CST_POKEMON_NAME_EDIT));
                    pk_.setGender((Gender) getForms().getVal(CST_POKEMON_GENDER_EDIT));
                    pk_.setItem((String) getForms().getVal(CST_ITEM_EDIT));
                    pk_.setLevel((Integer) getForms().getVal(CST_POKEMON_LEVEL_EDIT));
                    if (foe_) {
                        pk_.setIndex(foeTeam.size());
                        foeTeam.add(pk_);
                    } else {
                        pk_.setIndex(allyTeam.size());
                        allyTeam.add(pk_);
                    }
                } else {
                    boolean foe_ = (Boolean) getForms().getVal(CST_POKEMON_FOE);
                    PokemonTrainerDto pk_;
                    if (foe_) {
                        pk_ = foeTeam.get(selectedFoePk);
                    } else {
                        pk_ = allyTeam.get(selectedAllyPk);
                    }
                    pk_.getMoves().clear();
                    pk_.getMoves().addAllElts((StringList) getForms().getVal(CST_POKEMON_MOVES_EDIT));
                    pk_.setAbility((String) getForms().getVal(CST_POKEMON_ABILITY_EDIT));
                    pk_.setName((String) getForms().getVal(CST_POKEMON_NAME_EDIT));
                    pk_.setGender((Gender) getForms().getVal(CST_POKEMON_GENDER_EDIT));
                    pk_.setItem((String) getForms().getVal(CST_ITEM_EDIT));
                    pk_.setLevel((Integer) getForms().getVal(CST_POKEMON_LEVEL_EDIT));
                }
            } else {
                if (!getForms().contains(CST_NO_FIGHT)) {
                    getForms().put(CST_NO_FIGHT, (int) IndexConstants.FIRST_INDEX);
                }
                noFight = (Integer) getForms().getVal(CST_NO_FIGHT);
                coords = (Coords) getForms().getVal(CST_COORDS);
                if (coords != null) {
                    ok = true;
                }
                places = new CustList<PlaceIndex>();
                short i_ = 0;
                for (Place p: data_.getMap().getPlaces()) {
                    PlaceIndex pl_ = new PlaceIndex();
                    pl_.setIndex(i_);
                    pl_.setPlace(p);
                    places.add(pl_);
                    i_++;
                }
                places.sortElts(new ComparatorPlaceIndex());
            }
        } else if (simu_ == SimulationSteps.TEAM) {
            if (!simulation.getTeam().isEmpty()) {
                ok = true;
            }
            if (getForms().contains(CST_POKEMON_ADDED)) {
                PokemonPlayerDto pk_ = (PokemonPlayerDto) getForms().getVal(CST_POKEMON_ADDED);
                getForms().removeKey(CST_POKEMON_ADDED);
                pk_.setIndex(team.size());
                team.add(pk_);
                simulation.addPokemonPlayer(pk_.getPokemon(), pk_.getMoves(), (byte) 0, Rate.zero(), data_);
            } else if(getForms().contains(CST_POKEMON_INDEX_EDIT)) {
                int index_ = (Integer) getForms().getVal(CST_POKEMON_INDEX_EDIT);
                getForms().removeKey(CST_POKEMON_INDEX_EDIT);
                team.get(index_).setMoves((StringList) getForms().getVal(CST_POKEMON_MOVES_EDIT));
                getForms().removeKey(CST_POKEMON_MOVES_EDIT);
                team.get(index_).getPokemon().setItem((String) getForms().getVal(CST_ITEM_EDIT));
                getForms().removeKey(CST_ITEM_EDIT);
                simulation.setPokemonPlayerObject(index_, team.get(index_).getPokemon().getItem());
                Rate exp_ = (Rate) getForms().getVal(CST_POKEMON_EXPERIENCE);
                getForms().removeKey(CST_POKEMON_EXPERIENCE);
                PokemonPlayer pkPlayer_ = simulation.getTeam().get(index_);
                pkPlayer_.setWonExpSinceLastLevel(exp_);
                String ball_ = (String) getForms().getVal(CST_CATCHING_BALL);
                getForms().removeKey(CST_CATCHING_BALL);
                pkPlayer_.setUsedBallCatching(ball_);
                int happy_ = (Integer) getForms().getVal(CST_POKEMON_HAPPINESS);
                getForms().removeKey(CST_POKEMON_HAPPINESS);
                Rate hp_ = (Rate) getForms().getVal(CST_POKEMON_HP);
                getForms().removeKey(CST_POKEMON_HP);
                boolean heal_ = (Boolean) getForms().getVal(CST_HEAL_EDIT_PK);
                getForms().removeKey(CST_HEAL_EDIT_PK);
                for (Statistic s:Statistic.getStatisticsWithBase()) {
                    int ev_ = (Integer) getForms().getVal(StringUtil.concat(CST_POKEMON_EV_VAR, s.name()));
                    getForms().removeKey(StringUtil.concat(CST_POKEMON_EV_VAR,s.name()));
                    if (ev_ > data_.getMaxEv()) {
                        ev_ = (short) data_.getMaxEv();
                    }
                    pkPlayer_.getEv().put(s, (short) ev_);
                }
                if (Rate.strGreater(hp_, pkPlayer_.pvMax(data_)) || hp_.isZeroOrLt() || heal_) {
                    pkPlayer_.setRemainingHp(pkPlayer_.pvMax(data_));
                } else {
                    pkPlayer_.setRemainingHp(hp_);
                }
                pkPlayer_.setHappiness((short) happy_);
                simulation.setInitialMoves(index_, team.get(index_).getMoves(), data_);
                getForms().removeKey(CST_ITEMS_SET_EDIT);
                getForms().removeKey(CST_POKEMON_NAME_EDIT);
                getForms().removeKey(CST_POKEMON_LEVEL_EDIT);
            }
        } else if (simu_ == SimulationSteps.FRONT) {
            round = new IntTreeMap< Integer>();
            placesFight = new IntTreeMap< String>();
            int nbRounds_ = simulation.nbRounds();
            for (int i = IndexConstants.FIRST_INDEX; i < nbRounds_; i++) {
                round.put(i, i);
            }
            int mult_ = simulation.getFirstMult();
            placesFight.put((int) Fighter.BACK, DataBase.EMPTY_STRING);
            for (int i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
                placesFight.put(i, Long.toString(i));
            }
            //display front fighters
        } else if (simu_ == SimulationSteps.MOVES) {
            //.
            StringMap<String> translationsAbilities_;
            translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
            abilities = new TreeMap<String, String>(new ComparatorTrStrings(translationsAbilities_));
            currentAbility = DataBase.EMPTY_STRING;
            if (selectedIndexForMoves()) {
                if (isAvailableMoves()) {
                    if (isAvailableAbilities()) {
                        for (String a: getAvailableAbilities()) {
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
                    info_ = simulation.getAvailableMoves().getVal((byte) selectedPk);
                    for (String k: info_.getMoves().getKeys()) {
                        MoveData moveData_ = data_.getMoves().getVal(k);
                        SelectLineMove line_ = new SelectLineMove();
                        line_.setName(k);
                        line_.setDisplayName(translationsMoves_.getVal(k));
                        StringList types_ = new StringList();
                        for (String t: moveData_.getTypes()) {
                            types_.add(translationsTypes_.getVal(t));
                        }
                        line_.setTypes(types_);
                        line_.setPp(moveData_.getPp());
                        line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
                        line_.setDamageMove(moveData_ instanceof DamagingMoveData);
                        if (line_.isDamageMove()) {
                            DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                            line_.setDirect(damag_.isDirect());
                        }
                        line_.setPriority(moveData_.getPriority());
                        line_.setSelected(info_.getMoves().getVal(k));
                        keptMoves.add(line_);
                    }
                    keptMoves.sortElts(new ComparatorMoves());
                }
            }

        } else if (simu_ == SimulationSteps.MOVES_FIGHT) {
            if (selectedPk != IndexConstants.INDEX_NOT_FOUND_ELT) {
                targetFight = new IntTreeMap< String>();
                int mult_ = simulation.getFirstMult();
                for (int i = IndexConstants.FIRST_INDEX; i < mult_; i++) {
                    targetFight.put(i, Long.toString(i));
                }
                movesSet.clear();
                if (NumberUtil.parseInt(selectedRound) == 0) {
                    StringMap<String> translationsMoves_;
                    translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
                    StringMap<String> translationsTypes_;
                    translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
                    StringMap<String> translationsCategories_;
                    translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
                    StringList moves_ = team.get(selectedPk).getMoves();
                    for (String k: moves_) {
                        MoveData moveData_ = data_.getMoves().getVal(k);
                        RadioLineMove line_ = new RadioLineMove();
                        line_.setName(k);
                        line_.setDisplayName(translationsMoves_.getVal(k));
                        StringList types_ = new StringList();
                        for (String t: moveData_.getTypes()) {
                            types_.add(translationsTypes_.getVal(t));
                        }
                        line_.setTypes(types_);
                        line_.setPp(moveData_.getPp());
                        line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
                        line_.setDamageMove(moveData_ instanceof DamagingMoveData);
                        if (line_.isDamageMove()) {
                            DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                            line_.setDirect(damag_.isDirect());
                        }
                        line_.setPriority(moveData_.getPriority());
                        movesSet.add(line_);
                    }
                    int i_ = IndexConstants.FIRST_INDEX;
                    movesSet.sortElts(new ComparatorRadioLineMoves());
                    for (RadioLineMove l: movesSet) {
                        l.setIndex(i_);
                        i_++;
                    }
                    return;
                }
                StringMap<String> translationsMoves_;
                translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
                StringMap<String> translationsTypes_;
                translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
                StringMap<String> translationsCategories_;
                translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
                StringList moves_ = simulation.getKeptMoves().getVal((byte) selectedPk).getVal(new KeyFightRound(IndexConstants.FIRST_INDEX, (byte)(NumberUtil.parseInt(selectedRound) -1)));
                for (String k: moves_) {
                    MoveData moveData_ = data_.getMoves().getVal(k);
                    RadioLineMove line_ = new RadioLineMove();
                    line_.setName(k);
                    line_.setDisplayName(translationsMoves_.getVal(k));
                    StringList types_ = new StringList();
                    for (String t: moveData_.getTypes()) {
                        types_.add(translationsTypes_.getVal(t));
                    }
                    line_.setTypes(types_);
                    line_.setPp(moveData_.getPp());
                    line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
                    line_.setDamageMove(moveData_ instanceof DamagingMoveData);
                    if (line_.isDamageMove()) {
                        DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                        line_.setDirect(damag_.isDirect());
                    }
                    line_.setPriority(moveData_.getPriority());
                    movesSet.add(line_);
                }
                int i_ = IndexConstants.FIRST_INDEX;
                movesSet.sortElts(new ComparatorRadioLineMoves());
                for (RadioLineMove l: movesSet) {
                    l.setIndex(i_);
                    i_++;
                }
            }
        } else if (simu_ == SimulationSteps.SIMULATION) {
            if (isIssue()) {
                comments.clear();
                for (String l: simulation.getComment()) {
                    comments.add(escapedStringQuote(l));
                }
            }
            teamAfterFight = new CustList<PokemonPlayer>();
            if (!simulation.getProbleme()) {
                teamAfterFight = simulation.getTeamAfterFight();
            }
        }
    }
    public boolean isDiffState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.DIFF;
    }
    public boolean isFoeState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FOE;
    }
    public boolean isTeamState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.TEAM;
    }
    public boolean isEvolutionsState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.EVOLUTIONS;
    }
    public boolean isFrontState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FRONT;
    }
    public boolean isMovesState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES;
    }
    public boolean isMovesFightState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES_FIGHT;
    }
    public boolean isSimulationState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.SIMULATION;
    }
    public boolean isEvolutionAfterFightState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(CST_SIMULATION_STATE);
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
        Place pl_ = data_.getMap().getPlace(_coords.getNumberPlace());
        Level level_ = pl_.getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym g_ = (LevelIndoorGym) level_;
            if (Point.eq(g_.getGymLeaderCoords(), _coords.getLevel().getPoint())) {
                return g_.getGymLeader().getName();
            }
            return DataBase.EMPTY_STRING;
        }
        if (level_ instanceof LevelLeague) {
            LevelLeague l_ = (LevelLeague) level_;
            return l_.getTrainer().getName();
        }
        if (level_ instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
            if (w_.getDualFights().contains(_coords.getLevel().getPoint())) {
                return StringUtil.join(w_.getDualFights().getVal(_coords.getLevel().getPoint()).getNames(), CST_SPACE);
            }
        }
        return DataBase.EMPTY_STRING;
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        //getForms().removeKey(INSIDE);
        DataBase data_ = (DataBase) getDataBase();
        Place pl_ = data_.getMap().getPlace((short)_indexOne);
        if (pl_ instanceof League) {
            League l_ = (League) pl_;
            coords = new Coords();
            coords.setNumberPlace((short)_indexOne);
            coords.setLevel(new LevelPoint());
            coords.getLevel().setLevelIndex((byte)_indexTwo);
            coords.getLevel().setPoint(new Point(((LevelLeague)l_.getLevelsList().first()).getTrainerCoords()));
            getForms().put(CST_COORDS, coords);
            noFight = IndexConstants.FIRST_INDEX;
            return DataBase.EMPTY_STRING;
        }
        if (pl_ instanceof City) {
            City c_ = (City) pl_;
            for (Point p: c_.getBuildings().getKeys()) {
                Building b_ = c_.getBuildings().getVal(p);
                if (b_ instanceof Gym) {
                    getForms().put(CST_LEVEL_MAP_INDEX, _indexTwo);
                    getForms().put(CST_PLACE_MAP_INDEX, _indexOne);
                    getForms().put(CST_INSIDE, new Point(p));
                    return CST_LEVEL;
                }
            }
        }
        getForms().removeKey(CST_INSIDE);
        getForms().put(CST_LEVEL_MAP_INDEX, _indexTwo);
        getForms().put(CST_PLACE_MAP_INDEX, _indexOne);
        return CST_LEVEL;
    }
    public void validateDiffChoice() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        difficulty.setDiffWinningExpPtsFight(PokemonStandards.getDiffWonPtsByName(diffWinningExpPtsFight));
        difficulty.setAllowCatchingKo(allowCatchingKo);
        difficulty.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound);
        difficulty.setWinTrainerExp(winTrainerExp);
        difficulty.setRateWinningExpPtsFight(rateWinningExpPtsFight);
        difficulty.setEndFightIfOneTeamKo(endFightIfOneTeamKo);
        difficulty.setIvPlayer((short) ivPlayer);
        difficulty.setIvFoe((short) ivFoe);
        difficulty.setRateWinMoneyBase(rateWinMoneyBase);
        difficulty.setRateLooseMoneyWin(rateLooseMoneyWin);
        difficulty.setRestoredMovesEndFight(restoredMovesEndFight);
        difficulty.setEnabledClosing(enabledClosing);
        difficulty.setRandomWildFight(randomWildFight);
        difficulty.setStillPossibleFlee(stillPossibleFlee);
        difficulty.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel);
        difficulty.setDamageRateLawFoe(PokemonStandards.getModelByName(damageRateLawFoe));
        difficulty.setDamageRatePlayer(PokemonStandards.getModelByName(damageRatePlayer));
        difficulty.validate(data_);
        simulation = new FightSimulation(difficulty, data_);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber++;
        StringMap<String> translated_ = new StringMap<String>();
        EnumMap<EnvironmentType, String> tr_;
        tr_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        for (EntryCust<EnvironmentType,String> s: tr_.entryList()) {
            translated_.addEntry(s.getKey().name(),s.getValue());
        }
        if (freeTeams) {
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
            environments = new TreeMap<String, String>(new ComparatorTrString(translated_));
            environments.putAllMap(translated_);
            selectedFoePk = IndexConstants.INDEX_NOT_FOUND_ELT;
            selectedAllyPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        } else {
            environments = new TreeMap<String, String>(new ComparatorTrString(translated_));
        }
    }
    public void cancelDiffChoice() {
        ok = true;
        foeTeam.clear();
        allyTeam.clear();
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
        DataBase data_ = (DataBase) getDataBase();
        simulation.initializeFight(coords, noFight, data_);
        allyTeam.clear();
        foeTeam.clear();
        for (PkTrainer p: simulation.getAllyTeam()) {
            allyTeam.add(PokemonTrainerDto.fromPokemonTrainer(p));
        }
        for (PkTrainer p: simulation.getFoeTeam()) {
            foeTeam.add(PokemonTrainerDto.fromPokemonTrainer(p));
        }
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.name();
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }
    public String getImageFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(pk_.getName()));
    }
    public String getNameFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        return data_.translatePokemon(pk_.getName());
    }
    public int getLevelFoe(int _index) {
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        return pk_.getLevel();
    }
    public String getAbilityFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        return data_.translateAbility(pk_.getAbility());
    }
    public String getGenderFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        return data_.translateGenders(pk_.getGender());
    }
    public StringList getMovesFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemFoe(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index);
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }
    public String selectFoePk() {
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedFoePk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.EDIT) {
            getForms().put(CST_POKEMON_FOE, true);
            getForms().put(CST_ITEMS_SET_EDIT, new StringList());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedFoePk);
            getForms().put(CST_POKEMON_NAME_EDIT, foeTeam.get(selectedFoePk).getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, foeTeam.get(selectedFoePk).getLevel());
            getForms().put(CST_ITEM_EDIT, foeTeam.get(selectedFoePk).getItem());
            getForms().put(CST_POKEMON_GENDER_EDIT, foeTeam.get(selectedFoePk).getGender());
            getForms().put(CST_POKEMON_MOVES_EDIT, foeTeam.get(selectedFoePk).getMoves());
            getForms().put(CST_POKEMON_ABILITY_EDIT, foeTeam.get(selectedFoePk).getAbility());
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return CST_POKEMON_EDIT;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.REMOVE) {
            int index_ = selectedFoePk;
            foeTeam.remove(index_);
            int size_ = foeTeam.size();
            for (int i = index_; i < size_;i++) {
                foeTeam.get(i).setIndex(i);
            }
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return DataBase.EMPTY_STRING;
    }
    public String getImageAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(pk_.getName()));
    }
    public String getNameAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        return data_.translatePokemon(pk_.getName());
    }
    public int getLevelAlly(int _index) {
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        return pk_.getLevel();
    }
    public String getAbilityAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        return data_.translateAbility(pk_.getAbility());
    }
    public String getGenderAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        return data_.translateGenders(pk_.getGender());
    }
    public StringList getMovesAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemAlly(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index);
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }
    public String selectAllyPk() {
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedAllyPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.EDIT) {
            getForms().put(CST_POKEMON_FOE, false);
            getForms().put(CST_ITEMS_SET_EDIT, new StringList());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedAllyPk);
            getForms().put(CST_POKEMON_NAME_EDIT, allyTeam.get(selectedAllyPk).getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, allyTeam.get(selectedAllyPk).getLevel());
            getForms().put(CST_ITEM_EDIT, allyTeam.get(selectedAllyPk).getItem());
            getForms().put(CST_POKEMON_GENDER_EDIT, allyTeam.get(selectedAllyPk).getGender());
            getForms().put(CST_POKEMON_MOVES_EDIT, allyTeam.get(selectedAllyPk).getMoves());
            getForms().put(CST_POKEMON_ABILITY_EDIT, allyTeam.get(selectedAllyPk).getAbility());
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return CST_POKEMON_EDIT;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.REMOVE) {
            int index_ = selectedAllyPk;
            allyTeam.remove(index_);
            int size_ = allyTeam.size();
            for (int i = index_; i < size_;i++) {
                allyTeam.get(i).setIndex(i);
            }
            getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return DataBase.EMPTY_STRING;
    }
    public String addPkTrainer() {
        DataBase data_ = (DataBase) getDataBase();
        Pokemon pk_ = data_.getMap().getFirstPokemon();
        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesBeforeLevel(pk_.getLevel());
        getForms().put(CST_POKEMON_NAME_EDIT, pk_.getName());
        getForms().put(CST_POKEMON_LEVEL_EDIT,(int) pk_.getLevel());
        getForms().put(CST_ITEM_EDIT, pk_.getItem());
        getForms().put(CST_POKEMON_GENDER_EDIT, Gender.NO_GENDER);
        getForms().put(CST_POKEMON_MOVES_EDIT, moves_);
        getForms().put(CST_POKEMON_ABILITY_EDIT, pk_.getAbility());
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.ADD);
        getForms().put(CST_ITEMS_SET_EDIT, new StringList());
        return CST_POKEMON_EDIT;
    }
    public void validateFoeChoiceFree() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        coords = new Coords(data_.getMap().getBegin());
        if (foeTeam.isEmpty()) {
            ok = false;
            return;
        }
        if (multiplicity <= 0) {
            multiplicity = 1;
        }
        if (multiplicity >= DataBase.MAX_MULT_FIGHT) {
            multiplicity = DataBase.MAX_MULT_FIGHT;
        }
        nbMaxActions = multiplicity;
        if (!allyTeam.isEmpty()) {
            multiplicity = 2;
            nbMaxActions = 1;
        }
        CustList<PkTrainer> ally_;
        ally_ = new CustList<PkTrainer>();
        for (PokemonTrainerDto p: allyTeam) {
            ally_.add(p.toPokemonTrainer());
        }
        CustList<PkTrainer> foe_;
        foe_ = new CustList<PkTrainer>();
        for (PokemonTrainerDto p: foeTeam) {
            foe_.add(p.toPokemonTrainer());
        }
        simulation.setTeams(ally_, foe_, multiplicity, nbMaxActions, PokemonStandards.getEnvByName(environment), coords);
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.name();
        getForms().removeKey(CST_POKEMON_INDEX_EDIT);
        getForms().removeKey(CST_POKEMON_ADDED);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }
    public String selectPk() {
        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.EDIT) {
            getForms().put(CST_ITEMS_SET_EDIT, new StringList());
            getForms().put(CST_POKEMON_INDEX_EDIT, selectedPk);
            getForms().put(CST_POKEMON_NAME_EDIT, team.get(selectedPk).getPokemon().getName());
            getForms().put(CST_POKEMON_LEVEL_EDIT, (int)team.get(selectedPk).getPokemon().getLevel());
            getForms().put(CST_ITEM_EDIT, team.get(selectedPk).getPokemon().getItem());
            getForms().put(CST_POKEMON_MOVES_EDIT, team.get(selectedPk).getMoves());
            getForms().put(CST_POKEMON_EXPERIENCE, simulation.getTeam().get(selectedPk).getWonExpSinceLastLevel());
            getForms().put(CST_POKEMON_HAPPINESS, (int)simulation.getTeam().get(selectedPk).getHappiness());
            getForms().put(CST_HEAL_EDIT_PK, false);
            getForms().put(CST_POKEMON_HP, simulation.getTeam().get(selectedPk).getRemainingHp());
            getForms().put(CST_CATCHING_BALL, simulation.getTeam().get(selectedPk).getUsedBallCatching());
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                getForms().put(StringUtil.concat(CST_POKEMON_EV_VAR,s.name()), (int)simulation.getTeam().get(selectedPk).getEv().getVal(s));
            }
            return CST_EDIT_POKEMON_PLAYER;
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
        return DataBase.EMPTY_STRING;
    }
    public String add() {
        getForms().put(CST_POKEMON_SET_SIMU, new StringList());
        return CST_ADD_POKEMON_PLAYER;
    }
    public String getImage(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(pk_.getPokemon().getName()));
    }
    public String getName(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translatePokemon(pk_.getPokemon().getName());
    }
    public int getLevel(int _index) {
        PokemonPlayerDto pk_ = team.get(_index);
        return pk_.getPokemon().getLevel();
    }
    public String getAbility(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translateAbility(pk_.getPokemon().getAbility());
    }
    public String getGender(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        return data_.translateGenders(pk_.getPokemon().getGender());
    }
    public StringList getMoves(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItem(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index);
        if (pk_.getPokemon().getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
        //check if all pokemon have the maximum moves
        for (PokemonPlayer p: simulation.getTeam()) {
            if (p.getMoves().size() != data_.getNbMaxMoves()) {
                return;
            }
        }
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
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber--;
    }
    public void cancelEvolutions() {
        ok = true;
        simulation.cancelEvolutions();
        getForms().removeKey(CST_POKEMON_INDEX_EDIT);
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.name();
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
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            if (availableEvos != null) {
                availableEvos.clear();
            }
            return;
        }
        getForms().put(CST_POKEMON_INDEX_EDIT, selectedPk);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(selectedPk);
        DataBase data_ = (DataBase) getDataBase();
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }
    public void validateEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = (DataBase) getDataBase();
        int index_ = (Integer) getForms().getVal(CST_POKEMON_INDEX_EDIT);
        levelEvo = (short) Math.max(levelEvo, availableEvosLevel.getVal(chosenEvo));
        simulation.setNextEvolutions(index_, chosenEvo, (short) levelEvo, data_);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(index_);
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }
    public void cancelEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = (DataBase) getDataBase();
        int index_ = (Integer) getForms().getVal(CST_POKEMON_INDEX_EDIT);
        simulation.cancelEvolutions(index_, data_);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(index_);
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }
    public void validateFrontFighter() {
        displayIfError = false;
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        simulation.getFrontFighters().first().get(NumberUtil.parseInt(selectedRound)).put((byte) selectedPk, (byte) NumberUtil.parseInt(placeFight));
    }
    public void cancelFrontFighters() {
        displayIfError = false;
        ok = true;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.cancelEvolutions();
            getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
            stepNumber--;
            stepNumber--;
            return;
        }
        selectedAction = TeamCrud.NOTHING.name();
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
        stepNumber--;
    }
    public void validateFrontFighters() {
        displayIfError = true;
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
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
            int r_ = simulation.getAvailableMoves().getVal((byte) selectedPk).getKey().getRound();
            simulation.setAbilityWhileFight(selectedPk, IndexConstants.FIRST_INDEX, r_, currentAbility);
        }
        StringList moves_ = new StringList();
        for (SelectLineMove m: keptMoves) {
            if (m.isSelected()) {
                moves_.add(m.getName());
            }
        }
        DataBase data_ = (DataBase) getDataBase();
        simulation.keepMoves(selectedPk, moves_, data_);
    }
    public void cancelMoves() {
        displayIfError = false;
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
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
        DataBase data_ = (DataBase) getDataBase();
        return simulation.hasNextFight(data_);
    }
    public String getImageAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(pk_.getName()));
    }
    public String getNameAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translatePokemon(pk_.getName());
    }
    public int getLevelAfterFight(int _index) {
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return pk_.getLevel();
    }
    public String getAbilityAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translateAbility(pk_.getAbility());
    }
    public String getGenderAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return data_.translateGenders(pk_.getGender());
    }
    public StringList getMovesAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        StringList list_ = new StringList();
        for (String m: pk_.getMoves().getKeys()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }
    public String getItemAfterFight(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }
    public LgInt getRemainingLifeRate(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        return pk_.rateRemainHp(data_);
    }
    public Rate numberNecessaryPointsForGrowingLevel(int _index){
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index);
        short level_ = pk_.getLevel();
        PokemonData fPk_=data_.getPokemon(pk_.getName());
        String expLitt_=data_.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(level_ + 1));
        Rate next_;
        next_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Long.toString(level_));
        current_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toNumberString(), vars_, Rate.one());
        diff_.removeNb(pk_.getWonExpSinceLastLevel());
        return diff_;
    }
    public void selectPkAfterFight() {
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        DataBase data_ = (DataBase) getDataBase();
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        String lastPk_ = k_.getEvolutions().last();
        evolutionAfterFight = lastPk_;
        abilityAfterFight = k_.getAbility();
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evolutionsAfterFight = new TreeMap<String, String>(new ComparatorTrStrings(translationsPokemon_));
        StringList evolutions_ = pk_.directEvolutionsByStone(lastPk_, data_);
        for (String e: evolutions_) {
            evolutionsAfterFight.put(e, translationsPokemon_.getVal(e));
        }
        StringMap<Boolean> selectedMoves_ = PokemonPlayer.getMovesForEvolution(pk_.getLevel(), k_.getMoves(), lastPk_, data_);
        keptMovesAfterFight.clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String m: selectedMoves_.getKeys()) {
            MoveData moveData_ = data_.getMoves().getVal(m);
            SelectLineMove line_ = new SelectLineMove();
            line_.setName(m);
            line_.setDisplayName(data_.translateMove(m));
            line_.setCategory(data_.getTranslatedCategories().getVal(getLanguage()).getVal(moveData_.getCategory()));
            StringList types_ = new StringList();
            for (String t: moveData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setPp(moveData_.getPp());
            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
            if (line_.isDamageMove()) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(selectedMoves_.getVal(m));
            keptMovesAfterFight.add(line_);
        }
        keptMovesAfterFight.sortElts(new ComparatorMoves());
        //StringList moves_ = data_.getPokemon(lastPk_).getMovesBeforeLevel(pk_.getLevel());
        StringList abilities_ = data_.getPokemon(lastPk_).getAbilities();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesAfterFight = new TreeMap<String, String>(new ComparatorTrStrings(translationsAbilities_));
        for (String a: abilities_) {
            abilitiesAfterFight.put(a, translationsAbilities_.getVal(a));
        }
    }
    public void validateEvolutionAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        k_.getEvolutions().add(evolutionAfterFight);
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        StringList evolutions_ = pk_.directEvolutionsByStone(evolutionAfterFight, data_);
        for (String e: evolutions_) {
            evolutionsAfterFight.put(e, translationsPokemon_.getVal(e));
        }
        StringMap<Boolean> selectedMoves_ = PokemonPlayer.getMovesForEvolution(pk_.getLevel(), k_.getMoves(), evolutionAfterFight, data_);
        keptMovesAfterFight.clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String m: selectedMoves_.getKeys()) {
            MoveData moveData_ = data_.getMoves().getVal(m);
            SelectLineMove line_ = new SelectLineMove();
            line_.setName(m);
            line_.setDisplayName(data_.translateMove(m));
            line_.setCategory(data_.getTranslatedCategories().getVal(getLanguage()).getVal(moveData_.getCategory()));
            StringList types_ = new StringList();
            for (String t: moveData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setPp(moveData_.getPp());
            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
            if (line_.isDamageMove()) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(selectedMoves_.getVal(m));
            keptMovesAfterFight.add(line_);
        }
        keptMovesAfterFight.sortElts(new ComparatorMoves());
        StringList abilities_ = data_.getPokemon(evolutionAfterFight).getAbilities();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesAfterFight = new TreeMap<String, String>(new ComparatorTrStrings(translationsAbilities_));
        for (String a: abilities_) {
            abilitiesAfterFight.put(a, translationsAbilities_.getVal(a));
        }
        abilityAfterFight = abilitiesAfterFight.firstKey();
    }
    public void cancelEvolutionsAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        String base_ = k_.getEvolutions().first();
        k_.getEvolutions().clear();
        k_.getEvolutions().add(base_);
        evolutionAfterFight = base_;
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        k_.getMoves().clear();
        k_.getMoves().addAllElts(pk_.getMoves().getKeys());
        k_.setAbility(pk_.getAbility());
        StringList evolutions_ = pk_.directEvolutionsByStone(evolutionAfterFight, data_);
        for (String e: evolutions_) {
            evolutionsAfterFight.put(e, translationsPokemon_.getVal(e));
        }
        StringMap<Boolean> selectedMoves_ = PokemonPlayer.getMovesForEvolution(pk_.getLevel(), k_.getMoves(), evolutionAfterFight, data_);
        keptMovesAfterFight.clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String m: selectedMoves_.getKeys()) {
            MoveData moveData_ = data_.getMoves().getVal(m);
            SelectLineMove line_ = new SelectLineMove();
            line_.setName(m);
            line_.setDisplayName(data_.translateMove(m));
            line_.setCategory(data_.getTranslatedCategories().getVal(getLanguage()).getVal(moveData_.getCategory()));
            StringList types_ = new StringList();
            for (String t: moveData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setPp(moveData_.getPp());
            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
            if (line_.isDamageMove()) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(selectedMoves_.getVal(m));
            keptMovesAfterFight.add(line_);
        }
        keptMovesAfterFight.sortElts(new ComparatorMoves());
        StringList abilities_ = data_.getPokemon(evolutionAfterFight).getAbilities();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesAfterFight = new TreeMap<String, String>(new ComparatorTrStrings(translationsAbilities_));
        for (String a: abilities_) {
            abilitiesAfterFight.put(a, translationsAbilities_.getVal(a));
        }
        abilityAfterFight = k_.getAbility();
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
        DataBase data_ = (DataBase) getDataBase();
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
        simulation.nextFight(data_);
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber = 2;
    }
    public boolean isIssue() {
        return simulation.getProbleme();
    }
    public boolean isIssueAfterFight() {
        if (!simulation.isAcceptableChoices()) {
            //the fight could not be finished
            return false;
        }
        return simulation.getProbleme();
    }
    public StringList getKoPlayers() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getKoPlayers()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }
    public StringList getNotKoFrontFoes() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getNotKoFrontFoes()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }
    public StringList getKoFoes() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getKoFoes()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }
    public boolean isRulesIssue() {
        return simulation.getIssue().isRules();
    }
    public boolean isRulesMovesIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_MOVES;
    }
    public boolean isRulesLearnIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_LEARN;
    }
    public boolean isRulesSwitchIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_SWITCH;
    }
    public boolean isSendingIssue() {
        return simulation.getIssue() == IssueSimulation.SENDING_ISSUE;
    }
    public boolean isRandomIssue() {
        return simulation.getIssue() == IssueSimulation.RANDOM;
    }
    public boolean isUsingIssue() {
        return simulation.getIssue() == IssueSimulation.CANNOT_USE;
    }
    public boolean isHardSimulationIssue() {
        return simulation.getIssue() == IssueSimulation.TOO_HARD_SIMULATION;
    }
    public int getRealStepNumber() {
        return stepNumber + 1;
    }
    public String quit() {
        simulation = null;
        team.clear();
        teamAfterFight.clear();
        getForms().removeKey(CST_SIMULATION_STATE);
        getForms().removeKey(CST_COORDS);
        getForms().removeKey(CST_INSIDE);
        getForms().removeKey(CST_LEVEL_MAP_INDEX);
        getForms().removeKey(CST_PLACE_MAP_INDEX);
        stepNumber = 0;
        ok = true;
        return CST_LEVEL;
    }

    public TreeMap<String,String> getWinPointsFight() {
        return winPointsFight;
    }

    public String getDiffWinningExpPtsFight() {
        return diffWinningExpPtsFight;
    }

    public void setDiffWinningExpPtsFight(String _diffWinningExpPtsFight) {
        diffWinningExpPtsFight = _diffWinningExpPtsFight;
    }

    public void setAllowCatchingKo(boolean _allowCatchingKo) {
        allowCatchingKo = _allowCatchingKo;
    }

    public boolean getAllowCatchingKo() {
        return allowCatchingKo;
    }

    public void setAllowedSwitchPlacesEndRound(boolean _allowedSwitchPlacesEndRound) {
        allowedSwitchPlacesEndRound = _allowedSwitchPlacesEndRound;
    }

    public boolean getAllowedSwitchPlacesEndRound() {
        return allowedSwitchPlacesEndRound;
    }

    public void setWinTrainerExp(Rate _winTrainerExp) {
        winTrainerExp = _winTrainerExp;
    }

    public Rate getWinTrainerExp() {
        return winTrainerExp;
    }

    public void setRateWinningExpPtsFight(Rate _rateWinningExpPtsFight) {
        rateWinningExpPtsFight = _rateWinningExpPtsFight;
    }

    public Rate getRateWinningExpPtsFight() {
        return rateWinningExpPtsFight;
    }

    public void setEndFightIfOneTeamKo(boolean _endFightIfOneTeamKo) {
        endFightIfOneTeamKo = _endFightIfOneTeamKo;
    }

    public boolean getEndFightIfOneTeamKo() {
        return endFightIfOneTeamKo;
    }

    public void setIvPlayer(int _ivPlayer) {
        ivPlayer = _ivPlayer;
    }

    public int getIvPlayer() {
        return ivPlayer;
    }

    public void setIvFoe(int _ivFoe) {
        ivFoe = _ivFoe;
    }

    public int getIvFoe() {
        return ivFoe;
    }

    public void setRateWinMoneyBase(Rate _rateWinMoneyBase) {
        rateWinMoneyBase = _rateWinMoneyBase;
    }

    public Rate getRateWinMoneyBase() {
        return rateWinMoneyBase;
    }

    public void setRateLooseMoneyWin(Rate _rateLooseMoneyWin) {
        rateLooseMoneyWin = _rateLooseMoneyWin;
    }

    public Rate getRateLooseMoneyWin() {
        return rateLooseMoneyWin;
    }

    public void setRestoredMovesEndFight(boolean _restoredMovesEndFight) {
        restoredMovesEndFight = _restoredMovesEndFight;
    }

    public boolean getRestoredMovesEndFight() {
        return restoredMovesEndFight;
    }

    public void setEnabledClosing(boolean _enabledClosing) {
        enabledClosing = _enabledClosing;
    }

    public boolean getEnabledClosing() {
        return enabledClosing;
    }

    public void setRandomWildFight(boolean _randomWildFight) {
        randomWildFight = _randomWildFight;
    }

    public boolean getRandomWildFight() {
        return randomWildFight;
    }

    public void setStillPossibleFlee(boolean _stillPossibleFlee) {
        stillPossibleFlee = _stillPossibleFlee;
    }

    public boolean getStillPossibleFlee() {
        return stillPossibleFlee;
    }

    public void setSkipLearningMovesWhileNotGrowingLevel(boolean _skipLearningMovesWhileNotGrowingLevel) {
        skipLearningMovesWhileNotGrowingLevel = _skipLearningMovesWhileNotGrowingLevel;
    }

    public boolean getSkipLearningMovesWhileNotGrowingLevel() {
        return skipLearningMovesWhileNotGrowingLevel;
    }

    public TreeMap<String,String> getDamageRates() {
        return damageRates;
    }

    public String getDamageRatePlayer() {
        return damageRatePlayer;
    }

    public void setDamageRatePlayer(String _damageRatePlayer) {
        damageRatePlayer = _damageRatePlayer;
    }

    public NatCmpTreeMap<Rate,Rate> getDamageRatePlayerTable() {
        return damageRatePlayerTable;
    }

    public String getDamageRateLawFoe() {
        return damageRateLawFoe;
    }

    public void setDamageRateLawFoe(String _damageRateLawFoe) {
        damageRateLawFoe = _damageRateLawFoe;
    }

    public NatCmpTreeMap<Rate,Rate> getDamageRateFoeTable() {
        return damageRateFoeTable;
    }

    public void setFreeTeams(boolean _freeTeams) {
        freeTeams = _freeTeams;
    }

    public boolean getFreeTeams() {
        return freeTeams;
    }

    public void setMultiplicity(int _multiplicity) {
        multiplicity = _multiplicity;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public TreeMap<String,String> getEnvironments() {
        return environments;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String _environment) {
        environment = _environment;
    }

    public CustList<PokemonTrainerDto> getFoeTeam() {
        return foeTeam;
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
        return allyTeam;
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

    public TreeMap<String,String> getAvailableEvos() {
        return availableEvos;
    }

    public String getChosenEvo() {
        return chosenEvo;
    }

    public void setChosenEvo(String _chosenEvo) {
        chosenEvo = _chosenEvo;
    }

    public void setLevelEvo(int _levelEvo) {
        levelEvo = _levelEvo;
    }

    public int getLevelEvo() {
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

    public TreeMap<String,String> getAbilities() {
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

    public TreeMap<String,String> getEvolutionsAfterFight() {
        return evolutionsAfterFight;
    }

    public String getEvolutionAfterFight() {
        return evolutionAfterFight;
    }

    public void setEvolutionAfterFight(String _evolutionAfterFight) {
        evolutionAfterFight = _evolutionAfterFight;
    }

    public TreeMap<String,String> getAbilitiesAfterFight() {
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