package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.StorageActions;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import org.junit.Test;

public final class FacadeGameSwitchsTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }

    @Test
    public void takeItemTeam1Test() {
        FacadeGame facadeGame_ = initTests();
        assertTrue(!facadeGame_.isSelectedTeamPokemonItem());
        assertTrue(!facadeGame_.isSelectedTeamPokemon());
    }

    @Test
    public void takeItemTeam2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame_.isSelectedTeamPokemonItem());
        assertTrue(facadeGame_.isSelectedTeamPokemon());
    }

    @Test
    public void takeItemTeam3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        assertTrue(!facadeGame_.isSelectedTeamPokemonItem());
        assertTrue(!facadeGame_.isSelectedTeamPokemon());
    }
    @Test
    public void takeItemTeam4Test() {
        FacadeGame facadeGame_ = initTests();
        PokemonPlayer first_ = (PokemonPlayer) facadeGame_.getGame().getPlayer().getTeam().first();
        first_.setItem(PIERRE_LUNE);
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame_.isSelectedTeamPokemonItem());
        assertTrue(facadeGame_.isSelectedTeamPokemon());
    }
    @Test
    public void store1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame_.isSelectedPkTeamStorage());
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void store2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame_.isSelectedPkTeamStorage());
        facadeGame_.gearStorage(StorageActions.STORE);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }

    @Test
    public void store3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame_.isSelectedPkTeamStorage());
        facadeGame_.searchPokemonFirstBox();
        assertTrue(!facadeGame_.canTakeItemFromStorage());
        facadeGame_.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.canTakeItemFromStorage());
        facadeGame_.checkLinePokemonFirstBox(-1);
        assertTrue(!facadeGame_.canTakeItemFromStorage());
        facadeGame_.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        facadeGame_.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void withdrawPk4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)1);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        assertTrue(!facadeGame_.canTakeItemFromStorage());
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(facadeGame_.canTakeItemFromStorage());
        facadeGame_.gearStorage(StorageActions.WIDRAW_PK);
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void takeItemBox1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)1);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.gearStorage(StorageActions.TAKE_ITEM_BOX);
        assertEq(LgInt.zero(),facadeGame_.getGame().getPlayer().getInventory().getNumber(PIERRE_LUNE));
    }
    @Test
    public void takeItemBox2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChosenTeamPokemon((byte)1);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        facadeGame_.gearStorage(StorageActions.TAKE_ITEM_BOX);
        assertEq(LgInt.one(),facadeGame_.getGame().getPlayer().getInventory().getNumber(PIERRE_LUNE));
    }

    @Test
    public void withdrawEgg1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }

    @Test
    public void withdrawEgg2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.checkLineEggs(-1);
        facadeGame_.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(1,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void withdrawEgg3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.gearStorage(StorageActions.WIDRAW_EGG);
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void switchBoxTeam1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(!facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(!facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
        facadeGame_.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame_.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(!facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam6Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.setChosenTeamPokemon((byte)1);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(!facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam7Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam8Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.setChosenTeamPokemon((byte)1);
        assertTrue(facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
        facadeGame_.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame_.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam9Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.setChosenTeamPokemon((byte)1);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
    }
    @Test
    public void switchBoxTeam10Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.setChosenTeamPokemon((byte)1);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.setChosenTeamPokemon((byte)1);
        assertTrue(facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
        facadeGame_.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame_.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam11Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.setChosenTeamPokemon((byte)0);
        assertTrue(facadeGame_.isSwitchable());
        assertTrue(facadeGame_.isReleasable());
        facadeGame_.gearStorage(StorageActions.SWITCH_TEAM_BOX);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(1, facadeGame_.getPlayer().getEggsList().size());
    }
    @Test
    public void switchBoxTeam12Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.isSwitchable());
        assertTrue(!facadeGame_.isReleasable());
    }
    @Test
    public void release1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)0);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        facadeGame_.gearStorage(StorageActions.RELEASE);
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
    }
    @Test
    public void release2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        facadeGame_.setChosenTeamPokemon((byte)2);
        facadeGame_.gearStorage(StorageActions.STORE);
        facadeGame_.searchEgg();
        facadeGame_.checkLineEggs(0);
        facadeGame_.gearStorage(StorageActions.RELEASE);
        assertEq(0,facadeGame_.getGame().getPlayer().getBox().size());
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
