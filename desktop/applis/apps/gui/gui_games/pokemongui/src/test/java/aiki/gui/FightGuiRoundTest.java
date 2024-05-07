package aiki.gui;

import aiki.db.*;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.ActionType;
import aiki.instances.*;
import aiki.map.DataMap;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.*;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.maths.*;
import code.mock.MockCustComponent;
import code.mock.MockThreadFactory;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class FightGuiRoundTest extends InitDbGuiAiki {

    @Test
    public void eff1() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff2() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer2(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff3() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff4() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff5() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFast(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff6() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFastSelf(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff7() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff8() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff9() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatus(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff10() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusOther(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff11() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusSelf(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }
    private static void coreDataBaseTrainer(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainer());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    public static DataBase coreDataBaseTrainer() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = Instances.newStatusMoveData();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setImageMaxiFileName(SNOW);
        PokemonTeam team_ = Instances.newPokemonTeam();
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getTeamsRewards().add(team_);
        road_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }

    private static void coreDataBaseTrainer2(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainer2());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    public static DataBase coreDataBaseTrainer2() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setImageMaxiFileName(SNOW);
        PokemonTeam team_ = Instances.newPokemonTeam();
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        team_.getTeam().add(new PkTrainer(wild(RAICHU,1,PARATONNERRE,NULL_REF),new StringList(ECLAIR)));
        tr_.getTeamsRewards().add(team_);
        road_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    private static void coreDataBaseAttSt(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStFast(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.setEvtRate(Rate.one());
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.setEvtRate(Rate.one());
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStFastSelf(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.setEvtRate(Rate.one());
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        eone_.setTargetChoice(TargetChoice.LANCEUR);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.setEvtRate(Rate.one());
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        etwo_.setTargetChoice(TargetChoice.LANCEUR);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatus(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(NULL_REF,LgInt.one());
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        eone_.getDeletedStatus().add(NULL_REF);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(NULL_REF,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        etwo_.getDeletedStatus().add(NULL_REF);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatusOther(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatusSelf(WindowAiki _window) {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        eone_.setTargetChoice(TargetChoice.LANCEUR);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = Instances.newStatusMoveData();
        second_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.LANCEUR);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    public static DataBase coreDataBaseAttSt(MoveData _md, MoveData _sec) {
        return coreDataBaseAttSt(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseAttStatus(MoveData _md, MoveData _sec) {
        return coreDataBaseAttStatus(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttStatus(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        ab_.getAbility(PARATONNERRE).setBreakProtection(true);
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        ball_.completeQuickMembers(DESERT,Instances.newStatusSimple());
        trsSt_.addEntry(DESERT,"dessert");
        data_.getAnimStatus().addEntry(DESERT, new int[][]{new int[1]});
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static void initBeginAttSt(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
    }
    public static DataBase withMvGene(DataBase _data, String _key, StringMap<String> _trs, String _val, MoveData _d) {
        _data.completeQuickMembers(_key, _d);
        _trs.addEntry(_key,_val);
        return _data;
    }
}
