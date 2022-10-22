package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.TempTrainer;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

public final class FacadeGameFightSpecTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false, data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_, data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        assertTrue(facadeGame_.isDualFight());
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(TOURNIQUET);
        facadeGame_.roundAllThrowers(false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame_.getFight().getState());
        facadeGame_.roundWhileKoPlayer(false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame_.getFight().getState());
        facadeGame_.roundWhileKoPlayer(true);
        facadeGame_.roundUser();
        facadeGame_.endRoundFightKoUser();
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame_.getFight().getState());
        assertTrue(FightFacade.koTeam(facadeGame_.getFight()));
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }
    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
