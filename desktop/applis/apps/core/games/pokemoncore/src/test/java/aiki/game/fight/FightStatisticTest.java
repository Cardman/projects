package aiki.game.fight;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;


public class FightStatisticTest extends InitializationDataBase {

    private static final String VAR_EXAMPLE = "VAR__EXAMPLE";
    private static final String PIKA = "PIKA";

    private static Fight bonusBoost(DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false, _data);
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
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        return fight_;
    }

    @Test
    public void bonusBoost1Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.ATTACK, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost2Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.ATTACK, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost3Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.ATTACK, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost4Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(LENTILSCOPE);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.ATTACK, POKEMON_PLAYER_FIGHTER_ZERO, data_));
        assertEq(1, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost5Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentName(CARAPUCE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BATON);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.ATTACK, POKEMON_PLAYER_FIGHTER_ZERO, data_));
        assertEq(2, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost6Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_MIRACLE_QUATER);
        assertEq(1, FightStatistic.bonusBoost(fight_,Statistic.ACCURACY, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusBoost7Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(NULL_REF);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, fighter_, data_));
    }

    @Test
    public void bonusBoost8Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_MEPO);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, fighter_, data_));
    }

    @Test
    public void bonusBoost9Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_LANSAT);
        assertEq(0, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, fighter_, data_));
    }

    @Test
    public void bonusBoost10Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_LANSAT);
        f_.setRemainedHp(Rate.one());
        assertEq(1, FightStatistic.bonusBoost(fight_,Statistic.CRITICAL_HIT, fighter_, data_));
    }

    @Test
    public void multiplyStringFighterVariables1Test() {
        DataBase data_ = initDb();
        StringMap<String> map_ = new StringMap<String>();
        map_.put(VAR_EXAMPLE, "2");
        assertEq(new Rate("3"), FightStatistic.multiplyStringFighterVariables("1+VAR__EXAMPLE", map_, data_));
        assertEq(new Rate("1"), FightStatistic.multiplyStringFighterVariables(NULL_REF, map_, data_));
        //assertEq(new Rate("1"), FightStatistic.multiplyStringFighterVariables("-1", map_));
        //assertEq(new Rate("1"), FightStatistic.multiplyStringFighterVariables("0", map_));
        //assertEq(new Rate("1"), FightStatistic.multiplyStringFighterVariables("0/0", map_));
    }

    @Test
    public void multiplyStringFighter1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(HYPER_BALL);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        FightRound.initRound(fight_);
        assertEq(new Rate("2"), FightStatistic.multiplyStringFighter(fight_,"2", POKEMON_PLAYER_FIGHTER_ZERO, data_));
        assertEq(Rate.one(), FightStatistic.multiplyStringFighter(fight_,NULL_REF, POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void multiplyStatisticPartner1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        assertEq(Rate.one(),FightStatistic.multiplyStatisticPartner(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(Rate.one(),FightStatistic.multiplyStatisticPartner(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(MAGNEPIEGE);
        assertEq(new Rate("2"),FightStatistic.multiplyStatisticPartner(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
    }

    @Test
    public void multiplyStatisticFoeTeamMoveEffect1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        fight_.getUserTeam().activerEffetEquipe(REGARD_NOIR);
        assertEq(new Rate("1/2"),FightStatistic.multiplyStatisticFoeTeamMoveEffect(fight_,Statistic.EVASINESS, Fight.CST_PLAYER, data_));
        assertEq(Rate.one(),FightStatistic.multiplyStatisticFoeTeamMoveEffect(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
        fight_.getUserTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertEq(new Rate("1/2"),FightStatistic.multiplyStatisticFoeTeamMoveEffect(fight_,Statistic.EVASINESS, Fight.CST_PLAYER, data_));
    }

    @Test
    public void multiplyStatisticTeamMoveEffect1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        fight_.getUserTeam().activerEffetEquipe(REGARD_NOIR);
        assertEq(new Rate("2"),FightStatistic.multiplyStatisticTeamMoveEffect(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
        assertEq(Rate.one(),FightStatistic.multiplyStatisticTeamMoveEffect(fight_,Statistic.EVASINESS, Fight.CST_PLAYER, data_));
        fight_.getUserTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertEq(new Rate("2"),FightStatistic.multiplyStatisticTeamMoveEffect(fight_,Statistic.ACCURACY, Fight.CST_PLAYER, data_));
    }

    @Test
    public void coeffStatisticStatusImmu1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.DEFENSE, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.DEFENSE, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(BRULURE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterStatut(PARALYSIE);
        assertEq(new Rate("1/2"),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.DEFENSE, data_));
        assertEq(new Rate("1/2"),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PIED_VELOCE);
        assertEq(new Rate("1/2"),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, data_));
        assertEq(Rate.one(),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.DEFENSE, data_));
        assertEq(new Rate("2"),FightStatistic.coeffStatisticStatusImmu(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, data_));
    }

    @Test
    public void statisticWithoutBase1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
        pokemonUser_.initIv(new Difficulty());
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
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        FightRound.initRound(fight_);
        StringMap<String> map_ = FightValues.calculateValuesFighter(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ACCURACY, map_, data_));
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(POUDRE_ATTAQUE);
        assertEq(new Rate("2"), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ATTACK, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(POUDRE_VITE);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BOUE_NOIRE);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PIED_VELOCE);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_EAU);
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_D_HERBE);
        assertEq(new Rate("4/5"), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ACCURACY, map_, data_));
        fight_.getFoeTeam().addSuccessfulMoveRound(AIRE_DE_FEU);
        assertEq(new Rate("4/5"), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.ACCURACY, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).affecterTypes(new StringList(ROCHE,SOL,EAU));
        fight_.enableGlobalMove(TEMPETESABLE);
        assertEq(new Rate("3/2"), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPECIAL_DEFENSE, map_, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        assertEq(Rate.one(), FightStatistic.statisticWithoutBase(fight_,POKEMON_PLAYER_FIGHTER_ZERO, Statistic.SPEED, map_, data_));
        //BASE SPEED == 1893/25
        //BASE SPE DEF == 1093/25 * 3/2 = 3279/50
    }

    @Test
    public void rateBoost1Test() {
        DataBase data_ = initDb();
        assertEq(new Rate("1"), FightStatistic.rateBoost((byte) 0, data_));
        assertEq(new Rate("3/2"), FightStatistic.rateBoost((byte) 1, data_));
        assertEq(new Rate("2/3"), FightStatistic.rateBoost((byte) -1, data_));
    }

    @Test
    public void multiplyByLoveBetweenFighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(SEISME, (short) 10);
        moves_.put(COPIE, (short) 10);
        moves_.put(MIMIQUE, (short) 10);
        moves_.put(INTERVERSION, (short) 10);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_, moves_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        foePokemon_.setMoves(new StringList(JACKPOT,SAISIE,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(MAGNET);
        foePokemon_.setAbility(SECHERESSE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 4);
        foePokemon_.setMoves(new StringList(JACKPOT,PAR_ICI,COPIE));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        fight_.enableGlobalMove(ORAGE);
        assertEq(new Rate("2"), FightStatistic.multiplyByLoveBetweenFighters(fight_,data_));
    }

    @Test
    public void criticalHit1Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(NULL_REF);
        assertEq(0, FightStatistic.criticalHit(fight_, fighter_, 0, data_));
    }

    @Test
    public void criticalHit2Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_MEPO);
        assertEq(0, FightStatistic.criticalHit(fight_, fighter_, 0, data_));
    }

    @Test
    public void criticalHit3Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_LANSAT);
        assertEq(0, FightStatistic.criticalHit(fight_, fighter_, 0, data_));
    }

    @Test
    public void criticalHit4Test() {
        DataBase data_ = initDb();
        Fight fight_ = bonusBoost(data_);
        TeamPosition fighter_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter f_ = fight_.getFighter(fighter_);
        f_.backUpObject(BAIE_LANSAT);
        f_.setRemainedHp(Rate.one());
        assertEq(1, FightStatistic.criticalHit(fight_, fighter_, 0, data_));
    }
}
