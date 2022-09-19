package aiki.facade;

import aiki.comments.Comment;
import aiki.comparators.ComparatorTrStrings;
import aiki.comparators.TrMovesComparator;
import aiki.db.DataBase;
import aiki.db.ExchangedData;
import aiki.db.ImageHeroKey;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.facade.enums.StorageActions;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Fossil;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.GameProgression;
import aiki.game.HostPokemonDuo;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.BallNumberRate;
import aiki.game.fight.EvolutionChoiceMap;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.enums.ActionType;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.characters.Seller;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.Place;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTileInts;
import aiki.map.util.TileMiniMap;
import aiki.util.*;
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class FacadeGame {

    private DataBase data;

    private boolean loadedData;

    private Game game;

    private final Comment comment = new Comment();

    private final PaginationEgg paginationEgg = new PaginationEgg();

    private final PaginationPokemonPlayer firstPaginationPk = new PaginationPokemonPlayer();

    private final PaginationHealingItem paginationHealingItem = new PaginationHealingItem();

    private final PaginationItem paginationItem = new PaginationItem();

    // change state if going to buy technical move and if exiting the
    // functionnality
    private final PaginationMove paginationMove = new PaginationMove();

    private final StringMap<LgInt> chosenItemsForBuyOrSell = new StringMap<LgInt>();

    private final Shorts chosenTmForBuy = new Shorts();

    private short firstSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;

    private short secondSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean givingObject;

    private boolean selectedBoxPokemon;

    private boolean selectedOtherPokemon;

    private boolean selectedTeamPokemon;

    private boolean changeToFightScene;

    private boolean enabledMovingHero;

    private PokemonPlayer hostedPokemon;

    private ExchangedData exchangeData;

    private MiniMapCoords miniMapCoords = new MiniMapCoords(
            IndexConstants.INDEX_NOT_FOUND_ELT, IndexConstants.INDEX_NOT_FOUND_ELT);

    private String language;
    private StringList languages = new StringList();
    private StringMap<String> displayLanguages = new StringMap<String>();

    private String zipName = DataBase.EMPTY_STRING;

    // New game option
    public void newGame(String _pseudo, Sex _sexeHeros) {
        game = new Game(data);
        game.initUserInteract(_pseudo, _sexeHeros, game.getDifficulty(), data);
        // if (game.getVisitedPlaces().contains(game.getPlayerCoords())) {
        // game.getVisitedPlaces().put(game.getPlayerCoords(), true);
        // }
        enabledMovingHero = true;
        changeCamera();
    }

    public String getFrontChosenHeros() {
        ImageHeroKey i_;
        i_ = new ImageHeroKey(EnvironmentType.ROAD, game.getPlayer().getSex());
        return BaseSixtyFourUtil.getStringByImage(data.getFrontHeros().getVal(
                i_));
    }

    public String getFrontChosenHerosOppositeSex() {
        ImageHeroKey i_;
        i_ = new ImageHeroKey(EnvironmentType.ROAD, game.getPlayer().getSex()
                .getOppositeSex());
        return BaseSixtyFourUtil.getStringByImage(data.getFrontHeros().getVal(
                i_));
    }

    // public void initializeDefaultHtmlFiles() {
    // data.initializeDefaultHtmlFiles();
    // }
    //
    // public void initializeHtmlFiles() {
    // data.initializeHtmlFiles();
    // }

    public void clearGame() {
        game = null;
    }

    public boolean isLoadedData() {
        return loadedData;
    }
    public String getLanguage() {
        return language;
    }

    // Language option
    public void setLanguage(String _language) {
        setSimplyLanguage(_language);
        initializePaginatorTranslations();
    }
    public void setSimplyLanguage(String _language) {
        language = _language;
    }

    public void initializePaginatorTranslations() {
        paginationEgg.setTranslation(data, language);
        firstPaginationPk.setTranslation(data, language);
        paginationHealingItem.setTranslation(data, language);
        paginationItem.setTranslation(data, language);
        paginationMove.setTranslation(data, language);
    }

    // Load game option
    public void initIv() {
        game.initIv(data);
    }

    public boolean checkAndSetGame(Game _game) {
        if (!_game.checkAndInitialize(data)) {
            return false;
        }
        load(_game);
        return true;
    }

    public void load(Game _game) {
        game = _game;
        setupMovingHeros();
    }


    // Moving with arrows
    public void move(Direction _direction) {
        if (!enabledMovingHero) {
            return;
        }
        comment.clearMessages();
        game.moving(_direction, data);
        comment.addComment(game.getCommentGame());
        setupMovingHeros();
    }

    void setupMovingHeros() {
        changeToFightScene = game.getFight().getFightType().isExisting();
        enabledMovingHero = !changeToFightScene;
    }
    public void directInteraction() {
        game.setInterfaceType(InterfaceType.RIEN);
        Coords coords_ = closestTile();
        game.directInteraction(coords_, data.getMap());
    }

    public String getCurrentPlace() {
        short noPlace_ = game.getPlayerCoords().getNumberPlace();
        Place pl_ = data.getMap().getPlace(noPlace_);
        return pl_.getName();
    }

    public InterfaceType getInterfaceType() {
        return game.getInterfaceType();
    }

    public boolean isEnabledMovingHero() {
        return enabledMovingHero;
    }

    public void setChangeToFightScene(boolean _changeToFightScene) {
        changeToFightScene = _changeToFightScene;
    }

    public boolean isChangeToFightScene() {
        return changeToFightScene;
    }

    public void changeCamera() {
        data.getMap().getBackgroundImages().clear();
        data.getMap().getForegroundImages().clear();
        data.getMap().calculateIntersectWithScreen(game.getPlayerCoords());
        data.getMap().calculateBackgroundImagesFromTiles(data);
        game.calculateImagesFromTiles(data, 0, 0);
    }

    /**
     * @param _direction
     */
    public void changeCamera(Direction _direction) {
        data.getMap().getBackgroundImages().clear();
        data.getMap().getForegroundImages().clear();
        data.getMap().calculateIntersectWithScreenDirection(
                game.getPlayerCoords());

        // begin
        // data.getMap().moveCamera(_direction);
        data.getMap().calculateBackgroundImagesFromTiles(data);
        game.calculateImagesFromTiles(data, _direction.getx(),
                _direction.gety());
        // end

        // if (game.isPlaceChanged()) {
        // //data.getMap().calculateIntersectWithScreen(game.getPlayerCoords());
        // data.getMap().calculateBackgroundImagesFromTiles(data.getImages());
        // game.calculateImagesFromTiles(data);
        // } else {
        // //data.getMap().moveCamera(_direction);
        // data.getMap().calculateBackgroundImagesFromTiles(data.getImages());
        // game.calculateImagesFromTiles(data);
        // }
    }

    public ScreenCoordssInt getBackgroundImages() {
        return data.getMap().getBackgroundImages();
    }

    public ScreenCoordssCustListInt getForegroundImages() {
        return data.getMap().getForegroundImages();
    }

    // By clicking a button whose text depends on kind of interaction
    public void interact() {
        interact(true);
    }
    public void interactNoFish() {
        interact(false);
    }
    public void interact(boolean _allowFish) {
        changeToFightScene = false;
        comment.clearMessages();
        if (game.getInterfaceType() == InterfaceType.ACHATS_CT) {
            enabledMovingHero = false;
        } else if (game.getInterfaceType() == InterfaceType.PK_LEG) {
            game.initLegendaryPokemonFight(data);
            comment.addComment(game.getCommentGame());
            setupMovingHeros();
        } else if (_allowFish && game.getInterfaceType() == InterfaceType.PECHE) {
            game.initFishing(data);
            comment.addComment(game.getCommentGame());
            setupMovingHeros();
        } else if (game.getInterfaceType() == InterfaceType.DRESSEUR) {
            game.initTrainerFight(data);
            comment.addComment(game.getCommentGame());
            setupMovingHeros();
        } else if (game.getInterfaceType() == InterfaceType.OBJ_RAMAS || game.getInterfaceType() == InterfaceType.DON_OBJET) {
            takeObject();
        } else if (game.getInterfaceType() == InterfaceType.SOIN_PK) {
            healTeamWithoutUsingObject();
        } else if (game.getInterfaceType() == InterfaceType.ACHATS || game.getInterfaceType() == InterfaceType.PENSION || game.getInterfaceType() == InterfaceType.ECH_BOITE || game.getInterfaceType() == InterfaceType.MOVE_TUTORS) {
            enabledMovingHero = false;
        } else if (game.getInterfaceType() == InterfaceType.FOSSILE) {
            comment.clearMessages();
            fossilInteract();
            enabledMovingHero = false;
        } else if (game.getInterfaceType() == InterfaceType.GYM_LEADER) {
            comment.clearMessages();
            game.addMessageGymLeader(data);
        } else {
            comment.clearMessages();
            game.clearMessages();
        }
    }

    private void fossilInteract() {
        for (String i : data.getItems().getKeys()) {
            Item item_ = data.getItem(i);
            if (!(item_ instanceof Fossil)) {
                continue;
            }
            LgInt nb_ = game.getPlayer().getInventory().getNumber(i);
            LgInt incr_ = LgInt.zero();
            while (LgInt.strLower(incr_, nb_)) {
                game.doRevivingFossil(i, data);
                comment.addComment(game.getPlayer().getCommentGame());
                incr_.increment();
            }
        }
    }

    public int[][] getTrainerImage() {
        return game.getTrainerImage(data);
    }

    public void initFishing() {
        comment.clearMessages();
        game.initFishing(data);
        changeToFightScene = true;
        enabledMovingHero = false;
        comment.addComment(game.getCommentGame());
    }

    public void openMenu() {
        enabledMovingHero = false;
    }

    public void exitInteract() {
        enabledMovingHero = true;
        chosenItemsForBuyOrSell.clear();
        chosenTmForBuy.clear();
        cancelUseObject();
        clearFoundResultsStoragePokemon();
    }

    public void takeObject() {
        game.takeObject(data);
    }

    public void healTeamWithoutUsingObject() {
        game.healTeamWithoutUsingObject(data);
    }

    // %%%%begin%%%% functions for storage
    public void gearStorage(StorageActions _action) {
        if (_action == StorageActions.TAKE_ITEM_BOX) {
            takeObjectFromBox();
        } else if (_action == StorageActions.STORE) {
            store();
        } else if (_action == StorageActions.WIDRAW_PK) {
            withdrawPk();
        } else if (_action == StorageActions.WIDRAW_EGG) {
            withdrawEgg();
        } else if (_action == StorageActions.SWITCH_TEAM_BOX) {
            switchBoxTeam();
        } else {
            release();
        }
    }

    public void switchBoxTeam() {
        int currentIndex_ = firstPaginationPk.currentIndex();
        if (currentIndex_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
            game.getPlayer().switchPokemon(currentIndex_, data);
            firstPaginationPk.clear();
            // firstPaginationPk.search(game.getPlayer().getBox());
            // secondPaginationPk.search(game.getPlayer().getBox());
            return;
        }
        switchEggBoxTeam();
        paginationEgg.clear();
    }

    void switchEggBoxTeam() {
        int currentIndex_ = paginationEgg.currentIndex();
        game.getPlayer().switchPokemon(currentIndex_, data);
        // paginationEgg.search(game.getPlayer().getBox());
        // firstPaginationPk.search(game.getPlayer().getBox());
        // secondPaginationPk.search(game.getPlayer().getBox());
    }

    public boolean isSwitchable() {
        int currentIndexPk_ = firstPaginationPk.currentIndex();
        if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            currentIndexPk_ = paginationEgg.currentIndex();
            if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                return false;
            }
            int selected_ = game.getPlayer().getChosenTeamPokemon();
            if (selected_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                return false;
            }
            UsablePokemon us_ = game.getPlayer().getTeam().get(selected_);
            if (us_ instanceof Egg) {
                return true;
            }
            int nbPk_ = game.getPlayer().getPokemonPlayerList().size();
            return nbPk_ > IndexConstants.ONE_ELEMENT;
        }
        if (paginationEgg.currentIndex() != IndexConstants.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        int selected_ = game.getPlayer().getChosenTeamPokemon();
        return selected_ != IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public boolean isSelectedPkTeamStorage() {
        int nbPk_ = game.getPlayer().getPokemonPlayerList().size();
        if (nbPk_ <= IndexConstants.ONE_ELEMENT) {
            return false;
        }
        return game.getPlayer().getChosenTeamPokemon() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public void store() {
        if (!game.getPlayer().enabledStorePokemonBox()) {
            return;
        }
        game.getPlayer().storeIntoBox(data);
        // paginationEgg.search(game.getPlayer().getBox());
        // firstPaginationPk.search(game.getPlayer().getBox());
        // secondPaginationPk.search(game.getPlayer().getBox());
    }

    public void takeObjectFromBox() {
        int currentIndexPk_ = firstPaginationPk.currentIndex();
        if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        comment.clearMessages();
        game.getPlayer().takeObjectFromBox(currentIndexPk_, data);
        comment.addComment(game.getPlayer().getCommentGame());
        // firstPaginationPk.search(game.getPlayer().getBox());
        // secondPaginationPk.search(game.getPlayer().getBox());
    }

    public void withdrawEgg() {
        int currentIndexEgg_ = paginationEgg.currentIndex();
        if (currentIndexEgg_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        // int teamSize_ = game.getPlayer().getTeam().size();
        game.getPlayer().takePokemonFromBox(currentIndexEgg_, data);
        paginationEgg.clear();
        // if (teamSize_ >= game.getPlayer().getTeam().size()) {
        // paginationEgg.clear();
        // // paginationEgg.search(game.getPlayer().getBox());
        // }
    }

    public void withdrawPk() {
        int currentIndexPk_ = firstPaginationPk.currentIndex();
        if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        // int teamSize_ = game.getPlayer().getTeam().size();
        game.getPlayer().takePokemonFromBox(currentIndexPk_, data);
        firstPaginationPk.clear();
        // if (teamSize_ < game.getPlayer().getTeam().size()) {
        // firstPaginationPk.search(game.getPlayer().getBox());
        // secondPaginationPk.search(game.getPlayer().getBox());
        // }
    }



    public void release() {
        int currentIndexPk_ = firstPaginationPk.currentIndex();
        if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            currentIndexPk_ = paginationEgg.currentIndex();
            game.getPlayer().releasePokemon(currentIndexPk_, data);
            paginationEgg.clear();
            // release egg
            // paginationEgg.search(game.getPlayer().getBox());
        } else {
            game.getPlayer().releasePokemon(currentIndexPk_, data);
            // release pokemon
            firstPaginationPk.clear();
        }
    }

    public boolean isReleasable() {
        int currentIndexPk_ = firstPaginationPk.currentIndex();
        if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            currentIndexPk_ = paginationEgg.currentIndex();
            if (currentIndexPk_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                return false;
            }
            return game.getPlayer().isReleasable(currentIndexPk_, data);
            // release egg
            // paginationEgg.search(game.getPlayer().getBox());
        }
        if (paginationEgg.currentIndex() != IndexConstants.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return game.getPlayer().isReleasable(currentIndexPk_, data);
    }
    public void clearFoundResultsStoragePokemon() {
        paginationEgg.clear();
        firstPaginationPk.clear();
        paginationHealingItem.clear();
        paginationItem.clear();
        paginationMove.clear();
    }

    public void searchEgg() {
        paginationEgg.search(game.getPlayer().getBox());
    }

    public void searchPokemonFirstBox() {
        firstPaginationPk.search(game.getPlayer().getBox());
    }

    public void newSearchEgg() {
        paginationEgg.newSearch();
    }

    public void newSearchPokemonFirstBox() {
        firstPaginationPk.newSearch();
    }

    public void checkLineEggs(int _numberLine) {
        paginationEgg.checkLine(_numberLine);
    }

    public void setLineEggs(int _numberLine) {
        paginationEgg.setLine(_numberLine);
    }

    public void checkLinePokemonFirstBox(int _numberLine) {
        firstPaginationPk.checkLine(_numberLine);
        if (getSelectedPokemonFirstBox() != null) {
            selectedTeamPokemon = false;
            selectedBoxPokemon = true;
        }
    }

    public void setLinePokemonFirstBox(int _numberLine) {
        firstPaginationPk.setLine(_numberLine);
    }

    public Egg getSelectedEggBox() {
        return paginationEgg.currentObject();
    }

    public boolean canTakeItemFromStorage() {
        PokemonPlayer pk_ = getSelectedPokemonFirstBox();
        if (pk_ == null) {
            return false;
        }
        return !pk_.getItem().isEmpty();
    }

    public PokemonPlayer getSelectedPokemonFirstBox() {
        return firstPaginationPk.currentObject();
    }

    public UsablePokemon getSelectedPkTeam() {
        return game.getPlayer().getSelectedPkTeam();
    }

    public boolean enabledPreviousFirstBox() {
        return firstPaginationPk.enabledPrevious();
    }

    public void previousFirstBox() {
        firstPaginationPk.previous();
    }

    public void previousDeltaFirstBox() {
        firstPaginationPk.previousDelta();
    }

    public void endFirstBox() {
        firstPaginationPk.end();
    }

    public boolean enabledNextFirstBox() {
        return firstPaginationPk.enabledNext();
    }

    public void nextFirstBox() {
        firstPaginationPk.next();
    }

    public void nextDeltaFirstBox() {
        firstPaginationPk.nextDelta();
    }

    public void beginFirstBox() {
        firstPaginationPk.begin();
    }

    public void changePageFirstBox(int _page) {
        firstPaginationPk.changePage(_page);
    }

    public int pagesPk() {
        return firstPaginationPk.pages();
    }

    public CustList<SortingPokemonPlayer> getRenderedFirstBox() {
        return firstPaginationPk.getRendered();
    }

    public int getNumberPageFirstBox() {
        return firstPaginationPk.getNumberPage();
    }

    public int getNbResultsPerPageFirstBox() {
        return firstPaginationPk.getNbResultsPerPage();
    }

    public void setNbResultsPerPageFirstBox(int _nbResultsPerPage) {
        firstPaginationPk.setNbResultsPerPage(_nbResultsPerPage);
    }

    public void setDeltaFirstBox(int _delta) {
        firstPaginationPk.setDelta(_delta);
    }

    public void setCmpLevelIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpLevel().setIncreasing(_increasing);
    }

    public void setCmpLevelPriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpLevel().setPriority(_priority);
    }

    public void setCmpGenderIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpGender().setIncreasing(_increasing);
    }

    public void setCmpGenderPriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpGender().setPriority(_priority);
    }

    public void setCmpNameIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpName().setIncreasing(_increasing);
    }

    public void setCmpNamePriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpName().setPriority(_priority);
    }

    public void setCmpAbilityIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpAbility().setIncreasing(_increasing);
    }

    public void setCmpAbilityPriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpAbility().setPriority(_priority);
    }

    public void setCmpItemIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpItem().setIncreasing(_increasing);
    }

    public void setCmpItemPriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpItem().setPriority(_priority);
    }

    public void setCmpPossEvosIncreasingFirstBox(SelectedBoolean _increasing) {
        firstPaginationPk.getCmpPossEvos().setIncreasing(_increasing);
    }

    public void setCmpPossEvosPriorityFirstBox(int _priority) {
        firstPaginationPk.getCmpPossEvos().setPriority(_priority);
    }

    public void setSearchModeNameFirstBox(SearchingMode _searchModeName) {
        firstPaginationPk.getCriteria().setSearchModeName(_searchModeName);
    }

    public void setContentOfNameFirstBox(String _contentOfName) {
        firstPaginationPk.getCriteria().setContentOfName(_contentOfName);
    }

    public void setSearchModeAbilityFirstBox(SearchingMode _searchModeAbility) {
        firstPaginationPk.getCriteria()
                .setSearchModeAbility(_searchModeAbility);
    }

    public void setContentOfAbilityFirstBox(String _contentOfAbility) {
        firstPaginationPk.getCriteria().setContentOfAbility(_contentOfAbility);
    }

    public void setWithItemFirstBox(SelectedBoolean _withItem) {
        firstPaginationPk.getCriteria().setWithItem(_withItem);
    }

    public void setSearchModeItemFirstBox(SearchingMode _searchModeObject) {
        firstPaginationPk.getCriteria().setSearchModeItem(_searchModeObject);
    }

    public void setContentOfItemFirstBox(String _contentOfObject) {
        firstPaginationPk.getCriteria().setContentOfItem(_contentOfObject);
    }

    public void setSearchModeMoveFirstBox(SearchingMode _searchModeMove) {
        firstPaginationPk.getCriteria().setSearchModeMove(_searchModeMove);
    }

    public void setContentOfMoveFirstBox(String _contentOfMove) {
        firstPaginationPk.getCriteria().setContentOfMove(_contentOfMove);
    }

    public void setMinLevelFirstBox(Long _minLevel) {
        firstPaginationPk.getCriteria().setMinLevel(_minLevel);
    }

    public void setMaxLevelFirstBox(Long _maxLevel) {
        firstPaginationPk.getCriteria().setMaxLevel(_maxLevel);
    }

    public void setGenderFirstBox(Gender _gender) {
        firstPaginationPk.getCriteria().setGender(_gender);
    }

    public void setMinNbPossEvolsFirstBox(Long _minNbPossEvols) {
        firstPaginationPk.getCriteria().setMinNbPossEvols(_minNbPossEvols);
    }

    public void setMaxNbPossEvolsFirstBox(Long _minNbPossEvols) {
        firstPaginationPk.getCriteria().setMaxNbPossEvols(_minNbPossEvols);
    }

    public int getLineFirstBox() {
        return firstPaginationPk.getLine();
    }

    public void clearFiltersFirstBox() {
        setContentOfNameFirstBox(null);
        setSearchModeNameFirstBox(SearchingMode.WHOLE_STRING);
        setContentOfAbilityFirstBox(null);
        setSearchModeAbilityFirstBox(SearchingMode.WHOLE_STRING);
        setContentOfItemFirstBox(null);
        setSearchModeItemFirstBox(SearchingMode.WHOLE_STRING);
        setWithItemFirstBox(SelectedBoolean.YES_AND_NO);
        setMinLevelFirstBox(null);
        setMaxLevelFirstBox(null);
        setContentOfMoveFirstBox(null);
        setSearchModeMoveFirstBox(SearchingMode.WHOLE_STRING);
        setGenderFirstBox(null);
        setMinNbPossEvolsFirstBox(null);
        setMaxNbPossEvolsFirstBox(null);
    }

    public void clearSortingFirstBox() {
        setCmpNameIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpAbilityIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpItemIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpLevelIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpGenderIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpPossEvosIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        setCmpNamePriorityFirstBox(0);
        setCmpAbilityPriorityFirstBox(0);
        setCmpItemPriorityFirstBox(0);
        setCmpLevelPriorityFirstBox(0);
        setCmpGenderPriorityFirstBox(0);
        setCmpPossEvosPriorityFirstBox(0);
    }

    public boolean enabledPreviousEgg() {
        return paginationEgg.enabledPrevious();
    }

    public void previousEgg() {
        paginationEgg.previous();
    }

    public void previousDeltaEgg() {
        paginationEgg.previousDelta();
    }

    public void endEgg() {
        paginationEgg.end();
    }

    public boolean enabledNextEgg() {
        return paginationEgg.enabledNext();
    }

    public void nextEgg() {
        paginationEgg.next();
    }

    public void nextDeltaEgg() {
        paginationEgg.nextDelta();
    }

    public void beginEgg() {
        paginationEgg.begin();
    }

    public void changePageEgg(int _page) {
        paginationEgg.changePage(_page);
    }

    public int pagesEgg() {
        return paginationEgg.pages();
    }

    public CustList<SortingEgg> getRenderedEgg() {
        return paginationEgg.getRendered();
    }

    public int getNumberPageEgg() {
        return paginationEgg.getNumberPage();
    }

    public int getNbResultsPerPageEgg() {
        return paginationEgg.getNbResultsPerPage();
    }

    public void setNbResultsPerPageEgg(int _nbResultsPerPage) {
        paginationEgg.setNbResultsPerPage(_nbResultsPerPage);
    }

    public void setDeltaEgg(int _delta) {
        paginationEgg.setDelta(_delta);
    }

    public void setCmpStepsIncreasingEgg(SelectedBoolean _increasing) {
        paginationEgg.getCmpSteps().setIncreasing(_increasing);
    }

    public void setCmpStepsPriorityEgg(int _priority) {
        paginationEgg.getCmpSteps().setPriority(_priority);
    }

    public void setCmpNameIncreasingEgg(SelectedBoolean _increasing) {
        paginationEgg.getCmpName().setIncreasing(_increasing);
    }

    public void setCmpNamePriorityEgg(int _priority) {
        paginationEgg.getCmpName().setPriority(_priority);
    }

    public void setSearchModeNameEgg(SearchingMode _searchModeName) {
        paginationEgg.getCriteria().setSearchModeName(_searchModeName);
    }

    public void setContentOfNameEgg(String _contentOfName) {
        paginationEgg.getCriteria().setContentOfName(_contentOfName);
    }

    public void setMinStepsEgg(Long _minSteps) {
        paginationEgg.getCriteria().setMinSteps(_minSteps);
    }

    public void setMaxStepsEgg(Long _maxSteps) {
        paginationEgg.getCriteria().setMaxSteps(_maxSteps);
    }

    public void clearFiltersEgg() {
        setContentOfNameEgg(null);
        setSearchModeNameEgg(SearchingMode.WHOLE_STRING);
        setMinStepsEgg(null);
        setMaxStepsEgg(null);
    }

    public void clearSortingEgg() {
        setCmpNameIncreasingEgg(SelectedBoolean.YES_AND_NO);
        setCmpStepsIncreasingEgg(SelectedBoolean.YES_AND_NO);
        setCmpNamePriorityEgg(0);
        setCmpStepsPriorityEgg(0);
    }

    public int getLineEgg() {
        return paginationEgg.getLine();
    }

    // %%%%end%%%% functions for storage

    // %%%%begin%%%% option team

    public boolean isSelectedTeamPokemonItem() {
        if (!isSelectedTeamPokemon()) {
            return false;
        }
        Player pl_ = getPlayer();
        UsablePokemon us_ = pl_.getTeam().get(pl_.getChosenTeamPokemon());
        PokemonPlayer pk_ = (PokemonPlayer) us_;
        return !pk_.getItem().isEmpty();
    }

    public boolean isSelectedTeamPokemon() {
        return selectedTeamPokemon;
    }

    public boolean isSelectedBoxPokemon() {
        return selectedBoxPokemon;
    }

    public boolean isSelectedOtherPokemon() {
        return selectedOtherPokemon;
    }

    public void setSelectedOtherPokemon(boolean _selectedOtherPokemon) {
        selectedOtherPokemon = _selectedOtherPokemon;
    }

    public void setHostedPokemon(boolean _first, Coords _coords) {
        hostedPokemon = getHostedPokemon(_first, _coords);
    }

    PokemonPlayer getHostedPokemon(boolean _first, Coords _coords) {
        HostPokemonDuo host_ = game.getHostedPk().getVal(_coords);
        if (_first) {
            return host_.getFirstPokemon();
        }
        return host_.getSecondPokemon();
    }

    public PokemonPlayer getHostedPokemon() {
        return hostedPokemon;
    }

    public MiniMapCoordsTileInts getImages() {
        MiniMapCoordsTileInts miniMap_;
        miniMap_ = data.getMap().getImages(data);
        for (MiniMapCoords m : miniMap_.getKeys()) {
            TileMiniMap tile_ = data.getMap().getMiniMap().getVal(m);
            if (NumberUtil.eq(tile_.getPlace(), IndexConstants.INDEX_NOT_FOUND_ELT) || !(data.getMap().getPlace(tile_.getPlace()) instanceof City)) {
                continue;
            }
            boolean visited_ =  game.getVisitedPlacesNb().getVal(tile_.getPlace()) == BoolVal.TRUE;
            if (visited_) {
                miniMap_.put(m, data.getMiniMap(data.getMap().getUnlockedCity()));
            }
        }
        return miniMap_;
    }

    public int getMapHeight() {
        return data.getMap().getMapHeight();
    }

    public int getMapWidth() {
        return data.getMap().getMapWidth();
    }

    public String getChosenCity() {
        Coords coords_ = getCity(miniMapCoords);
        if (!coords_.isValid()) {
            return DataBase.EMPTY_STRING;
        }
        Place pl_ = data.getMap().getPlace(coords_.getNumberPlace());
        return pl_.getName();
    }

    public void choosePlace() {
        Coords coords_ = getCity(miniMapCoords);
        if (!coords_.isValid()) {
            return;
        }
        if (game.getVisitedPlaces().getVal(coords_) != BoolVal.TRUE) {
            return;
        }
        int notFound_;
        notFound_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        setMiniMapCoords(notFound_, notFound_);
        // miniMapCoords.setXcoords((short) CustList.INDEX_NOT_FOUND_ELT);
        // miniMapCoords.setYcoords((short) CustList.INDEX_NOT_FOUND_ELT);
        game.getPlayerCoords().affect(coords_);
        game.setRankLeague((byte) 0);
        changeCamera();
    }

    Coords getCity(MiniMapCoords _miniMapCoords) {
        return data.getMap().getCity(_miniMapCoords);
    }

    public GameProgression getGameProgression() {
        return new GameProgression(data, game);
    }

    public String getName(int _x, int _y) {
        return data.getMap().getName(_x, _y);
    }

    public void initTrading() {
        exchangeData = new ExchangedData(data);
    }

    public PokemonPlayer getReceivedPokemon() {
        return exchangeData.getPokemon();
    }

    public void setIndexTeamTrading(int _indexTeam) {
        exchangeData.setIndexTeam(_indexTeam);
    }

    public boolean isSelectedIndexTeamTrading() {
        return exchangeData.getIndexTeam() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public PokemonPlayer getSentPokemon() {
        return (PokemonPlayer) game.getPlayer().getTeam()
                .get(exchangeData.getIndexTeam());
    }

    public void receivePokemonPlayer(PokemonPlayer _pkPlayerOtherPlayer) {
        exchangeData.setPokemon(_pkPlayerOtherPlayer);
        exchangeData.check();
        if (exchangeData.getPokemon() == null) {
            return;
        }
        _pkPlayerOtherPlayer.initilializeFromExchange(data);
        setSelectedOtherPokemon(true);
        selectedTeamPokemon = false;
        selectedBoxPokemon = false;
    }

    public void applyTrading() {
        game.getPlayer().setTeamAfterTrading(exchangeData);
    }

    public void closeTrading() {
        exchangeData = null;
        setSelectedOtherPokemon(false);
    }

    public ExchangedData getExchangeData() {
        return exchangeData;
    }

    public void cancelLearningMoveOnPokemon() {
        game.getPlayer().cancelLearningMoveOnPokemon();
    }

    public void setChosenTeamPokemon(short _chosenPokemon) {
        game.getPlayer().setChosenTeamPokemon(_chosenPokemon);
        selectedTeamPokemon = game.getPlayer().isValidPkPlayerChoice();
        selectedBoxPokemon = false;
    }

    public void switchTeamOrder(short _chosenOtherPokemon) {
        game.getPlayer().switchTeamOrder(_chosenOtherPokemon);
    }

    public void takeObjectFromTeam() {
        comment.clearMessages();
        game.getPlayer().takeObjectFromTeam(data);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    public void validateNickname(String _nickname) {
        game.nickname(_nickname, data);
    }

    // %%%%end%%%% option team

    // %%%%begin%%%% functions for buy/sell items
    public void searchObjectToBuyOrSell(boolean _buy) {
        if (_buy) {
            Coords coords_;
            coords_ = game.closestTile(data.getMap());
            City pl_ = (City) data.getMap().getPlace(coords_.getNumberPlace());
            LevelIndoorPokemonCenter lev_ = (LevelIndoorPokemonCenter) pl_
                    .getLevelByCoords(coords_);
            Seller seller_ = (Seller) lev_.getGerants().getVal(
                    coords_.getLevel().getPoint());
            Inventory inventory_ = game.getPlayer().getInventory();
            paginationItem.setInventory(inventory_);
            paginationItem.search(seller_.getItems(), data);
        } else {
            searchObjectToUse();
        }
    }

    public void searchObjectToUse() {
        StringList list_ = new StringList();
        Inventory inventory_ = game.getPlayer().getInventory();
        for (String i : data.getItems().getKeys()) {
            if (inventory_.getNumber(i).isZero()) {
                continue;
            }
            list_.add(i);
        }
        paginationItem.setInventory(inventory_);
        paginationItem.search(list_, data);
    }

    public void addItemToBuyOrSell() {
        String current_ = paginationItem.currentObject();
        addItemToBuyOrSell(current_);
    }

    public void addOrRemoveItemToBuyOrSell(String _item, boolean _add) {
        if (_add) {
            addItemToBuyOrSell(_item);
        } else {
            removeItemToBuyOrSell(_item);
        }
    }

    public void addItemToBuyOrSell(String _item) {
        if (chosenItemsForBuyOrSell.contains(_item)) {
            chosenItemsForBuyOrSell.getVal(_item).increment();
        } else {
            chosenItemsForBuyOrSell.put(_item, LgInt.one());
        }
    }

    public void removeItemToBuyOrSell(String _item) {
        if (!chosenItemsForBuyOrSell.contains(_item)) {
            return;
        }
        if (chosenItemsForBuyOrSell.getVal(_item).isZeroOrLt()) {
            return;
        }
        chosenItemsForBuyOrSell.getVal(_item).decrement();
    }

    public void clearItemsToBuyOrSell() {
        chosenItemsForBuyOrSell.clear();
    }

    public LgInt amount() {
        LgInt amount_ = LgInt.zero();
        for (String i : chosenItemsForBuyOrSell.getKeys()) {
            amount_.addNb(LgInt.multiply(chosenItemsForBuyOrSell.getVal(i),
                    new LgInt(data.getItem(i).getPrice())));
        }
        return amount_;
    }

    public void buyOrSellItems(boolean _buy) {
        if (_buy) {
            if (!game.getPlayer().canBeBought(chosenItemsForBuyOrSell, data)) {
                return;
            }
            game.getPlayer().achatObjets(chosenItemsForBuyOrSell, data);
        } else {
            game.getPlayer().venteObjets(chosenItemsForBuyOrSell, data);
        }
        chosenItemsForBuyOrSell.clear();
        searchObjectToUse();
    }

    public boolean canBeBought() {
        return game.getPlayer().canBeBought(chosenItemsForBuyOrSell, data);
    }

    public boolean canBeBoughtTm() {
        return game.getPlayer().canBeBought(chosenTmForBuy, data);
    }

    public StringList getChosenTmForBuy() {
        StringList moves_ = new StringList();
        for (short t : chosenTmForBuy) {
            moves_.add(data.getTm().getVal(t));
        }
        return moves_;
    }

    public ItemsBuySellMap getChosenItemsForBuyOrSell() {
        ItemsBuySellMap tr_ = new ItemsBuySellMap(new ComparatorTrStrings(data
                .getTranslatedItems().getVal(language)));
        for (EntryCust<String, LgInt> e : chosenItemsForBuyOrSell.entryList()) {
            tr_.put(e.getKey(), e.getValue());
        }
        return tr_;
        // return new TreeMap<new>(new chosenItemsForBuyOrSell);
    }

    // %%%%end%%%% functions for buy/sell items

    // %%%%begin%%%% search items
    public void newSearchItem() {
        paginationItem.newSearch();
    }

    public void checkLineItem(int _numberLine) {
        paginationItem.checkLine(_numberLine);
    }

    public boolean enabledPreviousItem() {
        return paginationItem.enabledPrevious();
    }

    public void previousItem() {
        paginationItem.previous();
    }

    public void previousDeltaItem() {
        paginationItem.previousDelta();
    }

    public void endItem() {
        paginationItem.end();
    }

    public boolean enabledNextItem() {
        return paginationItem.enabledNext();
    }

    public void nextItem() {
        paginationItem.next();
    }

    public void nextDeltaItem() {
        paginationItem.nextDelta();
    }

    public void beginItem() {
        paginationItem.begin();
    }

    public void changePageItem(int _page) {
        paginationItem.changePage(_page);
    }

    public int pagesItem() {
        return paginationItem.pages();
    }

    public CustList<SortingItem> getRenderedItem() {
        return paginationItem.getRendered();
    }

    public int getNumberPageItem() {
        return paginationItem.getNumberPage();
    }

    public void setNbResultsPerPageItem(int _nbResultsPerPage) {
        paginationItem.setNbResultsPerPage(_nbResultsPerPage);
    }

    public void setDeltaItem(int _delta) {
        paginationItem.setDelta(_delta);
    }

    public void setCmpNameIncreasingItem(SelectedBoolean _increasing) {
        paginationItem.getCmpName().setIncreasing(_increasing);
    }

    public void setCmpNamePriorityItem(int _priority) {
        paginationItem.getCmpName().setPriority(_priority);
    }

    public void setCmpDescriptionIncreasingItem(SelectedBoolean _increasing) {
        paginationItem.getCmpDescription().setIncreasing(_increasing);
    }

    public void setCmpDescriptionPriorityItem(int _priority) {
        paginationItem.getCmpDescription().setPriority(_priority);
    }

    public void setCmpPriceIncreasingItem(SelectedBoolean _increasing) {
        paginationItem.getCmpPrice().setIncreasing(_increasing);
    }

    public void setCmpPricePriorityItem(int _priority) {
        paginationItem.getCmpPrice().setPriority(_priority);
    }

    public void setCmpNumberIncreasingItem(SelectedBoolean _increasing) {
        paginationItem.getCmpNumber().setIncreasing(_increasing);
    }

    public void setCmpNumberPriorityItem(int _priority) {
        paginationItem.getCmpNumber().setPriority(_priority);
    }

    public void setSearchModeNameItem(SearchingMode _searchModeName) {
        paginationItem.getCriteria().setSearchModeName(_searchModeName);
    }

    public void setContentOfNameItem(String _contentOfName) {
        paginationItem.getCriteria().setContentOfName(_contentOfName);
    }

    public void setSearchModeDescriptionItem(
            SearchingMode _searchModeDescription) {
        paginationItem.getCriteria().setSearchModeDescription(
                _searchModeDescription);
    }

    public void setContentOfDescriptionItem(String _contentOfDescription) {
        paginationItem.getCriteria().setContentOfDescription(
                _contentOfDescription);
    }

    public void setMinPriceItem(Long _minPrice) {
        paginationItem.getCriteria().setMinPrice(_minPrice);
    }

    public void setMaxPriceItem(Long _maxPrice) {
        paginationItem.getCriteria().setMaxPrice(_maxPrice);
    }

    public void setMinNumberItem(LgInt _minNumber) {
        paginationItem.getCriteria().setMinNumber(_minNumber);
    }

    public void setMaxNumberItem(LgInt _maxNumber) {
        paginationItem.getCriteria().setMaxNumber(_maxNumber);
    }

    public void clearFoundResultsItems() {
        paginationItem.clear();
    }

    public int getLineItem() {
        return paginationItem.getLine();
    }

    public int getNbResultsPerPageItem() {
        return paginationItem.getNbResultsPerPage();
    }
    public void clearFiltersItem() {
        setContentOfNameItem(null);
        setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        setContentOfDescriptionItem(null);
        setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        setMinPriceItem(null);
        setMaxPriceItem(null);
        setMinNumberItem(null);
        setMaxNumberItem(null);
    }

    public void clearSortingItem() {
        setCmpNameIncreasingItem(SelectedBoolean.YES_AND_NO);
        setCmpDescriptionIncreasingItem(SelectedBoolean.YES_AND_NO);
        setCmpPriceIncreasingItem(SelectedBoolean.YES_AND_NO);
        setCmpNumberIncreasingItem(SelectedBoolean.YES_AND_NO);
        setCmpNamePriorityItem(0);
        setCmpDescriptionPriorityItem(0);
        setCmpPricePriorityItem(0);
        setCmpNumberPriorityItem(0);
    }

    // %%%%end%%%% search items

    // %%%%begin%%%% functions for buy/use technical moves
    public void searchTmToBuy() {
        Coords coords_;
        coords_ = game.closestTile(data.getMap());
        City pl_ = (City) data.getMap().getPlace(coords_.getNumberPlace());
        LevelIndoorPokemonCenter lev_ = (LevelIndoorPokemonCenter) pl_
                .getLevelByCoords(coords_);
        Seller seller_ = (Seller) lev_.getGerants().getVal(
                coords_.getLevel().getPoint());
        StringList list_ = new StringList();
        for (short i : seller_.getTm()) {
            String m_ = data.getTm().getVal(i);
            list_.add(m_);
        }
        StringUtil.removeAllElements(list_, getOwnedMoves());
        paginationMove.search(list_, data);
    }

    public void searchTmToUse() {
        StringList list_ = getOwnedMoves();
        paginationMove.search(list_, data);
    }

    StringList getOwnedMoves() {
        StringList list_ = new StringList();
        Inventory inventory_ = game.getPlayer().getInventory();
        for (short i : inventory_.gotTm()) {
            String m_ = data.getTm().getVal(i);
            list_.add(m_);
        }
        return list_;
    }

    public void newSearchMove() {
        paginationMove.newSearch();
    }

    public void checkLineMove(int _numberLine) {
        paginationMove.checkLine(_numberLine);
    }

    public MoveData getSelectedMove() {
        return data.getMove(paginationMove.currentObject());
    }

    public boolean enabledPreviousMove() {
        return paginationMove.enabledPrevious();
    }

    public void previousMove() {
        paginationMove.previous();
    }

    public void previousDeltaMove() {
        paginationMove.previousDelta();
    }

    public void endMove() {
        paginationMove.end();
    }

    public boolean enabledNextMove() {
        return paginationMove.enabledNext();
    }

    public void nextMove() {
        paginationMove.next();
    }

    public void nextDeltaMove() {
        paginationMove.nextDelta();
    }

    public void beginMove() {
        paginationMove.begin();
    }

    public void changePageMove(int _page) {
        paginationMove.changePage(_page);
    }

    public int pagesMove() {
        return paginationMove.pages();
    }

    public CustList<SortingMove> getRenderedMove() {
        return paginationMove.getRendered();
    }

    public int getNumberPageMove() {
        return paginationMove.getNumberPage();
    }

    public void setNbResultsPerPageMove(int _nbResultsPerPage) {
        paginationMove.setNbResultsPerPage(_nbResultsPerPage);
    }

    public void setDeltaMove(int _delta) {
        paginationMove.setDelta(_delta);
    }

    public void setCmpPriceIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpPrice().setIncreasing(_increasing);
    }

    public void setCmpPricePriorityMove(int _priority) {
        paginationMove.getCmpPrice().setPriority(_priority);
    }

    public void setCmpPrioIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpPrio().setIncreasing(_increasing);
    }

    public void setCmpPrioPriorityMove(int _priority) {
        paginationMove.getCmpPrio().setPriority(_priority);
    }

    public void setCmpPppIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpPpp().setIncreasing(_increasing);
    }

    public void setCmpPppPriorityMove(int _priority) {
        paginationMove.getCmpPpp().setPriority(_priority);
    }

    public void setCmpTargetChoiceIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpTargetChoice().setIncreasing(_increasing);
    }

    public void setCmpTargetChoicePriorityMove(int _priority) {
        paginationMove.getCmpTargetChoice().setPriority(_priority);
    }

    public void setCmpNameIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpName().setIncreasing(_increasing);
    }

    public void setCmpDescriptionPriorityMove(int _priority) {
        paginationMove.getCmpDescription().setPriority(_priority);
    }

    public void setCmpDescriptionIncreasingMove(SelectedBoolean _increasing) {
        paginationMove.getCmpDescription().setIncreasing(_increasing);
    }

    public void setCmpNamePriorityMove(int _priority) {
        paginationMove.getCmpName().setPriority(_priority);
    }

    public void setSearchModeNameMove(SearchingMode _searchModeName) {
        paginationMove.getCriteria().setSearchModeName(_searchModeName);
    }

    public void setContentOfNameMove(String _contentOfName) {
        paginationMove.getCriteria().setContentOfName(_contentOfName);
    }

    public void setSearchModeTypeMove(SearchingMode _searchModeType) {
        paginationMove.getCriteria().setSearchModeType(_searchModeType);
    }

    public void setContentOfTypeMove(String _contentOfType) {
        paginationMove.getCriteria().setContentOfType(_contentOfType);
    }

    public void setMinPpMove(Long _minPp) {
        paginationMove.getCriteria().setMinPp(_minPp);
    }

    public void setMaxPpMove(Long _maxPp) {
        paginationMove.getCriteria().setMaxPp(_maxPp);
    }

    public void setMinPrioMove(Long _minPrio) {
        paginationMove.getCriteria().setMinPrio(_minPrio);
    }

    public void setMaxPrioMove(Long _maxPrio) {
        paginationMove.getCriteria().setMaxPrio(_maxPrio);
    }

    public void setMinPriceMove(Long _minPrice) {
        paginationMove.getCriteria().setMinPrice(_minPrice);
    }

    public void setMaxPriceMove(Long _maxPrice) {
        paginationMove.getCriteria().setMaxPrice(_maxPrice);
    }

    public void setSelectedClassMove(String _selectedClass) {
        paginationMove.getCriteria().setSelectedClass(_selectedClass);
    }

    public void setTargetChoiceMove(TargetChoice _targetChoice) {
        paginationMove.getCriteria().setTargetChoice(_targetChoice);
    }

    public int getNbResultsPerPageMove() {
        return paginationMove.getNbResultsPerPage();
    }
    public void clearFiltersMove() {
        setContentOfNameMove(null);
        setSearchModeNameMove(SearchingMode.WHOLE_STRING);
        setContentOfTypeMove(null);
        setSearchModeTypeMove(SearchingMode.WHOLE_STRING);
        setMinPpMove(null);
        setMaxPpMove(null);
        setMinPrioMove(null);
        setMaxPrioMove(null);
        setMinPriceMove(null);
        setMaxPriceMove(null);
        setTargetChoiceMove(null);
    }

    public void clearSortingMove() {
        setCmpNameIncreasingMove(SelectedBoolean.YES_AND_NO);
        setCmpPppIncreasingMove(SelectedBoolean.YES_AND_NO);
        setCmpPrioIncreasingMove(SelectedBoolean.YES_AND_NO);
        setCmpPriceIncreasingMove(SelectedBoolean.YES_AND_NO);
        setCmpTargetChoiceIncreasingMove(SelectedBoolean.YES_AND_NO);
        setCmpNamePriorityMove(0);
        setCmpPppPriorityMove(0);
        setCmpPrioPriorityMove(0);
        setCmpPricePriorityMove(0);
        setCmpTargetChoicePriorityMove(0);
    }

    public int getLineMove() {
        return paginationMove.getLine();
    }

    public void addTmToBuy() {
        String move_ = paginationMove.currentObject();
        addTmToBuy(move_);
    }

    public void addTmToBuy(String _move) {
        // chosenTmForBuy.add(data.getTm().getKeys(_move).first());
        chosenTmForBuy.add(data.getTmByMove(_move).first());
    }

    public void removeTmToBuy(String _item) {
        // chosenTmForBuy.removeObj(data.getTm().getKeys(_item).first());
        chosenTmForBuy.removeObj(data.getTmByMove(_item).first());
    }

    public LgInt amountTm() {
        LgInt amount_ = LgInt.zero();
        for (short i : chosenTmForBuy) {
            amount_.addNb(new LgInt(data.getTmPrice().getVal(i)));
        }
        return amount_;
    }

    public void buyTm() {
        if (!game.getPlayer().canBeBought(chosenTmForBuy, data)) {
            return;
        }
        game.getPlayer().achatCts(chosenTmForBuy, data);
        chosenTmForBuy.clear();
        searchTmToUse();
    }

    public void clearFoundResultsTm() {
        paginationMove.clear();
    }

    // %%%%end%%%% functions for buy/use technical moves

    // %%%%begin%%%% option items
    public void chooseObject() {
        String item_ = paginationItem.currentObject();
        game.getPlayer().chooseObject(item_);
    }

    public boolean usedObject() {
        return getPlayer().usedObject(data);
    }

    public void useObject() {
        game.getPlayer().useObject(data);
    }

    private void cancelUseObject() {
        game.getPlayer().cancelUseObject();
    }

    public void selectPokemon(short _index) {
        comment.clearMessages();
        giveObject(_index);
        choosePokemon(_index);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    void giveObject(short _index) {
        if (!givingObject) {
            return;
        }
        game.getPlayer().giveObject(_index);
    }

    public boolean usedObjectForEvolving() {
        return game.getPlayer().usedObjectForEvolving(data);
    }

    public boolean usedObjectForHealingAmove() {
        return game.getPlayer().usedObjectForHealingAmove(data);
    }

    public boolean usedObjectForBoosting() {
        return game.getPlayer().usedObjectForBoosting(data);
    }

    void choosePokemon(short _chosenTeamPokemon) {
        if (givingObject) {
            return;
        }
        game.getPlayer().choosePokemonForUsingObject(_chosenTeamPokemon, data);
    }

    public void evolvePokemon() {
        comment.clearMessages();
        game.getPlayer().evolvePokemon(data);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    public boolean isGivingObject() {
        return givingObject;
    }

    public void addOrDeleteMoveEvo(String _move) {
        PokemonPlayer pk_ = (PokemonPlayer) game.getPlayer()
                .getSelectedPkTeam();
        boolean learnt_ = pk_.getMovesToBeKeptEvo().getVal(_move) == BoolVal.TRUE;
        pk_.getMovesToBeKeptEvo().put(_move, ComparatorBoolean.of(!learnt_));
    }

    public StringList getKeptMovesToEvo() {
        PokemonPlayer pk_ = (PokemonPlayer) game.getPlayer()
                .getSelectedPkTeam();
        StringMap<BoolVal> m_ = pk_.getMovesToBeKeptEvo();
        StringList l_ = new StringList();
        for (EntryCust<String, BoolVal> e : m_.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public StringList getUnKeptMovesToEvo() {
        PokemonPlayer pk_ = (PokemonPlayer) game.getPlayer()
                .getSelectedPkTeam();
        StringMap<BoolVal> m_ = pk_.getMovesToBeKeptEvo();
        StringList l_ = new StringList();
        for (EntryCust<String, BoolVal> e : m_.entryList()) {
            if (e.getValue() != BoolVal.TRUE) {
                l_.add(e.getKey());
            }
        }
        return l_;
    }

    public void gainPpMax(String _move) {
        game.getPlayer().gainPpMax(_move, data);
    }

    public void healMove(String _move) {
        game.getPlayer().healMove(_move, data);
    }

    public void setGivingObject(boolean _givingObject) {
        givingObject = _givingObject;
        if (givingObject) {
            game.getPlayer().setPokemonAbleToHoldObject();
        }
    }

    // %%%%end%%%% option items

    // %%%%begin%%%% hosting pokemon
    public void setSelectPkToHost(short _selectPkToHost) {
        if (firstSelectPkToHost == IndexConstants.INDEX_NOT_FOUND_ELT) {
            firstSelectPkToHost = _selectPkToHost;
            return;
        }
        if (firstSelectPkToHost == _selectPkToHost) {
            if (secondSelectPkToHost == IndexConstants.INDEX_NOT_FOUND_ELT) {
                firstSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;
                return;
            }
            firstSelectPkToHost = secondSelectPkToHost;
            secondSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;
            return;
        }
        if (secondSelectPkToHost == _selectPkToHost) {
            secondSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;
            return;
        }
        secondSelectPkToHost = _selectPkToHost;
    }

    public short getFirstSelectPkToHost() {
        return firstSelectPkToHost;
    }

    public short getSecondSelectPkToHost() {
        return secondSelectPkToHost;
    }

    public void attemptForStoringPokemonToHost() {
        if (firstSelectPkToHost == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        if (secondSelectPkToHost == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        ByteTreeMap< PokemonPlayer> team_ = game.getPlayer()
                .getPokemonPlayerList();
        Bytes keys_ = new Bytes(team_.getKeys());
        // int teamSize_ = game.getPlayer().getTeam().size();
        game.attemptForStoringPokemonToHost(keys_.get(firstSelectPkToHost),
                keys_.get(secondSelectPkToHost), data);
        firstSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;
        secondSelectPkToHost = IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public int getRemaingingSteps() {
        Coords voisin_ = closestTile();
        if (game.availableHosting(voisin_)) {
            return IndexConstants.INDEX_NOT_FOUND_ELT;
        }
        return game.nbRemainingSteps(voisin_, data);
    }

    public int getRemaingingSteps(Coords _coords) {
        return game.nbRemainingSteps(_coords, data);
    }

    public void receiveFromHost(boolean _onlyEgg) {
        if (_onlyEgg) {
            receiveOnlyEgg();
        } else {
            receiveEggOrParents();
        }
    }

    public void receiveOnlyEgg() {
        game.receiveOnlyEgg(data);
    }

    public void receiveEggOrParents() {
        game.receiveEggOrParents(data);
    }

    // %%%%end%%%% hosting pokemon

    public boolean isFishArea() {
        Coords next_ = closestTile();
        if (!next_.isValid()) {
            return false;
        }
        Place pl_ = data.getMap().getPlace(next_.getNumberPlace());
        Level l_ = pl_.getLevelByCoords(next_);
        return l_.getBlockByPoint(next_.getLevel().getPoint()).getType() == EnvironmentType.WATER;
    }

    public Coords closestTile() {
        return game.closestTile(data.getMap());
    }

    // %%%%begin%%%% learn technical moves option
    public void chooseMoveByObject() {
        String move_ = paginationMove.currentObject();
        game.getPlayer().chooseMoveByObject(move_, data);
    }

    public void choosePokemonForLearningMove(byte _position) {
        comment.clearMessages();
        game.getPlayer().choosePokemonForLearningMove(_position, data);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    public void learnMove(String _oldMove) {
        comment.clearMessages();
        game.getPlayer().learnMove(_oldMove, data);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    // %%%%end%%%% learn technical moves option

    // %%%%begin%%%% functions for learning move tutor
    public void choosePokemonForMoveTutors(short _position) {
        game.getPlayer().choosePokemonForMoveTutors(_position, data);
    }

    public StringList getSelectedMoves() {
        StringList list_;
        list_ = new StringList();
        StringMap<BoolVal> moves_ = game.getPlayer().getSelectedMoves();
        for (String k : moves_.getKeys()) {
            if (moves_.getVal(k) != BoolVal.TRUE) {
                continue;
            }
            list_.add(k);
        }
        list_.sortElts(new TrMovesComparator(data));
        return list_;
    }

    public StringList getUnselectedMoves() {
        StringList list_;
        list_ = new StringList();
        StringMap<BoolVal> moves_ = game.getPlayer().getSelectedMoves();
        for (String k : moves_.getKeys()) {
            if (moves_.getVal(k) == BoolVal.TRUE) {
                continue;
            }
            list_.add(k);
        }
        list_.sortElts(new TrMovesComparator(data));
        return list_;
    }

    public void addOrDeleteMove(String _move) {
        boolean selected_ = game.getPlayer().getSelectedMoves().getVal(_move) == BoolVal.TRUE;
        game.getPlayer().getSelectedMoves().put(_move, ComparatorBoolean.of(!selected_));
    }

    public void learnMovesByMoveTutor() {
        comment.clearMessages();
        game.getPlayer().learnMovesByMoveTutor(data);
        comment.addComment(game.getPlayer().getCommentGame());
    }

    // %%%%end%%%% functions for learning move tutor

    // %%%%begin%%%% fight before round or before proponed switch
    public int[][] getMiniHeros() {
        return game.getMiniHeros(data);
    }

    public int[][] getBackHeros() {
        return game.getBackHeros(data);
    }

    public int[][] getBackHerosSexOpposite() {
        return game.getBackHerosSexOpposite(data);
    }

    public boolean isDualFight() {
        return game.isDualFight();
    }

    public void chooseFrontFighter(byte _pos) {
        game.chooseFrontFighter(_pos, data);
    }

    public void changeAction(ActionType _action) {
        game.changeAction(_action, data);
    }

    public void chooseBackFighter(byte _pos) {
        game.chooseBackFighter(_pos, data);
    }

    public void chooseMove(String _move) {
        game.chooseMove(_move, data);
    }

    public void deselect() {
        game.deselect();
    }

    // public void validateSwitch() {
    // game.validateSwitch();
    // }

    public void setFirstChosenMoveFoeTarget(byte _cible) {
        game.setFirstChosenMoveFoeTarget(_cible);
    }

    public void setFirstChosenMovePlayerTarget(byte _cible) {
        game.setFirstChosenMovePlayerTarget(_cible);
    }

    public void searchPokemonHealingItem() {
        StringList list_ = new StringList();
        Inventory inventory_ = game.getPlayer().getInventory();
        for (String i : data.getItems().getKeys()) {
            if (inventory_.getNumber(i).isZero()) {
                continue;
            }
            list_.add(i);
        }
        paginationHealingItem.setInventory(inventory_);
        paginationHealingItem.search(list_, data);
    }

    public void newSearchHealingItem() {
        paginationHealingItem.newSearch();
    }

    public void checkLineHealingItem(int _numberLine) {
        paginationHealingItem.checkLine(_numberLine);
    }

    public boolean enabledPreviousHealingItem() {
        return paginationHealingItem.enabledPrevious();
    }

    public void previousHealingItem() {
        paginationHealingItem.previous();
    }

    public void previousDeltaHealingItem() {
        paginationHealingItem.previousDelta();
    }

    public void endHealingItem() {
        paginationHealingItem.end();
    }

    public boolean enabledNextHealingItem() {
        return paginationHealingItem.enabledNext();
    }

    public void nextHealingItem() {
        paginationHealingItem.next();
    }

    public void nextDeltaHealingItem() {
        paginationHealingItem.nextDelta();
    }

    public void beginHealingItem() {
        paginationHealingItem.begin();
    }

    public void changePageHealingItem(int _page) {
        paginationHealingItem.changePage(_page);
    }

    public int pagesHealingItem() {
        return paginationHealingItem.pages();
    }

    public CustList<SortingHealingItem> getRenderedHealingItem() {
        return paginationHealingItem.getRendered();
    }

    public int getNumberPageHealingItem() {
        return paginationHealingItem.getNumberPage();
    }

    public void setNbResultsPerPageHealingItem(int _nbResultsPerPage) {
        paginationHealingItem.setNbResultsPerPage(_nbResultsPerPage);
    }

    public void setDeltaHealingItem(int _delta) {
        paginationHealingItem.setDelta(_delta);
    }

    public void setCmpPriceIncreasingHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCmpPrice().setIncreasing(_increasing);
    }

    public void setCmpPricePriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpPrice().setPriority(_priority);
    }

    public void setCmpNumberIncreasingHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCmpNumber().setIncreasing(_increasing);
    }

    public void setCmpNumberPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpNumber().setPriority(_priority);
    }

    public void setCmpDescriptionIncreasingHealingItem(
            SelectedBoolean _increasing) {
        paginationHealingItem.getCmpDescription().setIncreasing(_increasing);
    }

    public void setCmpDescriptionPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpDescription().setPriority(_priority);
    }

    public void setCmpNameIncreasingHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCmpName().setIncreasing(_increasing);
    }

    public void setCmpNamePriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpName().setPriority(_priority);
    }

    public void setCmpRateHpIncreasingHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCmpRateHp().setIncreasing(_increasing);
    }

    public void setCmpRateHpPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpRateHp().setPriority(_priority);
    }

    public void setCmpPpIncreasingHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCmpPp().setIncreasing(_increasing);
    }

    public void setCmpPpPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpPp().setPriority(_priority);
    }

    public void setCmpNbHealedStatusIncreasingHealingItem(
            SelectedBoolean _increasing) {
        paginationHealingItem.getCmpNbHealedStatus().setIncreasing(_increasing);
    }

    public void setCmpNbHealedStatusPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpNbHealedStatus().setPriority(_priority);
    }

    public void setCmpRelativeRateHpIncreasingHealingItem(
            SelectedBoolean _increasing) {
        paginationHealingItem.getCmpRelativeRateHp().setIncreasing(_increasing);
    }

    public void setCmpRelativeRateHpPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpRelativeRateHp().setPriority(_priority);
    }

    public void setCmpRelativeRatePpIncreasingHealingItem(
            SelectedBoolean _increasing) {
        paginationHealingItem.getCmpRelativeRatePp().setIncreasing(_increasing);
    }

    public void setCmpRelativeRatePpPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpRelativeRatePp().setPriority(_priority);
    }

    public void setCmpStatisticsIncreasingHealingItem(
            SelectedBoolean _increasing) {
        paginationHealingItem.getCmpStatistics().setIncreasing(_increasing);
    }

    public void setCmpStatisticsPriorityHealingItem(int _priority) {
        paginationHealingItem.getCmpStatistics().setPriority(_priority);
    }

    public void setSearchModeNameHealingItem(SearchingMode _searchModeName) {
        paginationHealingItem.getCriteria().setSearchModeName(_searchModeName);
    }

    public void setContentOfNameHealingItem(String _contentOfName) {
        paginationHealingItem.getCriteria().setContentOfName(_contentOfName);
    }

    public void setSearchModeDescriptionHealingItem(
            SearchingMode _searchModeDescription) {
        paginationHealingItem.getCriteria().setSearchModeDescription(
                _searchModeDescription);
    }

    public void setContentOfDescriptionHealingItem(String _contentOfDescription) {
        paginationHealingItem.getCriteria().setContentOfDescription(
                _contentOfDescription);
    }

    public void setSearchModeStatusHealingItem(SearchingMode _searchModeStatus) {
        paginationHealingItem.getCriteria().setSearchModeStatus(
                _searchModeStatus);
    }

    public void setContentOfStatusHealingItem(String _contentOfStatus) {
        paginationHealingItem.getCriteria()
                .setContentOfStatus(_contentOfStatus);
    }

    public void setHealOneMoveHealingItem(SelectedBoolean _healOneMove) {
        paginationHealingItem.getCriteria().setHealOneMove(_healOneMove);
    }

    public void setKoHealingItem(SelectedBoolean _increasing) {
        paginationHealingItem.getCriteria().setKo(_increasing);
    }

    public void setRelativeRateHpHealingItem(SelectedBoolean _relativeRateHp) {
        paginationHealingItem.getCriteria().setRelativeRateHp(_relativeRateHp);
    }

    public void setRelativeRatePpHealingItem(SelectedBoolean _relativeRatePp) {
        paginationHealingItem.getCriteria().setRelativeRatePp(_relativeRatePp);
    }

    public void setStatisticHealingItem(Statistic _statistic) {
        paginationHealingItem.getCriteria().setStatistic(_statistic);
    }

    public void setMinPriceHealingItem(Long _minPrice) {
        paginationHealingItem.getCriteria().setMinPrice(_minPrice);
    }

    public void setMaxPriceHealingItem(Long _maxPrice) {
        paginationHealingItem.getCriteria().setMaxPrice(_maxPrice);
    }

    public void setMinNumberHealingItem(LgInt _minNumber) {
        paginationHealingItem.getCriteria().setMinNumber(_minNumber);
    }

    public void setMaxNumberHealingItem(LgInt _maxNumber) {
        paginationHealingItem.getCriteria().setMaxNumber(_maxNumber);
    }

    public void setMinHpHealingItem(Rate _minHp) {
        paginationHealingItem.getCriteria().setMinHp(_minHp);
    }

    public void setMaxHpHealingItem(Rate _maxHp) {
        paginationHealingItem.getCriteria().setMaxHp(_maxHp);
    }

    public void setMinRateHpHealingItem(Rate _minRateHp) {
        paginationHealingItem.getCriteria().setMinRateHp(_minRateHp);
    }

    public void setMaxRateHpHealingItem(Rate _maxRateHp) {
        paginationHealingItem.getCriteria().setMaxRateHp(_maxRateHp);
    }

    public void setMinPpHealingItem(Long _minPp) {
        paginationHealingItem.getCriteria().setMinPp(_minPp);
    }

    public void setMaxPpHealingItem(Long _maxPp) {
        paginationHealingItem.getCriteria().setMaxPp(_maxPp);
    }

    public int getLineHealingItem() {
        return paginationHealingItem.getLine();
    }

    public void setLineHealingItem(int _line) {
        paginationHealingItem.setLine(_line);
    }

    public int getNbResultsPerPageHealingItem() {
        return paginationHealingItem.getNbResultsPerPage();
    }
    public void clearFiltersHealingItem() {
        setContentOfNameHealingItem(null);
        setContentOfDescriptionHealingItem(null);
        setContentOfStatusHealingItem(null);

        setSearchModeNameHealingItem(SearchingMode.WHOLE_STRING);
        setSearchModeStatusHealingItem(SearchingMode.WHOLE_STRING);
        setSearchModeDescriptionHealingItem(SearchingMode.WHOLE_STRING);

        setRelativeRateHpHealingItem(SelectedBoolean.YES_AND_NO);

        setRelativeRatePpHealingItem(SelectedBoolean.YES_AND_NO);
        setHealOneMoveHealingItem(SelectedBoolean.YES_AND_NO);
        setKoHealingItem(SelectedBoolean.YES_AND_NO);
        setStatisticHealingItem(null);

        setMinHpHealingItem(null);
        setMaxHpHealingItem(null);
        setMinRateHpHealingItem(null);
        setMaxRateHpHealingItem(null);
        setMinPpHealingItem(null);
        setMaxPpHealingItem(null);
        setMinPriceHealingItem(null);
        setMaxPriceHealingItem(null);
        setMinNumberHealingItem(null);
        setMaxNumberHealingItem(null);
    }

    public void clearSortingHealingItem() {
        setCmpNameIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpDescriptionIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpPriceIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpNumberIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpPpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpRelativeRatePpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpRateHpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpRelativeRateHpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpStatisticsIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        setCmpNbHealedStatusIncreasingHealingItem(SelectedBoolean.YES_AND_NO);

        setCmpNamePriorityHealingItem(0);
        setCmpDescriptionPriorityHealingItem(0);
        setCmpPricePriorityHealingItem(0);
        setCmpNumberPriorityHealingItem(0);
        setCmpPpPriorityHealingItem(0);
        setCmpRelativeRatePpPriorityHealingItem(0);
        setCmpRateHpPriorityHealingItem(0);
        setCmpRelativeRateHpPriorityHealingItem(0);
        setCmpStatisticsPriorityHealingItem(0);
        setCmpNbHealedStatusPriorityHealingItem(0);
    }

    public void setChosenHealingItemWalk() {
        String selected_ = paginationHealingItem.currentObject();
        game.getPlayer().chooseObject(selected_);
    }

    public void setChosenHealingItem() {
        String selected_ = paginationHealingItem.currentObject();
        game.setChosenHealingItem(selected_, data);
    }

    public TeamPositionsStringMapTeamPositionsRate remainingThrowersTargetsHp() {
        return game.remainingThrowersTargetsHp(data);
    }

    public NatStringTreeMap< TeamPositionList> sortedFightersBeginRoundWildFight() {
        return game.sortedFightersBeginRoundWildFight(data);
    }

    public TeamPositionList sortedFightersBeginRound() {
        return game.sortedFightersBeginRound(data);
    }

    // public TreeMap<TeamPosition,ActionMove>
    // sortedFightersUsingMoveDependingOnPlayerChoices() {
    // return game.sortedFightersUsingMoveDependingOnPlayerChoices(data);
    // }

    public void setSubstituteEndRound(byte _remplacant) {
        game.setSubstituteEndRound(_remplacant);
    }

    // public void cancelChooseBackFighterWhileRound() {
    // game.cancelChooseBackFighterWhileRound();
    // }

    public void roundWhileKoPlayer(boolean _enableAnimation) {
        comment.clearMessages();
        game.roundWhileKoPlayer(data, _enableAnimation);
        comment.addComment(game.getCommentGame());
        exitDirectFight(_enableAnimation);
    }


    public void endRoundFightKoUser() {
        comment.clearMessages();
        game.endRoundFightKoUser(data);
        comment.addComment(game.getCommentGame());
        exitFight();
    }

    public void sendSubstitutes() {
        comment.clearMessages();
        game.sendSubstitutes(data);
        comment.addComment(game.getCommentGame());
        exitFight();
    }

    public void roundAllThrowers(boolean _enableAnimation) {
        comment.clearMessages();
        game.roundAllThrowers(data, _enableAnimation);
        comment.addComment(game.getCommentGame());
        exitDirectFight(_enableAnimation);
    }

    public void roundUser() {
        comment.clearMessages();
        game.roundUser(data);
        comment.addComment(game.getCommentGame());
    }

    public void endRoundFightBasic() {
        comment.clearMessages();
        game.endRoundFightBasic(data);
        comment.addComment(game.getCommentGame());
        exitFight();
    }

    public boolean isErrorFight() {
        return game.getFight().isError();
    }

    // %%%%end%%%% fight before round or before proponed switch

    // %%%%begin%%%% fight player side team
    public CustList< Fighter> getPlayerTeam() {
        return game.getPlayerTeam();
    }

    public ByteTreeMap< Fighter> getFoeFrontTeam() {
        return game.getFoeFrontTeam();
    }

    public ByteTreeMap< Fighter> getUnionFrontTeam() {
        return game.getUnionFrontTeam();
    }

    public CustList< Fighter> getPlayerFrontTeam() {
        return game.getPlayerFrontTeam();
    }

    public CustList< Fighter> getPlayerBackTeam() {
        return game.getPlayerBackTeam();
    }

