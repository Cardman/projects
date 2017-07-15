package aiki.beans.simulation;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.PairNumber;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.dto.KeptMovesAfterFight;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.dto.PokemonTrainerDto;
import aiki.beans.facade.simulation.dto.RadioLineMove;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.comparators.ComparatorTrStringEnv;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.FightSimulation;
import aiki.game.fight.Fighter;
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

public class SimulationBean extends CommonBean {

    @Accessible
    private boolean allowCatchingKo;

    @Accessible
    private boolean allowedSwitchPlacesEndRound;

    @Accessible
    private DifficultyWinPointsFight diffWinningExpPtsFight;

    @Accessible
    private NatTreeMap<DifficultyWinPointsFight, String> winPointsFight;

    @Accessible
    private Rate rateWinningExpPtsFight;

    @Accessible
    private Rate winTrainerExp;

    @Accessible
    private NatTreeMap<DifficultyModelLaw, String> damageRates;

    @Accessible
    private DifficultyModelLaw damageRatePlayer;

    @Accessible
    private NatCmpTreeMap<Rate,Rate> damageRatePlayerTable;

    @Accessible
    private DifficultyModelLaw damageRateLawFoe;

    @Accessible
    private NatCmpTreeMap<Rate,Rate> damageRateFoeTable;

    @Accessible
    private boolean endFightIfOneTeamKo;

    @Accessible
    private Rate rateWinMoneyBase;

    @Accessible
    private Rate rateLooseMoneyWin;

    @Accessible
    private short ivPlayer;

    @Accessible
    private short ivFoe;

    @Accessible
    private boolean stillPossibleFlee;

    @Accessible
    private boolean restoredMovesEndFight;

    @Accessible
    private boolean enabledClosing;

    @Accessible
    private boolean randomWildFight;

    @Accessible
    private boolean skipLearningMovesWhileNotGrowingLevel;

    @Accessible
    private CustList<PlaceIndex> places;

    private Coords coords;

    @Accessible
    private boolean freeTeams;

    @Accessible
    private int selectedFoePk = CustList.INDEX_NOT_FOUND_ELT;

    @Accessible
    private CustList<PokemonTrainerDto> foeTeam = new CustList<PokemonTrainerDto>();

    @Accessible
    private int selectedAllyPk = CustList.INDEX_NOT_FOUND_ELT;

    @Accessible
    private CustList<PokemonTrainerDto> allyTeam = new CustList<PokemonTrainerDto>();

    @Accessible
    private int multiplicity = 1;

    private int nbMaxActions;

    @Accessible
    private EnvironmentType environment = EnvironmentType.ROAD;

    @Accessible
    private TreeMap<EnvironmentType, String> environments;

    private boolean enableEvolutions = true;

    @Accessible
    private CustList<PokemonPlayerDto> team = new CustList<PokemonPlayerDto>();

    @Accessible
    private int selectedPk = CustList.INDEX_NOT_FOUND_ELT;

    @Accessible
    private TeamCrud selectedFoeAction = TeamCrud.NOTHING;

    @Accessible
    private TeamCrud selectedAllyAction = TeamCrud.NOTHING;

    @Accessible
    private TeamCrud selectedAction = TeamCrud.NOTHING;

    private StringMap<Short> availableEvosLevel = new StringMap<Short>();

    @Accessible
    private TreeMap<String, String> availableEvos;

    @Accessible
    private StringList evos = new StringList();

    @Accessible
    private String chosenEvo = DataBase.EMPTY_STRING;

    @Accessible
    private short levelEvo;

    private FightSimulation simulation;

    @Accessible
    private CustList<SelectLineMove> checks = new CustList<SelectLineMove>();

    private Difficulty difficulty = new Difficulty();

    private int noFight;

    @Accessible
    private int selectedRound;

    @Accessible
    private NatTreeMap<Integer, Integer> round = new NatTreeMap<Integer, Integer>();

    @Accessible
    private int defaultPlaceFight = Fighter.BACK;

    @Accessible
    private int placeFight = Fighter.BACK;

    @Accessible
    private NatTreeMap<Integer, String> placesFight = new NatTreeMap<Integer, String>();

    @Accessible
    private int target;

    @Accessible
    private NatTreeMap<Integer, String> targetFight = new NatTreeMap<Integer, String>();

    @Accessible
    private String currentAbility = DataBase.EMPTY_STRING;

    @Accessible
    private TreeMap<String,String> abilities;

    @Accessible
    private CustList<SelectLineMove> keptMoves = new CustList<SelectLineMove>();

    @Accessible
    private CustList<RadioLineMove> movesSet = new CustList<RadioLineMove>();

    @Accessible
    private int selectedMove;

    @Accessible
    private boolean allyChoice;

    @Accessible
    private StringList comments = new StringList();

    @Accessible
    private String commentArea = DataBase.EMPTY_STRING;

    @Accessible
    private CustList<PokemonPlayer> teamAfterFight = new CustList<PokemonPlayer>();

    @Accessible
    private CustList<KeptMovesAfterFight> keptMovesAbilitiesDto = new CustList<KeptMovesAfterFight>();

    @Accessible
    private CustList<SelectLineMove> keptMovesAfterFight = new CustList<SelectLineMove>();

    @Accessible
    private String evolutionAfterFight = DataBase.EMPTY_STRING;

    @Accessible
    private TreeMap<String,String> evolutionsAfterFight;

    @Accessible
    private String abilityAfterFight = DataBase.EMPTY_STRING;

    @Accessible
    private TreeMap<String,String> abilitiesAfterFight;

    @Accessible
    private int stepNumber;

    @Accessible
    private boolean ok;

    @Accessible
    private boolean displayIfError;

