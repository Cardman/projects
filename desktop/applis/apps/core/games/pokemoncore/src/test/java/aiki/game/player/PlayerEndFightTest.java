package aiki.game.player;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;


public class PlayerEndFightTest extends InitializationDataBase {

    private static Fight endFight(
            Player _player,
            String _name,
            int _level,
            Difficulty _diff, DataBase _data) {
        WildPk foePokemon_ = new WildPk();
        foePokemon_.setName(_name);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel(_level);
        Fight fight_ = FightFacade.newFight();
        FightFacade.initFight(fight_,_player, _diff, foePokemon_, _data);
        FightFacade.initTypeEnv(fight_, _data.getMap().getBegin(), _diff, _data);
        return fight_;
    }

    @Test
    public void affectEndFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU,  1, diff_, data_);
        FightFacade.chooseFrontFighter(fight_,  0, diff_, data_);
        FightFacade.chooseMove(fight_, BULLES_D_O, diff_, data_);
        FightFacade.setFirstChosenMoveFoeTarget(fight_,  0);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        fight_.getLostObjects().add(HYPER_BALL);
        FightFacade.choosePokemonForLearningAndEvolving(fight_,  0, data_);
        FightFacade.setEvolution(fight_, TETARTE);
        FightFacade.addOrForgetMove(fight_, DANSE_PLUIE);
        FightFacade.addOrForgetMove(fight_, ECUME);
        FightFacade.setAbility(fight_, ABSORB_EAU);
        assertTrue(FightFacade.possibleChoices(fight_, data_));
        FightFacade.learnAndEvolveAttack(fight_, diff_, data_);
        assertTrue(FightFacade.win(fight_));
        player_.affectEndFight(fight_, diff_, data_);
        UsablePokemon usable_ = player_.getTeam().get(1);
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
        assertEq(LgInt.one(),player_.getInventory().getNumber(HYPER_BALL));
        assertTrue(player_.estAttrape(TETARTE));
        assertTrue(!player_.estAttrape(PIKACHU));
    }

    @Test
    public void catchWildPokemon1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU,  1, diff_, data_);
        player_.affectEndFight(fight_, diff_, data_);
        fight_.getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        player_.catchWildPokemon(fight_.wildPokemon(), PIKACHU, HYPER_BALL, diff_, data_, true);
        assertEq(3, player_.getTeam().size());
        assertEq(0, player_.getBox().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getTeam().last();
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
        assertEq(new Rate("1291/100"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
        assertTrue(player_.estAttrape(PIKACHU));
        assertTrue(!player_.estAttrape(TETARTE));
    }

    @Test
    public void catchWildPokemon2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU,  1, diff_, data_);
        player_.affectEndFight(fight_, diff_, data_);
        fight_.getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        player_.catchWildPokemon(fight_.wildPokemon(), PIKACHU, HYPER_BALL, diff_, data_, true);
        assertEq(6, player_.getTeam().size());
        assertEq(1, player_.getBox().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getBox().last();
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
        assertEq(new Rate("1291/100"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
        assertTrue(player_.estAttrape(PIKACHU));
        assertTrue(!player_.estAttrape(TETARTE));
    }

    @Test
    public void catchWildPokemon3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        Fight fight_ = endFight(player_, PIKACHU,  1, diff_, data_);
        player_.affectEndFight(fight_, diff_, data_);
        fight_.getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        player_.catchWildPokemon(fight_.wildPokemon(), PIKACHU, HYPER_BALL, diff_, data_, false);
        assertEq(2, player_.getTeam().size());
        assertEq(1, player_.getBox().size());
        PokemonPlayer pk_ = (PokemonPlayer) player_.getBox().last();
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
        assertEq(new Rate("1291/100"),pk_.getRemainingHp());
        assertEq(HYPER_BALL, pk_.getUsedBallCatching());
        assertEq(0, pk_.getNbStepsTeamLead());
        assertTrue(player_.estAttrape(PIKACHU));
        assertTrue(!player_.estAttrape(TETARTE));
    }
    @Test
    public void winMoneyFight1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.winMoneyFight(new LgInt("200"));
        assertEq(new LgInt("3200"), player_.getMoney());
    }

    @Test
    public void winMoneyFight2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.winMoneyFight(new LgInt("-3200"));
        assertEq(new LgInt("0"), player_.getMoney());
    }

    @Test
    public void swap1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        PokemonPlayer pkOne_ = (PokemonPlayer) player_.getTeam().get(0);
        PokemonPlayer pkTwo_ = (PokemonPlayer) player_.getTeam().get(1);
        PokemonPlayer pkThree_ = (PokemonPlayer) player_.getTeam().get(2);
        PokemonPlayer pkFour_ = (PokemonPlayer) player_.getTeam().get(3);
        PokemonPlayer pkFive_ = (PokemonPlayer) player_.getTeam().get(4);
        Ints indexes_= new Ints();
        indexes_.add(1);
        indexes_.add(4);
        indexes_.add(2);
        indexes_.add(0);
        indexes_.add(3);
        player_.swap(indexes_);
        assertSame(pkTwo_, player_.getTeam().get(0));
        assertSame(pkFive_, player_.getTeam().get(1));
        assertSame(pkThree_, player_.getTeam().get(2));
        assertSame(pkOne_, player_.getTeam().get(3));
        assertSame(pkFour_, player_.getTeam().get(4));
    }

    @Test
    public void restore1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        diff_.setRestoredMovesEndFight(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel( 25);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        PokemonPlayer pkOne_ = (PokemonPlayer) player_.getTeam().get(0);
        PokemonPlayer pkTwo_ = (PokemonPlayer) player_.getTeam().get(1);
        PokemonPlayer pkThree_ = (PokemonPlayer) player_.getTeam().get(2);
        PokemonPlayer pkFour_ = (PokemonPlayer) player_.getTeam().get(3);
        PokemonPlayer pkFive_ = (PokemonPlayer) player_.getTeam().get(4);
        Ints indexes_= new Ints();
        indexes_.add(1);
        indexes_.add(4);
        indexes_.add(2);
        indexes_.add(0);
        indexes_.add(3);
        player_.swap(indexes_);
        player_.restore(indexes_);
        assertSame(pkOne_, player_.getTeam().get(0));
        assertSame(pkTwo_, player_.getTeam().get(1));
        assertSame(pkThree_, player_.getTeam().get(2));
        assertSame(pkFour_, player_.getTeam().get(3));
        assertSame(pkFive_, player_.getTeam().get(4));
    }
}
