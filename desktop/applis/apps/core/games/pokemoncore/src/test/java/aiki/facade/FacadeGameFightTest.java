package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.params.Difficulty;
import aiki.instances.Instances;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import code.maths.Rate;
import code.util.IdMap;
import org.junit.Test;

public final class FacadeGameFightTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.setupMovingHeros();
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        assertEq(PTITARD, facadeGame_.getNicknameOrDefault(""));
        assertEq("NICKNAME", facadeGame_.getNicknameOrDefault("NICKNAME"));
        facadeGame_.attemptFlee(false);
        assertTrue(facadeGame_.isEnabledMovingHero());
    }

    @Test
    public void act2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.attemptFlee(true);
        facadeGame_.endRoundFightFlee();
        assertTrue(facadeGame_.isEnabledMovingHero());
    }

    @Test
    public void act3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.getPlayer().getItem(EAU_FRAICHE);
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.searchPokemonHealingItem();
        facadeGame_.checkLineHealingItem(0);
        facadeGame_.setChosenHealingItem();
        assertEq(EAU_FRAICHE,((ActionHeal)facadeGame_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction()).getChosenHealingItem());
    }

    @Test
    public void act4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        facadeGame_.attemptCatchingWildPokemon(false);
        assertEq(FightState.SURNOM, facadeGame_.getFight().getState());
    }

    @Test
    public void act5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        facadeGame_.attemptCatchingWildPokemon(false);
        facadeGame_.catchWildPokemon();
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act6Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        facadeGame_.attemptCatchingWildPokemon(true);
        facadeGame_.endRoundFightSuccessBall();
        facadeGame_.catchWildPokemon();
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act7Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(JACKPOT);
        assertEq(1,facadeGame_.sortedFightersBeginRoundWildFight().size());
//        assertEq(2,facadeGame_.sortedFightersBeginRoundWildFight().firstValue().size());
    }

    @Test
    public void act8Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        assertEq(1,facadeGame_.calculateCatchingRatesSingle((byte) 0,(byte) 0).size());
    }

    @Test
    public void act9Test() {
        FacadeGame facadeGame_ = initTests();
        IdMap<UsefulValueLaw, Rate> mcn_ = facadeGame_.calculateFleeingRate();
        assertEq(Rate.one(), mcn_.getVal(UsefulValueLaw.MINI));
        assertEq(Rate.one(), mcn_.getVal(UsefulValueLaw.MAXI));
        assertEq(Rate.one(), mcn_.getVal(UsefulValueLaw.MOY));
        assertEq(Rate.zero(), mcn_.getVal(UsefulValueLaw.VAR));
    }

    @Test
    public void act10Test() {
        FacadeGame facadeGame_ = initTests();
        assertEq(2,facadeGame_.remainingThrowersTargetsHp().size());
    }

    @Test
    public void act11Test() {
        FacadeGame facadeGame_ = initTests();
        assertTrue(facadeGame_.isExistingFight());
        assertTrue(facadeGame_.isWildFight());
    }

    @Test
    public void act12Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.getFight().getCatchingBalls().get(0).setCatchingBall(MASTER_BALL);
        assertEq(1,facadeGame_.calculateCatchingRatesSingle((byte) 0,(byte) 0).size());
        assertEq(1,facadeGame_.calculateCatchingRatesSum().size());
        assertEq(1,facadeGame_.swallow(facadeGame_.attempted()).size());
        assertTrue(facadeGame_.enoughBall());
        assertEq(2,facadeGame_.getPlayerToCatch().size());
        assertEq(PIKACHU,facadeGame_.getSinglePlayerToCatch(0).getFighter().getName());
        assertEq(LIMAGMA,facadeGame_.getSinglePlayerToCatch(1).getFighter().getName());
        assertEq(0,facadeGame_.getFoeToBeCaught(true).size());
        assertEq(1,facadeGame_.getFoeToBeCaught().size());
        assertEq(1,facadeGame_.getFoeToBeCaught(false).size());
        assertEq(PTITARD,facadeGame_.getSingleFoeToBeCaught(false, 0).getFighter().getName());
        assertEq(PTITARD,facadeGame_.getSingleFoeToBeCaught(0).getFighter().getName());
        assertEq(DataBase.EMPTY_STRING, Instances.newCatchingBallFoeAction().getCatchingBall());
    }

    @Test
    public void act13Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getCatchingBalls().get(0).setCatchingBall(MASTER_BALL);
        assertEq(0,facadeGame_.calculateCatchingRatesSingle((byte) 0,(byte) 0).size());
        assertFalse(facadeGame_.enoughBall());
    }

    @Test
    public void act14Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getCatchingBalls().get(0).setCatchingBall(MASTER_BALL);
        facadeGame_.getFight().getCatchingBalls().get(0).setCaught(true);
        assertEq(1,facadeGame_.getFoeToBeCaught(true).size());
        assertEq(1,facadeGame_.getFoeToBeCaught().size());
        assertEq(0,facadeGame_.getFoeToBeCaught(false).size());
        assertEq(PTITARD,facadeGame_.getSingleFoeToBeCaught(true, 0).getFighter().getName());
        assertEq(PTITARD,facadeGame_.getSingleFoeToBeCaught(0).getFighter().getName());
    }

}
