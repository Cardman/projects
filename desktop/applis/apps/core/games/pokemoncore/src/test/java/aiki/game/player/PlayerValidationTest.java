package aiki.game.player;
import static org.junit.Assert.assertTrue;

import code.util.core.StringUtil;
import org.junit.Before;
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

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void validate1Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate2Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate3Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getBox().add(pokemonPlayer(data, (short) 2));
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate4Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getBox().add(pokemonPlayer(data, (short) 2));
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate5Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTeam().clear();
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate6Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTeam().clear();
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate7Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTeam().clear();
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate8Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTeam().clear();
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate9Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTeam().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate10Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTeam().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate11Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate12Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTeam().add(new Egg(StringUtil.concat(PIKACHU,";10")));
        assertTrue(player_.validate(data));
    }
    @Test
    public void validate13Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getBox().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate14Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getBox().add(new Egg(StringUtil.concat(INVALID_DATA_KEY,";10")));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate15Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        PokemonPlayer pk_ = pokemonPlayer(data, (short) 2);
        pk_.setItem(INVALID_DATA_KEY);
        player_.getBox().add(pk_);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate16Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        PokemonPlayer pk_ = pokemonPlayer(data, (short) 2);
        pk_.setItem(INVALID_DATA_KEY);
        player_.getBox().add(pk_);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate17Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        PokemonPlayer pk_ = pokemonPlayer(data, (short) 2);
        pk_.setName(INVALID_DATA_KEY);
        player_.getTeam().add(pk_);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate18Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        PokemonPlayer pk_ = pokemonPlayer(data, (short) 2);
        pk_.setName(INVALID_DATA_KEY);
        player_.getTeam().add(pk_);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate19Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTm((short) -1);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate20Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTm((short) -1);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate21Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getCaughtPk().put(INVALID_DATA_KEY, false);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate22Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getCaughtPk().put(INVALID_DATA_KEY, false);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate23Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getCaughtPk().clear();
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate24Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getCaughtPk().clear();
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate25Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getMoney().removeNb(new LgInt("10000"));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate26Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getMoney().removeNb(new LgInt("10000"));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate27Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.setRemainingRepelSteps(-1);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate28Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.setRemainingRepelSteps(-1);
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate29Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.GIRL, diff_, true, data);
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        assertTrue(!player_.validate(data));
    }
    @Test
    public void validate30Test(){
        Difficulty diff_ = new Difficulty();
        diff_.setIvPlayer((byte) 31);
        Player player_ = new Player(NICKNAME, Sex.BOY, diff_, true, data);
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        player_.getTeam().add(pokemonPlayer(data, (short) 2));
        assertTrue(!player_.validate(data));
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