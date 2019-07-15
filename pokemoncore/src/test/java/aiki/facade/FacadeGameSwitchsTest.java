package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.StorageActions;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.*;

public final class FacadeGameSwitchsTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }

    @Test
    public void takeItemTeam1Test() {
        assertTrue(!facadeGame.isSelectedTeamPokemonItem());
        assertTrue(!facadeGame.isSelectedTeamPokemon());
    }

    @Test
    public void takeItemTeam2Test() {
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame.isSelectedTeamPokemonItem());
        assertTrue(facadeGame.isSelectedTeamPokemon());
    }

    @Test
    public void takeItemTeam3Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        assertTrue(!facadeGame.isSelectedTeamPokemonItem());
        assertTrue(!facadeGame.isSelectedTeamPokemon());
    }
    @Test
    public void takeItemTeam4Test() {
        PokemonPlayer first_ = (PokemonPlayer) game.getPlayer().getTeam().first();
        first_.setItem(PIERRE_LUNE);
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame.isSelectedTeamPokemonItem());
        assertTrue(facadeGame.isSelectedTeamPokemon());
    }
    @Test
    public void store1Test() {
        facadeGame.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame.isSelectedPkTeamStorage());
        assertEq(0,game.getPlayer().getBox().size());
    }
    @Test
    public void store2Test() {
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame.isSelectedPkTeamStorage());
        facadeGame.gearStorage(StorageActions.STORE);
        assertEq(1,game.getPlayer().getBox().size());
    }

    @Test
    public void store3Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        assertEq(1,game.getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk1Test() {
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame.isSelectedPkTeamStorage());
        facadeGame.searchPokemonFirstBox();
        assertTrue(!facadeGame.canTakeItemFromStorage());
        facadeGame.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(1,game.getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk2Test() {
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame.canTakeItemFromStorage());
        facadeGame.checkLinePokemonFirstBox(-1);
        assertTrue(!facadeGame.canTakeItemFromStorage());
        facadeGame.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(1,game.getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk3Test() {
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        facadeGame.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(0,game.getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk4Test() {
        facadeGame.setChosenTeamPokemon((byte)1);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        assertTrue(!facadeGame.canTakeItemFromStorage());
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(facadeGame.canTakeItemFromStorage());
        facadeGame.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(0,game.getPlayer().getBox().size());
    }
    @Test
    public void takeItemBox1Test() {
        facadeGame.setChosenTeamPokemon((byte)1);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.gearStorage(StorageActions.TAKE_ITEM_BOX);
        assertEq(LgInt.zero(),game.getPlayer().getInventory().getNumber(PIERRE_LUNE));
    }
    @Test
    public void takeItemBox2Test() {
        facadeGame.setChosenTeamPokemon((byte)1);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        facadeGame.gearStorage(StorageActions.TAKE_ITEM_BOX);
        assertEq(LgInt.one(),game.getPlayer().getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void withdrawEgg1Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(1,game.getPlayer().getBox().size());
    }

    @Test
    public void withdrawEgg2Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.checkLineEggs(-1);
        facadeGame.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(1,game.getPlayer().getBox().size());
    }
    @Test
    public void withdrawEgg3Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(0,game.getPlayer().getBox().size());
    }
    @Test
    public void switchBoxTeam1Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(!facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam2Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(!facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam3Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam4Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
        facadeGame.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam5Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(!facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam6Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.setChosenTeamPokemon((byte)1);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(!facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam7Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam8Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.setChosenTeamPokemon((byte)1);
        assertTrue(facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
        facadeGame.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam9Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.setChosenTeamPokemon((byte)1);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
    }
    @Test
    public void switchBoxTeam10Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.setChosenTeamPokemon((byte)1);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.setChosenTeamPokemon((byte)1);
        assertTrue(facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
        facadeGame.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam11Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame.isSwitchable());
        assertTrue(facadeGame.isReleasable());
        facadeGame.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam12Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame.isSwitchable());
        assertTrue(!facadeGame.isReleasable());
    }
    @Test
    public void release1Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)0);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        facadeGame.gearStorage(StorageActions.RELEASE);
        assertEq(0,game.getPlayer().getBox().size());
    }
    @Test
    public void release2Test() {
        game.getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame.setChosenTeamPokemon((byte)2);
        facadeGame.gearStorage(StorageActions.STORE);
        facadeGame.searchEgg();
        facadeGame.checkLineEggs(0);
        facadeGame.gearStorage(StorageActions.RELEASE);
        assertEq(0,game.getPlayer().getBox().size());
    }
    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }
}
