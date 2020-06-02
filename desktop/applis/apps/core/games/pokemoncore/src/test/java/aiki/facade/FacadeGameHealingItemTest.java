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

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.*;

public final class FacadeGameHealingItemTest extends InitializationDataBase {

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
        facadeGame.setContentOfNameHealingItem(null);
        facadeGame.setContentOfDescriptionHealingItem(null);
        facadeGame.setContentOfStatusHealingItem(null);
        facadeGame.setSearchModeNameHealingItem(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeDescriptionHealingItem(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeStatusHealingItem(SearchingMode.WHOLE_STRING);
        facadeGame.setHealOneMoveHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setKoHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setRelativeRateHpHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setRelativeRatePpHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setStatisticHealingItem(null);
        facadeGame.setMinPriceHealingItem(null);
        facadeGame.setMaxPriceHealingItem(null);
        facadeGame.setMinNumberHealingItem(null);
        facadeGame.setMaxNumberHealingItem(null);
        facadeGame.setMinHpHealingItem(null);
        facadeGame.setMaxHpHealingItem(null);
        facadeGame.setMinPpHealingItem(null);
        facadeGame.setMaxPpHealingItem(null);
        facadeGame.setMinRateHpHealingItem(null);
        facadeGame.setMaxRateHpHealingItem(null);
        facadeGame.setNbResultsPerPageHealingItem(1);
        facadeGame.setDeltaHealingItem(1);
        facadeGame.setCmpPriceIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPricePriorityHealingItem(1);
        facadeGame.setCmpNumberIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNumberPriorityHealingItem(2);
        facadeGame.setCmpDescriptionIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpDescriptionPriorityHealingItem(3);
        facadeGame.setCmpNameIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNamePriorityHealingItem(4);
        facadeGame.setCmpRateHpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpRateHpPriorityHealingItem(5);
        facadeGame.setCmpPpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPpPriorityHealingItem(6);
        facadeGame.setCmpNbHealedStatusIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNbHealedStatusPriorityHealingItem(7);
        facadeGame.setCmpRelativeRateHpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpRelativeRateHpPriorityHealingItem(8);
        facadeGame.setCmpRelativeRatePpIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpRelativeRatePpPriorityHealingItem(9);
        facadeGame.setCmpStatisticsIncreasingHealingItem(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpStatisticsPriorityHealingItem(10);
        facadeGame.searchPokemonHealingItem();
        facadeGame.endHealingItem();
        facadeGame.previousDeltaHealingItem();
        facadeGame.previousHealingItem();
        facadeGame.nextDeltaHealingItem();
        facadeGame.nextHealingItem();
        facadeGame.changePageHealingItem(0);
        facadeGame.beginHealingItem();
        facadeGame.checkLineHealingItem(0);
        assertTrue(!facadeGame.enabledNextHealingItem());
        assertTrue(!facadeGame.enabledPreviousHealingItem());
        assertEq(1, facadeGame.pagesHealingItem());
        assertEq(1, facadeGame.getRenderedHealingItem().size());
        assertEq(0, facadeGame.getNumberPageHealingItem());
        assertEq(0, facadeGame.getLineHealingItem());
        facadeGame.newSearchHealingItem();
        facadeGame.checkLineHealingItem(0);
        assertTrue(!facadeGame.enabledNextHealingItem());
        assertTrue(!facadeGame.enabledPreviousHealingItem());
        assertEq(1, facadeGame.pagesHealingItem());
        assertEq(1, facadeGame.getRenderedHealingItem().size());
        assertEq(0, facadeGame.getNumberPageHealingItem());
        assertEq(0, facadeGame.getLineHealingItem());
        facadeGame.setLineHealingItem(0);
        assertTrue(!facadeGame.enabledNextHealingItem());
        assertTrue(!facadeGame.enabledPreviousHealingItem());
        assertEq(1, facadeGame.getNbResultsPerPageHealingItem());
        assertEq(1, facadeGame.pagesHealingItem());
        assertEq(1, facadeGame.getRenderedHealingItem().size());
        assertEq(0, facadeGame.getNumberPageHealingItem());
        assertEq(0, facadeGame.getLineHealingItem());
        facadeGame.clearFiltersHealingItem();
        facadeGame.clearSortingHealingItem();
    }
}
