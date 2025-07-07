package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.beans.facade.dto.KeptMovesAfterFight;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.dto.PokemonTrainerDto;
import aiki.beans.facade.simulation.dto.RadioLineMove;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.beans.fight.*;
import aiki.beans.game.*;
import aiki.beans.moves.MovesBean;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.MoveData;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
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
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class SimulationBean extends CommonBean  implements WithDifficultyCommon, BeanRenderWithAppName {
    public static final String CONFIRM = "\u2611";
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
    private IntBeanChgLong multiplicity = new BeanChgLong();

    private final CustList<EnvironmentType> environmentsList = new CustList<EnvironmentType>();
    private DictionaryComparator<String, String> environments;
    private IntBeanChgString environment = new BeanChgString();

    private boolean enableEvolutions = true;
    private final CustList<PokemonPlayerDto> team = new CustList<PokemonPlayerDto>();
    private int selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
    private String selectedFoeAction = TeamCrud.NOTHING.getTeamCrudString();
    private String selectedAllyAction = TeamCrud.NOTHING.getTeamCrudString();
    private String selectedAction = TeamCrud.NOTHING.getTeamCrudString();

    private StringMap<Long> availableEvosLevel = new StringMap<Long>();
    private DictionaryComparator<String, String> availableEvos;

    private IntBeanChgString chosenEvo = new BeanChgString();
    private IntBeanChgLong levelEvo = new BeanChgLong();

    private FightSimulation simulation;

    private final Difficulty difficulty = new Difficulty();

    private long noFight;
    private IntBeanChgInt selectedRound = new BeanChgInt();
    private IntTreeMap< String> round = new IntTreeMap< String>();

    private IntBeanChgInt placeFight = new BeanChgInt();
    private IntTreeMap< String> placesFight = new IntTreeMap< String>();
    private IntBeanChgInt target =new BeanChgInt();
    private IntTreeMap< String> targetFight = new IntTreeMap< String>();
    private IntBeanChgString currentAbility = new BeanChgString();
    private DictionaryComparator<String,String> abilities;
    private final CustList<SelectLineMove> keptMoves = new CustList<SelectLineMove>();
    private final CustList<RadioLineMove> movesSet = new CustList<RadioLineMove>();
    private int selectedMove;
    private IntBeanChgBool allyChoice = new BeanChgBool();
    private final StringList comments = new StringList();

    private CustList<PokemonPlayer> teamAfterFight = new CustList<PokemonPlayer>();
    private final CustList<KeptMovesAfterFight> keptMovesAbilitiesDto = new CustList<KeptMovesAfterFight>();
    private final CustList<SelectLineMove> keptMovesAfterFight = new CustList<SelectLineMove>();
    private IntBeanChgString evolutionAfterFight = new BeanChgString();
    private DictionaryComparator<String,String> evolutionsAfterFight;
    private IntBeanChgString abilityAfterFight = new BeanChgString();
    private DictionaryComparator<String,String> abilitiesAfterFight;
    private int stepNumber;
    private boolean ok;
    private boolean displayIfError;
    private final DifficultyBeanForm form = new DifficultyBeanForm();
    private IntBeanChgLong nbTeamsField;
    private IntBeanChgSubmit updateValues;
    private IntBeanChgSubmit cancelDiffChoice;
    private IntBeanChgSubmit validMultEnv;
    private IntBeanChgSubmit addPkTrainer;
    private IntBeanChgSubmit validNpcTeams;
    private IntBeanChgString seed = new BeanChgString();
    private IntBeanChgLong nbFleeAttempt;
    private IntBeanChgLgInt nbRounds;
    private IntBeanChgRate winningMoney;
    private IntBeanChgList<String> lostObjects;
    private IntBeanChgList<String> caughtEvolutions;
    private IntBeanChgInt indexUserTeam;
    private AbsMap<TeamPosition, String> currentUserList;
    private IntBeanChgInt indexFightState;
    private AbsMap<FightState, String> fightState;

    public SimulationBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
        chosenEvo.setupValue(DataBase.EMPTY_STRING);
        currentAbility.setupValue(DataBase.EMPTY_STRING);
        evolutionAfterFight.setupValue(DataBase.EMPTY_STRING);
        abilityAfterFight.setupValue(DataBase.EMPTY_STRING);
        selectedRound.valueInt(0);
        target.valueInt(0);
        placeFight.valueInt(Fighter.BACK);
    }
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataSimulation.M_P_86_TITLE_PARAM),Long.toString(getRealStepNumber())));
        formatMessageAnc(new SimulationBeanQuit(this),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_INDEX);
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        if (simu_ == SimulationSteps.DIFF) {
            form.displayDiff(getBuilder().getGenInput(), this, getDifficultyCommon(), MessagesPkBean.DIFFICULTY);
            formatMessage(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_FREE_TEAMS);
            nbTeamsField = getBuilder().getGenInput().newLong();
            getNbTeamsField().valueLong(0);
            updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON));
            getUpdateValues().addEvt(new SimulationBeanValidDiffFormEvent(this, form));
        } else if (simu_ == SimulationSteps.FOE) {
            foe();
            errorsFoe();
        } else if (simu_ == SimulationSteps.TEAM) {
            team();
        } else if (simu_ == SimulationSteps.EVOLUTIONS) {
            evolutions();
        } else if (simu_ == SimulationSteps.FRONT) {
            front();
        } else if (simu_ == SimulationSteps.MOVES) {
            moves();
        } else if (simu_ == SimulationSteps.MOVES_FIGHT) {
            movesFight();
        } else if (simu_ == SimulationSteps.SIMULATION) {
            simulation();
        } else if (simu_ == SimulationSteps.SIMULATION_STEP) {
            simulationStep();
        } else {
            evoAfterFight();
        }
    }

    private void errorsFoe() {
        String k_;
        if (freeTeams) {
            k_ = MessagesDataSimulation.M_P_86_EMPTY_FOE_TEAM;
        } else {
            k_ = MessagesDataSimulation.M_P_86_NOT_SELECTED_FOE;
        }
        if (!ok) {
            formatMessage(MessagesPkBean.SIMU,k_);
        }
    }

    private void foe() {
        if (freeTeams) {
            Ints nbs_ = getNumbers();
            int len_ = nbs_.size();
            formatMessage(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SELECT_TEAM);
            for (int i = 0; i < len_; i++) {
                IntBeanChgSubmit chgIndex_ = getBuilder().button(Long.toString(nbs_.get(i)));
                chgIndex_.addEvt(new SimulationBeanValidateIndexTeamAction(this,i));
            }
            formatMessageDir(Long.toString(selectedTeamNumber()));
            addPkTrainer = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_ADD));
            getAddPkTrainer().addEvt(new SimulationBeanAddPkTrainer(this));
            new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDtoFoe(this)).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SELECT_FOE_PK,MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
            new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDtoAlly(this)).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SELECT_ALLY_PK,MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
            cancelDiffChoice = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON));
            getCancelDiffChoice().addEvt(new SimulationBeanCancelDiffChoice(this));
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MULTIPLICITY_FIGHT);
            multiplicity = DifficultyBeanForm.iv(getBuilder().getGenInput(), this, multiplicity());
            feedParents();
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENV_FIGHT);
            environment = DifficultyBeanForm.select(getBuilder().getGenInput(), this, environments, environment());
            feedParents();
            validMultEnv = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON));
            getValidMultEnv().addEvt(new SimulationBeanValidateMultiplicityEnvAction(this));
            validNpcTeams = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON));
            getValidNpcTeams().addEvt(new SimulationBeanValidateFoeChoiceFree(this));
            return;
        }
        int pls_ = places.size();
        for (int p = 0; p < pls_; p++) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(places.get(p).getPlace().getName());
            if (isMultiLayer(p)) {
                CustList<Level> layers_ = layers(p);
                int len_ = layers_.size();
                for (int i = 0; i < len_; i++) {
                    formatMessageDirAnc("->"+Long.toString(i),new SimulationBeanClickLevel(this,places.get(p).getIndex(),i));
                }
            } else {
                formatMessageAnc(new SimulationBeanClickLevel(this,places.get(p).getIndex(),0),MessagesPkBean.MAP,MessagesDataMapLevel.M_P_32_GOLEVEL);
            }
            feedParents();
        }
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NO_FIGHT);
        formatMessageDir(getTrainerName());
        cancelDiffChoice = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON));
        getCancelDiffChoice().addEvt(new SimulationBeanCancelDiffChoice(this));
        validNpcTeams = getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON));
        getValidNpcTeams().addEvt(new SimulationBeanValidateFoeChoice(this));
    }

    public DifficultyBeanForm getForm() {
        return form;
    }

    public IntBeanChgSubmit getAddPkTrainer() {
        return addPkTrainer;
    }

    public IntBeanChgSubmit getCancelDiffChoice() {
        return cancelDiffChoice;
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public IntBeanChgLong getNbTeamsField() {
        return nbTeamsField;
    }

    public IntBeanChgSubmit getValidMultEnv() {
        return validMultEnv;
    }

    public IntBeanChgSubmit getValidNpcTeams() {
        return validNpcTeams;
    }

    private void team() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_PK);
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD)).addEvt(new SimulationBeanAdd(this));
        new BeanDisplayListGrid<PokemonPlayerDto>(new BeanDisplayPokemonPlayerDtoCrud(this)).displayGrid(this,getTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanCancelTeam(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanValidateTeam(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_DISABLE_EVOS)).addEvt(new SimulationBeanValidateFoeChoiceSkipEvolutions(this));
//        seed = DifficultyBeanForm.txt(getBuilder().getGenInput(), this, "");
//        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SIMU_BY_STEP)).addEvt(new SimulationBeanIntroFight(this));
        if (!ok) {
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NOT_SELECTED_PLAYER_PK);
        }
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
    }

    private void evolutions() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_PK_EVOS);
        new BeanDisplayListGrid<PokemonPlayerDto>(new BeanDisplayPokemonPlayerDtoSelect(this)).displayGrid(this,getTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
        if (selectedIndex()) {
            if (!availableEvos.isEmpty()) {
                setChosenEvo(DifficultyBeanForm.select(getBuilder().getGenInput(), this, availableEvos, chosenEvo.tryRet()));
                initLine();
                setLevelEvo(DifficultyBeanForm.iv(getBuilder().getGenInput(), this, levelEvo.valueLong()));
                feedParents();
                getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SELECT)).addEvt(new SimulationBeanValidateEvo(this));
            }
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CANCEL_EVO)).addEvt(new SimulationBeanCancelEvo(this));
        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanCancelEvolutions(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanValidateEvolutions(this));
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
    }

    private void front() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_SENT_PK);
        new BeanDisplayListGrid<PokemonPlayerDto>(new BeanDisplayPokemonPlayerDtoSelectFront(this)).displayGrid(this,getTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
        initPage();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ROUND);
        setSelectedRound(DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, round, getSelectedRound().valueInt()));
        feedParents();
        initPage();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MULTIPLICITY);
        setPlaceFight(DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, placesFight, getPlaceFight().valueInt()));
        feedParents();
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanCancelFrontFighters(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanValidateFrontFighters(this));
        possibleError(MessagesDataSimulation.M_P_86_BAD_CHOICE_FRONT_FIGHTER);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
    }

    private void moves() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_PK_MOVES);
        new BeanDisplayListGrid<PokemonPlayerDto>(new BeanDisplayPokemonPlayerDtoSelectMoves(this)).displayGrid(this,getTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
        if (selectedIndexForMoves()) {
            if (isAvailableMoves()) {
                if (isAvailableAbilities()) {
                    initLine();
                    formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ABILITY_PK);
                    setCurrentAbility(DifficultyBeanForm.select(getBuilder().getGenInput(), this, abilities, currentAbility.tryRet()));
                    feedParents();
                }
                new BeanDisplayListGrid<SelectLineMove>(new BeanDisplaySelectLineMove()).displayGrid(this,keptMoves,MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
                getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_VALIDATE_MOVES)).addEvt(new SimulationBeanValidateMoves(this));
            }
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL_MOVES)).addEvt(new SimulationBeanCancelMoves(this));
        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanCancelMovesSets(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanValidateMovesSets(this));
        possibleError(MessagesDataSimulation.M_P_86_BAD_CHOICE_FRONT_FIGHTER_MOVES);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
    }
    private void possibleError(String _key) {
        int both_ = NumberUtil.signum(toInt(!ok)) * NumberUtil.signum(toInt(displayIfError));
        if (both_ == TRUE_VALUE) {
            formatMessage(MessagesPkBean.SIMULATION, _key);
        }
    }
    private void movesFight() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_PK_MOVE_TARGET);
        new BeanDisplayListGrid<PokemonPlayerDto>(new BeanDisplayPokemonPlayerDtoSelectMoves(this)).displayGrid(this,getTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_SELECTED);
        initPage();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ROUND);
        setSelectedRound(DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, round, getSelectedRound().valueInt()));
        feedParents();
        if (selectedIndexForMoves()) {
            new BeanDisplayListGrid<RadioLineMove>(new BeanDisplayRadioLineMove(this)).displayGrid(this,movesSet,MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICE);
            setAllyChoice(DifficultyBeanForm.check(getBuilder().getGenInput(), this,allyChoice.genericValue()));
            feedParents();
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICE);
            setTarget(DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, targetFight, target.valueInt()));
            feedParents();
        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanCancelMovesEvos(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanSimulateFight(this));
        if (!ok) {
            formatMessage(MessagesPkBean.SIMULATION, MessagesDataSimulation.M_P_86_BAD_CHOICE_FRONT_FIGHTER_MOVES_TARGETS);
        }
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getFoeTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
        new BeanDisplayListGrid<PokemonTrainerDto>(new BeanDisplayPokemonTrainerDto()).displayGrid(this,getAllyTeam(),MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK);
    }

    private void simulation() {
        if (isIssue()) {
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,getKoPlayers(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KO_PLAYER_FIGHTERS);
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,getNotKoFrontFoes(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NOT_KO_FOE_FIGHTERS);
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,getKoFoes(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KO_FOE_FIGHTERS);
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,comments);
        }
        new BeanDisplayListGrid<PokemonPlayer>(new BeanDisplayPokemonPlayer()).displayGrid(this,teamAfterFight,MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_HP_RATE,MessagesDataSimulation.M_P_86_EXP_PK,MessagesDataSimulation.M_P_86_EXP_PK_REM,MessagesDataSimulation.M_P_86_HAPPINESS_PK);
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanChangeFight(this));
        if (isFightAfter()) {
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanNextFight(this));
        }
        if (!isIssue()) {
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_DISPLAY_COMMENTS)).addEvt(new SimulationBeanDisplayComments(this));
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HIDE_COMMENTS)).addEvt(new SimulationBeanHideComments(this));
            new BeanDisplayList<String>(new BeanDisplayString()).display(this,comments);
        }
    }

    private void simulationStep() {
        initPage();
        setTitledBorder("");
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_FLEE_ATTEMPT);
        nbFleeAttempt = DifficultyBeanForm.iv(getBuilder().getGenInput(), this, simulation.getGame().getFight().getNbFleeAttempt());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_ROUND);
        nbRounds = DifficultyBeanForm.lgInt(getBuilder().getGenInput(), this, simulation.getGame().getFight().getNbRounds());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_WINNING_MONEY);
        winningMoney = DifficultyBeanForm.rate(getBuilder().getGenInput(), this, simulation.getGame().getFight().getWinningMoney());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CURRENT_USER);
        indexUserTeam = DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, curUserListIndex(currentUserList), currentUserList.indexOfEntry(simulation.getGame().getFight().getCurrentUser()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_FIGHT_STATE);
        indexFightState = DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, fightStateListIndex(fightState), fightState.indexOfEntry(simulation.getGame().getFight().getState()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LOST_OBJECTS);
        lostObjects = DifficultyBeanForm.selectLs(getBuilder().getGenInput(), this, DictionaryComparatorUtil.buildItemsStrElts(getDataBase(), getLanguage()), simulation.getGame().getFight().getLostObjects());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CAUGHT_EVOS);
        caughtEvolutions = DifficultyBeanForm.selectLs(getBuilder().getGenInput(), this, DictionaryComparatorUtil.buildPkStrElts(getDataBase(), getLanguage()), simulation.getGame().getFight().getCaughtEvolutions());
        getBuilder().button(CONFIRM).addEvt(new SimulationBeanValidateFightCoreForm(this));
        feedParents();
        posit(simulation.getGame().getFight().getFirstPositPlayerFighters(), messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIRST_POSIT_PLAYER));
        posit(simulation.getGame().getFight().getFirstPositFoeFighters(), messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIRST_POSIT_FOE));
        stillEnMoves(sorted(simulation.getGame().getFight().getStillEnabledMoves()),simulation.getGame().getFight().getStillEnabledMoves(), MessagesDataSimulation.M_P_86_FIGHT_STILL_ENABLED_MOVES);
        enMoves(sortedAc(simulation.getGame().getFight().getEnabledMoves()),simulation.getGame().getFight().getEnabledMoves(), MessagesDataSimulation.M_P_86_FIGHT_ENABLED_MOVES);
        usedItems(sortedUsedItems());
        evosChoices();
        allyChoices();
        team(simulation.getGame().getFight().getUserTeam(), messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TITLE_PLAYER));
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PLAYER_FOE));
        initGrid();
        getBuilder().colCount(3);
        CustList<Integer> allKeys_ = simulation.getGame().getFight().getFoeTeam().getMembers().getKeys();
        IntMap<String> rep_ = new IntMap<String>();
        for (Integer i: allKeys_) {
            rep_.addEntry(i,Long.toString(i));
        }
        for (EntryCust<Integer,CustList<Integer>> e:simulation.getGame().getFight().getUserTeam().getPlayerFightersAgainstFoe().entryList()) {
            formatMessageDirCts(Long.toString(e.getKey()));
            initLine();
            IntBeanChgList<Integer> chgPl_ = DifficultyBeanForm.selectLsInts(getBuilder().getGenInput(), this, rep_, e.getValue());
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<Integer,CustList<Integer>>(e,chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
        team(simulation.getGame().getFight().getFoeTeam(), messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TITLE_FOE));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanResetFight(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanStepFight(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_DISPLAY_COMMENTS)).addEvt(new SimulationBeanDisplayComments(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HIDE_COMMENTS)).addEvt(new SimulationBeanHideComments(this));
        new BeanDisplayList<String>(new BeanDisplayString()).display(this,comments);
    }
    private void team(Team _t, String _title) {
        initPage();
        setTitledBorder(_title);
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TEAM_GENERALITY));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_KO_ROUND);
        IntBeanChgLong nbKoRound_ = DifficultyBeanForm.iv(getBuilder().getGenInput(), this, _t.getNbKoRound());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_KO_PREVIOUS_ROUND);
        IntBeanChgLong nbKoPreviousRound_ = DifficultyBeanForm.iv(getBuilder().getGenInput(), this, _t.getNbKoPreviousRound());
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SUCCESSFUL_MOVES_ROUND);
        IntBeanChgList<String> successfulMovesRound_ = DifficultyBeanForm.selectLs(getBuilder().getGenInput(), this, DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage()), _t.getSuccessfulMovesRound());
        getBuilder().button(CONFIRM).addEvt(new SimulationBeanValidateTeamCoreForm(this,_t,nbKoRound_,nbKoPreviousRound_, successfulMovesRound_));
        feedParents();
        group(_t);
        enMoves(sortedAc(_t.getEnabledMoves()),_t.getEnabledMoves(), MessagesDataSimulation.M_P_86_TEAM_ENABLED_MOVES);
        stillEnMoves(sorted(_t.getEnabledMovesWhileSendingFoe()), _t.getEnabledMovesWhileSendingFoe(), MessagesDataSimulation.M_P_86_ENBALED_MOVES_SEND);
        enabledMovesWhileSendingFoeUses(_t);
        usedItemsFix(sortedLg(_t.getNbUsesMoves()), _t.getNbUsesMoves());
        usedItemsFix(sortedLg(_t.getNbUsesMovesRound()), _t.getNbUsesMovesRound());
        healAfter(_t);
        ant(_t);
        int max_ = -1;
        for (EntryCust<Integer,Fighter> f:_t.getMembers().entryList()) {
            max_ = NumberUtil.max(f.getKey(),max_);
        }
        for (EntryCust<Integer,Fighter> f:_t.getMembers().entryList()) {
            initPage();
            setTitledBorder(Long.toString(f.getKey()));
            fighter(f.getValue(),max_+1);
            feedParents();
        }
        feedParents();
    }

    private void fighter(Fighter _f, int _max) {
        initPage();
        setTitledBorder("");
        getBuilder().getGenInput().newSubmit(CONFIRM).addEvt(new SimulationBeanAbstractAction(_f,new IntBeanChgFighter(one(_f,getBuilder().getGenInput()),two(_f,getBuilder().getGenInput()),three(_f,getBuilder().getGenInput()),four(_f,getBuilder().getGenInput()),five(_f,getBuilder().getGenInput()),six(_f,getBuilder().getGenInput(),_max))));
        feedParents();
    }

    private IntBeanChgFighter1 one(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> pk_ = DictionaryComparatorUtil.buildPkStrElts(getDataBase(), getLanguage());
        IntBeanChgString name_ = _inputGene.newString(pk_);
        name_.setupValue(_f.getName());
        IntBeanChgString nickname_ = _inputGene.newText();
        nickname_.setupValue(_f.getNickname());
        IntBeanChgGender gender_ = _inputGene.newGender(genders_);
        gender_.valGender(_f.getGender());
        IntBeanChgRate weight_ = _inputGene.newRate();
        weight_.valueRate(_f.getWeight());
        IntBeanChgRate height_ = _inputGene.newRate();
        height_.valueRate(_f.getHeight());
        IntBeanChgString currentName_ = _inputGene.newString(pk_);
        currentName_.setupValue(_f.getCurrentName());
        IntBeanChgGender currentGender_ = _inputGene.newGender(genders_);
        currentGender_.valGender(_f.getCurrentGender());
        return new IntBeanChgFighter1(name_, nickname_, gender_, weight_, height_, currentName_, currentGender_);
    }

    private IntBeanChgFighter2 two(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> ab_ = DictionaryComparatorUtil.buildAbStrElts(getDataBase(), getLanguage());
        ab_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStrElts(getDataBase(), getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getDataBase(), getLanguage());
        IntBeanChgString lastUsedItem_ = _inputGene.newString(it_);
        lastUsedItem_.setupValue(_f.getLastUsedItem());
        IntBeanChgString item_ = _inputGene.newString(it_);
        item_.setupValue(_f.getItem());
        IntBeanChgString currentAbility_ = _inputGene.newString(ab_);
        currentAbility_.setupValue(_f.getCurrentAbility());
        IntBeanChgLgInt nbRounds_ = _inputGene.newLgInt();
        nbRounds_.valueLgInt(_f.getNbRounds());
        IntBeanChgList<String> types_ = _inputGene.newStringList(ty_);
        types_.setupValue(_f.getTypes());
        IntBeanChgString ability_ = _inputGene.newString(ab_);
        ability_.setupValue(_f.getAbility());
        IntBeanChgRate remainingHp_ = _inputGene.newRate();
        remainingHp_.valueRate(_f.getRemainingHp());
        return new IntBeanChgFighter2(lastUsedItem_, item_, currentAbility_, nbRounds_, types_, ability_, remainingHp_);
    }

    private IntBeanChgFighter3 three(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getDataBase(), getLanguage());
        IntBeanChgBool acted_ = _inputGene.newBool();
        acted_.setSelected(_f.isActed());
        IntMap<String> ids_ = ids(simulation.getGame().getFight().getMult());
        IntBeanChgInt groundPlace_ = _inputGene.newInt(ids_);
        groundPlace_.valueInt(_f.getGroundPlace());
        IntBeanChgInt groundPlaceSubst_ = _inputGene.newInt(ids_);
        groundPlaceSubst_.valueInt(_f.getGroundPlaceSubst());
        IntBeanChgRate wonExp_ = _inputGene.newRate();
        wonExp_.valueRate(_f.getWonExp());
        IntBeanChgList<String> protectedAgainstMoveTypes_ = _inputGene.newStringList(ty_);
        protectedAgainstMoveTypes_.setupValue(_f.getProtectedAgainstMoveTypes());
        IntBeanChgRate wonExpSinceLastLevel_ = _inputGene.newRate();
        wonExpSinceLastLevel_.valueRate(_f.getWonExpSinceLastLevel());
        IntBeanChgRate clone_ = _inputGene.newRate();
        clone_.valueRate(_f.getClone());
        return new IntBeanChgFighter3(acted_, groundPlace_, groundPlaceSubst_, wonExp_, protectedAgainstMoveTypes_, wonExpSinceLastLevel_, clone_);
    }

    private IntBeanChgFighter4 four(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStrElts(getDataBase(), getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        IntBeanChgString usedBallCatching_ = _inputGene.newString(it_);
        usedBallCatching_.setupValue(_f.getUsedBallCatching());
        IntBeanChgLong level_ = _inputGene.newLong();
        level_.valueLong(_f.getLevel());
        IntBeanChgLong happiness_ = _inputGene.newLong();
        happiness_.valueLong(_f.getHappiness());
        IntBeanChgLong nbPrepaRound_ = _inputGene.newLong();
        nbPrepaRound_.valueLong(_f.getNbPrepaRound());
        IntBeanChgBool disappeared_ = _inputGene.newBool();
        disappeared_.setSelected(_f.isDisappeared());
        IntBeanChgBool needingToRecharge_ = _inputGene.newBool();
        needingToRecharge_.setSelected(_f.isNeedingToRecharge());
        IntBeanChgString lastSufferedMove_ = _inputGene.newString(mv_);
        lastSufferedMove_.setupValue(_f.getLastSufferedMove());
        return new IntBeanChgFighter4(usedBallCatching_, level_, happiness_, nbPrepaRound_, disappeared_, needingToRecharge_, lastSufferedMove_);
    }

    private IntBeanChgFighter5 five(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getDataBase(), getLanguage());
        IntBeanChgList<String> lastSufferedMoveTypes_ = _inputGene.newStringList(ty_);
        lastSufferedMoveTypes_.setupValue(_f.getTypes());
        IntBeanChgList<String> alreadyInvokedMovesRound_ = _inputGene.newStringList(ty_);
        alreadyInvokedMovesRound_.setupValue(_f.getAlreadyInvokedMovesRound());
        IntBeanChgString lastSuccessfulMove_ = _inputGene.newString(mv_);
        lastSuccessfulMove_.setupValue(_f.getLastSuccessfulMove());
        IntBeanChgLgInt nbRepeatingSuccessfulMoves_ = _inputGene.newLgInt();
        nbRepeatingSuccessfulMoves_.valueLgInt(_f.getNbRepeatingSuccessfulMoves());
        IntBeanChgBool usingItem_ = _inputGene.newBool();
        usingItem_.setSelected(_f.isUsingItem());
        IntBeanChgString usedMoveLastRound_ = _inputGene.newString(mv_);
        usedMoveLastRound_.setupValue(_f.getUsedMoveLastRound());
        IntBeanChgString lastUsedMove_ = _inputGene.newString(mv_);
        lastUsedMove_.setupValue(_f.getLastUsedMove());
        return new IntBeanChgFighter5(lastSufferedMoveTypes_, alreadyInvokedMovesRound_, lastSuccessfulMove_, nbRepeatingSuccessfulMoves_, usingItem_, usedMoveLastRound_, lastUsedMove_);
    }

    private IntBeanChgFighter6 six(Fighter _f, IntBeanGeneInput _inputGene, int _max) {
        AbsMap<Gender, String> translatedGenders_ = getDataBase().getTranslatedGenders().getVal(getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> mvList_ = DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage());
        IntBeanChgList<String> movesToBeLearnt_ = _inputGene.newStringList(mvList_);
        movesToBeLearnt_.setupValue(_f.getTypes());
        IntBeanChgBool successfulMove_ = _inputGene.newBool();
        successfulMove_.setSelected(_f.isSuccessfulMove());
        IntBeanChgBool changed_ = _inputGene.newBool();
        changed_.setSelected(_f.isChanged());
        IdMap<KindAction,String> ka_ = new IdMap<KindAction, String>();
        ka_.addEntry(KindAction.NO,messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_NO));
        ka_.addEntry(KindAction.MOVE,messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_MOVE));
        ka_.addEntry(KindAction.SWITCH,messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_SWITCH));
        ka_.addEntry(KindAction.HEAL,messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_HEAL));
        ka_.addEntry(KindAction.HEAL_MOVE,messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_HEAL_MOVE));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION);
        IntBeanChgKindAction chgAc_ = getBuilder().getGenInput().newKa(ka_);
        chgAc_.valueKa(kindAction(_f.getAction()));
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStr(getDataBase(), getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_FIRST);
        IntBeanChgString first_ = getBuilder().getGenInput().newString(mv_);
        first_.setupValue(first(_f.getAction()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_FINAL);
        IntBeanChgString last_ = getBuilder().getGenInput().newString(mv_);
        last_.setupValue(last(_f.getAction()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_ITEM);
        IntBeanChgString healIt_ = getBuilder().getGenInput().newString(it_);
        healIt_.setupValue(item(_f.getAction()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_SUB);
        IntBeanChgInt sub_ = getBuilder().getGenInput().newInt(ids(_max));
        sub_.valueInt(sub(_f.getAction()));
        CustList<TargetCoords> targetCoords_ = allValuesTarget();
        AbsMap<TargetCoords,String> valuesMap_ = new TargetCoordssString();
        int lenVal_ = targetCoords_.size();
        for (int i = 0; i < lenVal_; i++) {
            TargetCoords k_ = targetCoords_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_TAR);
        IntBeanChgTargetCoords targets_ = getBuilder().getGenInput().newTc(valuesMap_);
        targets_.valueTc(targetChoice(_f.getAction()));
        DifficultyBeanForm.formatMessage(this,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_TEAM);
        IntBeanChgBool team_ = getBuilder().getGenInput().newBool();
        team_.setSelected(team(_f.getAction()));
        IntBeanChgAction action_ = new IntBeanChgAction(chgAc_, first_, last_, healIt_, sub_, targets_, team_);
        return new IntBeanChgFighter6(movesToBeLearnt_, action_, successfulMove_, changed_);
    }

    private KindAction kindAction(AbstractAction _a) {
        if (_a == null) {
            return KindAction.NO;
        }
        return _a.getKindAction();
    }

    private TargetCoords targetChoice(AbstractAction _a) {
        TargetCoordsList tl_ = new TargetCoordsList();
        if (_a instanceof ActionMove) {
            tl_ = ((ActionMove)_a).getChosenTargets();
        }
        if (tl_.size() != 1) {
            return TargetCoords.def();
        }
        return tl_.first();
    }

    private String item(AbstractAction _a) {
        if (!(_a instanceof ChosenHealing)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ChosenHealing)_a).getChosenHealingItem();
    }

    private String first(AbstractAction _a) {
        if (!(_a instanceof ChosenMove)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ChosenMove)_a).getFirstChosenMove();
    }

    private String last(AbstractAction _a) {
        if (!(_a instanceof ActionMove)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ActionMove)_a).getFinalChosenMove();
    }

    private int sub(AbstractAction _a) {
        if (!(_a instanceof ChosenReplacing)) {
            return Fighter.BACK;
        }
        return ((ChosenReplacing)_a).getSubstitute();
    }

    private boolean team(AbstractAction _a) {
        if (!(_a instanceof ActionHeal)) {
            return false;
        }
        return ((ActionHeal)_a).isTeam();
    }

    private void group(Team _t) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_GROUPS));
        ListActivityOfMoves gr_ = _t.getEnabledMovesByGroup();
        initGrid();
        getBuilder().colCount(3);
        int len_ = gr_.size();
        DictionaryComparator<StringList, ListActivityOfMove> sorted_ = new DictionaryComparator<StringList, ListActivityOfMove>(new ComparatorStringList());
        for (int i = 0; i < len_; i++) {
            StringList key_ = new StringList();
            for (String m: gr_.get(i).getList()) {
                key_.add(getDataBase().getTranslatedMoves().getVal(getLanguage()).getVal(m));
            }
            key_.sort();
            sorted_.put(key_,gr_.get(i));
        }
        int mapLen_ = sorted_.size();
        for (int i = 0; i < mapLen_; i++) {
            formatMessageDirCts(StringUtil.join(sorted_.getKey(i), CommonFightBean.MOVES_SEPARATOR));
            initLine();
            IntBeanChgActivityOfMove chgPl_ = getBuilder().getGenInput().newAc();
            chgPl_.valueActivity(sorted_.getValue(i).getCombo());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateValue(gr_,sorted_.getValue(i).getList(),chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }

    private void enabledMovesWhileSendingFoeUses(Team _t) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_USES));
        initGrid();
        getBuilder().colCount(3);
        StringMap<StringMap<String>> tp_ = getDataBase().getTranslatedMoves();
        DictionaryComparator<String, LgInt> pk_ = new DictionaryComparator<String, LgInt>(tp_.getVal(getLanguage()));
        pk_.putAllMap(_t.getEnabledMovesWhileSendingFoeUses());
        for (EntryCust<String,LgInt> e: pk_.entryList()) {
            formatMessageDirCts(tp_.getVal(getLanguage()).getVal(e.getKey()));
            initLine();
            IntBeanChgLgInt choice_ = getBuilder().getGenInput().newLgInt();
            choice_.valueLgInt(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<String,LgInt>(_t.getEnabledMovesWhileSendingFoeUses().getEntryByKey(e.getKey()),choice_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }
    private IntMap<String> curUserListIndex(AbsMap<TeamPosition,String> _id) {
        return new ConverterToIntMapUtil<TeamPosition>().convert(_id);
    }

    private IntMap<String> fightStateListIndex(AbsMap<FightState,String> _id) {
        return new ConverterToIntMapUtil<FightState>().convert(_id);
    }
    private DictionaryComparator<TranslatedKey,BoolVal> sorted(StringMap<BoolVal> _from) {
        DictionaryComparator<TranslatedKey,BoolVal> o_ = new DictionaryComparator<TranslatedKey, BoolVal>(new ComparingTranslatedKey());
        for (EntryCust<String,BoolVal> e: _from.entryList()) {
            o_.put(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }
    private DictionaryComparator<TranslatedKey,Long> sortedLg(StringMap<Long> _from) {
        DictionaryComparator<TranslatedKey,Long> o_ = new DictionaryComparator<TranslatedKey, Long>(new ComparingTranslatedKey());
        for (EntryCust<String,Long> e: _from.entryList()) {
            o_.put(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    private DictionaryComparator<TranslatedKey,ActivityOfMove> sortedAc(StringMap<ActivityOfMove> _from) {
        DictionaryComparator<TranslatedKey,ActivityOfMove> o_ = new DictionaryComparator<TranslatedKey, ActivityOfMove>(new ComparingTranslatedKey());
        for (EntryCust<String,ActivityOfMove> e: _from.entryList()) {
            o_.put(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    private DictionaryComparator<TranslatedKey,Long> sortedUsedItems() {
        DictionaryComparator<TranslatedKey,Long> o_ = new DictionaryComparator<TranslatedKey,Long>(new ComparingTranslatedKey());
        for (EntryCust<String, Long> e:simulation.getGame().getFight().getUsedItemsWhileRound().entryList()) {
            o_.put(buildIt(getFacade(),e.getKey()),e.getValue());
        }
        return o_;
    }

    private void posit(IntMap<Integer> _map, String _title) {
        initPage();
        setTitledBorder(_title);
        initGrid();
        getBuilder().colCount(3);
        for (EntryCust<Integer,Integer> e: _map.entryList()) {
            formatMessageDirCts(Long.toString(e.getKey()));
            initLine();
            IntBeanChgInt chgPl_ = DifficultyBeanForm.selectInt(getBuilder().getGenInput(), this, ids(simulation.getGame().getFight().getMult()), e.getValue());
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<Integer,Integer>(e,chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }

    private void stillEnMoves(AbsMap<TranslatedKey, BoolVal> _map, StringMap<BoolVal> _info, String _key) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU, _key));
        initGrid();
        getBuilder().colCount(3);
        for (EntryCust<TranslatedKey,BoolVal> e: _map.entryList()) {
            formatMessageDirCts(e.getKey().getTranslation());
            initLine();
            IntBeanChgBool chgPl_ = DifficultyBeanForm.check(getBuilder().getGenInput(), this, e.getValue());
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<String,BoolVal>(_info.getEntryByKey(e.getKey().getKey()),chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }

    private void enMoves(AbsMap<TranslatedKey, ActivityOfMove> _map, StringMap<ActivityOfMove> _info, String _key) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU, _key));
        initGrid();
        getBuilder().colCount(3);
        for (EntryCust<TranslatedKey,ActivityOfMove> e: _map.entryList()) {
            formatMessageDirCts(e.getKey().getTranslation());
            initLine();
            IntBeanChgActivityOfMove chgPl_ = getBuilder().getGenInput().newAc();
            chgPl_.valueActivity(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<String,ActivityOfMove>(_info.getEntryByKey(e.getKey().getKey()),chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }

    private void usedItems(AbsMap<TranslatedKey, Long> _map) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_USED_ITEMS_WHILE_ROUND));
        initGrid();
        getBuilder().colCount(4);
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()) {
            formatMessageDirCts(e.getKey().getTranslation());
            initLine();
            IntBeanChgLong chgPl_ = getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<String,Long>(simulation.getGame().getFight().getUsedItemsWhileRound().getEntryByKey(e.getKey().getKey()),chgPl_));
            feedParentsCts();
            initLine();
            getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<String,Long>(simulation.getGame().getFight().getUsedItemsWhileRound(),e.getKey().getKey()));
            feedParentsCts();
        }
        feedParents();
        int len_ = simulation.getGame().getFight().getUsedItemsWhileRound().size();
        DictionaryComparator<String, String> map_ = DictionaryComparatorUtil.buildItemsStr(getDataBase(), getLanguage());
        StringMap<String> fill_ = new StringMap<String>();
        fill_.putAllMap(getDataBase().getTranslatedItems().getVal(getLanguage()));
        for (int i = 0; i < len_; i++) {
            fill_.removeKey(simulation.getGame().getFight().getUsedItemsWhileRound().getKey(i));
        }
        map_.putAllMap(fill_);
        IntBeanChgString key_ = getBuilder().getGenInput().newString(map_);
        IntBeanChgLong value_ = getBuilder().getGenInput().newLong();
        getBuilder().button("+").addEvt(new SimulationBeanAddEntry<String,Long>(simulation.getGame().getFight().getUsedItemsWhileRound(),key_,value_));
        getBuilder().nextPart();
        feedParents();
    }

    private void usedItemsFix(AbsMap<TranslatedKey, Long> _map, StringMap<Long> _info) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_ENBALED_MOVES_USES));
        initGrid();
        getBuilder().colCount(3);
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()) {
            formatMessageDirCts(e.getKey().getTranslation());
            initLine();
            IntBeanChgLong chgPl_ = getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<String,Long>(_info.getEntryByKey(e.getKey().getKey()),chgPl_));
            feedParentsCts();
        }
        feedParents();
        feedParents();
    }

    private void healAfter(Team _t) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_HEAL_AFTER));
        initGrid();
        DictionaryComparator<TranslatedKey, IntMap<StacksOfUses>> map_ = new DictionaryComparator<TranslatedKey, IntMap<StacksOfUses>>(new ComparingTranslatedKey());
        for (EntryCust<String, IntMap<StacksOfUses>> e:_t.getHealAfter().entryList()) {
            map_.put(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        getBuilder().colCount(4);
        for (EntryCust<TranslatedKey, IntMap<StacksOfUses>> e: map_.entryList()) {
            for (EntryCust<Integer,StacksOfUses> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey().getTranslation());
                formatMessageDirCts(Long.toString(f.getKey()));
                initLine();
                IntBeanChgStackOfUses chgPl_ = getBuilder().getGenInput().newStack();
                chgPl_.valueStack(f.getValue());
                getBuilder().nextPart();
                feedParentsCts();
                initLine();
                getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<Integer,StacksOfUses>(_t.getHealAfter().getVal(e.getKey().getKey()).getEntryByKey(f.getKey()),chgPl_));
                feedParentsCts();
            }
        }
        feedParents();
        feedParents();
    }

    private void ant(Team _t) {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_MOVE_ANT));
        initGrid();
        DictionaryComparator<TranslatedKey, IntMap<Anticipation>> map_ = new DictionaryComparator<TranslatedKey, IntMap<Anticipation>>(new ComparingTranslatedKey());
        for (EntryCust<String, IntMap<Anticipation>> e:_t.getMovesAnticipation().entryList()) {
            map_.put(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        getBuilder().colCount(4);
        CustList<TargetCoords> targetCoords_ = allValuesTarget();
        AbsMap<TargetCoords,String> valuesMap_ = new TargetCoordssString();
        int lenVal_ = targetCoords_.size();
        for (int i = 0; i < lenVal_; i++) {
            TargetCoords k_ = targetCoords_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        for (EntryCust<TranslatedKey, IntMap<Anticipation>> e: map_.entryList()) {
            for (EntryCust<Integer,Anticipation> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey().getTranslation());
                formatMessageDirCts(Long.toString(f.getKey()));
                initLine();
                IntBeanChgAnticipation chgPl_ = getBuilder().getGenInput().newAnt(valuesMap_);
                chgPl_.valueAnt(f.getValue());
                getBuilder().nextPart();
                feedParentsCts();
                initLine();
                getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<Integer,Anticipation>(_t.getMovesAnticipation().getVal(e.getKey().getKey()).getEntryByKey(f.getKey()),chgPl_));
                feedParentsCts();
            }
        }
        feedParents();
        feedParents();
    }

    private CustList<TargetCoords> allValuesTarget() {
        CustList<TargetCoords> mt_ = new CustList<TargetCoords>();
        mt_.addAllElts(targets(false));
        mt_.addAllElts(targets(true));
        return mt_;
    }
    private IntMap<String> ids(int _max) {
        IntMap<String> m_ = new IntMap<String>();
        m_.addEntry(Fighter.BACK,Long.toString(Fighter.BACK));
        for (int i = 0; i < _max; i++) {
            m_.addEntry(i, Long.toString(i));
        }
        return m_;
    }

    private void evosChoices() {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_CHOICES));
        initGrid();
        getBuilder().colCount(4);
        DictionaryComparator<String, String> pk_ = DictionaryComparatorUtil.buildPkStrElts(getDataBase(), getLanguage());
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getDataBase(), getLanguage());
        DictionaryComparator<String, String> ab_ = DictionaryComparatorUtil.buildAbStrElts(getDataBase(), getLanguage());
        for (EntryCust<Integer,ChoiceOfEvolutionAndMoves> e: simulation.getGame().getFight().getChoices().entryList()) {
            formatMessageDirCts(Long.toString(e.getKey()));
            initLine();
            IntBeanChgChoiceOfEvolutionAndMoves choice_ = getBuilder().getGenInput().newChoice(pk_, mv_, ab_);
            choice_.valueChoice(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<Integer,ChoiceOfEvolutionAndMoves>(e,choice_));
            feedParentsCts();
            initLine();
            getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<Integer,ChoiceOfEvolutionAndMoves>(simulation.getGame().getFight().getChoices(),e.getKey()));
            feedParentsCts();
        }
        feedParents();
        int len_ = simulation.getGame().getFight().getUserTeam().getMembers().size();
        AbsMap<Integer,String> map_ = new IntMap<String>();
        for (int i = 0; i < len_; i++) {
            int k_ = simulation.getGame().getFight().getUserTeam().getMembers().getKey(i);
            if (!simulation.getGame().getFight().getChoices().contains(k_)) {
                map_.addEntry(k_,Long.toString(k_));
            }
        }
        if (!map_.isEmpty()) {
            IntBeanChgInt key_ = getBuilder().getGenInput().newInt(map_);
            IntBeanChgChoiceOfEvolutionAndMoves value_ = getBuilder().getGenInput().newChoice(pk_, mv_, ab_);
            getBuilder().button("+").addEvt(new SimulationBeanAddEntry<Integer,ChoiceOfEvolutionAndMoves>(simulation.getGame().getFight().getChoices(),key_,value_));
            getBuilder().nextPart();
        }
        feedParents();
    }

    private void allyChoices() {
        initPage();
        setTitledBorder(messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICES));
        initGrid();
        getBuilder().colCount(4);
        CustList<MoveTarget> values_ = allValues();
        int lenVal_ = values_.size();
        AbsMap<MoveTarget,String> valuesMap_ = new MoveTargetsString();
        for (int i = 0; i < lenVal_; i++) {
            MoveTarget k_ = values_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        for (EntryCust<MoveTarget, MoveTarget> e: simulation.getGame().getFight().getAllyChoice().entryList()) {
            formatMessageDirCts(e.getKey().display());
            initLine();
            IntBeanChgMoveTarget choice_ = getBuilder().getGenInput().newMt(valuesMap_);
            choice_.valueMt(e.getValue());
            getBuilder().nextPart();
            feedParentsCts();
            initLine();
            getBuilder().button(CONFIRM).addEvt(new SimulationBeanUpdateEntryValue<MoveTarget,MoveTarget>(e,choice_));
            feedParentsCts();
            initLine();
            getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<MoveTarget,MoveTarget>(simulation.getGame().getFight().getAllyChoice(),e.getKey()));
            feedParentsCts();
        }
        feedParents();
        CustList<MoveTarget> keys_ = allKeys();
        int len_ = keys_.size();
        AbsMap<MoveTarget,String> map_ = new MoveTargetsString();
        for (int i = 0; i < len_; i++) {
            MoveTarget k_ = keys_.get(i);
            if (!simulation.getGame().getFight().getAllyChoice().contains(k_)) {
                map_.addEntry(k_,k_.display());
            }
        }
        if (!map_.isEmpty()) {
            IntBeanChgMoveTarget key_ = getBuilder().getGenInput().newMt(map_);
            IntBeanChgMoveTarget value_ = getBuilder().getGenInput().newMt(valuesMap_);
            getBuilder().button("+").addEvt(new SimulationBeanAddEntry<MoveTarget,MoveTarget>(simulation.getGame().getFight().getAllyChoice(),key_,value_));
            getBuilder().nextPart();
        }
        feedParents();
    }
    private CustList<MoveTarget> allKeys() {
        CustList<MoveTarget> mt_ = new CustList<MoveTarget>();
        noMoveCase(mt_);
        for (EntryCust<String, MoveData> m: getDataBase().getMoves().entryList()) {
            for (TargetCoords t: targets(true)) {
                mt_.add(new MoveTarget(m.getKey(),t));
            }
        }
        return mt_;
    }
    private CustList<MoveTarget> allValues() {
        CustList<MoveTarget> mt_ = new CustList<MoveTarget>();
        noMoveCase(mt_);
        for (EntryCust<String, MoveData> m: getDataBase().getMoves().entryList()) {
            for (TargetCoords t: targets(m.getValue().getTargetChoice().isWithChoice())) {
                mt_.add(new MoveTarget(m.getKey(),t));
            }
        }
        return mt_;
    }

    private void noMoveCase(CustList<MoveTarget> _mt) {
        for (TargetCoords t: targets(false)) {
            _mt.add(new MoveTarget(DataBase.EMPTY_STRING,t));
        }
    }

    private CustList<TargetCoords> targets(boolean _valid) {
        CustList<TargetCoords> t_ = new CustList<TargetCoords>();
        if (!_valid) {
            t_.add(TargetCoords.def());
            return t_;
        }
        int mult_ = simulation.getGame().getFight().getMult();
        t_.addAllElts(targetsTeam(Fight.CST_PLAYER,mult_));
        t_.addAllElts(targetsTeam(Fight.CST_FOE,mult_));
        return t_;
    }
    private static CustList<TargetCoords> targetsTeam(int _cst, int _mult) {
        CustList<TargetCoords> ls_ = new CustList<TargetCoords>();
        for (int i = 0; i < _mult; i++) {
            ls_.add(new TargetCoords(_cst,i));
        }
        return ls_;
    }
    public void validateTeamCoreForm(Team _t, IntBeanChgLong _nbKo, IntBeanChgLong _nbKoPrevious, IntBeanChgList<String> _successfulMovesRound) {
        _t.setNbKoRound(_nbKo.valueLong());
        _t.setNbKoPreviousRound(_nbKoPrevious.valueLong());
        _t.setSuccessfulMovesRound(new StringList(_successfulMovesRound.tryRet()));
    }
    public void validateFightCoreForm() {
        simulation.getGame().getFight().setNbFleeAttempt(nbFleeAttempt.valueLong());
        simulation.getGame().getFight().setNbRounds(nbRounds.valueLgInt());
        simulation.getGame().getFight().setWinningMoney(winningMoney.valueRate());
        simulation.getGame().getFight().setLostObjects(new StringList(lostObjects.tryRet()));
        simulation.getGame().getFight().setCaughtEvolutions(new StringList(caughtEvolutions.tryRet()));
        simulation.getGame().getFight().setCurrentUser(currentUserList.getKey(indexUserTeam.valueInt()));
        simulation.getGame().getFight().setState(fightState.getKey(indexFightState.valueInt()));
    }
    private void evoAfterFight() {
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT_PK_EVOS_AFTER_FIGHT);
        new BeanDisplayListGrid<PokemonPlayer>(new BeanDisplayPokemonPlayerSelect(this)).displayGrid(this,teamAfterFight,MessagesPkBean.SIMULATION,"",MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataSimulation.M_P_86_NAME_PK,MessagesDataSimulation.M_P_86_LEVEL_PK,MessagesDataSimulation.M_P_86_ABILITY_PK,MessagesDataSimulation.M_P_86_GENDER_PK,MessagesDataSimulation.M_P_86_ITEM_PK,MessagesDataSimulation.M_P_86_MOVES_PK,MessagesDataSimulation.M_P_86_HP_RATE,MessagesDataSimulation.M_P_86_EXP_PK,MessagesDataSimulation.M_P_86_EXP_PK_REM,MessagesDataSimulation.M_P_86_HAPPINESS_PK,MessagesDataSimulation.M_P_86_SELECTED);
        if (selectedIndexForMoves()) {
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVOLUTION);
            setEvolutionAfterFight(DifficultyBeanForm.select(getBuilder().getGenInput(), this, evolutionsAfterFight, evolutionAfterFight.tryRet()));
            feedParents();
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT)).addEvt(new SimulationBeanValidateEvolutionAfterFight(this));
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL_MOVES)).addEvt(new SimulationBeanCancelEvolutionsAfterFight(this));
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ABILITY_PK);
            setAbilityAfterFight(DifficultyBeanForm.select(getBuilder().getGenInput(), this, abilitiesAfterFight, abilityAfterFight.tryRet()));
            feedParents();
            new BeanDisplayListGrid<SelectLineMove>(new BeanDisplaySelectLineMove()).displayGrid(this,keptMovesAfterFight,MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SELECT)).addEvt(new SimulationBeanValidateMovesAbilityAfterFight(this));
        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PREVIOUS_BUTTON)).addEvt(new SimulationBeanChangeFightWhileEnd(this));
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NEXT_BUTTON)).addEvt(new SimulationBeanValidateMovesAfterFight(this));
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        if (!getForms().contains(SIMU_CST_SIMULATION_STATE)) {
            getForms().put(SIMU_CST_SIMULATION_STATE,SimulationSteps.DIFF);
        }
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
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
            if (selectedRound.valueInt() == 0) {
                StringList moves_ = team.get(selectedPk).getMoves();
                moveSetInit(moves_);
                return;
            }
            StringList moves_ = simulation.getKeptMoves().getVal(selectedPk).getVal(new KeyFightRound(IndexConstants.FIRST_INDEX, selectedRound.valueInt() -1));
            moveSetInit(moves_);
        }
    }

    private void moveSetInit(StringList _moves) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: _moves) {
            MoveData moveData_ = data_.getMoves().getVal(k);
            RadioLineMove line_ = new RadioLineMove();
            MovesBean.line(translationsTypes_,translationsCategories_,buildMv(getFacade(),k),moveData_,line_,getDataBase());
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
        currentAbility.setupValue(DataBase.EMPTY_STRING);
        if (isAvailableMoves()) {
            if (isAvailableAbilities()) {
                for (String a : getAvailableAbilities()) {
                    abilities.put(a, translationsAbilities_.getVal(a));
                }
                currentAbility.setupValue(abilities.firstKey());
            }
            //keptMoves
            keptMoves.clear();
            StringMap<String> translationsTypes_;
            translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
            StringMap<String> translationsCategories_;
            translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
            AvailableMovesInfos info_;
            info_ = simulation.getAvailableMoves().getVal(selectedPk);
            for (String k : info_.getMoves().getKeys()) {
                MoveData moveData_ = data_.getMoves().getVal(k);
                SelectLineMove line_ = MovesBean.buildLine(translationsTypes_,translationsCategories_,buildMv(getFacade(),k),moveData_,getDataBase());
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
                line_.getSelected().setSelected(info_.getMoves().getVal(k) == BoolVal.TRUE);
                keptMoves.add(line_);
            }
            keptMoves.sortElts(new ComparatorMoves());
        }
    }

    private void stateFront() {
        round = new IntTreeMap< String>();
        placesFight = new IntTreeMap< String>();
        int nbRounds_ = simulation.nbRounds();
        for (int i = IndexConstants.FIRST_INDEX; i < nbRounds_; i++) {
            round.put(i, Long.toString(i));
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
        boolean nothing_ = getForms().getValTeamCrud(SIMU_CST_ADDING_TRAINER_PK) == TeamCrud.NOTHING;
        if (nothing_) {
            return;
        }
        if (getForms().contains(SIMU_CST_POKEMON_ADDED)) {
            PokemonPlayerDto pk_ = getForms().getVal(SIMU_CST_POKEMON_ADDED);
            getForms().removeKey(SIMU_CST_POKEMON_ADDED);
            pk_.setIndex(team.size());
            team.add(pk_);
            simulation.addPokemonPlayer(pk_.getPokemon(), pk_.getMoves(), 0, Rate.zero(), data_);
        } else if(getForms().contains(SIMU_CST_POKEMON_INDEX_EDIT)) {
            int index_ = getForms().getValInt(SIMU_CST_POKEMON_INDEX_EDIT);
            getForms().removeKey(SIMU_CST_POKEMON_INDEX_EDIT);
            team.get(index_).setMoves(getForms().getValList(SIMU_CST_POKEMON_MOVES_EDIT));
            getForms().removeKey(SIMU_CST_POKEMON_MOVES_EDIT);
            team.get(index_).getPokemon().setItem(getForms().getValStr(SIMU_CST_ITEM_EDIT));
            getForms().removeKey(SIMU_CST_ITEM_EDIT);
            simulation.setPokemonPlayerObject(index_, team.get(index_).getPokemon().getItem());
            Rate exp_ = getForms().getValRate(SIMU_CST_POKEMON_EXPERIENCE);
            getForms().removeKey(SIMU_CST_POKEMON_EXPERIENCE);
            PokemonPlayer pkPlayer_ = simulation.getTeam().get(index_);
            pkPlayer_.setWonExpSinceLastLevel(exp_);
            String ball_ = getForms().getValStr(SIMU_CST_CATCHING_BALL);
            getForms().removeKey(SIMU_CST_CATCHING_BALL);
            pkPlayer_.setUsedBallCatching(ball_);
            long happy_ = getForms().getValLong(SIMU_CST_POKEMON_HAPPINESS);
            getForms().removeKey(SIMU_CST_POKEMON_HAPPINESS);
            Rate hp_ = getForms().getValRate(SIMU_CST_POKEMON_HP).absNb();
            getForms().removeKey(SIMU_CST_POKEMON_HP);
            boolean heal_ = getForms().getValBool(SIMU_CST_HEAL_EDIT_PK);
            getForms().removeKey(SIMU_CST_HEAL_EDIT_PK);
            for (Statistic s:Statistic.getStatisticsWithBase()) {
                long ev_ = getForms().getValLong(StringUtil.concat(SIMU_CST_POKEMON_EV_VAR, s.getStatName()));
                getForms().removeKey(StringUtil.concat(SIMU_CST_POKEMON_EV_VAR,s.getStatName()));
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
            getForms().removeKey(SIMU_CST_ITEMS_SET_EDIT);
            getForms().removeKey(SIMU_CST_POKEMON_NAME_EDIT);
            getForms().removeKey(SIMU_CST_POKEMON_LEVEL_EDIT);
        }
    }

    private void stateFoe() {
        DataBase data_ = getDataBase();
        if (freeTeams) {
            stateFoeFree();
        } else {
            if (!getForms().contains(SIMU_CST_NO_FIGHT)) {
                getForms().put(SIMU_CST_NO_FIGHT, 0L);
            }
            noFight = getForms().getValLong(SIMU_CST_NO_FIGHT);
            coords = getForms().getValCoords(SIMU_CST_COORDS_TR);
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
        boolean remove_ = getForms().getValTeamCrud(SIMU_CST_ADDING_TRAINER_PK) == TeamCrud.REMOVE;
        if (remove_) {
            return;
        }
        boolean nothing_ = getForms().getValTeamCrud(SIMU_CST_ADDING_TRAINER_PK) == TeamCrud.NOTHING;
        if (nothing_) {
            return;
        }
        boolean add_ = getForms().getValTeamCrud(SIMU_CST_ADDING_TRAINER_PK) == TeamCrud.ADD;
        if (add_) {
            boolean foe_ = getForms().getValBool(SIMU_CST_POKEMON_FOE);
            PokemonTrainerDto pk_;
            pk_ = new PokemonTrainerDto();
            pk_.getPkTrainer().getMoves().addAllElts(getForms().getValList(SIMU_CST_POKEMON_MOVES_EDIT));
            pk_.getPkTrainer().setAbility(getForms().getValStr(SIMU_CST_POKEMON_ABILITY_EDIT));
            pk_.getPkTrainer().setName(getForms().getValStr(SIMU_CST_POKEMON_NAME_EDIT));
            pk_.getPkTrainer().setGender(getForms().getValGen(SIMU_CST_POKEMON_GENDER_EDIT));
            pk_.getPkTrainer().setItem(getForms().getValStr(SIMU_CST_ITEM_EDIT));
            pk_.getPkTrainer().setLevel(getForms().getValLong(SIMU_CST_POKEMON_LEVEL_EDIT));
            if (foe_) {
                pk_.setIndex(foeTeams.get(indexTeam).size());
                foeTeams.get(indexTeam).add(pk_);
            } else {
                pk_.setIndex(allyTeams.get(indexTeam).size());
                allyTeams.get(indexTeam).add(pk_);
            }
        } else {
            boolean foe_ = getForms().getValBool(SIMU_CST_POKEMON_FOE);
            PokemonTrainerDto pk_;
            if (foe_) {
                pk_ = foeTeams.get(indexTeam).get(selectedFoePk);
            } else {
                pk_ = allyTeams.get(indexTeam).get(selectedAllyPk);
            }
            pk_.getPkTrainer().getMoves().clear();
            pk_.getPkTrainer().getMoves().addAllElts(getForms().getValList(SIMU_CST_POKEMON_MOVES_EDIT));
            pk_.getPkTrainer().setAbility(getForms().getValStr(SIMU_CST_POKEMON_ABILITY_EDIT));
            pk_.getPkTrainer().setName(getForms().getValStr(SIMU_CST_POKEMON_NAME_EDIT));
            pk_.getPkTrainer().setGender(getForms().getValGen(SIMU_CST_POKEMON_GENDER_EDIT));
            pk_.getPkTrainer().setItem(getForms().getValStr(SIMU_CST_ITEM_EDIT));
            pk_.getPkTrainer().setLevel(getForms().getValLong(SIMU_CST_POKEMON_LEVEL_EDIT));
        }
        getForms().put(SIMU_CST_ADDING_TRAINER_PK,TeamCrud.NOTHING);
    }

    private void stateDiff() {
        DataBase data_ = getDataBase();
        difficultyCommon.init(data_,getLanguage(),difficulty);
    }

    public boolean isDiffState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.DIFF;
    }
    public boolean isFoeState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FOE;
    }
    public boolean isTeamState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.TEAM;
    }
    public boolean isEvolutionsState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.EVOLUTIONS;
    }
    public boolean isFrontState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.FRONT;
    }
    public boolean isMovesState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES;
    }
    public boolean isMovesFightState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.MOVES_FIGHT;
    }
    public boolean isSimulationState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
        return simu_ == SimulationSteps.SIMULATION;
    }
    public boolean isEvolutionAfterFightState() {
        SimulationSteps simu_ = getForms().getValSimStep(SIMU_CST_SIMULATION_STATE);
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
//                return StringUtil.join(w_.getDualFights().getVal(_coords.getLevel().getPoint()).getNames(), SIMU_CST_SPACE);
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
            Coords coords_ = new Coords();
            coords_.setNumberPlace(_indexOne);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(_indexTwo);
            coords_.getLevel().setPoint(new Point(((LevelLeague)l_.getLevelsList().get(_indexTwo)).getTrainerCoords().value()));
            getForms().put(SIMU_CST_COORDS_TR, coords_);
            noFight = IndexConstants.FIRST_INDEX;
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
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
                    getForms().put(SIMU_CST_COORDS_TR_VIRTUAL,co_);
                    return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
                }
            }
        }
        Coords co_ = new Coords();
        co_.setNumberPlace(_indexOne);
        co_.getLevel().setLevelIndex(_indexTwo);
        getForms().put(SIMU_CST_COORDS_TR_VIRTUAL,co_);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML;
    }
    public void validateDiffChoice() {
        ok = true;
        DataBase data_ = getDataBase();
        difficultyCommon.apply(data_,difficulty);
        simulation = new FightSimulation(difficulty, data_);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FOE);
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.DIFF);
        stepNumber--;
    }
    public void validateFoeChoice() {
        ok = true;
        if (coords == null) {
            ok = false;
            return;
        }
        DataBase data_ = getDataBase();
        simulation.initializeFight(coords, (int) NumberUtil.max(0,NumberUtil.min(noFight,Integer.MAX_VALUE)), data_);
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
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
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
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.EDIT) {
            getForms().put(SIMU_CST_POKEMON_FOE, true);
            getForms().putItems(SIMU_CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
            getForms().put(SIMU_CST_POKEMON_INDEX_EDIT, selectedFoePk);
            getForms().put(SIMU_CST_POKEMON_NAME_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getName());
            getForms().put(SIMU_CST_POKEMON_LEVEL_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getLevel());
            getForms().put(SIMU_CST_ITEM_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getItem());
            getForms().put(SIMU_CST_POKEMON_GENDER_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getGender());
            getForms().put(SIMU_CST_POKEMON_MOVES_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getMoves());
            getForms().put(SIMU_CST_POKEMON_ABILITY_EDIT, foeTeams.get(indexTeam).get(selectedFoePk).getPkTrainer().getAbility());
            getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedFoeAction) == TeamCrud.REMOVE) {
            int index_ = selectedFoePk;
            foeTeams.get(indexTeam).remove(index_);
            int size_ = foeTeams.get(indexTeam).size();
            for (int i = index_; i < size_;i++) {
                foeTeams.get(indexTeam).get(i).setIndex(i);
            }
            getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
//    public boolean errorSelectedFoePk() {
//        return selectedFoePk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedFoeAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedFoeAction) != TeamCrud.REMOVE);
//    }
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
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.EDIT) {
            getForms().put(SIMU_CST_POKEMON_FOE, false);
            getForms().putItems(SIMU_CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
            getForms().put(SIMU_CST_POKEMON_INDEX_EDIT, selectedAllyPk);
            getForms().put(SIMU_CST_POKEMON_NAME_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getName());
            getForms().put(SIMU_CST_POKEMON_LEVEL_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getLevel());
            getForms().put(SIMU_CST_ITEM_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getItem());
            getForms().put(SIMU_CST_POKEMON_GENDER_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getGender());
            getForms().put(SIMU_CST_POKEMON_MOVES_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getMoves());
            getForms().put(SIMU_CST_POKEMON_ABILITY_EDIT, allyTeams.get(indexTeam).get(selectedAllyPk).getPkTrainer().getAbility());
            getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAllyAction) == TeamCrud.REMOVE) {
            int index_ = selectedAllyPk;
            allyTeams.get(indexTeam).remove(index_);
            int size_ = allyTeams.get(indexTeam).size();
            for (int i = index_; i < size_;i++) {
                allyTeams.get(indexTeam).get(i).setIndex(i);
            }
            getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.REMOVE);
        }
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
//    public boolean errorSelectedAllyPk() {
//        return selectedAllyPk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedAllyAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedAllyAction) != TeamCrud.REMOVE);
//    }
    public String addPkTrainer() {
        DataBase data_ = getDataBase();
        Pokemon pk_ = data_.getMap().getFirstPokemon();
        StringList moves_ = data_.getPokemon(pk_.getName()).getMovesBeforeLevel(pk_.getLevel());
        getForms().put(SIMU_CST_POKEMON_NAME_EDIT, pk_.getName());
        getForms().put(SIMU_CST_POKEMON_LEVEL_EDIT, pk_.getLevel());
        getForms().put(SIMU_CST_ITEM_EDIT, pk_.getItem());
        getForms().put(SIMU_CST_POKEMON_GENDER_EDIT, Gender.NO_GENDER);
        getForms().put(SIMU_CST_POKEMON_MOVES_EDIT, moves_);
        getForms().put(SIMU_CST_POKEMON_ABILITY_EDIT, pk_.getAbility());
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.ADD);
        getForms().putItems(SIMU_CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
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
        getForms().removeKey(SIMU_CST_POKEMON_INDEX_EDIT);
        getForms().removeKey(SIMU_CST_POKEMON_ADDED);
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
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
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
        }
        if (TeamCrud.getTeamCrudByName(selectedAction) == TeamCrud.EDIT) {
            getForms().putItems(SIMU_CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
            getForms().put(SIMU_CST_POKEMON_INDEX_EDIT, selectedPk);
            getForms().put(SIMU_CST_POKEMON_NAME_EDIT, team.get(selectedPk).getPokemon().getName());
            getForms().put(SIMU_CST_POKEMON_LEVEL_EDIT, team.get(selectedPk).getPokemon().getLevel());
            getForms().put(SIMU_CST_ITEM_EDIT, team.get(selectedPk).getPokemon().getItem());
            getForms().put(SIMU_CST_POKEMON_MOVES_EDIT, team.get(selectedPk).getMoves());
            getForms().put(SIMU_CST_POKEMON_EXPERIENCE, simulation.getTeam().get(selectedPk).getWonExpSinceLastLevel());
            getForms().put(SIMU_CST_POKEMON_HAPPINESS, simulation.getTeam().get(selectedPk).getHappiness());
            getForms().put(SIMU_CST_HEAL_EDIT_PK, false);
            getForms().put(SIMU_CST_POKEMON_HP, simulation.getTeam().get(selectedPk).getRemainingHp());
            getForms().put(SIMU_CST_CATCHING_BALL, simulation.getTeam().get(selectedPk).getUsedBallCatching());
            for (Statistic s: Statistic.getStatisticsWithBase()) {
                getForms().put(StringUtil.concat(SIMU_CST_POKEMON_EV_VAR,s.getStatName()), simulation.getTeam().get(selectedPk).getEv().getVal(s));
            }
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
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
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
//    public boolean errorSelectedPk() {
//        return selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT || (TeamCrud.getTeamCrudByName(selectedAction) != TeamCrud.EDIT && TeamCrud.getTeamCrudByName(selectedAction) != TeamCrud.REMOVE);
//    }
    public String add() {
        getForms().putPokedex(SIMU_CST_POKEMON_SET_SIMU, DictionaryComparatorUtil.buildPkData());
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML;
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
        stepNumber++;
    }
    public void cancelTeam() {
        ok = true;
        team.clear();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FOE);
        stepNumber--;
    }
    public void cancelEvolutions() {
        ok = true;
        simulation.cancelEvolutions();
        getForms().removeKey(SIMU_CST_POKEMON_INDEX_EDIT);
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber--;
    }
    public void validateEvolutions() {
        ok = true;
        simulation.initializeFrontFighters();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().removeKey(SIMU_CST_POKEMON_INDEX_EDIT);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FRONT);
        stepNumber++;
    }
    public boolean selectedIndex() {
        return getForms().contains(SIMU_CST_POKEMON_INDEX_EDIT);
    }
    public void displayEvolutions() {
        DataBase data_ = getDataBase();
        if (selectedPk == IndexConstants.INDEX_NOT_FOUND_ELT) {
            availableEvos = DictionaryComparatorUtil.buildPkStr(data_,getLanguage());
            return;
        }
        getForms().put(SIMU_CST_POKEMON_INDEX_EDIT, selectedPk);
        evoList(data_,selectedPk);
    }
    public void validateEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = getDataBase();
        int index_ = getForms().getValInt(SIMU_CST_POKEMON_INDEX_EDIT);
        levelEvo.valueLong(NumberUtil.max(levelEvo.valueLong(), availableEvosLevel.getVal(chosenEvo.tryRet())));
        simulation.setNextEvolutions(index_, chosenEvo.tryRet(), levelEvo.valueLong(), data_);
        evoList(data_, index_);
    }
    public void cancelEvo() {
//        if (!getForms().contains(POKEMON_INDEX_EDIT)) {
//            return;
//        }
        DataBase data_ = getDataBase();
        int index_ = getForms().getValInt(SIMU_CST_POKEMON_INDEX_EDIT);
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
        simulation.getFrontFighters().first().get(selectedRound.valueInt()).put(selectedPk, placeFight.valueInt());
    }
    public void cancelFrontFighters() {
        displayIfError = false;
        ok = true;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (!enableEvolutions) {
            simulation.cancelEvolutions();
            getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
            getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
            stepNumber--;
            stepNumber--;
            return;
        }
        selectedAction = TeamCrud.NOTHING.getTeamCrudString();
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.EVOLUTIONS);
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
            getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
            stepNumber++;
            stepNumber++;
            return;
        }
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.MOVES);
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
            if (currentAbility.tryRet().isEmpty()) {
                return;
            }
            int r_ = simulation.getAvailableMoves().getVal(selectedPk).getKey().getRound();
            simulation.setAbilityWhileFight(selectedPk, IndexConstants.FIRST_INDEX, r_, currentAbility.tryRet());
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FRONT);
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber++;
    }
    public void validateMovesChoice() {
        displayIfError = false;
        if (selectedMove == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        String move_ = movesSet.get(selectedMove).getName();
        DataBase data_ = getDataBase();
        simulation.chooseMoveFirstFight(selectedRound.valueInt(), selectedPk, move_, allyChoice.isSelected(), target.valueInt(), data_);
    }
    public void cancelMovesEvos() {
        displayIfError = false;
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        selectedMove = IndexConstants.INDEX_NOT_FOUND_ELT;
        ok = true;
        if (!enableEvolutions) {
            getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.FRONT);
            stepNumber--;
            stepNumber--;
            return;
        }
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.MOVES);
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
        simulation.validateTeam();
        teamAfterFight.clear();
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.MOVES_FIGHT);
        stepNumber--;
    }
    public void resetFight() {
        ok = true;
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
        stepNumber--;
    }
    public void changeFightWhileEnd() {
        ok = true;
        keptMovesAfterFight.clear();
        keptMovesAbilitiesDto.clear();
        selectedPk = IndexConstants.INDEX_NOT_FOUND_ELT;
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.SIMULATION);
        stepNumber--;
    }

    public void introFight() {
        simulation.setSeed(seed.tryRet());
        simulation.simulateFightIntro(getDataBase());
        currentUserList = curUserList();
        fightState = fightStateList();
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.SIMULATION_STEP);
        stepNumber++;
    }

    private AbsMap<TeamPosition,String> curUserList() {
        AbsMap<TeamPosition,String> ls_ = new TeamPositionsString();
        ls_.addEntry(new TeamPosition(0, Fighter.BACK), "");
        for (int k: simulation.getGame().getFight().getUserTeam().getMembers().getKeys()) {
            ls_.addEntry(Fight.toUserFighter(k),Fight.CST_PLAYER+","+k);
        }
        for (int k: simulation.getGame().getFight().getFoeTeam().getMembers().getKeys()) {
            ls_.addEntry(Fight.toFoeFighter(k),Fight.CST_FOE+","+k);
        }
        return ls_;
    }

    private AbsMap<FightState,String> fightStateList() {
        AbsMap<FightState,String> ls_ = new IdMap<FightState, String>();
        ls_.addEntry(FightState.SWITCH_PROPOSE, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_SWITCH_PROPOSE));
        ls_.addEntry(FightState.SWITCH_WHILE_KO_USER, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_SWITCH_WHILE_KO_USER));
        ls_.addEntry(FightState.ATTAQUES, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_ATTAQUES));
        ls_.addEntry(FightState.APPRENDRE_EVOLUER, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_APPRENDRE_EVOLUER));
        ls_.addEntry(FightState.SWITCH_APRES_ATTAQUE, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_SWITCH_APRES_ATTAQUE));
        ls_.addEntry(FightState.RIEN, messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIGHT_STATE_RIEN));
        return ls_;
    }
    public void stepFight() {
        simulation.simulateFightStep(getDataBase());
    }
    public void simulateFight() {
        ok = true;
        DataBase data_ = getDataBase();
        simulation.validateTeam();
        simulation.simulateFight(data_);
        if (!simulation.isOk()) {
            ok = false;
            return;
        }
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.SIMULATION);
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
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.EVO_AFTER_FIGHT);
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
        evolutionAfterFight.setupValue(k_.getEvolutions().last());
        abilityAfterFight.setupValue(k_.getAbility());
        evolutionsAfterFight = DictionaryComparatorUtil.buildPkStr(data_,getLanguage());
        afterFighter(k_);
    }

    public void validateEvolutionAfterFight() {
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        k_.getEvolutions().add(evolutionAfterFight.tryRet());
        afterFighter(k_);
        abilityAfterFight.setupValue(abilitiesAfterFight.firstKey());
    }

    public void cancelEvolutionsAfterFight() {
        KeptMovesAfterFight k_ = keptMovesAbilitiesDto.get(selectedPk);
        String base_ = k_.getEvolutions().first();
        k_.getEvolutions().clear();
        k_.getEvolutions().add(base_);
        evolutionAfterFight.setupValue(base_);
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        k_.getMoves().clear();
        k_.getMoves().addAllElts(pk_.getMoves().getKeys());
        k_.setAbility(pk_.getAbility());
        afterFighter(k_);
        abilityAfterFight.setupValue(k_.getAbility());
    }

    private void afterFighter(KeptMovesAfterFight _k) {
        DataBase data_ = getDataBase();
        PokemonPlayer pk_ = teamAfterFight.get(selectedPk);
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringList evolutions_ = pk_.directEvolutionsByStone(evolutionAfterFight.tryRet(), data_);
        for (String e: evolutions_) {
            evolutionsAfterFight.put(e, translationsPokemon_.getVal(e));
        }
        StringMap<BoolVal> selectedMoves_ = PokemonPlayer.getMovesForEvolution(pk_.getLevel(), _k.getMoves(), evolutionAfterFight.tryRet(), data_);
        keptMovesAfterFight.clear();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String m: selectedMoves_.getKeys()) {
            MoveData moveData_ = data_.getMoves().getVal(m);
            SelectLineMove line_ = MovesBean.buildLine(translationsTypes_,translationsCategories_,buildMv(getFacade(),m),moveData_,getDataBase());
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
            line_.getSelected().setSelected(selectedMoves_.getVal(m) == BoolVal.TRUE);
            keptMovesAfterFight.add(line_);
        }
        keptMovesAfterFight.sortElts(new ComparatorMoves());
        //StringList moves_ = data_.getPokemon(lastPk_).getMovesBeforeLevel(pk_.getLevel());
        StringList abilities_ = data_.getPokemon(evolutionAfterFight.tryRet()).getAbilities();
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
        k_.setAbility(abilityAfterFight.tryRet());
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
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().put(SIMU_CST_SIMULATION_STATE, SimulationSteps.TEAM);
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
        getForms().removeKey(SIMU_CST_SIMULATION_STATE);
        getForms().removeKey(SIMU_CST_COORDS_TR_VIRTUAL);
        getForms().removeKey(SIMU_CST_COORDS_TR);
        stepNumber = 0;
        ok = true;
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
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
        if (freeTeams) {
            multiplicity.valueLong(1);
            environment.setupValue(EnvironmentType.ROAD.getEnvName());
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
        getForms().put(SIMU_CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
    }

    public void setMultiplicity(int _multiplicity) {
        multiplicities.set(indexTeam,_multiplicity);
    }

    public IntBeanChgLong getMultiplicity() {
        return multiplicity;
    }

    public int multiplicity() {
        return multiplicities.get(indexTeam);
    }

    public DictionaryComparator<String,String> getEnvironments() {
        return environments;
    }

    public IntBeanChgString getEnvironment() {
        return environment;
    }

    public String environment() {
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

//    public int getSelectedFoePk() {
//        return selectedFoePk;
//    }

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
//
//    public int getSelectedAllyPk() {
//        return selectedAllyPk;
//    }

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

    public IntBeanChgString getChosenEvo() {
        return chosenEvo;
    }

    public void setChosenEvo(IntBeanChgString _chosenEvo) {
        chosenEvo = _chosenEvo;
    }

    public void setLevelEvo(IntBeanChgLong _levelEvo) {
        levelEvo = _levelEvo;
    }

    public IntBeanChgLong getLevelEvo() {
        return levelEvo;
    }

    public IntTreeMap<String> getRound() {
        return round;
    }

    public IntBeanChgInt getSelectedRound() {
        return selectedRound;
    }

    public void setSelectedRound(IntBeanChgInt _selectedRound) {
        selectedRound = _selectedRound;
    }

    public IntTreeMap<String> getPlacesFight() {
        return placesFight;
    }

    public IntBeanChgInt getPlaceFight() {
        return placeFight;
    }

    public void setPlaceFight(IntBeanChgInt _placeFight) {
        placeFight = _placeFight;
    }

    public boolean getDisplayIfError() {
        return displayIfError;
    }

    public DictionaryComparator<String,String> getAbilities() {
        return abilities;
    }

    public IntBeanChgString getCurrentAbility() {
        return currentAbility;
    }

    public void setCurrentAbility(IntBeanChgString _currentAbility) {
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

    public void setAllyChoice(IntBeanChgBool _allyChoice) {
        allyChoice = _allyChoice;
    }

    public IntBeanChgBool getAllyChoice() {
        return allyChoice;
    }

    public IntTreeMap<String> getTargetFight() {
        return targetFight;
    }

    public IntBeanChgInt getTarget() {
        return target;
    }

    public void setTarget(IntBeanChgInt _target) {
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

    public IntBeanChgString getEvolutionAfterFight() {
        return evolutionAfterFight;
    }

    public void setEvolutionAfterFight(IntBeanChgString _evolutionAfterFight) {
        evolutionAfterFight = _evolutionAfterFight;
    }

    public DictionaryComparator<String,String> getAbilitiesAfterFight() {
        return abilitiesAfterFight;
    }

    public IntBeanChgString getAbilityAfterFight() {
        return abilityAfterFight;
    }

    public void setAbilityAfterFight(IntBeanChgString _abilityAfterFight) {
        abilityAfterFight = _abilityAfterFight;
    }

    public CustList<SelectLineMove> getKeptMovesAfterFight() {
        return keptMovesAfterFight;
    }

    public IntBeanChgString getSeed() {
        return seed;
    }

    public FightSimulation getSimulation() {
        return simulation;
    }
}