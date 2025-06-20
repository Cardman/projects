package aiki.game.fight;

import aiki.db.DataBase;
import aiki.util.TeamPositionList;
import code.util.core.BoolVal;
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
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightKoTest extends InitializationDataBase {

    private static Fight rateWonPoint(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(PIKACHU);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, foePokemon_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void rateWonPoint1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.TRES_FACILE);
        Fight fight_ = rateWonPoint(diff_, data_);
        assertEq(new Rate("4"), FightKo.rateWonPoint(diff_, data_, fight_.getUserTeam().refPartMembres(POKEMON_FIGHTER_ZERO), fight_.getFoeTeam().refPartMembres(tp(KEY_FOE, POKEMON_FIGHTER_ZERO).getPosition())));
    }

    @Test
    public void rateWonPoint2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        Fight fight_ = rateWonPoint(diff_, data_);
        assertEq(new Rate("2"), FightKo.rateWonPoint(diff_, data_, fight_.getUserTeam().refPartMembres(POKEMON_FIGHTER_ZERO), fight_.getFoeTeam().refPartMembres(tp(KEY_FOE, POKEMON_FIGHTER_ZERO).getPosition())));
    }

    @Test
    public void rateWonPoint3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = rateWonPoint(diff_, data_);
        assertEq(new Rate("1"), FightKo.rateWonPoint(diff_, data_, fight_.getUserTeam().refPartMembres(POKEMON_FIGHTER_ZERO), fight_.getFoeTeam().refPartMembres(tp(KEY_FOE, POKEMON_FIGHTER_ZERO).getPosition())));
    }

    @Test
    public void rateWonPoint4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.TRES_DIFFICILE);
        Fight fight_ = rateWonPoint(diff_, data_);
        assertEq(new Rate("1/2"), FightKo.rateWonPoint(diff_, data_, fight_.getUserTeam().refPartMembres(POKEMON_FIGHTER_ZERO), fight_.getFoeTeam().refPartMembres(tp(KEY_FOE, POKEMON_FIGHTER_ZERO).getPosition())));
    }

    private static Fight addExp(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void addExp1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        TeamPositionList fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        Ints porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, data_);
        Rate points_ = FightKo.pointsFoe(fight_, 0, diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        addExp(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_, porteursMultExp_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), points_, diff_, true, data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        TeamPositionList fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        Ints porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, data_);
        Rate points_ = FightKo.pointsFoe(fight_, 0, diff_, data_);
        Ints fighters_ = new Ints();
        addExp(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_, porteursMultExp_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), points_, diff_, false, data_);
        assertEq(Rate.zero(), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setExpItem(MULTI_EXP);
        TeamPositionList fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        Ints porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, data_);
        Rate points_ = FightKo.pointsFoe(fight_, 0, diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        addExp(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_, porteursMultExp_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), points_, diff_, true, data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addExp4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setExpItem(OEUF_CHANCE);
        TeamPositionList fightersBelongingToUser_ = FightOrder.fightersBelongingToUser(fight_,true);
        Ints porteursMultExp_ = FightOrder.fightersWearingExpObject(fight_,fightersBelongingToUser_, data_);
        Rate points_ = FightKo.pointsFoe(fight_, 0, diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        addExp(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_, porteursMultExp_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), points_, diff_, false, data_);
        assertEq(new Rate("14985/4"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
    }

    @Test
    public void addEvStatistics1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        IdMap<Statistic,Long> evs_ = new IdMap<Statistic,Long>();
        evs_.put(Statistic.ATTACK,2L);
        evs_.put(Statistic.DEFENSE,2L);
        evs_.put(Statistic.SPECIAL_ATTACK,2L);
        evs_.put(Statistic.SPECIAL_DEFENSE,2L);
        evs_.put(Statistic.SPEED,2L);
        evs_.put(Statistic.HP,2L);
        FightKo.addEvStatistics(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), new Rate("3"), evs_, data_);
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.ATTACK));
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.SPEED));
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.HP));
    }

    private static Fight addEv(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 1);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void addEv1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_,  0, data_);
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), fighters_,  0, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(HYPER_BALL);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_,  0, data_);
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).backUpObject(GROSSERACINE);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_,  0, data_);
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setExpItem(BRAC_MACHO);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_,  0, data_);
        assertEq(6, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addEv6Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addEv(diff_, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setExpItem(CEINT_POUV);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addEv(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), fighters_,  0, data_);
        assertEq(7, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void pointsFoe1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setItem(MULTI_EXP);
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setItem(BAIE_CHERIM);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        pokemonUser_.setRemainingHp(Rate.zero());
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        assertEq(new Rate("4995/2"), FightKo.pointsFoe(fight_, 0, diff_, data_));
    }

    @Test
    public void pointsFoe2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        Fight fight_ = rateWonPoint(diff_, data_);
        assertEq(new Rate("300"), FightKo.pointsFoe(fight_, 0, diff_, data_));
    }

    @Test
    public void addExpEvsFighters1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Fight fight_ = addExp(diff_, data_);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addExpEvsFighters(fight_,fighters_,  0, diff_, data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addExpEvsFighters2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addExpEvsFighters(fight_,fighters_,  0, diff_, data_);
        assertEq(new Rate("4995/4"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("4995/4"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void addExpEvsFighters3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setDiffWinningExpPtsFight(DifficultyWinPointsFight.DIFFICILE);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 100);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        pokemonUser_ = pkMoves(data_, diff_, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, diff_, trainer_, data_);
        fight_.setEnvType(EnvironmentType.ROAD);
        Ints fighters_ = FightOrder.fightersBelongingToUserHavingBeaten(fight_, 0);
        FightKo.addExpEvsFighters(fight_,fighters_,  0, diff_, data_);
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("4995/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getWonExp());
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
    }

    @Test
    public void setFighterKo1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        Fighter u_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter f_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        u_.transformer(f_,  5);
        assertEq(TARTARD, u_.getCurrentName());
        FightKo.setFighterKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), data_);
        assertEq(YANMA, u_.getCurrentName());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_PLAYER));
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_FOE));
    }

    private static Fight setKo(Difficulty _diff, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel( 3);
        allyPokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        DualFight dual_ = new DualFight();
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight( 3);
        dual_.setAlly(ally_);
        dual_.setFoeTrainer(trainer_);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, dual_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void setKo1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_, data_);
        Fighter u_ = fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        Fighter f_ = fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        u_.transformer(f_,  5);
        assertEq(TARTARD, u_.getCurrentName());
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(YANMA, u_.getCurrentName());
        assertEq(Fighter.BACK, fight_.getFirstPositPlayerFighters().getVal( 0));
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(0, fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal( 0).size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_PLAYER));
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_FOE));
    }

    @Test
    public void setKo2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(new Rate("4995/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getWonExp());
        assertEq(3, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getEv().getVal(Statistic.DEFENSE));
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        Ints list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal( 0);
        assertEq(2, list_.size());
        assertTrue(list_.containsObj( 1));
        assertTrue(list_.containsObj( 2));
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal( 1);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal(2);
        assertEq(0, list_.size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_PLAYER));
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_FOE));
    }

    @Test
    public void setKo3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        assertEq(new Rate("14985/2"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getWonExp());
        assertEq(new Rate("0"), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getWonExp());
        assertEq(9, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getEv().getVal(Statistic.DEFENSE));
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getRemainingHp());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getRemainingHp());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Rate.zero(), fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getRemainingHp());
        Ints list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal( 0);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal( 1);
        assertEq(0, list_.size());
        list_ = fight_.getUserTeam().getPlayerFightersAgainstFoe().getVal(2);
        assertEq(0, list_.size());
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_PLAYER));
        assertSame(BoolVal.TRUE, fight_.getTemp().getKos().getVal(Fight.CST_FOE));
    }

    @Test
    public void setKo4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = setKo(diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_THREE), diff_, data_);
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getGroundPlaceSubst());
        assertEq(Rate.zero(), fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).getRemainingHp());
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_PLAYER));
        assertSame(BoolVal.FALSE, fight_.getTemp().getKos().getVal(Fight.CST_FOE));
    }

    @Test
    public void endedFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = addExp(diff_, data_);
        fight_.getTemp().setEndRound(false);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = addExp(diff_, data_);
        fight_.getTemp().setEndRound(true);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = addExp(diff_, data_);
        fight_.getTemp().setEndRound(true);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = addExp(diff_, data_);
        fight_.getTemp().setEndRound(false);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = addExp(diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertTrue(FightKo.endedFight(fight_,diff_));
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void endedFight6Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        diff_.setEndFightIfOneTeamKo(false);
        Fight fight_ = addExp(diff_, data_);
        fight_.getTemp().setEndRound(false);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    private static Fight moveTeams(Difficulty _diff, int _mult, DataBase _data) {
        Player player_ = Player.build(NICKNAME,_diff,false, _data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(YANMA);
        pokemon_.setItem(NULL_REF);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 32);
        StringMap<Long> moves_ = new StringMap<Long>();
        moves_.put(A_LA_QUEUE,10L);
        moves_.put(APRES_VOUS,10L);
        moves_.put(SEISME,10L);
        moves_.put(BROUHAHA,10L);
        PokemonPlayer pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        pokemonUser_ = pkMoves(_data, _diff, pokemon_, moves_);
        pokemonUser_.setNickname(PIKA);
        pokemonUser_.setUsedBallCatching(SUPER_BALL);
        pokemonUser_.setHappiness( 140);
        pokemonUser_.setWonExpSinceLastLevel(new Rate("3167"));
        player_.getTeam().add(pokemonUser_);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel( 3);
        foePokemon_.setMoves(new StringList(JACKPOT,PREVENTION,RELAIS));
        foeTeam_.add(foePokemon_);
        GymLeader trainer_ = new GymLeader();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(_mult);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,player_, _diff, trainer_, _data);
        fight_.setEnvType(EnvironmentType.ROAD);
        return fight_;
    }

    @Test
    public void moveTeams1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  1, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
    }

    @Test
    public void moveTeams2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  2, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
    }

    @Test
    public void moveTeams3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams5Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams6Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void moveTeams7Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKo(fight_,tp(KEY_PLAYER, POKEMON_FIGHTER_ONE), diff_, data_);
        FightKo.moveTeams(fight_);
        assertEq(0, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
    }

    @Test
    public void setKoMoveTeams1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(2, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  2, data_);
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  2, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getRemainingHp().affectZero();
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void setKoMoveTeams4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  2, data_);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getRemainingHp().affectZero();
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getRemainingHp().affectZero();
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(!FightKo.endedFight(fight_,diff_));
        FightKo.setKoMoveTeams(fight_,tp(KEY_FOE, POKEMON_FIGHTER_ONE), diff_, data_);
        assertEq(0, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(1, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).getGroundPlace());
        assertEq(Fighter.BACK, fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_TWO).getGroundPlace());
        assertTrue(FightKo.endedFight(fight_,diff_));
    }

    @Test
    public void canBeHealed1Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        assertTrue(FightKo.canBeHealed(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void canBeHealed2Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        fight_.getFoeTeam().activerEffetEquipe(BRUME);
        assertTrue(FightKo.canBeHealed(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void canBeHealed3Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        fight_.getFoeTeam().activerEffetEquipe(ANTI_SOIN);
        assertTrue(!FightKo.canBeHealed(fight_, Fight.CST_PLAYER, data_));
    }

    @Test
    public void canBeHealed4Test() {
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Fight fight_ = moveTeams(diff_,  3, data_);
        fight_.getFoeTeam().activerEffetEquipe(TOUR_RAPIDE);
        assertTrue(FightKo.canBeHealed(fight_, Fight.CST_PLAYER, data_));
    }
    static void addExp(Fight _fight,TeamPosition _fighter,
                       Ints _members, Ints _porteursMultExp,
                       TeamPosition _foe, Rate _points,
                       Difficulty _diff, boolean _showMessage, DataBase _import) {
        FightKo.addExp(_fight,_fighter, new PointFoeExpObject(_members,_porteursMultExp,_points,_foe.getPosition()),_diff,_showMessage,_import);
    }
}
