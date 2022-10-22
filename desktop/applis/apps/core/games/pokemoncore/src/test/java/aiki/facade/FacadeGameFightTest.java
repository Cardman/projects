package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.Rate;
import org.junit.Test;

public final class FacadeGameFightTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
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
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.getPlayer().getItem(EAU_FRAICHE);
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.searchPokemonHealingItem();
        facadeGame_.checkLineHealingItem(0);
        facadeGame_.setChosenHealingItem();
        assertEq(EAU_FRAICHE,((ActionHeal)facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction()).getChosenHealingItem());
    }

    @Test
    public void act4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.attemptCatchingWildPokemon(MASTER_BALL,false);
        assertEq(FightState.SURNOM, facadeGame_.getFight().getState());
    }

    @Test
    public void act5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.attemptCatchingWildPokemon(MASTER_BALL,false);
        facadeGame_.catchWildPokemon("WILD");
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act6Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        facadeGame_.attemptCatchingWildPokemon(MASTER_BALL,true);
        facadeGame_.endRoundFightSuccessBall();
        facadeGame_.catchWildPokemon("WILD");
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act7Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(JACKPOT);
        assertEq(1,facadeGame_.sortedFightersBeginRoundWildFight().size());
        assertEq(2,facadeGame_.sortedFightersBeginRoundWildFight().firstValue().size());
    }

    @Test
    public void act8Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(MASTER_BALL);
        assertEq(1,facadeGame_.calculateCatchingRates().size());
    }

    @Test
    public void act9Test() {
        FacadeGame facadeGame_ = initTests();
        assertEq(Rate.one(),facadeGame_.calculateFleeingRate());
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
