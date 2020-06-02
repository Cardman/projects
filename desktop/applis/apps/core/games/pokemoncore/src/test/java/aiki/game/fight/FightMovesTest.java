package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.map.characters.GymLeader;
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


public class FightMovesTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void existenceAntiClimatActif1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        assertTrue(!FightMoves.existenceAntiClimatActif(fight_,data));
    }

    @Test
    public void existenceAntiClimatActif2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        assertTrue(FightMoves.existenceAntiClimatActif(fight_,data));
    }

    @Test
    public void enabledGlobalMoves1Test() {
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
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        assertEq(0, FightMoves.enabledGlobalMoves(fight_, data).size());
        fight_.enableGlobalMove(DISTORSION);
        StringList list_ = FightMoves.enabledGlobalMoves(fight_, data);
        assertEq(1, list_.size());
        assertTrue(StringList.contains(list_, DISTORSION));
    }

    @Test
    public void climatsActifs1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(GRAVITE);
        assertEq(0, FightMoves.climatsActifs(fight_,data).size());
    }

    @Test
    public void climatsActifs2Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,data);
        assertEq(1, list_.size());
        assertTrue(StringList.contains(list_, ZENITH));
    }

    @Test
    public void climatsActifs3Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,data);
        assertEq(0, list_.size());
    }

    @Test
    public void enabledGlobalNonWeatherMove1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(ARTIKODIN);
        pokemon_.setItem(MAGNET);
        pokemon_.setAbility(PARATONNERRE);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 3);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data);
        FightInitialization.initFight(fight_, data);
        fight_.enableGlobalMove(GRAVITE);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.enabledGlobalNonWeatherMove(fight_,data);
        assertEq(1, list_.size());
        assertTrue(StringList.contains(list_, GRAVITE));
    }

    private Fight moveTypes() {
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
        return fight_;
    }

    @Test
    public void moveTypes1Test() {
        Fight fight_ = moveTypes();
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, NORMAL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes2Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, COMBAT));
    }

    @Test
    public void moveTypes3Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes4Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, SOL));
    }

    @Test
    public void moveTypes5Test() {
        Fight fight_ = moveTypes();
        fight_.enableGlobalMove(ZENITH);
        fight_.enableGlobalMove(ORAGE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, data);
        assertEq(2, types_.size());
        assertTrue(StringList.contains(types_, FEU));
        assertTrue(StringList.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes6Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(MULTI_EXP);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes7Test() {
        Fight fight_ = moveTypes();
        fight_.enableGlobalMove(ZENITH);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_ORAGE, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes8Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, RELAIS, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, FEE));
    }

    @Test
    public void moveTypes9Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, TENEBRE));
    }

    @Test
    public void moveTypes10Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(ELECTRISATION);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes11Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, SOL));
    }

    @Test
    public void moveTypes12Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes13Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes14Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_GLACIAL);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, data);
        assertEq(1, types_.size());
        assertTrue(StringList.contains(types_, GLACE));
    }
}
