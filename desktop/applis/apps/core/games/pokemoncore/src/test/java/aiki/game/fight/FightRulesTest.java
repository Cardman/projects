package aiki.game.fight;

import aiki.db.DataBase;
import aiki.game.player.enums.Sex;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.util.AffectedMove;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public class FightRulesTest extends InitializationDataBase {

    @Test
    public void substitutable1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable2(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable2(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte)0);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte)0);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, (byte) 2);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, Fighter.BACK);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 3, Fighter.BACK);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, (byte)0);
        fight_.getFirstPositPlayerFighters().put((byte)1, Fighter.BACK);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable4(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable4(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable4(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable4(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte)0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte)2, (byte)0);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable5(data_, diff_, player_, new StringList(INTERVERSION), new StringList(INTERVERSION), new StringList(DETECTION));
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(thrower_).setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getFighter(thrower_).isSuccessfulMove());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable5(data_, diff_, player_, new StringList(INTERVERSION), new StringList(INTERVERSION), new StringList(DETECTION));
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(thrower_).setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerMove(fight_, thrower_, diff_, data_);
        assertTrue(fight_.getFighter(thrower_).isSuccessfulMove());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 0, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte)1);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable6(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable7(data_, diff_, player_, new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        FightRound.initRound(fight_);
        FightRound.roundThrowerHealing(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        Fighter fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1,(byte) 1);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable1(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DETECTION));
        FightRound.initRound(fight_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        fight_.getFirstPositPlayerFighters().put((byte) 1,(byte) 1);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void substitutable26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = substitutable1(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DETECTION));
        FightRound.initRound(fight_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(!FightRules.substitutable(fight_, diff_, data_));
    }

    @Test
    public void allowedMoves1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        StringList moves_ = FightRules.allowedMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.setNeedingToRecharge(true);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.setNbPrepaRound((short) 1);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,f_)).setMove(PISTOLET_A_O);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,f_)).getActivity().enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,f_)).setMove(PISTOLET_A_O);
        fighter_.getTrackingMoves().getVal(new MoveTeamPosition(ENTRAVE,f_)).getActivity().enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON,f_)).enable();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition foe_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foe_);
        fighter_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,f_)).add(ECUME);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(ROULADE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(ROULADE, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, ROULADE));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(ROULADE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(ROULADE, POKEMON_FOE_TARGET_ZERO);
        fighter_.setLastUsedMove();
        fighter_.activerAttaqueBlocantLanceur(ROULADE);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, ROULADE));
    }

    @Test
    public void allowedMoves12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(SOIN, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(EMBARGO);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMoves14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(TOURMENTE);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
    }

    @Test
    public void allowedMoves15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.activerAttaque(PROVOC);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(3, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
    }

    @Test
    public void allowedMoves16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(VOL_MAGNETIK, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        fight_.enableGlobalMove(GRAVITE);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(PRESCIENCE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, PRESCIENCE));
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PRESCIENCE, POKEMON_FOE_TARGET_ZERO);
        FightRound.roundAllThrowers(fight_, diff_, player_, data_);
        assertTrue(fighter_.isSuccessfulMove());
        assertEq(FightState.ATTAQUES, fight_.getState());
        moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
    }

    @Test
    public void allowedMoves19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(2, moves_.size());
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, DANSE_LUNE));
    }

    @Test
    public void allowedMoves20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void allowedMoves21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        fight_.getUserTeam().activerEffetEquipe(TOUR_RAPIDE);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(f_).activerAttaque(CYCLE_V);
        AffectedMove attaqueViseeActif_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).refPartAttaquesSurCombatAtt(new MoveTeamPosition(CHANT,f_));
        attaqueViseeActif_.getActivity().enable();
        attaqueViseeActif_.setMove(CHARGE);
        //fight_.getFighter(f_).a
        StringList moves_ = FightRules.allowedMoves(fight_, f_, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void playable1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(ECUME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SEISME, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(EAU_FRAICHE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(TOTAL_SOIN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(TOTAL_SOIN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(RAPPEL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(RAPPEL, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(MAX_ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(ELIXIR, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE_MAX, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable17Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ENIGMA);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ENIGMA, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable18Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_GOWAV);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_GOWAV, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable19Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_MEPO);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(BAIE_MEPO, SEISME);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable20Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(PARALYSIE);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable21Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_PITAYE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_PITAYE, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable22Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fighter_.setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable23Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable24Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable25Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable26Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable27Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable28Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable29Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable30Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BERCEUSE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BERCEUSE, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable31Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable32Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BATAILLE, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(BATAILLE, POKEMON_FOE_TARGET_TWO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable33Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable34Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable35Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 1);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable36Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable37Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(EAU_FRAICHE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(EAU_FRAICHE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable38Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(TOTAL_SOIN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(TOTAL_SOIN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable39Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(REVEIL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(REVEIL, data_);
        fighter_.affecterStatut(PARALYSIE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable40Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(RAPPEL);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(RAPPEL, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable41Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(MAX_ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable42Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(ELIXIR);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObjectMove(ELIXIR, SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable43Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE, DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable44Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObjectMove(HUILE_MAX, DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable45Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(HUILE, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable46Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(HUILE_MAX);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.usePowerPointsByMove(diff_, SEISME, (short) 1);
        fighter_.setChosenHealingObject(HUILE_MAX, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable47Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable48Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ENIGMA);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_ENIGMA, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable49Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_GOWAV);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_GOWAV, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable50Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_MEPO);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObjectMove(BAIE_MEPO, SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable51Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable52Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable53Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_PITAYE);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setChosenHealingObject(BAIE_PITAYE, data_);
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable54Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable55Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMove(SEISME);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable56Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_ORAN);
        player_.getItem(BAIE_ORAN);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable57Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(ECUME, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(f_).setFirstChosenMoveTarget(LUTTE, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable58Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 4);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable59Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ZERO);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable60Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable62Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_TWO);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable63Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(SIPHON, POKEMON_FOE_TARGET_THREE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable64Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        f_ = POKEMON_PLAYER_FIGHTER_ONE;
        fighter_ = fight_.getFighter(f_);
        fighter_.setSubstitute((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable65Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(BAIE_CERIZ, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable66Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(PETIT_RAPPEL, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable67Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setChosenHealingObject(NULL_REF, data_);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable68Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setFirstChosenMove(SEISME);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable69Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setSubstitute((byte) 0);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable70Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.affecterStatut(SOMMEIL);
        fighter_.setSubstitute(Fighter.BACK);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable71Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending3(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(f_);
        fighter_.setFirstChosenMoveTarget(INTERVERSION, POKEMON_PLAYER_TARGET_ONE);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    /*@Test
    public void playable72Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        Map<String,Short> map_ = new Map<String,Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(DETECTION,CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = playable(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }*/

    @Test
    public void playable73Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable74Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable75Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable76Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 3);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable77Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(DANSE_LUNE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable78Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable79Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable80Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 2);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable81Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending1(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 3);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    @Test
    public void playable82Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.getItem(BAIE_CERIZ);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = rulesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(RELAIS);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(!FightRules.playable(fight_, player_, diff_, data_));
    }

    private Fight substitutable7(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return substitutableGym(_player, _diff, _data, foeTeam_, 4);
    }

    private Fight substitutable6(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return substitutableGym(_player, _diff, _data, foeTeam_, 4);
    }

    private Fight substitutable5(DataBase _data, Difficulty _diff, Player _player, StringList _firstMoves, StringList _secondMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _firstMoves));
        allyTeam_.add(pkTrainer((short)3, _secondMoves));
        return substitutableDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight substitutable4(DataBase _data, Difficulty _diff, Player _player, StringList _moves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _moves));
        return substitutableDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight substitutable3(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return substitutableGym(_player, _diff, _data, foeTeam_, 2);
    }

    private Fight substitutable2(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return substitutableGym(_player, _diff, _data, foeTeam_);
    }

    private Fight substitutable1(DataBase _data, Difficulty _diff, Player _player, StringList _moves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _moves));
        return substitutableDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight rulesSending7(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = substitutableGym(_player, _diff, _data, foeTeam_, 3);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight rulesSending6(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _mult) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = substitutableGym(_player, _diff, _data, foeTeam_, _mult);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight rulesSending5(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = substitutableGym(_player, _diff, _data, foeTeam_, 4);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight rulesSending3(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = substitutableGym(_player, _diff, _data, foeTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight rulesSending1(DataBase _data, Difficulty _diff, Player _player, StringList _firstMoves, StringList _secondMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _firstMoves));
        allyTeam_.add(pkTrainer((short)3, _secondMoves));
        Fight fight_ = substitutableDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight rulesSending2(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = substitutableDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private static Fight firstEffectWhileSendingTeams(Difficulty _diff, DataBase _data, Fight _fight) {
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _data);
        return _fight;
    }

    private static Fight substitutableGym(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _user, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight substitutableGym(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, int _mult) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setMultiplicityFight((byte) _mult);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _user, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight substitutableDual(Player _user, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, CustList<PkTrainer> _allyTeam) {
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        ally_.setTeam(_allyTeam);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(_foeTeam);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _user, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static PkTrainer pkTrainer(short _level, StringList _moves) {
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        foePokemon_.setMoves(_moves);
        return foePokemon_;
    }

}
