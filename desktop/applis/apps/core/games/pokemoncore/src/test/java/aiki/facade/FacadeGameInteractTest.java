package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Test;

public final class FacadeGameInteractTest extends InitializationDataBase {

    @Test
    public void interact1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.RIEN, facadeGame_.getInterfaceType());
        assertTrue(!facadeGame_.isFishArea());
        facadeGame_.exitFight();
        assertTrue(facadeGame_.isEnabledMovingHero());
    }

    @Test
    public void interact2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        assertTrue(facadeGame_.isFishArea());
        facadeGame_.interact();
        assertTrue(facadeGame_.isChangeToFightScene());
        facadeGame_.exitFight();
        assertTrue(!facadeGame_.isEnabledMovingHero());
    }

    @Test
    public void interact3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertTrue(facadeGame_.isChangeToFightScene());
    }

    @Test
    public void interact4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(InterfaceType.RIEN, facadeGame_.getGame().getInterfaceType());
    }

    @Test
    public void interact5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertTrue(facadeGame_.isChangeToFightScene());
    }

    @Test
    public void interact6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.ECH_BOITE, game_.getInterfaceType());
    }

    @Test
    public void interact7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.SOIN_PK, game_.getInterfaceType());
    }

    @Test
    public void interact8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.FOSSILE, game_.getInterfaceType());
    }

    @Test
    public void interact9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 8, 5));
        game_.setPlayerOrientation(Direction.UP);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.PENSION, game_.getInterfaceType());
    }

    @Test
    public void interact10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.ACHATS, game_.getInterfaceType());
    }

    @Test
    public void interact11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.ACHATS_CT, game_.getInterfaceType());
    }

    @Test
    public void interact12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 6));
        game_.setPlayerOrientation(Direction.RIGHT);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.MOVE_TUTORS, game_.getInterfaceType());
    }

    @Test
    public void interact13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void interact14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void interact15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(LAVA);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        assertEq(InterfaceType.FOSSILE, game_.getInterfaceType());
    }

    @Test
    public void interact16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        //newCoords(0, 0, 0, 6) is in this data invalid
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        assertTrue(!facadeGame_.isFishArea());
        facadeGame_.interact();
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void interact17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        //newCoords(0, 0, 0, 6) is in this data invalid
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.changeCamera(Direction.DOWN);
        assertTrue(!facadeGame_.isFishArea());
        facadeGame_.interact();
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq("R 1", facadeGame_.getCurrentPlace());
    }

    @Test
    public void interact18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        assertTrue(facadeGame_.isFishArea());
        facadeGame_.interactNoFish();
        assertTrue(!facadeGame_.isChangeToFightScene());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void nicknameTest() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        //newCoords(0, 0, 0, 6) is in this data invalid
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setGame(game_);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.validateNickname("NICKNAME");
        assertEq("NICKNAME", facadeGame_.getPlayer().getPokemonPlayerList().firstValue().getNickname());
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.affectInside(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