    @Override
    public void beforeDisplaying() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        DataBase data_ = (DataBase) getDataBase();
        if (simu_ == SimulationSteps.DIFF) {
            damageRates = new NatTreeMap<DifficultyModelLaw, String>();
            winPointsFight = new NatTreeMap<DifficultyWinPointsFight, String>();
            EnumMap<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
            for (DifficultyWinPointsFight k: trWinPts_.getKeys()) {
//                winPointsFight.put(k, XmlParser.transformSpecialChars(trWinPts_.getVal(k)));
                winPointsFight.put(k, trWinPts_.getVal(k));
            }
            EnumMap<DifficultyModelLaw, String> trWinLaw_ = data_.getTranslatedDiffModelLaw().getVal(getLanguage());
            for (DifficultyModelLaw k: trWinLaw_.getKeys()) {
//                damageRates.put(k, XmlParser.transformSpecialChars(trWinLaw_.getVal(k)));
                damageRates.put(k, trWinLaw_.getVal(k));
            }
            diffWinningExpPtsFight = difficulty.getDiffWinningExpPtsFight();
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
            damageRatePlayer = difficulty.getDamageRatePlayer();
            damageRateLawFoe = difficulty.getDamageRateLawFoe();
            damageRatePlayerTable = new NatCmpTreeMap<Rate, Rate>();
            MonteCarloNumber law_;
            LgInt sum_;
            law_ = data_.getLawsDamageRate().getVal(damageRatePlayer).getLaw();
            sum_ = law_.sum();
            for (Rate e: law_.events()) {
                damageRatePlayerTable.put(e, new Rate(law_.rate(e), sum_));
            }
            damageRateFoeTable = new NatCmpTreeMap<Rate, Rate>();
            law_ = data_.getLawsDamageRate().getVal(damageRateLawFoe).getLaw();
            sum_ = law_.sum();
            for (Rate e: law_.events()) {
                damageRateFoeTable.put(e, new Rate(law_.rate(e), sum_));
            }
        } else if (simu_ == SimulationSteps.FOE) {
            if (freeTeams) {
                if (!foeTeam.isEmpty()) {
                    ok = true;
                }
                boolean remove_ = getForms().getVal(ADDING_TRAINER_PK) == TeamCrud.REMOVE;
                if (remove_) {
                    return;
                }
                boolean nothing_ = getForms().getVal(ADDING_TRAINER_PK) == TeamCrud.NOTHING;
                if (nothing_) {
                    return;
                }
                boolean add_ = getForms().getVal(ADDING_TRAINER_PK) == TeamCrud.ADD;
                if (add_) {
                    boolean foe_ = (Boolean) getForms().getVal(POKEMON_FOE);
                    PokemonTrainerDto pk_;
                    pk_ = new PokemonTrainerDto();
                    pk_.getMoves().addAllElts((StringList) getForms().getVal(POKEMON_MOVES_EDIT));
                    pk_.setAbility((String) getForms().getVal(POKEMON_ABILITY_EDIT));
                    pk_.setName((String) getForms().getVal(POKEMON_NAME_EDIT));
                    pk_.setGender((Gender) getForms().getVal(POKEMON_GENDER_EDIT));
                    pk_.setItem((String) getForms().getVal(ITEM_EDIT));
                    pk_.setLevel((Short) getForms().getVal(POKEMON_LEVEL_EDIT));
                    if (foe_) {
                        pk_.setIndex(foeTeam.size());
                        foeTeam.add(pk_);
                    } else {
                        pk_.setIndex(allyTeam.size());
                        allyTeam.add(pk_);
                    }
                } else {
                    boolean foe_ = (Boolean) getForms().getVal(POKEMON_FOE);
                    PokemonTrainerDto pk_;
                    if (foe_) {
                        pk_ = foeTeam.get(selectedFoePk);
                    } else {
                        pk_ = allyTeam.get(selectedAllyPk);
                    }
                    pk_.getMoves().clear();
                    pk_.getMoves().addAllElts((StringList) getForms().getVal(POKEMON_MOVES_EDIT));
                    pk_.setAbility((String) getForms().getVal(POKEMON_ABILITY_EDIT));
                    pk_.setName((String) getForms().getVal(POKEMON_NAME_EDIT));
                    pk_.setGender((Gender) getForms().getVal(POKEMON_GENDER_EDIT));
                    pk_.setItem((String) getForms().getVal(ITEM_EDIT));
                    pk_.setLevel((Short) getForms().getVal(POKEMON_LEVEL_EDIT));
                }
            } else {
                if (!getForms().contains(NO_FIGHT)) {
                    getForms().put(NO_FIGHT, CustList.FIRST_INDEX);
                }
                noFight = (Integer) getForms().getVal(NO_FIGHT);
                coords = (Coords) getForms().getVal(COORDS);
                if (coords != null) {
                    ok = true;
                }
                places = new CustList<PlaceIndex>();
                for (Short i: data_.getMap().getPlaces().getKeys()) {
                    PlaceIndex pl_ = new PlaceIndex();
                    pl_.setIndex(i);
                    pl_.setPlace(data_.getMap().getPlaces().getVal(i));
                    places.add(pl_);
                }
                places.sortElts(new ComparatorPlaceIndex());
            }
        } else if (simu_ == SimulationSteps.TEAM) {
            if (!simulation.getTeam().isEmpty()) {
                ok = true;
            }
            if (getForms().contains(POKEMON_ADDED)) {
                PokemonPlayerDto pk_ = (PokemonPlayerDto) getForms().getVal(POKEMON_ADDED);
                getForms().removeKey(POKEMON_ADDED);
                pk_.setIndex(team.size());
                team.add(pk_);
                simulation.addPokemonPlayer(pk_.getPokemon(), pk_.getMoves(), (byte) 0, Rate.zero(), data_);
            } else if(getForms().contains(POKEMON_INDEX_EDIT)) {
                int index_ = (Integer) getForms().getVal(POKEMON_INDEX_EDIT);
                getForms().removeKey(POKEMON_INDEX_EDIT);
                team.get(index_).setMoves((StringList) getForms().getVal(POKEMON_MOVES_EDIT));
                getForms().removeKey(POKEMON_MOVES_EDIT);
                team.get(index_).getPokemon().setItem((String) getForms().getVal(ITEM_EDIT));
                getForms().removeKey(ITEM_EDIT);
                simulation.setPokemonPlayerObject(index_, team.get(index_).getPokemon().getItem());
                Rate exp_ = (Rate) getForms().getVal(POKEMON_EXPERIENCE);
                getForms().removeKey(POKEMON_EXPERIENCE);
                PokemonPlayer pkPlayer_ = simulation.getTeam().get(index_);
                pkPlayer_.setWonExpSinceLastLevel(exp_);
                String ball_ = (String) getForms().getVal(CATCHING_BALL);
                getForms().removeKey(CATCHING_BALL);
                pkPlayer_.setUsedBallCatching(ball_);
                short happy_ = (Short) getForms().getVal(POKEMON_HAPPINESS);
                getForms().removeKey(POKEMON_HAPPINESS);
                Rate hp_ = (Rate) getForms().getVal(POKEMON_HP);
                getForms().removeKey(POKEMON_HP);
                boolean heal_ = (Boolean) getForms().getVal(HEAL_EDIT_PK);
                getForms().removeKey(HEAL_EDIT_PK);
                for (Statistic s:Statistic.getStatisticsWithBase()) {
                    short ev_ = (Short) getForms().getVal(POKEMON_EV_VAR+s.name());
                    getForms().removeKey(POKEMON_EV_VAR+s.name());
                    if (ev_ > data_.getMaxEv()) {
                        ev_ = (short) data_.getMaxEv();
                    }
                    pkPlayer_.getEv().put(s, ev_);
                }
                if (Rate.strGreater(hp_, pkPlayer_.pvMax(data_)) || hp_.isZeroOrLt() || heal_) {
                    pkPlayer_.setRemainingHp(pkPlayer_.pvMax(data_));
                } else {
                    pkPlayer_.setRemainingHp(hp_);
                }
                pkPlayer_.setHappiness(happy_);
                simulation.setInitialMoves(index_, team.get(index_).getMoves(), data_);
                getForms().removeKey(ITEMS_SET_EDIT);
                getForms().removeKey(POKEMON_NAME_EDIT);
                getForms().removeKey(POKEMON_LEVEL_EDIT);
            }
        } else if (simu_ == SimulationSteps.FRONT) {
            round = new NatTreeMap<Integer, Integer>();
            placesFight = new NatTreeMap<Integer, String>();
            int nbRounds_ = simulation.nbRounds();
            for (int i = CustList.FIRST_INDEX; i < nbRounds_; i++) {
                round.put(i, i);
            }
            int mult_ = simulation.getFirstMult();
            placesFight.put((int) Fighter.BACK, DataBase.EMPTY_STRING);
            for (int i = CustList.FIRST_INDEX; i < mult_; i++) {
                placesFight.put(i, Integer.toString(i));
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
            if (selectedPk != CustList.INDEX_NOT_FOUND_ELT) {
                targetFight = new NatTreeMap<Integer, String>();
                int mult_ = simulation.getFirstMult();
                for (int i = CustList.FIRST_INDEX; i < mult_; i++) {
                    targetFight.put(i, Integer.toString(i));
                }
                movesSet.clear();
                if (selectedRound == 0) {
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
                    int i_ = CustList.FIRST_INDEX;
                    movesSet.sortElts(new ComparatorMoves());
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
                StringList moves_ = simulation.getKeptMoves().getVal((byte) selectedPk).getVal(new PairNumber<Byte, Byte>((byte)CustList.FIRST_INDEX, (byte)(selectedRound -1)));
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
                int i_ = CustList.FIRST_INDEX;
                movesSet.sortElts(new ComparatorMoves());
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

    @Accessible
    private boolean isDiffState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.DIFF;
    }

    @Accessible
    private boolean isFoeState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.FOE;
    }

    @Accessible
    private boolean isTeamState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.TEAM;
    }

    @Accessible
    private boolean isEvolutionsState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.EVOLUTIONS;
    }

