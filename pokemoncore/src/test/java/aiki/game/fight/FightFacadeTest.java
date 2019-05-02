package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import aiki.game.fight.actions.*;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
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
import code.util.EntryCust;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;


public class FightFacadeTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    @Test
    public void newFight1Test() {
        Fight fight_ = FightFacade.newFight();
        assertEq(FightType.NOTHING,fight_.getFightType());
        assertEq(Rate.zero(),fight_.getWinningMoney());
        assertTrue(!fight_.getSimulation());
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(0, fight_.getUsedItemsWhileRound().size());
    }

    @Test
    public void newFight2Test() {
        Fight fight_ = FightFacade.newFight();
        fight_.getKos().put(Fight.PLAYER,false);
        fight_.getKos().put(Fight.FOE,false);
        assertEq(FightType.NOTHING,fight_.getFightType());
        assertEq(Rate.zero(),fight_.getWinningMoney());
        assertTrue(!fight_.getSimulation());
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(0, fight_.getUsedItemsWhileRound().size());
        assertTrue(FightFacade.stopSimulation(fight_));
    }
    @Test
    public void initFightGymLeader1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightGymTrainer1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightTrainerMultiFights1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, diff_, trainer_, 0, _data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightTrainerLeague1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data_);
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightDualFight1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        assertEq(2, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
        assertEq(4, fight_.getUserTeam().getMembers().size());
        assertEq(2, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void initFightPokemon1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, diff_, foePokemon_, _data_);
        assertEq(2, fight_.getUserTeam().getMembers().size());
        assertEq(1, fight_.getFoeTeam().getMembers().size());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getMult());
        assertEq(1, fight_.getPlayerMaxNumberFrontFighters());
    }

    @Test
    public void fightersBelongingToUser1Test(){
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
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
        FightInitialization.initUserTeam(fight_,player_, diff_, dual_, _data_);
        FightInitialization.initEquipeTmpTrainer(fight_,player_, diff_, dual_, _data_);
        EqList<TeamPosition> list_ = FightFacade.fightersBelongingToUser(fight_,true);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ZERO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_ONE));
        list_ = FightFacade.fightersBelongingToUser(fight_,false);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_TWO));
        assertTrue(list_.containsObj(POKEMON_PLAYER_FIGHTER_THREE));
    }

    private static Fight koTeam(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,false,_data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(_diff);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void koTeam1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_);
        assertTrue(!FightFacade.koTeam(fight_));
    }

    @Test
    public void koTeam2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_);
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.koTeam(fight_));
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightFacade.koTeam(fight_));
    }

    @Test
    public void koTeam3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = koTeam(diff_);
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(FightFacade.koTeam(fight_));
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightFacade.koTeam(fight_));
    }

    private static Fight win(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void win1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_);
        assertTrue(!FightFacade.win(fight_));
    }

    @Test
    public void win2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.win(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightFacade.win(fight_));
    }

    @Test
    public void win3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.win(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.win(fight_));
    }

    @Test
    public void win4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = win(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.win(fight_));
    }

    private static Fight loose(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void loose1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = loose(diff_);
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void loose2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = loose(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.loose(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void loose3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = loose(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.loose(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightFacade.loose(fight_));
    }

    @Test
    public void loose4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = loose(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.loose(fight_));
    }

    private static Fight equality(Difficulty _diff) {
        Player player_ = new Player(NICKNAME,null,_diff,true,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data_);
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
        FightFacade.initFight(fight_, player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void equality1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = equality(diff_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = equality(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.equality(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = equality(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightFacade.equality(fight_));
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightFacade.equality(fight_));
    }

    @Test
    public void equality4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = equality(diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightFacade.equality(fight_));
    }

    private static Fight defaultChoices(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void defaultChoices1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = defaultChoices(partnersMoves_, foesMoves_, player_, diff_);
        FightEndRound.calculateNewLevel(fight_, player_, diff_, _data_);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(0, map_.size());
    }

    @Test
    public void defaultChoices2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = defaultChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, _data_);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(1, map_.size());
        assertTrue(map_.contains((byte) 0));
        assertTrue(!map_.contains((byte) 1));
        ChoiceOfEvolutionAndMoves moves_ = map_.getVal((byte) 0);
        assertEq(4,moves_.getKeptMoves().size());
        assertTrue(moves_.getKeptMoves().containsObj(ECUME));
        assertTrue(moves_.getKeptMoves().containsObj(HYPNOSE));
        assertTrue(moves_.getKeptMoves().containsObj(PISTOLET_A_O));
        assertTrue(moves_.getKeptMoves().containsObj(TORGNOLES));
    }

    @Test
    public void defaultChoices3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = defaultChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.calculateNewLevel(fight_, player_, diff_, _data_);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> map_;
        map_ = FightFacade.defaultChoices(fight_);
        assertEq(1, map_.size());
        assertTrue(map_.contains((byte) 0));
        assertTrue(!map_.contains((byte) 1));
        ChoiceOfEvolutionAndMoves moves_ = map_.getVal((byte) 0);
        assertTrue(moves_.getKeptMoves().containsObj(PISTOLET_A_O));
        assertTrue(moves_.getKeptMoves().containsObj(TORGNOLES));
        assertTrue(moves_.getKeptMoves().containsObj(DANSE_PLUIE));
        assertTrue(moves_.getKeptMoves().containsObj(PLAQUAGE));
    }

    private static Fight possibleChoices(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void possibleChoices1Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices2Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        moves_.removeObj(HYPNOSE);
        moves_.add(DANSE_PLUIE);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices3Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_,diff_, _data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        moves_.removeObj(HYPNOSE);
        assertTrue(!FightFacade.possibleChoices(fight_, _data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices4Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("18"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("18"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        StringList moves_ = fight_.getChoices().getVal((byte) 0).getKeptMoves();
        moves_.add(DANSE_PLUIE);
        assertTrue(!FightFacade.possibleChoices(fight_, _data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices5Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices6Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        choice_.setAbility(ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices7Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        choice_.setAbility(ABSORB_EAU);
        choice_.getKeptMoves().removeObj(DANSE_PLUIE);
        choice_.getKeptMoves().add(BULLES_D_O);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    @Test
    public void possibleChoices8Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(TETARTE);
        assertTrue(!FightFacade.possibleChoices(fight_, _data_));
        assertTrue(fight_.isError());
    }

    @Test
    public void possibleChoices9Test(){
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(NINGALE);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = possibleChoices(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        ChoiceOfEvolutionAndMoves choice_ = fight_.getChoices().getVal((byte) 0);
        choice_.setName(MUNJA);
        choice_.getKeptMoves().clear();
        choice_.getKeptMoves().add(TUNNEL);
        choice_.getKeptMoves().add(OMBRE_PORTEE);
        choice_.getKeptMoves().add(GRIFFE);
        choice_.getKeptMoves().add(GRIFFE_ACIER);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        assertTrue(!fight_.isError());
    }

    private static Fight fighterMoves(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void fighterMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, POKEMON_PLAYER_FIGHTER_ONE, _data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void fighterMoves3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.fighterMoves(fight_, f_, _data_);
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

    private static Fight frontFighterMoves(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void frontFighterMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = frontFighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 0, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = frontFighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 1, _data_);
        assertEq(0, moves_.size());
    }

    @Test
    public void frontFighterMoves3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = frontFighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.frontFighterMoves(fight_, (byte) 2, _data_);
        assertEq(0, moves_.size());
    }

    private static Fight backFighterMoves(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void backFighterMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = backFighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (short) 25);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.backFighterMoves(fight_, (byte) 0, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = backFighterMoves(partnersMoves_, foesMoves_, player_, diff_);
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = FightFacade.backFighterMoves(fight_, (byte) 1, _data_);
        assertEq(0, moves_.size());
    }

    private static Fight initChosableTargets(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void initChosableTargets1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initChosableTargets(fight_, (byte) 0, SEISME, diff_, _data_);
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void initChosableTargets2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initChosableTargets(fight_, (byte) 0, INTERVERSION, diff_, _data_);
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(!fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initChosableTargets(fight_, (byte) 0, PISTOLET_A_O, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RISQUE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initChosableTargets(fight_, (byte) 0, RISQUE, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.initChosableTargets(fight_, (byte) 0, SIPHON, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.initChosableTargets(fight_, (byte) 0, SIPHON, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initChosableTargets(fight_, (byte) 0, PISTOLET_A_O, diff_, _data_);
        assertEq(1, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertEq(1, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
    }

    @Test
    public void initChosableTargets8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(ACUPRESSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.initChosableTargets(fight_, (byte) 0, ACUPRESSION, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightFacade.initChosableTargets(fight_, (byte) 0, INTERVERSION, diff_, _data_);
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(!fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void initChosableTargets10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initChosableTargets(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.initChosableTargets(fight_, (byte) 2, SIPHON, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(!fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseFrontFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getChosenSubstitute());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_FOE_TARGET_ZERO);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().last());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(0, fight_.getChosenFoeTarget());
        assertEq(PISTOLET_A_O, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMoveTarget(PISTOLET_A_O, POKEMON_PLAYER_TARGET_ONE);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().last());
        assertEq(1, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(PISTOLET_A_O, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.MOVE, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        ChosenMoveInfos infos_;
        assertEq(1, moves_.size());
        infos_ = moves_.getVal(SEISME);
        assertEq(1,infos_.getTypes().size());
        assertEq(SEISME,infos_.getName());
        assertEq(SOL,infos_.getTypes().first());
        assertTrue(infos_.isUsable());
        assertEq(10, infos_.getUses().getCurrent());
        assertEq(10, infos_.getUses().getMax());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(SEISME, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.SWITCH, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> initMoves_ = new StringMap<Short>();
        initMoves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,initMoves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setChosenHealingObject(EAU_FRAICHE, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.HEALING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(EAU_FRAICHE, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).usePowerPointsByMove(diff_, ECUME, (short) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        assertEq(0, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(4, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.MOVE, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.SWITCH, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(2));
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(3));
        assertEq(ActionType.HEALING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(ECUME, fight_.getChosenMoveFront());
        assertEq(HUILE, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseFrontFighter(fight_,(byte) 2, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 2, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) -1, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void chooseFrontFighter13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        FightFacade.chooseFrontFighter(fight_,(byte) -1, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    private static Fight changeAction(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void changeAction1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = changeAction(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getSelectedActionCurFighter());
    }

    @Test
    public void changeAction2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = changeAction(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.changeAction(fight_, ActionType.MOVE, _data_);
        assertEq(1, fight_.getCurrentFighterMoves().size());
        assertEq(ActionType.MOVE, fight_.getSelectedActionCurFighter());
    }

    @Test
    public void changeAction3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = changeAction(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        fight_.setChosenIndexBack(Fighter.BACK);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getSelectedActionCurFighter());
    }

    @Test
    public void changeAction4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = changeAction(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setChosenIndexFront(Fighter.BACK);
        fight_.setChosenIndexBack(Fighter.BACK);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(ActionType.SWITCH, fight_.getSelectedActionCurFighter());
    }

    @Test
    public void setSubstituteSwitch1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionSwitch);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
    }

    @Test
    public void setSubstituteSwitch2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 1);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setSubstituteSwitch3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.setChosenIndexFront((byte) 2);
        FightFacade.setSubstituteSwitch(fight_, (byte) 1);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setSubstituteForMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(RELAIS);
        FightFacade.setSubstituteForMove(fight_, (byte) 1, (byte) 0);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setChosenIndexFront((byte) 1);
        FightFacade.chooseBackFighterAddon(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.setChosenIndexFront((byte) 1);
        FightFacade.chooseBackFighterAddon(fight_, (byte) 0, _data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.setChosenIndexFront((byte) 1);
        FightFacade.chooseBackFighterAddon(fight_, (byte) -1, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterAddon4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)3,new StringList(CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(CHARGE)));
        partnersMoves_.add(new LevelMoves((short)3,new StringList(CHARGE)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.setChosenIndexFront((byte) 1);
        FightFacade.chooseBackFighterAddon(fight_, (byte) 2, _data_);
        assertTrue(fight_.isError());
    }

    @Test
    public void chooseBackFighterWhileRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        FightFacade.chooseBackFighterWhileRound(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterWhileRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        FightFacade.chooseBackFighterWhileRound(fight_, (byte) -1, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighterWhileRound3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        FightFacade.chooseBackFighterWhileRound(fight_, (byte) 0, _data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void cancelChooseBackFighterWhileRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        FightFacade.chooseBackFighterWhileRound(fight_, (byte) 0, _data_);
        FightFacade.cancelChooseBackFighterWhileRound(fight_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void validateSwitch1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.validateSwitch(fight_);
        Fighter fighter_ = fight_.getFighter(userOne_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_,(byte) 1, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertTrue(action_ instanceof ActionSwitch);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightFacade.chooseBackFighter(fight_,(byte) 0, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(0, fight_.getChosenIndexBack());
        assertEq(2, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(0, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setRemainedHp(Rate.one());
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(EAU_FRAICHE, _data_);
        FightFacade.chooseBackFighter(fight_,(byte) 0, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(0, fight_.getChosenIndexBack());
        assertEq(2, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(EAU_FRAICHE, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (byte) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseBackFighter(fight_,(byte) 0, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(0, fight_.getChosenIndexBack());
        assertEq(2, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getPossibleActionsCurFighter().get(0));
        assertEq(ActionType.HEALING, fight_.getPossibleActionsCurFighter().get(1));
        assertEq(ActionType.HEALING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(HUILE, fight_.getChosenHealingMove());
        assertEq(ECUME, fight_.getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
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
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, RELAIS, diff_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
    }

    @Test
    public void chooseBackFighter9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DANSE_LUNE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DANSE_LUNE, diff_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(DANSE_LUNE,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(2, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseBackFighter(fight_,(byte) 1, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightFacade.chooseBackFighter(fight_,(byte) 0, _data_);
        FightFacade.chooseBackFighter(fight_,(byte) -1, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)3);
        foePokemon_.setMoves(new StringList(DETECTION,CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)3);
        allyPokemon_.setMoves(new StringList(DETECTION,CHARGE));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_,player_, diff_, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).usePowerPointsByMove(diff_, ECUME, (byte) 25);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObjectMove(HUILE, ECUME);
        FightFacade.chooseBackFighter(fight_,(byte) 0, _data_);
        FightFacade.chooseBackFighter(fight_,(byte) -1, _data_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
        assertEq(0, fight_.getPossibleActionsCurFighter().size());
        assertEq(ActionType.NOTHING, fight_.getSelectedActionCurFighter());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(Fighter.BACK, fight_.getChosenPlayerTarget());
        assertEq(Fighter.BACK, fight_.getChosenFoeTarget());
    }

    @Test
    public void chooseBackFighter13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userOne_).setFirstChosenMove(ECUME);
        fight_.setCurrentUser(userOne_);
        fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) -1, _data_);
        assertTrue(!fight_.isError());
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(NULL_REF, fight_.getChosenMoveFront());
    }

    @Test
    public void chooseBackFighter14Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getRemainingHp().affectZero();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, RELAIS, diff_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 1, _data_);
        assertTrue(fight_.isError());
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RELAIS,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(0, fight_.getCurrentFighterMoves().size());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(RELAIS, fight_.getChosenMoveFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
    }

    @Test
    public void setFirstChosenMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.setFirstChosenMove(fight_, (byte) 1, SEISME);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(SEISME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setFirstChosenMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.setFirstChosenMove(fight_, (byte) 2, SEISME);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setChosenHealingItemFront1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, EAU_FRAICHE, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }


    @Test
    public void setChosenHealingItemFront2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, ELIXIR, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(ELIXIR, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(ELIXIR,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_MEPO, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(BAIE_MEPO, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, HUILE, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, HUILE_MAX, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE_MAX, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemFront6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_ORAN, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BAIE_ORAN, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(BAIE_ORAN,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setChosenHealingItemFront7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        fight_.setChosenIndexFront((byte) 2);
        FightFacade.setChosenHealingItemFront(fight_, BAIE_ORAN, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, EAU_FRAICHE, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, BAIE_ORAN, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(BAIE_ORAN, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(BAIE_ORAN,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, ELIXIR, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(ELIXIR, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(ELIXIR,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, HUILE, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, BAIE_MEPO, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(BAIE_MEPO, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemBack6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItemBack(fight_, HUILE_MAX, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE_MAX, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    //en: case of error by developing
    //fr: cas d'erreur de developpement
    @Test
    public void setChosenHealingItemBack7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        fight_.setChosenIndexBack((byte) 2);
        FightFacade.setChosenHealingItemBack(fight_, HUILE_MAX, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).setRemainedHp(Rate.one());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, EAU_FRAICHE, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).setRemainedHp(Rate.one());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, EAU_FRAICHE, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertTrue(!((ActionSimpleHeal)action_).isTeam());
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(EAU_FRAICHE,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItem4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, _data_);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void setChosenHealingItemMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 1);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, _data_);
        FightFacade.setChosenHealingItemMove(fight_, PISTOLET_A_O);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
    }

    @Test
    public void setChosenHealingItemMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition user_ = POKEMON_PLAYER_FIGHTER_TWO;
        fight_.getFighter(user_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 1);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, _data_);
        FightFacade.setChosenHealingItemMove(fight_, PISTOLET_A_O);
        Fighter fighter_ = fight_.getFighter(user_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
    }

    private static Fight chooseMove(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void chooseMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, SEISME, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(SEISME, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
    }

    @Test
    public void chooseMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ONE, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
//        assertEq(2, fight_.getChosableFoeTargets().size());
//        assertTrue(!fight_.getChosableFoeTargets().first());
//        assertTrue(!fight_.getChosableFoeTargets().last());
//        assertEq(2, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
//        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(2, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(2, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RISQUE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, RISQUE, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(RISQUE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
//        assertEq(2, fight_.getChosableFoeTargets().size());
//        assertTrue(fight_.getChosableFoeTargets().first());
//        assertTrue(!fight_.getChosableFoeTargets().last());
//        assertEq(2, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
//        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, SIPHON, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SIPHON, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, SIPHON, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(!fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(!fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(!fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(PISTOLET_A_O, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
//        assertEq(1, fight_.getChosableFoeTargets().size());
//        assertTrue(fight_.getChosableFoeTargets().first());
//        assertEq(1, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
    }

    @Test
    public void chooseMove8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(ACUPRESSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_, 3);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, ACUPRESSION, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(3, fight_.getChosableFoeTargets().size());
        assertTrue(fight_.getChosableFoeTargets().first());
        assertTrue(fight_.getChosableFoeTargets().get(1));
        assertTrue(fight_.getChosableFoeTargets().last());
        assertEq(3, fight_.getChosablePlayerTargets().size());
        assertTrue(fight_.getChosablePlayerTargets().first());
        assertTrue(fight_.getChosablePlayerTargets().get(1));
        assertTrue(fight_.getChosablePlayerTargets().last());
    }

    @Test
    public void chooseMove9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = chooseMove(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, _data_);
        AbstractAction action_;
        action_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
//        assertEq(1, fight_.getChosableFoeTargets().size());
//        assertTrue(!fight_.getChosableFoeTargets().first());
//        assertEq(1, fight_.getChosablePlayerTargets().size());
//        assertTrue(!fight_.getChosablePlayerTargets().first());
    }

    @Test
    public void chooseMove10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.HEALING, _data_);
        FightFacade.setChosenHealingItem(fight_, HUILE, _data_);
        FightFacade.chooseMove(fight_, ECUME, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionHealMove);
        assertEq(ECUME,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(HUILE,fighter_.getChosenHealingItem());
        assertEq(HUILE, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
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
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(ECUME,fight_.getChosenMoveFront());
    }

    @Test
    public void chooseMove11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        FightFacade.chooseMove(fight_, ECUME, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(NULL_REF,fight_.getChosenMoveFront());
    }

    @Test
    public void chooseMove12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        fight_.getFighter(userTwo_).usePowerPointsByMove(diff_, ECUME, (short) 2);
        fight_.setSelectedActionCurFighter(ActionType.MOVE);
        fight_.setChosenIndexFront((byte) 2);
        FightFacade.chooseMove(fight_, ECUME, diff_, _data_);
        Fighter fighter_ = fight_.getFighter(userTwo_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
        assertEq(NULL_REF,fighter_.getChosenHealingItem());
        assertEq(NULL_REF, fight_.getChosenHealingMove());
        NatStringTreeMap< ChosenMoveInfos> moves_;
        moves_ = fight_.getCurrentFighterMoves();
        assertEq(0, moves_.size());
        assertEq(0, fight_.getChosableFoeTargets().size());
        assertEq(0, fight_.getChosablePlayerTargets().size());
        assertEq(NULL_REF,fight_.getChosenMoveFront());
    }

    private static Fight allowedMovesNotEmpty(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void allowedMovesNotEmpty1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = allowedMovesNotEmpty(partnersMoves_, foesMoves_, player_, diff_);
        StringList moves_ = FightFacade.allowedMovesNotEmpty(fight_, POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(4, moves_.size());
        assertTrue(moves_.containsObj(ECUME));
        assertTrue(moves_.containsObj(PISTOLET_A_O));
        assertTrue(moves_.containsObj(TORGNOLES));
        assertTrue(moves_.containsObj(HYPNOSE));
    }

    @Test
    public void allowedMovesNotEmpty2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(PISTOLET_A_O, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(ECUME,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = allowedMovesNotEmpty(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition f_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(f_).usePowerPointsByMove(diff_, PISTOLET_A_O, (short) 50);
        StringList moves_ = FightFacade.allowedMovesNotEmpty(fight_, f_, _data_);
        assertEq(1, moves_.size());
        assertTrue(moves_.containsObj(LUTTE));
    }

    private static Fight remainingThrowersTargetsHp(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void remainingThrowersTargetsHp1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = remainingThrowersTargetsHp(partnersMoves_, foesMoves_, player_, diff_);
        ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>> map_;
        map_ = FightFacade.remainingThrowersTargetsHp(fight_, diff_, _data_);
        assertEq(2, map_.size());
        assertEq(3, map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).size());
        assertEq(3, map_.getVal(POKEMON_PLAYER_FIGHTER_ONE).size());
        StringMap<ObjectMap<TeamPosition,Rate>> mapMoves_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(3, mapMoves_.getVal(PISTOLET_A_O).size());
        assertEq(3, mapMoves_.getVal(ECUME).size());
        assertEq(3, mapMoves_.getVal(TORGNOLES).size());
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(3, mapMoves_.getVal(PISTOLET_A_O).size());
        assertEq(3, mapMoves_.getVal(ECUME).size());
        assertEq(3, mapMoves_.getVal(TORGNOLES).size());
        ObjectMap<TeamPosition,Rate> mapFighters_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        mapFighters_ = mapMoves_.getVal(PISTOLET_A_O);
        assertEq(3, mapFighters_.size());
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(ECUME);
        assertEq(3, mapFighters_.size());
        assertEq(new Rate("16909/5200"), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(TORGNOLES);
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        mapFighters_ = mapMoves_.getVal(PISTOLET_A_O);
        assertEq(3, mapFighters_.size());
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(ECUME);
        assertEq(3, mapFighters_.size());
        assertEq(new Rate("16909/5200"), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(TORGNOLES);
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("4587/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
    }

    private static Fight choice(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void setFirstChosenMoveFoeTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(PISTOLET_A_O,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(POKEMON_FOE_TARGET_ZERO));
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void setFirstChosenMovePlayerTarget1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 3);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ONE;
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, _data_);
        FightFacade.chooseMove(fight_, INTERVERSION, diff_, _data_);
        FightFacade.setFirstChosenMovePlayerTarget(fight_, (byte) 0);
        Fighter fighter_ = fight_.getFighter(userOne_);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof ActionMove);
        assertEq(INTERVERSION,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(1, targets_.size());
        assertTrue(targets_.containsObj(POKEMON_PLAYER_TARGET_ZERO));
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    @Test
    public void sortedFightersUsingMoveDependingOnPlayerChoices1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_.setLevel((short) 15);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        FightFacade.setFirstChosenMove(fight_, (byte) 0, SEISME);
        FightFacade.setFirstChosenMove(fight_, (byte) 1, SEISME);
        TreeMap<TeamPosition,ActionMove> tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)15,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        FightFacade.setFirstChosenMove(fight_, (byte) 0, SEISME);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        TreeMap<TeamPosition,ActionMove> tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, _data_);
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
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction() instanceof Action);
    }

    @Test
    public void sortedFightersBeginRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_.setLevel((short) 15);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        FightFacade.setFirstChosenMove(fight_, (byte) 0, SEISME);
        FightFacade.setFirstChosenMove(fight_, (byte) 1, SEISME);
        EqList<TeamPosition> tree_;
        tree_ = FightFacade.sortedFightersBeginRound(fight_, _data_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)15,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        FightFacade.setFirstChosenMove(fight_, (byte) 0, SEISME);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        EqList<TeamPosition> tree_;
        tree_ = FightFacade.sortedFightersBeginRound(fight_, _data_);
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
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction() instanceof Action);
    }

    @Test
    public void sortedFightersBeginRound3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(TARTARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_.setLevel((short) 14);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(SEISME);
        partnersMoves_.add(new LevelMoves((short)18,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)15,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)4,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(DETECTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setFirstChosenMove(DETECTION);
        FightFacade.setFirstChosenMove(fight_, (byte) 0, SEISME);
        fight_.getAllyChoice().put(new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)), new MoveTarget(SEISME, new TargetCoords(Fighter.BACK,Fighter.BACK)));
        TreeMap<TeamPosition,ActionMove> tree_;
        tree_ = FightFacade.sortedFightersUsingMoveDependingOnPlayerChoices(fight_, _data_);
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
        assertTrue(fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR).getAction() instanceof Action);
    }

    @Test
    public void setSubstituteFront1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void setSubstituteFront2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void setSubstituteFront3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteFront4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteFront5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 1, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
//        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
//        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteFront6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
//        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
//        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteFront7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, (byte) 1);
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
//        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteFront8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        fight_.getFirstPositPlayerFighters().put((byte) 0, (byte) 1);
        fight_.getFirstPositPlayerFighters().put((byte) 1, (byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        fight_.setChosenIndexFront((byte) 2);
        FightFacade.setSubstituteFront(fight_, (byte) 1);
        assertEq(1, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 2).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 3).intValue());
    }

    @Test
    public void setSubstituteBack1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void setSubstituteBack2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        fight_.setChosenIndexBack((byte) 2);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
    }

    @Test
    public void setSubstituteEndRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteEndRound(fight_, Fighter.BACK);
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(Fighter.BACK, fight_.getChosenSubstitute());
    }

    @Test
    public void setSubstituteEndRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.setSubstituteEndRound(fight_, (byte) 0);
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 0).intValue());
        assertEq(0, fight_.getFirstPositPlayerFighters().getVal((byte) 1).intValue());
        assertEq(0, fight_.getChosenSubstitute());
    }

    @Test
    public void getMoves1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        NatStringTreeMap<Boolean> map_ = FightFacade.getMoves(fight_, (byte) 0, TARINORME);
        assertEq(12, map_.size());
        assertTrue(map_.getVal(DETECTION));
        assertTrue(map_.getVal(ULTRASON));
        assertTrue(map_.getVal(BROUHAHA));
        assertTrue(map_.getVal(POURSUITE));
        assertTrue(!map_.getVal(CHARGE));
        assertTrue(!map_.getVal(GRAVITE));
        assertTrue(!map_.getVal(VOL_MAGNETIK));
        assertTrue(!map_.getVal(MUR_DE_FER));
        assertTrue(!map_.getVal(BOMBAIMANT));
        assertTrue(!map_.getVal(REGARD_NOIR));
        assertTrue(!map_.getVal(CAGE_ECLAIR));
        assertTrue(!map_.getVal(EBOULEMENT));
    }

    @Test
    public void getEvolutions1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        TreeMap<String,Boolean> map_ = FightFacade.getEvolutions(fight_, (byte) 0, _data_);
        assertEq(2, map_.size());
        assertTrue(map_.getVal(NULL_REF));
        assertTrue(!map_.getVal(TARINORME));
    }

    @Test
    public void getAbilities1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        StringList list_ = FightFacade.getAbilities(fight_, (byte) 0, TARINORME);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj(MAGNEPIEGE));
        assertTrue(list_.containsObj(FERMETE));
    }

    @Test
    public void isChosableForLearningAndEvolving1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        assertTrue(FightFacade.isChosableForLearningAndEvolving(fight_, (byte) 0));
    }

    @Test
    public void isChosableForLearningAndEvolving2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        assertTrue(!FightFacade.isChosableForLearningAndEvolving(fight_, (byte) -1));
    }

    @Test
    public void isChosableForLearningAndEvolving3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        assertTrue(!FightFacade.isChosableForLearningAndEvolving(fight_, (byte) -1));
    }

    @Test
    public void choosePokemonForLearningAndEvolving1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        assertEq(0, fight_.getChosenIndex());
    }

    @Test
    public void choosePokemonForLearningAndEvolving2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) -1, _data_);
        assertEq(-1, fight_.getChosenIndex());
    }

    @Test
    public void choosePokemonForLearningAndEvolving3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 10);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, _data_);
        assertEq(-1, fight_.getChosenIndex());
    }

    @Test
    public void setEvolution1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(ULTRASON));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POURSUITE));
    }

    @Test
    public void setEvolution2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.setEvolution(fight_, NULL_REF);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(NULL_REF, choice_.getName());
        assertEq(METEO, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(ULTRASON));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POURSUITE));
    }

    @Test
    public void setAbility1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.setAbility(fight_, MAGNEPIEGE);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(MAGNEPIEGE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(ULTRASON));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POURSUITE));
    }

    @Test
    public void setAbility2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 10);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, _data_);
        FightFacade.setAbility(fight_, MAGNEPIEGE);
        assertEq(-1, fight_.getChosenIndex());
    }

    @Test
    public void addOrForgetMove1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.addOrForgetMove(fight_, ULTRASON);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(3, list_.size());
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POURSUITE));
    }

    @Test
    public void addOrForgetMove2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowedSwitchPlacesEndRound(false);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setHappiness((short) 140);
        lasPk_.setWonExpSinceLastLevel(new Rate("1"));
        player_.getTeam().add(lasPk_);
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TARINORME);
        FightFacade.addOrForgetMove(fight_, ULTRASON);
        FightFacade.addOrForgetMove(fight_, BOMBAIMANT);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> choices_ = fight_.getChoices();
        ChoiceOfEvolutionAndMoves choice_ = choices_.getVal((byte) 0);
        assertEq(TARINORME, choice_.getName());
        assertEq(FERMETE, choice_.getAbility());
        StringList list_ = choice_.getKeptMoves();
        assertEq(4, list_.size());
        assertTrue(list_.containsObj(BOMBAIMANT));
        assertTrue(list_.containsObj(DETECTION));
        assertTrue(list_.containsObj(BROUHAHA));
        assertTrue(list_.containsObj(POURSUITE));
    }

    @Test
    public void getPlayerFrontTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getAllyFrontTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getAllyFrontTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE));
    }

    @Test
    public void getUnionFrontTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userOne_ = POKEMON_PLAYER_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_PLAYER_FIGHTER_THREE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getUnionFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerBackTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeam2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_,(byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_,(byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!fight_.isError());
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeam3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_,(byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!fight_.isError());
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerTeam(fight_);
        assertEq(3, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 2), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getAllyBackTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getAllyBackTeam(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_FOUR));
    }

    @Test
    public void getFoeFrontTeam1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        TeamPosition userOne_ = POKEMON_FOE_FIGHTER_ZERO;
        TeamPosition userTwo_ = POKEMON_FOE_FIGHTER_ONE;
        fight_.getFighter(userOne_).setGroundPlace((byte) 1);
        fight_.getFighter(userOne_).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(userTwo_).setGroundPlace((byte) 0);
        fight_.getFighter(userTwo_).setGroundPlaceSubst((byte) 0);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getFoeFrontTeam(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_FOE_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeamForSubstituting(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeamForSubstituting(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeamForSubstituting(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeamForSubstituting(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }

    @Test
    public void getPlayerFrontTeamForSubstituting5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerFrontTeamForSubstituting(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeamForSubstituting(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeamForSubstituting(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeamForSubstituting(fight_);
        assertEq(2, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE));
        assertSame(mapFighters_.getVal((byte) 1), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void getPlayerBackTeamForSubstituting4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        NatTreeMap<Byte, Fighter> mapFighters_ = FightFacade.getPlayerBackTeamForSubstituting(fight_);
        assertEq(1, mapFighters_.size());
        assertSame(mapFighters_.getVal((byte) 0), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO));
    }

    @Test
    public void deselect1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.setChosenIndexBack((byte) 0);
        FightFacade.deselect(fight_);
        assertEq(Fighter.BACK, fight_.getChosenIndexBack());
    }

    @Test
    public void deselect2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(RELAIS, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = choice(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.setChosenIndexFront((byte) 0);
        FightFacade.deselect(fight_);
        assertEq(Fighter.BACK, fight_.getChosenIndexFront());
    }

    private static Fight frontFighterChoiceFleeingCatching(
            WildPk _pk,
            Player _player,
            Difficulty _diff) {
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, _pk, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void frontFighterChoiceFleeingCatching1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = frontFighterChoiceFleeingCatching(pokemon_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_,(byte) 0);
        FightFacade.frontFighterChoiceFleeingCatching(fight_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        AbstractAction action_ = fighter_.getAction();
        assertTrue(action_ instanceof Action);
        assertEq(NULL_REF,fighter_.getFirstChosenMove());
        assertEq(NULL_REF, fighter_.getFinalChosenMove());
        EqList<TargetCoords> targets_ = fighter_.getChosenTargets();
        assertEq(0, targets_.size());
        assertEq(Fighter.BACK, fighter_.getSubstistute());
    }

    private static Fight beginRound(
            WildPk _pk,
            Player _player,
            Difficulty _diff) {
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, _pk, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    private static Fight beginRound(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void beginRound1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = beginRound(pokemon_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void beginRound2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void beginRound3Test() {

        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = beginRound(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    private static Fight roundAllThrowersChooseActionsFoe(
            WildPk _pk,
            Player _player,
            Difficulty _diff) {
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, _pk, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    private static Fight roundAllThrowersChooseActionsFoe(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void roundAllThrowersChooseActionsFoe1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = roundAllThrowersChooseActionsFoe(pokemon_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = roundAllThrowersChooseActionsFoe(pokemon_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = roundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter( POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = roundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void roundAllThrowersChooseActionsFoe1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = roundAllThrowersChooseActionsFoe(pokemon_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.roundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_);
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    //endRoundFightBasic
    private static Fight endRoundFightBasic(
            WildPk _pk,
            Player _player,
            Difficulty _diff) {
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, _pk, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    private static Fight endRoundFightBasic(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void endRoundFightBasic1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        Fight fight_ = endRoundFightBasic(pokemon_, player_, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endRoundFightBasic(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = endRoundFightBasic(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        Fight fight_ = endRoundFightBasic(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        fighter_ = fight_.getFighter( POKEMON_PLAYER_FIGHTER_ZERO);
        FightFacade.chooseFrontFighter(fight_,fighter_.getGroundPlace(), diff_, _data_);
        FightFacade.changeAction(fight_, ActionType.SWITCH, _data_);
        FightFacade.setSubstituteSwitch(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = endRoundFightBasic(pokemon_, player_, diff_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.getAcceptableChoices());
    }

    @Test
    public void endRoundFightBasic1SimulationTest() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 15);
        Fight fight_ = endRoundFightBasic(pokemon_, player_, diff_);
        fight_.setSimulation(true);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, OEIL_MIRACLE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, PASSE_PASSE, (short) 50);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).usePowerPointsByMove(diff_, ORAGE, (short) 50);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertTrue(!fight_.getAcceptableChoices());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    private static Fight regularRoundAllThrowersChooseActionsFoe(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(fight_.getBeginRound());
        assertTrue(fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(fight_.isError());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, true);
        assertTrue(fight_.getBeginRound());
        assertTrue(fight_.isError());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, true);
        assertTrue(!fight_.isError());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        assertTrue(!fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!fight_.isError());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void regularRoundAllThrowersChooseActionsFoe9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(DEMI_TOUR,(short) 20);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(DETECTION,CHARGE);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION,CHARGE);
        foesMoves_.add(new LevelMoves((short)12,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = regularRoundAllThrowersChooseActionsFoe(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        TeamPosition foePk_ = POKEMON_FOE_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(foePk_);
        fighter_.setFirstChosenMoveTarget(CHARGE, POKEMON_PLAYER_TARGET_ZERO);
        fight_.getAllyChoice().put(new MoveTarget(PISTOLET_A_O,POKEMON_FOE_TARGET_ZERO), new MoveTarget(CHARGE,POKEMON_FOE_TARGET_ZERO));
        assertTrue(!fight_.isError());
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(fight_.isError());
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertTrue(!fight_.isError());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
    }

    private static Fight learnAndEvolveAttack(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void learnAndEvolveAttack1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void learnAndEvolveAttack2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, PISTOLET_A_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 17);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 1, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, PISTOLET_A_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        //FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("25"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    @Test
    public void learnAndEvolveAttack10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
//        pokemon_.setLevel((short) 24);
        pokemon_.setLevel((short) 100);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        //lasPk_.setWonExpSinceLastLevel(new Rate("25"));
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = learnAndEvolveAttack(partnersMoves_, foesMoves_, player_, diff_);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp().affect(new Rate("50"));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setGroundPlaceSubst((byte) 0);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightEndRound.proponeMovesEvolutions(fight_, player_, diff_, _data_);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, _data_);
        FightFacade.setEvolution(fight_, TETARTE);
//        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
//        FightFacade.addOrForgetMove(fight_, BULLES_D_O);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, _data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, _data_);
        assertTrue(!fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).estKo());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
//        assertEq(FightState.ATTAQUES, fight_.getState());
        assertEq(FightState.SWITCH_PROPOSE, fight_.getState());
    }

    private static Fight sendSubstitutesChooseActions(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void sendSubstitutesChooseActions1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutesChooseActions(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutesChooseActions(partnersMoves_, foesMoves_, player_, diff_);
        fight_.getUserTeam().ajouterEffetEquipeEntreeAdv(PICOTS);
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).setRemainedHp(Rate.one());
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        assertTrue(FightRules.substitutable(fight_, diff_, _data_));
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, _data_);
        assertTrue(FightEndRound.proponedSwitch(fight_));
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        WildPk pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_, player_, diff_, pokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.setSubstituteFront(fight_, Fighter.BACK);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.setSubstituteBack(fight_, (byte) 0);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void sendSubstitutesChooseActions4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.getRemainingHp().affectZero();
        player_.getTeam().add(lasPk_);
        player_.getItem(RAPPEL);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = sendSubstitutesChooseActions(partnersMoves_, foesMoves_, player_, diff_, 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setChosenHealingObject(RAPPEL, _data_);
        FightFacade.beginRound(fight_, diff_, _data_);
        FightRound.roundThrowerHealing(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightArtificialIntelligence.choiceForSubstituing(fight_, _data_);
        FightFacade.chooseBackFighter(fight_, (byte) 0, _data_);
        FightFacade.setSubstituteBack(fight_, (byte) 1);
        FightFacade.sendSubstitutesChooseActions(fight_, diff_, player_, _data_);
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    private static Fight roundWhileKoPlayer(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void roundWhileKoPlayer1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TOURNIQUET);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,new StringList(JACKPOT)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, true);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void roundWhileKoPlayer4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(TOURNIQUET);
        partnersMoves_.add(new LevelMoves((short)1,partnerMoves_));
        partnersMoves_.add(new LevelMoves((short)19,new StringList(JACKPOT)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, true);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.roundUser(fight_, diff_, _data_);
        FightFacade.endRoundFightBasic(fight_, diff_, player_, _data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void roundWhileKoPlayer5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)50,new StringList(JACKPOT)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)7,foeMoves_));
        foesMoves_.add(new LevelMoves((short)7,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        partnersMoves_.add(new LevelMoves((short)50,new StringList(JACKPOT)));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)7,foeMoves_));
        foesMoves_.add(new LevelMoves((short)7,foeMoves_));
        foesMoves_.add(new LevelMoves((short)7,foeMoves_));
        Fight fight_ = roundWhileKoPlayer(partnersMoves_, foesMoves_, player_, diff_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, diff_, _data_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,_data_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, _data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    private static Fight attemptCatching(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void attemptCatching1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, false);
        assertEq(NULL_REF,fight_.getCatchingBall());
        assertTrue(FightKo.endedFight(fight_, diff_));
    }

    @Test
    public void attemptCatching2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.ATTAQUES, fight_.getState());
    }

    @Test
    public void attemptCatching3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, false);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SURNOM, fight_.getState());
    }

    @Test
    public void attemptCatching4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, true);
        assertEq(NULL_REF,fight_.getCatchingBall());
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void attemptCatching5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, true);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.ATTAQUES, fight_.getState());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void attemptCatching6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptCatching(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        FightFacade.attemptCatching(fight_, HYPER_BALL, false, diff_, player_, _data_, true);
        assertTrue(!FightKo.endedFight(fight_, diff_));
        assertEq(FightState.SURNOM, fight_.getState());
        assertTrue(!fight_.isKeepRound());
    }

    private static Fight calculateCatchingRates(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void calculateCatchingRates1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = calculateCatchingRates(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        NatStringTreeMap<BallNumberRate> rates_ = FightFacade.calculateCatchingRates(fight_, diff_, player_, _data_);
        assertEq(0, rates_.size());
    }

    @Test
    public void calculateCatchingRates2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = calculateCatchingRates(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.wildPokemon().setRemainedHp(Rate.one());
        NatStringTreeMap<BallNumberRate> rates_ = FightFacade.calculateCatchingRates(fight_, diff_, player_, _data_);
        assertEq(1, rates_.size());
        assertEq(LgInt.one(), rates_.getVal(HYPER_BALL).getNumber());
        assertEq(Rate.one(), rates_.getVal(HYPER_BALL).getRate());
        assertEq(HYPER_BALL, rates_.getVal(HYPER_BALL).getName());
        assertEq("100", rates_.getVal(HYPER_BALL).getPercent());
    }

    private static Fight sortedFightersBeginRoundWildFight(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void sortedFightersBeginRoundWildFight1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 5);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = sortedFightersBeginRoundWildFight(player_, PTITARD, (short) 1, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ECUME);
        NatStringTreeMap<EqList<TeamPosition>> map_;
        map_ = FightFacade.sortedFightersBeginRoundWildFight(fight_, _data_);
        assertEq(1, map_.size());
        assertEq(2, map_.getVal(TOURNIQUET).size());
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(0));
        assertEq(POKEMON_FOE_FIGHTER_ZERO, map_.getVal(TOURNIQUET).get(1));
    }

    @Test
    public void sortedFightersBeginRoundWildFight2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 6);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.getItem(HYPER_BALL);
        Fight fight_ = sortedFightersBeginRoundWildFight(player_, PTITARD, (short) 10, diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(ECUME);
        NatStringTreeMap<EqList<TeamPosition>> map_;
        map_ = FightFacade.sortedFightersBeginRoundWildFight(fight_, _data_);
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

    private static Fight attemptFlee(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void attemptFlee1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 14, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, false);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 14, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.setNbFleeAttempt((short) 255);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, false);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(255, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 2, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, false);
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(FightFacade.loose(fight_));
    }

    @Test
    public void attemptFlee4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 2, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, false);
        assertEq(1, fight_.getNbFleeAttempt());
        assertEq(FightState.ATTAQUES,fight_.getState());
    }

    @Test
    public void attemptFlee5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 14, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, true);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void attemptFlee6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 14, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.setNbFleeAttempt((short) 255);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, true);
        assertEq(FightState.REDESSIN_SCENE,fight_.getState());
        assertEq(255, fight_.getNbFleeAttempt());
        assertTrue(!FightFacade.loose(fight_));
        assertTrue(!fight_.isKeepRound());
    }

    @Test
    public void attemptFlee7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        lasPk_.setRemainedHp(Rate.one());
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 2, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, true);
        assertEq(1, fight_.getNbFleeAttempt());
        assertTrue(fight_.isKeepRound());
    }

    @Test
    public void attemptFlee8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(DEMI_TOUR, (short) 10);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = attemptFlee(player_, PIKACHU, (short) 2, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.attemptFlee(fight_, diff_, player_, _data_, true);
        assertEq(1, fight_.getNbFleeAttempt());
        assertEq(FightState.ATTAQUES,fight_.getState());
        assertTrue(fight_.isKeepRound());
    }

    private static Fight fightStatement(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    private static Fight fightStatement(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void fightStatement1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.CAPTURE_KO, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_,true, diff_));
    }

    @Test
    public void fightStatement8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement9Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement10Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.REDESSIN_SCENE, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement11Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.RIEN, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement12Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = fightStatement(partnersMoves_, foesMoves_, player_, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.RIEN, FightFacade.fightStatement(fight_, true, diff_));
    }

    @Test
    public void fightStatement13Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(FightState.FIN_CBT_SAUVAGE, FightFacade.fightStatement(fight_, false, diff_));
    }

    @Test
    public void keepLoop1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.keepLoop(fight_,true));
    }

    @Test
    public void keepLoop2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        fight_.getKos().put(Fight.FOE,true);
        assertTrue(!FightFacade.keepLoop(fight_,true));
    }

    @Test
    public void keepLoop3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = fightStatement(player_, PIKACHU, (short) 1, diff_);
        fight_.setState(FightState.SWITCH_PROPOSE);
        FightKo.setKo(fight_,Fight.toUserFighter((byte) 0),diff_,_data_);
        assertTrue(!FightFacade.keepLoop(fight_,false));
    }
    private static Fight endFight(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    private static Fight endFight(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        fight_.setEnvType(EnvironmentType.ROAD);
        FightSending.firstEffectWhileSendingTeams(fight_, _diff, _data_);
        return fight_;
    }

    @Test
    public void endFight1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void endFight2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endFight(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void endFight3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = endFight(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.endFight(fight_);
        assertTrue(!fight_.getFightType().isExisting());
    }

    @Test
    public void movesAfterFight1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(14, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getCurrent());
        assertEq(15, mapMoves_.getVal(TOURNIQUET).getMax());
    }

    @Test
    public void movesAfterFight5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        fight_.getFighter(playerPk_).apprendreAttaqueEcrasant(TOURNIQUET, COPIE, _data_);
        fight_.getFighter(playerPk_).usePowerPointsByMove(diff_, TOURNIQUET, (short) 1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        assertEq(1, mapMoves_.size());
        assertEq(10, mapMoves_.getVal(COPIE).getCurrent());
        assertEq(10, mapMoves_.getVal(COPIE).getMax());
    }

    @Test
    public void movesAfterFight7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
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
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(true);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> map_ = new StringMap<Short>();
        map_.put(COPIE, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,map_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
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
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        return fight_;
    }

    private static Fight initTypeEnv(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        return fight_;
    }

    @Test
    public void initTypeEnv1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initTypeEnv(player_, PIKACHU, (short) 1, diff_);
        FightFacade.initTypeEnv(fight_, _data_.getMap().getBegin(), diff_, _data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.wildPokemon().getAction() instanceof Action);
    }

    @Test
    public void initTypeEnv2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initTypeEnv(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initTypeEnv(fight_, _data_.getMap().getBegin(), diff_, _data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initTypeEnv(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initTypeEnv(fight_, _data_.getMap().getBegin(), diff_, _data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initTypeEnv(partnersMoves_, foesMoves_, player_, diff_);
        FightFacade.initTypeEnv(fight_, new Coords(), diff_, _data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }

    @Test
    public void initTypeEnv5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initTypeEnv(partnersMoves_, foesMoves_, player_, diff_);
        Coords c_ = new Coords();
        c_.setNumberPlace((byte)1);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setPoint(new Point((byte)-1,(byte)0));
        FightFacade.initTypeEnv(fight_, c_, diff_, _data_);
        assertEq(EnvironmentType.ROAD, fight_.getEnvType());
        assertTrue(fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getAction() instanceof ActionMove);
    }
    private static Fight initializeFromSavedGame(
            Player _player,
            String _name,
            short _level,
            Difficulty _diff) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data_);
        FightFacade.initTypeEnv(fight_, _data_.getMap().getBegin(), _diff, _data_);
        return fight_;
    }

    private static Fight initializeFromSavedGame(
            CustList<LevelMoves> _partnerMoves,
            CustList<LevelMoves> _foeMoves,
            Player _player,
            Difficulty _diff,
            int... _mult) {
        Fight fight_ = FightFacade.newFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        for (int i = CustList.FIRST_INDEX; i < _foeMoves.size(); i++) {
            PkTrainer foePokemon_ = new PkTrainer();
            foePokemon_.setName(TARTARD);
            foePokemon_.setItem(PLAQUE_DRACO);
            foePokemon_.setAbility(MULTITYPE);
            foePokemon_.setGender(Gender.NO_GENDER);
            foePokemon_.setLevel(_foeMoves.get(i).getFirst());
            foePokemon_.setMoves(_foeMoves.get(i).getSecond());
            foeTeam_.add(foePokemon_);
        }
        if (!_partnerMoves.isEmpty()) {
            DualFight dual_ = new DualFight();
            Ally ally_ = new Ally();
            CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
            for (int i = CustList.FIRST_INDEX; i < _partnerMoves.size(); i++) {
                PkTrainer allyPokemon_ = new PkTrainer();
                allyPokemon_.setName(TARTARD);
                allyPokemon_.setItem(PLAQUE_DRACO);
                allyPokemon_.setAbility(MULTITYPE);
                allyPokemon_.setGender(Gender.NO_GENDER);
                allyPokemon_.setLevel(_partnerMoves.get(i).getFirst());
                allyPokemon_.setMoves(_partnerMoves.get(i).getSecond());
                allyTeam_.add(allyPokemon_);
            }
            ally_.setTeam(allyTeam_);
            dual_.setAlly(ally_);
            TempTrainer trainer_ = new TempTrainer();
            trainer_.setTeam(foeTeam_);
            trainer_.setReward((short) 200);
            dual_.setFoeTrainer(trainer_);
            FightFacade.initFight(fight_,_player, _diff, dual_, _data_);
        } else {
            GymLeader leader_ = new GymLeader();
            leader_.setTeam(foeTeam_);
            if (_mult.length > 0) {
                leader_.setMultiplicityFight((byte) _mult[0]);
            }
            leader_.setReward((short) 200);
            FightFacade.initFight(fight_,_player, _diff, leader_, _data_);
        }
        FightFacade.initTypeEnv(fight_, _data_.getMap().getBegin(), _diff, _data_);
        return fight_;
    }

    @Test
    public void initializeFromSavedGame1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof Action);
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 11);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(PISTOLET_A_O,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, SEISME, diff_, _data_);
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.getChosenTargets().add(POKEMON_FOE_TARGET_ZERO);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(SEISME,playerPk_.getFirstChosenMove());
        assertEq(0, playerPk_.getChosenTargets().size());
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 11);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, PISTOLET_A_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        ActionMove actionMove_ = (ActionMove) loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.setFirstChosenMove(NULL_REF);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof Action);
        assertEq(NULL_REF,wildPk_.getUsedBallCatching());
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_,moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        pokemon_ = new WildPk();
        pokemon_.setName(PIKACHU);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        player_.recevoirPokemon(pokemon_, diff_, _data_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, DEMI_TOUR, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter playerBackPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerBackPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerBackPk_.getAction() instanceof Action);
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(DEMI_TOUR,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(wildPk_.getAction() instanceof ActionMove);
        assertEq(JACKPOT,wildPk_.getFirstChosenMove());
        assertEq(1, wildPk_.getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, wildPk_.getChosenTargets().get(0));
        assertEq(POKE_BALL,wildPk_.getUsedBallCatching());
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        diff_.setAllowCatchingKo(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = initializeFromSavedGame(player_, PIKACHU, (short) 1, diff_);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, _data_);
        FightFacade.chooseMove(fight_, BULLES_D_O, diff_, _data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, _data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof ActionMove);
        assertEq(BULLES_D_O,playerPk_.getFirstChosenMove());
        assertEq(1, playerPk_.getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, playerPk_.getChosenTargets().get(0));
        assertTrue(wildPk_.getAction() instanceof ActionMove);
        assertEq(JACKPOT,wildPk_.getFirstChosenMove());
        assertEq(1, wildPk_.getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ZERO, wildPk_.getChosenTargets().get(0));
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame7Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)3,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initializeFromSavedGame(partnersMoves_, foesMoves_, player_, diff_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter playerAllyPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerAllyPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof Action);
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    @Test
    public void initializeFromSavedGame8Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        diff_.setIvPlayer((short) 20);
        diff_.setIvFoe((short) 15);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 24);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,_data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(_data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(DETECTION);
        foesMoves_.add(new LevelMoves((short)3,foeMoves_));
        Fight fight_ = initializeFromSavedGame(partnersMoves_, foesMoves_, player_, diff_);
        assertEq(FightState.ATTAQUES, fight_.getState());
        Fight loadedFight_ = saveFight(fight_);
        FightFacade.initializeFromSavedGame(loadedFight_, diff_, player_, _data_);
        NumberMap<Byte,Boolean> ko_ = loadedFight_.getKos();
        assertEq(2, ko_.size());
        assertEq(2, getNbKoByValue(ko_, false));
        assertEq(0, getNbKoByValue(ko_, true));
        assertEq(0, getNbKoByValue(ko_, null));
        Fighter playerPk_ = loadedFight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter wildPk_ = loadedFight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertEq(20, playerPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(20, playerPk_.getIv().getVal(Statistic.HP).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(15, wildPk_.getIv().getVal(Statistic.HP).intValue());
        assertTrue(playerPk_.getAction() instanceof Action);
        assertTrue(loadedFight_.getAcceptableChoices());
    }

    private int getNbKoByValue(NumberMap<Byte,Boolean> _map, Boolean _value) {
        int nb_ = CustList.FIRST_INDEX;
        for (EntryCust<Byte,Boolean> e: _map.entryList()) {
            if (e.getValue() == _value) {
                nb_++;
            }
        }
        return nb_;
    }

    private static Fight saveFight(Fight _currentFight) {
        Fight savedFight_ = new Fight();
        savedFight_.setFightType(_currentFight.getFightType());
        savedFight_.setEnvType(_currentFight.getEnvType());
        savedFight_.setMult(_currentFight.getMult());
        savedFight_.setPlayerMaxNumberFrontFighters(_currentFight.getPlayerMaxNumberFrontFighters());
        savedFight_.setEnabledMoves(_currentFight.getEnabledMoves());
        savedFight_.setStillEnabledMoves(_currentFight.getStillEnabledMoves());
        savedFight_.setTeams(new NumberMap<Byte,Team>());
        for (byte k: _currentFight.getTeams().getKeys()) {
            Team team_;
            team_ = saveTeam(_currentFight.getTeams().getVal(k));
            savedFight_.getTeams().put(k, team_);
        }
        savedFight_.setNbFleeAttempt(_currentFight.getNbFleeAttempt());
        savedFight_.setNbRounds(_currentFight.getNbRounds());
        savedFight_.setWinningMoney(_currentFight.getWinningMoney());
        savedFight_.setCatchingBall(_currentFight.getCatchingBall());
        savedFight_.setCurrentUser(_currentFight.getCurrentUser());
        savedFight_.setState(_currentFight.getState());
        savedFight_.setUsedItemsWhileRound(_currentFight.getUsedItemsWhileRound());
        savedFight_.setFirstPositPlayerFighters(_currentFight.getFirstPositPlayerFighters());
        savedFight_.setFirstPositFoeFighters(_currentFight.getFirstPositFoeFighters());
        savedFight_.setAllyChoice(_currentFight.getAllyChoice());
        savedFight_.setLostObjects(_currentFight.getLostObjects());
        savedFight_.setChoices(new NumberMap<Byte,ChoiceOfEvolutionAndMoves>());
        for (byte k: _currentFight.getChoices().getKeys()) {
            ChoiceOfEvolutionAndMoves choice_;
            choice_ = saveChoice(_currentFight.getChoices().getVal(k));
            savedFight_.getChoices().put(k, choice_);
        }
        return savedFight_;
    }

    private static ChoiceOfEvolutionAndMoves saveChoice(ChoiceOfEvolutionAndMoves _currentChoice) {
        ChoiceOfEvolutionAndMoves savedChoice_;
        savedChoice_ = new ChoiceOfEvolutionAndMoves();
        savedChoice_.setName(_currentChoice.getName());
        savedChoice_.setKeptMoves(_currentChoice.getKeptMoves());
        savedChoice_.setAbility(_currentChoice.getAbility());
        return savedChoice_;
    }

    private static Team saveTeam(Team _currentTeam) {
        Team savedTeam_;
        savedTeam_ = new Team();
        savedTeam_.setEnabledMovesByGroup(_currentTeam.getEnabledMovesByGroup());
        savedTeam_.setEnabledMoves(_currentTeam.getEnabledMoves());
        savedTeam_.setEnabledMovesWhileSendingFoe(_currentTeam.getEnabledMovesWhileSendingFoe());
        savedTeam_.setEnabledMovesWhileSendingFoeUses(_currentTeam.getEnabledMovesWhileSendingFoeUses());
        savedTeam_.setNbUsesMoves(_currentTeam.getNbUsesMoves());
        savedTeam_.setNbUsesMovesRound(_currentTeam.getNbUsesMovesRound());
        savedTeam_.setHealAfter(_currentTeam.getHealAfter());
        savedTeam_.setMovesAnticipation(_currentTeam.getMovesAnticipation());
        savedTeam_.setMembers(new NumberMap<Byte,Fighter>());
        for (byte k: _currentTeam.getMembers().getKeys()) {
            Fighter fighter_;
            fighter_ = saveFighter(_currentTeam.getMembers().getVal(k));
            savedTeam_.getMembers().put(k, fighter_);
        }
        savedTeam_.setPlayerFightersAgainstFoe(_currentTeam.getPlayerFightersAgainstFoe());
        savedTeam_.setNbKoRound(_currentTeam.getNbKoRound());
        savedTeam_.setNbKoPreviousRound(_currentTeam.getNbKoPreviousRound());
        savedTeam_.setSuccessfulMovesRound(_currentTeam.getSuccessfulMovesRound());
        return savedTeam_;
    }

    private static Fighter saveFighter(Fighter _currentFighter) {
        Fighter savedFighter_;
        savedFighter_ = new Fighter();
        savedFighter_.setName(_currentFighter.getName());
        savedFighter_.setNickname(_currentFighter.getNickname());
        savedFighter_.setGender(_currentFighter.getGender());
        savedFighter_.setWeight(_currentFighter.getWeight());
        savedFighter_.setHeight(_currentFighter.getHeight());
        savedFighter_.setCurrentName(_currentFighter.getCurrentName());
        savedFighter_.setCurrentGender(_currentFighter.getCurrentGender());
        savedFighter_.setLastUsedItem(_currentFighter.getLastUsedItem());
        savedFighter_.setItem(_currentFighter.getItem());
        savedFighter_.setExpItem(_currentFighter.getExpItem());
        savedFighter_.setAbility(_currentFighter.getAbility());
        savedFighter_.setCurrentAbility(_currentFighter.getCurrentAbility());
        savedFighter_.setStatus(_currentFighter.getStatus());
        savedFighter_.setStatusRelat(_currentFighter.getStatusRelat());
        savedFighter_.setNbRounds(_currentFighter.getNbRounds());
        savedFighter_.setTypes(_currentFighter.getTypes());
        savedFighter_.setMoves(_currentFighter.getMoves());
        savedFighter_.setCurrentMoves(_currentFighter.getCurrentMoves());
        savedFighter_.setEv(_currentFighter.getEv());
        savedFighter_.setStatisBase(_currentFighter.getStatisBase());
        savedFighter_.setStatisBoost(_currentFighter.getStatisBoost());
        savedFighter_.setRemainingHp(_currentFighter.getRemainingHp());
        savedFighter_.setClone(_currentFighter.getClone());
        savedFighter_.setEnabledMoves(_currentFighter.getEnabledMoves());
        savedFighter_.setEnabledMovesProt(_currentFighter.getEnabledMovesProt());
        savedFighter_.setProtectedAgainstMoveTypes(_currentFighter.getProtectedAgainstMoveTypes());
        savedFighter_.setEnabledMovesUnprot(_currentFighter.getEnabledMovesUnprot());
        savedFighter_.setEnabledMovesEndRound(_currentFighter.getEnabledMovesEndRound());
        savedFighter_.setEnabledMovesConstChoices(_currentFighter.getEnabledMovesConstChoices());
        savedFighter_.setEnabledMovesForAlly(_currentFighter.getEnabledMovesForAlly());
        savedFighter_.setDamageRateInflictedByType(_currentFighter.getDamageRateInflictedByType());
        savedFighter_.setDamageRateSufferedByType(_currentFighter.getDamageRateSufferedByType());
        savedFighter_.setActed(_currentFighter.isActed());
        savedFighter_.setGroundPlace(_currentFighter.getGroundPlace());
        savedFighter_.setGroundPlaceSubst(_currentFighter.getGroundPlaceSubst());
        savedFighter_.setWonExp(_currentFighter.getWonExp());
        savedFighter_.setWonExpSinceLastLevel(_currentFighter.getWonExpSinceLastLevel());
        savedFighter_.setLevel(_currentFighter.getLevel());
        savedFighter_.setHappiness(_currentFighter.getHappiness());
        savedFighter_.setUsedBallCatching(_currentFighter.getUsedBallCatching());
        savedFighter_.setIncrUserAccuracy(_currentFighter.getIncrUserAccuracy());
        savedFighter_.setNbUsesMoves(_currentFighter.getNbUsesMoves());
        savedFighter_.setNbPrepaRound(_currentFighter.getNbPrepaRound());
        savedFighter_.setDisappeared(_currentFighter.isDisappeared());
        savedFighter_.setNeedingToRecharge(_currentFighter.isNeedingToRecharge());
        savedFighter_.setTrackingMoves(_currentFighter.getTrackingMoves());
        savedFighter_.setTrappingMoves(_currentFighter.getTrappingMoves());
        savedFighter_.setLastSufferedMove(_currentFighter.getLastSufferedMove());
        savedFighter_.setLastSufferedMoveTypes(_currentFighter.getLastSufferedMoveTypes());
        savedFighter_.setDamageSufferedCateg(_currentFighter.getDamageSufferedCateg());
        savedFighter_.setDamageSufferedCategRound(_currentFighter.getDamageSufferedCategRound());
        savedFighter_.setLastUsedMove(_currentFighter.getLastUsedMove());
        savedFighter_.setUsedMoveLastRound(_currentFighter.getUsedMoveLastRound());
        savedFighter_.setAlreadyInvokedMovesRound(_currentFighter.getAlreadyInvokedMovesRound());
        savedFighter_.setLastSuccessfulMove(_currentFighter.getLastSuccessfulMove());
        savedFighter_.setCopiedMoves(_currentFighter.getCopiedMoves());
        savedFighter_.setNbRepeatingSuccessfulMoves(_currentFighter.getNbRepeatingSuccessfulMoves());
        savedFighter_.setUsingItem(_currentFighter.isUsingItem());
        savedFighter_.setSuccessfulMove(_currentFighter.isSuccessfulMove());
        savedFighter_.setChanged(_currentFighter.isChanged());
        savedFighter_.setEnabledImmuAbilities(_currentFighter.getEnabledImmuAbilities());
        savedFighter_.setPrivateMoves(_currentFighter.getPrivateMoves());
        savedFighter_.setBelongingToPlayer(_currentFighter.isBelongingToPlayer());
        savedFighter_.setAction(_currentFighter.getAction());
        savedFighter_.setMovesToBeLearnt(_currentFighter.getMovesToBeLearnt());
        savedFighter_.setMovesAbilitiesEvos(_currentFighter.getMovesAbilitiesEvos());
        return savedFighter_;
    }
}
