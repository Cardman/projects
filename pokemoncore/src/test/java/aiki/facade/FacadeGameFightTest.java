package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.Rate;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameFightTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.setupMovingHeros();
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }

    @Test
    public void act1Test() {
        assertEq(PTITARD, facadeGame.getNicknameOrDefault(""));
        assertEq("NICKNAME", facadeGame.getNicknameOrDefault("NICKNAME"));
        facadeGame.attemptFlee(false);
        assertTrue(facadeGame.isEnabledMovingHero());
    }

    @Test
    public void act2Test() {
        facadeGame.attemptFlee(true);
        facadeGame.endRoundFightFlee();
        assertTrue(facadeGame.isEnabledMovingHero());
    }

    @Test
    public void act3Test() {
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.getPlayer().getItem(EAU_FRAICHE);
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.searchPokemonHealingItem();
        facadeGame.checkLineHealingItem(0);
        facadeGame.setChosenHealingItem();
        assertEq(EAU_FRAICHE,((ActionHeal)facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction()).getChosenHealingItem());
    }

    @Test
    public void act4Test() {
        facadeGame.getPlayer().getItem(MASTER_BALL);
        facadeGame.attemptCatchingWildPokemon(MASTER_BALL,false);
        assertEq(FightState.SURNOM, facadeGame.getFight().getState());
    }

    @Test
    public void act5Test() {
        facadeGame.getPlayer().getItem(MASTER_BALL);
        facadeGame.attemptCatchingWildPokemon(MASTER_BALL,false);
        facadeGame.catchWildPokemon("WILD");
        assertTrue(facadeGame.isEnabledMovingHero());
        assertEq(3,facadeGame.getPlayer().getTeam().size());
    }

    @Test
    public void act6Test() {
        facadeGame.getPlayer().getItem(MASTER_BALL);
        facadeGame.attemptCatchingWildPokemon(MASTER_BALL,true);
        facadeGame.endRoundFightSuccessBall();
        facadeGame.catchWildPokemon("WILD");
        assertTrue(facadeGame.isEnabledMovingHero());
        assertEq(3,facadeGame.getPlayer().getTeam().size());
    }

    @Test
    public void act7Test() {
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(JACKPOT);
        assertEq(1,facadeGame.sortedFightersBeginRoundWildFight().size());
        assertEq(2,facadeGame.sortedFightersBeginRoundWildFight().firstValue().size());
    }

    @Test
    public void act8Test() {
        facadeGame.getPlayer().getItem(MASTER_BALL);
        assertEq(1,facadeGame.calculateCatchingRates().size());
    }

    @Test
    public void act9Test() {
        assertEq(Rate.one(),facadeGame.calculateFleeingRate());
    }

    @Test
    public void act10Test() {
        assertEq(2,facadeGame.remainingThrowersTargetsHp().size());
    }

    @Test
    public void act11Test() {
        assertTrue(facadeGame.isExistingFight());
        assertTrue(facadeGame.isWildFight());
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