    @Accessible
    private boolean isFrontState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.FRONT;
    }

    @Accessible
    private boolean isMovesState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES;
    }

    @Accessible
    private boolean isMovesFightState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES_FIGHT;
    }

    @Accessible
    private boolean isSimulationState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.SIMULATION;
    }

    @Accessible
    private boolean isEvolutionAfterFightState() {
        SimulationSteps simu_ = (SimulationSteps) getForms().getVal(SIMULATION_STATE);
        return simu_ == SimulationSteps.EVO_AFTER_FIGHT;
    }

    @Accessible
    private boolean isMultiLayer(Long _index) {
        return layers(_index).size() > CustList.ONE_ELEMENT;
    }

//    @Accessible
//    private String cancelButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(CANCEL));
//    }

//    @Accessible
//    private String cancelEvoButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(CANCEL_EVO));
//    }

    @Accessible
    private CustList<? extends Level> layers(Long _index) {
        Place pl_ = places.get(_index.intValue()).getPlace();
        return pl_.getLevelsList();
    }

    @Accessible
    private String getTrainerName() {
        if (coords == null) {
            return DataBase.EMPTY_STRING;
        }
        String placeName_ = DataBase.EMPTY_STRING;
        DataBase data_ = (DataBase) getDataBase();
        Place pl_ = data_.getMap().getPlaces().getVal(coords.getNumberPlace());
        placeName_ += pl_.getName();
        placeName_ += SPACE;
        if (pl_ instanceof Cave) {
            placeName_ += coords.getLevel().getLevelIndex();
            placeName_ += SPACE;
        }
        return placeName_+getTrainerName(coords)+SPACE+noFight;
    }

    private String getTrainerName(Coords _coords) {
        DataBase data_ = (DataBase) getDataBase();
        Place pl_ = data_.getMap().getPlaces().getVal(_coords.getNumberPlace());
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
                return w_.getDualFights().getVal(_coords.getLevel().getPoint()).getNames().join(SPACE);
            }
        }
        return DataBase.EMPTY_STRING;
    }

//    @Accessible
//    private boolean isCenter(Long _indexOne, Long _indexTwo) {
//        return buildings(_indexOne).get(_indexTwo.intValue()) instanceof PokemonCenter;
//    }

//    @Accessible
//    private boolean isGym(Long _indexOne, Long _indexTwo) {
//        return buildings(_indexOne).get(_indexTwo.intValue()) instanceof Gym;
//    }

//    @Accessible
//    private CustList<Building> buildings(Long _index) {
//        Place pl_ = places.get(_index.intValue()).getPlace();
//        if (!(pl_ instanceof City)) {
//            return new CustList<>();
//        }
//        City c_ = (City) pl_;
//        return c_.getBuildings().values();
//    }

