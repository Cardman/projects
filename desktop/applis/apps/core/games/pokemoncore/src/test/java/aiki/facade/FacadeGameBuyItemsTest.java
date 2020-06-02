package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
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

public final class FacadeGameBuyItemsTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 4));
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
    public void listItem1Test() {
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setContentOfNameItem(HYPER_BALL);
        facadeGame.searchObjectToBuyOrSell(true);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("2"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("2000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(POTION,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.amount());
    }

    @Test
    public void listItem2Test() {
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setContentOfNameItem(HYPER_BALL);
        facadeGame.searchObjectToBuyOrSell(true);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("2"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("2000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(POTION,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.amount());
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame.amount());
    }

    @Test
    public void listItem3Test() {
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setContentOfNameItem(HYPER_BALL);
        facadeGame.searchObjectToBuyOrSell(true);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.buyOrSellItems(true);
        assertEq(LgInt.newLgInt("3000"),game.getPlayer().getMoney());
        assertEq(LgInt.newLgInt("0"),game.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void listItem4Test() {
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setContentOfNameItem(HYPER_BALL);
        facadeGame.searchObjectToBuyOrSell(true);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertTrue(facadeGame.canBeBought());
        facadeGame.buyOrSellItems(true);
        assertEq(LgInt.newLgInt("1000"),game.getPlayer().getMoney());
        assertEq(LgInt.newLgInt("2"),game.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void listItem5Test() {
        facadeGame.clearItemsToBuyOrSell();
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setContentOfNameItem(HYPER_BALL);
        facadeGame.searchObjectToBuyOrSell(true);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.buyOrSellItems(true);
        facadeGame.searchObjectToBuyOrSell(false);
        facadeGame.checkLineItem(0);
        facadeGame.addItemToBuyOrSell();
        facadeGame.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame.buyOrSellItems(false);
        assertEq(LgInt.newLgInt("3000"),game.getPlayer().getMoney());
        assertEq(LgInt.newLgInt("0"),game.getPlayer().getInventory().getNumber(HYPER_BALL));
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
