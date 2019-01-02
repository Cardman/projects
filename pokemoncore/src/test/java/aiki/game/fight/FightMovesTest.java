package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

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

@SuppressWarnings("static-method")
public class FightMovesTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    @Test
    public void existenceAntiClimatActif1Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        assertTrue(!FightMoves.existenceAntiClimatActif(fight_,_data_));
    }

    @Test
    public void existenceAntiClimatActif2Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        assertTrue(FightMoves.existenceAntiClimatActif(fight_,_data_));
    }

    @Test
    public void enabledGlobalMoves1Test() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
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
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        assertEq(0, FightMoves.enabledGlobalMoves(fight_, _data_).size());
        fight_.enableGlobalMove(DISTORSION);
        StringList list_ = FightMoves.enabledGlobalMoves(fight_, _data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(DISTORSION));
    }

    @Test
    public void climatsActifs1Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.enableGlobalMove(GRAVITE);
        assertEq(0, FightMoves.climatsActifs(fight_,_data_).size());
    }

    @Test
    public void climatsActifs2Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,_data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(ZENITH));
    }

    @Test
    public void climatsActifs3Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,_data_);
        assertEq(0, list_.size());
    }

    @Test
    public void enabledGlobalNonWeatherMove1Test() {
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, _data_);
        FightInitialization.initFight(fight_, _data_);
        fight_.enableGlobalMove(GRAVITE);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.enabledGlobalNonWeatherMove(fight_,_data_);
        assertEq(1, list_.size());
        assertTrue(list_.containsObj(GRAVITE));
    }

    private static Fight moveTypes() {
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        return fight_;
    }

    @Test
    public void moveTypes1Test() {
        Fight fight_ = moveTypes();
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(NORMAL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(NORMAL));
    }

    @Test
    public void moveTypes2Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(COMBAT));
    }

    @Test
    public void moveTypes3Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(NORMAL));
    }

    @Test
    public void moveTypes4Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
    }

    @Test
    public void moveTypes5Test() {
        Fight fight_ = moveTypes();
        fight_.enableGlobalMove(ZENITH);
        fight_.enableGlobalMove(ORAGE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, _data_);
        assertEq(2, types_.size());
        assertTrue(types_.containsObj(FEU));
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void moveTypes6Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(MULTI_EXP);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(NORMAL));
    }

    @Test
    public void moveTypes7Test() {
        Fight fight_ = moveTypes();
        fight_.enableGlobalMove(ZENITH);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_ORAGE, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(NORMAL));
    }

    @Test
    public void moveTypes8Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, RELAIS, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(FEE));
    }

    @Test
    public void moveTypes9Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(TENEBRE));
    }

    @Test
    public void moveTypes10Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(ELECTRISATION);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void moveTypes11Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(SOL));
    }

    @Test
    public void moveTypes12Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void moveTypes13Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(ELECTRIQUE));
    }

    @Test
    public void moveTypes14Test() {
        Fight fight_ = moveTypes();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_GLACIAL);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, _data_);
        assertEq(1, types_.size());
        assertTrue(types_.containsObj(GLACE));
    }
}