//    @Accessible
//    private boolean isCity(Long _index) {
//        return places.get(_index.intValue()).getPlace() instanceof City;
//    }

    @Accessible
    private String clickLevel(Long _indexOne, Long _indexTwo) {
        //getForms().removeKey(INSIDE);
        DataBase data_ = (DataBase) getDataBase();
        Place pl_ = data_.getMap().getPlaces().getVal(_indexOne.shortValue());
        if (pl_ instanceof League) {
            League l_ = (League) pl_;
            coords = new Coords();
            coords.setNumberPlace(_indexOne.shortValue());
            coords.setLevel(new LevelPoint());
            coords.getLevel().setLevelIndex(_indexTwo.byteValue());
            coords.getLevel().setPoint(new Point(l_.getLevelsList().first().getTrainerCoords()));
            getForms().put(COORDS, coords);
            noFight = CustList.FIRST_INDEX;
            return DataBase.EMPTY_STRING;
        }
        if (pl_ instanceof City) {
            City c_ = (City) pl_;
            for (Point p: c_.getBuildings().getKeys()) {
                Building b_ = c_.getBuildings().getVal(p);
                if (b_ instanceof Gym) {
                    getForms().put(LEVEL_MAP_INDEX, _indexTwo.intValue());
                    getForms().put(PLACE_MAP_INDEX, _indexOne.byteValue());
                    getForms().put(INSIDE, new Point(p));
                    return LEVEL;
                }
            }
        }
        getForms().removeKey(INSIDE);
        getForms().put(LEVEL_MAP_INDEX, _indexTwo.intValue());
        getForms().put(PLACE_MAP_INDEX, _indexOne.byteValue());
        return LEVEL;
    }

    @Accessible
    private void validateDiffChoice() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        difficulty.setDiffWinningExpPtsFight(diffWinningExpPtsFight);
        difficulty.setAllowCatchingKo(allowCatchingKo);
        difficulty.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound);
        difficulty.setWinTrainerExp(winTrainerExp);
        difficulty.setRateWinningExpPtsFight(rateWinningExpPtsFight);
        difficulty.setEndFightIfOneTeamKo(endFightIfOneTeamKo);
        difficulty.setIvPlayer(ivPlayer);
        difficulty.setIvFoe(ivFoe);
        difficulty.setRateWinMoneyBase(rateWinMoneyBase);
        difficulty.setRateLooseMoneyWin(rateLooseMoneyWin);
        difficulty.setRestoredMovesEndFight(restoredMovesEndFight);
        difficulty.setEnabledClosing(enabledClosing);
        difficulty.setRandomWildFight(randomWildFight);
        difficulty.setStillPossibleFlee(stillPossibleFlee);
        difficulty.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel);
        difficulty.setDamageRateLawFoe(damageRateLawFoe);
        difficulty.setDamageRatePlayer(damageRatePlayer);
        difficulty.validate(data_);
        simulation = new FightSimulation(difficulty, data_);
        getForms().put(SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber++;
        if (freeTeams) {
            getForms().put(ADDING_TRAINER_PK, TeamCrud.NOTHING);
            EnumMap<EnvironmentType, String> tr_;
            tr_ = data_.getTranslatedEnvironment().getVal(getLanguage());
            environments = new TreeMap<EnvironmentType, String>(new ComparatorTrStringEnv(tr_));
            environments.putAllMap(tr_);
            selectedFoePk = CustList.INDEX_NOT_FOUND_ELT;
            selectedAllyPk = CustList.INDEX_NOT_FOUND_ELT;
        } else {
            EnumMap<EnvironmentType, String> tr_;
            tr_ = data_.getTranslatedEnvironment().getVal(getLanguage());
            environments = new TreeMap<EnvironmentType, String>(new ComparatorTrStringEnv(tr_));
        }
    }

    @Accessible
    private void cancelDiffChoice() {
        ok = true;
        foeTeam.clear();
        allyTeam.clear();
        simulation = null;
        getForms().put(SIMULATION_STATE, SimulationSteps.DIFF);
        stepNumber--;
    }

    @Accessible
    private void validateFoeChoice() {
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
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING;
        getForms().put(SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }

    @Accessible
    private String getImageFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        return ConverterBufferedImage.surroundImage(data_.getMiniPk().getVal(pk_.getName()));
    }

    @Accessible
    private String getNameFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        return data_.translatePokemon(pk_.getName());
    }

    @Accessible
    private int getLevelFoe(Long _index) {
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        return pk_.getLevel();
    }

    @Accessible
    private String getAbilityFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        return data_.translateAbility(pk_.getAbility());
    }

    @Accessible
    private String getGenderFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        return data_.translateGenders(pk_.getGender());
    }

    @Accessible
    private StringList getMovesFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private String getItemFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = foeTeam.get(_index.intValue());
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }

    @Accessible
    private String selectFoePk() {
        if (selectedFoeAction == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedFoePk == CustList.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedFoeAction == TeamCrud.EDIT) {
            getForms().put(POKEMON_FOE, true);
            getForms().put(ITEMS_SET_EDIT, new StringList());
            getForms().put(POKEMON_INDEX_EDIT, selectedFoePk);
            getForms().put(POKEMON_NAME_EDIT, foeTeam.get(selectedFoePk).getName());
            getForms().put(POKEMON_LEVEL_EDIT, foeTeam.get(selectedFoePk).getLevel());
            getForms().put(ITEM_EDIT, foeTeam.get(selectedFoePk).getItem());
            getForms().put(POKEMON_GENDER_EDIT, foeTeam.get(selectedFoePk).getGender());
            getForms().put(POKEMON_MOVES_EDIT, foeTeam.get(selectedFoePk).getMoves());
            getForms().put(POKEMON_ABILITY_EDIT, foeTeam.get(selectedFoePk).getAbility());
            getForms().put(ADDING_TRAINER_PK, TeamCrud.EDIT);
            return POKEMON_EDIT;
        }
        if (selectedFoeAction == TeamCrud.REMOVE) {
            int index_ = selectedFoePk;
            foeTeam.removeAt(index_);
            int size_ = foeTeam.size();
            for (int i = index_; i < size_;i++) {
                foeTeam.get(i).setIndex(i);
            }
            getForms().put(ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private String getImageAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        return ConverterBufferedImage.surroundImage(data_.getMiniPk().getVal(pk_.getName()));
    }

    @Accessible
    private String getNameAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        return data_.translatePokemon(pk_.getName());
    }

    @Accessible
    private int getLevelAlly(Long _index) {
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        return pk_.getLevel();
    }

    @Accessible
    private String getAbilityAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        return data_.translateAbility(pk_.getAbility());
    }

    @Accessible
    private String getGenderAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        return data_.translateGenders(pk_.getGender());
    }

    @Accessible
    private StringList getMovesAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private String getItemAlly(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonTrainerDto pk_ = allyTeam.get(_index.intValue());
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }

    @Accessible
    private String selectAllyPk() {
        if (selectedAllyAction == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedAllyPk == CustList.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedAllyAction == TeamCrud.EDIT) {
            getForms().put(POKEMON_FOE, false);
            getForms().put(ITEMS_SET_EDIT, new StringList());
            getForms().put(POKEMON_INDEX_EDIT, selectedAllyPk);
            getForms().put(POKEMON_NAME_EDIT, allyTeam.get(selectedAllyPk).getName());
            getForms().put(POKEMON_LEVEL_EDIT, allyTeam.get(selectedAllyPk).getLevel());
            getForms().put(ITEM_EDIT, allyTeam.get(selectedAllyPk).getItem());
            getForms().put(POKEMON_GENDER_EDIT, allyTeam.get(selectedAllyPk).getGender());
            getForms().put(POKEMON_MOVES_EDIT, allyTeam.get(selectedAllyPk).getMoves());
            getForms().put(POKEMON_ABILITY_EDIT, allyTeam.get(selectedAllyPk).getAbility());
            getForms().put(ADDING_TRAINER_PK, TeamCrud.EDIT);
            return POKEMON_EDIT;
        }
        if (selectedAllyAction == TeamCrud.REMOVE) {
            int index_ = selectedAllyPk;
            allyTeam.removeAt(index_);
            int size_ = allyTeam.size();
            for (int i = index_; i < size_;i++) {
                allyTeam.get(i).setIndex(i);
            }
            getForms().put(ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private String addPkTrainer() {
        DataBase data_ = (DataBase) getDataBase();
        Pokemon pk_ = data_.getMap().getFirstPokemon();
        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesBeforeLevel(pk_.getLevel());
        getForms().put(POKEMON_NAME_EDIT, pk_.getName());
        getForms().put(POKEMON_LEVEL_EDIT, pk_.getLevel());
        getForms().put(ITEM_EDIT, pk_.getItem());
        getForms().put(POKEMON_GENDER_EDIT, Gender.NO_GENDER);
        getForms().put(POKEMON_MOVES_EDIT, moves_);
        getForms().put(POKEMON_ABILITY_EDIT, pk_.getAbility());
        getForms().put(ADDING_TRAINER_PK, TeamCrud.ADD);
        getForms().put(ITEMS_SET_EDIT, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private void validateFoeChoiceFree() {
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
        simulation.setTeams(ally_, foe_, multiplicity, nbMaxActions, environment, coords);
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING;
        getForms().removeKey(POKEMON_INDEX_EDIT);
        getForms().removeKey(POKEMON_ADDED);
        getForms().put(SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber++;
    }

    @Accessible
    private void cancelFoeChoice() {
        DataBase data_ = (DataBase) getDataBase();
        simulation = new FightSimulation(difficulty, data_);
        coords = null;
        ok = true;
        getForms().removeKey(COORDS);
        getForms().put(SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber--;
    }

//    @Accessible
//    private String addButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(ADD));
//    }
//
//    @Accessible
//    private String removeButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(REMOVE));
//    }
//
//    @Accessible
//    private String selectButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(SELECT));
//    }

//    @Accessible
//    private String editButton() {
//        Map<String,String> messages_ = getMessages(MSG_SIMULATION);
//        return XmlParser.transformSpecialChars(messages_.getVal(EDIT));
//    }

    @Accessible
    private String selectPk() {
        if (selectedAction == TeamCrud.NOTHING) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedPk == CustList.INDEX_NOT_FOUND_ELT) {
            return DataBase.EMPTY_STRING;
        }
        if (selectedAction == TeamCrud.EDIT) {
            getForms().put(ITEMS_SET_EDIT, new StringList());
            getForms().put(POKEMON_INDEX_EDIT, selectedPk);
            getForms().put(POKEMON_NAME_EDIT, team.get(selectedPk).getPokemon().getName());
            getForms().put(POKEMON_LEVEL_EDIT, team.get(selectedPk).getPokemon().getLevel());
            getForms().put(ITEM_EDIT, team.get(selectedPk).getPokemon().getItem());
            getForms().put(POKEMON_MOVES_EDIT, team.get(selectedPk).getMoves());
            getForms().put(POKEMON_EXPERIENCE, simulation.getTeam().get(selectedPk).getWonExpSinceLastLevel());
            getForms().put(POKEMON_HAPPINESS, simulation.getTeam().get(selectedPk).getHappiness());
            getForms().put(HEAL_EDIT_PK, false);
            getForms().put(POKEMON_HP, simulation.getTeam().get(selectedPk).getRemainingHp());
            getForms().put(CATCHING_BALL, simulation.getTeam().get(selectedPk).getUsedBallCatching());
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                getForms().put(POKEMON_EV_VAR+s.name(), simulation.getTeam().get(selectedPk).getEv().getVal(s));
            }
            return EDIT_POKEMON_PLAYER;
        }
        if (selectedAction == TeamCrud.REMOVE) {
            int index_ = selectedPk;
            team.removeAt(index_);
            int size_ = team.size();
            for (int i = index_; i < size_;i++) {
                team.get(i).setIndex(i);
            }
            simulation.removePokemonPlayer(index_);
        }
        return DataBase.EMPTY_STRING;
    }

//    @Accessible
//    private String edit() {
//        CustList<Integer> indexes_ = new CustList<>();
//        int i_ = CustList.FIRST_INDEX;
//        for (PokemonPlayerDto p: team) {
//            if (p.isSelected()) {
//                indexes_.add(i_);
//            }
//            i_++;
//        }
//        if (indexes_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
//            return DataBase.EMPTY_STRING;
//        }
//        getForms().put(ITEMS_SET_EDIT, new StringList());
//        getForms().put(POKEMON_INDEX_EDIT, indexes_.first());
//        getForms().put(POKEMON_NAME_EDIT, team.get(indexes_.first()).getPokemon().getName());
//        getForms().put(POKEMON_LEVEL_EDIT, team.get(indexes_.first()).getPokemon().getLevel());
//        getForms().put(ITEM_EDIT, team.get(indexes_.first()).getPokemon().getItem());
//        getForms().put(POKEMON_MOVES_EDIT, team.get(indexes_.first()).getMoves());
//        getForms().put(POKEMON_EXPERIENCE, simulation.getTeam().get(indexes_.first()).getWonExpSinceLastLevel());
//        getForms().put(POKEMON_HAPPINESS, simulation.getTeam().get(indexes_.first()).getHappiness());
//        return EDIT_POKEMON_PLAYER;
//    }
//
//    @Accessible
//    private void remove() {
//        CustList<Integer> indexes_ = new CustList<>();
//        int i_ = CustList.FIRST_INDEX;
//        for (PokemonPlayerDto p: team) {
//            if (p.isSelected()) {
//                indexes_.add(i_);
//            }
//            i_++;
//        }
//        if (indexes_.size() != DataBase.ONE_POSSIBLE_CHOICE) {
//            return;
//        }
//        int index_ = indexes_.first();
//        team.removeAt(index_);
//        int size_ = team.size();
//        for (int i = index_; i < size_;i++) {
//            team.get(i).setIndex(i);
//        }
//        simulation.removePokemonPlayer(index_);
//    }

    @Accessible
    private String add() {
        getForms().put(POKEMON_SET_SIMU, new StringList());
        return ADD_POKEMON_PLAYER;
    }

    @Accessible
    private String getImage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        return ConverterBufferedImage.surroundImage(data_.getMiniPk().getVal(pk_.getPokemon().getName()));
    }

    @Accessible
    private String getName(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        return data_.translatePokemon(pk_.getPokemon().getName());
    }

    @Accessible
    private int getLevel(Long _index) {
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        return pk_.getPokemon().getLevel();
    }

    @Accessible
    private String getAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        return data_.translateAbility(pk_.getPokemon().getAbility());
    }

    @Accessible
    private String getGender(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        return data_.translateGenders(pk_.getPokemon().getGender());
    }

    @Accessible
    private StringList getMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        StringList list_ = new StringList();
        for (String m: pk_.getMoves()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private String getItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayerDto pk_ = team.get(_index.intValue());
        if (pk_.getPokemon().getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getPokemon().getItem());
    }

    @Accessible
    private void validateTeam() {
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
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
//        if (!enableEvolutions) {
//            simulation.initializeFrontFighters();
//            getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
//            stepNumber++;
//            stepNumber++;
//            return;
//        }
        getForms().put(SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
        stepNumber++;
    }

    @Accessible
    private void validateFoeChoiceSkipEvolutions() {
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
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        simulation.initializeFrontFighters();
        getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
        stepNumber++;
    }

    @Accessible
    private void cancelTeam() {
        ok = true;
        team.clear();
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber--;
    }

    @Accessible
    private void cancelEvolutions() {
        ok = true;
        simulation.cancelEvolutions();
        getForms().removeKey(POKEMON_INDEX_EDIT);
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING;
        getForms().put(SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber--;
    }

    @Accessible
    private void validateEvolutions() {
        ok = true;
        simulation.initializeFrontFighters();
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().removeKey(POKEMON_INDEX_EDIT);
        getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
    }

    @Accessible
    private boolean selectedIndex() {
        return getForms().contains(POKEMON_INDEX_EDIT);
    }

    @Accessible
    private void displayEvolutions() {
        if (selectedPk == CustList.INDEX_NOT_FOUND_ELT) {
            if (availableEvos != null) {
                availableEvos.clear();
            }
            return;
        }
        getForms().put(POKEMON_INDEX_EDIT, selectedPk);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(selectedPk);
        DataBase data_ = (DataBase) getDataBase();
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }

    @Accessible
    private String getPokemonEvo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String pk_ = availableEvos.getKey(_index.intValue());
        return data_.getTranslatedPokemon().getVal(getLanguage()).getVal(pk_);
    }

    @Accessible
    private void validateEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = (DataBase) getDataBase();
        int index_ = (Integer) getForms().getVal(POKEMON_INDEX_EDIT);
        levelEvo = (short) Math.max(levelEvo, availableEvosLevel.getVal(chosenEvo));
        simulation.setNextEvolutions(index_, chosenEvo, levelEvo, data_);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(index_);
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }

    @Accessible
    private void cancelEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = (DataBase) getDataBase();
        int index_ = (Integer) getForms().getVal(POKEMON_INDEX_EDIT);
        simulation.cancelEvolutions(index_, data_);
        StringMap<Short> evos_ = simulation.getAvailableEvolutions().get(index_);
        availableEvosLevel = new StringMap<Short>(evos_);
        StringMap<String> map_ = data_.getTranslatedPokemon().getVal(getLanguage());
        availableEvos = new TreeMap<String, String>(new ComparatorTrStrings(map_));
        for (String e: evos_.getKeys()) {
            availableEvos.put(e, map_.getVal(e));
        }
    }

    @Accessible
    private void validateFrontFighter() {
        displayIfError = false;
        if (selectedPk == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        simulation.getFrontFighters().first().get(selectedRound).put((byte) selectedPk, (byte) placeFight);
    }

    @Accessible
    private void cancelFrontFighters() {
        displayIfError = false;
        ok = true;
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.cancelEvolutions();
            getForms().put(SIMULATION_STATE, SimulationSteps.TEAM);
            stepNumber--;
            stepNumber--;
            return;
        }
        selectedAction = TeamCrud.NOTHING;
        getForms().put(SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
        stepNumber--;
    }

    @Accessible
    private void validateFrontFighters() {
        displayIfError = true;
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        try {
            simulation.prepareMovesToBeLearntOneFight(data_);
        } catch (RuntimeException _0) {
            ok = false;
            return;
        }
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.initializeAllMoves(data_);
            getForms().put(SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
            stepNumber++;
            stepNumber++;
            return;
        }
        getForms().put(SIMULATION_STATE, SimulationSteps.MOVES);
        stepNumber++;
    }

    @Accessible
    private boolean selectedIndexForMoves() {
        return selectedPk != CustList.INDEX_NOT_FOUND_ELT;
    }

    @Accessible
    private boolean isAvailableMoves() {
        return simulation.isAvailableMoves(selectedPk);
    }

    @Accessible
    private boolean isAvailableAbilities() {
        return simulation.isAvailableAbilities(selectedPk);
    }

//    @Accessible
//    private TreeMap<String, String> getDisplayedAbilities() {
//        DataBase data_ = (DataBase) getDataBase();
//        Map<String,String> translatedAbilities_;
//        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        TreeMap<String,String> abilities_ = new TreeMap<>(new ComparatorTrString<>(translatedAbilities_));
//        abilities_.put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
//        for (String a: getAvailableAbilities()) {
//            abilities_.put(a, translatedAbilities_.getVal(a));
//        }
//        return abilities_;
//    }

    private StringList getAvailableAbilities() {
        return simulation.getAvailableAbilities(selectedPk);
    }

    @Accessible
    private void validateMoves() {
        displayIfError = false;
        if (isAvailableAbilities()) {
            if (currentAbility.isEmpty()) {
                return;
            }
            int r_ = simulation.getAvailableMoves().getVal((byte) selectedPk).getKey().getSecond();
            simulation.setAbilityWhileFight(selectedPk, CustList.FIRST_INDEX, r_, currentAbility);
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

    @Accessible
    private void cancelMoves() {
        displayIfError = false;
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        simulation.cancelAllMovesOneFight(selectedPk, data_);
    }

    @Accessible
    private void cancelMovesSets() {
        displayIfError = false;
        ok = true;
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber--;
    }

    @Accessible
    private void validateMovesSets() {
        displayIfError = true;
        DataBase data_ = (DataBase) getDataBase();
        ok = true;
        simulation.validateAllMoves(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber++;
    }

    @Accessible
    private void validateMovesChoice() {
        displayIfError = false;
        if (selectedMove == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        String move_ = movesSet.get(selectedMove).getName();
        DataBase data_ = (DataBase) getDataBase();
        simulation.chooseMoveFirstFight(selectedRound, selectedPk, move_, allyChoice, target, data_);
    }

    @Accessible
    private void cancelMovesEvos() {
        displayIfError = false;
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        selectedMove = CustList.INDEX_NOT_FOUND_ELT;
        ok = true;
        if (!enableEvolutions) {
            getForms().put(SIMULATION_STATE, SimulationSteps.FRONT);
            stepNumber--;
            stepNumber--;
            return;
        }
        getForms().put(SIMULATION_STATE, SimulationSteps.MOVES);
        stepNumber--;
    }

    @Accessible
    private void displayComments() {
        comments.clear();
        for (String l: simulation.getComment()) {
            comments.add(escapedStringQuote(l));
        }
    }

    @Accessible
    private void hideComments() {
        comments.clear();
    }

    @Accessible
    private void changeFight() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        simulation.validateTeam(data_);
        teamAfterFight.clear();
        getForms().put(SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber--;
    }

    @Accessible
    private void changeFightWhileEnd() {
        ok = true;
        keptMovesAfterFight.clear();
        keptMovesAbilitiesDto.clear();
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMULATION_STATE, SimulationSteps.SIMULATION);
        stepNumber--;
    }

    @Accessible
    private void simulateFight() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        simulation.validateTeam(data_);
        simulation.simulateFight(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        getForms().put(SIMULATION_STATE, SimulationSteps.SIMULATION);
        stepNumber++;
    }

    @Accessible
    private void nextFight() {
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
        selectedPk = CustList.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMULATION_STATE, SimulationSteps.EVO_AFTER_FIGHT);
        stepNumber++;
    }

    @Accessible
    private boolean isFightAfter() {
        if (teamAfterFight.isEmpty()) {
            return false;
        }
        DataBase data_ = (DataBase) getDataBase();
        if (!simulation.hasNextFight(data_)) {
            return false;
        }
        return true;
    }

    @Accessible
    private String getImageAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return ConverterBufferedImage.surroundImage(data_.getMiniPk().getVal(pk_.getName()));
    }

    @Accessible
    private String getNameAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return data_.translatePokemon(pk_.getName());
    }

    @Accessible
    private int getLevelAfterFight(Long _index) {
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return pk_.getLevel();
    }

    @Accessible
    private String getAbilityAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return data_.translateAbility(pk_.getAbility());
    }

    @Accessible
    private String getGenderAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return data_.translateGenders(pk_.getGender());
    }

    @Accessible
    private StringList getMovesAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        StringList list_ = new StringList();
        for (String m: pk_.getMoves().getKeys()) {
            list_.add(data_.translateMove(m));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private String getItemAfterFight(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        if (pk_.getItem().isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        return data_.translateItem(pk_.getItem());
    }

    @Accessible
    private LgInt getRemainingLifeRate(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        return pk_.rateRemainHp(data_);
    }

    @Accessible
    private Rate numberNecessaryPointsForGrowingLevel(Long _index){
        DataBase data_ = (DataBase) getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(_index.intValue());
        short level_ = pk_.getLevel();
        PokemonData fPk_=data_.getPokemon(pk_.getName());
        String expLitt_=data_.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level_ + 1));
        Rate next_;
        next_ = data_.evaluate(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level_));
        current_ = data_.evaluate(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toString(), vars_, Rate.one());
        diff_.removeNb(pk_.getWonExpSinceLastLevel());
        return diff_;
    }

    @Accessible
    private void selectPkAfterFight() {
        if (selectedPk == CustList.INDEX_NOT_FOUND_ELT) {
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

    @Accessible
    private void validateEvolutionAfterFight() {
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

    @Accessible
    private void cancelEvolutionsAfterFight() {
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

    @Accessible
    private void validateMovesAbilityAfterFight() {
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

    @Accessible
    private void validateMovesAfterFight() {
        ok = true;
        DataBase data_ = (DataBase) getDataBase();
        int len_ = teamAfterFight.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
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
        int i_ = CustList.FIRST_INDEX;
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
        getForms().put(SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber = 2;
    }

    @Accessible
    private boolean proponeEvolutions() {
        return simulation.proponeEvolutions();
    }

    @Accessible
    private boolean isIssue() {
        return simulation.getProbleme();
    }

    @Accessible
    private boolean isIssueAfterFight() {
        if (!simulation.isAcceptableChoices()) {
            //the fight could not be finished
            return false;
        }
        return simulation.getProbleme();
    }

    @Accessible
    private StringList getKoPlayers() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getKoPlayers()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private StringList getNotKoFrontFoes() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getNotKoFrontFoes()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private StringList getKoFoes() {
        DataBase data_ = (DataBase) getDataBase();
        StringList list_ = new StringList();
        for (String f: simulation.getKoFoes()) {
            list_.add(data_.translatePokemon(f));
        }
        list_.sort();
        return list_;
    }

    @Accessible
    private NatTreeMap<Byte, StringList> getEvolutionsAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        NatTreeMap<Byte, StringList> tree_;
        tree_ = new NatTreeMap<Byte, StringList>();
        NatTreeMap<Byte, StringList> recTree_ = simulation.getEvolutionsAfterFight();
        for (byte b: recTree_.getKeys()) {
            StringList pk_ = new StringList();
            for (String m: recTree_.getVal(b)) {
                pk_.add(data_.translatePokemon(m));
            }
            pk_.sort();
            tree_.put(b, pk_);
        }
        return tree_;
    }

    @Accessible
    private NatTreeMap<Byte, StringList> getMovesNoEvoAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        NatTreeMap<Byte, StringList> tree_;
        tree_ = new NatTreeMap<Byte, StringList>();
        NatTreeMap<Byte, StringList> recTree_ = simulation.getMovesNoEvoAfterFight();
        for (byte b: recTree_.getKeys()) {
            StringList moves_ = new StringList();
            for (String m: recTree_.getVal(b)) {
                moves_.add(data_.translateMove(m));
            }
            moves_.sort();
            tree_.put(b, moves_);
        }
        return tree_;
    }

    @Accessible
    private NatTreeMap<Byte, NatTreeMap<String, StringList>> getAbilitiesAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> ab_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> pk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        NatTreeMap<Byte, NatTreeMap<String, StringList>> tree_;
        tree_ = new NatTreeMap<Byte, NatTreeMap<String, StringList>>();
        NatTreeMap<Byte,StringMap<StringList>> recTree_;
        recTree_ = simulation.getAbilitiesAfterFight();
        for (byte b: recTree_.getKeys()) {
            NatTreeMap<String, StringList> tr_ = new NatTreeMap<String, StringList>();
            for (String e: recTree_.getVal(b).getKeys()) {
                StringList abilities_ = new StringList();
                for (String m: recTree_.getVal(b).getVal(e)) {
                    abilities_.add(ab_.getVal(m));
                }
                abilities_.sort();
                tr_.put(pk_.getVal(e), abilities_);
            }
            tree_.put(b, tr_);
        }
        return tree_;
    }

    @Accessible
    private NatTreeMap<Byte, NatTreeMap<String, StringList>> getMovesAfterFight() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> mv_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> pk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        NatTreeMap<Byte, NatTreeMap<String, StringList>> tree_;
        tree_ = new NatTreeMap<Byte, NatTreeMap<String, StringList>>();
        NatTreeMap<Byte,StringMap<StringList>> recTree_;
        recTree_ = simulation.getMovesAfterFight();
        for (byte b: recTree_.getKeys()) {
            NatTreeMap<String, StringList> tr_ = new NatTreeMap<String, StringList>();
            for (String e: recTree_.getVal(b).getKeys()) {
                StringList moves_ = new StringList();
                for (String m: recTree_.getVal(b).getVal(e)) {
                    moves_.add(mv_.getVal(m));
                }
                moves_.sort();
                tr_.put(pk_.getVal(e), moves_);
            }
            tree_.put(b, tr_);
        }
        return tree_;
    }

    @Accessible
    private boolean isRulesIssue() {
        return simulation.getIssue().isRules();
    }

    @Accessible
    private boolean isRulesMovesIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_MOVES;
    }

    @Accessible
    private boolean isRulesLearnIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_LEARN;
    }

    @Accessible
    private boolean isRulesSwitchIssue() {
        return simulation.getIssue() == IssueSimulation.RULES_SWITCH;
    }

    @Accessible
    private boolean isSendingIssue() {
        return simulation.getIssue() == IssueSimulation.SENDING_ISSUE;
    }

    @Accessible
    private boolean isRandomIssue() {
        return simulation.getIssue() == IssueSimulation.RANDOM;
    }

    @Accessible
    private boolean isUsingIssue() {
        return simulation.getIssue() == IssueSimulation.CANNOT_USE;
    }

    @Accessible
    private boolean isHardSimulationIssue() {
        return simulation.getIssue() == IssueSimulation.TOO_HARD_SIMULATION;
    }

    @Accessible
    private int getRealStepNumber() {
        return stepNumber + 1;
    }

