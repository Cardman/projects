package aiki.map.pokemon;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.StringList;
import org.junit.Before;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Fighter;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.TeamPosition;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.StringMap;


public class PokemonPlayerEndFightTest extends InitializationDataBase {

    private static final String PIKA = "PIKA";

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    private Fight endFight(
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
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, data);
        FightFacade.initTypeEnv(fight_, data.getMap().getBegin(), _diff, data);
        return fight_;
    }

    @Test
    public void endFight1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.getStatus().add(SOMMEIL);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        UsablePokemon usable_ = player_.getTeam().first();
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(PTITARD,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(METEO,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(1, ((PokemonPlayer)usable_).getStatus().size());
        assertTrue(StringList.contains(((PokemonPlayer) usable_).getStatus(), SOMMEIL));
        assertEq(Rate.zero(), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(1, ((PokemonPlayer)usable_).getLevel());
        assertEq(1, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getMax());
        assertEq(70, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("1211/100"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void endFight2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.getStatus().add(SOMMEIL);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        fighter_.affecterStatut(CONFUSION);
        UsablePokemon usable_ = player_.getTeam().first();
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(PTITARD,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(METEO,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(1, ((PokemonPlayer)usable_).getStatus().size());
        assertTrue(StringList.contains(((PokemonPlayer) usable_).getStatus(), SOMMEIL));
        assertEq(Rate.zero(), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(1, ((PokemonPlayer)usable_).getLevel());
        assertEq(1, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getMax());
        assertEq(70, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("1211/100"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void endFight3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        fighter_.affecterStatut(CONFUSION);
        UsablePokemon usable_ = player_.getTeam().first();
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(PTITARD,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(METEO,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(0, ((PokemonPlayer)usable_).getStatus().size());
        assertEq(Rate.zero(), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(1, ((PokemonPlayer)usable_).getLevel());
        assertEq(1, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getMax());
        assertEq(70, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("1211/100"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void endFight4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        UsablePokemon usable_ = player_.getTeam().first();
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(PTITARD,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(METEO,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(0, ((PokemonPlayer)usable_).getStatus().size());
        assertEq(Rate.zero(), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(1, ((PokemonPlayer)usable_).getLevel());
        assertEq(1, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(TOURNIQUET).getMax());
        assertEq(70, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("1211/100"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void endFight5Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data);
        FightFacade.chooseMove(fight_, BULLES_D_O, diff_, data);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data, false);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, ECUME);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data);
        assertTrue(FightFacade.win(fight_));
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ZERO;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        UsablePokemon usable_ = player_.getTeam().first();
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(TETARTE,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(ABSORB_EAU,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(0, ((PokemonPlayer)usable_).getStatus().size());
        assertEq(new Rate("49"), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(26, ((PokemonPlayer)usable_).getLevel());
        assertEq(4, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(25, ((PokemonPlayer)usable_).getMoves().getVal(ECUME).getCurrent());
        assertEq(25, ((PokemonPlayer)usable_).getMoves().getVal(ECUME).getMax());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(BULLES_D_O).getCurrent());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(BULLES_D_O).getMax());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(PLAQUAGE).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(PLAQUAGE).getMax());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(TORGNOLES).getCurrent());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(TORGNOLES).getMax());
        assertEq(72, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(2, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("3893/50"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void endFight6Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data);
        FightFacade.chooseMove(fight_, BULLES_D_O, diff_, data);
        FightFacade.setFirstChosenMoveFoeTarget(fight_, (byte) 0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data, false);
        FightFacade.choosePokemonForLearningAndEvolving(fight_, (byte) 0, data);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, ECUME);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data);
        assertTrue(FightFacade.win(fight_));
        TeamPosition playerPk_ = POKEMON_PLAYER_FIGHTER_ONE;
        Fighter fighter_ = fight_.getFighter(playerPk_);
        UsablePokemon usable_ = player_.getTeam().get(1);
        StringMap<UsesOfMove> mapMoves_ = FightFacade.movesAfterFight(fight_, playerPk_, diff_);
        ((PokemonPlayer)usable_).endFight(fighter_, mapMoves_, data);
        assertEq(TETARTE,((PokemonPlayer)usable_).getName());
        assertEq(Gender.NO_GENDER, ((PokemonPlayer)usable_).getGender());
        assertEq(ABSORB_EAU,((PokemonPlayer)usable_).getAbility());
        assertEq(PLAQUE_DRACO,((PokemonPlayer)usable_).getItem());
        assertEq(0, ((PokemonPlayer)usable_).getStatus().size());
        assertEq(new Rate("49"), ((PokemonPlayer)usable_).getWonExpSinceLastLevel());
        assertEq(26, ((PokemonPlayer)usable_).getLevel());
        assertEq(4, ((PokemonPlayer)usable_).getMoves().size());
        assertEq(25, ((PokemonPlayer)usable_).getMoves().getVal(ECUME).getCurrent());
        assertEq(25, ((PokemonPlayer)usable_).getMoves().getVal(ECUME).getMax());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(BULLES_D_O).getCurrent());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(BULLES_D_O).getMax());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(PLAQUAGE).getCurrent());
        assertEq(15, ((PokemonPlayer)usable_).getMoves().getVal(PLAQUAGE).getMax());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(TORGNOLES).getCurrent());
        assertEq(20, ((PokemonPlayer)usable_).getMoves().getVal(TORGNOLES).getMax());
        assertEq(72, ((PokemonPlayer)usable_).getHappiness());
        assertEq(6, ((PokemonPlayer)usable_).getEv().size());
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.DEFENSE));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(2, ((PokemonPlayer)usable_).getEv().getVal(Statistic.SPEED));
        assertEq(0, ((PokemonPlayer)usable_).getEv().getVal(Statistic.HP));
        assertEq(new Rate("3893/50"),((PokemonPlayer)usable_).getRemainingHp());
    }

    @Test
    public void new_PokemonPlayer_Fighter_String_String_DataBase_1Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        PokemonPlayer pk_ = new PokemonPlayer(fight_.wildPokemon(), PIKA, HYPER_BALL, data);
        assertEq(PIKACHU,pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(MULTITYPE,pk_.getAbility());
        assertEq(PLAQUE_DRACO,pk_.getItem());
        assertEq(0, pk_.getStatus().size());
        assertEq(new Rate("0"), pk_.getWonExpSinceLastLevel());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(70, pk_.getHappiness());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(new Rate("63/5"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
    }

    @Test
    public void new_PokemonPlayer_Fighter_String_String_DataBase_2Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        PokemonPlayer pk_ = new PokemonPlayer(fight_.wildPokemon(), PIKA, HYPER_BALL, data);
        assertEq(PIKACHU,pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(MULTITYPE,pk_.getAbility());
        assertEq(PLAQUE_DRACO,pk_.getItem());
        assertEq(1, pk_.getStatus().size());
        assertTrue(StringList.contains(pk_.getStatus(), SOMMEIL));
        assertEq(new Rate("0"), pk_.getWonExpSinceLastLevel());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(70, pk_.getHappiness());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(new Rate("63/5"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
    }

    @Test
    public void new_PokemonPlayer_Fighter_String_String_DataBase_3Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        fight_.wildPokemon().affecterStatut(SOMMEIL);
        fight_.wildPokemon().affecterStatut(CONFUSION);
        PokemonPlayer pk_ = new PokemonPlayer(fight_.wildPokemon(), PIKA, HYPER_BALL, data);
        assertEq(PIKACHU,pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(MULTITYPE,pk_.getAbility());
        assertEq(PLAQUE_DRACO,pk_.getItem());
        assertEq(1, pk_.getStatus().size());
        assertTrue(StringList.contains(pk_.getStatus(), SOMMEIL));
        assertEq(new Rate("0"), pk_.getWonExpSinceLastLevel());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(70, pk_.getHappiness());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(new Rate("63/5"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
    }

    @Test
    public void new_PokemonPlayer_Fighter_String_String_DataBase_4Test() {
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = new Player(NICKNAME,null,diff_,false,data);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU, (short) 1, diff_);
        fight_.wildPokemon().affecterStatut(CONFUSION);
        PokemonPlayer pk_ = new PokemonPlayer(fight_.wildPokemon(), PIKA, HYPER_BALL, data);
        assertEq(PIKACHU,pk_.getName());
        assertEq(Gender.NO_GENDER, pk_.getGender());
        assertEq(MULTITYPE,pk_.getAbility());
        assertEq(PLAQUE_DRACO,pk_.getItem());
        assertEq(0, pk_.getStatus().size());
        assertEq(new Rate("0"), pk_.getWonExpSinceLastLevel());
        assertEq(1, pk_.getLevel());
        assertEq(1, pk_.getMoves().size());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getCurrent());
        assertEq(20, pk_.getMoves().getVal(JACKPOT).getMax());
        assertEq(70, pk_.getHappiness());
        assertEq(6, pk_.getEv().size());
        assertEq(0, pk_.getEv().getVal(Statistic.ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(0, pk_.getEv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(0, pk_.getEv().getVal(Statistic.SPEED));
        assertEq(0, pk_.getEv().getVal(Statistic.HP));
        assertEq(new Rate("63/5"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
    }
}
