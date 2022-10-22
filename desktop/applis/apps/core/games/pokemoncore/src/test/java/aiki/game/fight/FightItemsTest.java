package aiki.game.fight;

import aiki.db.DataBase;
import aiki.fight.items.Berry;
import aiki.game.player.enums.Sex;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
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

    private static Fight canUseObjectIfPossible(DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
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
    public void canUseObjectIfPossible1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseObjectIfPossible(data_);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseObjectIfPossible2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseObjectIfPossible(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(PROVOC);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaque(PROVOC);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseObjectIfPossible3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseObjectIfPossible(data_);
        fight_.enableGlobalMove(ZONE_MAGIQUE);
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.enableGlobalMove(ZONE_ETRANGE);
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
        assertTrue(!FightItems.canUseObjectIfPossible(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseObjectIfPossible4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseObjectIfPossible(data_);
        fight_.enableGlobalMove(BROUHAHA);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).activerAttaque(CYCLE_V);
        assertTrue(FightItems.canUseObjectIfPossible(fight_,POKEMON_FOE_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseItsObject1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        assertTrue(!FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(!FightItems.canUseItsObject(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    private static Fight canUseBerry(DataBase _data) {
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false, _data);
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
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data, moves_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canUseBerry1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setCurrentAbility(NULL_REF);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(ATTENTION);
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).setCurrentAbility(TENSION);
        assertTrue(FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setCurrentAbility(TENSION);
        assertTrue(!FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setCurrentAbility(TERA_VOLTAGE);
        assertTrue(FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.enableGlobalMove(ZONE_MAGIQUE);
        assertTrue(!FightItems.canUseBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void canUseItsBerry1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(NULL_REF);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(PLAQUE_DRACO);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(BAIE_MEPO);
        assertTrue(FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).activerAttaque(EMBARGO);
        assertTrue(!FightItems.canUseItsBerry(fight_,POKEMON_PLAYER_FIGHTER_ZERO, data_));
    }

    @Test
    public void bonusHp1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(NULL_REF);
        FightItems.bonusHp(fight_, fighterCoords_, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
    }

    @Test
    public void bonusHp2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(METEO);
        FightItems.bonusHp(fight_, fighterCoords_, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
    }

    @Test
    public void bonusHp3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.setCurrentAbility(BAJOUES);
        FightItems.bonusHp(fight_, fighterCoords_, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1219/50"), fighter_.getRemainingHp());
    }

    @Test
    public void enableBerryHp1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, false, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(METEO);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(GLOUTONNERIE);
        fighter_.setRemainedHp(new Rate("2313/25"));
        enableBerryHp(fight_, fighterCoords_, BAIE_ORAN, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("2313/25"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp5Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, false, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp6Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(NULL_REF);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp7Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(METEO);
        fighter_.setRemainedHp(Rate.one());
        enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1269/100"), fighter_.getRemainingHp());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHp8Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setCurrentAbility(GLOUTONNERIE);
        fighter_.setRemainedHp(new Rate("2313/25"));
        enableBerryHp(fight_, fighterCoords_, BAIE_GOWAV, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("2313/25"), fighter_.getRemainingHp());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        enableBerryStatus(data_, fight_, fighterCoords_, false);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        enableBerryStatus(data_, fight_, fighterCoords_, true);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(SOMMEIL);
        enableBerryStatus(data_, fight_, fighterCoords_, true);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(1, fighter_.getStatusNbRound(SOMMEIL));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(PARALYSIE);
        enableBerryStatus(data_, fight_, fighterCoords_, true);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatus5Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affecterStatut(PARALYSIE);
        enableBerryStatus(data_, fight_, fighterCoords_, false);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp1Test(){
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, false, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp2Test(){
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, true, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp3Test(){
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, false, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp4Test(){
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_MEPO, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryPp5Test(){
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        FightItems.enableBerryPp(fight_, fighterCoords_, BAIE_CERIZ, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        enableBerryStatistic(fight_, fighterCoords_, false, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        enableBerryStatistic(fight_, fighterCoords_, true, data_);
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        enableBerryStatistic(fight_, fighterCoords_, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(fighter_.isUsingItem());
    }

    @Test
    public void enableBerryStatistic4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.variationBoostStatistique(Statistic.SPECIAL_ATTACK, (byte) 6);
        enableBerryStatistic(fight_, fighterCoords_, true, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.ATTACK));
        assertEq(6, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        enableBerry(fight_, fighterCoords_, BAIE_ORAN, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("11"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        enableBerry(fight_, fighterCoords_, BAIE_CERIZ, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(0, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        enableBerry(fight_, fighterCoords_, BAIE_MEPO, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(10, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        enableBerry(fight_, fighterCoords_, BAIE_PITAYE, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(1, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerry5Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.setRemainedHp(Rate.one());
        fighter_.getCurrentMoves().getVal(BROUHAHA).setCurrent((short) 0);
        fighter_.affecterStatut(PARALYSIE);
        enableBerry(fight_, fighterCoords_, BAIE_CHERIM, data_);
        fighter_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(new Rate("1"), fighter_.getRemainingHp());
        assertEq(1, fighter_.getStatusNbRound(PARALYSIE));
        assertEq(0, fighter_.getCurrentMoves().getVal(BROUHAHA).getCurrent());
        assertEq(0, fighter_.getStatisBoost().getVal(Statistic.SPECIAL_ATTACK));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove1Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(NULL_REF);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data_);
        assertEq(Rate.one(), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove2Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BAIE_MEPO);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data_);
        assertEq(Rate.one(), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove3Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.backUpObject(BAIE_ENIGMA);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data_);
        assertEq(new Rate("2338/25"), fighter_.getRemainingHp());
        assertTrue(!fight_.getSuccessfulEffects().contains(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(!fighter_.isUsingItem());
    }

    @Test
    public void enableBerryHpWhileSuperEffectiveMove4Test() {
        DataBase data_ = initDb();
        Fight fight_ = canUseBerry(data_);
        TeamPosition fighterCoords_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(fighterCoords_);
        fighter_.setRemainedHp(Rate.one());
        fighter_.backUpObject(BAIE_ENIGMA);
        FightItems.enableBerryHpWhileSuperEffectiveMove(fight_, fighterCoords_, IndexConstants.FIRST_INDEX, data_);
        assertEq(new Rate("1219/50"), fighter_.getRemainingHp());
        assertSame(BoolVal.FALSE,fight_.getSuccessfulEffects().getVal(new NbEffectFighterCoords(IndexConstants.FIRST_INDEX, fighterCoords_)));
        assertTrue(fighter_.isUsingItem());
    }

    private void enableBerryHp(Fight _fight, TeamPosition _fighterCoords, String _item, boolean _useObject, DataBase _data) {
        FightItems.enableBerryHp(_fight, _fighterCoords, _useObject, _useObject, _data, (Berry) _data.getItem(_item));
    }

    private void enableBerry(Fight _fight, TeamPosition _fighterCoords, String _item, DataBase _data) {
        FightItems.enableBerry(_fight, _fighterCoords, _item, _data,(Berry) _data.getItem(_item));
    }

    private void enableBerryStatus(DataBase _data, Fight _fight, TeamPosition _fighterCoords, boolean _useObject) {
        FightItems.enableBerryStatus(_fight, _fighterCoords, _useObject, _data, (Berry) _data.getItem(BAIE_CERIZ));
    }

    private void enableBerryStatistic(Fight _fight, TeamPosition _fighterCoords, boolean _useObject, DataBase _data) {
        FightItems.enableBerryStatistic(_fight, _fighterCoords, _useObject, _useObject, _data, (Berry) _data.getItem(BAIE_PITAYE));
    }

}
