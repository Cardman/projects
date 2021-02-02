package aiki.game.fight;

import aiki.db.DataBase;
import code.util.core.IndexConstants;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.util.NbEffectFighterCoords;
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


public class FightItemsTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    private Fight canUseObjectIfPossible() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(GRELOT_ZEN);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        return fight_;
    }

    @Test
    public void canUseObjectIfPossible1Test() {
        Fight fight_ = canUseObjectIfPossible();
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
    }

    @Test
    public void canUseObjectIfPossible2Test() {
        Fight fight_ = canUseObjectIfPossible();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(PROVOC);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaque(PROVOC);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
    }

    @Test
    public void canUseObjectIfPossible3Test() {
        Fight fight_ = canUseObjectIfPossible();
        fight_.enableGlobalMove(ZONE_MAGIQUE);
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.enableGlobalMove(ZONE_ETRANGE);
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
    }

    @Test
    public void canUseObjectIfPossible4Test() {
        Fight fight_ = canUseObjectIfPossible();
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaque(CYCLE_V);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data));
    }

    @Test
    public void canUseItsObject1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        assertTrue(!FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(!FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
    }

    private Fight canUseBerry() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canUseBerry1Test() {
        Fight fight_ = canUseBerry();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).setCurrentAbility(TENSION);
        assertTrue(FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(TENSION);
        assertTrue(!FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        assertTrue(FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.enableGlobalMove(ZONE_MAGIQUE);
        assertTrue(!FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
    }

    private Fight canUseItsBerry() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canUseItsBerry1Test() {
        Fight fight_ = canUseItsBerry();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data));
    }

    private Fight bonusHp() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void bonusHp1Test() {
        Fight fight_ = bonusHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        FightItems.bonusHp(fight_, fighterCoords_, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
    }

    @Test
    public void bonusHp2Test() {
        Fight fight_ = bonusHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(METEO);
        FightItems.bonusHp(fight_, fighterCoords_, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
    }

    @Test
    public void bonusHp3Test() {
        Fight fight_ = bonusHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(BAJOUES);
        FightItems.bonusHp(fight_, fighterCoords_, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1219/50"), fighter_.getRemainingHp());
    }

    private Fight enableBerryHp() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerryHp1Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, false, false, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp2Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp3Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(METEO);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp4Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(GLOUTONNERIE);
        fighter_.setRemainedHp(new Rate("2313/25"));
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("2313/25"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp5Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, false, false, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp6Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp7Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(METEO);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp8Test() {
        Fight fight_ = enableBerryHp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(GLOUTONNERIE);
        fighter_.setRemainedHp(new Rate("2313/25"));
        FightItems.enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("2313/25"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    private Fight enableBerryStatus() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerryStatus1Test() {
        Fight fight_ = enableBerryStatus();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryStatus(fight_, fighterCoords_, BAIE_CERIZ, false, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus2Test() {
        Fight fight_ = enableBerryStatus();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryStatus(fight_, fighterCoords_, BAIE_CERIZ, true, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus3Test() {
        Fight fight_ = enableBerryStatus();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(SOMMEIL);
        FightItems.enableBerryStatus(fight_, fighterCoords_, BAIE_CERIZ, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus4Test() {
        Fight fight_ = enableBerryStatus();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerryStatus(fight_, fighterCoords_, BAIE_CERIZ, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus5Test() {
        Fight fight_ = enableBerryStatus();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerryStatus(fight_, fighterCoords_, BAIE_CERIZ, false, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertTrue(!fighter_.isUsingItem());
    }

    private Fight enableBerryPp() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerryPp1Test(){
        Fight fight_ = enableBerryPp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, false, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp2Test(){
        Fight fight_ = enableBerryPp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, true, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp3Test(){
        Fight fight_ = enableBerryPp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, false, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp4Test(){
        Fight fight_ = enableBerryPp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp5Test(){
        Fight fight_ = enableBerryPp();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_CERIZ, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(!fighter_.isUsingItem());
    }

    private Fight enableBerryStatistic() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerryStatistic1Test() {
        Fight fight_ = enableBerryStatistic();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryStatistic(fight_, fighterCoords_, BAIE_PITAYE, false, false, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic2Test() {
        Fight fight_ = enableBerryStatistic();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryStatistic(fight_, fighterCoords_, BAIE_PITAYE, true, true, data);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic3Test() {
        Fight fight_ = enableBerryStatistic();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        FightItems.enableBerryStatistic(fight_, fighterCoords_, BAIE_PITAYE, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic4Test() {
        Fight fight_ = enableBerryStatistic();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        FightItems.enableBerryStatistic(fight_, fighterCoords_, BAIE_PITAYE, true, true, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    private Fight enableBerry() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerry1Test() {
        Fight fight_ = enableBerry();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerry(fight_, fighterCoords_, BAIE_ORAN, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry2Test() {
        Fight fight_ = enableBerry();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerry(fight_, fighterCoords_, BAIE_CERIZ, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry3Test() {
        Fight fight_ = enableBerry();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerry(fight_, fighterCoords_, BAIE_MEPO, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry4Test() {
        Fight fight_ = enableBerry();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerry(fight_, fighterCoords_, BAIE_PITAYE, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry5Test() {
        Fight fight_ = enableBerry();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        FightItems.enableBerry(fight_, fighterCoords_, BAIE_CHERIM, data);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    private Fight enableBerryHpWhileSuperEffectiveMove() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, data, moves_);
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
        foePokemon_.setItem(PLAQUE_DRACO);
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
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove1Test() {
        Fight fight_ = enableBerryHpWhileSuperEffectiveMove();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(NULL_REF);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data);
        assertEq(Rate.one(), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove2Test() {
        Fight fight_ = enableBerryHpWhileSuperEffectiveMove();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BAIE_MEPO);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data);
        assertEq(Rate.one(), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove3Test() {
        Fight fight_ = enableBerryHpWhileSuperEffectiveMove();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.backUpObject(BAIE_ENIGMA);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data);
        assertEq(new Rate("2338/25"), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove4Test() {
        Fight fight_ = enableBerryHpWhileSuperEffectiveMove();
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BAIE_ENIGMA);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data);
        assertEq(new Rate("1219/50"), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(fighter_.isUsingItem());
    }
}
