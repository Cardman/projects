package aiki.game.player;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import aiki.DataBase;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.util.StringList;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class PlayerValidationTest extends InitializationDataBase {

    private static final String SEX = "sex";

    Object[] sex() {
        return $($(Sex.GIRL),$(Sex.BOY));
    }

    @Test
    @Parameters(method=SEX)
    public void validate1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        assertTrue(player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getBox().add(pokemonPlayer(_data_, (short) 2));
        assertTrue(player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTeam().clear();
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTeam().clear();
        player_.getTeam().add(new Egg(StringList.concat(PIKACHU,";10")));
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate5Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTeam().add(new Egg(StringList.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate6Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTeam().add(new Egg(StringList.concat(PIKACHU,";10")));
        assertTrue(player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate7Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getBox().add(new Egg(StringList.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate8Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 2);
        pk_.setItem(INVALID_DATA_KEY);
        player_.getBox().add(pk_);
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate9Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        PokemonPlayer pk_ = pokemonPlayer(_data_, (short) 2);
        pk_.setName(INVALID_DATA_KEY);
        player_.getTeam().add(pk_);
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate10Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTm((short) -1);
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate11Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getCaughtPk().put(INVALID_DATA_KEY, false);
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate12Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getCaughtPk().clear();
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate13Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getMoney().removeNb(new LgInt("10000"));
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate14Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.setRemainingRepelSteps(-1);
        assertTrue(!player_.validate(_data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate15Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, _sex, diff_, true, _data_);
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(_data_, (short) 2));
        assertTrue(!player_.validate(_data_));
    }

    static PokemonPlayer pokemonPlayer(DataBase _data, short _level) {
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel(_level);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        return new PokemonPlayer(base_,_data);
    }
}
