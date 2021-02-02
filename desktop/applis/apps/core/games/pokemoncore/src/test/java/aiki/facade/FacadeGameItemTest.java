package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Before;
import org.junit.Test;

public final class FacadeGameItemTest extends InitializationDataBase {

    private DataBase data;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }
    @Test
    public void searchTest() {
        facadeGame.getPlayer().getItem(POTION);
        facadeGame.setContentOfNameItem(null);
        facadeGame.setContentOfDescriptionItem(null);
        facadeGame.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        facadeGame.setMinPriceItem(null);
        facadeGame.setMaxPriceItem(null);
        facadeGame.setMinNumberItem(null);
        facadeGame.setMaxNumberItem(null);
        facadeGame.setNbResultsPerPageItem(1);
        facadeGame.setDeltaItem(1);
        facadeGame.setCmpPriceIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPricePriorityItem(1);
        facadeGame.setCmpNumberIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNumberPriorityItem(2);
        facadeGame.setCmpDescriptionIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpDescriptionPriorityItem(3);
        facadeGame.setCmpNameIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNamePriorityItem(4);
        facadeGame.searchObjectToUse();
        facadeGame.endItem();
        facadeGame.previousDeltaItem();
        facadeGame.previousItem();
        facadeGame.nextDeltaItem();
        facadeGame.nextItem();
        facadeGame.changePageItem(0);
        facadeGame.beginItem();
        facadeGame.checkLineItem(0);
        assertTrue(!facadeGame.enabledNextItem());
        assertTrue(!facadeGame.enabledPreviousItem());
        assertEq(1, facadeGame.pagesItem());
        assertEq(1, facadeGame.getRenderedItem().size());
        assertEq(0, facadeGame.getNumberPageItem());
        assertEq(0, facadeGame.getLineItem());
        facadeGame.newSearchItem();
        facadeGame.checkLineItem(0);
        assertTrue(!facadeGame.enabledNextItem());
        assertTrue(!facadeGame.enabledPreviousItem());
        assertEq(1, facadeGame.getNbResultsPerPageItem());
        assertEq(1, facadeGame.pagesItem());
        assertEq(1, facadeGame.getRenderedItem().size());
        assertEq(0, facadeGame.getNumberPageItem());
        assertEq(0, facadeGame.getLineItem());
        facadeGame.clearFoundResultsItems();
        facadeGame.clearFiltersItem();
        facadeGame.clearSortingItem();
    }
}
