package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;

import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import code.maths.LgInt;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class FacadeGameBuyTmTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 5));
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
    public void listTm1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        facadeGame_.addTmToBuy();
        assertEq(1,facadeGame_.getChosenTmForBuy().size());
        assertEq(TONNERRE,facadeGame_.getChosenTmForBuy().get(0));
        assertEq(LgInt.newLgInt("4000"),facadeGame_.amountTm());
        facadeGame_.buyTm();
        assertEq(0, facadeGame_.getPlayer().getInventory().gotTm().size());
        assertEq(LgInt.newLgInt("3000"),facadeGame_.getGame().getPlayer().getMoney());
    }

    @Test
    public void listTm2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().setMoney(LgInt.newLgInt("4000"));
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        facadeGame_.addTmToBuy();
        assertEq(1,facadeGame_.getChosenTmForBuy().size());
        assertEq(TONNERRE,facadeGame_.getChosenTmForBuy().get(0));
        assertEq(LgInt.newLgInt("4000"),facadeGame_.amountTm());
        assertTrue(facadeGame_.canBeBoughtTm());
        facadeGame_.buyTm();
        assertEq(1, facadeGame_.getPlayer().getInventory().gotTm().size());
        assertEq(LgInt.newLgInt("0"),facadeGame_.getGame().getPlayer().getMoney());
    }

    @Test
    public void listTm3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().setMoney(LgInt.newLgInt("4000"));
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        facadeGame_.addTmToBuy();
        facadeGame_.removeTmToBuy(TONNERRE);
        assertEq(0,facadeGame_.getChosenTmForBuy().size());
    }

    @Test
    public void listTm4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().setMoney(LgInt.newLgInt("4000"));
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        facadeGame_.addTmToBuy();
        facadeGame_.searchTmToBuy();
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, facadeGame_.getLineMove());
        assertEq(1,facadeGame_.getChosenTmForBuy().size());
        assertEq(TONNERRE,facadeGame_.getChosenTmForBuy().get(0));
        assertEq(LgInt.newLgInt("4000"),facadeGame_.amountTm());
        assertTrue(facadeGame_.canBeBoughtTm());
        facadeGame_.buyTm();
        assertEq(1, facadeGame_.getPlayer().getInventory().gotTm().size());
        assertEq(LgInt.newLgInt("0"),facadeGame_.getGame().getPlayer().getMoney());
    }

    @Test
    public void listTm5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().setMoney(LgInt.newLgInt("4000"));
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        facadeGame_.addTmToBuy();
        facadeGame_.removeTmToBuy(TONNERRE);
        facadeGame_.searchTmToBuy();
        facadeGame_.checkLineMove(0);
        assertEq(0, facadeGame_.getLineMove());
    }

}
