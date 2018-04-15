package aiki.game;
import static aiki.EquallablePkUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

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

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class GameValidationTest extends InitializationDataBase {

    private static final String SEX = "sex";
    private static final String ZIPPED_ROM = "my_rom.zip";

    Object[] sex() {
        return $($(Sex.GIRL),$(Sex.BOY));
    }

    @Test
    @Parameters(method=SEX)
    public void validate1Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate2Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate3Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(2, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate4Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(1, 0, 1, 2), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate5Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(1, 0, 1, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate6Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short)1, (short) 2, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(3, 0, 2, 1, 7, 4), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate7Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 0, 6, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate8Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }
    //hostedPk

    @Test
    @Parameters(method=SEX)
    public void validate9Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate10Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate11Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate12Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(1, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate13Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(2, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate14Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
//        game_.beatGymLeader(newCoords(5, 0, 2, 0));
//        game_.beatGymLeader(newCoords(2, 0, 4, 0));
//        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate15Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the league is not beaten, so go back to begin.
        game_.setPlayerCoords(newCoords(7, 0, 1, 1));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate16Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the trainer is out of map, so go back to begin.
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate17Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is never empty, so go back to begin.
        game_.setPlayerCoords(newCoords(6, 1, 4, 4));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate18Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        //pseudo valid data, the tile is empty only if the dual fight is won, but it is not true, so go back to begin.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate19Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        //valid data, the tile is empty only if the dual fight is won, and it is true, keep coords.
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(2, 0, 2, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate20Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate21Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate22Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate23Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate24Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate25Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate26Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate27Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate28Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.initTrainerFight(_data_);
        //quick beat trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate29Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate30Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.initTrainerFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate31Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.initTrainerFight(_data_);
        //beat quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(1, 0, 5, 1, 2, 7), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).first());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate32Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getVisitedPlaces().put(newCoords(1, 0, 1, 2), false);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertTrue(game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
    }

    @Test
    @Parameters(method=SEX)
    public void validate33Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.visitPlace(newCoords(7, 0, 3, 4));
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate34Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        Pokemon pk_;
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.FEMALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(Gender.MALE);
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 5);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        //invalid data
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(8, 0, 3, 3, 0, 4));
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate35Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkPl_.getRemainingHp().affectZero();
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate36Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getPlayer().getTeam().clear();
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate37Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate38Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getVisitedPlaces().put(newCoords(0, 0, 0, 0), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate39Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getBeatGymTrainer().put((short) 0, new EqList<Point>());
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate40Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(0, 0));
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate41Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 1, 1), 2), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate42Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getBeatTrainer().put(new NbFightCoords(newCoords(0, 0, 0, 1), 0), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate43Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        //invalid data
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate44Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getBeatGymLeader().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate45Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getTakenObjects().put(newCoords(0, 0, 0, 2), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate46Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getTakenPokemon().put(newCoords(0, 0, 0, 1), false);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate47Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        HostPokemonDuo host_ = new HostPokemonDuo();
        host_.setFirstPokemon(new PokemonPlayer());
        host_.setSecondPokemon(new PokemonPlayer());
        game_.getHostedPk().put(newCoords(0, 0, 0, 1), host_);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate48Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(1);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate49Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.setIndexPeriod(-1);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate50Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.setIndexPeriodFishing(-1);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate51Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.setIndexStep(-1);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate52Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data
        game_.setRankLeague((byte) 1);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate53Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        //invalid data
        game_.setRankLeague((byte) 3);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate54Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        //invalid data
        game_.setRankLeague((byte) 0);
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate55Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //invalid data
        game_.getFight().getWinningMoney().affect(new Rate("-1"));
        assertTrue(!game_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate56Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //invalid data
//        game_.getPlayerCoords().affect(data.getMap().getBegin());
        game_.getPlayerCoords().affect(newCoords(0, 0, 5, 5));
//        game_.validate(data);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate57Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //invalid data
        game_.setPlayerCoords(newCoords(7, 0, 0, 1));
        //game_.validate(data);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate58Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //invalid data
        game_.setPlayerCoords(newCoords(0, 0, 6, 6));
//        game_.validate(data);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate59Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //invalid data
        game_.setPlayerCoords(newCoords(2, 0, 2, 0));
        //game_.validate(data);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void validate60Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        //invalid data because of inaccessibility of block
        game_.setPlayerCoords(newCoords(1, 0, 0, 0));
        //game_.validate(data);
        assertTrue(game_.validate(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
    }

    @Test
    @Parameters(method=SEX)
    public void checkAndInitialize1Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        assertTrue(game_.checkAndInitialize(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(NULL_REF, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }

    @Test
    @Parameters(method=SEX)
    public void checkAndInitialize2Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }

    @Test
    @Parameters(method=SEX)
    public void checkAndInitialize3Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(_data_));
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }

    @Test
    @Parameters(method=SEX)
    public void checkAndInitialize4Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(_data_));
        assertEq(newCoords(0, 0, 1, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(!game_.isShowEndGame());
    }

    @Test
    @Parameters(method=SEX)
    public void checkAndInitialize5Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUserInteract(NICKNAME, _sex, game_.getDifficulty(), _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.catchAll(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 2));
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 2));
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.setRankLeague((byte) 0);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.initTrainerFight(_data_);
        //beat very quickly a trainer
        game_.getFight().getKos().put(Fight.FOE, true);
        game_.endFight(_data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.UP, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.LEFT, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPlayer_.setHappiness((short) 170);
        pkPlayer_.setLevel((short) 100);
        game_.setZippedRom(ZIPPED_ROM);
        assertTrue(game_.checkAndInitialize(_data_));
        assertEq(newCoords(8, 0, 3, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getRankLeague());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(2, game_.getBeatGymTrainer().getVal((short) 3).size());
        assertEq(ZIPPED_ROM, game_.getZippedRom());
        assertTrue(game_.isShowEndGame());
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
    }
}
