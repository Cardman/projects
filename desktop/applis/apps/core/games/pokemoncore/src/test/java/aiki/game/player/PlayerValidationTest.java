package aiki.game.player;

import code.util.core.StringUtil;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;


public class PlayerValidationTest extends InitializationDataBase {

    @Test
    public void validate1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getBox().add(pokemonPlayer(data_, (short) 2));
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getBox().add(pokemonPlayer(data_, (short) 2));
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTeam().clear();
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTeam().clear();
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTeam().clear();
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTeam().clear();
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate9Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTeam().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate10Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTeam().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate11Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate12Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(player_.validate(data_));
    }
    @Test
    public void validate13Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getBox().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate14Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getBox().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate15Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 2);
        pk_.setItem(INVALID_DATA_KEY);
        player_.getBox().add(pk_);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate16Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 2);
        pk_.setItem(INVALID_DATA_KEY);
        player_.getBox().add(pk_);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate17Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 2);
        pk_.setName(INVALID_DATA_KEY);
        player_.getTeam().add(pk_);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate18Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        PokemonPlayer pk_ = pokemonPlayer(data_, (short) 2);
        pk_.setName(INVALID_DATA_KEY);
        player_.getTeam().add(pk_);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate19Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTm((short) -1);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate20Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTm((short) -1);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate21Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getCaughtPk().put(INVALID_DATA_KEY, false);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate22Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getCaughtPk().put(INVALID_DATA_KEY, false);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate23Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getCaughtPk().clear();
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate24Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getCaughtPk().clear();
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate25Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getMoney().removeNb(new LgInt("10000"));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate26Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getMoney().removeNb(new LgInt("10000"));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate27Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.setRemainingRepelSteps(-1);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate28Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.setRemainingRepelSteps(-1);
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate29Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data_);
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        assertTrue(!player_.validate(data_));
    }
    @Test
    public void validate30Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data_);
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        player_.getTeam().add(pokemonPlayer(data_, (short) 2));
        assertTrue(!player_.validate(data_));
    }


    static PokemonPlayer pokemonPlayer(DataBase _data, short _level) {
        Pokemon base_ = new WildPk();
        base_.setName(PIKACHU);
        base_.setLevel(_level);
        base_.setAbility(STATIK);
        base_.setItem(NULL_REF);
        base_.setGender(Gender.NO_GENDER);
        return new PokemonPlayer(base_,_data);
    }}