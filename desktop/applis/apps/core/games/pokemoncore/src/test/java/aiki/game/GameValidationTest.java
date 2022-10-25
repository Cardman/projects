package aiki.game;

import aiki.db.DataBase;
import aiki.facade.SexListImpl;
import aiki.util.PointEqList;
import code.util.core.BoolVal;
import org.junit.Test;

import aiki.game.enums.InterfaceType;
import aiki.game.fight.Fight;
import aiki.game.fight.InitializationDataBase;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.Rate;


public class GameValidationTest extends InitializationDataBase {


    private static final String ZIPPED_ROM = "my_rom.zip";

    @Test
    public void validate1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate3Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate4Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate5Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(2, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate6Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(2, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate7Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 1, 2), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate8Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 1, 2), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate9Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 1, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate10Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 1, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate11Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short)1, (short) 2, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(3, 0, 2, 1, 7, 4), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate12Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short)1, (short) 2, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(3, 0, 2, 1, 7, 4), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate13Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 6, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate14Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 6, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate15Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate16Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate17Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate18Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate19Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate20Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate21Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate22Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate23Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate24Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate25Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(2, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate26Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(2, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate27Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate28Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate29Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the league is not beaten, so go back to begin.
        game_.setPlayerCoords(newCoords(7, 0, 1, 1));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate30Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the league is not beaten, so go back to begin.
        game_.setPlayerCoords(newCoords(7, 0, 1, 1));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate31Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the trainer is out of map, so go back to begin.
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate32Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the trainer is out of map, so go back to begin.
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate33Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is never empty, so go back to begin.
        game_.setPlayerCoords(newCoords(6, 1, 4, 4));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate34Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is never empty, so go back to begin.
        game_.setPlayerCoords(newCoords(6, 1, 4, 4));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate35Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is empty only if the dual fight is won, but it is not true, so go back to begin.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate36Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is empty only if the dual fight is won, but it is not true, so go back to begin.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate37Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        //valid data, the tile is empty only if the dual fight is won, and it is true, keep coords.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(2, 0, 2, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate38Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        //valid data, the tile is empty only if the dual fight is won, and it is true, keep coords.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(2, 0, 2, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate39Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate40Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate41Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate42Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate43Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate44Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate45Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate46Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate47Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate48Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate49Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate50Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate51Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate52Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate53Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate54Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate55Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate56Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //quick beat trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate57Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate58Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate59Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate60Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate61Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).first());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate62Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).first());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate63Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getVisitedPlaces().put(newCoords(1, 0, 1, 2), BoolVal.FALSE);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertSame(BoolVal.TRUE,game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
    }
    @Test
    public void validate64Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getVisitedPlaces().put(newCoords(1, 0, 1, 2), BoolVal.FALSE);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertSame(BoolVal.TRUE,game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
    }
    @Test
    public void validate65Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.visitPlace(newCoords(7, 0, 3, 4));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate66Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.visitPlace(newCoords(7, 0, 3, 4));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate67Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        //invalid data
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate68Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        //invalid data
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate69Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate70Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate71Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getPlayer().getTeam().clear();
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate72Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getPlayer().getTeam().clear();
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate73Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate74Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate75Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate76Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate77Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymTrainer().put((short) 0, new PointEqList());
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate78Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymTrainer().put((short) 0, new PointEqList());
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate79Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(0, 0));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate80Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(0, 0));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate81Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 1, 1), 2), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate82Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 1, 1), 2), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate83Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 0, 1), 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate84Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 0, 1), 0), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate85Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        //invalid data
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate86Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        //invalid data
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate87Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymLeader().put(newCoords(0, 0, 0, 1), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate88Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getBeatGymLeader().put(newCoords(0, 0, 0, 1), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate89Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getTakenObjects().put(newCoords(0, 0, 0, 2), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate90Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getTakenObjects().put(newCoords(0, 0, 0, 2), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate91Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getTakenPokemon().put(newCoords(0, 0, 0, 1), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate92Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getTakenPokemon().put(newCoords(0, 0, 0, 1), BoolVal.FALSE);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate93Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        game_.getHostedPk().put(newCoords(0, 0, 0, 1), host_);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate94Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        game_.getHostedPk().put(newCoords(0, 0, 0, 1), host_);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate95Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate96Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate97Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexPeriod(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate98Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexPeriod(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate99Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexPeriodFishing(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate100Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexPeriodFishing(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate101Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexStep(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate102Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.setIndexStep(-1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate103Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data
        game_.setRankLeague((byte) 1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate104Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data
        game_.setRankLeague((byte) 1);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate105Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        //invalid data
        game_.setRankLeague((byte) 3);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate106Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        //invalid data
        game_.setRankLeague((byte) 3);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate107Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        //invalid data
        game_.setRankLeague((byte) 0);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate108Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        //invalid data
        game_.setRankLeague((byte) 0);
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate109Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.getFight().getWinningMoney().affect(new Rate("-1"));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate110Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.getFight().getWinningMoney().affect(new Rate("-1"));
        assertTrue(!validate(game_, data_));
    }
    @Test
    public void validate111Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
//        game_.getPlayerCoords().affect(data.getMap().getBegin());
        game_.getPlayerCoords().affect(newCoords(0, 0, 5, 5));
//        game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate112Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
//        game_.getPlayerCoords().affect(data.getMap().getBegin());
        game_.getPlayerCoords().affect(newCoords(0, 0, 5, 5));
//        game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate113Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(7, 0, 0, 1));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate114Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(7, 0, 0, 1));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate115Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
//        game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate116Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
//        game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate117Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate118Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //invalid data
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate119Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 0, 0));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate120Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 0, 0));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate121Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 5,1,4,1));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate122Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 5,1,4,1));
        //game_.validate(data);
        assertTrue(validate(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void checkAndInitialize1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(NULL_REF, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(NULL_REF, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize3Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.moving(Direction.LEFT, data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize4Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.moving(Direction.LEFT, data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize5Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.moving(Direction.DOWN, data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize6Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.moving(Direction.DOWN, data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize7Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize8Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize9Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.catchAll(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 2));
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setRankLeague((byte) 0);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPlayer_.setHappiness((short) 170);
        pkPlayer_.setLevel((short) 100);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(8, 0, 3, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(game_.isShowEndGame());
    }
    @Test
    public void checkAndInitialize10Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.catchAll(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 2));
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setRankLeague((byte) 0);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.initTrainerFight(data_);
        //beat very quickly a trainer
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.UP, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.LEFT, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPlayer_.setHappiness((short) 170);
        pkPlayer_.setLevel((short) 100);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(8, 0, 3, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(game_.isShowEndGame());
    }

    @Test
    public void checkAndInitialize11Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(-1, 0, 0, 0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize12Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(0, -1, 0, 0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize13Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(0, 0, -1, 0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize14Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(0, 0, 0, 0,0,0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize15Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(1, 0, 0, 0,0,0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize16Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.setPlayerCoords(newCoords(1, 0, 1, 1,-1,0));
        assertTrue(checkAndInitialize(game_, data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }
    @Test
    public void checkAndInitialize17Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.getPlayer().getTeam().clear();
        assertTrue(!checkAndInitialize(game_, data_));
    }
    @Test
    public void checkAndInitialize18Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
        //invalid
        game_.getBeatTrainer().clear();
        assertTrue(!checkAndInitialize(game_, data_));
    }

    private boolean checkAndInitialize(Game _game, DataBase _data) {
        return _game.checkAndInitialize(_data,new SexListImpl());
    }

    private boolean validate(Game _game, DataBase _data) {
        return _game.validate(_data,new SexListImpl());
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }}