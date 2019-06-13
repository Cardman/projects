package aiki.game.fight;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Player;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;


public class FightKoTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private static Fight rateWonPoint(Difficulty _diff) {
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
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void rateWonPoint1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.TRES_FACILE);
        Fight fight_ = rateWonPoint(diff_);
        assertEq(new Rate("4"), FightKo.rateWonPoint(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_));
    }

    @Test
    public void rateWonPoint2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        Fight fight_ = rateWonPoint(diff_);
        assertEq(new Rate("2"), FightKo.rateWonPoint(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_));
    }

    @Test
    public void rateWonPoint3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = rateWonPoint(diff_);
        assertEq(new Rate("1"), FightKo.rateWonPoint(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_));
    }

    @Test
    public void rateWonPoint4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.TRES_DIFFICILE);
        Fight fight_ = rateWonPoint(diff_);
        assertEq(new Rate("1/2"), FightKo.rateWonPoint(fight_,POKEMON_PLAYER_FIGHTER_ZERO, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_));
    }

    private static Fight addExp(Difficulty _diff) {
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
        pokemonUser_.setRemainingHp(Rate.zero());
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
    public void addExp1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_);
        EqList<TeamPosition> fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        EqList<TeamPosition> porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, _data_);
        Rate points_ = FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExp(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, porteursMultExp_, POKEMON_FOE_FIGHTER_ZERO, points_, diff_, true, _data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_);
        EqList<TeamPosition> fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        EqList<TeamPosition> porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, _data_);
        Rate points_ = FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_);
        Numbers<Byte> fighters_ = new Numbers<Byte>();
        FightKo.addExp(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, porteursMultExp_, POKEMON_FOE_FIGHTER_ZERO, points_, diff_, false, _data_);
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setExpItem(MULTI_EXP);
        EqList<TeamPosition> fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        EqList<TeamPosition> porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, _data_);
        Rate points_ = FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExp(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, porteursMultExp_, POKEMON_FOE_FIGHTER_ZERO, points_, diff_, true, _data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setExpItem(OEUF_CHANCE);
        EqList<TeamPosition> fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        EqList<TeamPosition> porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, _data_);
        Rate points_ = FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExp(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, porteursMultExp_, POKEMON_FOE_FIGHTER_ZERO, points_, diff_, false, _data_);
        assertEq(new Rate("14985/4"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addEvStatistics1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
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
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        EnumMap<Statistic,Short> evs_ = new EnumMap<Statistic,Short>();
        evs_.put(Statistic.ATTACK, (short) 2);
        evs_.put(Statistic.DEFENSE, (short) 2);
        evs_.put(Statistic.SPECIAL_ATTACK, (short) 2);
        evs_.put(Statistic.SPECIAL_DEFENSE, (short) 2);
        evs_.put(Statistic.SPEED, (short) 2);
        evs_.put(Statistic.HP, (short) 2);
        FightKo.addEvStatistics(fight_,POKEMON_PLAYER_FIGHTER_ZERO, new Rate("3"), evs_, _data_);
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.ATTACK));
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.SPEED));
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.HP));
    }

    private static Fight addEv(Difficulty _diff) {
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
        pokemonUser_.setRemainingHp(Rate.zero());
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
        trainer_.setMultiplicityFight((byte) 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void addEv1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, (byte) 0, _data_);
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ONE, fighters_, (byte) 0, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, (byte) 0, _data_);
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).backUpObject(GROSSERACINE);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, (byte) 0, _data_);
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv5Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setExpItem(BRAC_MACHO);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, (byte) 0, _data_);
        assertEq(6, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setExpItem(CEINT_POUV);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addEv(fight_,POKEMON_PLAYER_FIGHTER_ZERO, fighters_, (byte) 0, _data_);
        assertEq(7, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void pointsFoe1Test() {
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
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        assertEq(new Rate("4995/2"), FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_));
    }

    @Test
    public void pointsFoe2Test() {
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
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(new Difficulty());
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, foePokemon_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        assertEq(new Rate("300"), FightKo.pointsFoe(fight_,(byte) 0, diff_, _data_));
    }

    @Test
    public void addExpEvsFighters1Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
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
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExpEvsFighters(fight_,fighters_, (byte) 0, diff_, _data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addExpEvsFighters2Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
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
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExpEvsFighters(fight_,fighters_, (byte) 0, diff_, _data_);
        assertEq(new Rate("4995/4"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("4995/4"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addExpEvsFighters3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Player player_ = new Player(NICKNAME,null,diff_,false,_data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 100);
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(A_LA_QUEUE, (short) 10);
        moves_.put(APRES_VOUS, (short) 10);
        moves_.put(SEISME, (short) 10);
        moves_.put(BROUHAHA, (short) 10);
        PokemonPlayer pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness((short) 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 32);
        pokemonUser_ = new PokemonPlayer(pokemon_, _data_, moves_);
        pokemonUser_.initIv(diff_);
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
        FightFacade.initFight(fight_,player_, diff_, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Numbers<Byte> fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_,(byte) 0);
        FightKo.addExpEvsFighters(fight_,fighters_, (byte) 0, diff_, _data_);
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("4995/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    private static Fight setFighterKo(Difficulty _diff) {
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
        trainer_.setMultiplicityFight((byte) 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void setFighterKo1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setFighterKo(diff_);
        Fighter u_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter f_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        u_.transformer(f_, (short) 5);
        assertEq(TARTARD, u_.getCurrentName());
        FightKo.setFighterKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, _data_);
        assertEq(YANMA, u_.getCurrentName());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertTrue(!fight_.getKos().getVal(Fight.PLAYER));
        assertTrue(!fight_.getKos().getVal(Fight.FOE));
    }

    private static Fight setKo(Difficulty _diff) {
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
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short) 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        DualFight dual_ = new DualFight();
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 3);
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void setKo1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_);
        Fighter u_ = fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        Fighter f_ = fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        u_.transformer(f_, (short) 5);
        assertEq(TARTARD, u_.getCurrentName());
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        assertEq(YANMA, u_.getCurrentName());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 0).size());
        assertTrue(!fight_.getKos().getVal(Fight.PLAYER));
        assertTrue(!fight_.getKos().getVal(Fight.FOE));
    }

    @Test
    public void setKo2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getWonExp());
        assertEq(3, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getEv().getVal(Statistic.DEFENSE));
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        Numbers<Byte> list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 0);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj((byte) 1));
        assertTrue(list_.containsObj((byte) 2));
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte)2);
        assertEq(0, list_.size());
        assertTrue(!fight_.getKos().getVal(Fight.PLAYER));
        assertTrue(!fight_.getKos().getVal(Fight.FOE));
    }

    @Test
    public void setKo3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        assertEq(new Rate("14985/2"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getWonExp());
        assertEq(9, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getEv().getVal(Statistic.DEFENSE));
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getRemainingHp());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getRemainingHp());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getRemainingHp());
        Numbers<Byte> list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 0);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte) 1);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal((byte)2);
        assertEq(0, list_.size());
        assertTrue(!fight_.getKos().getVal(Fight.PLAYER));
        assertTrue(fight_.getKos().getVal(Fight.FOE));
    }

    @Test
    public void setKo4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_THREE, diff_, _data_);
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).getRemainingHp());
        assertTrue(!fight_.getKos().getVal(Fight.PLAYER));
        assertTrue(!fight_.getKos().getVal(Fight.FOE));
    }

    private static Fight endedFight(Difficulty _diff) {
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
        pokemonUser_.setRemainingHp(Rate.zero());
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
    public void endedFight1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = endedFight(diff_);
        fight_.setEndRound(false);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = endedFight(diff_);
        fight_.setEndRound(true);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight3Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = endedFight(diff_);
        fight_.setEndRound(true);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight4Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = endedFight(diff_);
        fight_.setEndRound(false);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = endedFight(diff_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(FightKo.endedFight(fight_,diff_));
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight6Test() {
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = endedFight(diff_);
        fight_.setEndRound(false);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    private static Fight moveTeams(Difficulty _diff, byte _mult) {
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
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void moveTeams1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 1);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
    }

    @Test
    public void moveTeams2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 2);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
    }

    @Test
    public void moveTeams3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 3);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 3);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams5Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 3);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams6Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 3);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams7Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_, (byte) 3);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKo(fight_,POKEMON_PLAYER_FIGHTER_ONE, diff_, _data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
    }

    private static Fight setKoMoveTeams(Difficulty _diff, byte _mult) {
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
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void setKoMoveTeams1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKoMoveTeams(diff_, (byte) 3);
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKoMoveTeams(diff_, (byte) 2);
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKoMoveTeams(diff_, (byte) 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getRemainingHp().affectZero();
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKoMoveTeams(diff_, (byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getRemainingHp().affectZero();
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,POKEMON_FOE_FIGHTER_ONE, diff_, _data_);
        assertEq(0, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(POKEMON_FOE_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    private static Fight canBeHealed(Difficulty _diff, byte _mult) {
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
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void canBeHealed1Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = canBeHealed(diff_, (byte) 3);
        assertTrue(FightKo.canBeHealed(fight_, Fight.PLAYER, _data_));
    }

    @Test
    public void canBeHealed2Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = canBeHealed(diff_, (byte) 3);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightKo.canBeHealed(fight_, Fight.PLAYER, _data_));
    }

    @Test
    public void canBeHealed3Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = canBeHealed(diff_, (byte) 3);
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        assertTrue(!FightKo.canBeHealed(fight_, Fight.PLAYER, _data_));
    }

    @Test
    public void canBeHealed4Test() {
        Difficulty diff_ = new Difficulty();
        Fight fight_ = canBeHealed(diff_, (byte) 3);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(FightKo.canBeHealed(fight_, Fight.PLAYER, _data_));
    }
}
