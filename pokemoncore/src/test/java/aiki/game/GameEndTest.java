package aiki.game;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;


public class GameEndTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void endGame1Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame2Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame3Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!game_.endGame(data));
    }

//    @Test
//    public void endGame4Test() {
//        Game game_ = new Game(data);
//        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
//        game_.getDifficulty().setRandomWildFight(true);
//        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
//        game_.setPlayerOrientation(Direction.RIGHT);
//        game_.catchAll(data);
//        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(6, 0, 4, 8));
//        assertTrue(game_.endGame(data));
//    }

    @Test
    public void endGame4Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        game_.getPlayer().recupererOeufPensions(new Egg(PIKACHU));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame5Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        game_.getPlayer().getBox().add(new Egg(PIKACHU));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame6Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        game_.getPlayer().getBox().add(new PokemonPlayer(data.getMap().getFirstPokemon(), data));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame7Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame8Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame9Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame10Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(!game_.endGame(data));
    }

    @Test
    public void endGame11Test() {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data.getMaxLevel());
        first_.setHappiness((short) data.getHappinessMax());
        assertTrue(game_.endGame(data));
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
