package aiki.game.fight;

import aiki.db.DataBase;
import aiki.game.fight.actions.*;
import aiki.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;


public class FightFacadeTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    @Test
    public void newFight1Test() {
        Fight fight_ = FightFacade.newFight();
        assertEq(FightType.NOTHING,fight_.getFightType());
        assertEq(Rate.zero(),fight_.getWinningMoney());
        assertTrue(!fight_.getTemp().getSimulation());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(0, fight_.getUsedItemsWhileRound().size());
    }

    @Test
    public void newFight2Test() {
        Fight fight_ = FightFacade.newFight();
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.FALSE);
        fight_.getTemp().getKos().put(Fight.CST_FOE,BoolVal.FALSE);
        assertEq(FightType.NOTHING,fight_.getFightType());
        assertEq(Rate.zero(),fight_.getWinningMoney());
        assertTrue(!fight_.getTemp().getSimulation());
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(0, fight_.getUsedItemsWhileRound().size());
        assertTrue(FightFacade.stopSimulation(fight_));
    }
    @Test
    public void initFightGymLeader1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightGymTrainer1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymTrainer trainer_ = new GymTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightTrainerMultiFights1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        PokemonTeam foeTeamList_ = new PokemonTeam();
        foeTeamList_.setTeam(foeTeam_);
        foeTeamList_.setReward((short) 200);
        TrainerMultiFights trainer_ = new TrainerMultiFights();
        trainer_.setTeamsRewards(new CustList<PokemonTeam>(foeTeamList_));
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, 0, data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightTrainerLeague1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        TrainerLeague trainer_ = new TrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightDualFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        DualFight dual_ = new DualFight();
        Ally allyTrainer_ = new Ally();
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(ARTIKODIN);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        allyTrainer_.setTeam(ally_);
        dual_.setAlly(allyTrainer_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        TempTrainer tempTrainer_ = new TempTrainer();
        tempTrainer_.setTeam(foeTeam_);
        tempTrainer_.setReward((short)200);
        dual_.setFoeTrainer(tempTrainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        assertEq(2, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(4, fight_.getUserTeam().getMembers().size());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightPokemon1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(ARTIKODIN);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, foePokemon_, data_);
        assertEq(2, fight_.getUserTeam().getMembers().size());
        assertEq(1, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
    }

    @Test
    public void fightersBelongingToUser1Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,true,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        DualFight dual_ = new DualFight();
        Ally allyTrainer_ = new Ally();
        CustList<PkTrainer> ally_ = new CustList<PkTrainer>();
        PkTrainer pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(ARTIKODIN);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        pokemonAlly_ = new PkTrainer();
        pokemonAlly_.setName(PIKACHU);
        pokemonAlly_.setItem(MAGNET);
        pokemonAlly_.setAbility(PARATONNERRE);
        pokemonAlly_.setGender(Gender.NO_GENDER);
        pokemonAlly_.setLevel((short) 3);
        pokemonAlly_.setMoves(new StringList(JACKPOT));
        ally_.add(pokemonAlly_);
        allyTrainer_.setTeam(ally_);
        dual_.setAlly(allyTrainer_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        TempTrainer tempTrainer_ = new TempTrainer();
        tempTrainer_.setTeam(foeTeam_);
        tempTrainer_.setReward((short)200);
        dual_.setFoeTrainer(tempTrainer_);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initMultiplicity(fight_,dual_.getFoeTrainer().getMultiplicityFight());
        FightInitialization.initUserTeam(fight_,player_, diff_, dual_, data_);
        FightInitialization.initEquipeTmpTrainer(fight_,player_, diff_, dual_, data_);
        TeamPositionList list_ = FightFacade.fightersBelongingToUser(fight_,true);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        list_ = FightFacade.fightersBelongingToUser(fight_,false);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
    }

    private static Fight koTeam(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(JACKPOT, PREVENTION, RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        setEnvTypeCom(fight_);
        return fight_;
    }

    @Test
    public void koTeam1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_, data_);
        assertTrue(!FightFacade.koTeam(fight_));
    }

    @Test
    public void koTeam2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_, data_);
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.koTeam(fight_));
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        assertTrue(FightFacade.koTeam(fight_));
    }

    @Test
    public void koTeam3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_, data_);
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertTrue(FightFacade.koTeam(fight_));
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        assertTrue(FightFacade.koTeam(fight_));
    }

    private static Fight win(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,true, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, _data);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        player_.getTeam().add(lasPk_);
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(PARATONNERRE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        TrainerLeague trainer_ = new TrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, _diff, trainer_, _data);
        setEnvTypeCom(fight_);
        return fight_;
    }

    @Test
    public void win1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        assertTrue(!FightFacade.win(fight_));
    }

    @Test
    public void win2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.win(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(FightFacade.win(fight_));
    }

    @Test
    public void win3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.win(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.win(fight_));
    }

    @Test
    public void win4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.win(fight_));
    }

    @Test
    public void loose1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void loose2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.loose(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void loose3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.loose(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        assertTrue(FightFacade.loose(fight_));
    }

    @Test
    public void loose4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void equality1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.equality(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertTrue(!FightFacade.equality(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, data_);
        assertTrue(FightFacade.equality(fight_));
    }

    @Test
    public void defaultChoices1Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data_);
        ByteMap<ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(0, map_.size());
    }

    @Test
    public void defaultChoices2Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data_);
        ByteMap<ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(1, map_.size());
        assertTrue(map_.contains((byte) 0));
        assertTrue(!map_.contains((byte) 1));
        ChoiceOfEvolutionAndMoves moves_ = map_.getVal((byte) 0);
        assertEq(4,moves_.getKeptMoves().size());
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), ECUME));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), HYPNOSE));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), TORGNOLES));
    }

    @Test
    public void defaultChoices3Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, data_);
        ByteMap<ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(1, map_.size());
        assertTrue(map_.contains((byte) 0));
        assertTrue(!map_.contains((byte) 1));
        ChoiceOfEvolutionAndMoves moves_ = map_.getVal((byte) 0);
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), TORGNOLES));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), DANSE_PLUIE));
        assertTrue(StringUtil.contains(moves_.getKeptMoves(), PLAQUAGE));
    }

    @Test
    public void possibleChoices1Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices2Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        StringUtil.removeObj(moves_, HYPNOSE);
        moves_.add(DANSE_PLUIE);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices3Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_,diff_, data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        StringUtil.removeObj(moves_, HYPNOSE);
        assertTrue(!FightFacade.possibleChoices(fight_, data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices4Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        moves_.add(DANSE_PLUIE);
        assertTrue(!FightFacade.possibleChoices(fight_, data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices5Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices6Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        choice_.setAbility(ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices7Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        choice_.setAbility(ABSORB_EAU);
        StringUtil.removeObj(choice_.getKeptMoves(), DANSE_PLUIE);
        choice_.getKeptMoves().add(BULLES_D_O);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices8Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        assertTrue(!FightFacade.possibleChoices(fight_, data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices9Test(){
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defaultChoices1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(MUNJA);
        choice_.getKeptMoves().clear();
        choice_.getKeptMoves().add(TUNNEL);
        choice_.getKeptMoves().add(OMBRE_PORTEE);
        choice_.getKeptMoves().add(GRIFFE);
        choice_.getKeptMoves().add(GRIFFE_ACIER);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void fighterMoves1Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
    }

    @Test
    public void fighterMoves2Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, POKEMON_PLAYER_FIGHTER_ONE, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void fighterMoves3Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(ECUME, CHARGE), 3);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, f_, data_);
        ChosenMoveInfos infos_;
        assertEq(1, moves_.size());
        infos_ = moves_.getVal(LUTTE);
        assertEq(1,infos_.getTypes().size());
        assertEq(LUTTE,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(0, infos_.getUses().getMax());
    }

    @Test
    public void frontFighterMoves1Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 0, data_);
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
    }

    @Test
    public void frontFighterMoves2Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 1, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void frontFighterMoves3Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 2, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void backFighterMoves1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.backFighterMoves(fight_, (byte) 0, data_);
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
    }

    @Test
    public void backFighterMoves2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.backFighterMoves(fight_, (byte) 1, data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void initChosableTargets1Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 0, SEISME, diff_, data_);
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void initChosableTargets2Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 0, INTERVERSION, diff_, data_);
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets3Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 0, PISTOLET_A_O, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets4Test() {
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
        map_.put(RISQUE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 0, RISQUE, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets5Test() {
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
        Fight fight_ = defChoicesSending8(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, 3, 3);
        initChosableTargets(fight_, 0, SIPHON, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets6Test() {
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
        Fight fight_ = defChoicesSending9(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        initChosableTargets(fight_, 0, SIPHON, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets7Test() {
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
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 0, PISTOLET_A_O, diff_, data_);
        assertEq(1, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertEq(1, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
    }

    @Test
    public void initChosableTargets8Test() {
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
        map_.put(ACUPRESSION, (short) 10);
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
        Fight fight_ = defChoicesSending9(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        initChosableTargets(fight_, 0, ACUPRESSION, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets9Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION, CHARGE), 2);
        initChosableTargets(fight_, 0, INTERVERSION, diff_, data_);
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void initChosableTargets10Test() {
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
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        initChosableTargets(fight_, 2, SIPHON, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void chooseFrontFighter1Test() {
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
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter2Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter3Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
        //        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(0, fight_.getChosenFoeTarget());
        assertEq(PISTOLET_A_O, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter4Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_PLAYER_TARGET_ONE);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
        //        assertEq(1, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(PISTOLET_A_O, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter5Test() {
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
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(1, moves_.size());
        infos_ = moves_.getVal(SEISME);
        assertEq(1,infos_.getTypes().size());
        assertEq(SEISME,infos_.getName());
        assertEq(SOL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(10, infos_.getUses().getCurrent());
        assertEq(10, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(SEISME, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter6Test() {
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
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.SWITCH, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter7Test() {
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
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setChosenHealingObject(EAU_FRAICHE, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.HEALING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(EAU_FRAICHE, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter8Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        assertEq(0, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(4, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.HEALING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(ECUME, fight_.getTemp().getChosenMoveFront());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter9Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseFrontFighter(fight_,(byte) 2, diff_, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter10Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter11Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 2, diff_, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter12Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) -1, diff_, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter13Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,(byte) -1, diff_, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void changeAction1Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getTemp().getSelectedActionCurFighter());
    }

    @Test
    public void changeAction2Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        FightFacade.changeAction(fight_, ActionType.MOVE, data_);
        assertEq(1, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(ActionType.MOVE, fight_.getTemp().getSelectedActionCurFighter());
    }

    @Test
    public void changeAction3Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        fight_.getTemp().setChosenIndexBack(Fighter.BACK);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getTemp().getSelectedActionCurFighter());
    }

    @Test
    public void changeAction4Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(DETECTION, CHARGE), new StringList(DETECTION, CHARGE));
        fight_.getTemp().setChosenIndexFront(Fighter.BACK);
        fight_.getTemp().setChosenIndexBack(Fighter.BACK);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getTemp().getSelectedActionCurFighter());
    }

    @Test
    public void setSubstituteSwitch1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionSwitch);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
    }

    @Test
    public void setSubstituteSwitch2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 1);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setSubstituteSwitch3Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getTemp().setChosenIndexFront((byte) 2);
        setSubstituteSwitch(fight_, 1);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setSubstituteForMove1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(RELAIS);
        setSubstituteForMove(fight_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.getTemp().setChosenIndexFront((byte) 1);
        chooseBackFighterAddon(fight_, 0, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.getTemp().setChosenIndexFront((byte) 1);
        chooseBackFighterAddon(fight_, 0, data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon3Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.getTemp().setChosenIndexFront((byte) 1);
        chooseBackFighterAddon(fight_, -1, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon4Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending3(data_, diff_, player_, new StringList(CHARGE), new StringList(CHARGE), new StringList(CHARGE), new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getTemp().setChosenIndexFront((byte) 1);
        chooseBackFighterAddon(fight_, 2, data_);
        assertTrue(!fight_.isError());
    }

    @Test
    public void chooseBackFighterWhileRound1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        chooseBackFighterWhileRound(fight_, 0, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterWhileRound2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        chooseBackFighterWhileRound(fight_, -1, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterWhileRound3Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        chooseBackFighterWhileRound(fight_, 0, data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void cancelChooseBackFighterWhileRound1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        chooseBackFighterWhileRound(fight_, 0, data_);
        FightFacade.cancelChooseBackFighterWhileRound(fight_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void validateSwitch1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        FightFacade.validateSwitch(fight_);
        Fighter fighter_ = fight_.getFighter(userOne_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertTrue(action_ instanceof ActionSwitch);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightFacade.chooseBackFighter(fight_,(byte) 0, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(0, fight_.getTemp().getChosenIndexBack());
        assertEq(2, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter3Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(0, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter4Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(EAU_FRAICHE, data_);
        FightFacade.chooseBackFighter(fight_,(byte) 0, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(0, fight_.getTemp().getChosenIndexBack());
        assertEq(2, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(EAU_FRAICHE, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter5Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (byte) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseBackFighter(fight_,(byte) 0, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(0, fight_.getTemp().getChosenIndexBack());
        assertEq(2, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getTemp().getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(!infos_.isUsable());
        assertEq(0, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        assertEq(ECUME, fight_.getTemp().getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter6Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter7Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter8Test() {
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
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, RELAIS, diff_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
    }

    @Test
    public void chooseBackFighter9Test() {
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
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DANSE_LUNE, diff_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(DANSE_LUNE,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter10Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseBackFighter(fight_,(byte) 1, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter11Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseBackFighter(fight_,(byte) 0, data_);
        FightFacade.chooseBackFighter(fight_,(byte) -1, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter12Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = pkTrainer((short) 3, new StringList(DETECTION, CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (byte) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseBackFighter(fight_,(byte) 0, data_);
        FightFacade.chooseBackFighter(fight_,(byte) -1, data_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
        assertEq(0, fight_.getTemp().getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getTemp().getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
//        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter13Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.chooseBackFighter(fight_, (byte) -1, data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter14Test() {
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
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getRemainingHp().affectZero();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, RELAIS, diff_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 1, data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getTemp().getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(RELAIS, fight_.getTemp().getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
    }

    @Test
    public void setFirstChosenMove1Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        setFirstChosenMove(fight_, 1);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(SEISME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setFirstChosenMove2Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        setFirstChosenMove(fight_, 2);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setChosenHealingItemFront1Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, EAU_FRAICHE, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }


    @Test
    public void setChosenHealingItemFront2Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, ELIXIR, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(ELIXIR, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(ELIXIR,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront3Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_MEPO, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(BAIE_MEPO, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront4Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, HUILE, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront5Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, HUILE_MAX, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE_MAX, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront6Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_ORAN, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BAIE_ORAN, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(BAIE_ORAN,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setChosenHealingItemFront7Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        fight_.getTemp().setChosenIndexFront((byte) 2);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_ORAN, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack1Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, EAU_FRAICHE, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack2Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, BAIE_ORAN, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BAIE_ORAN, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(BAIE_ORAN,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack3Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, ELIXIR, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(ELIXIR, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(ELIXIR,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack4Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, HUILE, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack5Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, BAIE_MEPO, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(BAIE_MEPO, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack6Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItemBack(fight_, HUILE_MAX, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE_MAX, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setChosenHealingItemBack7Test() {
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        fight_.getTemp().setChosenIndexBack((byte) 2);
        FightFacade.setChosenHealingItemBack(fight_, HUILE_MAX, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, EAU_FRAICHE, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, EAU_FRAICHE, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem3Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(23, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem4Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(23, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemMove1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 1);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, data_);
        FightFacade.setChosenHealingItemMove(fight_, PISTOLET_A_O);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
    }

    @Test
    public void setChosenHealingItemMove2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 1);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, data_);
        FightFacade.setChosenHealingItemMove(fight_, PISTOLET_A_O);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
    }

    @Test
    public void chooseMove1Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, SEISME, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(SEISME, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
    }

    @Test
    public void chooseMove2Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ONE, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(2, fight_.getChosableFoeTargets().size());
//        assertTrue(!fight_.getChosableFoeTargets().first());
//        assertTrue(!fight_.getChosableFoeTargets().last());
//        assertEq(2, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
//        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove3Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(2, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(2, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void chooseMove4Test() {
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
        map_.put(RISQUE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, RISQUE, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RISQUE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(2, fight_.getChosableFoeTargets().size());
//        assertTrue(fight_.getChosableFoeTargets().first());
//        assertTrue(!fight_.getChosableFoeTargets().last());
//        assertEq(2, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
//        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove5Test() {
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
        Fight fight_ = defChoicesSending8(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, 3, 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, SIPHON, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void chooseMove6Test() {
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
        Fight fight_ = defChoicesSending9(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, SIPHON, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.FALSE,  fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void chooseMove7Test() {
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
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(PISTOLET_A_O, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(1, fight_.getChosableFoeTargets().size());
//        assertTrue(fight_.getChosableFoeTargets().first());
//        assertEq(1, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
    }

    @Test
    public void chooseMove8Test() {
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
        map_.put(ACUPRESSION, (short) 10);
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
        Fight fight_ = defChoicesSending9(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, ACUPRESSION, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(3, fight_.getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().get(1).getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosableFoeTargets().last().getChosable());
        assertEq(3, fight_.getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().first().getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().get(1).getChosable());
        assertSame(BoolVal.TRUE, fight_.getTemp().getChosablePlayerTargets().last().getChosable());
    }

    @Test
    public void chooseMove9Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
//        assertEq(1, fight_.getChosableFoeTargets().size());
//        assertTrue(!fight_.getChosableFoeTargets().first());
//        assertEq(1, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
    }

    @Test
    public void chooseMove10Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, data_);
        FightFacade.chooseMove(fight_, ECUME, diff_, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(4, moves_.size());
        infos_ = moves_.getVal(ECUME);
        assertEq(1,infos_.getTypes().size());
        assertEq(ECUME,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(23, infos_.getUses().getCurrent());
        assertEq(25, infos_.getUses().getMax());
        infos_ = moves_.getVal(PISTOLET_A_O);
        assertEq(1,infos_.getTypes().size());
        assertEq(PISTOLET_A_O,infos_.getName());
        assertEq(EAU,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(TORGNOLES);
        assertEq(1,infos_.getTypes().size());
        assertEq(TORGNOLES,infos_.getName());
        assertEq(NORMAL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(20, infos_.getUses().getCurrent());
        assertEq(20, infos_.getUses().getMax());
        infos_ = moves_.getVal(HYPNOSE);
        assertEq(1,infos_.getTypes().size());
        assertEq(HYPNOSE,infos_.getName());
        assertEq(PSY,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(15, infos_.getUses().getCurrent());
        assertEq(15, infos_.getUses().getMax());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
        assertEq(ECUME, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseMove11Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseMove(fight_, ECUME, diff_, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void chooseMove12Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        fight_.getTemp().setSelectedActionCurFighter(ActionType.MOVE);
        fight_.getTemp().setChosenIndexFront((byte) 2);
        FightFacade.chooseMove(fight_, ECUME, diff_, data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getTemp().getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getTemp().getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getTemp().getChosableFoeTargets().size());
        assertEq(0, fight_.getTemp().getChosablePlayerTargets().size());
        assertEq(NULL_REF, fight_.getTemp().getChosenMoveFront());
    }

    @Test
    public void allowedMovesNotEmpty1Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        StringList moves_ = FightFacade.allowedMovesNotEmpty(fight_, POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(4, moves_.size());
        assertTrue(StringUtil.contains(moves_, ECUME));
        assertTrue(StringUtil.contains(moves_, PISTOLET_A_O));
        assertTrue(StringUtil.contains(moves_, TORGNOLES));
        assertTrue(StringUtil.contains(moves_, HYPNOSE));
    }

    @Test
    public void allowedMovesNotEmpty2Test() {
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(ECUME, CHARGE), 3);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightFacade.allowedMovesNotEmpty(fight_, f_, data_);
        assertEq(1, moves_.size());
        assertTrue(StringUtil.contains(moves_, LUTTE));
    }

    @Test
    public void remainingThrowersTargetsHp1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3);
        TeamPositionsStringMapTeamPositionsRate map_;
        map_ = FightFacade.remainingThrowersTargetsHp(fight_, diff_, data_);
        assertEq(2, map_.size());
        assertEq(3, map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).size());
        assertEq(3, map_.getVal(POKEMON_PLAYER_FIGHTER_ONE).size());
        StringMap<TeamPositionsPairRatesPair> mapMoves_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, mapMoves_.getVal(PISTOLET_A_O).getFoe().size());
        assertEq(2, mapMoves_.getVal(PISTOLET_A_O).getPlayer().size());
        assertEq(1, mapMoves_.getVal(ECUME).getFoe().size());
        assertEq(2, mapMoves_.getVal(ECUME).getPlayer().size());
        assertEq(1, mapMoves_.getVal(TORGNOLES).getFoe().size());
        assertEq(2, mapMoves_.getVal(TORGNOLES).getPlayer().size());
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(1, mapMoves_.getVal(PISTOLET_A_O).getFoe().size());
        assertEq(2, mapMoves_.getVal(PISTOLET_A_O).getPlayer().size());
        assertEq(1, mapMoves_.getVal(ECUME).getFoe().size());
        assertEq(2, mapMoves_.getVal(ECUME).getPlayer().size());
        assertEq(1, mapMoves_.getVal(TORGNOLES).getFoe().size());
        assertEq(2, mapMoves_.getVal(TORGNOLES).getPlayer().size());
        TeamPositionsPairRatesPair mapFighters_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        mapFighters_ = mapMoves_.getVal(PISTOLET_A_O);
        assertEq(1, mapFighters_.getFoe().size());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        mapFighters_ = mapMoves_.getVal(ECUME);
        assertEq(1, mapFighters_.getFoe().size());
        assertEq(new Rate("16909/5200"), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(new Rate("16909/5200"), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        mapFighters_ = mapMoves_.getVal(TORGNOLES);
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        mapFighters_ = mapMoves_.getVal(PISTOLET_A_O);
        assertEq(1, mapFighters_.getFoe().size());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        mapFighters_ = mapMoves_.getVal(ECUME);
        assertEq(1, mapFighters_.getFoe().size());
        assertEq(new Rate("16909/5200"), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(new Rate("16909/5200"), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
        mapFighters_ = mapMoves_.getVal(TORGNOLES);
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(POKEMON_FOE_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getFront());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ZERO).getBack());
        assertEq(new Rate("4587/100"), mapFighters_.getPlayer().getVal(POKEMON_PLAYER_FIGHTER_ONE).getBack());
    }

    @Test
    public void setFirstChosenMoveFoeTarget1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setFirstChosenMovePlayerTarget1Test() {
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
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
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
        Fight fight_ = defChoicesSending8(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, 3, 3);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, data_);
        FightFacade.setFirstChosenMovePlayerTarget(fight_, (byte) 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void sortedFightersUsingMoveDependingOnPlayerChoices1Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        pokemon_.setLevel((short) 15);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending8(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 4, 3, 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        setFirstChosenMove(fight_, 0);
        setFirstChosenMove(fight_, 1);
        TeamPositionActionMoveMap tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, data_);
        assertEq(4, tree_.size());
        assertEq(POKEMON_FOE_FIGHTER_ONE,tree_.getKey(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,tree_.getKey(1));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,tree_.getKey(2));
        assertEq(POKEMON_PLAYER_FIGHTER_ONE,tree_.getKey(3));
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_ONE).getFirstChosenMove());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getFirstChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getFinalChosenMove());
    }

    @Test
    public void sortedFightersUsingMoveDependingOnPlayerChoices2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
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
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(SEISME), 18, 15, new StringList(DETECTION, CHARGE), 3, 4, 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        setFirstChosenMove(fight_, 0);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        TeamPositionActionMoveMap tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, data_);
        assertEq(4, tree_.size());
        assertEq(POKEMON_FOE_FIGHTER_ONE,tree_.getKey(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,tree_.getKey(1));
        assertEq(POKEMON_PLAYER_FIGHTER_FOUR,tree_.getKey(2));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,tree_.getKey(3));
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFirstChosenMove());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertNull(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction());
    }

    @Test
    public void sortedFightersBeginRound1Test() {
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        pokemon_.setLevel((short) 15);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending8(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 4, 3, 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        setFirstChosenMove(fight_, 0);
        setFirstChosenMove(fight_, 1);
        TeamPositionList tree_;
        tree_ = FightFacade.sortedFightersBeginRound(fight_, data_);
        assertEq(POKEMON_FOE_FIGHTER_ONE,tree_.get(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,tree_.get(1));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,tree_.get(2));
        assertEq(POKEMON_PLAYER_FIGHTER_ONE,tree_.get(3));
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getFirstChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getFinalChosenMove());
    }

    @Test
    public void sortedFightersBeginRound2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
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
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(SEISME), 18, 15, new StringList(DETECTION, CHARGE), 3, 4, 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        setFirstChosenMove(fight_, 0);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        TeamPositionList tree_;
        tree_ = FightFacade.sortedFightersBeginRound(fight_, data_);
        assertEq(4,tree_.size());
        assertEq(POKEMON_FOE_FIGHTER_ONE,tree_.get(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,tree_.get(1));
        assertEq(POKEMON_PLAYER_FIGHTER_FOUR,tree_.get(2));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,tree_.get(3));
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertNull(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction());
    }

    @Test
    public void sortedFightersBeginRound3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
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
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(SEISME), 18, 15, new StringList(DETECTION, CHARGE), 3, 4, 3);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        setFirstChosenMove(fight_, 0);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        TeamPositionActionMoveMap tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, data_);
        assertEq(4, tree_.size());
        assertEq(POKEMON_FOE_FIGHTER_ONE,tree_.getKey(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO,tree_.getKey(1));
        assertEq(POKEMON_PLAYER_FIGHTER_FOUR,tree_.getKey(2));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO,tree_.getKey(3));
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, tree_.getVal(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(SEISME, tree_.getVal(POKEMON_PLAYER_FIGHTER_FOUR).getFirstChosenMove());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getAction() instanceof ActionMove);
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction() instanceof ActionMove);
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(DETECTION, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFirstChosenMove());
        assertEq(SEISME, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFirstChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getFinalChosenMove());
        assertEq(NULL_REF, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getFinalChosenMove());
        assertNull(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction());
    }

    @Test
    public void setSubstituteFront1Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void setSubstituteFront2Test() {
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
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(DETECTION), new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void setSubstituteFront3Test() {
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
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteFront4Test() {
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
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteFront5Test() {
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
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
//        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
//        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteFront6Test() {
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
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
//        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
//        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteFront7Test() {
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, data_);
        FightFacade.setSubstituteFront(fight_, (byte) 1);
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
//        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteFront8Test() {
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending12(data_, diff_, player_, new StringList(DETECTION), 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getTemp().setChosenIndexFront((byte) 2);
        FightFacade.setSubstituteFront(fight_, (byte) 1);
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3));
    }

    @Test
    public void setSubstituteBack1Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void setSubstituteBack2Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        fight_.getTemp().setChosenIndexBack((byte) 2);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void setSubstituteEndRound1Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteEndRound(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(Fighter.BACK, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void setSubstituteEndRound2Test() {
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
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.setSubstituteEndRound(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1));
        assertEq(0, fight_.getTemp().getChosenSubstitute());
    }

    @Test
    public void getMoves1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        NatStringTreeMap<BoolVal> map_ = getMoves(fight_);
        assertEq(12, map_.size());
        assertSame(BoolVal.TRUE,map_.getVal(DETECTION));
        assertSame(BoolVal.TRUE,map_.getVal(ULTRASON));
        assertSame(BoolVal.TRUE,map_.getVal(BROUHAHA));
        assertSame(BoolVal.TRUE,map_.getVal(POURSUITE));
        assertSame(BoolVal.FALSE,map_.getVal(CHARGE));
        assertSame(BoolVal.FALSE,map_.getVal(GRAVITE));
        assertSame(BoolVal.FALSE,map_.getVal(VOL_MAGNETIK));
        assertSame(BoolVal.FALSE,map_.getVal(MUR_DE_FER));
        assertSame(BoolVal.FALSE,map_.getVal(BOMBAIMANT));
        assertSame(BoolVal.FALSE,map_.getVal(REGARD_NOIR));
        assertSame(BoolVal.FALSE,map_.getVal(CAGE_ECLAIR));
        assertSame(BoolVal.FALSE,map_.getVal(EBOULEMENT));
    }

    @Test
    public void getEvolutions1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        EvolutionChoiceMap map_ = getEvolutions(data_, fight_);
        assertEq(2, map_.size());
        assertSame(BoolVal.TRUE,map_.getVal(NULL_REF));
        assertSame(BoolVal.FALSE,map_.getVal(TARINORME));
    }

    @Test
    public void getAbilities1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        StringList list_ = getAbilities(fight_);
        assertEq(2, list_.size());
        assertTrue(StringUtil.contains(list_, MAGNEPIEGE));
        assertTrue(StringUtil.contains(list_, FERMETE));
    }

    @Test
    public void isChosableForLearningAndEvolving1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        assertTrue(FightFacade.isChosableForLearningAndEvolving(fight_, (byte) 0));
    }

    @Test
    public void isChosableForLearningAndEvolving2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        assertTrue(!FightFacade.isChosableForLearningAndEvolving(fight_, (byte) -1));
    }

    @Test
    public void isChosableForLearningAndEvolving3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        assertTrue(!FightFacade.isChosableForLearningAndEvolving(fight_, (byte) -1));
    }

    @Test
    public void choosePokemonForLearningAndEvolving1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        assertEq(0, fight_.getTemp().getChosenIndex());
    }

    @Test
    public void choosePokemonForLearningAndEvolving2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) -1, data_);
        assertEq(-1, fight_.getTemp().getChosenIndex());
    }

    @Test
    public void choosePokemonForLearningAndEvolving3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 10);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, data_);
        assertEq(-1, fight_.getTemp().getChosenIndex());
    }

    @Test
    public void setEvolution1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(StringUtil.contains(list_, DETECTION));
        assertTrue(StringUtil.contains(list_, ULTRASON));
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, POURSUITE));
    }

    @Test
    public void setEvolution2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.setEvolution(fight_, NULL_REF);
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(NULL_REF, choice_.getName());
        assertEq(METEO, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(StringUtil.contains(list_, DETECTION));
        assertTrue(StringUtil.contains(list_, ULTRASON));
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, POURSUITE));
    }

    @Test
    public void setAbility1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.setAbility(fight_, MAGNEPIEGE);
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(MAGNEPIEGE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(StringUtil.contains(list_, DETECTION));
        assertTrue(StringUtil.contains(list_, ULTRASON));
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, POURSUITE));
    }

    @Test
    public void setAbility2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 10);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, data_);
        FightFacade.setAbility(fight_, MAGNEPIEGE);
        assertEq(-1, fight_.getTemp().getChosenIndex());
    }

    @Test
    public void addOrForgetMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.addOrForgetMove(fight_, ULTRASON);
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(3, list_.size());
        assertTrue(StringUtil.contains(list_, DETECTION));
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, POURSUITE));
    }

    @Test
    public void addOrForgetMove2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARINOR);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DETECTION, (short) 10);
        moves_.put(ULTRASON, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        moves_.put(POURSUITE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        Fight fight_ = defChoicesSending1(data_, diff_, player_, new StringList(DETECTION));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.addOrForgetMove(fight_, ULTRASON);
        FightFacade.addOrForgetMove(fight_, BOMBAIMANT);
        ByteMap<ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(StringUtil.contains(list_, BOMBAIMANT));
        assertTrue(StringUtil.contains(list_, DETECTION));
        assertTrue(StringUtil.contains(list_, BROUHAHA));
        assertTrue(StringUtil.contains(list_, POURSUITE));
    }

    @Test
    public void getPlayerFrontTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeam2Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getAllyFrontTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        ByteTreeMap< Fighter> mapFighters_ = FightFacade.getAllyFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE));
    }

    @Test
    public void getUnionFrontTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        ByteTreeMap< Fighter> mapFighters_ = FightFacade.getUnionFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerBackTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending10(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeam2Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending10(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!fight_.isError());
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeam3Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!fight_.isError());
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerTeam(fight_);
        assertEq(3, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 2), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getAllyBackTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        ByteTreeMap< Fighter> mapFighters_ = FightFacade.getAllyBackTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void getFoeFrontTeam1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        TeamPosition userOne_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_FOE_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        ByteTreeMap< Fighter> mapFighters_ = FightFacade.getFoeFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_FOE_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting2Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending10(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting3Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting4Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting5Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting2Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting3Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending4(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.get((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting4Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        CustList< Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.get((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void deselect1Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        fight_.getTemp().setChosenIndexBack((byte) 0);
        FightFacade.deselect(fight_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexBack());
    }

    @Test
    public void deselect2Test() {
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
        map_.put(RELAIS, (short) 10);
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
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION, CHARGE));
        fight_.getTemp().setChosenIndexFront((byte) 0);
        FightFacade.deselect(fight_);
        assertEq(Fighter.BACK, fight_.getTemp().getChosenIndexFront());
    }

    @Test
    public void frontFighterChoiceFleeingCatching1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        WildPk pokemon_ = new WildPk();
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_,(byte) 0);
        FightFacade.frontFighterChoiceFleeingCatching(fight_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        AbstractAction action_ = fighter_.getAction();
        assertNull(action_);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        TargetCoordsList targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void beginRound1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void beginRound2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.beginRound(fight_, diff_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void beginRound3Test() {

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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.beginRound(fight_, diff_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe4Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe5Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter( POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe6Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    //endRoundFightBasic

    private static Fight fihtRoadSending(WildPk _pk, Player _player, Difficulty _diff, DataBase _data) {
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, _pk, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        firstEffectWhileSendingTeams(fight_, _diff, _data);
        return fight_;
    }

    @Test
    public void endRoundFightBasic1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic3Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic4Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter( POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, data_);
        setSubstituteSwitch(fight_, 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic1SimulationTest() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = fihtRoadSending(pokemon_, player_, diff_, data_);
        fight_.getTemp().setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.beginRound(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertTrue(!fight_.getTemp().getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe1Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(fight_.getBeginRound());
        assertTrue(fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe2Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe3Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe4Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe5Test() {
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
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(fight_.isError());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe6Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, true);
        assertTrue(fight_.getBeginRound());
        assertTrue(fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe7Test() {
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 3, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, true);
        assertTrue(!fight_.isError());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        assertTrue(!fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!fight_.isError());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(DETECTION, CHARGE), 3, new StringList(DETECTION, CHARGE), 12, 3, 3);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        assertTrue(!fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(fight_.isError());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertTrue(!fight_.isError());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void learnAndEvolveAttack2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, PISTOLET_A_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3, 3, 3);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, PISTOLET_A_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        //FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
//        pokemon_.setLevel((short) 24);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        //lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending7(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("50"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
//        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
//        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3, 3, 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending14(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        assertTrue(FightRules.substitutable(fight_, diff_, data_));
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, data_);
        assertTrue(FightEndRound.proponedSwitch(fight_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending2(data_, diff_, player_, new StringList(DETECTION));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, data_);
        FightFacade.beginRound(fight_, diff_, data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, data_);
        FightFacade.setSubstituteBack(fight_, (byte) 1);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void roundWhileKoPlayer1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending14(data_, diff_, player_, new StringList(JACKPOT), 19, new StringList(CHARGE), 17);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending13(data_, diff_, player_, new StringList(TOURNIQUET), 1, new StringList(JACKPOT), 19, new StringList(CHARGE));
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending14(data_, diff_, player_, new StringList(JACKPOT), 19, new StringList(CHARGE), 17);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, true);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void roundWhileKoPlayer4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending13(data_, diff_, player_, new StringList(TOURNIQUET), 1, new StringList(JACKPOT), 19, new StringList(CHARGE));
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, true);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.roundUser(fight_, diff_, data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void roundWhileKoPlayer5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending15(data_, diff_, player_, new StringList(JACKPOT), 50, new StringList(CHARGE), 7, 7);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = defChoicesSending11(data_, diff_, player_, new StringList(JACKPOT), 50, new StringList(CHARGE), 7, 7, 7);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void attemptCatching1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, false);
        assertEq(NULL_REF,fight_.getCatchingBall());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void attemptCatching2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void attemptCatching3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SURNOM, fight_.getState());
    }

    @Test
    public void attemptCatching4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, true);
        assertEq(NULL_REF,fight_.getCatchingBall());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void attemptCatching5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, true);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void attemptCatching6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, data_, true);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SURNOM, fight_.getState());
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void calculateCatchingRates1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        NatStringTreeMap<BallNumberRate> rates_ = FightFacade.calculateCatchingRates(fight_, diff_, player_, data_);
        assertEq(0, rates_.size());
    }

    @Test
    public void calculateCatchingRates2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        NatStringTreeMap<BallNumberRate> rates_ = FightFacade.calculateCatchingRates(fight_, diff_, player_, data_);
        assertEq(1, rates_.size());
        assertEq(LgInt.one(), rates_.getVal(HYPER_BALL).getNumber());
        assertEq(Rate.one(), rates_.getVal(HYPER_BALL).getRate());
        assertEq(HYPER_BALL, rates_.getVal(HYPER_BALL).getName());
        assertEq("100", rates_.getVal(HYPER_BALL).getPercent());
    }

    @Test
    public void sortedFightersBeginRoundWildFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 5);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = fightRoad(player_, PTITARD, (short) 1, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ECUME);
        NatStringTreeMap<TeamPositionList> map_;
        map_ = FightFacade.sortedFightersBeginRoundWildFight(fight_, data_);
        assertEq(1, map_.size());
        assertEq(2, map_.getVal(TOURNIQUET).size());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(1));
    }

    @Test
    public void sortedFightersBeginRoundWildFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 6);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = fightRoad(player_, PTITARD, (short) 10, diff_, data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ECUME);
        NatStringTreeMap<TeamPositionList> map_;
        map_ = FightFacade.sortedFightersBeginRoundWildFight(fight_, data_);
        assertEq(3, map_.size());
        assertEq(2, map_.getVal(TOURNIQUET).size());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(0));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(1));
        assertEq(2, map_.getVal(ECUME).size());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, map_.getVal(ECUME).get(0));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, map_.getVal(ECUME).get(1));
        assertEq(2, map_.getVal(HYPNOSE).size());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, map_.getVal(HYPNOSE).get(0));
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, map_.getVal(HYPNOSE).get(1));
    }

    @Test
    public void attemptFlee1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 14, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, false);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 14, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.setNbFleeAttempt((short) 255);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, false);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(255, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 2, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, false);
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 2, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, false);
        assertEq(1, fight_.getNbFleeAttempt());
        assertEq(FightState.ATTAQUES,fight_.getState());
    }

    @Test
    public void attemptFlee5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 14, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, true);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void attemptFlee6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 14, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.setNbFleeAttempt((short) 255);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, true);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(255, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
        assertTrue(!fight_.getTemp().isKeepRound());
    }

    @Test
    public void attemptFlee7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 2, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, true);
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void attemptFlee8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 2, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, data_, true);
        assertEq(1, fight_.getNbFleeAttempt());
        assertEq(FightState.ATTAQUES,fight_.getState());
        assertTrue(fight_.getTemp().isKeepRound());
    }

    @Test
    public void fightStatement1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.CAPTURE_KO, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_,true, diff_));
    }

    @Test
    public void fightStatement8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement9Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement10Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement11Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.RIEN, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement12Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION), 3);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.RIEN, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement13Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, false, diff_));
    }

    @Test
    public void keepLoop1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.keepLoop(fight_,true,data_));
    }

    @Test
    public void keepLoop2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertTrue(!FightFacade.keepLoop(fight_,true,data_));
    }

    @Test
    public void keepLoop3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightKo.setKo(fight_,Fight.toUserFighter((byte) 0),diff_,data_);
        assertTrue(!FightFacade.keepLoop(fight_,false,data_));
    }

    private static Fight fightRoad(Player _player, String _name, short _level, Difficulty _diff, DataBase _data) {
        Fight fight_ = initTypeEnv(_player, _name, _level, _diff, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        firstEffectWhileSendingTeams(fight_, _diff, _data);
        return fight_;
    }

    @Test
    public void endFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void endFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending5(data_, diff_, player_, new StringList(JACKPOT), 3, new StringList(DETECTION), 3);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void endFight3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = defChoicesSending6(data_, diff_, player_, new StringList(DETECTION), 3);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void movesAfterFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(14, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).transformer(fight_.wildPokemon(), (short) 5);
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, JACKPOT, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).apprendreAttaqueEcrasant(TOURNIQUET, COPIE, data_);
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(10, mapMoves_.getVal(COPIE).getCurrent());
        assertEq(10, mapMoves_.getVal(COPIE).getMax());
    }

    @Test
    public void movesAfterFight7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).getMoves().put(TOURNIQUET, new UsesOfMove((short) 15));
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(2, mapMoves_.size());
        assertEq(10, mapMoves_.getVal(COPIE).getCurrent());
        assertEq(10, mapMoves_.getVal(COPIE).getMax());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightRoad(player_, PIKACHU, (short) 1, diff_, data_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).getMoves().put(TOURNIQUET, new UsesOfMove((short) 15));
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(2, mapMoves_.size());
        assertEq(10, mapMoves_.getVal(COPIE).getCurrent());
        assertEq(10, mapMoves_.getVal(COPIE).getMax());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    private static Fight initTypeEnv(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff, DataBase _data) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data);
        return fight_;
    }

    @Test
    public void initTypeEnv1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertNull(fight_.wildPokemon().getAction());
    }

    @Test
    public void initTypeEnv2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initTypeEnv2(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        FightFacade.initTypeEnv(fight_, data_.getMap().getBegin(), diff_, data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initTypeEnv1(data_, diff_, player_, new StringList(DETECTION));
        FightFacade.initTypeEnv(fight_, data_.getMap().getBegin(), diff_, data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initTypeEnv1(data_, diff_, player_, new StringList(DETECTION));
        FightFacade.initTypeEnv(fight_, new Coords(), diff_, data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initTypeEnv1(data_, diff_, player_, new StringList(DETECTION));
        Coords c_ = new Coords();
        c_.setNumberPlace((byte)1);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setPoint(new Point((byte)-1,(byte)0));
        FightFacade.initTypeEnv(fight_, c_, diff_, data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initializeFromSavedGame1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertNull(playerPk_.getAction());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 11);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(PISTOLET_A_O,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, SEISME, diff_, data_);
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.getChosenTargets().add(POKEMON_FOE_TARGET_ZERO);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(SEISME,playerPk_.getFirstChosenMove());
        assertEq(0, playerPk_.getChosenTargets().size());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 11);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        ActionMove actionMove_ = (ActionMove) loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.setFirstChosenMove(NULL_REF);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertNull(playerPk_.getAction());
        assertEq(NULL_REF,wildPk_.getUsedBallCatching());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame5Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        player_.recevoirPokemon(pokemon_, diff_, data_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter playerBackPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertNull(playerBackPk_.getAction());
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(DEMI_TOUR,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(wildPk_.getAction() instanceof ActionMove);
        assertEq(JACKPOT,wildPk_.getFirstChosenMove());
        assertEq(1, wildPk_.getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, wildPk_.getChosenTargets().get(0));
        assertEq(POKE_BALL,wildPk_.getUsedBallCatching());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame6Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_, data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, BULLES_D_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(BULLES_D_O,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(wildPk_.getAction() instanceof ActionMove);
        assertEq(JACKPOT,wildPk_.getFirstChosenMove());
        assertEq(1, wildPk_.getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, wildPk_.getChosenTargets().get(0));
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame7Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initializeFromSavedGame1(data_, diff_, player_, new StringList(JACKPOT), new StringList(DETECTION));
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter playerAllyPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertNull(playerPk_.getAction());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame8Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        Fight fight_ = initializeFromSavedGame2(data_, diff_, player_, new StringList(DETECTION));
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, data_);
        ByteMap<BoolVal> ko_ = loadedFight_.getTemp().getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, BoolVal.FALSE));
        assertEq(0, getNbKoByValue(ko_, BoolVal.TRUE));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED));
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP));
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED));
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP));
        assertNull(playerPk_.getAction());
        assertTrue(loadedFight_.getTemp().getAcceptableChoices());
    }

    private static Fight initializeFromSavedGame(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff, DataBase _data) {
        Fight fight_ = initTypeEnv(_player, _name, _level, _diff, _data);
        initTypeEnvCom(fight_, _data, _diff);
        return fight_;
    }

    private Fight defChoicesSending15(DataBase _data, Difficulty _diff, Player _player, StringList _moves, int _p, StringList _foeMoves, int _f1, int _f2) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f1, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p, _moves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending14(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, int _p, StringList _foeMoves, int _f) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending13(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, int _p1, StringList _second, int _p2, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)17, _foeMoves));
        foeTeam_.add(pkTrainer((short)17, _foeMoves));
        foeTeam_.add(pkTrainer((short)17, _foeMoves));
        foeTeam_.add(pkTrainer((short)17, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p1, _partnerMoves));
        allyTeam_.add(pkTrainer((short)_p2, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending12(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _mult) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_, _mult);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending11(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, int _p, StringList _foeMoves, int _f1, int _f2, int _f3) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f1, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending10(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending9(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_, 3);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending8(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _f1, int _f2, int _f3, int _mult) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f1, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_, _mult);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending7(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        return defChoicesSending15(_data, _diff, _player, _partnerMoves, 3, _foeMoves, 3, 3);
    }

    private Fight defChoicesSending6(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves, int _f) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending5(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, int _p, StringList _foeMoves, int _f) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending4(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, int _p1, int _p2, StringList _foeMoves, int _f1, int _f2, int _f3) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)_f1, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f2, _foeMoves));
        foeTeam_.add(pkTrainer((short)_f3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)_p1, _partnerMoves));
        allyTeam_.add(pkTrainer((short)_p2, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending3(DataBase _data, Difficulty _diff, Player _player, StringList _first, StringList _second, StringList _third, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _first));
        allyTeam_.add(pkTrainer((short)3, _first));
        allyTeam_.add(pkTrainer((short)3, _first));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending2(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_, 2);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defChoicesSending1(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_);
        setEnvTypeCom(_diff, _data, fight_);
        return fight_;
    }

    private Fight defaultChoices1(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        setEnvTypeCom(fight_);
        return fight_;
    }

    private Fight initializeFromSavedGame2(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        Fight fight_ = initTypeEnvGym(_player, _diff, _data, foeTeam_);
        initTypeEnvCom(fight_, _data, _diff);
        return fight_;
    }

    private Fight initializeFromSavedGame1(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        Fight fight_ = initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
        initTypeEnvCom(fight_, _data, _diff);
        return fight_;
    }

    private Fight initTypeEnv2(DataBase _data, Difficulty _diff, Player _player, StringList _partnerMoves, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(pkTrainer((short)3, _partnerMoves));
        return initTypeEnvDual(_player, _diff, _data, foeTeam_, allyTeam_);
    }

    private Fight initTypeEnv1(DataBase _data, Difficulty _diff, Player _player, StringList _foeMoves) {
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(pkTrainer((short)3, _foeMoves));
        return initTypeEnvGym(_player, _diff, _data, foeTeam_);
    }

    private static void setEnvTypeCom(Difficulty _diff, DataBase _data, Fight _fight) {
        setEnvTypeCom(_fight);
        firstEffectWhileSendingTeams(_fight, _diff, _data);
    }

    private static void firstEffectWhileSendingTeams(Fight _fight, Difficulty _diff, DataBase _data) {
        FightSending.firstEffectWhileSendingTeams(_fight, _diff, _data);
    }

    private static void setEnvTypeCom(Fight _fight) {
        _fight.setEnvType(EnvironmentType.ROAD);
    }

    private static void initTypeEnvCom(Fight _fight, DataBase _data, Difficulty _diff) {
        FightFacade.initTypeEnv(_fight, _data.getMap().getBegin(), _diff, _data);
    }

    private static Fight initTypeEnvGym(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, leader_, _data);
        return fight_;
    }

    private static Fight initTypeEnvGym(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, int _mult) {
        GymLeader leader_ = new GymLeader();
        leader_.setTeam(_foeTeam);
        leader_.setMultiplicityFight((byte) _mult);
        leader_.setReward((short) 200);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, _player, _diff, leader_, _data);
        return fight_;
    }

    private static Fight initTypeEnvDual(Player _player, Difficulty _diff, DataBase _data, CustList<PkTrainer> _foeTeam, CustList<PkTrainer> _allyTeam) {
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

    private static int getNbKoByValue(ByteMap<BoolVal> _map, BoolVal _value) {
//        int nb_ = IndexConstants.FIRST_INDEX;
//        for (EntryCust<Byte,BoolVal> e: _map.entryList()) {
//            if (e.getValue() == _value) {
//                nb_++;
//            }
//        }
//        return nb_;
        return DataBase.countValues(_map.values(), _value);
    }

    private static Fight saveFight(Fight _currentFight) {
        return FightFacade.cpFight(_currentFight);
    }

    private void chooseBackFighterAddon(Fight _fight, int _x, DataBase _data) {
        FightFacade.chooseBackFighterAddon(_fight, _data, _fight.getUserTeam().substituteAtIndex((byte) _x));
    }

    private static void initChosableTargets(Fight _fight, int _x, String _move, Difficulty _diff, DataBase _data) {
        FightFacade.initChosableTargets(_fight, _move, _diff, _data, _fight.getUserTeam().playerFighterAtIndex((byte) _x).first());
    }

    private void setSubstituteSwitch(Fight _fight, int _x) {
        FightFacade.setSubstituteSwitch(_fight, _fight.getUserTeam().substituteAtIndex((byte) _x));
    }

    private void chooseBackFighterWhileRound(Fight _fight, int _x, DataBase _data) {
        FightFacade.chooseBackFighterWhileRound(_fight, _data, _fight.getUserTeam().substituteAtIndex((byte) _x));
    }

    private NatStringTreeMap<BoolVal> getMoves(Fight _fight) {
        return FightFacade.getMoves(_fight, TARINORME, _fight.getUserTeam().fighterAtIndex((byte) 0));
    }

    private StringList getAbilities(Fight _fight) {
        return FightFacade.getAbilities(_fight, TARINORME, _fight.getUserTeam().fighterAtIndex((byte) 0));
    }

    private EvolutionChoiceMap getEvolutions(DataBase _data, Fight _fight) {
        return FightFacade.getEvolutions(_fight, _data, _fight.getUserTeam().fighterAtIndex((byte) 0));
    }

    private void setFirstChosenMove(Fight _fight, int _x) {
        FightFacade.setFirstChosenMove(SEISME, _fight.getUserTeam().playerFighterAtIndex((byte) _x));
    }

    private void setSubstituteForMove(Fight _fight) {
        FightFacade.setSubstituteForMove(_fight.getUserTeam().substituteAtIndex((byte) 0).first(), _fight.getUserTeam().playerFighterAtIndex((byte) 1).first().getFighter());
    }

}
