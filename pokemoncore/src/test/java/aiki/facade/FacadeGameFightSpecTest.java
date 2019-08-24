package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.LevelMoves;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.DataMap;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.TempTrainer;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameFightSpecTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
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
        player_.recupererOeufPensions(new Egg(PTITARD));
        CustList<LevelMoves> partnersMoves_ = new CustList<LevelMoves>();
        StringList partnerMoves_ = new StringList(JACKPOT);
        partnersMoves_.add(new LevelMoves((short)19,partnerMoves_));
        CustList<LevelMoves> foesMoves_ = new CustList<LevelMoves>();
        StringList foeMoves_ = new StringList(CHARGE);
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        foesMoves_.add(new LevelMoves((short)17,foeMoves_));
        Game game_ = new Game(data);
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
        FightFacade.initFight(fight_, player_, diff_, dual_, data);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame = facadeGame_;
    }


    @Test
    public void act1Test() {
        assertTrue(facadeGame.isDualFight());
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(TOURNIQUET);
        facadeGame.roundAllThrowers(false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame.getFight().getState());
        facadeGame.roundWhileKoPlayer(false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame.getFight().getState());
        facadeGame.roundWhileKoPlayer(true);
        facadeGame.roundUser();
        facadeGame.endRoundFightKoUser();
        assertEq(FightState.SWITCH_WHILE_KO_USER, facadeGame.getFight().getState());
        assertTrue(FightFacade.koTeam(facadeGame.getFight()));
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
