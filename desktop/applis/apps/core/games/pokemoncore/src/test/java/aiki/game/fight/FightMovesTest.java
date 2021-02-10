package aiki.game.fight;

import aiki.db.DataBase;
import code.util.core.StringUtil;
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

    @Test
    public void existenceAntiClimatActif1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        assertTrue(!FightMoves.existenceAntiClimatActif(fight_,data_));
    }

    @Test
    public void existenceAntiClimatActif2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        assertTrue(FightMoves.existenceAntiClimatActif(fight_,data_));
    }

    @Test
    public void enabledGlobalMoves1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,data_);
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
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, data_, moves_);
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
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        assertEq(0, FightMoves.enabledGlobalMoves(fight_, data_).size());
        fight_.enableGlobalMove(DISTORSION);
        StringList list_ = FightMoves.enabledGlobalMoves(fight_, data_);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, DISTORSION));
    }

    @Test
    public void climatsActifs1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.enableGlobalMove(GRAVITE);
        assertEq(0, FightMoves.climatsActifs(fight_,data_).size());
    }

    @Test
    public void climatsActifs2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,data_);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, ZENITH));
    }

    @Test
    public void climatsActifs3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(AIR_LOCK);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.climatsActifs(fight_,data_);
        assertEq(0, list_.size());
    }

    @Test
    public void enabledGlobalNonWeatherMove1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,data_);
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
        trainer_.setMultiplicityFight((byte) 2);
        Fight fight_ = FightFacade.newFight();
        FightInitialization.initFight(fight_, player_, diff_, trainer_, data_);
        FightInitialization.initFight(fight_, data_);
        fight_.enableGlobalMove(GRAVITE);
        fight_.enableGlobalMove(ZENITH);
        StringList list_ = FightMoves.enabledGlobalNonWeatherMove(fight_,data_);
        assertEq(1, list_.size());
        assertTrue(StringUtil.contains(list_, GRAVITE));
    }

    private static Fight moveTypes(DataBase _data) {
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
    public void moveTypes1Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, NORMAL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes2Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
        types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DON_NATUREL, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, COMBAT));
    }

    @Test
    public void moveTypes3Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes4Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
    }

    @Test
    public void moveTypes5Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.enableGlobalMove(ZENITH);
        fight_.enableGlobalMove(ORAGE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_METEO, data_);
        assertEq(2, types_.size());
        assertTrue(StringUtil.contains(types_, FEU));
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes6Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(MULTI_EXP);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes7Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.enableGlobalMove(ZENITH);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, BALL_ORAGE, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, NORMAL));
    }

    @Test
    public void moveTypes8Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, RELAIS, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, FEE));
    }

    @Test
    public void moveTypes9Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(PEAU_FEERIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, DEGOMMAGE, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, TENEBRE));
    }

    @Test
    public void moveTypes10Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(ELECTRISATION);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes11Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, SOL));
    }

    @Test
    public void moveTypes12Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(NORMALISE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, SEISME, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes13Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_PLASMIQUE);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, ELECTRIQUE));
    }

    @Test
    public void moveTypes14Test() {
        DataBase data_ = initDb();
        Fight fight_ = moveTypes(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).enableChangingMovesTypes(DELUGE_GLACIAL);
        StringList types_ = FightMoves.moveTypes(fight_,POKEMON_PLAYER_FIGHTER_ZERO, COMBO_GRIFFE, data_);
        assertEq(1, types_.size());
        assertTrue(StringUtil.contains(types_, GLACE));
    }
}