//    public CustList< Fighter> getPlayerFrontTeamForSubstituting() {
//        return game.getPlayerFrontTeam();
//    }

//    public CustList< Fighter> getPlayerBackTeamForSubstituting() {
//        return game.getPlayerBackTeamForSubstituting();
//    }

    // public TreeMap<Byte,Fighter> getAllyFrontTeam() {
    // return game.getAllyFrontTeam();
    // }
    //
    // public TreeMap<Byte,Fighter> getAllyBackTeam() {
    // return game.getAllyBackTeam();
    // }
    // %%%%end%%%% fight player side team

    // %%%%begin%%%% fight evolutions and learning moves
    public boolean isChosableForLearningAndEvolving(byte _key) {
        return game.isChosableForLearningAndEvolving(_key);
    }

    public void choosePokemonForLearningAndEvolving(byte _key) {
        game.choosePokemonForLearningAndEvolving(_key, data);
    }

    public byte getChosenIndex() {
        return game.getChosenIndex();
    }

    public NatStringTreeMap< BoolVal> getMoves() {
        return game.getMoves();
    }

    public EvolutionChoiceMap getEvolutions() {
        return game.getEvolutions();
    }

    public StringList getAbilities() {
        return game.getAbilities();
    }

    public String getAbility() {
        return game.getAbility();
    }

    public void addOrForgetMove(String _move) {
        game.addOrForgetMove(_move);
    }

    public void setAbility(String _ability) {
        game.setAbility(_ability);
    }

    public void setEvolution(String _evo) {
        game.setEvolution(_evo);
    }

    public void learnAndEvolve() {
        comment.clearMessages();
        game.learnAndEvolve(data);
        comment.addComment(game.getCommentGame());
        exitFight();
    }

    // %%%%end%%%% fight evolutions and learning moves

    // %%%%begin%%%% wild fight
    public NatStringTreeMap< BallNumberRate> calculateCatchingRates() {
        return game.calculateCatchingRates(data);
    }

    public void attemptFlee(boolean _enableAnimation) {
        comment.clearMessages();
        game.attemptFlee(data, _enableAnimation);
        exitDirectFight(_enableAnimation);
        // comment.addComment(game.getPlayer().getCommentGame());
    }

    public void endRoundFightFlee() {
        comment.clearMessages();
        game.endRoundFightFlee(data);
        exitFight();
    }

    public Rate calculateFleeingRate() {
        return game.calculateFleeingRate(data);
    }

    public void attemptCatchingWildPokemon(String _ball,
            boolean _enableAnimation) {
        comment.clearMessages();
        game.attemptCatchingWildPokemon(_ball, data, _enableAnimation);
        comment.addComment(game.getCommentGame());
        exitDirectFight(_enableAnimation);
    }

    public void endRoundFightBall() {
        game.endRoundFightBall(data);
        exitFight();
    }

    public void endRoundFightSuccessBall() {
        game.endRoundFightSuccessBall(data);
        exitFight();
    }

    public void notCatchKoWildPokemon() {
        comment.clearMessages();
        game.notCatchKoWildPokemon(data);
        comment.addComment(game.getPlayer().getCommentGame());
        exitFight();
    }

    public void catchKoWildPokemon(String _ball, String _pseudo) {
        comment.clearMessages();
        String pseudo_ = getNicknameOrDefault(_pseudo);
        game.catchKoWildPokemon(_ball, pseudo_, data);
        comment.addComment(game.getPlayer().getCommentGame());
        exitFight();
    }

    public void catchWildPokemon(String _pseudo) {
        comment.clearMessages();
        String pseudo_ = getNicknameOrDefault(_pseudo);
        game.catchWildPokemon(pseudo_, data);
        comment.addComment(game.getPlayer().getCommentGame());
        exitFight();
    }

    public void exitDirectFight(boolean _enableAnimation) {
        if (!_enableAnimation) {
            exitFight();
        }
    }

    public void exitFight() {
        enabledMovingHero = !game.getFight().getFightType().isExisting();
        if (enabledMovingHero) {
            changeToFightScene = false;
            game.directInteraction(game.closestTile(getMap()), getMap());
        }
    }
    public String getNicknameOrDefault(String _pseudo) {
        String pseudo_ = _pseudo;
        if (pseudo_.isEmpty()) {
            pseudo_ = data.translatePokemon(game.getFight().wildPokemon()
                    .getName());
        }
        return pseudo_;
    }

    // %%%%end%%%% wild fight

    public String translatePokemon(String _pokemon) {
        return data.translatePokemon(_pokemon);
    }

    public String translateItem(String _item) {
        return data.translateItem(_item);
    }

    public String translateAbility(String _ability) {
        return data.translateAbility(_ability);
    }

    public String translateMove(String _move) {
        return data.translateMove(_move);
    }

    public String translateStatus(String _status) {
        return data.translateStatus(_status);
    }

    public String translateType(String _status) {
        return data.translateType(_status);
    }

    public String translatedTargets(TargetChoice _statistic) {
        return data.translatedTargets(_statistic);
    }

    public StringMap<String> getTranslatedAbilitiesCurLanguage() {
        return data.getTranslatedAbilitiesCurLanguage(language);
    }

    public AbsMap<SelectedBoolean, String> getTranslatedBooleansCurLanguage() {
        return data.getTranslatedBooleansCurLanguage(language);
    }

    public AbsMap<Gender, String> getTranslatedGendersCurLanguage() {
        return data.getTranslatedGendersCurLanguage(language);
    }

    public String translateGenders(Gender _gender) {
        return data.translateGenders(_gender);
    }

    public DataMap getMap() {
        return data.getMap();
    }

    public String getEndGameMessage() {
        StringMap<String> mess_ = data.getMessagesGame();
        return mess_.getVal(Game.END_GAME);
    }

    public DataBase getData() {
        return data;
    }

    public void setData(DataBase _data) {
        data = _data;
    }

    public void setLoadedData(boolean _loadedData) {
        loadedData = _loadedData;
    }

    public void setZipName(String _zipName) {
        zipName = _zipName;
    }

    public boolean isExistingFight() {
        return game.getFight().getFightType().isExisting();
    }

    public boolean isWildFight() {
        return game.getFight().getFightType().isWild();
    }

    public Fight getFight() {
        return game.getFight();
    }

    public int getRemainingRooms() {
        int max_ = data.getNbMaxTeam();
        int current_ = getPlayer().getTeam().size();
        return max_ - current_;
    }

    public Player getPlayer() {
        return game.getPlayer();
    }

    public boolean isShowEndGame() {
        return game.isShowEndGame();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game _game) {
        game = _game;
    }

    public Comment getComment() {
        return comment;
    }

    public int getMaxHeightPk() {
        return data.getMaxHeightPk();
    }

    public int getMaxWidthPk() {
        return data.getMaxWidthPk();
    }

    public void setMiniMapCoords(int _x, int _y) {
        miniMapCoords = new MiniMapCoords((byte) _x, (byte) _y);
    }

    public MiniMapCoords getMiniMapCoords() {
        return miniMapCoords;
    }

    // public void saveRom(String _folder) {
    // StreamBufferedImage.saveRom(_folder, data);
    // }

    public String getZipName() {
        return zipName;
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public StringMap<String> getDisplayLanguages() {
        return displayLanguages;
    }

    public void setDisplayLanguages(StringMap<String> _displayLanguages) {
        displayLanguages = _displayLanguages;
    }
}
