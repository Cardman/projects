package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameItemTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
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
    public void searchTest() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(POTION);
        facadeGame_.setContentOfNameItem(null);
        facadeGame_.setContentOfDescriptionItem(null);
        facadeGame_.setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        facadeGame_.setMinPriceItem(null);
        facadeGame_.setMaxPriceItem(null);
        facadeGame_.setMinNumberItem(null);
        facadeGame_.setMaxNumberItem(null);
        facadeGame_.setNbResultsPerPageItem(1);
        facadeGame_.setDeltaItem(1);
        facadeGame_.setCmpPriceIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpPricePriorityItem(1);
        facadeGame_.setCmpNumberIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpNumberPriorityItem(2);
        facadeGame_.setCmpDescriptionIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpDescriptionPriorityItem(3);
        facadeGame_.setCmpNameIncreasingItem(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpNamePriorityItem(4);
        facadeGame_.searchObjectToUse();
        facadeGame_.endItem();
        facadeGame_.previousDeltaItem();
        facadeGame_.previousItem();
        facadeGame_.nextDeltaItem();
        facadeGame_.nextItem();
        facadeGame_.changePageItem(0);
        facadeGame_.beginItem();
        facadeGame_.checkLineItem(0);
        assertTrue(!facadeGame_.enabledNextItem());
        assertTrue(!facadeGame_.enabledPreviousItem());
        assertEq(1, facadeGame_.pagesItem());
        assertEq(1, facadeGame_.getRenderedItem().size());
        assertEq(0, facadeGame_.getNumberPageItem());
        assertEq(0, facadeGame_.getLineItem());
        facadeGame_.newSearchItem();
        facadeGame_.checkLineItem(0);
        assertTrue(!facadeGame_.enabledNextItem());
        assertTrue(!facadeGame_.enabledPreviousItem());
        assertEq(1, facadeGame_.getNbResultsPerPageItem());
        assertEq(1, facadeGame_.pagesItem());
        assertEq(1, facadeGame_.getRenderedItem().size());
        assertEq(0, facadeGame_.getNumberPageItem());
        assertEq(0, facadeGame_.getLineItem());
        facadeGame_.clearFoundResultsItems();
        facadeGame_.clearFiltersItem();
        facadeGame_.clearSortingItem();
    }
}
