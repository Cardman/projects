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
import org.junit.Test;

public final class FacadeGameBuyItemsTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 4));
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
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }

    @Test
    public void listItem1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setContentOfNameItem(HYPER_BALL);
        facadeGame_.searchObjectToBuyOrSell(true);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("2"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("2000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(POTION,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.amount());
    }

    @Test
    public void listItem2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setContentOfNameItem(HYPER_BALL);
        facadeGame_.searchObjectToBuyOrSell(true);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("2"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("2000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(POTION,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("1"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("1000"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.amount());
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,false);
        assertEq(1,facadeGame_.getChosenItemsForBuyOrSell().size());
        assertEq(HYPER_BALL,facadeGame_.getChosenItemsForBuyOrSell().getKey(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.getChosenItemsForBuyOrSell().getValue(0));
        assertEq(LgInt.newLgInt("0"),facadeGame_.amount());
    }

    @Test
    public void listItem3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setContentOfNameItem(HYPER_BALL);
        facadeGame_.searchObjectToBuyOrSell(true);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.buyOrSellItems(true);
        assertEq(LgInt.newLgInt("3000"),facadeGame_.getGame().getPlayer().getMoney());
        assertEq(LgInt.newLgInt("0"),facadeGame_.getGame().getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void listItem4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setContentOfNameItem(HYPER_BALL);
        facadeGame_.searchObjectToBuyOrSell(true);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        assertTrue(facadeGame_.canBeBought());
        facadeGame_.buyOrSellItems(true);
        assertEq(LgInt.newLgInt("1000"),facadeGame_.getGame().getPlayer().getMoney());
        assertEq(LgInt.newLgInt("2"),facadeGame_.getGame().getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void listItem5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.clearItemsToBuyOrSell();
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setContentOfNameItem(HYPER_BALL);
        facadeGame_.searchObjectToBuyOrSell(true);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.buyOrSellItems(true);
        facadeGame_.searchObjectToBuyOrSell(false);
        facadeGame_.checkLineItem(0);
        facadeGame_.addItemToBuyOrSell();
        facadeGame_.addOrRemoveItemToBuyOrSell(HYPER_BALL,true);
        facadeGame_.buyOrSellItems(false);
        assertEq(LgInt.newLgInt("3000"),facadeGame_.getGame().getPlayer().getMoney());
        assertEq(LgInt.newLgInt("0"),facadeGame_.getGame().getPlayer().getInventory().getNumber(HYPER_BALL));
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
