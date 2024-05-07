package aiki.gui;

import aiki.db.*;
import aiki.game.Game;
import aiki.game.fight.Fight;
import aiki.game.fight.actions.ActionSwitch;
import aiki.instances.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.util.*;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.maths.*;
import code.mock.MockCustComponent;
import code.mock.MockThreadFactory;
import code.util.*;
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
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
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
}
