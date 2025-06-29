package aiki.game;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;


public class GameEndTest extends InitializationDataBase {

    @Test
    public void endGame1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!game_.endGame(data_));
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
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        game_.getPlayer().getBox().add(pkMoves(data_,game_.getDifficulty(),data_.getMap().getFirstPokemon()));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame9Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame10Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(!game_.endGame(data_));
    }

    @Test
    public void endGame11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.catchAll(data_);
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
        first_.setLevel( data_.getMaxLevel());
        first_.setHappiness( data_.getHappinessMax());
        assertTrue(game_.endGame(data_));
    }
}
