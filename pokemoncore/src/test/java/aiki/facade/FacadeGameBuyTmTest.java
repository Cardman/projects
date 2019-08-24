package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;

import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameBuyTmTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 5));
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
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }

    @Test
    public void listTm1Test() {
        facadeGame.searchTmToBuy();
        facadeGame.checkLineMove(0);
        facadeGame.addTmToBuy();
        assertEq(1,facadeGame.getChosenTmForBuy().size());
        assertEq(TONNERRE,facadeGame.getChosenTmForBuy().get(0));
        assertEq(LgInt.newLgInt("4000"),facadeGame.amountTm());
        facadeGame.buyTm();
        assertEq(0, facadeGame.getPlayer().getInventory().gotTm().size());
        assertEq(LgInt.newLgInt("3000"),game.getPlayer().getMoney());
    }

    @Test
    public void listTm2Test() {
        game.getPlayer().setMoney(LgInt.newLgInt("4000"));
        facadeGame.searchTmToBuy();
        facadeGame.checkLineMove(0);
        facadeGame.addTmToBuy();
        assertEq(1,facadeGame.getChosenTmForBuy().size());
        assertEq(TONNERRE,facadeGame.getChosenTmForBuy().get(0));
        assertEq(LgInt.newLgInt("4000"),facadeGame.amountTm());
        assertTrue(facadeGame.canBeBoughtTm());
        facadeGame.buyTm();
        assertEq(1, facadeGame.getPlayer().getInventory().gotTm().size());
        assertEq(LgInt.newLgInt("0"),game.getPlayer().getMoney());
    }
    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
