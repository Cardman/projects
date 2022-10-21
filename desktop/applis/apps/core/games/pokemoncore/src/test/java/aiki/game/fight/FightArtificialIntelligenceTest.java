package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.util.*;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.util.MoveTarget;
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


public class FightArtificialIntelligenceTest extends InitializationDataBase {

    @Test
    public void choiceForSubstituing1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(1, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1));
    }

    @Test
    public void choiceForSubstituing2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing2(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(1, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
    }

    @Test
    public void choiceForSubstituing3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing3(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(2, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 3));
    }

    @Test
    public void choiceForSubstituing4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing4(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
    }

    @Test
    public void choiceForSubstituing5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing5(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 4));
    }

    @Test
    public void choiceForSubstituing6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing6(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void choiceForSubstituing7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
    }

    @Test
    public void choiceForSubstituing8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(4, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
    }

    @Test
    public void choiceForSubstituing9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
    }

    @Test
    public void choiceForSubstituing10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing8(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
    }

    @Test
    public void choiceForSubstituing11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing9(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(2, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 2));
    }

    @Test
    public void choiceForSubstituing12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing10(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).fullHeal(data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
    }


    @Test
    public void choiceForSubstituing13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing10(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).fullHeal(data_);
        fight_.getFirstPositFoeFighters().put((byte) 1, Fighter.BACK);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositFoeFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositFoeFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositFoeFighters().getVal((byte) 2));
    }

    @Test
    public void choiceForSubstituing14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).exitFrontBattleForBeingSubstitued();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).fullHeal(data_);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 4));
    }

    @Test
    public void choiceForSubstituing15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = choiceForSubstituing6(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 2, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        //Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        //assertEq(3, fighter_.getSubstistute());
        //fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE);
        //assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setFirstChosenMoveAlly1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DETECTION));
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, POKEMON_PLAYER_FIGHTER_TWO, INTERVERSION, POKEMON_PLAYER_TARGET_ZERO, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMoveAlly2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(SEISME), new StringList(DETECTION));
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, POKEMON_PLAYER_FIGHTER_TWO, SEISME, POKEMON_FOE_TARGET_ZERO, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DETECTION));
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, INTERVERSION, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, INTERVERSION, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(SEISME), new StringList(DETECTION));
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, SEISME, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, SEISME, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(BERCEUSE), new StringList(DETECTION));
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, BERCEUSE, POKEMON_FOE_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, BERCEUSE, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(RELAIS), new StringList(DETECTION), 3, 3, 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_THREE, diff_, data_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, RELAIS, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, RELAIS, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(4, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_FOUR, diff_, data_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_TWO;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, DANSE_LUNE, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, DANSE_LUNE, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(3, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION));
        //FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_FOUR, diff_, data_);
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, DANSE_LUNE, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, DANSE_LUNE, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPassAlly7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(RELAIS), new StringList(DETECTION));
        TeamPosition ally_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightArtificialIntelligence.setFirstChosenMoveAlly(fight_, ally_, RELAIS, POKEMON_PLAYER_TARGET_ZERO, data_);
        FightArtificialIntelligence.setBatonPassAlly(fight_, ally_, RELAIS, data_);
        Fighter fighter_ = fight_.getFighter(ally_);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void reachable1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SACRIFICE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(reachable(data_, diff_, fight_, thrower_, target_, SACRIFICE));
    }

    @Test
    public void reachable2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(reachable(data_, diff_, fight_, thrower_, target_, SIPHON));
    }

    @Test
    public void reachable3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(reachable(data_, diff_, fight_, thrower_, target_, SEISME));
    }

    @Test
    public void reachable4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_TWO;
        assertTrue(!reachable(data_, diff_, fight_, thrower_, target_, SIPHON));
    }

    @Test
    public void reachable6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COUPE_VENT, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_TWO;
        assertTrue(!reachable(data_, diff_, fight_, thrower_, target_, COUPE_VENT));
    }

    @Test
    public void remainingFoeTargetHp1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(Rate.zero(), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    @Test
    public void remainingFoeTargetHp2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_VOLT);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("92/5"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    @Test
    public void remainingFoeTargetHp3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_VOLT);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, TONNERRE, diff_, data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("375506/8375"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("52"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, data_);
        assertEq(3, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_TWO));
    }

    @Test
    public void remainingFoeTargetHp7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove7(data_, diff_, player_, new StringList(DETECTION));
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(thrower_).variationBoostStatistique(Statistic.ACCURACY, (byte) 6);
        TargetCoordssRate damages_;
        damages_ = FightArtificialIntelligence.remainingFoeTargetHp(fight_, thrower_, SIPHON, diff_, data_);
        assertEq(2, damages_.size());
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ZERO));
        assertEq(new Rate("7768603/160000"), damages_.getVal(POKEMON_FOE_TARGET_ONE));
    }

    @Test
    public void untouchablePartners1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SACRIFICE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, SACRIFICE);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, TONNERRE);
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, SIPHON);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COUPE_VENT, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, COUPE_VENT);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(BROUHAHA, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, BROUHAHA);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PICORE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, PICORE);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void untouchablePartners7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PICORE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(DETECTION), 15);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, PICORE);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(thrower_));
    }

    @Test
    public void untouchablePartners8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).affecterTypes(SOL);
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, TONNERRE);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void untouchablePartners9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        TeamPositionList list_ = untouchablePartners(data_, diff_, fight_, thrower_, TONNERRE);
        assertEq(5, list_.size());
        assertTrue(list_.containsObj(thrower_));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void remainingPartnerTargetHp1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionsPairRates damages_;
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, SIPHON, diff_, data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getBack());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getBack());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getBack());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getBack());
    }

    @Test
    public void remainingPartnerTargetHp2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionsPairRates damages_;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, TONNERRE, diff_, data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getBack());
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getBack());
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getBack());
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getBack());
    }

    @Test
    public void remainingPartnerTargetHp3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(TONNERRE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 3, 18, 18, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionsPairRates damages_;
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setCurrentAbility(ABSORB_VOLT);
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, TONNERRE, diff_, data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getFront());
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getFront());
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getBack());
        assertEq(new Rate("0"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        assertEq(new Rate("1933/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getBack());
        assertEq(new Rate("2586941/111950"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getBack());
        assertEq(new Rate("2586941/111950"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getBack());
    }

    @Test
    public void remainingPartnerTargetHp4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(ABIME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 1, 18, 17, 1, 4);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionsPairRates damages_;
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, ABIME, diff_, data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("1311/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getFront());
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getFront());
        assertEq(new Rate("6287/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getBack());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        assertEq(new Rate("1311/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getBack());
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getBack());
        assertEq(new Rate("6287/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getBack());
    }

    @Test
    public void remainingPartnerTargetHp5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(ABIME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DANSE_LUNE), new StringList(DETECTION), 1, 18, 17, 1, 4);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPositionsPairRates damages_;
        damages_ = FightArtificialIntelligence.remainingPartnerTargetHp(fight_, thrower_, SEISME, diff_, data_);
        assertEq(5, damages_.size());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(Rate.zero(),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getFront());
        assertEq(new Rate("3299/50"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getFront());
        assertEq(new Rate("6287/100"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFront());
        assertEq(new Rate("4587/100"),damages_.getVal(thrower_).getBack());
        assertEq(new Rate("122739/21700"),damages_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        assertEq(Rate.zero(),damages_.getVal(POKEMON_PLAYER_FIGHTER_TWO).getBack());
        assertEq(new Rate("4986701/111950"),damages_.getVal(POKEMON_PLAYER_FIGHTER_THREE).getBack());
        assertEq(new Rate("173381/4300"),damages_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getBack());
    }

    @Test
    public void usableMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, SIPHON, diff_, data_));
    }

    @Test
    public void usableMove2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.usableMove(fight_, thrower_, target_, false, SIPHON, diff_, data_));
    }

    @Test
    public void usableMove3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(target_).affecterTypes(SOL);
        assertTrue(FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, TONNERRE, diff_, data_));
    }

    @Test
    public void usableMove4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 3, 3, 3, 3, 3);
        TeamPosition target_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        assertTrue(!FightArtificialIntelligence.usableMove(fight_, thrower_, target_, true, TONNERRE, diff_, data_));
    }

    @Test
    public void koFoeFighter1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, data_));
    }

    @Test
    public void koFoeFighter2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 12, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, data_));
    }

    @Test
    public void koFoeFighter3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).variationBoostStatistique(Statistic.EVASINESS, (byte) 1);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, data_));
    }

    @Test
    public void koFoeFighter4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).setCurrentAbility(ABSORB_VOLT);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, data_));
    }

    @Test
    public void koFoeFighter5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 3, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        TeamPosition target_ = POKEMON_FOE_FIGHTER_ZERO;
        fight_.getFighter(target_).affecterTypes(SOL);
        assertTrue(!FightArtificialIntelligence.koFoeFighter(fight_, thrower_, TONNERRE, target_, diff_, data_));
    }

    @Test
    public void koFoeFighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 17, 3);
        TeamPosition thrower_ = POKEMON_PLAYER_FIGHTER_TWO;
        StringMap<TargetCoordsList> mapTargets_;
        mapTargets_ = new StringMap<TargetCoordsList>();
        mapTargets_.put(TONNERRE, TargetCoordsList.newList(POKEMON_PLAYER_TARGET_ZERO,POKEMON_PLAYER_TARGET_ONE));
        TargetCoordsList kos_ = FightArtificialIntelligence.koFoeFighters(fight_, thrower_, TONNERRE, mapTargets_, diff_, data_);
        assertEq(1, kos_.size());
        assertTrue(kos_.containsObj(POKEMON_PLAYER_TARGET_ONE));
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<TargetCoordsList> targets_ = new StringMap<TargetCoordsList>();
        targets_.put(COUPE_VENT, TargetCoordsList.newList(POKEMON_FOE_TARGET_ZERO,POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(MoveTarget.def()).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(MoveTarget.def()).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) -2);
        StringMap<TargetCoordsList> targets_ = new StringMap<TargetCoordsList>();
        targets_.put(COUPE_VENT, TargetCoordsList.newList(POKEMON_FOE_TARGET_ZERO,POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(MoveTarget.def()).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(MoveTarget.def()).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) -2);
        StringMap<TargetCoordsList> targets_ = new StringMap<TargetCoordsList>();
        targets_.put(SIPHON, TargetCoordsList.newList(POKEMON_FOE_TARGET_ZERO));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(SIPHON,choices_.getVal(MoveTarget.def()).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(MoveTarget.def()).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<TargetCoordsList> targets_ = new StringMap<TargetCoordsList>();
        targets_.put(SIPHON, TargetCoordsList.newList(POKEMON_FOE_TARGET_ONE));
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(SIPHON,choices_.getVal(MoveTarget.def()).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(MoveTarget.def()).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligenceWithoutUser5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getStatisBoost().put(Statistic.SPECIAL_DEFENSE, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        StringMap<TargetCoordsList> targets_ = new StringMap<TargetCoordsList>();
        FightArtificialIntelligence.choiceAllyArtificialIntelligenceWithoutUser(fight_, POKEMON_PLAYER_FIGHTER_ONE, targets_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(0, choices_.size());
    }

    @Test
    public void choiceAllyArtificialIntelligence1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 17, 3);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 17, 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 3, 3);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(TONNERRE), new StringList(DETECTION), 17, 3, 3, 17, 12);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(TONNERRE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 17, 3, 3, 17, 12);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DOUBLE_PIED), new StringList(DETECTION), 17, 3, 3, 17, 3);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ONE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
    }

    @Test
    public void choiceAllyArtificialIntelligence7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DOUBLE_PIED), new StringList(DETECTION), 17, 3, 3, 17, 10);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(DOUBLE_PIED,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(PICORE), new StringList(DETECTION), 17, 3, 3, 17, 10);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(PICORE), new StringList(DETECTION), 17, 3, 3, 17, 13);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(PICORE,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove1(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION), 17, 3, 3, 17, 13);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(6, choices_.size());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(ECUME,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ONE)).getTarget().getPosition());
    }

    @Test
    public void choiceAllyArtificialIntelligence11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove2(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(3, choices_.size());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(PLAQUAGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PLAQUAGE,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(JACKPOT,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(TORGNOLES,POKEMON_FOE_TARGET_ZERO)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(CHARGE), new StringList(DETECTION), 12);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ABSORB_EAU);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(CHARGE,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(CHARGE,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(SIPHON,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(SIPHON), new StringList(DETECTION), 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("1"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().put(Statistic.ATTACK, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(NULL_REF,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(Fighter.BACK,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getTarget().getPosition());
        assertEq(SIPHON,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence14Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(ABSORB_EAU);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getStatisBoost().put(Statistic.ATTACK, (byte) -1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(2, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO)).getTarget());
        assertEq(COUPE_VENT,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ONE)).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence15Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setRemainedHp(new Rate("2"));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(new Rate("3"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setCurrentAbility(ANNULE_GARDE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getStatisBoost().put(Statistic.SPECIAL_ATTACK, (byte) 2);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(1, choices_.size());
        assertEq(COUPE_VENT,choices_.getVal(MoveTarget.def()).getMove());
        assertEq(POKEMON_FOE_TARGET_ZERO,choices_.getVal(MoveTarget.def()).getTarget());
    }

    @Test
    public void choiceAllyArtificialIntelligence16Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_;
        moves_ = new StringMap<Short>();
        moves_.put(CHARGE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(COUPE_VENT), new StringList(DETECTION), 3);
//        FightKo.setFighterKo(fight_, POKEMON_PLAYER_FIGHTER_ONE, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightArtificialIntelligence.choiceAllyArtificialIntelligence(fight_, diff_, data_);
        MoveTargets choices_;
        choices_ = fight_.getAllyChoice();
        assertEq(0, choices_.size());
    }

    @Test
    public void setFirstChosenMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(INTERVERSION));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(SEISME), new StringList(SEISME));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, SEISME, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SEISME, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(SEISME), new StringList(PISTOLET_A_O));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, PISTOLET_A_O, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setFirstChosenMove4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove9(data_, diff_, player_, new StringList(INTERVERSION));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        //assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(INTERVERSION));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, INTERVERSION, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(INTERVERSION), new StringList(RELAIS), 3);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(2, action_.getSubstitute());
    }

    @Test
    public void setBatonPass3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove8(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DANSE_LUNE));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(3, action_.getSubstitute());
    }

    @Test
    public void setBatonPass4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove3(data_, diff_, player_, new StringList(INTERVERSION), new StringList(BERCEUSE), 3);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, BERCEUSE, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(DANSE_LUNE));
        //FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data_);
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, DANSE_LUNE, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(DANSE_LUNE, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void setBatonPass6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(RELAIS));
        FightArtificialIntelligence.setFirstChosenMove(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, diff_, data_);
        FightArtificialIntelligence.setBatonPass(fight_, POKEMON_FOE_FIGHTER_ZERO, RELAIS, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RELAIS, action_.getFirstChosenMove());
        assertEq(0, action_.getChosenTargets().size());
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(INTERVERSION));
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(INTERVERSION, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(BERCEUSE));
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(BERCEUSE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove4(data_, diff_, player_, new StringList(INTERVERSION), new StringList(SIPHON, BERCEUSE));
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(PISTOLET_A_O, SIPHON), 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(PISTOLET_A_O, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(PISTOLET_A_O, CHARGE), 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(PISTOLET_A_O, CHARGE), 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(CHARGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove6(data_, diff_, player_, new StringList(PISTOLET_A_O, CHARGE));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, CHARGE, (short) 50);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(LUTTE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        assertNull(fighter_.getAction());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        assertNull(fighter_.getAction());
    }

    @Test
    public void choiceFoeArtificialIntelligence8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        WildPk pokemon_ = new WildPk();
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
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(JACKPOT, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(EMPAL_KORNE, SIPHON), 3);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(SIPHON, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(EMPAL_KORNE, GLACIATION), 18);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(EMPAL_KORNE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(GUILLOTINE, GLACIATION), 18);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(GUILLOTINE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(GUILLOTINE, RISQUE), 18);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(RISQUE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    @Test
    public void choiceFoeArtificialIntelligence13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        Fight fight_ = setFirstChosenMove5(data_, diff_, player_, new StringList(ONDE_VIDE, STOCKAGE), 18);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(SOL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).affecterTypes(SOL);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).affecterTypes(SOL);
        FightArtificialIntelligence.choiceFoeArtificialIntelligence(fight_, diff_, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        ActionMove action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_ONE));
        assertEq(Fighter.BACK, action_.getSubstitute());
        fighter_ = fight_.getFighter(POKEMON_FOE_FIGHTER_TWO);
        action_ = (ActionMove) fighter_.getAction();
        assertEq(STOCKAGE, action_.getFirstChosenMove());
        assertEq(1, action_.getChosenTargets().size());
        assertTrue(action_.getChosenTargets().containsObj(POKEMON_PLAYER_TARGET_TWO));
        assertEq(Fighter.BACK, action_.getSubstitute());
    }

    private Fight setFirstChosenMove9(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = choiceForSubstituingGym(_player, _diff, _data, foeTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove8(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove7(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)15, _foeMoves));
        foeTeam_.add(pkTrainer((short)15, _foeMoves));
        Fight fight_ = choiceForSubstituingGym(_player, _diff, _data, foeTeam_, 3);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove6(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = choiceForSubstituingGym(_player, _diff, _data, foeTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove5(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _f) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        Fight fight_ = choiceForSubstituingGym(_player, _diff, _data, foeTeam_, 3);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove4(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove3(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves, int _f2) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove2(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private Fight setFirstChosenMove1(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves, int _p1, int _p2, int _p3, int _f1, int _f2) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f1, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p1, _partnerMoves));
        allyTeam_.add(pkTrainer((short)_p2, _partnerMoves));
        allyTeam_.add(pkTrainer((short)_p3, _partnerMoves));
        Fight fight_ = choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
        return firstEffectWhileSendingTeams(_diff, _data, fight_);
    }

    private static Fight firstEffectWhileSendingTeams(Difficulty _diff, DataBase _data, Fight _fight) {
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _data);
        return _fight;
    }

    private Fight choiceForSubstituing10(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight choiceForSubstituing9(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight choiceForSubstituing8(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight choiceForSubstituing7(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight choiceForSubstituing6(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return choiceForSubstituingDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight choiceForSubstituing5(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return choiceForSubstituingGym(_player, _diff, _data, foeTeam_, 2);
    }

    private Fight choiceForSubstituing4(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return choiceForSubstituingGym(_player, _diff, _data, foeTeam_, 2);
    }

    private Fight choiceForSubstituing3(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return choiceForSubstituingGym(_player, _diff, _data, foeTeam_, 2);
    }

    private Fight choiceForSubstituing2(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return choiceForSubstituingGym(_player, _diff, _data, foeTeam_);
    }

    private Fight choiceForSubstituing1(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return choiceForSubstituingGym(_player, _diff, _data, foeTeam_);
    }

    private static Fight choiceForSubstituingGym(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight choiceForSubstituingGym(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, int _mult) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setMultiplicityFight((byte) _mult);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, leader_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static Fight choiceForSubstituingDual(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, CustList<PkTrainer> _allyTeam) {
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        ally_.setTeam(_allyTeam);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(_foeTeam);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    private static PkTrainer pkTrainer(short _level, StringList _moves) {
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel(_level);
        allyPokemon_.setMoves(_moves);
        return allyPokemon_;
    }

    private TeamPositionList untouchablePartners(DataBase _data, Difficulty _diff, Fight _fight, TeamPosition _thrower, String _move) {
        return FightArtificialIntelligence.untouchablePartners(_fight, _thrower, _move, _diff, _data).getBack();
    }

    private boolean reachable(DataBase _data, Difficulty _diff, Fight _fight, TeamPosition _thrower, TeamPosition _target, String _move) {
        return FightArtificialIntelligence.reachable(_fight, _thrower, _target, _diff, _data, _data.getMove(_move));
    }

}