//    @Accessible
//    private StringList getEvolutions(Long _index) {
//        TreeMap<Byte,ChoiceOfEvolutionAndMoves> tree_;
//        tree_ = new TreeMap<Byte, ChoiceOfEvolutionAndMoves>(new);
//        tree_.putAllMap(simulation.getChoices());
//        String n_ = tree_.getValue(_index.intValue()).getName();
//        if (n_.isEmpty()) {
//            return n_;
//        }
//        DataBase data_ = (DataBase) getDataBase();
//        return data_.translatePokemon(n_);
//    }

//    @Accessible
//    private String getAbilities(Long _index) {
//        TreeMap<Byte,ChoiceOfEvolutionAndMoves> tree_;
//        tree_ = new TreeMap<Byte, ChoiceOfEvolutionAndMoves>(new);
//        tree_.putAllMap(simulation.getChoices());
//        String n_ = tree_.getValue(_index.intValue()).getName();
//        if (n_.isEmpty()) {
//            return n_;
//        }
//        DataBase data_ = (DataBase) getDataBase();
//        return data_.translatePokemon(n_);
//    }

    @Accessible
    private String quit() {
        simulation = null;
        team.clear();
        teamAfterFight.clear();
        getForms().removeKey(SIMULATION_STATE);
        getForms().removeKey(COORDS);
        getForms().removeKey(INSIDE);
        getForms().removeKey(LEVEL_MAP_INDEX);
        getForms().removeKey(PLACE_MAP_INDEX);
        stepNumber = 0;
        ok = true;
        return LEVEL;
    }

//    @Accessible
//    private void test() {
//
//    }
}
