package aiki.game;

import aiki.db.DataBase;
import org.junit.Before;
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
import code.util.EqList;


public class GameValidationTest extends InitializationDataBase {


    private static final String ZIPPED_ROM = "my_rom.zip";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void validate1Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate2Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate3Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate4Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate5Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(2, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate6Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(2, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate7Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 1, 2), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate8Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 1, 2), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate9Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 1, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate10Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 1, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate11Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.attemptForStoringPokemonToHost((short)1, (short) 2, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(3, 0, 2, 1, 7, 4), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate12Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.attemptForStoringPokemonToHost((short)1, (short) 2, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(3, 0, 2, 1, 7, 4), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate13Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 6, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate14Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 6, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate15Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate16Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate17Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate18Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate19Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate20Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate21Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate22Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate23Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate24Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate25Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(2, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate26Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(2, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate27Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate28Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate29Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the league is not beaten, so go back to begin.
        game_.setPlayerCoords(newCoords(7, 0, 1, 1));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate30Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the league is not beaten, so go back to begin.
        game_.setPlayerCoords(newCoords(7, 0, 1, 1));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate31Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the trainer is out of map, so go back to begin.
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate32Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the trainer is out of map, so go back to begin.
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate33Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is never empty, so go back to begin.
        game_.setPlayerCoords(newCoords(6, 1, 4, 4));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate34Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is never empty, so go back to begin.
        game_.setPlayerCoords(newCoords(6, 1, 4, 4));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate35Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is empty only if the dual fight is won, but it is not true, so go back to begin.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate36Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is empty only if the dual fight is won, but it is not true, so go back to begin.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate37Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        //valid data, the tile is empty only if the dual fight is won, and it is true, keep coords.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(data));
        assertEq(newCoords(2, 0, 2, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate38Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        //valid data, the tile is empty only if the dual fight is won, and it is true, keep coords.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(data));
        assertEq(newCoords(2, 0, 2, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate39Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate40Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate41Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate42Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate43Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate44Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate45Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate46Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate47Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate48Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate49Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate50Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate51Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate52Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate53Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate54Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate55Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate56Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate57Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate58Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate59Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate60Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate61Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).first());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate62Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).first());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate63Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getVisitedPlaces().put(newCoords(1, 0, 1, 2), false);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertTrue(game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
    }
    @Test
    public void validate64Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getVisitedPlaces().put(newCoords(1, 0, 1, 2), false);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertTrue(game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
    }
    @Test
    public void validate65Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.visitPlace(newCoords(7, 0, 3, 4));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate66Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.visitPlace(newCoords(7, 0, 3, 4));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate67Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        //invalid data
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate68Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        //invalid data
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate69Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate70Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate71Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getPlayer().getTeam().clear();
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate72Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getPlayer().getTeam().clear();
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate73Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate74Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate75Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate76Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate77Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymTrainer().put((short) 0, new EqList<Point>());
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate78Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymTrainer().put((short) 0, new EqList<Point>());
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate79Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(0, 0));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate80Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(0, 0));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate81Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 1, 1), 2), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate82Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 1, 1), 2), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate83Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 0, 1), 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate84Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 0, 1), 0), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate85Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        //invalid data
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate86Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        //invalid data
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate87Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymLeader().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate88Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getBeatGymLeader().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate89Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getTakenObjects().put(newCoords(0, 0, 0, 2), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate90Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getTakenObjects().put(newCoords(0, 0, 0, 2), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate91Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getTakenPokemon().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate92Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getTakenPokemon().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate93Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        game_.getHostedPk().put(newCoords(0, 0, 0, 1), host_);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate94Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        game_.getHostedPk().put(newCoords(0, 0, 0, 1), host_);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate95Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate96Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate97Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexPeriod(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate98Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexPeriod(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate99Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexPeriodFishing(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate100Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexPeriodFishing(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate101Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexStep(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate102Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.setIndexStep(-1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate103Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data
        game_.setRankLeague((byte) 1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate104Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data
        game_.setRankLeague((byte) 1);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate105Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        //invalid data
        game_.setRankLeague((byte) 3);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate106Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        //invalid data
        game_.setRankLeague((byte) 3);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate107Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        //invalid data
        game_.setRankLeague((byte) 0);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate108Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        //invalid data
        game_.setRankLeague((byte) 0);
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate109Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.getFight().getWinningMoney().affect(new Rate("-1"));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate110Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.getFight().getWinningMoney().affect(new Rate("-1"));
        assertTrue(!game_.validate(data));
    }
    @Test
    public void validate111Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
//        game_.getPlayerCoords().affect(data.getMap().getBegin());
        game_.getPlayerCoords().affect(newCoords(0, 0, 5, 5));
//        game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate112Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
//        game_.getPlayerCoords().affect(data.getMap().getBegin());
        game_.getPlayerCoords().affect(newCoords(0, 0, 5, 5));
//        game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate113Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(7, 0, 0, 1));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate114Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(7, 0, 0, 1));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate115Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
//        game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate116Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
//        game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate117Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate118Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //invalid data
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate119Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 0, 0));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate120Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 0, 0));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate121Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 5,1,4,1));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void validate122Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 5,1,4,1));
        //game_.validate(data);
        assertTrue(game_.validate(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    @Test
    public void checkAndInitialize1Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.moving(Direction.LEFT, data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.moving(Direction.LEFT, data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.moving(Direction.DOWN, data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.moving(Direction.DOWN, data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.catchAll(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 2));
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setRankLeague((byte) 0);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPlayer_.setHappiness((short) 170);
        pkPlayer_.setLevel((short) 100);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.catchAll(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 2));
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.setRankLeague((byte) 0);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.initTrainerFight(data);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.UP, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.LEFT, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPlayer_.setHappiness((short) 170);
        pkPlayer_.setLevel((short) 100);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(data));
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
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(-1, 0, 0, 0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize12Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(0, -1, 0, 0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize13Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(0, 0, -1, 0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize14Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(0, 0, 0, 0,0,0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize15Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(1, 0, 0, 0,0,0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void checkAndInitialize16Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.setPlayerCoords(newCoords(1, 0, 1, 1,-1,0));
        assertTrue(game_.checkAndInitialize(data));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }
    @Test
    public void checkAndInitialize17Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.getPlayer().getTeam().clear();
        assertTrue(!game_.checkAndInitialize(data));
    }
    @Test
    public void checkAndInitialize18Test(){
        Game game_ = new Game(data);
        game_.initUserInteract(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //invalid
        game_.getBeatTrainer().clear();
        assertTrue(!game_.checkAndInitialize(data));
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